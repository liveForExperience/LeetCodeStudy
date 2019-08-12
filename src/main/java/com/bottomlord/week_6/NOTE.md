# LeetCode_697_数组的度
## 题目
给定一个非空且只包含非负数的整数数组 nums, 数组的度的定义是指数组里任一元素出现频数的最大值。

你的任务是找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。

示例 1:
```
输入: [1, 2, 2, 3, 1]
输出: 2
解释: 
输入数组的度是2，因为元素1和2的出现频数最大，均为2.
连续子数组里面拥有相同度的有如下所示:
[1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
最短连续子数组[2, 2]的长度为2，所以返回2.
```
示例 2:
```
输入: [1,2,2,3,1,4,2]
输出: 6
```
注意:
```
nums.length 在1到50,000区间范围内。
nums[i] 是一个在0到49,999范围内的整数。
```
## 解法
### 思路
- 遍历数组，通过桶的方法，算出数组的度
- 同时还要有个桶，记录元素第一次出现时候的下标
- 然后通过次数和下标，再遍历原数组，看看长度是多少，如果又度一样的元素，就取短的那个
### 代码
```java
class Solution {
    public int findShortestSubArray(int[] nums) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(num, max);
        }
        
        int[] countBucket = new int[max + 1];
        Integer[] indexBucket = new Integer[max + 1];
        
        int maxCount = 0;
        for (int i = 0; i < nums.length; i++) {
            countBucket[nums[i]]++;
            maxCount = Math.max(countBucket[nums[i]], maxCount);
            
            if (indexBucket[nums[i]] == null) {
                indexBucket[nums[i]] = i;
            }
        }
        
        int len = Integer.MAX_VALUE;
        for (int i = 0; i < countBucket.length; i++) {
            if (countBucket[i] == maxCount) {
                int tmpCount = maxCount;
                int tmpIndex = indexBucket[i];
                int tmpLen = 0;
                while (tmpCount > 0) {
                    if (nums[tmpIndex++] == i) {
                        tmpCount--;
                    }
                    tmpLen++;
                }
                len = Math.min(len, tmpLen);
            }
        }
        return len;
    }
}
```
# LeetCode_551_学生出勤记录
## 题目
给定一个字符串来代表一个学生的出勤记录，这个记录仅包含以下三个字符：
```
'A' : Absent，缺勤
'L' : Late，迟到
'P' : Present，到场
如果一个学生的出勤记录中不超过一个'A'(缺勤)并且不超过两个连续的'L'(迟到),那么这个学生会被奖赏。

你需要根据这个学生的出勤记录判断他是否会被奖赏。
```
示例 1:
```
输入: "PPALLP"
输出: True
```
示例 2:
```
输入: "PPALLL"
输出: False
```
## 解法
### 思路
- 看缺勤是否符合条件：用String.replace后的字符串和源字符串来比较长度差是否超过1
- 看迟到是否符合条件：通过遍历字符数组
### 代码
```java
class Solution {
    public boolean checkRecord(String s) {
        if (s.length() - s.replaceAll("A", "").length() > 1) {
            return false;
        }
        
        for (int i = 0; i < s.length() - 2; i++) {
            if (s.charAt(i) == 'L' && s.charAt(i + 1) == 'L' && s.charAt(i + 2) == 'L') {
                return false;
            }
        }
        
        return true;
    }
}
```
## 解法二
### 思路
解法一中看是否迟到，也可以使用String.replace的方法，replace一下三个L，看看长度是否改变
### 代码
```java
class Solution {
    public boolean checkRecord(String s) {
        if (s.length() - s.replaceAll("A", "").length() > 1) {
            return false;
        }

        return s.length() == s.replaceAll("LLL", "").length();
    }
}
```
## 解法三
### 思路
解法一中看是否阙清，也可以使用数组遍历的方式，使用一个变量count来计算A出现的次数
### 代码
```java
class Solution {
    public boolean checkRecord(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == 'A') {
                if (count++ == 1) {
                    return false;
                }
            }
        }

        for (int i = 0; i < s.length() - 2; i++) {
            if (s.charAt(i) == 'L' && s.charAt(i + 1) == 'L' && s.charAt(i + 2) == 'L') {
                return false;
            }
        }

        return true;
    }
}
```
# LeetCode_492_构造矩形
## 题目
作为一位web开发者， 懂得怎样去规划一个页面的尺寸是很重要的。 现给定一个具体的矩形页面面积，你的任务是设计一个长度为 L 和宽度为 W 且满足以下要求的矩形的页面。要求：
```
1. 你设计的矩形页面必须等于给定的目标面积。

2. 宽度 W 不应大于长度 L，换言之，要求 L >= W 。

3. 长度 L 和宽度 W 之间的差距应当尽可能小。
你需要按顺序输出你设计的页面的长度 L 和宽度 W。
```
示例：
```
输入: 4
输出: [2, 2]
解释: 目标面积是 4， 所有可能的构造方案有 [1,4], [2,2], [4,1]。
但是根据要求2，[1,4] 不符合要求; 根据要求3，[2,2] 比 [4,1] 更能符合要求. 所以输出长度 L 为 2， 宽度 W 为 2。
```
说明:
```
给定的面积不大于 10,000,000 且为正整数。
你设计的页面的长度和宽度必须都是正整数。
```
## 失败解法
### 思路
- 对area开根号，并对结果向下取整得到num
- 如果num与开根号的值相等，就返回这个值
- 否则就嵌套循环遍历
    - 外层是从1到num
    - 内层是从area到num
