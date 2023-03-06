# [LeetCode_880_索引处的解码字符串]()
## 解法
### 思路

### 代码
```java

```
# [LeetCode_1653_使字符串平衡的最少删除次数](https://leetcode.cn/problems/minimum-deletions-to-make-string-balanced/)
## 解法
### 思路
- 题目要求所有a的坐标都要比所有b的坐标小
- 如果将数组切分成2部分，将分割线的左侧的b全部删除，将分割线右侧的a全部删除，就能达到题目要求的平衡状态
- 可以预先通过统计b的前缀和统计a的后缀数组，来优化遍历数组时候的重复计算
### 代码
```java
class Solution {
    public int minimumDeletions(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length, preB = 0, sufA = 0;
        int[] preBs = new int[n], sufAs = new int[n];
        for (int i = 0; i < n; i++) {
            preBs[i] = preB + (cs[i] == 'b' ? 1 : 0);
            preB = preBs[i];
        }
        
        for (int i = n - 1; i >= 0; i--) {
            sufAs[i] = sufA + (cs[i] == 'a' ? 1 : 0);
            sufA = sufAs[i];
        }
        
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < cs.length; i++) {
            ans = Math.min(ans, preBs[i] + sufAs[i] - 1);
        }
        return ans;
    }
}
```
## 解法二
### 思路
动态规划：
- dp[i]:以i为字符串结尾，最少需要删除的字符个数
- countB:到目前为止出现的b的个数
- 状态转移方程：
  - cs[i] == 'a'：dp[i] = max(dp[i - 1] + 1, b)
    - dp[i - 1] + 1表示将当前a删除，从而使s[0, i]与s[0, i-1]保持一致，成为平衡的
    - b表示，表示不保留所有的b，将b全部删除
  - cs[i] == 'b'：dp[i] = dp[i - 1]且b++
- 初始状态:
  - dp[0] = 0，无论是什么字符串，因为只有1个，所有无需任何操作就是平衡的
  - b = cs[i] == 'b' ? 1 : 0
- 结果：dp[n - 1]
### 代码
```java
class Solution {
    public int minimumDeletions(String s) {
        int n = s.length(), b = s.charAt(0) == 'b' ? 1 : 0;
        char[] cs = s.toCharArray();
        int[] dp = new int[n];
        for (int i = 1; i < n; i++) {
            if (cs[i] == 'a') {
                dp[i] = Math.min(dp[i - 1] + 1, b);
            } else {
                dp[i] = dp[i - 1];
                b++;
            }
        }
        
        return dp[n - 1];
    }
}
```
## 解法三
### 思路
基于解法二，发现所有状态都只依赖前一个元素，所以讲dp数组压缩成变量dp
### 代码
```java
class Solution {
    public int minimumDeletions(String s) {
int dp = 0, b = 0;
        for (char c : s.toCharArray()) {
            if (c == 'a') {
                dp = Math.min(dp + 1, b);
            } else {
                b++;
            }
        }
        
        return dp;
    }
}
```