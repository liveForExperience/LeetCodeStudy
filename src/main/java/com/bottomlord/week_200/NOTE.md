# [LeetCode_1263_推箱子](https://leetcode.cn/problems/minimum-moves-to-move-a-box-to-their-target-location/)
## 解法
### 思路
bfs
- 因为题目中不仅包括箱子，还包括人，所以bfs的驱动元素需要同时包含箱子和人的坐标信息
- bfs过程中通过一个记事本数组来记录当前箱子和人的坐标状态时，经过的最小步数，这个步数基于bfs过程来推导，其实也就是在做动态规划
- bfs过程中，内层循环的时候，在循环体之外再维护一个队列，这个队列用于存储箱子移动后的人和箱子的状态，而同时内层循环的时候，不仅有箱子动的状态，也有人动但箱子不动的状态，这种状态就在内层循环掉
- 循环过程中对记事本进行更新，为了区分是否搜索过，初始化这个记事本的所有元素为int的最大值
- 在内层循环过程中，如果箱子的坐标到达了`T`的位置，那么就返回记事本记录的值即可
### 代码
```java
class Solution {
    private int r, c;
    private int[][] memo;
    private char[][] grid;

    public int minPushBox(char[][] grid) {
        this.r = grid.length;
        this.c = grid[0].length;
        this.memo = new int[c * r][c * r];
        this.grid = grid;

        int sx = -1, sy = -1, bx = -1, by = -1;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 'S') {
                    sx = i;
                    sy = j;
                } else if (grid[i][j] == 'B') {
                    bx = i;
                    by = j;
                }
            }
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{sx * c + sy, bx * c + by});
        int[] dirs = new int[]{0, -1, 0, 1, 0};
        for (int[] arr : memo) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }

        memo[sx * c + sy][bx * c + by] = 0;

        while (!queue.isEmpty()) {
            Queue<int[]> next = new ArrayDeque<>();
            while (!queue.isEmpty()) {
                int[] arr = queue.poll();
                int s1 = arr[0], b1 = arr[1],
                    sx1 = s1 / c, sy1 = s1 % c,
                    bx1 = b1 / c, by1 = b1 % c;

                if (grid[bx1][by1] == 'T') {
                    return memo[s1][b1];
                }

                for (int i = 0; i < 4; i++) {
                    int sx2 = sx1 + dirs[i], sy2 = sy1 + dirs[i + 1], s2 = sx2 * c + sy2;

                    if (!ok(sx2, sy2)) {
                        continue;
                    }

                    if (sx2 == bx1 && sy2 == by1) {
                        int bx2 = bx1 + dirs[i], by2 = by1 + dirs[i + 1], b2 = bx2 * c + by2;
                        if (!ok(bx2, by2) || memo[s2][b2] <= memo[s1][b1] + 1) {
                            continue;
                        }

                        memo[s2][b2] = memo[s1][b1] + 1;
                        next.offer(new int[]{s2, b2});
                    } else {
                        if (memo[s2][b1] <= memo[s1][b1]) {
                            continue;
                        }

                        memo[s2][b1] = memo[s1][b1];
                        queue.offer(new int[]{s2, b1});
                    }
                }
            }
            
            queue = next;
        }

        return -1;
    }

    private boolean  ok(int x, int y) {
        return x >= 0 && x < r && y >= 0 && y < c && grid[x][y] != '#';
    }
}
```
# [LeetCode_967_连续差相同的数字]()
## 解法
### 思路
dfs，需要注意k为0的情况，不要重复搜索，其他就是简单的深度优先搜索即可
### 代码
```java
class Solution {
    private int k, n;
    public int[] numsSameConsecDiff(int n, int k) {
        this.k = k;
        this.n = n;
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            dfs(1, i, i, list);
        }
        
        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    private void dfs(int index, int num, int pre, List<Integer> list) {
        if (index >= n) {
            list.add(num);
            return;
        }
        
        if (pre + k <= 9) {
            dfs(index + 1, num * 10 + pre + k, pre + k, list);
        }
        
        if (pre - k >= 0 && pre - k != pre + k) {
            dfs(index + 1, num * 10 + pre - k, pre - k, list);
        }
    }
}
```
# [LeetCode_1015_可被k整除的最小整数](https://leetcode.cn/problems/smallest-integer-divisible-by-k/)
## 解法
### 思路
- 因为2和5肯定不能整除个位是1的数字，所以k能被这2个数整除，都可以直接返回-1
- 通过模运算的交换律：`(a + b) % k` = `((a % k) + (b % k)) % k`，`(a * b) % k` = `((a % k) * (b % k)) % k` 
- 可以得到：`(x * 10 + 1) % k` = `((x % k) * (10 % k) + 1 % k) % k` 
- 所以在遍历1到n个1的过程中，可以通过`((x % k) * (10 % k) + 1 % k) % k`来进行值的转换
- 使用一个set来记录是否存在已经遇到过的余数，因为如果没有除尽的情况下，遇到相同的余数，那就说明会进入一个死循环，所以直接返回-1
- 循环更新x，直到x能被k整除，或者遇到相同的余数为止
### 代码
```java
class Solution {
    public int smallestRepunitDivByK(int k) {
        if (k % 2 == 0 || k % 5 == 0) {
            return -1;
        }
        
        Set<Integer> set = new HashSet<>();
        int x = 1;
        for (int i = 1; ; i++) {
            if (x % k == 0) {
                return i;
            }

            x = (x % k) * 10 + 1;
            if (!set.add(x)) {
                return -1;
            }
        }
    }
}

```
# [LeetCode_971_翻转二叉树以匹配先序遍历](https://leetcode.cn/problems/flip-binary-tree-to-match-preorder-traversal/)
## 解法
### 思路
- 先序遍历二叉树，如果下一个值与预期的下一个值不同，就翻转并记录
- 如果当前值与预期值不同就记录-1，并返回
### 代码
```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int index = 0;
    private int[] voyage;
    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        this.voyage = voyage;
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        return list;
    }

    private void dfs(TreeNode node, List<Integer> list) {
        if (index >= voyage.length) {
            return;
        }
        
        if (node == null) {
            return;
        }

        if (list.size() == 1 && list.get(0) == -1) {
            return;
        }

        int val = node.val;
        if (val != voyage[index++]) {
            list.clear();
            list.add(-1);
            return;
        }

        int next = 0;
        if (node.left != null) {
            next = node.left.val;
        } else if (node.right != null) {
            next = node.right.val;
        }

        if (index >= voyage.length || next == 0) {
            return;
        }
        
        if (next != voyage[index]) {
            list.add(node.val);
            dfs(node.right, list);
            dfs(node.left, list);
        } else {
            dfs(node.left, list);
            dfs(node.right, list);
        }
    }
}
```
# [LeetCode_1330_翻转子数组得到最大的数组值](https://leetcode.cn/problems/reverse-subarray-to-maximize-array-value/)
## 解法
### 思路
- [i, j]代表需要翻转的子数组区间
- 通过观察可以发现，翻转后的数值和最大的情况，其实就是原数值和新数值和的差的最大值，而实际变化的就是子数组头尾两个元素与其相邻的非子数组元素的差
  - 原来：|nums[i - 1] - nums[i]| + |nums[j] - nums[j + 1]|
  - 现在：|nums[i - 1] - nums[j]| + |nums[i] - nums[j + 1]|
