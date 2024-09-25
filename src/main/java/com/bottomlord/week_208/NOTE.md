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
# [LeetCode_2178_拆分成最多数目的多偶数之和](https://leetcode.cn/problems/maximum-split-of-positive-even-integers/)
## 解法
### 思路
- 如果目标是奇数，直接返回空数组即可
- 使用贪心的方式，将尽可能小的左右偶数进行累加，得到sum，直到sum累加到小于等于finalSum的最大值为止
  - 如果sum等于finalSum，那么返回记录的所有元素
  - 如果sum小于finalSum，那么将final与sumFinal的差值加到列表中的最后一个元素上即可
### 代码
```java
class Solution {
    public List<Long> maximumEvenSplit(long finalSum) {
        if (finalSum % 2 != 0) {
            return new ArrayList<>();
        }
        
        long cur = 2, sum = 0;
        List<Long> ans = new ArrayList<>();
        while (sum + cur <= finalSum) {
            sum += cur;
            ans.add(cur);
            cur += 2;
        }
        
        if (sum == finalSum) {
            return ans;
        }
        
        ans.set(ans.size() - 1, finalSum - sum + ans.get(ans.size() - 1));
        return ans;
    }
}
```