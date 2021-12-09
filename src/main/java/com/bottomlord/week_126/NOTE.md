# [LeetCode_1913_两个数对之间的最大乘积差](https://leetcode-cn.com/problems/maximum-product-difference-between-two-pairs/)
## 解法
### 思路
- 数组排序，获取前2个和后2个元素
- 计算乘积差返回
### 代码
```java
class Solution {
    public int maxProductDifference(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length - 1] * nums[nums.length - 2] - nums[0] * nums[1];
    }
}
```
## 解法二
### 思路
桶排序降低时间复杂度
### 代码
```java
class Solution {
    public int maxProductDifference(int[] nums) {
        int max = Arrays.stream(nums).max().getAsInt();

        int[] bucket = new int[max + 1];
        for (int num : nums) {
            bucket[num]++;
        }

        int count = 0;
        int a = 0, b = 0, c = 0, d = 0;
        for (int i = 0; i < bucket.length;) {
            if (bucket[i] > 0) {
                bucket[i]--;
                count++;

                if (count == 1) {
                    a = i;
                } else {
                    b = i;
                }
            } else {
                i++;
            }

            if (count == 2) {
                break;
            }
        }

        for (int i = bucket.length - 1; i >= 0;) {
            if (bucket[i] > 0) {
                bucket[i]--;
                count++;

                if (count == 3) {
                    c = i;
                } else if (count == 4) {
                    d = i;
                }
            } else {
                i--;
            }

            if (count == 4) {
                break;
            }
        }

        return c * d - a * b;
    }
}
```
# [LeetCode_1920_基于排列构建数组](https://leetcode-cn.com/problems/build-array-from-permutation/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public int[] buildArray(int[] nums) {
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = nums[nums[i]];
        }
        return ans;
    }
}
```
# [LeetCode_1925_统计平方和三元组的数目](https://leetcode-cn.com/problems/count-square-sum-triples/)
## 解法
### 思路
3层循环
### 代码
```java
class Solution {
    public int countTriples(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                for (int k = j; k <= n; k++) {
                    if (Math.pow(i, 2) + Math.pow(j, 2) == Math.pow(k, 2)) {
                        count+=2;
                    }
                }
            }
        }

        return count;
    }
}
```
## 解法
### 思路
2层循环
### 代码
```java
class Solution {
    public int countTriples(int n) {
        int count = 0;
        Set<Double> set = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            set.add(i * 1.0);
        }

        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                double a = Math.pow(i, 2), b = Math.pow(j, 2);
                if (set.contains(Math.sqrt(a + b))) {
                    count += 2;
                }
            }
        }

        return count;
    }
}
```
# [LeetCode_1034_边界着色](https://leetcode-cn.com/problems/coloring-a-border/)
## 解法
### 思路
- 记忆+回溯
- 注意不要对不同颜色的区块的坐标进行记忆，否则会导致减枝后出现判断错误
### 代码
```java
class Solution {
    private int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        int r = grid.length, c = grid[0].length;
        boolean[][] memo = new boolean[r][c];

        backTrack(grid, row, col, r, c, grid[row][col], color, memo);
        return grid;
    }

    private boolean backTrack(int[][] grid, int row, int col, int r, int c, int target, int color, boolean[][] memo) {
        if (row < 0 || row >= r || col < 0 || col >= c) {
            return false;
        }
        
        if (memo[row][col]) {
            return true;
        }
        
        if (grid[row][col] != target) {
            return false;
        }

        memo[row][col] = true;

        for (int[] dir : dirs) {
            boolean result = backTrack(grid, row + dir[0], col + dir[1],  r, c, target, color, memo);
            if (!result) {
                grid[row][col] = color;
            }
        }

        return true;
    }
}
```
# [LeetCode_794_有效的井字游戏](https://leetcode-cn.com/problems/valid-tic-tac-toe-state/)
## 解法
### 思路
回溯
### 代码
```java
class Solution {
    public boolean validTicTacToe(String[] board) {
        int[][] matrix = new int[3][3];
        LinkedList<int[]> xs = new LinkedList<>(), os = new LinkedList<>();

        for (int i = 0; i < board.length; i++) {
            char[] cs = board[i].toCharArray();
            for (int j = 0; j < cs.length; j++) {
                if (cs[j] == 'X') {
                    xs.add(new int[]{i, j});
                } else if (cs[j] == 'O') {
                    os.add(new int[]{i, j});
                }
            }
        }

        return backTrack(xs, os, true, matrix);
    }

    private boolean backTrack(LinkedList<int[]> xs, LinkedList<int[]> os, boolean isX, int[][] board) {
        if (xs.isEmpty() && os.isEmpty()) {
            return true;
        }

        if (isOver(board)) {
            return false;
        }

        LinkedList<int[]> list = isX ? xs : os;
        int count = list.size();
        for (int i = 0; i < count; i++) {
            int[] arr = list.poll();
            if (arr == null) {
                continue;
            }

            board[arr[0]][arr[1]] += isX ? 1 : -1;
            boolean result = backTrack(xs, os, !isX, board);
            if (result) {
                return true;
            }

            board[arr[0]][arr[1]] -= isX ? 1 : -1;
            list.addLast(arr);
        }

        return false;
    }

    private boolean isOver(int[][] board) {
        for (int i = 0; i < 3; i++) {
            int sum = Arrays.stream(board[i]).sum();
            if (sum == 3 || sum == -3) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            int sum = 0;
            for (int j = 0; j < 3; j++) {
                sum += board[j][i];
            }

            if (sum == 3 || sum == -3) {
                return true;
            }
        }

        int pie = board[0][0] + board[1][1] + board[2][2],
            na = board[0][2] + board[1][1] + board[2][0];
        return pie == 3 || pie == -3 || na == 3 || na == -3;
    }
}
```