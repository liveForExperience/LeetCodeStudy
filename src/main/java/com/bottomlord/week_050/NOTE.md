# LeetCode_41_缺失的第一个正数
## 题目
给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。

示例 1:
```
输入: [1,2,0]
输出: 3
```
示例 2:
```
输入: [3,4,-1,1]
输出: 2
```
示例 3:
```
输入: [7,8,9,11,12]
输出: 1
```
提示：
```
你的算法的时间复杂度应为O(n)，并且只能使用常数级别的额外空间。
```
## 解法
### 思路
- 如果数组中不包含1，那么答案就是1
- 如果数组长度是1，且不包含1，那么答案就是2
- 找到缺失的最小正数，所以负数和0是不需要考虑的，在如上两步都不符合的情况下，将负数和0都转换为1
- 缺失的最小正整数有两种情况：
    - 如果处理完后的n个元素正好是`[1, n]，那么答案就是`n + 1`
    - 否则就是`[1, n]`范围内的数
- 搜索一次数组，使用数组下标对应数字（0代表n），正负符号来代表是否搜索到。
### 代码
```java
class Solution {
    public int firstMissingPositive(int[] nums) {
        boolean hasOne = false;
        int len = nums.length;
        for (int num : nums) {
            if (num == 1) {
                hasOne = true;
                break;
            }
        }

        if (!hasOne) {
            return 1;
        }

        if (len == 1) {
            return 2;
        }

        for (int i = 0; i < len; i++) {
            if (nums[i] <= 0) {
                nums[i] = 1;
            }
        }

        for (int i = 0; i < len; i++) {
            int num = Math.abs(nums[i]);

            if (num < len) {
                nums[num] = -Math.abs(nums[num]);
            }

            if (num == len) {
                nums[0] = -Math.abs(nums[0]);
            }
        }

        for (int i = 1; i < len; i++) {
            if (nums[i] > 0) {
                return i;
            }
        }

        if (nums[0] > 0) {
            return len;
        }

        return len + 1;
    }
}
```