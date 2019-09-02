# LeetCode_680_验证回文字符串II
## 题目
给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。

示例 1:
```
输入: "aba"
输出: True
```
示例 2:
```
输入: "abca"
输出: True
解释: 你可以删除c字符。
```
注意:
```
字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。
```
## 失败解法
### 思路
暴力解法，一个个试，有true返true，否则返回false
### 失败原因
超时
### 代码
```java
class Solution {
    public boolean validPalindrome(String s) {
        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            int head = 0, tail = cs.length - 1;
            boolean flag = true;
            while (head < tail) {
                if (head == i) {
                    head++;   
                }
                
                if (tail == i) {
                    tail--;
                }
                
                if (cs[head] != cs[tail]) {
                    flag = false;
                    break;
                }
                
            head++;
            tail--;
            }
            
            if (flag) {
                return true;
            }
        }
        
        return false;
    }
}
```
## 解法
### 思路
- 其实只需要在两个字符不相等的情况下，判断两种情况：
    - head + 1到tail之间的字符串是否为回文字符串
    - head到tail-1之间的字符串是否为回文字符串
- 如果两种情况中的一种符合就是true，否则就是false
### 代码
```java
class Solution {
    public boolean validPalindrome(String s) {
        char[] cs = s.toCharArray();
        int head = 0, tail = cs.length - 1;
        while (head < tail) {
            if (cs[head] != cs[tail]) {
                return validate(head + 1, tail, cs) || validate(head, tail - 1, cs);
            } else {
                head++;
                tail--;
            }
        }
        
        return true;
    }
    
    private boolean validate(int start, int end, char[] cs) {
        while (start < end) {
            if (cs[start] != cs[end]) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
```