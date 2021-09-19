# [LeetCode_313_超级丑数](https://leetcode-cn.com/problems/super-ugly-number/)
## 解法
### 思路
小顶堆
- 初始化
  - 初始化一个小顶堆queue，将最小的丑数1放入queue
  - 初始化一个set用于去重
- 遍历n次
  - 每次将queue的堆顶元素拿出作为当前第i个的最小超级丑数
  - 然后基于该丑数，依次乘以数组中的所有质因数，并判断是否在set中，如果不在就放入queue
  - 直到n次循环结束
- 返回din次获取到的queue的堆顶元素并返回
### 代码
```java
class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        Set<Long> set = new HashSet<>();
        set.add(1L);
        
        PriorityQueue<Long> queue = new PriorityQueue<>();
        queue.offer(1L);
        
        long ugly = 1;
        for (int i = 0; i < n; i++) {
            ugly = queue.poll();
            for (int num : primes) {
                long next = num * ugly;
                if (set.add(next)) {
                    queue.offer(next);
                }
            }
        }
        
        return (int)ugly;
    }
}
```
## 解法二
### 思路
dp+n指针：
- 使用dp数组，dp[i]表示第i个超级丑数
- dp数组初始化为n+1的长度，dp[1] = 1，代表第一个超级丑数是1
- 初始化一个指针数组
  - 每一个元素对应的坐标对应primes数组中对应坐标的元素
  - 值对应dp数组中对应坐标的元素，也就是当前指针值对应dp的坐标，该值代表当前质因数没有相乘过的最小丑数，也就是下一个丑数可能从当前指针对应的丑数与当前质因数的乘积中获得
### 代码
```java
class Solution {
  public int nthSuperUglyNumber(int n, int[] primes) {
    long[] dp = new long[n + 1];
    dp[1] = 1;

    int[] points = new int[primes.length];
    Arrays.fill(points, 1);

    for (int i = 2; i <= n; i++) {
      long min = Integer.MAX_VALUE;
      for (int j = 0; j < primes.length; j++) {
        min = Math.min(dp[points[j]] * primes[j], min);
      }
      dp[i] = min;

      for (int j = 0; j < primes.length; j++) {
        if (dp[i] == primes[j] * dp[points[j]]) {
          points[j]++;
        }
      }
    }

    return (int)dp[n];
  }
}
```
# [LeetCode_413_等差数列划分](https://leetcode-cn.com/problems/arithmetic-slices/)
## 解法
### 思路
模拟：嵌套循环判断
### 代码
```java
class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            int diff = nums[i + 1] - nums[i], 
                pre = nums[i + 1];
            for (int j = i + 2; j < nums.length; j++) {
                if (nums[j] - pre == diff) {
                    count++;
                    pre = nums[j];
                } else {
                    break;
                }
            }
        }
        
        return count;
    }
}
```
## 解法二
### 思路
动态规划：
- dp[i]：代表0到i区间的数组能够获取的等差数列总数
- base case：
  - 公差：diff = nums[1] - nums[0]
  - dp[0] = 0, dp[1] = 0, dp[2] = nums[2] - nums[1] == diff ? 1 : 0
- 状态转移方程：
  - nums[i] - nums[i - 1] == diff：dp[i] = dp[i - 1] + 1
  - nums[i] - nums[i - 1] != diff：dp[i] = 0, diff = nums[i] - nums[i - 1];
  - 如果当前值与前置的差与公差相等，那么就说明0到当前元素坐标所组成的数组中，一定能够组成和[0,i-1]区间一样个数的连续数组
  - 假设原来[0,i-1]这个区间的[j,i-1]是最长的连续等差数组，这个区间能组成n个连续等差数组，那么[j +1,i]等于右移1位，也就能同样获得n个数组，同时再加上[j,i]这个数组
  - 所以最后就是2 * n + 1个数组
