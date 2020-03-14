# Interview_58I_翻转单词顺序
## 题目
输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，则输出"student. a am I"。

示例 1：
```
输入: "the sky is blue"
输出: "blue is sky the"
```
示例 2：
```
输入: "  hello world!  "
输出: "world! hello"
解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
```
示例 3：
```
输入: "a good   example"
输出: "example good a"
解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
```
说明：
```
无空格字符构成一个单词。
输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
```
## 解法
### 思路
- 遍历字符串
- 如果当前字符不为空格，开始内层循环
    - 拼接字符到内层中需要生成的单词中
    - 如果内层循环遇到空格，停止拼接，并将单词放入结果字符串的尾部
- 继续外层虚幻，如果遇到空格就跳过
### 代码
```java
class Solution {
    public String reverseWords(String s) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                StringBuilder word = new StringBuilder();
                for (; i < s.length(); i++) {
                    if (s.charAt(i) != ' ') {
                        word.append(s.charAt(i));
                    } else {
                        break;
                    }
                }
                
                ans.insert(0, word.append(' '));
            }
        }
        
        return ans.toString().trim();
    }
}
```
## 解法二
### 思路
- 根据空格切分字符串
- 从后往前遍历切分后的字符串数组
    - 如果遇到空字符串，跳过
    - 否则就拼接当前字符串，并添加一个空格在头部
- 遍历结束后，将拼接完成的字符串的头部空格去除并返回
### 代码
```java
class Solution {
    public String reverseWords(String s) {
        s = s.trim();
        
        if (s.length() == 0) {
            return "";
        }
        
        String[] ss = s.split(" ");
        if (ss.length == 1) {
            return ss[0];
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = ss.length - 1; i >= 0; i--) {
            if (!"".equals(ss[i])) {
                sb.append(" ").append(ss[i]);
            }
        }
        
        return sb.deleteCharAt(0).toString();
    }
}
```
# Interview_58II_左旋转字符串
## 题目
字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。

示例 1：
```
输入: s = "abcdefg", k = 2
输出: "cdefgab"
```
示例 2：
```
输入: s = "lrloseumgh", k = 6
输出: "umghlrlose"
```
限制：
```
1 <= k < s.length <= 10000
```
## 解法
### 思路
- 定义两个StringBuilder对象：
    - left：暂存前n个字符，并拼接成的字符串
    - right：暂存剩下的字符，并拼接成的字符串
- 遍历字符串
    - 如果当前坐标`i < n`，就拼接入`left`
    - 否则就拼接入`right`
- 遍历结束返回`right + left`
### 代码
```java
class Solution {
    public String reverseLeftWords(String s, int n) {
        StringBuilder left = new StringBuilder(), right = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (i < n) {
                left.append(s.charAt(i));
            } else {
                right.append(s.charAt(i));
            }
        }
        
        return right.append(left).toString();
    }
}
```
## 解法二
### 思路
使用String的API：`subString`
### 代码
```java
class Solution {
    public String reverseLeftWords(String s, int n) {
        return s.substring(n) + s.substring(0, n);
    }
}
```
# LeetCode_151_1_翻转字符串里的单词
## 题目
给定一个字符串，逐个翻转字符串中的每个单词。

示例 1：
```
输入: "the sky is blue"
输出: "blue is sky the"
```
示例 2：
```
输入: "  hello world!  "
输出: "world! hello"
解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
```
示例 3：
```
输入: "a good   example"
输出: "example good a"
解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
```
说明：
```
无空格字符构成一个单词。
输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
```
## 解法
### 思路
思路和面试题58第二解相等
### 代码
```java
class Solution {
    public String reverseWords(String s) {
        s = s.trim();
        if (s.length() == 0) {
            return "";
        }

        String[] ss = s.split(" ");
        if (ss.length == 1) {
            return ss[0];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = ss.length - 1; i >= 0; i--) {
            if (!"".equals(ss[i])) {
                sb.append(" ").append(ss[i]);
            }
        }

        return sb.deleteCharAt(0).toString();
    }
}
```
# Interview_59I_滑动窗口的最大值
## 题目
给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。

示例:
```
输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
输出: [3,3,5,5,6,7] 
解释: 

  滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
```
提示：
```
你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。
```
## 解法
### 思路
- 定义临时变量：
    - max：暂存窗口中的最大值，初始为int的最小值
    - maxIndex：暂存窗口中最大值的坐标，初始为-1
- 遍历数组，遍历范围为`[i, len - k + 1]`
    - 判断`maxIndex`是否在窗口范围内
        - 如果在，就拿新的值与原最大值比较，大就更新两个变量
        - 如果不在，就遍历窗口，判断当前的最大值和对应的坐标
    - 记录这个窗口的max
