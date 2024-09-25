# LeetCode_1039_多边形三角剖分的最低得分
## 题目
给定 N，想象一个凸 N 边多边形，其顶点按顺时针顺序依次标记为 A[0], A[i], ..., A[N-1]。

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
给定正整数数组 A，A[i] 表示第 i 个观光景点的评分，并且两个景点 i 和 j 之间的距离为 j - i。

一对景点（i < j）组成的观光组合的得分为（A[i] + A[j] + i - j）：景点的评分之和减去它们两者之间的距离。

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
我们将给定的数组 A 分成 K 个相邻的非空子数组 ，我们的分数由每个子数组内的平均值的总和构成。计算我们所能得到的最大分数是多少。

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
答案误差在 10^-6 内被视为是正确的。
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
给定一个元素都是正整数的数组A ，正整数 L 以及 R (L <= R)。

求连续、非空且其中最大元素满足大于等于L 小于等于R的子数组个数。

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
L, R  和 A[i] 都是整数，范围在 [0, 10^9]。
数组 A 的长度范围在[1, 50000]。
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
今天，书店老板有一家店打算试营业 customers.length 分钟。每分钟都有一些顾客（customers[i]）会进入书店，所有这些顾客都会在那一分钟结束后离开。

在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。 当书店老板生气时，那一分钟的顾客就会不满意，不生气则他们是满意的。

书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 X 分钟不生气，但却只能使用一次。

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
1 <= X <= customers.length == grumpy.length <= 20000
0 <= customers[i] <= 1000
0 <= grumpy[i] <= 1
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
# LeetCode_764_最大加号标志
## 题目
在一个大小在 (0, 0) 到 (N-1, N-1) 的2D网格 grid 中，除了在 mines 中给出的单元为 0，其他每个单元都是 1。网格中包含 1 的最大的轴对齐加号标志是多少阶？返回加号标志的阶数。如果未找到加号标志，则返回 0。

一个 k" 阶由 1 组成的“轴对称”加号标志具有中心网格  grid[x][y] = 1 ，以及4个从中心向上、向下、向左、向右延伸，长度为 k-1，由 1 组成的臂。下面给出 k" 阶“轴对称”加号标志的示例。注意，只有加号标志的所有网格要求为 1，别的网格可能为 0 也可能为 1。

k 阶轴对称加号标志示例:
```
阶 1:
000
010
000

阶 2:
00000
00100
01110
00100
00000

阶 3:
0000000
0001000
0001000
0111110
0001000
0001000
0000000
```
示例 1：
```
输入: N = 5, mines = [[4, 2]]
输出: 2
解释:

11111
11111
11111
11111
11011

在上面的网格中，最大加号标志的阶只能是2。一个标志已在图中标出。
```
示例 2：
```
输入: N = 2, mines = []
输出: 1
解释:

11
11

没有 2 阶加号标志，有 1 阶加号标志。
```
示例 3：
```
输入: N = 1, mines = [[0, 0]]
输出: 0
解释:

0

没有加号标志，返回 0 。
```
提示：
```
整数N 的范围： [1, 500].
mines 的最大长度为 5000.
mines[i] 是长度为2的由2个 [0, N-1] 中的数组成.
(另外,使用 C, C++, 或者 C# 编程将以稍小的时间限制进行​​判断.)
```
## 失败解法
### 失败原因
超出时间限制
### 思路
暴力：
- 生成`mine`的set集合，用于快速的判断
- 三层循环：
    - 外部两层用来确定二维数组中的加号中点的x和y轴
    - 内部一层用来判断这个中点最大能构成多大的加号，记录这个值，与暂存值之间取较大值
        - 使用变量k来记录加号的臂展，通过set和N来判断当前臂展k是否符合题意
