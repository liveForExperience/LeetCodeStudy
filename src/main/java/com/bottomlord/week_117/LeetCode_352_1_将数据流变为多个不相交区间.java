package com.bottomlord.week_117;

import java.util.*;

/**
 * @author chen yue
 * @date 2021-10-09 08:31:38
 */
public class LeetCode_352_1_将数据流变为多个不相交区间 {
    class SummaryRanges {
        private TreeSet<Integer> set;
        public SummaryRanges() {
            this.set = new TreeSet<>();
        }

        public void addNum(int val) {
            this.set.add(val);
        }

        public int[][] getIntervals() {
            Iterator<Integer> iterator = set.iterator();
            List<int[]> list = new ArrayList<>();
            int[] arr = new int[2];
            while (iterator.hasNext()) {
                int num = iterator.next();

                if (!set.contains(num - 1) && !set.contains(num + 1)) {
                    list.add(new int[]{num, num});
                    continue;
                }

                if (set.contains(num - 1) && set.contains(num + 1)) {
                    continue;
                }

                if (!set.contains(num - 1) && set.contains(num + 1)) {
                    arr[0] = num;
                    continue;
                }

                if (set.contains(num - 1) && !set.contains(num + 1)) {
                    arr[1] = num;
                    list.add(arr);
                    arr = new int[2];
                }
            }

            int[][] ans = new int[list.size()][2];
            for (int i = 0; i < list.size(); i++) {
                ans[i][0] = list.get(i)[0];
                ans[i][1] = list.get(i)[1];
            }
            return ans;
        }
    }
}
