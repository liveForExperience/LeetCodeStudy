# [LeetCode_749_隔离病毒](https://leetcode.cn/problems/contain-virus/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_655_输出二叉树](https://leetcode.cn/problems/print-binary-tree/)
## 解法
### 思路
dfs
- dfs获取二叉树深度
- 根据题目要求，得到m和n的值
- 初始化二维list
- dfs搜索并在list中填值
- dfs结束返回
### 代码
```java
class Solution {
    public List<List<String>> printTree(TreeNode root) {
        int m = depth(root), height = m - 1,  n = (1 << m) - 1;
        List<List<String>> ans = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<String> list = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                list.add("");
            }
            ans.add(list);
        }

        print(root, ans, 0, (n - 1) / 2, height);
        return ans;
    }

    private int depth(TreeNode node) {
        if (node == null) {
            return 0;
        }

        return Math.max(depth(node.left), depth(node.right)) + 1;
    }

    private void print(TreeNode node, List<List<String>> ans, int r, int c, int height) {
        if (node == null) {
            return;
        }

        ans.get(r).set(c, "" + node.val);
        print(node.left, ans, r + 1, c - (1 << (height - r - 1)), height);
        print(node.right, ans, r + 1, c + (1 << (height - r - 1)), height);
    }
}
```
