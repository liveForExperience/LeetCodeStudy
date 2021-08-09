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