package com.bottomlord.week_211;

/**
 * @author chen yue
 * @date 2023-07-24 09:30:48
 */
public class LeetCode_771_1_宝石与石头 {
    public int numJewelsInStones(String jewels, String stones) {
        boolean[] bucket = new boolean['z' - 'A' + 1];
        char[] jcs = jewels.toCharArray(), cs = stones.toCharArray();
        for (char jc : jcs) {
            bucket[jc - 'A'] = true;
        }

        int count = 0;
        for (char c : cs) {
            if (bucket[c - 'A']) {
                count++;
            }
        }

        return count;
    }
}
