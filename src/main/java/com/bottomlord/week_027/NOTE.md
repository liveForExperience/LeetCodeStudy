# LeetCode_1020_飞地的数量
## 题目
给出一个二维数组 A，每个单元格为 0（代表海）或 1（代表陆地）。

移动是指在陆地上从一个地方走到另一个地方（朝四个方向之一）或离开网格的边界。

返回网格中无法在任意次数的移动中离开网格边界的陆地单元格的数量。

示例 1：
```
输入：[[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
输出：3
解释： 
有三个 1 被 0 包围。一个 1 没有被包围，因为它在边界上。
```
示例 2：
```
输入：[[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
输出：0
解释：
所有 1 都在边界上或可以到达边界。
```
提示：
```
1 <= A.length <= 500
1 <= A[i].length <= 500
0 <= A[i][j] <= 1
所有行的大小都相同
```
## 解法
### 思路
递归：
- 从第一行、最后一行、第一列、最后一列开始递归并将所有1置为0
- 遍历二维数组，如果不是0，就开始递归，将所有元素置为0，并逐层累加1
- 返回累加值
### 代码
```java
class Solution {
    public int numEnclaves(int[][] A) {
        int ans = 0;
        if (A == null || A.length <= 1 || A[0].length <= 1) {
            return 0;
        }
        
        int row = A.length, col = A[0].length;
        
        for (int i = 0; i < col; i++) {
            if (A[0][i] == 1) {
                dfs(A, 0, i, row, col);
            }
            
            if (A[row - 1][i] == 1) {
                dfs(A, row - 1, i, row, col);
            }
        }
        
        for (int i = 0; i < row; i++) {
            if (A[i][0] == 1) {
                dfs(A, i, 0, row, col);
            }
            
            if (A[i][col - 1] == 1) {
                dfs(A, i, col - 1, row, col);
            }
        }
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (A[i][j] == 1) {
                    ans += dfs(A, i, j, row, col);
                }
            }
        }

        return ans;
    }

    private int dfs(int[][] matrix, int x, int y, int row, int col) {
        if (x >= row || x < 0 || y >= col || y < 0 || matrix[x][y] == 0) {
            return 0;
        }

        matrix[x][y] = 0;
        return dfs(matrix, x + 1, y, row, col) +
                dfs(matrix, x - 1, y, row, col) +
                dfs(matrix, x, y + 1, row, col) +
                dfs(matrix, x, y - 1, row, col) + 1;
    }
}
```
## 优化代码
### 思路
- 将连接边缘的所有1置为0
- 之后就不需要再递归了，直接遍历二维数组累加即可
### 代码
```java
class Solution {
    public int numEnclaves(int[][] A) {
        int ans = 0;
        if (A == null || A.length <= 1 || A[0].length <= 1) {
            return 0;
        }

        int row = A.length, col = A[0].length;

        for (int i = 0; i < col; i++) {
            dfs(A, 0, i, row, col);
            dfs(A, row - 1, i, row, col);
        }

        for (int i = 0; i < row; i++) {
            dfs(A, i, 0, row, col);
            dfs(A, i, col - 1, row, col);
        }

        for (int[] ints : A) {
            for (int j = 0; j < col; j++) {
                ans += ints[j];
            }
        }

        return ans;
    }

    private void dfs(int[][] matrix, int x, int y, int row, int col) {
        if (x >= row || x < 0 || y >= col || y < 0 || matrix[x][y] == 0) {
            return;
        }

        matrix[x][y] = 0;
        dfs(matrix, x + 1, y, row, col);
        dfs(matrix, x - 1, y, row, col);
        dfs(matrix, x, y + 1, row, col);
        dfs(matrix, x, y - 1, row, col);
    }
}
```
# LeetCode_92_反转链表II
## 题目
反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。

说明:
1 ≤ m ≤ n ≤ 链表长度。

示例:
```
输入: 1->2->3->4->5->NULL, m = 2, n = 4
输出: 1->4->3->2->5->NULL
```
## 解法
### 思路
- 通过值交换来反转链表
- 使用两个指针
    - `left`：通过m来移动到反转的起始位置，作为反转起始位置的起点指针，这个变量会作为类变量保存
    - `right`：通过n递归移动到反转的结束位置，作为反转的结束位置的结尾指针
- 过程：
    - 递归遍历整个链表
    - 通过每一层递减m和n的值来移动并定位left和right指针
    - 递归的退出条件是`n == 1`，也就是遍历到了最后一个节点的位置
    - 在递归返回后的回溯过程中，进行left和right的值交换
    - 然后left向右移动，存于类变量中，right指针继续回溯
    - 如果left和right指针指向同一个节点，或者right已经到了left的左边，也就是`left == right.next`，那么就不需要进行值交换
