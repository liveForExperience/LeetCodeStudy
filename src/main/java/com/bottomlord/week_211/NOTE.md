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