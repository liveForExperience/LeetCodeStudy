# LeetCode_312_戳气球
## 题目
有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组 nums 中。

现在要求你戳破所有的气球。如果你戳破气球 i ，就可以获得 nums[left] * nums[i] * nums[right] 个硬币。 这里的 left 和 right 代表和 i 相邻的两个气球的序号。注意当你戳破了气球 i 后，气球 left 和气球 right 就变成了相邻的气球。

求所能获得硬币的最大数量。

说明:
```
你可以假设 nums[-1] = nums[n] = 1，但注意它们不是真实存在的所以并不能被戳破。
0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
```
示例:
```
输入: [3,1,5,8]
输出: 167 
解释: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
     coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
```
## 解法
### 思路
分治+记忆化
- 定义变量：
    - `val[]`：构建一个开区间(i,j)，i最小为`0`，j最大为`n+1`，而`[1,n]`的值就是`nums`，且根据题目提示，头尾坐标可以表示为0
    - `memo[i][j]`：记忆化缓存，初始化为-1，作为没有计算过的标记。i和j代表value开区间的头尾坐标
- 过程：
    - 退出条件：
        - 如果头尾坐标相等，代表开区间中已经没有元素需要计算
        - 如果memo中的值不为-1，直接返回当前值
    - 遍历当前头尾坐标中所有可能的坐标`mid`，计算这个`mid`构成的乘积
    - 利用`mid`将当前层的区间切分成2个，继续递归
    - 左右区间递归返回的结果,和当前层的值相加，这个值再与memo的结果进行比较，取最大值
    - 最后返回当前层最大值
### 代码
```java
class Solution {
    public int maxCoins(int[] nums) {
        int len = nums.length;
        int[] val = new int[len + 2];
        for (int i = 1; i <= len; i++) {
            val[i] = nums[i -  1];
        }
        val[0] = val[len + 1] = 1;

        int[][] memo = new int[len + 2][len + 2];
        for (int[] arr : memo) {
            Arrays.fill(arr, -1);
        }

        return recurse(val, 0, len + 1, memo);
    }

    private int recurse(int[] val, int head, int tail, int[][] memo) {
        if (head + 1 >= tail) {
            return 0;
        }

        if (memo[head][tail] != -1) {
            return memo[head][tail];
        }
        
        for (int mid = head + 1; mid < tail; mid++) {
            int sum = val[mid] * val[head] * val[tail];
            sum += recurse(val, head, mid, memo) + recurse(val, mid, tail, memo);
            memo[head][tail] = Math.max(sum, memo[head][tail]);
        }
        
        return memo[head][tail];
    }
}
```
## 解法二
### 思路
动态规划：
- `dp[i][j]`：开区间(i,j)范围内能够获得的最大值
- 初始化：0
- 状态转移方程：`dp[i][j] = val[i] * val[k] * val[j] + dp[i,k] + dp[k][j]`
- 返回结果：`dp[0][n + 1]`
- 三层循环：
    - 外层确定起始i
    - 中间层确定结尾j
    - 内层确定mid值
### 代码
```java
class Solution {
    public int maxCoins(int[] nums) {
        int len = nums.length;
        int[] val = new int[len + 2];
        val[0] = val[len + 1] = 1;
        for (int i = 1; i<= len; i++) {
            val[i] = nums[i - 1];
        }
            
        int[][] dp = new int[len + 2][len + 2];
        
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i + 2; j <= len + 1; j++) {
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], val[i] * val[k] * val[j] + dp[i][k] + dp[k][j]);
                }
            }
        }
        
        return dp[0][len + 1];
    }
}
```
# LeetCode_145_二叉树的后序遍历
## 题目
给定一个二叉树，返回它的 后序 遍历。

示例:
```
输入: [1,null,2,3]  
   1
    \
     2
    /
   3 

输出: [3,2,1]
```
```
进阶: 递归算法很简单，你可以通过迭代算法完成吗？
```
## 解法
### 思路
dfs后序遍历
### 代码
```java
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        return list;
    }
    
    private void dfs(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        
        dfs(node.left, list);
        dfs(node.right, list);
        list.add(node.val);
    }
}
```
## 解法二
### 思路
使用栈进行迭代：
- 使用2个栈
- 一个栈`stack`用来驱动搜索
- 一个栈`temp`用来记录搜索过的节点
- 每一次循环从`stack`取出的节点就压入`temp`，因为栈是先进后出，所以正好匹配了后序的顺序
- `stack`迭代结束后，循环弹出`temp`就能获得后序打印的结果
### 代码
```java
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        
        Stack<TreeNode> stack = new Stack<>(), temp = new Stack<>();
        List<Integer> ans = new ArrayList<>();
        
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node == null) {
                continue;
            }
            
            if (node.left != null) {
                stack.push(node.left);    
            }
            
            if (node.right != null) {
                stack.push(node.right);
            }
            
            temp.push(node);
        }
        
        while (!temp.isEmpty()) {
            ans.add(temp.pop().val);
        }
        
        return ans;
    }
}
```
# LeetCode_149_直线上最多的点数
## 题目
给定一个二维平面，平面上有 n 个点，求最多有多少个点在同一条直线上。

