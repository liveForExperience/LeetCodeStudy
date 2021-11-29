# [LeetCode_786_第K个最小的素数分数](https://leetcode-cn.com/problems/k-th-smallest-prime-fraction/)
## 解法
### 思路
优先级队列
### 代码
```java
class Solution {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((x, y) -> Double.compare(y[0] * 1.0 / y[1], x[0] * 1.0 / x[1]));
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                queue.offer(new int[]{arr[i], arr[j]});
                if (queue.size() > k) {
                    queue.poll();
                }
            }
        }

        return queue.poll();
    }
}
```