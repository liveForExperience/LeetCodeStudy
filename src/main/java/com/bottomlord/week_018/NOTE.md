# LeetCode_1110_删点成林
## 题目
给出二叉树的根节点 root，树上每个节点都有一个不同的值。

如果节点值在 to_delete 中出现，我们就把该节点从树上删去，最后得到一个森林（一些不相交的树构成的集合）。

返回森林中的每棵树。你可以按任意顺序组织答案。

示例：
```
输入：root = [1,2,3,4,5,6,7], to_delete = [3,5]
输出：[[1,2,null,4],[6],[7]]
```
提示：
```
树中的节点数最大为 1000。
每个节点都有一个介于 1 到 1000 之间的值，且各不相同。
to_delete.length <= 1000
to_delete 包含一些从 1 到 1000、各不相同的值。
```
## 解法
### 思路
- 把to_delete数组转换成set，方便查找
- dfs遍历二叉树
    - 如果节点为null，返回null
    - 否则就继续递归搜索，并在当前层用左右子树指针接收递归返回的结果
    - 这里必须是后序遍历，先递归到叶子节点再开始判断，否则子树的一些需要删除的节点就没法被删除了，结果会有问题
    - 如果节点值为to_delete中的值，返回null，并将左右非空子树作为根节点，放入结果list中
- 最后返回结果list，但返回前需要判断根节点是否被删除，如果返回是null，必需过滤掉
### 代码
```java
class Solution {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> ans = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (Integer val : to_delete) {
            set.add(val);
        }
        
        TreeNode node = dfs(root, set, ans);
        if (node != null) {
            ans.add(node);
        }
        
        return ans;
    }

    private TreeNode dfs(TreeNode node, Set<Integer> toDelete, List<TreeNode> ans) {
        if (node == null) {
            return null;
        }

        node.left = dfs(node.left, toDelete, ans);
        node.right = dfs(node.right, toDelete, ans);

        if (toDelete.contains(node.val)) {
            if (node.left != null) {
                ans.add(node.left);
            }

            if (node.right != null) {
                ans.add(node.right);
            }

            return null;
        }
        return node;
    }
}
```
# LeetCode_983_最低票价
## 题目
在一个火车旅行很受欢迎的国度，你提前一年计划了一些火车旅行。在接下来的一年里，你要旅行的日子将以一个名为 days 的数组给出。每一项是一个从 1 到 365 的整数。

火车票有三种不同的销售方式：
```
一张为期一天的通行证售价为 costs[0] 美元；
一张为期七天的通行证售价为 costs[1] 美元；
一张为期三十天的通行证售价为 costs[2] 美元。
通行证允许数天无限制的旅行。 例如，如果我们在第 2 天获得一张为期 7 天的通行证，那么我们可以连着旅行 7 天：第 2 天、第 3 天、第 4 天、第 5 天、第 6 天、第 7 天和第 8 天。
```
返回你想要完成在给定的列表 days 中列出的每一天的旅行所需要的最低消费。

