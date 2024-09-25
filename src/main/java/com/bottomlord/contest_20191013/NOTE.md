# Contest_1_分割平衡字符串
## 题目
在一个「平衡字符串」中，'L' 和 'R' 字符的数量是相同的。

给出一个平衡字符串 s，请你将它分割成尽可能多的平衡字符串。

返回可以通过分割得到的平衡字符串的最大数量。

示例 1：
```
输入：s = "RLRRLLRLRL"
输出：4
解释：s 可以分割为 "RL", "RRLL", "RL", "RL", 每个子字符串中都包含相同数量的 'L' 和 'R'。
```
示例 2：
```
输入：s = "RLLLLRRRLR"
输出：3
解释：s 可以分割为 "RL", "LLLRRR", "LR", 每个子字符串中都包含相同数量的 'L' 和 'R'。
```
示例 3：
```
输入：s = "LLLLRRRR"
输出：1
解释：s 只能保持原样 "LLLLRRRR".
```
提示：
```
1 <= s.length <= 1000
s[i] = 'L' 或 'R'
```
## 解法
### 思路
- 遍历数组，遇到`L`或`R`计数，然后判断是否count一样
    - 如果是，就在结果上++并将`L`和`R`的count值清零。
- 遍历结束，返回结果
### 代码
```java
class Solution {
    public int balancedStringSplit(String s) {
        int l = 0, r = 0, count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'L') {
                l++;
            } else {
                r++;
            }

            if (l == r && l != 0) {
                count++;
                l = 0;
                r = 0;
            }
        }
        
        return count;
    }
}
```
# Contest_2_可以攻击国王的皇后
## 题目
在一个 8x8 的棋盘上，放置着若干「黑皇后」和一个「白国王」。

「黑皇后」在棋盘上的位置分布用整数坐标数组 queens 表示，「白国王」的坐标用数组 king 表示。

「黑皇后」的行棋规定是：横、直、斜都可以走，步数不受限制，但是，不能越子行棋。

请你返回可以直接攻击到「白国王」的所有「黑皇后」的坐标（任意顺序）。

示例 1：
```
输入：queens = [[0,1],[1,0],[4,0],[0,4],[3,3],[2,4]], king = [0,0]
输出：[[0,1],[1,0],[3,3]]
解释： 
[0,1] 的皇后可以攻击到国王，因为他们在同一行上。 
[1,0] 的皇后可以攻击到国王，因为他们在同一列上。 
[3,3] 的皇后可以攻击到国王，因为他们在同一条对角线上。 
[0,4] 的皇后无法攻击到国王，因为她被位于 [0,1] 的皇后挡住了。 
[4,0] 的皇后无法攻击到国王，因为她被位于 [1,0] 的皇后挡住了。 
[2,4] 的皇后无法攻击到国王，因为她和国王不在同一行/列/对角线上。
```
示例 2：
```
输入：queens = [[0,0],[1,1],[2,2],[3,4],[3,5],[4,4],[4,5]], king = [3,3]
输出：[[2,2],[3,4],[4,4]]
```
示例 3：
```
输入：queens = [[5,6],[7,7],[2,1],[0,7],[1,6],[5,1],[3,7],[0,3],[4,0],[1,2],[6,3],[5,0],[0,4],[2,2],[1,1],[6,4],[5,4],[0,0],[2,6],[4,5],[5,2],[1,4],[7,5],[2,3],[0,5],[4,2],[1,0],[2,7],[0,1],[4,6],[6,1],[0,6],[4,3],[1,7]], king = [3,4]
输出：[[2,3],[1,4],[1,6],[3,7],[4,3],[5,4],[4,5]]
```
提示：
```
1 <= queens.length <= 63
queens[0].length == 2
0 <= queens[i][j] < 8
king.length == 2
0 <= king[0], king[1] < 8
一个棋盘格上最多只能放置一枚棋子。
```
## 解法
### 思路
- 遍历所有皇后坐标
- 从该皇后坐标开始从8个方向递归
    - 如果越界返回
    - 如果遇到皇后返回
    - 如果遇到国王，将结果放入list，返回
