package com.bottomlord.week_7;

public class LeetCode_387_1_字符串中第一个唯一字符 {
    public int firstUniqChar(String s) {
        int max = 0;
        char[] cs = s.toCharArray();

        for (char c: cs) {
            max = Math.max(c, max);
        }

        int[] numBucket = new int[max + 1];
        Integer[] indexBucket = new Integer[max + 1];

        for (int i = 0; i < cs.length; i++) {
            numBucket[cs[i]]++;
            if (indexBucket[cs[i]] == null) {
                indexBucket[cs[i]] = i;
            }
        }

        int index = cs.length;
        for (int i = 0; i < numBucket.length; i++) {
            if (numBucket[i] == 1) {
                index = Math.min(index, indexBucket[i]);
            }
        }

        return index == cs.length ? -1 : index;
    }
}
