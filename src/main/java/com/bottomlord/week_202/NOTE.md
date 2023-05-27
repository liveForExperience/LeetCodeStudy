# [LeetCode_1080_根到叶路径上的不足节点](https://leetcode.cn/problems/insufficient-nodes-in-root-to-leaf-paths/)
## 解法
### 思路
dfs
- 如果节点是叶子节点，那么就要判断叶根路径上的总和是否小于limit，如果小于，那么这个节点就是不足节点，需要去除
- 如果当前节点的左右子树都是不足节点，那么当前节点也需要被删除
- 递归的退出条件： 当前节点是叶子节点
- 递归的过程：
  - 左子树不为空，递归左子树，返回当前左子树是否是不足节点
  - 右子树不为空，递归右子树，返回当前右子树是否是不足节点
- 递归返回：
  - 左右子树是否为空的与条件（即，如果任意不为不足节点，那么这个节点就保留，否则就去除）
### 代码
```java
class Solution {
    public TreeNode sufficientSubset(TreeNode root, int limit) {
        if (dfs(root, limit, 0)) {
            return null;
        }
        
        return root;
    }

    private boolean dfs(TreeNode node, int limit, int pre) {
        if (node.left == null && node.right == null) {
            return pre + node.val < limit;
        }

        boolean leftDel = true, rightDel = true;
        if (node.left != null) {
            leftDel = dfs(node.left, limit, pre + node.val);
        }
        
        if (node.right != null) {
            rightDel = dfs(node.right, limit, pre + node.val);
        }
        
        if (leftDel) {
            node.left = null;
        }
        
        if (rightDel) {
            node.right = null;
        }
        
        return leftDel && rightDel;
    }
}
```
# [LeetCode_2689_ExtractKthCharacterFromTheRopeTree](https://leetcode.cn/problems/extract-kth-character-from-the-rope-tree/)
## 解法
### 思路
dfs模拟
### 代码
```java
class Solution {
  public char getKthCharacter(RopeTreeNode root, int k) {
    return dfs(root).charAt(k - 1);
  }

  private String dfs(RopeTreeNode node) {
    if (node == null) {
      return "";
    }

    if (node.left == null && node.right == null) {
      return node.val;
    }

    return dfs(node.left) + dfs(node.right);
  }
}
```
# [LeetCode_2696_1_删除子串后的字符串最小长度](https://leetcode.cn/problems/minimum-string-length-after-removing-substrings/)
## 解法
### 思路
使用String的contains函数进行模拟
### 代码
```java
class Solution {
  public int minLength(String s) {
    final String ab = "AB", cd = "CD";
    while (s.contains(ab) || s.contains(cd)) {
      if (s.contains(ab)) {
        s = s.substring(0, s.indexOf(ab)) + s.substring(s.indexOf(ab) + 2);
      } else {
        s = s.substring(0, s.indexOf(cd)) + s.substring(s.indexOf(cd) + 2);
      }
    }

    return s.length();
  }
}
```
# [LeetCode_1377_T秒后青蛙的位置](https://leetcode.cn/problems/frog-position-after-t-seconds/)
## 解法
### 思路
dfs
- 如果在t秒到达target，返回1
- 如果没有路径可走，返回0
- 否则返回所有可能路径返回值的总和，并除以当前可达的路径个数
### 代码
```java
class Solution {
  public double frogPosition(int n, int[][] edges, int t, int target) {
    List<Integer>[] graph = new ArrayList[n];
    for (int i = 0; i < graph.length; i++) {
      graph[i] = new ArrayList<>();
    }

    for (int[] edge : edges) {
      graph[edge[0] - 1].add(edge[1] - 1);
      graph[edge[1] - 1].add(edge[0] - 1);
    }

    boolean[] memo = new boolean[n];

    return dfs(0, t, graph, memo, target);
  }

  private double dfs(int index, int t, List<Integer>[] graph, boolean[] memo, int target) {
    int count = graph[index].size();
    if (t == 0 || (count -= (index == 0 ? 0 : 1)) == 0) {
      return index == target - 1 ? 1 : 0;
    }

    memo[index] = true;
    double result = 0;
    for (Integer nextIndex : graph[index]) {
      if (memo[nextIndex]) {
        continue;
      }

      result += dfs(nextIndex, t - 1, graph, memo, target);
    }

    return result / count;
  }
}
```
# [LeetCode_1091_二进制矩阵的最短路径](https://leetcode.cn/problems/shortest-path-in-binary-matrix/)
## 失败解法
### 原因
超时
### 思路
dfs
### 代码
```java
class Solution {
    private int n;
    private boolean[][] memo;
    private int[][] grid;
    private int[][] dirs = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    public int shortestPathBinaryMatrix(int[][] grid) {
        this.n = grid.length;
        this.memo = new boolean[n][n];
        this.grid = grid;
        if (!isValid(0, 0)) {
            return -1;
        }

        memo[0][0] = true;
        return backTrack(0, 0);
    }

    private int backTrack(int x, int y) {
        if (x == n - 1 && y == n - 1) {
            return 1;
        }

        int ans = Integer.MAX_VALUE;
        for (int[] dir : dirs) {
            int nx = x + dir[0], ny = y + dir[1];
            if (!isValid(nx, ny)) {
                continue;
            }

            memo[nx][ny] = true;
            int path = backTrack(nx, ny);
            memo[nx][ny] = false;

            if (path == -1) {
                continue;
            }

            ans = Math.min(path, ans);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans + 1;
    }

    private boolean isValid(int x, int y) {
        return in(x, y) && !memo[x][y] && grid[x][y] == 0;
    }

    private boolean in(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }
}
```
## 解法
### 思路
bfs
### 代码
```java
class Solution {
  private int[][] dirs = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
  private int n;
  private boolean[][] memo;
  private int[][] grid;
  public int shortestPathBinaryMatrix(int[][] grid) {
    this.memo = new boolean[n][n];
    this.grid = grid;
    this.n = grid.length;
    if (isNotValid(0, 0)) {
      return -1;
    }

    Queue<int[]> queue = new ArrayDeque<>();
    queue.offer(new int[]{0, 0});

    int path = 0;
    while (!queue.isEmpty()) {
      int cnt = queue.size();
      while (cnt-- > 0) {
        int[] arr = queue.poll();
        int x = arr[0], y = arr[1];
        for (int[] dir : dirs) {
          int nx = x + dir[0], ny = y + dir[1];

          if (nx == n - 1 && ny == n - 1) {
            return path + 1;
          }

          if (isNotValid(nx, ny)) {
            continue;
          }

          queue.offer(new int[]{nx, ny});
          grid[nx][ny] = 1;
        }
      }
      path++;
    }

    return -1;
  }

  private boolean isNotValid(int x, int y) {
    return !in(x, y) || grid[x][y] != 0;
  }

  private boolean in(int x, int y) {
    return x >= 0 && x < n && y >= 0 && y < n;
  }
}
```