- 结果：累加dp中所有元素的和
- 优化整个算法过程，累加值可以在循环时就处理，减少一个循环
- 需要注意判断nums长度不足3的情况，此时结果是0
### 代码
```java
class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return 0;
        }
        
        int diff = nums[1] - nums[0], sum = 0;
        int[] dp = new int[n];
        
        dp[0] = dp[1] = 0;
        for (int i = 2; i < n; i++) {
            if (nums[i] - nums[i - 1] == diff) {
                dp[i] = dp[i - 1] + 1;
            } else {
                diff = nums[i] - nums[i - 1];
            }
            sum += dp[i];
        }
        
        return sum;
    }
}
```
## 解法三
### 思路
基于解法二可以发现，状态转移时只依赖前一个数字的状态，可以用一个变量来代替dp数组
### 代码
```java
class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return 0;
        }
        
        int diff = nums[1] - nums[0], total = 0, ans = 0;
        for (int i = 2; i < n; i++) {
            if (nums[i] - nums[i - 1] == diff) {
                total++;
            } else {
                diff = nums[i] - nums[i - 1];
                total = 0;
            }
            
            ans += total;
        }
        
        return ans;
    }
}
```
# [LeetCode_446_等差数列划分II_子序列](https://leetcode-cn.com/problems/arithmetic-slices-ii-subsequence/)
## 解法
### 思路
动态规划+hash表
- dp[i][d]：结尾是nums[i]，公差是d的等差数列的个数
- 状态转移方程：dp[i] += dp[j] + 1
  - dp[j]代表i结尾的等差数列的个数，其实就是累加所有以小于i的元素为结尾的等差数列的和
  - 1是指增加的i到j的2个元素的数列，注意的一点是，两个数组成的数列不是等差数列，必须是3个，所以其实dp[j]的值才是nums[i]结尾的等差数列的一个有效值
  - 那既然那个1是无效的，为什么还要累加呢，因为这个1在当前i这里是无效的，因为2个，但对于i后面的元素，又会成为有效的了，所以要累加起来
- 最终返回的结果，是在状态转移过程中，累加的dp值，但这个dp值不包含最后一个元素为结尾的dp值集合
- 需要注意越界的情况，用long代替int
### 代码

```java
class Solution {
  public int numberOfArithmeticSlices(int[] nums) {
    int n = nums.length, ans = 0;
    Map<Long, Integer>[] dp = new Map[n + 1];
    for (int i = 0; i < dp.length; i++) {
      dp[i] = new HashMap<>();
    }

    for (int i = 1; i < n; i++) {
      for (int j = 0; j < i; j++) {
        long d = (long) nums[i] - nums[j];
        int jCount = dp[j].getOrDefault(d, 0);
        Map<Long, Integer> iMap = dp[i];
        ans += jCount;
        iMap.put(d, iMap.getOrDefault(d, 0) + jCount + 1);
      }
    }

    return ans;
  }
}
```
# [LeetCode_516_最长回文子序列](https://leetcode-cn.com/problems/longest-palindromic-subsequence/)
## 解法
### 思路
动态规划：
- dp[i][j]：坐标i和j之间形成子序列中回文串的最大长度
- 状态转移方程：
  - 如果s[i] == s[j]，那么dp[i][j] = dp[i + 1][j - 1] + 2
  - 如果s[i] != s[j]，那么dp[i][j] = max(dp[i + 1][j], dp[i][j - 1])
- base case：dp[i][i] == 1
### 代码
```java
class Solution {
  public int longestPalindromeSubseq(String s) {
    int n = s.length();
    int[][] dp = new int[n][n];

    for (int i = n - 1; i >= 0; i--) {
      dp[i][i] = 1;
      char x = s.charAt(i);
      for (int j = i + 1; j < n; j++) {
        if (s.charAt(j) == x) {
          dp[i][j] = dp[i + 1][j - 1] + 2;
        } else {
          dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
        }
      }
    }

    return dp[0][n - 1];
  }
}
```
# [LeetCode_233_数字1的个数](https://leetcode-cn.com/problems/number-of-digit-one/)
## 解法
### 思路
- 可以把计算过程分割成对每一位可能出现1的情况做枚举，然后将所有位的结果累加
- 每一个位上的结果的计算方式，将整个数分成3部分
  - high：从当前位之后开始的所有高位组成的值，例如1102，当前位是位是0，那么高位就是11
  - cur：当前位对应的值，如上例就是0
  - low：当前位之前的所有低位，如上例就是2
