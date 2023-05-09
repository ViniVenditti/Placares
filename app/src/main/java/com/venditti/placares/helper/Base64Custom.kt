package com.venditti.placares.helper

import android.util.Base64

object Base64Custom {
    fun codificarBase64(text: String): String {
        return Base64.encodeToString(text.toByteArray(), Base64.NO_WRAP).replace("(\\n|\\r)", "")
    }

    fun decodificarBase64(text: String?): String {
        return String(Base64.decode(text, Base64.DEFAULT))
    }
}