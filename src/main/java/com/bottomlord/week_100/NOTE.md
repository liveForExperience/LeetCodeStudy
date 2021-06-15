# [LeetCode_494_目标和](https://leetcode-cn.com/problems/target-sum/)
## 解法
### 思路
dp：
- `dp[i][j]`：代表有i个元素的情况下获取到j总和的可能个数
  - 因为j可能是负数，所以需要在原来元素的基础上增加1000（1000是因为题目的要求范围是1000）
- 初始化状态：
    - 如果第一个元素是0：`dp[0][1000] = 2`，首先j == 1000代表的是值为0的情况，另外因为第一个元素是0，所以无论正负都能得到0，所以是2
    - 如果不是：
        - dp[0][1000 - nums[i]] = 1
        - dp[0][1000 + nums[i]] = 1
- 状态转移方程：
    - dp[i][j] = dp[i - 1][j - nums[i] + 1000] + dp[i - 1][j + nums[i] + 1000]
- 最终结果返回：dp[len - 1][target + 1000]
- 剪枝：如果所有元素的和不超过target，则直接返回0，说明不能达到题目的要求
- 修正：因为要剪枝，求得了所有元素的正数和，所以j的范围也就找到了，所以可以用这个sum来替换1000
### 代码
```java
class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        int len = nums.length, sum = Arrays.stream(nums).sum();
        if (sum < S) {
            return 0;
        }

        int[][] dp = new int[len][2 * sum + 1];
        if (nums[0] == 0) {
            dp[0][sum] = 2;
        } else {
            dp[0][-nums[0] + sum] = 1;
            dp[0][nums[0] + sum] = 1;
        }

        for (int i = 1; i < len; i++) {
            for (int j = -sum; j <= sum; j++) {
                int l = j - nums[i] + sum < 0 ? 0 : dp[i - 1][j - nums[i] + sum],
                    r = j + nums[i] + sum > 2 * sum ? 0 : dp[i - 1][j + nums[i] + sum];
                dp[i][j + sum] = l + r;
            }
        }

        return dp[len - 1][S + sum];
    }
}
```
# [LeetCode_1200_最小绝对差](https://leetcode-cn.com/problems/minimum-absolute-difference/)
## 解法
### 思路
- 排序
- 遍历比较
- 保存
### 代码
```java
class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        int len = arr.length;

        Arrays.sort(arr);
        int min = arr[len - 1];
        for (int i = 1; i < len; i++) {
            min = Math.min(min, arr[i] - arr[i - 1]);
        }

        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] == min) {
                List<Integer> list = new ArrayList<>();
                list.add(arr[i - 1]);
                list.add(arr[i]);
                ans.add(list);
            }
        }

        return ans;
    }
}
```
# [LeetCode_LCP17_速算机器人](https://leetcode-cn.com/problems/nGK0Fy/)
## 解法
### 思路
- 每出现一个字符就代表将当前值*2
- 所以结果就是2的len次方
### 代码
```java
class Solution {
    public int calculate(String s) {
        return (int) Math.pow(2, s.length());
    }
}
```
# [LeetCode_1049_最后一块石头的重量II](https://leetcode-cn.com/problems/last-stone-weight-ii/)
## 解法
### 思路
- 要使最终剩下的石头的重量最小，可以理解成将石头分成2堆，各自的总重量的差值越小，就相当于剩下的石头的重量越小
- 如果A代表一堆，B代表另一堆，那么如果`A<sum/2`，则A越大，B也就越接近`sum/2`，故问题变成：求`sum/2`范围内，求一堆石头的最大和，而最终结果的`sum - 2 * A`
- 在特定范围内，求一堆书中的最值，可以用0/1背包的最值公式来解决：
    - dp[i]：代表最大重量为i的范围内，能够得到的最大石头重量
    - 状态转移方程：`dp[i] = max(dp[i], dp[i - stone] + stone)`
        - 这个代表，在i的范围内，是否还要增加重量，并比较增加和不增加的大小
    - 最终得到的最大值就是`dp[len - 1]`
