# LeetCode_296_最佳的碰头地点
## 题目
有一队人（两人或以上）想要在一个地方碰面，他们希望能够最小化他们的总行走距离。

给你一个 2D 网格，其中各个格子内的值要么是 0，要么是 1。

1 表示某个人的家所处的位置。这里，我们将使用 曼哈顿距离 来计算，其中 distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|。

示例：
```
输入: 

1 - 0 - 0 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0

输出: 6 

解析: 给定的三个人分别住在(0,0)，(0,4) 和 (2,2):
     (0,2) 是一个最佳的碰面点，其总行走距离为 2 + 2 + 2 = 6，最小，因此返回 6。
```
## 解法
### 思路
- 遍历二维数组，找到为1的坐标
- 遍历二维数组，计算所有坐标与所有为1坐标的曼哈顿距离之和
- 求所有可能坐标中最小的总和距离
### 代码
```java
class Solution {
    public int minTotalDistance(int[][] grid) {
        int r = grid.length;
        if (r == 0) {
            return 0;
        }
        int c = grid[0].length;
        if (c == 0) {
            return 0;
        }

        List<int[]> points = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    points.add(new int[]{i, j});
                }
            }
        }

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int sum = 0;
                for (int[] point : points) {
                    sum += distance(point[0], point[1], i, j);
                }

                ans = Math.min(ans, sum);
            }
        }

        return ans;
    }

    private int distance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
```
# LeetCode_298_二叉树最长连续序列
## 题目
给你一棵指定的二叉树，请你计算它最长连续序列路径的长度。

该路径，可以是从某个初始结点到树中任意结点，通过「父 - 子」关系连接而产生的任意路径。

这个最长连续的路径，必须从父结点到子结点，反过来是不可以的。

示例 1：
```
输入:

   1
    \
     3
    / \
   2   4
        \
         5

输出: 3

解析: 当中，最长连续序列是 3-4-5，所以返回结果为 3
```
示例 2：
```
输入:

   2
    \
     3
    / 
   2    
  / 
 1

输出: 2 

解析: 当中，最长连续序列是 2-3。注意，不是 3-2-1，所以返回 2。
```
## 解法
### 思路
dfs：
- 自顶向下
- 使用3个变量进行递归：
    - `len`记录当前的长度
    - `pre`记录父节点引用
    - `node`记录当前节点引用
- 退出条件：当前节点为null，返回len值
- 过程：判断pre是否为null且pre的值是否与当前节点形成升序，如果是就在len基础上+1，否则就初始化为1
- 返回：当前len与左右子树递归后获得的结果的最大值，再作比较，取最大值返回
### 代码
```java
class Solution {
    public int longestConsecutive(TreeNode root) {
        return dfs(null, root, 0);
    }
    
    private int dfs(TreeNode pre, TreeNode node, int len) {
        if (node == null) {
            return len;
        }
        
        len = (pre != null && pre.val == node.val - 1) ? len + 1 : 1;
        return Math.max(len, Math.max(dfs(node, node.left, len), dfs(node, node.right, len)));
    }
}
```
## 解法二
### 思路
dfs：
- 自底向上
- 使用1个变量：`node`代表当前层节点的指针
- 退出条件：`node`为空时，返回0，说明当前层没有节点，距离就是0
- 过程：
    - 递归左右子树
    - 返回左右子树的距离值，返回的值并不考虑当前层是否与上一层的节点形成升序
    - 在当前层处理时，就需要考虑是否与下一层的节点是否能形成升序，如果能，就累加在这个距离中
    - 所以返回的左右子树的距离，需要分别判断是否与下一层能够相连，如果可以，就在其距离上+1，否则说明当前层和之前层断开，只记录当前层这1个节点的距离值1
    - 之后，将当前层计算完成后，与全局变量ans做比较，更新最大距离
