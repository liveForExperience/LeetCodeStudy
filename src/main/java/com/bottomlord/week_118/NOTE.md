# [LeetCode_273_整数转换英文表示](https://leetcode-cn.com/problems/integer-to-english-words/)
## 解法
### 思路
- 将数字按照每3位一分来拆分，依次为billion，million和thousand
- 每三位中再按照不同位数的值进行映射
- 单词与单词之间需要拼接空格
- 在准备阶段需要先将数字与单词的映射管理梳理出来
### 代码
```java
class Solution {
    private Map<Integer, String> map = new HashMap<>();
    private Map<Integer, String> map2 = new HashMap<>();

    {
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        map.put(4, "Four");
        map.put(5, "Five");
        map.put(6, "Six");
        map.put(7, "Seven");
        map.put(8, "Eight");
        map.put(9, "Nine");
        map.put(10, "Ten");
        map.put(11, "Eleven");
        map.put(12, "Twelve");
        map.put(13, "Thirteen");
        map.put(14, "Fourteen");
        map.put(15, "Fifteen");
        map.put(16, "Sixteen");
        map.put(17, "Seventeen");
        map.put(18, "Eighteen");
        map.put(19, "Nineteen");
        map.put(20, "Twenty");
        map.put(30, "Thirty");
        map.put(40, "Forty");
        map.put(50, "Fifty");
        map.put(60, "Sixty");
        map.put(70, "Seventy");
        map.put(80, "Eighty");
        map.put(90, "Ninety");
        map2.put(0, "");
        map2.put(1, "Thousand");
        map2.put(2, "Million");
        map2.put(3, "Billion");
    }

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }

        int index = 0;
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            int cur = num % 1000;
            if (cur != 0) {
                sb.insert(0, " ");
                sb.insert(0, map2.get(index));

                int n = cur % 100;
                if (n == 0) {

                } else if (map.containsKey(n)) {
                    sb.insert(0, " ");
                    sb.insert(0, map.get(n));
                } else {
                    sb.insert(0, " ");
                    sb.insert(0, map.get(n % 10));
                    sb.insert(0, " ");
                    sb.insert(0, map.get(n / 10 * 10));
                }

                if (cur / 100 * 100 != 0) {
                    sb.insert(0, " ");
                    sb.insert(0, map.get(cur / 100) + " Hundred");
                }
            }

            num /= 1000;
            index++;
        }
        
        return sb.toString().trim();
    }
}
```
# [LeetCode_29_两数相除](https://leetcode-cn.com/problems/divide-two-integers/)
## 解法
### 思路
- 因为不用考虑小数部分，所以使用减法来实现
- 特殊情况：
  - 被除数为0，直接返回0
  - 被除数是int最小值且除数是-1，那么商就是int最大值
- 判断正负号
- 被除数通过除数累减的方式，会超时，所以加速累减的过程
- 可以通过左移的方式，将除数*2，然后和被除数比较，只要不大于被除数就一直左移，超过了就累减，然后这个过程不断循环
- 需要注意int最大最小值，所以需要将除数和被除数先转换成long值进行操作，除了int最小值除以-1的特殊情况
### 代码
```java
class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }

        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        boolean negative = dividend < 0 ^ divisor < 0;
        long d = dividend, r = divisor;
        d = Math.abs(d);
        r = Math.abs(r);

        int result = 0;
        while (d >= r) {
            long num = r, count = 1;

            while (d - num >= num) {
                num <<= 1;
                count <<= 1;
            }

            d -= num;
            result += count;
        }

        return negative ? -result : result;
    }
}
```
# [LeetCode_412_FizzBuzz](https://leetcode-cn.com/problems/fizz-buzz/)
## 解法
### 思路
遍历，计算，存储
### 代码
```java
class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            if (i % 3 == 0) {
                sb.append("Fizz");
            }

            if (i % 5 == 0) {
                sb.append("Buzz");
            }

            list.add(sb.length() == 0 ? "" + i : sb.toString());
        }

        return list; 
    }
}
```
## 解法二
### 思路
不new新的StringBuilder对象，直接add字符串常量
### 代码
```java
class Solution {
  public List<String> fizzBuzz(int n) {
    List<String> ans = new ArrayList<>();
    for (int i = 1; i <= n; i++) {
      ans.add(get(i));
    }
    return ans;
  }

  private String get(int i) {
    if (i % 3 == 0 && i % 5 == 0) {
      return "FizzBuzz";
    } else if (i % 3 == 0) {
      return "Fizz";
    } else if (i % 5 == 0) {
      return "Buzz";
    } else {
      return "" + i;
    }
  }
}
```
# [LeetCode_1796_字符串中第二大的数字](https://leetcode-cn.com/problems/second-largest-digit-in-a-string/)
## 解法
### 思路
循环模拟
### 代码
```java
class Solution {
  public int secondHighest(String s) {
    char[] cs = s.toCharArray();
    Integer one = null, two = null;
    for (char c : cs) {
      if (!Character.isDigit(c)) {
        continue;
      }

      int num = c - '0';

      if (one == null) {
        one = num;
        continue;
      }

      if (num > one) {
        two = one;
        one = num;
        continue;
      }

      if (num < one && (two == null || num > two)) {
        two = num;
      }
    }

    return two == null ? -1 : two;
  }
}
```
## 解法二
### 思路
- 遍历字符串，使用布尔数组记录遍历到的数字，下标对应值
- 遍历布尔数组，找到第二大的值返回，否则返回-1
### 代码
```java
class Solution {
    public int secondHighest(String s) {
        boolean[] bucket = new boolean[10];
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                bucket[s.charAt(i) - '0'] = true;
            }
        }
        
        int count = 0;
        for (int i = 9; i >= 0; i--) {
            if (bucket[i]) {
                count++;
            }
            
            if (count == 2) {
                return i;
            }
        }
        
        return -1;
    }
}
```