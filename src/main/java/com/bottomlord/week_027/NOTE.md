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