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
# [LeetCode_2240_1_买钢笔和铅笔的方案数](https://leetcode.cn/problems/number-of-ways-to-buy-pens-and-pencils/)
## 失败解法
### 原因
时间复杂度过高，超时
### 思路
2层for循环模拟
### 代码
```java
class Solution {
    public long waysToBuyPensPencils(int total, int cost1, int cost2) {
        int ans = 0;
        for (int c1 = 0; c1 * cost1 <= total; c1++) {
            int cost = c1 * cost1;
            for (int c2 = 0; c2 * cost2 <= total - cost; c2++) {
                ans++;
            }
        }
        return ans;
    }
}
```
## 解法
### 思路
- 在失败解法的基础上，将内层循环的时间复杂度降低
- 很容易想到通过二分查找的方法找到铅笔的个数上限
### 代码
```java
class Solution {
    public long waysToBuyPensPencils(int total, int cost1, int cost2) {
        long sum = 0;
        for (long i = 0; i * cost1 <= total; i++) {
            sum += binarySearch(total - i * cost1, cost2) + 1;
        }
        return sum;
    }

    private long binarySearch(long target, int cost2) {
        long head = 0, tail = target, ans = -1;
        while (head <= tail) {
            long mid = head + (tail - head) / 2, cur = mid * cost2;

            if (cur <= target) {
                head = mid + 1;
                ans = mid;
            } else {
                tail = mid - 1;
            }
        }

        return ans;
    }
}
```
## 解法二
### 思路
- 失败解法慢的原因是一个个的去判断每个个数的可能性
- 解法一在解法1的基础上，将铅笔个数的个数可能性通过二分查找最大个数给缩短了
- 基于解法2的思路，其实稍微进一步想一下就可以发现，一个除法就可以得到这个最大个数
- 而2种物品的个数都可以通过除法来处理，于是问题就变成了算出钢笔的最大个数n
- 从0开始遍历n+1个个数可能，来累加铅笔对应的个数即可
- 时间复杂度降低到O(N)
### 代码
```java
class Solution {
    public long waysToBuyPensPencils(int total, int cost1, int cost2) {
        if (cost1 < cost2) {
            return waysToBuyPensPencils(total, cost2, cost1);
        }
        
        long n = total / cost1, sum = 0;
        for (long i = 0; i <= n; i++) {
            long rest = total - i * cost1;
            sum += rest / cost2 + 1;
        }
        return sum;
    }
}
```