- 以1102为例：
  - 个位为1的情况有（0001，0011，0021，……，1101）共（110+1）*1种
  - 十位为1的情况有（0010，0011，0012，……0019，0110，0111，0112，……0119，1010，1011，1012，……1019）共11*10种
  - 百位为1的情况有（0100，0101，……0199，……1100，1101，1102）共1*100+2+1种
  - 同理千位为1的有102+1种
- 所以总结出来就是：
  - 当前值大于1的时候，(high + 1) * i(位数，个位就是1，十位就是10)
  - 当前值等于0的时候，high * i
  - 当前值等于1的时候，high * i + low + 1
### 代码
```java
class Solution {
    public int countDigitOne(int n) {
        int ans = 0;
        for (int i = 1; i <= n; i *= 10) {
            int high = n / (i * 10),
                low = n % i,
                cur = (n - high * i * 10 - low) / i;

            if (cur == 0) {
                ans += high * i;
            } else if (cur == 1) {
                ans += high * i + 1 + low;
            } else {
                ans += (high + 1) * i;
            }
        }

        return ans;
    }
}
```
# [LeetCode_1446_连续字符](https://leetcode-cn.com/problems/consecutive-characters/)
## 解法
### 思路
- 初始化字符x为字符串第一个字符
- 初始化累加值acc为1，因为第一个字符应该被计数
- 初始化最大值max为1，因为acc一开始最小是1
- 遍历字符串，从第二个字符开始，判断是否和x相等
  - 如果相等累计acc
  - 如果不相等，判断max和acc的大小，暂存较大值为新的max，更新acc为1，更新x为新的字符
- 遍历结束，再比较下acc和max的大小，返回较大值作为结果
### 代码
```java
class Solution {
    public int maxPower(String s) {
        char x = s.charAt(0);
        int acc = 1, max = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == x) {
                acc++;
            } else {
                max = Math.max(max, acc);
                acc = 1;
                x = s.charAt(i);
            }
        }
        return Math.max(acc, max);
    }
}
```
# [LeetCode_1583_统计不开心的朋友](https://leetcode-cn.com/problems/count-unhappy-friends/)
## 解法
### 思路
- 二维数组统计每个人对其他人的好感度的得分
- 遍历pairs，统计每组pair中不开心的人数
  - 外层确定pair，并初始化pair中两个人是否不开心，初始都是false
  - 内层遍历所有pair，如果和外层相同就跳过
  - 根据题目要求确定pair中是否有符合不开心条件的，然后标记
  - 如果内层遍历到所有2个都已经标记为不开心，就终止内层循环
  - 内层判断的时候就利用二维数组来快速查找好感度
