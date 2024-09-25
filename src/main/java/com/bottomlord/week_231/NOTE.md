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
# [LeetCode_2048_下一个更大的数值平衡数](https://leetcode.cn/problems/next-greater-numerically-balanced-number)
## 解法
### 思路
- 思考过程：
  - 从`n + 1`开始遍历，依次判断当前数字是否符合题目要求
- 算法过程：
  - 死循环遍历
  - 依次对n做累加，同时判断累加后的值是否符合题目要求
    - 使用一个长度为10的数组`bucket`记录当前数值上每一位值对应的出现次数
    - 遍历判断的数，依次截断每一位进行记录，如果遇到0，则直接返回false。因为0这个数字应该出现0次:)
    - 截断处理结束后，遍历`bucket`，判断坐标（也就是位上的数字）与值是否相等，如果不相等就返回false
    - 遍历结束，返回true，说明每一个数值都是符合要求的
### 代码
```java
class Solution {
    public int nextBeautifulNumber(int n) {
        while (true) {
            n++;
            if (valid(n)) {
                return n;
            }
        }
    }
    
    private boolean valid(int n) {
        int[] bucket = new int[10];
        while (n > 0) {
            int cur = n % 10;
            if (cur == 0) {
                return false;
            }
            
            bucket[cur]++;
            n /= 10;
        }

        for (int i = 1; i < bucket.length; i++) {
            if (bucket[i] == 0) {
                continue;
            }
            
            if (bucket[i] != i) {
                return false;
            }
        }
        
        return true;
    }
}
```
# [LeetCode_2415_反转二叉树的奇数层](https://leetcode.cn/problems/reverse-odd-levels-of-binary-tree)
## 解法
### 思路
- 思考过程：
  - bfs遍历整棵树，在需要反转的奇数层，基于其上层和下层，进行反转
    - 遍历上层的节点，依次将反转后节点重新赋值给上层的左右指针
    - 遍历当前反转后的节点列表，将原来的节点依次赋值给当前节点的左右指针
- 算法过程：
  - 初始化一个布尔控制变量，用于判断bfs过程中当前层的奇偶性
  - bfs完美二叉树
    - bfs的元素需要传递3部分内容
      - 父节点
      - 当前节点
      - 左右子节点
    - 如果当前层为奇数层，那么在遍历的过程中，将当前层的节点存储在列表中，然后做如下几件事
      - 将当前层节点的父节点存储在列表中
      - 将当前层节点的左右子节点按顺序存储在列表中
      - 将当前层的节点倒序排列
      - 遍历倒序后的节点，将对应的父节点和左右子节点重新绑到当前新的顺序的节点上
  - bfs结束返回根节点即可
### 代码
```java
class Solution {
    public TreeNode reverseOddLevels(TreeNode root) {
        Queue<TreeNode[]> queue = new ArrayDeque<>();
        boolean flag = false;
        queue.offer(new TreeNode[]{null, root});
        while (!queue.isEmpty()) {
            int count = queue.size();

            List<TreeNode> parents = new ArrayList<>(),
                           nodes = new ArrayList<>(),
                           children = new ArrayList<>();
            while (count-- > 0) {
                TreeNode[] arr = queue.poll();
                if (arr == null) {
                    continue;
                }

                parents.add(arr[0]);
                TreeNode node = arr[1];
                nodes.add(node);

                if (node.left != null) {
                    children.add(node.left);
                    queue.offer(new TreeNode[]{node, node.left});
                }

                if (node.right != null) {
                    children.add(node.right);
                    queue.offer(new TreeNode[]{node, node.right});
                }
            }

            if (flag) {
                Collections.reverse(nodes);
                for (int i = 0; i < nodes.size(); i++) {
                    TreeNode node = nodes.get(i);
                    if (i % 2 == 0) {
                        parents.get(i).left = node;
                    } else {
                        parents.get(i).right = node;
                    }
                    
                    if (children.isEmpty()) {
                        continue;
                    }
                    
                    node.left = children.get(i * 2);
                    node.right = children.get(i * 2 + 1);
                }
            }

            flag = !flag;
        }
        
        return root;
    }
}
```
# [LeetCode_2276_统计区间中的整数数目](https://leetcode.cn/problems/count-integers-in-intervals)
## 解法
### 思路
- 思考过程：
  - 题目要求实现的类，具备新增和维护区间，以及返回区间元素个数的功能
  - 维护的区间在新增新的区间的过程中，会出现重合、覆盖等情况
  - 而个数，其实通过枚举区间的大小就能得到
  - 为了在返回个数时的时间复杂度最低，所以需要尽量避免重合、覆盖这种情况导致的重复计算，甚至最好能在新增区间的时候同步将个数维护好，返回个数时直接返回暂存的变量就可以了
  - 为了实现这个目的，可以通过一个能根据区间边界，快速获取已有与之重合区间的数据结构，显然`TreeMap`这种带有有序性特性的平衡数可以实现这种需要
  - 在实现新增区间功能时，可以通过去除旧的关联区间，更新需要新增的新区建的边界并新增该区间的方式，来维护
  - 而个数，就可以通过先减去老的区间大小，再加上新的区间大小的方式来同步维护
