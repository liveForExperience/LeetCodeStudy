# Interview_0802_迷路的机器人
## 题目
设想有个机器人坐在一个网格的左上角，网格 r 行 c 列。机器人只能向下或向右移动，但不能走到一些被禁止的网格（有障碍物）。设计一种算法，寻找机器人从左上角移动到右下角的路径。

网格中的障碍物和空位置分别用 1 和 0 来表示。

返回一条可行的路径，路径由经过的网格的行号和列号组成。左上角为 0 行 0 列。

示例 1:
```
输入:
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
输出: [[0,0],[0,1],[0,2],[1,2],[2,2]]
```
解释: 
```
输入中标粗的位置即为输出表示的路径，即
0行0列（左上角） -> 0行1列 -> 0行2列 -> 1行2列 -> 2行2列（右下角）
说明：r 和 c 的值均不超过 100。
```
## 解法
### 思路
回溯+记忆化搜索
### 代码
```java
class Solution {
    public List<List<Integer>> pathWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return Collections.emptyList();
        }
        
        LinkedList<List<Integer>> paths = new LinkedList<>();
        int row = obstacleGrid.length, col = obstacleGrid[0].length;
        backTrack(0, 0, row, col, obstacleGrid, new boolean[row][col], paths);
        return paths;
    }

    private boolean backTrack(int x, int y, int row, int col, int[][] grid, boolean[][] visited, LinkedList<List<Integer>> paths) {
        if (x >= row || y >= col || grid[x][y] == 1 || visited[x][y]) {
            return false;
        }
        
        paths.add(Arrays.asList(x, y));
        visited[x][y] = true;
        
        if (x == row - 1 && y == col - 1) {
            return true;
        }
        
        if (backTrack(x, y + 1, row, col, grid, visited, paths) ||
            backTrack(x + 1, y, row, col, grid, visited, paths)) {
            return true;
        }
        
        paths.removeLast();
        return false;
    }
}
```
# Interview_0803_魔术索引
## 题目
魔术索引。 在数组A[0...n-1]中，有所谓的魔术索引，满足条件A[i] = i。给定一个有序整数数组，编写一种方法找出魔术索引，若有的话，在数组A中找出一个魔术索引，如果没有，则返回-1。若有多个魔术索引，返回索引值最小的一个。

示例1:
```
 输入：nums = [0, 2, 3, 4, 5]
 输出：0
 说明: 0下标的元素为0
```
示例2:
```
 输入：nums = [1, 1, 1]
 输出：1
```
提示:
```
nums长度在[1, 1000000]之间
```
## 解法
### 思路
- 遍历比对索引和值
### 代码
```java
class Solution {
    public int findMagicIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i == nums[i]) {
                return i;
            }
        }

        return -1;
    }
}
```
# Interview_0804_幂集
## 题目
幂集。编写一种方法，返回某集合的所有子集。集合中不包含重复的元素。

说明：解集不能包含重复的子集。

示例:
```
 输入： nums = [1,2,3]
 输出：
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
```
## 解法
### 思路 
回溯：
- 退出条件：数组元素被全部遍历
- 过程：
    - 将path放入ans中作为一种集合可能
    - 遍历数组元素，将当前元素放入path中，继续递归
    - 返回后将当前循环加入path的元素删去，继续下一个元素的递归
### 代码
```java
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backTrack(0, nums, new LinkedList<>(), ans);
        return ans;
    }

    private void backTrack(int index, int[] nums, LinkedList<Integer> path, List<List<Integer>> ans) {
        ans.add(new ArrayList<>(path));

        for (int i = index; i < nums.length; i++) {
            path.offerLast(nums[i]);
            backTrack(i + 1, nums, path, ans);
            path.removeLast();
        }
    }
}
```
# Interview_0805_递归乘法
## 题目
递归乘法。 写一个递归函数，不使用 * 运算符， 实现两个正整数的相乘。可以使用加号、减号、位移，但要吝啬一些。

示例1:
```
 输入：A = 1, B = 10
 输出：10
```
示例2:
```
 输入：A = 3, B = 4
 输出：12
```
提示:
```
保证乘法范围不会溢出
```
## 解法
### 思路
递归相加：
- 以`B`作为递归的次数，`A`作为累加的值
- 退出条件是`B <= 1`，返回`A`
### 代码
```java
class Solution {
    public int multiply(int A, int B) {
        return B > 1 ? multiply(A, B - 1) + A : A;
    }
}
```
# Interview_0806_汉诺塔问题
## 题目
在经典汉诺塔问题中，有 3 根柱子及 N 个不同大小的穿孔圆盘，盘子可以滑入任意一根柱子。一开始，所有盘子自上而下按升序依次套在第一根柱子上(即每一个盘子只能放在更大的盘子上面)。移动圆盘时受到以下限制:
```
(1) 每次只能移动一个盘子;
(2) 盘子只能从柱子顶端滑出移到下一根柱子;
(3) 盘子只能叠在比它大的盘子上。
```
请编写程序，用栈将所有盘子从第一根柱子移到最后一根柱子。

你需要原地修改栈。