- 如果a = nums[i], b = nums[i - 1], x = nums[j], y = nums[j + 1]，那么如上的表达式就是：
    - 原来：|b - a| + |x - y|
    - 现在：|b - x| + |a - y|
    - 他们的差值就是|b - a| + |x - y| - |b - x| - |a - y|
- 可以暴力枚举所有的子数组状态，然后求这个值，这样的时间复杂度是O(N2)
- 但是如果能够能将`|b - a| + |x - y| - |b - x| - |a - y|`化简，就可以压缩到O(N)的时间复杂度
- 观察可知：
  - |a - b| = max(a,b) - min(a, b)
  - a + b = max(a, b) + min(a, b)
- 结合公式可推得：
  - a + b - |a - b| = 2 * min(a, b)
  - a + b + |a - b| = 2 * max(a, b)
- a,b,x,y之间的大小关系，通过排列可以计算出共有4!=24个
- 其中又可以分成3大类
  - max(a,b) <= min(x,y), max(x,y) <= min(a,b)：
    - =>|a - x| + |b - y| - |a - b| - |x - y|
    - => x - a + y - b - |a - b| - |x - y|
    - => (x + y - |x - y|) - (a + b + |a - b|)
    - => 2 * min(x, y) - 2 * max(a, b)
    - ∵ max(a,b) <= min(x,y)
    - ∴ 2 * min(x, y) - 2 * max(a, b) >= 0
    - 同理：2 * min(a,b) - 2 * max(x,y) >= 0
  - max(a,x) <= min(b,y), max(b,y) <= min(a,x)
    - =>|a - x| + |b - y| - |a - b| - |x - y|
    - =>|a - x| + |b - y| - (b - a) - (y - x)
    - =>|a - x| + |b - y| - (b + y) - (a + x)
    - =>(a + x + |a - x|) - (b + y - |b - y|)
    - =>2 * max(a, x) - 2 * min(b, y)
    - ∵ max(a,x) <= min(b,y)
    - ∴ 2 * min(a, y) - 2 * max(b, y) <= 0，对答案无影响
    - 同理，2 * min(b, y) - 2 * max(a, x) <= 0，也对答案无影响
  - max(a,y) <= min(b,x), max(a,y) <= min(b,x)
    - 同样推倒：得到0，对答案无影响
