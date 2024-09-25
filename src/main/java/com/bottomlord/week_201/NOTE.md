# [LeetCode_1335_工作计划的最低难度](https://leetcode.cn/problems/minimum-difficulty-of-a-job-schedule/)
## 解法
### 思路
- 特殊情况，当工作量少于d的时候，不符合题目要求，直接返回-1
- 初始化变量n，代表任务数。
- dp[i][j]：第i天完成第j个工作的最小难度值
- 状态转移方程：dp[i][j] = min(dp[i][j], max + dp[i - 1][k - 1]);
  - k∈[i, j]
  - max：k到j区间内的最大值
- 初始化dp[1][j]（j∈[1,n]）
  - 遍历获取区间内的最大值并赋值给对应的dp[1][j]，值的比较区间是[1, j]
- 3层循环
  - 第一层遍历天数
  - 第二层遍历从天数开始的任务数
  - 第三层从任务数开始倒序遍历，确定区间内的最大值，内部则进行状态转移方程的计算
- 最终返回dp[d][n]
### 代码
```java
class Solution {
    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (n < d) {
            return -1;
        }

        int[][] dp = new int[d + 1][n + 1];
        for (int[] arr : dp) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, jobDifficulty[i]);
            dp[1][i + 1] = max;
        }

        for (int i = 2; i <= d; i++) {
            for (int j = i; j <= n; j++) {
                max = 0;
                for (int k = j; k >= i; k--) {
                    max = Math.max(max, jobDifficulty[k - 1]);
                    dp[i][j] = Math.min(dp[i][j], max + dp[i - 1][k - 1]);
                }
            }
        }

        return dp[d][n];
    }
}
```
## 解法二
### 思路
状态压缩
### 代码
```java
class Solution {
    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (n < d) {
            return -1;
        }

        int[] pre = new int[n + 1];
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, jobDifficulty[i]);
            pre[i + 1] = max;
        }

        for (int i = 2; i <= d; i++) {
            int[] cur = new int[n + 1];
            Arrays.fill(cur, Integer.MAX_VALUE);
            for (int j = i; j <= n; j++) {
                max = 0;
                for (int k = j; k >= i; k--) {
                    max = Math.max(max, jobDifficulty[k - 1]);
                    cur[j] = Math.min(cur[j], max + pre[k - 1]);
                }
            }
            pre = cur;
        }

        return pre[n];
    }
}
```
# [LeetCode_2682_找出转圈游戏输家](https://leetcode.cn/problems/find-the-losers-of-the-circular-game/)
## 解法
### 思路
记事本加模拟
### 代码
```java
class Solution {
    public int[] circularGameLosers(int n, int k) {
        boolean[] memo = new boolean[n];
        int index = 0;
        for (int i = 0;; i++) {
            if (memo[index]) {
                break;
            }
            memo[index] = true;
            index = (index + (i + 1) * k) % n;
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < memo.length; i++) {
            if (memo[i]) {
                continue;
            }
            
            list.add(i + 1);
        }
        
        int[] ans = new int[list.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }
}
```
# [LeetCode_1073_负二进制数相加](https://leetcode.cn/problems/adding-two-negabinary-numbers/)
## 解法
### 思路
模拟
- 初始化一个进位值carry，用于存储进位
- 分情况讨论，x代表当前位上计算后的值（arr1元素和arr2元素还有carry的值相加）
  - 0/1：当前位就是x，carry是0
  - 2/3：当前位是x-2，carry是-1
  - -1：当前位是1，carry是1
- 从2个数组的尾部开始遍历模拟，得到的数组再翻转后，作为结果返回
- 需要注意空数组或者只有一个元素0的情况
### 代码
```java
class Solution {
    public int[] addNegabinary(int[] arr1, int[] arr2) {
        int carry = 0;
        int a = arr1.length - 1, b = arr2.length - 1;
        List<Integer> list = new ArrayList<>();
        while (a >= 0 || b >= 0) {
            if (a < 0) {
                int x = carry + arr2[b];
                list.add(getCur(x));
                carry = getCarry(x);
                b--;
                continue;
            }

            if (b < 0) {
                int x = carry + arr1[a];
                list.add(getCur(x));
                carry = getCarry(x);
                a--;
                continue;
            }

            int x = arr1[a] + arr2[b] + carry;
            list.add(getCur(x));
            carry = getCarry(x);
            a--;
            b--;
        }

        if (carry == 1) {
            list.add(1);
        }

        if (carry == -1) {
            list.add(1);
            list.add(1);
        }

        int n = list.size();
        if (n == 0) {
            return new int[]{0};
        }
        
        int[] ans = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            ans[n - i - 1] = list.get(i);
        }

        int i = 0;
        for (; i < ans.length; i++) {
            if (ans[i] == 1) {
                break;
            }
        }

        ans = Arrays.copyOfRange(ans, i, n);
        if (ans.length == 0) {
            return new int[]{0};
        }

        return ans;
    }

    private int getCarry(int x) {
        if (x == 0 || x == 1) {
            return 0;
        }

        if (x == 2 || x == 3) {
            return -1;
        }
        
        return 1;
    }
    
    private int getCur(int x) {
        if (x == 0 || x == 1) {
            return x;
        }

        if (x == 2 || x == 3) {
            return x - 2;
        }

        return 1;
    }
}
```
# [LeetCode_984_不含aaa或bbb的字符串](https://leetcode.cn/problems/string-without-aaa-or-bbb/)
## 解法
### 思路
- 确定a和b谁多谁少，那么交替追加，一定能把少的消耗掉
- 同时，多的部分与少的部分的差值，也就是交替追加后剩下的多的字符的个数diff，可以平均的插入到1个多字符和1个少字符组成的一个小组中
- 以少的那个值作为循环的次数，在循环过程中：
  - 先追加1个多的字符，然后多的字符个数累减1
  - 然后判断diff是否为0，如果不是，就再追加一个多的字符，同时diff累减1，同时多的字符个数也累减1
  - 再追加1个少的字符
