package it.enricobasso.asd;

import java.util.Scanner;

public class InputGetter {

	public InputGetter() {
		// TODO Auto-generated constructor stub
	}		
	
	public static double[] readInput() {
		double[] input;
		
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		sc.close();
		String[] splittedLine = line.split(",");
		input = new double[splittedLine.length];
		
		for (int i = 0; i < splittedLine.length; i++) {
			input[i] = Double.valueOf(splittedLine[i]);
		}

		return input;
	}

}
