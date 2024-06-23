# [LeetCode_520_检测大写字母](https://leetcode.cn/problems/detect-capital)
## 解法
### 思路
- 遍历字符串，统计大写字母个数`cnt`
- 判断：
    - `cnt`与字符串长度是否一致，如果一致，就是全部都是大写字母的情况
    - `cnt`是否为0，如果是，就是所有字母都是小写的情况
    - `cnt`是1，且第一个字母是大写，则符合题目要求的仅首字母是大写的情况
- 符合如上情况的返回true，否则返回false
### 代码
```java
class Solution {
    public boolean detectCapitalUse(String word) {
        int n = word.length(), cnt = 0;
        char[] cs = word.toCharArray();
        for (char c : cs) {
            if (isUpper(c)) {
                cnt++;
            }
        }
        
        return n == cnt || cnt == 0 || (cnt == 1 && isUpper(cs[0]));
    }
    
    private boolean isUpper(char c) {
        return c >= 'A' && c <= 'Z';
    }
}
```