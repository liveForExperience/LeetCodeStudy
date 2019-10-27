# LeetCode_695_岛屿的最大面积
## 题目
给定一个包含了一些 0 和 1的非空二维数组 grid , 一个 岛屿 是由四个方向 (水平或垂直) 的 1 (代表土地) 构成的组合。你可以假设二维矩阵的四个边缘都被水包围着。

找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为0。)

示例 1:
```
[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]
对于上面这个给定矩阵应返回 6。注意答案不应该是11，因为岛屿只能包含水平或垂直的四个方向的‘1’。
```
示例 2:
```
[[0,0,0,0,0,0,0,0]]
对于上面这个给定的矩阵, 返回 0。
```
```
注意: 给定的矩阵grid 的长度和宽度都不超过 50。
```
## 解法
### 思路
- 和岛屿sink的思路一样，循环二维数组的所有下标，从该下标开始递归。
- 如果越界或遇到0就返回
- 将当前1设置成0，标记为已探索，同时计数
- 超四个方向继续递归
- 递归返回后将记录的值和现有最大值比较，保存最大值
- 循环结束返回
### 代码
```java
class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int[] arr = new int[]{0};
                dfs(grid, i, j, arr);
                max = Math.max(max, arr[0]);
            }
        }
        return max;
    }

    private void dfs(int[][] grid, int row, int col, int[] arr) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[row].length || grid[row][col] == 0) {
            return;
        }

        arr[0]++;
        grid[row][col] = 0;

        dfs(grid, row + 1, col, arr);
        dfs(grid, row - 1, col, arr);
        dfs(grid, row, col + 1, arr);
        dfs(grid, row, col - 1, arr);
    }
}
```
# LeetCode_62_不同路径
## 题目
一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

问总共有多少条不同的路径？

例如，上图是一个7 x 3 的网格。有多少可能的路径？

说明：m 和 n 的值均不超过 100。

示例 1:
```
输入: m = 3, n = 2
输出: 3
解释:
从左上角开始，总共有 3 条路径可以到达右下角。
1. 向右 -> 向右 -> 向下
2. 向右 -> 向下 -> 向右
3. 向下 -> 向右 -> 向右
```
示例 2:
```
输入: m = 7, n = 3
输出: 28
```
## 失败解法
### 思路
递归计数
### 失败原因
超时
### 代码
```java
class Solution {
    private int sum = 0;

    public int uniquePaths(int m, int n) {
        recurse(0, 0, m, n);
        return sum;
    }

    private void recurse(int r, int c, int m, int n) {
        if (r < 0 || r >= m || c < 0 || c >= n) {
            return;
        }
        
        if (r == m - 1 && c == n - 1) {
            sum++;
            return;
        }
        
        recurse(r + 1, c, m, n);
        recurse(r, c + 1, m, n);
    }
}
```
## 解法
### 思路
动态规划：
- dp[i][j]：保存到达当前下标的可能路径个数
- base case：第一行和第一列的dp值
- 状态转移方程：`dp[i][j] = dp[i - 1][j] + dp[i][j - 1]`
- 结果：dp[m - 1][n - 1]
### 代码
```java
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        dp[0][0] = 1;

        Arrays.fill(dp[0], 1);
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }
}
```
## 解法二
### 思路
只需要使用一维数组，因为在计算当前下标可能路径时，每一列的元素可以共用一个下标，因为每一行的这一列计算得到的值会用在下一行的计算上。
### 代码
```java
class Solution {
    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] = dp[j] + dp[j - 1];
            }
        }
        
        return dp[n - 1];
    }
}
```
# LeetCode_398_随机数索引
## 题目
给定一个可能含有重复元素的整数数组，要求随机输出给定的数字的索引。 您可以假设给定的数字一定存在于数组中。
```
注意：
数组大小可能非常大。 使用太多额外空间的解决方案将不会通过测试。
```
示例:
```
int[] nums = new int[] {1,2,3,3,3};
Solution solution = new Solution(nums);

// pick(3) 应该返回索引 2,3 或者 4。每个索引的返回概率应该相等。
solution.pick(3);

// pick(1) 应该返回 0。因为只有nums[0]等于1。
solution.pick(1);
```
## 解法
### 思路
流水线问题：
- 推导原理：
    1. 假设数据流只有一个元素，当获取到元素后就返回，概率是1
    2. 假设数据流只有两个元素，当获取第一个元素的时候，给该元素设定50%的获取概率，给第2个元素设置50%的获取概率。获取的方式就是计算随机数，如果小于0.5就是第1个数，如果大于等于0.5就是第2个数
    3. 假设数据流只有三个元素，当获取第一个和第二个元素的时候，和第2步一样先获取到这个数，然后再找第三个数，这时给获取前一个数2/3的概率，第三个数设置为1/3的概率，然后通过计算随机数来决定算用哪个数
    4. 假设数据流有n个元素，那么每一次的元素获取概率就是`(n - 1) / n`和`1 / n`
