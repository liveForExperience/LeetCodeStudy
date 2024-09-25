# [LeetCode_2673_使二叉树所有路径值相等的最小代价](https://leetcode.cn/problems/make-costs-of-paths-equal-in-a-binary-tree/)
## 解法
### 思路
- 思考过程：
  - 因为题目限制了这颗树是满二叉树，也就意味着每个路径上的节点数是一样的
  - 因为互为兄弟的2个节点，他们所对应的路径除了自身不同之外，其他节点都是相同的，如果要使这两个路径的值总和相等，就必须使2者相等
  - 那么，为了解决整颗树的所有路径值之和都相等，就可以通过自底向上的思路，一对对兄弟的相等，然后将相等的值累加到父节点上，让父节点的兄弟通过增加的值知道其与其兄弟之间需要通过多大的差值，才能将他们对应的子树的所有路径都计算成路径和相等，最终推导到根节点
- 算法过程
  - 初始化答案值`ans`
  - 从最右的叶子节点开始遍历，每次遍历一组兄弟节点
  - 根据`cost`的坐标作为遍历的坐标，从`n`和`n + 1`开始向数组的开头开始遍历，步长为2，终止条件是`i > 0`，因为根节点没有兄弟节点，所以不必处理
  - 每次都计算兄弟节点的差值，将差值的绝对值累加到`ans`
  - 然后将兄弟节点中的较大的那个值增加到父节点上，父节点即`i / 2`
  - 继续循环，直到循环结束，返回`ans`作为结果
### 代码
```java
class Solution {
    public int minIncrements(int n, int[] cost) {
         int ans = 0;
        for (int i = n - 2; i > 0; i -= 2) {
            ans += Math.abs(cost[i] - cost[i + 1]);
            cost[i / 2] += Math.max(cost[i], cost[i + 1]);
        }
        return ans;
    }
}
```
# [LeetCode_2368_受限条件下可到达节点的数目](https://leetcode.cn/problems/reachable-nodes-with-restrictions)
## 解法
### 思路
- 思考过程
  - 一看就可以用bfs做，因为0一定不在限制条件里，所以从0开始bfs就可以了
  - 通过二维的边数组构建邻接表，然后基于邻接表做bfs
- 算法过程
  - 初始化邻接表`matrix`，基于`edges`对`matrix`做初始化，`matrix`可以是一个动态列表数组
  - 初始化队列`queue`，队列元素为节点编号
  - 将编号0放入队列中，开始bfs
  - 初始化一个布尔数组`memo`记录遍历过的节点，同时将`restrited`值也放入`memo`中，因为此时拒绝和记录过的状态可以共用
  - 初始化一个结果值`ans`，用于记录能够搜索到的节点个数
  - 在bfs过程中
    - 基于`matrix`获得下一批可以放入的`queue`
    - 基于`memo`对当前元素做是否有效的判断
    - 如果有效就基于编号值记录到`memo`中，并累加`ans`
  - bfs结束后返回`ans`
### 代码
```java
class Solution {
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        boolean[] memo = new boolean[n];
        for (int i : restricted) {
            memo[i] = true;
        }

        int ans = 0;
        List<Integer>[] matrix = new List[n];
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            matrix[edge[0]].add(edge[1]);
            matrix[edge[1]].add(edge[0]);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(0);
        while (!queue.isEmpty()) {
            int cnt = queue.size();
            while (cnt-- > 0) {
                Integer index = queue.poll();
                if (index == null) {
                    continue;
                }
                
                ans++;
                memo[index] = true;
                
                List<Integer> nexts = matrix[index];
                for (Integer next : nexts) {
                    if (memo[next]) {
                        continue;
                    }
                    
                    queue.offer(next);
                }
            }
        }
        
        return ans;
    }
}
```