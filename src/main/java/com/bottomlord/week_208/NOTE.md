# [LeetCode_2679_矩阵中的和](https://leetcode.cn/problems/sum-in-a-matrix/)
## 解法
### 思路
- 这道题思路平铺直叙
- 如下是逻辑步骤
  - 遍历二维数组，对每个数组进行排序
  - 在通过2层循环依次对每一列的所有元素进行最大值比较，将得到的最大值累加
  - 遍历结束返回结果
### 代码
```java
class Solution {
    public int matrixSum(int[][] nums) {
        for (int[] arr : nums) {
            Arrays.sort(arr);
        }

        int ans = 0;
        int row = nums.length, col = nums[0].length;
        for (int i = 0; i < col; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < row; j++) {
                max = Math.max(max, nums[j][i]);
            }
            ans += max;
        }
        
        return ans;
    }
}
```