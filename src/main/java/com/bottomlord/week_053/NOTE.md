# LeetCode_98_验证二叉搜索树
## 题目
给定一个二叉树，判断其是否是一个有效的二叉搜索树。

假设一个二叉搜索树具有如下特征：
```
节点的左子树只包含小于当前节点的数。
节点的右子树只包含大于当前节点的数。
所有左子树和右子树自身必须也是二叉搜索树。
```
示例 1:
```
输入:
    2
   / \
  1   3
输出: true
```
示例 2:
```
输入:
    5
   / \
  1   4
     / \
    3   6
输出: false
解释: 输入为: [5,1,4,null,null,3,6]。
     根节点的值为 5 ，但是其右子节点值为 4 。
```
## 解法
### 思路
- 中序遍历
- 暂存节点值
- 遍历节点值，判断是否升序
### 代码
```java
class Solution {
    public boolean isValidBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) <= list.get(i - 1)) {
                return false;
            }
        }
        
        return true;
    }
    
    private void inorder(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        
        inorder(node.left, list);
        list.add(node.val);
        inorder(node.right, list);
    }
}
```
## 解法二
### 思路
dfs
- 参数：
    - 当前层节点
    - 上一层确定的最大值
    - 上一层确定的最小值
- 递归：
    - 退出条件：当前节点为空，返回true
    - 过程：
        - 如果当前节点不比上一层确定的最小值大，返回false
        - 如果当前节点不比上一层确定的最大值小，返回false
        - 向左子树递归时，当前值作为最大值
        - 向右子树递归时，当前值作为最小值
### 代码
```java
class Solution {
    public boolean isValidBST(TreeNode root) {
        return dfs(root, null, null);
    }
    
    private boolean dfs(TreeNode node, Integer min, Integer max) {
        if (node == null) {
            return true;
        }
        
        int val = node.val;
        if (min != null && val <= min) {
            return false;
        }
        
        if (max != null && val >= max) {
            return false;
        }
        
        if (!dfs(node.left, min, val)) {
            return false;
        }
        
        if (!dfs(node.right, val, max)) {
            return false;
        }
        
        return true;
    }
}
```
# LeetCode_99_恢复二叉搜索树
## 题目
给定一个二叉树，判断其是否是一个有效的二叉搜索树。

假设一个二叉搜索树具有如下特征：
```
节点的左子树只包含小于当前节点的数。
节点的右子树只包含大于当前节点的数。
所有左子树和右子树自身必须也是二叉搜索树。
```
示例 1:
```
输入:
    2
   / \
  1   3
输出: true
```
示例 2:
```
输入:
    5
   / \
  1   4
     / \
    3   6
输出: false
解释: 输入为: [5,1,4,null,null,3,6]。
     根节点的值为 5 ，但是其右子节点值为 4 。
```
## 解法
### 思路
- 中序遍历
- 生成存储节点引用的list
- 从后向前遍历list，找到第一个不符合升序的节点
- 从前往后遍历list，找到第一个不符合升序的节点
- 将这两个节点的值互换
### 代码
```java
class Solution {
    public void recoverTree(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        inorder(root, list);
        
        TreeNode node1 = list.get(list.size() - 1);
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).val > list.get(i + 1).val) {
                node1 = list.get(i);
                break;
            }
        }
        
        TreeNode node2 = list.get(0);
        for (int i = list.size() - 1; i > 0; i--) {
            if (list.get(i).val < list.get(i - 1).val) {
                node2 = list.get(i);
                break;
            }
        }
        
        int tmp = node1.val;
        node1.val = node2.val;
        node2.val = tmp;
    }
    
    private void inorder(TreeNode node, List<TreeNode> list) {
        if (node == null) {
            return;
        }
        
        inorder(node.left, list);
        list.add(node);
        inorder(node.right, list);
    }
}
```
# LeetCode_115_不同的子序列
## 题目
给定一个字符串 S 和一个字符串 T，计算在 S 的子序列中 T 出现的个数。

一个字符串的一个子序列是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）