- 返回暂存的最大值
### 代码
```java
class Solution {
    public int orderOfLargestPlusSign(int N, int[][] mines) {
        Set<Integer> set = new HashSet<>();
        for (int[] mine : mines) {
            set.add(mine[0] * N + mine[1]);
        }
        
        int ans = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int k = 0;
                while (k <= r && k + r < N && k <= c && k + c < N &&
                        !set.contains((r - k) * N + c) &&
                        !set.contains((r + k) * N + c) &&
                        !set.contains(r * N + c - k) &&
                        !set.contains(r * N + c + k)) {
                    k++;
                }
                ans = Math.max(ans, k);
            }
        }
        return ans;
    }
}
```
## 解法
### 思路
失败算法中，确定中点后遍历的所有臂展的过程可以被优化
- 通过确定四个方向的有效臂展的最小值，可以确定当前中点的臂展值
- 从二维数组的角度，如果某一行是`0111011`，那么它这个方向上每个点的臂展值可以表示为`0123012`
- 所以就计算每一行每一列四个方向的臂展累加值，然后求当前点上的最小值
- 当所有四个方向的值都计算完并在每个点上都确定了四个方向的最小值，就求二维数组中元素的最大值作为答案返回
### 代码
```java
class Solution {
    public int orderOfLargestPlusSign(int N, int[][] mines) {
        Set<Integer> set = new HashSet<>();
        for (int[] mine : mines) {
            set.add(mine[0] * N + mine[1]);
        }
        int ans = 0, count;
        int[][] dp = new int[N][N];

        for (int r = 0; r < N; r++) {
            count = 0;
            for (int c = 0; c < N; c++) {
                count = set.contains(r * N + c) ? 0 : count + 1;
                dp[r][c] = count;
            }

            count = 0;
            for (int c = N - 1; c >= 0; c--) {
                count = set.contains(r * N + c) ? 0 : count + 1;
                dp[r][c] = Math.min(dp[r][c], count);
            }
        }

        for (int c = 0; c < N; c++) {
            count = 0;
            for (int r = 0; r < N; r++) {
                count = set.contains(r * N + c) ? 0 : count + 1;
                dp[r][c] = Math.min(dp[r][c], count);
            }

            count = 0;
            for (int r = N - 1; r >= 0; r--) {
                count = set.contains(r * N + c) ? 0 : count + 1;
                dp[r][c] = Math.min(dp[r][c], count);
                ans = Math.max(ans, dp[r][c]);
            }
        }
        return ans;
    }
}
```
## 优化代码
### 思路
因为这是一个行列数相等的矩阵，可以将四个方向的臂展值的过程合成在一个嵌套循环中
### 代码
```java
class Solution {
    public int orderOfLargestPlusSign(int N, int[][] mines) {
        int[][] dp = new int[N][N];
        for (int[] arr : dp) {
            Arrays.fill(arr, N);
        }

        for (int[] mine : mines) {
            dp[mine[0]][mine[1]] = 0;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0, k = N - 1, l = 0, r = 0, u = 0, d = 0; j < N; j++, k--) {
                dp[i][j] = Math.min(dp[i][j], l = dp[i][j] == 0 ? 0 : l + 1);
                dp[i][k] = Math.min(dp[i][k], r = dp[i][k] == 0 ? 0 : r + 1);
                dp[j][i] = Math.min(dp[j][i], u = dp[j][i] == 0 ? 0 : u + 1);
                dp[k][i] = Math.min(dp[k][i], d = dp[k][i] == 0 ? 0 : d + 1);
            }
        }
        
        int ans = 0;
        for (int[] arr : dp) {
            for (int num : arr) {
                ans = Math.max(ans, num);
            }
        }
        
        return ans;
    }
}
```
# LeetCode_6_Z字形变换
## 题目
将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。

比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
```
L   C   I   R
E T O E S I I G
E   D   H   N
```
之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。

请你实现这个将字符串进行指定行数变换的函数：
```
string convert(string s, int numRows);
```
示例 1:
```
输入: s = "LEETCODEISHIRING", numRows = 3
输出: "LCIRETOESIIGEDHN"
```
示例 2:
```
输入: s = "LEETCODEISHIRING", numRows = 4
输出: "LDREOEIIECIHNTSG"
```
解释:
```
L     D     R
E   O E   I I
E C   I H   N
T     S     G
```
## 解法
### 思路
- 使用list<StringBuilder>来定义z字形二维图形
- 使用`min(s.len, numRows)`来确定list的大小
- 遍历字符串：
    - 将遍历到的字符放到list中的StringBuilder中
    - 使用临时变量`down`来确定下一个字符是放在哪一行的StringBuilder中
