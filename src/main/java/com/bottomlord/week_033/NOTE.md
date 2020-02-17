# Interview_12_矩阵的路径
## 题目
请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。
```
[["a","b","c","e"],
["s","f","c","s"],
["a","d","e","e"]]
```
但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。

示例 1：
```
输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
输出：true
```
示例 2：
```
输入：board = [["a","b"],["c","d"]], word = "abcd"
输出：false
```
提示：
```
1 <= board.length <= 200
1 <= board[i].length <= 200
```
## 解法
### 思路
回溯：
- 遍历二维字符数组，从每一个元素开始递归
- 递归：
    - 退出：
        - 越界或当前字符与目标字符不同，返回false
        - 当前字符为目标字符串的最后一个字符，且相等，返回true
    - 标记数组中当前字符为已遍历
    - 开始四个方向继续递归
    - 回复数组中当前字符内容
    - 返回四个方向相或后的结果
### 代码
```java
class Solution {
    public boolean exist(char[][] board, String word) {
        if (board.length == 0 || board[0].length == 0) {
            return false;
        }
        
        int row = board.length, col = board[0].length;
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (dfs(board, row, col, i, j, word,0)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private boolean dfs(char[][] board, int row, int col, int x, int y, String word, int index) {
        if (x < 0 || x >= row || y < 0 || y >= col || board[x][y] != word.charAt(index)) {
            return false;
        }
        
        if (index + 1 == word.length()) {
            return true;
        }
        
        char c = board[x][y];
        board[x][y] = '#';
        
        boolean ans =  dfs(board, row, col, x + 1, y, word, index + 1) ||
                        dfs(board, row, col, x - 1, y, word, index + 1) ||
                        dfs(board, row, col, x, y + 1, word, index + 1) ||
                        dfs(board, row, col, x, y - 1, word, index + 1);
        
        board[x][y] = c;
        return ans;
    }
}
```