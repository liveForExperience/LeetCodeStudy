# [LeetCode_2697_字典序最小回文串](https://leetcode.cn/problems/lexicographically-smallest-palindrome/)
## 解法
### 思路
双指针模拟
### 代码
```java
class Solution {
    public String makeSmallestPalindrome(String s) {
        int n = s.length(), head = 0, tail = n - 1;
        char[] cs = s.toCharArray();
        while (head <= tail) {
            char a = cs[head], b = cs[tail];
            if (a != b) {
                cs[head] = cs[tail] = a < b ? a : b;
            }

            head++;
            tail--;
        }
        
        return new String(cs);
    }
}
```