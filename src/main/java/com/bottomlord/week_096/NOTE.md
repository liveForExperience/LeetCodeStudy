# [LeetCode_872_叶子相似的树](https://leetcode-cn.com/problems/leaf-similar-trees/)
## 解法
### 思路
- 分别dfs两棵树，搜索收集从左到右的叶子
- 然后比较搜集到的集合是否相等
### 代码
```java
class Solution {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>(), list2 = new ArrayList<>();
        dfs(root1, list1);
        dfs(root2, list2);
        if (list1.size() != list2.size()) {
            return false;
        }

        for (int i = 0; i < list1.size(); i++) {
            if (!Objects.equals(list1.get(i), list2.get(i))) {
                return false;
            }
        }
        
        return true;
    }
    
    private void dfs(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        
        if (node.left == null && node.right == null) {
            list.add(node.val);
        }
        
        dfs(node.left, list);
        dfs(node.right, list);
    }
}
```