package sort;

import java.util.Arrays;

public class QuickSortDemo {
    public static void main(String args[]) {
        // unsorted integer array
        int[] unsorted;
        unsorted = new int[]{6, 5, 3, 1, 8, 7, 2, 4};
        System.out.println("Unsorted array :" + Arrays.toString(unsorted));
        QuickSort algorithm = new QuickSort();
        // sorting integer array using quicksort algorithm
        algorithm.sort(unsorted);
        // printing sorted array
        System.out.println("Sorted array :" + Arrays.toString(unsorted));
    }
}