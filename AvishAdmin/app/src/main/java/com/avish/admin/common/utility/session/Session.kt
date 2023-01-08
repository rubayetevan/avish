package com.avish.admin.common.utility.session

interface Session<T> {

    fun isLoggedIn(): Boolean

    fun isAdmin(): Boolean

    fun logOut()

    fun createSession(sessionData: T)

    fun getSessionData(): T

    fun  updateToken(refreshToken:String,accessToken:String)

    fun getAccessToken():String

    fun getRefreshToken():String

    fun getUserName():String

    fun getUserId():String
}