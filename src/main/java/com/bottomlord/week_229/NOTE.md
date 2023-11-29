# [LeetCode_907_子数组的最小值之和](https://leetcode.cn/problems/sum-of-subarray-minimums)
## 失败解法
### 原因
内存不足
### 思路
- 思考过程：
  - `dp[i][j]`：`i`到`j`区间内的最小值，`i <= j`
  - 状态转移方程：`dp[i][j] = min(dp[i][j - 1], nums[j])`
  - base case：`dp[i][i] = nums[i]`
- 算法过程：
  - 声明并初始化二维dp数组`dp[][]`
  - 初始化暂存最小值总和的变量`ans`
  - 嵌套循环dp数组，通过状态转移方程填充数组值，同时将得到的最小值累加到暂存变量`ans`中
  - 循环结束，返回`ans`
### 代码
```java
class Solution {
    public int sumSubarrayMins(int[] arr) {
        int n = arr.length, mod = 1000000007, ans = Arrays.stream(arr).sum() % mod;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {# [LeetCode_907_子数组的最小值之和](https://leetcode.cn/problems/sum-of-subarray-minimums)
## 失败解法
### 原因
内存不足
### 思路
- 思考过程：
  - `dp[i][j]`：`i`到`j`区间内的最小值，`i <= j`
  - 状态转移方程：`dp[i][j] = min(dp[i][j - 1], nums[j])`
  - base case：`dp[i][i] = nums[i]`
- 算法过程：
  - 声明并初始化二维dp数组`dp[][]`
  - 初始化暂存最小值总和的变量`ans`
  - 嵌套循环dp数组，通过状态转移方程填充数组值，同时将得到的最小值累加到暂存变量`ans`中
  - 循环结束，返回`ans`
### 代码
```java
class Solution {
    public int sumSubarrayMins(int[] arr) {
        int n = arr.length, mod = 1000000007, ans = Arrays.stream(arr).sum() % mod;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = arr[i];
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i][j - 1], arr[j]);
                ans += dp[i][j];
                ans %= mod;
            }
        }
        
        return ans;
    }
}
```
## 失败解法二
### 失败原因
超时
### 思路
- 基于解法一进行状态压缩
- 通过观察解法一发现，状态转移过程中，`dp[i][j]`只与`dp[i][j - 1]`有关，故可以通过一个变量来暂存`dp[i][j - 1]`所代表的值
- 每次外层循环的时候，通过`arr[i]`来确定初始的`dp[i][j - 1]`的值，也即`arr[i]`
### 代码
```java
class Solution {
    public int sumSubarrayMins(int[] arr) {
        int n = arr.length, mod = 1000000007, ans = 0;

        for (int i = 0; i < n; i++) {
            int pre = arr[i];
            ans += pre;
            for (int j = i + 1; j < n; j++) {
                int cur = Math.min(pre, arr[j]);
                ans += cur;
                ans %= mod;
                pre = cur;
            }
        }
        
        return ans;
    }
}
```
## 解法
### 思路
- 这道题可以拆分成：计算`arr[i]`作为最小值所在子数组的个数`C`去乘以`arr[i]`的值，并对n组这样的值进行求和。
- 但其中需要去除掉如果有大于1个值是最小值的情况，为了剔除这种情况，可以进一步将子数组拆分成以`arr[i]`为中心的2部分
  - 左半部分，`arr[i]`作为这一部分的最小值，允许左半部分包含相同的最小值
  - 右半部分，`arr[i]`作为这一部分的最小值，不允许出现相同的值
- 这两部分可以通过单调栈来求，也即
  - 遍历`arr`数组
  - 判断当前值`arr[i]`
    - 如果栈为空，说明当前值是遍历方向上的最小值，这个距离的距离就是`i - (-1)`
    - 如果栈不为空：
      - 栈顶元素大于（等于，是否等于取决于放下，如上面讨论的）`arr[i]`，那么说明这个元素不是当前需要考虑的元素，不会对判断当前元素出现的子数组个数有影响，将该元素出栈
      - 否则说明包含栈顶元素的子数组，肯定不是当前元素能够成为最小值的子数组，这时候就可以通过`i`这个栈顶元素的坐标距离得到长度
- 将2个方向上得到的长度分别存在2个数组`lefts`和`rights`中，通过遍历arr数组坐标，计算`lefts[i] * rights[i] * arr[i]) % mod`即可得到当前元素作为最小值的总和，将这些总和累加即可
- 为了防止溢出，可以将暂存用的变量设置为long，返回结果的时候再转成int
### 代码
```java
class Solution {
    public int sumSubarrayMins(int[] arr) {
                int n = arr.length, mod = 1000000007;
        int[] lefts = new int[n], rights = new int[n];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int num = arr[i];
            while (!stack.isEmpty() && arr[stack.peek()] >= num) {
                stack.pop();
            }

            lefts[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            stack.push(i);
        }

        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            int num = arr[i];
            while (!stack.isEmpty() && arr[stack.peek()] > num) {
                stack.pop();
            }

            rights[i] = stack.isEmpty() ? n - i : stack.peek() - i;
            stack.push(i);
        }

