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
# Interview_18_删除链表的节点
## 题目
给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。

返回删除后的链表的头节点。

注意：此题对比原题有改动

示例 1:
```
输入: head = [4,5,1,9], val = 5
输出: [4,1,9]
解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
```
示例 2:
```
输入: head = [4,5,1,9], val = 1
输出: [4,5,9]
解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
```
说明：
```
题目保证链表中节点的值互不相同
```
## 解法
### 思路
- 定义参数：
    - `fakeHead`：作为链表的假头，方便处理删除链表头部的特殊情况
    - `node`：作为遍历链表的指针，初始化指向`head`
    - `pre`：作为前置指针，方便进行删除操作，初始化指向`fakeHead`
- 过程：
    - 遍历链表
    - 如果当前节点值与目标值一致
        - 如果是头部节点被删除，`fakeHead.next = node.next`
        - 进行删除动作`pre.next = node.next`
        - 结束遍历
    - 移动指针`pre = node; node = node.next`
### 代码
```java
class Solution {
    public ListNode deleteNode(ListNode head, int val) {
        ListNode node = head, fakeHead = new ListNode(0), pre = fakeHead;
        fakeHead.next = node;
        while (node != null) {
            if (node.val == val) {
                if (pre == fakeHead) {
                    fakeHead.next = node.next;
                } else {
                    pre.next = node.next;
                }
                break;
            }
            
            pre = node;
            node = node.next;
        }
        
        return fakeHead.next;
    }
}
```
# Interview_19_正则表达式匹配
## 题目
请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。

示例 1:
```
输入:
s = "aa"
p = "a"
输出: false
解释: "a" 无法匹配 "aa" 整个字符串。
```
示例 2:
```
输入:
s = "aa"
p = "a*"
输出: true
解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
```
示例 3:
```
输入:
s = "ab"
p = ".*"
输出: true
解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
```
示例 4:
```
输入:
s = "aab"
p = "c*a*b"
输出: true
解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
```
示例 5:
```
输入:
s = "mississippi"
p = "mis*is*p*."
输出: false
s 可能为空，且只包含从 a-z 的小写字母。
p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
```
## 解法
### 思路
递归：每一层都针对当前两个字符串遍历到的元素，根据不同的情况进行判断并相应移动对应指针
- 递归参数：
    - `s`：字符串s
    - `si`：字符串s的指针
    - `p`：正则规则字符串p
    - `pi`：字符串p的指针
- 退出条件：
    - `si`和`pi`同时越界，说明之前的所有字符都能匹配ok，返回true
    - `pi`越界，但`si`没有越界，说明匹配规则无法完全匹配字符串s，返回false
    - 如果只是`si`越界，那还有一些特殊可能，所以不能判定为false
        - 当前p对应的是`x*`规则匹配，但s字符串已经匹配完，需要再移动一次
        - 当前这个`x*`规则不匹配，需要视为空字符串，跳过当前规则字符
- 递归过程中的不同情况：    
    - 遇到正则规则为`x*`：
        - 如果`s`的字符与规则的`x`匹配：
            - 匹配一次：两个指针同时移动
            - 匹配多次：`s`的指针移动
            - 不匹配：当作`x*`为空字符串，`p`的指针移动
        - 如果`s`的字符与规则的`x`不匹配：
            - `x*`当作空字符串，`p`的指针移动
    - 遇到正则规则为`.`：
        - 一定匹配，两个指针同时移动
    - 遇到正则规则为字符：
        - 如果匹配：两个指针同时移动
        - 如果不匹配：查看规则的后一个字符是否为`*`，如果是，就将当前`x*`规则视为空字符串，跳过
