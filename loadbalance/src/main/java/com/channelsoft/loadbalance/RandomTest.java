package com.channelsoft.loadbalance;

import java.util.Arrays;
import java.util.Random;

/**
 * @author sicwen
 * @date 2019/05/13
 */
public class RandomTest {

    private static Random random = new Random();

    public static void main(String[] args){
        int[] weights = new int[]{1,4,3,2};
        int[] statistics = new int[4];
        for (int i = 0; i < 1000; i++) {
            int index = random(weights);
            statistics[index]++;
        }
        System.out.println(Arrays.toString(statistics));
    }

    private static int random(int[] weights){
        int length = 0;
        for (int weight : weights) {
            length += weight;
        }
        int offset = random.nextInt(length);
        for (int i = 0; i < weights.length; i++) {
            offset -= weights[i];
            if(offset < 0){
                return i;
            }
        }
        return 0;
    }
}