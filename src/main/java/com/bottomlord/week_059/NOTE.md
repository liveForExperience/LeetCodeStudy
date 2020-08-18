# LeetCode_213_打家劫舍II
## 题目
你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。

示例1:
```
输入: [2,3,2]
输出: 3
解释: 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
```
示例 2:
```
输入: [1,2,3,1]
输出: 4
解释: 你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
     偷窃到的最高金额 = 1 + 3 = 4 。
```
## 解法
### 思路
动态规划：
- 因为首尾相连，所以可以将这个环形数组拆分成两部分：
    - 一个不包含头，只包含尾
    - 一个只包含头，不包含尾
- `dp[i]`：从0到i范围内，可以偷到的最大金额
- 状态转移方程：`dp[i] = max(dp[i - 2] + nums[i], dp[i - 1])`：
    - 偷当前的，那么最大值就是`dp[i - 2] + nums[i]`
    - 不偷当前的，那么最大值就是`dp[i - 1]`
- base case：
    - `dp[0] = nums[0]`
    - `i > 0 && i <= 3`：`dp[i] = max(nums)`
- 返回结果：`dp[len - 1]`
### 代码
```java
class Solution {
    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }

        if (len == 1) {
            return nums[0];
        }

        if (len <= 3) {
            Arrays.sort(nums);
            return nums[len - 1];
        }
        
        return Math.max(doRob(Arrays.copyOfRange(nums, 0, len - 1)), doRob(Arrays.copyOfRange(nums, 1, len)));
    }

    private int doRob(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[len - 1];
    }
}
```
## 解法二
### 思路
通过状态转移方程发现，状态转移都是依赖前两个dp元素，所以可以通过临时变量来代替整个dp数组
### 代码
```java
class Solution {
    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        if (len == 1) {
            return nums[0];
        }

        return Math.max(doRob(Arrays.copyOfRange(nums, 0, len - 1)),
                        doRob(Arrays.copyOfRange(nums, 1, len)));
    }

    private int doRob(int[] nums) {
        int pre = 0, cur = 0, tmp;
        for (int num : nums) {
            tmp = cur;
            cur = Math.max(pre + num, cur);
            pre = tmp;
        }

        return cur;
    }
}
```
# LeetCode_214_最短回文串
## 题目
给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。

示例 1:
```
输入: "aacecaaa"
输出: "aaacecaaa"
```
示例 2:
```
输入: "abcd"
输出: "dcbabcd"
```
## 解法
### 思路
- 因为题目只允许在字符串头部插入字符，所以可以从字符串头部开始，找到当前字符串的最大回文串
- 找到字符串最大回文串的方法是:
    - 将原来字符串翻转，得到rev
    - 使用指针同时对应原字符串s和反转字符串rev的起始位置
    - 循环移动指针，循环次数为字符串长度len
    - 判断s从`0`到`n - i`区间得到的字符串是否与rev从`i`到结尾获得的字符串相等
    - i移动的距离，相当于不是s中回文串的字符的个数，所以最早找到两部分相等的坐标位置，就是需要增加字符最少的情况
- 找到后，就直接将rev从0到i区间的字符串拼接到s前面，获得最后的解
### 代码
```java

```