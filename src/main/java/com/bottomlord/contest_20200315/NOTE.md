# Contest_1_矩阵中的幸运数
## 题目
给你一个 m * n 的矩阵，矩阵中的数字 各不相同 。请你按 任意 顺序返回矩阵中的所有幸运数。

幸运数是指矩阵中满足同时下列两个条件的元素：
```
在同一行的所有元素中最小
在同一列的所有元素中最大
```
示例 1：
```
输入：matrix = [[3,7,8],[9,11,13],[15,16,17]]
输出：[15]
解释：15 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
```
示例 2：
```
输入：matrix = [[1,10,4,2],[9,3,8,7],[15,16,17,12]]
输出：[12]
解释：12 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
```
示例 3：
```
输入：matrix = [[7,8],[1,2]]
输出：[7]
```
提示：
```
m == mat.length
n == mat[i].length
1 <= n, m <= 50
1 <=matrix[i][j]<= 10^5
矩阵中的所有元素都是不同的
```
## 解法
### 思路
- 按行遍历，找到每一行最小值和对应的下标
- 找到最小值后，再根据对应的下标，和当前列的所有元素比较，如果是最大值，就放入结果中
### 代码
```java
class Solution {
    public List<Integer> luckyNumbers (int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return Collections.emptyList();
        }
        
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            int rowMin = Integer.MAX_VALUE, ri = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] < rowMin) {
                    rowMin = matrix[i][j];
                    ri = j;
                }
            }

            int colMax = matrix[i][ri];
            boolean isMax = true;
            for (int k = 0; k < matrix.length; k++) {
                if (colMax < matrix[k][ri]) {
                    isMax = false;
                    break;
                }
            }

            if (isMax) {
                ans.add(colMax);
            }
        }
        
        return ans;
    }
}
```
# Contest_2_设计一个支持增量操作的栈
## 题目
请你设计一个支持下述操作的栈。

实现自定义栈类 CustomStack ：
```
CustomStack(int maxSize)：用 maxSize 初始化对象，maxSize 是栈中最多能容纳的元素数量，栈在增长到 maxSize 之后则不支持 push 操作。
void push(int x)：如果栈还未增长到 maxSize ，就将 x 添加到栈顶。
int pop()：返回栈顶的值，或栈为空时返回 -1 。
void inc(int k, int val)：栈底的 k 个元素的值都增加 val 。如果栈中元素总数小于 k ，则栈中的所有元素都增加 val 。
```
示例：
```
输入：
["CustomStack","push","push","pop","push","push","push","increment","increment","pop","pop","pop","pop"]
[[3],[1],[2],[],[2],[3],[4],[5,100],[2,100],[],[],[],[]]
输出：
[null,null,null,2,null,null,null,null,null,103,202,201,-1]
解释：
CustomStack customStack = new CustomStack(3); // 栈是空的 []
customStack.push(1);                          // 栈变为 [1]
customStack.push(2);                          // 栈变为 [1, 2]
customStack.pop();                            // 返回 2 --> 返回栈顶值 2，栈变为 [1]
customStack.push(2);                          // 栈变为 [1, 2]
customStack.push(3);                          // 栈变为 [1, 2, 3]
customStack.push(4);                          // 栈仍然是 [1, 2, 3]，不能添加其他元素使栈大小变为 4
customStack.increment(5, 100);                // 栈变为 [101, 102, 103]
customStack.increment(2, 100);                // 栈变为 [201, 202, 103]
customStack.pop();                            // 返回 103 --> 返回栈顶值 103，栈变为 [201, 202]
customStack.pop();                            // 返回 202 --> 返回栈顶值 202，栈变为 [201]
customStack.pop();                            // 返回 201 --> 返回栈顶值 201，栈变为 []
customStack.pop();                            // 返回 -1 --> 栈为空，返回 -1
```
提示：
```
1 <= maxSize <= 1000
1 <= x <= 1000
1 <= k <= 1000
0 <= val <= 100
每种方法 increment，push 以及 pop 分别最多调用 1000 次
```
## 解法
### 思路
- 使用两个栈：
    - `stack1`负责push和pop
    - `stack2`用来在increase的时候，将`stack1`的元素弹出并依次放入，然后根据k值将`stack2`栈顶的元素依次累加`value`，同时压回`stack1`
- 使用两个变量：
    - 记录栈大小`size`，用来判断是否还支持`push`
    - 记录`maxSize`
