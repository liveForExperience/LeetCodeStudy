package com.bottomlord.week_039;

/**
 * @author ChenYue
 * @date 2020/3/31 8:34
 */
public class Interview_0805_1_递归乘法 {
    public int multiply(int A, int B) {
        return B > 1 ? multiply(A, B - 1) + A : A;
    }
}
