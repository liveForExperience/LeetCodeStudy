package com.bottomlord.week_061;

/**
 * @author ChenYue
 * @date 2020/9/3 8:26
 */
public class LeetCode_252_2 {
    public boolean canAttendMeetings(int[][] intervals) {
        quickSort(intervals, 0, intervals.length - 1);
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i][1] > intervals[i + 1][0]) {
                return false;
            }
        }
        return true;
    }

    private void quickSort(int[][] intervals, int head, int tail) {
        if (head >= tail) {
            return;
        }

        int partition = partition(intervals, head, tail);

        quickSort(intervals, head, partition - 1);
        quickSort(intervals, partition + 1, tail);
    }

    private int partition(int[][] intervals, int head, int tail) {
        int[] pivot = intervals[head];
        while (head < tail) {
            while (head < tail && intervals[tail][0] >= pivot[0]) {
                tail--;
            }
            intervals[head] = intervals[tail];

            while (head < tail && intervals[head][0] <= pivot[0]) {
                head++;
            }
            intervals[tail] = intervals[head];
        }

        intervals[head] = pivot;
        return head;
    }
}