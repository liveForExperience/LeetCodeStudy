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