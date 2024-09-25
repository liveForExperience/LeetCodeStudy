package com.bottomlord.week_032;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ThinkPad
 * @date 2020/2/11 16:08
 */
public class LeetCode_729_1_我的日程安排表I {
    class MyCalendar {
        private List<int[]> list;
        public MyCalendar() {
            this.list = new ArrayList<>();
        }

        public boolean book(int start, int end) {
            for (int[] arr : list) {
                if (start < arr[1] && end > arr[0]) {
                    return false;
                }
            }

            list.add(new int[]{start, end});
            return true;
        }
    }
}
