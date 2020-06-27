# LeetCode_51_N皇后II
## 题目
n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。

给定一个整数 n，返回 n 皇后不同的解决方案的数量。

示例:
```
输入: 4
输出: 2
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
提示：
```
皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一或七步，可进可退。（引用自 百度百科 - 皇后 ）
```
## 解法
### 思路
dfs+backtarck
### 代码
```java
class Solution {
    private int ans = 0, num;
    private int[] row, col, pie, na;

    public int totalNQueens(int n) {
        num = n;
        row = new int[n];
        col = new int[n];
        pie = new int[2 * n - 1];
        na = new int[4 * n - 1];
        backtrack(0);
        return ans;
    }

    private void backtrack(int r) {
        for (int c = 0; c < num; c++) {
            if (canPut(r, c)) {
                put(r, c);
                if (r == num - 1) {
                    ans++;
                } else {
                    backtrack(r + 1);
                }
                remove(r, c);
            }
        }
    }

    private boolean canPut(int r, int c) {
        return row[r] + col[c] + pie[r + c] + na[r - c + 2 * num] == 0;
    }
    
    private void put(int r, int c) {
        row[r] = 1;
        col[c] = 1;
        pie[r + c] = 1;
        na[r - c + 2 * num] = 1;
    }
    
    private void remove(int r, int c) {
        row[r] = 0;
        col[c] = 0;
        pie[r + c] = 0;
        na[r - c + 2 * num] = 0;
    }
}
```
# LeetCode_57_插入区间
## 题目
给出一个无重叠的 ，按照区间起始端点排序的区间列表。

在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。

示例 1:
```
输入: intervals = [[1,3],[6,9]], newInterval = [2,5]
输出: [[1,5],[6,9]]
```
示例 2:
```
输入: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
输出: [[1,2],[3,10],[12,16]]
解释: 这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
```
## 解法
### 思路
贪心：
- 初始化作为结果返回的二维数组`ans`
- 遍历二维数组，将遍历到的数组起始值小于新数组起始值的数组直接放入`ans`
- 将新数组放入`ans`：
    - 如果新数组的起始值大于`ans`最后一个数组的结尾值，或者`ans`没有数组，直接插入新数组。
    - 如果新数组与最后一个数组的结尾值有重合，就更新这个数组，取两个数组结尾值的最大值，做到融合
- 遍历剩下的数组，如果还是与`ans`的最后一个数组相交：
    - 遍历到的数组的起始值小于等于最后数组的结尾值，就继续融合
    - 否则直接放入`ans`中
### 代码
```java
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        LinkedList<int[]> list = new LinkedList<>();
        int newStart = newInterval[0], newEnd = newInterval[1];
        int index = 0, len = intervals.length;

        while (index < len && newStart > intervals[index][0]) {
            list.add(intervals[index++]);
        }
        
        if (list.isEmpty() || list.getLast()[1] < newStart) {
            list.add(newInterval);
        } else {
            int[] interval = list.removeLast();
            interval[1] = Math.max(interval[1], newEnd);
            list.add(interval);
        }
        
        while (index < len) {
            if (list.getLast()[1] < intervals[index][0]) {
                list.add(intervals[index++]);
            } else {
                int[] interval = list.removeLast();
                interval[1] = Math.max(interval[1], intervals[index++][1]);
                list.add(interval);
            }
        }
        
        return list.toArray(new int[0][0]);
    }
}
```
# LeetCode_61_旋转链表
## 题目
给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。

示例 1:
```
输入: 1->2->3->4->5->NULL, k = 2
输出: 4->5->1->2->3->NULL
解释:
向右旋转 1 步: 5->1->2->3->4->NULL
向右旋转 2 步: 4->5->1->2->3->NULL
```
示例 2:
```
输入: 0->1->2->NULL, k = 4
输出: 2->0->1->NULL
解释:
向右旋转 1 步: 2->0->1->NULL
向右旋转 2 步: 1->2->0->NULL
向右旋转 3 步: 0->1->2->NULL
向右旋转 4 步: 2->0->1->NULL
```
## 解法
### 思路
- 先遍历一次链表：
    - 计算长度
    - 找到尾节点，头尾相连
- 从头节点开始移动`len - k % len`次，模拟右移
- 将pre的next指针指向null
- node指针指向作为头节点返回
### 代码
```java
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        
        if (k == 0) {
            return head;
        }

        ListNode node = head, pre = null;
        int len = 0;
        while (node != null) {
            pre = node;
            node = node.next;
            len++;
        }

        pre.next = head;
        node = head;
        k = Math.abs(len - k % len);
        while (k-- > 0) {
            pre = node;
            node = node.next;
        }

        head = node;
        pre.next = null;
        
        return head;
    }
}
```
# LeetCode_63_不同路径II
## 题目
一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？

网格中的障碍物和空位置分别用 1 和 0 来表示。

说明：m 和 n 的值均不超过 100。

示例 1:
```
输入:
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
输出: 2
```
解释:
```
3x3 网格的正中间有一个障碍物。
从左上角到右下角一共有 2 条不同的路径：
1. 向右 -> 向右 -> 向下 -> 向下
2. 向下 -> 向下 -> 向右 -> 向右
```
## 失败解法
### 原因
超时
### 思路
dfs
### 代码
```java
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }
        
        return recurse(obstacleGrid, 0, 0, obstacleGrid.length, obstacleGrid[0].length);
    }
    
    private int recurse(int[][] board, int r, int c, int row, int col) {
        int count = 0;
        if (isValid(board, r, c, row, col)) {
            if (r == row - 1 && c == col - 1) {
                return 1;
            }
            
            count = recurse(board, r + 1, c, row, col) + recurse(board, r, c + 1, row, col);
        }
        return count;
    }
    
    private boolean isValid(int[][] board, int r, int c, int row, int col) {
        return r < row && c < col && board[r][c] != 1;
    }
}
```
## 解法
### 思路
dfs+记忆化搜索
### 代码
```java
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }
        
        int row = obstacleGrid.length, col = obstacleGrid[0].length;
        int[][] memo = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                memo[i][j] = -1;
            }
        }
        
        return recurse(obstacleGrid, 0, 0, row, col, memo);
    }

    private int recurse(int[][] board, int r, int c, int row, int col, int[][] memo) {
        int count = 0;
        if (isValid(board, r, c, row, col)) {
            if (memo[r][c] != -1) {
                return memo[r][c];
            }
            
            if (r == row - 1 && c == col - 1) {
                return 1;
            }
            
            count = recurse(board, r + 1, c, row, col, memo) + recurse(board, r, c + 1, row, col, memo);
            memo[r][c] = count;
        }
        
        return count;
    }
    
    private boolean isValid(int[][] board, int r, int c, int row, int col) {
        return r < row && c < col && board[r][c] != 1;
    }
}
```
# LeetCode_68_文本左右对齐
## 题目
给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。

你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。

要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。

文本的最后一行应为左对齐，且单词之间不插入额外的空格。

说明:
```
单词是指由非空格字符组成的字符序列。
每个单词的长度大于 0，小于等于 maxWidth。
输入单词数组 words 至少包含一个单词。
```
示例:
```
输入:
words = ["This", "is", "an", "example", "of", "text", "justification."]
maxWidth = 16
输出:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
```
示例 2:
```
输入:
words = ["What","must","be","acknowledgment","shall","be"]
maxWidth = 16
输出:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]
解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
     因为最后一行应为左对齐，而不是左右两端对齐。       
     第二行同样为左对齐，这是因为这行只包含一个单词。
