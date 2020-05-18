# LeetCode_152_乘积最大子数组
## 题目
给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。

示例 1:
```
输入: [2,3,-2,4]
输出: 6
解释: 子数组 [2,3] 有最大乘积 6。
```
示例 2:
```
输入: [-2,0,-1]
输出: 0
解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
```
## 解法
### 思路
动态规划：
- dp[i]：以i为结尾的子序列中乘积最大值
- 因为包含负数，所以如果当前值是负数，就会希望之前的值越小越好。所以需要再维护一个最小值的dp序列
- 状态转移方程：
    - `dpMax[i] = max(dpMax[i - 1] * nums[i], dpMin[i - 1] * nums[i], nums[i])` 
    - `dpMin[i] = min(dpMax[i - 1] * nums[i], dpMin[i - 1] * nums[i], nums[i])`
- 遍历`dpMax[i]`，返回最大值
### 代码
```java
class Solution {
    public int maxProduct(int[] nums) {
        int len = nums.length;
        int[] dpMax = new int[len], dpMin = new int[len];
        dpMax[0] = nums[0];
        dpMin[0] = nums[0];
        
        for (int i = 1; i < len; i++) {
            int num = nums[i];
            dpMax[i] = Math.max(dpMax[i - 1] * num, Math.max(dpMin[i - 1] * num, num));
            dpMin[i] = Math.min(dpMax[i - 1] * num, Math.min(dpMin[i - 1] * num, num));
        }
        
        return Arrays.stream(dpMax).max().getAsInt();
    }
}
```
## 解法二
### 思路
- 观察解法一可以发现，状态之间的转换只依赖前一个最大值和最小值上，随意可以使用两个变量替换数组
- 同时在循环过程中直接计算最大值，省去最后的循环步骤
- 在循环体中需要暂存上一个循环的最大最小值，不能直接使用，因为有两个状态转移方程式，如果第一个方程式直接赋值，会导致第二个方程式求解错误
### 代码
```java
class Solution {
    public int maxProduct(int[] nums) {
        int max = nums[0], min = nums[0], ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int curMax = max, curMin = min, num = nums[i];
            max = Math.max(curMax * num, Math.max(curMin * num, num));
            min = Math.min(curMax * num, Math.min(curMin * num, num));
            ans = Math.max(max, ans);
        }
        
        return ans;
    }
}
```