### 代码
```java
class Solution {
    private int[] nums;
    public Solution(int[] nums) {
        this.nums = nums;
    }

    public int pick(int target) {
        Random r = new Random();
        int count = 0, index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                count++;

                if (r.nextInt() % count == 0) {
                    index = i;
                }
            }
        }
        return index;
    }
}
```
# LeetCode_515_在每个树行中找最大值
## 题目
您需要在二叉树的每一行中找到最大的值。

示例：
```
输入: 

          1
         / \
        3   2
       / \   \  
      5   3   9 

输出: [1, 3, 9]
```
## 解法
### 思路
bfs，记录每一行最大值
### 代码
```java
class Solution {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int count = queue.size(), max = Integer.MIN_VALUE;
            while (count-- > 0) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }
                
                max = Math.max(max, node.val);
                
                if (node.left != null) {
                    queue.offer(node.left);
                }
                
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            ans.add(max);
        }
        
        return ans;
    }
}
```
# LeetCode_378_有序矩阵中第k小的元素
## 题目
给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第k小的元素。
```
请注意，它是排序后的第k小元素，而不是第k个元素。
```
示例:
```
matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

返回 13。
说明:
你可以假设 k 的值永远是有效的, 1 ≤ k ≤ n2 。
```
## 解法
### 思路
二分查找：
- 头尾指针分别代表矩阵中的最大值和最小值
- 通过二分找到中间值，查找矩阵中比中间值小或等于的元素个数
- 如果元素个数小于k，说明包括中间值在内的元素太小，需要将头指针指向中间值+1
- 如果元素个数大于等于k，则说明包括中间值在内的元素都有可能是第k个元素，所以尾指针指向中间值
- 最终退出循环必定是头尾指针相等的情况，返回尾指针即可
### 代码
```java
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int row = matrix.length, col = matrix[row - 1].length, head = matrix[0][0], tail = matrix[row - 1][col - 1];
        while (head < tail) {
            int mid = head + (tail - head) / 2 , count = 0;
            
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (matrix[i][j] <= mid) {
                        count++;
                    } else {
                        break;
                    }
                }
            }
            
            if (count < k) {
                head = mid + 1;
            } else {
                tail = mid;
            }
        }
        
        return tail;
    }
}
```
## 优化代码
### 思路
优化计算小于中间值的元素个数的算法：
- 从矩阵的左下角元素开始进行判断
- 判断当前坐标元素是否<=mid
    - 如果是：说明当前列元素一定都<=mid，将整列累加
    - 如果不是：就要向上寻找<=mid的元素，同时也代表之后的每一列所累加的数也是到寻找到的那个元素所在行为止
