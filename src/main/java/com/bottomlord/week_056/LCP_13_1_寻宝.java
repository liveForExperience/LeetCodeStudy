package com.bottomlord.week_056;

import java.util.*;

/**
 * @author ChenYue
 * @date 2020/7/29 7:13
 */
public class LCP_13_1_寻宝 {
    private final int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private int row, col;
    private String[] maze;

    public int minimalSteps(String[] maze) {
        this.row = maze.length;
        this.col = maze[0].length();
        this.maze = maze;

        List<int[]> os = new ArrayList<>(),
                ms = new ArrayList<>();

        int sx = -1, sy = -1, tx = -1, ty = -1;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                char c = getChar(i, j);

                if (c == 'M') {
                    ms.add(new int[]{i, j});
                } else if (c == 'O') {
                    os.add(new int[]{i, j});
                } else if (c == 'S') {
                    sx = i;
                    sy = j;
                } else if (c == 'T') {
                    tx = i;
                    ty = j;
                }
            }
        }

        int oSize = os.size(), mSize = ms.size();

        int[][] startDist = bfs(sx, sy);

        if (mSize == 0) {
            return startDist[tx][ty];
        }

        int[][] dists = new int[mSize][mSize + 2];
        for (int[] dist : dists) {
            Arrays.fill(dist, -1);
        }

        int[][][] mDists = new int[mSize][][];
        for (int i = 0; i < mSize; i++) {
            int[][] mDist = bfs(ms.get(i)[0], ms.get(i)[1]);
            mDists[i] = mDist;
            dists[i][mSize + 1] = mDist[tx][ty];
        }

        for (int i = 0; i < mSize; i++) {
            int startPhaseDist = -1;
            for (int[] o : os) {
                int ox = o[0], oy = o[1];
                if (mDists[i][ox][oy] != -1 && startDist[ox][oy] != -1) {
                    if (startPhaseDist == -1 || startPhaseDist > mDists[i][ox][oy] + startDist[ox][oy]) {
                        startPhaseDist = mDists[i][ox][oy] + startDist[ox][oy];
                    }
                }
            }
            dists[i][mSize] = startPhaseDist;
        }

        for (int i = 0; i < mSize; i++) {
            for (int j = i + 1; j < mSize; j++) {
                int midPhaseDist = -1;
                for (int k = 0; k < oSize; k++) {
                    int ox = os.get(k)[0], oy = os.get(k)[1],
                            ioDist = mDists[i][ox][oy], joDist = mDists[j][ox][oy];

                    if (ioDist != -1 && joDist != -1) {
                        if (midPhaseDist == -1 || midPhaseDist > ioDist + joDist) {
                            midPhaseDist = ioDist + joDist;
                        }
                    }
                }

                dists[i][j] = midPhaseDist;
                dists[j][i] = midPhaseDist;
            }
        }

        for (int i = 0; i < mSize; i++) {
            if (dists[i][mSize + 1] == -1 || dists[i][mSize] == -1) {
                return -1;
            }
        }

        int[][] dp = new int[1 << mSize][mSize];
        for (int[] arr : dp) {
            Arrays.fill(arr, -1);
        }

        for (int i = 0; i < mSize; i++) {
            dp[1 << i][i] = dists[i][mSize];
        }

        for (int mask = 1; mask < (1 << mSize); mask++) {
            for (int i = 0; i < mSize; i++) {
                if ((mask & (1 << i)) != 0) {
                    for (int j = 0; j < mSize; j++) {
                        if ((mask & (1 << j)) == 0) {
                            int nextMask = mask | (1 << j);
                            if (dp[nextMask][j] == -1 || dp[nextMask][j] > dp[mask][i] + dists[i][j]) {
                                dp[nextMask][j] = dp[mask][i] + dists[i][j];
                            }
                        }
                    }
                }
            }
        }

        int ans = -1, finalMask = (1 << mSize) - 1;
        for (int i = 0; i < mSize; i++) {
            ans = Math.min(dp[finalMask][i] + dists[i][mSize + 1], ans);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private int[][] bfs(int x, int y) {
        int[][] ans = new int[row][col];

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});

        for (int[] arr : ans) {
            Arrays.fill(arr, -1);
        }

        ans[x][y] = 0;

        while (!queue.isEmpty()) {
            int count = queue.size();

            while (count-- > 0) {
                int[] arr = queue.poll();
                if (arr == null) {
                    continue;
                }

                int curX = arr[0], curY = arr[1];
                for (int[] direction : directions) {
                    int nextX = curX + direction[0],
                            nextY = curY + direction[1];

                    if (isValid(nextX, nextY) && getChar(nextX, nextY) != '#' && ans[nextX][nextY] == -1) {
                        ans[nextX][nextY] = ans[curX][curY] + 1;
                        queue.offer(new int[]{nextX, nextY});
                    }
                }
            }
        }

        return ans;
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < row && y >= 0 && y < col;
    }

    private char getChar(int x, int y) {
        return maze[x].charAt(y);
    }
}