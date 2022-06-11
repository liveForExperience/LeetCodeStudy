# [LeetCode_1905_统计子岛屿](https://leetcode.cn/problems/count-sub-islands/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_732_我的日程安排III](https://leetcode.cn/problems/my-calendar-iii/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_875_爱吃香蕉的珂珂](https://leetcode.cn/problems/koko-eating-bananas/)
## 解法
### 思路
- 通过观察可以发现，速度与时间的关系时线性的，所以求解速度过程中呈现单调性，可以通过二分查找来找到这个最小的速度
- 最小的速度一定是1，最大的速度，可以设置为最大的堆的香蕉个数
- 然后二分查找最小的，满足h小时内可以吃完香蕉的速度
### 代码
```java
class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int l = 1, r = 0;
        for (int pile : piles) {
            r = Math.max(pile, r);
        }

        int ans = 0;
        while (l <= r) {
            int mid = (r - l) / 2 + l;

            int time = getTime(piles, mid);

            if (time <= h) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return ans;
    }

    private int getTime(int[] piles, int speed) {
        int time = 0;
        for (int pile : piles) {
            time += (pile + speed - 1) / speed;
        }
        return time;
    }
}
```
# [LeetCode_730_统计不同回文子序列](https://leetcode.cn/problems/count-different-palindromic-subsequences/)
## 解法
### 思路
动态规划：
- dp[i][j]：坐标范围是i到j的子字符串，他的回文子序列个数
- 状态转移方程：
  - s[i] != s[j]：
    - `dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1]`
    - 既然两个字符不相等，那就把判断下临近的两个子字符串，然后去除掉重复的部分
  - s[i] == s[j]
    - 如果s[i + 1][j - 1]范围内不包含这个添加的字符：
      - dp[i][j] = 2 * dp[i + 1][j - 1] + 2
      - 新增加的外部的2个字符，和原来的回文子串，可以获得相同个数的回文子串，同时，新增加的2个字符自身还能增加2个回文子串
    - 如果s[i + 1][j - 1]范围内有1个添加的字符：
      - dp[i][j] = 2 * dp[i + 1][j - 1] + 1
      - 在上一种情况的基础上，减少一个额外回文子串的可能，比如外围是2个a，那么那个单独的a就需要去除，因为里面的子串中已经把这种可能加上了
    - 如果s[i + 1][j - 1]范围内2个或以上添加的字符：
        - dp[i][j] = 2 * dp[i + 1][j - 1] - dp[l + 1][r - 1]
        - 新增加的外部的2个字符，和原来的回文子串，可以获得相同个数的回文子串，但是因为里面有相同的2个或以上字符，那么他们和他们内部的子字符串能够组成的序列，就会和现在的字符组成的序列重复，所以这部分要删掉
        - 但是只要考虑遇到的最外层的那组就可以了，因为再内层的已经在之前的判断过程中被考虑掉了
### 代码
```java
class Solution {
    public int countPalindromicSubsequences(String s) {
        int n = s.length();
        int mod = 1000000007;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1;
                if (s.charAt(i) != s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1];
                } else {
                    int l = i + 1, r = j - 1;

                    while (l <= r && s.charAt(i) != s.charAt(l)) {
                        l++;
                    }

                    while (l <= r && s.charAt(i) != s.charAt(r)) {
                        r--;
                    }

                    if (l > r) {
                        dp[i][j] = 2 * dp[i + 1][j - 1] + 2;
                    } else if (l == r) {
                        dp[i][j] = 2 * dp[i + 1][j - 1] + 1;
                    } else {
                        dp[i][j] = 2 * dp[i + 1][j - 1] - dp[l + 1][r - 1];
                    }
                }

                dp[i][j] = dp[i][j] >= 0 ? dp[i][j] % mod : dp[i][j] + mod;
            }
        }

        return dp[0][n - 1];
    }
}
```
# [LeetCode_926_将字符串翻转到单调递增](https://leetcode.cn/problems/flip-string-to-monotone-increasing/)
## 解法
### 思路
动态规划：
- dp[i][2]：从0到i坐标位置的字符，为0的时候，需要翻转的最小此处，或者，为1时候需要翻转的最小个数
- 状态转移方程：
  - 为0的时候，前面必须是0：dp[i + 1][0] = dp[i][0] + (cs[i] == '0' ? 0 : 1);
  - 为1的时候，前面可以是0和1：dp[i + 1][1] = Math.min(dp[i][1], dp[i][0]) + (cs[i] == '1' ? 0 : 1);
- 结果：min(dp[n][0], dp[n][1])
### 代码
```java
class Solution {
  public int minFlipsMonoIncr(String s) {
    int n = s.length();
    char[] cs = s.toCharArray();
    int[][] dp = new int[n + 1][2];
    for (int i = 0; i < n; i++) {
      dp[i + 1][0] = dp[i][0] + (cs[i] == '0' ? 0 : 1);
      dp[i + 1][1] = Math.min(dp[i][1], dp[i][0]) + (cs[i] == '1' ? 0 : 1);
    }

    return Math.min(dp[n][0], dp[n][1]);
  }
}
```