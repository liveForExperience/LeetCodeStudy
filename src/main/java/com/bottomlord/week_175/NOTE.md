# [LeetCode_775_全局倒置与局部倒置](https://leetcode.cn/problems/global-and-local-inversions/)
## 解法
### 思路
- 全局倒置包含局部倒置
- 如果需要全局倒置=局部倒置个数，那么就需要关注全局倒置的非局部倒置个数，也就是不相邻的倒序是否存在，如果存在，那么这种情况也是一种全局倒置，这就导致和题目要求不符合
- 初始化一个max变量，赋值为arr[0]，然后从第3个元素开始遍历数组，每次都比较不包含前一个数组的区间中的最大值，如果比该最大值小，那么就符合非局部倒置的全局倒置情况，返回false
- 否则找不到的话，就返回true
### 代码
```java
class Solution {
    public boolean isIdealPermutation(int[] nums) {
        int n = nums.length, max = nums[0];
        for (int i = 2; i < n; i++) {
            if (nums[i] < max) {
                return false;
            }

            max = Math.max(max, nums[i - 1]);
        }
        return true;
    }
}
```