- 并且优化一下遍历的范围，如果发现成绩小于area了，就终止内层循环，进入下一个外层循环    

但是超出了时间限制
### 代码
```java
class Solution {
    public int[] constructRectangle(int area) {
        double squareRoot = Math.sqrt(area);
        int num = (int)squareRoot;
        if ((double)num == squareRoot) {
            return new int[]{num, num};
        }
        
        int min = Integer.MAX_VALUE;
        int[] ans = new int[2];
        for (int i = 1; i <= num; i++) {
            for (int j = area; j > num; j--) {
                if (i * j < area) {
                    break;
                }
                
                if (i * j == area && j - i < min) {
                    min = j - i;
                    ans[0] = j;
                    ans[1] = i;
                }
            }
        }
        return ans;
    }
}
```
## 解法
### 思路
失败的解法太蠢，直接求出平方根向下取整以后，如果不满足，那么不需要嵌套循环，只要递减或递增求出其中一个因数就可以了呀，判断的依据就是是否取余等于0，然后再通过area / num获得另一个数，只要位置放对就好了。
### 代码
```java
class Solution {
    public int[] constructRectangle(int area) {
        int num = (int) Math.sqrt(area);

        while (area % num != 0) {
            num--;
        }

        return new int[]{area / num, num};
    }
}
```
# LeetCode_541_反转字符串II
## 题目
给定一个字符串和一个整数 k，你需要对从字符串开头算起的每个 2k 个字符的前k个字符进行反转。如果剩余少于 k 个字符，则将剩余的所有全部反转。如果有小于 2k 但大于或等于 k 个字符，则反转前 k 个字符，并将剩余的字符保持原样。

示例:
```
输入: s = "abcdefg", k = 2
输出: "bacdfeg"
```
要求:
```
该字符串只包含小写的英文字母。
给定字符串的长度和 k 在[1, 10000]范围内。
```
## 解法
### 思路
- 遍历字符数组，根据要求旋转
### 代码
```java
class Solution {
    public String reverseStr(String s, int k) {
        int index = 0;
        char[] cs = s.toCharArray();
        while (index + 2 * k <= s.length() - 1) {
            int head = index, tail = index + k - 1;
            reserve(cs, head, tail);
            index += 2 * k;
        }

        if (index < s.length() - 1) {
            int tail = Math.min(index + k - 1, s.length() - 1);
            reserve(cs, index, tail);   
        }

        return new String(cs);
    }
    
    private void reserve(char[] cs, int head, int tail) {
        while (head < tail) {
            cs[head] ^= cs[tail];
            cs[tail] ^= cs[head];
            cs[head] ^= cs[tail];

            head++;
            tail--;
        }
    }
}
```