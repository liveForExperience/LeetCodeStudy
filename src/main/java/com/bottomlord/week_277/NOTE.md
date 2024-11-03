# [LeetCode_3226_使两个整数相等的位更改次数](https://leetcode.cn/problems/number-of-bit-changes-to-make-two-integers-equal/description/?envType=daily-question&envId=2024-11-02)
## 解法
### 思路
- 依次对2个数字的二进制表示形式，从低位开始进行比较
  - 如果相等，就直接跳过，判断下一位
  - 如果n的当前位是0，而k是1，那么无法转换，直接返回-1作为结果
  - 否则，计数1次
- 2个数字所有剩余位都无需比较后（如果右移之后数字为0，则代表当前数字无需在进行比较），结束循环，返回计数值作为结果
### 代码
```java
class Solution {
    public int minChanges(int n, int k) {
        int cnt = 0;
        do {
            if ((n & 1) == (k & 1)) {
                continue;
            }

            if ((n & 1) == 0) {
                return -1;
            }

            cnt++;
        } while ((n = n >> 1) > 0 | (k = k >> 1) > 0);

        return cnt;
    }
}
```