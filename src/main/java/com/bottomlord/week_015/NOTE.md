# LeetCode_647_回文子串
## 题目
给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。

具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。

示例 1:
```
输入: "abc"
输出: 3
解释: 三个回文子串: "a", "b", "c".
```
示例 2:
```
输入: "aaa"
输出: 6
说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa".
```
注意:
```
输入的字符串长度不会超过1000。
```
## 解法
### 思路
中心扩散
- 遍历字符串，以当前元素为中心扩散
- 扩散过程中判断当前范围的字符串是否是回文串，是的话就累加，否则终止
    - 字符串总长度为奇数
    - 字符串总长度为偶数
### 代码
```java
class Solution {
    private int sum;

    public int countSubstrings(String s) {
        for (int i = 0; i < s.length(); i++) {
            spread(s, i, i);
            spread(s, i, i + 1);
        }
        return sum;
    }

    private void spread(String s, int start, int end) {
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            sum++;
            start--;
            end++;
        }
    }
}
```
## 解法二
### 思路
动态规划：
- dp[i][j]存储字符串中起始i结尾j范围的子串是否是回文
- 状态转移方程：dp[i][j] = s[i] == s[j] && (dp[i + 1][j - 1] || j - i <= 2)(如果子字符串长度不超过2，那两个字符相等的情况下就一定是回文串)
- base case：`i == j`的情况下都是true
### 代码
```java
class Solution {
    public int countSubstrings(String s) {
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        int count = 0;
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1]);
                if(dp[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }
}
```
# LeetCode_318_最大单词长度乘积
## 题目
给定一个字符串数组 words，找到 length(word[i]) * length(word[j]) 的最大值，并且这两个单词不含有公共字母。你可以认为每个单词只包含小写字母。如果不存在这样的两个单词，返回 0。

示例 1:
```
输入: ["abcw","baz","foo","bar","xtfn","abcdef"]
输出: 16 
解释: 这两个单词为 "abcw", "xtfn"。
```
示例 2:
```
输入: ["a","ab","abc","d","cd","bcd","abcd"]
输出: 4 
解释: 这两个单词为 "ab", "cd"。
```
示例 3:
```
输入: ["a","aa","aaa","aaaa"]
输出: 0 
解释: 不存在这样的两个单词。
```
## 解法
### 思路
- 遍历字符串，将字母对应到位上
- 嵌套遍历字符串，对比两个字符串对应的位值相与是否是0，如果是就计算乘积
- 把计算出的乘积和已有的最大值比较，取最大
- 遍历结束后返回
### 代码
```java
class Solution {
    public int maxProduct(String[] words) {
        int len = words.length;
        int[] arr = new int[len];
        
        for (int i = 0; i < len; i++) {
            String word = words[i];
            for (int j = 0; j < word.length(); j++) {
                arr[i] |= 1 << (word.charAt(j) - 'a');
            }
        }
        
        int max = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if ((arr[i] & arr[j]) == 0) {
                    max = Math.max(words[i].length() * words[j].length(), max);
                }
            }
        }
        return max;
    }
}
```
# LeetCode_313_超级丑数
## 题目
编写一段程序来查找第 n 个超级丑数。

超级丑数是指其所有质因数都是长度为 k 的质数列表 primes 中的正整数。

示例:
```
输入: n = 12, primes = [2,7,13,19]
输出: 32 
解释: 给定长度为 4 的质数列表 primes = [2,7,13,19]，前 12 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,28,32] 。
```
说明:
```
1 是任何给定 primes 的超级丑数。
 给定 primes 中的数字以升序排列。
0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000 。
第 n 个超级丑数确保在 32 位有符整数范围内。
```
## 解法
### 思路
动态规划：
- dp[i]：存储第i+1个超级丑数
- pos[i]：存储第i下标对应的prime值，用于和dp[pos[i]]值进行乘积
- 如果当前下标对应的素数得到的乘积是所有可能中的最小值，那么当前下标对应值+1，代表素数与后一个dp值相乘
### 代码
```java
class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] dp = new int[n];
        dp[0] = 1;
        int[] pos = new int[primes.length];
        
        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++) {
                min = Math.min(min, dp[pos[j]] * primes[j]);
            }
            
            for (int j = 0; j < primes.length; j++) {
                if (min == dp[pos[j]] * primes[j]) {
                    pos[j]++;
                    dp[i] = min;
                }
            }
        }
        return dp[n - 1];
    }
}
```
# LeetCode_714_买卖股票的最佳时机含手续费
## 题目
给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。

