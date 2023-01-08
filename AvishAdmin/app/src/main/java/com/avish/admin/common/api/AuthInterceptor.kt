package com.avish.admin.common.api

import android.util.Log
import com.avish.admin.common.utility.DispatcherProvider
import com.avish.admin.common.utility.session.Session
import com.avish.admin.models.SessionData
import com.avish.admin.models.TokenRequestModel
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.net.HttpURLConnection.HTTP_UNAUTHORIZED
import javax.inject.Inject
import javax.inject.Provider

class AuthInterceptor @Inject constructor(
    private val avishRestApi: Provider<AvishRestApi>,
    private val dispatcherProvider: DispatcherProvider,
    private val session: Session<SessionData>
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        //Log.d("AuthInterceptor","intercept: ${avishRestApi.get()}")

        val req = chain.request()
        val currentToken = session.getSessionData().accessToken
        val originalResponse = chain.proceedWithToken(req, currentToken)
        if (originalResponse.code != HTTP_UNAUTHORIZED || currentToken == null) {
            return originalResponse
        }

        val tokenRequestModel = TokenRequestModel(session.getSessionData().userName,session.getSessionData().refreshToken)

        val newSession: SessionData? = runBlocking(dispatcherProvider.io) {
            val newTokenRes = avishRestApi.get().getNewToken(tokenRequestModel)
            if (newTokenRes.isSuccessful)
                newTokenRes.body()
            else
                null
        }

        newSession?.let { s ->
            s.accessToken?.let { a ->
                s.refreshToken?.let { r ->
                    session.updateToken(a, r)
                }
            }
        }

        return if (newSession != null && newSession.refreshToken !== null) chain.proceedWithToken(
            req,
            newSession.refreshToken
        ) else originalResponse
    }

    private fun Interceptor.Chain.proceedWithToken(req: Request, token: String?): Response =
        req.newBuilder()
            .apply {
                if (token !== null) {
                    addHeader("token", "Bearer $token")
                }
            }
            .build()
            .let(::proceed)

}