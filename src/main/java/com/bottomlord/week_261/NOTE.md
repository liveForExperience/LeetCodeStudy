# [LeetCode_2974_最小数字游戏](https://leetcode.cn/problems/minimum-number-game)
## 解法
### 思路
- 排序`nums`
- 初始化一个新的数组`arr`，长度与`nums`一致
- 从坐标0开始遍历`nums`，步长为2
- 每次循环就是模拟题目的操作
  - `arr[i] = nums[i + 1]`
  - `arr[i + 1] = nums[i]`
- 遍历结束后，返回`arr`
### 代码
```java
class Solution {
    public int[] numberGame(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] arr = new int[n];
        for (int i = 0; i < n; i += 2) {
            arr[i] = nums[i + 1];
            arr[i + 1] = nums[i]; 
        }
        return arr;
    }
}
```