### 代码
```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length == 0 || k == 0) {
            return new int[0];
        }
        
        int len = nums.length, max = Integer.MIN_VALUE, maxIndex = -1;
        int[] ans = new int[len - k + 1];

        for (int i = 0; i < len - k + 1; i++) {
            if (maxIndex >= i && maxIndex < i + k) {
                if (nums[i + k - 1] > max) {
                    maxIndex = i + k - 1;
                    max = nums[i + k - 1];
                }
            } else {
                max = nums[i];
                for (int j = i; j < i + k; j++) {
                    if (max < nums[j]) {
                        max = nums[j];
                        maxIndex = j;
                    }
                }

            }

            ans[i] = max;
        }

        return ans;
    }
}
```
## 解法二
### 思路
双向队列：
### 代码
```java

```
# LeetCode_239_滑动窗口的最大值
## 题目
给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。

返回滑动窗口中的最大值。

示例:
```
输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
输出: [3,3,5,5,6,7] 
解释: 

  滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
```
提示：
```
你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。
```
进阶：
```
你能在线性时间复杂度内解决此题吗？
```
## 解法
### 思路
解题思路和面试题59I第一解相同
### 代码
```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length == 0 || k == 0) {
            return new int[0];
        }
        
        int len = nums.length, max = Integer.MIN_VALUE, maxIndex = -1;
        int[] ans = new int[len - k + 1];

        for (int i = 0; i < len - k + 1; i++) {
            if (maxIndex >= i && maxIndex < i + k) {
                if (nums[i + k - 1] > max) {
                    maxIndex = i + k - 1;
                    max = nums[i + k - 1];
                }
            } else {
                max = nums[i];
                for (int j = i; j < i + k; j++) {
                    if (max < nums[j]) {
                        max = nums[j];
                        maxIndex = j;
                    }
                }

            }

            ans[i] = max;
        }

        return ans;
    }
}
```
# Interview_60_n个色子的点数
## 题目
把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。

你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。

示例 1:
```
输入: 1
输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
```
示例 2:
```
输入: 2
输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]
```
限制：
```
1 <= n <= 11
```
## 解法
### 思路
动态规划：
- `dp[i][s]`：表示i个色子情况下总和为s的情况个数
- 状态转移方程：
    - `dp[i][s] = dp[i - 1][s- 1] + dp[i - 1][s- 2] + dp[i - 1][s- 3] + dp[i - 1][s- 4] + dp[i - 1][s- 5] + dp[i - 1][s- 6]` 
    - 六种不同点数在`i-1`个色子时能得到`s - x`总和的情况总和
- 初始化：`dp[1][1] = 1, dp[1][2], dp[1][3], dp[1][4], dp[1][5], dp[1][6]`
- 需要求的点数总和的范围：`[n, 6 * n]`，因为3个色子不可能得到`[0,2]`
- 总的可能数是6的n次方
- 状态转移过程中还要注意，总和数不能小于色子数
### 代码
```java
class Solution {
    public double[] twoSum(int n) {
        int[][] dp = new int[n + 1][6 * n + 1];
        for (int i = 1; i <= 6; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int s = i; s <= 6 * n; s++) {
                for (int k = 1; k <= 6; k++) {
                    if (s - k >= i - 1) {
                        dp[i][s] += dp[i - 1][s - k];
                    }
                }
            }
        }

        double total = Math.pow(6, n);
        double[] ans = new double[5 * n + 1];

        for (int i = n; i <= 6 * n; i++) {
            ans[i - n] = dp[n][i] / total;
        }
        return ans;
    }
}
```
# Interview_61_扑克牌中的顺子
## 题目
从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。

示例 1:
```
输入: [1,2,3,4,5]
输出: True
```
示例 2:
```
输入: [0,0,1,2,5]
输出: True
```
限制：
```
数组长度为 5 
数组的数取值为 [0, 13] .
通过次数2,542提交次数5,930
```
## 解法
### 思路
- 初始化桶`bucket`，用来记录出现的元素个数
- 遍历数组
    - 求数组最大值
    - 记录元素个数
- 如果最大值为0，直接返回true，说明全都是王
- 遍历数组：
    - 判断当前元素在`bucket`中是否存在，或者是否有可用的王
    - 如果都没有就返回false
