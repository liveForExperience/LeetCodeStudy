package com.bottomlord.week_111;

/**
 * @author chen yue
 * @date 2021-08-23 08:27:16
 */
public class LeetCode_1518_1_换酒问题 {
    public int numWaterBottles(int numBottles, int numExchange) {
        int drink = numBottles, emptyBottles = numBottles;

        while (emptyBottles >= numExchange) {
            int newDrink = emptyBottles / numExchange;
            drink += newDrink;
            emptyBottles = emptyBottles + newDrink - newDrink * numExchange;
        }

        return drink;
    }
}
