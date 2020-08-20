# LeetCode_279_完全平方数
## 题目
给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。

示例 1:
```
输入: n = 12
输出: 3 
解释: 12 = 4 + 4 + 4.
```
示例 2:
```
输入: n = 13
输出: 2
解释: 13 = 4 + 9.
```
## 解法
### 思路
动态规划：
- `dp[i]`：最少的组成`i`的完全平方数的个数
- base case：`dp[1] = 1`
- 状态转移方程：`dp[i] = min(dp[i], dp[i - j * j] + 1)`
    - 每个数的最坏情况是都由1来组成
    - `j * j`代表完全平方数
### 代码
```java
class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = i;
            for (int j = 1; i - j * j >= 0; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }
}
```
# LeetCode_1023_驼峰式匹配
## 题目
如果我们可以将小写字母插入模式串 pattern 得到待查询项 query，那么待查询项与给定模式串匹配。（我们可以在任何位置插入每个字符，也可以插入 0 个字符。）

给定待查询列表 queries，和模式串 pattern，返回由布尔值组成的答案列表 answer。只有在待查项 queries[i] 与模式串 pattern 匹配时， answer[i] 才为 true，否则为 false。

示例 1：
```
输入：queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FB"
输出：[true,false,true,true,false]
示例：
"FooBar" 可以这样生成："F" + "oo" + "B" + "ar"。
"FootBall" 可以这样生成："F" + "oot" + "B" + "all".
"FrameBuffer" 可以这样生成："F" + "rame" + "B" + "uffer".
```
示例 2：
```
输入：queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBa"
输出：[true,false,true,false,false]
解释：
"FooBar" 可以这样生成："Fo" + "o" + "Ba" + "r".
"FootBall" 可以这样生成："Fo" + "ot" + "Ba" + "ll".
```
示例 3：
```
输出：queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBaT"
输入：[false,true,false,false,false]
解释： 
"FooBarTest" 可以这样生成："Fo" + "o" + "Ba" + "r" + "T" + "est".
```
提示：
```
1 <= queries.length <= 100
1 <= queries[i].length <= 100
1 <= pattern.length <= 100
所有字符串都仅由大写和小写英文字母组成。
```
## 解法
### 思路
- 遍历`queries`数组
- 判断当前字符串是否匹配，并记录到`list`中：
    - 遍历当前字符串的字符数组
    - 暂存遍历`pattern`的下标`i`
    - 如果`i`小于`pattern`的长度
        - 如果字符与`pattern`的字符相等，`i++`
        - 如果字符与`pattern`的字符不等，判断是否是小写，如果不是就返回false
    - 如果`i`大于等于`pattern`的长度，说明剩下的字符应该全是小写，如果不是就返回false
    - 当前字符遍历结束，如果`i < pattern.length`，返回false，否则返回true
- 遍历结束，返回`list`
### 代码
```java
class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> ans = new ArrayList<>(queries.length);
        for (String query : queries) {
            ans.add(match(query, pattern));
        }
        return ans;
    }
    
    private boolean match(String query, String pattern) {
        int index = 0;
        for (char c : query.toCharArray()) {
            if (index < pattern.length()) {
                if (c == pattern.charAt(index)) {
                    index++;
                } else if (c < 'a' || c > 'z') {
                    return false;
                }
            } else if (c < 'a' || c > 'z') {
                return false;
            }
        }

        return index >= pattern.length();
    }
}
```
# LeetCode_672_灯泡开关II
## 题目
现有一个房间，墙上挂有 n 只已经打开的灯泡和 4 个按钮。在进行了 m 次未知操作后，你需要返回这 n 只灯泡可能有多少种不同的状态。

假设这 n 只灯泡被编号为 [1, 2, 3 ..., n]，这 4 个按钮的功能如下：
```
将所有灯泡的状态反转（即开变为关，关变为开）
将编号为偶数的灯泡的状态反转
将编号为奇数的灯泡的状态反转
将编号为 3k+1 的灯泡的状态反转（k = 0, 1, 2, ...)
```
示例 1:
```
输入: n = 1, m = 1.
输出: 2
说明: 状态为: [开], [关]
```
示例 2:
```
输入: n = 2, m = 1.
输出: 3
说明: 状态为: [开, 关], [关, 开], [关, 关]
```
示例 3:
```
输入: n = 3, m = 1.
输出: 4
说明: 状态为: [关, 开, 关], [开, 关, 开], [关, 关, 关], [关, 开, 开].
注意： n 和 m 都属于 [0, 1000].
```
## 解法
### 思路
- 所有可能性的搜索空间很大：
    - 灯的状态：2 ^ n
    - 操作的可能：4 ^ m
