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