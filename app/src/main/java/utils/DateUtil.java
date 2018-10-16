package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Shamyyoun on 2/22/2015.
 */
public class DateUtil {
    public static Date convertToDate(String strDate, String strFormat) {
        Date date;
        try {
            SimpleDateFormat format = new SimpleDateFormat(strFormat);
            date = format.parse(strDate);
        } catch (ParseException e) {
            date = null;
            e.printStackTrace();
        }

        return date;
    }

    public static String convertToString(Date date, String strFormat) {
        String strDate;
        try {
            SimpleDateFormat format = new SimpleDateFormat(strFormat);
            strDate = format.format(date);
        } catch (Exception e) {
            strDate = null;
            e.printStackTrace();
        }

        return strDate;
    }
}
