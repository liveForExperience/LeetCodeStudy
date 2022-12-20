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
# [LeetCode_1760_袋子里最小数目的球](https://leetcode.cn/problems/minimum-limit-of-balls-in-a-bag/)
## 解法
### 思路
二分查找
- 二分查找那个最小的y值
- 每个查找到的y，其操作数计算公式：`(nums[i] - 1) / y`，将这些操作数累加，与maxOperations比较来判断
  - 如果大于，则在大于y的区间找
  - 如果小于等于，则在小于y的区间找，并暂存答案
- 二分查找结束后，返回结果
### 代码
```java
class Solution {
    public int minimumSize(int[] nums, int maxOperations) {
         Arrays.sort(nums);
        int ans = Integer.MAX_VALUE, left = 1, right = nums[nums.length - 1];
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int opt = 0;
            for (int num : nums) {
                opt += (num - 1) / mid;
            }
            
            if (opt <= maxOperations) {
                ans = Math.min(ans, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return ans;
    }
}
```