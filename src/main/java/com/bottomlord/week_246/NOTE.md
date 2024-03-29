# [LeetCode_518_零钱兑换II](https://leetcode.cn/problems/coin-change-ii)
## 解法
### 思路
动态规划：
- 涂格子
- `dp[i]`: 总金额为i的情况下的所有可能数
- 状态转义方程：`dp[i] += dp[i - coin]`，当前的金额是以某种硬币金额的选择累加出来的
- base case：`dp[0] = 1`，金额为0的可能数是1，也即什么硬币都不选
- 算法过程：
  - 创建数组`dp[amount + 1]`
  - 初始化数组：`dp[0] = 1`
  - 2层循环
    - 第一层遍历所有硬币
    - 第二层通过硬币的金额一层层的涂格子，第二层的遍历区间是`[coin, amount]`
  - 遍历结束后，返回`dp[amount]`即可
### 代码
```java
class Solution {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }
}
```
# [LeetCode_2580_统计将重叠区间合并成组的方案数](https://leetcode.cn/problems/count-ways-to-group-overlapping-ranges)
## 解法
### 思路
- 将有交集的区间进行合并，最终合并成一组没有交集的区间集合
- 合并的方式
  - 先对数组进行排序，排序规则是对区间起始值进行升序排序
  - 遍历排序后的数组
    - 内层先将当前元素的结尾变量初始化为变量`end`
    - 然后从当前元素的后一个开始遍历，遍历时判断当前元素是否小于等于end，如果是，说明有交集，然后更新`end`值为两者之间的最大值，然后继续遍历判断，直到越界或者不符合
- 得到区间总个数后，因为每个区间都可以放在任意一个组中，所以如果有`m`个区间，那么组合数就是`2 ^ m`
  - 那么实际在遍历合并区间的时候，可以同时暂存记录到的区间个数`m`和组合数`ans`，然后通过`ans = ans * 2`的方式等价替换掉`2 ^ "(m + 1)`的公式，同时通过取模保证数值不越界，得到答案`ans`
- 最终返回`ans`即可
### 代码
```java
class Solution {
    public int countWays(int[][] ranges) {
        int ans = 1, mod = 1000000007, n = ranges.length;
        Arrays.sort(ranges, Comparator.comparingInt(x -> x[0]));
        for (int i = 0; i < ranges.length;) {
            int end = ranges[i][1];
            while (i < n && ranges[i][0] <= end) {
                end = Math.max(end, ranges[i][1]);
                i++;
            }
            
            ans = ans * 2 % mod;
        }
        
        return ans;
    }
}
```
# [LeetCode_2642_设计可以求最短路径的图类](https://leetcode.cn/problems/design-graph-with-shortest-path-calculator)
## 解法
### 思路

### 代码
```java

```