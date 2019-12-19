package it.enricobasso.asd;

public class MisurationMain {
	public static void main(String[] args) {
		InputGetter inputHandler = new InputGetter();
		
		Complexity c = new Complexity(inputHandler);
		long granularity = c.getGranularity();
		int rips = c.getNumberOfTest(20*granularity);
		
		double mediumTime = c.timeMisurator(2.33, rips, 5);
		
		System.out.println("Calcolo tempo medio: "+mediumTime);
	}

}
