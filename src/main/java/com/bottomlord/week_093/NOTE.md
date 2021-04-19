# [LeetCode_27_移除元素](https://leetcode-cn.com/problems/remove-element/submissions/)
## 解法
### 思路
- 使用一个坐标作为新数组的元素指针，然后遍历原数组，判断当前元素是否与val相等
    - 如果相等就跳过，继续移动原指针
    - 否则就将当前元素放在新指针对应的位置，同时移动新旧2个指针
- 遍历结束后，返回新指针的值，就是新数组的长度
### 代码
```java
class Solution {
    public int removeElement(int[] nums, int val) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                continue;
            }
            nums[index++] = nums[i];
        }
        return index;
    }
}
```
# [LeetCode_524_通过删除字母匹配到字典里最长单词](https://leetcode-cn.com/problems/longest-word-in-dictionary-through-deleting/)
## 解法
### 思路
- 对字典列表根据题目要求排序
- 遍历字典元素，判断是否有元素被s包含
### 代码
```java
class Solution {
    public String findLongestWord(String s, List<String> dictionary) {
        dictionary.sort((x, y) -> {
            if (x.length() != y.length()) {
                return y.length() - x.length();
            }
            return x.compareTo(y);
        });
        
        for (String word : dictionary) {
            int wi = 0, si = 0;
            while (wi < word.length() && si < s.length()) {
                if (word.charAt(wi) == s.charAt(si)) {
                    wi++;
                    si++;
                } else {
                    si++;
                }
                
                if (wi == word.length()) {
                    return word;
                }
            }
        }
        
        return "";
    }
}
```
## 解法二
### 思路
不排序，直接比较所有字典元素，比较过程中保留符合的且长或者字典序小的作为暂存结果
### 代码
```java
class Solution {
    public String findLongestWord(String s, List<String> dictionary) {
        String ans = "";
        for (String word : dictionary) {
            if (isSubSequence(s, word)) {
                if (word.length() > ans.length() || word.length() == ans.length() && word.compareTo(ans) < 0) {
                    ans = word;
                }
            }
        }

        return ans;
    }

    private boolean isSubSequence(String s, String word) {
        int wi = 0, si = 0;
        while (wi < word.length() && si < s.length()) {
            if (word.charAt(wi) == s.charAt(si)) {
                wi++;
                si++;
            } else {
                si++;
            }
        }

        return wi == word.length();
    }
}
```
## 解法三
### 思路
- 在解法二的结构上，优化isSubSequence函数，使用String的indexOf函数来判断字典元素当前字符是否是s的一部分
- 这个一部分还需要不停的缩短s的判断范围，而每一次判断的这个起始坐标（也就是s每次判断的范围）都基于上一次indexOf找到的字符的坐标再+1，这样加快了判断的效率
### 代码
```java
class Solution {
    public String findLongestWord(String s, List<String> dictionary) {
        String ans = "";
        for (String word : dictionary) {
            if (isSubSequence(s, word)) {
                if (word.length() > ans.length() || word.length() == ans.length() && word.compareTo(ans) < 0) {
                    ans = word;
                }
            }
        }
        
        return ans;
    }

    private boolean isSubSequence(String s, String word) {
        int index = -1;
        for (int i = 0; i < word.length(); i++) {
            index = s.indexOf(word.charAt(i), index + 1);
            
            if (index == -1) {
                return false;
            }
        }
        
        return true;
    }
}
```