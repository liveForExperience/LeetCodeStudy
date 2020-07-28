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
# LeetCode_159_至多包含两个不同字符的最长子串
## 题目
给定一个字符串 s ，找出 至多 包含两个不同字符的最长子串 t ，并返回该子串的长度。

示例 1:
```
输入: "eceba"
输出: 3
解释: t 是 "ece"，长度为3。
```
示例 2:
```
输入: "ccaabbb"
输出: 5
解释: t 是 "aabbb"，长度为5。
```
## 解法
### 思路
哈希表+双指针：
- 使用hash表，key存储字符，value存储连续相同字符的最右下标
- 使用两个指针：
    - head：代表窗口的起始下标，第一个字符的最小坐标
    - tail：代表窗口的结尾下标，第二个字符的做大坐标
- 过程：
    - 遍历字符串，将当前字符放入map中指定的位置
        - 如果map元素总数大于2个，将元素放入map中，并更新value坐标值
        - 如果map元素总数大于2个，不放入元素
    - 如果当前map的元素个数是否大于2
        - 获取value值最小的key，保存其值value，并删除
        - 更新head指针为`value + 1`
    - 每次循环都更新窗口长度`(right - left) + 1`
        
### 代码
```java
class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int len = s.length(), head = 0, tail = 0, ans = 0;
        Map<Character, Integer> map = new HashMap<>();

        while (tail < len) {
            if (map.size() < 3) {
                map.put(s.charAt(tail), tail++);
            }
            
            if (map.size() == 3) {
                int index = map.remove(s.charAt(Collections.min(map.values())));
                head = index + 1;
            }
            
            ans = Math.max(ans, tail - head);
        }
        
        return ans;
    }
}
```
## 解法二
### 思路
使用数组代替解法一中的map，仍然使用双指针
- 遍历字符串，移动tail指针，并累加遍历到的字符的个数
- 用一个变量count来记录遍历到的不同字符数
- 如果遍历到的字符在数组中个数为0，那么就累加count
- 如果count值等于3，那么就循环移动head指针，将head指针对应的字符数到数组中进行累减
- 如果累减到0，那么就将count减1
- 每次移动tail都更新一次ans值，更新的动作是在移动head之前
- 也因为每次更新是在更新head之前，所以最后一次更新需要在循环结束以后，也就是tail越界，head可能被更新之后
### 代码
```java
class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int len = s.length(), head = 0, tail = 0, count = 0, ans = 0;
        int[] bucket = new int[128];

        while (tail < len) {
            char c = s.charAt(tail);
            if (bucket[c]++ == 0) {
                count++;
            }

            ans = Math.max(ans, tail - head);
            tail++;
            
            while (count > 2) {
                if (--bucket[s.charAt(head++)] == 0) {
                    count--;
                }
            }
        }
        
        return Math.max(ans, tail - head);
    }
}
```
# LeetCode_161_相隔为1的编辑距离
## 题目
给定两个字符串 s 和 t，判断他们的编辑距离是否为 1。

注意：

满足编辑距离等于 1 有三种可能的情形：
```
往 s 中插入一个字符得到 t
从 s 中删除一个字符得到 t
在 s 中替换一个字符得到 t
```
示例 1：
```
输入: s = "ab", t = "acb"
输出: true
解释: 可以将 'c' 插入字符串 s 来得到 t。
```
示例 2:
```
输入: s = "cab", t = "ad"
输出: false
解释: 无法通过 1 步操作使 s 变为 t。
```
示例 3:
```
输入: s = "1203", t = "1213"
输出: true
解释: 可以将字符串 s 中的 '0' 替换为 '1' 来得到 t。
```
## 解法
### 思路
遍历字符串：
- 先确定特殊情况，如果两个字符串的长度差大于1，直接返回错误
- 遍历字符串，比较两个字符串的字符
    - 如果两个字符相等继续循环
    - 如果两个字符串不相等，判断它们的长度是否相等：
        - 如果相等，就截去当前字符，判断剩下的字符串是否全等
        - 如果不相等，就截取第一个入参的字符串的当前字符，判断剩下的字符串是否全等
- 如果遍历结束，没有中断，那么就判断两个字符串是不是相差1，因为这时候可能短的字符串和另一个字符串开头的完全相等，那么必须要差一个字符，否则就全等了
### 代码
```java
class Solution {
    public boolean isOneEditDistance(String s, String t) {
        int sl = s.length(), tl = t.length();
        if (tl > sl) {
            return isOneEditDistance(t, s);
        }
        
        if (sl - tl > 1) {
            return false;
        }
        
        for (int i = 0; i < tl; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (sl == tl) {
                    return Objects.equals(s.substring(i + 1), t.substring(i + 1));
                } else {
                    return Objects.equals(s.substring(i + 1), t.substring(i));
                }
            }
        }
        
        return tl + 1 == sl;
    }
}
```
## 解法二
### 思路
动态规划：计算编辑距离
### 代码
```java
class Solution {
    public boolean isOneEditDistance(String s, String t) {
        int sl = s.length(), tl = t.length();
        if (Math.abs(sl - tl) > 1) {
            return false;
        }

        int[][] dp = new int[sl + 1][tl + 1];
        for (int i = 0; i <= sl; i++) {
            dp[i][0] = i;
        }
        
        for (int i = 0; i <= tl; i++) {
            dp[0][i] = i;
        }
        
        for (int i = 1; i <= sl; i++) {
            for (int j = 1; j <= tl; j++) {
                int sAdd = dp[i - 1][j] + 1, tAdd = dp[i][j - 1] + 1,
                    edit = s.charAt(i - 1) == t.charAt(j - 1) ? dp[i - 1][j - 1] : dp[i - 1][j - 1] + 1;
                dp[i][j] = Math.min(sAdd, Math.min(tAdd, edit));
            }
        }
        
        return dp[sl][tl] == 1;
    }
}
```