# [LeetCode_2806_取整购买后的账户余额](https://leetcode.cn/problems/account-balance-after-rounded-purchase)
## 解法
### 思路
- 将`purchaseAmount`与10取模得到余数，这个余数`mod`的含义是，比`purchaseAmount`小的最大的10的倍数`num`，比`purchaseAmount`小的值。即：`mod = purchaseAmount - num`
- 判断`mod`是否小于5，如果是，那么他离`num`更近，否则说明它离更大的10的倍数更近，那么就让`purchaseAmount + (10 - mod)`得到结果即可
### 代码
```java
class Solution {
    public int accountBalanceAfterPurchase(int purchaseAmount) {
        int mod = purchaseAmount % 10;
        return mod < 5 ? 100 - (purchaseAmount - mod) : 100 - (purchaseAmount + 10 - mod);
    }
}
```
# [LeetCode_2779_数组的最小美丽值](https://leetcode.cn/problems/maximum-beauty-of-an-array-after-applying-operation)
## 解法
### 思路
- 因为题目要求的是子序列的长度，又因为只要基于大小为K做上下幅度变化后，值一样即可，那么最终的子序列一定是排序后连续的几个值在一起才能够组成
- 对数组`nums`排序，使其正序排列
- 以排序后的`nums`的每一个元素作为可能序列的起始坐标元素，然后嵌套遍历之后的所有元素，判断起始元素是否在遍历到的元素的`+-K`区间内，如果在就继续遍历，直到不符合为止，然后计算区间的长度，与暂存的最大值进行比较并保留较大值
- 在如上过程中可以做几个剪枝的动作
    - 如果当前的起始坐标到数组尾部的长度已经小于暂存的最大值，就可以提前中断，因为已经一定不能得到更大值
    - 因为下一个循环的元素一定比排序后的前一个元素大，所以前一个元素对应的序列一定也是当前元素所对应的序列的一部分，直接从上一个序列的结尾坐标开始遍历即可，所以可以暂存上一个序列的结尾坐标值
### 代码
```java
class Solution {
    public int maximumBeauty(int[] nums, int k) {
        Arrays.sort(nums);
        int cursor = 0, max = 1, n = nums.length;
        for (int i = 0; i < n; i++) {
            if (n - i + 1 <= max) {
                break;
            }

            int num = nums[i];
            while (cursor < n && nums[cursor] <= num + 2 * k) {
                cursor++;
            }

            max = Math.max(max, cursor - i);
        }

        return max;
    }
}
```