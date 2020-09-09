# LeetCode_256_粉刷房子
## 题目
假如有一排房子，共 n 个，每个房子可以被粉刷成红色、蓝色或者绿色这三种颜色中的一种，你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。

当然，因为市场上不同颜色油漆的价格不同，所以房子粉刷成不同颜色的花费成本也是不同的。每个房子粉刷成不同颜色的花费是以一个 n x 3 的矩阵来表示的。

例如，costs[0][0] 表示第 0 号房子粉刷成红色的成本花费；costs[1][2] 表示第 1 号房子粉刷成绿色的花费，以此类推。请你计算出粉刷完所有房子最少的花费成本。

注意：
```
所有花费均为正整数。
```
示例：
```
输入: [[17,2,17],[16,16,5],[14,3,19]]
输出: 10
解释: 将 0 号房子粉刷成蓝色，1 号房子粉刷成绿色，2 号房子粉刷成蓝色。
     最少花费: 2 + 5 + 3 = 10。
```
## 失败解法
### 失败原因
超时，时间复杂度过高
### 思路
自顶向下的递归：
- 把3种颜色对应成3种路径，模拟树的搜索过程
- 递归参数：
    - costs：数组集合
    - depth：树的深度
    - color：上一层为当前层挑选的颜色
- 返回各个路径作为根节点得子树的和的最小值
- 退出条件是找到叶子节点，也就是depth和costs.length - 1相等
### 代码
```java
class Solution {
    public int minCost(int[][] costs) {
        if (costs.length == 0) {
            return 0;
        }

        return Math.min(recurse(costs, 0, 0), Math.min(recurse(costs, 0, 1), recurse(costs, 0, 2)));
    }

    private int recurse(int[][] costs, int depth, int color) {
        int total = costs[depth][color];
        if (depth == costs.length - 1) {
            return total;
        }

        if (color == 0) {
            total += Math.min(recurse(costs, depth + 1, 1), recurse(costs, depth + 1, 2));
        } else if (color == 1) {
            total += Math.min(recurse(costs, depth + 1, 0), recurse(costs, depth + 1, 2));
        } else {
            total += Math.min(recurse(costs, depth + 1, 1), recurse(costs, depth + 1, 0));
        }

        return total;
    }
}
```
## 解法
### 思路
在失败解法的基础上增加记忆化搜索
### 代码
```java
class Solution {
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        
        Integer[][] memo = new Integer[costs.length][3];
        return Math.min(recurse(costs, 0, 0, memo), Math.min(recurse(costs, 0, 1, memo), recurse(costs, 0, 2, memo)));
    }
    
    private int recurse(int[][] costs, int depth, int color, Integer[][] memo) {
        int total = costs[depth][color];
        if (depth == costs.length - 1) {
            memo[depth][color] = total;
            return total;
        }
        
        if (color == 0) {
            int green = memo[depth + 1][1] == null ? recurse(costs, depth + 1, 1, memo) : memo[depth + 1][1],
                blue = memo[depth + 1][2] == null ? recurse(costs, depth + 1, 2, memo) : memo[depth + 1][2];
            total += Math.min(green, blue);
        } else if (color == 1) {
            int red = memo[depth + 1][0] == null ? recurse(costs, depth + 1, 0, memo) : memo[depth + 1][0],
                blue = memo[depth + 1][2] == null ? recurse(costs, depth + 1, 2, memo) : memo[depth + 1][2];
            total += Math.min(red, blue);
        } else {
            int red = memo[depth + 1][0] == null ? recurse(costs, depth + 1, 0, memo) : memo[depth + 1][0],
                green = memo[depth + 1][1] == null ? recurse(costs, depth + 1, 1, memo) : memo[depth + 1][1];
            total += Math.min(red, green);
        }
        
        memo[depth][color] = total;
        return total;
    }
}
```
## 解法二
### 思路
动态规划：
- `dp[i][j]`：0到坐标i的楼，使用第j种颜色得到做的最小的颜料花费
- 状态转移方程：`dp[i][j] = costs[i][j] + min(dp[i - 1],[k], dp[i - 1][l])`
- base case：
    - `dp[0][j] = costs[0][0]`
    - `dp[0][k] = costs[0][1]`
    - `dp[0][l] = costs[0][2]`
- 最终结果`min(dp[len - 1][0], dp[len - 1][1], dp[len - 1][2])`
### 代码
```java
class Solution {
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        
        int len = costs.length;
        int[][] dp = new int[len][3];
        dp[0][0] = costs[0][0];
        dp[0][1] = costs[0][1];
        dp[0][2] = costs[0][2];
        
        for (int i = 1; i < len; i++) {
            dp[i][0] = costs[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] = costs[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
            dp[i][2] = costs[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
        }
        
        return Math.min(dp[len - 1][0], Math.min(dp[len - 1][1], dp[len - 1][2]));
    }
}
```
# LeetCode_259_较小的三数之和
## 题目
给定一个长度为 n 的整数数组和一个目标值 target，寻找能够使条件 nums[i] + nums[j] + nums[k] < target 成立的三元组  i, j, k 个数（0 <= i < j < k < n）。

示例：
```
输入: nums = [-2,0,1,3], target = 2
输出: 2 
解释: 因为一共有两个三元组满足累加和小于 2:
    [-2,0,1]
     [-2,0,3]
```
 ```
进阶：是否能在 O(n2) 的时间复杂度内解决？
```
## 解法
### 思路
3重循环遍历求解
### 代码
```java
class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        int len = nums.length, ans = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    if (nums[i] + nums[j] + nums[k] < target) {
                        ans++;
                    }
                }
            }
        }
        
        return ans;
    }
}
```
## 解法二
### 思路
二分查找：
- 将数组排序
- 先确定一个坐标`i`，遍历的范围是`[0, len - 2)`，空出2个位置给另2个值
- 确定`i`之后，用`target - nums[i]`获得一个`newTarget`，这样使得`3sum`计算降到了`2sum`
- 以`newTarget`为目标，同时从`i + 1`开始确定剩下两个数中较小的那个
- 而求第三个数的过程就交给二分查找完成，求的目标值就是`newTarget - nums[j]`
- 二分找到的坐标`k`，就是`nums[j] + nums[k] < newTarget`的最大区间，基于`i`的状态下能够得到的最多的`2sum`个数就是`k - j`
- 遍历`i`并累加`2sum`的个数，并作为结果在遍历结束后返回
- 在二分查找的时候，`mid`要故意向`tail`偏移，因为找的是离`target`最近的小于它的数，所以当发现mid坐标对应的值小于target，有可能这个就是离之最近的值，那么就使得head = mid的同时，让下一次mid的值靠右，那么新mid的值就会大于或等于target，此时再让tail = mid，就能使head == tail，从而获得这个目标坐标
### 代码
```java
class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        int sum = 0, len = nums.length;

        Arrays.sort(nums);

        for (int i = 0; i < len - 2; i++) {
            int newTarget = target - nums[i];

            for (int j = i + 1; j < len - 1; j++) {
                int k = binarySearch(nums, j, newTarget - nums[j]);
                sum += k - j;
            }
        }

        return sum;
    }

    private int binarySearch(int[] nums, int start, int target) {
        int head = start, tail = nums.length - 1;
        while (head < tail) {
            int mid = head + (tail + 1 - head) / 2;

            if (nums[mid] < target) {
                head = mid;
            } else {
                tail = mid - 1;
            }
        }

        return head;
    }
}
```