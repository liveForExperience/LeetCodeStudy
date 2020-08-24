# LeetCode_227_基本计算器II
## 题目
实现一个基本的计算器来计算一个简单的字符串表达式的值。

字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。

示例 1:
```
输入: "3+2*2"
输出: 7
```
示例 2:
```
输入: " 3/2 "
输出: 1
```
示例 3:
```
输入: " 3+5 / 2 "
输出: 5
```
说明：
```
你可以假设所给定的表达式都是有效的。
请不要使用内置的库函数 eval。
```
## 解法
### 思路
栈：
- 初始化临时变量：
    - `num`：暂存遍历到的数字的计算过程
    - `op`：记录上一个操作符
- 遍历字符串：
    - 如果是空字符就跳过
    - 如果字符是数字，就计算这个数字，`num = num * 10 + digit`
    - 如果是操作符，那么就判断`op`是什么操作符：
        - 如果是`+``-`，就将对应的正负数压入栈中，
        - 如果是`*``/`，就说明需要使用优先级，将栈中的值和暂存的`num`拿出进行计算，并再将这个接入压入栈中
    - 从如上可知，当前是否为操作符会驱动计算，但是最后一组操作数和操作符的组合却无法被驱动，所以判断的时候需要加入是否是最后一个字符。但又因为最后一个字符可能是` `，所以还需要对s作一个trim的操作，避免这种特殊情况
- 最后将栈中的元素遍历后累加，就能获得结果
### 代码
```java
class Solution {
    public int calculate(String s) {
        s = s.trim();
        
        int num = 0; char op = '+';
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }
            
            if (c == '+' || c == '-' || c == '*' || c == '/' || i == s.length() - 1) {
                if (op == '+') {
                    stack.push(num);
                } else if (op == '-') {
                    stack.push(-num);
                } else if (op == '*') {
                    stack.push(num * stack.pop());
                } else {
                    stack.push(stack.pop() / num);
                }
                
                op = s.charAt(i);
                num = 0;
            }
        }
        
        int ans = 0;
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        
        return ans;
    }
}
```