- 简化搜索空间：
    - 连续两个重复的操作与不操作一致，所以每个操作只考虑`0`或`1`
    - 因为执行`a,b`与执行`b,a`的效果相同，所以操作可以只按升序排列
    - 根据操作d可得，`n <= 3`可以覆盖所有可能性
- 因搜索空间有限，可以穷举：
    - `n == 0`或`m == 0`：1
    - `n == 1`：两种情况，开或关
    - `n == 2 && m == 1`：3种情况，开关，关开，关关
    - `n == 2 && m == 2`：4种情况，关关，开开，开关，关开
    - `n >= 3 && m == 1`：4种情况，关开开，关关关，开关开，关开关
    - `n >= 3 && m == 2`：7种情况，关关关，开开开，开关开，关开关，关关开，开开关，开关关
    - `n >= 3 && m > 2`：8种可能，关关关，开开开，开关开，关开关，关关开，开开关，开关关，关开开
### 代码
```java
class Solution {
    public int flipLights(int n, int m) {
        if (n == 0 || m == 0) {
            return 1;
        }

        if (n == 1) {
            return 2;
        }

        if (n == 2) {
            if (m == 1) {
                return 3;
            }

            if (m == 2) {
                return 4;
            }

            if (m > 2) {
                return 4;
            }
        }

        if (n >= 3) {
            if (m == 1) {
                return 4;
            }

            if (m == 2) {
                return 7;
            }

            if (m > 2) {
                return 8;
            }
        }
        
        return 8;
    }
} 
```
# LeetCode_423_从英文中重建数字
## 题目
给定一个非空字符串，其中包含字母顺序打乱的英文单词表示的数字0-9。按升序输出原始的数字。

注意:
```
输入只包含小写英文字母。
输入保证合法并可以转换为原始的数字，这意味着像 "abc" 或 "zerone" 的输入是不允许的。
输入字符串的长度小于 50,000。
```
示例 1:
```
输入: "owoztneoer"

输出: "012" (zeroonetwo)
```
示例 2:
```
输入: "fviefuro"

输出: "45" (fourfive)
```
## 解法
### 思路
- 分析每个英文字母发现，所有偶数都有其他数字没有的独有字母
    - 0：z
    - 2：w
    - 4：u
    - 6：x
    - 8：g
- 剩下的奇数中有3个数字与偶数的数字的某个字母有交集
    - 3 & 8：h
    - 5 & 4：f
    - 7 & 6：s
- 最后剩下的1和9，各自的n和i在之前的数字中有出现
    - 1 & 7 & 9：n
    - 9 & 5 & 6 & 8：i
- 根据如上的分析，过程如下：
    - 遍历字符数组，计算所有字母的出现个数，放入数组
    - 依次计算0，2，4，6，8的独有字母个数，算出各自代表数字的个数
    - 依次计算3，5，7中与偶数出现交集的字母个数并减去对应偶数的个数，算出这三个值的个数
    - 依次计算1，9的n和i，将对应重复出现的数字的个数减去，算出这两个数的值
    - 嵌套循环：
        - 外层遍历10个数
        - 内层遍历每个数对应的个数，append数字
### 代码
```java
class Solution {
    public String originalDigits(String s) {
        char[] cs = new char[26 + (int)'a'];

        for (char c : s.toCharArray()) {
            cs[c]++;
        }

        int[] nums = new int[10];
        nums[0] = cs['z'];
        nums[2] = cs['w'];
        nums[4] = cs['u'];
        nums[6] = cs['x'];
        nums[8] = cs['g'];
        nums[3] = cs['h'] - nums[8];
        nums[5] = cs['f'] - nums[4];
        nums[7] = cs['s'] - nums[6];
        nums[9] = cs['i'] - nums[5] - nums[6] - nums[8];
        nums[1] = cs['n'] - nums[7] - 2 * nums[9];
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < nums[i]; j++) {
                sb.append(i);
            }
        }

        return sb.toString();
    }
}
```
# LeetCode_1017_负二进制转换
## 题目
给出数字 N，返回由若干 "0" 和 "1"组成的字符串，该字符串为 N 的负二进制（base -2）表示。

除非字符串就是 "0"，否则返回的字符串中不能含有前导零。

