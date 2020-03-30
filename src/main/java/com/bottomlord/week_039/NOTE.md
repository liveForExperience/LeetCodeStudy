# Interview_0802_迷路的机器人
## 题目
设想有个机器人坐在一个网格的左上角，网格 r 行 c 列。机器人只能向下或向右移动，但不能走到一些被禁止的网格（有障碍物）。设计一种算法，寻找机器人从左上角移动到右下角的路径。

网格中的障碍物和空位置分别用 1 和 0 来表示。

返回一条可行的路径，路径由经过的网格的行号和列号组成。左上角为 0 行 0 列。

示例 1:
```
输入:
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
输出: [[0,0],[0,1],[0,2],[1,2],[2,2]]
```
解释: 
```
输入中标粗的位置即为输出表示的路径，即
0行0列（左上角） -> 0行1列 -> 0行2列 -> 1行2列 -> 2行2列（右下角）
说明：r 和 c 的值均不超过 100。
```
## 解法
### 思路
回溯+记忆化搜索
### 代码
```java
class Solution {
    public List<List<Integer>> pathWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return Collections.emptyList();
        }
        
        LinkedList<List<Integer>> paths = new LinkedList<>();
        int row = obstacleGrid.length, col = obstacleGrid[0].length;
        backTrack(0, 0, row, col, obstacleGrid, new boolean[row][col], paths);
        return paths;
    }

    private boolean backTrack(int x, int y, int row, int col, int[][] grid, boolean[][] visited, LinkedList<List<Integer>> paths) {
        if (x >= row || y >= col || grid[x][y] == 1 || visited[x][y]) {
            return false;
        }
        
        paths.add(Arrays.asList(x, y));
        visited[x][y] = true;
        
        if (x == row - 1 && y == col - 1) {
            return true;
        }
        
        if (backTrack(x, y + 1, row, col, grid, visited, paths) ||
            backTrack(x + 1, y, row, col, grid, visited, paths)) {
            return true;
        }
        
        paths.removeLast();
        return false;
    }
}
```