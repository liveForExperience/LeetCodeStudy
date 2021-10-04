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
# [LeetCode_1758_生成交替二进制字符串的最少操作数](https://leetcode-cn.com/problems/minimum-changes-to-make-alternating-binary-string/)
## 解法
### 思路
- 分别以0和1为起始，遍历字符串，并计算需要转换的次数，最后取最小值
### 代码
```java
class Solution {
    public int minOperations(String s) {
        char[] cs = s.toCharArray();
        boolean zero = true, one = true;
        int a = 0, b = 0;
        for (char c : cs) {
            if (c == '0') {
                a += zero ? 0 : 1;
                b += one ? 1 : 0;
            } else {
                a += zero ? 1 : 0;
                b += one ? 0 :  1;
            }
            
            zero = !zero;
            one = !one;
        }
        
        return Math.min(a, b);
    }
}
```