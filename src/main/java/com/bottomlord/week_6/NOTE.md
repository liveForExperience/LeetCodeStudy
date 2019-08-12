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