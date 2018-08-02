package com.algo.timer;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yzy yezhangyuan on 2017/8/31 0031.
 */
public class FormatUtil {

	public static void main(String[] args){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String s = sdf.format(new Date());
		System.out.println(s);
	}
}