### 代码
```java
public class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int row = matrix.length, col = matrix[row - 1].length, head = matrix[0][0], tail = matrix[row - 1][col - 1];
        while (head < tail) {
            int mid = head + (tail - head) / 2 , count = 0, r = row - 1, c = 0;

            while (r >= 0 && c < col) {
                if (matrix[r][c] <= mid) {
                    count += r + 1;
                    c++;
                } else {
                    r--;
                }
            }

            if (count < k) {
                head = mid + 1;
            } else {
                tail = mid;
            }
        }

        return tail;
    }
}
```
# LeetCode_835_图像重叠
## 题目
给出两个图像 A 和 B ，A 和 B 为大小相同的二维正方形矩阵。（并且为二进制矩阵，只包含0和1）。

我们转换其中一个图像，向左，右，上，或下滑动任何数量的单位，并把它放在另一个图像的上面。之后，该转换的重叠是指两个图像都具有 1 的位置的数目。

（请注意，转换不包括向任何方向旋转。）

最大可能的重叠是什么？

示例 1:
```
输入：A = [[1,1,0],
          [0,1,0],
          [0,1,0]]
     B = [[0,0,0],
          [0,1,1],
          [0,0,1]]
输出：3
解释: 将 A 向右移动一个单位，然后向下移动一个单位。
```
注意: 
```
1 <= A.length = A[0].length = B.length = B[0].length <= 30
0 <= A[i][j], B[i][j] <= 1
```
## 解法
### 思路
- 设置一个二维数组count[][]，用来记录x和y轴分别移动多少次后能够匹配到1的次数
- 4层循环二维数组：
    - 外面两层：遍历二维矩阵A的所有元素，如果碰到元素为1，开始内层嵌套循环
    - 里面两层：遍历二维矩阵B的所有元素，如果碰到元素为1，就计算和外层A元素的坐标差，然后count对应的下标为止累加
    - count的下标是坐标差+N，原因是，向上和向下或向左向右是不同的方式，不仅防止为负数也可以做到将不同的方式区分，所以`count[][]`初始化的时候需要开辟`2 * N + 1`长度(+1是因为会有类似`0 - (n - 1)`)
- 遍历count元素个数，找到最大值返回
### 代码
```java
class Solution {
    public int largestOverlap(int[][] A, int[][] B) {
        int len = A.length;
        int[][] count = new int[2 * len + 1][2 * len + 1];
        
        for (int ra = 0; ra < len; ra++) {
            for (int ca = 0; ca < len; ca++) {
                if (A[ra][ca] == 1) {
                    for (int rb = 0; rb < len; rb++) {
                        for (int cb = 0; cb < len; cb++) {
                            if (B[rb][cb] == 1) {
                                count[ra - rb + len][ca - cb + len]++;
                            }
                        }
                    }
                }
            }
        }
        
        int ans = 0;
        for (int i = 0; i < count.length; i++) {
            for (int j = 0; j < count.length; j++) {
                ans = Math.max(count[i][j], ans);
            }
        }
        return ans;
    }
}
```
# LeetCode_36_有效的数独
## 题目
判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
```
数字 1-9 在每一行只能出现一次。
数字 1-9 在每一列只能出现一次。
数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
```
上图是一个部分填充的有效的数独。

数独部分空格内已填入了数字，空白格用 '.' 表示。

示例 1:
```
输入:
[
  ["5","3",".",".","7",".",".",".","."],
  ["6",".",".","1","9","5",".",".","."],
  [".","9","8",".",".",".",".","6","."],
  ["8",".",".",".","6",".",".",".","3"],
  ["4",".",".","8",".","3",".",".","1"],
  ["7",".",".",".","2",".",".",".","6"],
  [".","6",".",".",".",".","2","8","."],
  [".",".",".","4","1","9",".",".","5"],
  [".",".",".",".","8",".",".","7","9"]
]
输出: true
```
示例 2:
```
输入:
[
  ["8","3",".",".","7",".",".",".","."],
  ["6",".",".","1","9","5",".",".","."],
  [".","9","8",".",".",".",".","6","."],
  ["8",".",".",".","6",".",".",".","3"],
  ["4",".",".","8",".","3",".",".","1"],
  ["7",".",".",".","2",".",".",".","6"],
  [".","6",".",".",".",".","2","8","."],
  [".",".",".","4","1","9",".",".","5"],
  [".",".",".",".","8",".",".","7","9"]
]
输出: false
解释: 除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。
     但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。
```
说明:
```
一个有效的数独（部分已被填充）不一定是可解的。
只需要根据以上规则，验证已经填入的数字是否有效即可。
给定数独序列只包含数字 1-9 和字符 '.' 。
给定数独永远是 9x9 形式的。
```
## 解法
### 思路
硬做：
- 分三部分检验：
    - 每行遍历
    - 每列遍历
    - 每块遍历
