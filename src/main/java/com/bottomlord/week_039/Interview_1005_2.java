package com.bottomlord.week_039;

/**
 * @author ChenYue
 * @date 2020/4/4 22:36
 */
public class Interview_1005_2 {
    public int findString(String[] words, String s) {
        int l = 0, r = words.length - 1;
        while (l <= r) {
            while (l <= r && "".equals(words[l])) {
                l++;
            }
            while (l <= r && "".equals(words[r])) {
                r--;
            }
            int mid = l + (r - l) / 2;
            while (mid < r && "".equals(words[mid])) {
                mid++;
            }
            if (words[mid].equals(s)) {
                return mid;
            }

            if (s.compareTo(words[mid]) > 0) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return -1;
    }
}