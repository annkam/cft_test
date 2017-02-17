/**
 * Created by eshkigal on 17.02.17.
 */
public class InsertionSorter {

    // Сортировка массива целых чисел
   public static void sortIntArray(int[] arr, boolean isAscending) {
       int tmp;
       int j;
       for (int i = 1; i < arr.length; i++) {
           tmp = arr[i];
           j = i - 1;
           while (j >= 0 && (isAscending ? arr[j] > tmp : arr[j] < tmp)) {
               arr[j+1] = arr[j];
               j--;
           }
           arr[j+1] = tmp;
       }
    }

    // Сортировка массива строк
    public static void sortStringArray(String[] arr, boolean isAscending) {
        String tmp;
        int j;
        for (int i = 1; i < arr.length; i++) {
            tmp = arr[i];
            j = i - 1;
            while (j >= 0 && (isAscending ? arr[j].compareTo(tmp) > 0 : arr[j].compareTo(tmp) < 0)) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = tmp;
        }
    }

}