- 返回：当前层，基于左右子树返回的距离，重新计算与当前层节点关系后，生成的新值，并取两者之间的最大值返回
### 代码
```java
class Solution {
    private int ans;
    public int longestConsecutive(TreeNode root) {
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        int left = dfs(node.left),
            right = dfs(node.right);
        
        left = node.left != null && node.left.val == node.val + 1 ? left + 1 : 1;
        right = node.right != null && node.right.val == node.val + 1 ? right + 1 : 1;
        
        ans = Math.max(ans, Math.max(left, right));
        
        return Math.max(left, right);
    }
}
```
# LeetCode_299_猜数字游戏
## 题目
你在和朋友一起玩 猜数字（Bulls and Cows）游戏，该游戏规则如下：
```
你写出一个秘密数字，并请朋友猜这个数字是多少。
朋友每猜测一次，你就会给他一个提示，告诉他的猜测数字中有多少位属于数字和确切位置都猜对了（称为“Bulls”, 公牛），有多少位属于数字猜对了但是位置不对（称为“Cows”, 奶牛）。
朋友根据提示继续猜，直到猜出秘密数字。
请写出一个根据秘密数字和朋友的猜测数返回提示的函数，返回字符串的格式为 xAyB ，x 和 y 都是数字，A 表示公牛，用 B 表示奶牛。

xA 表示有 x 位数字出现在秘密数字中，且位置都与秘密数字一致。
yB 表示有 y 位数字出现在秘密数字中，但位置与秘密数字不一致。
请注意秘密数字和朋友的猜测数都可能含有重复数字，每位数字只能统计一次。
```
示例 1:
```
输入: secret = "1807", guess = "7810"
输出: "1A3B"
解释: 1 公牛和 3 奶牛。公牛是 8，奶牛是 0, 1 和 7。
```
示例 2:
```
输入: secret = "1123", guess = "0111"
输出: "1A1B"
解释: 朋友猜测数中的第一个 1 是公牛，第二个或第三个 1 可被视为奶牛。
```
```
说明: 你可以假设秘密数字和朋友的猜测数都只包含数字，并且它们的长度永远相等。
```
## 解法
### 思路
哈希表
### 代码
```java
class Solution {
    public String getHint(String secret, String guess) {
        char[] scs = secret.toCharArray(),
               gcs = guess.toCharArray();

        Map<Character, List<Integer>> sMap = new HashMap<>();
        for (int i = 0; i < scs.length; i++) {
            List<Integer> list = sMap.getOrDefault(scs[i], new ArrayList<>());
            list.add(i);
            sMap.put(scs[i], list);
        }

        int bulls = 0, cows = 0;
        for (int i = 0; i < gcs.length; i++) {
            char c = gcs[i];
            if (sMap.containsKey(c) && sMap.get(c).contains(i)) {
                bulls++;
                sMap.get(c).remove(new Integer(i));

                if (sMap.get(c).size() == 0) {
                    sMap.remove(c);
                }
                gcs[i] = '-';
            }
        }

        for (int i = gcs.length - 1; i >= 0; i--) {
            char c = gcs[i];
            if (c == '-') {
                continue;
            }

            if (sMap.containsKey(c)) {
                cows++;
                sMap.get(c).remove(0);

                if (sMap.get(c).size() == 0) {
                    sMap.remove(c);
                }
            }
        }

        return bulls + "A" + cows + "B";
    }
}
```
## 解法二
### 思路
- 题目限制了2个字符串的长度永远相等
- bull的个数就是同位且同值的个数
- cow的个数：
    - 两个字符串在某一个数字上出现次数的最小值，也就是这个数字在两个字符串中同时出现的次数，将这些数字的次数累加
    - 将累加值减去求出bull的个数，也就是总的同时出现的个数 - 同位同值的个数，就得到了同值不同位的个数
### 代码
```java
class Solution {
    public String getHint(String secret, String guess) {
        int[] s = new int[10],
              g = new int[10];
        
        int bull = 0, cow = 0;
        for (int i = 0; i < secret.length(); i++) {
            s[secret.charAt(i) - '0']++;
            g[guess.charAt(i) - '0']++;
            
            bull += secret.charAt(i) == guess.charAt(i) ? 1 : 0;
        }
        
        for (int i = 0; i < s.length; i++) {
            cow += Math.min(s[i], g[i]);
        }
        
        cow -= bull;
        
        return bull + "A" + cow + "B";
    }
}
```
# LeetCode_301_删除无效的括号
## 题目
删除最小数量的无效括号，使得输入的字符串有效，返回所有可能的结果。

