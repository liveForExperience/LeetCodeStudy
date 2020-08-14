# LeetCode_186_翻转字符串里的单词II
## 题目
给定一个字符串，逐个翻转字符串中的每个单词。

示例：
```
输入: ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
输出: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
```
注意：
```
单词的定义是不包含空格的一系列字符
输入字符串中不会包含前置或尾随的空格
单词与单词之间永远是以单个空格隔开的
```
```
进阶：使用 O(1) 额外空间复杂度的原地解法。
```
## 解法
### 思路
硬做：
- 遍历字符数组s：
    - 不是空格，就将字符append到sb中
    - 是空格，就将sb转成string放入list中，并将sb清空
    - 遍历结束后，如果sb不是空，再add到list中
- 双指针翻转list
- 遍历list，生成对应的字符串，生成过程中补上空格
- 遍历字符数组s，将生成的字符串按迭代的下标放入s中
### 代码
```java
class Solution {
    public void reverseWords(char[] s) {
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (char c : s) {
            if (c != ' ') {
                sb.append(c);
            } else {
                list.add(sb.toString());
                sb = new StringBuilder();
            }
        }
        
                
        if (sb.length() != 0) {
            list.add(sb.toString());
        }
        
        int head = 0, tail = list.size() - 1;
        while (head < tail) {
            String tmp = list.get(tail);
            list.set(tail, list.get(head));
            list.set(head, tmp);
            
            head++;
            tail--;
        }
        
        sb = new StringBuilder();
        for (String str : list) {
            sb.append(str).append(" ");
        }
        
        String str = sb.toString();
        for (int i = 0; i < s.length; i++) {
            s[i] = str.charAt(i);
        }
    }
}
```
## 解法二
### 思路
- 头尾翻转字符数组s
- 遍历s，记录起始坐标head，并初始化为0
- 当遇到空格时，触发翻转，tail为空格坐标-1
- 翻转结束后，更新head
- 遍历结束后，再判断head是否小于len，如果是就再做最后一次翻转，tail为字符数组长度-1
### 代码
```java
class Solution {
    public void reverseWords(char[] s) {
        int len = s.length;

        if (len < 2) {
            return;
        }

        int head = 0;
        reserve(s, head, len - 1);

        for (int i = 0; i < len; i++) {
            if (s[i] == ' ') {
                reserve(s, head, i - 1);
                head = i + 1;
            }
        }

        if (head < len) {
            reserve(s, head, len - 1);
        }
    }

    private void reserve(char[] s, int head, int tail) {
        while (head < tail) {
            char c = s[head];
            s[head] = s[tail];
            s[tail] = c;

            head++;
            tail--;
        }
    }
}
```
# LeetCode_187_重复的DNA序列
## 题目
所有 DNA 都由一系列缩写为 A，C，G 和 T 的核苷酸组成，例如：“ACGAATTCCG”。在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。

编写一个函数来查找目标子串，目标子串的长度为 10，且在 DNA 字符串 s 中出现次数超过一次。

示例：
```
输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
输出：["AAAAACCCCC", "CCCCCAAAAA"]
```
## 解法
### 思路
hash表：
- 遍历字符串，截取长度为10的子串，计算hash值，放入map中
- 如果新遍历到的子串在map中存在，就记录当前子串
- 并记录出现次数，如果出现次数超过2次，就不做记录
### 代码
```java
class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        Map<String, Integer> map = new HashMap<>();
        List<String> ans = new ArrayList<>();
        for (int i = 0; i <= s.length() - 10; i++) {
            String word = s.substring(i, i + 10);
            map.put(word, map.getOrDefault(word, 0) + 1);
            if (map.get(word) == 2) {
                ans.add(word);     
            }
        }
        return ans;
    }
}
```
## 解法二
### 思路
Rabin-Karp算法：
- 基因序列可以看作是4进制的数
- 通过滑动窗口计算10位4进制数对应的10进制的值，就能快速确定字符串的编码，从而从set中找到是否有重复
- 首先要将`A`,`C`,`G`,`T`组成的字符串转换成4进制的数
- 然后遍历字符串，计算10位字符串对应的编码值，也就是4进制转10进制的值
- 计算中可以通过减去最高位，加上最低位的方式快速的求得新的编码
- 查看set中是否存在该编码，如果存在就存储，如果不存在就放入set中
### 代码
```java
class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        int n = 10, len = s.length();
        if (len < n) {
            return new ArrayList<>();
        }

        Set<Integer> memo = new HashSet<>();
        Set<String> set = new HashSet<>();
        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 0);
        map.put('C', 1);
        map.put('G', 2);
        map.put('T', 3);
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = map.get(s.charAt(i));
        }

        int num = 0, headNum = (int) Math.pow(4, 10);
        for (int i = 0; i < len - n + 1; i++) {
            if (i != 0) {
                num = num * 4 - headNum * arr[i - 1] + arr[i + n - 1];
            } else {
                for (int j = 0; j < n; j++) {
                    num = num * 4 + arr[j];
                }
            }

            if (memo.contains(num)) {
                set.add(s.substring(i, i + n));
            }
            memo.add(num);
        }
        
        return new ArrayList<>(set);
    }
}
```
# LeetCode_1044_最长重复子串
## 题目
给出一个字符串S，考虑其所有重复子串（S 的连续子串，出现两次或多次，可能会有重叠）。