- 算法逻辑
  - 根据如上公示及题目要求，需要求出如下几项的值
    - 原来的数值和：base，这个通过遍历累加就可以得到
    - max(a,b)和max(x,y)，这个通过遍历，并通过计算当前元素和前一个元素两个值的最小值，再取与历史数据的最大值即可，也就是在4个数中取最大的，再与历史上的取一个最小的
    - min(x,y)和min(a,b)，这个和如上相同，通过计算当前元素和前一个元素两个值的最大值，再取与历史数据的最小值即可，也就是在4个数中取最小的，再与历史上的取一个最大的
    - 还需要处理i=0和j=n-1这两个边界特殊情况，得到最大值d：
      - i=0，就是|a - b| - |nums[0] - a|
      - j=n-1，就是|a - b| - |nums[n - 1] - b|
      - 将这两个值的最大值也维护在一个变量中，并在每次迭代的时候更新即可
  - 那么要求的就是d，2 * (max - min)之间的最大值
  - 最后返回base + max(d, 2 * (max - min))即可
### 代码
```java
class Solution {
    public int maxValueAfterReverse(int[] nums) {
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE, n = nums.length,
            d = 0, base = 0;

        for (int i = 1; i < nums.length; i++) {
            int a = nums[i], b = nums[i - 1], diff = Math.abs(a - b);
            base += diff;
            max = Math.max(max, Math.min(a, b)); 
            min = Math.min(min, Math.max(a, b));
            d = Math.max(d,
                    Math.max(
                            Math.abs(nums[0] - a) - diff,
                            Math.abs(nums[n - 1] - b) - diff
                    )
            );
        }

        return base + Math.max(d, 2 * (max - min));
    }
}
```
# [LeetCode_2670_找出不同元素数目差数组](https://leetcode.cn/problems/find-the-distinct-difference-array/)
## 解法
### 思路
- 初始化diff数组作为结果数组，存储前后子数组不同元素个数的差值
- 初始化memo数组，用来记录遍历过程中元素出现的情况，方便在遍历过程中记录不同元素个数
- 初始化变量cnt，用来记录[0,i]区间的不同元素个数
- 2层嵌套循环
  - 外层记录[0,i]区间的不同元素个数，配置memo数组进行判断
  - 内层从i+1开始遍历，计算[i + 1, n - 1]区间的不同元素个数，配合另一个memo数组进行判断
  - 内层循环结束后，cnt与内层计算的个数相减，得到的值存入diff对应位置即可
- 循环结束，返回diff数组作为结果
### 代码
```java
class Solution {
  public int[] distinctDifferenceArray(int[] nums) {
    int n = nums.length, cnt = 0;
    int[] diff = new int[n];
    boolean[] memo = new boolean[51];
    for (int i = 0; i < n; i++) {
      if (!memo[nums[i]]) {
        memo[nums[i]] = true;
        cnt++;
      }

      boolean[] innerMemo = new boolean[51];
      int latterCnt = 0;
      for (int j = i + 1; j < n; j++) {
        if (!innerMemo[nums[j]]) {
          latterCnt++;
          innerMemo[nums[j]] = true;
        }
      }

      diff[i] = cnt - latterCnt;
    }

    return diff;
  }
}
```
# [LCP_77_符文储备](https://leetcode.cn/problems/W2ZX4X/)
## 解法
### 思路
求最长连续子数组的长度
- 排序后2层嵌套遍历
- 内层嵌套循环，分段计数
- 内层循环结束，与暂存的最大值比较，取较大值
- 外层遍历结束，返回暂存的最大值即可
### 代码
```java
class Solution {
    public int runeReserve(int[] runes) {
        Arrays.sort(runes);
        int max = 0, n = runes.length;
        for (int i = 0; i < n;) {
            int pre = runes[i++], cnt = 1;
            while (i < n && runes[i] - pre <= 1) {
                pre = runes[i];
                cnt++;
                i++;
            }
            max = Math.max(max, cnt);
        }
        return max;
    }
}
```