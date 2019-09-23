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
# LeetCode_791_自定义字符串排序
## 题目
字符串S和 T 只包含小写字符。在S中，所有字符只会出现一次。

S 已经根据某种规则进行了排序。我们要根据S中的字符顺序对T进行排序。更具体地说，如果S中x在y之前出现，那么返回的字符串中x也应出现在y之前。

返回任意一种符合条件的字符串T。

示例:
```
输入:
S = "cba"
T = "abcd"
输出: "cbad"
解释: 
S中出现了字符 "a", "b", "c", 所以 "a", "b", "c" 的顺序应该是 "c", "b", "a". 
由于 "d" 没有在S中出现, 它可以放在T的任意位置. "dcba", "cdba", "cbda" 都是合法的输出。
```
注意:
```
S的最大长度为26，其中没有重复的字符。
T的最大长度为200。
S和T只包含小写字符。
```
## 解法
### 思路
- 一个数组nums存T中字母的个数
- 遍历S字符数组，根据顺序从nums中找对应字符的个数，append进sb中，同时将nums的字符改成0
- 遍历nums，将不为0的字符append进sb
- 返回`sb.toString()`
### 代码
```java
class Solution {
    public String customSortString(String S, String T) {
        int[] nums = new int[26];
        for (char c : T.toCharArray()) {
            nums[c - 'a']++;
        }
        
        StringBuilder sb = new StringBuilder();
        for (char c : S.toCharArray()) {
            for (int i = 0; i < nums[c - 'a']; i++) {
                sb.append(c);
            }
            nums[c - 'a'] = 0;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                for (int j = 0; j < nums[i]; j++) {
                    sb.append((char) (i + 'a'));
                }
            }
        }
        
        return sb.toString();
    }
}
```