### 代码
```java
class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null || (p.length() == 0 && s.length() > 0)) {
            return false;
        }

        return backTrack(s, 0, p, 0);
    }

    private boolean backTrack(String s, int si, String p, int pi) {
        if (si >= s.length() && pi >= p.length()) {
            return true;
        }

        if (pi >= p.length()) {
            return false;
        }

        boolean ans = false;
        if (p.charAt(pi) == '*') {
            if (si < s.length() && (s.charAt(si) == p.charAt(pi - 1) || p.charAt(pi - 1) == '.')) {
                ans = backTrack(s, si + 1, p, pi) || backTrack(s, si + 1, p, pi + 1);
            }
            ans = ans || backTrack(s, si, p, pi + 1);
        } else {
            if (si < s.length() && (s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '.')) {
                ans = backTrack(s, si + 1, p, pi + 1);
            }
            
            if (pi < p.length() - 1 && p.charAt(pi + 1) == '*') {
                ans = ans || backTrack(s, si, p, pi + 2);
            }
        }
        
        return ans;
    }
}
```
## 优化代码
### 思路
减少需要递归的路径：
- 通过遍历两个字符串，通过通过正则字符的不同情况，确定不同的递归路径
- 过程：
    - 判断两个字符串的首个字符是否匹配，暂存结果
    - 如果当前剩余的正则字符串长度不小于2，且是`x*`模式：
        - 将当前这个模式视作空字符串，跳过，继续递归
        - 如果首个字符已经匹配了，那么就移动`s`字符串，继续匹配
    - 否则就返回首字符是否匹配的结果，以及同时移动两个字符串坐标的结果的相与结果
### 代码
```java
class Solution {
    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) {
            return s.isEmpty();
        }

        boolean firstMatch = !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');

        if (p.length() >= 2 && p.charAt(1) == '*') {
            return isMatch(s, p.substring(2)) || (firstMatch && isMatch(s.substring(1), p));
        }
        return firstMatch && isMatch(s.substring(1), p.substring(1));
    }
}
```
## 解法二
### 思路
动态规划：
- `dp[i][j]`：s的前i项和p的前j项是否匹配
- 状态转移方程：
    - 如果`s[i] == p[j] || p[j] == '.'`：`dp[i][j] = dp[i - 1][j - 1]`
    - 如果`p[j] == '*' && s[i] != p[j - 1]`：`dp[i][j] = dp[i][j - 2]`
    - 如果`p[j] == '*' && (s[i] == p[j - 1] || p[j - 1] == '.'`：`dp[i][j] = dp[i][j - 2] || dp[i - 1][j]`
    - 其他情况：false
- 初始化：
    - `dp[0][0] = true`：空字符串，空正则，是true
    - `dp[0][j] = dp[0][j - 2]`：空字符串，如果正则式`x*x*x*`，那么也初始化为true
- 返回结果：`dp[s.length][p.length]`
- 过程：
    - 确定边界情况：
        - 如果s为空，那么p的长度如果等于1，说明无法组成n个`x*`的模式，就是false
        - 如果p为空：字符串为空为true，否则为false
    - 嵌套循环，确定i和j
    - 通过不同状态更新dp
    - 循环结束后返回`dp[s.length][p.length]`
### 代码
```java
class Solution {
    public boolean isMatch(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        boolean[][] dp = new boolean[sLen + 1][pLen + 1];
        dp[0][0] = true;
        for (int j = 2; j <= p.length(); j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        for (int i = 1; i <= sLen; i++) {
            for (int j = 1; j <= pLen; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*' && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.')) {
                    dp[i][j] = dp[i][j - 2] || dp[i - 1][j];
                } else if (p.charAt(j - 1) == '*' && s.charAt(i - 1) != p.charAt(j - 1)) {
                    dp[i][j] = dp[i][j - 2];
                } else {
                    dp[i][j] = false;
                }
            }
        }

        return dp[sLen][pLen];
    }
}
```
# Interview_20_表示数值的字符串
## 题目
请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100"、"5e2"、"-123"、"3.1416"、"0123"及"-1E-16"都表示数值，但"12e"、"1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是。
## 解法
### 思路
- 如果字符串长度为0，就返回false
- 如果字符串首个字符为`+`或`-`，就移动指针一步
- 如果接下来的字符为数字或者`.`就不断移动指针，直到当前元素不再符合循环要求
    - 在循环中记录数字和`.`的数量
        - 如果数字为0，返回false
        - 如果`.`大于1，返回false
    - 如果指针越界，说明判断结束，符合要求，返回true
- 剩下的情况，如果当前字符是e：
    - 如果在e之后的字符是`+`或`-`，就移动一位指针
    - 如果移动好以后，指针越界，说明不符合要求，因为加减号之后必须要有数字，所以返回false
    - 再次开始循环判断是否是数字`.`或数字
    - 如果循环结束，指针越界，就返回true
