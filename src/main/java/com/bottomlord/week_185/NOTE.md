# [LeetCode_1815_得到新鲜甜甜圈的最多组数](https://leetcode.cn/problems/maximum-number-of-groups-getting-fresh-donuts/)
## 解法
### 思路
- 根据题意，其实不需要关心顾客的个数num，只要算出num%m的余数即可，又因为batchSize大小不超过9，所以可以用一个数组来记录余数mod出现的个数
- 有了各个余数出现的个数，就可以通过状态转移方程来求最值了，但因为如果用dp记事本来记录状态的话，需要至多9维的数组，这会导致代码非常难以编写，所以需要考虑是否能够做状态压缩
- 因为顾客的批次数不超过30，所以每个余数的个数cnt不会超过30，也就可以使用一个5位的二进制数来记录，而9组5位的二进制数只需要45位，所以一个64位的long是可以记录下状态的
- 因为mod为0的情况不需要做额外的计算，把这些顾客组排列在序列的前列，就一定能保证他们是快乐的
- 那么状态转移方程又该怎么思考呢？
  - 可以发现，如果将所有的mod值累加起来得到S，它模m的值与当前要判断的最后一组的mod值是同余的，那么这最后一组就是快乐的
  - 也就是说，当前这组成员是否快乐，取决于(之前所有顾客的总数+当前顾客总数)取模后是否能与当前顾客总数取模同余
  - 而(之前所有顾客的总数+当前顾客总数)可以通过status值来获取
  - 同过dfs的方式，从下往上的进行处理，边界条件就是status=0，也就是所有mod值的出现个数都是0，此时也就没有快乐的组，返回0
  - 每一层都会基于下一层返回的值，以及当前层是否同余，来进行是否+1的判断和计算，然后遍历所有mod的可能，判断所有路径上得到当前层状态的最大值
### 代码
```java
class Solution {
  private int width = 5, rangeMask = (1 << width) - 1;

  public int maxHappyGroups(int batchSize, int[] groups) {
    int[] counts = new int[batchSize];
    for (int group : groups) {
      counts[group % batchSize]++;
    }

    long status = 0;
    for (int i = batchSize - 1; i >= 1; i--) {
      status = (status << width) | counts[i];
    }

    return dfs(status, new HashMap<>(), batchSize) + counts[0];
  }

  private int dfs(long status, Map<Long, Integer> map, int batchSize) {
    if (status == 0) {
      return 0;
    }

    if (map.containsKey(status)) {
      return map.get(status);
    }

    long cur = 0;
    for (int i = 1; i < batchSize; i++) {
      cur += i * getAmount(status, i);
    }

    int best = 0;
    for (int i = 1; i < batchSize; i++) {
      long amount = getAmount(status, i);
      if (amount == 0) {
        continue;
      }

      int result = dfs(status - (1L << (i - 1) * width), map, batchSize);
      if ((cur - i) % batchSize == 0) {
        result++;
      }

      best = Math.max(result, best);
    }

    map.put(status, best);
    return best;
  }

  private long getAmount(long status, int modIndex) {
    return (status >> (modIndex - 1) * width) & rangeMask;
  }
}
```
# [LeetCode_1828_统计一个圆中点的数目](https://leetcode.cn/problems/queries-on-number-of-points-inside-a-circle/)
## 解法
### 思路
暴力
- 遍历queries，然后计算所有点与圆心的距离，看是否小于等于r，如果是就计数累加
- 遍历结束，返回结果
### 代码
```java
class Solution {
    public int[] countPoints(int[][] points, int[][] queries) {
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int[] center = new int[]{query[0], query[1]};
            int r = query[2];

            for (int[] point : points) {
                if (distance(point, center) <= r * r) {
                    ans[i]++;
                }
            }
        }

        return ans;
    }

    private int distance(int[] x, int[] y) {
        return (x[0] - y[0]) * (x[0] - y[0]) + (x[1] - y[1]) * (x[1] - y[1]);
    }
}
```
# [LeetCode_1632_矩阵转换后的秩](https://leetcode.cn/problems/rank-transform-of-a-matrix/)
## 解法
### 思路
暴力
### 代码
```java

```
# [LeetCode_1663_具有给定数值的最小字符串](https://leetcode.cn/problems/smallest-string-with-a-given-numeric-value/)
## 失败解法
### 原因
超时
### 思路
模拟
### 代码
```java
class Solution {
  public String getSmallestString(int n, int k) {
    StringBuilder sb = new StringBuilder();
    while (k > 0) {
      if (k == n) {
        for (int i = 0; i < n; i++) {
          sb.insert(0, "a");
        }

        return sb.toString();
      }

      n--;
      int cur = k - n;

      if (cur > 26) {
        k -= 26;
        sb.insert(0, "z");
      } else {
        k -= cur;
        sb.insert(0, (char)('a' + cur - 1));
      }
    }

    return sb.toString();
  }
}
```
## 解法二
### 思路
- 观察可得，答案字符串一定只包含a和z以及至多一个其他字母
- 所以可以通过n和k得到如下内容
  - 先初始化一个char数组，用a填充，相当于k - n
  - 然后算出z的个数：(k - n) / 25，除25是因为不知道z有多少个，当假设全部是1的时候，剩下的n需要使用26来填充，又因为每位都已经填充了1，所以就是26-1
  - 然后计算出至多1个的其他字母：(k - n) % 25，道理相同，如果z个数计算的过程中无法整除，也就说明有一个1需要变成更大的其他字母才能最终相加得到k
- 最后通过z和其他字母将初始化的char数组更新即可
### 代码
```java
class Solution {
  public String getSmallestString(int n, int k) {
    char[] cs = new char[n];
    Arrays.fill(cs, 'a');
    int z = (k - n) / 25;
    int notAorZ = (k - n) % 25;
    if (n - z - 1 >= 0) {
      cs[n - z - 1] += notAorZ;
    }

    for (int i = n - 1; i >= n - z; i--) {
      cs[i] = 'z';
    }

    return new String(cs);
  }
}
```