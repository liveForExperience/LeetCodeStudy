package com.bottomlord.week_096;

public class LeetCode_LCP29_4 {
    public int orchestraLayout(int num, int xPos, int yPos) {
        if (num == 1) {
            return 1;
        }

        long layer = Math.min(xPos, Math.min(yPos, Math.min(num -  1 - xPos, num - 1 - yPos)));

        long sum = layer * (num - layer) * 4 % 9;
        long count = (num - 1) - 2 * layer;

        if (layer == xPos) {
            sum += yPos - layer + 1;
        } else if (layer == num - 1 - yPos) {
            sum += count + xPos - layer + 1;
        } else if (layer == num - 1 - xPos) {
            sum += count * 2 + num - 1 - layer - yPos + 1;
        } else {
            sum += count * 3 + num - 1 - layer - xPos + 1;
        }

        int mod = (int) (sum % 9);
        return mod == 0 ? 9 : mod;
    }
}
