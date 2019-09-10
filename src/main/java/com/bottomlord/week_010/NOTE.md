# LeetCode_22_括号生成
## 题目
给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。

例如，给出 n = 3，生成结果为：
```
[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
```
## 解法
### 思路
有效的括号代表从左往右看， 左括号个数要>=右括号，且最终左右括号数要相等
- 递归生成字符串
- 每一层增加一个括号，增加时需要判断两种状况：
    - 如果当前的括号数相等，只能增加左括号
    - 如果当前的左括号小于，那只要左括号不为0，就可以两种括号都添加
- 递归的退出条件是记录左右还剩多少括号没用的值都为0
### 代码
```java
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        recurse(ans, n, n, new String[2 * n]);
        return ans;
    }

    private void recurse(List<String> ans, int left, int right, String[] strings) {
        if (left == 0 && right == 0) {
            ans.add(String.join("", strings));
            return;
        }
        
        if (left == right) {
            strings[strings.length - left - right] = "(";
            recurse(ans, left - 1, right, strings);
        } else if (left < right) {
            if (left != 0) {
                strings[strings.length - left - right] = "(";
                recurse(ans, left - 1, right, strings);
            }
            strings[strings.length - left - right] = ")";
            recurse(ans, left, right - 1, strings);
        }
    }
}
```
## 解法二
### 思路
- 所有有效的括号字符串起始字符一定是"("，结尾字符一定是")"
- 用四个变量来定义括号字符串：
    - 可以增加的左括号的个数l
    - 可以增加的右括号的个数r
    - 左边可供消耗的左括号的个数lb
    - 右边可供消耗的右括号的个数rb
- 四个变量的作用是：
    - 如果左括号和右括号都大于0，那么两个括号以"()"的形态相向放置是没有问题的
    - 如果左括号和右括号都大于0，且可供消耗的左右括号也是大于0，那么就可以在最边上的两边分别放上")("
    - 如果左边可供消耗的左括号个数大于0，且右括号大于1，那么就可以在最边上的两边分别放上"))"
    - 如果右边可供消耗的右括号个数大于0，且左括号大于1，那么就可以在最边上的两边分别放上"(("
- 退出条件是左右括号都为0的时候
### 代码
```java
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        if (n == 0) {
            ans.add("");
            return ans;
        }
        
        char[] cs = new char[2 * n];
        cs[0] = '(';
        cs[2 * n - 1] = ')';
        recurse(ans, n - 1, n - 1, 1, 1, 1, cs);
        
        return ans;
    }
    
    private void recurse(List<String> ans, int l, int r, int lb, int rb, int index, char[] cs) {
        if (l == 0 && r == 0) {
            ans.add(new String(cs));
            return;
        }
        
        if (l > 0 && r > 0) {
            cs[index] = '(';
            cs[cs.length - 1 - index] = ')';
            recurse(ans, l - 1, r - 1, lb + 1, rb + 1, index + 1, cs);
        }
        
        if (l > 1 && rb > 0) {
            cs[index] = '(';
            cs[cs.length - 1 - index] = '(';
            recurse(ans, l - 2, r, lb + 1, rb - 1, index + 1, cs);
        }
        
        if (r > 1 && lb > 0) {
            cs[index] = ')';
            cs[cs.length - 1 - index] = ')';
            recurse(ans, l, r - 2, lb - 1, rb + 1, index + 1, cs);
        }
        
        if (l > 0 && r > 0 && lb > 0 && rb > 0) {
            cs[index] = ')';
            cs[cs.length - 1 - index] = '(';
            recurse(ans, l - 1, r - 1, lb - 1, rb - 1, index + 1, cs);
        }
    }
}
```
# LeetCode_46_全排列
## 题目
给定一个没有重复数字的序列，返回其所有可能的全排列。

示例:
```
输入: [1,2,3]
输出:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
```
## 解法
### 思路
回溯算法，循环交换相邻元素，并同时下钻处理以一个元素，返回时回溯到下钻前状态，进入下个循环的下钻。
### 代码
```java
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums.length == 0) {
            return ans;
        }

        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }

        backTrack(nums.length, ans, list, 0);
        return ans;
    }

    private void backTrack(int len, List<List<Integer>> ans, List<Integer> list, int index) {
        if (index == len) {
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int i = index; i < len; i++) {
            Collections.swap(list, index, i);
            backTrack(len, ans, list, index + 1);
            Collections.swap(list, index, i);
        }
    }
}
```
## 解法二
### 思路
因为数字是[1,n]排列的有序不重复数组，所以可以通过boolean[]数组来记录记忆化搜索内容的方式来搜索整个数组的可能。递归搜索。
### 代码
```java
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        boolean[] visit = new boolean[nums.length + 1];
        recurse(nums, ans, visit, new ArrayList<>(), nums.length);
        return ans;
    }
    
    private void recurse(int[]nums, List<List<Integer>> ans, boolean[] visit, List<Integer> list, int len) {
        if (len == list.size()) {
            ans.add(new ArrayList<>(list));
        }
        
        for (int i = 0; i < len; i++) {
            if (!visit[i]) {
                list.add(nums[i]);
                visit[i] = true;
                recurse(nums, ans, visit, list, len);
                list.remove(list.size() - 1);
                visit[i] = false;
            }
        }
    }
}
```
# LeetCode_1008_先序遍历构造二叉树
## 题目
返回与给定先序遍历 preorder 相匹配的二叉搜索树（binary search tree）的根结点。