示例 1:
```
输入: [[1,1],[2,2],[3,3]]
输出: 3
解释:
^
|
|        o
|     o
|  o  
+------------->
0  1  2  3  4
```
示例 2:
```
输入: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
输出: 4
解释:
^
|
|  o
|     o        o
|        o
|  o        o
+------------------->
0  1  2  3  4  5  6
```
## 解法
### 思路
暴力解法：
- 特殊情况：如果点的个数n小于等于2，那么在同一直线上的点的个数就是n
- 判断条件：判断是否在同一直线上，就需要有3个点，2个点`(x1, y1)`，`(x2, y2)`确定直线，再枚举点`(x, y)`，判断是否在那条直线上。故循环需要有3层
- 3层循环：
    - 外面2层确定组成直线的2个点
    - 最里面一层遍历所有其他的点，判断是否与外面两个点在同一条直线上
    - 判断公式：`(y2 - y1) / (x2 - x1) = (y - y2) / (x - x2)`
    - 公式是除法，使用double可能导致精度丢失，所以转换为：`(y2 - y1) * (x - x2) = (y - y2) * (x2 - x1)`
    - 但相乘有可能导致溢出，所以使用gcd求最大公约数，对乘数做一下约分
- 注意：
    - 确定直线的两个点如果完全相等，需要跳过
    - 最内层的点在遍历时如果与最外层的2个点重复，需要跳过
### 代码
```java
class Solution {
    public int maxPoints(int[][] points) {
        int len = points.length;
        if (len < 3) {
            return len;
        }

        int index = 0;
        for (; index < len - 1; index++) {
            if (points[index][0] != points[index + 1][0] || points[index][1] != points[index + 1][1]) {
                break;
            }
        }

        if (index == len - 1) {
            return len;
        }

        int max = 0;

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int x1 = points[i][0], y1 = points[i][1],
                    x2 = points[j][0], y2 = points[j][1];

                if (x1 == x2 && y1 == y2) {
                    continue;
                }

                int count = 2;

                for (int k = 0; k < len; k++) {
                    if (k != i && k != j && isLine(points[k][0], points[k][1], x1, y1, x2, y2)) {
                        count++;
                    }
                }

                max = Math.max(max, count);
            }
        }

        return max;
    }

    private boolean isLine(int x, int y, int x1, int y1, int x2, int y2) {
        int gcd1 = gcd(y2 - y1,  x2 - x1);
        if (x == x2 && y == y2) {
            return true;
        }
        int gcd2 = gcd(y - y2, x - x2);

        return (y2 - y1) / gcd1 == (y - y2) / gcd2 && (x2 - x1) / gcd1 == (x - x2) / gcd2;
    }

    private int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }
}
```
## 解法二
### 思路
map存储斜率和常量并计数
- 与同一个点的斜率相同的点，它们在同一条直线上
- 双层循环：
    - 外层确定计算斜率的点，并初始化一个map用来计数
    - 内层计算除该点外所有点的斜率，并计数
    - 计算斜率时为了避免浮点数精度丢失，使用约分并记录分数形式，用字符串`x:y`表示
    - 内层枚举点时直接从外层确定的点之后开始枚举，因为之前的点已经在前面的循环中被计算和枚举过了，如果有和之前的点形成一条直线的，都已经在之前计算过了
### 代码
```java
class Solution {
    public int maxPoints(int[][] points) {
        int len = points.length;

        if (len < 3) {
            return len;
        }

        int index = 0;
        for (; index < len - 1; index++) {
            if (points[index][0] != points[index + 1][0] || points[index][1] != points[index + 1][1]) {
                break;
            }
        }

        if (index == len - 1) {
            return len;
        }

        int ans = 0;

        for (int i = 0; i < len; i++) {
            int dup = 0, max = 0;
            Map<String, Integer> map = new HashMap<>();
            for (int j = i + 1; j < len; j++) {
                int x = points[i][0] - points[j][0],
                    y = points[i][1] - points[j][1];

                if (x == 0 && y == 0) {
                    dup++;
                    continue;
                }

                int gcd = gcd(x, y);
                x = x / gcd;
                y = y / gcd;

                String key = x + ":" + y;
                map.put(key, map.getOrDefault(key, 0) + 1);

                max = Math.max(max, map.get(key));
            }

             ans = Math.max(ans, max + dup + 1);
        }

        return ans;
    }

    private int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }
}
```
# LeetCode_156_上下翻转二叉树
## 题目
给定一个二叉树，其中所有的右节点要么是具有兄弟节点（拥有相同父节点的左节点）的叶节点，要么为空，将此二叉树上下翻转并将它变成一棵树， 原来的右节点将转换成左叶节点。返回新的根。

