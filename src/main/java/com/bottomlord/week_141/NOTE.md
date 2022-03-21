# [LeetCode_653_两数之和IV-输入BST](https://leetcode-cn.com/problems/two-sum-iv-input-is-a-bst/)
## 解法
### 思路
dfs
- dfs中序遍历生成升序序列
- 双指针寻找target
- 找到就返回true，否则返回false
### 代码
```java
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);

        int head = 0, tail = list.size() - 1;
        while (head < tail) {
            int sum = list.get(head) + list.get(tail);
            if (sum > k) {
                tail--;
            } else if (sum < k) {
                head++;
            } else {
                return true;
            }
        }

        return false;
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
## 解法二
### 思路
dfs
- 2层dfs
  - 第一层用于确定第一个加数
  - 第二层从根节点开始寻找k - val(第一层的node值)是否存在，如果存在就返回true
- 需要注意，因为是bst，所以不存在val * 2 = target的情况，所以需要把这种情况剔除掉
### 代码
```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        return dfs(root, k, root);
    }

    private boolean dfs(TreeNode node, int target, TreeNode root) {
        if (node == null) {
            return false;
        }
        
        int val = node.val;
        if (val * 2 != target && search(root, target - val)) {
            return true;
        }
        
        return dfs(node.left, target, root) || dfs(node.right, target, root); 
    }
    
    private boolean search(TreeNode node, int target) {
        if (node == null) {
            return false;
        }
        
        int val = node.val;
        if (val == target) {
            return true;
        }
        
        return val < target ? search(node.right, target) : search(node.left, target);
    }
}
```
# [LeetCode_625_最小因式分解](https://leetcode-cn.com/problems/minimum-factorization/)
## 解法
### 思路
模拟
- 注意溢出，需要先用long声明进行计算，最后和int的极值进行比较后再判断是返回0还是强转为int
### 代码
```java
class Solution {
  public int smallestFactorization(int num) {
    long ans = 0, index = 0, ten = 1;
    while (num >= 10) {
      long cur = 0;
      for (int i = 9; i > 1; i--) {
        if (num % i == 0) {
          cur = i;
          break;
        }
      }

      if (cur == 0) {
        return 0;
      }

      ten *= index == 0 ? 1 : 10;
      ans += index == 0 ? cur : ten * cur;
      index++;
      num /= cur;
    }

    ans += index == 0 ? num : ten * 10 * num;

    return ans > Integer.MAX_VALUE || ans < Integer.MIN_VALUE ? 0 : (int) ans;
  }
}
```