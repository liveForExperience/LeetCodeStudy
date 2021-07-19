# [LeetCode_1838_最高频元素的频数](https://leetcode-cn.com/problems/frequency-of-the-most-frequent-element/)
## 解法
### 思路
排序+滑动窗口
- 对数组排序，是的数组成为一个升序序列
- 这样就可以通过遍历的方式，判断要变化的目标值，也就是滑动窗口右边界
- 初始化滑动窗口的左右指针，分别为0和1
- 遍历过程中，累加达到右边界需要的变化数：
    - 每次移动右边界的时候，都需要累加右边界变化时增加的数，也就是`(nums[r] - nums[r - 1]) * (r - l)`
    - 然后判断total（累加的变化值）的大小是否大于了K，如果大了，就移动左边界，缩小total直到小于等于K为止
    - 然后比较暂存窗口大小和当前窗口大小的大小，取较大值暂存
- 需要注意
    - total要用long值否则会溢出
    - ans最小值是1
### 代码
```java
class Solution {
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int l = 0, n = nums.length, ans = 1;
        long total = 0;
        for (int r = 1; r < n; r++) {
            total += (long) (nums[r] - nums[r - 1]) * (r - l);
            while (total > k) {
                total -= (nums[r] - nums[l]);
                l++;
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }
}
```
# [LeetCode_1342_将数字变成0的操作次数](https://leetcode-cn.com/problems/number-of-steps-to-reduce-a-number-to-zero/)
## 解法
### 思路
模拟
### 代码
```java

```