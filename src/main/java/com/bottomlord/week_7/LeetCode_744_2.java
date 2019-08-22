package com.bottomlord.week_7;

public class LeetCode_744_2 {
    public char nextGreatestLetter(char[] letters, char target) {
        if (letters == null || letters.length == 0) {
            return 0;
        }

        int l = 0, r = letters.length - 1;
        if (target < letters[l] || target >= letters[r]) {
            return letters[l];
        }

        while (l < r) {
            int mid = l + r >> 1;
            if (letters[mid] > target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return letters[r];
    }
}