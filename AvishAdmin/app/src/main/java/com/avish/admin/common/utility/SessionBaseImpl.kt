package com.avish.admin.common.utility

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.avish.admin.BuildConfig

abstract class  SessionBaseImpl <T> (context: Context):Session<T> {

    private val keyIsLoggedIn: String = "isLoggedIn"

    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    val sharedPreferences = EncryptedSharedPreferences.create(
        BuildConfig.APPLICATION_ID,
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    override fun isLoggedIn():Boolean = sharedPreferences.getBoolean(keyIsLoggedIn,false)

    override fun logOut(){
        sharedPreferences
            .edit()
            .putBoolean(keyIsLoggedIn, false)
            .apply()
    }

    override fun createSession(sessionData:T){
        sharedPreferences
            .edit()
            .putBoolean(keyIsLoggedIn, true)
            .apply()
    }

    abstract override fun getSessionData():T
}