package com.example.phishguard.network.data

import com.google.gson.annotations.SerializedName

data class AccessInfo (
    var id: Int = -1,
    var username: String = "",
    @SerializedName("access_token")
    var accessToken: String = ""
)