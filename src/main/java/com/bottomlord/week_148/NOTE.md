# [LeetCode_591_标签验证器](https://leetcode.cn/problems/tag-validator/)
## 解法
### 思路
模拟:
- 用栈来保存标签的tagName，通过入栈和出栈来处理每一对标签，和处理括号是一样的
- 如果当前字符是`<`
  - 后面没有跟字符，则不符合要求
  - 第二个字符是`/`，代表是终止标签
    - 如果从当前字符开始，找不到`>`，那么就是false
    - 如果栈为空或者tagName和栈顶的元素不同，就返回false
    - 如果相同就弹出栈顶的元素，然后更新当前坐标到`>`之后，如果更新后的坐标不越界，且栈已经是空，那么返回false
  - 第二个字符是`!`，那么说明要处理cdata
    - 如果当前字符之后的9个字符对应的坐标，已经越界，那么返回false
    - 如果找不到`]]>`，返回false
    - 更新坐标到`]]>`之后
  - 如果都不是如上情况，说明是起始标签
    - 如果是最后的字符，那么返回false
    - 如果找不到`>`，返回false
    - 将tagName求出后，判断tagName的长度是否是1-9的区间，且是否都是大写，如果不是就返回false
    - 将tagName压入栈中，更新坐标到`>`之后
- 如果不是`<`，说明是普通字符串，那么就判断下是否栈为空，如果为空就返回false，否则就i++
- 遍历结束，判断栈是否为空，如果不为空，说明还有没结束的标签，返回false，否则就是true
### 代码
```java
class Solution {
    public boolean isValid(String code) {
        int n = code.length(), i = 0;
        Stack<String> stack = new Stack<>();

        while (i < n) {
            char c = code.charAt(i);
            if (c == '<') {
                if (i == n - 1) {
                    return false;
                }

                if (code.charAt(i + 1) == '/') {
                    int j = code.indexOf('>', i);
                    if (j < 0) {
                        return false;
                    }

                    String tagName = code.substring(i + 2, j);
                    if (stack.isEmpty() || !stack.peek().equals(tagName)) {
                        return false;
                    }

                    stack.pop();

                    i = j + 1;
                    if (stack.isEmpty() && i != n) {
                        return false;
                    }
                } else if (code.charAt(i + 1) == '!') {
                    if (stack.isEmpty()) {
                        return false;
                    }

                    if (i + 9 > n) {
                        return false;
                    }

                    String cdata = code.substring(i + 2, i + 9);
                    if (!Objects.equals(cdata, "[CDATA[")) {
                        return false;
                    }

                    int j = code.indexOf("]]>", i);
                    if (j < 0) {
                        return false;
                    }

                    i = j + 3;
                } else {
                    int j = code.indexOf(">", i);
                    if (j < 0) {
                        return false;
                    }

                    String tagName = code.substring(i + 1, j);
                    if (tagName.length() < 1 || tagName.length() > 9) {
                        return false;
                    }

                    for (int k = 0; k < tagName.length(); k++) {
                        if (!Character.isUpperCase(tagName.charAt(k))) {
                            return false;
                        }
                    }

                    stack.push(tagName);
                    i = j + 1;
                }
            } else {
                if (stack.isEmpty()) {
                    return false;
                }

                i++;
            }
        }
        
        return stack.isEmpty();
    }
}
```
# [LeetCode_2255_统计是给定字符串前缀的字符串数目](https://leetcode.cn/problems/count-prefixes-of-a-given-string/)
## 解法
### 思路
使用startWith遍历判断
### 代码
```java
class Solution {
    public int countPrefixes(String[] words, String s) {
        int count = 0;
        for (String word : words) {
            if (s.startsWith(word)) {
                count++;
            }
        }
        return count;
    }
}
```