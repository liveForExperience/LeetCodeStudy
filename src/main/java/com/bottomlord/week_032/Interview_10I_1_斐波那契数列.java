package com.bottomlord.week_032;

/**
 * @author ThinkPad
 * @date 2020/2/15 19:13
 */
public class Interview_10I_1_斐波那契数列 {
    public int fib(int n) {
        return n <= 1 ? n : (fib(n - 1) + fib(n - 2)) % 1000000007;
    }
}
