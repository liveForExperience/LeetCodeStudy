package com.bottomlord.week_066;

public class LeetCode_302_3 {
    public int minArea(char[][] image, int x, int y) {
        if (image.length == 0 || image[0].length == 0) {
            return 0;
        }

        int l = findLeft(image, y);
        int r = findRight(image, y);
        int t = findTop(image, x);
        int b = findBottom(image, x);

        return (r - l) * (b - t);
    }

    private int findLeft(char[][] image, int y) {
        int head = 0, tail = y;
        while (head < tail) {
            int mid = head + (tail - head) / 2;
            int index = 0;
            while (index < image.length && image[index][mid] == '0') {
                index++;
            }

            if (index == image.length) {
                head = mid + 1;
            } else {
                tail = mid;
            }
        }

        return head;
    }

    private int findRight(char[][] image, int y) {
        int head = y + 1, tail = image[0].length;
        while (head < tail) {
            int mid = head + (tail - head) / 2;
            int index = 0;
            while (index < image.length && image[index][mid] == '0') {
                index++;
            }

            if (index == image.length) {
                tail = mid;
            } else {
                head = mid + 1;
            }
        }

        return head;
    }

    private int findTop(char[][] image, int x) {
        int head = 0, tail = x;
        while (head < tail) {
            int mid = head + (tail - head) / 2;
            int index = 0;
            while (index < image[0].length && image[mid][index] == '0') {
                index++;
            }

            if (index == image[0].length) {
                head = mid + 1;
            } else {
                tail = mid;
            }
        }

        return head;
    }

    private int findBottom(char[][] image, int x) {
        int head = x + 1, tail = image.length;
        while (head < tail) {
            int mid = head + (tail - head) / 2;
            int index = 0;
            while (index < image[0].length && image[mid][index] == '0') {
                index++;
            }

            if (index == image[0].length) {
                tail = mid;
            } else {
                head = mid + 1;
            }
        }

        return head;
    }
}
