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
# [LeetCode_1953_你可以工作的最大周数](https://leetcode.cn/problems/maximum-number-of-weeks-for-which-you-can-work)
## 解法
### 思路
- 初始化变量`day`，用于记录答案需要的最大天数
- 对`milestones`进行升序排序
- 遍历`milestones`，每次循环时处理如下逻辑：
  - 当前元素为`x`，对`day`值进行累加，公式为`day += 2 * x`
  - 后一个元素为`y`，通过`y`与`x`的相减，将后一个元素变更为`y -= x`
- 如果遍历结束后，最后一个元素大于0，则说明违反了题目规则，返回-1，否则，返回`day`值
### 代码
```java

```
# [LeetCode_826_安排工作以达到最大收益](https://leetcode.cn/problems/most-profit-assigning-work)
## 解法
### 思路
- 使用一个二维数组`matrix`记录2个数组中所有`difficuly`和`profit`之间的映射关系
  - 对`matrix`数组进行排序，排序规则为`difficulty`升序，排序后获取最大回报的过程就变成了线性的
    - 只要`work`值比当前难度大，则比左侧所有难度都大
    - 只要`work`值闭当前难度小，则闭右侧所有难度都小
  - 对`matrix`的`profit`进行处理，通过遍历`matrix`数组，并暂存遍历过元素的最大值的方式，将当前元素与当前暂存的最大值进行比较，取其中的较大致，并将其存储在当前`difficulty`所对应的`profit`位置，从而用该元素来代表难度为当前值的时候，可以得到的最大的`profit`
- 对`work`数组进行排序，排序规则为升序
- 完成如上2个排序后，获取每个工人所工作最大收益的方式就是：
  - 初始化变量`idx`，用于记录已经遍历过的`matrix`对，`idx`初始化为0，代表从第一个元素开始遍历
  - 初始化变量`ans`，存储累加的`profit`
  - 遍历`work`数组，求取每个工人能获得的最大`profit`
  - 基于`idx`当前值开始遍历`matrix`，判断当前`matrix`对中的`difficulty`是否小于等于`work`元素，如果小于，就累加`idx`，直到大于为止
  - 如果`idx`仍然为0，代表不存在可以做的工作，此时就跳过，否则就累加`profit`，`profit`是基于`matrix[idx - 1]`的数组对而来，`idx - 1`是因为目前`idx`对应的是第一个`difficulty`大于`work`元素值的数组对，故要-1
- 循环结束后，返回`ans`即可
### 代码
```java
class Solution {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int n = difficulty.length;

        int[][] matrix = new int[n][2];
        for (int i = 0; i < n; i++) {
            matrix[i][0] = difficulty[i];
            matrix[i][1] = profit[i];
        }
        Arrays.sort(matrix, Comparator.comparingInt(x -> x[0]));
        Arrays.sort(worker);

        int max = -1;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, matrix[i][1]);
            matrix[i][1] = max;
        }

        int ans = 0, idx = 0;
        for (int w : worker) {
            while (idx < n && matrix[idx][0] <= w) {
                idx++;
            }

            if (idx == 0) {
                continue;
            }

            ans += matrix[idx - 1][1];   
        }
        
        return ans;
    }
}
```
# [LeetCode_1535_找出数组游戏的赢家](https://leetcode.cn/problems/find-the-winner-of-an-array-game)
## 解法
### 思路
- 题目实际要求的是某个元素`x`与下一个比自己大的元素之间，是否有大于等于k个比该元素小的元素存在（包括本身），如果存在，则直接`x`
- 且，如果当前`arr`排列中，不包含如上情况的排列，则直接返回数组中最大值即可，因为按照题目要求，当遇到数组最大值后，只要循环足够多次，一定能得到k次连续大于的情况
- 故，只需要遍历一次数组，按照如上2条的方式模拟计算，即可。
### 代码
```java
class Solution {
    public int getWinner(int[] arr, int k) {
        int max = arr[0], cnt = 0;
        for (int i = 1; i < arr.length; i++) {
            int num = arr[i];
            
            if (num > max) {
                max = num;
                cnt = 1;

                if (cnt == k) {
                    return num;
                }
                continue;
            }
            
            cnt++;
            
            if (cnt == k) {
                return max;
            }
        }

        return max;
    }
}
```
# [LeetCode_2903_找出满足差值条件的下标I](https://leetcode.cn/problems/find-indices-with-index-and-value-difference-i)
## 解法
### 思路
2层循环
- 外层从0开始遍历`nums`数组，遍历的坐标为`i`
- 内层从`i + indexDifference`开始，遍历的坐标为`j`
- 基于如上的遍历规则，则`i`和`j`符合了题目的第一个要求，即`abs(i - j) >= indexDifference`
- 然后在内层判断`abs(nums[i] - nums[j])`是否符合，如果符合则直接返回坐标`i`和`j`组成的数组即可
- 否则，遍历结束后，代表未找到匹配的坐标，返回数组`[-1, -1]`即可
### 代码
```java
class Solution {
    public int[] findIndices(int[] nums, int indexDifference, int valueDifference) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + indexDifference; j < n; j++) {
                if (Math.abs(nums[i] - nums[j]) >= valueDifference) {
                    return new int[]{i, j};
                }
            }
        }
        
        return new int[]{-1, -1};
    }
}
```