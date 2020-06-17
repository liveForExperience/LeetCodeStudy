# LeetCode_41_缺失的第一个正数
## 题目
给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。

示例 1:
```
输入: [1,2,0]
输出: 3
```
示例 2:
```
输入: [3,4,-1,1]
输出: 2
```
示例 3:
```
输入: [7,8,9,11,12]
输出: 1
```
提示：
```
你的算法的时间复杂度应为O(n)，并且只能使用常数级别的额外空间。
```
## 解法
### 思路
- 如果数组中不包含1，那么答案就是1
- 如果数组长度是1，且不包含1，那么答案就是2
- 找到缺失的最小正数，所以负数和0是不需要考虑的，在如上两步都不符合的情况下，将负数和0都转换为1
- 缺失的最小正整数有两种情况：
    - 如果处理完后的n个元素正好是`[1, n]，那么答案就是`n + 1`
    - 否则就是`[1, n]`范围内的数
- 搜索一次数组，使用数组下标对应数字（0代表n），正负符号来代表是否搜索到。
### 代码
```java
class Solution {
    public int firstMissingPositive(int[] nums) {
        boolean hasOne = false;
        int len = nums.length;
        for (int num : nums) {
            if (num == 1) {
                hasOne = true;
                break;
            }
        }

        if (!hasOne) {
            return 1;
        }

        if (len == 1) {
            return 2;
        }

        for (int i = 0; i < len; i++) {
            if (nums[i] <= 0) {
                nums[i] = 1;
            }
        }

        for (int i = 0; i < len; i++) {
            int num = Math.abs(nums[i]);

            if (num < len) {
                nums[num] = -Math.abs(nums[num]);
            }

            if (num == len) {
                nums[0] = -Math.abs(nums[0]);
            }
        }

        for (int i = 1; i < len; i++) {
            if (nums[i] > 0) {
                return i;
            }
        }

        if (nums[0] > 0) {
            return len;
        }

        return len + 1;
    }
}
```
# LeetCode_43_字符串相乘
## 题目
给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。

示例 1:
```
输入: num1 = "2", num2 = "3"
输出: "6"
```
示例 2:
```
输入: num1 = "123", num2 = "456"
输出: "56088"
```
说明：
```
num1 和 num2 的长度小于110。
num1 和 num2 只包含数字 0-9。
num1 和 num2 均不以零开头，除非是数字 0 本身。
不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
```
## 解法
### 思路
- 根据两个字符串的长度初始化一个int数组，长度为两个字符串长度之和
- 模拟乘法过程，嵌套循环：
    - 从字符串尾部开始往前确定数字
    - 内外层的数字想乘，分别记录余数和商，商作为进位
    - 余数+上一次的进位，得到一个和，再判断这个和是否大于10，如果是就继续处理
    - 将本次的商作为下一次的进位
    - 内层循环结束后，判断当前进位是否不为0，如果是，就继续填充一位值
    - 在循环过程中，外层数字位数变动一次，就需要变动一下填充值的开始位置，该位置需要向低位开始
### 代码
```java
class Solution {
    public String multiply(String num1, String num2) {
        String zero = "0";
        if (zero.equals(num1) || zero.equals(num2)) {
            return zero;
        }

        int[] arr = new int[num1.length() + num2.length()];
        int start = arr.length - 1;
        for (int i = num1.length() - 1; i >= 0; i--) {
            int a = num1.charAt(i) - '0', index = start, carry = 0;
            for (int j = num2.length() - 1; j >= 0; j--) {
                int b = num2.charAt(j) - '0';
                int c = a * b;
                int remainder = c % 10 + carry;
                carry = c / 10;
                int sum = arr[index] + remainder;
                arr[index] = sum % 10;
                carry += sum / 10;
                index--;
            }
            
            if (carry > 0) {
                arr[index] += carry;
            }
            
            start--;
        }
        
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (int num : arr) {
            if (num != 0) {
                break;
            }
            index++;
        }
        
        for (; index < arr.length; index++) {
            sb.append(arr[index]);
        }
        return sb.toString();
    }
}
```
# LeetCode_44_通配符匹配
## 题目
给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
```
'?' 可以匹配任何单个字符。
'*' 可以匹配任意字符串（包括空字符串）。
两个字符串完全匹配才算匹配成功。
```
说明:
```
s 可能为空，且只包含从 a-z 的小写字母。
p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
```
示例 1:
```
输入:
s = "aa"
p = "a"
输出: false
解释: "a" 无法匹配 "aa" 整个字符串。
```
示例 2:
```
输入:
s = "aa"
p = "*"
输出: true
解释: '*' 可以匹配任意字符串。
```
示例 3:
```
输入:
s = "cb"
p = "?a"
输出: false
解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
```
示例 4:
```
输入:
s = "adceb"
p = "*a*b"
输出: true
解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
```
示例 5:
```
输入:
s = "acdcb"
p = "a*c?b"
输出: false
```
## 解法
### 思路
动态规划：
- `dp[i][j]`：结尾为第i个字符的匹配字符串p和结尾为第j个字符的字符串s之间的匹配结果
- `base case`：
    - p和s的长度都为0，返回true
    - p的长度为0或s的长度为0，返回false
    - p和s完全相同，返回true
    - p为`*`，返回true
- 状态转移方程：
    - 如果p的当前字符是`*`：如果前一个字符为结尾的字符串的匹配结果是true，该字符之后的所有字符为结尾的字符串的匹配结果都为true
    - 如果p的当前字符是`?`：那么当前结果依赖于`dp[i - 1][j - 1]`
    - 如果p的当前字符是其他：那么当前结果依赖于`dp[i - 1][j - 1] && p[i - 1] == s[j - 1]（字符串下标从0开始，dp从1开始，所以dp[i]对应p[i - 1]）`
### 代码
```java
class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }

        int sLen = s.length(), pLen = p.length();
        if (s.equals(p)) {
            return true;
        }
        
        if ("*".equals(p)) {
            return true;
        }
        
        if (sLen == 0 || pLen == 0) {
            return false;
        }
        
        boolean[][] dp = new boolean[pLen + 1][sLen + 1];
        dp[0][0] = true;

        for (int i = 1; i < pLen + 1; i++) {
            int j = 1;
            if (p.charAt(i - 1) == '*') {
                while (!dp[i - 1][j - 1] && j < sLen + 1) {
                    j++;
                }

                dp[i][j - 1] = dp[i - 1][j - 1];

                while (j < sLen + 1) {
                    dp[i][j++] = true;
                }
            } else if (p.charAt(i - 1) == '?') {
                for (; j < sLen + 1; j++) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            } else {
                for (; j < sLen + 1; j++) {
                    dp[i][j] = dp[i - 1][j - 1] && p.charAt(i - 1) == s.charAt(j - 1);
                }
            }
        }
        
        return dp[pLen][sLen];
    }
}
```
## 解法二
### 思路
双指针：
- 初始化变量：
    - 指针：si对应字符串，pi对应模式串
    - 起始位置：sStart和pStart，起始位置用来确定，当匹配到`*`的时候，那么就记录这次匹配的位置，然后假设`*`匹配的是空字符串，然后继续移动pi来判断下一组是否匹配。如果是不匹配的话，就利用记录的pStart来重新判断，也就是用这个最近的`*`将不能匹配的字符覆盖掉
    - 字符串的长度，sLen和pLen
        - 使用sLen和si来确定匹配的退出条件，也就是指针遍历完s的长度
        - 使用PLen和pi来确定是否匹配完成，也就是执行完了以后，pi是否遍历完了整个p的长度
- 循环：
    - 循环条件：si是否等于sLen，也就是si是否遍历完s字符串
    - 过程：
        - 防止越界，如下条件都要考虑`pi < pLen`
        - 如果当前`s[si] == p[pi] || p[pi] == '?'`：那么就判断为两个字符匹配，两个指针+1，跳过
        - 如果当前`p[pi] == ‘*’`：
            - 那么先从`p[pi]`匹配的是空字符串开始考虑
            - 记录当前si和pi作为sStart和pStart
            - 同时pi+1
        -  如果以上两种情况都不符合，那代表当前两个字符串不匹配，且模式串也不是特殊字符：
            - 如果sStart不是-1，就代表之前有匹配过一次`*`，就从这个`*`匹配的位置开始，重新判断，`si = sStart + 1`
            - 如果sStart是-1，也就是没有遇到过`*`，就判断无法匹配
- 循环结束后，再判断`pi`是否遍历完了`p`，如果没有，但剩下的都是`*`，那匹配，否则就不匹配
### 代码
```java
class Solution {
    public boolean isMatch(String s, String p) {
        int si = 0, pi = 0, sStart = -1, pStart = -1, sLen = s.length(), pLen = p.length();
        while (si < sLen) {
            if (pi < pLen && (s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '?')) {
                si++;
                pi++;
            } else if (pi < pLen && p.charAt(pi) == '*') {
                sStart = si;
                pStart = pi++;
            } else if (sStart != -1) {
                si = ++sStart;
                pi = pStart + 1;
            } else {
                return false;
            }
        }

        while (pi < pLen && p.charAt(pi) == '*') {
            pi++;
        }

        return pi == pLen;
    }
}
```
# LeetCode_45_跳跃游戏II
## 题目
给定一个非负整数数组，你最初位于数组的第一个位置。

数组中的每个元素代表你在该位置可以跳跃的最大长度。

你的目标是使用最少的跳跃次数到达数组的最后一个位置。

示例:
```
输入: [2,3,1,1,4]
输出: 2
解释: 跳到最后一个位置的最小跳跃数是 2。
     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