- 字符串遍历结束后，遍历list，将StringBuilder拼接就获得了题目要的字符串
### 代码
```java
class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        List<StringBuilder> list = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++) {
            list.add(new StringBuilder());
        }

        boolean down = false;
        int row = 0;
        for (char c : s.toCharArray()) {
            list.get(row).append(c);
            if (row == 0 || row == numRows - 1) {
                down = !down;
            }
            row = down ? row + 1 : row - 1;
        }
        
        return String.join("", list);
    }
}
```
## 解法二
### 思路
根据Z字形切分，字符串中字符在每一行的分布：
- 第一行：字符串之间间隔`2numRows - 2`
- 最后一行：字符串之间间隔`2numRows - 2 + numRows - 1`
- 中间行：字符串之间间隔为`(k + 1)(2numRows - 2) - rows`
### 代码
```java
class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        
        StringBuilder ans = new StringBuilder();
        int len = s.length(), cycle = 2 * numRows - 2;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; i + j < len; j += cycle) {
                ans.append(s.charAt(i + j));
                if (i != 0 && i != numRows - 1 && j + cycle - i < len) {
                    ans.append(s.charAt(j + cycle - i));
                }
            }
        }
        return ans.toString();
    }
}
```
# LeetCode_1004_最大连续1的个数III
## 题目
给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。

返回仅包含 1 的最长（连续）子数组的长度。

示例 1：
```
输入：A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
输出：6
解释： 
[1,1,1,0,0,1,1,1,1,1,1]
粗体数字从 0 翻转到 1，最长的子数组长度为 6。
```
示例 2：
```
输入：A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
输出：10
解释：
[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
粗体数字从 0 翻转到 1，最长的子数组长度为 10。
```
提示：
```
1 <= A.length <= 20000
0 <= K <= A.length
A[i] 为 0 或 1 
```
## 解法
### 思路
移动窗口：
- 初始化变量：
    - `start`：代表窗口起始坐标，初始为0
    - `end`：代表窗口右侧结束坐标，初始为0
    - `count`：计算为0的字符数
    - `ans`：代表窗口大小的较大暂存值，初始为0
    - `len`：代表数组大小，初始为数组长度
- 嵌套循环：
    - 外层循环：
        - 退出条件是`end >= len`
        - 外层中计算`count`
        - 在完成内层循环后，还要计算当前的窗口大小和暂存之进行比较，同时尝试增大`end`
    - 内层循环：
        - 退出条件：`count <= k`
        - 内层移动`start`直到`count <= k`为止
- 最终返回`ans`
### 代码
```java
class Solution {
    public int longestOnes(int[] A, int K) {
        int start = 0, end = 0, count = 0, len = A.length, ans = 0;
        while (end < len) {
            count += A[end] == 0 ? 1 : 0;
            while (count > K) {
                count -= A[start] == 0 ? 1 : 0;
                start++;
            }
            ans = Math.max(ans, end - start + 1);
            end++;
        }
        return ans;
    }
}
```
# LeetCode_947_移除最多的同行或同列石头
## 题目
在二维平面上，我们将石头放置在一些整数坐标点上。每个坐标点上最多只能有一块石头。

现在，move 操作将会移除与网格上的某一块石头共享一列或一行的一块石头。

我们最多能执行多少次 move 操作？

