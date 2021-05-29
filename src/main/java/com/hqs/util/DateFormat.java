package com.hqs.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {
    //HH为24h制
    private static final String formatString = "yyyy-MM-dd HH:mm:ss";
    private static final String formatInputString = "MM/dd/yyyy - HH:mm";
    private static SimpleDateFormat sd;
    static {
        sd = new SimpleDateFormat (formatString);
    }
    public static String dateFormat(Date date){
        return sd.format (date);
    }
    public static Date dateParse(String time) throws ParseException {
        SimpleDateFormat inputFormat = new SimpleDateFormat (formatInputString);
        return inputFormat.parse (time);
    }
}
