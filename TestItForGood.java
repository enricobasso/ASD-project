public class TestItForGood {

	public static void main(String[] args) {
		InputGenerator ig = new InputGenerator(System.currentTimeMillis());
		
		for (int i = 0; i < 1000; i++) {
			double[] input = ig.getNewArray();
			double main = Main.calcInferiorMedian(input, 0, input.length - 1, Main.arraySum(input, 0, input.length - 1)/2);
			double mainNAIVE = MainNAIVE.execute(input);
			
			System.out.println(main + " == " + mainNAIVE +  " ? (" + i + ")");
			if(main == mainNAIVE) {
				System.out.println("ok");
			} else {
				System.out.println("!!!! ERRORE !!!!");
			}
		}
	}

}
