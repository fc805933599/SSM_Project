package cn.itcast.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

        public static String DateToStr (Date date) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String format = sdf.format(date);
            return format;
        }


        public static Date StrToDate (String Str) throws ParseException {
            SimpleDateFormat sdf = new SimpleDateFormat();
            Date parse = sdf.parse(Str);
            return parse;
        }
}
