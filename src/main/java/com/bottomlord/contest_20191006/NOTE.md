# Contest_1_玩筹码
## 题目
数轴上放置了一些筹码，每个筹码的位置存在数组 chips 当中。

你可以对 任何筹码 执行下面两种操作之一（不限操作次数，0 次也可以）：
```
将第 i 个筹码向左或者右移动 2 个单位，代价为 0。
将第 i 个筹码向左或者右移动 1 个单位，代价为 1。
最开始的时候，同一位置上也可能放着两个或者更多的筹码。
```
返回将所有筹码移动到同一位置（任意位置）上所需要的最小代价。

示例 1：
```
输入：chips = [1,2,3]
输出：1
解释：第二个筹码移动到位置三的代价是 1，第一个筹码移动到位置三的代价是 0，总代价为 1。
```
示例 2：
```
输入：chips = [2,2,2,3,3]
输出：2
解释：第四和第五个筹码移动到位置二的代价都是 1，所以最小总代价为 2。
```
提示：
```
1 <= chips.length <= 100
1 <= chips[i] <= 10^9
```
## 解法
### 思路
- 奇数位到奇数位的开销是0，到偶数位的开销是1
- 偶数位到偶数位的开销是0，到奇数位的开销是1
- 计算最小开销就是计算奇数和偶数的个数的最小值`Math.min(odd,even)`
### 代码
```java
class Solution {
    public int minCostToMoveChips(int[] chips) {
        int[] arr = new int[2];
        
        for (int num : chips) {
            arr[num & 1]++;
        }
        
        return Math.min(arr[0], arr[1]);
    }
}
```
# Contest_2_最长定差子序列
## 题目
给你一个整数数组 arr 和一个整数 difference，请你找出 arr 中所有相邻元素之间的差等于给定 difference 的等差子序列，并返回其中最长的等差子序列的长度。

示例 1：
```
输入：arr = [1,2,3,4], difference = 1
输出：4
解释：最长的等差子序列是 [1,2,3,4]。
```
示例 2：
```
输入：arr = [1,3,5,7], difference = 1
输出：1
解释：最长的等差子序列是任意单个元素。
```
示例 3：
```
输入：arr = [1,5,7,8,5,3,4,2,1], difference = -2
输出：4
解释：最长的等差子序列是 [7,5,3,1]。
```
提示：
```
1 <= arr.length <= 10^5
-10^4 <= arr[i], difference <= 10^4
```
## 解法
### 思路
遍历数组，并使用map来记录遍历到的元素的步数
- 先通过`difference`来计算当前元素的上一个元素是否在map中有，有的话当前的步数就是`上一个元素存储的步数 + 当前的1步`
- 再到map中查找当前元素是否存在
    - 如果不存在，就把当前步数存入
    - 如果存在，就和存在值比较，存入两者的最大值
### 代码
```java
class Solution {
    public int longestSubsequence(int[] arr, int difference) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        
        for (int num : arr) {
            int pre = num - difference, cur = 1 + map.getOrDefault(pre, 0);
            
            if (!map.containsKey(num)) {
                map.put(num, cur);
            } else {
                map.put(num, Math.max(cur, map.getOrDefault(num, 0)));
            }
            
            ans = Math.max(cur, ans);
        }
        
        return ans;
    }
}
```
# Contest_3_黄金矿工
## 题目
你要开发一座金矿，地质勘测学家已经探明了这座金矿中的资源分布，并用大小为 m * n 的网格 grid 进行了标注。每个单元格中的整数就表示这一单元格中的黄金数量；如果该单元格是空的，那么就是 0。

为了使收益最大化，矿工需要按以下规则来开采黄金：
```
每当矿工进入一个单元，就会收集该单元格中的所有黄金。
矿工每次可以从当前位置向上下左右四个方向走。
每个单元格只能被开采（进入）一次。
不得开采（进入）黄金数目为 0 的单元格。
矿工可以从网格中 任意一个 有黄金的单元格出发或者是停止。
```
示例 1：
```
输入：grid = [[0,6,0],[5,8,7],[0,9,0]]
输出：24
解释：
[[0,6,0],
 [5,8,7],
 [0,9,0]]
一种收集最多黄金的路线是：9 -> 8 -> 7。
```
示例 2：
```
输入：grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
输出：28
解释：
[[1,0,7],
 [2,0,6],
 [3,4,5],
 [0,3,0],
 [9,0,20]]
一种收集最多黄金的路线是：1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7。
```
提示：
```
1 <= grid.length, grid[i].length <= 15
0 <= grid[i][j] <= 100
最多 25 个单元格中有黄金。
```
## 解法
### 思路
有点像数岛屿的sink方法，但是需要注意的是因为起始位置是未定的，所以数组需要进行二维数组的深度拷贝，否则会导致计算到被sink(置为0)的元素
- 遍历二维数组
- 以遍历到的元素下标位置为起点，开始递归遍历
    - 遍历的退出条件是超出数组边界或当前元素为0
    - 否则就累加计数值，并和最大值进行比较，存储两者的最大值
    - 继续四个方向的递归
