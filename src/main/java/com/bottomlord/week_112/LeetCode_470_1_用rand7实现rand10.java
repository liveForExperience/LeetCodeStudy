package com.bottomlord.week_112;

/**
 * @author chen yue
 * @date 2021-09-05 23:14:35
 */
public class LeetCode_470_1_用rand7实现rand10 {
    public int rand10() {
        int num;
        do {
            num = (rand7()  - 1) * 7 + rand7();
        } while (num > 40);

        return 1 + num % 10;
    }

    private int rand7() {
        return 0;
    }
}
