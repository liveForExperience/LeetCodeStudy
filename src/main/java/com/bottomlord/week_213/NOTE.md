# [LeetCode_1749_任意子数字和的绝对值的最大值](https://leetcode.cn/problems/maximum-absolute-sum-of-any-subarray/description/)
## 解法
### 思路
- 题目可以理解为，对子数字做极值求解，一个是最大值，一个是最小值，然后通过算绝对值求得题目要求的最大的答案
- 这是很基本的动态规划题目，且可以通过状态压缩来省略记事本数组
### 代码
```java
class Solution {
    public int maxAbsoluteSum(int[] nums) {
        int minDp = Integer.MAX_VALUE, maxDp = Integer.MIN_VALUE, ans = 0;
        for (int num : nums) {
            minDp = Math.min(minDp, 0) + num;
            maxDp = Math.max(maxDp, 0) + num;
            ans = Math.max(ans, Math.max(-minDp, maxDp));
        }
        return ans;
    }
}
```
# [LeetCode_2532_过桥的时间](https://leetcode.cn/problems/time-to-cross-a-bridge/description/)
## 解法
### 思路
- 一个工人在整个过程中会有4种状态
  - 在左侧等待
  - 在左侧搬运
  - 在右侧等待
  - 在右侧搬运
- 使用4个优先级队列来存储4种状态的工人
  - 在左侧、右侧等待的工人，根据题目要求，效率越低越先过桥，如果效率相同，则id越大效率越低，根据这个规则设置比较规则。这2个队列里放入代表工人的数组坐标
    - waitLeft
    - waitRight
  - 在左侧、右侧工作的工人，最终都需要在完成工作后，变为在一侧等待过桥的状态。队列中存储结束工作的时间，以及坐标，然后处理逻辑中会设置一个当前的时间（该时间也作为答案），谁结束时间越早，就越在队列头部，否则就根据坐标，谁小谁就在头部
    - workLeft
    - workRight
- 整体的逻辑过程
  - 初始化4个队列
  - 初始化当前时间curTime
  - 初始化在右侧仓库待搬运的货物个数n
  - 将所有工人放入waitLeft
  - 开始循环
    - 循环继续条件：
      - `n > 0`，代表还有货物需要搬运
      - `waitRight.size() > 0`，代表右侧还有工人需要过桥
      - `workRight.size() > 0`，代表右侧还有工人正在工作
    - 内层处理处理的内容
      - 先循环看一下`workLeft`和`workRight`队列里是否有比当前curTime小的元素，有就代表已经有工人完成工作，需要进入等待过桥队列了，将他们弹出后放入对应一侧的等待队列中
      - 然后根据题目，右侧的优先过桥，所以先看`waitRight`中是否有需要过桥的工人，如果有，就模拟过桥，然后放入`workLeft`队列中，
        - 此时curTime要加上其过桥的时间
        - 在入队`workLeft`的时候，需要根据curTime再加上其工作的时间作为其入队的数组元素之一，方便下次是否可以出队的参考
      - 如果右侧没有，那么看n是否大于0，且左侧`waitLeft`中是否有需要过桥的工人，如果有，就模拟过桥
        - 此时curTime依然加上其过桥时间
        - 在塞入`workRight`队列的时候，同样根据curTime再加上工作时间，设置为元素值
        - 将n累减1，代表有一个货物被当前员工处理了
      - 如果如上2种路径都不没有匹配，那么就模拟时间流逝，模拟的方式
        - 将`workLeft`和`workRight`的队头元素中结束时间最早的作为新的curTime
    - 循环结束之后，代表
      - 右侧没有需要工作和过桥的工人
      - 右侧没有需要搬运的货物
    - 此时将curTime返回作为结果即可
### 代码
```java
class Solution {
    public int findCrossingTime(int n, int k, int[][] time) {
       Comparator<Integer> waitComparator = (x, y) -> {
            int waitX = time[x][0] + time[x][2], waitY = time[y][0] + time[y][2];
            return waitX != waitY ? waitY - waitX : y - x;
        };

        Comparator<int[]> workComparator = (x, y) -> x[0] != y[0] ? x[0] - y[0] : x[1] - y[1];

        PriorityQueue<Integer> waitRight = new PriorityQueue<>(waitComparator),
                               waitLeft = new PriorityQueue<>(waitComparator);
        PriorityQueue<int[]> workRight = new PriorityQueue<>(workComparator),
                             workLeft = new PriorityQueue<>(workComparator);

        for (int i = 0; i < k; i++) {
            waitLeft.offer(i);
        }

        int curTime = 0;
        while (n > 0 || !workRight.isEmpty() || !waitRight.isEmpty()) {
            while (!workRight.isEmpty() && workRight.peek()[0] <= curTime) {
                waitRight.offer(workRight.poll()[1]);
            }

            while (!workLeft.isEmpty() && workLeft.peek()[0] <= curTime) {
                waitLeft.offer(workLeft.poll()[1]);
            }

            if (!waitRight.isEmpty()) {
                int id = waitRight.poll();
                int[] worker = time[id];
                curTime += worker[2];
                workLeft.offer(new int[]{curTime + worker[3], id});
            } else if (n > 0 && !waitLeft.isEmpty()) {
                int id = waitLeft.poll();
                int[] worker = time[id];
                curTime += worker[0];
                workRight.offer(new int[]{curTime + worker[1], id});
                n--;
            } else {
                int nextTime = Integer.MAX_VALUE;
                if (!workLeft.isEmpty()) {
                    nextTime = Math.min(nextTime, workLeft.peek()[0]);
                }
                
                if (!workRight.isEmpty()) {
                    nextTime = Math.min(nextTime, workRight.peek()[0]);
                }
                
                if (nextTime != Integer.MAX_VALUE) {
                    curTime = Math.max(curTime, nextTime);
                }
            }
        }
        
        return curTime;
    }
}
```