示例 1：
```
输入：days = [1,4,6,7,8,20], costs = [2,7,15]
输出：11
解释： 
例如，这里有一种购买通行证的方法，可以让你完成你的旅行计划：
在第 1 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 1 天生效。
在第 3 天，你花了 costs[1] = $7 买了一张为期 7 天的通行证，它将在第 3, 4, ..., 9 天生效。
在第 20 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 20 天生效。
你总共花了 $11，并完成了你计划的每一天旅行。
```
示例 2：
```
输入：days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
输出：17
解释：
例如，这里有一种购买通行证的方法，可以让你完成你的旅行计划： 
在第 1 天，你花了 costs[2] = $15 买了一张为期 30 天的通行证，它将在第 1, 2, ..., 30 天生效。
在第 31 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 31 天生效。 
你总共花了 $17，并完成了你计划的每一天旅行。
```
提示：
```
1 <= days.length <= 365
1 <= days[i] <= 365
days 按顺序严格递增
costs.length == 3
1 <= costs[i] <= 1000
```
## 解法
### 思路
动态规划：
- dp(i)：第i天到最后一天的最小票价总和
- 状态转移方程：dp(i) = Math.min(dp(i + 1) + cost[0], dp(i + 7) + cost[1], dp(i + 30) + cost[2])
- 递归返回最后的结果
- 需要记忆化搜索，否则会超时
### 代码
```java
class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        Set<Integer> set = new HashSet<>();
        for (int day : days) {
            set.add(day);
        }
        return dp(0, costs, set, new Integer[366]);
    }

    private int dp(int day, int[] costs, Set<Integer> set, Integer[] memo) {
        if (day > 365) {
            return 0;
        }

        if (memo[day] != null) {
            return memo[day];
        }
        
        int ans;
        if (set.contains(day)) {
            ans = Math.min(dp(day + 1, costs, set, memo) + costs[0], dp(day + 7, costs, set, memo) + costs[1]);
            ans = Math.min(ans, dp(day + 30, costs, set, memo) + costs[2]);
        } else {
            ans = dp(day + 1, costs, set, memo);
        }
        
        memo[day] = ans;
        return ans;
    }
}
```
## 优化代码
### 思路
- 在动态规划的过程中，真正需要计算的是days中的元素代表的日期，所以不需要像解法一中一样碰到days中没有的日期就进行+1的递归，直接可以跳到下一个days的日期。
- dp[i]表示从i天到最后旅行计划的最小花费
- 状态转移方程是：dp(i) = min(dp(j1) + costs(0), dp(j7) + cost(1), dp(j30) + cost(2))
    - dp(j1)代表最小的下标满足 days[j1] > days[i] + 1
    - dp(j7)代表最小的下标满足 days[j7] > days[i] + 7
    - dp(j30)代表最小的下标满足 days[j30] > days[i] + 30
    - 也就意味着dp(i)是求三种(刚刚超过当前旅行日期的dp值 + 当前方案)的和中的最小值，我这个方案已经cover了这些需要旅行的日期了，那么下一个日期的最小值方案是多少？算一下，递归返回回来
- 递归到days的最后一天，计算3中票价中的最低价，然后返回过程中再不断与前一个日期的dp进行比较，直到返回到第一个元素，货的最小值。
### 代码
```java
class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        int[] durations = new int[]{1, 7, 30};
        return dp(0, days, costs, durations, new Integer[days.length]);
    }

    private int dp(int index, int[] days, int[] costs, int[] durations, Integer[] memo) {
        if (index >= days.length) {
            return 0;
        }

        if (memo[index] != null) {
            return memo[index];
        }
        
        int tmp = index, ans = Integer.MAX_VALUE;
        for (int k = 0; k < 3; k++) {
            while (tmp < days.length && days[tmp] < days[index] + durations[k]) {
                tmp++;
            }
            
            ans = Math.min(ans, dp(tmp, days, costs, durations, memo) + costs[k]);
        }
        
        memo[index] = ans;
        return ans;
    }
}
```
# LeetCode_454_四数相加II
## 题目
给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。

为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1 。

例如:
```
输入:
A = [ 1, 2]
B = [-2,-1]
C = [-1, 2]
D = [ 0, 2]

输出:2

解释:
两个元组如下:
1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
```
## 解法
### 思路
把4个数分成两部分：
- A和B数组嵌套循环算出结果，放入哈希表，并计数
- C和D数组嵌套循环，计算两个元素的和的负值，如果在散列表中找到相应的key，就累加value
- 循环结束返回累加值
### 代码
```java
class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : A) {
            for (int b : B) {
                int sum = a + b;
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }
        
        int ans = 0;
        for (int c : C) {
            for (int d : D) {
                int sum = -(c + d);
                ans += map.getOrDefault(sum, 0);
            }
        }
        
        return ans;
    }
}
```
# LeetCode_1140_石子游戏II
## 题目
亚历克斯和李继续他们的石子游戏。许多堆石子 排成一行，每堆都有正整数颗石子 piles[i]。游戏以谁手中的石子最多来决出胜负。

亚历克斯和李轮流进行，亚历克斯先开始。最初，M = 1。

在每个玩家的回合中，该玩家可以拿走剩下的 前 X 堆的所有石子，其中 1 <= X <= 2M。然后，令 M = max(M, X)。

