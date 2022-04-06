# [LeetCode_307_区域和检索-数组可修改]()
## 解法
### 思路
线段树
### 代码
```java
class NumArray {
    private int n;
    private int[] tree;
    public NumArray(int[] nums) {
        this.n = nums.length;
        this.tree = new int[2 * n];
        for (int i = 0, j = n; j < 2 * n; j++, i++) {
            tree[j] = nums[i];
        }

        for (int i = n - 1; i > 0; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    public void update(int index, int val) {
        int i = index + n;
        tree[i] =val;
        while (i > 0) {
            int l = i % 2 == 0 ? i : i - 1;
            int r = i % 2 == 0 ? i + 1 : i;

            i /= 2;
            tree[i] = tree[l] + tree[r];
        }
    }

    public int sumRange(int left, int right) {
        left += n;
        right += n;
        int sum = 0;
        while (left <= right) {
            if (left % 2 == 1) {
                sum += tree[left++];
            }

            if (right % 2 == 0) {
                sum += tree[right--];
            }

            left /= 2;
            right /= 2;
        }

        return sum;
    }
}
```
# [LeetCode_666_路径总和IV](https://leetcode-cn.com/problems/path-sum-iv/)
## 解法
### 思路
构建二叉树并dfs
### 代码
```java
class Solution {
    private int ans = 0;

    public int pathSum(int[] nums) {
        int maxDepth = nums[nums.length - 1] / 100;
        List<TreeNode[]> list = new ArrayList<>();
        for (int i = 0; i < maxDepth; i++) {
            list.add(new TreeNode[1 << i]);
        }
        
        for (int num : nums) {
            int depth = num / 100, pos = (num % 100) / 10, val = num % 10;
            TreeNode node = new TreeNode(val);
            list.get(depth - 1)[pos - 1] = node;
            if (depth - 1 > 0) {
                if (pos % 2 == 1) {
                    list.get(depth - 2)[(pos - 1) / 2].left = node;
                } else {
                    list.get(depth - 2)[(pos - 1) / 2].right = node;
                }
            }
        }

        dfs(list.get(0)[0], 0);
        return ans;
    }

    private void dfs(TreeNode node, int sum) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            ans += node.val + sum;
        }

        dfs(node.left, sum + node.val);
        dfs(node.right, sum + node.val);
    }

    private class TreeNode {
        private int val;
        private TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
```