package ru.bvd.algorithms.base;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static ru.bvd.algorithms.base.Helper.*;

/**
 * Created by vadim on 30.09.17.
 */
public class QuickSort {
    private static final Logger log = Logger.getLogger(QuickSort.class.getName());


    private List<Integer> data;
    private List<Integer> sortData;

    public  void init() {
        data = getRandomDataList(DATA_SIZE);
        //sortData = new int[DATA_SIZE];
    }

    public void sort() {
        sortData = quicksort(data);
    }
    public List<Integer> getSortData() {
        return sortData;
    }
    public List<Integer> getUnsortData() {
        return data;
    }
    int stackHight = 0;

    private List<Integer> quicksort(List<Integer> arr) {
        stackHight++;
        if ( (stackHight%1000)==0 ) {
            log.info(Integer.toString(stackHight) + ": data length = " + data.size());
        }

        if (arr.size()<2)
            return arr;
        int pivot = arr.get(0);
        List<Integer> less = getLess(arr, pivot);
        List<Integer> greater = getGreater(arr, pivot);

        List<Integer> newarr = new ArrayList<>();
        newarr.addAll( quicksort(less) );
        newarr.add(pivot);
        newarr.addAll( quicksort(greater) );

        return newarr;
    }

    private List<Integer> getLess(List<Integer> src,int pivot) {
        List<Integer> less = new ArrayList<>();
        boolean first = true;
        for (Integer element : src) {
            if (first) {
                first = false;
                continue;
            }
            if (element<=pivot)
                less.add(element);
        }
        return less;
    }
    private List<Integer> getGreater(List<Integer> src,int pivot) {
        List<Integer> greater = new ArrayList<>();
        boolean first = true;
        for (Integer element : src) {
            if (first) {
                first = false;
                continue;
            }
            if (element>pivot)
                greater.add(element);
        }
        return greater;
    }





}
