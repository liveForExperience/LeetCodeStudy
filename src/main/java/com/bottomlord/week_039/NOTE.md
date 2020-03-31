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
# Interview_0803_魔术索引
## 题目
魔术索引。 在数组A[0...n-1]中，有所谓的魔术索引，满足条件A[i] = i。给定一个有序整数数组，编写一种方法找出魔术索引，若有的话，在数组A中找出一个魔术索引，如果没有，则返回-1。若有多个魔术索引，返回索引值最小的一个。

示例1:
```
 输入：nums = [0, 2, 3, 4, 5]
 输出：0
 说明: 0下标的元素为0
```
示例2:
```
 输入：nums = [1, 1, 1]
 输出：1
```
提示:
```
nums长度在[1, 1000000]之间
```
## 解法
### 思路
- 遍历比对索引和值
### 代码
```java
class Solution {
    public int findMagicIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i == nums[i]) {
                return i;
            }
        }

        return -1;
    }
}
```
# Interview_0804_幂集
## 题目
幂集。编写一种方法，返回某集合的所有子集。集合中不包含重复的元素。

说明：解集不能包含重复的子集。

示例:
```
 输入： nums = [1,2,3]
 输出：
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
```
## 解法
### 思路 
回溯：
- 退出条件：数组元素被全部遍历
- 过程：
    - 将path放入ans中作为一种集合可能
    - 遍历数组元素，将当前元素放入path中，继续递归
    - 返回后将当前循环加入path的元素删去，继续下一个元素的递归
### 代码
```java
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backTrack(0, nums, new LinkedList<>(), ans);
        return ans;
    }

    private void backTrack(int index, int[] nums, LinkedList<Integer> path, List<List<Integer>> ans) {
        ans.add(new ArrayList<>(path));

        for (int i = index; i < nums.length; i++) {
            path.offerLast(nums[i]);
            backTrack(i + 1, nums, path, ans);
            path.removeLast();
        }
    }
}
```
# Interview_0805_递归乘法
## 题目
递归乘法。 写一个递归函数，不使用 * 运算符， 实现两个正整数的相乘。可以使用加号、减号、位移，但要吝啬一些。

示例1:
```
 输入：A = 1, B = 10
 输出：10
```
示例2:
```
 输入：A = 3, B = 4
 输出：12
```
提示:
```
保证乘法范围不会溢出
```
## 解法
### 思路
递归相加：
- 以`B`作为递归的次数，`A`作为累加的值
- 退出条件是`B <= 1`，返回`A`
### 代码
```java
class Solution {
    public int multiply(int A, int B) {
        return B > 1 ? multiply(A, B - 1) + A : A;
    }
}
```