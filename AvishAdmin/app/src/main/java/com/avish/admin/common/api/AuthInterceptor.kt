package com.avish.admin.common.api

import android.util.Log
import com.avish.admin.BuildConfig
import com.avish.admin.common.utility.DispatcherProvider
import com.avish.admin.common.utility.session.Session
import com.avish.admin.models.SessionData
import com.avish.admin.models.TokenRequestModel
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
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

    private  val mutex = Mutex(true)

    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()
        val currentToken = session.getAccessToken()
        val originalResponse = chain.proceedWithToken(originalRequest, currentToken)

        if(BuildConfig.DEBUG) {
            Log.d("AuthInterceptor", "request: $originalRequest")
            Log.d("AuthInterceptor", "response: $originalResponse body: ${originalResponse.body.toString()}")
        }

        if (originalResponse.code != 498) {
            return originalResponse
        }


        val newSession: SessionData? = runBlocking(dispatcherProvider.io) {
            mutex.withLock {
                val tokenRequestModel = TokenRequestModel(
                    userName = session.getUserName(),
                    refreshToken = session.getRefreshToken()
                )
                val newTokenResponse = avishRestApi.get().getNewToken(tokenRequestModel)
                if (newTokenResponse.isSuccessful)
                    newTokenResponse.body()
                else
                    null
            }
        }

        newSession?.let { s ->
            s.accessToken?.let { aToken ->
                s.refreshToken?.let { rToken ->
                    session.updateToken(refreshToken = rToken, accessToken = aToken)
                }
            }
        }

        return if (newSession != null && newSession.accessToken !== null) chain.proceedWithToken(
            originalRequest,
            newSession.accessToken
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