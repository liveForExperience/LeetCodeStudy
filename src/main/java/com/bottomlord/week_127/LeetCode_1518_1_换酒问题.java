package com.bottomlord.week_127;

/**
 * @author chen yue
 * @date 2021-12-17 08:51:58
 */
public class LeetCode_1518_1_换酒问题 {
    public int numWaterBottles(int numBottles, int numExchange) {
        int sum = numBottles, empty = numBottles;
        while (empty >= numExchange) {
            int drink = empty / numExchange;
            sum += drink;
            empty = drink + empty % numExchange;
        }
        return sum;
    }
}
