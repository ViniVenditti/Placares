package com.venditti.placares.helper;

import android.util.Base64;

public class Base64Custom {

    public static String codificarBase64(String text){
        return Base64.encodeToString(text.getBytes(), Base64.NO_WRAP).replace("(\\n|\\r)", "");
    }

    public static String decodificarBase64(String text){
        return new String(Base64.decode(text, Base64.DEFAULT));
    }

}
