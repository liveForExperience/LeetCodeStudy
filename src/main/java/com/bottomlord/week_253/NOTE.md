# [LeetCode_994_腐烂的橘子](https://leetcode.cn/problems/rotting-oranges)
## 解法
### 思路
bfs
- 初始化如下变量：
  - 一个队列`queue`，用于驱动bfs，元素存储的是腐烂橘子坐标
  - 一个整数变量`left`，用于记录新鲜橘子的个数
  - 一个整数变量`minute`，用于记录感染的分钟数
- 遍历矩阵数组
  - 将值为2的代表腐烂橘子的坐标放入`queue`中，同时将坐标记录在`memo`中
  - 遇到一个值为1的元素，则将`left`累加
- 遍历`queue`，直到`queue`为空为止
  - 获取`queue`中的元素个数`cnt`
  - 遍历`cnt`次，将元素从`queue`中取出，然后从4个方向遍历可能影响的坐标
    - 如果坐标越界，则跳过
    - 如果新坐标上没有新鲜橘子（即坐标值为1），则跳过
    - 如果新坐标上对应的是腐烂的橘子，则跳过
  - 如果是新鲜橘子，则每碰到一个就将`left`累减1，并将该坐标放入`queue`中作为新的准备感染其他橘子的腐烂橘子，同时将`grid`对应坐标的值变更为2
  - `cnt`次遍历完后，如果`queue`不为空，则代表将进行一分钟的感染过程，此时将`minute`累加1
- 遍历结束，如果`left`不为0，返回-1，否则返回`minute`
### 代码
```java
class Solution {
    private int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int orangesRotting(int[][] grid) {
        int row = grid.length, col = grid[0].length, left = 0, minute = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    left++;
                } else if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        while (!queue.isEmpty()) {
            int cnt = queue.size();
            while (cnt-- > 0) {
                int[] cur = queue.poll();
                if (cur == null) {
                    continue;
                }

                for (int[] direction : directions) {
                    int nr = cur[0] + direction[0], nc = cur[1] + direction[1];
                    if (outOfBound(nr, row, nc, col) || grid[nr][nc] == 0 || grid[nr][nc] == 2) {
                        continue;
                    }

                    grid[nr][nc] = 2;
                    left--;
                    queue.offer(new int[]{nr, nc});
                }
            }

            if (!queue.isEmpty()) {
                minute++;
            }
        }

        return left != 0 ? -1 : minute;
    }
    
    private boolean outOfBound(int r, int row, int c, int col) {
        return r < 0 || r >= row || c < 0 || c >= col; 
    }
}
```
# [LeetCode_2589_完成所有任务的最少时间](https://leetcode.cn/problems/minimum-time-to-complete-all-tasks)
## 解法
### 思路
- 对`task`进行排序，排序规则是以二维数组的第二个元素的升序规则进行排序，也即以右闭区间升序排序
- 排序后，2个相邻的区间，前一个区间与后一个区间的交集一定是前一个区间靠后的部分，所以，如果要安排任务，应该尽量安排在有交集的位置，使该为止能尽可能多地运行任务
- 因为题目的样本空间范围有限，可以通过遍历数组的方式，一个个的判断每个区间中任务的分布，使其尽可能的重复利用已经使用过的时间区间
- 为了能方便快速找到使用过的区间，需要初始化一个布尔数组，用于记录哪些区间是被使用过的
- 然后在判断每个区间的时候，优先将`duration`分配到已经使用过的区间中（区间需要在当前区间的范围内）
- 然后再将剩下的`duration`分配在当前区间的右边界部分，并通过布尔数组进行记录，同时没分配一个新的区间，就记录一次空间使用情况，也即计数
- 遍历结束后，返回计数值
### 代码
```java
class Solution {
    public int findMinimumTime(int[][] tasks) {
        Arrays.sort(tasks, Comparator.comparingInt(x -> x[1]));
        int max = tasks[tasks.length - 1][1];
        boolean[] memo = new boolean[max + 1];
        int ans = 0;
        for (int[] task : tasks) {
            int start = task[0], end = task[1], duration = task[2];
            for (int i = start; i <= end; i++) {
                if (memo[i]) {
                    duration--;
                }
            }

            for (int i = end; i > 0 && duration > 0 ; i--) {
                if (!memo[i]) {
                    memo[i] = true;
                    duration--;
                    ans++;
                }
            }
        }
        
        return ans;
    }
}
```