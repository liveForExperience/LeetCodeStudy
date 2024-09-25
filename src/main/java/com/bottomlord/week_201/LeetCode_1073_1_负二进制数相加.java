package com.bottomlord.week_201;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author chen yue
 * @date 2023-05-18 09:36:52
 */
public class LeetCode_1073_1_负二进制数相加 {
    public int[] addNegabinary(int[] arr1, int[] arr2) {
        int carry = 0;
        int a = arr1.length - 1, b = arr2.length - 1;
        List<Integer> list = new ArrayList<>();
        while (a >= 0 || b >= 0) {
            if (a < 0) {
                int x = carry + arr2[b];
                list.add(getCur(x));
                carry = getCarry(x);
                b--;
                continue;
            }

            if (b < 0) {
                int x = carry + arr1[a];
                list.add(getCur(x));
                carry = getCarry(x);
                a--;
                continue;
            }

            int x = arr1[a] + arr2[b] + carry;
            list.add(getCur(x));
            carry = getCarry(x);
            a--;
            b--;
        }

        if (carry == 1) {
            list.add(1);
        }

        if (carry == -1) {
            list.add(1);
            list.add(1);
        }

        int n = list.size();
        if (n == 0) {
            return new int[]{0};
        }

        int[] ans = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            ans[n - i - 1] = list.get(i);
        }

        int i = 0;
        for (; i < ans.length; i++) {
            if (ans[i] == 1) {
                break;
            }
        }

        ans = Arrays.copyOfRange(ans, i, n);
        if (ans.length == 0) {
            return new int[]{0};
        }

        return ans;
    }

    private int getCarry(int x) {
        if (x == 0 || x == 1) {
            return 0;
        }

        if (x == 2 || x == 3) {
            return -1;
        }

        return 1;
    }

    private int getCur(int x) {
        if (x == 0 || x == 1) {
            return x;
        }

        if (x == 2 || x == 3) {
            return x - 2;
        }

        return 1;
    }
}
