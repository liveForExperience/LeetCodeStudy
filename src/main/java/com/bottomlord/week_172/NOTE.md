# [LeetCode_749_隔离病毒](https://leetcode.cn/problems/contain-virus/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_940_不同子序列](https://leetcode.cn/problems/distinct-subsequences-ii/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_915_分割数组](https://leetcode.cn/problems/partition-array-into-disjoint-intervals/)
## 解法
### 思路
- 维护连个数组
    - 记录数组从左侧开始的，截止到坐标i的子数组中的最大值
    - 记录数组从右侧开始的，截止到坐标i的子数组中的最小值
- 同时遍历2个数组，求出从左侧开始，第一个数组的坐标的值小于等于第二个数组坐标的值的坐标
### 代码
```java
class Solution {
    public int partitionDisjoint(int[] nums) {
        int n = nums.length, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        int[] lefts = new int[n], rights = new int[n];
        for (int i = 0; i < n; i++) {
            max = Math.max(max, nums[i]);
            lefts[i] = max;
        }

        for (int i = n - 1; i >= 0; i--) {
            min = Math.min(min, nums[i]);
            rights[i] = min;
        }

        for (int i = 1; i < n; i++) {
            if (lefts[i - 1] <= rights[i]) {
                return i;
            }
        }

        return n;
    }
}
```
## 解法二
### 思路
在解法一的基础上，省略一次左侧的遍历
### 代码
```java
class Solution {
    public int partitionDisjoint(int[] nums) {
        int n = nums.length, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        int[] rights = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            min = Math.min(min, nums[i]);
            rights[i] = min;
        }
        
        for (int i = 0; i < n - 1; i++) {
            max = Math.max(max, nums[i]);
            if (max <= rights[i + 1]) {
                return i + 1;
            }
        }
        
        return n;
    }
}
```
# [LeetCode_907_子数组的最小值之和](https://leetcode.cn/problems/sum-of-subarray-minimums/)
## 解法
### 思路
单调栈
- 在一个数组中，一个最小值，它在其他数组中也可能是最小值，所以每个值如果作为候选的最小值，一定有一个辐射范围，将这个范围求出来，其实就能得到这个最小值能作用的子数组个数
- 使用单调栈，在遍历数组的过程中，维护一个元素作用范围的左边界和右边界
  - 从左往右遍历，维护左边界
  - 从右往左遍历，维护右边界
  - 在维护过程中，一个维护包含自己的边界，一个维护不包含自己的边界，用来方便计算子数组个数
- 最后遍历数组，通过计算左右边界来计算子数组个数，然后乘以当前元素值
### 代码
```java
class Solution {
    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        int[] lefts = new int[n], rights = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                lefts[i] = -1;
            } else {
                lefts[i] = stack.peek();
            }

            stack.push(i);
        }

        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                rights[i] = n;
            } else {
                rights[i] = stack.peek();
            }

            stack.push(i);
        }

        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans = (ans + (long)arr[i] * (i - lefts[i]) * (rights[i] - i)) % 1000000007;
        }

        return (int)ans;
    }
}
```
# [LeetCode_934_最短的桥](https://leetcode.cn/problems/shortest-bridge/)
## 解法
### 思路
dfs+bfs
- 使用dfs遍历其中一块陆地，将遍历到的陆地位置放入到队列中，并标记为-1
- 通过队列进行bfs，对bfs的次数进行计数，当找到第一块陆地的时候返回计数值
### 代码
```java
class Solution {
    private int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int shortestBridge(int[][] grid) {
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0 || grid[i][j] == -1) {
                    continue;
                }

                dfs(grid, i, j, queue);

                int ans = 0;
                while (!queue.isEmpty()) {
                    int count = queue.size();

                    while (count-- > 0) {
                        int[] arr = queue.poll();
                        if (arr == null) {
                            continue;
                        }

                        int x = arr[0], y = arr[1];
                        for (int[] direction : directions) {
                            int nx = x + direction[0], ny = y + direction[1];
                            if (nx < 0 || ny < 0 || nx >= grid.length || ny >= grid[0].length || grid[nx][ny] == -1) {
                                continue;
                            }

                            if (grid[nx][ny] == 1) {
                                return ans;
                            }

                            queue.offer(new int[]{nx, ny});
                            grid[nx][ny] = -1;
                        }
                    }

                    ans++;
                }
            }
        }

        return 0;
    }

    private void dfs(int[][] grid, int x, int y, Queue<int[]> queue) {
        int r = grid.length, c = grid[0].length;
        if (x < 0 || x >= r || y < 0 || y >= c || grid[x][y] == -1 || grid[x][y] == 0) {
            return;
        }

        queue.offer(new int[]{x, y});
        grid[x][y] = -1;

        for (int[] direction : directions) {
            dfs(grid, x + direction[0], y + direction[1], queue);
        }
    }
}
```