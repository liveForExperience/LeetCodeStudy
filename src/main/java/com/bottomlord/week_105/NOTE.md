# [LeetCode_275_H指数II](https://leetcode-cn.com/problems/h-index-ii/)
## 解法
### 思路
从高引用论文开始倒序遍历，累加h值，直到当前引用数不再大于h值为止
### 代码
```java
class Solution {
    public int hIndex(int[] citations) {
        int h = 0, i = citations.length - 1;
        while (i >= 0 && citations[i] > h) {
            h++;
            i--;
        }
        return h;
    }
}
```