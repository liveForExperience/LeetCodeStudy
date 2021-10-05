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
# [LeetCode_1763_最长的美好子字符串](https://leetcode-cn.com/problems/longest-nice-substring/)
## 解法
### 思路
嵌套遍历
- 外层确定可能结果的起始坐标
- 内层遍历确定字符串的结尾坐标
- 2层循环确定的字符串，判断其是否是符合题目要求的，然后更新最大长度
- 循环次数基于更新的最大长度动态变化，从而减少循环次数
### 代码
```java
class Solution {
  public String longestNiceSubstring(String s) {
    int n = s.length(), max = 1;
    String ans = "";
    for (int i = 0; i < n - max; i++) {
      for (int j = i + max; j < n; j++) {
        if (isNice(s.substring(i, j + 1))) {
          if (j + 1 - i > max) {
            max = j + 1 - i;
            ans = s.substring(i, j + 1);
          }
        }
      }
    }

    return ans;
  }

  private boolean isNice(String s) {
    Set<String> set = new HashSet<>();
    for (int i = 0; i < s.length(); i++) {
      set.add("" + s.charAt(i));
    }

    for (int i = 0; i < s.length(); i++) {
      if (!set.contains(("" + s.charAt(i)).toUpperCase()) ||
              !set.contains(("" + s.charAt(i)).toLowerCase())) {
        return false;
      }
    }

    return true;
  }
}
```
# [LeetCode_284_顶端迭代器](https://leetcode-cn.com/problems/peeking-iterator/)
## 解法
### 思路
- 使用一个peek来暂存顶部元素
- 在hasNext和next函数时，先判断peek是否不为空，如果不为空
  - hasNext将peek置为null，然后返回true
  - next将peek置为null，并返回之前peek的值
### 代码
```java
class PeekingIterator implements Iterator<Integer> {
    private Integer peek;
    private Iterator<Integer> iterator;
    public PeekingIterator(Iterator<Integer> iterator) {
        this.iterator = iterator;
    }

    public Integer peek() {
        if (!hasNext()) {
            return null;
        }
        
        if (peek == null) {
            peek = next();
            return peek;
        }
        
        return peek;
    }

    @Override
    public Integer next() {
        if (peek != null) {
            Integer ans = peek;
            peek = null;
            return ans;
        }

        if (!hasNext()) {
            return null;
        }

        return iterator.next();
    }

    @Override
    public boolean hasNext() {
        if (peek != null) {
            return true;
        }
        return iterator.hasNext();
    }
}
```