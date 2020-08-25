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
# LeetCode_229_求众数
## 题目
给定一个大小为 n 的数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。

说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1)。

示例 1:
```
输入: [3,2,3]
输出: [3]
```
示例 2:
```
输入: [1,1,1,3,3,2,2,2]
输出: [1,2]
```
## 解法
### 思路
摩尔投票法
- 如果要在总数中选出n个人，就需要超过`1 / (n + 1)`票，因为题目中要找到所有票数超过`1 / 3`的元素，所以候选人个数就是2
- 在摩尔投票法中，遍历数组，判断`(n + 1)`个元素是否全不相等
    - 如果是，全部抵消
    - 如果不是，计数相等的数字
- 继续遍历比较，直到遍历到最后一个元素，此时判断哪些数字最后的值不为0，不为0不一定代表超过`(n + 1)`，但为0肯定代表不是
- 然后根据不为零的数，再次遍历数组，做计数统计，获得最后准确的答案
## 代码
```java
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int candidate1 = 0, count1 = 0,
            candidate2 = 0, count2 = 0;

        for (int num : nums) {
            if (candidate1 == num) {
                count1++;
                continue;
            }

            if (candidate2 == num) {
                count2++;
                continue;
            }

            if (count1 == 0) {
                candidate1 = num;
                count1++;
                continue;
            }

            if (count2 == 0) {
                candidate2 = num;
                count2++;
                continue;
            }

            count1--;
            count2--;
        }

        List<Integer> ans = new ArrayList<>();
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (candidate1 == num) {
                count1++;
            } else if (candidate2 == num) {
                count2++;
            }
        }

        if (count1 > nums.length / 3) {
            ans.add(candidate1);
        }

        if (count2 > nums.length / 3) {
            ans.add(candidate2);
        }

        return ans;
    }
}
```
# LeetCode_240_探索二维矩阵II
## 题目
编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
```
每行的元素从左到右升序排列。
每列的元素从上到下升序排列。
```
示例:

现有矩阵 matrix 如下：
```
[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
给定 target = 5，返回 true。

给定 target = 20，返回 false。
```
## 解法
### 思路
- 先逐行遍历，比较行尾元素与target的关系
    - 如果相等直接返回true
    - 如果小于target，跳入下一行
    - 如果大于target，开始当前行的搜索
- 在行内搜索时，如果找到匹配target的元素，就直接返回true
- 遍历结束还没有找到，返回false
### 代码
```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        if (row == 0) {
            return false;
        }

        int col = matrix[0].length;
        if (col == 0) {
            return false;
        }

        for (int[] rows : matrix) {
            if (rows[col - 1] == target) {
                return true;
            }

            if (rows[col - 1] < target) {
                continue;
            }

            for (int c = 0; c < col - 1; c++) {
                if (rows[c] == target) {
                    return true;
                }
            }
        }
        
        return false;
    }
}
```
