# LeetCode_392_判断子序列
## 题目
给定字符串 s 和 t ，判断 s 是否为 t 的子序列。

你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。

字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。

示例 1:
```
s = "abc", t = "ahbgdc"

返回 true.
```
示例 2:
```
s = "axc", t = "ahbgdc"

返回 false.
```
后续挑战 :
```
如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
```
## 解法
### 思路
- 同时遍历两个字符串
- 使用两个坐标代表遍历到的两个字符串的下标
    - `si`代表短字符串的坐标
    - `ti`代表长字符串的坐标
- 如果`ti`遍历完而`si`没有遍历完，返回false
### 代码
```
class Solution {
    public boolean isSubsequence(String s, String t) {
        int si = 0, sl = s.length(), ti = 0, tl = t.length();
        while (si < sl && ti < tl) {
            if (s.charAt(si) == t.charAt(ti)) {
                si++;
                ti++;
            } else {
                ti++;
            }
        }
        
        return si == sl && ti <= tl;
    }
}
```
## 解法二
### 思路
- 遍历t，记录26个字符在字符串中出现的所有下标，使用升序
- 遍历s，依次查看每个字符在t中出现的尽可能小的下标，也就是比前一个出现的下标大的最小值
    - 如果有这个值就继续遍历，直到遍历结束
    - 如果没有这个值就直接返回false
### 代码
```java
class Solution {
    public boolean isSubsequence(String s, String t) {
        Map<Character, List<Integer>> map =  new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            List<Integer> list = map.getOrDefault(c, new ArrayList<>());
            list.add(i);
            map.put(c, list);
        }
        
        int pre = -1;
        for (char c : s.toCharArray()) {
            if (!map.containsKey(c)) {
                return false;
            }
            
            List<Integer> list = map.get(c);
            int tmp = pre;
            for (Integer index : list) {
                if (index > tmp) {
                    pre = index;
                    break;
                }
            }
            
            if (tmp == pre) {
                return false;
            }
        }
        
        return true;
    }
}
```