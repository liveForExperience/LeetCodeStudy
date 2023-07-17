# [LeetCode_1911_最大子序列交替和](https://leetcode.cn/problems/maximum-alternating-subsequence-sum/)
## 解法
### 思路
动态规划
- dp[i][j]：前i个元素的最大交替和。如果j为0，则最后一个元素是奇数坐标，如果j为1，则最后一个元素是偶数坐标
- 状态转移方程：
  - j = 0: dp[i][0] = max(dp[i-1][1] - nums[i], dp[i-1][0])
  - j = 1: dp[i][1] = max(dp[i-1][0] + nums[i], dp[i-1][1])
- base case：dp[0][0] = dp[0][1] = 0
- 结果：max(dp[n][0], dp[n][1])
### 代码
```java
class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[][] dp = new long[n + 1][2];
        for (int i = 1; i <= n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - nums[i - 1]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + nums[i - 1]);
        }
        
        return Math.max(dp[n][0], dp[n][1]);
    }
}
```
# [LeetCode_18_四数之和](https://leetcode.cn/problems/4sum/)
## 解法
### 思路
- 这种在数组中找目标值的题目，一般都可以联想到二分查找，而二分的首要条件就是数组有序，所以这里先对数组做排序操作
- 排序后，开始模拟3层循环
  - 第1第2层确定四个数的前2个，将重复的元素剔除掉
  - 第3层做二分查找，然后将符合要求的元素组合放入准备好的列表中即可
- 3层循环结束，返回结果
- 注意：用例中有int溢出的情况，比较大小时需要用长整数来承接
- 可以尝试自己写一下快排
### 代码
```java
class Solution {
  public List<List<Integer>> fourSum(int[] nums, int target) {
    int n = nums.length;
    sort(nums, 0, n - 1);
    List<List<Integer>> ans = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      if (i > 0 && nums[i] == nums[i - 1]) {
        continue;
      }

      for (int j = i + 1; j < n; j++) {
        if (j > i + 1 && nums[j] == nums[j - 1]) {
          continue;
        }

        int a = j + 1, b = n - 1;
        while (a < b) {
          long sum = (long)nums[i] + nums[j] + nums[a] + nums[b];
          if (sum < target) {
            a++;
          } else if (sum > target) {
            b--;
          } else {
            ans.add(Arrays.asList(nums[i], nums[j], nums[a], nums[b]));
            while (a < b && nums[a] == nums[a + 1]) {
              a++;
            }
            a++;

            while (a < b && nums[b] == nums[b - 1]) {
              b--;
            }
            b--;
          }
        }
      }
    }

    return ans;
  }

  private void sort(int[] nums, int head, int tail) {
    if (head >= tail) {
      return;
    }

    int pivot = partition(nums, head, tail);
    sort(nums, head, pivot - 1);
    sort(nums, pivot + 1, tail);
  }

  private int partition(int[] nums, int head, int tail) {
    int num = nums[head];
    while (head < tail) {
      while (head < tail && num <= nums[tail]) {
        tail--;
      }
      nums[head] = nums[tail];

      while (head < tail && num >= nums[head]) {
        head++;
      }
      nums[tail] = nums[head];
    }

    nums[head] = num;
    return head;
  }
}
```