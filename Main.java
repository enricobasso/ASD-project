public class Main {

    public static void main(String[] args) {
    	InputGetter inputHandler = new InputGetter(null);
    	execute(inputHandler.getInput());
    }

    public static void execute(double[] input) {
      	// Calcolo somma array / 2
        double middleArraySum = arraySum(input, 0, input.length - 1) / 2;
        // Calcololo mediana inferiore
        System.out.println(calcInferiorMedian(input, 0, input.length - 1, middleArraySum));
    }

    public static double calcInferiorMedian(double[] array, int p, int q, double middleArraySum) {
        if (p > q) {
            return -1;
        }

        int m = (p + q) / 2;
        select(array, m, p, q); // O(n)

        double leftSum = arraySum(array, 0, m - 1); // H(n/2)
        double rightSum = arraySum(array, 0, m); // H(n/2)

        if (leftSum < middleArraySum && middleArraySum <= rightSum) {
            return array[m];
        } else {
        	if (leftSum > middleArraySum) {
                return calcInferiorMedian(array, p, m - 1, middleArraySum); // T(n/2)
        	} else {
                return calcInferiorMedian(array, m + 1, q, middleArraySum);
        	}
        }
    }

    /**
     * Partition algorithm
     *
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
     * Select algorithm
     * @param i
     * @param p
     * @param q
     * @return the element that would be in array[i] if this was ordered.
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
     * Method that sorts a small part of the array.
     * @param begin stard index.
     * @param endOfArray
     * @param k
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
     * choosePivot
     * @param p start index.
     * @param q end index.
     * @return selected pivot.
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

    public static double[] swapCells(double[] array, int i, int j) {
        double temp;

        temp = array[i];
        array[i] = array[j];
        array[j] = temp;

        return array;
    }

    /**
     * Method that searches for an array element.
     * @param x the item to search for.
     * @param p start index.
     * @param q end index.
     * @return the index of the element found, or -1 (error).
    */
    public static int seek(double[] array, double x, int p, int q) {
        for (int i = p; i <= q; i++) {
            if (array[i] == x) {
                return i;
            }
        }

        return -1;
    }

    public static double arraySum(double[] array, int pos1, int pos2) {
        double sum = 0;

        for (int i = pos1; i <= pos2; i++) {
            sum = sum + array[i];
        }

        return sum;
    }
}
