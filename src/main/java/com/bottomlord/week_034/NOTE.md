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
# Interview_32III_1_从上到下打印二叉树III
## 题目
请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。

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
  [20,9],
  [15,7]
]
```
提示：
```
节点总数 <= 1000
```
## 解法
### 思路
- bfs
- 使用标识符表示是否需要逆转每一层记录的内容
### 代码
```java
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        boolean right = false;
        List<List<Integer>> ans = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int count = queue.size();
            List<Integer> list = new ArrayList<>(count);
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
            if (right) {
                Collections.reverse(list);
            }
            ans.add(list);
            right = !right;
        }

        return ans;
    }
}
```
# Interview_33_二叉搜索树后序遍历序列
## 题目
输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。

参考以下这颗二叉搜索树：
```
     5
    / \
   2   6
  / \
 1   3
``` 
示例 1：
```
输入: [1,6,3,2,5]
输出: false
```
示例 2：
```
输入: [1,3,2,6,5]
输出: true
```
提示：
```
数组长度 <= 1000
```
## 解法
### 思路
- 后序遍历生成的序列特性：
    - 序列结尾元素为根节点
    - 除结尾元素外，剩下的序列会被分成连续的两大部分，左子树所有节点+右子树所有节点
- 递归：
    - 参数：
        - `start`：序列的起始坐标
        - `end`：序列的结尾坐标
    - 过程：
        - 判断当前序列是否只有不超2个的元素，如果是就返回true
        - 获取序列结尾元素，标记为根节点
        - 遍历序列，找到小于根节点的所有元素，标记为左子树
        - 遍历剩余序列，判断序列中是否还有小于根节点的元素，如果有就说明不是二叉搜索树，返回false
        - 继续递归判断左右子树
### 代码
```java
class Solution {
    public boolean verifyPostorder(int[] postorder) {
        return recurse(postorder, 0, postorder.length - 1);
    }
    
    private boolean recurse(int[] postOrder, int start, int end) {
        if (end - start < 2) {
            return true;
        }
        
        int root = postOrder[end], right = start;
        
        for (; right < end; right++) {
            if (postOrder[right] > root) {
                break;
            }
        }
        
        for (int i = right; i < end; i++) {
            if (postOrder[i] < root) {
                return false;
            }
        }
        
        return recurse(postOrder, start, right - 1) && recurse(postOrder, right, end - 1);
    }
}
```
# Interview_34_二叉树中和为某一值得路径
## 题目
输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。

示例:
```
给定如下二叉树，以及目标和 sum = 22，

              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
返回:

[
   [5,4,11,2],
   [5,8,4,5]
]
```
提示：
```
节点总数 <= 10000
```
## 解法
### 思路
回溯
- 路径指根节点到叶子节点
### 代码
```java
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ans = new ArrayList<>();
        backTrack(root, 0, sum, new LinkedList<>(), ans);
        return ans;
    }

    private void backTrack(TreeNode node, int count, int sum, LinkedList<Integer> list, List<List<Integer>> ans) {
        if (node == null) {
            return;
        }

        count += node.val;
        if (node.left == null && node.right == null) {
            if (count == sum) {
                list.add(node.val);
                ans.add(new ArrayList<>(list));
                list.removeLast();
                return;
            }
        }

        list.add(node.val);
        backTrack(node.left, count, sum, list, ans);
        list.removeLast();

        list.add(node.val);
        backTrack(node.right, count, sum, list, ans);
        list.removeLast();
    }
}
```
# Interview_35_复杂链表的复制
## 题目
请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。

示例 1：
```
输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
```
示例 2：
```
输入：head = [[1,1],[2,1]]
输出：[[1,1],[2,1]]
```
示例 3：
```
输入：head = [[3,null],[3,0],[3,null]]
输出：[[3,null],[3,0],[3,null]]
```
示例 4：
```
输入：head = []
输出：[]
解释：给定的链表为空（空指针），因此返回 null。
```
提示：
```
-10000 <= Node.val <= 10000
Node.random 为空（null）或指向链表中的节点。
节点数目不超过 1000 。
```
## 解法
### 思路
- 初始化一个哈希表`map`，存储原节点和复制节点的映射关系
- 遍历链表，生成`map`
- 再次遍历链表，通过`map`，将复制节点的指针关系关联
### 代码
```java
class Solution {
    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        map.put(null, null);
        
        Node node = head;
        while (node != null) {
            map.put(node, new Node(node.val));
            node = node.next;
        }
        
        node = head;
        while (node != null) {
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
            node = node.next;
        }
        
        return map.get(head);
    }
}
```
# LeetCode_138_复制带随机指针的链表
## 题目
给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。

要求返回这个链表的 深拷贝。 

我们用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
```
val：一个表示 Node.val 的整数。
random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
```
示例 1：
```
输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
```
示例 2：
```
输入：head = [[1,1],[2,1]]
输出：[[1,1],[2,1]]
```
示例 3：
```
输入：head = [[3,null],[3,0],[3,null]]
输出：[[3,null],[3,0],[3,null]]
```
示例 4：
```
输入：head = []
输出：[]
解释：给定的链表为空（空指针），因此返回 null。
```
提示：
```
-10000 <= Node.val <= 10000
Node.random 为空（null）或指向链表中的节点。
节点数目不超过 1000 。
```
## 解法
### 思路
思路和面试题35相一致
### 代码
```java
class Solution {
    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        map.put(null, null);

        Node node = head;
        while (node != null) {
            map.put(node, new Node(node.val));
            node = node.next;
        }

        node = head;
        while (node != null) {
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
            node = node.next;
        }

        return map.get(head);
    }
}
```
## 解法二
### 思路
dfs：
- 遍历访问复杂链表，因为有两个指针，且一个指针为随机，所以整个链表可以看作一个可能的有环图，所以需要暂存已访问的节点，避免进入死循环
- 初始化map用于存储已经访问过的原节点与新节点之间的关系
- 过程：
    - 退出条件：当前节点为空
    - 如果当前节点已经访问过，从`map`中获取并直接返回
    - 根据访问的节点生成新节点，并放入`map`中
    - 继续递归原节点`next`和`random`指针指向节点
### 代码
```java
class Solution {
    public Node copyRandomList(Node head) {
        return dfs(head, new HashMap<>());
    }
    
    private Node dfs(Node node, Map<Node, Node> map) {
        if (node == null) {
            return null;
        }
        
        if (map.containsKey(node)) {
            return map.get(node);
        }
        
        Node copy = new Node(node.val);
        map.put(node, copy);
        
        copy.next = dfs(node.next, map);
        copy.random = dfs(node.random, map);
        
        return copy;
    }
}
```
## 解法三
### 思路
迭代：
- 遍历原链表，将新新节点放在原节点`node`与`node.next`之间，并相连
    - `node.next = copy, copy.next = node.next.next`;
- 再次遍历新组合的链表，根据原节点的`random`指针更新新节点的`random`指针
    - `copy.random = node.random.next`
- 再次遍历新组合的链表，将新节点重新组合
    - `copy.next = copy.next.next`
### 代码
```java
class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        Node node = head;
        while (node != null) {
            Node copy = new Node(node.val);
            copy.next = node.next;
            node.next = copy;
            node = copy.next;
        }

        node = head;
        while (node != null) {
            node.next.random = node.random != null ? node.random.next : null;
            node = node.next.next;
        }

        Node oldList = head, newList = head.next, newHead = head.next;
        while (oldList != null) {
            oldList.next = oldList.next.next;
            newList.next = newList.next != null ? newList.next.next : null;
            oldList = oldList.next;
            newList = newList.next;
        }

        return newHead;
    }
}
```