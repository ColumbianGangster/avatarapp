package com.example.linearalgebra.avatarapp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by LinearAlgebra on 09/09/2016.
 */
public class DateUtils {
    public static Date convertToDate(String date) {
        Date d = null;
        try {
            d = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }
    public static String convertToString(Date date) {
        return new SimpleDateFormat("MMMM yyyy").format(date);
    }

    public static String ordinalDay(Date date){
        String[] suffixes =
                //    0     1     2     3     4     5     6     7     8     9
                { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th",
                        //    10    11    12    13    14    15    16    17    18    19
                        "th", "th", "th", "th", "th", "th", "th", "th", "th", "th",
                        //    20    21    22    23    24    25    26    27    28    29
                        "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th",
                        //    30    31
                        "th", "st" };
        SimpleDateFormat formatDayOfMonth  = new SimpleDateFormat("d");
        int day = Integer.parseInt(formatDayOfMonth.format(date));
        return day + suffixes[day];
    }
}
