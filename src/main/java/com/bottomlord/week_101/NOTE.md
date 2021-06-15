# [LeetCode_852_山脉数组的峰顶索引](https://leetcode-cn.com/problems/peak-index-in-a-mountain-array/)
## 解法
### 思路
遍历找到比前后元素都大的元素坐标，找到后直接返回
### 代码
```java
class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] < arr[i] && arr[i + 1] < arr[i]) {
                return i;
            }
        }
        
        return -1;
    }
}
```
## 解法二
### 思路
二分查找
### 代码
```java
class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        int head = 1, tail = arr.length - 2;
        while (head <= tail) {
            int mid = (tail - head) / 2 + head;
            
            if (arr[mid - 1] < arr[mid] && arr[mid + 1] < arr[mid]) {
                return mid;
            } else if (arr[mid - 1] < arr[mid]) {
                head = mid + 1;
            } else {
                tail = mid - 1;
            }
        }
        
        return -1;
    }
}
```
# [LeetCode_279_完全平方数](https://leetcode-cn.com/problems/perfect-squares/)
## 解法
### 思路
动态规划：
- `dp[i]`：组合成总和为i的整数的完全平方数个数的最小值
- 状态转移方程：`dp[i] = min(dp[i], dp[i - j * j] + 1)`
- base case：`dp[i] = i`，相当于设置初始值为最大值，方便做最小值比较
- 返回结果：`dp[n]`
### 代码
```java
class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = i;
            for (int j = 1; i - j * j >= 0; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        return dp[n];
    }
}
```
# [LeetCode_1449_数位成本和为目标值的最大数字](https://leetcode-cn.com/problems/form-largest-integer-with-digits-that-add-up-to-target/)
## 解法
### 思路

### 代码
```java

```