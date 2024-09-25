package com.bottomlord.week_085;

/**
 * @author ChenYue
 * @date 2021/2/22 8:32
 */
public class LeetCode_458_1_可怜的小猪 {
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int n = minutesToTest / minutesToDie + 1;
        return (int)Math.ceil(Math.log(buckets) / Math.log(n));
    }
}
