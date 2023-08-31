# [LeetCode_1654_到家的最少跳跃次数](https://leetcode.cn/problems/minimum-jumps-to-reach-home/)
## 解法
### 思路
- 使用bfs+记事本剪枝来寻找最小路径
- 关于右边界的计算证明 todo
### 代码
```java
class Solution {
    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        int max = a >= b ? x + b : 6000;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});
        int ans = 0;
        Set<Integer> set = new HashSet<>();
        for (int num : forbidden) {
            set.add(num);
        }

        boolean[][] memo = new boolean[max + 1][2];
        memo[0][0] = true;
        while (!queue.isEmpty()) {
            int cnt = queue.size();
            while (cnt-- > 0) {
                int[] arr = queue.poll();
                if (arr == null) {
                    continue;
                }

                int cur = arr[0], dir = arr[1], pre = cur - b, next = cur + a;

                if (cur == x) {
                    return ans;
                }

                if (dir == 0 && pre >= 0 && !set.contains(pre) && !memo[pre][1]) {
                    memo[pre][1] = true;
                    queue.offer(new int[]{pre, 1});
                }

                if (next <= max && !set.contains(next) && !memo[next][0]) {
                    memo[next][0] = true;
                    queue.offer(new int[]{next, 0});
                }
            }
            ans++;
        }

        return -1;
    }
}
```
# [LeetCode_1761_一个图中连通三元组的最小度数](https://leetcode.cn/problems/minimum-degree-of-a-connected-trio-in-a-graph/)
## 解法
### 思路
- 使用邻接表`g`记录边
- 使用`degrees`数组记录入度个数
- 初遍历`edges`始化`g`和`degrees`
- 3层遍历`edges`，找到3元组，然后计算度数，暂存最小值
- 遍历结束返回结果值
### 代码
```java
class Solution {
    public int minTrioDegree(int n, int[][] edges) {
        int[][] g = new int[n + 1][n + 1];
        int[] degrees = new int[n + 1];
        for (int[] edge : edges) {
            int x = Math.min(edge[0], edge[1]), y = Math.max(edge[0], edge[1]);
            g[x][y] = 1;
            degrees[x]++;
            degrees[y]++;
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (g[i][j] == 0) {
                    continue;
                }

                for (int k = j + 1; k <= n; k++) {
                    if (g[i][k] == 0 || g[j][k] == 0) {
                        continue;
                    }
                    
                    ans = Math.min(ans, degrees[i] + degrees[j] + degrees[k] - 6);
                }
            }
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
```