示例1:
```
 输入：A = [2, 1, 0], B = [], C = []
 输出：C = [2, 1, 0]
```
示例2:
```
 输入：A = [1, 0], B = [], C = []
 输出：C = [1, 0]
```
提示:
```
A中盘子的数目不大于14个。
```
## 解法
### 思路
- 过程可以分成三个部分：
    1. 将n-1个盘子借助C柱子放到B柱子上
    2. 将A柱子的最后一个盘子放到C柱子上
    3. 将n-1个盘子借助A柱子放到C柱子上
- 如上的三步中，1和2步其实是借助柱子不同的相同的动作，所以整个这个过程可以看成是`an = a(n - 1) + 1 + a(n - 1)`的公式，其中`a(n - 1)`就是n-1个盘子从某个柱子到另一个柱子的步数
- 而通过如上公式可以找到递推的过程，相当于当只有一个盘子的时候（也就是递归的退出条件），可以将这个盘子直接放到目标柱子上，如上
### 代码
```java
class Solution {
    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        move(A.size(), A, B, C);
    }

    private void move(int n, List<Integer> A, List<Integer> B, List<Integer> C) {
        if (n == 0) {
            
            return;
        }
        move(n - 1, A, C, B);
        C.add(A.remove(A.size() - 1));
        move(n - 1, B, A, C);
    }
}
```
# Interview_0807_无重复字符串的排列组合
## 题目
无重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合，字符串每个字符均不相同。

示例1:
```
 输入：S = "qwe"
 输出：["qwe", "qew", "wqe", "weq", "ewq", "eqw"]
```
示例2:
```
 输入：S = "ab"
 输出：["ab", "ba"]
```
提示:
```
字符都是英文字母。
字符串长度在[1, 9]之间。
```
## 解法
### 思路
回溯：
- 使用变量：
    - 一个变量path记录递归路径上组成的字符串组合
    - 一个set集合记录所有可使用的字符串，并做到去重
    - 一个变量memo记录当前使用过的字符
- 过程：
    - 递归的退出条件是path的长度和S的长度一致，将该字符串放入set中
    - 每一层递归都从字符串头部开始遍历，如果memo有记录，就跳过
    - 否则就记录这个memo，并拼接当前字符后递归下去
    - 循环中返回时候，做两件事
        - 从memo中将刚才标记为true的字符改为false，方便下一个循环中使用别的字符时，该字符在下一次递归时可以继续使用
        - 将path的最后一个字符删去，这个动作的含义与改memo含义一样
### 代码
```java
class Solution {
    public String[] permutation(String S) {
        Set<String> set = new HashSet<>();
        backTrack(S, new StringBuilder(), new boolean[S.length()], set);
        return set.toArray(new String[0]);
    }

    private void backTrack(String s, StringBuilder path, boolean[] memo, Set<String> set) {
        if (path.length() == s.length()) {
            set.add(path.toString());
            return;
        }
        
        for (int i = 0; i < s.length(); i++) {
            if (!memo[i]) {
                memo[i] = true;
                backTrack(s, path.append(s.charAt(i)), memo, set);
                path.deleteCharAt(path.length() - 1);
                memo[i] = false;
            }
        }
    }
}
```
## 解法二
### 思路
使用list代替set
- 将字符串排序
- 在递归遍历的过程中跳过上次使用过的字符
### 代码
```java
class Solution {
    public String[] permutation(String S) {
        char[] cs = S.toCharArray();
        Arrays.sort(cs);
        List<String> list = new LinkedList<>();
        backTrack(cs, new StringBuilder(), new boolean[cs.length], list);
        return list.toArray(new String[0]);
    }

    private void backTrack(char[] cs, StringBuilder path, boolean[] memo, List<String> list) {
        if (path.length() == cs.length) {
            list.add(path.toString());
            return;
        }
        
        char lastUsed = ' ';
        for (int i = 0; i < cs.length; i++) {            
            if (!memo[i] && cs[i] != lastUsed) {
                lastUsed = cs[i];
                memo[i] = true;
                backTrack(cs, path.append(cs[i]), memo, list);
                path.deleteCharAt(path.length() - 1);
                memo[i] = false;
            }
        }
    }
}
```
## 解法三
### 思路
- 使用字符交换代替StringBuilder拼接
- 使用递归算阶乘的方法，用数组代替动态数组
- 使用类变量记录结果对应的下标
### 代码
```java
class Solution {
    private int a = 0;

    public String[] permutation(String S) {
        String[] ans = new String[cal(S.length())];
        process(S.toCharArray(), 0, ans);
        return ans;
    }

    private void process(char[] cs, int index, String[] ans) {
        if (index == cs.length) {
            ans[a++] = new String(cs);
            return;
        }

        for (int i = index; i < cs.length; i++) {
            swap(cs, index, i);
            process(cs, index + 1, ans);
            swap(cs, index, i);
        }
    }

    private void swap(char[] cs, int x, int y) {
        if (x == y) {
            return;
        }

        char c = cs[x];
        cs[x] = cs[y];
        cs[y] = c;
    }

    private int cal(int len) {
        if (len <= 2) {
            return len;
        }

        return cal(len - 1) * len;
    }
}
```
# Interview_0808_有重复字符串的排列组合
## 题目
有重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合。

