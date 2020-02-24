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