# [LeetCode_749_隔离病毒](https://leetcode.cn/problems/contain-virus/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_940_不同子序列](https://leetcode.cn/problems/distinct-subsequences-ii/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_775_全局倒置与局部倒置](https://leetcode.cn/problems/global-and-local-inversions/)
## 解法
### 思路
- 全局倒置包含局部倒置
- 如果需要全局倒置=局部倒置个数，那么就需要关注全局倒置的非局部倒置个数，也就是不相邻的倒序是否存在，如果存在，那么这种情况也是一种全局倒置，这就导致和题目要求不符合
- 初始化一个max变量，赋值为arr[0]，然后从第3个元素开始遍历数组，每次都比较不包含前一个数组的区间中的最大值，如果比该最大值小，那么就符合非局部倒置的全局倒置情况，返回false
- 否则找不到的话，就返回true
### 代码
```java
class Solution {
    public boolean isIdealPermutation(int[] nums) {
        int n = nums.length, max = nums[0];
        for (int i = 2; i < n; i++) {
            if (nums[i] < max) {
                return false;
            }

            max = Math.max(max, nums[i - 1]);
        }
        return true;
    }
}
```
# [LeetCode_792_匹配子序列的单词数](https://leetcode.cn/problems/number-of-matching-subsequences/)
## 解法
### 思路
二分查找：
- 使用一个长度26的list数组，存储26个字母在字符串s中所在的坐标，坐标以升序排序
- 然后遍历words数组，在遍历其字符串元素，根据二分查找，在list数组中拿到当前元素坐标对应的字母在list中是否有比当前遍历到的s字符坐标更大的坐标
- 如果没有，word就不是子序列
### 代码
```java
class Solution {
        public int numMatchingSubseq(String s, String[] words) {
        List[] lists = new ArrayList[26];
        for (int i = 0; i < lists.length; i++) {
            lists[i] = new ArrayList();
        }

        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            char c = cs[i];
            lists[c - 'a'].add(i);
        }

        int count = 0;
        for (String word : words) {
            int si = -1;
            boolean flag = true;
            char[] wcs = word.toCharArray();
            for (char c : wcs) {
                si = binarySearch(lists[c - 'a'], si);
                if (si == -1) {
                    flag = false;
                    break;
                }
            }
            
            if (flag) {
                count++;
            }
        }

        return count;
    }

    private static int binarySearch(List<Integer> list, int target) {
        int head = 0, tail = list.size();
        while (head < tail) {
            int mid = head + (tail - head) / 2;
            int num = list.get(mid);
            if (num <= target) {
                head = mid + 1;
            } else {
                tail = mid;
            }
        }

        if (head >= list.size()) {
            return -1;
        }

        return list.get(head);
    }
}
```
# [LeetCode_891_子序列宽度之和](https://leetcode.cn/problems/sum-of-subsequence-widths/)
## 解法
### 思路
- 题目只关注最大最小值，所以其实是否是子序列是不关注的
- 如果将数组进行排序
  - 对于之前的数字来说，这个数字就是最大值
  - 对于之后的数字来说，这个数字就是最小值
- 那么通过计算数字与之前的数字长度的2的整数次幂，或者是和之后的数字之间的整数次幂，就能得到所有子序列的个数
- 然后通过这些子序列的个数与当前值的乘积，就可以得到当前这个值作为最大值或者最小值的结果
- 是最大值就在结果中累加，是最小值就在结果中累减
- 因为序列的个数需要通过2的整数次幂来计算，而整数次幂的最大值是根据数组长度n来确定的，所以可以预先计算出0到n的2的整数次幂来
- 另外在mod的时候，因为之前计算中可能会得到负数，所以需要在最后是用`(count % mod + mod) % mod`的方式来计算（没懂为什么）
### 代码
```java
class Solution {
    public int sumSubseqWidths(int[] nums) {
        int mod = 1000000007, n = nums.length;
        Arrays.sort(nums);
        long count = 0;
        long[] pows = new long[n];
        pows[0] = 1;
        for (int i = 1; i < n; i++) {
            pows[i] = pows[i - 1] * 2 % mod;
        }

        for (int i = 0; i < nums.length; i++) {
            count += ((pows[i] - pows[n - 1 - i]) * nums[i]) % mod;
        }

        return (int) (count % mod + mod) % mod;
    }
}
```
# [LeetCode_799_香槟塔](https://leetcode.cn/problems/champagne-tower/)
## 解法
### 思路
动态规划：
- dp[i][j]：代表x为i，y为j的坐标的流量
- 状态转移方程： 
  - dp[i + 1][j] = (dp[i][j] - 1) / 2
  - dp[i + 1][j + 1] = (dp[i][j] - 1) / 2
  - 如果就被不大于1，不可能留给下一层的杯子
  - 而如果大于了1，那么留给下一层的就是减去1的值
  - 并且会留给左右两边的杯子，所以流下去的值就是`(dp[i][j] - 1) / 2`
