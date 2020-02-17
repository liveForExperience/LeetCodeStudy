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
# Interview_13_机器人的运动范围
## 题目
地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？

示例 1：
```
输入：m = 2, n = 3, k = 1
输出：3
```
示例 2：
```
输入：m = 3, n = 1, k = 0
输出：1
```
提示：
```
1 <= n,m <= 100
0 <= k <= 20
```
## 解法
### 思路
递归：
- 判断当前元素是否符合要求
    - 是否越界
    - 是否已遍历
    - 是否数位超过了`k`值
- 如果不符合就退出，返回0
- 如果符合就返回1和其他4个方向递归后返回值的和
### 代码
```java
class Solution {
    public int movingCount(int m, int n, int k) {
        return dfs(new int[m][n], m,  n, 0, 0, k);
    }

    private int dfs(int[][] board, int row, int col, int x, int y, int k) {
        if (!isValid(board, row, col, x, y, k)) {
            return 0;
        }

        board[x][y] = 1;
        return 1 + dfs(board, row, col, x + 1, y, k) +
                dfs(board, row, col, x - 1, y, k) +
                dfs(board, row, col, x, y + 1, k) +
                dfs(board, row, col, x, y - 1, k);
    }

    private boolean isValid(int[][] board, int row, int col, int x, int y, int k) {
        if (x < 0 || x >= row || y < 0 || y >= col || board[x][y] == 1) {
            return false;
        }

        int a = 0;
        while (x > 0) {
            a += x % 10;
            x /= 10;
        }

        int b = 0;
        while (y > 0) {
            b += y % 10;
            y /= 10;
        }

        return a + b <= k;
    }
}
```
# Interview_14I_剪绳子
## 题目
给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m] 。请问 k[0]*k[1]*...*k[m] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。

示例 1：
```
输入: 2
输出: 1
解释: 2 = 1 + 1, 1 × 1 = 1
```
示例 2:
```
输入: 10
输出: 36
解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
```
提示：
```
2 <= n <= 58
```
## 解法
### 思路
动态规划：
- `dp[i]`：i个绳子段的长度相乘得到的最大值
- 状态转移方程：`dp[i] = max(dp[i], max(j, dp[j]) * max(dp[i - j], i - j))`
    - j是小于i的一个绳子长度可能
    - 比较之前计算过的`i - j`长度的最大值和`i - j`的长度之间的最大值，再与j与`dp[j]`之间的最大值相乘，从而获得最大值
- 初始值：`dp[1] = 1`
- 返回`dp[n]`，n为绳子的总长度
- 过程：
    - 嵌套循环，外层为较大的绳子长度，内层为不比外层长度一半长度大所有长度可能
    - 因为因为内层状态转移的一部分比较涉及的两个因数是通过`j`和`i - j`确定的，所以`j <= i / 2`就能囊括所有可能了
### 代码
```java
class Solution {
    public int cuttingRope(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i / 2; j++) {
                dp[i] = Math.max(dp[i], Math.max(j, dp[j]) * Math.max(i - j, dp[i - j]));
            }
        }
        
        return dp[n]; 
    }
}
```