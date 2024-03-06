# [LeetCode_225_用队列实现栈](https://leetcode.cn/problems/implement-stack-using-queues)
## 解法
### 思路
- 思考过程：
  - 队列是先进先出，而栈则是先进后出。为了实现用队列代替栈的能力，就要解决如何让后入的元素能被先弹出。
  - 思考后发现，每次`push`的时候必须要用一个空的队列来存储新加入的元素，这样才能保证该元素在队列的顶端，在使用该队列实现`pop`的时候，才能弹出正确的元素。
  - 那么当连续`push`的时候，为了保证每一次的新元素都能在队列的顶端，就需要一个除了空的队列之外的队列来存储历史的元素。而这些历史元素应该能够保证先进后出的顺序
  - 2个队列已经确定了，第一次`push`的元素也能保证先进后出，那么为了解决所有元素都是先进后出的顺序排列，就需要思考如何在2个队列之间进行同步：
    - 如果一开始2个队列都是空的，那么`push`进一个元素之后，它一定是先进后出的
    - 如果此时什么都不动，那么为了让第二个`push`进来的元素也能是先进后出的，就必须让它进入到另一个空的队列里。
    - 此时2个队列都有元素了，此时如果再放入一个元素，就没地方放了，所以，需要同步的逻辑就出现了。
    - 从第一次`push`重新思考，根据之前的经验，在`push`的时候，必须要有一个空的队列存储新`push`的元素，也就说明，每次存储完之后，就需要在2个队列中保留出一个新的空队列，意味着2个队列的元素要合并。
    - 现在将接收新元素的队列称之为`in`，另一个称之为`out`，那合并的方式就有2种：
      - `in`收到的1个元素，`push`到`out`中，但这样显然是不行的，因为这样这个元素就无法在下次第一个被`pop`出来了
      - `out`中的历史元素，都`push`到`in`中，这样，至少`in`中原来的那个元素的顺序保证了，那么历史元素的顺序保证了吗？因为历史元素如果能保证存储的时候就是先进先出的顺序，那么放入`in`的时候也就能保证顺序了，所以问题就变成了如何保证`out`中的元素顺序
      - 这里稍微转一下脑筋，`in`的顺序现在看是能保证的，怎么保证，第一个是保证的，第二个如果从`out`来的时候是保证，也能保证了，那么如果让`out`就是`in`是不是就解决了？所以，方式就是，将`out`的历史元素`push`到`in`中，此时`out`就空了，`in`的顺序也正确了，然后将2者互换一下，就ok了
- 算法过程：
  - 初始化2个队列，分别是`in`和`out`
  - `push`的时候：
    - 将元素放入`in`
    - 将`out`的元素依次`pop`并`push`到`in`中
    - 将`in`和`out`互换
  - `pop`的时候：
    - 将`out`的队头元素弹出
  - `peek`的时候：
    - 返回`out`的队头元素
  - `isEmpty`的时候：
    - 返回`out`是否为空
### 代码
```java
class MyStack {

    private Queue<Integer> in, out;

    public MyStack() {
        this.in = new ArrayDeque<>();
        this.out = new ArrayDeque<>();
    }

    public void push(int x) {
        in.offer(x);
        while (!out.isEmpty()) {
            in.offer(out.poll());
        }
        
        Queue<Integer> tmp = in;
        in = out;
        out = tmp;
    }

    public int pop() {
        if (out.isEmpty()) {
            return -1;
        }
        
        return out.poll();
    }

    public int top() {
        if (out.isEmpty()) {
            return -1;
        }
        
        return out.peek();
    }

    public boolean empty() {
        return out.isEmpty();
    }
}
```
# [LeetCode_2917_找出数组中的Kor值](https://leetcode.cn/problems/find-the-k-or-of-an-array)
## 解法
### 思路
- 算法过程：
  - 2层遍历，第一层遍历32位，第二层遍历`nums`元素
  - 依次对每个数字当前位`i`为1的数字进行计数，如果大于k，则累加`2 ^ i`
  - 返回最后的累加值
### 代码
```java
class Solution {
    public int findKOr(int[] nums, int k) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int cnt = 0;
            for (int num : nums) {
                if ((num & (1 << i)) != 0) {
                    cnt++;
                }
                
                if (cnt >= k) {
                    break;
                }
            }
            
            if (cnt >= k) {
                ans += (1 << i);
            }
        }
        
        return ans;
    }
}
```
# [LeetCode_232_用栈实现队列](https://leetcode.cn/problems/implement-queue-using-stacks)
## 解法
### 思路
- 思考过程：
  - 栈是先进后出的，队列是先进先出的。这也就意味着，按顺序放入栈的元素，如果按顺序从栈中取出，是反着的。但如果，将这些元素再放入另一个栈中，那么再从第二个栈中取出，就是正的了。
  - 所以可以用2个栈来实现队列的功能
    - 1个栈`in`专门用来存储`push`的元素
    - 1个栈`out`专门用来负责`pop`
      - `out`栈中的元素是从`in`栈中`pop`出来再`push`进去的
      - 如果`out`栈的元素不为空，那么直接从这个栈中获取元素
      - 如果`out`栈中的元素为空，那么就将`in`中的元素全部取出来放入`out`中
- 算法过程：
  - 初始化2个栈
  - `push`：将元素放入`in`
  - `pop`：
    - 如果`out`不为空，弹出并返回栈顶元素
    - 否则，将`in`的元素全部放入`out`，再从`out`中取出第一个元素
  - `peek`：
    - 如果`out`不为空，返回栈顶元素
    - 否则，将`in`的元素全部放入`out`中，再返回`out`的栈顶元素
  - `empty`: 如果`in`和`out`同时为空，返回true，否则返回false
### 代码
```java
class MyQueue {

    private LinkedList<Integer> in, out;

    public MyQueue() {
        this.in = new LinkedList<>();
        this.out = new LinkedList<>();
    }

    public void push(int x) {
        this.in.push(x);
    }

    public int pop() {
        return doReturn(() -> this.out.pop());
    }

    public int peek() {
        return doReturn(() -> this.out.peek());
    }

    public boolean empty() {
        return this.in.isEmpty() && this.out.isEmpty();
    }
    
    public int doReturn(Supplier<Integer> fun) {
        if (empty()) {
            return -1;
        }

        if (!this.out.isEmpty()) {
            return fun.get();
        }

        transferInToOut();
        return fun.get();
    }
    
    private void transferInToOut() {
        if (this.in.isEmpty()) {
            return;
        }

        while (!this.in.isEmpty()) {
            this.out.push(this.in.pop());
        }
    }
}
```