示例 1：
```
输入：stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
输出：5
```
示例 2：
```
输入：stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
输出：3
```
示例 3：
```
输入：stones = [[0,0]]
输出：0
```
提示：
```
1 <= stones.length <= 1000
0 <= stones[i][j] < 10000
```
## 解法
### 思路
并查集：
- 同一行同一列最终只能留下1个节点，所以能够移除的最多石头就是`len(stone的总个数) - left(剩下的石头)`
- 可以遍历`stones`，将所有`stone`的所有节点放入并查集，每个节点的x轴和y轴也进行合并，y轴+10000放入并查集
- 最后再遍历`stones`，并查集中的所有根节点个数，代入`len - left`中求到结果
### 代码
```java
class Solution {
    public int removeStones(int[][] stones) {
        int len = stones.length;
        DSU dsu = new DSU(20000);
        for (int[] stone : stones) {
            dsu.union(stone[0], stone[1] + 10000);
        }

        Set<Integer> set = new HashSet<>();
        for (int[] stone : stones) {
            set.add(dsu.find(stone[0]));
        }

        return len - set.size();
    }
}

class DSU {
    public int[] parent;

    public DSU(int N) {
        parent = new int[N];
        for (int i = 0; i < N; ++i) {
            parent[i] = i;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x, int y) {
        parent[find(x)] = find(y);
    }
}
```
## 解法二
### 思路
- 将所有能够被移除后剩下的`stone`节点看作是n棵树的根节点
- 遍历所有`stone`节点，通过dfs递归搜索当前节点的所有联通节点，同时将它们与根节点同时标记为已搜索
- 遍历结束后，统计根节点数量，并与`stones`的个数相减就得到了题目要求的数量
### 代码
```java
class Solution {
    public int removeStones(int[][] stones) {
        int count = 0, len = stones.length;
        boolean[] visited = new boolean[len];
        
        for (int i = 0; i < len; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(visited, stones[i][0], stones[i][1], stones);
                count++;
            }
        }
        
        return len - count;
    }
    
    private void dfs(boolean[] visited, int x, int y, int[][] stones) {
        for (int i = 0; i < stones.length; i++) {
            if ((stones[i][0] == x || stones[i][1] == y) && !visited[i]) {
                visited[i] = true;
                dfs(visited, stones[i][0], stones[i][1], stones);
            }
        }
    }
}
```
# LeetCode_958_二叉树的完全性验证
## 题目
给定一个二叉树，确定它是否是一个完全二叉树。

百度百科中对完全二叉树的定义如下：

若设二叉树的深度为 h，除第 h 层外，其它各层 (1～h-1) 的结点数都达到最大个数，第 h 层所有的结点都连续集中在最左边，这就是完全二叉树。（注：第 h 层可能包含 1~ 2h 个节点。）

示例 1：
```
输入：[1,2,3,4,5,6]
输出：true
解释：最后一层前的每一层都是满的（即，结点值为 {1} 和 {2,3} 的两层），且最后一层中的所有结点（{4,5,6}）都尽可能地向左。
```
示例 2：
```
输入：[1,2,3,4,5,null,7]
输出：false
解释：值为 7 的结点没有尽可能靠向左侧。
```
提示：
```
树中将会有 1 到 100 个结点。
```
## 解法
### 思路
bfs：
- 完全二叉树每一层的节点如果依次编号，那么会发现完全二叉树的最后一个节点的编号和所有二叉树的个数相等
- 所以遍历整个二叉树并编号后，如果最后一个节点值和个数相等就是完全二叉树，否则就不是
### 代码
```java
class Solution {
    public boolean isCompleteTree(TreeNode root) {
        List<Anode> list = new ArrayList<>();
        list.add(new Anode(root, 1));
        int i = 0;
        while (i < list.size()) {
            Anode anode = list.get(i++);

            if (anode.node != null) {
                list.add(new Anode(anode.node.left, anode.val * 2));
                list.add(new Anode(anode.node.right, anode.val * 2 + 1));
            }
        }
        
        return list.get(i - 1).val == list.size();
    }
}

class Anode {
    TreeNode node;
    int val;
    public Anode(TreeNode node, int val) {
        this.node = node;
        this.val = val;
    }
}
```
# LeetCode_863_二叉树中所有距离为K的结点
## 题目
给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 K 。

返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。

示例 1：
```
输入：root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2

输出：[7,4,1]

解释：
所求结点为与目标结点（值为 5）距离为 2 的结点，
值分别为 7，4，以及 1
```
```
注意，输入的 "root" 和 "target" 实际上是树上的结点。
上面的输入仅仅是对这些对象进行了序列化描述。
```
提示：
```
给定的树是非空的，且最多有 K 个结点。
树上的每个结点都具有唯一的值 0 <= node.val <= 500 。
目标结点 target 是树上的结点。
0 <= K <= 1000.
```
## 解法
### 思路
dfs + bfs：
- dfs：
    - 使用dfs将树中所有节点及其父节点的映射关系放在map中
    - 当拥有映射关系后就可以获得一个节点所有1距离的节点，从而可以从target节点出发找到与它距离为K的所有节点
- bfs：
    - 以target为根节点，出发寻找第K层的节点
    - 还需要一个set集合用来过滤已经遍历到的节点
    - 找到后存入list中返回