示例1:
```
 输入：S = "qqe"
 输出：["eqq","qeq","qqe"]
```
示例2:
```
 输入：S = "ab"
 输出：["ab", "ba"]
```
提示:
```
字符都是英文字母。
字符串长度在[1, 9]之间。
```
## 解法
### 思路
和0807解法一类似，使用set
### 代码
```java
class Solution {
    public String[] permutation(String S) {
        Set<String> set = new HashSet<>();
        backTrack(S, new StringBuilder(), new boolean[S.length()], set);
        return set.toArray(new String[0]);
    }

    private void backTrack(String s, StringBuilder path, boolean[] memo, Set<String> set) {
        if (path.length() == s.length()) {
            set.add(path.toString());
            return;
        }
        
        for (int i = 0; i < s.length(); i++) {
            if (!memo[i]) {
                memo[i] = true;
                backTrack(s, path.append(s.charAt(i)), memo, set);
                path.deleteCharAt(path.length() - 1);
                memo[i] = false;
            }
        }
    }
}
```
## 解法二
### 思路
和0807解法二类似：
- 字符串排序
- 使用临时变量记录当前层使用过的相同字符
### 代码
```java
class Solution {
    public String[] permutation(String S) {
        char[] cs = S.toCharArray();
        Arrays.sort(cs);
        List<String> list = new LinkedList<>();
        backTrack(cs, new StringBuilder(), new boolean[cs.length], list);
        return list.toArray(new String[0]);
    }

    private void backTrack(char[] cs, StringBuilder path, boolean[] memo, List<String> list) {
        if (path.length() == cs.length) {
            list.add(path.toString());
            return;
        }
        
        char lastUsed = ' ';
        for (int i = 0; i < cs.length; i++) {            
            if (!memo[i] && cs[i] != lastUsed) {
                memo[i] = true;
                lastUsed = cs[i];
                backTrack(cs, path.append(cs[i]), memo, list);
                path.deleteCharAt(path.length() - 1);
                memo[i] = false;
            }
        }
    }
}
```
# Interview_0809_括号
## 题目
括号。设计一种算法，打印n对括号的所有合法的（例如，开闭一一对应）组合。

说明：解集不能包含重复的子集。

例如，给出 n = 3，生成结果为：
```
[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
```
## 解法
### 思路
dfs：
- 根节点时左括号
- 退出条件：左右括号总数是否等于2n
- 递归时判断：
    - 如果左括号数小于n，可以继续append左括号递归
    - 如果右括号数小于n且左括号数大于右括号数，可以append右括号递归
### 代码
```java
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        dfs(ans, 0, 0, n, new StringBuilder());
        return ans;
    }
    
    private void dfs(List<String> list, int left, int right, int n, StringBuilder sb) {
        if (left + right == n * 2) {
            list.add(sb.toString());
            return;
        }
        
        if (left < n) {
            dfs(list, left + 1, right, n, sb.append('('));
            sb.deleteCharAt(sb.length() - 1);
        }
        
        if (right < n && right < left) {
            dfs(list, left, right + 1, n, sb.append(')'));
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
```
# Interview_0810_颜色填充
## 题目
颜色填充。编写函数，实现许多图片编辑软件都支持的“颜色填充”功能。给定一个屏幕（以二维数组表示，元素为颜色值）、一个点和一个新的颜色值，将新颜色值填入这个点的周围区域，直到原来的颜色值全都改变。

示例1:
```
 输入：
image = [[1,1,1],[1,1,0],[1,0,1]] 
sr = 1, sc = 1, newColor = 2
 输出：[[2,2,2],[2,2,0],[2,0,1]]
 解释: 
在图像的正中间，(坐标(sr,sc)=(1,1)),
在路径上所有符合条件的像素点的颜色都被更改成2。
注意，右下角的像素没有更改为2，
因为它不是在上下左右四个方向上与初始点相连的像素点。
```
说明：
```
image 和 image[0] 的长度在范围 [1, 50] 内。
给出的初始点将满足 0 <= sr < image.length 和 0 <= sc < image[0].length。
image[i][j] 和 newColor 表示的颜色值在范围 [0, 65535]内。
```
## 解法
### 思路
dfs：
- 从src节点开始bfs，发现相连的节点就进行变色，否则返回
- 增加记忆化搜索，否则改成相同颜色时会出现栈溢出
### 代码
```java
class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image == null || image.length == 0 || image[0].length == 0) {
            return image;
        }

        int color = image[sr][sc], row = image.length, col = image[0].length;
        dfs(image, sr, sc, row, col, color, newColor, new boolean[row][col]);
        return image;
    }

    private void dfs(int[][] image, int x, int y, int row, int col, int color, int newColor, boolean[][] memo) {
        if (x < 0 || x >= row || y < 0 || y >= col || image[x][y] != color || memo[x][y]) {
            return;
        }

        image[x][y] = newColor;
        memo[x][y] = true;
        
        dfs(image, x + 1, y, row, col, color, newColor, memo);
        dfs(image, x - 1, y, row, col, color, newColor, memo);
        dfs(image, x, y + 1, row, col, color, newColor, memo);
        dfs(image, x, y - 1, row, col, color, newColor, memo);
    }
}
```
## 解法二
### 思路
不使用记忆化搜索，其实只有当要改的颜色和目标颜色一致的时候才需要判断是否已经搜索过，所以当颜色一样时就直接返回image即可
### 代码
```java
class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image == null || image.length == 0 || image[0].length == 0 || image[sr][sc] == newColor) {
            return image;
        }

        int color = image[sr][sc], row = image.length, col = image[0].length;


        dfs(image, sr, sc, row, col, color, newColor);
        return image;
    }

    private void dfs(int[][] image, int x, int y, int row, int col, int color, int newColor) {
        if (x < 0 || x >= row || y < 0 || y >= col || image[x][y] != color) {
            return;
        }

        image[x][y] = newColor;

        dfs(image, x + 1, y, row, col, color, newColor);
        dfs(image, x - 1, y, row, col, color, newColor);
        dfs(image, x, y + 1, row, col, color, newColor);
        dfs(image, x, y - 1, row, col, color, newColor);
    }
}
```
# Interview_0811_硬币
## 题目
硬币。给定数量不限的硬币，币值为25分、10分、5分和1分，编写代码计算n分有几种表示法。(结果可能会很大，你需要将结果模上1000000007)

