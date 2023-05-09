# [LeetCode_1263_推箱子](https://leetcode.cn/problems/minimum-moves-to-move-a-box-to-their-target-location/)
## 解法
### 思路
bfs
- 因为题目中不仅包括箱子，还包括人，所以bfs的驱动元素需要同时包含箱子和人的坐标信息
- bfs过程中通过一个记事本数组来记录当前箱子和人的坐标状态时，经过的最小步数，这个步数基于bfs过程来推导，其实也就是在做动态规划
- bfs过程中，内层循环的时候，在循环体之外再维护一个队列，这个队列用于存储箱子移动后的人和箱子的状态，而同时内层循环的时候，不仅有箱子动的状态，也有人动但箱子不动的状态，这种状态就在内层循环掉
- 循环过程中对记事本进行更新，为了区分是否搜索过，初始化这个记事本的所有元素为int的最大值
- 在内层循环过程中，如果箱子的坐标到达了`T`的位置，那么就返回记事本记录的值即可
### 代码
```java
class Solution {
    private int r, c;
    private int[][] memo;
    private char[][] grid;

    public int minPushBox(char[][] grid) {
        this.r = grid.length;
        this.c = grid[0].length;
        this.memo = new int[c * r][c * r];
        this.grid = grid;

        int sx = -1, sy = -1, bx = -1, by = -1;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 'S') {
                    sx = i;
                    sy = j;
                } else if (grid[i][j] == 'B') {
                    bx = i;
                    by = j;
                }
            }
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{sx * c + sy, bx * c + by});
        int[] dirs = new int[]{0, -1, 0, 1, 0};
        for (int[] arr : memo) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }

        memo[sx * c + sy][bx * c + by] = 0;

        while (!queue.isEmpty()) {
            Queue<int[]> next = new ArrayDeque<>();
            while (!queue.isEmpty()) {
                int[] arr = queue.poll();
                int s1 = arr[0], b1 = arr[1],
                    sx1 = s1 / c, sy1 = s1 % c,
                    bx1 = b1 / c, by1 = b1 % c;

                if (grid[bx1][by1] == 'T') {
                    return memo[s1][b1];
                }

                for (int i = 0; i < 4; i++) {
                    int sx2 = sx1 + dirs[i], sy2 = sy1 + dirs[i + 1], s2 = sx2 * c + sy2;

                    if (!ok(sx2, sy2)) {
                        continue;
                    }

                    if (sx2 == bx1 && sy2 == by1) {
                        int bx2 = bx1 + dirs[i], by2 = by1 + dirs[i + 1], b2 = bx2 * c + by2;
                        if (!ok(bx2, by2) || memo[s2][b2] <= memo[s1][b1] + 1) {
                            continue;
                        }

                        memo[s2][b2] = memo[s1][b1] + 1;
                        next.offer(new int[]{s2, b2});
                    } else {
                        if (memo[s2][b1] <= memo[s1][b1]) {
                            continue;
                        }

                        memo[s2][b1] = memo[s1][b1];
                        queue.offer(new int[]{s2, b1});
                    }
                }
            }
            
            queue = next;
        }

        return -1;
    }

    private boolean ok(int x, int y) {
        return x >= 0 && x < r && y >= 0 && y < c && grid[x][y] != '#';
    }
}
```
# [LeetCode_967_连续差相同的数字]()
## 解法
### 思路
dfs，需要注意k为0的情况，不要重复搜索，其他就是简单的深度优先搜索即可
### 代码
```java
class Solution {
    private int k, n;
    public int[] numsSameConsecDiff(int n, int k) {
        this.k = k;
        this.n = n;
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            dfs(1, i, i, list);
        }
        
        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    private void dfs(int index, int num, int pre, List<Integer> list) {
        if (index >= n) {
            list.add(num);
            return;
        }
        
        if (pre + k <= 9) {
            dfs(index + 1, num * 10 + pre + k, pre + k, list);
        }
        
        if (pre - k >= 0 && pre - k != pre + k) {
            dfs(index + 1, num * 10 + pre - k, pre - k, list);
        }
    }
}
```