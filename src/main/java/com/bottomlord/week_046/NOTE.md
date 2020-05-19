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
# Interview_1721_直方图的水量
## 题目
给定一个直方图(也称柱状图)，假设有人从上面源源不断地倒水，最后直方图能存多少水量?直方图的宽度为 1。

上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的直方图，在这种情况下，可以接 6 个单位的水（蓝色部分表示水）。 感谢 Marcos 贡献此图。

示例:
```
输入: [0,1,0,2,1,0,1,3,2,1,2,1]
输出: 6
```
## 解法
### 思路
暴力：
- 遍历直方图数组
- 以当前元素为中心，向左右遍历，找到左右方向上的2个最大值max
- 在2个最大值max中找到最小值min
- 如果min比当前值cur大，则差值就是当前位置可以储存的数量
- 将所有位置的值累加就是结果
### 代码
```java
class Solution {
    public int trap(int[] height) {
        int ans = 0;
        for (int i = 0; i < height.length; i++) {
            int leftMax = 0, rightMax = 0, curIndex = i;
            while (--curIndex >= 0) {
                leftMax = Math.max(leftMax, height[curIndex]);
            }
            
            curIndex = i;
            while (++curIndex < height.length) {
                rightMax = Math.max(rightMax, height[curIndex]);
            }
            
            int min = Math.min(leftMax, rightMax);
            if (min > height[i]) {
                ans += min - height[i];
            }
        }
        
        return ans;
    }
}
```
## 解法二
### 思路
双指针：
- 初始化头尾两个指针
- 初始化头尾的最大值变量，起始为头尾元素
- 头尾指针相向移动
- 比较头尾最大值的大小：
    - 如果头最大值小于尾最大值，那么累加头最大值与头元素之间的差值
    - 如果尾最大值小于头最大值，那么累加尾最大值与尾元素之间的差值
    - 这么计算的原因是：当前元素是否能盛多少水，取决于当前元素的左右两边最大值中的最小值。头最大值如果小于尾最大值，那么头元素能盛多少水就能通过头最大值来确定，而此时尾最大值是否是当前头元素的真正右边的最大值是不用考虑的。反之亦然。
### 代码
```java
class Solution {
    public int trap(int[] height) {
        if (height.length < 3) {
            return 0;
        }
        
        int ans = 0, head = 0, tail = height.length - 1;
        int headMax = height[head], tailMax = height[tail];
        
        while (head < tail) {
            if (headMax < tailMax) {
                ans += headMax - height[head++];
                headMax = Math.max(headMax, height[head]);
            } else {
                ans += tailMax - height[tail--];
                tailMax = Math.max(tailMax, height[tail]);
            }
        }
        
        return ans;
    }
}
```
## 解法三
### 思路
- 初始化两个数组
    - pre：以当前坐标结尾的数组中的最大值
    - suf：以当前坐标开头的数组中的最大值
- 遍历height填充pre和suf
- 遍历height，求height当前元素与min(pre, suf)之间的差值，如果是负值就取0
### 代码
```java
class Solution {
    public int trap(int[] height) {
        int len = height.length;
        if (len < 3) {
            return 0;
        }
        
        int[] pre = new int[len], suf = new int[len];
        pre[0] = height[0];
        suf[len - 1] = height[len - 1];
        
        for (int i = 1; i < len - 1; i++) {
            pre[i] = Math.max(pre[i - 1], height[i]);
        }
        
        for (int i = len - 2; i >= 0; i--) {
            suf[i] = Math.max(suf[i + 1], height[i]);
        }
        
        int ans = 0;
        for (int i = 1; i < len - 1; i++) {
            ans += Math.max(0, Math.min(pre[i - 1], suf[i + 1]) - height[i]);
        }
        
        return ans;
    }
}
```