package com.upt.cti.smartwallet;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AppState {
    public static String getCurrentTimeDate() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        return sdfDate.format(now);
    }

}