- 在检验过程中，将元素放入长短为10的数组中，元素值对应数组下标，判断是否有重复
### 代码
```java
class Solution {
    public boolean isValidSudoku(char[][] board) {
        for (char[] row : board) {
            int[] arr = new int[10];
            for (char c : row) {
                if (c != '.') {
                    if (arr[Character.digit(c, 10)] != 0) {
                        return false;
                    } else {
                        arr[Character.digit(c, 10)]++;
                    }
                }
            }
        }

        for (int c = 0; c < 9; c++) {
            int[] arr = new int[10];
            for (int r = 0; r < 9; r++) {
                if (board[r][c] != '.') {
                    if (arr[Character.digit(board[r][c], 10)] != 0) {
                        return false;
                    } else {
                        arr[Character.digit(board[r][c], 10)]++;
                    }
                }
            }
        }

        for (int b = 0; b < 9; b++) {
            int r = b / 3 * 3, c = b % 3 * 3, rm = r + 3, cm = c + 3;
            int[] arr = new int[10];
            for (int i = r; i < rm; i++) {
                for (int j = c; j < cm; j++) {
                    if (board[i][j] != '.') {
                        if (arr[Character.digit(board[i][j], 10)] != 0) {
                            return false;
                        } else {
                            arr[Character.digit(board[i][j], 10)]++;
                        }
                    }
                }
            }
        }

        return true;
    }
}
```
## 解法二
### 思路
- 使用3个长度为9的数组，且元素为set的方式记录横竖和小方块的三种情况
- 每一个数组下标对应一种情况的9个小情况
- 如果遍历到的元素在set中存在，就返回false
- 计算小方块的方式就是通过`r / 3 * 3 + c / 3`来计算
### 代码
```java
class Solution {
    public boolean isValidSudoku(char[][] board) {
        HashSet[] rowArr = new HashSet[9];
        HashSet[] colArr = new HashSet[9];
        HashSet[] boxArr = new HashSet[9];

        for (int i = 0; i < 9; i++) {
            rowArr[i] = new HashSet<Integer>();
            colArr[i] = new HashSet<Integer>();
            boxArr[i] = new HashSet<Integer>();
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    int box = i / 3 * 3 + j / 3;
                    
                    if (rowArr[i].contains(c) || colArr[j].contains(c) || boxArr[box].contains(c)) {
                        return false;
                    }
                    
                    rowArr[i].add(c);
                    colArr[j].add(c);
                    boxArr[box].add(c);
                }
            }
        }
        
        return true;
    }
}
```
# LeetCode_1143_最长公共子序列
## 题目
给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列。
```
一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
```
若这两个字符串没有公共子序列，则返回 0。

