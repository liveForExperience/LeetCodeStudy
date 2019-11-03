# LeetCode_1110_删点成林
## 题目
给出二叉树的根节点 root，树上每个节点都有一个不同的值。

如果节点值在 to_delete 中出现，我们就把该节点从树上删去，最后得到一个森林（一些不相交的树构成的集合）。

返回森林中的每棵树。你可以按任意顺序组织答案。

示例：
```
输入：root = [1,2,3,4,5,6,7], to_delete = [3,5]
输出：[[1,2,null,4],[6],[7]]
```
提示：
```
树中的节点数最大为 1000。
每个节点都有一个介于 1 到 1000 之间的值，且各不相同。
to_delete.length <= 1000
to_delete 包含一些从 1 到 1000、各不相同的值。
```
## 解法
### 思路
- 把to_delete数组转换成set，方便查找
- dfs遍历二叉树
    - 如果节点为null，返回null
    - 否则就继续递归搜索，并在当前层用左右子树指针接收递归返回的结果
    - 这里必须是后序遍历，先递归到叶子节点再开始判断，否则子树的一些需要删除的节点就没法被删除了，结果会有问题
    - 如果节点值为to_delete中的值，返回null，并将左右非空子树作为根节点，放入结果list中
- 最后返回结果list，但返回前需要判断根节点是否被删除，如果返回是null，必需过滤掉
### 代码
```java
class Solution {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> ans = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (Integer val : to_delete) {
            set.add(val);
        }
        
        TreeNode node = dfs(root, set, ans);
        if (node != null) {
            ans.add(node);
        }
        
        return ans;
    }

    private TreeNode dfs(TreeNode node, Set<Integer> toDelete, List<TreeNode> ans) {
        if (node == null) {
            return null;
        }

        node.left = dfs(node.left, toDelete, ans);
        node.right = dfs(node.right, toDelete, ans);

        if (toDelete.contains(node.val)) {
            if (node.left != null) {
                ans.add(node.left);
            }

            if (node.right != null) {
                ans.add(node.right);
            }

            return null;
        }
        return node;
    }
}
```