### 代码
```java
class Solution {
    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        int[][] dict = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                dict[i][preferences[i][j]] = n - 1 - j;
            }
        }

        int ans = 0;
        for (int i = 0; i < pairs.length; i++) {
            boolean flag1 = false, flag2 = false;
            
            for (int j = 0; j < pairs.length; j++) {
                if (i == j) {
                    continue;
                }

                int[] pairA = pairs[i], pairB = pairs[j];

                int a12 = dict[pairA[0]][pairA[1]],
                    ab11 = dict[pairA[0]][pairB[0]],
                    ab12 = dict[pairA[0]][pairB[1]],
                    a21 = dict[pairA[1]][pairA[0]],
                    ab21 = dict[pairA[1]][pairB[0]],
                    ab22 = dict[pairA[1]][pairB[1]],
                    b12 = dict[pairB[0]][pairB[1]],
                    ba11 = dict[pairB[0]][pairA[0]],
                    ba12 = dict[pairB[0]][pairA[1]],
                    b21 = dict[pairB[1]][pairB[0]],
                    ba21 = dict[pairB[1]][pairA[0]],
                    ba22 = dict[pairB[1]][pairA[1]];
                
                if (!flag1 && a12 < ab11 && b12 < ba11) {
                    flag1 = true;
                    ans++;
                }

                if (!flag1 && a12 < ab12 && b21 < ba21) {
                    flag1 = true;
                    ans++;
                }
                
                if (!flag2 && a21 < ab21 && b12 < ba12) {
                    flag2 = true;
                    ans++;
                }

                if (!flag2 && a21 < ab22 && b21 < ba22) {
                    flag2 = true;
                    ans++;
                }

                if (flag1 && flag2) {
                    break;
                }
            }
        }

        return ans;
    }
}
```
# [LeetCode_576_出界的路径数](https://leetcode-cn.com/problems/out-of-boundary-paths/)
## 失败解法
### 思路
dfs
### 代码
```java
class Solution {
    private int mod = 1000000007;

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        return dfs(m, n, maxMove, 0, startRow, startColumn) % mod;
    }

    private int dfs(int m, int n, int maxMove, int move, int row, int col) {
        if (move > maxMove) {
            return 0;
        }

        if (row < 0 || row >= m || col < 0 || col >= n) {
            return 1;
        }

        return dfs(m, n, maxMove, move + 1, row + 1, col) %  mod+
                dfs(m, n, maxMove, move + 1, row - 1, col) % mod +
                dfs(m, n, maxMove, move + 1, row, col + 1) % mod +
                dfs(m, n, maxMove, move + 1, row, col - 1) % mod;
    }
}
```
## 解法
### 思路
动态规划
- dp[i][j][k]：移动i步，在j行k列有的路径数
  - i：最大值maxMove + 1
  - j：最大值m
  - k：最大值n
- 初始化一个方向二位数组，用于快速模拟在当前位置上的下一步的坐标
- base case：dp[0][startRow][startCol] = 0
- 状态转移方程：
  - 如果j>=0&&j<m&&k>=0&&k<n，则dp[i + 1][j][k] = dp[i][j][k]
  - 如果出界了，ans += dp[i][j][k]
  - 状态转移的时候还需要判断，当前dp元素的值是不是大于0，不大于0代表当前节点没有被遍历到，是不能做状态转移的