### 代码
```java
class Solution {
    public boolean isStraight(int[] nums) {
        int max = 0;
        int[] arr = new int[14];
        for (int num : nums) {
            arr[num]++;
            max = Math.max(max, num);
        }
        
        if (max == 0) {
            return true;
        }

        for (int i = max - 4; i <= max; i++) {
            if (arr[i]-- <= 0 && arr[0]-- <= 0) {
                return false;
            }
        }

        return true;
    }
}
```
# Interview_62_圆圈中最后剩下的数字
## 题目
0,1,,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字。求出这个圆圈里剩下的最后一个数字。

例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。

示例 1：
```
输入: n = 5, m = 3
输出: 3
```
示例 2：
```
输入: n = 10, m = 17
输出: 2
```
限制：
```
1 <= n <= 10^5
1 <= m <= 10^6
```
## 解法
### 思路
- 初始化链表
- 初始删除的位置是`index = (m -  1) % len` 
- 循环删除元素，直到链表长度为1
- 每次删除第index位置的元素后，因为是继续从删除元素的后一个元素开始遍历，所以还要更新一下`index`
    - `index = (index + m - 1) % list.size()`
### 代码
```java
class Solution {
    public int lastRemaining(int n, int m) {
        if (n == 0) {
            return -1;
        }
        
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        
        int index = (m - 1) % list.size();
        while (list.size() > 1) {
            list.remove(index);
            index = (index + m -  1) % list.size();
        }


        return list.get(0);
    }
}
```
## 解法二
### 思路
动态规划：
- `dp[i]`：i个人的时候幸存的人的位置
- 状态转移方程：`dp[i] = （dp[i - 1] + m) % n`，代表dp[i - 1]位置是上一个安全的位置，当前安全的位置就是从这个位置出发走m步的位置(包括循环)
- 初始化：`dp[1] = 0`
- 返回`dp[n]`
### 代码
```java
class Solution {
    public int lastRemaining(int n, int m) {
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 1] + m) % i;
        }
        return dp[n];
    }
}
```
## 优化代码
### 思路
用局部变量`ans`代替dp数组
### 代码
```java
class Solution {
    public int lastRemaining(int n, int m) {
        int ans = 0;
        for (int i = 2; i <= n; i++) {
            ans = (ans + m) % i;
        }
        return ans;
    }
}
```
# Interview_63_股票的最大利润
## 题目
假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？

示例 1:
```
输入: [7,1,5,3,6,4]
输出: 5
解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 ```
示例 2:
```
输入: [7,6,4,3,1]
输出: 0
解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
```
限制：
```
0 <= 数组长度 <= 10^5
```
## 解法
### 思路
动态规划：
- `dp[][]`：
    - `dp[i][0]`：第i天不持有股票的状态下，最大利润值
    - `dp[i][1]`：第i天持有股票的状态下，最大利润值
- 状态转移方程：
    - `dp[i][0] = max(dp[i - 1][0], dp[i - 1][1] + prices[i])`：昨天也没持有股票和昨天持有股票但今天卖了之间的最大值
    - `dp[i][1] = max(dp[i - 1][1], -prices[i]`：昨天也持有股票，和今天第一次买股票之间的最大值。因为只能买卖一次，所以买次买的时候都认为是从0开始计算
- 初始化：
    `dp[0][0]`：0，第一天没有买卖
    `dp[0][1]`：`-prices[0]`
- 返回`dp[n - 1][0]`
### 代码
```java
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0) {
            return 0;
        }
        
        int[][] dp = new int[n][2];
        dp[0][1] = -prices[0];
        
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], - prices[i]);
        }
        
        return dp[n - 1][0];
    }
}
```
# Interview_64_求1+2+…+n(等差数列求和)
## 题目
求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。

示例 1：
```
输入: n = 3
输出: 6
```
示例 2：
```
输入: n = 9
输出: 45
```
限制：
```
1 <= n <= 10000
```
## 解法
### 思路
递归
### 代码
```java
class Solution {
    public int sumNums(int n) {
        if (n == 0) {
            return 0;
        }
        
        return n + sumNums(n - 1);
    }
}
```
# Interview_65_不用加减乘除做加法
## 题目
写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。

示例:
```
输入: a = 1, b = 1
输出: 2
```
提示：
```
a, b 均可能是负数或 0
结果不会溢出 32 位整数
```
## 解法
### 思路
- 异或等于不进位的加法
- 相与得到同为1的值，相当于获得了需要进位的位，然后再左移，就得到了每一位需要计算的进位值，在下一次循环的时候可以进行不进位的异或
- 如上两个过程不断循环往复，直到进位值为0为止
### 代码
```java
class Solution {
    public int add(int a, int b) {
        while (b != 0) {
            int plus = a ^ b;
            b = (a & b) << 1;
            a = plus;
        }
        return a;
    }
}
```
# Interview_66_构建乘积数组
## 题目
给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中 B 中的元素 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。

