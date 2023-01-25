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