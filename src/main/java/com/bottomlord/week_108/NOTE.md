# [LeetCode_743_网络延迟时间](https://leetcode-cn.com/problems/network-delay-time/)
## 解法
### 思路
bfs
- 通过map构建有向图，图内容包含边的出度顶点和耗时
- 初始化一个map，用来记录到达某个顶点的最小耗时映射关系
- 通过优先级队列进行bfs搜索，排序规则为耗时越短的顶点越靠前，也就是以耗时为标准的小顶堆，初始放入k顶点，耗时定为0
- 每次搜索到一个顶点，就放入map中进行记录，然后基于当前搜索到的顶点，到图中找到出度，进行下一步的bfs，在放入队列时，将当前顶点的耗时与图中记录的耗时相加，作为该出度的整体耗时，因为是小顶堆，所以该耗时一定是所有路径中最小的
- 在搜索过程中，因为只要记录最小的耗时，所以将搜索到的顶点到map中进行判断，如果已经记录过，就抛弃
- 搜索结束后，查看map中的元素个数与题目中给出的是否一样多，如果不是就是-1，否则就返回map中的耗时的最大值
### 代码
```java
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> edges = new HashMap<>();
        for (int[] time : times) {
            edges.computeIfAbsent(time[0], x -> new ArrayList<>()).add(new int[]{time[1], time[2]});
        }

        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        Map<Integer, Integer> memo = new HashMap<>();
        queue.offer(new int[]{k, 0});
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            if (memo.containsKey(node[0])) {
                continue;
            }

            memo.put(node[0], node[1]);

            if (edges.containsKey(node[0])) {
                for (int[] edge : edges.get(node[0])) {
                    queue.offer(new int[]{edge[0], edge[1] + node[1]});
                }
            }
        }

        return memo.size() == n ? Collections.max(memo.values()) : -1;
    }
}
```