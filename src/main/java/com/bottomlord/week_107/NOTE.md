# [LeetCode_1713_得到子序列的最少操作次数](https://leetcode-cn.com/problems/minimum-operations-to-make-a-subsequence/)
## 解法
### 思路
- 如果target数组的长度n，arr数组的长度是m，那么m减去他们的最长公共子序列，就是最少的操作次数
- 然后将arr中的元素对应到target数组的坐标上，并将不在target数组中的坐标去除
- 同时将target数组也转换成坐标数组，同时会发现新生成的数组是单调递增的，此时就可以最长递增子序列长度
- 在确定最长递增子序列的时候，参考[题解](https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/yi-ti-shuang-jie-tu-jie-yuan-li-ji-chu-d-ptpz/)
    - 在确定序列的时候，如果当前元素比候选序列中的元素都大，那么就直接增加候选序列长度
    - 如果比序列最大值小，就找到序列中比当前值大的最小值，做替换，以确保后续元素在判断时保留更大的增长可能性
### 代码
```java
class Solution {
public int minOperations(int[] target, int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < target.length; i++) {
            map.put(target[i], i);
        }

        List<Integer> ans = new ArrayList<>();
        for (int num : arr) {
            if (map.containsKey(num)) {
                int index = map.get(num);
                int i = binarySearch(ans, index);
                if (i == ans.size()) {
                    ans.add(index);
                } else {
                    ans.set(i, index);
                }
            }
        }

        return target.length - ans.size();
    }

    private int binarySearch(List<Integer> ans, int num) {
        int size = ans.size();
        if (size == 0 || num > ans.get(size - 1)) {
            return size;
        }

        int head = 0, tail = size - 1;
        while (head < tail) {
            int mid = head + (tail - head) / 2;
            if (ans.get(mid) < num) {
                head = mid + 1;
            } else {
                tail = mid;
            }
        }

        return head;
    }
}
```
# [LeetCode_671_二叉树中的二小的节点](https://leetcode-cn.com/problems/second-minimum-node-in-a-binary-tree/)
## 错误解法
### 原因
测试用例中包含最大值的情况
### 思路
dfs，遇到根节点值，则转换为最大值返回
### 代码
```java
class Solution {
    public int findSecondMinimumValue(TreeNode root) {
        int val = dfs(root, root.val);
        return val == Integer.MAX_VALUE ? -1 : val;
    }
    
    private int dfs(TreeNode node, int min) {
        if (node == null) {
            return Integer.MAX_VALUE;
        }
        
        if (node.left == null && node.right == null) {
            return node.val == min ? Integer.MAX_VALUE : node.val;
        }
        
        return Math.min(dfs(node.left, min), dfs(node.right, min));
    }
}
```
## 解法
### 思路
- 因为二叉树树的根节点是该树所有节点的最小值：
  - 根节点的左右子树中和根节点值不一样的那个节点，对应的子树里的最小值就是该节点，这个节点是又可能为最小值的，但不能确定那个节点一定是，因为可能与根节点值一样的那个节点的子树里，存在比另一个子树中最小值更小的情况
  - 所以需要去搜索和根节点值一样的节点对应的子树
  - 搜索的时候，就一直去搜索去当前节点值一样的那个节点对应的子树，因为另一个节点已经确保了是子树的最小值，可以在搜索返回的时候进行比较判断
  - 当搜索到节点为叶子节点的时候，说明这个节点就是和根节点一样的元素，直接就返回-1
  - 返回之后，就依据-1来判断：
    - 如果左右子树都不是-1，这种情况出现在，与根节点值相同的节点对应子树中找到了第二小的值，然后第二小值与另一边的最小值做比较
    - 如果左子树或者右子树是-1，代表找到了整棵树的最小值，然后就直接返回另一个值，这个值相当于当前找到的第二小值
### 代码
```java
class Solution {
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) {
            return -1;
        }
        
        if (root.left == null && root.right == null) {
            return -1;
        }
        
        int val = root.val, left = root.left.val, right = root.right.val;
        
        if (left == val) {
            left = findSecondMinimumValue(root.left);   
        }
        
        if (right == val) {
            right = findSecondMinimumValue(root.right);
        }
        
        if (left != -1 && right != -1) {
            return Math.min(left, right);
        }
        
        if (left == -1) {
            return right;
        }
        
        return left;
    }
}
```