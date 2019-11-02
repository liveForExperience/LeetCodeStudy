package com.bottomlord.week_017;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_1238_1_循环码排列 {
    public List<Integer> circularPermutation(int n, int start) {
        List<Integer> list = new ArrayList<Integer>(){{add(0);}};
        int head = 1, index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = list.size() - 1; j >= 0; j--) {
                int num = head + list.get(j);
                list.add(num);

                if (num == start) {
                    index = list.size() - 1;
                }
            }
            head <<= 1;
        }

        List<Integer> ans = new ArrayList<>();
        ans.addAll(list.subList(index, list.size() - 1));
        ans.addAll(list.subList(0, index - 1));
        return ans;
    }
}
