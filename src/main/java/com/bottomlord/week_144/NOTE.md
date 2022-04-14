# [LeetCode_806_写字符串需要的行数](https://leetcode-cn.com/problems/number-of-lines-to-write-string/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public int[] numberOfLines(int[] widths, String S) {
        char[] cs = S.toCharArray();
        int line = 1, count = 0;

        for (char c : cs) {
            count += widths[c - 'a'];
            if (count > 100) {
                count = widths[c - 'a'];
                line++;
            }
        }

        return new int[]{line, count};
    }
}
```
# [LeetCode_694_不同岛屿的数量](https://leetcode-cn.com/problems/number-of-distinct-islands/)
## 解法
### 思路
- 遍历二维数组，从左到右，从上到下
- 当遇到非0的坐标时，开始dfs
- dfs遍历时使用字符串记录过程，并将遍历过的坐标的值转换为0
- 遍历结束后将字符串放入set中统计
- 二维数组遍历结束后，返回set的个数
### 代码
```java
class Solution {
private int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int numDistinctIslands(int[][] grid) {

        Set<String> set = new HashSet<>();
        int row = grid.length, col = grid[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }

                StringBuilder sb = new StringBuilder();
                dfs(grid, i, j, row, col, 0, sb);
                set.add(sb.toString());
            }
        }

        return set.size();
    }

    private void dfs(int[][] grid, int x, int y, int row, int col, int dir, StringBuilder sb) {
        if (x < 0 || x >= row || y < 0 || y >= col || grid[x][y] == 0) {
            return;
        }

        grid[x][y] = 0;
        sb.append(dir);
        for (int i = 0; i < dirs.length; i++) {
            dfs(grid, x + dirs[i][0], y + dirs[i][1], row, col, i, sb);
        }
        sb.append(-dir);
    }
}
```
# [LeetCode_2235_两整数相加](https://leetcode-cn.com/problems/add-two-integers/)
## 解法
### 思路
num1 + num2
### 代码
```java
class Solution {
    public int sum(int num1, int num2) {
        return num1 + num2;
    }
}
```
## 解法二
### 思路
不使用+号：
- 使用`^`号进行不进位加法
- 使用`&`号和左移得到进位的值
- 再用`^`和进位值做不进位加法就能得到一次进位的结果
- 持续以上过程，直到进位值为0为止
- 初始可以将num1的值当做进位值，另一个值当做不进位加法后的值
### 代码
```java
class Solution {
    public int sum(int num1, int num2) {
        while (num1 != 0) {
            int tmp = num1 ^ num2;
            num1 = (num1 & num2) << 1;
            num2 = tmp;
        }

        return num2;
    }
}
```