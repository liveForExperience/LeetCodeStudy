# [Contest_1_兼具大小写的最好英文单词](https://leetcode.cn/problems/greatest-english-letter-in-upper-and-lower-case/)
## 解法
### 思路
- 初始化长度为26的数组，用于记录字符出现的情况
- 遍历字符串，使用位来统计一个字母的大小写的出现情况
- 遍历数组，找到最后一个大小写都出现过的字母，并作为结果返回
### 代码
```java
class Solution {
    public String greatestLetter(String s) {
        int[] arr = new int[26];
        char[] cs = s.toCharArray();
        for (char c : cs) {
            if (Character.isUpperCase(c)) {
                arr[c - 'A'] |= 1;
            }

            if (Character.isLowerCase(c)) {
                arr[c - 'a'] |= (1 << 1);
            }
        }

        String ans = "";

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 3) {
                ans = "" + (char)(i + 'A');
            }
        }

        return ans;
    }
}
```
# [Contest_2_个位数为k的整数之和](https://leetcode.cn/problems/sum-of-numbers-with-units-digit-k/)
## 解法
### 思路
- 不需要去计算具体是用什么数字组成，直到得到当前数能通过多少次的累减，得到一个个位为k的数，那么就是处理的次数
- 累计减去k，直到得到的数个位数成为k为止，统计累减的次数，并作为结果返回
### 代码
```java
class Solution {
    public int minimumNumbers(int num, int k) {
        int count = 0;
        
        int x = k == 0 ? 10 : k;
        
        while (num > 0) {
            if (num % 10 == k) {
                return count + 1;
            }
            
            num -= x;
            if (num < 0) {
                return -1;
            }

            count++;
        }

        return count;
    }
}
```
# [Contest_3_小于等于K的最长二进制子序列](https://leetcode.cn/problems/longest-binary-subsequence-less-than-or-equal-to-k/)
## 解法
### 思路
- 分析可知，从最右侧开始统计1出现的次数，且该值小于目标值k，则这个个数就是1的最大长度
- 因为可以支持前导零，那么0所有的0都可以作为最长子序列的结果，所以统计0出现的个数即可
- 然后将1和0统计的个数累加即可
### 代码
```java
class Solution {
    public int longestSubsequence(String s, int k) {
        char[] cs = s.toCharArray();
        int count = 0;

        for (char c : cs) {
            if (c == '0') {
                count++;
            }
        }
        int t = 1, ans = 0;
        for (int i = cs.length - 1; i >= 0; i--) {
            char c = cs[i];
            if (c == '1' && ans + t <= k) {
                count++;
                ans += t;
            }

            t *= 2;

            if (t >= 1000000000) {
                break;
            }
        }
        
        return count;
    }
}
```
# [Contest_4_卖木头块](https://leetcode.cn/problems/selling-pieces-of-wood/)
## 解法
### 思路
动态规划：
- dp[i][j]：高为i，宽为j的木头可以卖出的最大价格
- 双层遍历，外层遍历1 - m，内层遍历1 - n
- 状态转移方程：
  - k <= i 或 k <= j，遍历0到k范围
  - dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[i][j - k]);
  - dp[i][j] = Math.max(dp[i][j], dp[k][j] + dp[i - k][j]);
- 返回dp[m][n]
### 代码
```java
class Solution {
    public long sellingWood(int m, int n, int[][] prices) {
        long[][] dp = new long[m + 1][n + 1];

        for (int[] price : prices) {
            int x = price[0], y = price[1];
            dp[x][y] = price[2];
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 0; k <= i; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[k][j] + dp[i - k][j]);
                }

                for (int k = 0; k <= j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[i][j - k]);
                }
            }
        }

        return dp[m][n];
    }
}
```