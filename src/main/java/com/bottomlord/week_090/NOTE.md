# [LeetCode_190_颠倒二进制位](https://leetcode-cn.com/problems/reverse-bits/)
## 解法
### 思路
- 使用`10000000000000000000000000000000`和`1`两个掩码来确定颠倒二进制
- 使用`1`，不断左移来来确定当前位上是否是1，用或来判断
- 如果判断是1，那就通过`10000000000000000000000000000000`同步无符号右移的过程中，通过或来填充1
### 代码
```java
public class Solution {
    public int reverseBits(int n) {
        int mask = 1, time = 31;
        while (time-- > 0) {
            mask <<= 1;
        }

        int ans = 0, count = 1;
        time = 32;
        while (time-- > 0) {
            if ((count | n) == n) {
                ans |= mask;
            }
            mask >>>= 1;
            count <<= 1;
        }
        return ans;
    }
}
```
# [LeetCode_502_IPO](https://leetcode-cn.com/problems/ipo/)
## 失败解法
### 原因
超时，时间复杂度过高，树高k层，第t(t <= n)层的搜索都要检查t-1个节点
### 思路
记忆化+回溯
### 代码
```java
class Solution {
    private int ans;

    public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        this.ans = W;
        k = Math.min(k, Profits.length);
        backTrack(k, W, Profits, Capital, new boolean[Profits.length]);
        return ans;
    }

    private void backTrack(int time, int w, int[] profits, int[] capital, boolean[] memo) {
        ans = Math.max(ans, w);
        if (time == 0) {
            return;
        }

        for (int i = 0; i < profits.length; i++) {
            if (memo[i] || w < capital[i]) {
                continue;
            }

            memo[i] = true;
            backTrack(time -  1, w + profits[i], profits, capital, memo);
            memo[i] = false;
        }
    }
}
```
## 解法
### 思路
贪心：
- 要的是k个项目的最大收益，所以每次判断是否可以做项目的情况下，取最大的收益项目即可
- 每次使用完一个项目以后，就将其的花费值设置为无限大，使得下次不能重复使用
- 每次都判断可以使用的项目中的最大值，然后累加，直到k和项目数两者之间的最小值的次数被遍历完
### 代码
```java
class Solution {
    public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        boolean speedUp = true;
        int sum = W;
        for (int i = 0; i < Capital.length; i++) {
            if (W < Capital[i]) {
                speedUp = false;
                break;
            }
        }

        if (speedUp) {
            PriorityQueue<Integer> queue = new PriorityQueue<>();
            for (int i = 1; i <= Capital.length; i++) {
                queue.offer(Profits[i - 1]);
                
                if (i > k) {
                    queue.poll();
                }
            }
            
            return queue.stream().mapToInt(x -> x).sum() + W;
        }

        for (int i = 0; i < Math.min(k, Profits.length); i++) {
            int index = -1;

            for (int j = 0; j < Profits.length; j++) {
                if (W >= Capital[j]) {
                    if (index == -1 || Profits[index] < Profits[j]) {
                        index = j;
                    }
                }
            }
            
            if (index == -1) {
                break;
            }

            W += Profits[index];
            Capital[index] = Integer.MAX_VALUE;
        }
        
        return W;
    }
}
```
# [LeetCode_74_搜索二维矩阵](https://leetcode-cn.com/problems/search-a-2d-matrix/)
## 解法
### 思路
记忆化+深度优先搜索
### 代码 
```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length, col = matrix[0].length;
        if (target < matrix[0][0] || target > matrix[row - 1][col - 1]) {
            return false;
        }

        return dfs(matrix, 0, 0, row, col, target, new boolean[row][col]);
    }

    private boolean dfs(int[][] matrix, int r, int c, int row, int col, int target, boolean[][] memo) {
        if (r < 0 || r >= row || c < 0 || c >= col || memo[r][c]) {
            return false;
        }

        memo[r][c] = true;
        int pivot = matrix[r][c];
        if (pivot == target) {
            return true;
        }

        if (pivot > target) {
            return dfs(matrix, r, c - 1, row, col, target, memo) || dfs(matrix, r - 1, c, row, col, target, memo);
        } else {
            return dfs(matrix, r, c + 1, row, col, target, memo) || dfs(matrix, r + 1, c, row, col, target, memo);
        }
    }
}
```
# [LeetCode_505_迷宫II](https://leetcode-cn.com/problems/the-maze-ii/)
## 解法
### 思路
记忆化+bfs
### 代码
```java
class Solution {
    private int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int row = maze.length, col = maze[0].length;

        Queue<Node> queue = new ArrayDeque<>();
        int[][] memo = new int[row][col];
        for (int[] arr : memo) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }
        memo[start[0]][start[1]] = 0;
        queue.offer(new Node(start[0], start[1], 0));
        int ans = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int x = cur.x, y = cur.y;

            for (int[] dir : dirs) {
                int nextX = x + dir[0], nextY = y + dir[1], count = 0;
                while (nextX >= 0 && nextX < row && nextY >= 0 && nextY < col && maze[nextX][nextY] != 1) {
                    nextX += dir[0];
                    nextY += dir[1];
                    count++;
                }

                if (count == 0) {
                    continue;
                }

                nextX -= dir[0];
                nextY -= dir[1];
                count += cur.count;
                
                if (count <= memo[nextX][nextY] && count < ans) {
                    memo[nextX][nextY] = count;
                    if (nextX == destination[0] && nextY == destination[1]) {
                        ans = count;
                        break;
                    } else {
                        queue.offer(new Node(nextX, nextY, count));
                    }
                }
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private class Node {
        private int count;
        private int x;
        private int y;

        public Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}
```