# LeetCode_1319_联通网络的操作次数
## 题目
用以太网线缆将n台计算机连接成一个网络，计算机的编号从0到n-1。线缆用connections表示，其中connections[i] = [a, b]连接了计算机a和b。

网络中的任何一台计算机都可以通过网络直接或者间接访问同一个网络中其他任意一台计算机。

给你这个计算机网络的初始布线connections，你可以拔开任意两台直连计算机之间的线缆，并用它连接一对未直连的计算机。请你计算并返回使所有计算机都连通所需的最少操作次数。如果不可能，则返回-1 。

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
0 <= connections[i][0], connections[i][1]< n
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

现在, 如果我们最后能走到终点，那么我们的起始节点是最终安全的。 更具体地说, 存在一个自然数 K, 无论选择从哪里开始行走, 我们走了不到 K 步后必能停止在一个终点。

哪些节点最终是安全的？ 结果返回一个有序的数组。

该有向图有 N 个节点，标签为 0, 1, ..., N-1, 其中 N 是graph的节点数. 图以以下的形式给出: graph[i] 是节点 j 的一个列表，满足 (i, j) 是图的一条有向边。

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
每个 graph[i] 被排序为不同的整数列表， 在区间 [0, graph.length - 1]中选取。
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
# LeetCode_688_马在棋盘上的概率
## 题目
已知一个NxN的国际象棋棋盘，棋盘的行号和列号都是从 0 开始。即最左上角的格子记为(0, 0)，最右下角的记为(N-1, N-1)。

现有一个 “马”（也译作 “骑士”）位于(r, c)，并打算进行K 次移动。

如下图所示，国际象棋的 “马” 每一步先沿水平或垂直方向移动 2 个格子，然后向与之相垂直的方向再移动 1 个格子，共有 8 个可选的位置。

现在 “马” 每一步都从可选的位置（包括棋盘外部的）中独立随机地选择一个进行移动，直到移动了K次或跳到了棋盘外面。

求移动结束后，“马” 仍留在棋盘上的概率。

示例：
```
输入: 3, 2, 0, 0
输出: 0.0625
解释: 
输入的数据依次为 N, K, r, c
第 1 步时，有且只有 2 种走法令 “马” 可以留在棋盘上（跳到（1,2）或（2,1））。对于以上的两种情况，各自在第2步均有且只有2种走法令 “马” 仍然留在棋盘上。
所以 “马” 在结束后仍在棋盘上的概率为 0.0625。
```
注意：
```
N 的取值范围为 [1, 25]
K的取值范围为 [0, 100]
开始时，“马” 总是位于棋盘上
```
## 解法
### 思路
dfs：
- 递归遍历当前位置可能走的8步，并累加这8步的返回值
    - 如果在，就判断步数是否到了，如果到了就返回1，否则继续递归
    - 如果不在，就返回0
- 将累加到的返回值除上8，并返回
- 增加记忆化搜索
### 代码
```java
class Solution {
    public double knightProbability(int N, int K, int r, int c) {
        int[][] paths = new int[][]{{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};
        Map<Integer, Map<Integer, Map<Integer, Double>>> memo = new HashMap<>();
        return dfs(N, K, r, c, memo, paths);
    }

    private double dfs(int n, int k, int r, int c, Map<Integer, Map<Integer, Map<Integer, Double>>> memo, int[][] paths) {
        if (!isValid(n, r, c)) {
            return 0;
        }

        if (k == 0) {
            return 1;
        }

        if (memo.containsKey(k) && memo.get(k).containsKey(r) && memo.get(k).get(r).containsKey(c)) {
            return memo.get(k).get(r).get(c);
        }

        double p = 0;
        for (int[] path : paths) {
            p += dfs(n, k - 1, r + path[0], c + path[1], memo, paths);
        }

        p /= 8;
        Map<Integer, Map<Integer, Double>> rmap = memo.getOrDefault(k, new HashMap<>());
        Map<Integer, Double> cmap = rmap.getOrDefault(r, new HashMap<>());
        cmap.put(c, p);
        rmap.put(r, cmap);
        memo.put(k, rmap);
        
        return p;
    }

    private boolean isValid(int n, int r, int c) {
        return r < n && r >= 0 && c < n && c >= 0;
    }
}
```
## 优化代码
### 思路
优化`memo`的缓存数据结构，使用`dp[][][] == 0.0`来判断是否已经有缓存，因为就算概率就是`0.0`，那他也不会导致多余1次的递归，因为这个位置的所有8个方向都没有可以继续递归的位置，所以可以这么判断
### 代码
```java
class Solution {
    public double knightProbability(int N, int K, int r, int c) {
        int[][] paths = new int[][]{{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};
        double[][][] memo = new double[K + 1][N][N];
        return dfs(N, K, r, c, memo, paths);
    }

    private double dfs(int n, int k, int r, int c, double[][][] memo, int[][] paths) {
        if (!isValid(n, r, c)) {
            return 0.0;
        }
        
        if (k == 0) {
            return 1.0;
        }
        
        if (memo[k][r][c] != 0.0) {
            return memo[k][r][c];
        }
        
        double p = 0.0;
        for (int[] path : paths) {
            p += dfs(n, k - 1, r + path[0], c + path[1], memo, paths);
        }
        
        p /= 8;
        memo[k][r][c] = p;
        return p;
    }
    
    private boolean isValid(int n, int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }
}
```
# LeetCode_430_扁平化多级双向链表
## 题目
您将获得一个双向链表，除了下一个和前一个指针之外，它还有一个子指针，可能指向单独的双向链表。这些子列表可能有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示。

