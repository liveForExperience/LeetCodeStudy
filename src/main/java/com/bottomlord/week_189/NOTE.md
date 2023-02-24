# [LeetCode_1792_最大平均通过率](https://leetcode.cn/problems/maximum-average-pass-ratio/)
## 解法
### 思路
优先级队列
- 比较的是增量之间的差值，将增量大的放在堆顶
- 需要注意比较的时候要转换成double
### 代码
```java
class Solution {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((x, y) -> {
            double diff = increasment(y) - increasment(x);
            if (diff == 0) {
                return 0;
            }
            return diff > 0 ? 1 : -1;
        });

        for (int[] aClass : classes) {
            queue.offer(aClass);
        }

        for (int i = 0; i < extraStudents; i++) {
            int[] aClass = queue.poll();
            if (aClass == null) {
                continue;
            }

            aClass[0]++;
            aClass[1]++;
            queue.offer(aClass);
        }

        double sum = 0, size = queue.size();
        while (!queue.isEmpty()) {
            int[] aClass = queue.poll();
            sum += 1D * aClass[0] / aClass[1];
        }

        return sum / size;
    }

    private double increasment(int[] x) {
        return (1D * x[0] + 1) / (x[1] + 1) - 1D * x[0] / x[1];
    }
}
```