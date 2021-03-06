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
# LeetCode_293_翻转游戏
## 题目
你和朋友玩一个叫做「翻转游戏」的游戏，游戏规则：给定一个只有 + 和 - 的字符串。你和朋友轮流将 连续 的两个 "++" 反转成 "--"。 当一方无法进行有效的翻转时便意味着游戏结束，则另一方获胜。

请你写出一个函数，来计算出第一次翻转后，字符串所有的可能状态。

示例：
```
输入: s = "++++"
输出: 
[
  "--++",
  "+--+",
  "++--"
]
注意：如果不存在可能的有效操作，请返回一个空列表 []。
```
## 解法
### 思路
一个个遍历
### 代码
```java
class Solution {
    public List<String> generatePossibleNextMoves(String s) {
        List<String> list = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return list;
        }

        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1) && s.charAt(i) == '+') {
                list.add(s.substring(0, i) + "--" + s.substring(i + 2));
            }
        }
        
        return list;
    }
}
```
## 解法二
### 思路
按条件不同步长的遍历
### 代码
```java
class Solution {
    public List<String> generatePossibleNextMoves(String s) {
        List<String> list = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return list;
        }

        int i = 0;
        while (i < s.length() - 1) {
            if (s.charAt(i) == '-') {
                i++;
                continue;
            }
            
            if (s.charAt(i + 1) == '-') {
                i += 2;
                continue;
            }

            list.add(s.substring(0, i) + "--" + s.substring(i + 2));
            i++;
        }
        
        return list;
    }
}
```
## 解法三
### 思路
转成字符数组再操作：
- 遇到都是`+`，转成`-`后生成字符串
- 再将`-`转回成`+`
### 代码
```java
class Solution {
    public List<String> generatePossibleNextMoves(String s) {
        List<String> list = new ArrayList<>();
        if (s.length() < 2) {
            return list;
        }
        
        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length - 1; i++) {
            if (cs[i] == '+' && cs[i + 1] == '+') {
                cs[i] = '-';
                cs[i + 1] = '-';
                list.add(new String(cs));
                cs[i] = '+';
                cs[i + 1] = '+';
            }
        }
        
        return list;
    }
}
```
# LeetCode_294_翻转游戏II
## 题目
你和朋友玩一个叫做「翻转游戏」的游戏，游戏规则：给定一个只有 + 和 - 的字符串。你和朋友轮流将 连续 的两个 "++" 反转成 "--"。 当一方无法进行有效的翻转时便意味着游戏结束，则另一方获胜。

请你写出一个函数来判定起始玩家是否存在必胜的方案。

示例：
```
输入: s = "++++"
输出: true 
解析: 起始玩家可将中间的 "++" 翻转变为 "+--+" 从而得胜。
延伸：
请推导你算法的时间复杂度。
```
## 解法
### 思路
记忆化搜索：
- 退出条件：
    - 当前字符串map中已存在，返回map映射结果
    - 当前字符串无法翻转，返回false
- 过程:
    - 迭代字符串
    - 尝试找到并翻转可翻的字符串，翻转后继续递归
    - 根据返回的结果判断：
        - 如果是false，代表当前层翻转后，下一层无法再翻转，直接返回true
        - 如果是true，代表当前字符串最终可以得到必胜的翻转方式，将当前字符串和true作为映射关系放入map
### 代码
```java
public class Solution {
    private Map<String, Boolean> map = new HashMap<>();
    public boolean canWin(String s) {
        if (map.containsKey(s)) {
            return map.get(s);
        }

        char[] cs = s.toCharArray();
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i - 1) == '+' && s.charAt(i) == '+') {
                cs[i - 1] = '-';
                cs[i] = '-';
                String str = new String(cs);
                if (!canWin(str)) {
                    return true;
                }
                map.put(str, true);
                cs[i - 1] = '+';
                cs[i] = '+';
            }
        }

        return false;
    }
}
```
# LeetCode_LCP_19_秋叶收藏集
## 题目
小扣出去秋游，途中收集了一些红叶和黄叶，他利用这些叶子初步整理了一份秋叶收藏集 leaves， 字符串 leaves 仅包含小写字符 r 和 y， 其中字符 r 表示一片红叶，字符 y 表示一片黄叶。

出于美观整齐的考虑，小扣想要将收藏集中树叶的排列调整成「红、黄、红」三部分。每部分树叶数量可以不相等，但均需大于等于 1。每次调整操作，小扣可以将一片红叶替换成黄叶或者将一片黄叶替换成红叶。请问小扣最少需要多少次调整操作才能将秋叶收藏集调整完毕。

示例 1：
```
输入：leaves = "rrryyyrryyyrr"

输出：2

解释：调整两次，将中间的两片红叶替换成黄叶，得到 "rrryyyyyyyyrr"
```
示例 2：
```
输入：leaves = "ryr"

输出：0

解释：已符合要求，不需要额外操作
```
提示：
```
3 <= leaves.length <= 10^5
leaves 中只包含字符 'r' 和字符 'y'
```
## 解法
### 思路
动态规划：
- `dp[i][j]`：从0到第i个叶子，能够使第i个叶子为j状态且符合要求的最小移动次数
- base case：
    - `dp[0][0] == isYellow(0) ? 1 : 0`
    - `dp[0][1] = dp[0][2] = dp[1][2] = Integer.MAX_VALUE`：因为叶子数量不能小于状态数量，否则不符合题目要求
- 状态转移方程：
    - `j == 0`：`dp[i][j] = dp[i - 1][0] + isYellow(i)`
    - `j == 1`：`dp[i][j] = Math.min(dp[i - 1][0], dp[i - 1][1]) + isRed(i)`
    - `j == 2`：`dp[i][j] = Math.min(dp[i - 1][1], dp[i - 1][2]) + isYellow(i)`
- 返回结果：
    - `dp[len - 1][2]`
### 代码
```java
class Solution {
    public int minimumOperations(String leaves) {
        int len = leaves.length();
        int[][] dp = new int[len][3];
        dp[0][0] = leaves.charAt(0) == 'y' ? 1 : 0;
        dp[0][1] = dp[0][2] = dp[1][2] = Integer.MAX_VALUE;

        for (int i = 1; i < len; i++) {
            int isYellow = leaves.charAt(i) == 'y' ? 1 : 0, 
                isRed = leaves.charAt(i) == 'r' ? 1 : 0;
            
            dp[i][0] = dp[i - 1][0] + isYellow;
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][1]) + isRed;
            
            if (i >= 2) {
                dp[i][2] = Math.min(dp[i - 1][1], dp[i - 1][2]) + isYellow;
            }
        }

        return dp[len - 1][2];
    }
}
```