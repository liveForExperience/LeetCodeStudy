# [LeetCode_2008_出租车的最大盈利](https://leetcode.cn/problems/maximum-earnings-from-taxi)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_2646_最小化旅行的价格总和](https://leetcode.cn/problems/minimize-the-total-price-of-the-trips)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_2336_无限集中的最小数字](https://leetcode.cn/problems/smallest-number-in-infinite-set)
## 解法
### 思路
使用TreeSet
### 代码
```java

```
# [LeetCode_1631_最小体力消耗路径](https://leetcode.cn/problems/path-with-minimum-effort)
## 解法
### 思考
- 思考过程：
  - 把题目看成一张图，体力差的绝对值是边的权重
  - 然后通过二分查找的思路，确定一个体力差上限`limit`，基于这个`limit`做减枝判断，如果超过`limit`，如果没办法到达，就扩大`limit`，否则只要有任意条路径到达，就记录该值，并缩小`limit`，直到查找结束
- 算法过程：
  - 初始化二分查找的头尾值，值对应的是题目要求的体力差
    - head：0
    - tail: 元素最大值与最小值的差
  - 进入二分查找循环，继续条件是`head <= tail`，`基于确定的`limit = head + (tail - head) / 2`，进行dfs
  - 根据bfs的返回结果判断二分查找的方向
    - 如果能搜索到终点，返回该次最小体力差值，搜索区间向较小区间移动。同时记录该返回值作为暂时的答案值
    - 如果不能搜索到终点，返回int最大值，搜索区间向较大区间移动
  - 搜索结果返回暂存的结果
### 代码
```java
class Solution {
  private int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

  public int minimumEffortPath(int[][] heights) {
    int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    for (int[] height : heights) {
      for (int cur : height) {
        min = Math.min(cur, min);
        max = Math.max(cur, max);
      }
    }

    int head = 0, tail = max - min, ans = max - min;

    while (head <= tail) {
      int limit = head + (tail - head) / 2;

      if (bfs(heights, limit)) {
        ans = limit;
        tail = limit - 1;
      } else {
        head = limit + 1;
      }
    }

    return ans;
  }

  private boolean bfs(int[][] heights, int limit) {
    int r = heights.length, c = heights[0].length;
    boolean[][] memo = new boolean[r][c];
    Queue<int[]> queue = new ArrayDeque<>();
    queue.offer(new int[]{0, 0});
    memo[0][0] = true;
    while (!queue.isEmpty()) {
      int cnt = queue.size();
      while (cnt-- > 0) {
        int[] arr = queue.poll();
        if (arr == null) {
          continue;
        }

        int x = arr[0], y = arr[1];

        for (int[] dir : dirs) {
          int nx = x + dir[0], ny = y + dir[1];
          if (!valid(heights, nx, ny, x, y, limit, memo)) {
            continue;
          }

          memo[nx][ny] = true;
          queue.offer(new int[]{nx, ny});
        }
      }
    }

    return memo[r - 1][c - 1];
  }

  private boolean valid(int[][] heights, int x, int y, int px, int py, int limit, boolean[][] memo) {
    return x >= 0 && x < heights.length && y >= 0 && y < heights[0].length && !memo[x][y] && Math.abs(heights[x][y] - heights[px][py]) <= limit;
  }
}
```
# [LeetCode_2454_下一个更大元素IV](https://leetcode.cn/problems/next-greater-element-iv)
## 解法
### 思路
- 思考过程：
  - 题目没有要求第一个和第二个元素之间的大小关系，所以只要找到2个比当前元素大后续元素即可
  - 可以通过单调栈来获取第一个比当前元素大的元素
    - 通过单调栈维护一个单调递减的序列
    - 在遍历数组的过程中，如果当前元素不比栈顶元素大，那么就可以将元素压入栈中，继续维护这个单调递减的序列
    - 如果遍历到的元素比栈顶元素大，栈顶元素会被弹出，同时基于栈顶元素坐标值将当前元素记录为他的下一个更大元素
    - 能够这么记录的原因是：栈顶元素只会在遇到第一个大的元素的时候被弹出，也就说明，只要在栈中，就一定没有遇到过比他大的元素
  - 那么如果这个栈顶元素被弹出，说明它找到了第一个更大元素了，那么也就意味着这个元素是有可能找到第二个更大元素的，所以可以将这个元素也用一个数据结构存储起来
  - 通过思考可知，通过小顶堆，将这些找到了第一个更大元素的坐标存储起来，那么在使用小顶堆判断与当前元素之间的关系的时候，就可以比较方便的模仿单调栈的操作，一路循环下去，直到堆顶元素不符合出队要求为止
  - 判断小顶堆堆顶元素的逻辑和单调栈一样，如果当前元素比小顶堆的元素大，那么当前元素就是这个坐标的第二大元素了
- 算法过程：
  - 初始化一个栈`stack`和一个小顶堆`queue`
    - `queue`存储`nums`坐标，但比较器是比较坐标对应的值
  - 初始化一个结果数组`ans`
    - 元素值统一赋值为-1
  - 遍历数组
    - 获得当前元素`cur`
    - 如果`queue`不为空，且堆顶坐标对应的元素比`cur`小，那么将堆顶坐标出队，并更新该坐标对应的值为`cur`，并继续循环判断
    - 如果`stack`不为空，且栈顶坐标对应的元素比`cur`小，那么将坐标出栈后放入`queue`，循环该过程，直到将`cur`对应的坐标压入栈中
  - 遍历结束后返回`ans`
### 代码
```java
class Solution {
    public int[] secondGreaterElement(int[] nums) {
        LinkedList<Integer> stack = new LinkedList<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(x -> nums[x]));
        int[] ans = new int[nums.length];
        Arrays.fill(ans, -1);
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            while (!queue.isEmpty() && nums[queue.peek()] < cur) {
                ans[queue.poll()] = cur; 
            }
            
            while (!stack.isEmpty() && nums[stack.peek()] < cur) {
                queue.offer(stack.pop());
            }
            
            stack.push(i);
        }
        
        return ans;
    }
}
```