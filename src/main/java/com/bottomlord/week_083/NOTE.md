# [LeetCode_978_最长湍流子数组](https://leetcode-cn.com/problems/longest-turbulent-subarray/)
## 解法
### 思路
双指针
- 初始化左右指针`left`和`right`，用来定义一个符合题意的子数组窗口`[left, right]`，初始化两个指针都指向坐标为0的元素
- 定义一个暂存窗口最大值的变量`ans`，初始为1，原因是初始窗口`[0,0]`就是一个窗口长度为1的窗口
- 然后遍历真个数组，根据如下情况来更新窗口：
    - 在两个指针相等的情况下，类似起始状态，此时`right`肯定是要右移来扩大窗口大小的，但题目不要求相等的相邻元素存在于数组中，所以还要判断`left`和`left+1`元素是否相等，如果相等，`left`也跟着右移，相当于重新开始判断一个新的窗口
    - 在两个指针不相等的情况下：
        - 如果`right`和`right-1`以及和`right+1`组成符合题目要求的相邻元素组合，那么说明子数组窗口可以扩大，`right`右移，同时更新`ans`
        - 如果不能组成符合题意的元素了，那么说明以`left`为起始的子数组已经不能再扩大了，且之前的长度已经更新到了`ans`中，所以就让`left=right`来重置窗口，继续判断
### 代码
```java
class Solution {
    public int maxTurbulenceSize(int[] arr) {
        int left = 0, right = 0, ans = 1, n = arr.length;
        
        while (right < n - 1) {
            if (left == right) {
                right++;
                if (arr[left] == arr[left + 1]) {
                    left++;
                }
            } else {
                if ((arr[right - 1] < arr[right] && arr[right] > arr[right + 1]) ||
                    (arr[right - 1] > arr[right] && arr[right] < arr[right + 1])) {
                    right++;
                } else {
                    left = right;
                }
            }
            
            ans = Math.max(ans, right - left + 1);
        }
        
        return ans;
    }
}
```
## 解法二
### 思路
动态规划：
- `dp[i][j]`：
    - `i`：对应湍流数组结尾坐标
    - `j`：对应`arr[i] < arr[i - 1]`还是`arr[i - 1] > arr[i]`，分别用0和1标识
- 状态转移方程：
    - 如果`arr[i] > arr[i - 1]`，则`dp[i][0] = dp[i - 1][1] + 1`
    - 如果`arr[i] < arr[i - 1]`，则`dp[i][1] = dp[i - 1][0] + 1`
- base case：
    - `dp[0][0] = 1`
    - `dp[0][1] = 1` 
- 最终结果：遍历dp数组找到最大值
### 代码
```java
class Solution {
    public int maxTurbulenceSize(int[] arr) {
        int len = arr.length;
        int[][] dp = new int[len][2];
        dp[0][0] = dp[0][1] = 1;

        int ans = 1;
        for (int i = 1; i < len; i++) {
            dp[i][0] = dp[i][1] = 1;
            if (arr[i - 1] < arr[i]) {
                dp[i][0] = dp[i - 1][1] + 1;
            } else if (arr[i - 1] > arr[i]) {
                dp[i][1] = dp[i - 1][0] + 1;
            }
            ans = Math.max(ans, dp[i][0]);
            ans = Math.max(ans, dp[i][1]);
        }

        return ans;
    }
}
```
## 解法三
### 思路
动态规划的状态转移只依赖当前因素和前一个因素的状态，不需要使用二维数组
### 代码
```java
class Solution {
    public int maxTurbulenceSize(int[] arr) {
        int len = arr.length, pre1 = 1, pre0 = 1, ans = 1;
        
        for (int i = 1; i < len; i++) {
            int cur0 = 1, cur1 = 1;
            if (arr[i- 1] < arr[i]) {
                cur0 = pre1 + 1;
            } else if (arr[i - 1] > arr[i]) {
                cur1 = pre0 + 1;
            }
            
            pre0 = cur0;
            pre1 = cur1;
            ans = Math.max(ans, cur0);
            ans = Math.max(ans, cur1);
        }
        
        return ans;
    }
}
```