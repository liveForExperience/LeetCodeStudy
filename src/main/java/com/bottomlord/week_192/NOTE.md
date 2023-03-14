# [LeetCode_2281_巫师的总力量和](https://leetcode.cn/problems/sum-of-total-strength-of-wizards/)
## 解法
### 思路

### 代码
```java

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