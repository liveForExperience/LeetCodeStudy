# LeetCode_386_字典序排数
## 题目
给定一个整数 n, 返回从 1 到 n 的字典顺序。

例如，

给定 n =1 3，返回 [1,10,11,12,13,2,3,4,5,6,7,8,9] 。

请尽可能的优化算法的时间复杂度和空间复杂度。 输入的数据 n 小于等于 5,000,000。
## 解法
### 思路
以构建10叉树的思路，dfs遍历整个序列，每一层都将元素放入list中，最终返回
- 需要注意，因为第一层是从1开始的，所以会出现`10+0`以及`1+9`都得到10，从而10重复的问题，需要特别去除
### 代码
```java
class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new ArrayList<>();
        dfs(ans, 1, n);
        return ans;
    }

    private void dfs(List<Integer> ans, int start, int n) {
        if (start > n) {
            return;
        }

        for (int i = 0; i < 10 && i + start <= n; i++) {
            if (start == 1 && i == 9) {
                continue;
            }
            ans.add(start + i);
            dfs(ans, (start + i) * 10, n);
        }
    }
}
```
# LeetCode_238_除自身以外数组的乘积
## 题目
给定长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。

示例:
```
输入: [1,2,3,4]
输出: [24,12,8,6]
说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
```
进阶：
```
你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
```
## 解法
### 思路
- 数组所有元素的乘积 = 当前数左边所有数的乘积left * 当前数 * 当前数右边所有数的乘积right
- 所以按题目要求：`ans = left * right`
- 遍历数组，求当前元素左边所有元素的乘积的集合
- 遍历数组，求当前元素右边所有元素的乘积的集合
- 遍历数组，求`left * right`
### 代码
```java
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] lefts = new int[nums.length];
        lefts[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            lefts[i] = nums[i - 1] * lefts[i - 1];
        }

        int[] rights = new int[nums.length];
        rights[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            rights[i] = nums[i + 1] * rights[i + 1];
        }
        
        for (int i = 0; i < nums.length; i++) {
            lefts[i] *= rights[i];
        }
        return lefts;
    }
}
```
## 优化代码
### 思路
把解法一种3次的遍历过程，压缩成一次：
- 将结果数组元素都初始化为1
- 通过头尾指针累乘的方式来计算左积和右积
- 将头尾指针的累乘值与元素值相乘
- 遍历结束后返回结果即可
### 代码
```java
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int left = 1, right = 1, len = nums.length;
        int[] ans = new int[len];
        Arrays.fill(ans, 1);
        for (int i = 0; i < len; i++) {
            ans[i] *= left;
            left *= nums[i];
            
            ans[len - i - 1] *= right;
            right *= nums[len - i - 1];
        }
        return ans;
    }
}
```
# LeetCode_1161_最大层内元素和
## 题目
给你一个二叉树的根节点 root。设根节点位于二叉树的第 1 层，而根节点的子节点位于第 2 层，依此类推。

请你找出层内元素之和 最大 的那几层（可能只有一层）的层号，并返回其中 最小 的那个。

示例：
```
输入：[1,7,0,7,-8,null,null]
输出：2
解释：
第 1 层各元素之和为 1，
第 2 层各元素之和为 7 + 0 = 7，
第 3 层各元素之和为 7 + -8 = -1，
所以我们返回第 2 层的层号，它的层内元素之和最大。
```
提示：
```
树中的节点数介于 1 和 10^4 之间
-10^5 <= node.val <= 10^5
```
## 解法
### 思路
- dfs先序遍历，记录层号
- 用层号作为下标在list中记录当层的累加值
- 遍历结束后，找到list中的最小下标的最大值返回
### 代码
```java
class Solution {
    public int maxLevelSum(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfs(list, root, 1);
        
        int max = Collections.max(list);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == max) {
                return i + 1;
            }
        }
        
        return 0;
    }

    private void dfs(List<Integer> list, TreeNode node, int level) {
        if (node == null) {
            return;
        }

        if (list.size() < level) {
            list.add(node.val);
        } else {
            list.set(level - 1, list.get(level - 1) + node.val);
        }
        dfs(list, node.left, level + 1);
        dfs(list, node.right, level + 1);
    }
}
```
# LeetCode_791_自定义字符串排序
## 题目
字符串S和 T 只包含小写字符。在S中，所有字符只会出现一次。

S 已经根据某种规则进行了排序。我们要根据S中的字符顺序对T进行排序。更具体地说，如果S中x在y之前出现，那么返回的字符串中x也应出现在y之前。

返回任意一种符合条件的字符串T。

