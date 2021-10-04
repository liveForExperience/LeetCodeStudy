# [LeetCode_482_秘钥格式化](https://leetcode-cn.com/problems/license-key-formatting/)
## 解法
### 思路
- 去除原有字符串的`-`
- 计算字母个数
- 算出分组的个数
  - 如果能够被k整除，就是len / k个区间
  - 如果不能被k整除，就是len / k + 1个区间，第一个区间是len % k个字母，剩下的都是k个
### 代码
```java
class Solution {
    public String licenseKeyFormatting(String s, int k) {
        StringBuilder sb = new StringBuilder();
        s = s.toUpperCase();
        char[] cs = s.toCharArray();
        for (char c : cs) {
            if (c != '-') {
                sb.append(c);
            }
        }
        cs = sb.toString().toCharArray();

        int n = sb.length();
        boolean flag = n % k == 0;
        
        int block = flag ? n / k : n / k + 1, first = flag ? k : n % k;

        StringBuilder ans = new StringBuilder();
        int index = 0;
        for (int i = 0; i < block; i++) {
            int loop = i == 0 ? first : k;
            for (int i1 = 0; i1 < loop; i1++) {
                ans.append(cs[index++]);
            }

            if (i != block - 1) {
                ans.append("-");
            }
        }
        
        return ans.toString();
    }
}
```