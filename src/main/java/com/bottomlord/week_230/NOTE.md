# [LeetCode_2336_无限集中的最小数字](https://leetcode.cn/problems/smallest-number-in-infinite-set)
## 解法
### 思路
使用TreeSet
### 代码
```java

```
# [LeetCode_1038_从二叉搜索树到更大和树](https://leetcode.cn/problems/binary-search-tree-to-greater-sum-tree)
## 解法
### 思路
- 思考过程：
  - 基于二叉搜索树中序遍历得到的是升序序列的特性，对BST做中序遍历，并生成升序序列，序列中存储节点的引用
  - 基于升序序列再生成一个前缀和序列
  - 然后就可以基于前缀和序列得到更大和树了
- 算法过程：
  - 中序dfs遍历BST，生成节点引用列表`list`
  - 遍历`list`生成前缀和数组`sums`
  - 遍历`list`，基于引用变更其value为对应的前缀和
### 代码
```java
class Solution {
    public TreeNode bstToGst(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        dfs(root, list);
        if (list.isEmpty()) {
            return root;
        }

        int[] sums = new int[list.size() + 1];
        for (int i = 1; i < sums.length; i++) {
            sums[i] = sums[i - 1] + list.get(i - 1).val;
        }

        for (int i = 0; i < list.size(); i++) {
            list.get(i).val = sums[list.size()] - sums[i];
        }
        
        return root;
    }

    private void dfs(TreeNode node, List<TreeNode> list) {
        if (node == null) {
            return;
        }

        dfs(node.left, list);
        list.add(node);
        dfs(node.right, list);
    }
}
```