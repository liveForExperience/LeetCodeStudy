package com.bottomlord.week_8;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_970_1_强整数 {
    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        List<Integer> ans = new ArrayList<>();
        boolean[] bucket = new boolean[bound + 1];
        int xi = 0;

        while (true) {
            int count = 0, yi = 0;
            while (true) {
                int sum = (int)(Math.pow(x, xi) + Math.pow(y, yi));
                if (sum <= bound) {
                    bucket[sum] = true;
                    yi++;
                    count++;

                    if (y == 1) {
                        break;
                    }
                } else {
                    break;
                }
            }

            if (x == 1) {
                break;
            }

            if (count > 0) {
                xi++;
            } else {
                break;
            }
        }

        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i]) {
                ans.add(i);
            }
        }

        return ans;
    }
}
