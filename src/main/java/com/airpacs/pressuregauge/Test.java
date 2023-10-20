package com.airpacs.pressuregauge;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
	public static void main(String[] args) {
		String value = "hi my name   is    345-567 and   address is 897-564-3454";
		
		/*
		 * StringTokenizer sT = new StringTokenizer(value," ");
		 * while(sT.hasMoreElements()) { String val = sT.nextToken();
		 * if(val.contains("-")) System.out.println(val); }
		 */
	
		
		Pattern p = Pattern.compile("-?\\d+");
		Matcher m = p.matcher(value);
		while (m.find()) {
		  System.out.println(m.group());
		}
		
	}
}
