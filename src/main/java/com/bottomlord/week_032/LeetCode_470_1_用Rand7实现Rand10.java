package com.bottomlord.week_032;

/**
 * @author ThinkPad
 * @date 2020/2/10 11:51
 */
public class LeetCode_470_1_用Rand7实现Rand10 {
    class Solution extends SolBase {
        public int rand10() {
            int index, col, row;
            do {
                col = rand7();
                row = rand7();
                index = col + (row - 1) * 7;
            } while (index > 40);

            return 1 + (index - 1) % 10;
        }
    }
}
