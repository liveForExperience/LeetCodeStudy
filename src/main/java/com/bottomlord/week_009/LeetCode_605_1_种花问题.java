package com.bottomlord.week_009;

public class LeetCode_605_1_种花问题 {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length; ) {
            if (n == 0) {
                break;
            }

            if (flowerbed[i] == 1) {
                i += 2;
            } else if (flowerbed[i] == 0) {
                if (i == 0) {
                    if (flowerbed.length == 1 || flowerbed[1] == 0) {
                        flowerbed[i] = 1;
                        i += 2;
                        n--;
                    } else {
                        i++;
                    }
                } else if (i == flowerbed.length - 1 && flowerbed[i - 1] == 0) {
                    flowerbed[i] = 1;
                    i += 2;
                    n--;
                } else if (flowerbed[i - 1] == 0 && flowerbed[i + 1] == 0) {
                    flowerbed[i] = 1;
                    i += 2;
                    n--;
                } else {
                    i++;
                }
            }
        }
        return n == 0;
    }
}
