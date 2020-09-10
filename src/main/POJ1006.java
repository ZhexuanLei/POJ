package main;

import java.util.Scanner;

//http://poj.org/problem?id=1006

public class POJ1006 {
//  中国剩余定理
	private final static int physPeriod = 23;
	private final static int emotPeriod = 28;
	private final static int intelPeriod = 33;
	
	public static int CRT(int p, int e, int i) {
		p %= physPeriod;
		e %= emotPeriod;
		i %= intelPeriod;
		int x1 = search(emotPeriod*intelPeriod, physPeriod, p);
		int x2 = search(physPeriod*intelPeriod, emotPeriod, e);
		int x3 = search(physPeriod*emotPeriod, intelPeriod, i);
		int nextTriPeak = x1 + x2 + x3;
		
		return nextTriPeak;
		
	}
	
	public static int search(int subNum, int div, int rem) {
		int ret = 0;
		int mul = 1;
		while (ret == 0) {
			int prod = subNum * mul;
			if (prod % div == rem) {
				ret = prod;
			}
			else {mul++;}
		}
		return ret;
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String[] paras = input.nextLine().split(" ");
		int no = 1;
		while (! paras[0].equals("-1")) {
			int phys = Integer.parseInt(paras[0]);
			int emot = Integer.parseInt(paras[1]);
			int intel = Integer.parseInt(paras[2]);
			int iniDate = Integer.parseInt(paras[3]);
			int comMul = physPeriod*emotPeriod*intelPeriod;
			int dis = CRT(phys, emot, intel)-iniDate;
			while (dis > comMul) {dis -= comMul;}
			System.out.println("Case " + no + ": " + "the next triple peak occurs in " + dis + " days.");
			no ++;
			paras = input.nextLine().split(" ");
		}
		
		input.close();
	}

}