你可以无限次地完成交易，但是你每次交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。

返回获得利润的最大值。

示例 1:
```
输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
输出: 8
解释: 能够达到的最大利润:  
在此处买入 prices[0] = 1
在此处卖出 prices[3] = 8
在此处买入 prices[4] = 4
在此处卖出 prices[5] = 9
总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
```
注意:
```
0 < prices.length <= 50000.
0 < prices[i] < 50000.
0 <= fee < 50000.
```
## 解法
### 思路
动态规划：
- 维护两个变量：
    - cash：手拿现金时的利润最大值
    - hold：手拿股票时的利润最大值
- 状态转移方程：
    - `cash = Math.max(cash, hold + prices[i] - fee)`
    - `hold = Math.max(hold, cash - prices[i])`
- base case：
    - cash = 0
    - hold = -prices[i]
- 过程：循环遍历prices，动态规划cash和hold，并在遍历结束后返回cash
### 代码
```java
class Solution {
    public int maxProfit(int[] prices, int fee) {
        int cash = 0, hold = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            cash = Math.max(cash, hold + prices[i] - fee);
            hold = Math.max(hold, cash - prices[i]);
        }
        return cash;
    }
}
```
# LeetCode_931_下降路径最小和
## 题目
给定一个方形整数数组 A，我们想要得到通过 A 的下降路径的最小和。

下降路径可以从第一行中的任何元素开始，并从每一行中选择一个元素。在下一行选择的元素和当前行所选元素最多相隔一列。

示例：
```
输入：[[1,2,3],[4,5,6],[7,8,9]]
输出：12
解释：
可能的下降路径有：
[1,4,7], [1,4,8], [1,5,7], [1,5,8], [1,5,9]
[2,4,7], [2,4,8], [2,5,7], [2,5,8], [2,5,9], [2,6,8], [2,6,9]
[3,5,7], [3,5,8], [3,5,9], [3,6,8], [3,6,9]
和最小的下降路径是 [1,4,7]，所以答案是 12。
```
提示：
```
1 <= A.length == A[0].length <= 100
-100 <= A[i][j] <= 100
```
## 解法
### 思路
动态规划：
- `dp[i][j]`存储的是到达当前元素的最小路径值
- base case：`dp[0][i] = A[0][i]`，二维数组第一行的元素值
### 代码
```java
class Solution {
    public int minFallingPathSum(int[][] A) {
       int len = A.length;
        int[][] dp = new int[len][len];
        System.arraycopy(A[0], 0, dp[0], 0, len);
        
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < len; j++) {
                int left = j - 1 >= 0 ? dp[i - 1][j - 1] : Integer.MAX_VALUE;
                int right = j + 1 < len ? dp[i - 1][j + 1] : Integer.MAX_VALUE;
                dp[i][j] = Math.min(dp[i - 1][j], Math.min(left, right)) + A[i][j];
            }
        }
        
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            min = Math.min(dp[len - 1][i], min);
        }
        
        return min; 
    }
}
```
# LeetCode_841_钥匙和房间
## 题目
有 N 个房间，开始时你位于 0 号房间。每个房间有不同的号码：0，1，2，...，N-1，并且房间里可能有一些钥匙能使你进入下一个房间。

在形式上，对于每个房间 i 都有一个钥匙列表 rooms[i]，每个钥匙 rooms[i][j] 由 [0,1，...，N-1] 中的一个整数表示，其中 N = rooms.length。 钥匙 rooms[i][j] = v 可以打开编号为 v 的房间。

最初，除 0 号房间外的其余所有房间都被锁住。

你可以自由地在房间之间来回走动。

