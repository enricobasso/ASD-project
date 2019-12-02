public class Main {

    public static void main(String[] args) {
        //double[] input = {0.549178110022452, 0.405145922046684, 0.4856229941081591, 0.10658278333555671, 0.5000518600705458, 0.7437491210789988, 0.037904615535225616, 0.8154027628258288, 0.3185980081163632, 0.6683937892023177, 0.6213634966911162, 0.35473355275873575, 0.8332198116112027, 0.1723053601088923, 0.7454741403777418, 0.6271299484699213, 0.1378825819359797, 0.6023749485443667, 0.6789246973211434, 0.06419211399776303, 0.8162793078034709, 0.7482706642890472, 0.11888593040522344, 0.9156208687826942, 0.46191117388642333, 0.5440911679364423, 0.28511610535133025, 0.726636170314028, 0.18576328034759715, 0.7121816655192645, 0.8771652493547185, 0.9744922655311266, 0.11673370801458238, 0.09377185096746155, 0.4120428447347487, 0.6404873490383513, 0.9946995162503398, 0.8573843402487593, 0.8200651948000074, 0.8307792594965936, 0.8320804039392222, 0.2535009882466128, 0.8182001637970087, 0.42748333153022744, 0.27698713484495396, 0.09276388403143432, 0.5030783033447221, 0.15094212159075093, 0.7495186189945912, 0.6650264410739999, 0.8099412941160964, 0.3447209815749187, 0.6424977384218887, 0.3878597583848099, 0.9691499020144632, 0.25679748443729267, 0.8789128453611421, 0.07565743571462968, 0.8584752561838596, 0.5835769436985472, 0.9005582858360026, 0.35427234883647196, 0.1972492614925322, 0.5677798940676735, 0.7258441161996991, 0.4111567760774779, 0.8575505128942071, 0.9969119928915311, 0.059471237164116064, 0.9672672893369932, 0.17795498309822155, 0.03594045447036276, 0.5626452722457814, 0.08453397340226565, 0.0569810312794824, 0.9459653762525164, 0.29972881761303094, 0.18148375036216058, 0.77621078691099, 0.3900883826853301, 0.06570812681357863, 0.41964528666561585, 0.9571709441127245, 0.20834512256934412, 0.7685772393244773, 0.444653155665396, 0.7914079317737888, 0.2554818323025623, 0.2917187645994527, 0.31850749070741646, 0.6997937981025686, 0.13852104463694204, 0.29358724498938116, 0.36323421522126975, 0.8749505612387366, 0.4654744363556611, 0.215477526324519, 0.5512308815013184, 0.739782990080432, 0.5222262687676517};
        /*
            0.05
            0.05
            0.1
            0.1
            0.15
            0.2
            0.35
        */
        double[] input = {0.1, 0.35, 0.05, 0.1, 0.15, 0.05, 0.2};

        // Calcolo somma array / 2
        double middleArraySum = arraySum(input, 0, input.length - 1) / 2;
        System.out.println("W / 2 = " + middleArraySum);
        // Calcolo la media
        System.out.println("Wk: " + calcInferiorMedian(input, 0, input.length - 1, middleArraySum));
    }

    /*
     *
    */
    public static double calcInferiorMedian(double[] array, int p, int q, double middleArraySum) {
        if (p > q)
            return -1;

        int m = (p + q) / 2;
        // select ritorna l'elemento che sarebbe in posizione (p + q) / 2
        double pivot = select(array, m, p, q);
        // metto il pivot in mezzo
        array = swapCells(array, seek(array, pivot, 0, array.length - 1), m);
        System.out.println("Perno: " + array[m] + " (index = " + m + ")");
        printArray(array);
        // eseguo partition sull'array e mi ritorno l'array diviso in due x < m < y
        array = leftPartition(array, m, p, q);
        

        // m va a destra o a sinistra?
        double leftSum = arraySum(array, p, m);
        double rightSum = arraySum(array, m + 1, q);
        
        if (leftSum < middleArraySum && middleArraySum <= rightSum)
            return array[m];
        else
            if (leftSum > middleArraySum)
                return calcInferiorMedian(array, p, m - 1, middleArraySum);
            else
                return calcInferiorMedian(array, m, q, middleArraySum);
    }

    /**
     * Partition algorithm
     *
     */
    public static int partition(double[] array, int p, int q) {
        double pivot = choosePivot(array, p, q);
        int i = p - 1;

        int pivotIndex = seek(array, pivot, p, q);
        array = swapCells(array, pivotIndex, q);

        for (int j = p; j <= q; j++) {
            if (array[j] <= pivot) {
                i++;
                array = swapCells(array, i, j);
            }
        }
        return i;
    }

    public static double[] leftPartition(double[] array, int pivotIndex, int p, int q) {
        int i = p - 1;
        double pivot = array[pivotIndex];
        array = swapCells(array, pivotIndex, q);

        for (int j = p; j <= q; j++) {
            if (array[j] <= pivot) {
                i++;
                array = swapCells(array, i, j);
            }
        }
        return array;
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

        if (i == x)
            return array[x];
        else
            if (i < x)
                return select(array, i, p, x - 1);
            else
                return select(array, i, x + 1, q);
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
                    array = swapCells(array, i, j);
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
            array = sortAPart(array, i, q, 5);
            double median;
            if (q - i < 5)
                median = array[i + (int) Math.floor((q - i) / 2)];
            else
                median = array[i + 2];

            B[j] = median;
            i = i + 5;

        }

        if (dim == 1)
            return B[0];
        else
            return select(array, (int) Math.floor(dim / 2), 0, dim - 1);
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

     /**
     * Return the sum of the specified elements of the array.
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

    public static void printArray(double[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static int countKey(double[] array, double k) {
        int j = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == k) {
                j = j + 1;
            }
        }

        return j;
    }
}
