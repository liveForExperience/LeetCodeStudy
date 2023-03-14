# [LeetCode_2281_巫师的总力量和](https://leetcode.cn/problems/sum-of-total-strength-of-wizards/)
## 解法
### 思路
- 求出数组中两部分的内容
  - 子数组中的最小值：可以计算某个元素在多少子数组中是最小值，然后通过单调栈来求出子数组组合的左右边界
  - 所有子数组的总和的总和：
    - 通过求前缀和的前缀和来求
    - 通过举例可以方便理解：[题解](https://leetcode.cn/problems/sum-of-total-strength-of-wizards/solution/by-wen-rou-yi-dao-123-xy2d/)
- 使用单调栈求出每个坐标对应的左右边界
- 准备前缀和的前缀和数组，这个数组的长度需要是原数组长度n的基础上+2，原因是每次求前缀和都需要在前一个数组的基础上+1，两次前缀和就要+2
- 遍历数组，根据题解中的公式求出子数组总和的总和，再乘以当前元素值即可，需要考虑取模
### 代码
```java
class Solution {
    public int totalStrength(int[] strength) {
        int n = strength.length, mod = (int) 1e9 + 7;
        int[] lefts = new int[n], rights = new int[n];
        Arrays.fill(lefts, -1);
        Arrays.fill(rights, n);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && strength[stack.peek()] >= strength[i]) {
                rights[stack.pop()] = i;
            }

            if (!stack.isEmpty()) {
                lefts[i] = stack.peek();
            }

            stack.push(i);
        }

        long sum = 0L;
        int[] ss = new int[n + 2];
        for (int i = 1; i <= n; i++) {
            sum = sum + strength[i - 1];
            ss[i + 1] = (int) ((ss[i] + sum) % mod);
        }

        long ans = 0L;
        for (int i = 0; i < n; i++) {
            int l = lefts[i] + 1, r = rights[i] - 1;
            long tot = ((long) (i - l + 1) * (ss[r + 2] - ss[i + 1]) - (long) (r - i + 1) * (ss[i + 1] - ss[l])) % mod;
            ans = (ans + strength[i] * tot) % mod;

        }
        return (int) (ans + mod) % mod;
    }
}
```
# [LeetCode_1605_给定行和列的和求可行矩阵](https://leetcode.cn/problems/find-valid-matrix-given-row-and-column-sums/)
## 解法
### 思路
贪心
- 一层一层的处理矩阵
- 每一层的从左向右，取横竖总和的最小值作为当前单元格的值，同时更新横竖的总和数组
- 保证每一行都满足总和矩阵的要求
- 因为行的总和与竖的总和是相等的，所以处理每一行变成了处理一个个的子问题
### 代码
```java
class Solution {
    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int r = rowSum.length, c = colSum.length;
        int[][] matrix = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int diff = Math.min(rowSum[i], colSum[j]);
                matrix[i][j] = diff;
                rowSum[i] -= diff;
                colSum[j] -= diff;
            }
        }
        
        return matrix;
    }
}
```