返回任何具有最长可能长度的重复子串。（如果 S不含重复子串，那么答案为""。）

示例 1：
```
输入："banana"
输出："ana"
```
示例 2：
```
输入："abcd"
输出：""
```
提示：
```
2 <= S.length <= 10^5
S 由小写英文字母组成。
```
## 解法
### 思路
二分查找+Rabin-Karp算法：
- 如果有最长重复子串L，那么一定也有`L0 < L`重复子串，那么就可以使用二分法，logn的复杂度来查找最长子串的长度
    - 如果当前一半长度找不到，那说明子串长度比一半更小
    - 如果当前一半长度找到了，那就尝试去更长找更长的可能
- 在尝试长度时，就使用Rabin-Karp算法：
    - 将每一个字符转换为26进制数进行计算，获得编码值
    - 然后遍历所有当前长度的可能
        - 第一组直接做26进制转换
        - 从第二组开始，在前一组的基础上，减去最高位的值，加上最低位的值
    - 将如上获得的编码放入set集合中，判断是否有重复
        - 如果有，直接返回当前遍历到的起始坐标
        - 如果没有，继续循环，直到迭代结束，返回-1
- 暂存Rabin-Karp算法求得的start值
- 二分查找结束后，根据start值和重复子串长度获得字符串
### 代码
```java
class Solution {
    public String longestDupSubstring(String S) {
        int n = S.length();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = S.charAt(i) - 'a';
        }

        int left = 1, right = n, a = 26, len = 0;
        long modulus = (long) Math.pow(2, 32);
        while (left != right) {
            len = left + (right - left) / 2;

            if (rabinKarp(len, a, modulus, nums) != -1) {
                left = len + 1;
            } else {
                right = len;
            }
        }

        int start = rabinKarp(left - 1, a, modulus, nums);
        return start == -1 ? "" : S.substring(start, start + left - 1);
    }

    private int rabinKarp(int l, int a, long modulus, int[] nums) {
        int n = nums.length;
        long h = 0;
        for (int i = 0; i < l; i++) {
            h = (h * a + nums[i]) % modulus;
        }

        long aL = 1;
        for (int i = 0; i < l; i++) {
            aL = (aL * a) % modulus;
        }

        Set<Long> memo = new HashSet<>();
        memo.add(h);

        for (int i = 1; i < n - l + 1; i++) {
            h = (h * a - nums[i - 1] * aL % modulus + modulus) % modulus;
            h = (h + nums[i + l - 1]) % modulus;

            if (memo.contains(h)) {
                return i;
            }

            memo.add(h);
        }

        return -1;
    }
}
```
# LeetCode_188_买卖股票的最佳时机IV
## 题目
给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。

设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。

注意:你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

