# [LeetCode_1305_两棵二叉搜索树中的所有元素](https://leetcode-cn.com/problems/all-elements-in-two-binary-search-trees/)
## 解法
### 思路
- 分别通过中序遍历生成2个BST的生序序列
- 然后通过双指针生成整体的升序序列并返回
### 代码
```java
class Solution {
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>(),
                list2 = new ArrayList<>();

        dfs(root1, list1);
        dfs(root2, list2);

        List<Integer> ans = new ArrayList<>();
        int i1 = 0, i2 = 0;
        while (i1 < list1.size() || i2 < list2.size()) {
            if (i1 >= list1.size()) {
                ans.add(list2.get(i2++));
            } else if (i2 >= list2.size()) {
                ans.add(list1.get(i1++));
            } else {
                int val1 = list1.get(i1), val2 = list2.get(i2);
                if (val1 < val2) {
                    ans.add(val1);
                    i1++;
                } else {
                    ans.add(val2);
                    i2++;
                }
            }
        }

        return ans;
    }

    private void dfs(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        dfs(node.left, list);
        list.add(node.val);
        dfs(node.right, list);
    }
}
```