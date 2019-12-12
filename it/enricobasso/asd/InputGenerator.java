package it.enricobasso.asd;

public class InputGenerator {
	
	double currentSeed;

	public InputGenerator(double seed) {
		currentSeed = seed;
		getRandomNumber(); //to avoid < 0 numbers error
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
	
	public double[] getNewArray() {
		double[] array = new double[(int)(1 + Math.round(this.getRandomNumber() * 100))];
		
		for (int i = 0; i < array.length; i++) {
			array[i] = Math.round(this.getRandomNumber());
		}
		
		return array;
	}
	
	public static void main(String[] args) {
		InputGenerator inputGen = new InputGenerator(System.currentTimeMillis());
		double[] array = inputGen.getNewArray();
		
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}
}
