package com.bottomlord.week_040;

/**
 * @author ChenYue
 * @date 2020/4/11 19:26
 */
public class Interview_1614_1_最佳直线 {
    public int[] bestLine(int[][] points) {
        int[] ans = new int[2];
        int max = 0;

        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                int x1 = points[i][0], y1 = points[i][1];
                int x2 = points[j][0], y2 = points[j][1];

                int a = y2 - y1, b = x1 - x2, c = y1 * (x2 - x1) - x1 * (y2 - y1);
                int count = 0;
                for (int[] point : points) {
                    if (atLine(a, b, c, point[0], point[1])) {
                        count++;
                    }
                }

                if (count > max) {
                    max = count;
                    ans[0] = i;
                    ans[1] = j;
                } else if (count == max) {
                    if (i == ans[0] && j < ans[1]) {
                        ans[1] = j;
                    }
                }
            }
        }

        return ans;
    }

    private boolean atLine(int a, int b, int c, int x, int y) {
        return a * x + b * y + c == 0;
    }
}
