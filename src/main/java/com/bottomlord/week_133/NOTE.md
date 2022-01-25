# [LeetCode_2045_ 到达目的地的第二短时间](https://leetcode-cn.com/problems/second-minimum-time-to-reach-destination/)
## 解法
### 思路
- 通过edge数组构建一个有向边的映射集合
- bfs遍历求得严格次短路径
- 遍历次短路径，累加time，并判断当前time是否会遇到需要等待红绿灯的情况，如果有就要加上等红灯的时间
  - 是否等红灯，就是当前累加值与红绿灯完整周期之间取余，如果大于半个周期，说明当前时间落入了红灯区域，需要等待
  - 等待的时间，就是完整周期减去取余的是时间
- 遍历结束，得到最终结果
### 代码
```java
class Solution {
    public int secondMinimum(int n, int[][] edges, int time, int change) {
        List<Integer>[] graph = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        int[][] path = new int[n + 1][2];
         for (int i = 0; i <= n; i++) {
            Arrays.fill(path[i], Integer.MAX_VALUE);
        }
        path[1][0] = 0;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{1, 0});
        while (path[n][1] == Integer.MAX_VALUE) {
            int[] arr = queue.poll();
            int index = arr[0], step = arr[1];

            for (Integer next : graph[index]) {
                if (step + 1 < path[next][0]) {
                    path[next][0] = step + 1;
                    queue.offer(new int[]{next, step + 1});
                } else if (step + 1 > path[next][0] && step + 1 < path[next][1]) {
                    path[next][1] = step + 1;
                    queue.offer(new int[]{next, step + 1});
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < path[n][1]; i++) {
            if (ans % (2 * change) >= change) {
                ans = ans + (2 * change - ans % (2 * change));
            }
            ans += time;
        }
        return ans;
    }
}
```
# [LeetCode_1688_比赛中的配对次数](https://leetcode-cn.com/problems/count-of-matches-in-tournament/)
## 解法
### 思路
模拟
- 循环变更n的同时计算出当前循环周期内的配对次数
- 循环退出条件为`n==1`
- 在循环过程中累加配对值，循环结束后返回
### 代码
```java
class Solution {
    public int numberOfMatches(int n) {
        int count = 0;
        while (n > 1) {
            count += n / 2;
            n = n % 2 == 1 ? n / 2 + 1 : n / 2;
        }
        return count;
    }
}
```
# [LeetCode_2124_检查是否所有A都在B之前](https://leetcode-cn.com/problems/check-if-all-as-appears-before-all-bs/)
## 解法
### 思路
- 遍历字符数组，找到可能的ab分界点，记录当前找到的状态为true
- 继续遍历，如果状态为true的同时，还碰到a则返回false，否则遍历结束返回true
### 代码
```java
class Solution {
    public boolean checkString(String s) {
        char[] cs = s.toCharArray();
        boolean half = false;
        for (char c : cs) {
            if (!half && c == 'b') {
                half = true;
                continue;
            }
            
            if (half && c == 'a') {
                return false;
            }
        }
        
        return true;
    }
}
```