说明: 输入可能包含了除 ( 和 ) 以外的字符。

示例 1:
```
输入: "()())()"
输出: ["()()()", "(())()"]
```
示例 2:
```
输入: "(a)())()"
输出: ["(a)()()", "(a())()"]
```
示例 3:
```
输入: ")("
输出: [""]
```
## 解法
### 思路
- 判断：
    - 字符串中左右括号数相等是有效括号的必要非充分条件
    - 如果从左往右遍历字符串，右括号的个数比左括号大，则必不是有效括号
- 算法：回溯，将所有可能的字符串枚举出来，并记录改动量最小的一组
    - 递归的变量：
        - `s`：初始字符串
        - `i`：字符坐标，记录递归当前层处理到的字符
        - `l`：左括号个数
        - `r`：右括号个数
        - `expression`：暂存的可能字符串
        - `removeCount`：暂存的移除的次数
    - 过程：
        - 如果`i == s.length`，则代表递归退出，判断当前字符串是否是有效括号：
            - 如果是，用`removeCount`和全局变量`minRemoveCOunt`比较
                - 如果大于`minRemoveCount`就不使用当前字符串
                - 如果小于则要重置之前的字符串集合
                - 如果小于等于就将`expression`加到结果集合中
        - 判断当前字符是否是括号，如果不是，直接将字符加到`expression`中，并继续递归
        - 如果是括号：
            - 假设当前字符被删除，直接递归，removeCount+1
            - 假设保留当前字符：
                - 则需先判断，是否是右括号，如果是，则当前右括号数是否大于左括号数，如果是的话，就直接剪枝，不递归
                - 如果可以保留，则将字符累加到`expression`中
        - 所有的递归动作都在expression上做回溯的操作，也就是append和delete当前递归层字符
### 代码
```java
class Solution {
    private Set<String> set = new HashSet<>();
    private int minRemoveCount = Integer.MAX_VALUE;
    public List<String> removeInvalidParentheses(String s) {
        backTrack(s, 0, 0, 0, 0, new StringBuilder());
        return new ArrayList<>(set);
    }
    
    private void backTrack(String s, int index, int l, int r, int removeCount, StringBuilder expression) {
        if (index == s.length()) {
            if (l == r) {
                if (removeCount <= minRemoveCount) {
                    String str = expression.toString();
                    
                    if (removeCount < minRemoveCount) {
                        set.clear();
                        minRemoveCount = removeCount;
                    }
                    
                    set.add(str);
                }
            }
        } else {
            char c = s.charAt(index);
            int len = expression.length();
            if (c != '(' && c != ')') {
                expression.append(c);
                backTrack(s, index + 1, l, r, removeCount, expression);
                expression.deleteCharAt(len);
            } else {
                backTrack(s, index + 1, l, r, removeCount + 1, expression);
                expression.append(c);
                
                if (c == '(') {
                    backTrack(s, index + 1, l + 1, r, removeCount, expression);
                } else if (r < l) {
                    backTrack(s, index + 1, l, r + 1, removeCount, expression);
                }
                
                expression.deleteCharAt(len);
            }
        }
    }
}
```
# LeetCode_302_包含全部黑色像素的最小矩形
## 题目
图片在计算机处理中往往是使用二维矩阵来表示的。

假设，这里我们用的是一张黑白的图片，那么 0 代表白色像素，1 代表黑色像素。

其中黑色的像素他们相互连接，也就是说，图片中只会有一片连在一块儿的黑色像素（像素点是水平或竖直方向连接的）。

那么，给出某一个黑色像素点 (x, y) 的位置，你是否可以找出包含全部黑色像素的最小矩形（与坐标轴对齐）的面积呢？

示例:
```
输入:
[
  "0010",
  "0110",
  "0100"
]
和 x = 0, y = 2

输出: 6
```
## 解法
### 思路
- 初始化矩形的上下左右4个变量，用来计算最后的面积
- 遍历二维数组，找到所有黑色像素
- 使用找到的黑色像素坐标与初始化的上下左右做比较和更新：
    - `up = min(up, y)`
    - `bottom = max(bottom, y + 1)`
    - `left = min(left, x)`
    - `right = max(right, x + 1)`
