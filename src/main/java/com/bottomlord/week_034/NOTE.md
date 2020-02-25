# Interview_30_包含min函数的栈
## 题目
定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。

示例:
```
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.min();   --> 返回 -3.
minStack.pop();
minStack.top();      --> 返回 0.
minStack.min();   --> 返回 -2.
```
提示：
```
各函数的调用总次数不超过 20000 次
```
## 解法
### 思路
使用一个栈：
- 定义一个min值：暂存当前的最小值
- 过程
    - push：如果需要压入的值x不比min大
        1. 将现有的min压入栈中，作为如果最小值被弹出后，更新min的参考值
        2. 将min重新赋值为x
        3. 将x压入栈中
    - pop：如果弹出的值等于min
        - 说明min需要更新了，将之前push的参考值弹出，用于更新min
    - top：调用原生api
    - min：返回min
### 代码
```java
class MinStack {
    private Stack<Integer> stack;
    private int min;
    public MinStack() {
        stack = new Stack<>();
        min = Integer.MAX_VALUE;
    }

    public void push(int x) {
        if (x <= min) {
            stack.push(min);
            min = x;
        }
        
        stack.push(x);
    }

    public void pop() {
        if (stack.pop() == min) {
            min = stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return min;
    }
}
```
# Interview_31_栈的压入和弹出序列
## 题目
输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。

示例 1：
```
输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
输出：true
解释：我们可以按以下顺序执行：
push(1), push(2), push(3), push(4), pop() -> 4,
push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
```
示例 2：
```
输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
输出：false
解释：1 不能在 2 之前弹出。
```
提示：
```
0 <= pushed.length == popped.length <= 1000
0 <= pushed[i], popped[i] < 1000
pushed 是 popped 的排列。
```
## 解法
### 思路
栈：
- 初始化一个栈
- 定义两个数组的指针
    - push
    - pop
- 过程：
    - 如果两个数组的任意一个没有遍历完，持续循环
    - 循环中
        1. 判断当前stack是否为空，如果不为空，查看pop指向的元素是否与栈顶元素相等
        2. 如果相等：
            - 栈顶元素出栈
            - pop指针后移
            - 继续循环
        3. 如果不相等：
            - 判断push是否越界，如果越界说明该push的元素都放入栈中了，还有需要pop的元素，且这些元素无法从栈顶获取，终止循环
            - 将push指向的元素放入栈中
        4. 最终判断栈中是否有元素，有元素说明popped数组中的元素不是有效的出栈顺序
### 代码
```java
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int push = 0, pop = 0;
        while (push < pushed.length || pop < popped.length) {
            if (!stack.isEmpty() && stack.peek() == popped[pop]) {
                stack.pop();
                pop++;
                continue;
            }
            
            if (push == pushed.length) {
                break;
            }
            
            stack.push(pushed[push++]);
        }

        return stack.isEmpty();
    }
}
```
# Interview_32I_从上到下打印二叉树
## 题目
从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。

例如:
```
给定二叉树: [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
返回：

[3,9,20,15,7]
```
 
提示：
```
节点总数 <= 1000
```
## 解法
### 思路
bfs
### 代码
```java
class Solution {
    public int[] levelOrder(TreeNode root) {
        if (root == null) {
            return new int[0];
        }

        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int count = queue.size();

            while (count-- > 0) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }

                list.add(node.val);

                if (node.left != null) {
                    queue.add(node.left);
                }
                
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
```
## 解法二
### 思路
不用lambda，改用遍历动态数组生成新的数组
### 代码
```java
class Solution {
    public int[] levelOrder(TreeNode root) {
        if (root == null) {
            return new int[0];
        }

        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int count = queue.size();

            while (count-- > 0) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }

                list.add(node.val);

                if (node.left != null) {
                    queue.add(node.left);
                }

                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }

        int[] ans = new int[list.size()];
        int index = 0;
        for (int num : list) {
            ans[index++] = num;
        }
        
        return ans;
    }
}
```
# Interview_32II_从上到下打印二叉树II
## 题目
从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。

例如:
```
给定二叉树: [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
返回其层次遍历结果：

[
  [3],
  [9,20],
  [15,7]
]
```
提示：
```
节点总数 <= 1000
```
## 解法
### 思路
bfs
### 代码
```java
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        
        List<List<Integer>> ans = new ArrayList<>();
        
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int count = queue.size();
            List<Integer> list = new ArrayList<>();
            while (count-- > 0) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }
                
                list.add(node.val);
                
                if (node.left != null) {
                    queue.offer(node.left);
                }
                
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            
            ans.add(list);
        }
        
        return ans;
    }
}
```