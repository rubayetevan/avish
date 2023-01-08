package com.avish.admin.common.utility.session

import android.content.Context
import com.avish.admin.models.SessionData
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionImpl @Inject constructor(@ApplicationContext appContext: Context) :
    SessionBaseImpl<SessionData>(appContext) {

    private val keyEmail = "email"
    private val keyUsername = "userName"
    private val keyToken = "token"
    private val keyRefreshToken = "rToken"
    private val keyAdmin = "isAdmin"
    private val keyId = "id"


    override fun createSession(sessionData: SessionData) {
        super.createSession(sessionData)

        with(sharedPreferences.edit()) {
            putString(keyId, sessionData.id)
            putString(keyEmail, sessionData.email)
            putString(keyUsername, sessionData.userName)
            putString(keyToken, sessionData.accessToken)
            putString(keyRefreshToken, sessionData.refreshToken)
            putBoolean(keyAdmin, sessionData.isAdmin)
            apply()
        }
    }

    override fun getSessionData(): SessionData {
        val id = sharedPreferences.getString(keyId, "")
        val email = sharedPreferences.getString(keyEmail, "")
        val userName = sharedPreferences.getString(keyUsername, "")
        val token = sharedPreferences.getString(keyToken, "")
        val isAdmin = sharedPreferences.getBoolean(keyAdmin, false)
        return SessionData(
            id = id,
            userName = userName,
            email = email,
            isAdmin = isAdmin,
            accessToken = token
        )
    }

    override fun logOut() {
        super.logOut()
        with(sharedPreferences.edit()) {
            putString(keyId, "")
            putString(keyEmail, "")
            putString(keyUsername, "")
            putString(keyToken, "")
            putString(keyRefreshToken, "")
            putBoolean(keyAdmin, false)
            apply()
        }
    }

    override fun updateToken(refreshToken: String, accessToken: String) {
        with(sharedPreferences.edit()) {
            putString(keyToken, accessToken)
            putString(keyRefreshToken, refreshToken)
            apply()
        }
    }

    override fun isAdmin(): Boolean = sharedPreferences.getBoolean(keyAdmin, false)

    override fun getAccessToken(): String = sharedPreferences.getString(keyToken, "") ?: ""

    override fun getUserName(): String = sharedPreferences.getString(keyUsername, "") ?: ""

    override fun getUserId(): String = sharedPreferences.getString(keyId, "") ?: ""

    override fun getRefreshToken(): String = sharedPreferences.getString(keyRefreshToken,
        "") ?: ""


}