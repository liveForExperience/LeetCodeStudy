# [LeetCode_7_整数反转](https://leetcode-cn.com/problems/reverse-integer/)
## 解法
### 思路
- 因为题目不允许用long，所以在判断int是否溢出的时候，就需要提前做判断，也就是在做进位的时候就判断是否已经超过的最大值/10或者小于了最小值*10
- 计算过程就是原数不断/10，缩小的过程中驱动获取每一个低位的数字，将这个数字放在反转后的数字的头部，过程就是rev * 10 + digit
- 负数取模还是负数，所以不用处理负数的情况
### 代码
```java
class Solution {
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            if (rev < Integer.MIN_VALUE / 10 || rev > Integer.MAX_VALUE / 10) {
                return 0;
            }

            int digit = x % 10;
            x /= 10;
            rev = rev * 10 + digit;
        }

        return rev;
    }
}
```
# [LeetCode_562_矩阵中最长的连续1线段](https://leetcode-cn.com/problems/longest-line-of-consecutive-one-in-matrix/)
## 解法
### 思路
dfs
### 代码
```java
class Solution {
    public int longestLine(int[][] mat) {
        int ans = 0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 1) {
                    ans = Math.max(ans, dfs(mat, i, j, 1, 0));
                    ans = Math.max(ans, dfs(mat, i, j, 0, 1));
                    ans = Math.max(ans, dfs(mat, i, j, 1, 1));
                    ans = Math.max(ans, dfs(mat, i, j, 1, -1));
                }
            }
        }
        return ans;
    }

    private int dfs(int[][] mat, int r, int c, int dx, int dy) {
        if (outOfBound(mat, r, c)) {
            return 0;
        }

        return dfs(mat, r + dx, c + dy, dx, dy) + 1;
    }

    private boolean outOfBound(int[][] mat, int r, int c) {
        int row = mat.length, col = mat[0].length;
        return r < 0 || r >= row || c < 0 || c >= col || mat[r][c] == 0;
    }
}
```