# LeetCode_279_完全平方数
## 题目
给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。

示例 1:
```
输入: n = 12
输出: 3 
解释: 12 = 4 + 4 + 4.
```
示例 2:
```
输入: n = 13
输出: 2
解释: 13 = 4 + 9.
```
## 解法
### 思路
动态规划：
- `dp[i]`：最少的组成`i`的完全平方数的个数
- base case：`dp[1] = 1`
- 状态转移方程：`dp[i] = min(dp[i], dp[i - j * j] + 1)`
    - 每个数的最坏情况是都由1来组成
    - `j * j`代表完全平方数
### 代码
```java
class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = i;
            for (int j = 1; i - j * j >= 0; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }
}
```
# LeetCode_1023_驼峰式匹配
## 题目
如果我们可以将小写字母插入模式串 pattern 得到待查询项 query，那么待查询项与给定模式串匹配。（我们可以在任何位置插入每个字符，也可以插入 0 个字符。）

给定待查询列表 queries，和模式串 pattern，返回由布尔值组成的答案列表 answer。只有在待查项 queries[i] 与模式串 pattern 匹配时， answer[i] 才为 true，否则为 false。

示例 1：
```
输入：queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FB"
输出：[true,false,true,true,false]
示例：
"FooBar" 可以这样生成："F" + "oo" + "B" + "ar"。
"FootBall" 可以这样生成："F" + "oot" + "B" + "all".
"FrameBuffer" 可以这样生成："F" + "rame" + "B" + "uffer".
```
示例 2：
```
输入：queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBa"
输出：[true,false,true,false,false]
解释：
"FooBar" 可以这样生成："Fo" + "o" + "Ba" + "r".
"FootBall" 可以这样生成："Fo" + "ot" + "Ba" + "ll".
```
示例 3：
```
输出：queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBaT"
输入：[false,true,false,false,false]
解释： 
"FooBarTest" 可以这样生成："Fo" + "o" + "Ba" + "r" + "T" + "est".
```
提示：
```
1 <= queries.length <= 100
1 <= queries[i].length <= 100
1 <= pattern.length <= 100
所有字符串都仅由大写和小写英文字母组成。
```
## 解法
### 思路
- 遍历`queries`数组
- 判断当前字符串是否匹配，并记录到`list`中：
    - 遍历当前字符串的字符数组
    - 暂存遍历`pattern`的下标`i`
    - 如果`i`小于`pattern`的长度
        - 如果字符与`pattern`的字符相等，`i++`
        - 如果字符与`pattern`的字符不等，判断是否是小写，如果不是就返回false
    - 如果`i`大于等于`pattern`的长度，说明剩下的字符应该全是小写，如果不是就返回false
    - 当前字符遍历结束，如果`i < pattern.length`，返回false，否则返回true
- 遍历结束，返回`list`
### 代码
```java
class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> ans = new ArrayList<>(queries.length);
        for (String query : queries) {
            ans.add(match(query, pattern));
        }
        return ans;
    }
    
    private boolean match(String query, String pattern) {
        int index = 0;
        for (char c : query.toCharArray()) {
            if (index < pattern.length()) {
                if (c == pattern.charAt(index)) {
                    index++;
                } else if (c < 'a' || c > 'z') {
                    return false;
                }
            } else if (c < 'a' || c > 'z') {
                return false;
            }
        }

        return index >= pattern.length();
    }
}
```
# LeetCode_672_灯泡开关II
## 题目
现有一个房间，墙上挂有 n 只已经打开的灯泡和 4 个按钮。在进行了 m 次未知操作后，你需要返回这 n 只灯泡可能有多少种不同的状态。

