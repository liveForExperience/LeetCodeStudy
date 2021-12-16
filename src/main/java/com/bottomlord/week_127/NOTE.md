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
[解法](https://leetcode-cn.com/problems/course-schedule-iii/solution/gong-shui-san-xie-jing-dian-tan-xin-yun-ghii2/)
### 代码
```java

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