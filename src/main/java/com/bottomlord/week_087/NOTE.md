# [LeetCode_479_最大回文数乘积](https://leetcode-cn.com/problems/largest-palindrome-product/)
## 解法
### 思路
- 求出n位数乘积的最大值和最小值
- 通过最大值求出可能的回文数字的前面一半
- 然后依次生成回文数后，判断当前回文数是否可以被n位数整除
    - 如果找到就返回
    - 如果没有就累减一半的回文数，继续求
    - 在寻找过程中，如果用来取余的值的两两乘积小于回文数，则提前终止，寻找下一个
### 代码
```java
class Solution {
    public int largestPalindrome(int n) {
        if (n == 1) {
            return 9;
        }

        long up = (long)Math.pow(10, n) - 1, low = up / 10 + 1,
             max = up * up, half = (long) (max / Math.pow(10, n));

        while (true) {
            long cur = get(half);


            for (long i = up; i >= low; i--) {
                if (i * i < cur) {
                    break;
                }

                if (cur % i == 0) {
                    return (int)(cur % 1337);
                }
            }

            half--;
        }
    }

    private long get(long num) {
        String numStr = String.valueOf(num);
        return Long.parseLong(numStr + new StringBuilder(numStr).reverse().toString());
    }
}
```
# [LeetCode_483_最小好进制](https://leetcode-cn.com/problems/smallest-good-base/)
## 解法
### 思路
- n可以理解成连续`s + 1`位都为1的k进制值
- 通过k进制与10进制转换的关系，可以得到`n > k ^ s`
- 通过对k进制与10进制的转换，带入二项式定理则`n < (k + 1) ^ s`
- 如上推导参考[链接](https://leetcode-cn.com/problems/smallest-good-base/solution/shu-xue-fang-fa-fen-xi-dai-ma-by-zerotrac/)
- 那么就可以得到不等式`k ^ s < n < (k + 1) ^ s`，进而得到`k < n ^ (1 / s) < k + 1`
- 所以`n ^ (1 / s)`的整数部分就是k
- 又因为n的最大值不超过10 ^ 18次方，所以s最大不超过59，因为取最小进制2的59位1，得到的值将将超过`10 ^ 18`
- 那么就可以通过枚举获得k这个值
    - 从59开始倒着枚举s
    - 通过`n ^ (1 / s)`获得k进制值
    - 判断k是否小于等于1，如果是则直接跳过，因为没有小于2的进制
    - 然后计算s位都是1的k进制值，枚举s + 1次，每次都累乘k进制值再+1
    - 如果结果与n相同就返回
    - 如果枚举了59次都没有找到，那么就返回比n小1的数作为进制数
### 代码
```java
class Solution {
    public String smallestGoodBase(String n) {
        long target = Long.parseLong(n);
        for (int m = 59; m > 1; m--) {
            long k = (long) Math.pow(target, 1.0 / m);

            if (k <= 1) {
                continue;
            }

            long s = 0;
            for (int i = 0; i <= m; i++) {
                s = s * k + 1;
            }

            if (s == target) {
                return String.valueOf(k);
            }
        }

        return String.valueOf(target - 1);
    }
}
```