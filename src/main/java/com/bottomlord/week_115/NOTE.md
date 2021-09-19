# [LeetCode_1678_设计Goal解析器](https://leetcode-cn.com/problems/goal-parser-interpretation/)
## 解法
### 思路
- 生成command字符串和结果字符串的映射关系
- 遍历字符串生成对应的结果字符串
- 遍历结束返回结果
### 代码
```java
class Solution {
    public String interpret(String command) {
        char[] cs = command.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cs.length;) {
            char c = cs[i];
            if ('G' == c) {
                sb.append(c);
                i++;
            } else {
                char nextC = command.charAt(++i);
                if (nextC == ')') {
                    sb.append("o");
                    i++;
                } else {
                    sb.append("al");
                    i += 3;
                }
            }
        }
        
        return sb.toString();
    }
}
```
## 解法二
### 思路
使用String的replaceAll这个Api
### 代码
```java
class Solution {
    public String interpret(String command) {
        return command.replaceAll("\\(\\)", "o").replaceAll("\\(al\\)", "al");
    }
}
```                                                                                                     