示例1:
```
 输入: n = 5
 输出：2
 解释: 有两种方式可以凑成总金额:
5=5
5=1+1+1+1+1
```
示例2:
```
 输入: n = 10
 输出：4
 解释: 有四种方式可以凑成总金额:
10=10
10=5+5
10=5+1+1+1+1+1
10=1+1+1+1+1+1+1+1+1+1
```
说明：
```
注意:

你可以假设：

0 <= n (总金额) <= 1000000
```
## 解法
### 思路
动态规划：
- `dp[i][j]`：第i种硬币时组成金额j的可能
- 状态转移方程：`dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i]]`
    - `dp[i - 1][j]`：不使用当前硬币
    - `dp[i][j - coin[i]]：使用当前硬币
- 初始化：
    - `dp[0][j] = 1`：只使用第1种硬币时的组合数都是1
    - `dp[i][0] = 1`：每种硬币组成0的组合数都是1，也就是取0个
- 过程：嵌套遍历
    - 外层遍历4种硬币，但因为第1种硬币的可能数已经初始化过，所以从第二种硬币开始状态转移
    - 内层遍历从1到n的所有可能
        - 如果当前n大于等于遍历的j，那么就可以累加一次使用当前币种的可能
        - 如果当前n小于遍历的j，那么说明只能不使用当前币种
### 代码
```java
class Solution {
    public int waysToChange(int n) {
        if (n < 5) {
            return 1;
        }

        if (n == 5) {
            return 2;
        }

        int[][] dp = new int[4][n + 1];
        int[] coins = {1, 5, 10, 25};

        for (int i = 0; i < 4; i++) {
            dp[i][0] = 1;
        }

        for (int j = 1; j <= n; j++) {
            dp[0][j] = 1;
        }

        for (int i = 1; i < 4; i++) {
            for (int j = 1; j <= n; j++) {
                if (coins[i] > j) {
                    dp[i][j] = dp[i - 1][j] % 1000000007;
                } else {
                    dp[i][j] = (dp[i - 1][j] + dp[i][j - coins[i]]) % 1000000007;
                }
            }
        }
        
        return dp[3][n];
    }
}
```
## 解法二
### 思路
伪代码：
```
for (i in (n / 25)):
    for (j in ((n - 25 * i) / 10):
        for (k in ((n - 25 * i - 10 * j) / 5)):
            ans += 1
```
简化后得到：
```
for (i in (n / 25)):
    j = (n - 25 * i) 10;
    ans += (j + 1) * (n / 5 - 5 * i - j + 1);
```
### 代码
```java
class Solution {
    public int waysToChange(int n) {
        int mod = 1000000007;
        long ans = 0;
        for (int i = 0; i <= n / 25; i++) {
            long j = (n - 25 * i) / 10;
            long num = (j + 1) * (n / 5 - 5 * i - j + 1);
            ans = (ans + num) % mod;
        }
        return (int) ans;
    }
}
```
# Interview_0812_八皇后
## 题目
设计一种算法，打印 N 皇后在 N × N 棋盘上的各种摆法，其中每个皇后都不同行、不同列，也不在对角线上。这里的“对角线”指的是所有的对角线，不只是平分整个棋盘的那两条对角线。

注意：本题相对原题做了扩展

示例:
```
 输入：4
 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 解释: 4 皇后问题存在如下两个不同的解法。
[
 [".Q..",  // 解法 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // 解法 2
  "Q...",
  "...Q",
  ".Q.."]
]
```
## 解法
### 思路
回溯：
- 判断是否能放置的三个因素：
    - 该行是否有皇后
    - 该列是否有皇后
    - 该点是否在有皇后的点的1和-1斜率的直线上
- 递归：
    - 参数：
        - 层数depth
        - 暂存的皇后的坐标值放入数组records中，下标为行，值为列，初始化为-1
    - 退出条件：判断当前递归层数是否为皇后个数，也就是矩阵行数
    - 递归过程：
        - 循环n列，根据records的记录判断当前点是否可以放置皇后
        - 记录到相应records中并递归
        - 回溯后重新置为-1，标记该行
### 代码
```java
class Solution {
public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        backTrack(0, new int[n], ans, n);
        return ans;
    }

    private void backTrack(int row, int[] records, List<List<String>> ans, int n) {
        if (row == n) {
            ans.add(print(records));
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isValid(records, row, col)) {
                records[row] = col;
                backTrack(row + 1, records, ans, n);
            }
        }
    }

    private boolean isValid(int[] records, int row, int col) {
        for (int r = 0; r < row; r++) {
            if (records[r] == col || Math.abs(row - r) == Math.abs(col - records[r])) {
                return false;
            }
        }

        return true;
    }

    private List<String> print(int[] records) {
        int len = records.length;
        List<String> ans = new ArrayList<>(len);

        for (int record : records) {
            StringBuilder sb = new StringBuilder();
            for (int col = 0; col < len; col++) {
                if (record == col) {
                    sb.append('Q');
                } else {
                    sb.append('.');
                }
            }
            ans.add(sb.toString());
        }

        return ans;
    }
}
```
# Interview_0813_堆箱子
## 题目
堆箱子。给你一堆n个箱子，箱子宽 wi、深 di、高 hi。箱子不能翻转，将箱子堆起来时，下面箱子的宽度、高度和深度必须大于上面的箱子。实现一种方法，搭出最高的一堆箱子。箱堆的高度为每个箱子高度的总和。

输入使用数组[wi, di, hi]表示每个箱子。

示例1:
```
 输入：box = [[1, 1, 1], [2, 2, 2], [3, 3, 3]]
 输出：6
