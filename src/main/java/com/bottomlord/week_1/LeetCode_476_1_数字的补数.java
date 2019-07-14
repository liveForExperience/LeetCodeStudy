package com.bottomlord.week_1;

/**
 * @author LiveForExperience
 * @date 2019/7/14 14:22
 */
public class LeetCode_476_1_数字的补数 {
    public int findComplement(int num) {
        int tmp = 1;
        while (tmp < num) {
            tmp <<= 1;
            tmp += 1;
        }

        return tmp ^ num;
    }

    public static void main(String[] args) {
        LeetCode_476_1_数字的补数 test = new LeetCode_476_1_数字的补数();
        System.out.println(2147483647);
    }
}