假设这 n 只灯泡被编号为 [1, 2, 3 ..., n]，这 4 个按钮的功能如下：
```
将所有灯泡的状态反转（即开变为关，关变为开）
将编号为偶数的灯泡的状态反转
将编号为奇数的灯泡的状态反转
将编号为 3k+1 的灯泡的状态反转（k = 0, 1, 2, ...)
```
示例 1:
```
输入: n = 1, m = 1.
输出: 2
说明: 状态为: [开], [关]
```
示例 2:
```
输入: n = 2, m = 1.
输出: 3
说明: 状态为: [开, 关], [关, 开], [关, 关]
```
示例 3:
```
输入: n = 3, m = 1.
输出: 4
说明: 状态为: [关, 开, 关], [开, 关, 开], [关, 关, 关], [关, 开, 开].
注意： n 和 m 都属于 [0, 1000].
```
## 解法
### 思路
- 所有可能性的搜索空间很大：
    - 灯的状态：2 ^ n
    - 操作的可能：4 ^ m
- 简化搜索空间：
    - 连续两个重复的操作与不操作一致，所以每个操作只考虑`0`或`1`
    - 因为执行`a,b`与执行`b,a`的效果相同，所以操作可以只按升序排列
    - 根据操作d可得，`n <= 3`可以覆盖所有可能性
- 因搜索空间有限，可以穷举：
    - `n == 0`或`m == 0`：1
    - `n == 1`：两种情况，开或关
    - `n == 2 && m == 1`：3种情况，开关，关开，关关
    - `n == 2 && m == 2`：4种情况，关关，开开，开关，关开
    - `n >= 3 && m == 1`：4种情况，关开开，关关关，开关开，关开关
    - `n >= 3 && m == 2`：7种情况，关关关，开开开，开关开，关开关，关关开，开开关，开关关
    - `n >= 3 && m > 2`：8种可能，关关关，开开开，开关开，关开关，关关开，开开关，开关关，关开开
### 代码
```java
class Solution {
    public int flipLights(int n, int m) {
        if (n == 0 || m == 0) {
            return 1;
        }

        if (n == 1) {
            return 2;
        }

        if (n == 2) {
            if (m == 1) {
                return 3;
            }

            if (m == 2) {
                return 4;
            }

            if (m > 2) {
                return 4;
            }
        }

        if (n >= 3) {
            if (m == 1) {
                return 4;
            }

            if (m == 2) {
                return 7;
            }

            if (m > 2) {
                return 8;
            }
        }
        
        return 8;
    }
} 
```
# LeetCode_423_从英文中重建数字
## 题目
给定一个非空字符串，其中包含字母顺序打乱的英文单词表示的数字0-9。按升序输出原始的数字。

注意:
```
输入只包含小写英文字母。
输入保证合法并可以转换为原始的数字，这意味着像 "abc" 或 "zerone" 的输入是不允许的。
输入字符串的长度小于 50,000。
```
示例 1:
```
输入: "owoztneoer"

输出: "012" (zeroonetwo)
```
示例 2:
```
输入: "fviefuro"

输出: "45" (fourfive)
```
## 解法
### 思路
- 分析每个英文字母发现，所有偶数都有其他数字没有的独有字母
    - 0：z
    - 2：w
    - 4：u
    - 6：x
    - 8：g
- 剩下的奇数中有3个数字与偶数的数字的某个字母有交集
    - 3 & 8：h
    - 5 & 4：f
    - 7 & 6：s
- 最后剩下的1和9，各自的n和i在之前的数字中有出现
    - 1 & 7 & 9：n
    - 9 & 5 & 6 & 8：i
- 根据如上的分析，过程如下：
    - 遍历字符数组，计算所有字母的出现个数，放入数组
    - 依次计算0，2，4，6，8的独有字母个数，算出各自代表数字的个数
    - 依次计算3，5，7中与偶数出现交集的字母个数并减去对应偶数的个数，算出这三个值的个数
    - 依次计算1，9的n和i，将对应重复出现的数字的个数减去，算出这两个数的值
    - 嵌套循环：
        - 外层遍历10个数
        - 内层遍历每个数对应的个数，append数字
