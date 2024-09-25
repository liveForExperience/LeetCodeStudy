# [Contest_1_判断矩阵是一个X矩阵](https://leetcode.cn/problems/check-if-matrix-is-x-matrix/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public boolean checkXMatrix(int[][] grid) {
        int r = grid.length, c = grid[0].length;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (i == j || i + j == r - 1) {
                    if (grid[i][j] == 0) {
                        return false;
                    }
                } else {
                    if (grid[i][j] != 0) {
                        return false;
                    }
                }
            }
        }
        
        return true;
    }
}
```
# [Contest_2_统计放置房子的方法数](https://leetcode.cn/problems/count-number-of-ways-to-place-houses/)
## 解法
### 思路

### 代码
```java
class Solution {
    public int countHousePlacements(int n) {
        int mod = 1000000007;
        long[][] dp = new long[n][2];
        dp[0][0] = 1;
        dp[0][1] = 1;

        for (int i = 1; i < n; i++) {
            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1]) % mod;
            dp[i][1] = dp[i - 1][0] % mod;
        }
        
        long side = (dp[n - 1][0] + dp[n - 1][1]) % mod;

        return (int)((side * side) % mod);
    }
}
```
# [Contest_3_拼接数组的最大分数](https://leetcode.cn/problems/maximum-score-of-spliced-array/)
## 失败解法
### 原因
超时
### 思路
- 初始化2个数组的前缀和
- 将结果分割成左、中、右三部分
- 嵌套循环确定数组中的两个分割点坐标
- 使用前缀和数组确定3部分的值，并更新最大值
### 代码
```java
class Solution {
    public int maximumsSplicedArray(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] sums1 = new int[n + 1], sums2 = new int[n + 1];
        for (int i = 0; i < nums1.length; i++) {
            sums1[i + 1] = sums1[i] + nums1[i];
            sums2[i + 1] = sums2[i] + nums2[i];
        }
        
        int max = Math.max(sums1[n], sums2[n]);

        for (int i = 0; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                int left1 = sums1[i] - sums1[0];
                int mid2 = sums2[j] - sums2[i];
                int right1 = sums1[n] - sums1[j];

                int left2 = sums2[i] - sums2[0];
                int mid1 = sums1[j] - sums1[i];
                int right2 = sums2[n] - sums2[j];
                
                int curMax = Math.max(left1 + mid2 + right1, left2 + mid1 + right2);
                max = Math.max(curMax, max);
            }
        }
        
        return max;
    }

}
```
## 解法
### 思路
dp：
- 可以理解成计算连续子数组最大值
### 代码
```java
class Solution {
    public int maximumsSplicedArray(int[] nums1, int[] nums2) {
        return Math.max(solve(nums1, nums2), solve(nums2, nums1));
    }

    private int solve(int[] nums1, int[] nums2) {
        int n = nums1.length, sum = 0, max = 0;
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1] + (nums2[i - 1] - nums1[i - 1]), 0);
            max = Math.max(max, dp[i]);
            sum += nums1[i - 1];
        }
        return sum + max;
    }
}
```
# [Contest_4_从树中删除边的最小分数](https://leetcode.cn/problems/minimum-score-after-removals-on-a-tree/)
## 解法
### 思路
- 通过edge生成邻接表
- 利用邻接表dfs，计算出以坐标0为根节点，每个节点作为子树根节点的异或和
- 在dfs的同时，通过一个全局变量，记录dfs当前节点时候的时间，通过进入和出去的时间，来判断节点之间的关系
- 利用节点之间的关系，在计算异或和的时候可以分成3种情况
  - 3个连通分量的根节点，0、x、y，x是y的祖先节点，这个时候，x这个连通分量的异或和就是`xor[x] ^ xor[y]`
    - x的连通分量的异或和就是`xor[x] ^ xor[y]`
    - y：`xor[y]`
    - 0: `xor[0] ^ xor[x]`
  - 同理，当y是x的祖先节点时候，
    - y的连通分量的异或和就是`xor[y] ^ xor[x]`
    - x：`xor[x]`
    - 0: `xor[0] ^ xor[y]`
  - 如果不是如上两种情况，说明两个节点互相不在一个子树中
    - y的连通分量的异或和就是`xor[y]`
    - x：`xor[x]`
    - 0: `xor[0] ^ xor[y] ^ xor[x]`
- [参考题解](https://leetcode.cn/problems/minimum-score-after-removals-on-a-tree/solution/dfs-shi-jian-chuo-chu-li-shu-shang-wen-t-x1kk/)
### 代码
```java
class Solution {
    private int clock;
    private int[] in, out, xor, nums;
    private List<Integer>[] g;
    public int minimumScore(int[] nums, int[][] edges) {
        int n = nums.length;
        this.nums = nums;
        in = new int[n];
        out = new int[n];
        xor = new int[n];
        g = new ArrayList[n];

        Arrays.setAll(g, x -> new ArrayList<Integer>());
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1];
            g[x].add(y);
            g[y].add(x);
        }

        dfs(0, -1);
        int ans = Integer.MAX_VALUE;
        for (int x = 1; x < n; x++) {
            for (int y = x + 1; y < n; y++) {
                int a, b, c;
                if (in[x] < in[y] && in[y] <= out[x]) {
                    a = xor[y];
                    b = xor[x] ^ a;
                    c = xor[0] ^ xor[x];
                } else if (in[y] < in[x] && in[x] <= out[y]) {
                    a = xor[x];
                    b = xor[y] ^ a;
                    c = xor[0] ^ xor[y];
                } else {
                    a = xor[x];
                    b = xor[y];
                    c = xor[0] ^ a ^ b;
                }

                int cur = Math.max(a, Math.max(b, c)) - Math.min(a, Math.min(b, c));
                ans = Math.min(cur, ans);

                if (ans == 0) {
                    return 0;
                }
            }
        }

        return ans;
    }

    private void dfs(int x, int pre) {
        in[x] = clock++;
        List<Integer> next = g[x];
        xor[x] = nums[x];
        for (Integer y : next) {
            if (y != pre) {
                dfs(y, x);
                xor[x] ^= xor[y];
            }
        }
        out[x] = clock++;
    }
}
```