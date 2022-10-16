# [LeetCode_749_隔离病毒](https://leetcode.cn/problems/contain-virus/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_801_使序列递增的最小交换次数](https://leetcode.cn/problems/minimum-swaps-to-make-sequences-increasing/)
## 解法
### 思路
动态规划：
- dp[i][j]：
  - dp[i][0]:到i位置为止，不交换时保持升序的最少次数
  - dp[i][1]:到i位置为止，交换时保持升序的最少次数
- 状态转移方程:
  - `nums1[i] == nums2[i]`:
    - `dp[i][0] = dp[i][1] = min(dp[i - 1][0], dp[i - 1][1])`
  - `nums1[i] > nums1[i - 1] && nums2[i] > nums2[i - 1]`:
    - 不交换:`dp[i][0] = min(dp[i][0], dp[i - 1][0])`
    - 交换两次:`dp[i][1] = min(dp[i][1], dp[i - 1][1] + 1)`
  - `nums1[i] > nums2[i - 1] && nums2[i] > nums1[i - 1]`:
    - 交换一次:
      - `dp[i][0] = min(dp[i][0], dp[i - 1][1])`
      - `dp[i][1] = min(dp[i][1], dp[i - 1][0] + 1)`
- base case
  - dp[0][0] = 0
  - dp[0][1] = 1
### 代码
```java
class Solution {
  public int minSwap(int[] nums1, int[] nums2) {
    int n = nums1.length;
    int[][] dp = new int[n][2];
    dp[0][1] = 1;

    for (int i = 1; i < n; i++) {
      int a1 = nums1[i], a2 = nums1[i - 1],
              b1 = nums2[i], b2 = nums2[i - 1];

      if ((a1 > a2 && b1 > b2) && (a1 > b2 && b1 > a2)) {
        dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1]);
        dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][1]) + 1;
      } else if (a1 > a2 && b1 > b2) {
        dp[i][0] = dp[i - 1][0];
        dp[i][1] = dp[i - 1][1] + 1;
      } else {
        dp[i][0] = dp[i - 1][1];
        dp[i][1] = dp[i - 1][0] + 1;
      }
    }

    return Math.min(dp[n - 1][0], dp[n - 1][1]);
  }
}
```
# [LeetCode_886_可能的二分法](https://leetcode.cn/problems/possible-bipartition/)
## 解法
### 思路
dfs
- 根据dislike生成邻接表，用于记录不喜欢的关系
- 初始化colors数组，用于记录涂色内容，涂色是为了区分分组情况
- 遍历所有人，根据涂色情况判断是否要dfs进行涂色，默认都涂色为1，dislike的都涂色为2
- dfs过程中
  - 对当前人进行涂色
  - 遍历邻接表中的不喜欢的人，如果该人已经涂色，且是和当前人相同颜色，则说明无法分组，返回false
  - 如果当前不喜欢的人没有涂色，那么就dfs涂色，并根据返回的情况判断当前涂色内容是否能成功，如果不成功也返回false
  - 遍历结束，如果没有直接返回false，就返回true，说明涂色ok
### 代码
```java
class Solution {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] dislike : dislikes) {
            graph.computeIfAbsent(dislike[0], x -> new ArrayList<>()).add(dislike[1]);
            graph.computeIfAbsent(dislike[1], x -> new ArrayList<>()).add(dislike[0]);
        }

        int[] colors = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            if (colors[i] != 0) {
                continue;
            }

            if (!dfs(i, 1, graph, colors)) {
                return false;
            }
        }

        return true;
    }

    private boolean dfs(int index, int color, Map<Integer, List<Integer>> graph, int[] colors) {
        colors[index] = color;

        for (Integer other : graph.getOrDefault(index, new ArrayList<>())) {
            if (colors[other] == color) {
                return false;
            }

            if (colors[other] == 0 && !dfs(other, 3 - color, graph, colors)) {
                return false;
            }
        }

        return true;
    }
}
```
## 解法二
### 思路
并查集
- 使用并查集，将每个人对应不喜欢的人加入到并查集中
- 如果互为不喜欢的人在同一个并查集中，那么就返回false
### 代码
```java
class Solution {
public boolean possibleBipartition(int n, int[][] dislikes) {
        Map<Integer, List<Integer>> g = new HashMap<>();
        for (int[] dislike : dislikes) {
            g.computeIfAbsent(dislike[0], x -> new ArrayList<>()).add(dislike[1]);
            g.computeIfAbsent(dislike[1], x -> new ArrayList<>()).add(dislike[0]);
        }

        UnionFind unionFind = new UnionFind(n);

        for (Map.Entry<Integer, List<Integer>> entry : g.entrySet()) {
            Integer key = entry.getKey();
            List<Integer> list = entry.getValue();

            for (Integer value : list) {
                if (unionFind.find(key) == unionFind.find(value)) {
                    return false;
                }
                
                unionFind.union(value, list.get(0));
            }
        }
        
        return true;
    }

    private class UnionFind {
        private int[] parent;

        public UnionFind(int n) {
            this.parent = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                this.parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }

            return parent[x];
        }

        public void union(int x, int y) {
            parent[x] = find(parent[y]);
        }
    }
}
```