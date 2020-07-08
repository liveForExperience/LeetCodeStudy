# LeetCode_98_验证二叉搜索树
## 题目
给定一个二叉树，判断其是否是一个有效的二叉搜索树。

假设一个二叉搜索树具有如下特征：
```
节点的左子树只包含小于当前节点的数。
节点的右子树只包含大于当前节点的数。
所有左子树和右子树自身必须也是二叉搜索树。
```
示例 1:
```
输入:
    2
   / \
  1   3
输出: true
```
示例 2:
```
输入:
    5
   / \
  1   4
     / \
    3   6
输出: false
解释: 输入为: [5,1,4,null,null,3,6]。
     根节点的值为 5 ，但是其右子节点值为 4 。
```
## 解法
### 思路
- 中序遍历
- 暂存节点值
- 遍历节点值，判断是否升序
### 代码
```java
class Solution {
    public boolean isValidBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) <= list.get(i - 1)) {
                return false;
            }
        }
        
        return true;
    }
    
    private void inorder(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        
        inorder(node.left, list);
        list.add(node.val);
        inorder(node.right, list);
    }
}
```
## 解法二
### 思路
dfs
- 参数：
    - 当前层节点
    - 上一层确定的最大值
    - 上一层确定的最小值
- 递归：
    - 退出条件：当前节点为空，返回true
    - 过程：
        - 如果当前节点不比上一层确定的最小值大，返回false
        - 如果当前节点不比上一层确定的最大值小，返回false
        - 向左子树递归时，当前值作为最大值
        - 向右子树递归时，当前值作为最小值
### 代码
```java
class Solution {
    public boolean isValidBST(TreeNode root) {
        return dfs(root, null, null);
    }
    
    private boolean dfs(TreeNode node, Integer min, Integer max) {
        if (node == null) {
            return true;
        }
        
        int val = node.val;
        if (min != null && val <= min) {
            return false;
        }
        
        if (max != null && val >= max) {
            return false;
        }
        
        if (!dfs(node.left, min, val)) {
            return false;
        }
        
        if (!dfs(node.right, val, max)) {
            return false;
        }
        
        return true;
    }
}
```
# LeetCode_99_恢复二叉搜索树
## 题目
给定一个二叉树，判断其是否是一个有效的二叉搜索树。

假设一个二叉搜索树具有如下特征：
```
节点的左子树只包含小于当前节点的数。
节点的右子树只包含大于当前节点的数。
所有左子树和右子树自身必须也是二叉搜索树。
```
示例 1:
```
输入:
    2
   / \
  1   3
输出: true
```
示例 2:
```
输入:
    5
   / \
  1   4
     / \
    3   6
输出: false
解释: 输入为: [5,1,4,null,null,3,6]。
     根节点的值为 5 ，但是其右子节点值为 4 。
```
## 解法
### 思路
- 中序遍历
- 生成存储节点引用的list
- 从后向前遍历list，找到第一个不符合升序的节点
- 从前往后遍历list，找到第一个不符合升序的节点
- 将这两个节点的值互换
### 代码
```java
class Solution {
    public void recoverTree(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        inorder(root, list);
        
        TreeNode node1 = list.get(list.size() - 1);
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).val > list.get(i + 1).val) {
                node1 = list.get(i);
                break;
            }
        }
        
        TreeNode node2 = list.get(0);
        for (int i = list.size() - 1; i > 0; i--) {
            if (list.get(i).val < list.get(i - 1).val) {
                node2 = list.get(i);
                break;
            }
        }
        
        int tmp = node1.val;
        node1.val = node2.val;
        node2.val = tmp;
    }
    
    private void inorder(TreeNode node, List<TreeNode> list) {
        if (node == null) {
            return;
        }
        
        inorder(node.left, list);
        list.add(node);
        inorder(node.right, list);
    }
}
```
# LeetCode_115_不同的子序列
## 题目
给定一个字符串 S 和一个字符串 T，计算在 S 的子序列中 T 出现的个数。

一个字符串的一个子序列是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）

题目数据保证答案符合 32 位带符号整数范围。

