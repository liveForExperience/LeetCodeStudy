# [LeetCode_749_隔离病毒](https://leetcode.cn/problems/contain-virus/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_857_雇佣k名工人的最低成本](https://leetcode.cn/problems/minimum-cost-to-hire-k-workers/)
## 解法
### 思路
- 总投入度：sumQ
- 总工资金额：sumW
- sumW需要符合的条件：
  - `sumW / sumQ * q[i] >= w[i]`
  - 由如上得到：`sumW >= (w[i] / q[i]) * sumQ`
- 从如上公式可知，当sumQ恒定的情况下，影响sumW大小的是`w[i] / q[i]`
- 根据`w[i] / q[i]`的值，从小到大对`i`进行排序
- 然后遍历前k-1个i，将q累加，并将`q[i]`放入到优先级队列中，从大到小排列
- 依次通过求sumW最小值的公式：`(w[i] / q[i]) * sumQ`来更新最小值
- 遍历结束后，返回最小值
### 代码
```java
class Solution {
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        double totalQ = 0, ans = Long.MAX_VALUE;
        int len = quality.length;

        Integer[] idxes = new Integer[len];
        for (int i = 0; i < len; i++) {
            idxes[i] = i;
        }
        Arrays.sort(idxes, (x, y) -> wage[x] * quality[y] - wage[y] * quality[x]);

        Queue<Integer> queue = new PriorityQueue<>((x, y) -> y - x);
        for (int i = 0; i < k - 1; i++) {
            totalQ += quality[idxes[i]];
            queue.offer(quality[idxes[i]]);
        }

        for (int i = k - 1; i < len; i++) {
            int index = idxes[i];
            queue.offer(quality[index]);
            totalQ += quality[index];
            ans = Math.min(ans, (double) wage[index] / quality[index] * totalQ);
            if (!queue.isEmpty()) {
                totalQ -= queue.poll();
            }
        }

        return ans;
    }
}
```