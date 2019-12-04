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