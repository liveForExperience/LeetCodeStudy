# [LeetCode_807_保持城市天际线](https://leetcode-cn.com/problems/max-increase-to-keep-city-skyline/)
## 解法
### 思路
- 分别求出二维数组的横和纵的最大值
- 遍历二维数组，将横和纵最大值之间的最小值与当前值相减，累加这个相减的值
### 代码
```java
class Solution {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        int[] rowMax = new int[row], colMax = new int[col];

        for (int i = 0; i < row; i++) {
            rowMax[i] = Arrays.stream(grid[i]).max().getAsInt();
        }

        for (int i = 0; i < col; i++) {
            int max = 0;
            for (int j = 0; j < row; j++) {
                max = Math.max(max, grid[j][i]);
            }
            colMax[i] = max;
        }

        int sum = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                sum += Math.min(rowMax[i], colMax[j]) - grid[i][j];
            }
        }
        
        return sum;
    }
}
```
# [LeetCode_689_三个无重叠子数组的最大和](https://leetcode-cn.com/problems/maximum-sum-of-3-non-overlapping-subarrays/)
## 解法
### 思路
- [解法参考](https://leetcode-cn.com/problems/maximum-sum-of-3-non-overlapping-subarrays/solution/cpp-dong-tai-gui-hua-si-lu-qing-xi-dai-m-izh9/)
- 时间复杂度是O(N)
- 先生成前缀和，通过这个前缀和快速获取到所有K长度子数组的总和
- 分别从左和右生成i位置的子数组的左边和右边的最大子数组起始位置，需要注意
  - 子数组之间不能有重复部分，所以间隔一定是K
  - 因为总和相等的时候，会对字典序进行排序，所以:
    - 求左边最大的时候，比较的是大于max的值，大才会更新坐标
    - 求右边最大的时候，比较的是大于等于max的值，符合的时候更新坐标
- 最后遍历之前的子数组和的那个数组，求能生成最大值的那个组合
- 遍历结束后返回记录的那个组合
### 代码
```java
class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] sums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }

        int[] arr = new int[n - k + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sums[i + k] - sums[i];
        }

        int[] left = new int[arr.length],
              right = new int[arr.length],
              ans = new int[3];

        Arrays.fill(left, -1);
        Arrays.fill(right, -1);

        int max = -1, maxIndex = -1;
        for (int i = k; i < left.length; i++) {
            if (arr[i - k] > max) {
                max = arr[i - k];
                maxIndex = i - k;
            }

            left[i] = maxIndex;
        }

        max = -1;
        maxIndex = -1;
        for (int i = right.length - 1 - k; i >= 0; i--) {
            if (arr[i + k] >= max) {
                max = arr[i + k];
                maxIndex = i + k;
            }

            right[i] = maxIndex;
        }

        max = -1;
        for (int i = 0; i < arr.length; i++) {
            if (left[i] == -1 || right[i] == -1) {
                continue;
            }

            int sum = arr[i] + arr[left[i]] + arr[right[i]];
            if (sum > max) {
                max = sum;
                ans[0] = left[i];
                ans[1] = i;
                ans[2] = right[i];
            }
        }

        return ans;
    }
}
```
# [LeetCode_690_课程表III](https://leetcode-cn.com/problems/course-schedule-iii/)
## 解法
### 思路
- 如果2门课的结束时间有早晚，d1 <= d2
  - 如果先学习d2，再学习d1，这种情况是符合的，那么先学习d1，再学习d2也肯定是可以的
  - 但是如果先学习d1在学习d2成立，却无法退出先学习d2再学习d1成立
- 
### 代码
```java
class Solution {
  public int scheduleCourse(int[][] courses) {
    Arrays.sort(courses, Comparator.comparingInt(x -> x[1]));
    Queue<Integer> queue = new PriorityQueue<>((x, y) -> y - x);

    int start = 0;
    for (int[] course : courses) {
      int duration = course[0], end = course[1];

      start += duration;
      queue.offer(duration);

      if (start > end && !queue.isEmpty()) {
        start -= queue.poll();
      }
    }

    return queue.size();
  }
}
```
# [LeetCode_851_喧闹与富有](https://leetcode-cn.com/problems/loud-and-rich/)
## 解法
### 思路
拓扑排序
- 根据贫富关系二维数组，生成对应的邻接表和入度计数表
- 初始化队列，将入度为0的元素放入队列中
- 初始化ans数组，所有人初始都指向自己为最安静的人
- 遍历队列
  - 取出当前未处理的相对富有的人
  - 再遍历所有人，查看是否有和当前富有人形成贫富关系的相对穷人
  - 如果有，判断当前富人指向的最安静的人是否比当前穷人的更安静，如果是就更新。因为是从相对富bfs到相对穷，所以这个安静的人已经是搜索路径上目前为止最安静的。
  - 同时更新入度表，将穷人的入度数减1
  - 查看当前穷人是否已经入度为0，如果是，说明他也成为相对富有的人，将其放入队列中，继续后面循环的处理
- 遍历结束，返回ans数组即可
### 代码
```java
class Solution {
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int n = quiet.length;
        int[][] w = new int[n][n];
        int[] in = new int[n];
        
        for (int[] rich : richer) {
            w[rich[0]][rich[1]] = 1;
            in[rich[1]]++;
        }
        
        Queue<Integer> queue = new ArrayDeque<>();
        int[] ans = new int[n];
        for (int i = 0; i < in.length; i++) {
            ans[i] = i;
            if (in[i] == 0) {
                queue.offer(i);
            }
        }
        
        while (!queue.isEmpty()) {
            int rich = queue.poll();
            for (int i = 0; i < n; i++) {
                if (w[rich][i] == 1) {
                    if (quiet[ans[rich]] < quiet[ans[i]]) {
                        ans[i] = ans[rich];
                    }
                    
                    if (--in[i] == 0) {
                        queue.offer(i);
                    }
                }
            }
        }
        
        return ans;
    }
}
```
# [LeetCode_1518_换酒问题](https://leetcode-cn.com/problems/water-bottles/)
## 解法
### 思路
循环模拟：
- 初始化2个变量
  - sum记录已经喝了的瓶数
  - empty记录当前的空瓶数
- 循环
  - 条件：empty >= numExchange
  - 过程：
    - sum累加当前空瓶可以兑换的新酒数drink
    - empty = drink + 剩下不能兑换的空瓶数
- 循环结束后返回sum
### 代码
```java
class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        int sum = numBottles, empty = numBottles;
        while (empty >= numExchange) {
            int drink = empty / numExchange;
            sum += drink;
            empty = drink + empty % numExchange;
        }
        return sum;
    }
}
```
# [LeetCode_911_在线选举](https://leetcode-cn.com/problems/online-election/)
## 解法
### 思路
二分查找：
- 初始化
  - 遍历person数组，统计变化过程中的得票数最大值
  - 当出现当前得票数与最大值一样或更大的时候，将内容记录下来（之所以在一样的是有也要记录下来，因为题目要求得票数一样的时候，将最近的人员作为得票数最高的那个人）
  - 记录的内容为，list中的一个一维2个元素的数组，第一个元素是时间，第二个元素记录的是人员坐标
- 查询：
  - 使用二分查找，找到比当前查询时间更小或相等的的最近的变化点，如果没有，就说明大家还都是0的状态，就直接返回0
  - 找到后，返回数组的第二个元素即可
### 代码
```java
class TopVotedCandidate {
    private List<int[]> list = new ArrayList<>();
    public TopVotedCandidate(int[] persons, int[] times) {
        int n = times.length, val = 0;
        Map<Integer, Integer> mapping = new HashMap<>();
        for (int i = 0; i < n; i++) {
            mapping.put(persons[i], mapping.getOrDefault(persons[i], 0) + 1);
            if (mapping.get(persons[i]) >= val) {
                val = mapping.get(persons[i]);
                list.add(new int[]{times[i], persons[i]});
            }
        }
    }

    public int q(int t) {
        int head = 0, tail = list.size() - 1;
        while (head < tail) {
            int mid = head + tail + 1 >> 1;
            if (list.get(mid)[0] <= t) {
                head = mid;
            } else {
                tail = mid - 1;
            }
        }

        return list.get(tail)[0] <= t ? list.get(tail)[1] : 0;
    }
}
```
# [LeetCode_419_甲板上的战舰](https://leetcode-cn.com/problems/battleships-in-a-board/)
## 解法
### 思路
dfs
### 代码
```java
class Solution {
  private final int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

  public int countBattleships(char[][] board) {
    int row = board.length, col = board[0].length, count = 0;
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (board[i][j] == 'X') {
          count++;
          dfs(board, row, col, i, j);
        }
      }
    }

    return count;
  }

  private void dfs(char[][] board, int row, int col, int r, int c) {
    if (r < 0 || r >= row || c < 0 || c >= col || board[r][c] != 'X') {
      return;
    }

    board[r][c] = '.';

    for (int[] direction : directions) {
      dfs(board, row, col, r + direction[0], c + direction[1]);
    }
  }
}
```
## 解法二
### 思路
- 基于解法一，发现其实不需要做深度优先的状态翻转
- 因为遍历过程是从上往下，从左往右，所以一艘舰船的左上方的标记一定是最先被发现的
- 又因为舰船之间是有空隙的，只需要统计第一次碰到的X就可以
- 为了只记录第一次的X，就需要当前出现X的位置，它的上方或左边不是X即可
### 代码
```java
class Solution {
    public int countBattleships(char[][] board) {
        int row = board.length, col = board[0].length, count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'X' && (i == 0 || board[i - 1][j] != 'X') && (j == 0 || board[i][j - 1] != 'X')) {
                    count++;
                }
            }
        }
        
        return count;
    }
}
```
# [LeetCode_997_找到小镇的法官](https://leetcode-cn.com/problems/find-the-town-judge/)
## 解法
### 思路
- 根据数组生成入度表和出度表
- 遍历人员数组，找到入度表为n-1，出度表为0的元素返回
- 遍历结束则说明没有，返回-1
### 代码
```java
class Solution {
  public int findJudge(int n, int[][] trust) {
    int[] ins = new int[n], outs = new int[n];
    for (int[] arr : trust) {
      outs[arr[0] - 1]++;
      ins[arr[1] - 1]++;
    }

    for (int i = 0; i < n; i++) {
      if (ins[i] == n - 1 && outs[i] == 0) {
        return i + 1;
      }
    }

    return -1;
  }
}
```