示例 1：
```
输入：2
输出："110"
解释：(-2) ^ 2 + (-2) ^ 1 = 2
```
示例 2：
```
输入：3
输出："111"
解释：(-2) ^ 2 + (-2) ^ 1 + (-2) ^ 0 = 3
```
示例 3：
```
输入：4
输出："100"
解释：(-2) ^ 2 = 4
```
提示：
```
0 <= N <= 10^9
```
## 解法
### 思路
与正数进制的转换类似
- 循环除2，将余数从低位相连
- 如果是奇数，负数进制要向下取整，否则当`N == -1`时，结果`N / -2`直接就为0了，但其实应该是第二位为1，所以需要向下取整为`-2`
- **具体原因没有理解**
### 代码
```java
class Solution {
    public String baseNeg2(int N) {
        if (N == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        while (N != 0) {
            if (N % 2 == 0) {
                N /= -2;
                sb.insert(0, 0);
            } else {
                N = (N - 1) / -2;
                sb.insert(0, 1);
            }
        }

        return sb.toString();
    }
}
```
# LeetCode_309_最佳股票买卖时机含冷冻期
## 题目
设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
```
你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
```
示例:
```
输入: [1,2,3,0,2]
输出: 3 
解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
```
## 解法
### 思路
动态规划：
- dp[i][j]：代表第i天j状态时的最大利润
    - i：代表天数
    - j：代表当天的状态，1为持有股票，0为没有股票
- base case：初始会有两个状态，持有和没有
    - `dp[0][0] = 0`：第一天不买，利润就是0
    - `dp[0][1] = -price[0]`：第一天买，利润为负的当天价格
- 状态转移方程：
    - `dp[i][0] = max(dp[i - 1][0], dp[i - 1][1] + price[i])`：当天没有股票时的利润最大值，就是`前一天也没有股票`和`前一天持有今天卖掉`的两种情况中的最大值
    - `dp[i][1]` = max(dp[i - 1][1], dp[i - 2][0] - price[i])：当天有股票时的利润最大值，就是`前一天也持有股票，今天没卖`和`两天前没有股票(冷却期1天)，今天买了`的两种情况中的最大值
- 求的最后结果：`dp[n - 1][0]`
- 注意：
    - 因为有冷冻期，所以第二天有股票的情况也需要特殊处理为：`dp[1][1] = max(dp[0][1], dp[0][0] - prices[i])`
    - 注意数组长度为0的情况
### 代码
```java
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0) {
            return 0;
        }
        
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }

            if (i == 1) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
                continue;
            }
            
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);
        }

        return dp[n - 1][0];
    }
}
```
## 代码优化
### 思路
因为每一天的状态只依赖于前一天持有，前一天没有持有，和两天前没有持有这3个状态，所以可以直接使用局部变量来暂存，降低了空间复杂度
### 代码
```java
class Solution {
    public int maxProfit(int[] prices) {
        int hold = Integer.MIN_VALUE, rest = 0, pre = 0;
        for (int price : prices) {
            int tmp = rest;
            rest = Math.max(rest, hold + price);
            hold = Math.max(hold, pre - price);
            pre = tmp;
        }
        
        return rest;
    }
}
```
# LeetCode_503_下一个更大元素
## 题目
给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。

示例 1:
```
输入: [1,2,1]
输出: [2,-1,2]
解释: 第一个 1 的下一个更大的数是 2；
数字 2 找不到下一个更大的数； 
第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
注意: 输入数组的长度不会超过 10000。
```
## 解法
### 思路
双指针：
- 慢指针用于确定比较的基准元素，循环结束条件为数组越界
- 快指针用于遍历数组找到更大值：
    - 如果没有找到且重新回到原来的元素位置，就置为-1，移动慢指针下标
    - 如果找到就置为找到值，移动慢指针下标
- 嵌套循环：
    - 外层遍历慢指针
    - 内层退出条件为下标快慢指针相同
### 代码
```java
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0; i < nums.length; i++) {
            int j = (i + 1) % n;
            boolean flag = false;
            while (i != j) {
                if (nums[i] < nums[j]) {
                    flag = true;
                    break;
                }
                
                j = (j + 1) % n;
            }
            
            if (flag) {
                ans[i] = nums[j];
            } else {
                ans[i] = -1;
            }
        }
        
        return ans;
    }
}
```
## 解法二
### 思路
使用栈：
- 初始化一个栈用来暂存数组下标
- 从数组尾部开始循环遍历两次数组
- 循环过程：
    - 如果`栈不为空`且`当前下标元素大于等于栈顶下标对应的元素`，就循环弹栈：因为是从尾部开始遍历，且循环过程中会把当前下标压入栈中，那么这个判断就意味着，用当前下标的元素与之后最近的元素进行比较，如果不符合要求就直接弹出栈
    - 弹栈的部分结束后，判断栈是否为空：
        - 如果为空说明当前元素之后的所有元素都不符合要求，就放入-1到结果中
        - 如果不为空，就把栈顶的下标对应的元素放入结果中
    - 将当前下标压入栈中
