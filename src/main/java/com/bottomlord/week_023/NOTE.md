# LeetCode_991_坏了的计算器
## 题目
在显示着数字的坏计算器上，我们可以执行以下两种操作：
```
双倍（Double）：将显示屏上的数字乘 2；
递减（Decrement）：将显示屏上的数字减 1 。
最初，计算器显示数字 X。
```
返回显示数字 Y 所需的最小操作数。

示例 1：
```
输入：X = 2, Y = 3
输出：2
解释：先进行双倍运算，然后再进行递减运算 {2 -> 4 -> 3}.
```
示例 2：
```
输入：X = 5, Y = 8
输出：2
解释：先递减，再双倍 {5 -> 4 -> 8}.
```
示例 3：
```
输入：X = 3, Y = 10
输出：3
解释：先双倍，然后递减，再双倍 {3 -> 6 -> 5 -> 10}.
```
示例 4：
```
输入：X = 1024, Y = 1
输出：1023
解释：执行递减运算 1023 次
```
提示：
```
1 <= X <= 10^9
1 <= Y <= 10^9
```
## 解法
### 思路
贪心算法：
- `Y > X`时，循环执行如下操作，并记录执行次数`count`：
    - 偶数：除2
    - 奇数：加1
- `Y <= X`时：`count + Y - X`，如果处理过后，Y少于X了，那么就计算相差的值，通过相加来补充
### 代码
```java
class Solution {
    public int brokenCalc(int X, int Y) {
        int count = 0;
        while (X < Y) {
            count++;
            if (Y % 2 == 1) {
                Y++;
            } else {
                Y /= 2;
            }
        }
        
        return count + X - Y;
    }
}
```
# LeetCode_153_寻找旋转排序数组中的最小值
## 题目
假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

请找出其中最小的元素。

你可以假设数组中不存在重复元素。

示例 1:
```
输入: [3,4,5,1,2]
输出: 1
```
示例 2:
```
输入: [4,5,6,7,0,1,2]
输出: 0
```
## 解法
### 思路
- 暂存数组第一个值作为最小值
- 从第二个元素开始遍历数组
- 与上次循环暂存的值进行比较，因为是升序，如果小于上一个值，就直接返回当前值
- 暂存当前值，作为下一个循环的前值
- 如果没有，就返回第一个值
### 代码
```java
class Solution {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int first = nums[0], pre = first;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < pre) {
                return nums[i];
            }
            
            pre = nums[i];
        }
        
        return first;
    }
}
```
## 解法二
### 思路
二分搜索：
- 参数：
    - 头指针：`nums[0]`
    - 尾指针：`nums[n - 1]`
- 循环之前：
    - 如果数组长度为1，返回头指针
    - 尾指针大于头指针，说明是数组是升序，直接返回头指针元素
- 循环，条件为头指针小于等于尾指针：
    - 计算中间指针
    - 如果中间元素比后一个元素大，返回后一个元素
    - 如果中间元素比前一个元素小，返回中间元素
    - 如果中间元素大于头指针，头指针为中间下标 + 1
    - 如果中间元素小于头指针，尾指针为中间下标 - 1
### 代码
```java
class Solution {
    public int findMin(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        int head = 0, tail = nums.length - 1;

        if (nums[tail] > nums[head]) {
            return nums[head];
        }

        while (head <= tail) {
            int mid = head + (tail - head) / 2;
            
            if (nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            }
            
            if (nums[mid] < nums[mid - 1]) {
                return nums[mid];
            }
            
            if (nums[mid] < nums[head]) {
                tail = mid - 1;
            } else {
                head = mid + 1;
            }
        }
        
        return -1;
    }
}
```
# LeetCode_740_删除与获得点数
## 题目
给定一个整数数组 nums ，你可以对它进行一些操作。

每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除每个等于 nums[i] - 1 或 nums[i] + 1 的元素。

开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。

示例 1:
```
输入: nums = [3, 4, 2]
输出: 6
解释: 
删除 4 来获得 4 个点数，因此 3 也被删除。
之后，删除 2 来获得 2 个点数。总共获得 6 个点数。
```
示例 2:
```
输入: nums = [2, 2, 3, 3, 3, 4]
输出: 9
解释: 
删除 3 来获得 3 个点数，接着要删除两个 2 和 4 。
之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
总共获得 9 个点数。
```
注意:
```
nums的长度最大为20000。
每个整数nums[i]的大小都在[1, 10000]范围内。
```
## 解法
### 思路
动态规划：
- 前提需要先进行计数排序，将元素个数统计出来放入数组bucket中，数组下标代表元素值
- `dp[i][j]`：i元素第j种情况下的最大点数，j只可能是0或1
- 状态转移方程，在遍历bucket过程中：
    - 如果前值不为0：
        - `dp[i][1] = dp[i - 1][0] + bucket[i] * i`
        - `dp[i][0] = max(dp[i - 1][1], dp[i - 1][0]` 
    - 如果前置为0：
        - `dp[i][1] = max(dp[i - 1][1], dp[i - 1][0]) + bucket[i] * i`
        - `dp[i][0] = max(dp[i - 1][0], dp[i - 1][1])`
- base case：`dp[0][0] = 0`, `dp[0][1] = bucket[0]`
- 返回结果：`max(dp[10000][1], dp[10000][0])`
### 代码
```java
class Solution {
    public int deleteAndEarn(int[] nums) {
        int[] bucket = new int[10001];
        for (int i = 0; i < nums.length; i++) {
            bucket[nums[i]]++;
        }

        int[][] dp = new int[10001][2];
        for (int i = 1; i < dp.length; i++) {
            int max = Math.max(dp[i - 1][1], dp[i - 1][0]);
            if (bucket[i - 1] > 0) {
                dp[i][1] = dp[i - 1][0] + bucket[i] * i;
            } else {
                dp[i][1] = max + bucket[i] * i;
            }
            dp[i][0] = max;
        }

        return Math.max(dp[10000][0], dp[10000][1]);
    }
}
```
## 解法二
### 思路
基于解法一，为0的元素不需要进行计算，且也不需要使用二维数组，因为每一个元素的不同状态的值都只取决于之前的元素不同状态的的值，所以只需要使用两个临时变量记录取值和不取值两种状态就可以了。
### 代码
```java
class Solution {
    public int deleteAndEarn(int[] nums) {
        int[] bucket = new int[10001];
        for (int num : nums) {
            bucket[num]++;
        }
        
        int take = 0, avoid = 0, pre = -1;
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] > 0) {
                int max = Math.max(take, avoid);
                if (i - 1 == pre) {
                    take = avoid + bucket[i] * i;
                } else {
                    take = max + bucket[i] * i;
                }
                avoid = max;
                
                pre = i;
            }
        }
        
        return Math.max(take, avoid);
    }
}
```