示例:
```
输入:
S = "cba"
T = "abcd"
输出: "cbad"
解释: 
S中出现了字符 "a", "b", "c", 所以 "a", "b", "c" 的顺序应该是 "c", "b", "a". 
由于 "d" 没有在S中出现, 它可以放在T的任意位置. "dcba", "cdba", "cbda" 都是合法的输出。
```
注意:
```
S的最大长度为26，其中没有重复的字符。
T的最大长度为200。
S和T只包含小写字符。
```
## 解法
### 思路
- 一个数组nums存T中字母的个数
- 遍历S字符数组，根据顺序从nums中找对应字符的个数，append进sb中，同时将nums的字符改成0
- 遍历nums，将不为0的字符append进sb
- 返回`sb.toString()`
### 代码
```java
class Solution {
    public String customSortString(String S, String T) {
        int[] nums = new int[26];
        for (char c : T.toCharArray()) {
            nums[c - 'a']++;
        }
        
        StringBuilder sb = new StringBuilder();
        for (char c : S.toCharArray()) {
            for (int i = 0; i < nums[c - 'a']; i++) {
                sb.append(c);
            }
            nums[c - 'a'] = 0;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                for (int j = 0; j < nums[i]; j++) {
                    sb.append((char) (i + 'a'));
                }
            }
        }
        
        return sb.toString();
    }
}
```
# LeetCode_64_最小路径和
## 题目
给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

说明：每次只能向下或者向右移动一步。

示例:
```
输入:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
输出: 7
解释: 因为路径 1→3→1→1→1 的总和最小。
```
## 失败解法
### 思路
递归搜索所有可能性，在递归过程中超出边界或大于已有最小值则直接返回。递归结束后返回获得最小值
### 失败原因
超出时间限制
### 代码
```java
class Solution {
    private int min = Integer.MAX_VALUE;
    public int minPathSum(int[][] grid) {
        recurse(grid, 0, 0, grid.length - 1, grid[0].length - 1, 0);
        return min;
    }

    private void recurse(int[][] grid, int row, int col, int finalRow, int finalCol, int sum) {
        if (sum > min || row > finalRow || col > finalCol) {
            return;
        }

        sum += grid[row][col];
        
        if (row == finalRow && col == finalCol) {
            min = Math.min(min, sum);
            return;
        }

        recurse(grid, row + 1, col, finalRow, finalCol, sum);
        recurse(grid, row, col + 1, finalRow, finalCol, sum);
    }
}
```
## 解法
### 思路
动态规划：
- 定义状态转移方程
```math
dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]
```
- 遍历二维数组会出现四种情况：
    - 终点：`dp[i][j] = grid[i][j]`
    - 最下面一层且不是终点，也就是状态转移的base case，`dp[i][j] = grid[i][j] + dp[i][j + 1]`
    - 不是最下面一层，但是最后一列，因为没有向右的选项，所以也是特殊情况，`dp[i][j] = grid[i][j] + dp[i + 1][j]`
    - 普通情况：`dp[i][j] = grid[i][j] + Math.min(dp[i + 1][j], dp[i][j + 1])`
- 最后返回dp[0][0]即可
### 代码
```java
class Solution {
    public int minPathSum(int[][] grid) {
        int rowLen = grid.length, colLen = grid[0].length;
        int[][] dp = new int[rowLen][colLen];
        for (int i = rowLen - 1; i >= 0; i--) {
            for (int j = colLen - 1; j >= 0; j--) {
                if (i == rowLen - 1 && j == colLen - 1) {
                    dp[i][j] = grid[i][j];
                } else if (i == rowLen - 1 && j != colLen - 1) {
                    dp[i][j] = grid[i][j] + dp[i][j + 1];
                } else if (i != rowLen - 1 && j == colLen - 1) {
                    dp[i][j] = grid[i][j] + dp[i + 1][j];
                } else {
                    dp[i][j] = grid[i][j] + Math.min(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        
        return dp[0][0];
    }
}
```
## 解法二
### 思路
在解法二的基础上，用一维数组代替二维数组，因为除了最后一行，所有的元素都是依赖上一行的同一列和同一行的右一个，所以数组可以复用。在更新当前元素的dp值时，可以利用到上一行对应的还未更新的dp数组的当前列的值。
```math
dp[j] = grid[i][j] + Math.min(dp[j], dp[j + 1])
```
### 代码
```java
class Solution {
    public int minPathSum(int[][] grid) {
        int rowLen = grid.length, colLen = grid[0].length;
        int[] dp = new int[colLen];
        for (int i = rowLen - 1; i >= 0; i--) {
            for (int j = colLen - 1; j >= 0; j--) {
                if (i == rowLen - 1 && j == colLen - 1) {
                    dp[j] = grid[i][j];
                } else if (i == rowLen - 1 && j != colLen - 1) {
                    dp[j] = grid[i][j] + dp[j + 1];
                } else if (i != rowLen - 1 && j == colLen - 1) {
                    dp[j] = grid[i][j] + dp[j];
                } else {
                    dp[j] = grid[i][j] + Math.min(dp[j], dp[j + 1]);
                }
            }
        }

        return dp[0];
    }
}
```
## 解法三
### 思路
直接在二维数组上原地修改
```math
grid[i][j] = grid[i][j] + Math.min(grid[i + 1][j], grid[i][j + 1]
```
### 代码
```java
class Solution {
    public int minPathSum(int[][] grid) {
        int rowLen = grid.length, colLen = grid[0].length;
        for (int i = rowLen - 1; i >= 0; i--) {
            for (int j = colLen - 1; j >= 0; j--) {
                if (i == rowLen - 1 && j != colLen - 1) {
                    grid[i][j] += grid[i][j + 1];
                } else if (i != rowLen - 1 && j == colLen - 1) {
                    grid[i][j] += grid[i + 1][j];
                } else if (i != rowLen - 1 && j != colLen - 1){
                    grid[i][j] += Math.min(grid[i + 1][j], grid[i][j + 1]);
                }
            }
        }

        return grid[0][0];
    }
}
```
# LeetCode_96_不同的二叉搜索树
## 题目
给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？

