# [LeetCode_736_LISP语法解析](https://leetcode.cn/problems/parse-lisp-expression/)
## 解法
### 思路
dfs
- 递归方程传递
  - 起始和结束坐标，用来确定表达式当前需要处理的部分
  - map，用来存储当前作用域的变量值
- 递归方程内部：
  - 起始字符是`(`：
    - 先找到其实的操作符号
    - 如果操作符号是add或者是multi，那么就将当前表达式分成两部分，这两部分也是通过递归dfs来获取值，然后根据不同表达式来计算，并返回结果
    - 如果操作符号是let，那么就需要循环处理变量+（值或表达式）的组合，直到越界
  - 起始字符不是`(`：说明当前表达式不需要计算，是一个变量或值
### 代码
```java
class Solution {
    private String s;
    private char[] cs;

    public int evaluate(String _s) {
        this.s = _s;
        this.cs = _s.toCharArray();
        return dfs(0, s.length() - 1, new HashMap<>());
    }

    private int dfs(int l, int r, Map<String, Integer> map) {
        char start = cs[l];
        if (start != '(') {
            String str = s.substring(l, r + 1);
            Integer value = map.get(str);
            return value == null ? Integer.parseInt(str) : value;
        }

        int index = ++l;
        r--;

        while (cs[index] != ' ') {
            index++;
        }

        String op = s.substring(l, index);
        index++;

        if (Objects.equals(op, "let")) {
            while (index <= r) {
                int mid = getNextIndex(index, r);
                if (mid >= r) {
                    return dfs(index, r, new HashMap<>(map));
                }

                String key = s.substring(index, mid);
                index = mid + 1;

                int mid2 = getNextIndex(index, r);
                map.put(key, dfs(index, mid2 - 1, new HashMap<>(map)));
                index = mid2 + 1;
            }

            return -1;
        } else {
            int mid = getNextIndex(index, r);
            int left = dfs(index, mid - 1, new HashMap<>(map)),
                right = dfs(mid + 1, r, new HashMap<>(map));

            return Objects.equals(op, "add") ? left + right : left * right;
        }
    }

    public int getNextIndex(int left, int end) {
        int right = left, score = 0;

        while (right <= end) {
            if (cs[right] == '(') {
                score++;
            } else if (cs[right] == ')') {
                score--;
            } else if (cs[right] == ' ') {
                if (score == 0) {
                    break;
                }
            }

            right++;
        }

        return right;
    }
}
```
# [LeetCode_741_摘樱桃](https://leetcode.cn/problems/cherry-pickup/)
## 解法
### 思路
动态规划：
- dp[k][x1][x2]：
  - k是总步数
  - x1代表从(x1, k-x1)位置出发，到(n-1,n-1)位置能够摘到的最大樱桃值
  - x2代表从(x2, k-x2)位置出发，到(n-1,n-1)位置能够摘到的最大樱桃值
  - 整体代表走k步，从(x1, k-x1)和(x2,k-x2)出发，到达(n-1，n-1)位置能够得到的总的最大樱桃值
- 状态转移方程：总共会有4种不同情况：
  - x1和x2同时向右：dp[k][x1][x2] = dp[k][x1][x2]
  - x1和x2同时向下：dp[k][x1][x2] = dp[k][x1 - 1][x2 - 2]
  - x1向下，x2向右：dp[k][x1][x2] = dp[k][x1 - 1][x2]
  - x1向右，x2向下：dp[k][x1][x2] = dp[k][x1][x2 - 1]
- 最终结果：dp[2n-2][n-1][n-1]和0之间的最大值
### 代码
```java
class Solution {
  public int cherryPickup(int[][] grid) {
    int n = grid.length;
    int[][][] dp = new int[2 * n - 1][n][n];
    for (int i = 0; i < 2 * n - 1; i++) {
      for (int j = 0; j < n; j++) {
        Arrays.fill(dp[i][j], Integer.MIN_VALUE);
      }
    }

    dp[0][0][0] = grid[0][0];

    for (int k = 1; k < 2 * n - 1; k++) {
      for (int x1 = Math.max(k - n + 1, 0); x1 <= Math.min(k, n - 1); x1++) {
        int y1 = k - x1;

        if (grid[x1][y1] == -1) {
          continue;
        }

        for (int x2 = Math.max(k - n + 1, 0); x2 <= Math.min(k, n - 1); x2++) {
          int y2 = k - x2;

          if (grid[x2][y2] == -1) {
            continue;
          }

          int ans = dp[k - 1][x1][x2];
          if (x1 > 0) {
            ans = Math.max(dp[k - 1][x1 - 1][x2], ans);
          }

          if (x2 > 0) {
            ans = Math.max(dp[k - 1][x1][x2 - 1], ans);
          }

          if (x1 > 0 && x2 > 0) {
            ans = Math.max(dp[k - 1][x1 - 1][x2 - 1], ans);
          }

          ans += grid[x1][y1];
          if (x1 != x2) {
            ans += grid[x2][y2];
          }

          dp[k][x1][x2] = ans;
        }
      }
    }

    return Math.max(0, dp[2 * n - 2][n - 1][n - 1]);
  }
}
```