### 代码
```java
class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        Map<TreeNode, TreeNode> map = new HashMap<>();
        dfs(root, null, map);

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(target);
        
        Set<TreeNode> set = new HashSet<>();
        set.add(target);
        int level = 0;
        
        while (!queue.isEmpty()) {
            if (level == K) {
                List<Integer> list = new ArrayList<>();
                while (!queue.isEmpty()) {
                    list.add(queue.poll().val);
                }
                return list;
            }
            
            int count = queue.size();
            while (count-- > 0) {
                TreeNode node = queue.poll();
                
                if (node.left != null && !set.contains(node.left)) {
                    set.add(node.left);
                    queue.add(node.left);
                }

                if (node.right != null && !set.contains(node.right)) {
                    set.add(node.right);
                    queue.add(node.right);
                }

                if (map.get(node) != null && !set.contains(map.get(node))) {
                    set.add(map.get(node));
                    queue.add(map.get(node));
                }
            }
            
            level++;
        }
        
        return Collections.emptyList();
    }

    private void dfs(TreeNode node, TreeNode pre, Map<TreeNode, TreeNode> map) {
        if (node == null) {
            return;
        }

        map.put(node, pre);
        dfs(node.left, node, map);
        dfs(node.right, node, map);
    }
}
```
## 解法二
### 思路
嵌套dfs：
- 解法一中是先通过dfs找到所有节点的父节点，然后再通过bfs从`target`节点开始找到K层的所有节点
- 但起始可以直接通过dfs找到target节点
- 然后再从`target`节点开始进行dfs，找到`target`更深的`K`距离的节点
- 同时从`target`返回到上层节点，在上层节点获得距离`target`的距离`dist`后，再通过区分`left`和`right`，从`node`的相反方向dfs，找`K - dist`的节点，这些节点也是符合题目要求的
### 代码
```java
class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> ans = new ArrayList<>();
        if (K == 0) {
            ans.add(target.val);
        } else {
            dfs(root, target, K, ans);
        }
        return ans;
    }

    private int dfs(TreeNode node, TreeNode target, int k, List<Integer> list) {
        if (node == null) {
            return -1;
        }

        if (node == target) {
            dfs2(node.left, 1, k, list);
            dfs2(node.right, 1, k, list);
            return 0;
        }

        int left = dfs(node.left, target, k, list) + 1;
        int right = dfs(node.right, target, k, list) + 1;

        if (left > 0) {
            if (left == k) {
                list.add(node.val);
            }
            dfs2(node.right, left + 1, k, list);
            return left;
        } else if (right > 0) {
            if (right == k) {
                list.add(node.val);
            }
            dfs2(node.left, right + 1, k, list);
            return right;
        } else {
            return -1;
        }
    }

    private void dfs2(TreeNode node, int dist, int k, List<Integer> list) {
        if (node == null || dist > k) {
            return;
        }

        if (dist == k) {
            list.add(node.val);
        }
        dfs2(node.left, dist + 1, k, list);
        dfs2(node.right, dist + 1, k, list);
    }
}
```
# LeetCode_1291_顺次数
## 题目
我们定义「顺次数」为：每一位上的数字都比前一位上的数字大 1 的整数。

请你返回由 [low, high] 范围内所有顺次数组成的 有序 列表（从小到大排序）。

示例 1：
```
输出：low = 100, high = 300
输出：[123,234]
```
示例 2：
```
输出：low = 1000, high = 13000
输出：[1234,2345,3456,4567,5678,6789,12345]
```
提示：
```
10 <= low <= high <= 10^9
```
## 解法
### 思路
枚举：
- 嵌套循环：
    - 外层依次遍历9个数字`i`
    - 内层从`i + 1`开始遍历`j`，退出条件一样是`> 9`
    - 在内层计算`i *10 + j`，并判断是否在`(low, high)`范围内，如果在就放入list
- 最后将list排序后返回
### 代码
```java
class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            int num = i;
            for (int j = i + 1; j <= 9; j++) {
                num = num * 10 + j;
                if (num >= low && num <= high) {
                    ans.add(num);
                }
            }
        }

        Collections.sort(ans);
        return ans;
    }
}
```
# LeetCode_822_翻转卡片游戏
## 题目
在桌子上有 N 张卡片，每张卡片的正面和背面都写着一个正数（正面与背面上的数有可能不一样）。

我们可以先翻转任意张卡片，然后选择其中一张卡片。

