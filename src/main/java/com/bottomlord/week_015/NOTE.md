# LeetCode_647_回文子串
## 题目
给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。

具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。

示例 1:
```
输入: "abc"
输出: 3
解释: 三个回文子串: "a", "b", "c".
```
示例 2:
```
输入: "aaa"
输出: 6
说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa".
```
注意:
```
输入的字符串长度不会超过1000。
```
## 解法
### 思路
中心扩散
- 遍历字符串，以当前元素为中心扩散
- 扩散过程中判断当前范围的字符串是否是回文串，是的话就累加，否则终止
    - 字符串总长度为奇数
    - 字符串总长度为偶数
### 代码
```java
class Solution {
    private int sum;

    public int countSubstrings(String s) {
        for (int i = 0; i < s.length(); i++) {
            spread(s, i, i);
            spread(s, i, i + 1);
        }
        return sum;
    }

    private void spread(String s, int start, int end) {
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            sum++;
            start--;
            end++;
        }
    }
}
```
## 解法二
### 思路
动态规划：
- dp[i][j]存储字符串中起始i结尾j范围的子串是否是回文
- 状态转移方程：dp[i][j] = s[i] == s[j] && (dp[i + 1][j - 1] || j - i <= 2)(如果子字符串长度不超过2，那两个字符相等的情况下就一定是回文串)
- base case：`i == j`的情况下都是true
### 代码
```java
class Solution {
    public int countSubstrings(String s) {
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        int count = 0;
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1]);
                if(dp[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }
}
```
# LeetCode_318_最大单词长度乘积
## 题目
给定一个字符串数组 words，找到 length(word[i]) * length(word[j]) 的最大值，并且这两个单词不含有公共字母。你可以认为每个单词只包含小写字母。如果不存在这样的两个单词，返回 0。

示例 1:
```
输入: ["abcw","baz","foo","bar","xtfn","abcdef"]
输出: 16 
解释: 这两个单词为 "abcw", "xtfn"。
```
示例 2:
```
输入: ["a","ab","abc","d","cd","bcd","abcd"]
输出: 4 
解释: 这两个单词为 "ab", "cd"。
```
示例 3:
```
输入: ["a","aa","aaa","aaaa"]
输出: 0 
解释: 不存在这样的两个单词。
```
## 解法
### 思路
- 遍历字符串，将字母对应到位上
- 嵌套遍历字符串，对比两个字符串对应的位值相与是否是0，如果是就计算乘积
- 把计算出的乘积和已有的最大值比较，取最大
- 遍历结束后返回
### 代码
```java
class Solution {
    public int maxProduct(String[] words) {
        int len = words.length;
        int[] arr = new int[len];
        
        for (int i = 0; i < len; i++) {
            String word = words[i];
            for (int j = 0; j < word.length(); j++) {
                arr[i] |= 1 << (word.charAt(j) - 'a');
            }
        }
        
        int max = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if ((arr[i] & arr[j]) == 0) {
                    max = Math.max(words[i].length() * words[j].length(), max);
                }
            }
        }
        return max;
    }
}
```
# LeetCode_313_超级丑数
## 题目
编写一段程序来查找第 n 个超级丑数。

超级丑数是指其所有质因数都是长度为 k 的质数列表 primes 中的正整数。

示例:
```
输入: n = 12, primes = [2,7,13,19]
输出: 32 
解释: 给定长度为 4 的质数列表 primes = [2,7,13,19]，前 12 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,28,32] 。
```
说明:
```
1 是任何给定 primes 的超级丑数。
 给定 primes 中的数字以升序排列。
0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000 。
第 n 个超级丑数确保在 32 位有符整数范围内。
```
## 解法
### 思路
动态规划：
- dp[i]：存储第i+1个超级丑数
- pos[i]：存储第i下标对应的prime值，用于和dp[pos[i]]值进行乘积
- 如果当前下标对应的素数得到的乘积是所有可能中的最小值，那么当前下标对应值+1，代表素数与后一个dp值相乘
### 代码
```java
class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] dp = new int[n];
        dp[0] = 1;
        int[] pos = new int[primes.length];
        
        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++) {
                min = Math.min(min, dp[pos[j]] * primes[j]);
            }
            
            for (int j = 0; j < primes.length; j++) {
                if (min == dp[pos[j]] * primes[j]) {
                    pos[j]++;
                    dp[i] = min;
                }
            }
        }
        return dp[n - 1];
    }
}
```