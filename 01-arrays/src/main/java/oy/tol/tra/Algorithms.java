package oy.tol.tra;

public class Algorithms {
   

    public static <T extends Comparable<T>> void sort(T [] array){

       
        int i = array.length - 1;
      for (int j = 0; j < array.length; j++) {
         while (i > 0) {
            T a = array[i];
            T b= array[i - 1];
            if (a.compareTo(b)<0) {
               T tmp = array[i];
               array[i] = array[i - 1];
               array[i - 1] = tmp;
            }
            i--;
         }
         i = array.length - 1;
      }



    }
 


    public static <T> void reverse(T [] array){

        int i = 0;
      while (i < array.length / 2) {
         T temp = array[i];
         array[i] = array[array.length - i - 1];
         array[array.length - i - 1] = temp;
         i++;
      }

    }




    
}