扁平化列表，使所有结点出现在单级双链表中。您将获得列表第一级的头部。

示例:
```
输入:
 1---2---3---4---5---6--NULL
         |
         7---8---9---10--NULL
             |
             11--12--NULL

输出:
1-2-3-7-8-11-12-9-10-4-5-6-NULL
```
## 解法
### 思路
dfs：
- 将`child`和`next`作为二叉树的左右子树指针来看待
- 通过中序遍历来遍历高维链表
- 过程：
    - 递归过程传入参数：
        - 前置指针：`pre`，用来将child的节点接到主干
        - 遍历指针：`cur`，用来遍历整个高位链表
    - 退出条件：如果当前节点为空，返回`pre`
    - 将当前节点接到主干上
        - `pre.next = cur`
        - `cur.pre = pre`
    - 获取当前节点的`next`分支的指针`next`
    - 递归获得`child`的分支，接到`cur`上
    - 注意这里需要将`cur.child`置为空，否则就无法做到扁平
    - 再将`next`接到`child`递归后返回的指针上
    - 最后将这个接好的节点返回到上一层主干上
### 代码
```java
class Solution {
    public Node flatten(Node head) {
        if (head == null) {
            return null;
        }
        
        Node start = new Node();
        dfs(start, head);
        start.next.prev = null;
        return start.next;
    }

    private Node dfs(Node pre, Node cur) {
        if (cur == null) {
            return pre;
        }

        pre.next = cur;
        cur.prev = pre;

        Node next = cur.next;
        Node child = dfs(cur, cur.child);

        cur.child = null;
        
        return dfs(child, next);
    }
}
```
# LeetCode_146_LRU缓存机制
## 题目
运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
```
获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
```
进阶:
```
你是否可以在O(1) 时间复杂度内完成这两种操作？
```
示例:
```
LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // 返回  1
cache.put(3, 3);    // 该操作会使得密钥 2 作废
cache.get(2);       // 返回 -1 (未找到)
cache.put(4, 4);    // 该操作会使得密钥 1 作废
cache.get(1);       // 返回 -1 (未找到)
cache.get(3);       // 返回  3
cache.get(4);       // 返回  4
```
## 解法
### 思路
- 可以通过继承LinkedHashMap实现LRU缓存
- 需要重写`removeEldestEntry`来实现缓存淘汰
> 正好最近做了LinkedHashMap的源码笔记^0^
### 代码
```java
class LRUCache extends LinkedHashMap<Integer, Integer>{
    private int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}
```
## 解法二
### 思路
- 因为题目的key是int，所以可以通过hash表+双向链表来实现，不需要单向链表
- 双向链表增加伪头部`head`和伪尾部`tail`，避免多余的null判断
- 双向链表中增加4个方法：
    - add：添加节点到`head`之后
    - remove：在双向链表中删除节点
    - moveToHead：将节点移到`head`之后
    - popTail：将尾部的节点删除，并返回
- lru的2个方法：
    - get：通过hash表获取节点，将节点移到双向链表头部，返回节点值，如果没有就返回-1
    - put：
        1. 通过hash表获取节点
        2. 如果节点为空，生成新的双向链表节点，放入头部，判断lru的size是否大于cap，如果是，popTail
        3. 如果节点不为空，替换节点值