- 算法过程：
  - 初始化一个`TreeMap`用于存储区间
    - `key`存储区间的左边界
    - `value`存储区间的右边界
  - 初始化一个`cnt`用于存储个数，初始为0
  - 新增方法实现：
    - 通过区间右边界，到`TreeMap`中寻找b不比右边界大的所有区间，寻找方式可以通过循环使用`floorEntry`这个api来实现
    - 如果能够获取到右边界不小于新增区间左边界的区间，就将这个找到区间的大小从`cnt`中扣除
    - 然后将`left`和`right`这2个区间边界更新
    - 循环直到无法获取关联区间后退出
    - 将更新的左右边界作为新的区间，放入到`TreeMap`中，同时将新区间的大小作为新增值更新到`cnt`中
  - 返回个数方法实现：直接返回`cnt`变量即可
### 代码
```java
class CountIntervals {
    private TreeMap<Integer, Integer> treeMap;
    private int cnt;

    public CountIntervals() {
        this.treeMap = new TreeMap<>();
        this.cnt = 0;
    }

    public void add(int left, int right) {
        while (!treeMap.isEmpty() && treeMap.floorEntry(right) != null) {
            Map.Entry<Integer, Integer> interval = treeMap.floorEntry(right);
            if (interval.getValue() < left) {
                break;
            }
            
            cnt -= interval.getValue() - interval.getKey() + 1;
            left = Math.min(interval.getKey(), left);
            right = Math.max(interval.getValue(), right);
            treeMap.remove(interval.getKey());
        }
        
        cnt += right - left + 1;
        treeMap.put(left, right);
    }

    public int count() {
        return cnt;
    }
}
```
## 解法二
### 思路
- 思考过程
  - 使用线段树来维护，线段树包括
    - 左右子树
    - 覆盖部分大小值`cnt`
    - 左右边界大小
- 算法过程
  - 初始化线段树
    - 覆盖大小为0
    - 左右边界为1和10的9次方
    - 左右子树为空
  - 新增方法：
    - 如果当前节点的`cnt`等于当前节点的左右边界对应区间的长度，那么说明区间已被全覆盖，无需重复计算，直接返回
    - 如果新增的区间能完全覆盖当前节点的左右边界，那么不需要继续搜索当前节点的左右子树，直接更新`cnt`值全覆盖的区间大小即可
    - 计算得到当前节点的区间中间值`mid`
    - 如果当前节点的左子树为空，初始化左子树，区间大小为当前左边界`left`与`mid`
    - 如果当前节点的右子树为空，初始化右子树，区间大小为当前右边界`mid + 1`与`right`
    - 如果新增加的区间的左边界在`mid`的左侧（包含`mid`），那么说明左子树需要被更新，递归操作左子树的新增操作
    - 如果新增加的区间的右边界在`mid`的右侧，那么说明右子树需要被更新，递归操作右子树的新增操作
    - 最后更新当前节点的`cnt`，更新公式：`cnt = left.cnt + right.cnt`
  - 获取个数方法：
    - 返回根节点的`cnt`
### 代码
```java
class CountIntervals {

    private CountIntervals left, right;
    private int cnt, l, r;

    public CountIntervals() {
        this.l = 1;
        this.r = (int) 10e9;
    }
    
    public CountIntervals(int l, int r) {
        this.l = l;
        this.r = r;
    }

    public void add(int left, int right) {
        if (this.cnt == this.r - this.l + 1) {
            return;
        }
        
        if (left <= l && r <= right) {
            cnt = this.r - this.l + 1;
            return;
        }
        
        int mid = l + (r - l) / 2;
        if (this.left == null) {
            this.left = new CountIntervals(l, mid);
        }
        
        if (this.right == null) {
            this.right = new CountIntervals(mid + 1, r);
        }
        
        if (left <= mid) {
            this.left.add(left, right);
        }
        
        if (right > mid) {
            this.right.add(left, right);
        }
        
        this.cnt = this.left.cnt + this.right.cnt;
    }

    public int count() {
        return cnt;
    }
}
```