import java.util.Scanner;

/**
 * Classe per la gestione dell'input
 */
public class InputGetter {

	private double[] input;

	public InputGetter(double[] input) {
		if (input == null) {
			readInput();
		} else {
			this.input = input;
		}

	}

	/**
	 * lettura array da standard input
	 */
	public void readInput() {
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		sc.close();
		// elimino punto finale
		line = line.substring(0, line.length() - 1);

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
