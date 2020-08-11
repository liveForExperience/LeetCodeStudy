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