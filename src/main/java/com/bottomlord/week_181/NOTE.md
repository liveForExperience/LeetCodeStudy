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
# [LeetCode_1774_统计同构字符串的个数](https://leetcode.cn/problems/count-number-of-homogenous-substrings/)
## 解法
### 思路
- 将字符串切分成一个个同构字符子串
- 每一个字符子串计算从1开始到子串长度的同构字符串个数，然后累加起来
- 计算的方式是sum(n - i + 1)，n是字符串串长度，i是小于n大于0的子串长度
- 处理过程就是遍历字符串，累加时记得取模1000000007
### 代码
```java
class Solution {
    public int countHomogenous(String s) {
        int index = 0, n = s.length(), ans = 0, mod = 1000000007;
        while (index < n) {
            char c = s.charAt(index);

            int count = 0;
            while (index < n && c == s.charAt(index)) {
                index++;
                count++;
            }

            for (int i = 1; i <= count; i++) {
                ans += (count - i) + 1;
                ans %= mod;
            }
        }

        return ans;
    }
}
```
# [LeetCode_2027_转换字符串的最少操作次数](https://leetcode.cn/problems/minimum-moves-to-convert-string/)
## 解法
### 思路
- 遍历字符串，当遇到x的时候就修改当前及之后的2个字符为o
- 然后继续遍历
### 代码
```java
class Solution {
    public int minimumMoves(String s) {
        char[] cs = s.toCharArray();
        int count = 0;
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == 'X') {
                cs[i] = 'O';

                if (i + 1 < cs.length) {
                    cs[i + 1] = 'O';
                }

                if (i + 2 < cs.length) {
                    cs[i + 2] = 'O';
                }

                i += 2;
                count++;
            }
        }

        return count;
    }
}
```
# [LeetCode_1750_删除字符串两端相同字符后的最短长度](https://leetcode.cn/problems/minimum-length-of-string-after-deleting-similar-ends/)
## 解法
### 思路
双指针模拟
### 代码
```java
class Solution {
    public int minimumLength(String s) {
        int left = 0, right = s.length() - 1;

        while (left < right) {
            char lc = s.charAt(left),
                 rc = s.charAt(right);

            if (lc != rc) {
                return right - left + 1;
            }

            while (left <= right && s.charAt(left) == lc) {
                left++;
            }
            
            while (left <= right && s.charAt(right) == rc) {
                right--;
            }
        }

        return right - left + 1;
    }
}
```
# [LeetCode_2451_差值数组不同的字符串](https://leetcode.cn/problems/odd-string-difference/)
## 解法
### 思路
模拟
- 2层循环
  - 第一层遍历差值比较的坐标位置
  - 第二层遍历words字符串数组
- 第二层内部使用map来记录diff和对应的字符串列表
- 然后判断是否存在只有1个字符串的diff，如果有就返回座位结果
### 代码
```java
class Solution {
    public String oddString(String[] words) {
        if (words == null || words.length == 0) {
            return null;
        }

        int wordLen = words[0].length();
        for (int i = 1; i < wordLen; i++) {
            Map<Integer, List<String>> map = new HashMap<>();
            
            for (String word : words) {
                map.computeIfAbsent(getDiff(word, i), x -> new ArrayList<>()).add(word);
            }

            for (Map.Entry<Integer, List<String>> entry : map.entrySet()) {
                if (entry.getValue().size() == words.length) {
                    break;
                }
                
                if (entry.getValue().size() == 1) {
                    return entry.getValue().get(0);
                }
            }
        }

        return null;
    }
    
    private int getDiff(String word, int index) {
        return word.charAt(index) - word.charAt(index - 1);
    }
}
```