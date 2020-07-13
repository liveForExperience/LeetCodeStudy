# LeetCode_130_被围绕的区域
## 题目
给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。

找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。

示例:
```
X X X X
X O O X
X X O X
X O X X
```
运行你的函数后，矩阵变为：
```
X X X X
X X X X
X X X X
X O X X
```
解释:
```
被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
```
## 解法
### 思路
dfs
- 从矩阵边缘中找到`O`，并从这些点开始dfs
- 将这些点修改成`#`
- 遍历完所有的边缘点后，遍历整个矩阵
    - 将`O`全部变为`X`
    - 将`#`全部变成`O`
### 代码
```java
class Solution {
    private int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public void solve(char[][] board) {
        int row = board.length;
        if (row == 0) {
            return;
        }

        int col = board[0].length;
        if (col == 0) {
            return;
        }

        for (int i = 0; i < col; i++) {
            if (board[0][i] == 'O') {
               dfs(board, row, col, 0, i);
            }

            if (board[row - 1][i] == 'O') {
                dfs(board, row, col, row - 1, i);
            }
        }

        for (int i = 0; i < row; i++) {
            if (board[i][0] == 'O') {
                dfs(board, row, col, i, 0);
            }

            if (board[i][col - 1] == 'O') {
                dfs(board, row, col, i, col - 1);
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void dfs(char[][] board, int row, int col, int r, int c) {
        if (r < 0 || r >= row || c < 0 || c >= col || board[r][c] == 'X' || board[r][c] == '#') {
            return;
        }

        board[r][c] = '#';

        for (int[] direction : directions) {
            dfs(board, row, col, r + direction[0], c + direction[1]);
        }
    }
}
```