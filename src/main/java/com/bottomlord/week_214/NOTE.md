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
# [LeetCode_1681_最小不兼容性](https://leetcode.cn/problems/minimum-incompatibility/)
## 解法
### 思路
- 算法概念：
  - `n`：`nums`数组的长度
  - `k`：`nums`数组要被且分成子数组的个数
  - `m`：每个子数组的长度，`m = n / k`
  - `g[j]`：j状态（二进制位对应nums坐标）的子集对应的不兼容性值
  - `f[i]`：i状态（二进制位对应nums坐标）的最小不兼容性
- 算法过程
  - 通过n和k得到m：`m = n / k`
  - 初始化数组`g[]`，g的长度为`2ⁿ`，表示n位坐标选择或不选择的所有可能状态
  - 填充g数组内容
    - 将g数组的所有制先填充为-1，用来表示如果当前坐标对应的状态不符合题目要求，则可以通过-1来判定
    - 循环条件：从1开始遍历`2ⁿ`个元素，每一个遍历到的坐标值`j`对应的就是g数组的状态值
    - 判断二进制位上是否有m个1，如果没有，说明没有选择m个数字，子集元素个数不符合
    - 初始化布尔数组用来去重
    - 遍历当前`j`上所有位上为1的位对应的nums元素，将其放入布尔数组中，如果之前已经放入过，说明重复了，不符合题目要求，直接终止循环
    - 遍历过程中维护当前状态里的最大值`max`和最小值`min`
    - 遍历结束后，如果元素个数不足m个，那么就说明当前状态不符合题目要求，遍历到下一个状态，否则就求出最大和最小值的差值，记录在g数组中：`g[j] = max - min`
  - 初始化数组`f[]`，f的长度与g相同
    - `f[0] = 0`
    - `f[]`其他元素初始化为int最大值
  - `f[]`实际就是动态规划的记事本
    - `f[i]`：表示i状态下的最小不兼容性，之前算法概念中有介绍
    - 状态转移方程：`f[i | j] = min(f[i | j], f[i] + g[j])`，j状态对应的二进制位上的1与i状态对应的为1的二进制一定是不相交的。方程实际代表，在当前状态下，增加一个可用的子集时候，i与j形成的新的状态的最小值，从而形成了状态转移
    - 初始状态：如上面描述的过程逻辑，将f[0]设置为0，代表没有状态的时候，最小值是0，其他状态为int最大值，代表还没有推导到当前状态
    - 最终结果：`f[2ⁿ - 1]`，如果是int最大值，则代表没有复合要求的可能，返回-1即可
  - 遍历所有2ⁿ状态
  - 如果当前状态是int最大值，说明当前状态还没有被推导过，要么答案为-1，要么当前状态无需推导，所以直接跳过
  - 初始化一个布尔数组，用来记录通过状态获取的不重复元素值的个数
  - 初始化一个mask整数，用来记录当前状态对应的不重复元素的二进制状态表示，之后需要通过这个mask来枚举当前状态下可以选择元素，并生成所有可能的子集
  - 遍历nums数组
    - 如果坐标在当前i状态中为未选择状态，则跳过
    - 如果坐标对应的元素值在布尔数组中存在，说明已经选择过相同值的元素，也跳过
    - 将元素值在布尔数组的对应坐标翻转为true
    - 将当前坐标位记录在mask的二进制位上
  - 遍历结束后，判断布尔数组中为true的元素个数是否不小于m，如果小于，说明剩下的可用元素不可能组成子集，当前状态不通，直接跳过
  - 遍历所有mask状态下的子集，遍历的方式是递减mask值，同时与mask相与之后，不能等于0，如果等于0了，说明当前状态没有任何为1的位是和mask一样的，不是mask的子集，直接跳过
  - 判断获得的子集状态在g数组中是否是-1，如果是就说明当前状态是不合法的，那么跳过
  - 否则就通过状态转移方程推导新的状态：`f[i | j] = min(f[i | j], f[i] + g[j])`
  - 遍历结束后如果`f[2ⁿ - 1]`为int最大值，返回-1，否则返回`f[2ⁿ - 1]`
### 代码
```java
class Solution {
    public int minimumIncompatibility(int[] nums, int k) {
        int n = nums.length, m = n / k;
        int[] g = new int[1 << n];
        Arrays.fill(g, -1);
        for (int i = 1; i < 1 << n; i++) {
            if (Integer.bitCount(i) != m) {
                continue;
            }

            boolean[] memo = new boolean[n + 1];
            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            for (int j = 0; j < n; j++) {
                if ((i >> j & 1) == 1) {
                    if (memo[nums[j]]) {
                        continue;
                    }

                    memo[nums[j]] = true;
                    min = Math.min(min, nums[j]);
                    max = Math.max(max, nums[j]);
                }
            }

            if (count(memo) == m) {
                g[i] = max - min;
            }
        }

        int[] f = new int[1 << n];
        Arrays.fill(f, Integer.MAX_VALUE);
        f[0] = 0;
        for (int i = 0; i < 1 << n; i++) {
            if (f[i] == Integer.MAX_VALUE) {
                continue;
            }

            boolean[] memo = new boolean[n + 1];
            int mask = 0;
            for (int j = 0; j < n; j++) {
                if ((1 << j & i) == 0 && !memo[nums[j]]) {
                    memo[nums[j]] = true;
                    mask |= 1 << j;
                }
            }

            if (count(memo) < m) {
                continue;
            }

            for (int j = mask; j > 0; j = (j - 1) & mask) {
                if (g[j] == -1) {
                    continue;
                }

                f[i | j] = Math.min(f[i | j], f[i] + g[j]);
            }
        }

        return f[(1 << n) - 1] == Integer.MAX_VALUE ? -1 : f[(1 << n) - 1];
    }

    private int count(boolean[] arr) {
        int c = 0;
        for (boolean b : arr) {
            if (b) {
                c++;
            }
        }
        return c;
    }
}
```