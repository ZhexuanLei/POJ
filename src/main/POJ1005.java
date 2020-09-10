package main;

import java.util.Scanner;

//http://poj.org/problem?id=1005

public class POJ1005 {
	
	public static int calc(Double cord1, Double cord2) {
		int year = (int)(0.5 * Math.PI * (cord1*cord1 + cord2*cord2) / 50 + 1);
		return year;
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int count = input.nextInt();
		for (int i = 0; i < count; i++) {
			Double cordX = input.nextDouble();
			Double cordY = input.nextDouble();
			System.out.println("Property " + (i+1) + ": " + "This property will begin eroding in year " + calc(cordX, cordY) + ".");
		}
		System.out.println("END OF OUTPUT.");
	}

}
