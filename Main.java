import static java.lang.System.*;

public class Main {
    double[] array;

    public Main(double[] input) {
        this.array = input;
    }

    public static void main(String[] args) {
        /*
         Array initialization
         @TODO: input from System.in
         */
        double[] input = {0.1, 0.35, 0.05, 0.1, 0.15, 0.05, 0.2};
        /*
            0.05
            0.05
            0.1
            0.1
            0.15
            0.2
            0.35
        */
        for (int i = 0; i < input.length; i++) {
            System.out.println(input[i]);
        }

        Main medianObject = new Main(input);

        System.out.println("Wk: " + medianObject.calcInferiorMedian(medianObject.array.length / 2, 0));
    }

    /*
     *
    */
    public double calcInferiorMedian(int k, double middleArraySum) {
        if (k < 1 || k > this.array.length - 1) {
            return -1;
        }

        this.select(k, 0, this.array.length - 1);

        // The first time the method is executed it calculates the sum of all the weights divided by 2.
        if (k == this.array.length / 2)
            middleArraySum = this.arraySum(0, this.array.length - 1) / 2;

        if (this.arraySum(0, k - 1) < middleArraySum && middleArraySum <= this.arraySum(0, k))
            return this.array[k];
        else if (this.arraySum(0, k) < middleArraySum)
            return this.calcInferiorMedian(k + 1, middleArraySum);
        else
            return this.calcInferiorMedian(k - 1, middleArraySum);

    }

    /**
     * Return the sum of the specified elements of the array.
     * @param pos1 the index of the first element to sum
     * @param pos2 the index of the last element to sum
     * @return the sum
     */
    public double arraySum(int pos1, int pos2) {
        double sum = 0;

        for (int i = pos1; i <= pos2; i++) {
            sum = sum + this.array[i];
        }

        return sum;
    }

    /**
     * Swap
     */
    public void swapCells(int i, int j) {
        double temp;

        temp = this.array[i];
        this.array[i] = this.array[j];
        this.array[j] = temp;
    }

    /**
     * Partition algorithm
     *
     */
    public int partition(int p, int q) {
        double pivot = this.choosePivot(p, q);
        int i = p - 1;

        int pivotIndex = this.seek(pivot, p, q);
        this.swapCells(pivotIndex, q);

        for (int j = p; j <= q; j++) {
            if (this.array[j] <= pivot) {
                i++;
                this.swapCells(i, j);
            }
        }
        return i;
    }

    /**
     * Select algorithm
     * @param i
     * @param p
     * @param q
     * @return the index of the element that would be in array[i] if this were ordered.
     */
    public double select(int i, int p, int q) {
        int x = this.partition(p, q);

        if (i == x)
            return this.array[x];
        else
            if (i < x)
                return this.select(i, p, x - 1);
            else
                return this.select(i, x + 1, q);
    }

    /**
     * Method that searches for an array element.
     * @param x the item to search for.
     * @param p start index.
     * @param q end index.
     * @return the index of the element found, or -1 (error).
    */
    public int seek(double x, int p, int q) {
        for (int i = p; i <= q; i++) {
            if (this.array[i] == x) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Method that sorts a small part of the array.
     * @param begin stard index.
     * @param endOfArray
     * @param k
    */
    public void sortAPart(int begin, int endOfArray, int k) {
        for (int i = begin; i < begin + (k - 1) && i < endOfArray; i++) {
            for (int j = i + 1; j < begin + k && j <= endOfArray; j++) {
                if (this.array[i] > this.array[j]) {
                    this.swapCells(i, j);
                }
            }
        }
    }

    /**
     * choosePivot
     * @param p start index.
     * @param q end index.
     * @return selected pivot.
    */
    public double choosePivot(int p, int q) {
        int n = q - p + 1;
        int dim = n / 5;

        if (n % 5 != 0) {
            dim = dim + 1;
        }

        double[] B = new double[dim];
        int i = p;

        for (int j = 0; j < dim; j++) {
            this.sortAPart(i, q, 5);
            double median;
            if (q - i < 5)
                median = this.array[i + (int) Math.floor((q - i) / 2)];
            else
                median = this.array[i + 2];

            B[j] = median;
            i = i + 5;

        }

        if (dim == 1)
            return B[0];
        else
            return select((int) Math.floor(dim / 2), 0, dim - 1);
    }
}
