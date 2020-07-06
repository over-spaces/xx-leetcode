package com.leetcode.learning.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BiSearchTest {

    private static Logger logger = LoggerFactory.getLogger(BiSearchTest.class);

    @Test
    public void testBiSearch(){
        int[] array = {1,4,5,7,9,10,15};
        int index = biSearch(array, 10);
        logger.info("index:{}", index);
        System.out.println(index);
    }

    private int biSearch(int[] array, int a) {

        int start = 0;
        int len = array.length - 1;
        int mid;
        while (start <= len){
            mid = (start + len) / 2;
            System.out.println("mid:" + mid + " , start:" + start);
            if(array[mid] == a){
                return mid + 1;
            }
            if(array[mid] < a){
                start = mid + 1;
            }else {
                start = mid - 1;
            }
        }
        return -1;
    }

}