示例:
```
输入: 3
输出: 5
解释:
给定 n = 3, 一共有 5 种不同结构的二叉搜索树:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
```
## 解法
### 思路
动态规划：
- 状态转移方程：
    - `G(n)`：长度为n的序列的不同二叉搜索树个数
    - `F(i, n)`：以i为根的不同二叉搜索树个数
    - 所以`G(n)`可以由`F(i,n)`累加而得
    - 因为`F(i,n)`等于序列的左右序列部分的笛卡尔积获得，所以`F(i,n) = G(i - 1) * G(n - i)`
    - 例如：F(3,7)就意味着是序列`[1,2]`和`[4,5,6,7]`的笛卡尔积，也就是`F(3,7) = G(2) * G(4)`
- base case：
    - `G(1)`或`G(0)`的结果都是1
### 代码
```java
class Solution {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        
        return dp[n];
    }
}
```
# LeetCode_148_排序链表
## 题目
在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。

示例 1:
```
输入: 4->2->1->3
输出: 1->2->3->4
```
示例 2:
```
输入: -1->5->3->4->0
输出: -1->0->3->4->5
```
## 解法
### 思路
使用归并排序：
- 使用快慢指针找到链表中点，递归将其分割
- 在递归分割后，返回前，对上一层返回的两个排序的子链表，进行合并
- 最终返回排序好的链表返回到上一层
### 代码
```java
class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode tmp = slow.next;
        slow.next = null;
        
        ListNode left = sortList(head);
        ListNode right = sortList(tmp);
        
        ListNode node = new ListNode(0);
        ListNode res = node;
        
        while (left != null && right != null) {
            if (left.val < right.val) {
                node.next = left;
                left = left.next;
            } else {
                node.next = right;
                right = right.next;
            }
            
            node = node.next;
        }
        
        node.next = left == null ? right : left;
        
        return res.next;
    }
}
```
## 解法二
### 思路
解法一的思路是通过拆分步骤将链表拆分成节点这个最小单元，然后通过合并并排序的方式最终返回排序后的结果。每一轮的排序都是对同样长度的链表进行的合并，所以可以直接通过下标标记来替代cut的步骤。而链表的范围通过下标来确定，下标则通过合并轮次来确定。
- 计算链表长度，用来确定是否合并过程结束
- 声明一个res节点，`res.next`指向排序后的链表头节点
- 声明一个int变量代表每轮合并的链表长度`l`,`l`的大小与合并的轮次有关
- 一共3层循环：
    - 最外层：不断增加`l`的大小直到大于等于链表长度`len`，模拟每一轮合并的过程，增大的步长为`l *= 2`
    - 第二层：对每一轮合并的最小单位，`l`长度的链表进行处理
    - 最内层：根据`l`长度找到要处理的两个链表的头节点，处理合并动作:
