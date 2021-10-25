# [LeetCode_240_搜索二维矩阵II](https://leetcode-cn.com/problems/search-a-2d-matrix-ii/)
## 解法
### 思路
dfs+记忆化
### 代码
```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int r = matrix.length, c = matrix[0].length;
        return dfs(matrix, r , c, 0, 0, target, new boolean[r][c]);
    }
    
    private boolean dfs(int[][] matrix, int r, int c, int x, int y, int target, boolean[][] memo) {
        if (x < 0 || x >= r || y < 0 || y >= c || matrix[x][y] > target || memo[x][y]) {
            return false;
        }
        
        if (matrix[x][y] == target) {
            return true;
        }
        
        memo[x][y] = true;
        
        return dfs(matrix, r, c, x + 1, y, target, memo) ||
                dfs(matrix, r, c, x, y + 1, target, memo);
    }
}
```
## 解法二
### 思路
dfs+记忆化+减枝
### 代码
```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int r = matrix.length, c = matrix[0].length;
        return dfs(matrix, r , c, 0, 0, target, new boolean[r][c]);
    }

    private boolean dfs(int[][] matrix, int r, int c, int x, int y, int target, boolean[][] memo) {
        if (x < 0 || x >= r || y < 0 || y >= c || matrix[x][y] > target || memo[x][y]) {
            return false;
        }

        if (matrix[x][y] == target || matrix[x][c - 1] == target || matrix[r - 1][y] == target) {
            return true;
        }

        memo[x][y] = true;

        boolean result = false;

        if (matrix[r - 1][y] > target) {
            result = dfs(matrix, r, c, x + 1, y, target, memo);
        }

        if (result) {
            return true;
        }

        return dfs(matrix, r, c, x, y + 1, target, memo);
    }
}
```
## 解法三
### 思路
- 从矩阵的右上角开始搜索
- 在这个位置的左边，所有元素都比它小，所以如果此时该值比target小，那么这一行就不用考虑了，所以就下移
- 此外，如果当前元素比target大，那么他的左边就可能存在目标值，所以就左移
### 代码
```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length, col = matrix[0].length,
            r = 0, c = col - 1;
        
        while (r < row && c >= 0) {
            int num = matrix[r][c];
            if (num == target) {
                return true;
            } else if (num > target) {
                c--;
            } else {
                r++;
            }
        }
        
        return false;
    }
}

```