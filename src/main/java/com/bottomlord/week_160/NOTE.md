# [LeetCode_622_设计循环队列](https://leetcode.cn/problems/design-circular-queue/)
## 解法
### 思路
- 属性：
  - cap：容量，设置为k+1，多出的一个slot用于简化编程，方便判断为空状态，由rear指针指向
  - front：指向队列头部的指针
  - rear：指向队列尾部后一个位置的指针，这个指针指向的slot永远代表为空
  - queue：数组，长度初始化为cap，用户存放实际的元素
- 算法：
  - 添加2个私有方法，方便逻辑实现复用
    - 向前移动，因为是循环队列，所以需要考虑从数组尾部回到数组头部的移动，需要借助mod计算
    - 向后移动也是一样
  - 其他算法就通过如上4种属性结合实现
### 代码
```java
class MyCircularQueue {
    private int front, rear, cap;
    private int[] queue;
    public MyCircularQueue(int k) {
        cap = k + 1;
        this.queue = new int[cap];
    }

    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }

        queue[rear] = value;
        rear = moveForward(rear);
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }

        front = moveForward(front);
        return true;
    }

    public int Front() {
        return isEmpty() ? -1 : queue[front];
    }

    public int Rear() {
        return isEmpty() ? -1 : queue[moveBack(rear)];
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public boolean isFull() {
        return front == moveForward(rear);
    }

    private int moveForward(int pos) {
        return (pos + 1) % cap;
    }

    private int moveBack(int pos) {
        return (pos + cap - 1) % cap;
    }
}
```
# [LeetCode_899_有序队列](https://leetcode.cn/problems/orderly-queue/)
## 解法
### 思路
- 当k==1的时候，不能变更字符串顺序，所以只能通过题目要求的方式，轮换字符串头部字符生成新的字符串，在轮转的时候，记录最小的字符串
- 当k>1的时候，只要返回字符串变换后的最小字典序排列即可
### 代码
```java
class Solution {
  public String orderlyQueue(String s, int k) {
    int n = s.length();
    if (k > 1) {
      char[] cs = s.toCharArray();
      Arrays.sort(cs);
      return new String(cs);
    }

    String ans = s;
    for (int i = 0; i < n; i++) {
      String cur = s.substring(i, n) + s.substring(0, i);

      if (ans.compareTo(cur) > 0) {
        ans = cur;
      }
    }

    return ans;
  }
}
```
# [LeetCode_2315_统计星号](https://leetcode.cn/problems/count-asterisks/)
## 解法
### 思路
- 遍历字符串
  - 如果遇到`|`，使用count记录
  - 如果遇到`*`，判断count是否被2取模后是0，如果是，那么就说明不在2个`|`之间，就用ans变量记录下来
- 遍历结束后返回ans个数作为结果
### 代码
```java
class Solution {
  public int countAsterisks(String s) {
    int count = 0, ans = 0;
    char[] cs = s.toCharArray();
    for (int c : cs) {
      if (c != '*' && c != '|') {
        continue;
      }

      if (c == '|') {
        count++;
        continue;
      } else {
        if (count % 2 == 0) {
          ans++;
        }
      }
    }

    return ans;
  }
}
```