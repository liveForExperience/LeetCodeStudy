# [LeetCode_415_字符串相加](https://leetcode.cn/problems/add-strings/)
## 解法
### 思路
- 模拟的时候如何设计算法很有讲究
- 从题目看，循环遍历两个字符串是肯定的
- 那么退出条件该如何设定？
  - 2个字符串没有遍历完肯定要继续循环
  - 字符与字符相加后，和大于9，有了进位，那也需要继续循环
- 循环内部的处理逻辑该如何设计？
  - 两个加数需要从字符转换为整型数字，然后通过运算获取
  - 当前位的和应该是`sum = a + b + carry`
  - 当前位额值应该是`num = sum % 10`
  - 进位应该是`carry = num / 10`
- 处理结束，返回字符串即可
### 代码
```java
class Solution {
    public String addStrings(String num1, String num2) {
        char[] cs1 = num1.toCharArray(), cs2 = num2.toCharArray();
        int i1 = cs1.length - 1, i2 = cs2.length - 1, carry = 0;
        StringBuilder ans = new StringBuilder();
        while (i1 >= 0 || i2 >= 0 || carry != 0) {
            int a = i1 >= 0 ? cs1[i1--] - '0' : 0,
                b = i2 >= 0 ? cs2[i2--] - '0' : 0;
            int num = a + b + carry;
            ans.insert(0, num % 10);
            carry = num / 10;
        }
        return ans.toString();
    }
}
```