### 代码
```java
class CustomStack {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;
    private int maxSize;
    private int size;

    public CustomStack(int maxSize) {
        this.stack1 = new Stack<>();
        this.stack2 = new Stack<>();
        this.maxSize = maxSize;
        this.size = 0;
    }

    public void push(int x) {
        if (size < maxSize) {
            size++;
            stack1.push(x);
        }
    }

    public int pop() {
        if (size == 0) {
            return -1;
        }

        size--;
        return stack1.pop();
    }

    public void increment(int k, int val) {
        if (size == 0) {
            return;
        }
        
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        
        int num = Math.min(size, k);
        while (!stack2.isEmpty()) {
            if (num-- > 0) {
                stack1.push(stack2.pop() + val);
            } else {
                stack1.push(stack2.pop());
            }
        }
    }
}
```
# Contest_3_将二叉搜索树再平衡
## 题目

## 解法
### 思路
- dfs中序生成升序序列
- 找到序列中点，开始递归
    - 根据中间坐标对应的值生成当前节点
    - 左半部分作为当前节点的左子树
    - 右半部分作为当前节点的右子树
    - 将当前节点返回
### 代码
```java
class Solution {
    public TreeNode balanceBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        
        return build(list.size() / 2, 0, list.size(), list);
    }
    
    private void dfs(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        
        dfs(node.left, list);
        list.add(node.val);
        dfs(node.right, list);
    }
    
    private TreeNode build(int mid, int head, int tail, List<Integer> list) {
        if (head >= tail) {
            return null;
        }
        
        TreeNode node = new TreeNode(list.get(mid));
        node.left = build(head + (mid - head) / 2, head, mid, list);
        node.right = build(mid + 1 + (tail - mid - 1) / 2, mid + 1, tail, list);
        return node;
    }
}
```
# Contest_4_最大的团队表现值
## 题目
公司有编号为 1到 n的 n个工程师，给你两个数组 speed和 efficiency，其中 speed[i]和 efficiency[i]分别代表第 i位工程师的速度和效率。请你返回由最多k个工程师组成的​​​​​​最大团队表现值，由于答案可能很大，请你返回结果对 10^9 + 7 取余后的结果。

团队表现值的定义为：一个团队中「所有工程师速度的和」乘以他们「效率值中的最小值」。

示例 1：
```
输入：n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 2
输出：60
解释：
我们选择工程师 2（speed=10 且 efficiency=4）和工程师 5（speed=5 且 efficiency=7）。他们的团队表现值为 performance = (10 + 5) * min(4, 7) = 60 。
```
示例 2：
```
输入：n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 3
输出：68
解释：
此示例与第一个示例相同，除了 k = 3 。我们可以选择工程师 1 ，工程师 2 和工程师 5 得到最大的团队表现值。表现值为 performance = (2 + 10 + 5) * min(5, 4, 7) = 68 。
```
示例 3：
```
输入：n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 4
输出：72
```
提示：
```
1 <= n <= 10^5
speed.length == n
efficiency.length == n
1 <= speed[i] <= 10^5
1 <= efficiency[i] <= 10^8
1 <= k <= n
```
## 解法
### 思路
- 最大值取决于`速度和 * 最小效率值`
- 效率值越小，速度和需要越大才能达到尽可能的最大值，所以如果将遍历的过程按照效率降序排列，那么在超过k的情况下，员工数一定是为4人（速度不存在负数）
- 过程：
    - 根据效率的降序排列获得员工坐标排序
    - 初始化变量：
        - heap：按照speed值暂存当前使用的员工，小顶堆
        - sum：记录当前表现值的最大值
        - total：当前累加得到的速度和
    - 根据效率降序遍历员工序列
        - 如果`count < k`：直接累加速度和，并将员工speed值存入heap中
        - 如果`count >= k && speed[i] > heap.top()`，说明当前的员工效率虽然低，但是speed高，可以替换之前使用的员工的speed最低的重新算一下最大值
        - 最后使用当前员工的效率值乘以新的`total`，和`sum`比较，取最大值
### 代码
```java
class Solution {
    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        Integer[] indexs = new Integer[n];
        for (int i = 0; i < n; i++) {
            indexs[i] = i;
        }

        Arrays.sort(indexs, (o1, o2) -> efficiency[o2] - efficiency[o1]);
        long sum = 0, total = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            int e = efficiency[indexs[i]];
            int s = speed[indexs[i]];

            if (i < k) {
                total += s;
                queue.offer(s);
            } else if (s > queue.peek()){
                total = total - queue.poll() + s;
                queue.offer(s);
            }

            sum = Math.max(sum, total * e);
        }

        return (int) (sum % 1000000007);
    }
}
```