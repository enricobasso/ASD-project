public class InputGenerator {

	double currentSeed;

	public InputGenerator(double seed) {
		currentSeed = seed;
		// per evitare numeri < 0
		getRandomNumber();
	}

	public double getRandomNumber(){
		int a = 16087;
		int m = 2147483647;
		int q = 127773;
		int r = 2836;

		double hi = Math.ceil(currentSeed / q);

		double lo = currentSeed - q * hi;

		double test = a * lo - r * hi;

		if (test < 0.0) {
			currentSeed = test + m;
		} else {
			currentSeed = test;
		}

		return currentSeed / m;
	}

	/**
	 * Metodo che ritorna una stringa valida come input.
	 */
	public String getNewStringArray() {
		double[] array = new double[4000000];
		String output = "";

		for (int i = 0; i < array.length; i++) {
			array[i] = this.getRandomNumber();

			if (i == 0) {
				output = array[i] + "";
			} else {
				output = output + ", " + array[i];
			}
		}
		return output + ".";
	}

	/**
	 * Genera un array di Double
	 */
	public double[] getNewArray(int dim) {
		double[] array = new double[dim];

		for (int i = 0; i < array.length; i++) {
			array[i] = this.getRandomNumber();
		}

		return array;
	}

	public static void main(String[] args) {
		// stampa in std output una stringa valida come input per l'algoritmo calcInferiorMedian().
		InputGenerator inputGen = new InputGenerator(System.currentTimeMillis());
		String output = inputGen.getNewStringArray();

		System.out.println(output);

	}
}