- 遍历结束，返回最大值
### 代码
```java
class Solution {
    private int max = 0;
    public int getMaximumGold(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int[][] copy = new int[grid.length][grid[i].length];
                for (int k = 0; k < grid.length; k++) {
                    copy[k] = Arrays.copyOf(grid[k], grid[k].length);
                }
                recurse(copy, i, j, 0);
            }
        }

        return max;
    }

    private void recurse(int[][] grid, int r, int c, int count) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[r].length || grid[r][c] == 0) {
            return;
        }

        count += grid[r][c];
        grid[r][c] = 0;
        max = Math.max(max, count);

        int[][] copy1 = new int[grid.length][grid[r].length];
        for (int k = 0; k < grid.length; k++) {
            copy1[k] = Arrays.copyOf(grid[k], grid[k].length);
        }
        recurse(copy1, r - 1, c, count);
        
        int[][] copy2 = new int[grid.length][grid[r].length];
        for (int k = 0; k < grid.length; k++) {
            copy2[k] = Arrays.copyOf(grid[k], grid[k].length);
        }
        recurse(copy2, r, c + 1, count);
        
        int[][] copy3 = new int[grid.length][grid[r].length];
        for (int k = 0; k < grid.length; k++) {
            copy3[k] = Arrays.copyOf(grid[k], grid[k].length);
        }
        recurse(copy3, r + 1, c, count);
        
        int[][] copy4 = new int[grid.length][grid[r].length];
        for (int k = 0; k < grid.length; k++) {
            copy4[k] = Arrays.copyOf(grid[k], grid[k].length);
        }
        recurse(copy4, r, c - 1, count);
    }
}
```
# Contest_4_统计元音字母序列的数目
## 题目
给你一个整数 n，请你帮忙统计一下我们可以按下述规则形成多少个长度为 n 的字符串：
```
字符串中的每个字符都应当是小写元音字母（'a', 'e', 'i', 'o', 'u'）
每个元音 'a' 后面都只能跟着 'e'
每个元音 'e' 后面只能跟着 'a' 或者是 'i'
每个元音 'i' 后面 不能 再跟着另一个 'i'
每个元音 'o' 后面只能跟着 'i' 或者是 'u'
每个元音 'u' 后面只能跟着 'a'
```
由于答案可能会很大，所以请你返回 模 10^9 + 7 之后的结果。

示例 1：
```
输入：n = 1
输出：5
解释：所有可能的字符串分别是："a", "e", "i" , "o" 和 "u"。
```
示例 2：
```
输入：n = 2
输出：10
解释：所有可能的字符串分别是："ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou" 和 "ua"。
```
示例 3：
```
输入：n = 5
输出：68
```
提示：
```
1 <= n <= 2 * 10^4
```
## 解法
### 思路
- 形成的总的字符串个数依赖于`a`、`e`、`i`、`o`、`u`在最后一层出现的个数
- 使用动态规划
    - 状态转移方程就相当于：
        - `a(n) = e(n - 1) + i(n - 1) + u(n - 1)`
        - `e(n) = a(n - 1) + i(n - 1)`
        - `i(n) = e(n - 1) + o(n - 1)`
        - `o(n) = i(n - 1)`
        - `u(n) = i(n - 1) + o(n - 1)`
    - base case：
        - `a(1) = 1`
        - `e(1) = 1`
        - `i(1) = 1`
        - `o(1) = 1`
        - `u(1) = 1`
### 代码
```java
class Solution {
    public int countVowelPermutation(int n) {
        long a = 1, e = 1, i = 1, o = 1, u = 1, mod = (long)1e9+7;
        for (int j = 1; j < n; j++) {
            long a1 = (e + i + u) % mod;
            long e1 = (a + i) % mod;
            long i1 = (e + o) % mod;
            long o1 = i % mod;
            long u1 = (i + o) % mod;
            
            a = a1; e = e1; i = i1; o = o1; u = u1;
        }
        
        return (int)((a + e + i + o + u) % mod);
    }
}
```