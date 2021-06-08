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