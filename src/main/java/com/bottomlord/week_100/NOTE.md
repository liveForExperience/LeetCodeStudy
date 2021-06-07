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