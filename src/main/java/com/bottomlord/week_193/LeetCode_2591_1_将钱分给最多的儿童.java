package com.bottomlord.week_193;

/**
 * @author chen yue
 * @date 2023-03-20 20:53:39
 */
public class LeetCode_2591_1_将钱分给最多的儿童 {
    public int distMoney(int money, int children) {
        if (money < children) {
            return -1;
        }

        money -= children;

        int c = money / 7, leftMoney = money % 7;

        if (c == children) {
            return leftMoney == 0 ? children : Math.max(0, children - 1);
        } else if (c > children) {
            return Math.max(0, children - 1);
        } else {
            int leftChildren = Math.max(0, children - c);
            if (leftChildren == 1 && leftMoney == 3) {
                return Math.max(0, c - 1);
            }

            return c;
        }
    }
}
