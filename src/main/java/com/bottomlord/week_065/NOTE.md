# LeetCode_285_二叉搜索树中的顺序后继
## 题目
给你一个二叉搜索树和其中的某一个结点，请你找出该结点在树中顺序后继的节点。

结点 p 的后继是值比 p.val 大的结点中键值最小的结点。

示例 1:
```
输入: root = [2,1,3], p = 1
输出: 2
解析: 这里 1 的顺序后继是 2。请注意 p 和返回值都应是 TreeNode 类型。
```
示例 2:
```
输入: root = [5,3,6,2,4,null,null,1], p = 6
输出: null
解析: 因为给出的结点没有顺序后继，所以答案就返回 null 了。
```
注意:
```
假如给出的结点在该树中没有顺序后继的话，请返回 null
我们保证树中每个结点的值是唯一的
```
## 解法
### 思路
中序dfs：
- 发现第一个比p大的节点，记录并返回
### 代码
```java
class Solution {
    private TreeNode ans = null;
    private boolean find = false;
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        dfs(root, p);
        return ans;
    }

    private void dfs(TreeNode node, TreeNode p) {
        if (node == null || find) {
            return;
        }
        
        dfs(node.left, p);
        
        if (node.val > p.val && !find) {
            ans = node;
            find = true;
        }
        
        dfs(node.right, p);
    }
}
```