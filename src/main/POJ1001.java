package main;

import java.math.BigDecimal;
import java.util.Scanner;

//http://poj.org/problem?id=1001

public class POJ1001 {
//  大数的高精度计算,使用BigDecimal类
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		do {
			BigDecimal bd = new BigDecimal(input.next());
			int e = input.nextInt();
			BigDecimal res = bd.pow(e);
//			去除末尾多余的0并阻止使用科学计数法
			String ret = res.stripTrailingZeros().toPlainString(); 
//			如果数小于1则不输出小数点前的0
			if (ret.charAt(0) == '0') {
				ret = ret.substring(1, ret.length());
			}
			System.out.println(ret);
		} while (input.hasNextLine()); 
//		此处涉及了Scanner的一些用法,实现了多行输入
		
		input.close();
	}

}
