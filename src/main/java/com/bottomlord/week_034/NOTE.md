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
# Interview_36_二叉搜索树和双向链表
## 题目
输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
 
我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。

特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针。
## 解法
### 思路
dfs：
- 因为是二叉搜索树，所以中序遍历获得的是一个升序序列，符合双向链表的顺序
- 因为要生成节点的前驱(left)和后置(right)指针，所以需要暂存树中最大值的指针，用这个指针和当前递归到的节点产生联系
- 同时因为要闭环整个链表，所以也要保留最小值的指针，用来在遍历完二叉树后将头尾闭环
- 过程：
    - 中序遍历
    - 先递归左子树
    - 操作当前节点时：
        - 判断最大值指针是否存在
            - 如果存在，就开始前后节点的关联
            - 如果不存在，说明当前节点为最小值，初始化最小和最大值的指针
        - 变更最大值指针为当前节点
    - 递归右子树
### 代码
```java
class Solution {
    private Node smallest;
    private Node biggest;

    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        
        dfs(root);
        smallest.left = biggest;
        biggest.right = smallest;
        return smallest;
    }

    private void dfs(Node node) {
        if (node != null) {
            dfs(node.left);

            if (biggest != null) {
                biggest.right = node;
                node.left = biggest;
            } else {
                smallest = node;
            }
            biggest = node;

            dfs(node.right);
        }
    }
}
```
# LeetCode_426_将二叉搜索树转化为排序的双向链表
## 题目
将一个二叉搜索树就地转化为一个已排序的双向循环链表。可以将左右孩子指针作为双向循环链表的前驱和后继指针。

我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。

特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针。
## 解法
### 思路
解法与面试题36一致
### 代码
```java
class Solution {
    private Node smallest;
    private Node biggest;

    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        
        dfs(root);
        smallest.left = biggest;
        biggest.right = smallest;
        return smallest;
    }

    private void dfs(Node node) {
        if (node != null) {
            dfs(node.left);

            if (biggest != null) {
                biggest.right = node;
                node.left = biggest;
            } else {
                smallest = node;
            }
            biggest = node;

            dfs(node.right);
        }
    }
}
```
# Interview_37_序列化二叉树
## 题目
请实现两个函数，分别用来序列化和反序列化二叉树。

示例: 
```
你可以将以下二叉树：

    1
   / \
  2   3
     / \
    4   5

序列化为 "[1,2,3,null,null,4,5]"
```
## 解法
### 思路
dfs：
- 序列化：遍历二叉树，将当前元素生成对应的字符串返回，返回过程中将元素与元素之间用逗号拼接
- 反序列化：
    - 将字符串根据逗号切分，并放入list
    - 递归：
        - 退出条件：当前list头元素为`null`，将头元素取出后，返回
        - 否则就生成对应数值的节点，将头元素去除后继续左右子树的递归
### 代码
```java
public class Codec {
        public String serialize(TreeNode root) {
            return root == null ? "null" : String.valueOf(root.val) + "," +  serialize(root.left) + "," + serialize(root.right);
        }

        public TreeNode deserialize(String data) {
            return doDeserialize(new LinkedList<>(Arrays.asList(data.split(","))));
        }
        
        private TreeNode doDeserialize(LinkedList<String> list) {
            String num = list.removeFirst();
            
            if (Objects.equals(num, "null")) {
                return null;
            }
            
            TreeNode node = new TreeNode(Integer.parseInt(num));
            node.left = doDeserialize(list);
            node.right = doDeserialize(list);
            
            return node;
        }
}
```
# LeetCode_297_二叉树的序列化与反序列化
## 题目
序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。

请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。