- 再剩下的所有情况都是false
### 代码
```java
class Solution {
    public static boolean isNumber(String s) {
        s = s.trim();
        if (s.length() == 0) {
            return false;
        }

        int index = 0;
        if (s.charAt(index) == '+' || s.charAt(index) == '-') {
            index++;
        }

        int num = 0, dot = 0;
        while (index < s.length()) {
            if (s.charAt(index) >= '0' && s.charAt(index) <= '9') {
                index++;
                num++;
            } else if (s.charAt(index) == '.') {
                index++;
                dot++;
            } else {
                break;
            }
        }
        
        if (dot > 1 || num == 0) {
            return false;
        }
        
        if (index == s.length()) {
            return true;
        }
        
        if (s.charAt(index++) == 'e') {
            if (index == s.length()) {
                return false;
            }
            
            if (s.charAt(index) == '+' || s.charAt(index) == '-') {
                index++;
                if (index == s.length()) {
                    return false;
                }
            }

            while (index < s.length() && s.charAt(index) >= '0' && s.charAt(index) <= '9') {
                index++;
            }

            return index == s.length();
        }
        
        return false;
    }
}
```
# LeetCode_65_有效数字
## 题目
验证给定的字符串是否可以解释为十进制数字。

例如:
```
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
" -90e3   " => true
" 1e" => false
"e3" => false
" 6e-1" => true
" 99e2.5 " => false
"53.5e93" => true
" --6 " => false
"-+3" => false
"95a54e53" => false
```
说明: 我们有意将问题陈述地比较模糊。在实现代码之前，你应当事先思考所有可能的情况。这里给出一份可能存在于有效十进制数字中的字符列表：
```
数字 0-9
指数 - "e"
正/负号 - "+"/"-"
小数点 - "."
当然，在输入中，这些字符的上下文也很重要。
```
## 解法
### 思路
和面试题20一致
### 代码
```java
class Solution {
    public static boolean isNumber(String s) {
        s = s.trim();
        if (s.length() == 0) {
            return false;
        }

        int index = 0;
        if (s.charAt(index) == '+' || s.charAt(index) == '-') {
            index++;
        }

        int num = 0, dot = 0;
        while (index < s.length()) {
            if (s.charAt(index) >= '0' && s.charAt(index) <= '9') {
                index++;
                num++;
            } else if (s.charAt(index) == '.') {
                index++;
                dot++;
            } else {
                break;
            }
        }
        
        if (dot > 1 || num == 0) {
            return false;
        }
        
        if (index == s.length()) {
            return true;
        }
        
        if (s.charAt(index++) == 'e') {
            if (index == s.length()) {
                return false;
            }
            
            if (s.charAt(index) == '+' || s.charAt(index) == '-') {
                index++;
                if (index == s.length()) {
                    return false;
                }
            }

            while (index < s.length() && s.charAt(index) >= '0' && s.charAt(index) <= '9') {
                index++;
            }

            return index == s.length();
        }
        
        return false;
    }
}
```
## 解法二
### 思路
DFA：
- 5种元素：
    - 数字
    - 加减号
    - 指数符号
    - 点
    - 其他
- 构建一个有限状态机，并根据状态机生成图表
    - 行代表状态
    - 列代表元素
    - 图表中的值在当前状态下，遇到当前元素时的状态
- 状态：
    - 初始或空格状态：
    - 前一个为加减号的状态
    - 只有点的状态
    - 有数字有点的状态
    - 数字的状态
    - 指数符号的状态
    - 为指数值得状态
    - 指数上加减号的状态
