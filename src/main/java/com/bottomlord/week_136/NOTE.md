# [LeetCode_540_有序数组中的单一元素](https://leetcode-cn.com/problems/single-element-in-a-sorted-array/)
## 解法
### 思路
- 两个一样的数字抑或后是0
- 基于如上特性，遍历数组并抑或元素
- 遍历结束，不停抑或后得到的值就是单一元素
### 代码
```java
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int num = 0;
        for (int i : nums) {
            num ^= i;
        }
        return num;
    }
}
```
# [LeetCode_offerII41_滑动窗口的平均值](https://leetcode-cn.com/problems/qIsx9U/)
## 解法
### 思路
队列存储
### 代码
```java
class MovingAverage {

    private Queue<Integer> queue;
    private int size;
    private int average;
    
    public MovingAverage(int size) {
        this.queue = new ArrayDeque<>();
        this.size = size;
    }

    public double next(int val) {
        queue.offer(val);
        if (queue.size() > size) {
            queue.poll();
        }

        int sum = 0;
        for (Integer num : queue) {
            sum += num;
        }
        
        return sum * 1D / queue.size();
    }
}
```
## 解法二
### 思路
在解法一的基础上优化next函数算法
- 不再每次都遍历一次队列求sum
- 暂存sum，然后再暂存值上做处理，如果size超了，就删除队头元素，加入队尾元素，然后再求平均值
### 代码
```java
class MovingAverage {

    private Queue<Integer> queue;
    private int size;
    private int sum;

    public MovingAverage(int size) {
        this.queue = new ArrayDeque<>();
        this.size = size;
    }

    public double next(int val) {
        queue.offer(val);
        sum += val;
        if (queue.size() > size) {
            assert !queue.isEmpty();
            sum -= queue.poll();
        }
        return sum * 1D / queue.size();
    }
}
```
# [LeetCode_2169_得到0的操作数](https://leetcode-cn.com/problems/count-operations-to-obtain-zero/)
## 解法
### 思路
递归
### 代码
```java
class Solution {
    public int countOperations(int num1, int num2) {
        if (num1 == 0 || num2 == 0) {
            return 0;
        }
        
        return 1 + countOperations(num1 >= num2 ? num1 - num2 : num1, num1 >= num2 ? num2 : num2 - num1);
    }
}
```
## 解法二
### 思路
- 题目的本意就是在模拟整数除法操作
- 所以可以通过除法快速求出操作的次数，然后用取余得到的数字代替原来的较大值，继续循环，直到某一个数为0为止
### 代码
```java
class Solution {
    public int countOperations(int num1, int num2) {
        int ans = 0;
        while (num1 > 0 && num2 > 0) {
            if (num1 >= num2) {
                ans += num1 / num2;
                num1 %= num2;
            } else {
                ans += num2 / num1;
                num2 %= num1;
            }
        }
        
        return ans;
    }
}
```
# [LeetCode_offerII42_最近请求次数](https://leetcode-cn.com/problems/H8086Q/)
## 解法
### 思路
优先级队列+map
### 代码
```java
class RecentCounter {
    private final Queue<int[]> queue;
    private final Map<Integer, int[]> map;
    private int sum;
    public RecentCounter() {
        this.sum = 0;
        this.queue = new PriorityQueue<>(Comparator.comparingInt(x -> x[0]));
        this.map = new HashMap<>();
    }

    public int ping(int t) {
        if (map.containsKey(t)) {
            int[] arr = map.get(t);
            arr[1]++;
        } else {
            int[] arr = new int[]{t, 1};
            queue.offer(arr);
            map.put(t, arr);
        }

        sum++;

        while (!queue.isEmpty() && queue.peek()[0] < t - 3000) {
            sum -= queue.poll()[1];
        }

        return sum;
    }
}
```
# [LeetCode_offerII52_展平二叉搜索树](https://leetcode-cn.com/problems/NYBBNL/)
## 解法
### 思路
- 中序遍历，将遍历到的节点按顺序放入list中
- 遍历list，将节点两两连接
- 返回list的第一个节点
### 代码
```java
class Solution {
    public TreeNode increasingBST(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        dfs(root, list);
        if (list.isEmpty()) {
            return null;
        }
        
        for (int i = 0; i < list.size(); i++) {
            list.get(i).left = null;
            if (i == list.size() - 1) {
                list.get(i).right = null;
            } else {
                list.get(i).right = list.get(i + 1);
            }
        }
        return list.get(0);
    }
    
    private void dfs(TreeNode node, List<TreeNode> list) {
        if (node == null) {
            return;
        }
        
        dfs(node.left, list);
        list.add(node);
        dfs(node.right, list);
    }
}
```
## 解法二
### 思路
- 不适用额外的集合来储存节点的指针，而是使用一个游标指针，在中序遍历的过程中进行操作
- 当找到最小节点后，开始使用游标指针，将节点的左树置为null，并将游标指向右子树的根节点
### 代码
```java
class Solution {
    private TreeNode iter;
    
    public TreeNode increasingBST(TreeNode root) {
        TreeNode head = new TreeNode(-1);
        iter = head;
        inOrder(root);
        return head.right;
    }
    
    private void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        
        inOrder(node.left);
        
        node.left = null;
        iter.right = node;
        iter = node;
        
        inOrder(node.right);
    }
}
```
# [LeetCode_1719_重构一棵树的方案数](https://leetcode-cn.com/problems/number-of-ways-to-reconstruct-a-tree/)
## 解法
### 思路
- 参考题解：
  - [题解1](https://leetcode-cn.com/problems/number-of-ways-to-reconstruct-a-tree/solution/zhong-gou-yi-ke-shu-de-fang-an-shu-by-le-36e1/)
  - [题解2](https://leetcode-cn.com/problems/number-of-ways-to-reconstruct-a-tree/solution/xiang-xi-fen-xi-liang-chong-jian-shu-si-eomax/)
- 核心是要确认一颗树中节点与其一个祖先路径的上的其他节点的度数的关系
- 根据题解中的分析可以了解到
  - 根节点的度数等于节点个数-1
  - 树中某个节点的祖先节点的度数一定比节点的度数大
  - 父节点一定是祖先节点中度数最小的
  - 如果度数相等的两个统一路径上的节点，一定是可以互换的
### 代码
```java
class Solution {
    public int checkWays(int[][] pairs) {
        int root = -1;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] pair : pairs) {
            map.computeIfAbsent(pair[0], x -> new HashSet<>()).add(pair[1]);
            map.computeIfAbsent(pair[1], x -> new HashSet<>()).add(pair[0]);
        }

        for (Map.Entry<Integer, Set<Integer>> entry : map.entrySet()) {
            if (entry.getValue().size() == map.size() - 1) {
                root = entry.getKey();
            }
        }

        if (root == -1) {
            return 0;
        }

        int ans = 1;
        for (Map.Entry<Integer, Set<Integer>> entry : map.entrySet()) {
            int node = entry.getKey();
            if (node == root) {
                continue;
            }

            Set<Integer> degrees = entry.getValue();
            int curDegreeSize = degrees.size();
            int parent = -1;
            int parentDegreeSize = Integer.MAX_VALUE;

            for (Integer degree : degrees) {
                if (map.get(degree).size() < parentDegreeSize && map.get(degree).size() >= curDegreeSize) {
                    parent = degree;
                    parentDegreeSize = map.get(degree).size();
                }
            }

            if (parent == -1) {
                return 0;
            }

            for (Integer degree : degrees) {
                if (degree == parent) {
                    continue;
                }

                if (!map.get(degree).contains(parent)) {
                    return 0;
                }
            }

            if (curDegreeSize == parentDegreeSize) {
                ans = 2;
            }
        }

        return ans;
    }
}
```
# [LeetCode_offerII56_二叉搜索树中两个节点之和](https://leetcode-cn.com/problems/opLdQZ/)
## 解法
### 思路
- dfs生成数字set
- 遍历set，找set中是否存在k-num的差值，如果存在就返回true
- 遍历结束，没有找到，就返回false
### 代码
```java
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        dfs(root, set);
        for (Integer num : set) {
            if (num != k - num && set.contains(k - num)) {
                return true;
            }
        }
        return false;
    }
    
    private void dfs(TreeNode node, Set<Integer> set) {
        if (node == null) {
            return;
        }
        
        dfs(node.left, set);
        set.add(node.val);
        dfs(node.right, set);
    }
}
```
## 解法二
### 思路
- 不使用额外的数据结构
- 两层递归
  - 第一层递归用于寻找两个元素中的第一个元素
  - 第二层递归用于从根节点开始，通过二叉搜索树的特性，二分查找第二个元素，如果找到就返回true，否则继续搜索
### 代码
```java
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        return dfs(root, root, k);
    }

    private boolean dfs(TreeNode node, TreeNode root, int k) {
        if (node == null) {
            return false;
        }

        int val = node.val, target = k - val;
        if (val != target && find(root, target)) {
            return true;
        }

        return dfs(node.left, root, k) || dfs(node.right, root, k);
    }

    private boolean find(TreeNode node, int target) {
        if (node == null) {
            return false;
        }

        if (node.val == target) {
            return true;
        }

        if (node.val > target) {
            return find(node.left, target);
        } else {
            return find(node.right, target);
        }
    }
}
```
# [LeetCode_offerII59_数据流的第K大数值](https://leetcode-cn.com/problems/jBjn9C/)
## 解法
### 思路
小顶堆
### 代码
```java
class KthLargest {

    private PriorityQueue<Integer> queue;
    private int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.queue = new PriorityQueue<>();
        for (int num : nums) {
            queue.offer(num);
        }
    }

    public int add(int val) {
        queue.offer(val);
        while (queue.size() > k) {
            queue.poll();
        }

        return queue.peek();
    }
}
```
# [LeetCode_offerII68_查找插入位置](https://leetcode-cn.com/problems/N6YdxV/)
## 解法
### 思路
二分查找
### 代码
```java
class Solution {
    public int searchInsert(int[] nums, int target) {
        int head = 0, tail = nums.length - 1;
        while (head <= tail) {
            int mid = head + (tail - head) / 2;
            
            int num = nums[mid];
            if (num == target) {
                return mid;
            } else if (num < target) {
                head = mid + 1;
            } else {
                tail = mid - 1;
            }
        }
        
        return head;
    }
}
```
# [LeetCode_offerII72_求平方根](https://leetcode-cn.com/problems/jJ0w9p/)
## 解法
### 思路
二分查找
### 代码
```java
class Solution {
    public int mySqrt(int x) {
        long head = 0, tail = x;
        while (head < tail) {
            long mid = head + (tail - head + 1) / 2;
            long val = mid * mid;
            if (val == x) {
                return (int)mid;
            } else if (val < x) {
                head = mid;
            } else {
                tail = mid - 1;
            }
        }
        return (int)head;
    }
}
```
# [LeetCode_offerII75_数组相对排序](https://leetcode-cn.com/problems/0H97ZC/)
## 解法
### 思路
桶排序
### 代码
```java
class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int max = Integer.MIN_VALUE;
        for (int num : arr1) {
            max = Math.max(max, num);
        }
        
        int[] bucket = new int[max + 1];
        for (int num : arr1) {
            bucket[num]++;
        }

        int[] ans = new int[arr1.length];
        
        int index = 0;
        for (int num : arr2) {
            for (int i = 0; i < bucket[num]; i++) {
                ans[index++] = num;
            }
            bucket[num] = 0;
        }

        for (int num = 0; num <= max; num++) {
            for (int i = 0; i < bucket[num]; i++) {
                ans[index++] = num;
            }
        }
        
        return ans;
    }
}
```
# [LeetCode_offerII88_爬楼梯的最少成本](https://leetcode-cn.com/problems/GzCJIP/)
## 解法
### 思路
动态规划：
- dp[i]：到达第i级并准备继续向上所需要的体力值的最小值
- 状态转移方程：`dp[i] = min(dp[i - 1], dp[i - 2]) + cost[i]`
- base case：
  - `dp[0] = cost[0]`
  - `dp[1] = cost[1]`
### 代码
```java
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;
        int[] dp = new int[len + 1];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < dp.length - 1; i++) {
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
        }
        
        return Math.min(dp[len - 1], dp[len - 2]);
    }
}
```
## 解法二
### 思路
通过观察解法一可以发现，状态转移方程只需要dp[i-1]和dp[i-2]这两个值，所以可以对数组进行压缩，使用变量代替
### 代码
```java
class Solution {
  public int minCostClimbingStairs(int[] cost) {
    int one = cost[0], two = cost[1], len = cost.length, ans;
    for (int i = 2; i < len; i++) {
      ans = Math.min(one, two) + cost[i];
      one = two;
      two = ans;
    }

    return Math.min(one, two);
  }
}
```
# [LeetCode_573_松鼠模拟](https://leetcode-cn.com/problems/squirrel-simulation/)
## 解法
### 思路
- 两个点的距离公式：`dis = abs(x[0] - y[0]) + abs(x[1] - y[1])`
- 根据观察可以发现，所有坚果，除了松鼠第一次选择的坚果外，其他坚果与树之间都会有来回的距离产生
- 而实际计算过程中，如果松鼠选择了某个坚果，那么该坚果与树之间的一次距离，就会替换成与松鼠的距离，而要求出距离总和的最短情况，就可以找到坚果与松鼠的距离比与树的距离缩短的长度最大的情况，将这种情况计算进去即可。
### 代码
```java
class Solution {
    public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
        int disTreeAndNut = 0, disSquirrelAndNut = 0, max = Integer.MIN_VALUE, ans = 0;
        for (int[] nut : nuts) {
            int treeDis = distance(tree, nut);
            int squDis = distance(squirrel, nut);
            
            if (treeDis - squDis > max) {
                disTreeAndNut = treeDis;
                disSquirrelAndNut = squDis;
                max = treeDis - squDis;
            }
            
            ans += 2 * treeDis;
        }
        
        return ans - disTreeAndNut + disSquirrelAndNut;
    }
    
    private int distance(int[] x, int[] y) {
        return Math.abs(x[0] - y[0]) + Math.abs(x[1] - y[1]);
    }
}
```
# [LeetCode_582_杀掉进程](https://leetcode-cn.com/problems/kill-process/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_969_煎饼排序](https://leetcode-cn.com/problems/pancake-sorting/)
## 解法
### 思路
- 因为是[0, k-1]范围的翻转，所以要形成升序，可以依次确定最大值即可
- 而将最大值通过煎饼排序放置到指定数组范围结尾的方法，就是
  - 先找到当前范围的最大值坐标
  - 将最大值坐标翻转到第一的位置，然后再做一次煎饼翻转，翻转的范围就是你要他到的范围
- 如此，这个最大值就确定了，然后缩小范围到前一个坐标即可
- 因为翻转次数在数组长度*10的范围内都有效，而这个算法的最差情况是每次确定一个最大值都翻转2次，所以一定小于最大翻转次数
### 代码
```java
class Solution {
  public List<Integer> pancakeSort(int[] arr) {
    int len = arr.length, index = len - 1;
    List<Integer> ans = new ArrayList<>();
    while (index >= 0) {
      int maxIndex = findMaxNumIndex(arr, index);

      if (maxIndex == index) {
        index--;
        continue;
      }

      reserve(arr, 0, maxIndex);
      ans.add(maxIndex + 1);
      reserve(arr, 0, index);
      ans.add(index-- + 1);
    }

    return ans;
  }

  private int findMaxNumIndex(int[] arr, int tail) {
    int max = Integer.MIN_VALUE, maxIndex = -1;
    for (int i = 0; i <= tail; i++) {
      if (max < arr[i]) {
        max = arr[i];
        maxIndex = i;
      }
    }
    return maxIndex;
  }

  private void reserve(int[] arr, int head, int tail) {
    while (head < tail) {
      int tmp = arr[head];
      arr[head] = arr[tail];
      arr[tail] = tmp;
      head++;
      tail--;
    }
  }
}
```
# [LeetCode_杀掉进程](https://leetcode-cn.com/problems/kill-process/)
## 解法
### 思路
邻接表+dfs
### 代码
```java
class Solution {
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        Map<Integer, Integer> mapping = new HashMap<>();
        Map<Integer, List<Integer>> edges = new HashMap<>();
        for (int i = 0; i < pid.size(); i++) {
            mapping.put(i, pid.get(i));
        }

        for (int i = 0; i < ppid.size(); i++) {
            edges.computeIfAbsent(ppid.get(i), x -> new ArrayList<>()).add(mapping.get(i));
        }

        List<Integer> ans = new ArrayList<>();
        dfs(kill, ans, edges);
        return ans;
    }

    private void dfs(Integer kill, List<Integer> list, Map<Integer, List<Integer>> edges) {
        if (kill == null) {
            return;
        }

        list.add(kill);
        List<Integer> nexts = edges.get(kill);
        if (nexts != null) {
            for (Integer next : nexts) {
                dfs(next, list, edges);
            }
        }
    }
}
```