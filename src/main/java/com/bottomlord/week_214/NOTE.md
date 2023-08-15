# [LeetCode_1595_连通两组点的最小成本](https://leetcode.cn/problems/minimum-cost-to-connect-two-groups-of-points/description/)
## 解法
### 思路
- 定义`dp[i][j]`来表示：第一组前i个元素全部已连接的情况在，第二组j状态时的最小成本
  - j状态是通过一个二进制整数来表示，k位为1代表已连接，为0代表未连接
- 状态转移方程
  - 第二组第k个节点只和i连接：
    - `dp[i][j] = min(dp[i][j], dp[i][j ^ (1 << k)] + cost[i - 1][k])`
    - `dp[i][j] = min(dp[i][j], dp[i - 1][j ^ (1 << k)] + cost[i - 1][k])`
  - 第二组第k个节点和i及其他节点连接：
    - `dp[i][j] = min(dp[i][j], dp[i - 1][j])`
- base case：
  - `dp[0][0] = 0`
  - 其他值初始化为极值
- 答案：`dp[m][(1 << n) - 1]`
### 代码
```java
class Solution {
    public int connectTwoGroups(List<List<Integer>> cost) {
        int m = cost.size(), n = cost.get(0).size(), inf = 1 << 30;
        int[][] dp = new int[m + 1][1 << n];
        for (int i = 0; i <= m; i++) {
            Arrays.fill(dp[i], inf);
        }
        
        dp[0][0] = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j < (1 << n); j++) {
                for (int k = 0; k < n; k++) {
                    if ((j >> k & 1) == 1) {
                        int c = cost.get(i - 1).get(k);
                        dp[i][j] = Math.min(dp[i][j], dp[i][j ^ (1 << k)] + c);
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + c);
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j ^ (1 << k)] + c);
                    }
                }
            }
        }
        
        return dp[m][(1 << n) - 1];
    }
}
```
# [LeetCode_883_字符串中的查找和替换](https://leetcode.cn/problems/find-and-replace-in-string/)
## 解法
### 思路
- 最直接的想法是，遍历indices数组，根据从对应坐标开始判断是否匹配source数组元素，然后将字符串中的对应位置替换为target数组元素，但是因为替换之后，s的长度就改变了，这样indices的坐标值与s的坐标就无法对应了，所以不能用这种方式。
- 那就换一种思路
  - 先通过一个数组arr，将原来s字符串上哪个坐标的元素需要改变，哪个不需要改变的坐标记录下来，通过-1作为不需要变更的标志
  - 初始化一个StringBuilder来记录替换之后的字符串
  - 然后遍历arr数组
    - 如果当前元素值为-1，那么s对应的字符拼接到sb之后
    - 如果不为-1，判断从当前坐标开始，`source[i]`对应长度的字符串是否与`source[i]`一致
      - 如果一致，将`target[i]`拼接到sb之后，并更新坐标i为`i += source[i].length`
      - 如果不一致，拼接s对应的字符
  - 遍历结束，返回sb的字符串即可
### 代码
```java
class Solution {
    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        int n = s.length();
        int[] arr = new int[n];
        Arrays.fill(arr, -1);
        for (int i = 0; i < indices.length; i++) {
            arr[indices[i]] = i;
        }
        
        char[] cs = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == -1) {
                sb.append(cs[i]);
            } else {
                int index = arr[i];
                if (s.startsWith(sources[index], indices[index])) {
                    sb.append(targets[index]);
                    i += sources[index].length() - 1;
                } else {
                    sb.append(cs[i]);
                }
            }
        }
        
        return sb.toString();
    }
}
```