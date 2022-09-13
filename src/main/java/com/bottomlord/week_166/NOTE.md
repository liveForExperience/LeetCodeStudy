# [LeetCode_749_隔离病毒](https://leetcode.cn/problems/contain-virus/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_2357_使数组中所有元素都等于零](https://leetcode.cn/problems/make-array-zero-by-subtracting-equal-amounts/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public int minimumOperations(int[] nums) {
        int sum = 0, ans = 0;
        for (int num : nums) {
            sum += num;
        }

        while (sum > 0) {
            sum = handleAndGetSum(nums, getNotZeroMin(nums));
            ans++;
        }

        return ans;
    }

    private int getNotZeroMin(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num == 0) {
                continue;
            }

            min = Math.min(min, num);
        }
        return min;
    }

    private int handleAndGetSum(int[] nums, int min) {
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                continue;
            }

            nums[i] -= min;
            sum += nums[i];
        }

        return sum;
    }
}
```