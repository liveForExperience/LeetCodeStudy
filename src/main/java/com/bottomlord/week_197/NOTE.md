# [LeetCode_1026_节点与其祖先之间的最大差值](https://leetcode.cn/problems/maximum-difference-between-node-and-ancestor/)
## 解法
### 思路
dfs
- 返回值使用一个数组，记录子树中的最大和最小值
- 和当前节点值做比较
- 退出条件是节点为空，为空时候，做一下特殊处理，比如最大最小值都设置成int最大值
### 代码
```java
class Solution {
    private int max;

    public int maxAncestorDiff(TreeNode root) {
        this.max = 0;
        dfs(root);
        return max;
    }

    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        }

        int val = node.val;
        int[] left = dfs(node.left), right = dfs(node.right);

        if (left[0] == Integer.MAX_VALUE) {
            left[0] = val;
        }
        
        if (left[1] == Integer.MAX_VALUE) {
            left[1] = val;
        }

        if (right[0] == Integer.MAX_VALUE) {
            right[0] = val;
        }

        if (right[1] == Integer.MAX_VALUE) {
            right[1] = val;
        }
        
        int min = Math.min(left[0], right[0]), max = Math.max(left[1], right[1]);
        this.max = Math.max(this.max, Math.max(Math.abs(val - min), Math.abs(val - max)));
        return new int[]{Math.min(min, val), Math.max(max, val)};
    }
}
```