- 最后根据上下左右返回面积`(right - left) * (bottom - up)`
### 代码
```java
class Solution {
    public int minArea(char[][] image, int x, int y) {
        int left = x, right = x,
            top = y, bottom = y;

        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                if (image[i][j] == '1') {
                    left = Math.min(left, i);
                    right = Math.max(right, i + 1);
                    top = Math.min(top, j);
                    bottom = Math.max(bottom, j + 1);
                }
            }
        }
        
        return (right - left) * (bottom - top);
    }
}
```
## 解法二
### 思路
题目前提条件是黑色相连，所以可以使用dfs
### 代码
```java
class Solution {
    private int left, right, top, bottom;
    public int minArea(char[][] image, int x, int y) {
        if (image.length == 0 || image[0].length == 0) {
            return 0;
        }
        
        left = x;
        right = x;
        top = y;
        bottom = y;
        
        dfs(image, image.length, image[0].length, x, y);
        
        return (right - left) * (bottom - top);
    }
    
    private void dfs(char[][] image, int r, int c, int x, int y) {
        if (x < 0 || x >= r || y < 0 || y >= c || image[x][y] == '0') {
            return;
        }
        
        left = Math.min(left, x);
        right = Math.max(right, x + 1);
        top = Math.min(top, y);
        bottom = Math.max(bottom, y + 1);
        
        image[x][y] = '0';
        
        dfs(image, r, c, x + 1, y);
        dfs(image, r, c, x - 1, y);
        dfs(image, r, c, x, y + 1);
        dfs(image, r, c, x, y - 1);
    }
}
```
## 解法三
### 思路
二分查找：
- 通过题目可知，二位数组可以被压缩成一维的看待：
    - 如果在确定黑色像素的行时，只要任意行上的任意列有黑色像素，这一行就可以看作是黑色的
    - 同理，确定列的时候，任意列上的任意行像素是黑色，这一列也可以看作是黑色的
    - 就好像倒映在墙上的银子，三位立体的人被压缩成了一个二位的平面影子
- 通过如上的解释，在题目初始给定的坐标基础上，可以将二维数组分成4个部分，且这4部分是有重叠的
    - 确定行的范围的时候：
        - 行上[0,x]这个范围的的部分，找到最靠上的黑色像素坐标
        - 行上[x + 1, r - 1]这个范围的部分，找到最靠下的黑色像素坐标、
    - 确定列的范围的时候：
        - 列上[0, y]这个范围的部分，找到最靠左的黑色像素坐标
        - 列上[y + 1, c - 1]这个范围的部分，找到最靠右的黑色像素坐标
    - 在找这4个部分的时候，确定每一个坐标是否是黑色，就可以用压缩的方法，查看当前行或列上是否有其他的黑色像素坐标
### 代码
```java
class Solution {
    public int minArea(char[][] image, int x, int y) {
        if (image.length == 0 || image[0].length == 0) {
            return 0;
        }

        int l = findLeft(image, y);
        int r = findRight(image, y);
        int t = findTop(image, x);
        int b = findBottom(image, x);

        return (r - l) * (b - t);
    }

    private int findLeft(char[][] image, int y) {
        int head = 0, tail = y;
        while (head < tail) {
            int mid = head + (tail - head) / 2;
            int index = 0;
            while (index < image.length && image[index][mid] == '0') {
                index++;
            }

            if (index == image.length) {
                head = mid + 1;
            } else {
                tail = mid;
            }
        }

        return head;
    }

    private int findRight(char[][] image, int y) {
        int head = y + 1, tail = image[0].length;
        while (head < tail) {
            int mid = head + (tail - head) / 2;
            int index = 0;
            while (index < image.length && image[index][mid] == '0') {
                index++;
            }

            if (index == image.length) {
                tail = mid;
            } else {
                head = mid + 1;
            }
        }

        return head;
    }

    private int findTop(char[][] image, int x) {
        int head = 0, tail = x;
        while (head < tail) {
            int mid = head + (tail - head) / 2;
            int index = 0;
            while (index < image[0].length && image[mid][index] == '0') {
                index++;
            }

            if (index == image[0].length) {
                head = mid + 1;
            } else {
                tail = mid;
            }
        }

        return head;
    }

    private int findBottom(char[][] image, int x) {
        int head = x + 1, tail = image.length;
        while (head < tail) {
            int mid = head + (tail - head) / 2;
            int index = 0;
            while (index < image[0].length && image[mid][index] == '0') {
                index++;
            }

            if (index == image[0].length) {
                tail = mid;
            } else {
                head = mid + 1;
            }
        }

        return head;
    }
}
```
# LeetCode_304_二维区域和检索-矩阵不可变
## 题目
给定一个二维矩阵，计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2)。

