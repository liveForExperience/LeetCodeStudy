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