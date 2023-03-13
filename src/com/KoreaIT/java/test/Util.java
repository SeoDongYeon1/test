package com.KoreaIT.java.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	public static String getNowDateStr() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 
        Date now = new Date();
 
        String nowTime = sdf.format(now);
        return nowTime;
	}
}