题目数据保证答案符合 32 位带符号整数范围。

示例 1：
```
输入：S = "rabbbit", T = "rabbit"
输出：3
解释：

如下图所示, 有 3 种可以从 S 中得到 "rabbit" 的方案。
(上箭头符号 ^ 表示选取的字母)

rabbbit
^^^^ ^^
rabbbit
^^ ^^^^
rabbbit
^^^ ^^^
```
示例 2：
```
输入：S = "babgbag", T = "bag"
输出：5

解释：

如下图所示, 有 5 种可以从 S 中得到 "bag" 的方案。 
(上箭头符号 ^ 表示选取的字母)

babgbag
^^ ^
babgbag
^^    ^
babgbag
^    ^^
babgbag
  ^  ^^
babgbag
    ^^^
```
## 失败解法
###
超时
### 思路
回溯
### 代码
```java
class Solution {
    public int numDistinct(String s, String t) {
        return backTrack(s, 0, t, 0);
    }

    private int backTrack(String s, int si, String t, int ti) {
        if (si == s.length() && ti != t.length()) {
            return 0;
        }

        if (ti == t.length()) {
            return 1;
        }

        int count = 0;
        for (int i = si; i < s.length(); i++) {
            if (s.charAt(i) == t.charAt(ti)) {
                count += backTrack(s, i + 1, t, ti + 1);
            }
        }

        return count;
    }
}
```
## 解法
### 题目
回溯+记忆化搜索
### 代码
```java
class Solution {
    public int numDistinct(String s, String t) {
        int[][] memo = new int[s.length()][t.length()];
        for (int[] arr : memo) {
            Arrays.fill(arr, -1);
        }
        return backTrack(s, 0, t, 0, memo);
    }

    private int backTrack(String s, int si, String t, int ti, int[][] memo) {
        if (si == s.length() && ti != t.length()) {
            return 0;
        }

        if (ti == t.length()) {
            return 1;
        }

        if (memo[si][ti] != -1) {
            return memo[si][ti];
        }

        int count = 0;
        for (int i = si; i < s.length(); i++) {
            if (s.charAt(i) == t.charAt(ti)) {
                count += backTrack(s, i + 1, t, ti + 1, memo);
            }
        }

        memo[si][ti] = count;
        return count;
    }
}
```
## 解法二
### 思路
动态规划：
- `dp[i][j]`：T的前i个字符组成的字符串，在S的前j个字符组成的字符串中，能够得到的最大个数
- 初始化：当i为0，也就是T为空字符串时，所有S的长度对应的dp值都是1
- 状态转移方程：
    - `T[i] != S[j]`：当前字符不相等，说明S[j]没有被用到，所以当前dp值与`dp[i][j - 1]`是一致的
    - `T[i] == S[j]`：当前字符相等，所以分成两种状态：
        - `S[j]`被用到了，那么它的值和后面情况相等：`S[j]`不存在且`T[i]`也不需要匹配，这种情况获得的所有可能，在T用到`T[i]`后，`T[i]`代表的值就都会只和`S[j]`匹配了
        - `S[j]`没有被用到，那么它的情况和`T[i] != S[j]`的情况相同
    - 所以状态转移方程就是如下两个方程：
        - `T[i] != S[j]`：`dp[i][j] = dp[i][j - 1]` 
        - `T[i] == S[j]`：`dp[i][j] = dp[i][j - 1] + dp[i - 1][j - 1]` 
### 代码
```java
class Solution {
    public int numDistinct(String s, String t) {
        int sl = s.length(), tl = t.length();
        int[][] dp = new int[tl + 1][sl + 1];
        for (int i = 0; i <= sl; i++) {
            dp[0][i] = 1;
        }
        
        for (int i = 1; i <= tl; i++) {
            for (int j = 1; j <= sl; j++) {
                if (t.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        
        return dp[tl][sl];
    }
}
```
# LeetCode_117_填充每个节点的下一个右侧节点指针II
## 题目
给定一个二叉树
```
struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
```
填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。

