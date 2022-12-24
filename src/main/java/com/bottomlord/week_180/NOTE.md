# [LeetCode_749_隔离病毒](https://leetcode.cn/problems/contain-virus/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_940_不同子序列](https://leetcode.cn/problems/distinct-subsequences-ii/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_1754_构造字典序最大的合并字符串](https://leetcode.cn/problems/largest-merge-of-two-strings/)
## 解法
### 思路
双指针：
- 3种情况
    - word1的字符大于word2的首字符，选取word1的字符
    - word2的字符大于word1的首字符，选取word2的字符
    - 2个字符相等的时候，判断后缀字符串的字典序大小，谁大，就用谁的首字符
- 判断后缀字符串的字典序，就是循环遍历判断
### 代码
```java
class Solution {
    public String largestMerge(String word1, String word2) {
        int i = 0, j = 0, n1 = word1.length(), n2 = word2.length();
        StringBuilder sb = new StringBuilder();
        while (i < n1 || j < n2) {
            if (judge(word1, i, n1, word2, j, n2)) {
                sb.append(word1.charAt(i++));
            } else {
                sb.append(word2.charAt(j++));
            }
        }

        return sb.toString();
    }

    private boolean judge(String word1, int i, int n1, String word2, int j, int n2) {
        if (i >= n1) {
            return false;
        } else if (j >= n2) {
            return true;
        } else {
            return word1.substring(i).compareTo(word2.substring(j)) >= 0;
        }
    }
}
```
# [LeetCode_2423_删除字符使频率相同](https://leetcode.cn/problems/remove-letter-to-equalize-frequency/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public boolean equalFrequency(String word) {
        int[] bucket = new int[26];
        char[] cs = word.toCharArray();
        for (char c : cs) {
            bucket[c - 'a']++;
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        int one = 0;
        for (int num : bucket) {
            if (num == 0) {
                continue;
            }
            
            if (num == 1) {
                one++;
            }
            
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        if (map.size() > 2) {
            return false;
        }
        
        if (map.size() == 1) {
            if (one > 0) {
                return true;
            }

            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                return entry.getValue() == 1;
            }
        }
        
        one = 0;
        
        int x = 0, xn = 0, y = 0, yn = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getKey() == 1) {
                one = entry.getValue();
            }
            
            if (x == 0) {
                x = entry.getKey();
                xn = entry.getValue();
            } else {
                y = entry.getKey();
                yn = entry.getValue();
            }
        }
        
        if (one == 1) {
            return true;
        }
        
        if (Math.abs(x - y) != 1) {
            return false;
        }

        if (x > y) {
            return xn == 1;
        } else {
            return yn == 1;
        }
    }
}
```
# [LeetCode_2427_公因子的数目](https://leetcode.cn/problems/number-of-common-factors/)
## 解法
### 思路
- 计算出最大公约数
- 从做大公约数开始递减判断是否是公因数，直到1为止
### 代码
```java
class Solution {
    public int commonFactors(int a, int b) {
        int gcd = gcd(a, b), cnt = 0;
        for (int i = gcd; i >= 1; i--) {
            if (a % i == 0 && b % i == 0) {
                cnt++;
            }
        }

        return cnt;
    }

    private int gcd(int x, int y) {
        return x % y == 0 ? y : gcd(y, x % y);
    }
}
```