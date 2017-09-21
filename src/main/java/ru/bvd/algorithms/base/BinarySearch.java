package ru.bvd.algorithms.base;

import java.util.Random;

/**
 * Created by vadim on 21.09.17.
 */
public class BinarySearch {

    public static void main(String[] args) {
        int size = 256 * 1024 * 512;
        int[] array = new int[ size ];

        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }

        Random random = new Random();
        int item = random.nextInt(size);

        Integer result = bynarySearch(array, item);
        if (result!=null) {
            System.out.println(result);
        } else {
            System.out.println("None");
        }

    }



    public static Integer bynarySearch(int[] array, int item) {

        int low = 0;
        int high = array.length-1;

        while (low <= high) {
            int mid = (low+high) / 2;
            int guess = array[mid];
            if (guess == item)
                return mid;
            if (guess > item) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return null;
    }


}
