# Interview_1723_最大黑方阵
## 题目
给定一个方阵，其中每个单元(像素)非黑即白。设计一个算法，找出 4 条边皆为黑色像素的最大子方阵。

返回一个数组 [r, c, size] ，其中 r, c 分别代表子方阵左上角的行号和列号，size 是子方阵的边长。若有多个满足条件的子方阵，返回 r 最小的，若 r 相同，返回 c 最小的子方阵。若无满足条件的子方阵，返回空数组。

示例 1:
```
输入:
[
   [1,0,1],
   [0,0,1],
   [0,0,1]
]
输出: [1,0,2]
解释: 输入中 0 代表黑色，1 代表白色，标粗的元素即为满足条件的最大子方阵
```
示例 2:
```
输入:
[
   [0,1,1],
   [1,0,1],
   [1,1,0]
]
输出: [0,0,1]
```
提示：
```
matrix.length == matrix[0].length <= 200
```
## 解法
### 思路
dfs：
- 初始化变量max为0，用来存储最大边长
- 遍历二维数组，以遍历到的元素作为左上角开始判断
    - 每次在max基础上加1，如果所有四个边都符合，那么就更新max，并再加1
    - 如果不再符合，就终止，继续二维数组的遍历
### 代码
```java
class Solution {
    public int[] findSquare(int[][] matrix) {
        int max = 0, row = matrix.length, col = matrix[0].length;
        int[] ans = new int[3];
        Arrays.fill(ans, -1);
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (matrix[r][c] == 1) {
                    continue;
                }

                int len = max;
                boolean flag1 = true;
                while (r + len < row && c + len < col) {
                    boolean flag2 = true;
                    for (int i = r; i <= r + len; i++) {
                        if (matrix[i][c] != 0) {
                            flag1 = false;
                            break;
                        }
                    }

                    if (!flag1) {
                        break;
                    }

                    for (int i = c; i <= c + len; i++) {
                        if (matrix[r][i] != 0) {
                            flag1 = false;
                            break;
                        }
                    }

                    if (!flag1) {
                        break;
                    }

                    for (int i = c; i <= c + len; i++) {
                        if (matrix[r + len][i] != 0) {
                            len++;
                            flag2 = false;
                            break;
                        }
                    }

                    if (!flag2) {
                        continue;
                    }

                    for (int i = r; i <= r + len; i++) {
                        if (matrix[i][c + len] != 0) {
                            len++;
                            flag2 = false;
                            break;
                        }
                    }

                    if (!flag2) {
                        continue;
                    }

                    len++;
                    max = len;
                    ans[0] = r;
                    ans[1] = c;
                    ans[2] = max;
                }
            }
        }
        return ans[0] == -1 ? new int[0] : ans;
    }
}
```
# LeetCode_1139_最大的以1为边界的正方形
## 题目
给你一个由若干 0 和 1 组成的二维网格 grid，请你找出边界全部由 1 组成的最大 正方形 子网格，并返回该子网格中的元素数量。如果不存在，则返回 0。

示例 1：
```
输入：grid = [[1,1,1],[1,0,1],[1,1,1]]
输出：9
```
示例 2：
```
输入：grid = [[1,1,0,0]]
输出：1
```
提示：
```
1 <= grid.length <= 100
1 <= grid[0].length <= 100
grid[i][j] 为 0 或 1
```
## 解法
### 思路
类似面试题1723解法
### 代码
```java
class Solution {
    public int largest1BorderedSquare(int[][] grid) {
        int max = 0, row = grid.length, col = grid[0].length;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (grid[r][c] == 0) {
                    continue;
                }

                int len = max;
                boolean flag1 = true;
                while (r + len < row && c + len < col) {
                    boolean flag2 = true;
                    for (int i = r; i <= r + len; i++) {
                        if (grid[i][c] != 1) {
                            flag1 = false;
                            break;
                        }
                    }

                    if (!flag1) {
                        break;
                    }

                    for (int i = c; i <= c + len; i++) {
                        if (grid[r][i] != 1) {
                            flag1 = false;
                            break;
                        }
                    }

                    if (!flag1) {
                        break;
                    }

                    for (int i = c; i <= c + len; i++) {
                        if (grid[r + len][i] != 1) {
                            len++;
                            flag2 = false;
                            break;
                        }
                    }

                    if (!flag2) {
                        continue;
                    }

                    for (int i = r; i <= r + len; i++) {
                        if (grid[i][c + len] != 1) {
                            len++;
                            flag2 = false;
                            break;
                        }
                    }

                    if (!flag2) {
                        continue;
                    }

                    len++;
                    max = len;
                }
            }
        }
        return max * max;
    }
}
```
## 解法二
### 思路
动态规划：
- 定义：
    - `r[i][j]`：坐标[i,j]左侧连续1的个数
    - `c[i][j]`：组表[i,j]上方连续1的个数
- 状态转移方程：
    - `r[i][j] = r[i][j - 1] + 1`
    - `c[i][j] = c[i - 1][j] + 1`
- 初始化：matrix中值为1的坐标，r和c中初始化为1
- 过程：
    - 枚举二维数组matrix中所有为1的元素
    - 将r和c的当前值设置为1
    - 如果i > 0：`r[i][j] = r[i][j - 1] + 1`
    - 如果j > 0：`c[i][j] = c[i - 1][j] + 1`
    - 求min(r[i][j], c[i][j])，遍历这个长度，查看上方和左侧两条边是否也都为1，如果是，且大于暂存的max，则更新max
