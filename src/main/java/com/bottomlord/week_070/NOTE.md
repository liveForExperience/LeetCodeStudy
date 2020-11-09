# LeetCode_325_和等于k的最长子数组长度
## 题目
给定一个数组 nums 和一个目标值 k，找到和等于 k 的最长子数组长度。如果不存在任意一个符合要求的子数组，则返回 0。

注意:
```
 nums 数组的总和是一定在 32 位有符号整数范围之内的。
```
示例 1:
```
输入: nums = [1, -1, 5, -2, 3], k = 3
输出: 4 
解释: 子数组 [1, -1, 5, -2] 和等于 3，且长度最长。
```
示例 2:
```
输入: nums = [-2, -1, 2, 1], k = 1
输出: 2 
解释: 子数组 [-1, 2] 和等于 1，且长度最长。
```
## 解法
### 思路
前缀和
### 代码
```java
class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        int len = nums.length;
        int[] sums = new int[len];
        
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sums[i] = (sum += nums[i]);
        }
        
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (sums[j] - sums[i] + nums[i] == k) {
                    max = Math.max(max, j - i + 1);
                }
            }
        }

        return max == Integer.MIN_VALUE ? 0 : max;
    }
}
```