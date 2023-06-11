# [LeetCode_2352_相等行列对](https://leetcode.cn/problems/equal-row-and-column-pairs/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public int equalPairs(int[][] grid) {
        int n = grid.length;
        Map<Integer, List<Integer>> colMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            colMap.computeIfAbsent(grid[0][i], x -> new ArrayList<>()).add(i);
        }

        int count = 0;
        for (int row = 0; row < n; row++) {
            int start = grid[row][0];
            List<Integer> cols = colMap.get(start);
            if (cols == null) {
                continue;
            }
            
            for (Integer col : cols) {
                boolean flag = true;
                for (int j = 0; j < n; j++) {
                    if (grid[j][col] != grid[row][j]) {
                        flag = false;
                        break;
                    }
                }
                
                if (flag) {
                    count++;
                }
            }
        }
        
        return count;
    }
}
```
# [LeetCode_2611_奶酪和老鼠](https://leetcode.cn/problems/mice-and-cheese/)
## 解法
### 思路
- 先算出第二只老鼠吃所有的奶酪的总得分
- 同时求出第一只老鼠吃当前奶酪与第二只老鼠吃的时候的得分差值
- 为了能得到最大值，其实就是求差值的最大值，差值越大，说明第一只老鼠吃的时候能得到更高的分数
- 然后对差值数组做降序排序
- 遍历排序后的差值数组，累加到之前的总和中作为结果
### 代码
```java
class Solution {
    public int miceAndCheese(int[] reward1, int[] reward2, int k) {
        int sum = 0, n = reward1.length;
        Integer[] diffs = new Integer[n];
        for (int i = 0; i < n; i++) {
            diffs[i] = reward1[i] - reward2[i];
            sum += reward2[i];
        }
        
        Arrays.sort(diffs, (x, y) -> y - x);

        for (int i = 0; i < k; i++) {
            sum += diffs[i];
        }
        
        return sum;
    }
}
```
# [LeetCode_2517_礼盒的最大甜蜜度](https://leetcode.cn/problems/maximum-tastiness-of-candy-basket/)
## 解法
### 思路
- 遇到最小值求最大或者最大值求最小，可以直接反应使用二分查找
- 此处对答案，即差值的最小绝对值进行二分查找
- 初始确定答案的最大可能，即[0, max]，max是最小和最大元素差值的绝对值
- 然后在这个区间中进行二分查找，确定一个可能答案
  - 这个可能答案成立的条件：在数组中有至少k个差值大于等于当前值的元素对
  - 所以为了能够在O(N)时间复杂度下获取到这个判断结果，可以先将数组进行排序，然后依次遍历数组元素，将两两差值大于可能答案的元素对个数记录下来
  - 可以使用一个前置值变量，这个前置值代表了两两元素对的前一个较小元素，同时在统计完一对之后将较大值更新为较小值，继续判断
  - 该值可以初始化为int最小值的一半，这样方便计算
- 如果这个目标值成立，那么就往较大区间继续寻找，否则就往较小区间查找
- 注意：取中间值时为了防止java整型向下取整的特性，所以需要额外+1
### 代码
```java
class Solution {
    public int maximumTastiness(int[] prices, int k) {
        Arrays.sort(prices);
        int tail = prices[prices.length - 1] - prices[0], head = 0;
        
        while (head < tail) {
            int mid = (tail - head + 1) / 2 + head;
            
            if (check(prices, mid, k)) {
                head = mid;
            } else {
                tail = mid - 1;
            }
        }
        
        return head;
    }
    
    private boolean check(int[] prices, int target, int k) {
        int pre = Integer.MIN_VALUE / 2, count = 0;
        for (int price : prices) {
            if (price - pre >= target) {
                count++;
                pre = price;
            }
        }
        
        return count >= k;
    }
}
```
# [LeetCode_1240_1_铺瓷砖](https://leetcode.cn/problems/tiling-a-rectangle-with-the-fewest-squares/)
## 解法
### 思路
回溯
- 题目要求的是最少的瓷砖个数
- 看到求最值，第一反应是动态规划，但是这个问题一直没有想到清晰的状态转移方程，所以退而求其次：`万事不决，回溯来解`
- 题目提供了长m和宽n，意味着瓷砖的大小是可以自己随意指定的，因为要求的是最少个数，所以每次如果能尽可能使用最大的瓷砖，那么也就意味着瓷砖数是最少的
- 在定义回溯过程的时候，每一次都从当前状态下最大可能面积的瓷砖开始向面积小的方向遍历
- 而又因为题目要求瓷砖是正方形的，所以此处的面积其实就可以理解成边长，也就是每次确定一个最大边长，然后不断减小边长来尝试
- 回溯的过程就是向列的右侧遍历，如果列坐标越界，就换下一行继续
- 如果行坐标越界，就代表回溯完成，这时候可以更新最小值，可以使用一个类变量来记录处理过程中得到的这个最小值cur，这个最小个数初始可以是m*n（即所有瓷砖都是1*1的）
- 而基于这个cur变量，可以进行减枝，回溯过程中一定会需要记录当前已使用的瓷砖个数，如果瓷砖个数大于等于当前已存在的最小个数值，那就可以减枝掉
- 接着是如何做记事本的记录，这里的记事本与通常回溯的不太一样，此处是需要记录2维数据，一维是横坐标，一维是纵坐标，记事本的含义即当前横坐标、纵坐标对应的点位已经被瓷砖覆盖
- 为了记录这个状态，在记录过程中需要2层循环做状态的更新，回溯回来之后，也需要通过相同的过程进行取消
- 有了记录，那么减枝的另一种情况就是，当前坐标如果已经记录在记事本中，就减枝掉
### 代码
```java
class Solution {
    private int min, r, c;
    private boolean[][] memo;
    public int tilingRectangle(int n, int m) {
        this.min = m * n;
        this.r = n;
        this.c = m;
        this.memo = new boolean[r][c];

        backTrack(0, 0, 0);
        return min;
    }

    private void backTrack(int x, int y, int cur) {
        if (cur >= min) {
            return;
        }

        if (x >= r) {
            min = cur;
            return;
        }

        if (y >= c) {
            backTrack(x + 1, 0, cur);
            return;
        }

        if (memo[x][y]) {
            backTrack(x, y + 1, cur);
            return;
        }

        int len = Math.min(r - x, c - y);
        while (len > 0 && !isValid(x, y, len)) {
            len--;
        }

        for (; len > 0; len--) {
            record(x, y, len, true);
            backTrack(x, y + len, cur + 1);
            record(x, y, len, false);
        }
    }

    private boolean isValid(int x, int y, int len) {
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (memo[x + i][y + j]) {
                    return false;
                }
            }
        }

        return true;
    }

    private void record(int x, int y, int len, boolean flag) {
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                memo[x + i][y + j] = flag;
            }
        }
    }
}
```