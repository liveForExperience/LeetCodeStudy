# LeetCode_386_字典序排数
## 题目
给定一个整数 n, 返回从 1 到 n 的字典顺序。

例如，

给定 n =1 3，返回 [1,10,11,12,13,2,3,4,5,6,7,8,9] 。

请尽可能的优化算法的时间复杂度和空间复杂度。 输入的数据 n 小于等于 5,000,000。
## 解法
### 思路
以构建10叉树的思路，dfs遍历整个序列，每一层都将元素放入list中，最终返回
- 需要注意，因为第一层是从1开始的，所以会出现`10+0`以及`1+9`都得到10，从而10重复的问题，需要特别去除
### 代码
```java
class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new ArrayList<>();
        dfs(ans, 1, n);
        return ans;
    }

    private void dfs(List<Integer> ans, int start, int n) {
        if (start > n) {
            return;
        }

        for (int i = 0; i < 10 && i + start <= n; i++) {
            if (start == 1 && i == 9) {
                continue;
            }
            ans.add(start + i);
            dfs(ans, (start + i) * 10, n);
        }
    }
}
```
# LeetCode_238_除自身以外数组的乘积
## 题目
给定长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。

示例:
```
输入: [1,2,3,4]
输出: [24,12,8,6]
说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
```
进阶：
```
你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
```
## 解法
### 思路
- 数组所有元素的乘积 = 当前数左边所有数的乘积left * 当前数 * 当前数右边所有数的乘积right
- 所以按题目要求：`ans = left * right`
- 遍历数组，求当前元素左边所有元素的乘积的集合
- 遍历数组，求当前元素右边所有元素的乘积的集合
- 遍历数组，求`left * right`
### 代码
```java
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] lefts = new int[nums.length];
        lefts[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            lefts[i] = nums[i - 1] * lefts[i - 1];
        }

        int[] rights = new int[nums.length];
        rights[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            rights[i] = nums[i + 1] * rights[i + 1];
        }
        
        for (int i = 0; i < nums.length; i++) {
            lefts[i] *= rights[i];
        }
        return lefts;
    }
}
```
## 优化代码
### 思路
把解法一种3次的遍历过程，压缩成一次：
- 将结果数组元素都初始化为1
- 通过头尾指针累乘的方式来计算左积和右积
- 将头尾指针的累乘值与元素值相乘
- 遍历结束后返回结果即可
### 代码
```java
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int left = 1, right = 1, len = nums.length;
        int[] ans = new int[len];
        Arrays.fill(ans, 1);
        for (int i = 0; i < len; i++) {
            ans[i] *= left;
            left *= nums[i];
            
            ans[len - i - 1] *= right;
            right *= nums[len - i - 1];
        }
        return ans;
    }
}
```
# LeetCode_1161_最大层内元素和
## 题目
给你一个二叉树的根节点 root。设根节点位于二叉树的第 1 层，而根节点的子节点位于第 2 层，依此类推。

请你找出层内元素之和 最大 的那几层（可能只有一层）的层号，并返回其中 最小 的那个。

示例：
```
输入：[1,7,0,7,-8,null,null]
输出：2
解释：
第 1 层各元素之和为 1，
第 2 层各元素之和为 7 + 0 = 7，
第 3 层各元素之和为 7 + -8 = -1，
所以我们返回第 2 层的层号，它的层内元素之和最大。
```
提示：
```
树中的节点数介于 1 和 10^4 之间
-10^5 <= node.val <= 10^5
```
## 解法
### 思路
- dfs先序遍历，记录层号
- 用层号作为下标在list中记录当层的累加值
- 遍历结束后，找到list中的最小下标的最大值返回
### 代码
```java
class Solution {
    public int maxLevelSum(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfs(list, root, 1);
        
        int max = Collections.max(list);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == max) {
                return i + 1;
            }
        }
        
        return 0;
    }

    private void dfs(List<Integer> list, TreeNode node, int level) {
        if (node == null) {
            return;
        }

        if (list.size() < level) {
            list.add(node.val);
        } else {
            list.set(level - 1, list.get(level - 1) + node.val);
        }
        dfs(list, node.left, level + 1);
        dfs(list, node.right, level + 1);
    }
}
```