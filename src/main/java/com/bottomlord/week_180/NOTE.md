# [LeetCode_749_隔离病毒](https://leetcode.cn/problems/contain-virus/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_940_不同子序列](https://leetcode.cn/problems/distinct-subsequences-ii/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_1754_构造字典序最大的合并字符串](https://leetcode.cn/problems/largest-merge-of-two-strings/)
## 解法
### 思路
双指针：
- 3种情况
    - word1的字符大于word2的首字符，选取word1的字符
    - word2的字符大于word1的首字符，选取word2的字符
    - 2个字符相等的时候，判断后缀字符串的字典序大小，谁大，就用谁的首字符
- 判断后缀字符串的字典序，就是循环遍历判断
### 代码
```java
class Solution {
    public String largestMerge(String word1, String word2) {
        int i = 0, j = 0, n1 = word1.length(), n2 = word2.length();
        StringBuilder sb = new StringBuilder();
        while (i < n1 || j < n2) {
            if (judge(word1, i, n1, word2, j, n2)) {
                sb.append(word1.charAt(i++));
            } else {
                sb.append(word2.charAt(j++));
            }
        }

        return sb.toString();
    }

    private boolean judge(String word1, int i, int n1, String word2, int j, int n2) {
        if (i >= n1) {
            return false;
        } else if (j >= n2) {
            return true;
        } else {
            return word1.substring(i).compareTo(word2.substring(j)) >= 0;
        }
    }
}
```