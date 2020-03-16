# Interview_0106_字符串压缩
## 题目
字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。比如，字符串aabcccccaaa会变为a2b1c5a3。若“压缩”后的字符串没有变短，则返回原先的字符串。你可以假设字符串中只包含大小写英文字母（a至z）。

示例1:
```
 输入："aabcccccaaa"
 输出："a2b1c5a3"
```
示例2:
```
 输入："abbccd"
 输出："abbccd"
 解释："abbccd"压缩后为"a1b2c2d1"，比原字符串长度更长。
```
提示：
```
字符串长度在[0, 50000]范围内。
```
## 解法
### 思路
嵌套循环：
- 外层确定重复字符的起始位置和字符
- 内层循环迭代所有和起始字符一致的字符，计算个数
- 通过StringBuilder叠加
- 循环结束，与原字符串比较，如果没有原字符串短就返回原字符串，否则返回StringBuilder
### 代码
```java
class Solution {
    public String compressString(String S) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S.length();) {
            char start = S.charAt(i);
            int j = i + 1;
            while (j < S.length() && start == S.charAt(j)) {
                j++;
            }
            sb.append(start).append(j - i);
            i = j;
        }
        return sb.length() >= S.length() ? S : sb.toString();
    }
}
```