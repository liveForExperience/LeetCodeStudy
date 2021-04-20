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
# [LeetCode_525_连续数组](https://leetcode-cn.com/problems/contiguous-array/)
## 失败解法
### 原因
超时
### 思路
- 用两个数组，分别计算0和1这两个元素，在0到i坐标范围内的前缀和
- 2层嵌套循环
  - 外层确定窗口的长度
  - 内层确定窗口的左边界
- 找到2个数组在指定窗口中值一样的情况就直接返回该长度
### 代码
```java
class Solution {
    public int findMaxLength(int[] nums) {
        int len = nums.length, oneSum = 0, zeroSum = 0;
        int[] ones = new int[len], zeros = new int[len];
        
        for (int i = 0; i < len; i++) {
            ones[i] = oneSum += (nums[i] == 1 ? 1 : 0);
            zeros[i] = zeroSum += (nums[i] == 1 ? 0 : 1);
        }
        
        for (int l = len; l > 0; l--) {
            for (int i = 0; i + l <= len; i++) {
                int one = ones[i + l - 1] - ones[i] + (nums[i] == 1 ? 1 : 0),
                    zero = zeros[i + l - 1] - zeros[i] + (nums[i] == 1 ? 0 : 1);
                
                if (one == zero) {
                    return l;
                }
            }
        }
        
        return 0;
    }
}
```
## 解法
### 思路
- 因为数组中只有0和1两种数字，所以可以通过出现1记正数，出现0记负数的方式来判断当前数组的0和1是否平衡，记为count
- 然后通过额外的数组空间来记录count的值出现的最早坐标，也就是说额外的空间坐标对应count值
- 当发现记录的count值在数组对应坐标上有记录的坐标值，那就计算当前坐标与记录坐标的差，判断是否出现更大的距离
- 遍历结束后，返回长度结果
- 因为存在正负数，所以额外的数组是一个长度为2 * len + 1
- 因为初始就是count值为0的情况，所以需要初始化arr[len]的值，为了方便计算，就初始化为-1
- 既然初始化是-1，那么没有初始化就用-2来表示
### 代码
```java
class Solution {
    public int findMaxLength(int[] nums) {
        int count = 0, len = nums.length, ans = 0;
        int[] arr = new int[2 * len + 1];
        Arrays.fill(arr, -2);
        arr[len] = -1;
        for (int i = 0; i < len; i++) {
            count += nums[i] == 1 ? 1 : -1;
            
            if (arr[count + len] >= -1) {
                ans = Math.max(ans, i - arr[count + len]);
            } else {
                arr[count + len] = i;
            }
        }
        
        return ans;
    }
}
```
# [LeetCode_28_实现strStr](https://leetcode-cn.com/problems/implement-strstr/)
## 解法
### 思路
直接使用String的indexOf
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
自己实现indexOf
### 代码
```java
class Solution {
    public int strStr(String haystack, String needle) {
        if (Objects.equals("", needle)) {
            return 0;
        }
        
        if (Objects.equals("", haystack)) {
            return -1;
        }
        
        if (haystack.length() < needle.length()) {
            return -1;
        }
        
        if (haystack.length() == needle.length()) {
            return Objects.equals(haystack, needle) ? 0 : -1;
        }

        for (int i = 0; i < haystack.length(); i++) {
            int hi = i, ni = 0;
            while (hi < haystack.length() && ni < needle.length() && haystack.charAt(hi) == needle.charAt(ni)) {
                hi++;
                ni++;
            }

            if (ni == needle.length()) {
                return i;
            }
        }

        return -1;
    }
}
```
