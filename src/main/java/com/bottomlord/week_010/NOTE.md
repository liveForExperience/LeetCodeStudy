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