- 最终的结果就是`sum - 2 * dp[len - 1]`
- [学习的题解](https://leetcode-cn.com/problems/last-stone-weight-ii/solution/yi-pian-wen-zhang-chi-tou-bei-bao-wen-ti-5lfv/)
### 代码
```java
class Solution {
    public int lastStoneWeightII(int[] stones) {
        int sum = Arrays.stream(stones).sum(), len = stones.length, target = sum / 2;
        int[] dp = new int[target + 1];
        for (int stone : stones) {
            for (int i = target; i >= stone; i--) {
                dp[i] = Math.max(dp[i], dp[i - stone] + stone);
            }
        }

        return sum - 2 * dp[target];
    }
}
```
# [LeetCode_879_盈利计划](https://leetcode-cn.com/problems/profitable-schemes/)
## 解法
### 思路
动态规划：
1. 问题中的变量：
    - 项目数量：
        - 项目需要的人数
        - 项目的利润
    - 人数
    - 利润
2. 满足最小收益的方案数
3. 在求解范式中，可以将与求解结果最直接相关的变量，作为dp数组的最后一维去求解，所以此处就是利润
4. 项目数中包含了项目需要的人数和项目利润两个子变量，且项目所需人数与第二个变量人数有关系，所以可以将项目数作为dp的第一个维度，人数作为dp的后一个维度。又因为在状态转移过程中，每一次都只依赖前一个状态，所以第一维可以省略，从而减小空间消耗
5. 遍历求解思路：
    - 初始状态：`dp[0][0] = 1`，代表0个项目0个人0收益的方案数是1个
    - 状态转移方程：`dp[i][j] += dp[i - ci][j - cj]`，当前项目，需要i个人的项目利润为j的情况下，有的方案数就是刨去当前项目要的人和产生利润的状态时所具有的方案数
    - 三层循环：
        - 最外层遍历所有的项目数，获取当前项目需要的人数和产生的利润
        - 内部两层循环：
            - 第一层遍历：以前的所有状态中，枚举，在总人数n及更少的情况下，可以抽调走当前项目需要的人后，还有执行方案的状态
            - 第二层遍历：以前的所有状态中，刨去当前项目的利润，还能够正好达到当前最小利润，或者超出当前最小利润的所有状态的方案数。在计算的时候，从minProfit开始倒序遍历，减去当前利润后，这个剩余需要的利润值在以前的状态里能够找到的对应的方案，这个差值很可能是负数，这种状态就代表了超出了minProfit，此时就把他当做0来累加
### 代码
```java
class Solution {
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int g = group.length, mod = 1000000007;
        int[][] dp = new int[n + 1][minProfit + 1];
        dp[0][0] = 1;

        for (int i = 0; i < g; i++) {
            int gCost = group[i], pGet = profit[i];
            for (int j = n; j >= gCost; j--) {
                for (int k = minProfit; k >= 0; k--) {
                    dp[j][k] += dp[j - gCost][Math.max(k - pGet, 0)];
                    if (dp[j][k] > mod) {
                        dp[j][k] -= mod;
                    }
                }
            }
        }

        int sum = 0;
        for (int i = 0; i <= n; i++) {
                sum += dp[i][minProfit];
                if (sum > mod) {
                    sum -= mod;
                }
        }

        return sum;
    }
}
```
# [LeetCode_518_零钱兑换](https://leetcode-cn.com/problems/coin-change-2/)
## 解法
### 思路
动态规划：
- 状态转移公式：`dp[k][i] = dp[k - 1][i] + dp[k, i - k]`
- 解释：前k种硬币凑成金额i的组合个数，等于两种情况的和：
    - 不用当前硬币就凑齐金额i的个数
    - 用当前这种硬币才能凑足金额i的个数，也就是用到k-1种硬币后能够凑齐`i - coins[k]`的个数
- 细节：
    - 初始化：
        - k的长度为coins.length + 1，因为需要考虑到没有使用硬币的情况，在这个前提下，dp[0][0] = 1，就代表不选用硬币获得总金额为0的可能组合是1
        - i的长度为amount + 1，同样是为了将dp[0][0]的状态记录在数组中
        - 填充初始值：所有选择币种的情况下，在获取总金额为0的状态时，就有1种组合可能
    - 内外层的循环分别为：
        - 外层遍历币种，代表用到第几种硬币，因为此时考虑了不选用硬币的情况，所以坐标是从1开始的
        - 内层遍历总金额，总金额遍历也是从1开始。内部判断时：
            - 如果当前的总金额小于选择的币种值，那么就不用考虑选用当前硬币的情况，直接将当前状态等价为不选用当前币值就能获取到总金额的状态值
            - 否则就要将两种情况考虑进去：不选当前币种就能获取总金额的组合数 + 选用当前币种正好达到总金额的组合数（也就是选用当前币种，能够达到j - coins[i]金额的组合数，为什么是当前币种，是因为当前币种可能用多次，且初始化的时候已经将金额值为0的时候考虑进去，且如果当前币种大于总金额，会将不选用当前币种的值等价过来，所以就用当前币种来处理状态转移）
    - 因为数组长度是+1的，所以获取当前币种的金额时，坐标值要-1
### 代码
```java
class Solution {
    public int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length + 1][amount + 1];
        for (int i = 0; i <= coins.length; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j < coins[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                }
            }
        }

        return dp[coins.length][amount];
    }
}
```