public class MisurationMain {
	public static void main(String[] args) {
		InputGenerator inputGenerator = new InputGenerator(System.currentTimeMillis());
		InputGetter inputHandler = new InputGetter(inputGenerator.getNewArray());

		Complexity c = new Complexity(inputHandler.getInput());
		long granularity = c.getGranularity();

		int rips = c.getNumberOfTest(20 * granularity);
		double mediumTime = c.timeMisurator(2.33, rips, 5);

		System.out.println("Calcolo tempo medio: " + mediumTime);
	}

}