### 代码
```java
class Solution {
    private boolean stop;
    private ListNode left;

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) {
            return null;
        }

        stop = false;
        ListNode right = left = head;
        reserve(right, m, n);
        
        return head;
    }

    private void reserve(ListNode right, int m, int n) {
        if (n == 1) {
            return;
        }

        right = right.next;

        if (m > 1) {
            left = left.next;
        }

        reserve(right, m - 1, n - 1);
        if (left == right || right.next == left) {
            stop = true;
        }
        
        if (!stop) {
            int tmp = left.val;
            left.val = right.val;
            right.val = tmp;
            
            left = left.next;
        }
    }
}
```
## 解法二
### 思路
- 通过改变链表结构来实现链表反转
- 链表节点之间的反转过程：
    - `A -> B -> C`
    - `pre`指针指向`A`
    - `cur`指针指向`B`
    - `tmp`指针指向`cur.next`
    - `cur.next = pre`
    - `pre = cur`
    - `cur = tmp`
    - 实现`A <- B <- C`
- 故本题中会用到的两个指针是：
    - `pre`：初始指向head之前的节点，也就是null
    - `cur`：初始指向head
- 另外需要的节点：
    - `con`：代表反转部分链表的头节点，初始点为m的前一个节点
    - `tail`：代表反转部分链表的尾节点，初始点为m节点
- 过程为迭代：
    - 逐步移动`pre`和`cur`
    - 直到`cur`来到m节点位置，开始反转
    - `con`指向`pre`，`tail`指向`cur`
    - 当`pre`指向第n个节点时
        - `con.next`指向`pre`
        - `tail.next`指向`cur`
- 结束循环并返回`head`
### 代码
```java
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) {
            return null;
        }

        ListNode pre = null, cur = head;
        while (m > 1) {
            pre = cur;
            cur = cur.next;
            m--;
            n--;
        }

        ListNode con = pre, tail = cur, tmp = null;
        while (n > 0) {
            tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
            n--;
        }

        if (con != null) {
            con.next = pre;
        } else {
            head = pre;
        }
        
        tail.next = cur;
        return head;
    }
}
```
# LeetCode_150_逆波兰表达式求值
## 题目
根据逆波兰表示法，求表达式的值。

有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。

说明：
```
整数除法只保留整数部分。
给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
```
示例 1：
```
输入: ["2", "1", "+", "3", "*"]
输出: 9
解释: ((2 + 1) * 3) = 9
```
示例 2：
```
输入: ["4", "13", "5", "/", "+"]
输出: 6
解释: (4 + (13 / 5)) = 6
```
示例 3：
```
输入: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
输出: 22
解释: 
  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22
```
## 解法
### 思路
栈:
- 初始化栈`stack`
- 遍历`tokens`
- 如果是数字，将字符串转为int压入栈中`stack`
- 如果是`+`、`-`、`*`、`/`，将栈顶两个元素依次出栈，进行对应的计算
- 在`/`和`-`的情况下还要考虑数字的前后，出栈顺序与运算顺序相反
- 字符串遍历结束后，返回栈顶元素作为结果
### 代码
```java
class Solution {
    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            int num;
            if (Objects.equals(token, "+")) {
                num = stack.pop() + stack.pop();
            } else if (Objects.equals(token, "-")) {
                Integer second = stack.pop();
                Integer first = stack.pop();
                num = first - second;
            } else if (Objects.equals(token, "*")) {
                num = stack.pop() * stack.pop();
            } else if (Objects.equals(token, "/")) {
                Integer second = stack.pop();
                Integer first = stack.pop();
                num = first / second;
            } else {
                num = Integer.parseInt(token);
            }

            stack.push(num);
        }

        return stack.pop();
    }
}
```
# LeetCode_636_函数的独占时间
## 题目
给出一个非抢占单线程CPU的 n 个函数运行日志，找到函数的独占时间。

每个函数都有一个唯一的 Id，从 0 到 n-1，函数可能会递归调用或者被其他函数调用。

日志是具有以下格式的字符串：function_id：start_or_end：timestamp。例如："0:start:0" 表示函数 0 从 0 时刻开始运行。"0:end:0" 表示函数 0 在 0 时刻结束。

函数的独占时间定义是在该方法中花费的时间，调用其他函数花费的时间不算该函数的独占时间。你需要根据函数的 Id 有序地返回每个函数的独占时间。