### 代码
```java
class Solution {
    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int[] queen : queens) {
            recurse(queen[0], queen[1] + 1, 0, 1, queens, king, ans, queen);
            recurse(queen[0], queen[1] - 1, 0, -1, queens, king, ans, queen);
            recurse(queen[0] + 1, queen[1], 1, 0, queens, king, ans, queen);
            recurse(queen[0] - 1, queen[1], -1, 0, queens, king, ans, queen);
            recurse(queen[0] + 1, queen[1] + 1, 1, 1, queens, king, ans, queen);
            recurse(queen[0] - 1, queen[1] + 1, -1, 1, queens, king, ans, queen);
            recurse(queen[0] + 1, queen[1] - 1, 1, -1, queens, king, ans, queen);
            recurse(queen[0] - 1, queen[1] - 1, -1, -1, queens, king, ans, queen);
        }

        return ans;
    }

    private void recurse(int x, int y, int dx, int dy, int[][] queens, int[] kings, List<List<Integer>> ans, int[] queen) {
        if (x >= 8 || x < 0 || y >= 8 || y < 0) {
            return;
        }

        for (int[] q : queens) {
            if (q[0] == x && q[1] == y) {
                return;
            }
        }
        
        if (kings[0] == x && kings[1] == y) {
            List<Integer> list = new ArrayList<>();
            list.add(queen[0]);
            list.add(queen[1]);
            ans.add(list);
            return;
        }
        
        recurse(x + dx, y + dy, dx, dy, queens, kings, ans, queen);
    }
}
```
# Contest_3_掷色子模拟
## 题目
有一个骰子模拟器会每次投掷的时候生成一个 1 到 6 的随机数。

不过我们在使用它时有个约束，就是使得投掷骰子时，连续 掷出数字 i 的次数不能超过 rollMax[i]（i 从 1 开始编号）。

现在，给你一个整数数组 rollMax 和一个整数 n，请你来计算掷 n 次骰子可得到的不同点数序列的数量。

假如两个序列中至少存在一个元素不同，就认为这两个序列是不同的。由于答案可能很大，所以请返回 模 10^9 + 7 之后的结果。

示例 1：
```
输入：n = 2, rollMax = [1,1,2,2,2,3]
输出：34
解释：我们掷 2 次骰子，如果没有约束的话，共有 6 * 6 = 36 种可能的组合。但是根据 rollMax 数组，数字 1 和 2 最多连续出现一次，所以不会出现序列 (1,1) 和 (2,2)。因此，最终答案是 36-2 = 34。
```
示例 2：
```
输入：n = 2, rollMax = [1,1,1,1,1,1]
输出：30
```
示例 3：
```
输入：n = 3, rollMax = [1,1,1,2,2,3]
输出：181
```
提示：
```
1 <= n <= 5000
rollMax.length == 6
1 <= rollMax[i] <= 15
```
## 失败解法
### 失败原因
审题不清，没看到是连续一致，以为是回溯了。
### 思路
回溯算法，递归下去
### 代码
```java
public class Contest_3_ {
    private long sum;
    public int dieSimulator(int n, int[] rollMax) {
        backTrack(n, rollMax);
        return (int)(sum % 10000000007);
    }

    private void backTrack(int level, int[] rollMax) {
        if (level == 0) {
            sum++;
        }

        for (int i = 0; i < 6; i++) {
            if (rollMax[i] == 0) {
                continue;
            }

            rollMax[i]--;
            backTrack(level - 1, rollMax);
            rollMax[i]++;
        }
    }
}
```
## 失败解法二
### 失败原因
时间超限
### 思路
用一个数组记录上一次的连续值，然后递归判断是否超过限制，如果超过就不再这一条路径上继续递归。20次时耗时爆炸了
### 代码
```java
class Solution {
    private long sum;
    public int dieSimulator(int n, int[] rollMax) {
        recurse(n, -1, new int[6], rollMax);
        return (int)(sum % 1000000007L);
    }

    private void recurse(int level, int pre, int[] record, int[] rollMax) {
        if (level == 0) {
            sum++;
            return;
        }

        for (int i = 0; i < 6; i++) {
            if (pre == i) {
                if (record[i] == 0) {
                    int[] tmp = new int[6];
                    tmp[i]++;
                    recurse(level - 1, i, tmp, rollMax);
                } else {
                    if (rollMax[i] != record[i]) {
                        record[i]++;
                        recurse(level - 1, i, record, rollMax);
                    }
                }
            } else {
                int[] tmp = new int[6];
                tmp[i]++;
                recurse(level - 1, i, tmp, rollMax);
            }
        }
    }
}
```
## 解法
### 思路
动态规划：
- `dp[n][i][j]`：投掷n次情况下，连续j个i结尾的序列的个数
- base case：`dp[0][i][1]`第一次投掷以i结尾的数的序列
- 状态转移方程：
    - 如果元素和上一个元素等：`dp[n][i][j + 1]` += `dp[n][i][j]`
    - 如果元素和上一个元素不等：`dp[n][i][1]` += dp[n][i][j]`
- 过程：
    - 先初始化，将投掷第一次的6种值的那1个序列赋给dp
    - 4层循环遍历：
        - 第一层代表投掷次数
        - 第二层代表当前投掷的值
        - 第三层代表上一次投掷的值
        - 第四层循环前判断：
            - 如果二、三层值相同，代表转移方程的第一种，循环第三层元素对应rollMax值次数，进行状态转移
            - 如果二、三层值不同，代表转移方程的第二种，循环第三层元素对应rollMax值-1次，进行状态转移，因为不能超过
    - 遍历结束后，进行嵌套循环算出答案：
        - 第一层遍历6种值的可能
        - 第二层遍历第一层值对应的rollMax个数
        - 将这个找到的值累加
### 代码
```java
class Solution {
    public int dieSimulator(int n, int[] rollMax) {
        int[][][] dp = new int[n][6][16];
        int mod = 1000000007;

        for (int i = 0; i < 6; i++) {
            dp[0][i][1] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 6; k++) {
                    if (j != k) {
                        for (int l = 1; l <= rollMax[k]; l++) {
                            dp[i][j][1] += dp[i - 1][k][l];
                            dp[i][j][1] %= mod;
                        }
                    } else {
                        for (int l = 1; l < rollMax[k]; l++) {
                            dp[i][j][l + 1] += dp[i - 1][k][l];
                            dp[i][j][l + 1] %= mod;
                        }
                    }
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 1; j <= rollMax[i]; j++) {
                ans += dp[n - 1][i][j];
                ans %= mod;
            }
        }

