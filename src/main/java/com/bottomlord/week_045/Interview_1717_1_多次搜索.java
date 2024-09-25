package com.bottomlord.week_045;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author ChenYue
 * @date 2020/5/11 17:53
 */
public class Interview_1717_1_多次搜索 {
    public int[][] multiSearch(String big, String[] smalls) {
        int[][] ans = new int[smalls.length][];
        for (int i = 0; i < smalls.length; i++) {
            List<Integer> list = new ArrayList<>();
            String small = smalls[i];
            if (Objects.equals(small, "")) {
                ans[i] = new int[0];
                continue;
            }

            int index = 0, pos = big.indexOf(small, index);
            while (pos != -1) {
                list.add(pos);
                index = pos + small.length();
                pos = big.indexOf(small, index);
            }
            ans[i] = list.stream().mapToInt(x -> x).toArray();
        }
        return ans;
    }
}
