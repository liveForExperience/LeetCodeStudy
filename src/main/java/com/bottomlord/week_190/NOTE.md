# [LeetCode_1144_递减元素使数组呈锯齿状](https://leetcode.cn/problems/decrease-elements-to-make-array-zigzag/)
## 解法
### 思路
- 遍历nums
- 根据题目要求计算奇偶坐标对应元素与相邻元素之间可能需要减去的差值
- 累加
- 遍历结束后返回奇偶坐标累加值的最小值作为结果返回
### 代码
```java
class Solution {
    public int movesToMakeZigzag(int[] nums) {
        int n = nums.length, odd = 0, even = 0;
        for (int i = 0; i < nums.length; i++) {
            int left = i == 0 ? Integer.MAX_VALUE : nums[i - 1],
                right = i == n - 1 ? Integer.MAX_VALUE : nums[i + 1],
                cur = nums[i], diff = Math.max(cur - Math.min(left, right) + 1, 0);
            if (i % 2 == 0) {
                even += diff;
            } else {
                odd += diff;
            }
        }
        
        return Math.min(even, odd);
    }
}
```