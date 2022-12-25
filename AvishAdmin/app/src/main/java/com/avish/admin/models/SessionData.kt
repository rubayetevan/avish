package com.avish.admin.models

import com.google.gson.annotations.SerializedName

data class SessionData(
    @SerializedName("_id"         ) var id          : String?  = null,
    @SerializedName("userName"    ) var userName    : String?  = null,
    @SerializedName("email"       ) var email       : String?  = null,
    @SerializedName("isAdmin"     ) var isAdmin     : Boolean,
    @SerializedName("accessToken" ) var accessToken : String?  = null
)
