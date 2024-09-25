# [LeetCode_1038_从二叉搜索树到更大和树](https://leetcode.cn/problems/binary-search-tree-to-greater-sum-tree)
## 解法
### 思路
- 思考过程：
  - 基于二叉搜索树中序遍历得到的是升序序列的特性，对BST做中序遍历，并生成升序序列，序列中存储节点的引用
  - 基于升序序列再生成一个前缀和序列
  - 然后就可以基于前缀和序列得到更大和树了
- 算法过程：
  - 中序dfs遍历BST，生成节点引用列表`list`
  - 遍历`list`生成前缀和数组`sums`
  - 遍历`list`，基于引用变更其value为对应的前缀和
### 代码
```java
class Solution {
    public TreeNode bstToGst(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        dfs(root, list);
        if (list.isEmpty()) {
            return root;
        }

        int[] sums = new int[list.size() + 1];
        for (int i = 1; i < sums.length; i++) {
            sums[i] = sums[i - 1] + list.get(i - 1).val;
        }

        for (int i = 0; i < list.size(); i++) {
            list.get(i).val = sums[list.size()] - sums[i];
        }
        
        return root;
    }

    private void dfs(TreeNode node, List<TreeNode> list) {
        if (node == null) {
            return;
        }

        dfs(node.left, list);
        list.add(node);
        dfs(node.right, list);
    }
}
```
# [LeetCode_2477_到达首都的最少油耗](https://leetcode.cn/problems/minimum-fuel-cost-to-report-to-the-capital)
## 解法
### 思路
- 思考过程：
  - 因为节点个数是`n`，而连接为`n-1`，且是一个`连通无环图`，所以无需考虑最优路径，通过dfs找到叶子节点向上递推到根就可以得到结果
  - 因为题目有`seat`的限制，所以在递推的过程中，路径上的人数也是有用的，需要一并向上传递，使上层节点能基于人数计算油耗数
  - 所以这道题只要通过二维表`roads`生成邻接表，然后通过dfs搜索就能够得到结果
- 算法过程：
  - 通过`roads`得到邻接表`graph`
  - 对`graph`进行dfs，每一层返回产生的油耗和人数
  - 当前层则对到达当前城市的人数，基于`seat`值计算出发车次数，也即油耗数，然后继续传递上去即可
    - 当前层的人数`sumPerson`：即下层返回人数的累加
    - 当前层的油耗`sumCost`：即下层返回油耗的累加，再当前节点出发需要的油耗`curCost`，也即`curCost = sumPerson / seats + (sumPerson % seats == 0 ? 0 : 1)`
    - 但要注意，如果当前层是首都，即0，那么`curCost`这部分不需要再增加
  - 搜索结束，返回油耗总数即可
### 代码
```java
class Solution {
  public long minimumFuelCost(int[][] roads, int seats) {
    if (roads.length == 0) {
      return 0;
    }

    Map<Integer, List<Integer>> graph = new HashMap<>();
    for (int[] road : roads) {
      graph.computeIfAbsent(road[0], x -> new ArrayList<>()).add(road[1]);
      graph.computeIfAbsent(road[1], x -> new ArrayList<>()).add(road[0]);
    }

    long[] r = dfs(0, graph, seats, new boolean[roads.length + 1]);
    return r[0];
  }

  private long[] dfs(int node, Map<Integer, List<Integer>> graph, int seats, boolean[] memo) {
    if (!graph.containsKey(node)) {
      return new long[]{1, 1};
    }

    memo[node] = true;
    List<Integer> nexts = graph.get(node);
    long sumCost = 0, sumPerson = 1;
    for (Integer next : nexts) {
      if (memo[next]) {
        continue;
      }

      long[] r = dfs(next, graph, seats, memo);
      sumPerson += r[1];
      sumCost += r[0];
    }

    long curCost = 0;
    if (node != 0) {
      curCost = sumPerson / seats + (sumPerson % seats == 0 ? 0 : 1);
    }

    return new long[]{sumCost + curCost, sumPerson};
  }
}
```

# [LeetCode_1466_重新规划路线](https://leetcode.cn/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero)
## 解法
### 思路
- 思考过程：
  - 因为题目限制了2个节点之间只有唯一一条路径，且`n`个节点一共只有`n-1`条路径，那就说明如果不考虑方向，将0作为根节点，这张图实际可以理解成一颗树
  - 同时，所有非0节点都要能够在调整后通到0节点，所以可以根据`connections`生成邻接表，并将入度和出度分别统计，然后遍历这颗树，将方向都设置为从叶子节点指向根节点0的状态，就可以了
- 算法过程：
  - 初始化2个数组`outs`和`ins`，元素分别初始化为动态列表，用这两个数组来记录入度和出度
  - 从节点0开始做bfs，然后对遍历到的元素节点进行判断
    - 如果当前节点有出度，那么将这些出度都从概念上进行翻转，实际就是记录一下翻转次数，然后放入队列
    - 如果当前节点有入度，那么将这些入度放入队列即可
  - bfs结束，返回记录的翻转次数即可
### 代码
```java
class Solution {
    public int minReorder(int n, int[][] connections) {
        List[] outs = new List[n], ins = new List[n];

        for (int i = 0; i < n; i++) {
            outs[i] = new ArrayList<>();
            ins[i] = new ArrayList();
        }

        for (int[] connection : connections) {
            int from = connection[0], to = connection[1];
            outs[from].add(to);
            ins[to].add(from);
        }

        int ans = 0;
        boolean[] memo = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(0);

        while (!queue.isEmpty()) {
            int index = queue.poll();
            memo[index] = true;

            List<Integer> outIndexes = outs[index];
            for (Integer outIndex : outIndexes) {
                if (!memo[outIndex]) {
                    queue.offer(outIndex);
                    ans++;
                }
            }

            List<Integer> inIndexes = ins[index];
            for (Integer inIndex : inIndexes) {
                if (!memo[inIndex]) {
                    queue.offer(inIndex);
                }
            }
        }

        return ans;
    }
}
```
