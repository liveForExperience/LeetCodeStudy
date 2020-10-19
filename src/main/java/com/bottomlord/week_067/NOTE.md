# LeetCode_306_累加数
## 题目
累加数是一个字符串，组成它的数字可以形成累加序列。

一个有效的累加序列必须至少包含 3 个数。除了最开始的两个数以外，字符串中的其他数都等于它之前两个数相加的和。

给定一个只包含数字 '0'-'9' 的字符串，编写一个算法来判断给定输入是否是累加数。

说明: 累加序列里的数不会以 0 开头，所以不会出现 1, 2, 03 或者 1, 02, 3 的情况。

示例 1:
```
输入: "112358"
输出: true 
解释: 累加序列为: 1, 1, 2, 3, 5, 8 。1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
```
示例 2:
```
输入: "199100199"
输出: true 
解释: 累加序列为: 1, 99, 100, 199。1 + 99 = 100, 99 + 100 = 199
进阶:
你如何处理一个溢出的过大的整数输入?
```
## 解法
### 思路
回溯+剪枝
### 代码
```java
class Solution {
    public boolean isAdditiveNumber(String num) {
        return backTrack(num, 0, 0, 0, 0);
    }

    private boolean backTrack(String num, int index, long pre, long sum, int count) {
        if (index == num.length()) {
            return count > 2;
        }

        for (int i = index; i < num.length(); i++) {
            long cur = getNum(num, index, i);

            if (cur < 0) {
                continue;
            }

            if (count >= 2 && cur != sum) {
                continue;
            }
            
            if (backTrack(num, i + 1, cur, pre + cur, count + 1)) {
                return true;
            }
        }
        
        return false;
    }

    private long getNum(String num, int l, int r) {
        if (l < r && num.charAt(l) == '0') {
            return -1;
        }

        long ans = 0;
        while (l <= r) {
            ans = 10 * ans + (num.charAt(l++) - '0');
        }
        return ans;
    }
}
```
# LeetCode_307_区域和检索-数组可修改
## 题目
给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。

update(i, val) 函数可以通过将下标为 i 的数值更新为 val，从而对数列进行修改。

示例:
```
Given nums = [1, 3, 5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8
```
说明:
```
数组仅可以在 update 函数下进行修改。
你可以假设 update 函数与 sumRange 函数的调用次数是均匀分布的。
```
## 解法
### 思路
暴力，查询一次遍历一次
### 代码
```java
class NumArray {
    private int[] nums;
    public NumArray(int[] nums) {
        this.nums = nums;
    }

    public void update(int i, int val) {
        nums[i] = val;
    }

    public int sumRange(int i, int j) {
        int sum = 0;
        for (int index = i; index <= j; index++) {
            sum += nums[index];
        }
        return sum;
    }
}
```
## 解法二
### 思路
前缀和，更新一次，维护一次前缀和
### 代码
```java
class NumArray {
    private int[] nums;
    public NumArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        
        this.nums = new int[nums.length];
        this.nums[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            this.nums[i] = this.nums[i - 1] + nums[i];
        }
    }

    public void update(int i, int val) {
        int diff = i == 0 ? -(nums[0] - val) : -(nums[i] - nums[i - 1] - val);
        for (int index = i; index < nums.length; index++) {
            nums[index] += diff;
        }
    }

    public int sumRange(int i, int j) {
        return i == 0 ? nums[j] : nums[j] - nums[i - 1];
    }
}
```