如果能进入每个房间返回 true，否则返回 false。

示例 1：
```
输入: [[1],[2],[3],[]]
输出: true
解释:  
我们从 0 号房间开始，拿到钥匙 1。
之后我们去 1 号房间，拿到钥匙 2。
然后我们去 2 号房间，拿到钥匙 3。
最后我们去了 3 号房间。
由于我们能够进入每个房间，我们返回 true。
```
示例 2：
```
输入：[[1,3],[3,0,1],[2],[0]]
输出：false
解释：我们不能进入 2 号房间。
```
提示：
```
1 <= rooms.length <= 1000
0 <= rooms[i].length <= 1000
所有房间中的钥匙数量总计不超过 3000。
```
## 解法
### 思路
dfs：
- 创建一个`boolean[]`作为结果的存储对象
- 从list的下标0元素开始下钻
- 递归逻辑中先判断当前房间是否被访问过，如果是就返回，否则会栈溢出
- 将当前房间对应的结果元素置为true
- 遍历list中当前元素的钥匙集合，递归到下一层
- 递归结束，遍历boolean数组，如果有false就返回false，否则遍历结束返回true
### 代码
```java
class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] ans = new boolean[rooms.size()];
        dfs(0, rooms, ans);
        for (int i = 1; i < ans.length; i++) {
            if (!ans[i]) {
                return false;
            }
        }
        return true;
    }

    private void dfs(int room, List<List<Integer>> list, boolean[] ans) {
        if (ans[room]) {
            return;
        }

        ans[room] = true;
        
        for (Integer key : list.get(room)) {
            dfs(key, list, ans);
        }
    }
}
```
# LeetCode_413_等差数列划分
## 题目
如果一个数列至少有三个元素，并且任意两个相邻元素之差相同，则称该数列为等差数列。

例如，以下数列为等差数列:
```
1, 3, 5, 7, 9
7, 7, 7, 7
3, -1, -5, -9
```
以下数列不是等差数列。
```
1, 1, 2, 5, 7
```
数组 A 包含 N 个数，且索引从0开始。数组 A 的一个子数组划分为数组 (P, Q)，P 与 Q 是整数且满足 0<=P<Q<N 。

如果满足以下条件，则称子数组(P, Q)为等差数组：

元素 A[P], A[p + 1], ..., A[Q - 1], A[Q] 是等差的。并且 P + 1 < Q 。

函数要返回数组 A 中所有为等差数组的子数组个数。

