package com.avish.admin.common.utility.session

interface Session<T> {

    fun isLoggedIn():Boolean

    fun logOut()

    fun createSession(sessionData:T)

    fun getSessionData():T
}