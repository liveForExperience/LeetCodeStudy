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
# [LeetCode_1921_消灭怪物的最大数量](https://leetcode.cn/problems/eliminate-maximum-number-of-monsters/)
## 解法
### 思路
- 求出怪兽到达城市的时间，对时间进行排序，排序规则非降序，然后遍历射击的回合数，从0开始，判断每个射击回合，是否可以将当前回合最先能到达的怪兽在到达城市前消灭
- 初始化一个数组`arr`，用来存储怪物到达城市的回合数
    - 一开始想到的计算公式：`dist[i] / speed[i]` 
    - 因为java在进行整数除法运算时会向下取整，而如果怪物到达的时间是`5 / 2 = 2.5`回合，那么java整数运算求出的就是2回合，但其实怪物是在第3个回合的一半时候才会到达城市，所以需要做向上取整的处理
    - 新的公式：`(dist[i] - 1) / speed[i] + 1`
      - `+ 1`就是向上取整
      - `dist[i] - 1`是为了处理整除的情况，因为如果考虑到整除，那么不会发生向下取整，而`-1`对于向下取整的运算没有影响，但可以使整除的状况也发生向下取整的运算
- 对`arr`进行排序
- 遍历`arr`，遍历坐标`index`范围是`[0, n)`，此时的`index`等价于射击回合数，只要射击回合早于怪兽到达的回合，就可以消灭怪兽，所以只要`index < arr[i]`，那么就可以累加答案，如果全部符合，那么答案就是`n`
### 代码
```java
class Solution {
     public int eliminateMaximum(int[] dist, int[] speed) {
        int n = dist.length;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (dist[i] - 1) / speed[i] + 1;
        }

        Arrays.sort(arr);
        for (int i = 0; i < n; i++) {
            if (i >= arr[i]) {
                return i;
            }
        }
        
        return n;
    }
}
```
## 解法二
### 思路
- 这个游戏最多有n个回合，每个回合玩家都可以消灭一个怪兽，所以消灭个数随回合数的增长是以`f(x) = x`函数线性增长的。
- 按照这个思路，可以将整个游戏划分成`n+1`个区间，生成`arr`数组，数组元素代表从0开始的回合数中怪兽出现的个数
- 然后将通过遍历怪兽的距离和速度，算出怪兽到达的回合数，在`arr`中对应坐标位置累加当前怪兽个数
- 如上这个过程的时间复杂度是`O(N)`
- 然后遍历游戏的`n+1`个回合，从0开始，在每个回合中累加1个消灭怪兽的个数`kills`，同时从`arr`数组中取出当前回合中到达的怪兽个数
  - 如果`kills(消灭个数)`不小于`arr[i](怪兽个数)`，则更新`kills -= arr[i]`
  - 如果`kills`小于`arr[i]`，则返回当前回合`i`，因为`i`从0开始，所以答案应该是`i+1`
- 如果成功遍历结束，说明每一个回合，玩家都可以消灭最近的怪兽，则答案为`n`
### 代码
```java
class Solution {
     public int eliminateMaximum(int[] dist, int[] speed) {
        int n = dist.length, kill = 0;
        
        int[] arr = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int round = (dist[i] - 1) / speed[i] + 1;
            if (round > n) {
                continue;
            }

            arr[round]++;
        }

        for (int i = 0; i <= n; i++) {
            kill -= arr[i];
            if (kill < 0) {
                return i;
            }
            kill++;
        }

        return n;
    }
}
```