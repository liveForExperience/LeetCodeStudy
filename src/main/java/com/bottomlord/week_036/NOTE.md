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