```
示例 3:
```
输入:
words = ["Science","is","what","we","understand","well","enough","to","explain",
         "to","a","computer.","Art","is","everything","else","we","do"]
maxWidth = 20
输出:
[
  "Science  is  what we",
  "understand      well",
  "enough to explain to",
  "a  computer.  Art is",
  "everything  else  we",
  "do                  "
]
```
## 解法
### 思路
硬做:
- 通过遍历单词长度，获得当前行能够放入的单词个数`wordsNum`和单词长度`wordsLen`
- 通过单词个数`wordNum`，获得单词间隙个数`gaps`，进而获得单词占用当前行的总长度`totalLen`
- 通过`maxWidth - totalLen`获得额外空格的长度`extraLen`
- 通过`gaps`能够获得两个值：
    - 每个单词后面的额外空格长度`avgExtraLen`
    - 不平衡时，左侧多少个单词会都出一个空格的单词个数`extraNum`
- 特殊处理如下两种情况：
    - 如果一行只有1个单词，单词左对齐，右侧填满
    - 如果时最后一行，单词左对齐，间隙只为1个空格
### 代码
```java
class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        int wordNum = words.length, maxRowLen = maxWidth + 1;
        List<String> ans = new ArrayList<>();

        int rowLen = maxRowLen;
        List<String> wordList = new ArrayList<>();
        for (int i = 0; i < wordNum; i++) {
            String word = words[i];
            if (rowLen - word.length() - 1 >= 0) {
                wordList.add(word);
                if (i == wordNum - 1) {
                    ans.add(fillSpecialRow(wordList, maxWidth));
                    break;
                }
                rowLen -= (word.length() + 1);
            } else {
                if (wordList.size() == 1) {
                    ans.add(fillSpecialRow(wordList, maxWidth));
                } else {
                    ans.add(fillRow(wordList, rowLen, maxWidth));
                }
                wordList.clear();
                rowLen = maxRowLen;
                i--;
            }
        }

        return ans;
    }

    private String fillRow(List<String> wordList, int leftLen, int maxWidth) {
        StringBuilder sb = new StringBuilder();
        
        int wordsNum = wordList.size();
        int gaps = wordsNum - 1;
        int avgExtraLen = leftLen / gaps;
        int extraNum = leftLen % gaps;
        
        for (int i = 0; i < wordList.size(); i++) {
            sb.append(wordList.get(i));
            if (i < wordsNum - 1) {
                sb.append(' ');
                for (int j = 0; j < avgExtraLen; j++) {
                    sb.append(' ');
                }
            }
            if (extraNum > 0) {
                sb.append(' ');
                extraNum--;
            }
        }
        
        return sb.toString();
    }

    private String fillSpecialRow(List<String> words, int maxWidth) {
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(word);
            maxWidth -= word.length();
            if (maxWidth == 0) {
                break;
            }
            
            maxWidth--;
            if (maxWidth >= 0) {
                sb.append(' ');
            }
        }

        while(maxWidth-- > 0) {
            sb.append(' ');
        }

        return sb.toString();
    }
}
```
# LeetCode_139_单词拆分
## 题目
给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。

说明：
```
拆分时可以重复使用字典中的单词。
你可以假设字典中没有重复的单词。
```
示例 1：
```
输入: s = "leetcode", wordDict = ["leet", "code"]
输出: true
解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
```
示例 2：
```
输入: s = "applepenapple", wordDict = ["apple", "pen"]
输出: true
解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
     注意你可以重复使用字典中的单词。
