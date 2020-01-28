# LeetCode_1039_多边形三角剖分的最低得分
## 题目
给定 N，想象一个凸 N 边多边形，其顶点按顺时针顺序依次标记为 A[0], A[i], ..., A[N-1]。

假设您将多边形剖分为 N-2 个三角形。对于每个三角形，该三角形的值是顶点标记的乘积，三角剖分的分数是进行三角剖分后所有 N-2 个三角形的值之和。

返回多边形进行三角剖分后可以得到的最低分。

示例 1：
```
输入：[1,2,3]
输出：6
解释：多边形已经三角化，唯一三角形的分数为 6。
```
示例 2：
```
输入：[3,7,4,5]
输出：144
解释：有两种三角剖分，可能得分分别为：3*7*5 + 4*5*7 = 245，或 3*4*5 + 3*4*7 = 144。最低分数为 144。
```
示例 3：
```
输入：[1,3,1,4,1,5]
输出：13
解释：最低分数三角剖分的得分情况为 1*1*3 + 1*1*4 + 1*1*5 + 1*1*1 = 13。
```
提示：
```
3 <= A.length <= 50
1 <= A[i] <= 100
```
## 解法
### 思路
动态规划：
- 对于点`0，1，...，n-1`构成的多边形三角剖分，考虑点`0`和`n-1`，因为总有某个点`j`与点`0`和`n-1`构成三角形，使得原多边形被分成一个三角形和至多两个凸多边形，求解原问题退化成求解两个规模更小的子问题。
- `dp[i][j]`：i到j范围内，最小的值乘积
- 状态转移方程：`dp[i][j] = dp[i][k] + dp[k][j] + A[i] * A[j] * A[k]`
- 三层循环确定`i,k,j`
    - 最外层确定`i`和`j`之间的距离`len`，最小是2
    - 中间层确定`i`，初始值是0
    - 根据最外层的`len`来确定`j`
    - 最内层来确定`k`，k的范围是在`i`和`j`之间，且因为会出现循环，所以要使用取余的方式来求得正确的下标
### 代码
```java
class Solution {
    public int minScoreTriangulation(int[] A) {
        int len = A.length;
        int[][] dp = new int[len][len];
        for (int[] arr : dp) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }
        for (int i = 0; i < len; i++) {
            dp[i][(i + 1) % len] = 0;
        }
        for (int l = 2; l < len; l++) {
            for (int i = 0; i < len; i++) {
                int j = (i + l) % len;
                for (int k = (i + 1) % len; k != j; k = (k + 1) % len) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + A[i] * A[k] * A[j]);
                }
            }
        }
        return dp[0][len - 1];
    }
}
```
## 解法二
### 思路
根据解法一中的分析，整个计算过程可以通过分解成若干子问题来解决，分解的过程是通过在首尾节点中找一个节点作为中点，组成一个三角形，从而将整个凸多边形分成至多2个。
- 且dfs过程中需要使用memo缓存，否则会超时
### 代码
```java
class Solution {
    public int minScoreTriangulation(int[] A) {
        Map<String, Integer> memo = new HashMap<>();
        return dfs(0, A.length - 1, A, memo);
    }

    private int dfs(int left, int right, int[] arr, Map<String, Integer> memo) {
        if (left + 1 == right) {
            return 0;
        }
        
        String key = Arrays.toString(new int[]{left, right});
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        
        int ans = Integer.MAX_VALUE;
        for (int i = left + 1; i < right; i++) {
            ans = Math.min(ans, dfs(left, i, arr, memo) + dfs(i, right, arr, memo) + arr[left] * arr[i] * arr[right]);
        }
        
        memo.put(key, ans);
        return ans;
    }
}
```
# LeetCode_1014_最佳观光组合
## 题目
给定正整数数组 A，A[i] 表示第 i 个观光景点的评分，并且两个景点 i 和 j 之间的距离为 j - i。

一对景点（i < j）组成的观光组合的得分为（A[i] + A[j] + i - j）：景点的评分之和减去它们两者之间的距离。

返回一对观光景点能取得的最高分。

