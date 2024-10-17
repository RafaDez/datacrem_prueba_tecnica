package com.example.datacrm_app.utils

import java.security.MessageDigest

class KeyGenerator {
    fun generateKey(token: String): String {
        val accessKey: String = "6StH3Drqc9mdmOp2"
        val combined = token + accessKey
        val md = MessageDigest.getInstance("MD5")
        val digest = md.digest(combined.toByteArray())
        return digest.joinToString("") { "%02x".format(it) }
    }
}