# [LeetCode_1738_找出第K大的异或坐标值](https://leetcode.cn/problems/find-kth-largest-xor-coordinate-value)
## 解法
### 思路
- 使用前缀和来加速获取二维异或和的过程，初始化一个`pre`二维数组
- 异或的特性是：`x ^ y ^ y = x`，所以，如果使用前缀和来推导当前的坐标的异或和时，如果当前坐标只倚赖上方和左方的坐标来异或，那么左方上部分的那个坐标的异或和会因为异或的特性而置为0，所以还需要将左上方的异或和加入到异或计算中
- 所以当前坐标的异或和计算公式：`pre[i][j] = pre[i - 1][j - 1] ^ pre[i - 1][j] ^ pre[i][j - 1] ^ matrix[i][j]`，其中`pre[i][j]`代表当前`(i, j)`坐标对应的异或和
- 为了简化循环遍历`matrix`时候，数组越界的判断，可以使`pre`的行和列长度+1，使`pre[i + 1][j + 1]`来对应`matrix[i][j]`的异或和
### 代码
```java
class Solution {
    public int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int[][] pre = new int[m + 1][n + 1];
        int[] result = new int[m * n];
        int index = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                pre[i + 1][j + 1] = pre[i][j + 1] ^ pre[i + 1][j] ^ pre[i][j] ^ matrix[i][j];
                result[index++] = pre[i + 1][j + 1];
            }
        }

        Arrays.sort(result);
        return result[m * n - k];
    }
}
```