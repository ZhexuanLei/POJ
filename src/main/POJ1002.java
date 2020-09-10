package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

//http://poj.org/problem?id=1002

public class POJ1002 {
	private static HashMap<String, Integer> mappingTable = new HashMap<String, Integer>();
	private static HashMap<String, Integer> counts = new HashMap<String, Integer>();
	private static HashSet<String> numSet = new HashSet<String>();
	
//	将输入的字符串转化为由7个数字组成的字符串
	public static String translate(String oriStr) {
		StringBuffer ret = new StringBuffer(); 
		for (int i = 0; i < oriStr.length(); i ++) {
			String ch = String.valueOf(oriStr.charAt(i));
			if (Character.isUpperCase(ch.charAt(0))) {
				ret.append(mappingTable.get(ch));
			}
			else if (Character.isDigit(ch.charAt(0))) {
				ret.append(String.valueOf(ch));
			}
		}
		
		return ret.toString();
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
//		两个用于构造mappingTable的变量
		int loopCount = 1;
		int coresNum = 2;
		
//		构造字母对应数字的Map
		for (char ch = 'A'; ch <= 'Z'; ch ++) {
			String cha = String.valueOf(ch);
			if (ch != 'Q' && ch != 'Z') {
				if (loopCount % 3 != 0) {
					mappingTable.put(cha, coresNum);
				}
				else {
					mappingTable.put(cha, coresNum ++);
				}
				loopCount ++;
			}
			
		}
		
//		System.out.println(mappingTable);
//		String str = "ITS-EASY";
//		System.out.println(translate(str));

//		读入电话簿,统计数量
		int num = input.nextInt();
		String useless = input.nextLine();
		for (int i = 0; i < num; i++) {
			String inStr = input.nextLine();
			String cleanedStr = translate(inStr);
			numSet.add(cleanedStr);
			if (counts.containsKey(cleanedStr)) {
				int count = counts.get(cleanedStr);
				count ++;
				counts.put(cleanedStr, count);
			}
			else {
				counts.put(cleanedStr, 1);
			}
		}
		
		ArrayList<String> numList = new ArrayList<String>();
		for (String str : numSet) {
			numList.add(str);
		}
		Collections.sort(numList);
		int totalDupe = 0;
		for (String tels : numList) {
			int count = counts.get(tels);
			if (count > 1) {
				System.out.println(tels.substring(0, 3) + "-" + tels.substring(3, 7) + " " + count);
				totalDupe ++;
			}
		}
		if (totalDupe == 0) {System.out.println("No duplicates.");}
				
		input.close();
	}

}
