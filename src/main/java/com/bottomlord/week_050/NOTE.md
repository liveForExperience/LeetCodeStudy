# LeetCode_41_缺失的第一个正数
## 题目
给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。

示例 1:
```
输入: [1,2,0]
输出: 3
```
示例 2:
```
输入: [3,4,-1,1]
输出: 2
```
示例 3:
```
输入: [7,8,9,11,12]
输出: 1
```
提示：
```
你的算法的时间复杂度应为O(n)，并且只能使用常数级别的额外空间。
```
## 解法
### 思路
- 如果数组中不包含1，那么答案就是1
- 如果数组长度是1，且不包含1，那么答案就是2
- 找到缺失的最小正数，所以负数和0是不需要考虑的，在如上两步都不符合的情况下，将负数和0都转换为1
- 缺失的最小正整数有两种情况：
    - 如果处理完后的n个元素正好是`[1, n]，那么答案就是`n + 1`
    - 否则就是`[1, n]`范围内的数
- 搜索一次数组，使用数组下标对应数字（0代表n），正负符号来代表是否搜索到。
### 代码
```java
class Solution {
    public int firstMissingPositive(int[] nums) {
        boolean hasOne = false;
        int len = nums.length;
        for (int num : nums) {
            if (num == 1) {
                hasOne = true;
                break;
            }
        }

        if (!hasOne) {
            return 1;
        }

        if (len == 1) {
            return 2;
        }

        for (int i = 0; i < len; i++) {
            if (nums[i] <= 0) {
                nums[i] = 1;
            }
        }

        for (int i = 0; i < len; i++) {
            int num = Math.abs(nums[i]);

            if (num < len) {
                nums[num] = -Math.abs(nums[num]);
            }

            if (num == len) {
                nums[0] = -Math.abs(nums[0]);
            }
        }

        for (int i = 1; i < len; i++) {
            if (nums[i] > 0) {
                return i;
            }
        }

        if (nums[0] > 0) {
            return len;
        }

        return len + 1;
    }
}
```
# LeetCode_43_字符串相乘
## 题目
给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。

示例 1:
```
输入: num1 = "2", num2 = "3"
输出: "6"
```
示例 2:
```
输入: num1 = "123", num2 = "456"
输出: "56088"
```
说明：
```
num1 和 num2 的长度小于110。
num1 和 num2 只包含数字 0-9。
num1 和 num2 均不以零开头，除非是数字 0 本身。
不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
```
## 解法
### 思路
- 根据两个字符串的长度初始化一个int数组，长度为两个字符串长度之和
- 模拟乘法过程，嵌套循环：
    - 从字符串尾部开始往前确定数字
    - 内外层的数字想乘，分别记录余数和商，商作为进位
    - 余数+上一次的进位，得到一个和，再判断这个和是否大于10，如果是就继续处理
    - 将本次的商作为下一次的进位
    - 内层循环结束后，判断当前进位是否不为0，如果是，就继续填充一位值
    - 在循环过程中，外层数字位数变动一次，就需要变动一下填充值的开始位置，该位置需要向低位开始
### 代码
```java
class Solution {
    public String multiply(String num1, String num2) {
        String zero = "0";
        if (zero.equals(num1) || zero.equals(num2)) {
            return zero;
        }

        int[] arr = new int[num1.length() + num2.length()];
        int start = arr.length - 1;
        for (int i = num1.length() - 1; i >= 0; i--) {
            int a = num1.charAt(i) - '0', index = start, carry = 0;
            for (int j = num2.length() - 1; j >= 0; j--) {
                int b = num2.charAt(j) - '0';
                int c = a * b;
                int remainder = c % 10 + carry;
                carry = c / 10;
                int sum = arr[index] + remainder;
                arr[index] = sum % 10;
                carry += sum / 10;
                index--;
            }
            
            if (carry > 0) {
                arr[index] += carry;
            }
            
            start--;
        }
        
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (int num : arr) {
            if (num != 0) {
                break;
            }
            index++;
        }
        
        for (; index < arr.length; index++) {
            sb.append(arr[index]);
        }
        return sb.toString();
    }
}
```