初始状态下，所有 next 指针都被设置为 NULL。

进阶：
```
你只能使用常量级额外空间。
使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
```
示例：
```
输入：root = [1,2,3,4,5,null,7]
输出：[1,#,2,3,#,4,5,7,#]
解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
```
## 解法
### 思路
bfs
### 代码
```java
class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int num = queue.size();
            Node pre = null;
            while (num-- > 0) {
                Node node = queue.poll();
                if (node == null) {
                    continue;
                }

                if (pre != null) {
                    pre.next = node;
                }
                
                pre = node;

                if (node.left != null) {
                    queue.offer(node.left);
                }
                
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        
        return root;
    }
}
```
# LeetCode_123_买卖股票的最佳时机III
## 题目
给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。

设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。

注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

示例 1:
```
输入: [3,3,5,0,0,3,1,4]
输出: 6
解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
     随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
```
示例 2:
```
输入: [1,2,3,4,5]
输出: 4
解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。   
     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。   
     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
```
示例 3:
```
输入: [7,6,4,3,1] 
输出: 0 
解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。
```
## 解法
### 思路
动态规划：
- `dp[i][k][s]`：
    - i：代表第几天
    - k：代表可以交易几次
    - s：代表现在的状态：
        - 1：代表持有
        - 0：代表未持有
    - 整个表达式代表了需要穷举的样本空间：在第i天当允许交易k次的条件下，状态s时可以获得的最大利润
- 初始化：
    - 第0天未持有时，代表利润为0
    - 第0天持有时不合法的，利润为负无穷
    - 允许0次交易，代表不能交易，利润为0
- 状态转移方程：
    - `dp[i][k][1] = max(dp[i - 1][k][1], dp[i - 1][k][0] - profit[i])`
    - `dp[i][k][0] = max(dp[i - 1][k][0], dp[i - 1][k][1] + profit[i])`
### 代码
```java
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0) {
            return 0;
        }
        
        int[][][] dp = new int[n][3][2];
        for (int i = 0; i < n; i++) {
            for (int j = 2; j >= 1; j--) {
                if (i == 0) {
                    dp[i][j][1] = -prices[i];
                    dp[i][j][0] = 0;
                    continue;
                }

                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }
        
        return dp[n - 1][2][0];
    }
}
```
# LeetCode_127_单词接龙
## 题目
给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
```
每次转换只能改变一个字母。
转换过程中的中间单词必须是字典中的单词。
```
说明:
```
如果不存在这样的转换序列，返回 0。
所有单词具有相同的长度。
所有单词只由小写字母组成。
字典中不存在重复的单词。
你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
```
示例 1:
```
输入:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

输出: 5

解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
     返回它的长度 5。
```
示例 2:
```
输入:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

输出: 0

解释: endWord "cog" 不在字典中，所以无法进行转换。
```
## 失败解法
### 原因
超时，时间复杂度高
### 思路
回溯
### 代码
```java
class Solution {
    private int ans = Integer.MAX_VALUE;
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        
        Set<String> set = new HashSet<>();
        set.add(beginWord);
        backTrack(beginWord, endWord, set, wordList, 1);
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    private void backTrack(String curWord, String endWord, Set<String> memo, List<String> wordList, int count) {
        if (count > ans) {
            return;
        }

        if (Objects.equals(curWord, endWord)) {
            ans = Math.min(ans, count);
            return;
        }
        
        for (String word : wordList) {
            if (isNext(curWord, word) && !memo.contains(word)) {
                memo.add(word);
                backTrack(word, endWord, memo, wordList, count + 1);
                memo.remove(word);
            }
        }
    }

    private boolean isNext(String curWord, String nextWord) {
        if (curWord.length() != nextWord.length()) {
            return false;
        }

        int count = 0;
        for (int i = 0; i < curWord.length(); i++) {
            if (curWord.charAt(i) != nextWord.charAt(i)) {
                count++;
            }

            if (count > 1) {
                return false;
            }
        }

        return count == 1;
    }
}
```
## 解法
### 思路
bfs+预处理
- 对字符串做占位符处理：
    - 将字符串的每一位用`*`来占位，作为一个标识放在map的key中，map的value是list，list中存放也能生成这种标志的字符串
    - 遍历字符串，处理字符串的每一位后填充map
