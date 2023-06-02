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