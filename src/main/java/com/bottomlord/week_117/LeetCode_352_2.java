package com.bottomlord.week_117;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2021-10-09 08:46:23
 */
public class LeetCode_352_2 {
    class SummaryRanges {
        private List<int[]> list;

        public SummaryRanges() {
            this.list = new ArrayList<>();
        }

        public void addNum(int val) {
            if (list.size() == 0) {
                list.add(new int[]{val, val});
                return;
            }

            int l = 0, r = list.size() - 1;
            while (l < r) {
                int mid = l + r + 1 >> 1;
                if (val >= list.get(mid)[0]) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }

            int[] cur = list.get(r);

            if (val >= cur[0] && val <= cur[1]) {
                return;
            }

            if (val < cur[0]) {
                if (val + 1 == cur[0]) {
                    cur[0] = val;
                } else {
                    list.add(new int[]{val, val});
                }

                return;
            }

            if (r == list.size() - 1) {
                if (cur[1] + 1 == val) {
                    cur[1] = val;
                } else {
                    list.add(new int[]{val, val});
                }
                return;
            }

            int[] next = list.get(r + 1);
            if (cur[1] + 1 == val && val == next[0] - 1) {
                cur[1] = next[1];
                list.remove(r + 1);
            } else if (cur[1] + 1 == val) {
                cur[1] = val;
            } else if (next[0] - 1 == val) {
                next[0] = val;
            } else {
                list.add(r + 1, new int[]{val, val});
            }

        }

        public int[][] getIntervals() {
            int[][] ans = new int[list.size()][2];
            for (int i = 0; i < list.size(); i++) {
                ans[i] = list.get(i);
            }
            return ans;
        }
    }
}
