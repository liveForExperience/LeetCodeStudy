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
# [LeetCode_877_石子游戏](https://leetcode-cn.com/problems/stone-game/)
## 解法
### 思路
[数学解法](https://leetcode-cn.com/problems/stone-game/solution/shi-zi-you-xi-by-leetcode-solution/)：
- 因为石头堆是偶数个，所以如果将石头堆分成2组，每组的单个元素间隔排列，那么总的石头堆就是的初始状态时，头是一组，尾一定是另一组
- 如果而当先手这选择了头尾的任意一堆后，后手者只能选择上剩下的那组石头堆，因为被拿走一堆后的总石头堆，头尾都只有另一组的石头
- 而后手者拿完他那一轮后，先手者又可以在2组石头堆中选择，而后手只能选剩下的一组的2个
- 所以按照这个特性，先手者只要计算出2组石头的总数，然后每次都选择那一组的石头，就可以确定拿到总数最多的石头
### 代码
```java
class Solution {
    public boolean stoneGame(int[] piles) {
        return true;
    }
}
```
## 解法二
### 思路
动态规划：
### 代码
```java

```
# [LeetCode_486_预测赢家](https://leetcode-cn.com/problems/predict-the-winner/)
## 解法
### 思路
递归：
- 假设先手获取分数为正，后手获取分数为负
- 那么计算过程就是，递归计算每种可能路径，并做好正负转换，累加数值
- 最后看返回的最大值是否大于等于0，如果是就说明先手不会输
- 时间复杂度为2的n次方
### 代码
```java
class Solution {
    public boolean PredictTheWinner(int[] nums) {
        return recuse(nums, 0, nums.length - 1, 1) >= 0;
    }

    private int recuse(int[] nums, int start, int end, int turn) {
        if (start == end) {
            return nums[start] * turn;
        }

        int pickStart = nums[start] * turn + recuse(nums, start + 1, end, -turn),
            pickEnd = nums[end] * turn + recuse(nums, start, end - 1, -turn);

        return turn == 1 ? Math.max(pickEnd, pickStart) : Math.min(pickStart, pickEnd);
    }
}
```
## 解法二
### 思路
动态规划：
- dp[i][j]：在数组坐标i与j的范围内（i<=j，否则无意义），当前玩家与后手玩家之间的分数的最大差值
- base case：当i == j时，dp[i][j] = nums[i]。当只剩下一个元素可以选择时，那么当前选择的玩家与后手玩家的差值就是当前元素值，因为后手选不了了
- 状态转移方程：dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1])
- 返回dp[0][nums.lenght() - 1] >= 0
### 代码
```java
class Solution {
    public boolean PredictTheWinner(int[] nums) {
        int len = nums.length;
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = nums[i];
        }

        for (int i = len - 2; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }

        return dp[0][len - 1] >= 0;
    }
}
```
# [LeetCode_1449_数位成本和为目标值的最大数字](https://leetcode-cn.com/problems/form-largest-integer-with-digits-that-add-up-to-target/)
## 解法
### 思路

### 代码
```java

```