如果选中的那张卡片背面的数字 X 与任意一张卡片的正面的数字都不同，那么这个数字是我们想要的数字。

哪个数是这些想要的数字中最小的数（找到这些数中的最小值）呢？如果没有一个数字符合要求的，输出 0。

其中, fronts[i] 和 backs[i] 分别代表第 i 张卡片的正面和背面的数字。

如果我们通过翻转卡片来交换正面与背面上的数，那么当初在正面的数就变成背面的数，背面的数就变成正面的数。

示例：
```
输入：fronts = [1,2,4,4,7], backs = [1,3,4,1,3]
输出：2
解释：假设我们翻转第二张卡片，那么在正面的数变成了 [1,3,4,4,7] ， 背面的数变成了 [1,2,4,1,3]。
接着我们选择第二张卡片，因为现在该卡片的背面的数是 2，2 与任意卡片上正面的数都不同，所以 2 就是我们想要的数字。
```
提示：
```
1 <= fronts.length == backs.length <= 1000
1 <= fronts[i] <= 2000
1 <= backs[i] <= 2000
```
## 解法
### 思路
- 如果某张卡片的正反面的数值一样，那么这个数就不能作为答案，剩下的数值中最小的值就是答案，因为翻若干次后，一定可以将这个最小值翻到背面并使正面没有这个值
- 过程：
    - 使用`set`统计正反面一样的值
    - 遍历两个数组，找到不在`set`中且最小的值
    - 如果没有这个值就返回0
### 代码
```java
class Solution {
    public int flipgame(int[] fronts, int[] backs) {
        int len = fronts.length, ans = Integer.MAX_VALUE;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < len; i++) {
            if (fronts[i] == backs[i]) {
                set.add(fronts[i]);
            }
        }
        
        for (int i = 0; i < len; i++) {
            int front = fronts[i];
            if (!set.contains(front)) {
                ans = Math.min(ans, front);
            }
            
            int back = backs[i];
            if (!set.contains(back)) {
                ans = Math.min(ans, back);
            }
        }
        
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
```
# LeetCode_1024_视频拼接
## 题目
你将会获得一系列视频片段，这些片段来自于一项持续时长为 T 秒的体育赛事。这些片段可能有所重叠，也可能长度不一。

视频片段 clips[i] 都用区间进行表示：开始于 clips[i][0] 并于 clips[i][1] 结束。我们甚至可以对这些片段自由地再剪辑，例如片段 [0, 7] 可以剪切成 [0, 1] + [1, 3] + [3, 7] 三部分。

我们需要将这些片段进行再剪辑，并将剪辑后的内容拼接成覆盖整个运动过程的片段（[0, T]）。返回所需片段的最小数目，如果无法完成该任务，则返回 -1 。

示例 1：
```
输入：clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], T = 10
输出：3
解释：
我们选中 [0,2], [8,10], [1,9] 这三个片段。
然后，按下面的方案重制比赛片段：
将 [1,9] 再剪辑为 [1,2] + [2,8] + [8,9] 。
现在我们手上有 [0,2] + [2,8] + [8,10]，而这些涵盖了整场比赛 [0, 10]。
```
示例 2：
```
输入：clips = [[0,1],[1,2]], T = 5
输出：-1
解释：
我们无法只用 [0,1] 和 [0,2] 覆盖 [0,5] 的整个过程。
```
示例 3：
```
输入：clips = [[0,1],[6,8],[0,2],[5,6],[0,4],[0,3],[6,7],[1,3],[4,7],[1,4],[2,5],[2,6],[3,4],[4,5],[5,7],[6,9]], T = 9
输出：3
解释： 
我们选取片段 [0,4], [4,7] 和 [6,9] 。
```
示例 4：
```
输入：clips = [[0,4],[2,8]], T = 5
输出：2
解释：
注意，你可能录制超过比赛结束时间的视频。
```
提示：
```
1 <= clips.length <= 100
0 <= clips[i][0], clips[i][1] <= 100
0 <= T <= 100
```
## 解法
### 思路
贪心算法：
- 根据二维数组第二个元素按降序排序整个二维数组
- 定义一个临界值`x`，这个临界值初始值为`T`
- 这个`x`用来找到结束值大于它的窗口，并确定这些窗口中最小的起始值，这样就能找到尽可能大的窗口
- 然后再将这个起始值作为下一个`x`来找到比上一个起始值大且当前起始值最小的窗口，以此类推，直到这个`x == 0`为止，这样就找到了尽可能少的能覆盖整个范围的窗口组合
- 过程：
    - 定义`x == T`
    - 排序二维数组`clips`
    - 循环遍历排序后的`clips`，退出条件是`T == 0`
    - 在循环过程中判断当前遍历到的一维数组的第二个元素是否大于`T`
        - 如果是：取出它的第一个元素和`x`做比较，找到起始值的最小值
        - 如果不是：判断`x < T`
            - 如果不是，说明整个二维数组中没有窗口的结束值大于等于T，或者没有起始为0的窗口，直接返回-1，代表不能找到窗口组合
            - 如果是：那么就`T == x`，这样代表第一个窗口已经确定，接下来在下个循环取找比这个窗口起始值大的窗口，同时计数`count`
