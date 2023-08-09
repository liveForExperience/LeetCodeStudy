# [LeetCode_1749_任意子数字和的绝对值的最大值](https://leetcode.cn/problems/maximum-absolute-sum-of-any-subarray/description/)
## 解法
### 思路
- 题目可以理解为，对子数字做极值求解，一个是最大值，一个是最小值，然后通过算绝对值求得题目要求的最大的答案
- 这是很基本的动态规划题目，且可以通过状态压缩来省略记事本数组
### 代码
```java
class Solution {
    public int maxAbsoluteSum(int[] nums) {
        int minDp = Integer.MAX_VALUE, maxDp = Integer.MIN_VALUE, ans = 0;
        for (int num : nums) {
            minDp = Math.min(minDp, 0) + num;
            maxDp = Math.max(maxDp, 0) + num;
            ans = Math.max(ans, Math.max(-minDp, maxDp));
        }
        return ans;
    }
}
```