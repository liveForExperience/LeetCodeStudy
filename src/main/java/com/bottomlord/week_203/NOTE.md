# [LeetCode_2697_字典序最小回文串](https://leetcode.cn/problems/lexicographically-smallest-palindrome/)
## 解法
### 思路
双指针模拟
### 代码
```java
class Solution {
    public String makeSmallestPalindrome(String s) {
        int n = s.length(), head = 0, tail = n - 1;
        char[] cs = s.toCharArray();
        while (head <= tail) {
            char a = cs[head], b = cs[tail];
            if (a != b) {
                cs[head] = cs[tail] = a < b ? a : b;
            }

            head++;
            tail--;
        }
        
        return new String(cs);
    }
}
```
# [LeetCode_2559_统计范围内的元音字符串数](https://leetcode.cn/problems/count-vowel-strings-in-ranges/)
## 解法
### 思路
前缀和
### 代码
```java
class Solution {
    public int[] vowelStrings(String[] words, int[][] queries) {
        int n = words.length;
        int[] sums = new int[n + 1];
        for (int i = 0; i < words.length; i++) {
            sums[i + 1] = sums[i] + (isVowelStr(words[i]) ? 1 : 0);
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = sums[queries[i][1] + 1] - sums[queries[i][0]];
        }
        
        return ans;
    }

    private boolean isVowelStr(String str) {
        return isVowel(str.charAt(0)) && isVowel(str.charAt(str.length() - 1));
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
```
# [LeetCode_1156_单字符重复子串的最大长度](https://leetcode.cn/problems/swap-for-longest-repeated-character-substring/)
## 解法
### 思路
- 题目允许交换一个字符，从而尝试将重复子串增长，那么就可以将整个字符串拆分成如下几部分
  - 一个搜索到的已存在的连续子串A
  - 一个准备交换的，与搜索到的字母一致但不在重复子串里的字符c
  - 一个可能在交换后可以与搜索到的连续子串连接上的连续子串B
- 那么在交换后，可能的最大长度就是A + c + B
- 围绕如上的逻辑，可以做如下几件事
  - 确定每个字母在字符串中的个数，这个个数能帮助我们判断是否存在c，即个数cnt - 连续子串长度n，是否大于0
  - 循环字符串，确定连续子串A
  - 在判断是否存在c后，再判断c交换后，是否存在能连接上的B
  - 然后计算A + c + B
- 需要注意，B这个子串的长度可能会重复计算c，因为c的位置在逻辑中并不关心，所以可以通过个数和A + c + B的最小长度来规避这个问题
### 代码
```java
class Solution {
    public int maxRepOpt1(String text) {
        int[] cnt = new int[26];
        char[] cs = text.toCharArray();
        for (char c : cs) {
            cnt[c - 'a']++;
        }

        int n = cs.length, max = 0;
        for (int i = 0; i < n;) {
            int j = i;
            char c = cs[i];
            while (j < n && c == cs[j]) {
                j++;
            }

            int cur = j - i;
            if (cnt[c - 'a'] > cur && (i > 0 && i < n)) {
                max = Math.max(cur + 1, max);
            }

            cur++;
            int k = j + 1;
            while (k < n && c == cs[k]) {
                k++;
            }
            
            max = Math.max(max, Math.min(k - i, cnt[c - 'a']));
            
            i = j;
        }
        
        return max;
    }
}
```