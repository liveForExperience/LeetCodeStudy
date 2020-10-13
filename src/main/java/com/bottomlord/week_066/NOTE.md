# LeetCode_296_最佳的碰头地点
## 题目
有一队人（两人或以上）想要在一个地方碰面，他们希望能够最小化他们的总行走距离。

给你一个 2D 网格，其中各个格子内的值要么是 0，要么是 1。

1 表示某个人的家所处的位置。这里，我们将使用 曼哈顿距离 来计算，其中 distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|。

示例：
```
输入: 

1 - 0 - 0 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0

输出: 6 

解析: 给定的三个人分别住在(0,0)，(0,4) 和 (2,2):
     (0,2) 是一个最佳的碰面点，其总行走距离为 2 + 2 + 2 = 6，最小，因此返回 6。
```
## 解法
### 思路
- 遍历二维数组，找到为1的坐标
- 遍历二维数组，计算所有坐标与所有为1坐标的曼哈顿距离之和
- 求所有可能坐标中最小的总和距离
### 代码
```java
class Solution {
    public int minTotalDistance(int[][] grid) {
        int r = grid.length;
        if (r == 0) {
            return 0;
        }
        int c = grid[0].length;
        if (c == 0) {
            return 0;
        }

        List<int[]> points = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    points.add(new int[]{i, j});
                }
            }
        }

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int sum = 0;
                for (int[] point : points) {
                    sum += distance(point[0], point[1], i, j);
                }

                ans = Math.min(ans, sum);
            }
        }

        return ans;
    }

    private int distance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
```
# LeetCode_298_二叉树最长连续序列
## 题目
给你一棵指定的二叉树，请你计算它最长连续序列路径的长度。

该路径，可以是从某个初始结点到树中任意结点，通过「父 - 子」关系连接而产生的任意路径。

这个最长连续的路径，必须从父结点到子结点，反过来是不可以的。

示例 1：
```
输入:

   1
    \
     3
    / \
   2   4
        \
         5

输出: 3

解析: 当中，最长连续序列是 3-4-5，所以返回结果为 3
```
示例 2：
```
输入:

   2
    \
     3
    / 
   2    
  / 
 1

输出: 2 

解析: 当中，最长连续序列是 2-3。注意，不是 3-2-1，所以返回 2。
```
## 解法
### 思路
dfs：
- 自顶向下
- 使用3个变量进行递归：
    - `len`记录当前的长度
    - `pre`记录父节点引用
    - `node`记录当前节点引用
- 退出条件：当前节点为null，返回len值
- 过程：判断pre是否为null且pre的值是否与当前节点形成升序，如果是就在len基础上+1，否则就初始化为1
- 返回：当前len与左右子树递归后获得的结果的最大值，再作比较，取最大值返回
### 代码
```java
class Solution {
    public int longestConsecutive(TreeNode root) {
        return dfs(null, root, 0);
    }
    
    private int dfs(TreeNode pre, TreeNode node, int len) {
        if (node == null) {
            return len;
        }
        
        len = (pre != null && pre.val == node.val - 1) ? len + 1 : 1;
        return Math.max(len, Math.max(dfs(node, node.left, len), dfs(node, node.right, len)));
    }
}
```