游戏一直持续到所有石子都被拿走。

假设亚历克斯和李都发挥出最佳水平，返回亚历克斯可以得到的最大数量的石头。

示例：
```
输入：piles = [2,7,9,4,4]
输出：10
解释：
如果亚历克斯在开始时拿走一堆石子，李拿走两堆，接着亚历克斯也拿走两堆。在这种情况下，亚历克斯可以拿到 2 + 4 + 4 = 10 颗石子。 
如果亚历克斯在开始时拿走两堆石子，那么李就可以拿走剩下全部三堆石子。在这种情况下，亚历克斯可以拿到 2 + 7 = 9 颗石子。
所以我们返回更大的 10。 
```
提示：
```
1 <= piles.length <= 100
1 <= piles[i] <= 10 ^ 4
```
## 解法
### 思路
动态规划：
- `dp(i,j)`：代表还剩第i堆到最后一堆石子的情况下，M为j时，可以获得最大石子数
- base case：
    - i >= n：代表石子已经拿完的情况，返回0
    - i + 2M >= n：代表这是最理智情况下，最后一次可以拿石子的状态，返回sum[i]，代表所有剩下石子的总和
- 状态转移方程：dp(i, M) = 所有`1 <= x <= 2M`情况下，sum[i] - dp(i + x, max(x, M))的最大值
- 使用一个memo进行记忆化搜索
### 代码
```java
class Solution {
    public int stoneGameII(int[] piles) {
        int len = piles.length;
        int[] sum = new int[len];
        Integer[][] memo = new Integer[len + 1][len + 1];
        sum[len - 1] = piles[len - 1];
        for (int i = piles.length - 2; i >= 0; i--) {
            sum[i] = sum[i + 1] + piles[i];
        }
        return dp(0, 1, sum, len, memo);
    }

    private int dp(int i, int j, int[] sum, int len, Integer[][] memo) {
        if (i >= len) {
            return 0;
        }

        if (i + 2 * j >= len) {
            return sum[i];
        }
        
        if (memo[i][j] != null) {
            return memo[i][j];
        }

        int best = 0;
        for (int k = 1; k <= 2 * j; k++) {
            best = Math.max(best, sum[i] - dp(i + k, Math.max(k, j), sum, len, memo));
        }
        
        memo[i][j] = best;
        return best;
    }
}
```
# LeetCode_684_冗余连接
## 题目
在本问题中, 树指的是一个连通且无环的无向图。

输入一个图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。

结果图是一个以边组成的二维数组。每一个边的元素是一对[u, v] ，满足 u < v，表示连接顶点u 和v的无向图的边。

返回一条可以删去的边，使得结果图是一个有着N个节点的树。如果有多个答案，则返回二维数组中最后出现的边。答案边 [u, v] 应满足相同的格式 u < v。

示例 1：
```
输入: [[1,2], [1,3], [2,3]]
输出: [2,3]
解释: 给定的无向图为:
  1
 / \
2 - 3
```
示例 2：
```
输入: [[1,2], [2,3], [3,4], [1,4], [1,5]]
输出: [1,4]
解释: 给定的无向图为:
5 - 1 - 2
    |   |
    4 - 3
```
注意:
```
输入的二维数组大小在 3 到 1000。
二维数组中的整数在1到N之间，其中N是输入数组的大小。
```
## 解法
### 思路
并查集：
- 如果是树，所有节点都会指向同一个根节点
- 在遍历二维数组过程中，记录每一个并查集结果不一致的边，同时将两个节点进行合并
- 遍历结束，返回最后一个并查集结果不一致的
### 代码
```java
class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        DSU dsu = new DSU(edges.length);
        int[] ans = new int[2];

        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];

            if (dsu.find(x - 1) == dsu.find(y - 1)) {
                ans[0] = x;
                ans[1] = y;
            } else {
                dsu.union(x - 1, y - 1);
            }
        }

        return ans;
    }
}

class DSU {
    public int[] parent;

    public DSU(int N) {
        parent = new int[N];
        for (int i = 0; i < N; ++i)
            parent[i] = i;
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x, int y) {
        parent[find(x)] = find(y);
    }
}
```