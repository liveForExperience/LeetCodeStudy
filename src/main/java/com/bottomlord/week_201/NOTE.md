# [LeetCode_1335_工作计划的最低难度](https://leetcode.cn/problems/minimum-difficulty-of-a-job-schedule/)
## 解法
### 思路
- 特殊情况，当工作量少于d的时候，不符合题目要求，直接返回-1
- 初始化变量n，代表任务数。
- dp[i][j]：第i天完成第j个工作的最小难度值
- 状态转移方程：dp[i][j] = min(dp[i][j], max + dp[i - 1][k - 1]);
  - k∈[i, j]
  - max：k到j区间内的最大值
- 初始化dp[1][j]（j∈[1,n]）
  - 遍历获取区间内的最大值并赋值给对应的dp[1][j]，值的比较区间是[1, j]
- 3层循环
  - 第一层遍历天数
  - 第二层遍历从天数开始的任务数
  - 第三层从任务数开始倒序遍历，确定区间内的最大值，内部则进行状态转移方程的计算
- 最终返回dp[d][n]
### 代码
```java
class Solution {
    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (n < d) {
            return -1;
        }

        int[][] dp = new int[d + 1][n + 1];
        for (int[] arr : dp) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, jobDifficulty[i]);
            dp[1][i + 1] = max;
        }

        for (int i = 2; i <= d; i++) {
            for (int j = i; j <= n; j++) {
                max = 0;
                for (int k = j; k >= i; k--) {
                    max = Math.max(max, jobDifficulty[k - 1]);
                    dp[i][j] = Math.min(dp[i][j], max + dp[i - 1][k - 1]);
                }
            }
        }

        return dp[d][n];
    }
}
```
## 解法二
### 思路
状态压缩
### 代码
```java
class Solution {
    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (n < d) {
            return -1;
        }

        int[] pre = new int[n + 1];
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, jobDifficulty[i]);
            pre[i + 1] = max;
        }

        for (int i = 2; i <= d; i++) {
            int[] cur = new int[n + 1];
            Arrays.fill(cur, Integer.MAX_VALUE);
            for (int j = i; j <= n; j++) {
                max = 0;
                for (int k = j; k >= i; k--) {
                    max = Math.max(max, jobDifficulty[k - 1]);
                    cur[j] = Math.min(cur[j], max + pre[k - 1]);
                }
            }
            pre = cur;
        }

        return pre[n];
    }
}
```
# [LeetCode_2682_找出转圈游戏输家](https://leetcode.cn/problems/find-the-losers-of-the-circular-game/)
## 解法
### 思路
记事本加模拟
### 代码
```java
class Solution {
    public int[] circularGameLosers(int n, int k) {
        boolean[] memo = new boolean[n];
        int index = 0;
        for (int i = 0;; i++) {
            if (memo[index]) {
                break;
            }
            memo[index] = true;
            index = (index + (i + 1) * k) % n;
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < memo.length; i++) {
            if (memo[i]) {
                continue;
            }
            
            list.add(i + 1);
        }
        
        int[] ans = new int[list.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }
}
```
# [LeetCode_1073_负二进制数相加](https://leetcode.cn/problems/adding-two-negabinary-numbers/)
## 解法
### 思路
模拟
- 初始化一个进位值carry，用于存储进位
- 分情况讨论，x代表当前位上计算后的值（arr1元素和arr2元素还有carry的值相加）
  - 0/1：当前位就是x，carry是0
  - 2/3：当前位是x-2，carry是-1
  - -1：当前位是1，carry是1
- 从2个数组的尾部开始遍历模拟，得到的数组再翻转后，作为结果返回
- 需要注意空数组或者只有一个元素0的情况
### 代码
```java
class Solution {
    public int[] addNegabinary(int[] arr1, int[] arr2) {
        int carry = 0;
        int a = arr1.length - 1, b = arr2.length - 1;
        List<Integer> list = new ArrayList<>();
        while (a >= 0 || b >= 0) {
            if (a < 0) {
                int x = carry + arr2[b];
                list.add(getCur(x));
                carry = getCarry(x);
                b--;
                continue;
            }

            if (b < 0) {
                int x = carry + arr1[a];
                list.add(getCur(x));
                carry = getCarry(x);
                a--;
                continue;
            }

            int x = arr1[a] + arr2[b] + carry;
            list.add(getCur(x));
            carry = getCarry(x);
            a--;
            b--;
        }

        if (carry == 1) {
            list.add(1);
        }

        if (carry == -1) {
            list.add(1);
            list.add(1);
        }

        int n = list.size();
        if (n == 0) {
            return new int[]{0};
        }
        
        int[] ans = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            ans[n - i - 1] = list.get(i);
        }

        int i = 0;
        for (; i < ans.length; i++) {
            if (ans[i] == 1) {
                break;
            }
        }

        ans = Arrays.copyOfRange(ans, i, n);
        if (ans.length == 0) {
            return new int[]{0};
        }

        return ans;
    }

    private int getCarry(int x) {
        if (x == 0 || x == 1) {
            return 0;
        }

        if (x == 2 || x == 3) {
            return -1;
        }
        
        return 1;
    }
    
    private int getCur(int x) {
        if (x == 0 || x == 1) {
            return x;
        }

        if (x == 2 || x == 3) {
            return x - 2;
        }

        return 1;
    }
}
```