例子:
```
输入: [1,2,3,4,5]
    1
   / \
  2   3
 / \
4   5

输出: 返回二叉树的根 [4,5,2,#,#,3,1]

   4
  / \
 5   2
    / \
   3   1  
```
说明:
```
对 [4,5,2,#,#,3,1] 感到困惑? 下面详细介绍请查看 二叉树是如何被序列化的。

二叉树的序列化遵循层次遍历规则，当没有节点存在时，'#' 表示路径终止符。
```
这里有一个例子:
```
   1
  / \
 2   3
    /
   4
    \
     5
上面的二叉树则被序列化为 [1,2,3,#,#,4,#,#,5].
```
## 解法
### 思路
dfs：
- 转换规则：
    - 左子树为根
    - 根节点为右
    - 右子树为左
- 递归：
    - 递归参数：
        - 根节点
        - 当前层节点
    - 退出条件：左子树为空
    - 过程：
        - 根节点为当前节点的右节点
        - 根节点的右节点为当前节点的左节点
        - 当前节点返回作为根节点
### 代码
```java
class Solution {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || root.left == null) {
            return root;
        }

        TreeNode ans = dfs(root.left, root);

        root.left = null;
        root.right = null;

        return ans;
    }

    private TreeNode dfs(TreeNode node, TreeNode root) {
        TreeNode cur;
        if (node.left == null) {
            cur = node;
        } else {
            cur = dfs(node.left, node);
        }

        node.left = root.right;
        node.right = root;

        return cur;
    }
}
```
# LeetCode_157_用Read4读取N个字符
## 题目
给你一个文件，并且该文件只能通过给定的 read4 方法来读取，请实现一个方法使其能够读取 n 个字符。

read4 方法：

API read4 可以从文件中读取 4 个连续的字符，并且将它们写入缓存数组 buf 中。

返回值为实际读取的字符个数。

注意 read4() 自身拥有文件指针，很类似于 C 语言中的 FILE *fp 。

read4 的定义：
```
参数类型: char[] buf
返回类型: int
```
```
注意: buf[] 是目标缓存区不是源缓存区，read4 的返回结果将会复制到 buf[] 当中。
```
下列是一些使用 read4 的例子：
```
File file("abcdefghijk"); // 文件名为 "abcdefghijk"， 初始文件指针 (fp) 指向 'a' 
char[] buf = new char[4]; // 创建一个缓存区使其能容纳足够的字符
read4(buf); // read4 返回 4。现在 buf = "abcd"，fp 指向 'e'
read4(buf); // read4 返回 4。现在 buf = "efgh"，fp 指向 'i'
read4(buf); // read4 返回 3。现在 buf = "ijk"，fp 指向文件末尾
```
read 方法：

通过使用 read4 方法，实现 read 方法。该方法可以从文件中读取 n 个字符并将其存储到缓存数组 buf 中。您 不能 直接操作文件。

返回值为实际读取的字符。

read 的定义：
```
参数类型:   char[] buf, int n
返回类型:   int
```
注意: buf[] 是目标缓存区不是源缓存区，你需要将结果写入 buf[] 中。

