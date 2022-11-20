# [LeetCode_790_多米诺和托米诺平铺](https://leetcode.cn/problems/domino-and-tromino-tiling/)
## 解法
### 思路
动态规划：
- dp[i][j]：第i列状态为j时候的方法数
  - i从1开始，结果从i=n中获取
  - j有4种状态
    - 0：全部填满
    - 1：上半部分填满
    - 2：下半部分填满
    - 3：没有
- 状态转移方程：
  - dp[0] = dp[0] + dp[1] + dp[2] + dp[3]
  - dp[1] = dp[3] + dp[2]
  - dp[2] = dp[3] + dp[1]
  - dp[3] = dp[0]
- base case：dp[0][0] = 1
- 返回结果：dp[n][0]
### 代码
```java
class Solution {
    public int numTilings(int n) {
        long[] dp = {1, 0, 0, 0};
        int mod = (int) 1e9 + 7;
        for (int i = 1; i <= n; ++i) {
            long[] g = new long[4];
            g[0] = (dp[0] + dp[1] + dp[2] + dp[3]) % mod;
            g[1] = (dp[2] + dp[3]) % mod;
            g[2] = (dp[1] + dp[3]) % mod;
            g[3] = dp[0];
            dp = g;
        }
        return (int) dp[0];
    }
}
```
# [LeetCode_864_获取所有钥匙的最短路径](https://leetcode.cn/problems/shortest-path-to-get-all-keys/)
## 解法
### 思路
bfs+状态压缩
- 队列中储存的长度为3的数组，分别
  - 横坐标
  - 纵坐标
  - 获取钥匙的状态（整数）
- 初始化一个3维数组，用于记录坐标x,y情况下，状态为s的最短路径，初始化为-1，代表没有到达过
- bfs遍历
  - 越界停止
  - 遇到墙停止
  - 遇到锁，如果没有钥匙，停止
  - 遇到钥匙更新钥匙状态(给每个锁初始化在状态中的二进制坐标，从而方便更新)
  - 如果当前位置的相同状态，没有到达过，那么就基于前一个位置的前一种状态的距离值，+1之后更新当前的值
  - 当状态与拿到所有钥匙的状态一致时，返回距离值
- 遍历结束，则说明没有可能的路径，返回-1
### 代码
```java
class Solution {
    private final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int shortestPathAllKeys(String[] grid) {
        int row = grid.length, col = grid[0].length(), num = 0, sr = -1, sc = -1;
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < grid.length; i++) {
            char[] cs = grid[i].toCharArray();
            for (int j = 0; j < cs.length; j++) {
                char c = cs[j];
                if (Character.isLowerCase(c)) {
                    num++;
                }

                if (c == '@') {
                    sr = i;
                    sc = j;
                    queue.offer(new int[]{i, j, 0});
                }
            }
        }

        int mask = (1 << num) - 1;
        int[][][] matrix = new int[row][col][mask + 1];
        for (int[][] r : matrix) {
            for (int[] c : r) {
                Arrays.fill(c, -1);
            }
        }
        matrix[sr][sc][0] = 0;

        while (!queue.isEmpty()) {
            int count = queue.size();
            while (count-- > 0) {
                int[] arr = queue.poll();
                if (arr == null) {
                    continue;
                }

                int r = arr[0], c = arr[1], s = arr[2];

                for (int[] dir : dirs) {
                    int nr = r + dir[0], nc = c + dir[1];
                    if (nr < 0 || nr >= row || nc < 0 || nc >= col) {
                        continue;
                    }

                    char ch = grid[nr].charAt(nc);
                    if (ch == '#') {
                        continue;
                    }

                    int ns = s;
                    if (Character.isLowerCase(ch)) {
                        ns |= (1 << (ch - 'a'));
                    }

                    if (matrix[nr][nc][ns] != -1) {
                        continue;
                    }

                    if (Character.isUpperCase(ch)) {
                        if ((s & (1 << (ch - 'A'))) == 0) {
                            continue;
                        }
                    }

                    matrix[nr][nc][ns] = matrix[r][c][s] + 1;
                    if (ns == mask) {
                        return matrix[nr][nc][ns];
                    }

                    queue.offer(new int[]{nr, nc, ns});
                }
            }
        }

        return -1;
    }
}
```
# [LeetCode_805_数值的均值分割](https://leetcode.cn/problems/split-array-with-same-average/)
## 解法
### 思路
折半搜索
- 公式推倒
  - sumA / lenA = sumB / lenB
  - sumA / (n - lenB) = sumB / lenB
  - sumA / n - sumA / lenB= sumB / lenB
  - sumA / n = sum / lenB
### 代码
```java

```