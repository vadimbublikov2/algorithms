package ru.bvd.algorithms.base;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by vadim on 30.09.17.
 */
public class Helper {
    private Helper() {};

    public static int DATA_SIZE = 256 * 1024 * 32;
    //public static int DATA_SIZE = 11;

    public static int[] getRandomDataArray(int size) {
        int[] d = new int[size];
        Random r = new Random();
        for (int i = 0; i< size; i++) {
            d[i] = r.nextInt();
        }
        return  d;
    }

    public static List<Integer> getRandomDataList(int size) {
        List<Integer> l = new ArrayList<Integer>(DATA_SIZE);
        Random r = new Random();
        for (int i = 0; i< size; i++) {
            l.add( r.nextInt() );
        }
        return  l;
    }


}