```
示例2:
```
 输入：box = [[1, 1, 1], [2, 3, 4], [2, 6, 7], [3, 4, 5]]
 输出：10
```
提示:
```
箱子的数目不大于3000个。
```
## 解法
### 思路
动态规划：
- 根据宽度升序排列，如果宽度相同，根据深度升序排列，如果深度相同，根据高度排序，这样所有前面的箱子都可以放在后面的箱子上面
- 然后计算dp：
    - `dp[i]`：前i个元素能堆积出的最大的高度
    - 状态转移方程：遍历i之前的所有箱子，判断i的深度和高度是否大于该箱子，如果是，就将高度与该dp值累加，并和最大值作比较
    - 初始化：`dp[0] = box[0][2]`
### 代码
```java
class Solution {
    public int pileBox(int[][] box) {
        Arrays.sort(box, (x, y) -> x[0] == y[0] ? x[1] == y[1] ? y[2] - x[2] : y[1] - x[1] : x[0] - y[0]);
        int[] dp = new int[box.length];
        dp[0] = box[0][2];
        int ans = dp[0];
        for (int i = 0; i < box.length; i++) {
            int depth = box[i][1], hight = box[i][2], max = 0;
            for (int j = 0; j < i; j++) {
                if (box[j][1] < depth && box[j][2] < hight) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + hight;
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
```
# Interview_0814_布尔运算
## 题目
给定一个布尔表达式和一个期望的布尔结果 result，布尔表达式由 0 (false)、1 (true)、& (AND)、 | (OR) 和 ^ (XOR) 符号组成。实现一个函数，算出有几种可使该表达式得出 result 值的括号方法。

示例 1:
```
输入: s = "1^0|0|1", result = 0

输出: 2
解释: 两种可能的括号方法是
1^(0|(0|1))
1^((0|0)|1)
```
示例 2:
```
输入: s = "0&0&0&1^1|0", result = 1

输出: 10
```
提示：
```
运算符的数量不超过 19 个
```
## 解法
### 思路
记忆化分治搜索；
- `memo[i][j][r]`：记录坐标i与j之间能够计算成r的可能个数，初始化为-1
- 因为是布尔计算，所以结果只有1和-1两种，样本空间只有4，方便遍历
- 递归：
    - 参数：
        - 当前递归区间的表达式头尾指针，start，end
        - 目标结果`result`
        - 暂存的结果集合`memo`
        - 表达式`s`
    - 退出条件：
        - `start == end`：判断当前结果是否
        - `memo中的值不为-1`，返回该值
    - 过程：
        - 定义一个游标k遍历表达式区间，将表达式切割为左右两个部分，步长为2
        - 遍历下一层递归的左右两个子区间的值，都是1或0的两种可能
        - 判断遍历得到的两个值是否能通过当前层的表达式获得对应的结果
            - 如果可以，就以k为中点，递归下去
            - 将返回的结果相乘得到这种组合可能的所有可能数字
### 代码
```java
class Solution {
    public int countEval(String s, int result) {
        int n = s.length();
        int[][][] memo = new int[n][n][2];
        for (int start = 0; start < n; start++) {
            for (int end = 0; end < n; end++) {
                Arrays.fill(memo[start][end], -1);
            }
        }
        
        return rec(0, n - 1, memo, result, s);
    }

    private int rec(int start, int end, int[][][] memo, int result, String s) {
        if (start == end) {
            return s.charAt(start) - '0' == result ? 1 : 0;
        }

        if (memo[start][end][result] != -1) {
            return memo[start][end][result];
        }

        int ans = 0;

        for (int k = start; k < end; k += 2) {
            char operator = s.charAt(k + 1);
            for (int left = 0; left <= 1; left++) {
                for (int right = 0; right <= 1; right++) {
                    if (cal(left, right, operator) == result) {
                        ans += rec(start, k, memo, left, s) * rec(k + 2, end, memo, right, s);
                    }
                }
            }
        }

        memo[start][end][result] = ans;
        return ans;
    }

    private int cal(int left, int right, char operator) {
        if (operator == '|') {
            return left | right;
        } else if (operator == '&') {
            return left & right;
        } else {
            return left ^ right;
        }
    }
}
```
# Interview_1002_变位词组
## 题目
编写一种方法，对字符串数组进行排序，将所有变位词组合在一起。变位词是指字母相同，但排列不同的字符串。

注意：本题相对原题稍作修改

示例:
```
输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
输出:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
```
说明：
```
所有输入均为小写字母。
不考虑答案输出的顺序。
```
## 解法
### 思路
桶排序+散列表计数
### 代码
```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] cs = str.toCharArray();
            Arrays.sort(cs);
            String s = new String(cs);
            
            List<String> list = map.getOrDefault(s, new ArrayList<>());
            list.add(str);
            map.put(s, list);
        }
        
        return new ArrayList<>(map.values());
    }
}
```
# Interview_1003_搜索旋转数组
## 题目
搜索旋转数组。给定一个排序后的数组，包含n个整数，但这个数组已被旋转过很多次了，次数不详。请编写代码找出数组中的某个元素，假设数组元素原先是按升序排列的。若有多个相同元素，返回索引值最小的一个。

示例1:
```
 输入: arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 5
 输出: 8（元素5在该数组中的索引）