### 代码
```java
class LRUCache {
    private Map<Integer, DoubleLinkedNode> map;
    private int capacity;
    private int size;
    private DoubleLinkedNode head;
    private DoubleLinkedNode tail;

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.head = new DoubleLinkedNode();
        this.tail = new DoubleLinkedNode();
        head.next = tail;
        tail.pre = head;
        this.capacity = capacity;
        this.size = 0;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }

        DoubleLinkedNode node = map.get(key);
        moveToHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        DoubleLinkedNode node = map.get(key);

        if (node != null) {
            node.val = value;
            moveToHead(node);
        } else {
            node = new DoubleLinkedNode(key, value);
            map.put(key, node);
            addNode(node);
            
            size++;
            if (size > capacity) {
                DoubleLinkedNode tail = popTail();
                map.remove(tail.key);
                size--;
            }
        }
    }

    private void addNode(DoubleLinkedNode node) {
        node.pre = head;
        node.next = head.next;

        head.next.pre = node;
        head.next = node;
    }

    private void removeNode(DoubleLinkedNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    private void moveToHead(DoubleLinkedNode node) {
        removeNode(node);
        addNode(node);
    }

    private DoubleLinkedNode popTail() {
        DoubleLinkedNode tail = this.tail.pre;
        removeNode(tail);
        return tail;
    }

    private class DoubleLinkedNode {
        public int key;
        public int val;
        public DoubleLinkedNode pre;
        public DoubleLinkedNode next;

        public DoubleLinkedNode() {}
        public DoubleLinkedNode(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
}
```
# LeetCode_743_网络延迟时间
## 题目
有N个网络节点，标记为1到N。

给定一个列表times，表示信号经过有向边的传递时间。times[i] = (u, v, w)，其中u是源节点，v是目标节点， w是一个信号从源节点传递到目标节点的时间。

现在，我们向当前的节点K发送了一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回-1。

注意:
```
N的范围在[1, 100]之间。
K的范围在[1, N]之间。
times 的长度在 [1, 6000] 之间。
所有的边 times[i] = (u, v, w) 都有 1 <= u, v <= N 且 0 <= w <= 100。
```
## 解法
### 思路
dfs：
- 生成有向图
- 递归遍历有向图
- 计算所有经过该节点的耗时，取最小值
- 遍历结束后，遍历所有节点耗时，取最大值返回
### 代码
```java
class Solution {
    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int [] time : times) {
            List<int[]> list = graph.getOrDefault(time[0], new ArrayList<>());
            list.add(new int[]{time[1], time[2]});
            graph.put(time[0], list);
        }

        Map<Integer, Integer> dist = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            dist.put(i, Integer.MAX_VALUE);
        }
        dfs(graph, dist, K, 0);

        int max = 0;
        for (Integer time : dist.values()) {
            if (time == Integer.MAX_VALUE) {
                return -1;
            } else {
                max = Math.max(max, time);
            }
        }
        return max;
    }

    private void dfs(Map<Integer, List<int[]>> graph, Map<Integer, Integer> dist, int node, int time) {
        if (time >= dist.get(node)) {
            return;
        }

        dist.put(node, time);

        if (graph.containsKey(node)) {
            for (int[] arr : graph.get(node)) {
                dfs(graph, dist, arr[0], time + arr[1]);
            }
        }
    }
}
```
## 解法二
### 思路
迪杰斯特拉最短路径：
- 每次扩展一个距离最短的点，更新与其相邻点的距离
- 过程：
    - 生成图
    - 生成记录访问节点耗时的集合`dist`，并初始化
    - 开始循环
        - 直到节点都访问过，或已经没有可以新到达的节点时，退出循环
        - 否则，获取当前`dist`中保存的最短节点和该节点坐标
        - 从有向图中找到它指向的所有节点，计算出那些节点目前为止的最短耗时，放入`dist`
        - 继续循环
    - 循环结束，遍历`dist`，找到最大值返回
