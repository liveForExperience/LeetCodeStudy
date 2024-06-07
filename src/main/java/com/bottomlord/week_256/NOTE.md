# [LeetCode_3038_相同分数的最大操作数目I](https://leetcode.cn/problems/maximum-number-of-operations-with-the-same-score-i)
## 解法
### 思路
- 从坐标0开始依次两两为一组，求和，并在每组和都相等的情况下，对组数进行累加，累加动作直到和不想等或坐标越界为止
- 比较的过程是对`nums`数组的遍历，遍历结束后，将累价值作为结果返回即可
### 代码
```java
class Solution {
    public int maxOperations(int[] nums) {
        int i = 2, n = nums.length, sum = nums[0] + nums[1], cnt = 1;
        while (i + 1 < n && nums[i] + nums[i + 1] == sum) {
            cnt++;
            i += 2;
        }
        return cnt;
    }
}
```