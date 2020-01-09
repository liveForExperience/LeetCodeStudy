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