示例: 
```
你可以将以下二叉树：

    1
   / \
  2   3
     / \
    4   5

序列化为 "[1,2,3,null,null,4,5]"
```
## 解法
### 思路
思路和面试题37一致
### 代码
```java
public class Codec {
        public String serialize(TreeNode root) {
            return root == null ? "null" : String.valueOf(root.val) + "," +  serialize(root.left) + "," + serialize(root.right);
        }

        public TreeNode deserialize(String data) {
            return doDeserialize(new LinkedList<>(Arrays.asList(data.split(","))));
        }
        
        private TreeNode doDeserialize(LinkedList<String> list) {
            String num = list.removeFirst();
            
            if (Objects.equals(num, "null")) {
                return null;
            }
            
            TreeNode node = new TreeNode(Integer.parseInt(num));
            node.left = doDeserialize(list);
            node.right = doDeserialize(list);
            
            return node;
        }
}
```
# Interview_38_字符串的排列
## 题目
输入一个字符串，打印出该字符串中字符的所有排列。

你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。

示例:
```
输入：s = "abc"
输出：["abc","acb","bac","bca","cab","cba"]
```
限制：
```
1 <= s 的长度 <= 8
```
## 解法
### 思路
回溯：
- 使用一个布尔数组`visit`记录当前一次搜索路径中已经遍历到的字符串下标
- 回溯时处理`visit`中当前遍历到的元素为false
- 递归中传入一个set集合用来起到保存可能结果和去重的功能
### 代码
```java
class Solution {
    public String[] permutation(String s) {
        Set<String> set = new HashSet<>();
        backTrack(new StringBuilder(), s.toCharArray(), set, new boolean[s.length()]);
        return set.toArray(new String[0]);
    }

    private void backTrack(StringBuilder sb, char[] cs, Set<String> set, boolean[] visited) {
        if (sb.length() == cs.length) {
            set.add(sb.toString());
            return;
        }

        for (int i = 0; i < cs.length; i++) {
            if (visited[i]) {
                continue;
            }

            sb.append(cs[i]);
            visited[i] = true;
            backTrack(sb, cs, set, visited);
            visited[i] = false;
            sb.delete(sb.length() - 1, sb.length());
        }
    }
}
```
## 解法二
### 思路
回溯：
- 不使用set去重
- 布尔数组`visited`不记录遍历到的s的字符坐标，记录当前递归层使用了什么字符，如果使用过就跳过，避免了当前层字符的重复使用，从而起到去重的效果
- 不使用StringBuilder来记录当前遍历的路径，直接值通过当前遍历到的s的下标`index`(也就是递归的层数)与当前层循环中遍历的下标`i`(也就是可以变换的字符)进行互换
- `i >= index`，因为当前i和`index - k`的交换可能在之前层的递归处理中已经处理过了
- 其他逻辑和解法一一致
### 代码
```java
class Solution {
    public String[] permutation(String s) {
        List<String> list = new ArrayList<>();
        backTrack(s.toCharArray(), 0, list);
        return list.toArray(new String[0]);
    }

    private void backTrack(char[] cs, int index, List<String> list) {
        if (index == cs.length - 1) {
            list.add(new String(cs));
            return;
        }

        boolean[] used = new boolean[256];
        for (int i = index; i < cs.length; i++) {
            if (used[cs[i]]) {
                continue;
            }

            used[cs[i]] = true;
            swap(cs, index, i);
            backTrack(cs, index + 1, list);
            swap(cs, index, i);
        }

    }

    private void swap(char[] cs, int x, int y) {
        char tmp = cs[x];
        cs[x] = cs[y];
        cs[y] = tmp;
    }
}
```
# Interview_39_数组中出现次数超过一半的数字
## 题目
数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。

你可以假设数组是非空的，并且给定的数组总是存在多数元素。

示例 1:
```
输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
输出: 2
```
限制：
```
1 <= 数组长度 <= 50000
```
## 解法
### 思路
用map计数
### 代码
```java
class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int target = nums.length / 2 + 1;
        
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (map.get(num) == target) {
                return num;
            }
        }
        
        return -1;
    }
}
```
## 解法二
### 思路
排序，然后遍历数组时直接看`i`下标元素和`i + len / 2`下标元素是否相同，相同就返回
### 代码
```java
class Solution {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        int target = nums.length / 2;
        for (int i = 0; i < target + 1; i++) {
            if (nums[i] == nums[i + target]) {
                return nums[i];
            }
        }
        
        return -1;
    }
}
```
## 解法三
### 思路
抵消法：因为有一个数字大于一半，所以如果这个数和所有不同的数相抵消，留下的一定是这个数。
### 代码
```java
class Solution {
    public int majorityElement(int[] nums) {
        int ans = nums[0], time = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == ans) {
                time++;
            } else if (time == 0) {
                ans = nums[i];
                time++;
            } else {
                time--;
            }
        }
        
        return ans;
    }
}
```
# Interview_40_最小的k个数
## 题目
输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。

