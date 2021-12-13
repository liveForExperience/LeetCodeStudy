# [LeetCode_807_保持城市天际线](https://leetcode-cn.com/problems/max-increase-to-keep-city-skyline/)
## 解法
### 思路
- 分别求出二维数组的横和纵的最大值
- 遍历二维数组，将横和纵最大值之间的最小值与当前值相减，累加这个相减的值
### 代码
```java
class Solution {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        int[] rowMax = new int[row], colMax = new int[col];

        for (int i = 0; i < row; i++) {
            rowMax[i] = Arrays.stream(grid[i]).max().getAsInt();
        }

        for (int i = 0; i < col; i++) {
            int max = 0;
            for (int j = 0; j < row; j++) {
                max = Math.max(max, grid[j][i]);
            }
            colMax[i] = max;
        }

        int sum = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                sum += Math.min(rowMax[i], colMax[j]) - grid[i][j];
            }
        }
        
        return sum;
    }
}
```