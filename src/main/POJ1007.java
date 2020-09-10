package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

//http://poj.org/problem?id=1007

public class POJ1007 {
	
	public static class seqStr{
		private String val;
		private int numofInput;
		private int revNum;
		
		public seqStr(String val, int numofInput) {
			this.val = val;
			this.numofInput = numofInput;
			this.revNum = calcRevNum(val);
		}
		
		public String getVal() {
			return this.val;
		}
		
		public int getSeq() {
			return this.numofInput;
		}
		
		public int getRevNum() {
			return this.revNum;
		}
	}
	
	public static int calcRevNum(String str) {
		int ret = 0;
		for (int i = 0; i < str.length(); i++) {
			for (int j = i+1; j < str.length(); j++) {
				if (str.charAt(j) < str.charAt(i)) {
					ret ++;
				}
			}
		}
		return ret;
	}
		
	public static class MyComparator implements Comparator {
		@Override
		public int compare(Object o1, Object o2) {
			int ret = 1;
			seqStr ss1 = (seqStr)o1;
			seqStr ss2 = (seqStr)o2;
			if (ss1.getRevNum() == ss2.getRevNum()) {
				ret = ss1.getSeq() - ss2.getSeq();
			}
			else {
				ret = ss1.getRevNum()-ss2.getRevNum();
			}
			return ret;
	}
		
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ArrayList<seqStr> arrStr = new ArrayList<seqStr>();
		int size = input.nextInt();
		int count = input.nextInt();
		String useless = input.nextLine();
		for (int i = 0; i < count; i++) {
			String inStr = input.nextLine();
			arrStr.add(new seqStr(inStr, i));
		}
		
		Collections.sort(arrStr, new MyComparator());
		for (seqStr ss : arrStr) {
			System.out.println(ss.getVal() + " " + ss.getRevNum()); //题目不要求输出反序数
		}
		
		input.close();
	}

}