示例：
```
输入：[8,1,5,2,6]
输出：11
解释：i = 0, j = 2, A[i] + A[j] + i - j = 8 + 5 + 0 - 2 = 11
```
提示：
```
2 <= A.length <= 50000
1 <= A[i] <= 1000
```
## 失败解法
### 失败原因
超出时间限制
### 思路
暴力解法：
遍历所有组合，求最大值
### 代码
```java
class Solution {
    public int maxScoreSightseeingPair(int[] A) {
        int ans = Integer.MIN_VALUE, len = A.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                ans = Math.max(ans, A[i] + A[j] + i - j);
            }
        }
        return ans;
    }
}
```
## 解法二
### 思路
- 题目公式：`A[i] + i + A[j] - j`，可以将这个公式分成两个部分`A[i] + i`和`A[j] - j`
- `A[i] + i`的值可以通过一个临时变量来保存，只要每次移动坐标时，将临时变量与当前遍历到的`A[i] + i`进性比较取最大值暂存
- 在比较`A[i] + i`的最大值之前，先将最大值与当前坐标对应的`A[j] + j`的和与暂存的结果比较，取最大值
- 这样就可以通过一次遍历获得两部分最大的值
### 代码
```java
class Solution {
    public int maxScoreSightseeingPair(int[] A) {
        int len = A.length, max = Integer.MIN_VALUE, ans = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            ans = Math.max(ans, max + A[i] - i);
            max = Math.max(max, A[i] + i);
        }
        return ans;
    }
}
```
# LeetCode_813_最大平均值和分组
## 题目
我们将给定的数组 A 分成 K 个相邻的非空子数组 ，我们的分数由每个子数组内的平均值的总和构成。计算我们所能得到的最大分数是多少。

注意我们必须使用 A 数组中的每一个数进行分组，并且分数不一定需要是整数。

示例:
```
输入: 
A = [9,1,2,3,9]
K = 3
输出: 20
解释: 
A 的最优分组是[9], [1, 2, 3], [9]. 得到的分数是 9 + (1 + 2 + 3) / 3 + 9 = 20.
我们也可以把 A 分成[9, 1], [2], [3, 9].
这样的分组得到的分数为 5 + 2 + 6 = 13, 但不是最大值.
```
说明:
```
1 <= A.length <= 100.
1 <= A[i] <= 10000.
1 <= K <= A.length.
答案误差在 10^-6 内被视为是正确的。
```
## 解法
### 思路
动态规划：
- `dp[i][k]`：A数组中前i个元素组成的数组分成k部分得到的最大分数
- 状态转移方程：`dp[i][k] = max(dp[i][k], dp[j][k - 1] + average(j + 1, i))`，整个`(0,i)`的范围中，取`(0,j)`的范围的最大分数，且分成的是`k - 1`部分，所以剩下的1部分就由`(j + 1, i)`的平均值代替，将两者相加与原`dp[i][k]`比较，取较大值
- base case：`dp[i][1] = sum[i] / i`，当整个数组分成1个分组时，就是整个元素求和再平均
- 获取`average()`可以通过提前计算每一个下标i对应的前i个元素的和`sum`，来快速计算average值`(sum[j] - sum[i]) / (j - i)`
### 代码
```java
class Solution {
    public double largestSumOfAverages(int[] A, int K) {
        int len = A.length;
        int[] sum = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            sum[i] = sum[i - 1] + A[i - 1];
        }

        double[][] dp = new double[len + 1][K + 1];
        for (int i = 1; i <= len; i++) {
            dp[i][1] = 1.0 * sum[i] / i;
            for (int k = 2; k <= K && k <= i; k++) {
                for (int j = 1; j < i; j++) {
                    dp[i][k] = Math.max(dp[i][k], dp[j][k - 1] + 1.0 * (sum[i] - sum[j]) / (i - j));
                }
            }
        }

        return dp[len][K];
    }
}
```
## 解法二
### 思路
动态规划：
- `dp[i][k]`：`(i,A.len)`的范围内分成`k`组的最大分数
- 状态转移方程：`dp[i][k] = max(dp[i][k], (sum[j] - sum[i]) / (j - i) + dp[j][k - 1])`
- base case：`dp[i][0] = (sum[len] - sum[i]) / (len - i)`
- 过程：
    - 生成sum数组用来暂存元素累加值，方便快速计算平均值
    - 遍历A数组生成base case
    - 三层循环生成`i,j,k`
    - 因为判断过程就是`k`和上一层`k-1`之间发生，所以一维数组可以复用代替二维的k
### 代码
```java
class Solution {
    public double largestSumOfAverages(int[] A, int K) {
        int len = A.length;
        double[] sum = new double[len + 1];
        for (int i = 0; i < len; i++) {
            sum[i + 1] = sum[i] + A[i];
        }

        double[] dp = new double[len];
        for (int i = 0; i < len; i++) {
            dp[i] = (sum[len] - sum[i]) / (len - i);
        }

        for (int k = 0; k < K - 1; k++) {
            for (int i = 0; i < len; i++) {
                for (int j = i + 1; j < len; j++) {
                    dp[i] = Math.max(dp[i], (sum[j] - sum[i]) / (j - i) + dp[j]);
                }
            }
        }
        
        return dp[0];
    }
}
```
# LeetCode_795_区间的子数组个数
## 题目
给定一个元素都是正整数的数组A ，正整数 L 以及 R (L <= R)。