- 使用bfs来处理变换的步数：
    - 将beginWord放入队列中开始bfs
    - 通过beginWord生成所有可能的占位符标识
    - 将占位符中找到的匹配的字符串放入下一层，并计数
    - 如果匹配到endWord就返回计数值
### 代码
```java
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int count = 1;
        Map<String, List<String>> map = new HashMap<>();
        for (String word : wordList) {
            for (int i = 0; i < word.length(); i++) {
                String label = word.substring(0, i) + "*" + word.substring(i + 1);
                List<String> list = map.getOrDefault(label, new ArrayList<>());
                list.add(word);
                map.put(label, list);
            }
        }

        Queue<String> queue = new ArrayDeque<>();
        Set<String> memo = new HashSet<>();
        queue.offer(beginWord);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                String word = queue.poll();
                if (word == null) {
                    continue;
                }
                
                if (memo.contains(word)) {
                    continue;
                }
                
                memo.add(word);
                
                if (Objects.equals(endWord, word)) {
                    return count;
                }
                
                for (int i = 0; i < word.length(); i++) {
                    String label = word.substring(0, i) + "*" + word.substring(i + 1);
                    for (String match : map.getOrDefault(label, new ArrayList<>())) {
                        queue.offer(match);
                    }
                }
            }
            
            count++;
        }
        
        return 0;
    }
}
```
# LeetCode_286_墙与门
## 题目
你被给定一个 m × n 的二维网格，网格中有以下三种可能的初始化值：
```
-1 表示墙或是障碍物
0 表示一扇门
INF 无限表示一个空的房间。然后，我们用 231 - 1 = 2147483647 代表 INF。你可以认为通往门的距离总是小于 2147483647 的。
你要给每个空房间位上填上该房间到 最近 门的距离，如果无法到达门，则填 INF 即可。
```
示例：

给定二维网格：
```
INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
```
运行完你的函数后，该网格应该变成：
```
  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4
```
## 解法
### 思路
bfs+记忆化搜索
### 代码
```java
class Solution {
    private int[][] directions = new int[][]{{0,1}, {1,0}, {0,-1}, {-1,0}};
    public void wallsAndGates(int[][] rooms) {
        int row = rooms.length;
        if (row == 0) {
            return;
        }

        int col = rooms[0].length;
        if (col == 0) {
            return;
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (rooms[i][j] == -1) {
                    continue;
                }

                rooms[i][j] = bfs(rooms, row, col, i, j);
            }
        }
    }

    private int bfs(int[][] rooms, int row, int col, int r, int c) {
        Queue<Pair<Integer, Integer>> queue = new ArrayDeque<>();
        boolean[][] memo = new boolean[row][col];
        queue.offer(new Pair<>(r, c));
        int count = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Pair<Integer, Integer> pair = queue.poll();
                if (pair == null) {
                    continue;
                }

                int x = pair.getKey(), y = pair.getValue();

                if (x < 0 || x >= row || y < 0 || y >= col || rooms[x][y] == -1 || memo[x][y]) {
                    continue;
                }

                memo[x][y] = true;

                if (rooms[x][y] == 0) {
                    return count;
                }

                for (int[] diection : directions) {
                    queue.offer(new Pair<>(x + diection[0], y + diection[1]));
                }
            }
            count++;
        }
        return Integer.MAX_VALUE;
    }
}
```
## 解法二
### 思路
从门开始bfs，而不是从任意一点开始
### 代码
```java
class Solution {
    private int[][] diections = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public void wallsAndGates(int[][] rooms) {
        int row = rooms.length;
        if (row == 0) {
            return;
        }

        int col = rooms[0].length;
        if (col == 0) {
            return;
        }

        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (rooms[i][j] == 0) {
                    queue.offer(new int[]{i,j});
                }
            }
        }

        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                int[] pair = queue.poll();
                if (pair == null) {
                    continue;
                }

                for (int[] direction : diections) {
                    int x = pair[0] + direction[0],
                        y = pair[1] + direction[1];
                    
                    if (!isValid(x, y, row, col) || rooms[x][y] != Integer.MAX_VALUE) {
                        continue;
                    }
                    
                    rooms[x][y] = count + 1;
                    queue.offer(new int[]{x, y});
                }
            }

            count++;
        }
    }
    
    private boolean isValid(int x, int y, int row, int col) {
        return x >= 0 && x < row && y >= 0 && y < col;
    }
}
```
# LeetCode_315_计算右侧小于当前元素的个数
## 题目
给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。

