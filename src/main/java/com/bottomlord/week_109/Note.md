# [LeetCode_313_超级丑数](https://leetcode-cn.com/problems/super-ugly-number/)
## 解法
### 思路
小顶堆
- 初始化
  - 初始化一个小顶堆queue，将最小的丑数1放入queue
  - 初始化一个set用于去重
- 遍历n次
  - 每次将queue的堆顶元素拿出作为当前第i个的最小超级丑数
  - 然后基于该丑数，依次乘以数组中的所有质因数，并判断是否在set中，如果不在就放入queue
  - 直到n次循环结束
- 返回din次获取到的queue的堆顶元素并返回
### 代码
```java
class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        Set<Long> set = new HashSet<>();
        set.add(1L);
        
        PriorityQueue<Long> queue = new PriorityQueue<>();
        queue.offer(1L);
        
        long ugly = 1;
        for (int i = 0; i < n; i++) {
            ugly = queue.poll();
            for (int num : primes) {
                long next = num * ugly;
                if (set.add(next)) {
                    queue.offer(next);
                }
            }
        }
        
        return (int)ugly;
    }
}
```
## 解法二
### 思路
dp+n指针：
- 使用dp数组，dp[i]表示第i个超级丑数
- dp数组初始化为n+1的长度，dp[1] = 1，代表第一个超级丑数是1
- 初始化一个指针数组
  - 每一个元素对应的坐标对应primes数组中对应坐标的元素
  - 值对应dp数组中对应坐标的元素，也就是当前指针值对应dp的坐标，该值代表当前质因数没有相乘过的最小丑数，也就是下一个丑数可能从当前指针对应的丑数与当前质因数的乘积中获得
### 代码
```java
class Solution {
  public int nthSuperUglyNumber(int n, int[] primes) {
    long[] dp = new long[n + 1];
    dp[1] = 1;

    int[] points = new int[primes.length];
    Arrays.fill(points, 1);

    for (int i = 2; i <= n; i++) {
      long min = Integer.MAX_VALUE;
      for (int j = 0; j < primes.length; j++) {
        min = Math.min(dp[points[j]] * primes[j], min);
      }
      dp[i] = min;

      for (int j = 0; j < primes.length; j++) {
        if (dp[i] == primes[j] * dp[points[j]]) {
          points[j]++;
        }
      }
    }

    return (int)dp[n];
  }
}
```
# [LeetCode_413_等差数列划分](https://leetcode-cn.com/problems/arithmetic-slices/)
## 解法
### 思路
模拟：嵌套循环判断
### 代码
```java
class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            int diff = nums[i + 1] - nums[i], 
                pre = nums[i + 1];
            for (int j = i + 2; j < nums.length; j++) {
                if (nums[j] - pre == diff) {
                    count++;
                    pre = nums[j];
                } else {
                    break;
                }
            }
        }
        
        return count;
    }
}
```
## 解法二
### 思路
动态规划：
- dp[i]：代表0到i区间的数组能够获取的等差数列总数
- base case：
  - 公差：diff = nums[1] - nums[0]
  - dp[0] = 0, dp[1] = 0, dp[2] = nums[2] - nums[1] == diff ? 1 : 0
- 状态转移方程：
  - nums[i] - nums[i - 1] == diff：dp[i] = dp[i - 1] + 1
  - nums[i] - nums[i - 1] != diff：dp[i] = 0, diff = nums[i] - nums[i - 1];
  - 如果当前值与前置的差与公差相等，那么就说明0到当前元素坐标所组成的数组中，一定能够组成和[0,i-1]区间一样个数的连续数组
  - 假设原来[0,i-1]这个区间的[j,i-1]是最长的连续等差数组，这个区间能组成n个连续等差数组，那么[j +1,i]等于右移1位，也就能同样获得n个数组，同时再加上[j,i]这个数组
  - 所以最后就是2 * n + 1个数组
