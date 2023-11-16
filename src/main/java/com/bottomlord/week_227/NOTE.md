# [LeetCode_2760_最长奇偶子数组](https://leetcode.cn/problems/longest-even-odd-subarray-with-threshold/description)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public int longestAlternatingSubarray(int[] nums, int threshold) {
        int max = 0;
        for (int i = 0; i < nums.length;) {
            if (nums[i] % 2 == 1 || nums[i] > threshold) {
                i++;
                continue;
            }

            i++;
            boolean flag = true;
            int cur = 1;
            while (i < nums.length && (nums[i] % 2 == 0) == !flag && nums[i] <= threshold) {
                cur++;
                flag = !flag;
                i++;
            }

            max = Math.max(cur, max);
        }

        return max;
    }
}
```