示例:
```
输入: [1,2,3,4,5]
输出: [120,60,40,30,24]
```
提示：
```
所有元素乘积之和不会溢出 32 位整数
a.length <= 100000
```
## 解法
### 思路
- 从左往右遍历数组A
    - 使用变量`left`，用来记录当前`i`的左边所有元素的乘积，如果`i`是0，那么`left`就是1
    - 将`left`放入数组`ans`中的第i个元素中
    - 将`left`与当前`A[i]`元素累乘，用于下一个循环
- 再从右往左遍历数组A
    - 使用变量`right`记录坐标`i`右边所有元素的累乘，`right`初始化为1
    - 将`right`与`ans[i]`中记录的i左边的累乘值相乘，得到了题目要求的答案
    - 将`right`与`A[i]`累乘，用于下一次循环
### 代码
```java
class Solution {
    public int[] constructArr(int[] a) {
        int len = a.length, left = 1, right = 1;
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = left;
            left *= a[i];
        }
        
        for (int i = len - 1; i >= 0; i--) {
            ans[i] *= right;
            right *= a[i];
        }
        
        return ans;
    }
}
```
# Interview_67_把字符串转换成整数
## 题目
写一个函数 StrToInt，实现把字符串转换成整数这个功能。不能使用 atoi 或者其他类似的库函数。

首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。

当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。

该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。

注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。

在任何情况下，若函数不能进行有效的转换时，请返回 0。

说明：
```
假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
```
示例 1:
```
输入: "42"
输出: 42
```
示例 2:
```
输入: "   -42"
输出: -42
解释: 第一个非空白字符为 '-', 它是一个负号。
     我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
```
示例 3:
```
输入: "4193 with words"
输出: 4193
解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
```
示例 4:
```
输入: "words and 987"
输出: 0
解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
     因此无法执行有效的转换。
```
示例 5:
```
输入: "-91283472332"
输出: -2147483648
解释: 数字 "-91283472332" 超过 32 位有符号整数范围。 
     因此返回 INT_MIN (−231) 。
```
## 解法
### 思路
- 初始化变量：
    - `i`：代表遍历的下标指针
    - `flag`：代表正负标志
    - `ans`：代表最后的结果
- 过程：
    - 过滤字符串前部分的空格
    - 判断第一个非空格：
        - 负号：`flag = -1`，`i++`
        - 正好：`i++`
    - 遍历字符串
        - 循环条件：指针不越界，字符为数字
        - 计算当前字符的数值
        - 判断`ans`是否已经溢出，如果溢出就根据`flag`直接返回结果
        - 将当前数值加入到`ans`中，`ans = ans * 10 + num`
    - 因为字符串只要遍历并生成的数字就是有效的，所以直到字符串遍历结束，或者遇到非数字的字符，就可以直接将累加的`ans`返回
### 代码
```java
class Solution {
    public int strToInt(String str) {
        if (str == null || "".equals(str)) {
            return 0;
        }

        int ans = 0, i = 0, flag = 1;
        while (i < str.length() && str.charAt(i) == ' ') {
            i++;
        }

        if (i < str.length() && str.charAt(i) == '-') {
            flag = -1;
        }

        if (i < str.length() && (str.charAt(i) == '-' || str.charAt(i) == '+')) {
            i++;
        }

        while (i < str.length() && Character.isDigit(str.charAt(i))) {
            int num = str.charAt(i) - '0';
            if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && num > 7)) {
                return flag > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            ans = ans * 10 + num;
            i++;
        }

        return flag > 0 ? ans : -ans;
    }
}
```
# LeetCode_8_字符串转换成整数
## 题目
请你来实现一个 atoi 函数，使其能将字符串转换成整数。

首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。

当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。

该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。

注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。

在任何情况下，若函数不能进行有效的转换时，请返回 0。

