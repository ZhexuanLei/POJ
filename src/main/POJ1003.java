package main;

import java.util.ArrayList;
import java.util.Scanner;

//http://poj.org/problem?id=1003

public class POJ1003 {
	private static ArrayList<Double> valueList = new ArrayList<Double>();
	
	public static Double sum(int n) {
		Double sum = 0.0;
		for (int i = 2; i<n+1; i++) {
			sum += 1.0 / i;
		}
		return sum;
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		valueList.add(0.0);
		valueList.add(0.0);
		valueList.add(0.5);
		Double num = input.nextDouble();
		while (num > 0) {
			if (num > valueList.get(valueList.size()-1)) {
				while (sum(valueList.size()-1) < num) {
					int size = valueList.size();
					valueList.add(sum(size));
				}
				System.out.println((valueList.size()-2) + " card(s)");
			}
			else {
				int ind = 1;
				while (true) {
					if (num < valueList.get(ind+1) && num > valueList.get(ind)) {
						System.out.println(ind + " card(s)");
						break;
					}
					else {ind ++;}
				}
			}
			num = input.nextDouble();
		}
		input.close();
	}

}
