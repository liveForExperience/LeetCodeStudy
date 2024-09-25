# [LeetCode_1186_删除一次得到子数组最大和](https://leetcode.cn/problems/maximum-subarray-sum-with-one-deletion/)
## 解法
### 思路
动态规划：
- dp[i][j]：以i坐标元素结尾的已经删除j次的子数组最大和
  - i∈[0,n-1]
  - j∈[0,1]
- 状态转移方程：
  - dp[i][0] = max(p[i - 1][0] + nums[i], arr[i])
  - dp[i][1] = max(dp[i - 1][0], dp[i - 1][1] + nums[i])
- base case：
  - dp[0][0] = nums[i]
  - dp[0][1] = 0
- 求结果，暂存状态转移过程中的最大值作为结果返回即可
- 注意：
  - dp[0][1]的初始状态是0，代表以坐标0为结尾的子数组的坐标0元素被删除，总和应该是0，但是max值并不能在arr[0]和0之间比较，因为题目要求不可以为一个空数组，所以dp[0][1]这种情况实际上是非法的，其总和应该是一个非法值，所以这个时候不做比较，直接去dp[0][0]的值作为结果
### 代码
```java
class Solution {
    public int maximumSum(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][2];
        int max = dp[0][0] = arr[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0] + arr[i], arr[i]);
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1] + arr[i]);
            max = Math.max(Math.max(dp[i][0], dp[i][1]), max);
        }
        return max;
    }
}
```
# [LeetCode_1253_重构2行二进制矩阵](https://leetcode.cn/problems/reconstruct-a-2-row-binary-matrix/)
## 解法
### 思路
- 根据题目要求分情况贪心的去求解即可
- 遍历colsum数组
  - 如果元素是2，同时往2个列表中放入1
  - 如果元素是0，同时往2个列表中放入0
  - 如果是1，那么就看upper和lower这2个变量哪个值更大，然后就往哪个对应的列表中放入1，另一个则放入0
- 每一次遍历，在判断完上述情况后，检查一下upper和lower的值是否小于0，如果是，则说明答案不存在，返回空列表
- 在遍历结束后，检查upper和lower变量的值是否为0，如果不是，也代表没有答案，返回空列表
- 否则就把2个列表返回即可
### 代码
```java
class Solution {
    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> one = new ArrayList<>(), two = new ArrayList<>();
        for (int num : colsum) {
            if (num == 2) {
                one.add(1);
                two.add(1);
                upper--;
                lower--;
            } else if (num == 0) {
                one.add(0);
                two.add(0);
            } else if (upper >= lower) {
                one.add(1);
                two.add(0);
                upper--;
            } else {
                one.add(0);
                two.add(1);
                lower--;
            }
                        
            if (upper < 0 || lower < 0) {
                return ans;
            }
        }

        if (upper != 0 || lower != 0) {
            return ans;
        }

        ans.add(one);
        ans.add(two);
        return ans;
    }
}
```