### 代码
```java
class Solution {
    public double champagneTower(int p, int x, int y) {
        double[][] dp = new double[x + 2][x + 2];
        dp[0][0] = p;
        
        for (int i = 0; i <= x; i++) {
            for (int j = 0; j <= i; j++) {
                if (dp[i][j] <= 1) {
                    continue;
                }
                
                dp[i + 1][j] += (dp[i][j] - 1) / 2;
                dp[i + 1][j + 1] += (dp[i][j] - 1) / 2;
            }
        }
        
        return Math.min(dp[x][y], 1);
    }
}
```
# [LeetCode_808_分汤](https://leetcode.cn/problems/soup-servings/)
## 解法
### 思路
动态规划：
- dp[i][j]：i和j代表ab各自剩余的汤的毫升数对应的概率结果
- base case：
  - i == 0 && j > 0，概率是1 + 0，因为a已经分配完了，b不能继续分配
  - i == 0 && j == 0，概率是0 + 0.5，因为ab都分配完了
  - i > 0 && j == 0，概率是0 + 0，因为b分配完了，a不能再分配
- 状态转移方程：dp[i][j] = 0.25 * (dp[i - 4][j] + dp[i - 3][j - 1] + dp[i - 2][j - 2] + dp[i - 1][j - 3])
- 结果：dp[n][n]
### 代码
```java
class Solution {
    public double soupServings(int n) {
        n = Math.min(200, (int)Math.ceil(n / 25.0));
        double[][] dp = new double[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            dp[0][i] = 1;
        }

        dp[0][0] = 0.5;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                double a = dp[Math.max(0, i - 4)][j],
                    b = dp[Math.max(0, i - 3)][Math.max(0, j - 1)],
                    c = dp[Math.max(0, i - 2)][Math.max(0, j - 2)],
                    d = dp[Math.max(0, i - 1)][Math.max(0, j - 3)];
                dp[i][j] = 0.25 * (a + b + c + d);
            }
        }

        return dp[n][n];
    }
}
```
## 解法二
### 思路
记忆化+dfs
### 代码
```java
class Solution {
    public double soupServings(int n) {
        n = Math.min(200, (int) Math.ceil(n / 25.0));
        double[][] memo = new double[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                memo[i][j] = -1;
            }
        }
        return dfs(n, n, memo);
    }

    private double dfs(int a, int b, double[][] memo) {
        if (a == 0 && b > 0) {
            return 1;
        }

        if (a == 0 && b == 0) {
            return 0.5;
        }

        if (a > 0 && b == 0) {
            return 0;
        }

        if (memo[a][b] != -1) {
            return memo[a][b];
        }

        memo[a][b] = 0.25 * (
                dfs(Math.max(0, a - 4), b, memo) +
                dfs(Math.max(0, a - 3), Math.max(b - 1, 0), memo) +
                dfs(Math.max(0, a - 2), Math.max(b - 2, 0), memo) +
                dfs(Math.max(0, a - 1), Math.max(b - 3, 0), memo)
        );

        return memo[a][b];
    }
}
```
# [LeetCode_878_第N个神奇数字](https://leetcode.cn/problems/nth-magical-number/)
## 解法
### 思路
二分查找
- 题目要找的是第n个a或b的倍数
- 因为这个序列一定是有序的，所以可以通过二分查找去找
- 序列的区间
  - 最小值：min(a,b)
  - 最大值：n * min(a,b)
- 这个值对应的个数可以通过这个公式求得
  - cnt = x / a + x / b - x / c
  - c是a和b的最小公倍数
  - x/a代表能被a整除的小于等于x的个数
  - x/b代表能被b整除的小于等于x的个数
  - x/c代表当同时能被a和b整除的时候，重复计数的个数
### 代码
```java
class Solution {
    public int nthMagicalNumber(int n, int a, int b) {
        long l = Math.min(a, b), r = (long)n * Math.min(a, b), 
        mod = 1000000007L, c = lcm(a, b);
        while (l <= r) {
            long mid = l + (r - l) / 2;
            long count = mid / a + mid / b - mid / c;
            if (count >= n) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return (int)((r + 1) % mod);
    }

    private int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```