        return ans;
    }
}
```
# Contest_4_最大相等频率
## 题目
给出一个正整数数组 nums，请你帮忙从该数组中找出能满足下面要求的 最长 前缀，并返回其长度：
```
从前缀中 删除一个 元素后，使得所剩下的每个数字的出现次数相同。
如果删除这个元素后没有剩余元素存在，仍可认为每个数字都具有相同的出现次数（也就是 0 次）。
```
示例 1：
```
输入：nums = [2,2,1,1,5,3,3,5]
输出：7
解释：对于长度为 7 的子数组 [2,2,1,1,5,3,3]，如果我们从中删去 nums[4]=5，就可以得到 [2,2,1,1,3,3]，里面每个数字都出现了两次。
```
示例 2：
```
输入：nums = [1,1,1,2,2,2,3,3,3,4,4,4,5]
输出：13
```
示例 3：
```
输入：nums = [1,1,1,2,2,2]
输出：5
```
示例 4：
```
输入：nums = [10,2,8,9,3,8,1,5,2,3,7,6]
输出：8
```
提示：
```
2 <= nums.length <= 10^5
1 <= nums[i] <= 10^5
```
## 解法
### 思路
- 定义四个变量：
    - `count[i]`：出现i次的元素个数
    - `map`：i元素出现的个数
- 遍历nums数组：
    - 统计每种元素出现的个数，直接`count[num[i]]++`
    - 统计出现n次的元素的个数，判断`count[num[i]]`是否大于1：
        - 如果是，说明之前在map里已经把这个元素算在了`count[num[i]] - 1`这个key里，需要去掉，因为现在它要被算在现在这个次数的key里，如果减掉之后值变成0了，那么就把这个key从map中去掉
        - 如果不是，就说明是第一次统计，直接放入map就可以了
    - 之后就对`count[i]`和`map`这两个变量进行判断，以确定当前序列是否是符合题意的：
        - map的size是1的情况下：说明元素出现的次数只有1中情况
            - 如果map的key等于1，说明序列元素出现的个数都是1，这时候删除一个元素是符合规则的
            - 如果map的这个唯一key的value等于1，说明这个序列的所有元素都是一样的，这是后删除一个元素也是符合规则的
        - map的size是2的情况下：说明元素出现个数的情况有两种
            - 如果有key等于1，并且对应的value也等于1，说明有1个元素出现了1次，这种情况下是符合规则的
            - 如果有key的情况不等于1，但是key值比另一个key大1，且value值是1，说明有一个元素的出现个数比其他元素多1，这种情况也是符合规则的
        - 在如上2种大情况4中情况下，可以给ans赋值当前遍历的下标值+1
- 遍历结束，返回结果ans
### 代码
```java
class Solution {
    public int maxEqualFreq(int[] nums) {
        int[] count = new int[100010];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int ans = 2;
        for (int i = 0; i < nums.length; i++) {
            count[nums[i]]++;
            if (count[nums[i]] != 1) {
                map.put(count[nums[i]] - 1, map.get(count[nums[i]] - 1) - 1);
                if (map.get(count[nums[i]] - 1) == 0) {
                    map.remove(count[nums[i]] - 1);
                }
            }
            map.put(count[nums[i]], map.getOrDefault(count[nums[i]], 0) + 1);

            if (map.size() == 1) {
                if (map.firstKey() == 1) {
                    ans = i + 1;
                } else if (map.firstEntry().getValue() == 1) {
                    ans = i + 1;
                }
            } else if (map.size() == 2) {
                if (map.firstKey() == 1 && map.firstEntry().getValue() == 1) {
                    ans = i + 1;
                } else if (map.firstKey() + 1 == map.lastKey() && map.lastEntry().getValue() == 1) {
                    ans = i + 1;
                }
            }
        }

        return ans;
    }
}
```