上图子矩阵左上角 (row1, col1) = (2, 1) ，右下角(row2, col2) = (4, 3)，该子矩形内元素的总和为 8。

示例:
```
给定 matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
sumRegion(1, 1, 2, 2) -> 11
sumRegion(1, 2, 2, 4) -> 12
```
说明:
```
你可以假设矩阵不可变。
会多次调用 sumRegion 方法。
你可以假设 row1 ≤ row2 且 col1 ≤ col2。
```
## 解法
### 思路
前缀和
- 初始化时将二维数组维护成每一层都是一个前缀和数组
- 求区域和时：
    - 如果col1是0，就直接累加每一行的matrix[i][col2]
    - 如果col1不是0，就累加每一行的matrix[i][col2] - matrix[i][col1 - 1]
### 代码
```java
class NumMatrix {
    private int[][] matrix;

    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        
        this.matrix = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            int sum = 0;
            for (int j = 0; j < matrix[0].length; j++) {
                this.matrix[i][j] = (sum += matrix[i][j]);
            }
        }
        
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int i = row1; i <= row2; i++) {
            if (col1 == 0) {
                sum += matrix[i][col2];
            } else {
                sum += matrix[i][col2] - matrix[i][col1 - 1];
            }
        }
        return sum;
    }
}
```
# LeetCode_305_岛屿数量II
## 题目
假设你设计一个游戏，用一个 m 行 n 列的 2D 网格来存储你的游戏地图。

起始的时候，每个格子的地形都被默认标记为「水」。我们可以通过使用 addLand 进行操作，将位置 (row, col) 的「水」变成「陆地」。

你将会被给定一个列表，来记录所有需要被操作的位置，然后你需要返回计算出来 每次 addLand 操作后岛屿的数量。

注意：一个岛的定义是被「水」包围的「陆地」，通过水平方向或者垂直方向上相邻的陆地连接而成。你可以假设地图网格的四边均被无边无际的「水」所包围。

请仔细阅读下方示例与解析，更加深入了解岛屿的判定。