### 代码
```java
class Solution {
    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] time : times) {
            List<int[]> list = graph.getOrDefault(time[0], new ArrayList<>());
            list.add(new int[]{time[1], time[2]});
            graph.put(time[0], list);
        }

        Map<Integer, Integer> dict = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            dict.put(i, Integer.MAX_VALUE);
        }
        dict.put(K, 0);
        boolean[] visited = new boolean[N + 1];

        while (true) {
            int dist = Integer.MAX_VALUE;
            int node = 0;
            for (int i = 1; i <= N; i++) {
                if (!visited[i] && dict.get(i) < dist) {
                    dist = dict.get(i);
                    node = i;
                }
            }

            if (node == 0) {
                break;
            }
            
            visited[node] = true;
            if (graph.containsKey(node)) {
                for (int[] arr : graph.get(node)) {
                    dict.put(arr[0], Math.min(dict.get(arr[0]), arr[1] + dist));
                }
            }
        }
        
        int max = 0;
        for (int i = 1; i <= N; i++) {
            if (dict.get(i) == Integer.MAX_VALUE) {
                return -1;
            } else {
                max = Math.max(max, dict.get(i));
            }
        }
        return max;
    }
}
```
## 优化代码
### 思路
- 通过小顶堆替换解法二中的`while(true)`，直接获得最短耗时，相同节点的其他耗时选项都跳过都可以直接跳过
- 设置一下小顶堆的比较器逻辑就可以了，其他逻辑和解法二雷同
- 这个算法的时间复杂度因为使用了小顶堆，所以缩短为`O(NlogN)`
### 代码
```java
class Solution {
    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] time : times) {
            List<int[]> list = graph.getOrDefault(time[0], new ArrayList<>());
            list.add(new int[]{time[1], time[2]});
            graph.put(time[0], list);
        }

        Map<Integer, Integer> dict = new HashMap<>();
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        queue.offer(new int[]{K, 0});
        
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            if (dict.containsKey(node[0])) {
                continue;
            }

            dict.put(node[0], node[1]);
            if (graph.containsKey(node[0])) {
                for (int[] edge : graph.get(node[0])) {
                    queue.offer(new int[]{edge[0], edge[1] + node[1]});
                }
            }
        }
        
        return dict.size() == N ? Collections.max(dict.values()) : -1;
    }
}
```
# LeetCode_424_替换后的最长重复字符
## 题目
给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。在执行上述操作后，找到包含重复字母的最长子串的长度。
```
注意:
字符串长度 和 k 不会超过 104。
```
示例 1:
```
输入:
s = "ABAB", k = 2

输出:
4

解释:
用两个'A'替换为两个'B',反之亦然。
```
示例 2:
```
输入:
s = "AABABBA", k = 1

输出:
4

解释:
将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
子串 "BBBB" 有最长重复字母, 答案为 4。
```
## 解法
### 思路
滑动窗口：
- 定义变量：
    - `left`：窗口左指针，用于在窗口溢出条件时缩小窗口
    - `right`：窗口右指针，用于在窗口不满足条件时扩大窗口
    - `count`：重复字母的最大值
    - `ans`：包含最大重复字符的字符串
    - `bucket`：字母和个数的映射关系表
- 过程：
    - 右指针循环遍历字符串
    - 更新当前字母的统计个数
    - 更新`count`值
    - 循环判断当前窗口中是否包含超过k个的不同字母，通过`right - left + 1 - count`来计算
        - 如果是，就缩小窗口
    - 否则更新`ans`并扩大窗口，向右移动
    - 最后返回`ans`
### 代码
```java
class Solution {
    public int characterReplacement(String s, int k) {
        int left = 0, right = 0, count = 0, ans = 0;
        int[] bucket = new int[26];

        while (right < s.length()) {
            count = Math.max(count, ++bucket[s.charAt(right) - 'A']);
            while (right - left + 1 - count > k) {
                bucket[s.charAt(left++) - 'A']--;
            }

            ans = Math.max(ans, right++ - left + 1);
        }

        return ans;
    }
}
```
# LeetCode_491_1_递增子序列
## 题目
给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。

示例:
```
输入: [4, 6, 7, 7]
输出: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
```
说明:
```
给定数组的长度不会超过15。
数组中的整数范围是 [-100,100]。
给定数组中可能包含重复数字，相等的数字应该被视为递增的一种情况。
```
## 解法
### 思路
回溯：
- 递归入参：
    - 数组`arr`
    - 坐标`index`
    - 存储暂存子序列的集合`list`，使用`LinkedList`
    - 存储子序列的set集合`ans`
- 递归过程：
    - 如果`index`越界，返回
    - 遍历数组，判断当前`list`是否有元素且当前元素是否不符合递增，如果是，就跳过
    - 否则就将元素放入`list`
    - 判断`list`的元素个数是否大于1，如果是就放入`ans`
    - 递归
    - 返回后将尾部元素删除