说明：
```
假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
```
示例 1:
```
输入: "42"
输出: 42
```
示例 2:
```
输入: "   -42"
输出: -42
解释: 第一个非空白字符为 '-', 它是一个负号。
     我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
```
示例 3:
```
输入: "4193 with words"
输出: 4193
解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
```
示例 4:
```
输入: "words and 987"
输出: 0
解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
     因此无法执行有效的转换。
```
示例 5:
```
输入: "-91283472332"
输出: -2147483648
解释: 数字 "-91283472332" 超过 32 位有符号整数范围。 
     因此返回 INT_MIN (−231) 。
```
## 解法
### 思路
解法与面试题67一致
### 代码
```java
class Solution {
    public int myAtoi(String str) {
        int ans = 0, i = 0, len = str.length();
        boolean positive = true;
        
        while (i < len && str.charAt(i) == ' ') {
            i++;
        }
        
        if (i < len && str.charAt(i) == '-') {
            positive = false;
        }
        
        if (i < len && (str.charAt(i) == '-' || str.charAt(i) == '+')) {
            i++;
        }
        
        while (i < len && Character.isDigit(str.charAt(i))) {
            int num = str.charAt(i) - '0';
            if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && num > 7)) {
                return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            
            ans = ans * 10 + num;
            i++;
        }
        
        return positive ? ans : -ans;
    }
}
```
# Interview_68I_二叉搜索树的最近公共祖先
## 题目
给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]

示例 1:
```
输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
输出: 6 
解释: 节点 2 和节点 8 的最近公共祖先是 6。
```
示例 2:
```
输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
输出: 2
解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
```
说明:
```
所有节点的值都是唯一的。
p、q 为不同节点且均存在于给定的二叉搜索树中。
```
## 解法
### 思路
递归：
- 如果p和q的值都小于root，递归搜索root的左子树
- 如果p和q的值都大于root，递归搜索root的右子树
### 代码
```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        
        return root;
    }
}
```
# Interview_68II_二叉树的最近公共祖先
## 题目
给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]

示例 1:
```
输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
输出: 3
解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
```
示例 2:
```
输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
输出: 5
解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
```
说明:
```
所有节点的值都是唯一的。
p、q 为不同节点且均存在于给定的二叉树中。
```
## 解法
### 思路
dfs：
- 退出条件：如果`root`是null或者是p或q，那么就返回root
- 过程：
    - 递归获得左右子树的返回值
    - 判断：
        - 如果左子树是null，就返回右子树。可能左右都是null，这个节点以下没有p或q，或者右子树已经有祖先节点，或者当前节点就是公共祖先节点
        - 如果右子树是null，就返回左子树，可能性如上
        - 如果左右子树都不是null，当前节点就是公共祖先节点
### 代码
```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left == null) {
            return right;
        } 
        
        if (right == null) {
            return left;
        }

        return root;
    }
}
```
# Interview_0101_判断字符是否唯一
## 题目
实现一个算法，确定一个字符串 s 的所有字符是否全都不同。

示例 1：
```
输入: s = "leetcode"
输出: false 
```
示例 2：
```
输入: s = "abc"
输出: true
```
限制：
```
0 <= len(s) <= 100
如果你不使用额外的数据结构，会很加分。
```
## 解法
### 思路
- 排序
- 遍历判断前后元素是否相等
### 代码
```java
class Solution {
    public boolean isUnique(String astr) {
        char[] cs = astr.toCharArray();
        Arrays.sort(cs);
        
        for (int i = 1; i < cs.length; i++) {
            if (cs[i - 1] == cs[i]) {
                return false;
            }
        }
        
        return true;
    }
}
```
## 解法二
### 思路
- 使用int的32位来存储26个字母
- 遍历字符串，如果有一位上之前已经存储，就返回falase
- 否则就将当前字符代表的位数或到存储用的数字上
### 代码
```java
class Solution {
    public boolean isUnique(String astr) {
        int check = 0;
        for (char c : astr.toCharArray()) {
            int mask = 1 << c;
            if ((check & mask) != 0) {
                return false;
            } else {
                check  |= mask;
            }
        }
        return true;
    }
}
```
LeetCode_300_最长上升子序列
## 题目
给定一个无序的整数数组，找到其中最长上升子序列的长度。

示例:
```
输入: [10,9,2,5,3,7,101,18]
输出: 4 
解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
```
说明:
```
可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
你算法的时间复杂度应该为 O(n2) 。
```
```
进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
```
## 解法
### 思路
动态规划：
- `dp[i]`：以第 i 个数字结尾的最长上升子序列的长度
- 状态转移方程：`dp[i] = max(dp[j], maxvalue)`
    - dp[j]的范围`[0, i - 1]`
    - 如果i元素比其之前的j元素大，就计算j元素对应的dp值是否时`[0, i - 1]`范围中的最大值
    - 最终将暂存的最大值置于`dp[i]`
- 初始化：`dp[0] = 1`，代表第一个元素上升序列是其本身，长度就是1
- 返回dp数组中的最大值
### 代码
```java
class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int ans = 0, len = nums.length;
        int[] dp = new int[len];
        dp[0] = 1;
        for (int i = 0; i < len; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + 1;
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
```