示例:
```
输入: [5,2,6,1]
输出: [2,1,1,0] 
解释:
5 的右侧有 2 个更小的元素 (2 和 1).
2 的右侧仅有 1 个更小的元素 (1).
6 的右侧有 1 个更小的元素 (1).
1 的右侧有 0 个更小的元素.
```
## 失败解法
## 原因
超时，时间复杂度太高
### 思路
2层迭代
### 代码
```java
class Solution {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> counts = new ArrayList<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[i]) {
                    count++;
                }
            }
            counts.add(count);
        }
        return counts;
    }
}
```
## 解法
### 思路
归并排序+索引数组
- 使用归并排序的思路，将数组拆分成前后两部分
- 归并递归返回的时候，前后两部分都是升序的已经排列好的数组
- 在处理前后两部分，进行的排序的同时，将计算个数的工作做掉
    - 用两个指针分别从前后2部分的起始坐标开始遍历
    - 从小到大的依次将元素出列
    - 假设遍历前半部分的指针为i，后半部分的指针为j
    - 如果i越界，那么说明前半部分已经没有比后半部分大的元素了，直接移动j
    - 如果j越界，那么说明所有后半部分的元素都比剩下没有遍历到的前半部分小，计算后半部分的长度累加到前半部分元素的位置
    - 如果i和j都没有越界：
        - 如果遍历到的是前半部分的元素，就计算j与后半部分起始位置的距离，并移动i
        - 如果遍历到的是后半部分的元素，就直接移动j
- 在归并排序的过程中：
    - 使用数组indexes来记录排序的元素的下表
    - 使用数组counts来记录下表对应下标元素在题目中需要记录的答案，也就是该元素后面有多少个比它小的个数
### 代码
```java
class Solution {
    private int[] indexes;
    private int[] counts;

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        
        int len = nums.length;
        if (len == 0) {
            return ans;
        }
        
        indexes = new int[len];
        counts = new int[len];
       
        for (int i = 0; i < len; i++) {
            indexes[i] = i;
        }

        mergeSort(nums, 0, len - 1);
        
        for (int num : counts) {
            ans.add(num);
        }
        
        return ans;
    }

    private void mergeSort(int[] nums, int head, int tail) {
        if (head == tail) {
            return;
        }

        int mid = head + (tail - head) / 2;
        mergeSort(nums, head, mid);
        mergeSort(nums, mid + 1, tail);

        if (nums[indexes[mid]] > nums[indexes[mid + 1]]) {
            doMergeSort(nums, head, mid, tail);
        }
    }

    private void doMergeSort(int[] nums, int head, int mid, int tail) {
        int[] tmp = new int[nums.length];
        for (int i = head; i <= tail; i++) {
            tmp[i] = indexes[i];
        }

        int i = head, j = mid + 1;
        for (int k = head; k <= tail; k++) {
            if (i > mid) {
                indexes[k] = tmp[j];
                j++;
            } else if (j > tail) {
                indexes[k] = tmp[i];
                i++;
                counts[indexes[k]] += (tail - mid);
            } else if (nums[tmp[i]] <= nums[tmp[j]]) {
                indexes[k] = tmp[i];
                i++;
                counts[indexes[k]] += ( j - mid - 1);
            } else {
                indexes[k] = tmp[j];
                j++;
            }
        }
    }
}
```