- `ans`转化为动态数组后返回
### 代码
```java
class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        Set<List<Integer>> ans = new HashSet<>();
        LinkedList<Integer> list = new LinkedList<>();
        backTrace(nums, 0, list, ans);
        return new ArrayList<>(ans);
    }

    private void backTrace(int[] nums, int index, LinkedList<Integer> list, Set<List<Integer>> ans) {
        if (index >= nums.length) {
            return;
        }

        for (int i = index; i < nums.length; i++) {
            if (list.size() > 0 && list.getLast() > nums[i]) {
                continue;
            }

            list.offerLast(nums[i]);
            if (list.size() > 1) {
                ans.add(new ArrayList<>(list));
            }
            backTrace(nums, i + 1, list, ans);
            list.removeLast();
        }
    }
}
```
## 优化代码
### 思路
- 在解法一的基础上，使用标志变量来代替set去重的作用，当递归当前层遍历时，如果新的需要递归的元素与之前已经递归的元素一样，就跳过，这样就避免了重复元素的出现
- 其他过程与解法一基本一致
### 代码
```java
class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(nums, new ArrayList<>(), ans, 0, -101);
        return ans;
    }

    private void dfs(int[] nums, List<Integer> list, List<List<Integer>> ans, int index, int lastNum) {
        if (list.size() > 1) {
            ans.add(new ArrayList<>(list));
        }

        for (int i = index; i < nums.length; i++) {
            if (nums[i] < lastNum) {
                continue;
            }

            boolean repeat = false;
            for (int j = index; j <= i - 1; j++) {
                if (nums[i] == nums[j]) {
                    repeat = true;
                    break;
                }
            }

            if (repeat) {
                continue;
            }

            list.add(nums[i]);
            dfs(nums, list, ans, i + 1, nums[i]);
            list.remove(list.size() - 1);
        }
    }
}
```
# LeetCode_1040_移动石子直到连续II
## 题目
在一个长度无限的数轴上，第 i 颗石子的位置为 stones[i]。如果一颗石子的位置最小/最大，那么该石子被称作端点石子。

每个回合，你可以将一颗端点石子拿起并移动到一个未占用的位置，使得该石子不再是一颗端点石子。

值得注意的是，如果石子像 stones = [1,2,5] 这样，你将无法移动位于位置 5 的端点石子，因为无论将它移动到任何位置（例如 0 或 3），该石子都仍然会是端点石子。

当你无法进行任何移动时，即，这些石子的位置连续时，游戏结束。

要使游戏结束，你可以执行的最小和最大移动次数分别是多少？ 以长度为 2 的数组形式返回答案：answer = [minimum_moves, maximum_moves] 。

示例 1：
```
输入：[7,4,9]
输出：[1,2]
解释：
我们可以移动一次，4 -> 8，游戏结束。
或者，我们可以移动两次 9 -> 5，4 -> 6，游戏结束。
```
示例 2：
```
输入：[6,5,4,3,10]
输出：[2,3]
解释：
我们可以移动 3 -> 8，接着是 10 -> 7，游戏结束。
或者，我们可以移动 3 -> 7, 4 -> 8, 5 -> 9，游戏结束。
注意，我们无法进行 10 -> 2 这样的移动来结束游戏，因为这是不合要求的移动。
```
示例 3：
```
输入：[100,101,104,102,103]
输出：[0,0]
```
提示：
```
3 <= stones.length <= 10^4
1 <= stones[i] <= 10^9
stones[i] 的值各不相同。
```
## 解法
### 思路
- 最小值求解：
    - 使用滑动窗口
    - 如果有n个元素，那么最小值就是在原元素值区间中，找到范围为`n - 1`的元素区间，并在这若干个区间中找到初始石子最多的区间
    - 但有特例，例如`[1,2,3,4,7]`，这时候不能移动7，只能1移动到6，7移动到5，需要移动两次
- 最大值求解：
    - `s1 = stone[n - 1] - stone[0] + 1 - len`：石子最大最小范围之间的空位个数
    - `s2 = min(stone[n - 1] - stone[n - 2] - 1, stone[2] - stone[1] - 1)`：当移动一次端点后，这个端点与它相邻的节点之间的空位就不能用了，求这两个端点中间隔
    - 最大值就是`s1 - s2`
### 代码
```java
class Solution {
    public int[] numMovesStonesII(int[] stones) {
        Arrays.sort(stones);
        int len = stones.length,
            max = (stones[len - 1] - stones[0] + 1 - len) - (Math.min(stones[len - 1] - stones[len - 2] - 1, stones[1] - stones[0] - 1)),
            right = 0, min = len;

        for (int left = 0; left < len; left++) {
            while (right + 1 < len && stones[right + 1] - stones[left] + 1 <= len) {
                right++;
            }

            int cost = len - (right - left + 1);
            if (right - left + 1 == len - 1 && stones[right] - stones[left] + 1 == len - 1) {
                cost = 2;
            }

            min = Math.min(cost, min);
        }

        return new int[]{min, max};
    }
}
```