- 过程中如果是-1，就直接返回false
### 代码
```java
class Solution {
    public int make(char c) {
        switch(c) {
            case ' ': return 0;
            case '+':
            case '-': return 1;
            case '.': return 3;
            case 'e': return 4;
            default:
                if(c >= '0' && c <= '9') {
                    return 2;
                }
        }
        return -1;
    }

    public boolean isNumber(String s) {
        int state = 0, finals = 0b101101000;
        int[][] transfer = new int[][]{{ 0, 1, 6, 2,-1},
                {-1,-1, 6, 2,-1},
                {-1,-1, 3,-1,-1},
                { 8,-1, 3,-1, 4},
                {-1, 7, 5,-1,-1},
                { 8,-1, 5,-1,-1},
                { 8,-1, 6, 3, 4},
                {-1,-1, 5,-1,-1},
                { 8,-1,-1,-1,-1}};
        char[] cs = s.toCharArray();
        for (char c : cs) {
            int id = make(c);
            if (id < 0) {
                return false;
            }
            state = transfer[state][id];
            if (state < 0) {
                return false;
            }
        }
        return (finals & (1 << state)) > 0;
    }
}
```
# LeetCode_10_正则表达式匹配
## 题目
给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
```
'.' 匹配任意单个字符
'*' 匹配零个或多个前面的那一个元素
所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
```
说明:
```
s 可能为空，且只包含从 a-z 的小写字母。
p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
```
示例 1:
```
输入:
s = "aa"
p = "a"
输出: false
解释: "a" 无法匹配 "aa" 整个字符串。
```
示例 2:
```
输入:
s = "aa"
p = "a*"
输出: true
解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
```
示例 3:
```
输入:
s = "ab"
p = ".*"
输出: true
解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
```
示例 4:
```
输入:
s = "aab"
p = "c*a*b"
输出: true
解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
```
示例 5:
```
输入:
s = "mississippi"
p = "mis*is*p*."
输出: false
```
## 解法
### 思路
递归：思路和面试题19第二解一致
### 代码
```java
class Solution {
    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) {
            return s.isEmpty();
        }

        boolean firstMatch = !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
        if (p.length() > 1 && p.charAt(1) == '*') {
            return isMatch(s, p.substring(2)) || (firstMatch && isMatch(s.substring(1), p));
        }

        return firstMatch && isMatch(s.substring(1), p.substring(1));
    }
}
```
## 解法二
### 思路
动态规划：思路与面试题19第三解一致
### 代码
```java
class Solution {
    public boolean isMatch(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        boolean[][] dp = new boolean[sLen + 1][pLen + 1];
        dp[0][0] = true;
        for (int j = 2; j <= p.length(); j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        for (int i = 1; i <= sLen; i++) {
            for (int j = 1; j <= pLen; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*' && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.')) {
                    dp[i][j] = dp[i][j - 2] || dp[i - 1][j];
                } else if (p.charAt(j - 1) == '*' && s.charAt(i - 1) != p.charAt(j - 1)) {
                    dp[i][j] = dp[i][j - 2];
                } else {
                    dp[i][j] = false;
                }
            }
        }

        return dp[sLen][pLen];
    }
}
```
# Interview_21_调整数组顺序使奇数位于偶数前面
## 题目
输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。

示例：
```
输入：nums = [1,2,3,4]
输出：[1,3,2,4] 
注：[3,1,2,4] 也是正确的答案之一。
```
提示：
```
1 <= nums.length <= 50000
1 <= nums[i] <= 10000
```
## 解法
### 思路
- 初始化一个与原数组一样大小的数组
- 定义新数组的头尾指针
- 遍历原数组
    - 如果是奇数就放在头指针的位置，同时头指针向后移动
    - 如果是偶数就放在尾指针的位置，同时尾指针向前移动
### 代码
```java
class Solution {
    public int[] exchange(int[] nums) {
        int[] ans = new int[nums.length];
        int head = 0, tail = ans.length - 1;
        for (int num : nums) {
            if ((num & 1) == 1) {
                ans[head++] = num;
            } else {
                ans[tail--] = num;
            }
        }
        
        return ans;
    }
}
```
## 解法二
### 思路
原地修改：
二分法：
- 定义头尾指针
- 从头开始遍历数组
- 如果元素为奇数，跳过
- 如果元素为偶数，和tail互换，tail向前移动，head不动
- 直到`head >= tail` 
### 代码
```java
class Solution {
    public int[] exchange(int[] nums) {
        int head = 0, tail = nums.length - 1;
        while (head < tail) {
            if ((nums[head] & 1) == 1) {
                head++;
                continue;
            }
            
            int tmp = nums[tail];
            nums[tail] = nums[head];
            nums[head] = tmp;
            
            tail--;
        }
        
        return nums;
    }
}
```
# Interview_22_链表中倒数第k个节点
## 题目
输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。这个链表的倒数第3个节点是值为4的节点。

示例：
```
给定一个链表: 1->2->3->4->5, 和 k = 2.

返回链表 4->5.
```
## 解法
### 思路
- 遍历链表计算长度
- 计算正数的位置
- 遍历找到节点
### 代码
```java
class Solution {
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode node = head;
        int count = 0;
        while (node != null) {
            node = node.next;
            count++;
        }
        
        node = head;
        for (int i = 0; i < count - k; i++) {
            node = node.next;
        }
        
        return node;
    }
}
```
# Interview_24_反转链表
## 题目
定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。

示例:
```
输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL
```
限制：
```
0 <= 节点个数 <= 5000
```
## 解法
### 思路
- 定义前置节点指针`pre`，用于反转
- 遍历链表
    - 暂存`node.next`
    - 将`node.next`指向`pre`
    - 将`pre`指向当前节点
    - 判断暂存的`next`是否为空
        - 也就是是否到了链表的最后位置，如果是就中断
        - 否则就将`node`指向暂存的`next`，继续遍历
