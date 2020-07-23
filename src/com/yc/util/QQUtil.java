package com.yc.util;

import java.util.Random;

import com.yc.dao.QQInfoDao;

public class QQUtil {

	public static String getRandomQQ() {
		String val = "";
		Random random = new Random();
		for (int i = 0; i < 8; i++) {
			val += String.valueOf(random.nextInt(10));
		}
		return val;
	}
	public static String getRandomGno() {
		String val = "";
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			val += String.valueOf(random.nextInt(10));
		}
		return val;
	}

}
