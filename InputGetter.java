import java.util.Scanner;

public class InputGetter {
	
	private double[] input;

	public InputGetter() {
		readInput();
	}
	
	public void readInput() {
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		sc.close();
		String[] splittedLine = line.split(",");
		input = new double[splittedLine.length];
		
		for (int i = 0; i < splittedLine.length; i++) {
			input[i] = Double.valueOf(splittedLine[i]);
		}
	}
	
	public double[] getInput() {
		return input;
	}
	
	public void setInput(double[] input) {
		this.input = input;
	}
}