- 返回`count`
### 代码
```java
class Solution {
    public int videoStitching(int[][] clips, int T) {
        Arrays.sort(clips, (x, y) -> y[1] - x[1]);
        int x = T, index = 0, len = clips.length, count = 0;

        while (T != 0) {
            if (index < len && clips[index][1] >= T) {
                x = Math.min(x, clips[index][0]);
                index++;
            } else {
                if (x < T) {
                    T = x;
                    count++;
                } else {
                    return -1;
                }
            }
        }
        return count;
    }
}
```
# LeetCode_846_一手顺子
## 题目
爱丽丝有一手（hand）由整数数组给定的牌。 

现在她想把牌重新排列成组，使得每个组的大小都是 W，且由 W 张连续的牌组成。

如果她可以完成分组就返回 true，否则返回 false。

示例 1：
```
输入：hand = [1,2,3,6,2,3,4,7,8], W = 3
输出：true
解释：爱丽丝的手牌可以被重新排列为 [1,2,3]，[2,3,4]，[6,7,8]。
```
示例 2：
```
输入：hand = [1,2,3,4,5], W = 4
输出：false
解释：爱丽丝的手牌无法被重新排列成几个大小为 4 的组。
```
提示：
```
1 <= hand.length <= 10000
0 <= hand[i] <= 10^9
1 <= W <= hand.length
```
## 解法
### 思路
暴力：
- 使用`TreeMap`统计牌号`num`与个数的映射关系
- 循环：
    - 退出条件：`TreeMap`是`size <= 0`
    - 过程：获得`TreeMap`的第一个key，然后从`TreeMap`中找之后W个值，如果没有就返回false，否则就将其的个数对应减1
### 代码
```java
class Solution {
    public boolean isNStraightHand(int[] hand, int W) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : hand) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        while (map.size() > 0) {
            int num = map.firstKey();
            for (int i = num; i < num + W; i++) {
                if (!map.containsKey(i)) {
                    return false;
                }
                int count = map.get(i);
                if (count == 1) {
                    map.remove(i);
                } else {
                    map.replace(i, count - 1);
                }
            }
        }
        
        return true;
    }
}
```
## 解法二
### 思路
- 如果`hand`的长度不能被`W`取余，那么就是false
- 因为是连续W个数，且能够分成W组，所以这组数必定可以通过取余获得W组`(0, W - 1)`的数
- 初始化一个长度为`W`的数组
- 遍历`hand`，将元素与`W`取余，并将值累加到数组中
- 遍历结束后，再遍历`W`，查看元素是否相等
    - 相等返回true
    - 不相等返回false
### 代码
```java
class Solution {
    public boolean isNStraightHand(int[] hand, int W) {
        if (hand.length % W != 0) {
            return false;
        }
        
        int[] arr = new int[W];
        for (int num : hand) {
            arr[num % W]++;
        }
        
        for (int i = 0; i < W - 1; i++) {
            if (arr[i] != arr[i + 1]) {
                return false;
            }
        }
        
        return true;
    }
}
```
# LeetCode_82_删除排序链表中的重复元素II
## 题目
给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。

示例 1:
```
输入: 1->2->3->3->4->4->5
输出: 1->2->5
```
示例 2:
```
输入: 1->1->1->2->3
输出: 2->3
```
## 解法
### 思路
- 使用两个指针：
    - `cur`：保存主要遍历链表的指针
    - `pre`：保存前置指针，当`cur`判断完是否值相等的逻辑后，通过这个指针来实现删除的具体逻辑
