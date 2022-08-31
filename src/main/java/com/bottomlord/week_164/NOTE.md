# [LeetCode_749_隔离病毒](https://leetcode.cn/problems/contain-virus/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_946_验证栈序列](https://leetcode.cn/problems/validate-stack-sequences/)
## 解法
### 思路
- 初始化一个栈
- 初始化两个坐标，对应两个数组
- 循环，退出条件时push指针越界
  1. 处理pop数组，如果stack不为空，且栈顶是当前指向的pop元素，就操作stack的出栈操作，且循环处理该过程
  2. 处理push数组，如果push指针未越界，就push当前元素到stack
- 再处理pop数组，看是否能继续pop栈
- 最后判断栈中是否为空，是的话就符合题意，否则不符合
### 代码
```java
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int pui = 0, poi = 0, pulen = pushed.length, polen = popped.length;
        while (pui < pulen) {
            while (poi < polen && !stack.isEmpty() && stack.peek() == popped[poi]) {
                stack.pop();
                poi++;
            }
            
            stack.push(pushed[pui++]);
        }

        while (poi < polen && !stack.isEmpty() && stack.peek() == popped[poi]) {
            stack.pop();
            poi++;
        }
        
        return stack.isEmpty();
    }
}
```