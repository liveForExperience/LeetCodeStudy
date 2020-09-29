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
# LeetCode_291_单词规律II
## 题目
给你一种规律 pattern 和一个字符串 str，请你判断 str 是否遵循其相同的规律。

这里我们指的是 完全遵循，例如 pattern 里的每个字母和字符串 str 中每个 非空 单词之间，存在着双向连接的对应规律。

示例1:
```
输入: pattern = "abab", str = "redblueredblue"
输出: true
```
示例2:
```
输入: pattern = "aaaa", str = "asdasdasdasd"
输出: true
```
示例3:
```
输入: pattern = "aabb", str = "xyzabcxzyabc"
输出: false
```
提示：
```
你可以默认 pattern 和 str 都只会包含小写字母。
```
## 解法
### 思路
回溯
- 实例变量：map，用来暂存pattern和对应字符串之间的映射关系
- 退出条件：
    - patern和str的长度皆为0，返回true，说明搜索结束，全部匹配
    - patern的长度为0，str不为0，返回false，说明当前映射方式不匹配
- 处理部分：
    1. 截取pattern的第一个字符，作为当前要确定的模式字符
    2. 从1开始循环`str - pattern`的长度，代表，遍历从长度为1到最大可能映射长度的所有可能性
    3. 循环体内，根据确定的映射字段长度，确定映射字符串
    4. 通过截取的pattern第一个字符，到map中获取映射字段，然后判断是否符合如下情况，如果符合就进行实际的下钻和回溯
        - 映射字段不为空，且与截取的待确定字段相等，说明当前待确认字符串是和之前层确定的字符串映射一致的，可以继续下钻
        - 映射字段为空，且map中没有桶截取字段一致的value，代表当前待确认字符串能和截取的pattern组成一组新的映射
    5. 将截取的pattern字符和映射字符串放入map中
    6. 下钻，下钻时str进行截取，将当前确定的映射字符串从str中截去
    7. 如果回溯的结果是true，代表已经匹配成功，直接返回true
    8. 如果不是true，则将新增的映射关系，也就是当前层映射字段为空的情况下，将put的映射去除
    9. 如果不是新增的映射关系，则不需处理，在当前层处理结束后，通过回溯再去除
    10. 如果循环结束，没有返回true，则代表当前层的所有可能都不能匹配，返回false
### 代码
```java
class Solution {
    private Map<String, String> map = new HashMap<>();
    public boolean wordPatternMatch(String pattern, String str) {
        if (pattern.length() == 0) {
            return str.length() == 0;
        }

        String p = pattern.substring(0, 1);
        for (int i = 1; i <= str.length() - pattern.length() + 1; i++) {
            String s = str.substring(0, i), mapping = map.get(p);

            boolean isNewMatch = mapping == null && !map.containsValue(s),
                    isOldMatch = mapping != null && Objects.equals(map.get(p), s);
            if (isNewMatch || isOldMatch) {
                map.put(p, s);
                if (wordPatternMatch(pattern.substring(1), str.substring(i))) {
                    return true;
                }
                if (isNewMatch) {
                    map.remove(p);
                }
            }
        }
        
        return false;
    }
}
```