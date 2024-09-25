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
# [LeetCode_2661_找出叠涂元素](https://leetcode.cn/problems/first-completely-painted-row-or-column)
## 解法
### 思路
- 初始化部分：
  - 2个记录行列元素个数的数组，长度分别为`m`和`n`
  - 1个记录数值和坐标映射关系的数组`mapping`，长度为`m * n + 1`
- 遍历`mat`矩阵，将元素值放入到`mapping`中，值为`r * n + c`：
  - `r`代表横坐标
  - `c`代表纵坐标
- 遍历`arr`，通过`mapping`找到坐标，并在对应的记录个数数组中累加，同时判断任意一个数组对应坐标的值是否已经与`m`或`n`相等，横坐标计数值看`n`，纵坐标看`m`
- 遍历的时候如果找到，就返回`i`
### 代码
```java
class Solution {
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[] r = new int[m], c = new int[n], mapping = new int[m * n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int num = mat[i][j];
                mapping[num] = i * n + j;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            int index = mapping[arr[i]];
            int row = index / n, col = index % n;
            if (++r[row] == n || ++c[col] == m) {
                return i;
            }
        }

        return -1;
    }
}
```
# [LeetCode_1094_拼车](https://leetcode.cn/problems/car-pooling)
## 解法
### 思路
- 思考过程：
  - 理解参数：
    - `capacity`是汽车的承载上限
    - `trips`代表有若干个`trip`，有需要上车的数量，有上车的位置，还有下车的位置
  - 理解题目：
    - 车子有`capacity`，根据题目意思，这个值会在`trip`指定位置上车时被消耗，消耗的值是其第一个元素
    - `tirp`第二个元素，对应上车，也代表要消耗`capacity`的位置
    - `trip`第三个元素，对应下车，也代表了恢复`capacity`的位置
    - 所以需要有一个可以方便记录上下车位置的数据结构，在对应的上下车位置记录上会消耗或恢复的值
    - 然后能通过一个暂存值来判定，在某一个位置，车子的capacity是否足够，如果不足够，那么就可以返回false了
    - 那么这个暂存值就可以通过累加数组中记录值，用`差分`的思路来记录即可
- 算法过程：
  - 初始化一个数组`arr`，长度可以设置为题目规定的距离上限1000，再+1，加一是因为这个数组的坐标代表的是距离，所以需要有坐标1000
  - 遍历`trips`数组，根据元素的3个子元素，将值，放入到`arr`数组中的上下车坐标位置
  - 再遍历`arr`数组，通过差分的方式求得当前位置的车子的实际承载值，如果大于`capacity`，就返回false
  - 如果遍历结束，那么就返回true，说明整个过程是满足承载要求的
### 代码
```java
class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        int[] arr = new int[1001];
        for (int[] trip : trips) {
            arr[trip[1]] += trip[0];
            arr[trip[2]] -= trip[0];
        }
        
        int cur = 0;
        for (int n : arr) {
            cur += n;
            if (cur > capacity) {
                return false;
            }
        }
        
        return true;
    }
}
```
# [LeetCode_1423_可获得的最大点数](https://leetcode.cn/problems/maximum-points-you-can-obtain-from-cards)
## 解法
### 思路
- 思考过程：
  - 通过阅读题目可知，虽然每次都需要选择数组区间的头尾元素，看上去需要穷举所有可能性，但其实整个选择区间是一个子数组，不可被分割，所以好像一个包含头尾两端的滑动区间，极限位置就是左边全选或者右边全选，然后在这两个区间的之间滑动
  - 我们就可以通过算出一个极限区间的区间数组元素和，然后通过滑动进行比较，就可以得到最大值了
- 算法过程：
  - 初始化一个变量`ans`用于暂存答案
  - 确定区间的长度，也就是`k`
  - 遍历数组`k`次，左侧全部元素的极限总和`sum`
  - 然后通过加入右侧元素，减去左侧最右端元素的方式，更新`sum`，比较`sum`和`ans`之间的较大值，将较大值暂存在`ans`上
    - `sum`的更新公式：`sum = sum - cardPoints[k - 1 - i] + cardPoints[n - 1 - i]`，`i`是遍历的坐标
  - 遍历结束以后返回`ans`即可
### 代码
```java
class Solution {
  public int maxScore(int[] cardPoints, int k) {
    int ans = 0, n = cardPoints.length;
    for (int i = 0; i < k; i++) {
      ans += cardPoints[i];
    }

    if (n == k) {
      return ans;
    }

    int sum = ans;
    for (int i = 0; i < k; i++) {
      sum = sum - cardPoints[k - 1 - i] + cardPoints[n - 1 - i];
      ans = Math.max(ans, sum);
    }

    return ans;
  }
}
```