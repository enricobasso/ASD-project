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

        input = sort(input, 0, input.length);
        for (int i = 0; i < input.length; i++) {
            System.out.println(input[i]);
        }

        /*for (int i = 1; i < array.length; i++) {


        }*/
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
     * Merges two subarrays of array[].
     * @param array
     * @param l
     * @param m
     * @param r
     */
    public static double[] merge(double[] arr, int l, int m, int r) {
        
    }

    /**
     * Uses merge() to sort an array.
     * @param array
     * @param l
     * @param r
     */
    public static double[] sort(double[] arr, int l, int r) {

    }
}
