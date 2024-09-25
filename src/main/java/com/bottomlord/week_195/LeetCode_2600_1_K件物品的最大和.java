package com.bottomlord.week_195;

/**
 * @author chen yue
 * @date 2023-04-05 14:00:14
 */
public class LeetCode_2600_1_K件物品的最大和 {
    public int kItemsWithMaximumSum(int numOnes, int numZeros, int numNegOnes, int k) {
        int sum = 0;
        sum += numOnes = Math.min(k, numOnes);
        k -= numOnes;
        k -= Math.min(k, numZeros);
        return sum -= numNegOnes = Math.min(k, numNegOnes);
    }
}