示例 1:
```
输入：text1 = "abcde", text2 = "ace" 
输出：3  
解释：最长公共子序列是 "ace"，它的长度为 3。
```
示例 2:
```
输入：text1 = "abc", text2 = "abc"
输出：3
解释：最长公共子序列是 "abc"，它的长度为 3。
```
示例 3:
```
输入：text1 = "abc", text2 = "def"
输出：0
解释：两个字符串没有公共子序列，返回 0。
```
## 解法
### 思路
动态规划：求text1的i长度和text2的j长度情况下，重叠的字符数是多少。
- dp[i][j]：在text1的第i个元素结尾的序列和text2第j个元素结尾的序列，能找到的最长公共子序列的长度
- base case：`dp[0][1] = 0`、`dp[1][0] = 0`、`dp[0][0] = 0`
- 状态转移方程：`dp[i][j] = max(dp[i - 1][j], dp[i][j-1], dp[i-1][j-1] + t1[i] == t2[j] ? 1 : 0`
- 过程：嵌套循环遍历两个字符串，计算dp
- 结果：`dp[text1.length][text2.length]`
### 代码
```java
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length(), len2 = text2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                dp[i][j] = max3(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1] + (text1.charAt(i - 1) == text2.charAt(j - 1) ? 1 : 0));
            }
        }
        
        return dp[len1][len2];
    }
    
    private int max3(int x, int y, int z) {
        return Math.max(x, Math.max(y, z));
    }
}
```
## 优化代码
### 思路
状态转移方程还可以在简化：
- 两个字符串指定长度下，最后一个元素是否相等：
    - 如果相等，那么只要计算两个长度-1状态下的dp长度再加上当前长度1就是当前长度的最长公共子序列
    - 如果不相等，那么就计算两个字符串分别-1和另一个字符串当前长度时dp记录的长度，取最大值就可以
- 同时将两个字符串为零的状态先初始化
### 代码
```java
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length(), len2 = text2.length();
        char[] cs1 = text1.toCharArray(), cs2 = text2.toCharArray();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i < len1; i++) {
            dp[i][0] = 0;
        }

        for (int i = 0; i < len2; i++) {
            dp[0][i] = 0;
        }

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (cs1[i - 1] == cs2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        return dp[len1][len2];
    }
}
```
# LeetCode_427_建立四叉树
## 题目
我们想要使用一棵四叉树来储存一个 N x N 的布尔值网络。网络中每一格的值只会是真或假。树的根结点代表整个网络。对于每个结点, 它将被分等成四个孩子结点直到这个区域内的值都是相同的.

每个结点还有另外两个布尔变量: isLeaf 和 val。isLeaf 当这个节点是一个叶子结点时为真。val 变量储存叶子结点所代表的区域的值。

由上文的定义，它能被这样分割：
```
对应的四叉树应该像下面这样，每个结点由一对 (isLeaf, val) 所代表.

对于非叶子结点，val 可以是任意的，所以使用 * 代替。
```
提示：
```
N 将小于 1000 且确保是 2 的整次幂。
```
## 解法
### 思路
递归：
- 将矩阵分成4个部分，分别确定4个部分的边界，如果全是同样的val就作为叶子节点，否则继续递归，直到每一部分的元素只有1个为止
- 每次递归都会把4部分的边界确定
### 代码
```java
class Solution {
    public Node construct(int[][] grid) {
        return recurse(grid, 0, grid.length - 1, 0, grid[0].length - 1);
    }

    private Node recurse(int[][] grid, int rowStart, int rowEnd, int colStart, int colEnd) {
        if (rowStart > rowEnd || colStart > colEnd) {
            return null;
        }

        if (rowStart == rowEnd && colStart == colEnd) {
            return new Node(grid[rowStart][colStart] == 1, true, null, null, null, null);
        }

        Node node = new Node();
        boolean isLeaf = true;
        for (int i = rowStart + 1; i <= rowEnd; i++) {
            for (int j = colStart + 1; j <= colEnd; j++) {
                if (grid[i][j] != grid[i][j - 1] || grid[i - 1][j] != grid[i][j]) {
                    isLeaf = false;
                    break;
                }
            }
            if (!isLeaf) {
                break;
            }
        }
        
        if (isLeaf) {
            node.isLeaf = true;
            node.val = grid[rowStart][colStart] == 1;
            return node;
        } else {
            node.topLeft = recurse(grid, rowStart, rowStart + (rowEnd - rowStart) / 2, colStart, colStart + (colEnd - colStart) / 2);
            node.topRight = recurse(grid, rowStart, rowStart + (rowEnd - rowStart) / 2, colStart + (colEnd - colStart) / 2 + 1, colEnd);
            node.bottomLeft = recurse(grid, rowStart + (rowEnd - rowStart) / 2 + 1, rowEnd, colStart, colStart + (colEnd - colStart) / 2);
            node.bottomRight = recurse(grid, rowStart + (rowEnd - rowStart) / 2 + 1, rowEnd, colStart + (colEnd - colStart) / 2 + 1, colEnd);  
        }
        return node;
    }
}
```
# LeetCode_998_最大二叉树II
## 题目
最大树定义：一个树，其中每个节点的值都大于其子树中的任何其他值。

