# LeetCode_285_二叉搜索树中的顺序后继
## 题目
给你一个二叉搜索树和其中的某一个结点，请你找出该结点在树中顺序后继的节点。

结点 p 的后继是值比 p.val 大的结点中键值最小的结点。

示例 1:
```
输入: root = [2,1,3], p = 1
输出: 2
解析: 这里 1 的顺序后继是 2。请注意 p 和返回值都应是 TreeNode 类型。
```
示例 2:
```
输入: root = [5,3,6,2,4,null,null,1], p = 6
输出: null
解析: 因为给出的结点没有顺序后继，所以答案就返回 null 了。
```
注意:
```
假如给出的结点在该树中没有顺序后继的话，请返回 null
我们保证树中每个结点的值是唯一的
```
## 解法
### 思路
中序dfs：
- 发现第一个比p大的节点，记录并返回
### 代码
```java
class Solution {
    private TreeNode ans = null;
    private boolean find = false;
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        dfs(root, p);
        return ans;
    }

    private void dfs(TreeNode node, TreeNode p) {
        if (node == null || find) {
            return;
        }
        
        dfs(node.left, p);
        
        if (node.val > p.val && !find) {
            ans = node;
            find = true;
        }
        
        dfs(node.right, p);
    }
}
```
# LeetCode_288_单词的唯一缩写
## 题目
一个单词的缩写需要遵循 <起始字母><中间字母数><结尾字母> 这样的格式。

以下是一些单词缩写的范例：
```
a) it                      --> it    (没有缩写)

     1
     ↓
b) d|o|g                   --> d1g

              1    1  1
     1---5----0----5--8
     ↓   ↓    ↓    ↓  ↓    
c) i|nternationalizatio|n  --> i18n

              1
     1---5----0
     ↓   ↓    ↓
d) l|ocalizatio|n          --> l10n
请你判断单词缩写在字典中是否唯一。当单词的缩写满足下面任何一个条件是，可以认为该单词缩写是唯一的：

字典 dictionary 中没有任何其他单词的缩写与该单词 word 的缩写相同。
字典 dictionary 中的所有缩写与该单词 word 的缩写相同的单词都与 word 相同。
```
示例 1：
```
输入：
["ValidWordAbbr","isUnique","isUnique","isUnique","isUnique"]
[[["deer","door","cake","card"]],["dear"],["cart"],["cane"],["make"]]
输出：
[null,false,true,false,true]

解释：
ValidWordAbbr validWordAbbr = new ValidWordAbbr(["deer", "door", "cake", "card"]);
validWordAbbr.isUnique("dear"); // return False
validWordAbbr.isUnique("cart"); // return True
validWordAbbr.isUnique("cane"); // return False
validWordAbbr.isUnique("make"); // return True
```
提示：
```
每个单词都只由小写字符组成
```
## 解法
### 思路
map：
- key为单词缩写
- value为实际单词的集合，使用set去重
### 代码
```java
class ValidWordAbbr {
    private Map<String, Set<String>> map;
    public ValidWordAbbr(String[] dictionary) {
        this.map = new HashMap<>();
        for (String word : dictionary) {
            String sWord = simplify(word);
            Set<String> set = this.map.getOrDefault(sWord, new HashSet<>());
            set.add(word);
            this.map.put(sWord, set);
        }
    }

    public boolean isUnique(String word) {
        String sWord = simplify(word);
        if (this.map.containsKey(sWord)) {
            Set<String> words = this.map.get(sWord);
            return words.size() == 1 && words.contains(word);
        }

        return true;
    }

    private String simplify(String word) {
        if (word.length() <= 2) {
            return word;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(word.charAt(0)).append(word.length() - 2).append(word.charAt(word.length() - 1));
        return sb.toString();
    }
}
```