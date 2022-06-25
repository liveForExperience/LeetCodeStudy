# [LeetCode_732_我的日程安排III](https://leetcode.cn/problems/my-calendar-iii/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_715_Range模块](https://leetcode.cn/problems/range-module/)
## 解法
### 思路
- 套用线段树模板(动态开点)
- val替换为cover，用于表述是否覆盖
- add和remove就用update来处理，val用true和false来代替
- query就用query来实现
### 代码
```java
class RangeModule {

    public RangeModule() {

    }

    public void addRange(int left, int right) {
        update(root, 1, N, left, right - 1, 1);
    }

    public boolean queryRange(int left, int right) {
        return query(root, 1, N, left, right - 1);
    }

    public void removeRange(int left, int right) {
        update(root, 1, N, left, right - 1, -1);
    }
    
    private int N = (int)1e9;
    private Node root = new Node();

    public void update(Node node, int start, int end, int l, int r, int val) {
        if (l <= start && r >= end) {
            node.cover = val == 1;
            node.add = val;
            return;
        }

        int mid = (start + end) >> 1;
        pushDown(node);
        
        if (l <= mid) {
            update(node.left, start, mid, l, r, val);
        }
        
        if (r > mid) {
            update(node.right, mid + 1, end, l, r, val);
        }
        
        pushUp(node);
    }

    public boolean query(Node node, int start, int end, int l, int r) {
        if (l <= start && r >= end) {
            return node.cover;
        }

        boolean ans = true;
        int mid = (start + end) >> 1;

        pushDown(node);

        if (l <= mid) {
            ans &= query(node.left, start, mid, l, r);
        }

        if (r > mid) {
            ans &= query(node.right, mid + 1, end, l, r);
        }

        return ans;
    }

    public void pushDown(Node node) {
        if (node.left == null) {
            node.left = new Node();
        }

        if (node.right == null) {
            node.right = new Node();
        }

        if (node.add == 0) {
            return;
        }

        node.left.cover = node.add == 1;
        node.right.cover = node.add == 1;
        node.left.add = node.add;
        node.right.add = node.add;
        node.add = 0;
    }

    public void pushUp(Node node) {
        node.cover = node.left.cover && node.right.cover;
    }

    class Node {
        private Node left, right;
        private boolean cover;
        private int add;
    }
}
```
# [LeetCode_513_找树左下角的值](https://leetcode.cn/problems/find-bottom-left-tree-value/)
## 解法
### 思路
bfs
### 代码
```java
class Solution {
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int ans = -1;
        while (!queue.isEmpty()) {
            int count = queue.size();
            boolean start = true;
            while (count-- > 0) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }
                
                if (start) {
                    ans = node.val;
                    start = false;
                }
                
                if (node.left != null) {
                    queue.offer(node.left);
                }
                
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        
        return ans;
    }
}
```
# [LeetCode_offerII91_粉刷房子](https://leetcode.cn/problems/JEj789/)
## 解法
### 思路
动态规划：
- dp[i][0 - 3]：从0到i花费最小，且i为某颜色的最小花费值
- 状态转移方程：
  - dp[i][0] = min(dp[i - 1][1], dp[i - 1][2]) + cost[0];
  - dp[i][1] = min(dp[i - 1][0], dp[i - 1][2]) + cost[1];
  - dp[i][2] = min(dp[i - 1][0], dp[i - 1][1]) + cost[2];
- 结果：min(dp[n][0], dp[n][1], dp[n][2])
### 代码
```java
class Solution {
    public int minCost(int[][] costs) {
        int n = costs.length;
        int[][] dp = new int[n + 1][3];
        for (int i = 0; i < n; i++) {
            int[] cost = costs[i];
            dp[i + 1][0] = Math.min(dp[i][1], dp[i][2]) + cost[0];
            dp[i + 1][1] = Math.min(dp[i][0], dp[i][2]) + cost[1];
            dp[i + 1][2] = Math.min(dp[i][0], dp[i][1]) + cost[2];
        }

        return Math.min(Math.min(dp[n][0], dp[n][1]), dp[n][2]);
    }
}
```