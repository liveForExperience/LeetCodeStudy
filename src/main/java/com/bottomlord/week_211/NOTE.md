# [LeetCode_771_宝石与石头](https://leetcode.cn/problems/jewels-and-stones/)
## 解法
### 思路
- 使用布尔数组作为宝石字符的判断记录
- 遍历石头数组，与布尔数组的坐标值做判断，如果为宝石就累加结果值
- 遍历结束返回结果值
### 代码
```java
class Solution {
    public int numJewelsInStones(String jewels, String stones) {
        boolean[] bucket = new boolean['z' - 'A' + 1];
        char[] jcs = jewels.toCharArray(), cs = stones.toCharArray();
        for (char jc : jcs) {
            bucket[jc - 'A'] = true;
        }

        int count = 0;
        for (char c : cs) {
            if (bucket[c - 'A']) {
                count++;
            }
        }
        
        return count;
    }
}
```
# [LeetCode_2208_将数组和减半的最少操作次数](https://leetcode.cn/problems/minimum-operations-to-halve-array-sum/)
## 解法
### 思路
- 因为题目只要求在尽可能少的操作数基础上，使得操作后的综合至少少于原总和的一半，所以我们可以用贪心的方式每次都尽可能减半当前最大的数
- 为了能在每次能快速的获取到要减半的尽可能大的数，可以通过优先级队列来存储列表中的元素
- 整个逻辑在优先级队列的帮助下就会非常简单：
  - 初始化优先级队列，也即大顶堆
  - 遍历数组，在求和的同时将元素放入大顶堆
  - 遍历结束后，循环大顶堆元素（退出条件时大顶堆为空）
  - 将堆顶元素弹出减半，再放回到大顶堆中，并将减半的值累减到总和里，并对操作数累加1，直到总和小于等于原来的一半
- 循环结束后返回累加值作为结果即可
### 代码
```java
class Solution {
    public int halveArray(int[] nums) {
        Queue<Double> queue = new PriorityQueue<>((x, y) -> Double.compare(y, x));
        double sum = 0D;
        for (int num : nums) {
            queue.offer(num * 1D);
            sum += num;
        }

        double target = sum / 2;
        int count = 0;
        while (!queue.isEmpty() && sum > target) {
            double cur = queue.poll();
            sum -= cur / 2;
            queue.offer(cur / 2);
            count++;
        }

        return count;
    }
}
```
# [LeetCode_2569_更新数组后处理求和查询](https://leetcode.cn/problems/handling-sum-queries-after-update/)
## 解法
### 思路
- 题目的三个操作
  - 1：讲nums1中[l,r]区间内的数进行反转
  - 2：`nums2[i] += p * nums[i]`
  - 3：求 nums2 的和
- 根据题目要求，实际就是在要求维护 nums1 的区间和，区间和可以通过线段树来维护
- 定义线段树的每个节点为Node，每个节点包含如下属性：
  - l：节点的左端点，下标从1开始
  - r：节点的右端点，下标从1开始
  - s：区间的和
  - lazy：节点的懒加载
- 线段树的操作
  - build(u,l,r)：建立线段树
  - pushDown(u)：下传懒标记
  - pushUp(u)：用子节点的信息更新父节点的信息
  - modify(u, l, r)：修改区间和，本题中是反转区间中的每个数，所以区间和就是`s = r - l + 1 - s`
  - query(u, l, r)：查询区间和
- 主体逻辑：
  - 先算出数组nums2的所有元素之和s
  - 执行操作1的时候：调用modify(l, r)
  - 执行操作2的时候：`s += p * query(1, n)`
  - 执行操作3的时候：将s放入结果数组中
