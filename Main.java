import static java.lang.System.*;

public class Main {
    public static void main(String[] args) {
        /*
         Array initialization
         @TODO: input from System.in
         */
        double[] input = {0.1, 0.35, 0.05, 0.1, 0.15, 0.05, 0.2};
        for (int i = 0; i < input.length; i++) {
            System.out.println(input[i]);
        }

        System.out.println("-> " + randomizedSelect(input, 0, input.length - 1, 6));

    }

    /**
     * Return the sum of the specified elements of the array.
     * @param array the array containing the elements to sum
     * @param pos1 the index of the first element to sum
     * @param pos2 the index of the last element to sum
     * @return the sum
     */
    public static double arraySum(double[] array, int pos1, int pos2) {
        double sum = 0;

        for (int i = pos1; i <= pos2; i++) {
            sum = sum + array[i];
        }

        return sum;
    }

    /**
     * Swap
     */
    public static double[] swapCells(double[] array, int i, int j) {
        double temp;

        temp = array[i];
        array[i] = array[j];
        array[j] = temp;

        return array;
    }

    /**
     * Partition algorithm
     *
     */
    public static int partition(double[] array, int p, int r) {
        double x = array[r];
        int i = p - 1;

        for (int j = p; j < r; j++) {
            if (array[j] <= x) {
                i++;
                array = swapCells(array, i, j);
            }
        }
        array = swapCells(array, i + 1, r);

        return i + 1;
    }

    /**
     * Randomized partition
     */
    public static int randomizedPartition(double[] array, int p, int r) {
        /*int i = random(p, r);
        array = swapCells(array, r, i);*/

        return partition(array, p, r);
    }

    /**
     * Select algorithm
     */
    public static double randomizedSelect(double[] array, int p, int r, int i) {
        int q, k;

        if (p == r)
            return array[p];

        q = partition(array, p, r);
        k = q - p + 1;

        if (i == k)
            return array[q];
        else if (i < k)
            return randomizedSelect(array, p, q - 1, i);
        else
            return randomizedSelect(array, q + 1, r, i - k);

    }

    /**
     * Random number generator
     */
    /*public static int random(int seed) {

    }*/
}