- 遍历链表：
    - 初始`pre`和`cur`都指向同一个节点
    - 然后移动`cur = cur.next`
    - 判断`cur`和`cur.next`的值是否相等
        - 如果相等，就暂存当前`val = cur.val`
        - 继续循环，判断`cur.next == val`，如果相等就`cur = cur.next`
        - 直到不相等，进入下一个循环，判断`112234`中的`11`遍历完后`22`的情况
    - 如果上述嵌套循环退出后，就将`pre.next = cur`
    - 然后继续最外层循环，直到链表遍历结束
### 代码
```java
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode start = new ListNode(0), pre, cur = start;
        start.next = head;
        while (cur != null) {
            pre = cur;
            cur = cur.next;
            while (cur != null && cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
                int val = cur.val;
                while (cur != null && cur.val == val) {
                    cur = cur.next;
                }
            }
            pre.next = cur;
        }
        return start.next;
    }
}
```
# LeetCode_93_复原IP地址
## 题目
给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。

示例:
```
输入: "25525511135"
输出: ["255.255.11.135", "255.255.111.35"]
```
## 解法
### 思路
暴力
- ip地址分成四个部分，所以使用四层for循环
- 因为ip地址每部分的值长度都是3，所以for循环的循环次数就是3，也因为要使用String的API，所以循环`1 <= i <= 3`
- 循环内部的判断条件，每部分的值范围`>= 0 && <= 255`
### 代码
```java
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                for (int k = 1; k <= 3; k++) {
                    for (int l = 1; l <= 3; l++) {
                        if (i + j + k + l != s.length()) {
                            continue;
                        }
                        
                        int a = Integer.parseInt(s.substring(0, i));
                        int b = Integer.parseInt(s.substring(i, i + j));
                        int c = Integer.parseInt(s.substring(i + j, i + j + k));
                        int d = Integer.parseInt(s.substring(i + j + k, i + j + k + l));

                        if (a <= 255 && b <= 255 && c <= 255 && d <= 255) {
                            StringBuilder ip = new StringBuilder();
                            ip.append(a).append(".").append(b).append(".").append(c).append(".").append(d);

                            if (ip.length() == s.length() + 3) {
                                ans.add(ip.toString());
                            }
                        }
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
回溯：
- 每次递归回溯过程中确定一个ip的点的位置
- 函数传入参数
    - `preDot`：上一个点的坐标
    - `dots`：还需确定的点的数量
    - `segments`：用来暂存ip4个部分的字符串list
    - `ans`：最终的答案list
- 递归过程：
    - 遍历字符串
    - 从`preDot + 1`开始遍历，确定下一个点的位置
    - 判断生成的ip段是否符合要求，如果符合就放入`segments`中
    - 判断`dots`是否已经生成完
        - 如果是就放入ans中
        - 如果不是就继续回溯
    - 回溯的时候将`segments`的最后一个ip段删除
### 代码
```java
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();
        LinkedList<String> segments = new LinkedList<>();
        backTrack(s, segments, ans, -1, 3);
        return ans;
    }

    private void backTrack(String s, LinkedList<String> segments, List<String> ans, int prePos, int dots) {
        int maxPos = Math.min(s.length() - 1, prePos + 4);
        for (int curPos = prePos + 1; curPos < maxPos; curPos++) {
            String segment = s.substring(prePos + 1, curPos + 1);
            if (isValid(segment)) {
                segments.add(segment);
                if (dots > 1) {
                    backTrack(s, segments, ans, curPos, dots - 1);
                } else {
                    output(s, curPos, segments, ans);
                }
                segments.removeLast();
            }
        }
    }

    private boolean isValid(String segment) {
        return segment.length() <= 3 && (segment.charAt(0) == '0' ? segment.length() == 1 : Integer.parseInt(segment) <= 255);
    }

    private void output(String s, int curPos, LinkedList<String> segments, List<String> ans) {
        String segment = s.substring(curPos + 1);
        if (isValid(segment)) {
            segments.add(segment);
            ans.add(String.join(".", segments));
            segments.removeLast();
        }
    }
}
```