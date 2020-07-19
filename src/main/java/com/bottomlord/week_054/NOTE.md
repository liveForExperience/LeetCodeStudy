# LeetCode_130_被围绕的区域
## 题目
给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。

找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。

示例:
```
X X X X
X O O X
X X O X
X O X X
```
运行你的函数后，矩阵变为：
```
X X X X
X X X X
X X X X
X O X X
```
解释:
```
被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
```
## 解法
### 思路
dfs
- 从矩阵边缘中找到`O`，并从这些点开始dfs
- 将这些点修改成`#`
- 遍历完所有的边缘点后，遍历整个矩阵
    - 将`O`全部变为`X`
    - 将`#`全部变成`O`
### 代码
```java
class Solution {
    private int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public void solve(char[][] board) {
        int row = board.length;
        if (row == 0) {
            return;
        }

        int col = board[0].length;
        if (col == 0) {
            return;
        }

        for (int i = 0; i < col; i++) {
            if (board[0][i] == 'O') {
               dfs(board, row, col, 0, i);
            }

            if (board[row - 1][i] == 'O') {
                dfs(board, row, col, row - 1, i);
            }
        }

        for (int i = 0; i < row; i++) {
            if (board[i][0] == 'O') {
                dfs(board, row, col, i, 0);
            }

            if (board[i][col - 1] == 'O') {
                dfs(board, row, col, i, col - 1);
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void dfs(char[][] board, int row, int col, int r, int c) {
        if (r < 0 || r >= row || c < 0 || c >= col || board[r][c] == 'X' || board[r][c] == '#') {
            return;
        }

        board[r][c] = '#';

        for (int[] direction : directions) {
            dfs(board, row, col, r + direction[0], c + direction[1]);
        }
    }
}
```
# LeetCode_132_分割回文串
## 题目
给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。

返回符合要求的最少分割次数。

示例:
```
输入: "aab"
输出: 1
解释: 进行一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
```
## 解法
### 思路
动态规划：
- `dp[i]`：`s[0,i]`区间内需要分割的最小次数
- 状态转移方程：
    - `dp[i]`的值可以通过`min(dp[j](0 <= j < i) + 1, dp[i])`的状态进行转移，其中`s[j + 1, i]`是一个回文串
    - 所以每次状态转移就是在`[0,i)`范围中找到最小值
- 边界初始值：
    - `s[0,0]`，也就是第一个字符一定是回文串
    - 因为要求最小值，所以`dp[i]`可以初始化为下标值，因为`s[0,i]`的最坏情况就是每一个字符自己才能组成回文串
- 返回`dp[len - 1]`
### 代码
```java
class Solution {
    public int minCut(String s) {
        int len = s.length();
        if (len == 0) {
            return 0;
        }
        
        int[] dp = new int[len];
        for (int i = 0; i < len; i++) {
            dp[i] = i;
        }
        
        for (int i = 1; i < len; i++) {
            if (isValid(s, 0, i)) {
                dp[i] = 0;
                continue;
            }
            
            for (int j = 0; j < i; j++) {
                if (isValid(s, j + 1, i)) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        
        return dp[len - 1];
    }
    
    private boolean isValid(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        
        return true;
    }
}
```
## 解法二
### 思路
- 在解法一的基础上，将每次都遍历字符串判断是否为回文串的步骤，替换为生成一个二维数组，记录是否为回文串。
- 二维数组也是一个dp方程，`dp[i][j]`代表`s[i,j]`是否为回文串
- 过程是遍历二维数组，然后使用两个指针分别代表字符串的头尾字符，如果头尾字符相同，且它们要么分别往里缩进一个字符的dp也是true，要么长度不超过2，那么这个字符串就是回文串
- 然后再通过解法一的dp状态转移方程，获得最后的结果
### 代码
```java
class Solution {
    public int minCut(String s) {
        int len = s.length();
        if (len < 2) {
            return 0;
        }

        boolean[][] isValid = new boolean[len][len];
        for (int right = 0; right < len; right++) {
            for (int left = 0; left <= right; left++) {
                if (s.charAt(right) == s.charAt(left) && (right - left <= 2 || isValid[left + 1][right - 1])) {
                    isValid[left][right] = true;
                }
            }
        }

        int[] dp = new int[len];
        for (int i = 0; i < len; i++) {
            dp[i] = i;
        }

        for (int i = 1; i < len; i++) {
            if (isValid[0][i]) {
                dp[i] = 0;
            }
            
            for (int j = 0; j < i; j++) {
                if (isValid[j + 1][i]) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[len - 1];
    }
}
```
# LeetCode_135_分发糖果
## 题目
老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。