求连续、非空且其中最大元素满足大于等于L 小于等于R的子数组个数。

例如 :
```
输入: 
A = [2, 1, 4, 3]
L = 2
R = 3
输出: 3
解释: 满足条件的子数组: [2], [2, 1], [3].
```
注意:
```
L, R  和 A[i] 都是整数，范围在 [0, 10^9]。
数组 A 的长度范围在[1, 50000]。
```
## 解法
### 思路
- 最大元素x，`x >= L && x <= R`，可以理解为A数组中，小于等于R的连续子序列个数减去小于等于L-1的连续子序列个数，`count(R) - count(L-1)`
- n个元素的连续的子序列个数等于n个相差1的等差数列求和，`1 + 2 + 3 + ... + n`
- 过程：
    - 求两组连续子序列个数，一组是小于等于R的，一组是小于等于L-1的，它们的差值就是题目要求的子序列个数
    - 初始化一个变量`cur`，用来记录等差数列的元素，同时作为每次遍历累加的值，如果当元素不符合要求，这个变量就重置为0
    - 遍历数组A，根据元素是否小于等于目标值，来判断是否`cur++`，同时累加到`ans`上，作为最终的答案
### 代码
```java
class Solution {
    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        return count(A, R) - count(A, L - 1);
    }
    
    private int count(int[] A, int target) {
        int cur = 0, ans = 0;
        for (int num : A) {
            cur = num <= target ? cur + 1 : 0;
            ans += cur;
        }
        return ans;
    }
}
```
# LeetCode_1052_爱生气的书店老板
## 题目
今天，书店老板有一家店打算试营业 customers.length 分钟。每分钟都有一些顾客（customers[i]）会进入书店，所有这些顾客都会在那一分钟结束后离开。

在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。 当书店老板生气时，那一分钟的顾客就会不满意，不生气则他们是满意的。

书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 X 分钟不生气，但却只能使用一次。

请你返回这一天营业下来，最多有多少客户能够感到满意的数量。

示例：
```
输入：customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
输出：16
解释：
书店老板在最后 3 分钟保持冷静。
感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.
```
提示：
```
1 <= X <= customers.length == grumpy.length <= 20000
0 <= customers[i] <= 1000
0 <= grumpy[i] <= 1
```
## 解法
### 思路
- 求`X`分钟内不满意顾客最多的时间窗口
- 嵌套循环：
    - 外层移动窗口的首个下标，且累加所有老板不生气时的顾客数量
    - 内层循环遍历所有窗口可能，累加所有可以从不满意变为满意的窗口总值作为临时值，比较与上个临时值之间的较大值，然后继续外层循环
- 最终返回外层的累加值和内层找到的最大临时值的和
### 代码
```java
class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int ans = 0, sum = 0;
        for (int i = 0; i < customers.length; i++) {
            ans += grumpy[i] == 0 ? customers[i] : 0;
            int tmp = 0;
            for (int time = 0; time < X && i + time < customers.length; time++) {
                tmp += grumpy[i + time] == 1 ? customers[i + time] : 0;
            }
            sum = Math.max(sum, tmp);
        }
        return ans + sum;
    }
}
```
## 解法二
### 思路
分析解法一可以看到，内层循环求的窗口值，主要基于窗口的第一个元素和最后一个元素以及上一个窗口的值，只要计算这三个值就可以求得新的窗口，避免重复计算过多的元素
### 代码
```java
class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int len = customers.length, ans = grumpy[0] == 0 ? customers[0] : 0, pre = 0, start = grumpy[0] == 1 ? customers[0] : 0;;
        for (int i = 0; i < X && i < len; i++) {
            pre += grumpy[i] == 1 ? customers[i] : 0;
        }
        int sum = pre;

        for (int i = 1; i < len; i++) {
            ans += grumpy[i] == 0 ? customers[i] : 0;
            if (i <= len - X) {
                int endIndex = i + X - 1;
                int end = grumpy[endIndex] == 1 ? customers[endIndex] : 0;
                int tmp = pre - start + end;
                sum = Math.max(sum, tmp);
                start = grumpy[i] == 1 ? customers[i] : 0;
                pre = tmp;
            }
        }
        return ans + sum;
    }
}
```