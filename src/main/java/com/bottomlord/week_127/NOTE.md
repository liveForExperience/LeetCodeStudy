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
# [LeetCode_689_三个无重叠子数组的最大和](https://leetcode-cn.com/problems/maximum-sum-of-3-non-overlapping-subarrays/)
## 解法
### 思路
[解法](https://leetcode-cn.com/problems/course-schedule-iii/solution/gong-shui-san-xie-jing-dian-tan-xin-yun-ghii2/)
### 代码
```java

```
# [LeetCode_690_课程表III](https://leetcode-cn.com/problems/course-schedule-iii/)
## 解法
### 思路
- 如果2门课的结束时间有早晚，d1 <= d2
  - 如果先学习d2，再学习d1，这种情况是符合的，那么先学习d1，再学习d2也肯定是可以的
  - 但是如果先学习d1在学习d2成立，却无法退出先学习d2再学习d1成立
- 
### 代码
```java

```