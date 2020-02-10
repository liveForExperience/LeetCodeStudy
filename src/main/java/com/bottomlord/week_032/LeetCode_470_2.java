package com.bottomlord.week_032;

/**
 * @author ThinkPad
 * @date 2020/2/10 14:47
 */
public class LeetCode_470_2 {
    class Solution extends SolBase {
        public int rand10() {
            while (true) {
                int index = rand7() + (rand7() - 1) * 7;
                if (index <= 40) {
                    return 1 + (index - 1) % 10;
                }

                index = rand7() + (index - 40 - 1) * 7;
                if (index <= 60) {
                    return 1 + (index - 1) % 10;
                }

                index = rand7() + (index - 60 - 1) * 7;
                if (index <= 20) {
                    return 1 + (index - 1) % 10;
                }
            }
        }
    }
}
