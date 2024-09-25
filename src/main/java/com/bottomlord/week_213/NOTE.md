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
# [LeetCode_1289_下降路径最小和II](https://leetcode.cn/problems/minimum-falling-path-sum-ii/description/)
## 解法
### 思路
- 动态规划
- 逻辑过程是：
  - 初始化一个记事本数组dp，数组每个坐标i代表当前行i列为路径最后位置的最短距离
  - dp初始化为`grid[0]`
  - 从第二行开始遍历grid，内层继续循环当前`grid[i]`
  - 内层循环的处理逻辑是：
    - 初始化一个与`grid[i]`长度相同的空数组arr 
    - 遍历dp数组中与当前列j不同的所有坐标元素`dp[k], k != j`，找到最小值并累加`grid[i][j]`，暂存在`arr[j]`位置
    - 内层遍历结束，将arr赋值给dp
  - 遍历结束后，返回dp数组中的最小值
### 代码
```java
class Solution {
    public int minFallingPathSum(int[][] grid) {
        int n = grid[0].length;
        int[] dp = grid[0];

        for (int i = 1; i < grid.length; i++) {
            int[] arr = new int[n];
            for (int j = 0; j < n; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = 0; k < n; k++) {
                    if (j == k) {
                        continue;
                    }

                    min = Math.min(min, dp[k]);
                }
                arr[j] = min + grid[i][j];
            }
            dp = arr;
        }

        int ans = Integer.MAX_VALUE;
        for (int num : dp) {
            ans = Math.min(num, ans);
        }
        return ans;
    }
}
```
# [LeetCode_23_合并K个升序链表](https://leetcode.cn/problems/merge-k-sorted-lists/description/)
## 解法
### 思路
- 初始化一个优先级队列
  - 存储元素为链表节点
  - 比较规则为节点值的非降序排列
- 将K个链表的头节点依次放入优先级队列中
- 初始化一个fake头作为结果链表的起始节点
- 遍历优先级队列，退出条件是队列为空
- 依次将弹出的节点拼接到fake及后续节点之后
- 遍历结束返回fake的next指针对应的节点即可
### 代码
```java
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode fake = new ListNode(0), cur = fake;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(x -> x.val));
        for (ListNode node : lists) {
            if (node == null) {
                continue;
            }
            queue.offer(node);
        }
        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            cur.next = node;
            cur = cur.next;
            if (node.next != null) {
                queue.offer(node.next);
                node.next = null;
            }
        }
        
        return fake.next;
    }
}
```