```
示例 3：
```
输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
输出: false
```
## 失败解法
### 原因
超时
### 思路
backtrack
### 代码
```java
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if ("".equals(s)) {
            return true;
        }

        boolean flag = false;
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                flag |= wordBreak(s.substring(word.length()), wordDict);
            }
            
            if (flag) {
                return true;
            }
        }

        return flag;
    }
}
```
## 解法
### 思路
动态规划：
- dp[i]：坐标是`[0, i - 1]`，长度是i的子字符串是否能被单词完整覆盖
- base case：`dp[0] = true`，代表字符串长度为0时，为true
- 状态转移方程：`dp[i] = dp[j] && wordList.contains(str[j, i - 1])`
- 返回结果：`dp[len]`
- 两层循环:
    - 外层确定dp代表的字符串的长度
    - 内层寻找能够从wordList找到的，结尾是s的i - 1坐标字符串的起始坐标
    - 如果内层找到的坐标dp值也是true，说明当前字符串是可以完全由wordList中的字符串覆盖的
### 代码
```java
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;

        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[len];
    }
}
```
## 优化代码
### 思路
内层循环从结尾开始倒着查
### 代码
```java
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;

        for (int i = 1; i <= len; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[len];
    }
}
```
# LeetCode_71_简化路径
## 题目
以 Unix 风格给出一个文件的绝对路径，你需要简化它。或者换句话说，将其转换为规范路径。

在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。更多信息请参阅：Linux / Unix中的绝对路径 vs 相对路径

请注意，返回的规范路径必须始终以斜杠 / 开头，并且两个目录名之间必须只有一个斜杠 /。最后一个目录名（如果存在）不能以 / 结尾。此外，规范路径必须是表示绝对路径的最短字符串。

示例 1：
```
输入："/home/"
输出："/home"
解释：注意，最后一个目录名后面没有斜杠。
```
示例 2：
```
输入："/../"
输出："/"
解释：从根目录向上一级是不可行的，因为根是你可以到达的最高级。
```
示例 3：
```
输入："/home//foo/"
输出："/home/foo"
解释：在规范路径中，多个连续斜杠需要用一个斜杠替换。
```
示例 4：
```
输入："/a/./b/../../c/"
输出："/c"
```
示例 5：
```
输入："/a/../../b/../c//.//"
输出："/c"
```
示例 6：
```
输入："/a//b////c/d//././/.."
输出："/a/b/c"
```
## 解法
### 思路
栈
- 使用`/`分隔字符串
- 初始化一个栈用来存目录路径
- 遍历字符串数组
    - 如果是`..`，且stack不是空的，就弹栈
    - 如果不是`.`或者空，就将当前字符串压入栈中
- 从stack的起始位置开始遍历，拼成结果字符串
### 代码
```java
class Solution {
    public String simplifyPath(String path) {
        String[] strs = path.split("/");
        Stack<String> stack = new Stack<>();
        
        for (String str : strs) {
            if (Objects.equals(str, "..")) {
                if (!stack.isEmpty()) {
                    stack.pop();   
                }
            } else if (!Objects.equals(str, ".") && !Objects.equals(str, "")) {
                stack.push(str);
            }
        }
        
        if (stack.isEmpty()) {
            return "/";
        }
        
        StringBuilder sb = new StringBuilder();
        for (String s : stack) {
            sb.append("/").append(s);
        }
        return sb.toString();
    }
}
```
# LeetCode_74_搜索二维矩阵
## 题目
编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
```
每行中的整数从左到右按升序排列。
每行的第一个整数大于前一行的最后一个整数。
```
示例 1:
```
输入:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 3
输出: true
```
示例 2:
```
输入:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 13
输出: false
```
## 解法
### 思路
- 从[0,0]开始嵌套遍历
- 每一层开始前，除最后一层外，先和下一层进行判断
    - 如果和target相等，返回true
    - 如果小于target，说明当前层没有要的元素，直接跳到下一层
    - 如果大于target，那么说明当前层有可能有target
- 如果开始遍历当前层的元素
    - 找到返回true
    - 遍历完没有找到，返回false
- 如果所有层都没有找到，返回false
### 代码
```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        
        int row = matrix.length, col = matrix[0].length;
        
        for (int i = 0; i < row; i++) {
            if (i != row - 1) {
                int nextRowStart = matrix[i + 1][0];
                
                if (nextRowStart == target) {
                    return true;
                }
                
                if (nextRowStart < target) {
                    continue;
                }
            }
            
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == target) {
                    return true;
                }
            }
            
            return false;
        }
        
        return false;
    }
}
```