- 结果：累加dp中所有元素的和
- 优化整个算法过程，累加值可以在循环时就处理，减少一个循环
- 需要注意判断nums长度不足3的情况，此时结果是0
### 代码
```java
class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return 0;
        }
        
        int diff = nums[1] - nums[0], sum = 0;
        int[] dp = new int[n];
        
        dp[0] = dp[1] = 0;
        for (int i = 2; i < n; i++) {
            if (nums[i] - nums[i - 1] == diff) {
                dp[i] = dp[i - 1] + 1;
            } else {
                diff = nums[i] - nums[i - 1];
            }
            sum += dp[i];
        }
        
        return sum;
    }
}
```
## 解法三
### 思路
基于解法二可以发现，状态转移时只依赖前一个数字的状态，可以用一个变量来代替dp数组
### 代码
```java
class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return 0;
        }
        
        int diff = nums[1] - nums[0], total = 0, ans = 0;
        for (int i = 2; i < n; i++) {
            if (nums[i] - nums[i - 1] == diff) {
                total++;
            } else {
                diff = nums[i] - nums[i - 1];
                total = 0;
            }
            
            ans += total;
        }
        
        return ans;
    }
}
```
# [LeetCode_446_等差数列划分II_子序列](https://leetcode-cn.com/problems/arithmetic-slices-ii-subsequence/)
## 解法
### 思路
动态规划+hash表
- dp[i][d]：结尾是nums[i]，公差是d的等差数列的个数
- 状态转移方程：dp[i] += dp[j] + 1
  - dp[j]代表i结尾的等差数列的个数，其实就是累加所有以小于i的元素为结尾的等差数列的和
  - 1是指增加的i到j的2个元素的数列，注意的一点是，两个数组成的数列不是等差数列，必须是3个，所以其实dp[j]的值才是nums[i]结尾的等差数列的一个有效值
  - 那既然那个1是无效的，为什么还要累加呢，因为这个1在当前i这里是无效的，因为2个，但对于i后面的元素，又会成为有效的了，所以要累加起来
- 最终返回的结果，是在状态转移过程中，累加的dp值，但这个dp值不包含最后一个元素为结尾的dp值集合
- 需要注意越界的情况，用long代替int
### 代码

```java
class Solution {
  public int numberOfArithmeticSlices(int[] nums) {
    int n = nums.length, ans = 0;
    Map<Long, Integer>[] dp = new Map[n + 1];
    for (int i = 0; i < dp.length; i++) {
      dp[i] = new HashMap<>();
    }

    for (int i = 1; i < n; i++) {
      for (int j = 0; j < i; j++) {
        long d = (long) nums[i] - nums[j];
        int jCount = dp[j].getOrDefault(d, 0);
        Map<Long, Integer> iMap = dp[i];
        ans += jCount;
        iMap.put(d, iMap.getOrDefault(d, 0) + jCount + 1);
      }
    }

    return ans;
  }
}
```
# [LeetCode_516_最长回文子序列](https://leetcode-cn.com/problems/longest-palindromic-subsequence/)
## 解法
### 思路
动态规划：
- dp[i][j]：坐标i和j之间形成子序列中回文串的最大长度
- 状态转移方程：
  - 如果s[i] == s[j]，那么dp[i][j] = dp[i + 1][j - 1] + 2
  - 如果s[i] != s[j]，那么dp[i][j] = max(dp[i + 1][j], dp[i][j - 1])
- base case：dp[i][i] == 1
### 代码
```java
class Solution {
  public int longestPalindromeSubseq(String s) {
    int n = s.length();
    int[][] dp = new int[n][n];

    for (int i = n - 1; i >= 0; i--) {
      dp[i][i] = 1;
      char x = s.charAt(i);
      for (int j = i + 1; j < n; j++) {
        if (s.charAt(j) == x) {
          dp[i][j] = dp[i + 1][j - 1] + 2;
        } else {
          dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
        }
      }
    }

    return dp[0][n - 1];
  }
}
```
# [LeetCode_233_数字1的个数](https://leetcode-cn.com/problems/number-of-digit-one/)
## 解法
### 思路
- 可以把计算过程分割成对每一位可能出现1的情况做枚举，然后将所有位的结果累加
- 每一个位上的结果的计算方式，将整个数分成3部分
  - high：从当前位之后开始的所有高位组成的值，例如1102，当前位是位是0，那么高位就是11
  - cur：当前位对应的值，如上例就是0
  - low：当前位之前的所有低位，如上例就是2
- 以1102为例：
  - 个位为1的情况有（0001，0011，0021，……，1101）共（110+1）*1种
  - 十位为1的情况有（0010，0011，0012，……0019，0110，0111，0112，……0119，1010，1011，1012，……1019）共11*10种
  - 百位为1的情况有（0100，0101，……0199，……1100，1101，1102）共1*100+2+1种
  - 同理千位为1的有102+1种
- 所以总结出来就是：
  - 当前值大于1的时候，(high + 1) * i(位数，个位就是1，十位就是10)
  - 当前值等于0的时候，high * i
  - 当前值等于1的时候，high * i + low + 1
### 代码
```java
class Solution {
    public int countDigitOne(int n) {
        int ans = 0;
        for (int i = 1; i <= n; i *= 10) {
            int high = n / (i * 10),
                low = n % i,
                cur = (n - high * i * 10 - low) / i;

            if (cur == 0) {
                ans += high * i;
            } else if (cur == 1) {
                ans += high * i + 1 + low;
            } else {
                ans += (high + 1) * i;
            }
        }

        return ans;
    }
}
```