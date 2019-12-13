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
	
	public String getNewArray() {
		double[] array = new double[(int)(1 + Math.round(this.getRandomNumber() * 10000))];
		String output = "";
		
		for (int i = 0; i < array.length; i++) {
			array[i] = this.getRandomNumber();
			
			if (i == 0) {
				output = array[i] + "";
			} else {
				output = output + ", " + array[i];
			}
			
		}
		
		return output;
	}
	
	public static void main(String[] args) {
		InputGenerator inputGen = new InputGenerator(System.currentTimeMillis());
		String output = inputGen.getNewArray();
	
		System.out.println(output);
		
	}
}