示例 1：
```
输入：arr = [3,2,1], k = 2
输出：[1,2] 或者 [2,1]
```
示例 2：
```
输入：arr = [0,1,2,1], k = 1
输出：[0]
```
限制：
```
0 <= k <= arr.length <= 10000
0 <= arr[i] <= 10000
```
## 解法
### 思路
使用小顶堆，将元素依次放入小顶堆后，依次取出k个返回
### 代码
```java
class Solution {
    public int[] getLeastNumbers(int[] arr, int k) {
        int[] ans = new int[k];
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int num : arr) {
            queue.offer(num);
        }
        
        for (int i = 0; i < k; i++) {
            ans[i] = queue.poll();
        }
        
        return ans;
    }
}
```
## 解法二
### 思路
排序并返回前k个元素
### 代码
```java
class Solution {
    public int[] getLeastNumbers(int[] arr, int k) {
        int[] ans = new int[k];
        Arrays.sort(arr);
        
        for (int i = 0; i < k; i++) {
            ans[i] = arr[i];
        }
        
        return ans;
    }
}
```
## 解法三
### 思路
自己实现小顶堆
### 代码
```java

```
# Interview_41_数据流中的中位数
## 题目
如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。

例如，

[2,3,4] 的中位数是 3

[2,3] 的中位数是 (2 + 3) / 2 = 2.5

设计一个支持以下两种操作的数据结构：
```
void addNum(int num) - 从数据流中添加一个整数到数据结构中。
double findMedian() - 返回目前所有元素的中位数。
```
示例 1：
```
输入：
["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
[[],[1],[2],[],[3],[]]
输出：[null,null,null,1.50000,null,2.00000]
```
示例 2：
```
输入：
["MedianFinder","addNum","findMedian","addNum","findMedian"]
[[],[2],[],[3],[]]
输出：[null,null,2.00000,null,2.50000]
```
限制：
```
最多会对 addNum、findMedia进行 50000 次调用。
```
## 解法
### 思路
- 维护两个堆：
    - 一个大顶堆`lH`：存储从小到中间位置的元素
    - 一个小顶堆`sH`：存储从中间位置到最大位置的元素
- 维护插入值的总数
- 过程：
    - 插入时，累加总数：
        - 如果`sH`大小不为0，将值先插入小顶堆，再将小顶堆堆顶的元素放入大顶堆`lH`
        - 否则直接将值放入大顶堆`lH`
    - 如果需要找到中位数：
        - 将大顶堆的值`size / 2 - sH.size()`次的放入小顶堆，使得两个堆的值一样多或大顶堆比小顶堆多一个
        - 根据size的奇偶返回中间值：
            - 奇：返回大顶堆顶的值
            - 偶：返回连个堆顶元素的平均值
### 代码
```java
class MedianFinder {
    private PriorityQueue<Double> lH;
    private PriorityQueue<Double> sH;
    private int size;

    public MedianFinder() {
        this.lH = new PriorityQueue<>();
        this.sH = new PriorityQueue<>(Comparator.reverseOrder());
        this.size = 0;
    }

    public void addNum(int num) {
        double dNum = num;
        if (!sH.isEmpty()) {
            sH.offer(dNum);
            dNum = sH.poll();
        }
        lH.offer(dNum);
        size++;
    }

    public double findMedian() {
        for (int i = 0; i < size / 2 - sH.size(); i++) {
            sH.offer(lH.poll());
        }

        return (size & 1) == 1 ? lH.peek() : (sH.peek() + lH.peek()) / 2;
    }
}
```