给出最大树的根节点 root。

就像之前的问题那样，给定的树是从表 A（root = Construct(A)）递归地使用下述 Construct(A) 例程构造的：
```
如果 A 为空，返回 null
否则，令 A[i] 作为 A 的最大元素。创建一个值为 A[i] 的根节点 root
root 的左子树将被构建为 Construct([A[0], A[1], ..., A[i-1]])
root 的右子树将被构建为 Construct([A[i+1], A[i+2], ..., A[A.length - 1]])
返回 root
请注意，我们没有直接给定 A，只有一个根节点 root = Construct(A).
```
假设 B 是 A 的副本，并附加值 val。保证 B 中的值是不同的。

返回 Construct(B)。

示例 1：
```
输入：root = [4,1,3,null,null,2], val = 5
输出：[5,4,null,1,3,null,null,2]
解释：A = [1,4,2,3], B = [1,4,2,3,5]
```
示例 2：
```
输入：root = [5,2,4,null,1], val = 3
输出：[5,2,4,null,1,null,3]
解释：A = [2,1,5,4], B = [2,1,5,4,3]
```
示例 3：
```
输入：root = [5,2,3,null,1], val = 4
输出：[5,2,4,null,1,3]
解释：A = [2,1,5,3], B = [2,1,5,3,4]
```
提示：
```
1 <= B.length <= 100
```
## 解法
### 思路
- 根据题意，`val`是放在生成最大树的数组的尾部。
- 遍历树节点：
    - 如果`val`值大于当前节点值，说明当前当前节点是`val`值所生成的节点的左子树
    - 如果`val`值小于当前节点值，需要去右子树继续寻找
### 代码
```java
class Solution {
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        if (root.val > val) {
            root.right = insertIntoMaxTree(root.right, val);
            return root;
        }

        TreeNode node = new TreeNode(val);
        node.left = root;
        return node;
    }
}
```
# LeetCode_654_最大二叉树
## 题目
给定一个不含重复元素的整数数组。一个以此数组构建的最大二叉树定义如下：
```
二叉树的根是数组中的最大元素。
左子树是通过数组中最大值左边部分构造出的最大二叉树。
右子树是通过数组中最大值右边部分构造出的最大二叉树。
通过给定的数组构建最大二叉树，并且输出这个树的根节点。
```
示例 ：
```
输入：[3,2,1,6,0,5]
输出：返回下面这棵树的根节点：

      6
    /   \
   3     5
    \    / 
     2  0   
       \
        1
```
提示：
```
给定的数组的大小在 [1, 1000] 之间。
```
## 解法
### 思路
递归：
- 参数：
    - 数组`nums[]`
    - 数组的开始`start`和结束`end`下标
- 退出条件
    - `start` > `end`：返回null
    - `start` == `end`：返回当前元素值的节点
