package com.venditti.placares.helper

import java.text.SimpleDateFormat

object DateCustom {
    fun dataAtual(): String {
        val date = System.currentTimeMillis()
        val s = SimpleDateFormat("ddMMyyyy")
        return s.format(date)
    }
}