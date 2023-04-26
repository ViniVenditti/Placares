package com.venditti.placares.helper;

import java.text.SimpleDateFormat;

public class DateCustom {

    public static String dataAtual(){
        long date = System.currentTimeMillis();
        SimpleDateFormat s = new SimpleDateFormat("ddMMyyyy");
        return s.format(date);
    }

    public static String mesAno(String data){
        String[] split = data.split("/");
        return split[1]+split[2];
    }

}
