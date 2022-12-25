package com.avish.admin.common.utility

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.avish.admin.BuildConfig

abstract class  SessionManager <T> (context: Context) {

    private val keyIsLoggedIn: String = "isLoggedIn"

    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    val sharedPreferences = EncryptedSharedPreferences.create(
        BuildConfig.APPLICATION_ID,
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun isLoggedIn():Boolean = sharedPreferences.getBoolean(keyIsLoggedIn,false)

    open fun logOut(){
        sharedPreferences
            .edit()
            .putBoolean(keyIsLoggedIn, false)
            .apply()
    }

    open fun createSession(sessionData:T){
        sharedPreferences
            .edit()
            .putBoolean(keyIsLoggedIn, true)
            .apply()
    }

    abstract fun getSessionData():T
}