示例 1:
```
输入:
n = 2
logs = 
["0:start:0",
 "1:start:2",
 "1:end:5",
 "0:end:6"]
输出:[3, 4]
说明：
函数 0 在时刻 0 开始，在执行了  2个时间单位结束于时刻 1。
现在函数 0 调用函数 1，函数 1 在时刻 2 开始，执行 4 个时间单位后结束于时刻 5。
函数 0 再次在时刻 6 开始执行，并在时刻 6 结束运行，从而执行了 1 个时间单位。
所以函数 0 总共的执行了 2 +1 =3 个时间单位，函数 1 总共执行了 4 个时间单位。
```
说明：
```
输入的日志会根据时间戳排序，而不是根据日志Id排序。
你的输出会根据函数Id排序，也就意味着你的输出数组中序号为 0 的元素相当于函数 0 的执行时间。
两个函数不会在同时开始或结束。
函数允许被递归调用，直到运行结束。
1 <= n <= 100
```
## 解法
### 思路
使用栈来模拟函数调用：
- 初始化一个长度为n的数组`res`，每一个下标保存对应id的函数耗时
- 初始化一个栈用来保存函数调用的顺序，栈顶元素为正在被调用的函数
- 定义一个变量`pre`，用来保存栈顶被执行函数的起始执行时间
    - 当遇到`start`时，`pre = str[2]`
    - 当遇到`end`时，`pre = str[2] + 1`
- 当遇到`start`的时候，如果栈顶有元素，那么栈顶元素的部分耗时就是`str[2] - pre`，累加至`res`的id对应下标中，并将`id`压入栈中
- 当遇到`end`的时候，如果栈顶有元素，那么栈顶元素的耗时就是`str[2] - pre`，累加至`res`的id对应下标中，并将栈顶`id`弹出
- 最后返回`res`
### 代码
```java
class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] ans = new int[n];
        Stack<Integer> stack = new Stack<>();
        int pre = 0;
        for (String log : logs) {
            String[] strs = log.split(":");
            Integer id = Integer.valueOf(strs[0]);
            int time = Integer.parseInt(strs[2]);
            if (Objects.equals("start", strs[1])) {
                if (!stack.isEmpty()) {
                    ans[stack.peek()] += time - pre;
                }
                pre = time;
                stack.push(id);
            } else {
                ans[stack.pop()] += time - pre + 1;
                pre = time + 1;
            }
        }

        return ans;
    }
}
```
# LeetCode_752_打开转盘锁
## 题目
你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。每个拨轮可以自由旋转：例如把 '9' 变为  '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。

锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。

列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。

字符串 target 代表可以解锁的数字，你需要给出最小的旋转次数，如果无论如何不能解锁，返回 -1。

示例 1:
```
输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
输出：6
解释：
可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
因为当拨动到 "0102" 时这个锁就会被锁定。
```
示例 2:
```
输入: deadends = ["8888"], target = "0009"
输出：1
解释：
把最后一位反向旋转一次即可 "0000" -> "0009"。
```
示例 3:
```
输入: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
输出：-1
解释：
无法旋转到目标数字且不被锁定。
```
示例 4:
```
输入: deadends = ["0000"], target = "8888"
输出：-1
```
提示：
```
死亡列表 deadends 的长度范围为 [1, 500]。
目标数字 target 不会在 deadends 之中。
每个 deadends 和 target 中的字符串的数字会在 10,000 个可能的情况 '0000' 到 '9999' 中产生。
```
## 解法
### 思路
广度优先搜索：
- 初始化一个队列，用来进行bfs的广度优先搜索
- 初始化一个set，用来存已经出现过的字符串
- 初始化`depth`，作为bfs的层数也是题目的变化次数
- 从`0000`开始，每一步都有8中可能：`0`,`1`,`2`,`3`每一个下标的值可以加减1
- 每一层的最后一个元素塞入`null`，用来标志搜索进入下一层
- 在每一个层的遍历中，走如下三个分支：
    - 判断是否是`null`，如果是null就进入下一层
    - 判断是否是`target`，是就结束遍历，并返回`depth`
    - 判断是否是`deadends`，如果不是，就嵌套遍历所有8种可能，然后将set中没有的字符串添加到set中，并将该字符串放入队列
- 如果都没有匹配的，就返回-1
### 代码
```java
class Solution {
    public int openLock(String[] deadends, String target) {
        Set<String> dead = new HashSet<>();
        Collections.addAll(dead, deadends);

        Queue<String> queue = new LinkedList<>();
        Set<String> memo = new HashSet<>();
        int depth = 0;
        queue.offer("0000");
        queue.offer(null);

        memo.add("0000");

        while (!queue.isEmpty()) {
            String str = queue.poll();
            if (str == null) {
                depth++;
                if (queue.peek() != null) {
                    queue.offer(null);
                }
            } else if (Objects.equals(target, str)) {
                return depth;
            } else if (!dead.contains(str)){
                for (int i = 0; i < 4; i++) {
                    for (int j = -1; j <= 1; j++) {
                        int num = ((str.charAt(i) - '0') + j + 10) % 10;
                        String tmp = str.substring(0, i) + ("" + num) + str.substring(i + 1);

                        if (!memo.contains(tmp)) {
                            memo.add(tmp);
                            queue.offer(tmp);
                        }
                    }
                }
            }
        }

        return -1;
    }
}
```