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
# [LeetCode_1684_统计一致字符串的数目](https://leetcode-cn.com/problems/count-the-number-of-consistent-strings/)
## 解法
### 思路
- 用set存储所有的allowed字符
- 遍历字符串和字符串的字符，查看是否有字符在set中不存在，如果没有就记录下来
- 遍历结束，返回存储的个数结果
### 代码
```java
class Solution {
    public int countConsistentStrings(String allowed, String[] words) {
        Set<Character> set = new HashSet<>();
        for (char c : allowed.toCharArray()) {
            set.add(c);
        }

        int count = 0;
        for (String word : words) {
            boolean flag = true;
            for (char c : word.toCharArray()) {
                if (!set.contains(c)) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                count++;
            }
        }

        return count;
    }
}
```
## 解法二
### 思路
使用32位的数字代替set来存储allowed的字母
### 代码
```java
class Solution {
    public int countConsistentStrings(String allowed, String[] words) {
        int mask = 0;
        for (char c : allowed.toCharArray()) {
            int bit = c - '0';
            mask |= 1 << bit;
        }
        
        int count = 0;
        for (String word : words) {
            boolean flag = true;
            for (char c : word.toCharArray()) {
                int bit = c - '0';
                if (((1 << bit) | mask) != mask) {
                    flag = false;
                    break;
                }
            }
            
            if (flag) {
                count++;
            }
        }
        
        return count;
    }
}
```