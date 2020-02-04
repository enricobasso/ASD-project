public class Main {

    public static void main(String[] args) {
    	InputGetter inputHandler = new InputGetter(null);
    	execute(inputHandler.getInput());
    }

    public static void execute(double[] input) {
      	// Calcolo somma array / 2
        double middleArraySum = arraySum(input, 0, input.length - 1) / 2;
        // Calcololo mediana inferiore
        double result = calcInferiorMedian(input, 0, input.length - 1, middleArraySum);
        System.out.println(result);
    }

    public static double calcInferiorMedian(double[] array, int p, int q, double middleArraySum) {
        if (p > q) {
            return -1;
        }

        int m = (p + q) / 2;
        // cerco la mediana candidata
        select(array, m, p, q);

        // calcolo somme per effettuare verifica della mediana
        double leftSum = arraySum(array, 0, m - 1);
        double rightSum = arraySum(array, 0, m);

        if (leftSum < middleArraySum && middleArraySum <= rightSum) {
            return array[m];
        } else {
        	if (leftSum > middleArraySum) {
                return calcInferiorMedian(array, p, m - 1, middleArraySum);
        	} else {
                return calcInferiorMedian(array, m + 1, q, middleArraySum);
        	}
        }
    }

    /**
     * Algoritmo partition
     */
    public static int partition(double[] array, int p, int q) {
        double pivot = choosePivot(array, p, q);
        int i = p - 1;

        int pivotIndex = seek(array, pivot, p, q);
        swapCells(array, pivotIndex, q);

        for (int j = p; j <= q; j++) {
            if (array[j] <= pivot) {
                i++;
                swapCells(array, i, j);
            }
        }
        return i;
    }

    /**
     * algoritmo select
     */
    public static double select(double[] array, int i, int p, int q) {
        int x = partition(array, p, q);
        if (i == x) {
            return array[x];
        } else if (i < x) {
            return select(array, i, p, x - 1);
        } else {
            return select(array, i, x + 1, q);
        }
    }

    /**
     * Metodo che ordina una piccola porzione di array
    */
    public static double[] sortAPart(double[] array, int begin, int endOfArray, int k) {
        for (int i = begin; i < begin + (k - 1) && i < endOfArray; i++) {
            for (int j = i + 1; j < begin + k && j <= endOfArray; j++) {
                if (array[i] > array[j]) {
                    swapCells(array, i, j);
                }
            }
        }
        return array;
    }

    /**
     * Metodo per la scelta di un pivot (mediano dei mediani)
    */
    public static double choosePivot(double[] array, int p, int q) {
        int n = q - p + 1;
        int dim = n / 5;

        if (n % 5 != 0) {
            dim = dim + 1;
        }

        double[] B = new double[dim];
        int i = p;

        for (int j = 0; j < dim; j++) {
            sortAPart(array, i, q, 5);
            double median;
            if (q - i < 5) {
                median = array[i + (int) Math.floor((q - i) / 2)];
            } else {
                median = array[i + 2];
            }

            B[j] = median;
            i = i + 5;

        }

        if (dim == 1) {
            return B[0];
        } else {
            return select(B, (int) Math.floor(dim / 2), 0, dim - 1);
        }
    }

    /*
    * metodo per lo scambio di due celle
    */
    public static double[] swapCells(double[] array, int i, int j) {
        double temp;

        temp = array[i];
        array[i] = array[j];
        array[j] = temp;

        return array;
    }

    /**
     * Metodo che ricerca un elemento in un array.
    */
    public static int seek(double[] array, double x, int p, int q) {
        for (int i = p; i <= q; i++) {
            if (array[i] == x) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Metodo che calcola la somma degli elementi di un array
     */
    public static double arraySum(double[] array, int pos1, int pos2) {
        double sum = 0;

        for (int i = pos1; i <= pos2; i++) {
            sum = sum + array[i];
        }

        return sum;
    }
}
