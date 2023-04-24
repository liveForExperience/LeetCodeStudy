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