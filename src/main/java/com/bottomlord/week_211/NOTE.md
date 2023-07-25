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