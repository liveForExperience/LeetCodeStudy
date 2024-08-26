# [LeetCode_698_划分为k个相等的子集](https://leetcode.cn/problems/partition-to-k-equal-sum-subsets)
## 解法
### 思路
- 将`nums`降序排序
- 初始化一个数组`bucket`用于记录搜索`nums`过程中元素累加的变化情况
- 通过回溯的方式将`nums`中的元素从大到小依次尝试放入到`bucket`中，尝试找到有效的组合
### 代码
```java
class Solution {
private int[] bucket, nums;
    public boolean canPartitionKSubsets(int[] nums, int k) {
        this.nums = nums;
        int sum = 0;
        for (int num : this.nums) {
            sum += num;
        }

        if (sum % k != 0) {
            return false;
        }

        int target = sum / k;
        for (int num : this.nums) {
            if (num > target) {
                return false;
            }
        }

        this.bucket = new int[k];
        Arrays.sort(this.nums);
        int n = this.nums.length;
        for (int i = 0; i < n / 2; i++) {
            int tmp = this.nums[i];
            this.nums[i] = this.nums[this.nums.length - 1 - i];
            this.nums[this.nums.length - 1 - i] = tmp;
        }
        
        return backTrack(0, target);
    }

    private boolean backTrack(int index, int target) {
        if (index >= this.nums.length) {
            return true;
        }
        
        int num = this.nums[index];
        for (int i = 0; i < this.bucket.length; i++) {
            if (i < this.bucket.length - 1 && bucket[i] == bucket[i + 1]) {
                continue;
            }
            
            if (bucket[i] + num > target) {
                continue;
            }
            
            bucket[i] += num;
            boolean result = backTrack(index + 1, target);
            if (result) {
                return true;
            }
            bucket[i] -= num;
        }
        
        return false;
    }
}
```