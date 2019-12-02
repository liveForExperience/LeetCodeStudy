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