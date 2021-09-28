# [LeetCode_639_解码方法II](https://leetcode-cn.com/problems/decode-ways-ii/)
## 解法
### 思路
动态规划
- 在[91题](https://leetcode-cn.com/problems/decode-ways/)的基础上，状态转移时候，需要额外考虑为星的情况
- 状态转移方程：
  - 只考虑1个数字
    - 如果当前是星号，那么就有9种可能：dp[i] = 9 * dp[i - 1]
    - 如果当前是0：dp[i] = 0
    - 如果当前是[1,9]：dp[i] = dp[i - 1]
  - 考虑2个数字
    - 如果有2个星，那么就有[11,19]和[21,26]共15种可能：dp[i] = 15 * dp[i - 2]
    - 如果当前是星
      - 如果s[i - 1]是1：dp[i] = 9 * dp[i - 2]
      - 如果s[i - 1]是2：dp[i] = 6 * dp[i - 2]
    - 如果前一个是星
      - 如果s[i]是[7,9]：dp[i] = dp[i - 2]
      - 如果s[i]是[0,6]：dp[i] = 2 * dp[i - 2]
    - 如果都不是星
      - 那么s[i -  1]不是0，且组成的数字在10到26之间：dp[i] = dp[i - 2]
- 计算过程中需要取余1000000007，而且dp数组需要用long类型
### 代码
```java
class Solution {
    private int mod = 1000000007;
    
    public int numDecodings(String s) {
        int n = s.length();
        long[] dp = new long[n + 1];
        s = " " + s;
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            if (s.charAt(i) == '*') {
                dp[i] = 9 * dp[i - 1];
            } else if (s.charAt(i) != '0') {
                dp[i] = dp[i - 1];
            }

            dp[i] %= mod;

            if (s.charAt(i - 1) == s.charAt(i) && s.charAt(i) == '*') {
                dp[i] += 15 * dp[i - 2];
            } else if (s.charAt(i) == '*') {
                if (s.charAt(i - 1) == '1') {
                    dp[i] += 9 * dp[i - 2];
                } else if (s.charAt(i - 1) == '2') {
                    dp[i] += 6 * dp[i - 2];
                }
            } else if (s.charAt(i - 1) == '*') {
                int a = s.charAt(i) - '0';
                if (a >= 7 && a <= 9) {
                    dp[i] += dp[i - 2];
                } else if (a >= 0 && a <= 6) {
                    dp[i] += 2 * dp[i - 2];
                }
            } else {
                int a = s.charAt(i) - '0', b = (s.charAt(i - 1) - '0') * 10 + a;
                if (b >= 10 && b <= 26) {
                    dp[i] += dp[i - 2];
                }
            }

            dp[i] %= mod;
        }

        return (int)dp[n];
    }
}
```
# [LeetCode_91_解法方法](https://leetcode-cn.com/problems/decode-ways/)
## 解法
### 思路
动态规划：
- dp[i]：代表长度为i的字符串能够组成的编码组合个数
- 状态转移方程：
  - 如果当前数字为[1,9]，那么他能单独组成一个字母，所以他的编码个数就和dp[i - 1]一样
  - 如果当前数组与之前的数组能够组成[10, 26]，那么他们2个就能组成一个字母，从而他们的编码个数就和dp[i - 2]一样
  - 所以如果当前这个数字同时满足如上2种情况，那么当前能够组成的编码个数就是dp[i - 1] + dp[i - 2]
- base case：
  - 为了简便计算，可以在字符串之前拼接一个空字符，然后设置这个位置的dp值为1
- 最终返回dp[n]
### 代码
```java
class Solution {
    public int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        s = " " + s;
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            int a = s.charAt(i) - '0', b = (s.charAt(i - 1) - '0') * 10 + a;
            if (a >= 1 && a <= 9) {
                dp[i] = dp[i - 1];
            }

            if (b >= 10 && b <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[n];
    }
}
```
# [LeetCode_437_路径总和III](https://leetcode-cn.com/problems/path-sum-iii/)
## 解法
### 思路
嵌套dfs
- 第一层dfs确定计算路径和的起始节点
- 第二层dfs用来计算路径和
### 代码
```java
class Solution {
    private int count = 0;
    public int pathSum(TreeNode root, int targetSum) {
        dfs(root, targetSum);
        return count;
    }

    private void dfs(TreeNode node, int sum) {
        if (node == null) {
            return;
        }
        
        if (node.val == sum) {
            count++;
        }
        
        doDfs(node.left, node.val, sum);
        doDfs(node.right, node.val, sum);
        
        dfs(node.left, sum);
        dfs(node.right, sum);
    }
    
    private void doDfs(TreeNode node, int val, int sum) {
        if (node == null) {
            return;
        }
        
        val += node.val;
        
        if (val == sum) {
            count++;
        }
        
        doDfs(node.left, val, sum);
        doDfs(node.right, val, sum);
    }
}
```
## 解法
### 思路
dfs+前缀和
- 一层dfs
- 初始化一个map，用于存储当前遍历路径上的所有前缀和，默认key为0，value为1，这个默认值用于从根节点开始累加的累加值正好等于sum的情况，方便计数
- dfs搜索过程中，查找当前前缀和和已有前缀和之间是否存在差值为sum的情况，有的话，就累加这个值，然后继续向下搜索
- 在搜索过程中还需要做回溯时候的状态清除，因为求的是路径上的和，所以过去搜索到的其他路径上的和需要被清理掉
### 代码
```java
class Solution {
  public int pathSum(TreeNode root, int targetSum) {
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);
    return dfs(root, targetSum, 0, map);
  }

  private int dfs(TreeNode node, int sum, int curSum, Map<Integer, Integer> map) {
    if (node == null) {
      return 0;
    }

    curSum += node.val;

    int count = map.getOrDefault(curSum - sum, 0);
    map.put(curSum, map.getOrDefault(curSum, 0) + 1);
    count += dfs(node.left, sum, curSum, map) + dfs(node.right, sum, curSum, map);
    map.put(curSum, map.get(curSum) - 1);
    return count;
  }
}
```