        long ans = 0L;
        for (int i = 0; i < n; i++) {
            ans = (ans + (long)lefts[i] * rights[i] * arr[i]) % mod;
        }

        return (int)ans;
    }
}
```
            dp[i][i] = arr[i];
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i][j - 1], arr[j]);
                ans += dp[i][j];
                ans %= mod;
            }
        }
        
        return ans;
    }
}
```
## 失败解法二
### 失败原因
超时
### 思路
- 基于解法一进行状态压缩
- 通过观察解法一发现，状态转移过程中，`dp[i][j]`只与`dp[i][j - 1]`有关，故可以通过一个变量来暂存`dp[i][j - 1]`所代表的值
- 每次外层循环的时候，通过`arr[i]`来确定初始的`dp[i][j - 1]`的值，也即`arr[i]`
### 代码
```java
class Solution {
    public int sumSubarrayMins(int[] arr) {
        int n = arr.length, mod = 1000000007, ans = 0;

        for (int i = 0; i < n; i++) {
            int pre = arr[i];
            ans += pre;
            for (int j = i + 1; j < n; j++) {
                int cur = Math.min(pre, arr[j]);
                ans += cur;
                ans %= mod;
                pre = cur;
            }
        }
        
        return ans;
    }
}
```
## 解法
### 思路
- 这道题可以拆分成：计算`arr[i]`作为最小值所在子数组的个数`C`去乘以`arr[i]`的值，并对n组这样的值进行求和。
- 但其中需要去除掉如果有大于1个值是最小值的情况，为了剔除这种情况，可以进一步将子数组拆分成以`arr[i]`为中心的2部分
  - 左半部分，`arr[i]`作为这一部分的最小值，允许左半部分包含相同的最小值
  - 右半部分，`arr[i]`作为这一部分的最小值，不允许出现相同的值
- 这两部分可以通过单调栈来求，也即
  - 遍历`arr`数组
  - 判断当前值`arr[i]`
    - 如果栈为空，说明当前值是遍历方向上的最小值，这个距离的距离就是`i - (-1)`
    - 如果栈不为空：
      - 栈顶元素大于（等于，是否等于取决于放下，如上面讨论的）`arr[i]`，那么说明这个元素不是当前需要考虑的元素，不会对判断当前元素出现的子数组个数有影响，将该元素出栈
      - 否则说明包含栈顶元素的子数组，肯定不是当前元素能够成为最小值的子数组，这时候就可以通过`i`这个栈顶元素的坐标距离得到长度
- 将2个方向上得到的长度分别存在2个数组`lefts`和`rights`中，通过遍历arr数组坐标，计算`lefts[i] * rights[i] * arr[i]) % mod`即可得到当前元素作为最小值的总和，将这些总和累加即可
- 为了防止溢出，可以将暂存用的变量设置为long，返回结果的时候再转成int
### 代码
```java
class Solution {
    public int sumSubarrayMins(int[] arr) {
                int n = arr.length, mod = 1000000007;
        int[] lefts = new int[n], rights = new int[n];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int num = arr[i];
            while (!stack.isEmpty() && arr[stack.peek()] >= num) {
                stack.pop();
            }

            lefts[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            stack.push(i);
        }

        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            int num = arr[i];
            while (!stack.isEmpty() && arr[stack.peek()] > num) {
                stack.pop();
            }

            rights[i] = stack.isEmpty() ? n - i : stack.peek() - i;
            stack.push(i);
        }

        long ans = 0L;
        for (int i = 0; i < n; i++) {
            ans = (ans + (long)lefts[i] * rights[i] * arr[i]) % mod;
        }

        return (int)ans;
    }
}
```