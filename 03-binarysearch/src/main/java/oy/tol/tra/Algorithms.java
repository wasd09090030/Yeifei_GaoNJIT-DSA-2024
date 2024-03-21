package oy.tol.tra;

public class Algorithms {

    public static <T extends Comparable<T>> int binarySearch(T aValue, T[] fromArray, int fromIndex, int toIndex) {

        T[] Array = fromArray;




        if (aValue.compareTo(Array[toIndex]) > 0 || aValue.compareTo(Array[fromIndex]) < 0)
            return -1;
        int mid = (fromIndex + toIndex) / 2;
        int result =0;


        if (aValue.compareTo(Array[mid]) == 0)
            result = mid;

        else if (aValue.compareTo(Array[mid]) < 0)

            return binarySearch(aValue, fromArray, fromIndex, mid - 1);
        else if (aValue.compareTo(Array[mid]) > 0)

            return binarySearch(aValue, fromArray, mid + 1, toIndex);

        return result;

    }

    public static <E extends Comparable<E>> void fastSort(E[] array) {
        quickSort(array, 0, array.length - 1);
    }

    public static <E extends Comparable<E>> void quickSort(E[] array, int begin, int end) {
        if (begin < end) {

            int position = partition(array, begin, end);

            quickSort(array, begin, position - 1);

            quickSort(array, position + 1, end);
        }
    }

    private static <E extends Comparable<E>> int partition(E[] array, int begin, int end) {


        E pivot = array[end];

        int pointer = begin;

        for (int i = begin; i < end; i++) {

            if (array[i].compareTo(pivot) < 0) {

                E temp = array[i];
                array[i] = array[pointer];
                array[pointer] = temp;
                pointer++;
            }

        }

        E temp = array[pointer];
        array[pointer] = array[end];
        array[end] = temp;
        return pointer;
    }

    public static <T extends Comparable<T>> void sort(T[] array) {

        int i = array.length - 1;
        for (int j = 0; j < array.length; j++) {
            while (i > 0) {
                T a = array[i];
                T b = array[i - 1];
                if (a.compareTo(b) < 0) {
                    T tmp = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = tmp;
                }
                i--;
            }
            i = array.length - 1;
        }

    }

    public static <T> void reverse(T[] array) {

        int i = 0;
        while (i < array.length / 2) {
            T temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
            i++;
        }

    }

}
