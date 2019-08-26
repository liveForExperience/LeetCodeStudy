# LeetCode_28_实现strStr()
## 题目
实现 strStr() 函数。

给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。

示例 1:
```
输入: haystack = "hello", needle = "ll"
输出: 2
```
示例 2:
```
输入: haystack = "aaaaa", needle = "bba"
输出: -1
```
说明:
```
当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。

对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
```
## 解法
### 思路
- 通过indexOf函数，查找hayStack字符串中的needle字符串的首字符
- 判断needle字符串是否和找到的首字符之后的字符一致，如果一致就返回首字符的下标
- 循环这个过程，每次都从上一个首字符开始，直到indexOf返回-1
### 代码
```java
class Solution {
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        
        if (haystack == null || haystack.length() == 0) {
            return -1;
        }

        int index = 0, needleLen = needle.length(), hayLen = haystack.length();
        char c = needle.charAt(0);
        while (haystack.indexOf(c, index) != -1) {
            if (index + needleLen - 1 >= hayLen) {
                return -1;
            }

            boolean find = true;
            for (int i = 0; i < needleLen; i++) {
                if (needle.charAt(i) != haystack.charAt(index + i)) {
                    find = false;
                }
            }
            
            if (find) {
                return index;
            }

            index++;
        }

        return -1;
    }
}
```
## 优化代码
### 思路
直接使用indexOf
### 代码
```java
class Solution {
    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }
}
```
## 解法二
### 思路
使用subString在遍历hayStack过程中判断是否与needle相等
### 代码
```java
class Solution {
    public int strStr(String haystack, String needle) {
        int hayLen = haystack.length(), needleLen = needle.length();
        if (hayLen < needleLen) {
            return -1;
        }

        int start = 0, end = needleLen - 1;
        while (end < hayLen) {
            if (haystack.substring(start++, ++end).equals(needle)) {
                return start - 1;
            }
        }
        
        return -1;
    }
}
```
# LeetCode_290_单词规律
## 题目
给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。

这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。

示例1:
```
输入: pattern = "abba", str = "dog cat cat dog"
输出: true
```
示例 2:
```
输入:pattern = "abba", str = "dog cat cat fish"
输出: false
```
示例 3:
```
输入: pattern = "aaaa", str = "dog cat cat dog"
输出: false
```
示例 4:
```
输入: pattern = "abba", str = "dog dog dog dog"
输出: false
```
```
说明:
你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。   
```
## 解法
### 思路
使用两个map映射字符和单词之间的关系，用到两个是因为需要确认双向关系，遍历字符串数组，查看是否符合。
### 代码
```java
class Solution {
    public boolean wordPattern(String pattern, String str) {
        Map<Character, String> cMap = new HashMap<>();
        Map<String, Character> sMap = new HashMap<>();
        
        char[] cs = pattern.toCharArray();
        String[] ss = str.split(" ");

        if (cs.length != ss.length) {
            return false;
        }

        for (int i = 0; i < ss.length; i++) {
            Character c = cs[i];
            String s = ss[i];
            
            if (!cMap.containsKey(c) && !sMap.containsKey(s)) {
                cMap.put(c, s);
                sMap.put(s, c);
                continue;
            }
            
            if (cMap.containsKey(c) && sMap.containsKey(s)) {
                if (cMap.get(c).equals(s) && sMap.get(s).equals(c)) {
                    continue;
                }
            }
            
            return false;
        }

        return true;
    }
}
```
## 优化代码
### 思路
省略一个map，如果map里没有字符对应的字符串，那么如果map里有对应的value是这个字符串，也是false的
### 代码
```java
class Solution {
    public boolean wordPattern(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();
        String[] ss = str.split(" ");
        if (pattern.length() != ss.length) {
            return false;
        }
        for (int i = 0; i < ss.length; i++) {
            char c = pattern.charAt(i);
            if (map.containsKey(c)) {
                if (!map.get(c).equals(ss[i])) {
                    return false;
                }
            } else if (map.containsValue(ss[i])) {
                return false;
            } else {
                map.put(c, ss[i]);
            }
        }
        return true;
    }
}
```