示例1:
```
输入: [2,4,1], k = 2
输出: 2
解释: 在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
```
示例 2:
```
输入: [3,2,6,5,0,3], k = 2
输出: 7
解释: 在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
    随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
```
## 失败解法
### 失败原因
超出内存
### 思路
三维dp
### 代码
```java
class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n == 0) {
            return 0;
        }
        
        int[][][] dp = new int[n][2][k + 1];

        for (int i = 0; i < n; i++) {
            for (int j = k; j >= 1; j--) {
                if (i == 0) {
                    dp[i][0][j] = 0;
                    dp[i][1][j] = -prices[i];
                    continue;
                }

                dp[i][0][j] = Math.max(dp[i - 1][0][j], dp[i - 1][1][j] + prices[i]);
                dp[i][1][j] = Math.max(dp[i - 1][1][j], dp[i - 1][0][j - 1] - prices[i]);
            }
        }

        return dp[n - 1][0][k];
    }
}
```
## 解法
### 思路
- 基于失败解法，如果n和k很大，那么3维数组就会超过内存限制
- 但起始n和k之间有一定的关系，如果k大于n的二分之一，那么在n天内就一定不会超过k次的交易，因为买卖需要2天
- 那么如果`k > n / 2`时，直接使用二维数组就可以解决这个问题
### 代码
```java
class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n == 0) {
            return 0;
        }
        
        if (k > n / 2) {
            int[][] dp = new int[n][2];
            for (int i = 0; i < n; i++) {
                if (i == 0) {
                    dp[i][0] = 0;
                    dp[i][1] = -prices[i];
                    continue;
                }
                
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            }
            
            return dp[n - 1][0];
        }
        
        
        int[][][] dp = new int[n][2][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                if (i == 0) {
                    dp[i][0][j] = 0;
                    dp[i][1][j] = -prices[i];
                    continue;
                }
                
                dp[i][0][j] = Math.max(dp[i - 1][0][j], dp[i - 1][1][j] + prices[i]);
                dp[i][1][j] = Math.max(dp[i - 1][1][j], dp[i - 1][0][j - 1] - prices[i]);
            }
        }
        
        return dp[n - 1][0][k];
    }
}
```
# LeetCode_211_添加与搜索单词
## 题目
设计一个支持以下两种操作的数据结构：
```
void addWord(word)
bool search(word)
search(word)可以搜索文字或正则表达式字符串，字符串只包含字母 . 或 a-z 。 . 可以表示任何一个字母。
```
示例:
```
addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
```
说明:
```
你可以假设所有单词都是由小写字母 a-z 组成的。
```
## 解法
### 思路
字典树+回溯：
- 初始化字典树
    - 字典树节点数组`next`，长度为26
    - 当前节点是否为某个单词最后一个字符的标志`flag`
- `addWord`的时候将单词放入字典树中
- `search`的时候回溯搜索字典树，找到一个单词就返回true，否则false
### 代码
```java
class WordDictionary {
    private TrieNode root;
    public WordDictionary() {
        this.root = new TrieNode();
    }

    public void addWord(String word) {
        if (word == null) {
            return;
        }

        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }

            node = node.children[index];
        }

        node.flag = true;
    }

    public boolean search(String word) {
        if (word == null) {
            return false;
        }

        return doSearch(word, root);
    }

    private boolean doSearch(String word, TrieNode node) {
        if (node == null) {
            return false;
        }

        if (Objects.equals("", word)) {
            return node.flag;
        }

        if (word.charAt(0) == '.') {
            for (int i = 0; i < 26; i++) {
                boolean flag = doSearch(word.substring(1), node.children[i]);

                if (flag) {
                    return true;
                }
            }
            return false;
        }

        return doSearch(word.substring(1), node.children[word.charAt(0) - 'a']);
    }

    private class TrieNode {
        TrieNode[] children;
        boolean flag;

        TrieNode() {
            children = new TrieNode[26];
        }
    }
}
```
## 解法二
### 思路
哈希表
- 初始化哈希表map
    - key：字符长度
    - valve：set集合，存储add的word
- add：
    - 计算word的长度
    - 将字符放入map中
- search：
    - 计算word的长度，查看map中是否存在，不存在就返回false
    - 查看长度对应的set中，是否存在word，如果存在就直接返回true
    - 如果不存在就开始在set中遍历，进行一一比对
    - 一一比对的时候：
        - 如果word的当前字符是"."，就跳过
        - 如果word的当前字符和set遍历到的字符串中的当前字符不相同就返回false
        - 如果遍历完set中的一个字符串，没有返回false，那就返回true
    - 如果如上都没有直接返回，就返回false
### 代码
```java

```