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
# Interview_14II_剪绳子II
## 题目
给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m] 。请问 k[0]*k[1]*...*k[m] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。

答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

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
2 <= n <= 1000
```
## 解法
### 思路
动态规划(与面试题14相同)：
- `dp[i]`：长度为i的绳子能组成的乘积最大的值
- 状态转移方程：`dp[i] = max(dp[i], max(dp[j], j) * max(dp[i - j], i - j))`
- 初始化：`dp[1] = 1`
- 返回`dp[n]`
- 但是因为int和long都会溢出，需要使用BigInteger，并取模
### 代码
```java
import java.math.BigInteger;
class Solution {
    public int cuttingRope(int n) {
        BigInteger[] dp = new BigInteger[n + 1];
        dp[1] = new BigInteger("1");
        for (int i = 2; i <= n; i++) {
            dp[i] = new BigInteger("0");
            for (int j = 1; j <= i /2; j++) {
                dp[i] = dp[i].max(dp[j].max(new BigInteger("" + j)).multiply(dp[i - j].max(new BigInteger(i - j + ""))));
            }
        }

        return dp[n].mod(new BigInteger("1000000007")).intValue();
    }
}
```
## 优化代码
### 思路
- 根据`n <= 3`的几种特例，进行返回
- 在dp[]方程中，将1，2，3位置的元素设置为不需要切的情况下的最大值，也分别是1,2,3，这3个值用于简化状态转移方程
- 新的状态转移方程为：`dp[i] = dp[j] * dp[i - j]`，因为上一步中的3个值，它们切后的值比原值要小，和其他数的性质不同，所以需要特殊对待，同时修改后才能用在当前的状态转移方程中，省去比较乘积与原值之间大小这一步
### 代码
```java
import java.math.BigInteger;
class Solution {
    public int cuttingRope(int n) {
        if (n < 2) {
            return 0;
        } else if (n == 2) {
            return 1;
        } else if (n == 3) {
            return 2;
        }

        BigInteger[] dp = new BigInteger[n + 1];
        dp[1] = new BigInteger("1");
        dp[2] = new BigInteger("2");
        dp[3] = new BigInteger("3");
        for (int i = 4; i <= n; i++) {
            dp[i] = new BigInteger("0");
            for (int j = 1; j <= i /2; j++) {
                dp[i] = dp[i].max(dp[j].multiply(dp[i - j]));
            }
        }

        return dp[n].mod(new BigInteger("1000000007")).intValue();
    }
}
```
## 解法二
### 思路
根据上面优化代码中总结出的结论：
- `n < 4`的绳子，它们切分后的乘积最大值都小于其本身，为`n - 1`
- `n == 4`的绳子，它们切分后的乘积最大值为`2 * 2 = 4`
- `n > 4`的绳子，尽量切分为多个长度为3的绳段，直到小于等于4为止
- 防止溢出，最终答案在计算过程中定义为long类型
### 代码
```java
class Solution {
    public int cuttingRope(int n) {
        if (n < 4) {
            return n - 1;
        }

        long ans = 1;
        while (n > 4) {
            n -= 3;
            ans = ans * 3 % 1000000007;
        }

        return (int)(ans * n % 1000000007);
    }
}
```
# Interview_15_二进制中1的个数
## 题目
请实现一个函数，输入一个整数，输出该数二进制表示中 1 的个数。例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2。

示例 1：
```
输入：00000000000000000000000000001011
输出：3
解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
```
示例 2：
```
输入：00000000000000000000000010000000
输出：1
解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
```
示例 3：
```
输入：11111111111111111111111111111101
输出：31
解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
```
## 解法
### 思路
通过位运算`x = x & (x - 1)`可以将二进制位上最低位的1置为0的特性，循环使用这个计算直到该值为0，并计数。最终将计数值返回即可得到1的个数。
### 代码
```java
public class Solution {
    public int hammingWeight(int n) {
        int ans = 0;
        while (n != 0) {
            n &= (n - 1);
            ans++;
        }
        return ans;
    }
}
```
# Interview_16_数值的整数次方
## 题目
实现函数double Power(double base, int exponent)，求base的exponent次方。不得使用库函数，同时不需要考虑大数问题。

示例 1:
```
输入: 2.00000, 10
输出: 1024.00000
```
示例 2:
```
输入: 2.10000, 3
输出: 9.26100
```
示例 3:
```
输入: 2.00000, -2
输出: 0.25000
解释: 2-2 = 1/22 = 1/4 = 0.25
```
说明:
```
-100.0 < x < 100.0
n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
```
## 失败解法
### 失败原因
超时
### 思路
- 循环累乘n的绝对值次x
- 如果n是负数，累成的值为`x = 1 / x`
### 代码
```java
class Solution {
    public double myPow(double x, int n) {
        boolean f = n < 0;
        int num = Math.abs(n);
        double ans = 1.0;
        
        for (int i = 0; i < num; i++) {
            ans *= f ? (1 / x) : x;  
        }
        
        return ans;
    }
}
```
## 解法二
### 思路
递归二分法：
- 如果n为奇数，`ans = f(x, n / 2), return ans * ans`
- 如果n为偶数：`ans = f(x, n / 2), return x * ans * ans`
- 如果是负数，就如上计算，然后取倒数
- 因为将原本两次的递归减少为了1次，所以整个整个时间复杂度是O(log2N)
### 代码
```java
class Solution {
    public double myPow(double x, int n) {
        double ans = recurse(x, n);
        return n > 0 ? ans : 1 / ans;
    }

    private double recurse(double x, int n) {
        if (n == 0 || x == 1) {
            return 1;
        }

        double ans = recurse(x, n / 2);
        return (n & 1) == 1 ? x * ans * ans: ans * ans;
    }
}
```
## 解法三
### 思路
- 指数的二进制上每一个为1的位，换算成该位代表的值，并累加，就是指数值
- 指数的相加，等于底数与对应指数的累乘
- 所以可以通过右移求当前位是否为1，来判断是否和底数相乘
- 指数则通过累乘来代表
- 这样只要移动常数次，就能求得最终的解
### 代码
```java
class Solution {
    public double myPow(double x, int n) {     
        long exponent = n;
        
        if (x == 1 || exponent == 0) {
            return 1;
        }

        if (exponent < 0) {
            exponent = Math.abs(exponent);
            x = 1 / x;
        }

        double ans = 1;
        while (exponent > 0) {
            if ((exponent & 1) == 1) {
                ans *= x;
            }

            x *= x;
            exponent >>>= 1;
        }

        return ans;
    }
}
```
# Interview_17_打印1到最大的n位数
## 题目
输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。

示例 1:
```
输入: n = 1
输出: [1,2,3,4,5,6,7,8,9]
```
说明：
```
用返回一个整数列表来代替打印
n 为正整数
```
## 解法
### 思路
- 遍历生成list
- 通过n生成遍历的最大值
### 代码
```java
class Solution {
    public int[] printNumbers(int n) {
        int max = 1;
        while (n-- > 0) {
            max *= 10;
        }
        
        int[] ans = new int[max - 1];
        for (int i = 1; i < max; i++) {
            ans[i - 1] = i;
        }
        return ans;
    }
}
```