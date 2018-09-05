package com.example.admin.something;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeHandler {
    public static SimpleDateFormat format;

    static {
        format = new SimpleDateFormat("yyyy / M / d");
    }

    public static String systemToClient(long time) {
        return format.format(new Date(time));
    }

    public static long clientToSystem(String time) {
        try {
            return format.parse(time).getTime();
        }
        catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
