package com.avish.admin.common.api

import com.avish.admin.common.utility.DispatcherProvider
import com.avish.admin.common.utility.session.Session
import com.avish.admin.models.SessionData
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.net.HttpURLConnection.HTTP_UNAUTHORIZED
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val avishRestApi: AvishRestApi,
    private val dispatcherProvider: DispatcherProvider,
    private val  session: Session<SessionData>
) :Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val req = chain.request()
        val token = session.getSessionData().accessToken
        val res = chain.proceedWithToken(req, token)
        if (res.code != HTTP_UNAUTHORIZED || token == null) {
            return res
        }

        val newToken: String? = runBlocking(dispatcherProvider.io) {
            null
            //TODO: NOT IMPLEMENTED
        }

        return if (newToken !== null) chain.proceedWithToken(req, newToken) else res
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