# [LeetCode_924_尽量减少恶意软件的传播](https://leetcode.cn/problems/minimize-malware-spread)
## 解法
### 思路
- 根据邻接表生成联通分量，并统计连通分量的节点个数
- 然后思考如何最大程度的通过减少一个节点来减少传播的节点个数
  - 如果连通分量中没有感染的节点，不用考虑这个连通分量
  - 如果连通分量中只有一个感染节点，那么这个联通分量的节点个数就是可以减缓的个数
  - 如果连通分量中的感染节点大于1个，那么就算去掉某一个感染节点，该联通分量还是无法避免传染，所以也不用考虑
- 算法过程：
  - 初始化参数：
    - index作为联通分量的标识
    - 长度为n的数组`memo`来记录当前坐标对应的节点对应的连通分量的标识
    - map用于统计联通分量标识与联通分量个数的映射关系
    - map用于存储联通分量标识与受污染节点的的个数的映射关系`initialMap`
  - 从0开始遍历n个元素，对每个元素进行判断，如果其对应的联通分量标识不存在，则开始bfs搜索，否则代表已经处理过该节点
  - 每次循环都累加`index`作为一个新的联通分量标识
  - 初始化一个队列来进行bfs，并将当前坐标节点作为坐标在`memo`对应的坐标上记录`index`，将当前坐标作为初始值放入队列中
  - 通过邻接表来寻找出度，如果该出度没有被遍历过，则累加联通分量统计值`cnt`，并为该出度在`memo`中进行记录，紧接着继续放入队列中
  - 遍历结束后，将`index`和`cnt`存入到对应的映射表中
  - 遍历`initial`列表，根据该元素对应的连通分量标识，到`initialMap`中进行污染节点的累加统计
  - 最后遍历所有污染节点，判断当前污染节点对应的联通分量，其污染节点个数是否为1个，如果是，根据连通分量的个数来判断当前的个数是否是最大的，如果是最大的，就记录当前节点作为要删除的污染节点。
  - 遍历结束后返回记录的节点返回即可
### 代码
```java
class Solution {
    public int minMalwareSpread(int[][] graph, int[] initial) {
        int n = graph.length;
        int[] memo = new int[n];
        int index = 0;
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (memo[i] != 0) {
                continue;
            }
            
            index++;
            int cnt = 1;
            memo[i] = index;
            Queue<Integer> queue = new ArrayDeque<>();
            queue.offer(i);
            while (!queue.isEmpty()) {
                int cur = queue.poll();

                for (int next = 0; next < n; next++) {
                    if (memo[next] != 0 || graph[cur][next] != 1) {
                        continue;
                    }

                    cnt++;
                    memo[next] = index;
                    queue.offer(next);
                }
            }
            
            indexMap.put(index, cnt);
        }

        Map<Integer, Integer> initialMap = new HashMap<>();
        for (int i : initial) {
            initialMap.put(memo[i], initialMap.getOrDefault(memo[i], 0) + 1);
        }

        int ans = n, max = 0;
        for (int i : initial) {
            int removed = initialMap.get(memo[i]) == 1 ? indexMap.get(memo[i]) : 0;
            if (removed > max || (removed == max && i < ans)) {
                max = removed;
                ans = i;
            }
        }
        
        return ans;
    }
}
```