示例:
```
A = [1, 2, 3, 4]

返回: 3, A 中有三个子等差数组: [1, 2, 3], [2, 3, 4] 以及自身 [1, 2, 3, 4]。
```
## 解法
### 思路
暴力循环比较，三层循环
- 第一层确定起始下标
- 第二层确定结束下标
- 第三层进行每两个元素的比较
### 代码
```java
class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        int count = 0, len = A.length;
        for (int start = 0; start < len - 2; start++) {
            int diff = A[start + 1] - A[start];
            for (int end = start + 2; end < len; end++) {
                int index = start + 1;
                for (; index <= end; index++) {
                    if (A[index] - A[index - 1] != diff) {
                        break;
                    }
                }
                
                if (index > end) {
                    count++;
                }
            }
        }
        return count;
    }
}
```
## 优化代码
### 思路
在暴力解法的基础上，进行改进：
- 在`end`下标累加的过程中，`index`坐标重复的计算了上一个循环计算过的比较diff的过程。
- 而如果前一个子序列是等差数列，那么只需要`A[end] - A[end - 1]`和`A[end + 1] - A[end]`进行比较就可以判断`count`是否需要增加
### 代码
```java
class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        int count = 0, len = A.length;
        for (int start = 0; start < len - 2; start++) {
            int diff = A[start + 1] - A[start];
            for (int end = start + 2; end < len; end++) {
                if (A[end] - A[end - 1] == diff) {
                    count++;
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
- `dp[i]`存储的是在`A[x, i]`范围且不在`A[x,j]`范围(j < i)的所有等差数列个数，也就是求一个`A[x,i]`与`A[x,j]`的补集
- base case：dp[2]的值取决于前三个元素是否是等差数列，如果是就是1否则是0
- 状态转移方程：
    - `A[i] - A[i - 1] == A[i - 1] - A[i - 2]`：`dp[i] = dp[i - 1] + 1`
    - `A[i] - A[i - 1] != A[i - 1] - A[i - 2]`：0
- 结果返回：遍历累加dp[i]的值
### 代码
```java
class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        int ans = 0, len = A.length;
        int[] dp = new int[len];
        for (int i = 2; i < len; i++) {
            dp[i] = A[i] - A[i - 1] == A[i - 1] - A[i - 2] ? dp[i - 1] + 1 : 0;
            ans += dp[i];
        }
        return ans;
    }
}
```
# LeetCode_529_扫雷游戏
## 题目
让我们一起来玩扫雷游戏！

给定一个代表游戏板的二维字符矩阵。 'M' 代表一个未挖出的地雷，'E' 代表一个未挖出的空方块，'B' 代表没有相邻（上，下，左，右，和所有4个对角线）地雷的已挖出的空白方块，数字（'1' 到 '8'）表示有多少地雷与这块已挖出的方块相邻，'X' 则表示一个已挖出的地雷。

现在给出在所有未挖出的方块中（'M'或者'E'）的下一个点击位置（行和列索引），根据以下规则，返回相应位置被点击后对应的面板：
```
如果一个地雷（'M'）被挖出，游戏就结束了- 把它改为 'X'。
如果一个没有相邻地雷的空方块（'E'）被挖出，修改它为（'B'），并且所有和其相邻的方块都应该被递归地揭露。
如果一个至少与一个地雷相邻的空方块（'E'）被挖出，修改它为数字（'1'到'8'），表示相邻地雷的数量。
如果在此次点击中，若无更多方块可被揭露，则返回面板。
```
示例 1：
```
输入: 

[['E', 'E', 'E', 'E', 'E'],
 ['E', 'E', 'M', 'E', 'E'],
 ['E', 'E', 'E', 'E', 'E'],
 ['E', 'E', 'E', 'E', 'E']]

Click : [3,0]

输出: 

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]
```
示例 2：
```
输入: 

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

Click : [1,2]

输出: 

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'X', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]
```
注意：
```
输入矩阵的宽和高的范围为 [1,50]。
点击的位置只能是未被挖出的方块 ('M' 或者 'E')，这也意味着面板至少包含一个可点击的方块。
输入面板不会是游戏结束的状态（即有地雷已被挖出）。
简单起见，未提及的规则在这个问题中可被忽略。例如，当游戏结束时你不需要挖出所有地雷，考虑所有你可能赢得游戏或标记方块的情况。
```
## 解法
### 思路
递归遍历：
从click元素处开始做如下遍历动作：
- 如果遍历到的元素为M，赋值为X，返回
- 如果遍历到的坐标越界，返回
- 如果遍历到的坐标元素不是E，返回
- 如果遍历到的坐标上下左右有地雷，计算地雷个数，赋值为地雷个数，返回
- 如果遍历到的坐标上下左右没有地雷，赋值为B，继续8个方向遍历
### 代码
```java
class Solution {
    private int[][] directions = {{1,0},{-1,0},{0,1},{0,-1},{1,1},{1,-1},{-1,1},{-1,-1}};

    public char[][] updateBoard(char[][] board, int[] click) {
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
        } else {
            recurse(board, click[0], click[1]);
        }

