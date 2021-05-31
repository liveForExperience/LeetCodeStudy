# [LeetCode_342_4的幂](https://leetcode-cn.com/problems/power-of-four/)
## 解法
### 思路
- 非正整数，返回false
- 32位的整数，依次左移2位来判断是否与当前值相等，左移结束如果还是不相等就返回false，否则返回true
### 代码
```java
class Solution {
    public boolean isPowerOfFour(int n) {
        if (n <= 0) {
            return false;
        }
        
        int bit = 1, time = 0;
        while (time < 16) {
            if (bit == n) {
                return true;
            }
            
            if (bit > n) {
                return false;
            }

            bit <<= 2;
            time++;
        }

        return false;
    }
}
```