(回想一下，二叉搜索树是二叉树的一种，其每个节点都满足以下规则，对于 node.left 的任何后代，值总 < node.val，而 node.right 的任何后代，值总 > node.val。此外，先序遍历首先显示节点的值，然后遍历 node.left，接着遍历 node.right。）

示例：
```
输入：[8,5,1,7,10,12]
输出：[8,5,10,1,7,null,12]
```
提示：
```
1 <= preorder.length <= 100
先序 preorder 中的值是不同的。
```
## 解法
### 思路
- 基于如下两个结论，可以构造这棵树:
    - 根据二叉树的先序遍历和中序遍历，可以唯一确定这个二叉树
    - 如果一棵树是二叉树，那么它的中序遍历就是所有树元素的升序排列
- 过程：
    1. 排序先序遍历数组，获得中序遍历
    2. 通过先序数组的第一个元素，获知中序数组中的根节点，并获得这棵树的左右两半部分
    3. 因为先序遍历数组的左右子树是连续的片段，通过先序确定根节点，通过中序确定左右子树，这样递归构建
### 代码
```java
class Solution {
    private int predex = 0;

    public TreeNode bstFromPreorder(int[] preorder) {
        int[] inorder = Arrays.copyOf(preorder, preorder.length);
        Arrays.sort(inorder);

        Map<Integer, Integer> map = new HashMap<>(inorder.length);
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return dfs(preorder, 0, preorder.length, map);
    }

    private TreeNode dfs(int[] preorder, int left, int right, Map<Integer, Integer> map) {
        if (left == right) {
            return null;
        }

        int rootVal = preorder[predex];
        TreeNode root = new TreeNode(rootVal);

        int rootIndex = map.get(rootVal);
        predex++;
        
        root.left = dfs(preorder, left, rootIndex, map);
        root.right = dfs(preorder, rootIndex + 1, right, map);
        return root;
    }
}
```
## 解法二
### 思路
不获得中序遍历，直接通过先序遍历构造二叉树：
- 通过节点值的上下界来限定当前节点的数值
- 通过preorder的下标和上下界来节点当前下标对应的值是否在范围内：
    - 如果是就新建一个node
    - 否则就回溯
- 如果index等于preorder的长度，说明构造完成    
### 代码
```java
class Solution {
    private int predex;
    public TreeNode bstFromPreorder(int[] preorder) {
        this.predex = 0;
        return recurse(preorder.length, preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private TreeNode recurse(int len, int[] preorder, int low, int high) {
        if (predex == len) {
            return null;
        }

        int val = preorder[predex];
        if (val < low || val > high) {
            return null;
        }

        predex++;
        TreeNode root = new TreeNode(val);
        root.left = recurse(len, preorder, low, val);
        root.right = recurse(len, preorder, val, high);
        return root;
    }
}
```
# LeetCode_1079_活字印刷
## 题目
你有一套活字字模 tiles，其中每个字模上都刻有一个字母 tiles[i]。返回你可以印出的非空字母序列的数目。

示例 1：
```
输入："AAB"
输出：8
解释：可能的序列为 "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA"。
```
示例 2：
```
输入："AAABBC"
输出：188
```
提示：
```
1 <= tiles.length <= 7
tiles 由大写英文字母组成
```
## 解法
### 思路
回溯，记忆化的搜索，同时将结果放入set中去重，最终给返回set的长度
### 代码
```java
class Solution {
    public int numTilePossibilities(String tiles) {
        Set<String> set = new HashSet<>();
        backTrack(tiles.toCharArray(), new boolean[tiles.length()], set, new char[tiles.length()], 0);
        return set.size();
    }

    private void backTrack(char[] cs, boolean[] visit, Set<String> set, char[] c, int index) {
        if (index == cs.length) {
            return;
        }
        
        for (int i = 0; i < cs.length; i++) {
            if (!visit[i]) {
                c[index] = cs[i];
                set.add(new String(c).trim());
                visit[i] = true;
                backTrack(cs, visit, set, c, index + 1);
                c[index] = ' ';
                visit[i] = false;
            }
        }
    }
}
```
## 优化代码
### 思路
不是用set去重，解法一中每次下钻通过visit来规避之前遍历过的字符串的字符，但是会导致有相同字符出现而产生的重复情况。如果使用字典表的思路，字母作为子树来遍历，那同样排列的字符串就只会出现一次，从而省去了构建set的时间复杂度。
### 代码
```java
class Solution {
    public int numTilePossibilities(String tiles) {
        if (tiles.length() == 0) {
            return 0;
        }

        int[] bucket = new int[26];
        for (char c : tiles.toCharArray()) {
            bucket[c - 'A']++;
        }

        return backTrack(bucket);
    }

    private int backTrack(int[] bucket) {
        int result = 0;
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] == 0) {
                continue;
            }
            result++;
            bucket[i]--;
            result += backTrack(bucket);
            bucket[i]++;
        }
        return result;
    }
}
```