### 代码
```java
class Solution {
    public String originalDigits(String s) {
        char[] cs = new char[26 + (int)'a'];

        for (char c : s.toCharArray()) {
            cs[c]++;
        }

        int[] nums = new int[10];
        nums[0] = cs['z'];
        nums[2] = cs['w'];
        nums[4] = cs['u'];
        nums[6] = cs['x'];
        nums[8] = cs['g'];
        nums[3] = cs['h'] - nums[8];
        nums[5] = cs['f'] - nums[4];
        nums[7] = cs['s'] - nums[6];
        nums[9] = cs['i'] - nums[5] - nums[6] - nums[8];
        nums[1] = cs['n'] - nums[7] - 2 * nums[9];
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < nums[i]; j++) {
                sb.append(i);
            }
        }

        return sb.toString();
    }
}
```
# LeetCode_1017_负二进制转换
## 题目
给出数字 N，返回由若干 "0" 和 "1"组成的字符串，该字符串为 N 的负二进制（base -2）表示。

除非字符串就是 "0"，否则返回的字符串中不能含有前导零。

示例 1：
```
输入：2
输出："110"
解释：(-2) ^ 2 + (-2) ^ 1 = 2
```
示例 2：
```
输入：3
输出："111"
解释：(-2) ^ 2 + (-2) ^ 1 + (-2) ^ 0 = 3
```
示例 3：
```
输入：4
输出："100"
解释：(-2) ^ 2 = 4
```
提示：
```
0 <= N <= 10^9
```
## 解法
### 思路
与正数进制的转换类似
- 循环除2，将余数从低位相连
- 如果是奇数，负数进制要向下取整，否则当`N == -1`时，结果`N / -2`直接就为0了，但其实应该是第二位为1，所以需要向下取整为`-2`
- **具体原因没有理解**
### 代码
```java
class Solution {
    public String baseNeg2(int N) {
        if (N == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        while (N != 0) {
            if (N % 2 == 0) {
                N /= -2;
                sb.insert(0, 0);
            } else {
                N = (N - 1) / -2;
                sb.insert(0, 1);
            }
        }

        return sb.toString();
    }
}
```
# LeetCode_309_最佳股票买卖时机含冷冻期
## 题目
设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
```
你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
```
示例:
```
输入: [1,2,3,0,2]
输出: 3 
解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
```
## 解法
### 思路
动态规划：
- dp[i][j]：代表第i天j状态时的最大利润
    - i：代表天数
    - j：代表当天的状态，1为持有股票，0为没有股票
- base case：初始会有两个状态，持有和没有
    - `dp[0][0] = 0`：第一天不买，利润就是0
    - `dp[0][1] = -price[0]`：第一天买，利润为负的当天价格
- 状态转移方程：
    - `dp[i][0] = max(dp[i - 1][0], dp[i - 1][1] + price[i])`：当天没有股票时的利润最大值，就是`前一天也没有股票`和`前一天持有今天卖掉`的两种情况中的最大值
    - `dp[i][1]` = max(dp[i - 1][1], dp[i - 2][0] - price[i])：当天有股票时的利润最大值，就是`前一天也持有股票，今天没卖`和`两天前没有股票(冷却期1天)，今天买了`的两种情况中的最大值
- 求的最后结果：`dp[n - 1][0]`
- 注意：
    - 因为有冷冻期，所以第二天有股票的情况也需要特殊处理为：`dp[1][1] = max(dp[0][1], dp[0][0] - prices[i])`
    - 注意数组长度为0的情况
### 代码
```java
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0) {
            return 0;
        }
        
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }

            if (i == 1) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
                continue;
            }
            
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);
        }

        return dp[n - 1][0];
    }
}
```
## 代码优化
### 思路
因为每一天的状态只依赖于前一天持有，前一天没有持有，和两天前没有持有这3个状态，所以可以直接使用局部变量来暂存，降低了空间复杂度
### 代码
```java
class Solution {
    public int maxProfit(int[] prices) {
        int hold = Integer.MIN_VALUE, rest = 0, pre = 0;
        for (int price : prices) {
            int tmp = rest;
            rest = Math.max(rest, hold + price);
            hold = Math.max(hold, pre - price);
            pre = tmp;
        }
        
        return rest;
    }
}
```