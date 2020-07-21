# LeetCode_312_戳气球
## 题目
有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组 nums 中。

现在要求你戳破所有的气球。如果你戳破气球 i ，就可以获得 nums[left] * nums[i] * nums[right] 个硬币。 这里的 left 和 right 代表和 i 相邻的两个气球的序号。注意当你戳破了气球 i 后，气球 left 和气球 right 就变成了相邻的气球。

求所能获得硬币的最大数量。

说明:
```
你可以假设 nums[-1] = nums[n] = 1，但注意它们不是真实存在的所以并不能被戳破。
0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
```
示例:
```
输入: [3,1,5,8]
输出: 167 
解释: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
     coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
```
## 解法
### 思路
分治+记忆化
- 定义变量：
    - `val[]`：构建一个开区间(i,j)，i最小为`0`，j最大为`n+1`，而`[1,n]`的值就是`nums`，且根据题目提示，头尾坐标可以表示为0
    - `memo[i][j]`：记忆化缓存，初始化为-1，作为没有计算过的标记。i和j代表value开区间的头尾坐标
- 过程：
    - 退出条件：
        - 如果头尾坐标相等，代表开区间中已经没有元素需要计算
        - 如果memo中的值不为-1，直接返回当前值
    - 遍历当前头尾坐标中所有可能的坐标`mid`，计算这个`mid`构成的乘积
    - 利用`mid`将当前层的区间切分成2个，继续递归
    - 左右区间递归返回的结果,和当前层的值相加，这个值再与memo的结果进行比较，取最大值
    - 最后返回当前层最大值
### 代码
```java
class Solution {
    public int maxCoins(int[] nums) {
        int len = nums.length;
        int[] val = new int[len + 2];
        for (int i = 1; i <= len; i++) {
            val[i] = nums[i -  1];
        }
        val[0] = val[len + 1] = 1;

        int[][] memo = new int[len + 2][len + 2];
        for (int[] arr : memo) {
            Arrays.fill(arr, -1);
        }

        return recurse(val, 0, len + 1, memo);
    }

    private int recurse(int[] val, int head, int tail, int[][] memo) {
        if (head + 1 >= tail) {
            return 0;
        }

        if (memo[head][tail] != -1) {
            return memo[head][tail];
        }
        
        for (int mid = head + 1; mid < tail; mid++) {
            int sum = val[mid] * val[head] * val[tail];
            sum += recurse(val, head, mid, memo) + recurse(val, mid, tail, memo);
            memo[head][tail] = Math.max(sum, memo[head][tail]);
        }
        
        return memo[head][tail];
    }
}
```
## 解法二
### 思路
动态规划：
- `dp[i][j]`：开区间(i,j)范围内能够获得的最大值
- 初始化：0
- 状态转移方程：`dp[i][j] = val[i] * val[k] * val[j] + dp[i,k] + dp[k][j]`
- 返回结果：`dp[0][n + 1]`
- 三层循环：
    - 外层确定起始i
    - 中间层确定结尾j
    - 内层确定mid值
### 代码
```java
class Solution {
    public int maxCoins(int[] nums) {
        int len = nums.length;
        int[] val = new int[len + 2];
        val[0] = val[len + 1] = 1;
        for (int i = 1; i<= len; i++) {
            val[i] = nums[i - 1];
        }
            
        int[][] dp = new int[len + 2][len + 2];
        
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i + 2; j <= len + 1; j++) {
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], val[i] * val[k] * val[j] + dp[i][k] + dp[k][j]);
                }
            }
        }
        
        return dp[0][len + 1];
    }
}
```
# LeetCode_145_二叉树的后序遍历
## 题目
给定一个二叉树，返回它的 后序 遍历。

示例:
```
输入: [1,null,2,3]  
   1
    \
     2
    /
   3 

输出: [3,2,1]
```
```
进阶: 递归算法很简单，你可以通过迭代算法完成吗？
```
## 解法
### 思路
dfs后序遍历
### 代码
```java
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        return list;
    }
    
    private void dfs(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        
        dfs(node.left, list);
        dfs(node.right, list);
        list.add(node.val);
    }
}
```
## 解法二
### 思路
使用栈进行迭代：
- 使用2个栈
- 一个栈`stack`用来驱动搜索
- 一个栈`temp`用来记录搜索过的节点
- 每一次循环从`stack`取出的节点就压入`temp`，因为栈是先进后出，所以正好匹配了后序的顺序
- `stack`迭代结束后，循环弹出`temp`就能获得后序打印的结果
### 代码
```java
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        
        Stack<TreeNode> stack = new Stack<>(), temp = new Stack<>();
        List<Integer> ans = new ArrayList<>();
        
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node == null) {
                continue;
            }
            
            if (node.left != null) {
                stack.push(node.left);    
            }
            
            if (node.right != null) {
                stack.push(node.right);
            }
            
            temp.push(node);
        }
        
        while (!temp.isEmpty()) {
            ans.add(temp.pop().val);
        }
        
        return ans;
    }
}
```