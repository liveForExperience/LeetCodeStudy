# [LeetCode_1376_通知所有员工所需的时间](https://leetcode.cn/problems/time-needed-to-inform-all-employees/)
## 解法
### 思路
自底向上的dfs
- map生成上级与下级列表之间的映射关系
- 递归方式实现bfs，每一层递归的主逻辑是为获取当前manager发送通知的最大耗时：`自身的informTime + max(下属及下属通知的总耗时)`
### 代码
```java
class Solution {
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTimes) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < manager.length; i++) {
            map.computeIfAbsent(manager[i], x -> new ArrayList<>()).add(i);
        }
        
        return bfs(headID, map, informTimes);
    }
    
    private int bfs(int manager, Map<Integer, List<Integer>> map, int[] informTimes) {
        int curTime = informTimes[manager], cost = 0;
        for (Integer emp : map.getOrDefault(manager, new ArrayList<>())) {
            cost = Math.max(cost, bfs(emp, map, informTimes));
        }
        return curTime + cost;
    }
}
```
## 解法二
### 思路
自顶向下的dfs
- 初始化一个time数组
- 遍历n个员工，递归过程主要是通过dfs计算出从顶到到达当前层的耗时cost
- 当前层的总耗时就是`cost + informTime[cur]`
- 遍历结束后，返回最大值，这个最大值可以在遍历过程中通过临时变量动态维护
### 代码
```java
class Solution {
    public int numOfMinutes(int n, int headID, int[] managers, int[] informTimes) {
        int[] costs = new int[n];
        Arrays.fill(costs, -1);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dfs(i, costs, informTimes, managers));
        }
        return ans;
    }

    private int dfs(int cur, int[] costs, int[] informTimes, int[] managers) {
        int manager = managers[cur];

        if (manager == -1) {
            return costs[cur] = informTimes[cur];
        }

        if (costs[manager] == -1) {
            costs[manager] = dfs(manager, costs, informTimes, managers);
        }

        return costs[cur] = costs[manager] + informTimes[cur];
    }
}
```