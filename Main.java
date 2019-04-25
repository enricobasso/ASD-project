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

        Main media = new Main(input);
        int pivot = media.select(0, media.array.length - 1, media.array.length / 2);
        System.out.println("-> pivot: " + pivot);
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
    public int partition(int p, int r) {
        double x = this.array[r];
        int i = p - 1;

        for (int j = p; j < r; j++) {
            if (this.array[j] <= x) {
                i++;
                this.swapCells(i, j);
            }
        }
        this.swapCells(i + 1, r);

        return i + 1;
    }

    /**
     * Select algorithm
     * @return the index of the element found
     */
    public int select(int p, int r, int i) {
        int q, k;

        if (p == r)
            //return array[p];
            return p;

        q = this.partition(p, r);
        k = q - p + 1;

        if (i == k)
            //return array[q];
            return q;
        else if (i < k)
            return this.select(p, q - 1, i);
        else
            return this.select(q + 1, r, i - k);

    }
}
