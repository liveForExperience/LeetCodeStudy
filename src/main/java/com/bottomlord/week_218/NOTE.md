# [LeetCode_2596_检查骑士巡视方案](https://leetcode.cn/problems/check-knight-tour-configuration)
## 解法
### 思路
- 初始化骑士可以移动的8个方向，可以用一个二维数组`dirs`表示
- 遍历棋盘数组，将步数对应的横竖坐标记录在一个二维数组`steps`中
  - 坐标对应步数
  - 元素存储横轴和纵轴坐标
- 从坐标`[0,0]`开始，遍历`steps`，并将`[0,0]`作为初始的前置坐标
- 基于`pre`，遍历`dirs`，计算是否有步数可以匹配当前`steps[i]`，如果不能匹配就终止，否则继续
- 因为可能存在`steps[0]`就在默认的起始`[0,0]`位置，所以可以将`[0,0]`加入到`dirs`数组中，覆盖这种情况
### 代码
```java
class Solution {
  private int[][] dirs = {{0, 0}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};
  public boolean checkValidGrid(int[][] grid) {
    int row = grid.length, col = grid[0].length, n = row * col;
    int[][] steps = new int[n][2];
    for (int r = 0; r < row; r++) {
      for (int c = 0; c < col; c++) {
        steps[grid[r][c]] = new int[]{r, c};
      }
    }

    int[] pre = {0, 0};
    for (int i = 0; i < n; i++) {
      int r = pre[0], c = pre[1],
              nr = steps[i][0], nc = steps[i][1];

      boolean reach = false;
      for (int[] dir : dirs) {
        if (r + dir[0] == nr && c + dir[1] == nc) {
          pre = steps[i];
          reach = true;
          break;
        }
      }

      if (!reach) {
        return false;
      }
    }

    return true;
  }
}
```