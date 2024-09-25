package com.bottomlord.week_206;

import java.util.*;

/**
 * @author chen yue
 * @date 2023-06-22 11:48:55
 */
public class LeetCode_interview_16_19_1_水域大小 {

    private int[][] land;
    private int row, col;

    public int[] pondSizes(int[][] land) {
        this.land = land;
        this.row = land.length;
        this.col = land[0].length;

        List<Integer> list = new ArrayList<>();
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < col; y++) {
                if (land[x][y] != 0) {
                    continue;
                }

                list.add(bfs(x, y));
            }
        }

        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        Arrays.sort(ans);
        return ans;
    }

    private int bfs(int x, int y) {
        int count = 1;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});
        land[x][y] = -1;
        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int r = arr[0], c = arr[1];

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i == 0 && j == 0) {
                        continue;
                    }

                    int nr = r + i, nc = c + j;
                    if (!isValid(nr, nc)) {
                        continue;
                    }

                    queue.offer(new int[]{nr, nc});
                    land[nr][nc] = -1;
                    count++;
                }
            }
        }

        return count;
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < row && y >= 0 && y < col && land[x][y] == 0;
    }
}