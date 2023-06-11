package com.example.phishguard.network.data

data class User (
    var id: Int,
    var username: String,
    val password: String,
    var token: String
) {
    constructor(username: String, password: String)
            : this(id = 0, username = username, password = password, token = "")

    constructor(id: Int, username: String, token: String)
            : this(id = id, username = username, password = "", token = token)

    override fun toString(): String {
        return "${id} ${username} ${token}"
    }
}
