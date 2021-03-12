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
# [LeetCode_484_寻找排列](https://leetcode-cn.com/problems/find-permutation/)
## 失败解法
### 原因
超时
### 思路
暴力回溯
### 代码
```java
class Solution {
    public int[] findPermutation(String s) {
        int len = s.length();
        if (len == 0) {
            return new int[0];
        }

        int[] ans = new int[len + 1];
        char[] cs = s.toCharArray();
        boolean[] memo = new boolean[len + 2];

        backTrack(0, cs, ans, memo);
        return ans;
    }

    private boolean backTrack(int index, char[] cs, int[] ans, boolean[] memo) {
        if (index == ans.length) {
            return true;
        }

        for (int i = 1; i <= ans.length; i++) {
            if (memo[i]) {
                continue;
            }
            
            if (index == 0) {
                memo[i] = true;
                ans[index] = i;
                boolean result = backTrack(index + 1, cs, ans, memo);
                if (result) {
                    return true;
                }

                ans[index] = 0;
                memo[i] = false;
                
            } else {
                if (cs[index - 1] == 'I') {
                    if (i > ans[index - 1]) {
                        memo[i] = true;
                        ans[index] = i;
                        boolean result = backTrack(index + 1, cs, ans, memo);
                        if (result) {
                            return true;
                        }

                        memo[i] = false;
                        ans[index] = 0;
                    }
                } else {
                    if (i < ans[index - 1]) {
                        memo[i] = true;
                        ans[index] = i;
                        boolean result = backTrack(index + 1, cs, ans, memo);
                        if (result) {
                            return true;
                        }

                        memo[i] = false;
                        ans[index] = 0;
                    }
                }
            }
        }

        return false;
    }
}
```
## 解法
### 思路
- 原始序列是严格的升序排列
- 假设是连续的`III`序列，那么原始序列就不需要变更
- 假设是连续的`DDD`序列，那么整个序列可以直接翻转，就成为了符合的序列
- 那么将2种序列合并一起处理时候，发现也不会产生冲突，升序就不处理数组，碰到需要降序了，就累计一下连续的降序序列，然后在对应的原始序列中将子序列做一下翻转，因为原始序列是严格递增的，所以反转后原看来子序列的最后一个数字，变成了现在的第一个数字，仍然比前一个数要大。
- 算法过程：
    - 初始化从1开始升序的数组ans
    - 定义一个指针i，用来遍历确定s字符串的密码字符
    - 如果是I，说明不需要处理ans的对应元素
    - 如果是D：
        - 暂存i的值为j，这个j对应的是第一个D这个字符，同时也相当于ans中升序的最后一个数字，也就是波峰
        - 然后在通过i，内层继续遍历s，找到所有连续的D，直到找到第一个I或者s遍历结束为止
        - 此时i对应的是ans中降序的最后一个数字，也就是波谷
        - 将波峰和波谷的这个区间的数字以中心点进行翻转
    - 处理完之后继续走，直到循环结束，返回ans
### 代码
```java
class Solution {
    public int[] findPermutation(String s) {
        int[] ans = new int[s.length() + 1];
        for (int i = 1; i <= s.length() + 1; i++) {
            ans[i - 1] = i;
        }

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'I') {
                continue;
            }

            int j = i;
            while (i < s.length() && s.charAt(i) == 'D') {
                i++;
            }

            reserve(ans, j, i);
        }

        return ans;
    }

    private void reserve(int[] arr, int start, int end) {
        while (start <= end) {
            int tmp = arr[start];
            arr[start] = arr[end];
            arr[end] = tmp;
            start++;
            end--;
        }
    }
}
```
# [LeetCode_487_最大连续1的个数II](https://leetcode-cn.com/problems/max-consecutive-ones-ii/)
## 解法
### 思路
- 定义2个指针
    - 指针i遍历数组
    - 指针j标记为0的坐标，初始化为-1，代表没有遇到
- 过程：
    - i遍历数组，并累加当前1的个数
    - 如果第一次遇到0，记录j，同时将当前坐标当成1继续累加
    - 如果第二次遇到0：
        - 使用暂存值与最大值比较，更新最大值
        - 计算当前0与第一次0的距离，更新暂存值
        - 更新坐标j
    - 循环结束，比较暂存值与最大值，返回最大值
### 代码
```java
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int j = -1, cur = 0, max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                cur++;
            }
            
            if (nums[i] == 0) {
                if (j == -1) {
                    j = i;
                    cur++;
                } else {
                    max = Math.max(max, cur);
                    cur = i - j;
                    j = i;
                }
            }
        }
        
        return Math.max(max, cur);
    }
}
```