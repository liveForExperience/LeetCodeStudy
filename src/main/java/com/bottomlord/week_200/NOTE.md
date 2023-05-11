# [LeetCode_1263_推箱子](https://leetcode.cn/problems/minimum-moves-to-move-a-box-to-their-target-location/)
## 解法
### 思路
bfs
- 因为题目中不仅包括箱子，还包括人，所以bfs的驱动元素需要同时包含箱子和人的坐标信息
- bfs过程中通过一个记事本数组来记录当前箱子和人的坐标状态时，经过的最小步数，这个步数基于bfs过程来推导，其实也就是在做动态规划
- bfs过程中，内层循环的时候，在循环体之外再维护一个队列，这个队列用于存储箱子移动后的人和箱子的状态，而同时内层循环的时候，不仅有箱子动的状态，也有人动但箱子不动的状态，这种状态就在内层循环掉
- 循环过程中对记事本进行更新，为了区分是否搜索过，初始化这个记事本的所有元素为int的最大值
- 在内层循环过程中，如果箱子的坐标到达了`T`的位置，那么就返回记事本记录的值即可
### 代码
```java
class Solution {
    private int r, c;
    private int[][] memo;
    private char[][] grid;

    public int minPushBox(char[][] grid) {
        this.r = grid.length;
        this.c = grid[0].length;
        this.memo = new int[c * r][c * r];
        this.grid = grid;

        int sx = -1, sy = -1, bx = -1, by = -1;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 'S') {
                    sx = i;
                    sy = j;
                } else if (grid[i][j] == 'B') {
                    bx = i;
                    by = j;
                }
            }
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{sx * c + sy, bx * c + by});
        int[] dirs = new int[]{0, -1, 0, 1, 0};
        for (int[] arr : memo) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }

        memo[sx * c + sy][bx * c + by] = 0;

        while (!queue.isEmpty()) {
            Queue<int[]> next = new ArrayDeque<>();
            while (!queue.isEmpty()) {
                int[] arr = queue.poll();
                int s1 = arr[0], b1 = arr[1],
                    sx1 = s1 / c, sy1 = s1 % c,
                    bx1 = b1 / c, by1 = b1 % c;

                if (grid[bx1][by1] == 'T') {
                    return memo[s1][b1];
                }

                for (int i = 0; i < 4; i++) {
                    int sx2 = sx1 + dirs[i], sy2 = sy1 + dirs[i + 1], s2 = sx2 * c + sy2;

                    if (!ok(sx2, sy2)) {
                        continue;
                    }

                    if (sx2 == bx1 && sy2 == by1) {
                        int bx2 = bx1 + dirs[i], by2 = by1 + dirs[i + 1], b2 = bx2 * c + by2;
                        if (!ok(bx2, by2) || memo[s2][b2] <= memo[s1][b1] + 1) {
                            continue;
                        }

                        memo[s2][b2] = memo[s1][b1] + 1;
                        next.offer(new int[]{s2, b2});
                    } else {
                        if (memo[s2][b1] <= memo[s1][b1]) {
                            continue;
                        }

                        memo[s2][b1] = memo[s1][b1];
                        queue.offer(new int[]{s2, b1});
                    }
                }
            }
            
            queue = next;
        }

        return -1;
    }

    private boolean  ok(int x, int y) {
        return x >= 0 && x < r && y >= 0 && y < c && grid[x][y] != '#';
    }
}
```
# [LeetCode_967_连续差相同的数字]()
## 解法
### 思路
dfs，需要注意k为0的情况，不要重复搜索，其他就是简单的深度优先搜索即可
### 代码
```java
class Solution {
    private int k, n;
    public int[] numsSameConsecDiff(int n, int k) {
        this.k = k;
        this.n = n;
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            dfs(1, i, i, list);
        }
        
        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    private void dfs(int index, int num, int pre, List<Integer> list) {
        if (index >= n) {
            list.add(num);
            return;
        }
        
        if (pre + k <= 9) {
            dfs(index + 1, num * 10 + pre + k, pre + k, list);
        }
        
        if (pre - k >= 0 && pre - k != pre + k) {
            dfs(index + 1, num * 10 + pre - k, pre - k, list);
        }
    }
}
```
# [LeetCode_1015_可被k整除的最小整数](https://leetcode.cn/problems/smallest-integer-divisible-by-k/)
## 解法
### 思路
- 因为2和5肯定不能整除个位是1的数字，所以k能被这2个数整除，都可以直接返回-1
- 通过模运算的交换律：`(a + b) % k` = `((a % k) + (b % k)) % k`，`(a * b) % k` = `((a % k) * (b % k)) % k` 
- 可以得到：`(x * 10 + 1) % k` = `((x % k) * (10 % k) + 1 % k) % k` 
- 所以在遍历1到n个1的过程中，可以通过`((x % k) * (10 % k) + 1 % k) % k`来进行值的转换
- 使用一个set来记录是否存在已经遇到过的余数，因为如果没有除尽的情况下，遇到相同的余数，那就说明会进入一个死循环，所以直接返回-1
- 循环更新x，直到x能被k整除，或者遇到相同的余数为止
### 代码
```java
class Solution {
    public int smallestRepunitDivByK(int k) {
        if (k % 2 == 0 || k % 5 == 0) {
            return -1;
        }
        
        Set<Integer> set = new HashSet<>();
        int x = 1;
        for (int i = 1; ; i++) {
            if (x % k == 0) {
                return i;
            }

            x = (x % k) * 10 + 1;
            if (!set.add(x)) {
                return -1;
            }
        }
    }
}

```
# [LeetCode_971_翻转二叉树以匹配先序遍历](https://leetcode.cn/problems/flip-binary-tree-to-match-preorder-traversal/)
## 解法
### 思路
- 先序遍历二叉树，如果下一个值与预期的下一个值不同，就翻转并记录
- 如果当前值与预期值不同就记录-1，并返回
### 代码
```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int index = 0;
    private int[] voyage;
    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        this.voyage = voyage;
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        return list;
    }

    private void dfs(TreeNode node, List<Integer> list) {
        if (index >= voyage.length) {
            return;
        }
        
        if (node == null) {
            return;
        }

        if (list.size() == 1 && list.get(0) == -1) {
            return;
        }

        int val = node.val;
        if (val != voyage[index++]) {
            list.clear();
            list.add(-1);
            return;
        }

        int next = 0;
        if (node.left != null) {
            next = node.left.val;
        } else if (node.right != null) {
            next = node.right.val;
        }

        if (index >= voyage.length || next == 0) {
            return;
        }
        
        if (next != voyage[index]) {
            list.add(node.val);
            dfs(node.right, list);
            dfs(node.left, list);
        } else {
            dfs(node.left, list);
            dfs(node.right, list);
        }
    }
}
```