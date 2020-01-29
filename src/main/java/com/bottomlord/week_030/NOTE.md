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
# LeetCode_764_最大加号标志
## 题目
在一个大小在 (0, 0) 到 (N-1, N-1) 的2D网格 grid 中，除了在 mines 中给出的单元为 0，其他每个单元都是 1。网格中包含 1 的最大的轴对齐加号标志是多少阶？返回加号标志的阶数。如果未找到加号标志，则返回 0。

一个 k" 阶由 1 组成的“轴对称”加号标志具有中心网格  grid[x][y] = 1 ，以及4个从中心向上、向下、向左、向右延伸，长度为 k-1，由 1 组成的臂。下面给出 k" 阶“轴对称”加号标志的示例。注意，只有加号标志的所有网格要求为 1，别的网格可能为 0 也可能为 1。

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
整数N 的范围： [1, 500].
mines 的最大长度为 5000.
mines[i] 是长度为2的由2个 [0, N-1] 中的数组成.
(另外,使用 C, C++, 或者 C# 编程将以稍小的时间限制进行​​判断.)
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
将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。

比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
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
示例 1:
```
输入: s = "LEETCODEISHIRING", numRows = 3
输出: "LCIRETOESIIGEDHN"
```
示例 2:
```
输入: s = "LEETCODEISHIRING", numRows = 4
输出: "LDREOEIIECIHNTSG"
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
给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。

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
A[i] 为 0 或 1 
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