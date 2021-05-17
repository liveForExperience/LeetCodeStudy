# [LeetCode_993_二叉树的堂兄弟节点](https://leetcode-cn.com/problems/cousins-in-binary-tree/)
## 解法
### 思路
dfs
- 分别对两个值做dfs搜索，找到后记录父节点和深度
- 最终比较记录的结果
### 代码
```java
class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
        Note noteX = new Note(), noteY = new Note();
        dfs(null, root, x, 0, noteX);
        dfs(null, root, y, 0, noteY);
        return noteX.parent != noteY.parent && noteX.level == noteY.level; 
    }
    
    private void dfs(TreeNode parent, TreeNode node, int target, int level, Note note) {
        if (node == null) {
            return;
        }
        
        if (node.val == target) {
            note.level = level;
            note.parent = parent;
            return;
        }
        
        dfs(node, node.left, target, level + 1, note);
        dfs(node, node.right, target, level + 1, note);
    }
    
    class Note {
        private TreeNode parent;
        private int level;
    }
}
```
## 解法二
### 思路
分别dfs计算2个节点的深度，和是不是兄弟节点，然后做判断
### 代码
```java
class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
        return deep(root, x) == deep(root, y) && !isBrother(root, x, y); 
    }
    
    private int deep(TreeNode node, int target) {
        if (node == null) {
            return -99999;
        }
        
        if (node.val == target) {
            return 0;
        }
        
        return Math.max(deep(node.left, target), deep(node.right, target)) + 1;
    }
    
    private boolean isBrother(TreeNode node, int x, int y) {
        if (node == null) {
            return false;
        }
        
        if (node.left != null && node.right != null) {
            if ((node.left.val == x && node.right.val == y) || (node.right.val == x && node.left.val == y)) {
                return true;
            }
        }
        
        return isBrother(node.left, x, y) || isBrother(node.right, x, y);
    }
}
```