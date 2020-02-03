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