- 这样可以完成题目要求的原因是：
    - 每次需要判断的元素，与栈顶下标对应的元素进行比较时，这个栈顶元素之后所有小于它的下标已经全部被淘汰了，剩下的都是有可能的元素，如果栈中的所有元素都不符合，就会全部被弹出，同时也就可以根据栈中是否有元素来决定结果是-1还是具体的下标了。
    - 而外层循环两个数组长度的原因是，如果第一个数组长度的循环判断完以后，有些元素之后的元素没有比当前的值大，那么在第二个数组长度的循环中，它们之前的有可能的元素也会被放入栈中，这样就能再进行一次判断，从而进行所有可能性的判断
### 代码
```java
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = (n - 1) * 2; i >= 0; i--) {
            while (!stack.empty() && nums[i % n] >= nums[stack.peek()]) {
                stack.pop();
            }

            ans[i % n] = stack.empty() ? -1 : nums[stack.peek()];

            stack.push(i % n);
        }
        
        return ans;
    }
}
```
# LeetCode_116_填充每个节点的下一个右侧节点指针
## 题目
给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
```
struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
```
初始状态下，所有 next 指针都被设置为 NULL。

示例：
```
输入：{"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":null,"right":null,"val":4},"next":null,"right":{"$id":"4","left":null,"next":null,"right":null,"val":5},"val":2},"next":null,"right":{"$id":"5","left":{"$id":"6","left":null,"next":null,"right":null,"val":6},"next":null,"right":{"$id":"7","left":null,"next":null,"right":null,"val":7},"val":3},"val":1}

输出：{"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":{"$id":"4","left":null,"next":{"$id":"5","left":null,"next":{"$id":"6","left":null,"next":null,"right":null,"val":7},"right":null,"val":6},"right":null,"val":5},"right":null,"val":4},"next":{"$id":"7","left":{"$ref":"5"},"next":null,"right":{"$ref":"6"},"val":3},"right":{"$ref":"4"},"val":2},"next":null,"right":{"$ref":"7"},"val":1}

解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
```
提示：
```
你只能使用常量级额外空间。
使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
```
## 解法
### 思路
bfs：
- 遍历每一层的元素时，将队列每个元素next指向同层的下一个元素
- 如果遍历到当前层的最后一个元素，就将next指向null
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
            int len = queue.size();
            
            while (len-- > 0) {
                Node node = queue.poll();
                if (node == null) {
                    continue;
                }
                
                if (node.left != null) {
                    queue.offer(node.left);
                }
                
                if (node.right != null) {
                    queue.offer(node.right);
                }
                
                if (len != 0) {
                    node.next = queue.peek();
                }
            }
        }
        
        return root;
    }
}
```
## 解法二
### 思路
- 初始化3个指针：
    - start：指向每一层的起始节点，通过它来进行向下的一层继续的操作
    - cur：指向当前层遍历到的节点，从每一层的第二个节点开始，同时也是判断当前层是否遍历完成的标志
    - pre：指向cur之前的节点，next指针指向cur，从每一层的start节点开始
- 过程：
    - 从根节点开始，pre和start指向root，cur为null
    - 遍历当前层时，通过pre和cur对当前层的下一层的节点的next指针进行初始化，每一次都初始化pre指向的连个子节点
    - 同时依赖上一层进行的初始化来横向移动遍历
    - 直到cur为null时
        - pre只操作left节点的next指针初始化
        - 同时pre和start指针一起向下一层移动
    - 循环的退出条件是，start和pre向下移动的时候，pre的left节点为空，这说明整个二叉树已经遍历完了，因为这是一个完美二叉树，如果起始节点为空，那么这一行也就是空的
### 代码
```java
class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        
        Node pre = root, start = pre, cur = null;

        while (pre.left != null) {
            if (cur == null) {
                pre.left.next = pre.right;
                pre = start.left;
                cur = start.right;
                start = pre;
            } else {
                pre.left.next = pre.right;
                pre.right.next = cur.left;
                pre = pre.next;
                cur = cur.next;
            }
        }
        
        return root;
    }
}
```