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