```
示例2:
```
 输入：arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 11
 输出：-1 （没有找到）
```
提示:
```
arr 长度范围在[1, 1000000]之间
```
## 解法
### 思路
二分查找：
- 退出条件，头尾指针相遇
- 指针移动过程：
    - 计算中间点
    - 头元素小于中间元素，说明`头中`这一段是一个升序
        - 如果target在`头中`这一段中，就将尾指针移到中点
        - 如果不在，就把头指针移到中+1
    - 头元素大于中间元素，说明`头中`这段可能包含了一旋转点
        - 如果target比头元素大，或者比中间元素小，就说明在这前半段里，尾指针移到中间点
        - 如果target不是如上状态，就将头指针移到中+1的位置
    - 如果头元素和中间元素相等，说明可能找到了target或者元素出现了重复
        - 如果头元素是target，那就是头尾元素相遇，返回这个坐标
        - 如果头元素不是target，说明是出现了重复的状态，向右移动头指针，逐一判断头元素之后的元素
### 代码
```java
class Solution {
    public int search(int[] arr, int target) {
        
        int head = 0, tail = arr.length - 1;
        while (head < tail) {
            int mid = head + (tail - head) / 2;

            if (arr[head] < arr[mid]) {
                if (arr[head] <= target && target <= arr[mid]) {
                    tail = mid;
                } else {
                    head = mid + 1;
                }
            } else if (arr[head] > arr[mid]) {
                if (arr[head] <= target || target <= arr[mid]) {
                    tail = mid;
                } else {
                    head = mid + 1;
                }
            } else if (arr[head] == arr[mid]) {
                if (arr[head] != target) {
                    head++;
                } else {
                    tail = head;
                }
            }
        }

        return arr[head] == target ? head : -1;
    }
}
```
# LeetCode_42_接雨水
## 题目
给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。

示例:
```
输入: [0,1,0,2,1,0,1,3,2,1,2,1]
输出: 6
```
## 解法
### 思路
嵌套循环：
- 遍历数组，获得当前高度
- 从当前元素开始向左右分别遍历，获得左右两边的最高值
- 求左右两边最高值之间的较小值，这个值比当前高度高的话，就可以盛水
- 将可以盛的水的值进行累加
### 代码
```java
class Solution {
    public int trap(int[] height) {
        int ans = 0, len = height.length;
        for (int i = 0; i < len; i++) {
            int maxL = 0, maxR = 0;
            
            for (int l = i; l >= 0; l--) {
                maxL = Math.max(maxL, height[l]);
            }
            
            for (int r = i; r < len; r++) {
                maxR = Math.max(maxR, height[r]);
            }
            
            ans += Math.min(maxL, maxR) - height[i];
        }
        
        return ans;
    }
}
```
## 解法二
### 思路
- 先分别遍历数组，记录当前坐标左右两边的最大值
- 再遍历数组，计算当前元素左右两边的最大值的最小值，累加这个最小值与当前元素的差值
### 代码
```java
class Solution {
    public int trap(int[] height) {
        int len = height.length;
        if (len == 0) {
            return 0;
        }
        
        int[] lMax = new int[len], rMax = new int[len];
        lMax[0] = height[0];
        rMax[len - 1] = height[len - 1];
        for (int i = 1; i < len; i++) {
            lMax[i] = Math.max(height[i], lMax[i - 1]);
        }
        
        for (int i = len - 2; i >= 0; i--) {
            rMax[i] = Math.max(height[i], rMax[i + 1]);
        }
        
        int ans = 0;
        for (int i = 0; i < len; i++) {
            ans += Math.min(lMax[i], rMax[i]) - height[i];
        }
        return ans;
    }
}
```
## 解法三
### 思路
单调栈：
- 遍历数组，使用栈记录数组下标：
    - 如果栈为空，直接入栈元素
    - 如果当前元素比栈顶元素小，说明当前这个高度是可以被之前遍历到的元素被覆盖住的，入栈元素
    - 如果当前元素比栈顶元素大，那说明当前元素和栈顶元素之前的元素形成了一个洼地
        - 将该元素出栈，记录这个坐标
        - 计算之前的坐标与当前元素坐标的距离
        - 计算当前元素和栈顶元素的最小值，减去出栈元素的高度，再乘以距离，获得当前元素与栈顶元素之间形成的没有被计算的洼地面积，累加到答案中
### 代码
```java
class Solution {
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int len = height.length;
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int ans = 0;
        for (int i = 1; i < len; i++) {
            while (height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int distance = i - stack.peek() - 1;
                int num = Math.min(height[i], height[stack.peek()]) - height[top];
                ans += distance * num;
            }
            stack.push(i);
        }
        return ans;
    }
}
```
## 解法四
### 思路
双指针：
- 变量：
    - 左右指针
    - 左右最大值
- 退出条件：左右指针相遇
- 过程：
    - 判断左右指针对应的元素大小：
        - 左元素小于右元素，
            - 如果左边已经遍历过的元素，有比当前的元素大的，那么就能形成一个洼地，可以累加这个洼地值
            - 如果当前元素比左边遍历过的元素大，那么就把当前值作为下一个洼地的左边高度，记录下来
        - 左边元素大于等于右边元素：
            - 逻辑和左边时类似
### 代码
```java
class Solution {
    public int trap(int[] height) {
        int l = 0, lMax = 0, r = height.length - 1, rMax = 0, ans = 0;
        while (l < r) {
            if (height[l] < height[r]) {
                if (lMax > height[l]) {
                    ans += lMax - height[l];
                } else {
                    lMax = height[l];
                }
                l++;
            } else {
                if (rMax > height[r]) {
                    ans += rMax - height[r];
                } else {
                    rMax = height[r];
                }
                r--;
            }
        }

        return ans;
    }
}
```
# Interview_1005_稀疏数组搜索
## 题目
稀疏数组搜索。有个排好序的字符串数组，其中散布着一些空字符串，编写一种方法，找出给定字符串的位置。

示例1:
```
 输入: words = ["at", "", "", "", "ball", "", "", "car", "", "","dad", "", ""], s = "ta"
 输出：-1
 说明: 不存在返回-1。