```
说明:
```
假设你总是可以到达数组的最后一个位置。
```
## 失败解法
### 原因
超时
### 思路
递归
### 代码
```java
class Solution {
    public int jump(int[] nums) {
        return recurse(nums, 0, 0);
    }

    private int recurse(int[] nums, int index, int count) {
        if (index == nums.length - 1) {
            return count;
        }
        
        if (index >= nums.length - 1) {
            return Integer.MAX_VALUE;
        }
        
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= nums[index]; i++) {
            min = Math.min(min, recurse(nums, index + i, count + 1));
        }
        
        return min;
    }
}
```
## 解法
### 思路
贪心：
- 从最后一个位置向前倒推
- 每次找到最远的一个可以到达当前位置的坐标
- 然后更新当前位置到那个最远位置
- 重复如上的步骤，直到当前位置为坐标0为止
### 代码
```java
class Solution {
    public int jump(int[] nums) {
        int count = 0, position = nums.length - 1;
        while (position > 0) {
            for (int i = 0; i < position; i++) {
                if (i + nums[i] >= position) {
                    position = i;
                    count++;
                    break;
                }
            }
        }
        return count;
    }
}
```
## 解法二
### 思路
贪心：
- 初始化变量：
    - 初始化一个最大值`max`用来统计当前能够跳跃的范围内，最大能到达的距离
    - 初始化一个终点变量`end`，记录当前这次跳跃的终点坐标
- 从第一个元素开始遍历数组
    - 每次都将当前值`nums[i]`和当前坐标`i`的和与暂存的最大值比较，如果大于，就刷新这个值
    - 当坐标`i`移动到`end`，刷新`end`为`max`，同时记一次数，这次计数可以理解为，记录从起跳位置到`max`对应位置这个区间的完成
    - 遍历到倒数第二个元素为止，因为计数的时候，记录的是起跳为止到结束位置的区间的完成，而最后一次起跳一定是在最后一个坐标之前的某个位置，所以只要在坐标到达倒数第二个区间结束时，也就也就意味最后一个区间意味最后一个区间会在那个时候被计数
### 代码
```java
class Solution {
    public int jump(int[] nums) {
        int max = 0, end = 0, count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            max = Math.max(max, nums[i] + i);
            if (i == end) {
                end = max;
                count++;
            }
        }
        
        return count;
    }
}
```