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