```
示例2:
```
 输入：words = ["at", "", "", "", "ball", "", "", "car", "", "","dad", "", ""], s = "ball"
 输出：4
```
提示:
```
words的长度在[1, 1000000]之间
```
## 解法
### 思路
遍历匹配
### 代码
```java
class Solution {
    public int findString(String[] words, String s) {
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if ("".equals(word)) {
                continue;
            }
            
            if (word.equals(s)) {
                return i;
            }
        }
        
        return -1;
    }
}
```
## 解法二
### 思路
二分搜索
### 代码
```java
class Solution {
    public int findString(String[] words, String s) {
        int l = 0, r = words.length - 1;
        while (l <= r) {
            while (l <= r && "".equals(words[l])) {
                l++;
            }
            while (l <= r && "".equals(words[r])) {
                r--;
            }
            int mid = l + (r - l) / 2;
            while (mid < r && "".equals(words[mid])) {
                mid++;
            }
            if (words[mid].equals(s)) {
                return mid;
            }
            
            if (s.compareTo(words[mid]) > 0) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        
        return -1;
    }
}
```
# Interview_1009_排序矩阵查找
## 题目
给定M×N矩阵，每一行、每一列都按升序排列，请编写代码找出某元素。

示例:
```
现有矩阵 matrix 如下：

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
给定 target = 5，返回 true。

给定 target = 20，返回 false。
```
## 解法
### 思路
dfs+记忆化搜索
### 代码
```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int row = matrix.length, col = matrix[0].length;
        return dfs(matrix, 0, 0, matrix.length, matrix[0].length, target, new boolean[row][col]);
    }

    private boolean dfs(int[][] matrix, int x, int y, int row, int col, int target, boolean[][] memo) {
        if (x >= row || y >= col || target < matrix[x][y] || memo[x][y]) {
            return false;
        }

        if (matrix[x][y] == target) {
            return true;
        }
        
        memo[x][y] = true;

        return dfs(matrix, x + 1, y, row, col, target, memo) ||
                dfs(matrix, x, y + 1, row, col, target, memo);
    }
}
```
## 解法二
### 思路
逐行二分查找
### 代码
```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        for (int[] row : matrix) {
            if (search(row, target)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean search(int[] row, int target) {
        int l = 0, r = row.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            
            if (row[mid] == target) {
                return true;
            }
            
            if (row[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return false;
    }
}
```
## 解法三
### 思路
- 从矩阵的左上和右下两个方向向中间走
- 行从右边开始从大到小搜索，如果比target大就左移
- 列从上边开始从小到大搜索，如果比target小就下移
### 代码
```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int row = matrix.length, col = matrix[0].length, ri = 0, ci = col - 1;

        while (ri < row && ci >= 0) {
            if (matrix[ri][ci] == target) {
                return true;
            }
            
            if (matrix[ri][ci] > target) {
                ci--;
            } else {
                ri++;
            }
        }
        
        return false;
    }
}
```
# LeetCode_460_LFU缓存
## 题目
设计并实现最不经常使用（LFU）缓存的数据结构。它应该支持以下操作：get 和 put。
```
get(key) - 如果键存在于缓存中，则获取键的值（总是正数），否则返回 -1。
put(key, value) - 如果键不存在，请设置或插入值。当缓存达到其容量时，它应该在插入新项目之前，使最不经常使用的项目无效。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，最近最少使用的键将被去除。
```
进阶：
```
你是否可以在 O(1) 时间复杂度内执行两项操作？
```
示例：
```
LFUCache cache = new LFUCache( 2 /* capacity (缓存容量) */ );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // 返回 1
cache.put(3, 3);    // 去除 key 2
cache.get(2);       // 返回 -1 (未找到key 2)
cache.get(3);       // 返回 3
cache.put(4, 4);    // 去除 key 1
cache.get(1);       // 返回 -1 (未找到 key 1)
cache.get(3);       // 返回 3
cache.get(4);       // 返回 4
```
## 解法
### 思路
两个散列表和一个双向链表：
- 参数：
    - 散列表cache保存节点
    - 散列表freqs保存次数与对应的节点链表
- 过程：
    - get：
        - 如果没有返回-1
        - 如果有，返回节点值，且移动其再freqs中的节点
    - put：
        - 如果大小超过了容量，就删除最近出现次数最少的节点，并插入新的节点
        - 如果没有，就新增一个节点，并查看是否有出现次数为1的，如果有就新增到链表头，否则就新增一个链表，并将节点插入
        - 如果有，就覆盖该值，并移动其在freq中的位置
### 代码
```java
class LFUCache {
    private Map<Integer, Node> cache;
    private Map<Integer, LinkedHashSet<Node>> freqs;
    private int size;
    private int cap;
    private int min;

    public LFUCache(int capacity) {
        this.cache = new HashMap<>();
        this.freqs = new HashMap<>();
        this.size = 0;
        this.cap = capacity;
        this.min = 0;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        incrFreq(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (cap == 0) {
            return;
        }

        Node node = cache.get(key);
        if (node != null) {
            node.value = value;
            incrFreq(node);
        } else {
            if (size == cap) {
                Node toDelNode = removeNode();
                cache.remove(toDelNode.key);
                size--;
            }
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addNode(newNode);
            size++;
        }
    }

    private void addNode(Node newNode) {
        LinkedHashSet<Node> set = freqs.computeIfAbsent(1, k -> new LinkedHashSet<>());
        set.add(newNode);
        min = 1;
    }

    private Node removeNode() {
        LinkedHashSet<Node> set = freqs.get(min);
        Node toDelNode = set.iterator().next();
        set.remove(toDelNode);
        return toDelNode;
    }

    private void incrFreq(Node node) {
        int freq = node.freq;
        LinkedHashSet<Node> set = freqs.get(freq);
        set.remove(node);
        if (freq == min && set.size() == 0) {
            min = freq + 1;
        }

        node.freq++;
        LinkedHashSet<Node> newSet = freqs.computeIfAbsent(freq + 1, k -> new LinkedHashSet<>());
        newSet.add(node);
    }
}

class Node {
    public int key;
    public int value;
    public int freq;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
        this.freq = 1;
    }
}
```
# Interview_1607_最大数值
## 题目
编写一个方法，找出两个数字a和b中最大的那一个。不得使用if-else或其他比较运算符。

示例：
```
输入： a = 1, b = 2
输出： 2
```
## 解法
### 思路
- 使用逻辑右移判断`a-b`获得是整数还是负数
- 用这个值乘以b，加上这个值与1的异或值乘以a，就获得了最大值
- 大的值乘以的是1，小的值乘以的是0
### 代码
```java
class Solution {
    public int maximum(int a, int b) {
        int bit = (int) (((long)a - (long)b) >>> 63);
        return a * (bit ^ 1) + b * bit;
    }
}
```