示例 1：
```
输入：S = "rabbbit", T = "rabbit"
输出：3
解释：

如下图所示, 有 3 种可以从 S 中得到 "rabbit" 的方案。
(上箭头符号 ^ 表示选取的字母)

rabbbit
^^^^ ^^
rabbbit
^^ ^^^^
rabbbit
^^^ ^^^
```
示例 2：
```
输入：S = "babgbag", T = "bag"
输出：5

解释：

如下图所示, 有 5 种可以从 S 中得到 "bag" 的方案。 
(上箭头符号 ^ 表示选取的字母)

babgbag
^^ ^
babgbag
^^    ^
babgbag
^    ^^
babgbag
  ^  ^^
babgbag
    ^^^
```
## 失败解法
###
超时
### 思路
回溯
### 代码
```java
class Solution {
    public int numDistinct(String s, String t) {
        return backTrack(s, 0, t, 0);
    }

    private int backTrack(String s, int si, String t, int ti) {
        if (si == s.length() && ti != t.length()) {
            return 0;
        }

        if (ti == t.length()) {
            return 1;
        }

        int count = 0;
        for (int i = si; i < s.length(); i++) {
            if (s.charAt(i) == t.charAt(ti)) {
                count += backTrack(s, i + 1, t, ti + 1);
            }
        }

        return count;
    }
}
```
## 解法
### 题目
回溯+记忆化搜索
### 代码
```java
class Solution {
    public int numDistinct(String s, String t) {
        int[][] memo = new int[s.length()][t.length()];
        for (int[] arr : memo) {
            Arrays.fill(arr, -1);
        }
        return backTrack(s, 0, t, 0, memo);
    }

    private int backTrack(String s, int si, String t, int ti, int[][] memo) {
        if (si == s.length() && ti != t.length()) {
            return 0;
        }

        if (ti == t.length()) {
            return 1;
        }

        if (memo[si][ti] != -1) {
            return memo[si][ti];
        }

        int count = 0;
        for (int i = si; i < s.length(); i++) {
            if (s.charAt(i) == t.charAt(ti)) {
                count += backTrack(s, i + 1, t, ti + 1, memo);
            }
        }

        memo[si][ti] = count;
        return count;
    }
}
```
## 解法二
### 思路
动态规划：
- `dp[i][j]`：T的前i个字符组成的字符串，在S的前j个字符组成的字符串中，能够得到的最大个数
- 初始化：当i为0，也就是T为空字符串时，所有S的长度对应的dp值都是1
- 状态转移方程：
    - `T[i] != S[j]`：当前字符不相等，说明S[j]没有被用到，所以当前dp值与`dp[i][j - 1]`是一致的
    - `T[i] == S[j]`：当前字符相等，所以分成两种状态：
        - `S[j]`被用到了，那么它的值和后面情况相等：`S[j]`不存在且`T[i]`也不需要匹配，这种情况获得的所有可能，在T用到`T[i]`后，`T[i]`代表的值就都会只和`S[j]`匹配了
        - `S[j]`没有被用到，那么它的情况和`T[i] != S[j]`的情况相同
    - 所以状态转移方程就是如下两个方程：
        - `T[i] != S[j]`：`dp[i][j] = dp[i][j - 1]` 
        - `T[i] == S[j]`：`dp[i][j] = dp[i][j - 1] + dp[i - 1][j - 1]` 
### 代码
```java
class Solution {
    public int numDistinct(String s, String t) {
        int sl = s.length(), tl = t.length();
        int[][] dp = new int[tl + 1][sl + 1];
        for (int i = 0; i <= sl; i++) {
            dp[0][i] = 1;
        }
        
        for (int i = 1; i <= tl; i++) {
            for (int j = 1; j <= sl; j++) {
                if (t.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        
        return dp[tl][sl];
    }
}
```
# LeetCode_117_填充每个节点的下一个右侧节点指针II
## 题目
给定一个二叉树
```
struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
```
填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。

初始状态下，所有 next 指针都被设置为 NULL。

进阶：
```
你只能使用常量级额外空间。
使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
```
示例：
```
输入：root = [1,2,3,4,5,null,7]
输出：[1,#,2,3,#,4,5,7,#]
解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
```
## 解法
### 思路
bfs
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
            int num = queue.size();
            Node pre = null;
            while (num-- > 0) {
                Node node = queue.poll();
                if (node == null) {
                    continue;
                }

                if (pre != null) {
                    pre.next = node;
                }
                
                pre = node;

                if (node.left != null) {
                    queue.offer(node.left);
                }
                
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        
        return root;
    }
}
```