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
# [LeetCode_1806_还原排列的最少操作步数](https://leetcode.cn/problems/minimum-number-of-operations-to-reinitialize-a-permutation/)
## 解法
### 思路
暴力模拟
### 代码
```java
class Solution {
    public int reinitializePermutation(int n) {
        int[] perm = new int[n], arr = new int[n];
        for (int i = 0; i < n; i++) {
            perm[i] = i;
            arr[i] = i;
        }

        int ans = 0;
        while (true) {
            int[] cur = new int[n];

            for (int i = 0; i < n; i++) {
                if (i % 2 == 0) {
                    cur[i] = arr[i / 2];
                } else {
                    cur[i] = arr[n / 2 + (i - 1) / 2];
                }
            }

            ans++;
            if (Arrays.equals(cur, perm)) {
                break;
            }
            
            arr = cur;
        }

        return ans;
    }
}
```
## 解法二
### 思路
- 找规律发现，一个坐标值根据题目要求进行变化，一定会最终变回到原始值
- 这就形成了一个环
- 且发现最大环的长度是所有小环长度的公倍数
- 那么最大的环就是题目要求的操作数结果
### 代码
```java
class Solution {
    public int reinitializePermutation(int n) {
        int i = 1, path = 0;
        while (true) {
            i = i % 2 == 0 ? i / 2 : n / 2 + (i - 1) / 2;
            path++;
            
            if (i == 1) {
                return path;
            }
        }
    }
}
```