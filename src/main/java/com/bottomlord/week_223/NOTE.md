# [LeetCode_2530_执行K次操作后的最大分数](https://leetcode.cn/problems/maximal-score-after-applying-k-operations)
## 解法
### 思路
优先级队列
- 初始化一个优先级队列，用于存储`nums`数组的坐标
  - 比较规则：根据`nums`数组的元素值降序排列
- 将n个坐标依次放入优先级队列中
- 初始化一个`long`类型的变量值`sum`，用于暂存结果分数值
- 进行k次循环
  - 从队列中取出`nums`坐标，根据比较器规则，该坐标对应的元素值一定是目前`nums`中最大的
  - 将元素值累加到`sum`变量中
  - 将元素值根据题目规则通过`ceil(num / 3)`的方式更新，并将坐标值重新放回到优先级队列，使其对新的值重新排序。需要注意运算除法时需要先讲`nums`数组的`int`元素转为`double`，否则会导致精度丢失。
- 循环结束，返回`sum`即可
### 代码
```java
class Solution {
    public long maxKelements(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<Integer> queue = new PriorityQueue<>((x, y) -> nums[y] - nums[x]);
        for (int i = 0; i < n; i++) {
            queue.offer(i);
        }
        
        long sum = 0;
        while (k-- > 0 && !queue.isEmpty()) {
            int index = queue.poll();
            sum += nums[index];
            nums[index] = (int) Math.ceil(1D * nums[index] / 3);
            queue.offer(index);
        }
        
        return sum;
    }
}
```
# [LeetCode_1726_同积元组](https://leetcode.cn/problems/tuple-with-same-product)
## 解法
### 思路
- 思考过程：
  - 题目要求的是`a * b == c * d`的个数，这个公式中包含4个元素，然后看两两乘积相等的组合有多少个
  - 又因为`a * b`和`b * a`在题目中是算不同组合的，所以交换位置的情况也需要考虑
  - 可以通过遍历数组的方式，将两两的乘积记录下来，对乘积的个数进行统计，因为题目中限制元素是互补相同的，所以不需要考虑值重复的情况
  - 然后通过统计值来计算相同乘积的元组组合有多少个
    - 可以这么理解，假设元组数有`2n`个（因为之前说到的排列的问题，所以一定是偶数个），那么等式的左侧相当于是在`2n`个选择中选1个，而等式的右侧，相当于是在`2n - 2`个选择中选1个（-2是因为，题目要求所有元素是不想等的，所以不应该出现`a * b == b * a`的情况，所以需要将这两种情况一起去除）
    - 所以个数的计算公式就是：`2n * (2n - 2)`
      - `2n`是相同乘积的元组个数
- 算法过程：
  - 嵌套循环`nums`数组
    - 第一层从0开始循环
    - 第二次从第一层坐标的后一个坐标开始循环
    - 第二层循环中的循环体，将一层和二层的元素相乘，获取乘积后，放入map中进行统计和累加
  - 遍历结束后，开始遍历map的键值对，通过公式`2n * (2n - 2)`将个数累加起来
  - 遍历结束后将累加值返回即可
### 代码
```java
class Solution {
  public int tupleSameProduct(int[] nums) {
    Map<Integer, Integer> map = new HashMap<>();
    int n = nums.length, sum = 0;
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        int multi = nums[i] * nums[j];
        map.merge(multi, 1, Integer::sum);
      }
    }

    for (Integer num : map.values()) {
      sum += (num) * (num - 1) / 2;
    }

    return sum << 3;
  }
}
```
# [LeetCode_2316_统计无向图中无法互相到达点对数](https://leetcode.cn/problems/count-unreachable-pairs-of-nodes-in-an-undirected-graph)
## 解法
### 思路
- 初始化并查集对象，并查集需要具备如下能力
  - 存储
  - 根据元素查询根节点
  - 根据元素查询联通分量大小
- 遍历邻接表，将边的两个节点存储到并查集中
- 遍历节点，通过节点到并查集中查询节点所在联通分量的大小`x`
- 根据`x`计算与该点不联通的点的点对数：
  - 不在同一个联通分量的点就是不联通的，这个个数就是`n - x`，`n`即节点总数
- 将`n - x`的值进行累加，遍历结束后，得到累加总和，这个总和中，每个点其实被计算过两次：
  - 第1次是以该点为基础计算的与该点不联通的点对
  - 第2次是该点不作为基础，而是不联通点集合中的一部分时所计算的次数
- 故，将总和除以2作为答案返回即可
### 代码
```java
class Solution {
    public long countPairs(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            uf.union(edge[0], edge[1]);
        }

        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += n - uf.size(uf.find(i));
        }

        return sum / 2;
    }

    private static class UnionFind {

        private int[] parent, size;

        public UnionFind(int n) {
            this.parent = new int[n];
            this.size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public void union(int x, int y) {
            int rx = find(x), ry = find(y);
            if (rx != ry) {
                if (size(rx) > size(ry)) {
                    parent[ry] = rx;
                    size[rx] += size[ry];
                } else {
                    parent[rx] = ry;
                    size[ry] += size[rx];
                }
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }

            return parent[x];
        }

        public int size(int x) {
            return size[x];
        }
    }
}
```