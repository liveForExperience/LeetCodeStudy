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