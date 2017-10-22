package ru.bvd.algorithms.base;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import static ru.bvd.algorithms.base.Helper.*;

/**
 * Created by vadim on 30.09.17.
 */
public class SelectionSort {
    private static final Logger log = Logger.getLogger(SelectionSort.class.getName());

    private List<Integer> data;
    private int[] data2;
    private int[] sortData;
    boolean[] data2Exclude;

    public void init() {
        data = getRandomDataList(DATA_SIZE);
        sortData = new int[DATA_SIZE];
    }
    public void init2() {
        data2 = getRandomDataArray(DATA_SIZE);
        data2Exclude = new boolean[DATA_SIZE];
        sortData = new int[DATA_SIZE];
    }

    public void sort() {
        int to = data.size();
        for (int i = 0; i < to; i++) {
            int smallest = findSmallest(data);
            sortData[i] = data.get(smallest);
            data.remove(smallest);
            if ( (i % 1000) == 0) {
                log.info(Integer.toString(i) + ": data length = " + data.size());
            }
        }
    }
    public void sort2() {
        for (int i = 0; i < data2.length; i++) {
            int smallest = findSmallest2();
            sortData[i] = data2[smallest];
            data2Exclude[smallest] = true;
            if ( (i % 1000) == 0) {
                log.info(Integer.toString(i) + ": data length = " + data2.length);
            }
        }
    }

    public int[] getSortData() {
        return sortData;
    }
    public List<Integer> getUnSortedData() {
        return data;
    }
    public int[] getUnSortedData2() {
        return data2;
    }

    private int findSmallest (List<Integer> list) {
        int smallest = list.get(0);
        int smallestIndex = 0;
        int currentIndex = 0;
        for (Integer element : list) {
            if (element < smallest) {
                smallest = element;
                smallestIndex = currentIndex;
            }
            currentIndex++;
        }
        return smallestIndex;
    }



    private int findSmallest2 () {
        int smallest = 0;
        int smallestIndex = 0;
        for (int i = 0; i < data2.length; i++) {
            if (!data2Exclude[i]) {
                smallest = data2[i];
                smallestIndex = i;
            }
        }

        for (int i = 0; i < data2.length; i++) {
            if (data2[i] < smallest && !data2Exclude[i]) {
                smallest = data2[i];
                smallestIndex = i;
            }
        }

        return smallestIndex;
    }


}
