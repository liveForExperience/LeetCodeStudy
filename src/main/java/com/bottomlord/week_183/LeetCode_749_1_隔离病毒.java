package com.bottomlord.week_183;

import java.util.*;

/**
 * @author chen yue
 * @date 2022-07-18 08:56:48
 */
public class LeetCode_749_1_隔离病毒 {

    private final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int containVirus(int[][] isInfected) {
        int ans = 0, r = isInfected.length, c = isInfected[0].length;

        while (true) {
            List<Set<Integer>> neighbourhoods = new ArrayList<>();
            List<Integer> qList = new ArrayList<>();
            int idx;

            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (isInfected[i][j] != 1) {
                        continue;
                    }
                    idx = -(neighbourhoods.size() + 1);
                    int q = 0;
                    Set<Integer> neighbourhood = new HashSet<>();

                    Queue<int[]> queue = new ArrayDeque<>();
                    queue.offer(new int[]{i, j});
                    isInfected[i][j] = idx;

                    while (!queue.isEmpty()) {
                        int[] arr = queue.poll();
                        if (arr == null) {
                            continue;
                        }

                        int x = arr[0], y = arr[1];

                        for (int[] dir : dirs) {
                            int nx = dir[0] + x, ny = dir[1] + y;
                            if (nx < 0 || nx >= r || ny < 0 || ny >= c || isInfected[nx][ny] < 0) {
                                continue;
                            }

                            if (isInfected[nx][ny] == 1) {
                                queue.offer(new int[]{nx, ny});
                                isInfected[nx][ny] = idx;
                            } else if (isInfected[nx][ny] == 0) {
                                neighbourhood.add(getHash(nx, ny));
                                q++;
                            }
                        }
                    }

                    neighbourhoods.add(neighbourhood);
                    qList.add(q);
                }
            }

            if (neighbourhoods.size() == 0) {
                break;
            }

            idx = -1;
            int maxLen = 0;
            for (int i = 0; i < neighbourhoods.size(); i++) {
                Set<Integer> neighbourhood = neighbourhoods.get(i);
                if (neighbourhood.size() > maxLen) {
                    idx = -i - 1;
                    maxLen = neighbourhood.size();
                }
            }

            ans += qList.get(-idx - 1);

            if (neighbourhoods.size() == 1) {
                break;
            }

            for (int i = 0; i < neighbourhoods.size(); i++) {
                if (i == -idx - 1) {
                    continue;
                }

                for (Integer num : neighbourhoods.get(i)) {
                    isInfected[num >> 16][num & ((1 << 16) - 1)] = 1;
                }
            }

            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (isInfected[i][j] == idx) {
                        isInfected[i][j] = 2;
                    } else if (isInfected[i][j] < 0) {
                        isInfected[i][j] = 1;
                    }
                }
            }
        }

        return ans;
    }

    private int getHash(int x, int y) {
        return (x << 16) ^ y;
    }
}
