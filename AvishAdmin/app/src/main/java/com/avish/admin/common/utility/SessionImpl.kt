package com.avish.admin.common.utility

import android.content.Context
import com.avish.admin.models.SessionData
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionImpl @Inject constructor(@ApplicationContext appContext: Context) : SessionBaseImpl<SessionData>(appContext) {

    private val keyEmail = "email"
    private val keyUsername = "userName"
    private val keyToken = "token"
    private val keyAdmin = "isAdmin"
    private val keyId = "id"

    override fun createSession(sessionData: SessionData) {
        super.createSession(sessionData)

        with(sharedPreferences.edit()) {
            putString(keyId, sessionData.id)
            putString(keyEmail, sessionData.email)
            putString(keyUsername, sessionData.userName)
            putString(keyToken, sessionData.accessToken)
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
            putBoolean(keyAdmin, false)
            apply()
        }
    }


}