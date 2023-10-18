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