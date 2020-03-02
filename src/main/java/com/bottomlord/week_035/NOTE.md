# Interview_47_礼物的最大值
## 题目
在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？

示例 1:
```
输入: 
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
输出: 12
解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
```
提示：
```
0 < grid.length <= 200
0 < grid[0].length <= 200
```
## 解法
### 思路
递归+记忆化搜索
- 使用map存储已经遍历过的`x,y`位起点的路径上的最大值
- 递归退出条件是坐标越界
- 过程中就是判断向右或向下的节点，`visited`中是否已经存储，如果已经存储就直接返回，否则递归下去求得这个路径的最大值
- 最终返回当前节点值与向下或向右的最大值之和
### 代码
```java
class Solution {
    public int maxValue(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        return dfs(grid, 0, 0, grid.length, grid[0].length, new HashMap<>());
    }

    private int dfs(int[][] grid, int x, int y, int row, int col, Map<Integer, Integer> visited) {
        if (x < 0 || x >= row || y < 0 || y >= col) {
            return 0;
        }

        int right = visited.containsKey(1000 * x + y + 1) ? visited.get(1000 * x + y + 1) : dfs(grid, x, y + 1, row, col, visited);
        int down = visited.containsKey(1000 * (x + 1) + y) ? visited.get(1000 * (x + 1) + y) : dfs(grid, x + 1, y, row, col, visited);
        
        int max = grid[x][y] + Math.max(right, down);
        visited.put(1000 * x + y, max);
        return max;
    }
}
```
## 解法二
### 思路
动态规划：
- `dp[x][y]`：坐标`x,y`为起点的最大路径值
- 状态转移方程：`dp[x][y] = grid[x][y] + max(dp[x + 1][y], dp[x][y + 1])`
- 初始化：`dp[row - 1][col - 1] = grid[row - 1][col - 1]`
- 返回结果：`dp[0][0]`
- 过程：嵌套循环所有二维数组节点，从行和列的最大值开始向前遍历，遍历中执行状态转移方程
### 代码
```java
class Solution {
    public int maxValue(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int row = grid.length, col = grid[0].length;
        int[][] dp = new int[row][col];
        dp[row - 1][col - 1] = grid[row - 1][col - 1];
        
        for (int i = row - 1; i >= 0; i--) {
            for (int j = col - 1; j >= 0; j--) {
                dp[i][j] = grid[i][j] + Math.max(i + 1 < row ? dp[i + 1][j] : 0, j + 1 < col ? dp[i][j + 1] : 0);
            }
        }
        
        return dp[0][0];
    }
}
```
## 解法三
### 思路
- 因为只能向右和向下，所以第一行和第一列的值可以处理成途径节点的累加值
- 然后通过这一行一列的初始值，开始状态转移
### 代码
```java
class Solution {
    public int maxValue(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int row = grid.length, col = grid[0].length;

        for (int i = 1; i < row; i++) {
            grid[i][0] += grid[i - 1][0];
        }
        
        for (int i = 1; i < col; i++) {
            grid[0][i] += grid[0][i - 1];
        }
        
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                grid[i][j] += Math.max(grid[i - 1][j], grid[i][j - 1]);
            }
        }
        
        return grid[row - 1][col - 1];
    }
}
```
# Interview_48_最长不含重复字符的子字符串
## 题目
请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。

示例 1:
```
输入: "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
```
示例 2:
```
输入: "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
```
示例 3:
```
输入: "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
```
提示：
```
s.length <= 40000
```
## 解法
### 思路
遍历：
- 初始化一个map记录遍历到的字母和数组中坐标的映射关系
- 定义`max`记录暂存的最大值
- 定义`start`作为当前子串的起始坐标
- 遍历数组，记录字母与坐标关系
- 如果发现当前字母已经存在，并且这个重复的坐标在start之后(在start之前的值不再有效，需要在之后重置为当前坐标)：
    - 计算当前坐标与`start`之间的差作为当前子串的长度，在于`max`比较，取较大值暂存为`max`
    - 将之前发现的重复的字母的坐标作为新的`start`，同时将当前坐标覆盖这个字母的坐标
- 遍历结束后，再计算依次最后一个子串的长度并于`max`比较
- 返回`max`
### 代码
```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        char[] cs = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        int start = 0, max = 0;
        
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(cs[i]) && map.get(cs[i]) >= start) {
                max = Math.max(i - start, max);
                start = map.get(cs[i]) + 1;
            }
            map.put(cs[i], i);
        }
        
        return Math.max(max, s.length() - start);
    }
}
```