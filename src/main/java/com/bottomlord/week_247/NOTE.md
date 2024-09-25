# [LeetCode_2810_故障键盘](https://leetcode.cn/problems/faulty-keyboard)
## 解法
### 思路
- 初始化`StringBuilder`作为暂存新字符串的数据结构
- 遍历字符串
    - 没有遇到`i`，将当前遍历到的字符拼接到`StringBuilder`中
    - 遇到`i`，将`StringBuilder`的内容进行翻转
- 遍历结束，返回`StringBuilder`转成字符串后的结果
### 代码
```java
class Solution {
    public String finalString(String s) {
        StringBuilder sb = new StringBuilder();
        char[] cs = s.toCharArray();
        for (char c : cs) {
            if (c == 'i') {
                sb.reverse();
            } else {
                sb.append(c);
            }
        }
        
        return sb.toString();
    }
}
```