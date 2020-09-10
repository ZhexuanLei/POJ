package main;

import java.util.Scanner;

//http://poj.org/problem?id=1004

public class POJ1004 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		double sum = 0.0;
		for (int i = 0; i < 12; i++) {
			sum += input.nextDouble();
		}
		String res = String.format("%.2f", sum/12);
		System.out.println("$" + res);
		
		input.close();
	}

}
