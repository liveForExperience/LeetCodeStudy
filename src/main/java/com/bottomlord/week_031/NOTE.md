# LeetCode_1319_联通网络的操作次数
## 题目
用以太网线缆将 n 台计算机连接成一个网络，计算机的编号从 0 到 n-1。线缆用 connections 表示，其中 connections[i] = [a, b] 连接了计算机 a 和 b。

网络中的任何一台计算机都可以通过网络直接或者间接访问同一个网络中其他任意一台计算机。

给你这个计算机网络的初始布线 connections，你可以拔开任意两台直连计算机之间的线缆，并用它连接一对未直连的计算机。请你计算并返回使所有计算机都连通所需的最少操作次数。如果不可能，则返回 -1 。 

示例 1：
```
输入：n = 4, connections = [[0,1],[0,2],[1,2]]
输出：1
解释：拔下计算机 1 和 2 之间的线缆，并将它插到计算机 1 和 3 上。
```
示例 2：
```
输入：n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
输出：2
```
示例 3：
```
输入：n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
输出：-1
解释：线缆数量不足。
```
示例 4：
```
输入：n = 5, connections = [[0,1],[0,2],[3,4],[2,3]]
输出：0
```
提示：
```
1 <= n <= 10^5
1 <= connections.length <= min(n*(n-1)/2, 10^5)
connections[i].length == 2
0 <= connections[i][0], connections[i][1] < n
connections[i][0] != connections[i][1]
没有重复的连接。
两台计算机不会通过多条线缆连接。
```
## 解法
### 思路
- 如果有`n`台机器，就需要至少`n - 1`根网线，所以`connections`长度如果小于`n - 1`就返回`-1`
- 将机器看作节点，将网线看作边，就是一个无向图，无向图中的连通分量集合`S`就是被连接在一起的节点的集合
- `S`中连通分量的个数如果是`k`，那么最多需要`k-1`根网线就可以将所有节点联通
- dfs：
    - 生成无向图
    - 生成遍历需要的标记集合
    - 遍历无向图，将遍历到的节点标记
    - 遍历结束后返回并计数
- 最终放回计数值
### 代码
```java
class Solution {
    public int makeConnected(int n, int[][] connections) {
        if (n - 1 > connections.length) {
            return -1;
        }
        
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] connection : connections) {
            graph.get(connection[0]).add(connection[1]);
            graph.get(connection[1]).add(connection[0]);
        }

        boolean[] visited = new boolean[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, visited, graph);
                count++;
            }
        }
        
        return count - 1;
    }

    private void dfs(int i, boolean[] visited, List<List<Integer>> graph) {
        visited[i] = true;
        for (int n : graph.get(i)) {
            if (!visited[n]) {
                dfs(n, visited, graph);
            }
        }
    }
}
```
## 解法二
### 思路
并查集
### 代码
```java
class Solution {
    public int makeConnected(int n, int[][] connections) {
        if (n - 1 > connections.length) {
            return -1;
        }

        DSU dsu = new DSU(n);
        for (int[] connection : connections) {
            dsu.union(connection[0], connection[1]);
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (i == dsu.find(i)) {
                count++;
            }
        }
        
        return count - 1;
    }
}

class DSU {
    private int[] parent;
    
    public DSU(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }
    
    public void union(int x, int y) {
        parent[find(x)] = find(y);
    }
    
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        
        return parent[x];
    }
}
```
# LeetCode_802_找到最终的安全状态
## 题目
在有向图中, 我们从某个节点和每个转向处开始, 沿着图的有向边走。 如果我们到达的节点是终点 (即它没有连出的有向边), 我们停止。

现在, 如果我们最后能走到终点，那么我们的起始节点是最终安全的。 更具体地说, 存在一个自然数 K,  无论选择从哪里开始行走, 我们走了不到 K 步后必能停止在一个终点。

哪些节点最终是安全的？ 结果返回一个有序的数组。

该有向图有 N 个节点，标签为 0, 1, ..., N-1, 其中 N 是 graph 的节点数.  图以以下的形式给出: graph[i] 是节点 j 的一个列表，满足 (i, j) 是图的一条有向边。

示例：
```
输入：graph = [[1,2],[2,3],[5],[0],[5],[],[]]
输出：[2,4,5,6]
这里是上图的示意图。
```
提示：
```
graph 节点数不超过 10000.
图的边数不会超过 32000.
每个 graph[i] 被排序为不同的整数列表， 在区间 [0, graph.length - 1] 中选取。
```
## 解法
### 思路
- 如果当前节点是安全节点，那么与它相连的节点也是安全节点，无环。
- 可以从没有出边的节点开始找，再找仅仅与这些节点相连的节点，以此类推
- 过程：
    - 生成图，并将图反向，将记录节点的出度变成记录节点的入度
    - 将原图中没有出度的节点放入队列
    - 然后开始循环，从队列中取出节点`x`，将该节点标记为`safe`
    - 遍历当前节点的入度`y`，从图中将`y`的出度`x`删除，如果删除后`y`没有了出度，那么将`y`放入队列
    - 直到队列为空
- 返回标记过的节点
### 代码
```java
class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int len = graph.length;

        boolean[] safe = new boolean[len];
        List<Set<Integer>> graphs = new ArrayList<>();
        List<Set<Integer>> rGraphs = new ArrayList<>();

        for (int i = 0; i < len; i++) {
            graphs.add(new HashSet<>());
            rGraphs.add(new HashSet<>());
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            if (graph[i].length == 0) {
                queue.add(i);
            }
            for (int j : graph[i]) {
                graphs.get(i).add(j);
                rGraphs.get(j).add(i);
            }
        }

        while (!queue.isEmpty()) {
            int num = queue.poll();
            safe[num] = true;

            for (int in : rGraphs.get(num)) {
                graphs.get(in).remove(num);
                if (graphs.get(in).isEmpty()) {
                    queue.offer(in);
                }
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (safe[i]) {
                ans.add(i);
            }
        }

        return ans;
    }
}
```
## 解法二
### 思路
dfs染色：
- 使用三种状态描述节点：
    - 0：还未访问
    - 1：正被访问，或有环
    - 2：证实无环
- 过程：
    - 初始化涂色数组
    - 遍历二维数组
    - 递归涂色
        - 如果已经涂色，返回是否是2
        - 将当前节点涂色为1
        - 开始遍历二维数组
            - 如果已经涂色为2，跳过
            - 如果已经涂色为1，或则返回false，返回false
        - 否则将当前节点涂色为2，并返回true
    - 根据涂色返回将节点放入`ans`
    - 遍历结束返回`ans`
### 代码
```java
class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int len = graph.length;
        List<Integer> ans = new ArrayList<>();
        int[] color = new int[len];
        
        for (int i = 0; i < len; i++) {
           if (dfs(i, graph, color)) {
               ans.add(i);
           }
        }
        
        return ans;
    }

    private boolean dfs(int node, int[][] graph, int[] color) {
        if (color[node] > 0) {
            return color[node] == 2;
        }
        
        color[node] = 1;
        for (int n : graph[node]) {
            if (color[n] == 2) {
                continue;
            }
            
            if (color[n] == 1 || !dfs(n, graph, color)) {
                return false;
            }
        }
        
        color[node] = 2;
        return true;
    }
}
```