# [LeetCode_827_最大人工岛](https://leetcode.cn/problems/making-a-large-island/)
## 解法
### 思路
- 先搜索岛屿图，对每个岛屿做标记，通过hash表存储
- 然后遍历岛屿图，找到海水的坐标，尝试将其设置为1，然后和四个方向进行连接，看能最多连接出多大的岛屿大小
- 遍历结束后返回最大的岛屿大小值
- 过程中使用了dfs
### 代码
```java
class Solution {
    private final int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int largestIsland(int[][] grid) {
        int r = grid.length, c = grid[0].length;
        int[][] tags = new int[r][c];
        for (int[] tag : tags) {
            Arrays.fill(tag, -1);
        }

        Map<Integer, Integer> tagMap = new HashMap<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0 || tags[i][j] != -1) {
                    continue;
                }

                int tag = getTag(i, j, r);
                tagMap.put(tag, dfs(grid, tags, i, j, r, c, tag));
            }
        }

        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    max = Math.max(max, tagMap.get(tags[i][j]));
                    continue;
                }

                Set<Integer> tagSet = new HashSet<>();
                int sum = 1;
                grid[i][j] = 1;
                for (int[] dir : dirs) {
                    int nx = i + dir[0], ny = j + dir[1];
                    if (nx < 0 || nx >= r || ny < 0 || ny >= c) {
                        continue;
                    }

                    int tag = tags[nx][ny];
                    if (tag == -1 || !tagSet.add(tag)) {
                        continue;
                    }

                    sum += tagMap.get(tag);
                }
                grid[i][j] = 0;

                max = Math.max(max, sum);
            }
        }

        return max;
    }

    private int dfs(int[][] grid, int[][] tags, int x, int y, int r, int c, int tag) {
        if (!isValid(x, y, r, c, grid, tags)) {
            return 0;
        }

        tags[x][y] = tag;

        int cur = 1;
        for (int[] dir : dirs) {
            cur += dfs(grid, tags, x + dir[0], y + dir[1], r, c, tag);
        }

        return cur;
    }

    private int getTag(int x, int y, int r) {
        return x * r + y;
    }

    private boolean isValid(int x, int y, int r, int c, int[][] grid, int[][] tags) {
        return x >= 0 && x < r && y >= 0 && y < c && tags[x][y] == -1 && grid[x][y] == 1;
    }
}
```