你需要按照以下要求，帮助老师给这些孩子分发糖果：
```
每个孩子至少分配到 1 个糖果。
相邻的孩子中，评分高的孩子必须获得更多的糖果。
那么这样下来，老师至少需要准备多少颗糖果呢？
```
示例 1:
```
输入: [1,0,2]
输出: 5
解释: 你可以分别给这三个孩子分发 2、1、2 颗糖果。
```
示例 2:
```
输入: [1,2,2]
输出: 4
解释: 你可以分别给这三个孩子分发 1、2、1 颗糖果。
     第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
```
## 解法
### 思路
暴力：
- 初始化一个记录糖果个数的数组
- 双层循环：
    - 外层驱动ratings数组中元素前后装填的检查
    - 内层从数组头到尾遍历元素，检查当前元素是否比前后大，如果大且糖果值不比前后值大，则更新糖果值
    - 当糖果值变更时，做标记`change`
    - 如果`change`过，外层继续循环
- 返回糖果数组元素的总和
### 代码
```java
class Solution {
    public int candy(int[] ratings) {
        int len = ratings.length;
        if (len == 0) {
            return 0;
        }

        boolean change = false;
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);

        do {
            change = false;
            
            for (int i = 0; i < len; i++) {
                if (i != 0 && ratings[i] > ratings[i - 1] && candies[i] <= candies[i - 1]) {
                    candies[i] = candies[i - 1] + 1;
                    change = true;
                }

                if (i != len - 1 && ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1]) {
                    candies[i] = candies[i + 1] + 1;
                    change = true;
                }
            }

        } while (change);

        return Arrays.stream(candies).sum();
    }
}
```
## 解法二
### 思路
两个数组：
- 维护两个数组：
    - l2r：只关心当前学生和左边学生关系，并确定糖果数
    - r2l：只关心当前学生和右边学生关系，并确定糖果数
- 过程：
    - 从左到右遍历，并更新每一个元素：如果`ratings[i] > ratings[i - 1]`，`l2r[i] = l2r[i - 1] + 1` 
    - 从右到左遍历，并更新每一个元素：如果`ratings[i] > ratings[i + 1]`，`l2r[i] = l2r[i + 1] + 1` 
    - 遍历所有学生，确定最终结果，取两个数组当前元素的最大值作为当前学生的糖果数
### 代码
```java
class Solution {
    public int candy(int[] ratings) {
        int len = ratings.length;
        if (len == 0) {
            return 0;
        }
        
        int[] l2r = new int[len],
              r2l = new int[len];

        Arrays.fill(l2r, 1);
        Arrays.fill(r2l, 1);
        
        for (int i = 1; i < len; i++) {
            if (ratings[i] > ratings[i - 1]) {
                l2r[i] = l2r[i - 1] + 1;
            }
        }

        for (int i = len - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                r2l[i] = r2l[i + 1] + 1;
            }
        }
        
        int ans = 0;
        for (int i = 0; i < len; i++) {
            ans += Math.max(l2r[i], r2l[i]);
        }
        
        return ans;
    }
}
```
## 解法三
### 思路
- 在解法二两个数组的基础上，压缩空间，只是用一个数组
- 这个数组先确定从左到右的糖果数状态，再确定从右到左的状态
- 确定从右到左的状态时，直接将最后判断最大值的逻辑也合在一起
### 代码
```java
class Solution {
    public int candy(int[] ratings) {
        int len = ratings.length;
        if (len == 0) {
            return 0;
        }
        
        int[] candies = new int[len];
        Arrays.fill(candies, 1);
        
        for (int i = 1; i < len; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }
        
        for (int i = len - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }
        
        return Arrays.stream(candies).sum();
    }
}
```
## 解法四
### 思路
- 将`ratings`看作被若干个山峰组成
- 山峰又通过升序和降序两个序列组成
- 升序和降序序列的起始一定是1，结尾一定是序列长度，所以总和可以通过等差数列求和公式获得
- 但关键要注意升序和降序的最大值只能取最大的那个，所以可以在取值的时候不计算两个序列组成的峰顶值
- 变量：
    - 两个变量记录前一个状态和当前状态，状态包含：
        - 升序
        - 平
        - 降序
    - 两个变量记录升序和降序的序列个数
        - up：升序个数
        - down：降序个数
- 过程：
    - 遍历`ratings`
    - 确定当前的状态（当前元素和前一个元素比较）
    - 如果完成一个山峰（升序后平，降序后平或升序）
        - 计算升序和降序的总和：`sum = count(up) + count(down) + Math.max(up, down)`
        - 重置up和down的值为0
    - 根据当前状态更新up和down，如果是平，结果值就加1
    - 最后更新当前状态为之前状态
    - 进入下一个循环
    - 循环结束后，还需要计算下剩下的up和down的值以及对应的峰顶
    - 并且还要再加1，因为循环`ratings`是从第二个元素开始的，第一个元素没有计算
