# [LeetCode_993_二叉树的堂兄弟节点](https://leetcode-cn.com/problems/cousins-in-binary-tree/)
## 解法
### 思路
dfs
- 分别对两个值做dfs搜索，找到后记录父节点和深度
- 最终比较记录的结果
### 代码
```java
class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
        Note noteX = new Note(), noteY = new Note();
        dfs(null, root, x, 0, noteX);
        dfs(null, root, y, 0, noteY);
        return noteX.parent != noteY.parent && noteX.level == noteY.level; 
    }
    
    private void dfs(TreeNode parent, TreeNode node, int target, int level, Note note) {
        if (node == null) {
            return;
        }
        
        if (node.val == target) {
            note.level = level;
            note.parent = parent;
            return;
        }
        
        dfs(node, node.left, target, level + 1, note);
        dfs(node, node.right, target, level + 1, note);
    }
    
    class Note {
        private TreeNode parent;
        private int level;
    }
}
```
## 解法二
### 思路
分别dfs计算2个节点的深度，和是不是兄弟节点，然后做判断
### 代码
```java
class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
        return deep(root, x) == deep(root, y) && !isBrother(root, x, y); 
    }
    
    private int deep(TreeNode node, int target) {
        if (node == null) {
            return -99999;
        }
        
        if (node.val == target) {
            return 0;
        }
        
        return Math.max(deep(node.left, target), deep(node.right, target)) + 1;
    }
    
    private boolean isBrother(TreeNode node, int x, int y) {
        if (node == null) {
            return false;
        }
        
        if (node.left != null && node.right != null) {
            if ((node.left.val == x && node.right.val == y) || (node.right.val == x && node.left.val == y)) {
                return true;
            }
        }
        
        return isBrother(node.left, x, y) || isBrother(node.right, x, y);
    }
}
```
# [LeetCode_1442_形成两个异或相等数组的三元组数目](https://leetcode-cn.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/)
## 解法
### 思路
- 模仿前缀和求出累计异或的值，存储在数组xor中
- 实际要求出的两个子数组的异或和，通过运算可以获得公式：
    - `xor[i] = arr[0] ^ arr[1] ^ ... arr[i]`
    - `xor[j] = arr[0] ^ arr[1] ^ ... arr[i] ^ arr[i + 1] ^ ... arr[j]`
    - `arr[i] ^ ... arr[j - 1] = xor[i - 1] ^ xor[j - 1]`
    - `arr[j] ^ ... arr[k] = xor[j - 1] ^ xor[k]`
    - 为了达成左右元素相等，等式可以表达为：`xor[i - 1] ^ xor[j - 1] = xor[j - 1] ^ xor[k]`
    - 通过计算就可以获得`xor[i - 1] = xor[k]`
- 3层循环，分别确定i，j，k
- 然后判断`xor[i - 1] == xor[k]`
- 为了方便计算，让xor数组整体右移一位，所以判断的公式可以为`xor[j] == xor[k + 1]`
### 代码
```java
class Solution {
    public int countTriplets(int[] arr) {
        int[] xor = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            xor[i + 1] = xor[i] ^ arr[i];
        }

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                for (int k = j; k < arr.length; k++) {
                    if (xor[i] == xor[k + 1]) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}
```
## 解法二
### 思路
- 实际并不需要使用到j，所以可以将解法1的3重循环降低为2重
- 但计算个数的时候需要注意，因为i和k + 1符合要求了，所以中间任意的j都是ok的，那么个数就应该是k-i
### 代码
```java
class Solution {
    public int countTriplets(int[] arr) {
        int len = arr.length;
        int[] xor = new int[len + 1];
        for (int i = 0; i < arr.length; i++) {
            xor[i + 1] = xor[i] ^ arr[i];
        }
        
        int count = 0;
        for (int i = 0; i < len; i++) {
            for (int k = i + 1; k < len; k++) {
                if (xor[i] == xor[k + 1]) {
                    count += k - i;   
                }
            }
        }
        
        return count;
    }
}
```