示例:
```
输入: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
输出: [1,1,2,3]
```
解析:
```
起初，二维网格 grid 被全部注入「水」。（0 代表「水」，1 代表「陆地」）

0 0 0
0 0 0
0 0 0
操作 #1：addLand(0, 0) 将 grid[0][0] 的水变为陆地。

1 0 0
0 0 0   Number of islands = 1
0 0 0
操作 #2：addLand(0, 1) 将 grid[0][1] 的水变为陆地。

1 1 0
0 0 0   岛屿的数量为 1
0 0 0
操作 #3：addLand(1, 2) 将 grid[1][2] 的水变为陆地。

1 1 0
0 0 1   岛屿的数量为 2
0 0 0
操作 #4：addLand(2, 1) 将 grid[2][1] 的水变为陆地。

1 1 0
0 0 1   岛屿的数量为 3
0 1 0
```
拓展：
```
你是否能在 O(k log mn) 的时间复杂度程度内完成每次的计算？（k 表示 positions 的长度）
```
## 失败解法
### 失败原因
超时
### 思路
每次更新一次地图就做一次dfs
### 代码
```java
class Solution {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> ans = new ArrayList<>();
        if (m == 0 || n == 0 || positions == null || positions.length == 0) {
            return ans;
        }
        
        int[][] matrix = new int[m][n];
        for (int i = 0; i < positions.length; i++) {
            matrix[positions[i][0]][positions[i][1]] = 1;
            
            int[][] tmpMatrix = new int[m][n];
            for (int j = 0; j < m; j++) {
                tmpMatrix[j] = Arrays.copyOfRange(matrix[j], 0, n);
            }
            ans.add(count(tmpMatrix));
        }
        
        return ans;
    }
    
    private int count(int[][] matrix) {
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    count++;
                    dfs(matrix, i, j);
                }
            }
        }
        
        return count;
    }
    
    private void dfs(int[][] matrix, int r, int c) {
        if (r < 0 || r >= matrix.length || c < 0 || c >= matrix[0].length || matrix[r][c] != 1) {
            return;
        }
        
        matrix[r][c] = 0;
        
        dfs(matrix, r + 1, c);
        dfs(matrix, r - 1, c);
        dfs(matrix, r, c + 1);
        dfs(matrix, r, c - 1);
    }
}
```
## 失败解法二
### 失败原因
超时
### 思路
在失败解法一基础上增加记忆化搜索
### 代码
```java
class Solution {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> ans = new ArrayList<>();
        if (m == 0 || n == 0 || positions == null || positions.length == 0) {
            return ans;
        }
        
        int[][] matrix = new int[m][n];
        for (int i = 0; i < positions.length; i++) {
            matrix[positions[i][0]][positions[i][1]] = 1;
            
            int[][] tmpMatrix = new int[m][n];
            for (int j = 0; j < m; j++) {
                tmpMatrix[j] = Arrays.copyOfRange(matrix[j], 0, n);
            }
            ans.add(count(tmpMatrix));
        }
        
        return ans;
    }
    
    private int count(int[][] matrix) {
        int count = 0;
        boolean[][] memo = new boolean[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    count++;
                    dfs(matrix, i, j, memo);
                }
            }
        }

        return count;
    }

    private void dfs(int[][] matrix, int r, int c, boolean[][] memo) {
        if (r < 0 || r >= matrix.length || c < 0 || c >= matrix[0].length || matrix[r][c] != 1 || memo[r][c]) {
            return;
        }

        matrix[r][c] = 0;
        memo[r][c] = true;
        
        dfs(matrix, r + 1, c, memo);
        dfs(matrix, r - 1, c, memo);
        dfs(matrix, r, c + 1, memo);
        dfs(matrix, r, c - 1, memo);
    }
}
```
## 解法
### 思路
并查集
### 代码
```java
class Solution {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> ans = new ArrayList<>();
        if (m == 0 || n == 0 || positions == null || positions.length == 0) {
            return ans;
        }

        UnionFind uf = new UnionFind(m * n);
        for (int[] position : positions) {
            int r = position[0], c = position[1];
            List<Integer> overlap = new ArrayList<>();

            if (r >= 1 && uf.isValid((r - 1) * n + c)) {
                overlap.add((r - 1) * n + c);
            }

            if (r < (m - 1) && uf.isValid((r + 1) * n + c)) {
                overlap.add((r + 1) * n + c);
            }

            if (c >= 1 && uf.isValid(r * n + c - 1)) {
                overlap.add(r * n + c - 1);
            }

            if (c < (n - 1) && uf.isValid(r * n + c + 1)) {
                overlap.add(r * n + c + 1);
            }
            
            int index = r * n + c;
            if (uf.parent[index] == -1) {
                uf.setParent(index);
                
                for (int i : overlap) {
                    uf.union(i, index);
                }
            }
            
            ans.add(uf.count);
        }
        
        return ans;
    }

    class UnionFind {
        private int count;
        private int[] parent;
        private int[] rank;

        public UnionFind(int n) {
            parent = new int[n];
            Arrays.fill(parent, -1);
            
            rank = new int[n];
            count = 0;
        }

        public int count() {
            return count;
        }

        public boolean isValid(int n) {
            return parent[n] >= 0;
        }

        public void setParent(int n) {
            parent[n] = n;
            count++;
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }

            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x),
                rootY = find(y);

            if (rootX != rootY) {
                if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else if(rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else {
                    rank[rootX]++;
                    parent[rootY] = rootX;
                }
                
                count--;
            }
        }
    }
}
```