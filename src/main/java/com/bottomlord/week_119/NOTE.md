# [LeetCode_476_数字的补数](https://leetcode-cn.com/problems/number-complement/)
## 解法
### 思路
- 求出num中为1的最高位
- 然后左移一位-1，获得掩码
- 用掩码和原数字进行抑或求出补数
### 代码
```java
class Solution {
    public int findComplement(int num) {
        int highBit = 0;
        for (int i = 1; i <= 30; i++) {
            if (num >= 1 << i) {
                highBit = i;
            } else {
                break;
            }
        }

        int mask = highBit == 30 ? 0x7fffffff : (1 << (highBit + 1)) - 1;
        return mask ^ num;
    }
}
```
# [LeetCode_282_给表达式添加运算符](https://leetcode-cn.com/problems/expression-add-operators/)
## 解法
### 思路

### 代码
```java

```