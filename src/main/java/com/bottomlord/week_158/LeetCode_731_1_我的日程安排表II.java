package com.bottomlord.week_158;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2022-07-20 19:55:18
 */
public class LeetCode_731_1_我的日程安排表II {
    class MyCalendarTwo {
        private List<int[]> once, twice;
        public MyCalendarTwo() {
            this.once = new ArrayList<>();
            this.twice = new ArrayList<>();
        }

        public boolean book(int start, int end) {
            for (int[] two : twice) {
                int l = two[0], r = two[1];
                if (start < r && end > l) {
                    return false;
                }
            }

            for (int[] one : once) {
                int l = one[0], r = one[1];
                if (start < r && end > l) {
                    twice.add(new int[]{Math.max(l, start), Math.min(r, end)});
                }
            }

            once.add(new int[]{start, end});
            return true;
        }
    }
}