- 最终返回ans即可
### 代码
```java
 class Solution {
  private int mod = 1000000007;

  public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
    int[][][] dp = new int[maxMove + 1][m][n];
    int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    int ans = 0;
    dp[0][startRow][startColumn] = 1;
    for (int i = 0; i < maxMove; i++) {
      for (int j = 0; j < m; j++) {
        for (int k = 0; k < n; k++) {
          int path = dp[i][j][k];

          if (path > 0) {
            for (int[] direction : directions) {
              int nextRow = j + direction[0], nextCol = k + direction[1];

              if (nextRow >= 0 && nextRow < m && nextCol >= 0 && nextCol < n) {
                dp[i + 1][nextRow][nextCol] = (dp[i + 1][nextRow][nextCol] + path) % mod;

              } else {
                ans = (ans + path) % mod;
              }
            }
          }
        }
      }
    }

    return ans;
  }
}
```
## 解法二
### 思路
- 在解法一的基础上，发现状态转移过程中，当前状态只依赖前一个状态，所以不需要记录到底是第几步，所以可以省略一个维度
- 但需要注意的是，在状态转移过程中，每一次的移动，其实只是生成了4个新的位置并从老的位置上继承其步数
- 所以在最外层开始遍历的时候，先初始化一个新的dp二位数组，用于记录这一次要生成的新的位置
- 在内部的2层遍历结束后，将整个dp数组更新成循环开始时候的数组
### 代码
```java
class Solution {
private static final int MOD = 1000000007;

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        int ans = 0;
        int[][] dp = new int[m][n];
        int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        dp[startRow][startColumn] = 1;

        for (int i = 0; i < maxMove; i++) {
            int[][] newDp = new int[m][n];
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < n; k++) {
                    int count = dp[j][k];

                    if (count > 0) {
                        for (int[] direction : directions) {
                            int nextR = j + direction[0], nextC = k + direction[1];

                            if (nextR >= 0 && nextR < m && nextC >= 0 && nextC < n) {
                                newDp[nextR][nextC] = (newDp[nextR][nextC] + count) % MOD;
                            } else {
                                ans = (ans + count) % MOD;
                            }
                        }
                    }
                }
            }
            
            dp = newDp;
        }

        return ans;
    }
}
```
# [LeetCode_1480_一堆数组的动态和](https://leetcode-cn.com/problems/running-sum-of-1d-array/)
## 解法
### 思路
前缀和
### 代码
```java
class Solution {
    public int[] runningSum(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return new int[0];
        }
        
        int[] sums = new int[n];
        sums[0] = nums[0];
        for (int i = 1; i < n; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }
        
        return sums;
    }
}
```
# [LeetCode_1450_在既定时间做作业的学生人数](https://leetcode-cn.com/problems/number-of-students-doing-homework-at-a-given-time/)
## 解法
### 思路
- 遍历start数组，和queryTime进行比较，找到比queryTime小的所有坐标，记录下来
- 遍历坐标列表，用遍历到的坐标到到end数组中找到元素，与queryTime进行比较，找到小于等于endTime数组元素的坐标并计数
- 返回第二次遍历的计数值
### 代码
```java
class Solution {
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < startTime.length; i++) {
            if (startTime[i] <= queryTime) {
                list.add(i);
            }
        }
        
        int ans = 0;
        for (int index : list) {
            if (endTime[index] >= queryTime) {
                ans++;
            }
        }
        
        return ans;
    }
}
```
# [LeetCode_1455_检查单词是否为句中其他单词的前缀](https://leetcode-cn.com/problems/check-if-a-word-occurs-as-a-prefix-of-any-word-in-a-sentence/)
## 解法
### 思路
- 用空格分割字符串
- 遍历分割后的单词，比较是否searchWord是否是该单词的前缀，如果是就返回坐标
### 代码
```java
class Solution {
    public int isPrefixOfWord(String sentence, String searchWord) {
        String[] arr = sentence.split(" ");
        int index = 1;
        for (String word : arr) {
            if (word.startsWith(searchWord)) {
                return index;
            }
            index++;
        }
        
        return -1;
    }
}
```
# [LeetCode_1460_通过翻转子数组使两个数组相等](https://leetcode-cn.com/problems/make-two-arrays-equal-by-reversing-sub-arrays/)
## 解法
### 思路
- 只要两个数组的数值对应的个数都相等，就能翻转成功
- 对2个数组的元素计数并统计，比较是否一致
### 代码
```java
class Solution {
    public boolean canBeEqual(int[] target, int[] arr) {
        int[] bucket = new int[1001];
        for (int i = 0; i < target.length; i++) {
            bucket[target[i]]++;
            bucket[arr[i]]--;
        }
        
        for (int num : bucket) {
            if (num != 0) {
                return false;
            }
        }
        
        return true;
    }   
}
```
# [LeetCode_1464_数组中两元素的最大乘积](https://leetcode-cn.com/problems/maximum-product-of-two-elements-in-an-array/)
## 解法
### 思路
- 遍历数组，找到最大的2个元素
- 计算他们各自-1之后的乘积
### 代码
```java
class Solution {
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE, sec = Integer.MIN_VALUE;
        for (int num : nums) {
            if (sec == Integer.MIN_VALUE) {
                sec = num;
                continue;
            }

            if (max == Integer.MIN_VALUE) {
                max = Math.max(sec, num);
                sec = Math.min(sec, num);
                continue;
            }

            if (num > max) {
                sec = max;
                max = num;
                continue;
            }

            if (num > sec) {
                sec = num;
            }
        }
        
        return (max - 1) * (sec - 1);
    }
}
```
# [LeetCode_1469_寻找所有独生节点](https://leetcode-cn.com/problems/find-all-the-lonely-nodes/)
## 解法
### 思路
dfs
### 代码
```java
class Solution {
    public List<Integer> getLonelyNodes(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list, false);
        return list;
    }

    private void dfs(TreeNode node, List<Integer> list, boolean single) {
        if (node == null) {
            return;
        }
        
        if (single) {
            list.add(node.val);
        }
        
        single = node.left == null || node.right == null;

        dfs(node.left, list, single);
        dfs(node.right, list, single);
    } 
}
```