示例 1：
```
输入： file = "abc", n = 4
输出： 3
解释： 当执行你的 rand 方法后，buf 需要包含 "abc"。 文件一共 3 个字符，因此返回 3。 注意 "abc" 是文件的内容，不是 buf 的内容，buf 是你需要写入结果的目标缓存区。 
```
示例 2：
```
输入： file = "abcde", n = 5
输出： 5
解释： 当执行你的 rand 方法后，buf 需要包含 "abcde"。文件共 5 个字符，因此返回 5。
```
示例 3:
```
输入： file = "abcdABCD1234", n = 12
输出： 12
解释： 当执行你的 rand 方法后，buf 需要包含 "abcdABCD1234"。文件一共 12 个字符，因此返回 12。
```
示例 4:
```
输入： file = "leetcode", n = 5
输出： 5
解释： 当执行你的 rand 方法后，buf 需要包含 "leetc"。文件中一共 5 个字符，因此返回 5。
```
注意：
```
你 不能 直接操作该文件，文件只能通过 read4 获取而 不能 通过 read。
read  函数只在每个测试用例调用一次。
你可以假定目标缓存数组 buf 保证有足够的空间存下 n 个字符。
```
## 解法
### 思路
- Reader4中有一个`file`，`read4`的时候每次都会从`file`中读取至多4个字符的数据
- 题目就是要求将读出来的文件写到`buf`中，同时返回读取了多少个
- 定义一个指针，读写时移动指针，避免eof，最终返回指针
### 代码
```java
public class Solution extends Reader4 {
    public int read(char[] buf, int n) {
        int i = 0;
        while (n > 0) {
            char[] arr = new char[4];
            int len = read4(arr);
            for (int j = 0; j < Math.min(len, n); j++) {
                buf[i++] = arr[j];
            }
            n -= 4;
        }

        return i;
    }
}
```
# LeetCode_158_用Read4读取N个字符II
## 题目
给你一个文件，并且该文件只能通过给定的 read4 方法来读取，请实现一个方法使其能够读取 n 个字符。注意：你的 read 方法可能会被调用多次。

read4 的定义：
```
参数类型: char[] buf
返回类型: int
```
```
注意: buf[] 是目标缓存区不是源缓存区，read4 的返回结果将会复制到 buf[] 当中。
```
下列是一些使用 read4 的例子：
```
File file("abcdefghijk"); // 文件名为 "abcdefghijk"， 初始文件指针 (fp) 指向 'a' 
char[] buf = new char[4]; // 创建一个缓存区使其能容纳足够的字符
read4(buf); // read4 返回 4。现在 buf = "abcd"，fp 指向 'e'
read4(buf); // read4 返回 4。现在 buf = "efgh"，fp 指向 'i'
read4(buf); // read4 返回 3。现在 buf = "ijk"，fp 指向文件末尾
```
read 方法：

通过使用 read4 方法，实现 read 方法。该方法可以从文件中读取 n 个字符并将其存储到缓存数组 buf 中。您 不能 直接操作文件。

返回值为实际读取的字符。

read 的定义：
```
参数:   char[] buf, int n
返回值: int

注意: buf[] 是目标缓存区不是源缓存区，你需要将结果写入 buf[] 中。
```
示例 1：
```
File file("abc");
Solution sol;
// 假定 buf 已经被分配了内存，并且有足够的空间来存储文件中的所有字符。
sol.read(buf, 1); // 当调用了您的 read 方法后，buf 需要包含 "a"。 一共读取 1 个字符，因此返回 1。
sol.read(buf, 2); // 现在 buf 需要包含 "bc"。一共读取 2 个字符，因此返回 2。
sol.read(buf, 1); // 由于已经到达了文件末尾，没有更多的字符可以读取，因此返回 0。
```
示例 2:
```
File file("abc");
Solution sol;
sol.read(buf, 4); // 当调用了您的 read 方法后，buf 需要包含 "abc"。 一共只能读取 3 个字符，因此返回 3。
sol.read(buf, 1); // 由于已经到达了文件末尾，没有更多的字符可以读取，因此返回 0。
```
注意：
```
你 不能 直接操作该文件，文件只能通过 read4 获取而 不能 通过 read。
read  函数可以被调用 多次。
请记得 重置 在 Solution 中声明的类变量（静态变量），因为类变量会 在多个测试用例中保持不变，影响判题准确。请 查阅 这里。
你可以假定目标缓存数组 buf 保证有足够的空间存下 n 个字符。 
保证在一个给定测试用例中，read 函数使用的是同一个 buf。
```
## 解法
### 思路
因为是连续read，所以有可能某次的read动作是从上一次读取的内容中开始的，所以需要有一个实例变量存储读取状态。
- 实例变量：
    - tmp：作为临时存储字符的容器
    - point：指针，指向tmp第一个待读取的元素下标
    - count：代表tmp中有的字符长度
- 过程：
    - 创建局部变量total，记录实际读取了多少字符
    - 开始循环，退出条件是`total == n`
        - 如果point为0，则代表需要重新开始`read4`
        - 如果`count == 0`，代表文件已经读取完，直接退出
        - 读取后，更新count，并使用`read4`读取字符到tmp中
        - 开始将tmp中的元素写入到buf中，使用total边移动边写
        - 写完后，如果`point == 4`代表tmp被写完了，point需要重置
        - 如果此时`count != 4`，代表本次`read4`已经读到了文件尾部，需要退出最外层循环，否则会死循环
    - 返回total作为结果
