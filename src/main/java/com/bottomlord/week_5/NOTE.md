# LeetCode_892_三维形体的表面积
## 题目
在 N * N 的网格上，我们放置一些 1 * 1 * 1  的立方体。

每个值 v = grid[i][j] 表示 v 个正方体叠放在对应单元格 (i, j) 上。

请你返回最终形体的表面积。

示例 1：
```
输入：[[2]]
输出：10
```
示例 2：
```
输入：[[1,2],[3,4]]
输出：34
```
示例 3：
```
输入：[[1,0],[0,2]]
输出：16
示例 4：
```
输入：[[1,1,1],[1,0,1],[1,1,1]]
输出：32
示例 5：

输入：[[2,2,2],[2,1,2],[2,2,2]]
输出：46

提示：
```
1 <= N <= 50
0 <= grid[i][j] <= 50
```
## 解法
### 思路
把表面积分成6部分
- 上下两面：(N * N - 为0的元素) * 2
- 4个面：
    - 第1行的v的和
    - 第N行的v的和
    - 第1列的v的和
    - 第N列的v的和
- 上面参差不齐露出的部分

难点在露出的部分，需要遍历二维数组，和相邻4个位置的v作比较，大于相邻的部分就累加表面积里
### 代码
```java
class Solution {
    public int surfaceArea(int[][] grid) {
        return topBottom(grid) + side(grid) + diff(grid);
    }

    private int diff(int[][] grid) {
        int sum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    sum-=2;
                    continue;
                }
                
                if (!outOfRnage(i - 1, j, grid)) {
                    int dif = grid[i][j] - grid[i - 1][j];
                    sum += dif > 0 ? dif : 0;
                }

                if (!outOfRnage(i + 1, j, grid)) {
                    int dif = grid[i][j] - grid[i + 1][j];
                    sum += dif > 0 ? dif : 0;
                }

                if (!outOfRnage(i, j - 1, grid)) {
                    int dif = grid[i][j] - grid[i][j - 1];
                    sum += dif > 0 ? dif : 0;
                }

                if (!outOfRnage(i, j + 1, grid)) {
                    int dif = grid[i][j] - grid[i][j + 1];
                    sum += dif > 0 ? dif : 0;
                }
            }
        }

        return sum;
    }

    private int side(int[][] grid) {
        int sum = 0;
        for (int i = 0; i < grid[0].length; i++) {
            sum += grid[0][i];
        }

        int lastRow = grid.length - 1;
        for (int i = 0; i < grid[lastRow].length; i++) {
            sum += grid[lastRow][i];
        }

        for (int[] row : grid) {
            sum += row[0] + row[row.length - 1];
        }
        return sum;
    }

    private int topBottom(int[][] grid) {
        return grid.length * grid.length * 2;
    }

    private boolean outOfRnage(int row, int col, int[][] grid) {
        return row < 0 || col < 0 || row >= grid.length || col >= grid[row].length;
    }
}
```
## 解法二
### 思路
遍历二维数组：
- 先把当前坐标上的立方体的表面积算出：v * 4 + 2，累加
- 和横坐标上前一个元素比较大小，谁堆叠的立方体少，谁的一面就会被遮盖，当然长的那个立方体的一面也会遮盖，把这两个面从累加值中减去
- 同理和纵坐标也做一次比较
- 遍历结束，返回累加值
### 代码
```java
class Solution {
    public int surfaceArea(int[][] grid) {
        int sum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != 0) {
                    sum += grid[i][j] * 4 + 2;
                }
                
                if (i != 0) {
                    sum -= Math.min(grid[i][j], grid[i - 1][j]) * 2;
                }
                
                if (j != 0) {
                    sum -= Math.min(grid[i][j], grid[i][j - 1]) * 2;
                }
            }
        }
        
        return sum;
    }
}
```
# LeetCode_653_两数之和 IV (输入 BST)
## 题目
给定一个二叉搜索树和一个目标结果，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。

案例 1:
```
输入: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 9

输出: True
```
案例 2:
```
输入: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 28

输出: False
```
## 解法
### 思路
- dfs中序递归遍历得到升序序列
- 迭代寻找是否有满足target的两个值
### 代码
```java
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                int ans = list.get(i) + list.get(j); 
                
                if (ans == k) {
                    return true;
                }
                
                if (ans > k) {
                    break;
                }
            }
        }
        return false;
    }
    
    private void dfs(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        
        dfs(node.left, list);
        list.add(node.val);
        dfs(node.right, list);
    }
}
```
## 解法二
### 思路
嵌套递归
- 第一层递归目的是找到两数相加的第一个数，而是否进入第二层递归的判断依据是，k-当前节点的val是否还等于当前节点的val，如果是说明当前节点不符合要求，因为二叉搜索树的节点不能相等
- 如果符合要求，就进行第二层递归，目的是从根节点开始找到k与第一层递归的那个节点的val的差，如果有就返回true
- 如果如果没有找到，就递归取找左右子树，再重复如上的过程
### 代码
```java
class Solution {
    private TreeNode node;
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        
        if (node == null) {
            node = root;
        }

        int result = k - root.val;
        if (root.val == result) {
            return findTarget(root.left, k) || findTarget(root.right, k);
        }

        boolean find = dfs(this.node, result);
        if (find) {
            return true;
        }

        return findTarget(root.left, k) || findTarget(root.right, k);
    }

    private boolean dfs(TreeNode node, int target) {
        if (node == null) {
            return false;
        }

        if (node.val == target) {
            return true;
        }

        return node.val > target ? dfs(node.left, target) : dfs(node.right, target);
    }
}
```