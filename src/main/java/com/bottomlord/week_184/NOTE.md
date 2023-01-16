# [LeetCode_940_不同子序列II](https://leetcode.cn/problems/distinct-subsequences-ii/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_1813_句子相似性III](https://leetcode.cn/problems/sentence-similarity-iii/)
## 解法
### 思路
双指针：
- 将两个句子根据空格切分
- 根据题目要求，使用双指针，从头尾找到相对起始位置的坐标值相等的元素
- 找到的元素的个数和，一定是2个句子中单词数最少的那个
### 代码
```java
class Solution {
    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        int i = 0, j = 0;

        String[] words1 = sentence1.split(" "),
                words2 = sentence2.split(" ");

        int n1 = words1.length, n2 = words2.length;
        while (i < n1 && i < n2 && Objects.equals(words1[i], words2[i])) {
            i++;
        }

        while (j < n1 - i && j < n2 - i && Objects.equals(words1[n1 - 1 - j], words2[n2 - 1 - j])) {
            j++;
        }

        return i + j == Math.min(words1.length, words2.length);
    }
}
```