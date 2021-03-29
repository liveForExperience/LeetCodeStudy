# [LeetCode_190_颠倒二进制位](https://leetcode-cn.com/problems/reverse-bits/)
## 解法
### 思路
- 使用`10000000000000000000000000000000`和`1`两个掩码来确定颠倒二进制
- 使用`1`，不断左移来来确定当前位上是否是1，用或来判断
- 如果判断是1，那就通过`10000000000000000000000000000000`同步无符号右移的过程中，通过或来填充1
### 代码
```java
public class Solution {
    public int reverseBits(int n) {
        int mask = 1, time = 31;
        while (time-- > 0) {
            mask <<= 1;
        }

        int ans = 0, count = 1;
        time = 32;
        while (time-- > 0) {
            if ((count | n) == n) {
                ans |= mask;
            }
            mask >>>= 1;
            count <<= 1;
        }
        return ans;
    }
}
```
# [LeetCode_502_IPO](https://leetcode-cn.com/problems/ipo/)
## 失败解法
### 原因
超时，时间复杂度过高，树高k层，第t(t <= n)层的搜索都要检查t-1个节点
### 思路
记忆化+回溯
### 代码
```java
class Solution {
    private int ans;

    public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        this.ans = W;
        k = Math.min(k, Profits.length);
        backTrack(k, W, Profits, Capital, new boolean[Profits.length]);
        return ans;
    }

    private void backTrack(int time, int w, int[] profits, int[] capital, boolean[] memo) {
        ans = Math.max(ans, w);
        if (time == 0) {
            return;
        }

        for (int i = 0; i < profits.length; i++) {
            if (memo[i] || w < capital[i]) {
                continue;
            }

            memo[i] = true;
            backTrack(time -  1, w + profits[i], profits, capital, memo);
            memo[i] = false;
        }
    }
}
```
## 解法
### 思路

### 代码
```java

```