### 代码
```java
class Solution {
    public int largest1BorderedSquare(int[][] grid) {
        int row = grid.length, col = grid[0].length, ans = 0;
        int[][] r = new int[row][col],
                c = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] != 1) {
                    continue;
                }

                r[i][j] = 1;
                c[i][j] = 1;

                if (i > 0) {
                    r[i][j] += r[i - 1][j];
                }

                if (j > 0) {
                    c[i][j] += c[i][j - 1];
                }

                int max = Math.min(r[i][j], c[i][j]);
                for (int k = 0; k <= max; k++) {
                    if (k > ans && c[i - k + 1][j] >= k && r[i][j - k + 1] >= k) {
                        ans = k;
                    }
                }
            }
        }
        
        return ans * ans;
    }
}
```
# LeetCode_974_和可被K整除的子数组
## 题目
给定一个整数数组 A，返回其中元素之和可被 K 整除的（连续、非空）子数组的数目。

示例：
```
输入：A = [4,5,0,-2,-3,1], K = 5
输出：7
解释：
有 7 个子数组满足其元素之和可被 K = 5 整除：
[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
```
提示：
```
1 <= A.length <= 30000
-10000 <= A[i] <= 10000
2 <= K <= 10000
```
## 失败解法
### 失败原因
超时
### 思路
前缀和
- 初始化一个数组arr
- 计算原数组的前缀和，放入arr中，下标对应前缀子数组的结尾元素下标
- 嵌套遍历arr，计算是否有前缀和差值可以被K整除，做计数累加
### 代码
```java
class Solution {
    public int subarraysDivByK(int[] A, int K) {
        int sum = 0, len = A.length, ans = 0;
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (sum += A[i]);
        }
        
        for (int i = 0; i < len; i++) {
            int a = arr[i];
            if (a % K == 0) {
                ans++;
            }
            
            for (int j = i + 1; j < len; j++) {
                if ((arr[j] - a) % K == 0) {
                    ans++;
                }
            }
        }
        
        return ans;
    }
}
```
## 解法
### 思路
前缀和+散列表
- 和失败解法一样要求前缀和
- 但是只需要在一次循环中处理
- 因为是否能整除，在之前前缀和相减的基础上，其实也可以理解成，是否有前缀和取模K后的值和当前取模K后的值一致，如果有，那么相减的差就一定能被K整除
- 所以只要把取模后的值放入散列表中，并统计出现的个数，就能算出与当前前缀和相减后能获得整除的对数，将这个对数累加到结果中就可以了
- 要注意取模的时候java对被除数为负的情况，仍取负数，而类似-1和4这两个数取模5后，一个是-1一个是4，这两个数在算法中是不会被统计的，但是-1 - 4 = -5，其实是可以的，所以要使用Math.floorMod或者(num % K + K) % k的方式来求
### 代码
```java
class Solution {
    public int subarraysDivByK(int[] A, int K) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0, ans = 0;

        for (int num : A) {
            sum += num;
            int modules = (sum % K + K) % K;
            int same = map.getOrDefault(modules, 0);
            ans += same;
            map.put(modules, same + 1);
        }
        return ans;
    }
}
```
## 解法二
### 思路
- 先循环A数组，累加sum值，并求当前元素作为数组最后一个元素的取模K的值modulus
- 将modulus放入map中计数
- 遍历modulus值，使用排列组合来计算计数值能组成的子数组组合，累加后返回
### 代码
```java
class Solution {
    public int subarraysDivByK(int[] A, int K) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0;
        for (int num : A) {
            sum += num;
            int modulus = (sum % K + K) % K;
            map.put(modulus, map.getOrDefault(modulus, 0) + 1);
        }
        
        int ans = 0;
        for (int num : map.values()) {
            ans += num * (num - 1) / 2;
        }
        return ans;
    }
}
```
# Interview_1724_最大子矩阵
## 题目
给定一个正整数和负整数组成的 N × M 矩阵，编写代码找出元素总和最大的子矩阵。

返回一个数组 [r1, c1, r2, c2]，其中 r1, c1 分别代表子矩阵左上角的行号和列号，r2, c2 分别代表右下角的行号和列号。若有多个满足条件的子矩阵，返回任意一个均可。

示例:
```
输入:
[
   [-1,0],
   [0,-1]
]
输出: [0,1,0,1]
解释: 输入中标粗的元素即为输出所表示的矩阵
```
## 解法
### 思路
动态规划+降维压缩
- 动态规划求解一维数组的最大子数组
- 将一列的所有值累加，使矩阵降维到一维
- 三层循环：
    - 第一层确定矩阵起始行
    - 第二层确定当前遍历的行
    - 第三层遍历当前列：
        - 每一个新的起始行，都在每一层都累加当前列到一个数组中
        - 每到一个新行，就使用动态规划计算当前列为尾元素的子数组的最大值
        - 因为dp[i - 1]时如果是负数，一定导致nums[i]的值大于dp[i - 1] + nums[i]，所以在dp[i- 1]为负数时就刷新起始左上角坐标
        - 如果累加的值大于暂存的最大值，就更新结果坐标
### 代码
```java
class Solution {
    public int[] getMaxMatrix(int[][] matrix) {
        int rowLen = matrix.length, colLen = matrix[0].length,
            max = Integer.MIN_VALUE, r = 0, c = 0;

        int[] ans = new int[4];

        for (int startRow = 0; startRow < rowLen; startRow++) {
            int[] colSum = new int[colLen];
            for (int row = startRow; row < rowLen; row++) {
                int sum = 0;
                for (int col = 0; col < colLen; col++) {
                    colSum[col] += matrix[row][col];
                    if (sum > 0) {
                        sum += colSum[col];
                    } else {
                        sum = colSum[col];
                        r = startRow;
                        c = col;
                    }

                    if (sum > max) {
                        max = sum;
                        ans[0] = r;
                        ans[1] = c;
                        ans[2] = row;
                        ans[3] = col;
                    }
                }
            }
        }

        return ans;
    }
}
```