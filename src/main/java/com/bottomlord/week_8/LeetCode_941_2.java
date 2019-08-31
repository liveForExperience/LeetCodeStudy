package com.bottomlord.week_8;

public class LeetCode_941_2 {
    public boolean validMountainArray(int[] A) {
        if (A.length < 3) {
            return false;
        }

        int head = 0, tail = A.length - 1;
        while (head < A.length - 1 && A[head] < A[head + 1]) {
            head++;
        }

        while (tail > 0 && A[tail] < A[tail - 1]) {
            tail--;
        }

        return head == tail;
    }
}