# [LeetCode_7_整数反转](https://leetcode-cn.com/problems/reverse-integer/)
## 解法
### 思路
- 因为题目不允许用long，所以在判断int是否溢出的时候，就需要提前做判断，也就是在做进位的时候就判断是否已经超过的最大值/10或者小于了最小值*10
- 计算过程就是原数不断/10，缩小的过程中驱动获取每一个低位的数字，将这个数字放在反转后的数字的头部，过程就是rev * 10 + digit
- 负数取模还是负数，所以不用处理负数的情况
### 代码
```java
class Solution {
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            if (rev < Integer.MIN_VALUE / 10 || rev > Integer.MAX_VALUE / 10) {
                return 0;
            }

            int digit = x % 10;
            x /= 10;
            rev = rev * 10 + digit;
        }

        return rev;
    }
}
```
# [LeetCode_562_矩阵中最长的连续1线段](https://leetcode-cn.com/problems/longest-line-of-consecutive-one-in-matrix/)
## 解法
### 思路
dfs
### 代码
```java
class Solution {
    public int longestLine(int[][] mat) {
        int ans = 0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 1) {
                    ans = Math.max(ans, dfs(mat, i, j, 1, 0));
                    ans = Math.max(ans, dfs(mat, i, j, 0, 1));
                    ans = Math.max(ans, dfs(mat, i, j, 1, 1));
                    ans = Math.max(ans, dfs(mat, i, j, 1, -1));
                }
            }
        }
        return ans;
    }

    private int dfs(int[][] mat, int r, int c, int dx, int dy) {
        if (outOfBound(mat, r, c)) {
            return 0;
        }

        return dfs(mat, r + dx, c + dy, dx, dy) + 1;
    }

    private boolean outOfBound(int[][] mat, int r, int c) {
        int row = mat.length, col = mat[0].length;
        return r < 0 || r >= row || c < 0 || c >= col || mat[r][c] == 0;
    }
}
```
# [LeetCode_1473_粉刷房子III](https://leetcode-cn.com/problems/paint-house-iii/submissions/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_740_删除并获得点数](https://leetcode-cn.com/problems/delete-and-earn/)
## 解法
### 思路
- 如果找到一个数值，就应该持续的累加相同的数，这样就能最小化被删除的不能几点数的数子，就可以做到最大化。所以可以通过一个数组将某一个数的点数进行累加并存储
- 然后使用动态规划来计算
    - dp[i]：0到i的范围内，能够获得的最大点数
    - 状态转移方程：`dp[i] = max(dp[i - 2] + sums[i], dp[i-1])`
    - 因为状态转移的时候只考虑前后3个数的关系，所以，可以使用2个变量来记录状态转移的过程，这样就省下了空间
    - 返回`dp[max]`，max是nums中的最大值
### 代码
```java
class Solution {
    public int deleteAndEarn(int[] nums) {
        int max = Arrays.stream(nums).max().orElse(0);
        int[] sums = new int[max + 1];
        for (int num : nums) {
            sums[num] += num;
        }
        
        int one = sums[0], two = sums[1];
        for (int i = 2; i < sums.length; i++) {
            int tmp = two;
            two = Math.max(one + sums[i], two);
            one = tmp;
        }
        
        return two;
    }
}
```
# [LeetCode_1720_解码异或后的数组](https://leetcode-cn.com/problems/decode-xored-array/)
## 解法
### 思路
- 根据encoded生成结果数组nums
- 初始化`nums[0] = first`
- 遍历nums数组，从给的first元素开始，依次和encode的对应元素进行异或，还原出原始元素
- 遍历结束后返回
### 代码
```java
class Solution {
    public int[] decode(int[] encoded, int first) {
        int len = encoded.length;
        int[] nums = new int[len + 1];
        nums[0] = first;
        for (int i = 1; i < len + 1; i++) {
            nums[i] = encoded[i - 1] ^ nums[i - 1];
        }
        return nums;
    }
}
```