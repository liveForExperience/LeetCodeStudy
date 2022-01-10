package com.bottomlord.week_131;

/**
 * @author chen yue
 * @date 2022-01-10 20:52:19
 */
public class LeetCode_2037_2 {
    public int minMovesToSeat(int[] seats, int[] students) {
        sort(seats);
        sort(students);
        int sum = 0;
        for (int i = 0; i < seats.length; i++) {
            sum += Math.abs(seats[i] - students[i]);
        }

        return sum;
    }

    private void sort(int[] arr) {
        doSort(0, arr.length - 1, arr);
    }

    private void doSort(int head, int tail, int[] arr) {
        if (head >= tail) {
            return;
        }

        int pivot = partition(head, tail, arr);

        doSort(head, pivot - 1, arr);
        doSort(pivot + 1, tail, arr);
    }

    private int partition(int head, int tail, int[] arr) {
        int num = arr[head];
        while (head < tail) {
            while (head < tail && arr[tail] >= num) {
                tail--;
            }

            arr[head] = arr[tail];

            while (head < tail && arr[head] <= num) {
                head++;
            }

            arr[tail] = arr[head];
        }

        arr[head] = num;
        return head;
    }
}
