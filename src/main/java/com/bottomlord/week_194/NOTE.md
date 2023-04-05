# [LeetCode_957_N天后的牢房](https://leetcode.cn/problems/prison-cells-after-n-days/)
## 失败解法
### 原因
超时
### 思路
暴力模拟
### 代码
```java
class Solution {
    public int[] prisonAfterNDays(int[] cells, int n) {
        int[] pre = cells;
        while (n-- > 0) {
            int[] cur = new int[8];
            for (int i = 0; i < 8; i++) {
                if (i == 0 || i == 7) {
                    cur[i] = 0;
                    continue;
                }
                
                if (pre[i - 1] != pre[i + 1]) {
                    cur[i] = 0;
                } else {
                    cur[i] = 1;
                }
            }
            
            pre = cur;
        }
        
        return pre;
    }
}
```
## 解法
### 思路

### 代码
```java

```