### 代码
```java
class Solution {
    public int candy(int[] ratings) {
        int len = ratings.length;
        if (len <= 1) {
            return len;
        }
        
        int pre = 0, cur = 0, up = 0, down = 0, ans = 0;
        for (int i = 1; i < len; i++) {
            cur = Integer.compare(ratings[i], ratings[i - 1]);
            if (cur == 0 && pre == 1 || cur >= 0 && pre == -1) {
                ans += count(up) + count(down) + Math.max(down, up);
                up = 0;
                down = 0;
            }
            
            if (cur == 1) {
                up++;
            } else if (cur == -1) {
                down++;
            } else {
                ans++;
            }
            
            pre = cur;
        }
        
        ans += count(up) + count(down) + Math.max(down, up) + 1;
        
        return ans;
    }
    
    private int count(int n) {
        return (n + 1) * n / 2;
    }
}
```
# LeetCode_785_判断二分图
## 题目
给定一个无向图graph，当这个图为二分图时返回true。

如果我们能将一个图的节点集合分割成两个独立的子集A和B，并使图中的每一条边的两个节点一个来自A集合，一个来自B集合，我们就将这个图称为二分图。

graph将会以邻接表方式给出，graph[i]表示图中与节点i相连的所有节点。每个节点都是一个在0到graph.length-1之间的整数。这图中没有自环和平行边： graph[i] 中不存在i，并且graph[i]中没有重复的值。

示例 1:
```
输入: [[1,3], [0,2], [1,3], [0,2]]
输出: true
解释: 
无向图如下:
0----1
|    |
|    |
3----2
我们可以将节点分成两组: {0, 2} 和 {1, 3}。
```
示例 2:
```
输入: [[1,2,3], [0,2], [0,1,3], [0,2]]
输出: false
解释: 
无向图如下:
0----1
| \  |
|  \ |
3----2
我们不能将节点分割成两个独立的子集。
```
注意:
```
graph 的长度范围为 [1, 100]。
graph[i] 中的元素的范围为 [0, graph.length - 1]。
graph[i] 不会包含 i 或者有重复的值。
图是无向的: 如果j 在 graph[i]里边, 那么 i 也会在 graph[j]里边。
```
## 解法
### 思路
bfs+记忆化搜索+涂色
- 因为拓扑图可能是不连通的，还需要从每个节点开始进行搜索
- 使用记忆化搜索，避免重复
- 使用涂色来判断是否有相邻但颜色相同的情况
### 代码
```java
class Solution {
    public boolean isBipartite(int[][] graph) {
        int len = graph.length;
        int[] color = new int[len];
        
        for (int i = 0; i < len; i++) {
            Queue<Integer> queue = new ArrayDeque<>();
            queue.offer(i);
            
            while (!queue.isEmpty()) {
                Integer num = queue.poll();
                if (num == null) {
                    continue;
                }
                
                color[num] = color[num] == 0 ? 1 : color[num];
                int[] arr = graph[num];
                for (int index : arr) {
                    if (color[index] == 0) {
                        queue.offer(index);
                        color[index] = color[num] == 1 ? 2 : 1;
                    } else if (color[index] == color[num]) {
                        return false;
                    }
                }
            }
        }
        
        return true;
    }
}
```
# LeetCode_140_单词拆分II
## 题目
给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。

说明：
```
分隔时可以重复使用字典中的单词。
你可以假设字典中没有重复的单词。
```
示例 1：
```
输入:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
输出:
[
  "cats and dog",
  "cat sand dog"
]
```
示例 2：
```
输入:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
输出:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
解释: 注意你可以重复使用字典中的单词。
```
示例 3：
```
输入:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
输出:
[]
```
## 失败解法
### 原因
超时，搜索时间复杂度可能是O(N^N)
### 思路
回溯
### 代码
```java
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> words = new HashSet<>(wordDict);
        List<String> ans = new ArrayList<>();
        backTrack(s, 0, words, new StringBuilder(), ans);
        return ans;
    }

    private void backTrack(String s, int index, Set<String> words, StringBuilder path, List<String> ans) {
        if (index == s.length()) {
            ans.add(path.toString().trim());
            return;
        }

        for (int i = index + 1; i <= s.length(); i++) {
            String word = s.substring(index, i);
            if (words.contains(word)) {
                int len = path.length();
                path.append(" ").append(word);
                backTrack(s, i, words, path, ans);
                path.delete(len, path.length());
            }
        }
    }
}
```
## 解法
### 思路
回溯+记忆化搜索
### 代码
```java
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        return backTrack(s, new HashSet<>(wordDict), 0, new HashMap<>());
    }

    private List<String> backTrack(String s, Set<String> words, int index, Map<Integer, List<String>> memo) {
        if (memo.containsKey(index)) {
            return memo.get(index);
        }

        List<String> ans = new ArrayList<>();
        if (index == s.length()) {
            ans.add("");
        }
        
        for (int i = index + 1; i <= s.length(); i++) {
            String word = s.substring(index, i);
            if (words.contains(word)) {
                List<String> list = backTrack(s, words, i, memo);
                for (String str : list) {
                    ans.add(s.substring(index, i) + (Objects.equals(str, "") ? "" : " ") + str);
                }
            }
        }

        memo.put(index, ans);
        return ans;
    }
}
```