- 最终返回`node`，也就是原链表的尾节点
### 代码
```java
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null, node = head;
        while (node != null) {
            ListNode next = node.next;
            node.next = pre;
            pre = node;
            if (next == null) {
                break;
            }
            node = next;
        }

        return node;
    }
}
```
# Interview_25_合并两个排序的链表
## 题目
输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。

示例1：
```
输入：1->2->4, 1->3->4
输出：1->1->2->3->4->4
```
限制：
```
0 <= 链表长度 <= 1000
```
## 解法
### 思路
- 遍历两个链表，暂存所有元素在list中
- 排序list
- 遍历list，生成链表
### 代码
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        List<Integer> list = new ArrayList<>();
        ListNode node = l1;
        while (node != null) {
            list.add(node.val);
            node = node.next;
        }
        
        node = l2;
        while (node != null) {
            list.add(node.val);
            node = node.next;
        }
        
        Collections.sort(list);
        
        ListNode fake = new ListNode(0);
        if (list.size() == 0) {
            return null;
        }
        node = new ListNode(list.get(0));
        fake.next = node;
        for (int i = 1; i < list.size(); i++) {
            node.next = new ListNode(list.get(i));
            node = node.next;
        }
        
        return fake.next;
    }
}
```
## 解法二
### 思路
- 定义两个指针：
    - `head`：作为链表的头指针，方便返回结果
    - `cur`：作为新链表的遍历指针，初始指向`head`
- 过程：
    - 开始循环，循环条件是，两个原链表的指针没有同时为空
    - 循环过程中：
        - 判断是否有链表已经遍历完，如果是，将`cur.next`指向不为空的那个链表
        - 否则判断两个指针的值的大小，如果小的，就将`cur.next`指针指向当前节点，然后移动指针`cur = cur.next`
- 循环结束后，返回`head.next`
### 代码
```java
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0), cur = head;
        
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                cur.next = l2;
                break;
            } 
            
            if (l2 == null) {
                cur.next = l1;
                break;
            }
            
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        
        return head.next;
    }
}
```
## 优化代码
### 思路
- 将解法二的循环条件改为全部都不为空
- 当循环退出后，再判断那个链表已经遍历完，从而接上就可以
- 其他逻辑和解法二类似
### 代码
```java
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0), cur = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        
        cur.next = l1 == null ? l2 : l1;
        return head.next;
    }
}
```
# Interview_26_树的子结构
## 题目
输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)

B是A的子结构， 即 A中有出现和B相同的结构和节点值。

例如:
```
给定的树 A:

     3
    / \
   4   5
  / \
 1   2
给定的树 B：

   4 
  /
 1
返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
```
示例 1：
```
输入：A = [1,2,3], B = [3,1]
输出：false
```
示例 2：
```
输入：A = [3,4,5,1,2], B = [4,1]
输出：true
```
限制：
```
0 <= 节点个数 <= 10000
```
## 解法
### 思路
嵌套dfs：
- 外层递归确定从A的哪个节点开始比较A和B
- 内层递归确定A的这部分子结构是否与B完全相等
- 外层：
    - 退出条件：A或B是null，返回false
    - 过程：
        - 进入内层递归，代表开始从当前节点开始判断是否为相同子结构
        - 递归左右子树，寻找别的节点作为内层的起点
        - 将三个返回的布尔值相或，只要有一个节点开始返回的是true，就说明有子结构
- 内层：
    - 退出条件：A为null，代表当前子结构遍历完了，因为上一层如果B不是null，不会递归进来，所以A如果为空，B却不是空的，所以不是完全一样的结构
    - 过程：
        - 如果当前两个节点的值相等：
            - 判断当前两个节点的左右子树是否分别也一样，于是继续递归下去
            - 返回的结果相与，返回这个结果
### 代码
```java
class Solution {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }

        return dfs(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    private boolean dfs(TreeNode a, TreeNode b) {
        if (a == null) {
            return false;
        }

        if (a.val == b.val) {
            boolean ans = true;
            if (b.left != null) {
                ans = dfs(a.left, b.left);
            }
            
            if (b.right != null) {
                ans = ans && dfs(a.right, b.right);
            }
            
            return ans;
        }
        
        return false;
    }
}
```