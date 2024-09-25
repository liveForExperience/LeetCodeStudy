package com.bottomlord.week_071;

import java.util.*;

/**
 * @author ChenYue
 * @date 2020/11/21 11:03
 */
public class LeetCode_352_1_将数据流变为多个不相交区域 {
    class SummaryRanges {
        private Set<Integer> set;
        /** Initialize your data structure here. */
        public SummaryRanges() {
            this.set = new TreeSet<>();
        }

        public void addNum(int val) {
            this.set.add(val);
        }

        public int[][] getIntervals() {
            List<int[]> list = new LinkedList<>();
            int[] nums = new int[2];
            Iterator<Integer> iterator = set.iterator();
            while (iterator.hasNext()) {
                int num = iterator.next();
                if (!set.contains(num - 1) && !set.contains(num + 1)) {
                    nums = new int[]{num, num};
                    list.add(nums);
                    nums = new int[2];
                    continue;
                }

                if (set.contains(num - 1) && set.contains(num + 1)) {
                    continue;
                }

                if (!set.contains(num - 1) && set.contains(num + 1)) {
                    nums[0] = num;
                    continue;
                }

                if (set.contains(num - 1) && !set.contains(num + 1)) {
                    nums[1] = num;
                    list.add(nums);
                    nums = new int[2];
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