### 代码
```java
class Solution {
    public long[] handleQuery(int[] nums1, int[] nums2, int[][] queries) {
        int num3 = 0;
        for (int[] query : queries) {
            if (query[0] == 3) {
                num3++;
            }
        }

        long[] ans = new long[num3];
        int index = 0;

        long sum = 0;
        for (int num : nums2) {
            sum += num;
        }

        SegmentTree tree = new SegmentTree(nums1);
        for (int[] query : queries) {
            int operator = query[0];
            if (operator == 1) {
                tree.modify(1, query[1] + 1, query[2] + 1);
            } else if (operator == 2) {
                sum += (long) query[1] * tree.query(1, 1, nums2.length);
            } else {
                ans[index++] = sum;
            }
        }

        return ans;
    }

    private static class SegmentTree {
        private Node[] nodes;
        private int[]  nums;

        public SegmentTree(int[] nums) {
            int n = nums.length;
            this.nums = nums;
            nodes = new Node[n << 2];
            for (int i = 0; i < nodes.length; i++) {
                nodes[i] = new Node();
            }
            build(1, 1, n);
        }

        private void build(int u, int l, int r) {
            nodes[u].l = l;
            nodes[u].r = r;
            if (l == r) {
                nodes[u].s = nums[l - 1];
                return;
            }

            int mid = (l + r) >> 1;
            build(u << 1, l, mid);
            build(u << 1 | 1, mid + 1, r);
            pushUp(u);
        }

        private void modify(int u, int l, int r) {
            if (nodes[u].l >= l && nodes[u].r <= r) {
                nodes[u].lazy ^= 1;
                nodes[u].s = nodes[u].r - nodes[u].l + 1 - nodes[u].s;
                return;
            }
            pushDown(u);

            int mid = (nodes[u].r + nodes[u].l) >> 1;
            if (l <= mid) {
                modify(u << 1, l, r);
            }
            if (r > mid) {
                modify(u << 1 | 1, l, r);
            }
            pushUp(u);
        }

        private int query(int u, int l, int r) {
            if (nodes[u].l >= l && nodes[u].r <= r) {
                return nodes[u].s;
            }

            pushDown(u);
            int mid = (nodes[u].l + nodes[u].r) >> 1;
            int sum = 0;
            if (l <= mid) {
                sum += query(u << 1, l , r);
            }

            if (r > mid) {
                sum += query(u << 1 | 1, l, r);
            }
            return sum;
        }

        private void pushDown(int u) {
            if (nodes[u].lazy != 1) {
                return;
            }

            int mid = (nodes[u].l + nodes[u].r) >> 1;
            nodes[u << 1].s = mid - nodes[u].l + 1 - nodes[u << 1].s;
            nodes[u << 1].lazy ^= 1;
            nodes[u << 1 | 1].s = nodes[u].r - mid - nodes[u << 1 | 1].s;
            nodes[u << 1 | 1].lazy ^= 1;
            nodes[u].lazy ^= 1;
        }

        private void pushUp(int u) {
            nodes[u].s = nodes[u << 1].s + nodes[u << 1 | 1].s;
        }
    }

    private static class Node {
        private int l, r, s, lazy;
    }
}
```
# [LeetCode_2050_并行课程III](https://leetcode.cn/problems/parallel-courses-iii/)
## 解法
### 思路
- 拓扑排序的同时通过动态规划维护出完成所有课程的最小消耗实践
- 主要逻辑：
  - 初始化一个临界表edge
    - key为节点
    - value为出度列表
  - 初始化一个入度的个数数组inDegrees
  - 初始化一个队列queue，用于存储入度为零的节点
  - 初始化一个dp数组，用于存储i课程上完需要的最少时间
  - 初始化一个ans变量，用于暂存读完所有课程的最少时间
  - 遍历relations，维护edge和inDegrees
  - 遍历inDegrees数组，将入度为0的节点放入queue中
  - 当queue不为空，取出队首元素i，根据邻接表获得出度列表，遍历出度列表，依次对元素节点j进行状态转移处理
    - 状态转移方程：`dp[j] = max(dp[i] + time[j], dp[j])`
  - 出度遍历结束后，将dp[j]累加到ans中
  - queue处理结束，返回ans结果即可
### 代码
```java
class Solution {
    public int minimumTime(int n, int[][] relations, int[] time) {
        int[] dp = new int[n + 1], inDegrees = new int[n + 1];
        int ans = 0;
        Map<Integer, List<Integer>> edge = new HashMap<>();
        Queue<Integer> queue = new ArrayDeque<>();
        for (int[] relation : relations) {
            inDegrees[relation[1]]++;
            edge.computeIfAbsent(relation[0], x -> new ArrayList<>()).add(relation[1]);
        }

        for (int i = 1; i < inDegrees.length; i++) {
            if (inDegrees[i] == 0) {
                queue.offer(i);
                dp[i] = time[i - 1];
                ans = Math.max(dp[i], ans);
            }
        }

        while (!queue.isEmpty()) {
            int i = queue.poll();
            if (!edge.containsKey(i)) {
                continue;
            }

            for (Integer j : edge.get(i)) {
                dp[j] = Math.max(dp[j], dp[i] + time[j - 1]);
                ans = Math.max(dp[j], ans);
                if (--inDegrees[j] == 0) {
                    queue.offer(j);
                }
            }
        }
        
        return ans;
    }
}
```
# [LeetCode_834_树中距离之和](https://leetcode.cn/problems/sum-of-distances-in-tree/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_142_环形链表II](https://leetcode.cn/problems/linked-list-cycle-ii/)
## 解法
### 思路
- 使用快慢指针，如果有环，那么快慢指针一定会有相交点meet
- 如果没有环，直接返回null即可
- 因为快指针是慢指针速度的2倍，所以慢指针移动的距离是x，快指针移动的距离是2x
- 也就是从meet开始移动x的距离，可以再次到达meet
- 而从head开始移动，到达meet节点前，一定能遇到环的起始节点
- 如果有2个指针，以相同的速度分别从meet和head开始移动，那么它们一定会在超过meet节点前相遇（最晚会在meet节点相遇），因为它们移动的速度和距离（都是x）相同，而它们相遇的点就是环的起始节点
- 因为：它们都移动x长度，如果从环起始节点start到相遇节点meet的距离是a，那么：
  - 如果从head开始移动的指针到达start的距离是b，那么到达meet经过的距离是`b + a`
  - 如果从meet开始移动的指针到达start的距离是c，那么到达meet经过的距离是`c + a`
  - 因为两个指针都经过x距离同时到达meet，所以`c + a = b + a` => `c = b`
  - 因为速度相同，所以当两个指针相遇的时候，就是start的节点位置
- 代码逻辑：
  - 通过快慢指针找到meet，如果没有就返回null
  - 在meet上初始化指针a
  - 在head上初始化指针b
  - a和b同时移动，首次相遇的位置就是答案节点start
### 代码
```java
public class Solution {
  public ListNode detectCycle(ListNode head) {
    ListNode slow = head, fast = head;
    ListNode meet = null;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;

      if (slow == fast) {
        meet = slow;
        break;
      }
    }

    if (meet == null) {
      return null;
    }

    ListNode a = meet, b = head;
    while (a != b) {
      a = a.next;
      b = b.next;
    }

    return a;
  }
}
```