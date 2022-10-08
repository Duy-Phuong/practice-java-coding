package search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BinarySearch {

    int binarySearch(int[] array, int left, int right, int search) {
        if (left <= right) {
            int mid = left + (right - left)/2;
            if (array[mid] == search) {
                return mid;
            }
            if (array[mid] < search) {
                return binarySearch(array, mid+1, right, search);
            }
            if (array[mid] > search) {
                return binarySearch(array, left,mid-1, search);
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        int[] num = {1, 2, 3, 9, 5};
        Arrays.sort(num);

        int res = Arrays.binarySearch(num, 30);
        if (res < 0) {
            System.out.println("Not found");
        } else {
            System.out.println("Result :" + res);
        }

        List<Integer> list = new ArrayList<Integer>();
        list.add(2);
        list.add(3);
        list.add(0);
        list.add(9);

        Collections.sort(list);
        System.out.println(list.toString());

        int res2 = Collections.binarySearch(list, 3);
        if (res2 < 0) {
            System.out.println("Not found");
        } else {
            System.out.println("Result position after sort :" + res2);
        }


        BinarySearch ob = new BinarySearch();
        int arr[] = {2,3,4,10,40};
        int n = arr.length;
        int x = 10;
        int result = ob.binarySearch(arr,0,n-1,x);
        if (result == -1)
            System.out.println("Element not present");
        else
            System.out.println("Element found at index " +
                    result);
    }
}
