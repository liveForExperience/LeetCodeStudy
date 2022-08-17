# [LeetCode_749_隔离病毒](https://leetcode.cn/problems/contain-virus/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_641_设计循环双端队列](https://leetcode.cn/problems/design-circular-deque/)
## 解法
### 思路
- 使用数组模拟循环双端队列
- 增加一个数组中的空位，即`cap = len + 1`
  - 空位（rear）与起始坐标（front）相等，代表队列为空
  - 空位与front相邻，即`(rear + 1) % cap = front`，代表队列已满
  - 队列最后一个元素放置在于rear相邻的空位上，即`(rear - 1 + cap) % cap`
- 增加这个空位的原因：
  - 如果不增加这个空位，首先思考如何来定义空这个状态，发现，只有`front == rear`这个状态是最好的表示方式
  - 当`front == rear`代表空之后，那么rear如果并不是空出来的就完全不行了，因为rear又有值，又要代表空，肯定是不行的
- 队列前部插入，就向后移动front，并将元素放入移动后的位置
- 队列后部插入，就将元素放入rear当前的位置，并向前移动rear
- 删除前部元素，就是向前移动front
- 删除后部元素，就是向后移动rear
- 前进就是：`(index + 1) % cap`
- 向后就是：`(index - 1 + cap) % cap`
### 代码
```java
class MyCircularDeque {
    private final int cap;
    private int front, rear;
    private final int[] queue;

    public MyCircularDeque(int k) {
        this.cap = k + 1;
        this.queue = new int[cap];
        this.front = rear = 0;
    }

    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }

        queue[front = moveBackward(front)] = value;
        return true;
    }

    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }

        queue[rear] = value;
        rear = moveForward(rear);
        return true;
    }

    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }

        front = moveForward(front);
        return true;
    }

    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }

        rear = moveBackward(rear);
        return true;
    }

    public int getFront() {
        return isEmpty() ? -1 : queue[front];
    }

    public int getRear() {
        return isEmpty() ? -1 : queue[moveBackward(rear)];
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public boolean isFull() {
        return moveForward(rear) == front;
    }

    private int moveForward(int index) {
        return (index + 1) % cap;
    }

    private int moveBackward(int index) {
        return (index - 1 + cap) % cap;
    }
}
```
# [LeetCode_1302_层数最深的叶子节点的和](https://leetcode.cn/problems/deepest-leaves-sum/)
## 解法
### 思路
bfs
### 代码
```java
class Solution {
    public int deepestLeavesSum(TreeNode root) {
        int sum = 0;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int count = queue.size(), cur = 0;
            while (count-- > 0) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }
                
                cur += node.val;
                
                if (node.left != null) {
                    queue.offer(node.left);
                }
                
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            
            sum = cur;
        }
        
        return sum;
    }
}
```