- 循环结束后，判断多的字符个数是否不为0，如果是，就继续讲剩下的多的字符追加到字符串里，因为题目保证一定有答案，所以不用担心会出现违反题目要求的情况
- 追加完后，返回字符串即可
### 代码
```java
class Solution {
    public String strWithout3a3b(int a, int b) {
                char maxC, minC;
        int max, min;
        if (a >= b) {
            maxC = 'a';
            minC = 'b';
            max = a;
            min = b;
        } else {
            maxC = 'b';
            minC = 'a';
            max = b;
            min = a;
        }

        int diff = max - min;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < min; i++) {
            sb.append(maxC);
            max--;
            if (diff-- > 0) {
                sb.append(maxC);
                max--;
            }

            sb.append(minC);
        }

        for (int i = 0; i < max; i++) {
            sb.append(maxC);
        }

        return sb.toString();
    }
}
```
# [LeetCode_1079_活字印刷](https://leetcode.cn/problems/letter-tile-possibilities/)
## 解法
### 思路
回溯，使用set记录字符串组合，并将结束回溯后的set的长度作为结果返回即可
### 代码
```java
class Solution {
  public int numTilePossibilities(String tiles) {
    char[] cs = tiles.toCharArray();
    Set<String> set = new HashSet<>();
    backTrack(0, cs, new boolean[cs.length], new StringBuilder(), set);
    return set.size();
  }

  private void backTrack(int index, char[] cs, boolean[] memo, StringBuilder sb, Set<String> set) {
    if (index >= cs.length) {
      return;
    }

    for (int i = 0; i < cs.length; i++) {
      if (memo[i]) {
        continue;
      }

      int len = sb.length();
      memo[i] = true;
      sb.append(cs[i]);
      set.add(sb.toString());
      backTrack(index + 1, cs, memo, sb, set);
      sb.setLength(len);
      memo[i] = false;
    }
  }
}
```
# [LeetCode_1373_二叉搜索子树的最大键值和](https://leetcode.cn/problems/maximum-sum-bst-in-binary-tree/)
## 解法
### 思路
- 定义一个子树类，其中包含如下属性
  - 是否是二叉搜索树（bst）
  - 最大值
  - 最小值
  - 总和
- 如果子树为空：
  - 最大值：int最小值
  - 最小值：int最大值
  - 总和为0
  - 是bst
- 递归判定当前节点是否为二叉搜索树的根节点
- 然后更新最大值类变量
- 搜索结束后返回暂存的最大值作为结果
- 注意如果是二叉搜索子树根节点的话，在返回实体的时候，该子树的最大和最小值需要和当前节点的值进行大小的比较，因为在处理空的时候做了特殊处理，如果当前节点是叶子节点，那么不比较的话，会把特殊处理的值返回上去，导致所有节点都变成了二叉搜索子树，导致累加所有节点的值
### 代码
```java
class Solution {
  private int ans = 0;
  public int maxSumBST(TreeNode root) {
    dfs(root);
    return ans;
  }

  private static class SubBst {
    private boolean isBst;
    private int max;
    private int min;
    private int sum;

    public SubBst(boolean isBst, int max, int min, int sum) {
      this.isBst = isBst;
      this.max = max;
      this.min = min;
      this.sum = sum;
    }
  }

  private SubBst dfs(TreeNode node) {
    if (node == null) {
      return new SubBst(true, Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
    }

    SubBst left = dfs(node.left), right = dfs(node.right);
    if (left.isBst &&
            right.isBst &&
            left.max < node.val &&
            right.min > node.val) {
      int sum = left.sum + right.sum + node.val;
      ans = Math.max(ans, sum);
      return new SubBst(true, Math.max(right.max, node.val), Math.min(left.min, node.val), sum);
    }

    return new SubBst(false, 0, 0, 0);
  }
}
```