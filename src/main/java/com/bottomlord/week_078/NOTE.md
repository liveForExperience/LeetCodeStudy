# LeetCode_239_滑动窗口最大值
## 解法
### 思路
- 使用优先级队列来辅助存储最大值的状态
- 优先级队列中的元素需要包含两个值
    - 数组中的元素值
    - 数组中的下标
- 使用元素值是用来配合优先级队列快速获得最大值
- 使用数组中下标，是因为，获取的是滑动窗口的最大值，所以需要配合下标来淘汰掉不符合要求的最大值，也就出超出滑动窗口的最大值
- 为了能快速获得最大值，且能快速淘汰非窗口的，优先级队列的排序规则就是，先根据元素大小降序排列，然后按照下标降序排序，这样在遍历数组时候，如果遇到下标超出滑动窗口范围的，就将元素弹出队列，而第一个符合范围的就是队列中的最大值
### 代码
```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }
        
        int len = nums.length;
        
        PriorityQueue<int[]> queue = new PriorityQueue<>((x, y) -> x[0] != y[0] ? y[0] - x[0] : y[1] - x[1]);
        for (int i = 0; i < k; i++) {
            queue.offer(new int[]{nums[i], i});
        }
        
        int[] ans = new int[len - k + 1];
        ans[0] = queue.peek()[0];
        
        for (int i = k; i < len; i++) {
            queue.offer(new int[]{nums[i], i});
            while (!queue.isEmpty() && queue.peek()[1] <= i - k) {
                queue.poll();
            }
            
            ans[i - k + 1] = queue.peek()[0];
        }
        
        return ans;
    }
}
```