        return board;
    }

    private void recurse(char[][] board, int row, int col) {
        if (isInValid(board, row, col)) {
            return;
        }

        if (board[row][col] != 'E') {
            return;
        }

        int count = countM(board, row, col);
        if ( count == 0) {
            board[row][col] = 'B';

            for (int[] direction : directions) {
                recurse(board, row + direction[0], col + direction[1]);
            }
        } else {
            board[row][col] = Character.forDigit(count, 10);
        }
    }

    private int countM(char[][] board, int row, int col) {
        int count = 0;
        for (int[] direction : directions) {
            if (isInValid(board, row + direction[0], col + direction[1])) {
                continue;
            }
            count += board[row + direction[0]][col + direction[1]] == 'M' ? 1 : 0;
        }
        return count;
    }

    private boolean isInValid(char[][]board, int row, int col) {
        return row < 0 || row >= board.length || col < 0 || col >= board[row].length;
    }
}
```
# LeetCode_40_组合总和II
## 题目
给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的每个数字在每个组合中只能使用一次。

说明：
```
所有数字（包括目标数）都是正整数。
解集不能包含重复的组合。 
```
示例 1:
```
输入: candidates = [10,1,2,7,6,1,5], target = 8,
所求解集为:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
```
示例 2:
```
输入: candidates = [2,5,2,1,2], target = 5,
所求解集为:
[
  [1,2,2],
  [5]
]
```
## 解法
### 思路
回溯：
- 排序数组，为了避免出现重复组合做准备
- 递归遍历数组
- 通过上一层带来的下标确定循环的起始位置
- 将当前元素放入list中，同时将sum值累加，将所有参数带入下一层
- 返回后将加入list的元素remove掉，进入当前层的下一个循环体
- 将和当前元素相同的元素跳过，避免出现重复组合
- 如果和target相等，将list放入，必需深度拷贝一个新的list，旧的要用来回溯
### 代码
```java
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ans = new ArrayList<>();
        backTrack(candidates, target, 0, 0, new LinkedList<>(), ans);
        return ans;
    }

    private void backTrack(int[] candidates, int target, int start, int sum, LinkedList<Integer> list, List<List<Integer>> ans) {
        if (sum == target) {
            ans.add(new LinkedList<>(list));
            return;
        }

        if (sum > target || start >= candidates.length) {
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            list.addLast(candidates[i]);
            backTrack(candidates, target, i + 1, sum + candidates[i], list, ans);
            list.removeLast();

            while (i + 1 < candidates.length && candidates[i] == candidates[i + 1]) {
                i++;
            }
        }
    }
}
```
# LeetCode_113_路径总和
## 题目
给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。

说明: 叶子节点是指没有子节点的节点。

示例:
```
给定如下二叉树，以及目标和 sum = 22，

              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
```
返回:
```
[
   [5,4,11,2],
   [5,8,4,5]
]
```
## 解法
### 思路
递归并回溯list，list中保存路径信息，如果叶子节点处的路径总和等于`sum`，那么就将list深度拷贝后放入`ans`集合
### 代码
```java
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ans = new ArrayList<>();
        backTrack(root, 0, sum, new LinkedList<>(), ans);
        return ans;
    }

    private void backTrack(TreeNode node, int count, int sum, LinkedList<Integer> list, List<List<Integer>> ans) {
        if (node == null) {
            return;
        }

        count += node.val;

        if (node.left == null && node.right == null) {
            if (count == sum) {
                list.addLast(node.val);
                ans.add(new LinkedList<>(list));
                list.removeLast();
            }
            return;
        }

        list.addLast(node.val);
        backTrack(node.left, count, sum, list, ans);
        list.removeLast();

        list.addLast(node.val);
        backTrack(node.right, count, sum, list, ans);
        list.removeLast();
    }
}
```
# LeetCode_90_1_子集
## 题目
给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。

说明：解集不能包含重复的子集。

示例:
```
输入: [1,2,2]
输出:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
```
## 解法
### 思路
递归并回溯，同时为了避免重复的集合，需要先对数组进行排序，在递归的循环数组过程中，需要跳过和上一个元素一样的元素。
### 代码
```java
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        backTrack(nums, 0, new LinkedList<>(), ans);
        return ans;
    }

    private void backTrack(int[] nums, int index, LinkedList<Integer> list, List<List<Integer>> ans) {
        ans.add(new LinkedList<>(list));

        for (int i = index; i < nums.length; i++) {
            list.addLast(nums[i]);
            backTrack(nums, i + 1, list, ans);
            list.removeLast();

            while (i + 1 < nums.length && nums[i] == nums[i + 1]) {
                i++;
            }
        }
    }
}
```