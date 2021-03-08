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