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
# [LeetCode_1224_最大相等频率](https://leetcode.cn/problems/maximum-equal-frequency/)
## 解法
### 思路
- 使用2个数组和一个变量记录如下3项数据
  - count[]：记录数值x出现的次数
  - countFreq[]：记录出现次数为y的数值有多少个
  - maxCount：记录数值出现次数最大的值
- 遍历数组，并在每次遍历的时候判断，如上3项数据是否符合如下的3种状态之一，如果有符合就更新最大长度
  - `maxCount == 1`：说明所有元素的出现次数都是1，那么减掉任何一个都符合规则
  - 除了一个数值外，所有数值出现的个数都与maxCount相等，而那个特殊的数值是1
  - 除了一个数值与maxCount一样外，所有其他数值的元素个数都相等，且都比maxCount小1
### 代码
```java
class Solution {
    public int maxEqualFreq(int[] nums) {
        int[] count = new int[100001], countFreq = new int[100001];
        int maxCount = 0, ans = 0;

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (countFreq[count[num]] != 0) {
                countFreq[count[num]]--;
            }

            count[num]++;
            countFreq[count[num]]++;
            maxCount = Math.max(maxCount, count[num]);

            boolean ok = maxCount == 1 ||
                    (countFreq[1] == 1 && maxCount * countFreq[maxCount] == i) ||
                    (countFreq[maxCount] == 1 && countFreq[maxCount - 1] * (maxCount - 1) == i - maxCount + 1);
            
            if (ok) {
                ans = i + 1;
            }
        }

        return ans;
    }
}
```
# [LeetCode_2341_数组能形成多少数对](https://leetcode.cn/problems/maximum-number-of-pairs-in-array/)
## 解法
### 思路
- 遍历并桶计数
- 遍历桶，并计算数对和剩余个数
  - 计数值 / 2 相当于当前数的数对个数
  - 如果计数值是奇数，遗留数会增加
### 代码
```java
class Solution {
    public int[] numberOfPairs(int[] nums) {
        int[] bucket = new int[101];
        for (int num : nums) {
            bucket[num]++;
        }

        int count = 0, left = 0;
        for (int num : bucket) {
            count += num / 2;
            left += num % 2 == 1 ? 1 : 0;
        }

        return new int[]{count, left};
    }
}
```
# [LeetCode_2347_最好的扑克手牌](https://leetcode.cn/problems/best-poker-hand/)
## 解法
### 思路
根据题目列出的4种等级，依次判断：
- 同花顺：所有suit相同
- 3条：有3个一样的数字
- pair：有2个一样的数字
- High Card：5张都不一样
### 代码
```java
class Solution {
  public String bestHand(int[] ranks, char[] suits) {
    int[] suitBucket = new int[4], rankBucket = new int[14];
    for (int rank : ranks) {
      rankBucket[rank]++;
    }

    for (char suit : suits) {
      suitBucket[suit - 'a']++;
    }

    for (int count : suitBucket) {
      if (count == 5) {
        return "Flush";
      }
    }

    boolean isPair = false;
    for (int count : rankBucket) {
      if (count >= 3) {
        return "Three of a Kind";
      }

      if (count == 2) {
        isPair = true;
      }
    }

    return isPair ? "Pair" : "High Card";
  }
}
```