- 递归过程：通过`start`和`end`来找区间中的最大值，作为当前递归层的节点值，然后通过确定左右子树所用的数组的边界来继续递归生成左右子树
- 返回后返回当前节点
### 代码
```java
class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }
    
    private TreeNode dfs(int[] nums, int start, int end) {
        if (start == end) {
            return new TreeNode(nums[start]);
        }
        
        if (start > end) {
            return null;
        }
        
        int index = start;
        for (int i = start + 1; i <= end; i++) {
            if (nums[i] > nums[index]) {
                index = i;
            }
        }
        
        TreeNode node = new TreeNode(nums[index]);
        node.left = dfs(nums, start, index - 1);
        node.right = dfs(nums, index + 1, end);
        return node;
    }
}
```
# LeetCode_667_优美的排列II
## 题目
给定两个整数 n 和 k，你需要实现一个数组，这个数组包含从 1 到 n 的 n 个不同整数，同时满足以下条件：

① 如果这个数组是 [a1, a2, a3, ... , an] ，那么数组 [|a1 - a2|, |a2 - a3|, |a3 - a4|, ... , |an-1 - an|] 中应该有且仅有 k 个不同整数；.

② 如果存在多种答案，你只需实现并返回其中任意一种.

示例 1:
```
输入: n = 3, k = 1
输出: [1, 2, 3]
解释: [1, 2, 3] 包含 3 个范围在 1-3 的不同整数， 并且 [1, 1] 中有且仅有 1 个不同整数 : 1
```
示例 2:
```
输入: n = 3, k = 2
输出: [1, 3, 2]
解释: [1, 3, 2] 包含 3 个范围在 1-3 的不同整数， 并且 [2, 1] 中有且仅有 2 个不同整数: 1 和 2
```
提示:
```
 n 和 k 满足条件 1 <= k < n <= 104.
```
## 解法
### 思路
根据题目要求，可以通过`[1,k+1,2,k,3,k-1,....k+1,k+2,...n]`得到题目要求的数组
### 代码
```java
class Solution {
    public int[] constructArray(int n, int k) {
        int[] ans = new int[n];
        int num = k;
        for (int i = 0; i < n; i++) {
            if (i >= num + 1) {
                ans[i] = i + 1;
            } else {
                if (i % 2 == 0) {
                    ans[i] = num - k + 1;
                } else {
                    ans[i] = k-- + 1;
                }
            }
        }
        return ans;
    }
}
```
# LeetCode_526_优美的排列
## 题目
假设有从 1 到 N 的 N 个整数，如果从这 N 个数字中成功构造出一个数组，使得数组的第 i 位 (1 <= i <= N) 满足如下两个条件中的一个，我们就称这个数组为一个优美的排列。条件：
```
第 i 位的数字能被 i 整除
i 能被第 i 位上的数字整除
现在给定一个整数 N，请问可以构造多少个优美的排列？
```
示例1:
```
输入: 2
输出: 2
```
解释: 
```
第 1 个优美的排列是 [1, 2]:
  第 1 个位置（i=1）上的数字是1，1能被 i（i=1）整除
  第 2 个位置（i=2）上的数字是2，2能被 i（i=2）整除

第 2 个优美的排列是 [2, 1]:
  第 1 个位置（i=1）上的数字是2，2能被 i（i=1）整除
  第 2 个位置（i=2）上的数字是1，i（i=2）能被 1 整除
```
说明:
```
N 是一个正整数，并且不会超过15。
```
## 解法
### 思路
回溯算法：
- 初始化数组
- 递归
    - 参数：
        - 数组`nums[]`
        - 移动的下标：`index`
    - 退出条件：`index`等于`nums[]`的长度，说明交换已经结束，且在交换过程中也已经验证过了每一个元素是符合题目要求的，所以当前状态就可以对累加值+1
    - 过程：
        - 从`index`开始循环，将`index`之后的所有元素和当前`index`元素进行交换，用来遍历所有的可能性
        - 交换完成后，判断当前元素是否符合题目要求`nums[i] % (i + 1) == 0 || (i + 1) % nums[i] == 0 `，如果符合就`index + 1`，继续递归到下一层。
        - 递归返回后，将元素返回到原始状态，进入下个循环，尝试下一种可能
- 递归结束后，返回结果`count`
### 代码
```java

```