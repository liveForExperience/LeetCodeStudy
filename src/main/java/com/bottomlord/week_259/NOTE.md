# [LeetCode_2710_移除字符串中的尾随零](https://leetcode.cn/problems/remove-trailing-zeros-from-a-string)
## 解法
### 思路
- 从字符串尾部开始遍历字符串，计算尾部连续0的长度`len`
- 初始化字符串长度变量`n`
- 通过String的subString API从0坐标开始获取`[0, n - len]`区间的字符串作为结果返回
### 代码
```java
class Solution {
    public String removeTrailingZeros(String num) {
        char[] cs = num.toCharArray();
        int n = cs.length, len = 0, index = n - 1;
        while (index >= 0 && cs[index] == '0') {
            index--;
            len++;
        }
        
        return num.substring(0, n - len);
    }
}
```