package ru.bvd.algorithms.base;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by vadim on 30.09.17.
 */
public class MainSort {
    private static final Logger log = Logger.getLogger(MainSort.class.getName());


    public static void main(String[] args) {
        configureLog();
//        SelectionSort selectionSort = new SelectionSort();
////        selectionSort.init();
////        int lengthUnsorted = selectionSort.getUnSortedData().size();
////        selectionSort.sort();
//        selectionSort.init2();
//        int lengthUnsorted = selectionSort.getUnSortedData2().length;
//        selectionSort.sort2();
//        int[] sortData = selectionSort.getSortData();

        QuickSort quickSort = new QuickSort();
        quickSort.init();
        int lengthUnsorted = quickSort.getUnsortData().size();
        quickSort.init();
        quickSort.sort();
        List<Integer> sortData = quickSort.getSortData();

        int lengthSorted = sortData.size();
        log.info("Unsorted data length = " + lengthUnsorted + ", sorted data length = " + lengthSorted);
        if (lengthSorted<100) {
            for (int i = 0; i < sortData.size(); i++) {
                log.info(Integer.toString(i) + " : " + sortData.get(i));
            }
        }

    }

    private static void configureLog() {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tF %1$tT %4$s %2$s %5$s%6$s%n");
        log.setLevel(Level.INFO);

    }


}
