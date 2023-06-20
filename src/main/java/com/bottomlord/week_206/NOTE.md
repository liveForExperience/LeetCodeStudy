# [LeetCode_1262_可悲三整除的最大和](https://leetcode.cn/problems/greatest-sum-divisible-by-three/)
## 解法
### 思路
贪心
- 因为除数是3，所以取模后有3种情况：0、1、2
- 先对数组进行求和sum
  - 如果为0，那么sum就是结果
  - 如果为1，那么就可以尝试在数组中找到最小的1或2个元素，它们的和被3取模也是1
  - 如果为2，那么如上类似，找到和被取模后为2的最小的1或2个元素
- 为了找到如上算法中的元素，可以在遍历nums的过程中，对元素进行取模计算，并暂存最小的模为1和模为2的各2个元素
  - min1a,min1b
  - min2a,min2b
- 如果sum取模是1，那么就比较min1a和min2a+min2b之间的最小值
- 如果sum取模是2，那么比较min2a和min1a+min1b之间的最小值
- 如果如上4个元素并不都能找到值，那么做对应的舍弃处理即可
### 代码
```java
class Solution {
    public int maxSumDivThree(int[] nums) {
        int sum = 0;
        int[] arr1 = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE},
                arr2 = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};

        for (int num : nums) {
            sum += num;

            int mod = num % 3;

            if (mod == 1) {
                getMin2(num, arr1);
            }

            if (mod == 2) {
                getMin2(num, arr2);
            }
        }

        if (sum % 3 == 0) {
            return sum;
        }

        return sum % 3 == 1 ? getAns(sum, arr1, arr2) : getAns(sum, arr2, arr1);
    }

    private void getMin2(int num, int[] arr) {
        if (num < arr[0]) {
            arr[1] = arr[0];
            arr[0] = num;
        } else if (num < arr[1]) {
            arr[1] = num;
        }
    }

    private int getAns(int sum, int[] arrA, int[] arrB) {
        if (arrA[0] == Integer.MAX_VALUE) {
            return arrB[1] == Integer.MAX_VALUE ? 0 : sum - arrB[0] - arrB[1];
        } else {
            return arrB[1] == Integer.MAX_VALUE ? sum - arrA[0] : sum - Math.min(arrB[0] + arrB[1], arrA[0]);
        }
    }
}
```
## 解法二
### 思路
动态规划
- 设置一个二维数组dp[i][j]：
  - 代表[0,j]区间元素，选取任意元素后得到的sum值，取模3得到j时的最大可能sum值
- 状态转移方程：
  - 选取第i个元素：dp[i][j] = dp[i - 1][(j - nums[i] % 3 + 3) % 3] + nums[i]
  - 不选第i个元素：dp[i][j] = dp[i - 1][j]
  - dp[i][j] = max(dp[i - 1][(j - nums[i] % 3 + 3) % 3] + nums[i], dp[i - 1][j])
- base case： dp[0][0] = dp[0][1] = dp[0][2] = nums[0]
- 最终结果：dp[n - 1][0]
- base case设置的时候无法理解，做了下思考：
  - 定义base case时候，对dp[0][1]和dp[0][2]都设置为了-inf，而dp[0][0]则是0
  - 从语义上理解，dp[0][0]代表啥元素都没选的情况下，sum值就是0，这个好理解
  - dp[0][1]和dp[0][2]设置为-inf，从字面上理解是非法值，这个也能理解，因为啥元素都没有选，那sum会是1或者2吗？不可能，所以设置为-inf
  - 那么这2个-inf有什么用呢？
  - 当我选择第一个元素的时候，如果我希望i-1坐标的j是1或者2，那么我会选择如下的公式：(j - nums[i] % 3 + 3) % 3，来求出上一个取模为该值的sum
  - 这个时候，如果当前nums[0] % 3（这就是第一个元素）= 1，而我要得到的，选择当前元素得到的新sum值取模后的mod值也是1，那么根据公式，就能得到dp[0][0] + nums[0]
  - 这个dp[0][0] + nums[0]，不就代表了当我选择第一个元素后，我的新sum就是nums[0]，而这个时候sum取模后的值就是nums[0]取模后的值，非常直观
  - 但如果这个时候，我的nums[0] % 3还是1，但是我要求的新sum是取模后为2的值，那么我就需要找到之前sum取模后为1的状态，而这个状态根据之前理解的，是不可能出现的，所以这个时候通过-inf，我们套用max的比较公式，得到的还是一个接近-inf的值，也就是个非法的值，因为从刚才分析的逻辑看，这种情况也是不合法的
  - 所以这里的base case我目前理解就是这么玩的
### 代码
```java
class Solution {
  public int maxSumDivThree(int[] nums) {
    int n = nums.length;
    int[][] dp = new int[n + 1][3];
    dp[0][1] = dp[0][2] = Integer.MIN_VALUE;
    for (int i = 1; i <= n; i++) {
      for (int j = 0; j < 3; j++) {
        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][(j - nums[i - 1] % 3 + 3) % 3] + nums[i - 1]);
      }
    }
    return dp[n][0];
  }
}
```