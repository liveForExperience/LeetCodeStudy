# [LeetCode_3129_找出所有稳定的二进制数组I](https://leetcode.cn/problems/find-all-possible-stable-binary-arrays-i)
## 解法
### 思路
- `dp[i][j][k]`：表示最后选择的数字是`k`（0或1），且包含`i`个0，`j`个1的情况下，满足稳定数组要求的最大可能数字
  - i的范围：`zero`
  - j的范围：`one`
  - k的范围：`[0, 1]`
- base case：
  - dp[i][0][0] = 1（i ∈ [1, min(limit, zero)]）
  - dp[0][j][1] = 1 (j ∈ [1, min(limit, one)])
- 状态转移方程：
  - 若k = 0：
    - 如果i>limit：dp[i][j][0] = dp[i - 1][j][0] + dp[i - 1][j][1] - dp[i - limit - 1][j][1]
    - 如果i<=limit：dp[i][j][0] = dp[i - 1][j][0] + dp[i - 1][j][1]
  - 若k = 1：
    - 如果j > limit：dp[i][j][1] = dp[i][j - 1][1] + dp[i][j - 1][0] - dp[i][j - limit - 1][0]
    - 如果j <= limit：dp[i][j][1] = dp[i][j - 1][1] + dp[i][j - 1][0]
  - 为什么减去的特殊状态时，当前使用0时，减去的是1的状态，以及使用1的时候，减去的是0的状态，那是因为：我们要剔除的是已经连续limit次的0或者1的组合情况，如果当前使用0的时候（1的情况类似）减去的是`(i - limit - 1)`时使用0的情况，那么就代表(limit+1)次也是0，就和需要减去的情况不同。
- 返回结果：`dp[zero][one][0] + dp[zero][one][1]`
- 因为题目提示结果会很大，需要取模，所以需要对计算过程中的加法计算的时候，使用MOD来取模计算：
  - `num = ((num1 + num2) % mod + mod) % mod`
### 代码
```java
class Solution {
    public int numberOfStableArrays(int zero, int one, int limit) {
        int mod = 1000000007;
        long[][][] dp = new long[zero + 1][one + 1][2];
        for (int i = 0; i <= Math.min(zero, limit); i++) {
            dp[i][0][0] = 1;
        }

        for (int j = 0; j <= Math.min(one, limit); j++) {
            dp[0][j][1] = 1;
        }

        for (int i = 1; i <= zero; i++) {
            for (int j = 1; j <= one; j++) {
                dp[i][j][0] = dp[i - 1][j][1] + dp[i - 1][j][0];
                if (i > limit) {
                    dp[i][j][0] -= dp[i - limit - 1][j][1];
                }
                dp[i][j][0] = (dp[i][j][0] % mod + mod) % mod;

                dp[i][j][1] = dp[i][j - 1][0] + dp[i][j - 1][1];
                if (j > limit) {
                    dp[i][j][1] -= dp[i][j - limit - 1][0];
                }
                dp[i][j][1] = (dp[i][j][1] % mod + mod) % mod;
            }
        }

        return (int) ((dp[zero][one][0] + dp[zero][one][1]) % mod);
    }
}
```