### 代码
```java
public class Solution extends Reader4 {
    private int point;
    private int count;
    private char[] tmp = new char[4];
    public int read(char[] buf, int n) {
        int total = 0;
        while (total < n) {
            if (point == 0) {
                count = read4(tmp);
            }
            
            if (count == 0) {
                break;
            }

            while (total < n && point < count) {
                buf[total++] = tmp[point++];
            }

            if (point == count) {
                point = 0;
            }

            if (count < 4) {
                break;
            }
        }

        return total;
    }
}
```
# LeetCode_410_分割数组的最大值
## 题目
给定一个非负整数数组和一个整数 m，你需要将这个数组分成 m 个非空的连续子数组。设计一个算法使得这 m 个子数组各自和的最大值最小。

注意:
```
数组长度 n 满足以下条件:

1 ≤ n ≤ 1000
1 ≤ m ≤ min(50, n)
```
示例:
```
输入:
nums = [7,2,5,10,8]
m = 2

输出:
18
```
解释:
```
一共有四种方法将nums分割为2个子数组。
其中最好的方式是将其分为[7,2,5] 和 [10,8]，
因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
```
## 解法
### 思路
动态规划
- `dp[i][j]`：前i个字符组成的字符串，拆分成j段后，各种组合中最大值最小的那个值
- 状态转移方程：
    - 公式：`dp[i][j] = min(dp[i][j], max(dp[k][j - 1] + sub(k + 1, i)))`
    - 解释：
        - 将前i个字符拆分成`(0, k)`和`(k + 1, i)`两部分，其中`(k + 1, i)`的总和与`dp[k][j - 1]`中的最大值比较，求最大值
        - 再与`dp[i][j]`中的最小的最大值比较，取较小值
        - 在遍历所有可能的组合后，更新完前i个字符拆分成j段的所有可能，并得到最小值
        - 不断依赖之前的结果推演得到最后`dp[n][m]`
- 初始化：
    - 初始化所有dp值为int的最大值
    - `dp[0][0] = 0`，长度为0的字符拆成0段自然最大值就是0，这个值可以作为计算`j = 1`的情况的值
- 为了快速的求得sub`(k + 1, i)`，需要先获取前缀和，通过前缀和快速的获得区间的总和
### 代码
```java
class Solution {
    public int splitArray(int[] nums, int m) {
        int n = nums.length;
        int[][] dp = new int[n + 1][m + 1];
        for (int[] arr : dp) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }
        dp[0][0] = 0;

        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(m, i); j++) {
                for (int k = 0; k < i; k++) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j - 1], sum[i] - sum[k]));
                }
            }
        }

        return dp[n][m];
    }
}
```
# LeetCode_329_矩阵中的最长递增路径
## 题目
给定一个整数矩阵，找出最长递增路径的长度。

对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。

示例 1:
```
输入: nums = 
[
  [9,9,4],
  [6,6,8],
  [2,1,1]
] 
输出: 4 
解释: 最长递增路径为 [1, 2, 6, 9]。
```
示例 2:
```
输入: nums = 
[
  [3,4,5],
  [3,2,6],
  [2,2,1]
] 
输出: 4 
解释: 最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
```
## 解法
### 思路
dfs+记忆化搜索
### 代码
```java
class Solution {
public int longestIncreasingPath(int[][] matrix) {
        int row = matrix.length;
        if (row == 0) {
            return 0;
        }

        int col = matrix[0].length;
        if (col == 0) {
            return 0;
        }

        int max = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int len = dfs(matrix, row, col, i, j, Integer.MIN_VALUE, new int[row][col]);
                max = Math.max(max, len);
            }
        }

        return max;
    }

    private int dfs(int[][] matrix, int row, int col, int r, int c, int pre, int[][] memo) {
        if (!isValid(matrix, row, col, r, c, pre)) {
            return 0;
        }

        if (memo[r][c] != 0) {
            return memo[r][c];
        }

        int cur = matrix[r][c];

        int up = dfs(matrix, row, col, r - 1, c, cur, memo);
        int down = dfs(matrix, row, col, r + 1, c, cur, memo);
        int left = dfs(matrix, row, col, r, c - 1, cur, memo);
        int right = dfs(matrix, row, col, r, c + 1, cur, memo);

        int max = Math.max(up, Math.max(down, Math.max(left, right)));

        memo[r][c] = max + 1;
        return max + 1;
    }

    private boolean isValid(int[][] matrix, int row, int col, int r, int c, int pre) {
        return r >= 0 && r < row && c >= 0 && c < col && matrix[r][c] > pre;
    }
}
```