- 最终返回`res.next`，这样空间复杂度就是O(1)
### 代码
```java
class Solution {
    public ListNode sortList(ListNode head) {
        ListNode node = head;
        int len = 0, l = 1;
        while (node != null) {
            node = node.next;
            len++;
        }

        ListNode res = new ListNode(0);
        res.next = head;
        while (l < len) {
            ListNode pre = res;
            node = res.next;
            while (node != null) {
                ListNode n1 = node;
                int tmpL = l;
                while (node != null && tmpL != 0) {
                    node = node.next;
                    tmpL--;
                }

                if (node == null) {
                    break;
                }

                ListNode n2 = node;
                tmpL = l;
                while (node != null && tmpL != 0) {
                    node = node.next;
                    tmpL--;
                }

                int c1 = l, c2 = l - tmpL;
                while (c1 != 0 && c2 != 0) {
                    if (n1.val < n2.val) {
                        pre.next = n1;
                        n1 = n1.next;
                        c1--;
                    } else {
                        pre.next = n2;
                        n2 = n2.next;
                        c2--;
                    }
                    pre = pre.next;
                }

                pre.next = c1 == 0 ? n2 : n1;
                while (c1 > 0 || c2 > 0) {
                    pre = pre.next;
                    c1--;
                    c2--;
                }

                pre.next = node;
            }

            l *= 2;
        }

        return res.next;
    }
}
```
# LeetCode_406_根据身高重建队列
## 题目
假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。

注意：
```
总人数少于1100人。
```
示例
```
输入:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

输出:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
```
## 解法
### 思路
- 将数组按先高度(第1个元素)(降序)再个数(第2个元素)(升序)的顺序排列
- 然后按照数组的第二个元素重新插入到链表中，从而新建链表
- 最后转换为数组返回
- 思路是：对于每一个人来说，他只要考虑比自己高的人，所以先将高个子且排在前面的人放入队列，从而给后一个人提供满足元素要求的条件。
### 代码
```java
class Solution {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);
        
        List<int[]> list = new LinkedList<>();
        for (int[] arr : people) {
            list.add(arr[1], arr);
        }
        
        return list.toArray(new int[0][0]);
    }
}
```
# LeetCode_120_三角形最小路径和
## 题目
给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。

例如，给定三角形：
```
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
```
说明：
```
如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
```
## 失败解法
### 思路
递归，自底向上返回左右相邻元素的最小值
### 失败原因
超时，重复了多路分支
### 代码
```java
public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        return recurse(triangle, 0, 0, triangle.size());
    }

    private int recurse(List<List<Integer>> triangle, int level, int index, int row) {
        if (level == row - 1) {
            return triangle.get(level).get(index);
        }

        int left = recurse(triangle, level + 1, index, row);
        int right = recurse(triangle, level + 1, index + 1, row);

        return Math.min(left, right) + triangle.get(level).get(index);
    }
}
```
## 解法
### 思路
在失败解法基础上增加记忆化搜索
### 代码
```java
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int row = triangle.size();
        return recurse(triangle, new Integer[row][row], 0, 0, row);
    }

    private int recurse(List<List<Integer>> triangle, Integer[][] memo, int level, int index, int row) {
        if (memo[level][index] != null) {
            return memo[level][index];
        }
        
        if (level == row - 1) {
            return memo[level][index] = triangle.get(level).get(index);
        }
        
        int left = recurse(triangle, memo, level + 1, index, row);
        int right = recurse(triangle, memo, level + 1, index + 1, row);
        
        return memo[level][index] = Math.min(left, right) + triangle.get(level).get(index);
    }
}
```
## 解法二
### 思路
动态规划：
- base case：最底部一行`dp[level][index] = list.get(level).get(index)`
- 状态转移方程：`dp[level][index] = Math.min(dp[level + 1][index], dp[level + 1][index + 1]) + list.get(level).get(index)`
### 代码
```java
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int row = triangle.size();
        int[][] dp = new int[row][row];
        
        for (int level = row - 1; level >= 0; level--) {
            for (int index = 0; index < triangle.get(level).size(); index++) {
                if (level == row - 1) {
                    dp[level][index] = triangle.get(level).get(index);
                } else {
                    dp[level][index] = Math.min(dp[level + 1][ index], dp[level + 1][index + 1]) + triangle.get(level).get(index);
                }
            }
        }
        
        return dp[0][0];
    }
}
```
# LeetCode_144_二叉树的前序遍历
## 题目
给定一个二叉树，返回它的 前序 遍历。

示例:
```
输入: [1,null,2,3]  
   1
    \
     2
    /
   3 

输出: [1,2,3]
```
```
进阶: 递归算法很简单，你可以通过迭代算法完成吗？
```
## 解法
### 思路
dfs前序遍历
### 代码
```java
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        dfs(ans, root);
        return ans;
    }
    
    private void dfs(List<Integer> list, TreeNode node) {
        if (node == null) {
            return;
        }
        
        list.add(node.val);
        dfs(list, node.left);
        dfs(list, node.right);
    }
}
```
## 解法二
### 思路
迭代方式的先序深度优先遍历
### 代码
```java
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            ans.add(node.val);
            
            if (node.right != null) {
                stack.push(node.right);
            }
            
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        
        return ans;
    }
}
```