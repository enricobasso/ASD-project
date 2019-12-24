package it.enricobasso.asd;

public class Complexity {
	double[] input;
	
	public Complexity(double[] input) {
		this.input = input;
	}
	
	/**
	* get the granularity of the system
	*/
	public long getGranularity() {
		long tStart = System.currentTimeMillis();
		long tEnd = System.currentTimeMillis();
		
		while(tStart == tEnd){
			tEnd = System.currentTimeMillis();
		}
		
		return tEnd - tStart;
	}
	
	
	/*
	 * tMin = 20*granularità.
	 */
	public int getNumberOfTest(long tMin) { 
		long t0 = 0; 
		long t1 = 0; 
		
		int repsCount = 1; 
		
		long currentExecutionTime = t1 - t0;
		
		while(currentExecutionTime <= tMin) {
			repsCount = repsCount * 2;
			t0 = System.currentTimeMillis();
			for(int i = 0; i < repsCount;  i++) {
				Main.execute(input);
			}
			t1 = System.currentTimeMillis();
			
			currentExecutionTime = t1 - t0;
		}
		
		int maxRepsCount = repsCount;
		int minRepsCount = repsCount / 2;
		
		int wrongCicles = 5;
		
		while(maxRepsCount - minRepsCount >= wrongCicles) {
			int middleReps = (maxRepsCount+minRepsCount) / 2;
			t0 = System.currentTimeMillis();
			
			for(int i = 0; i < middleReps;  i++) {
				Main.execute(input);
			}
			t1 = System.currentTimeMillis();
			
			if(t1 - t0 <= tMin){
				minRepsCount = middleReps;
			} else {
				maxRepsCount = repsCount;
				// non maxRepsCount = middleReps; ??
			}
		}
		
		return maxRepsCount;
	}
	
	public long timeCalculator(int repsCount) {
		long t0 = System.currentTimeMillis();
		
		for(int i = 0; i < repsCount; i++) {
			Main.execute(input);
		}
		
		long t1 = System.currentTimeMillis(); 
		long tTot = t1 - t0;
		long tSing = tTot / repsCount;
		
		return tSing;
	}
	
	/*rip = ripetizioni,  c = 5 o 10, za = fissato, */
	
	public double timeMisurator(double za, int rip, int c) {
		double mediumValue, sqrtResult, delta;
		double deltaMaiusc = 0; // 1/5 del tempo medio;
		double stats = 0;
    	double numberOfCicles = 0;
    	double sumOfAllExecutionsTimes = 0; 
    	
    	do {
    		//c = 5, ciclo eseguito per 5 volte
    		//in tutto il programma viene eseguito c*rip ogni volta
    		for(int i = 0; i < c; i++){
    			long timeForRipTimes = timeCalculator(rip); //esegue il programma per rip volte e calcola il tempo medio
    			sumOfAllExecutionsTimes = sumOfAllExecutionsTimes + timeForRipTimes; //somma al tempo totale il tempo dell'esecuzione corrente
    			stats = stats + (timeForRipTimes*timeForRipTimes);
    		}
    		
    		numberOfCicles += c;
    		mediumValue =  sumOfAllExecutionsTimes / numberOfCicles; //tempo medio corrente 
    		
    		sqrtResult = Math.sqrt((stats / numberOfCicles) - mediumValue*mediumValue);
    		delta = (1 / (Math.sqrt(numberOfCicles))) * za * sqrtResult;
    		
    		deltaMaiusc = mediumValue / 5; //DELTA è settato ad 1/5 del tempo medio

    	} while(delta >= deltaMaiusc);
    	
    	return mediumValue;
	}
}
