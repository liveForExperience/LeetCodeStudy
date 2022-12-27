# [LeetCode_749_隔离病毒](https://leetcode.cn/problems/contain-virus/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_940_不同子序列](https://leetcode.cn/problems/distinct-subsequences-ii/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_1774_统计同构字符串的个数](https://leetcode.cn/problems/count-number-of-homogenous-substrings/)
## 解法
### 思路
- 将字符串切分成一个个同构字符子串
- 每一个字符子串计算从1开始到子串长度的同构字符串个数，然后累加起来
- 计算的方式是sum(n - i + 1)，n是字符串串长度，i是小于n大于0的子串长度
- 处理过程就是遍历字符串，累加时记得取模1000000007
### 代码
```java
class Solution {
    public int countHomogenous(String s) {
        int index = 0, n = s.length(), ans = 0, mod = 1000000007;
        while (index < n) {
            char c = s.charAt(index);

            int count = 0;
            while (index < n && c == s.charAt(index)) {
                index++;
                count++;
            }

            for (int i = 1; i <= count; i++) {
                ans += (count - i) + 1;
                ans %= mod;
            }
        }

        return ans;
    }
}
```
# [LeetCode_2027_转换字符串的最少操作次数](https://leetcode.cn/problems/minimum-moves-to-convert-string/)
## 解法
### 思路
- 遍历字符串，当遇到x的时候就修改当前及之后的2个字符为o
- 然后继续遍历
### 代码
```java
class Solution {
    public int minimumMoves(String s) {
        char[] cs = s.toCharArray();
        int count = 0;
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == 'X') {
                cs[i] = 'O';

                if (i + 1 < cs.length) {
                    cs[i + 1] = 'O';
                }

                if (i + 2 < cs.length) {
                    cs[i + 2] = 'O';
                }

                i += 2;
                count++;
            }
        }

        return count;
    }
}
```