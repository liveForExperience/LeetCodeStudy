# [LeetCode_1163_按字典序排在最后的子串](https://leetcode.cn/problems/last-substring-in-lexicographical-order/)
## 解法
### 思路
双指针
- 字符串中的前缀子串，一定不是字典序排在最后的子串，因为任何一个基于前缀子串延伸的子串，都比该前缀子串字典序大
- 初始化2个坐标，i和j
  - 坐标i代表以i为起始坐标的后缀子串，且该子串应是当前判断过程中字典序最大的
  - 坐标j代表作比较的目标后缀子串的起始坐标
- 基础逻辑就是，s[i]和s[j]进行比较
  - 如果s[i] < s[j]，那么i = j
  - 否则s[i] > s[j]，那么j就后移
- 关键是j怎么后移，可以通过考虑2个字符串的起始相同字符长度k来处理
  - 如果从i和j比较的第k个字符开始出现不同
  - 如果c[i + k] < c[j + k]
    - i + k > j，那么j就移动到j = i + k + 1，因为[i, i + k]区间里肯定不会有最大字典序
    - i + k <= j，那么 j = j + 1
  - 如果c[i + k] > c[j + k]
    - 那么同理，[j, j + k]区间里也一定不会有字典序最大的子串，j = j + k + 1即可
### 代码
```java
class Solution {
    public String lastSubstring(String s) {
        int i = 0, j = 1, n = s.length();
        while (j < n) {
            int k = 0;
            while (j + k < n && s.charAt(i + k) == s.charAt(j + k)) {
                k++;
            }
            
            if (j + k < n && s.charAt(i + k) < s.charAt(j + k)) {
                int tmp = i;
                i = j;
                j = Math.max(j + 1, tmp + k + 1);
            } else {
                j = j + k + 1;
            }
        }
        
        return s.substring(i, n);
    }
}
```
# [LeetCode_1048_最长字符串链]()
## 解法
### 思路
dfs+记忆化搜索
- 假设s作为字符串链的最后一个字符串，该链的长度为dfs(s)
- 那么根据题意，应有字符串t，比s少一个字符，其对应链为dfs(t)，且dfs(s) = dfs(t) + 1
- 如上可知，该问题可以拆分成连续子问题，故可以通过递归来解决
- 为了方便计算，并避免重复计算相同情况下的t，比如aca和aba，减去中间的字符都是aa，所以可以通过map来减枝这种情况
- 递归
  - 退出条件：字符串长度为0 | 记事本中已记录
  - 过程：遍历字符串，依次尝试删除字符，然后向下递归，返回时候通过表达式计算出当前层字符串的最大长度`dfs(s) = dfs(t) + 1`
  - 将遍历得到的最大长度值存储到记事本中，并作为当前层结果返回
- 最外层，遍历words数组，依次将每个字符串作为字符串链的字符串进行搜索，并记录返回的长度，与暂存的最大值进行比较
- 遍历结束后返回暂存的最大值作为结果返回即可
### 代码
```java
class Solution {
    public int longestStrChain(String[] words) {
        int max = 0;
        Map<String, Integer> memo = new HashMap<>();
        for (String word : words) {
            memo.put(word, 0);
        }
        
        for (String word : words) {
            max = Math.max(max, dfs(word, memo));
        }

        return max;
    }

    private int dfs(String s, Map<String, Integer> memo) {
        if (s.length() == 0) {
            return 0;
        }

        if (memo.get(s) > 0) {
            return memo.get(s);
        }

        int max = 1;
        for (int i = 0; i < s.length(); i++) {
            String t = s.substring(0, i) + s.substring(i + 1);
            if (!memo.containsKey(t)) {
                continue;
            }
            
            max = Math.max(max, dfs(t, memo) + 1);
        }

        memo.put(s, max);
        return max;
    } 
}
```