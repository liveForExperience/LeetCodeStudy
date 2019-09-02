# LeetCode_680_验证回文字符串II
## 题目
给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。

示例 1:
```
输入: "aba"
输出: True
```
示例 2:
```
输入: "abca"
输出: True
解释: 你可以删除c字符。
```
注意:
```
字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。
```
## 失败解法
### 思路
暴力解法，一个个试，有true返true，否则返回false
### 失败原因
超时
### 代码
```java
class Solution {
    public boolean validPalindrome(String s) {
        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            int head = 0, tail = cs.length - 1;
            boolean flag = true;
            while (head < tail) {
                if (head == i) {
                    head++;   
                }
                
                if (tail == i) {
                    tail--;
                }
                
                if (cs[head] != cs[tail]) {
                    flag = false;
                    break;
                }
                
            head++;
            tail--;
            }
            
            if (flag) {
                return true;
            }
        }
        
        return false;
    }
}
```
## 解法
### 思路
- 其实只需要在两个字符不相等的情况下，判断两种情况：
    - head + 1到tail之间的字符串是否为回文字符串
    - head到tail-1之间的字符串是否为回文字符串
- 如果两种情况中的一种符合就是true，否则就是false
### 代码
```java
class Solution {
    public boolean validPalindrome(String s) {
        char[] cs = s.toCharArray();
        int head = 0, tail = cs.length - 1;
        while (head < tail) {
            if (cs[head] != cs[tail]) {
                return validate(head + 1, tail, cs) || validate(head, tail - 1, cs);
            } else {
                head++;
                tail--;
            }
        }
        
        return true;
    }
    
    private boolean validate(int start, int end, char[] cs) {
        while (start < end) {
            if (cs[start] != cs[end]) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
```
# LeetCode_414_第三大的数
## 题目
给定一个非空数组，返回此数组中第三大的数。如果不存在，则返回数组中最大的数。要求算法时间复杂度必须是O(n)。

示例 1:
```
输入: [3, 2, 1]

输出: 1

解释: 第三大的数是 1.
```
示例 2:
```
输入: [1, 2]

输出: 2

解释: 第三大的数不存在, 所以返回最大的数 2 .
```
示例 3:
```
输入: [2, 2, 3, 1]

输出: 1
```
```
解释: 注意，要求返回第三大的数，是指第三大且唯一出现的数。
存在两个值为2的数，它们都排第二。
```
## 解法
### 思路
主要依赖Collections的api
- 遍历元素放入set
- 如果set的元素个数小于3个，返回最大值
- 找两次最大值并把最大值从set中取出
- 最后一次把set的最大值返回
### 代码
```java
class Solution {
    public int thirdMax(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        
        if (set.size() < 3) {
            return Collections.max(set);
        }
        
        for (int i = 0; i < 2; i++) {
            set.remove(Collections.max(set));
        }
        
        return Collections.max(set);
    }
}
```
## 解法二
### 思路
- 定义三个变量：
    - first：最大值
    - second：第二大值
    - third：第三大值
- 遍历数组并进行判断：
    - 如果大于first，那么三个变量同时依次变更
    - 如果大于second，除first的变量依次变更
    - 如果大于third，third变量变更
### 代码
```java
class Solution {
    public int thirdMax(int[] nums) {
        int first =  nums[0];
        long second = Long.MIN_VALUE;
        long third = Long.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (num > first) {
                third = second;
                second = first;
                first = num;
            } else if (num > second && num < first) {
                third = second;
                second = num;
            } else if (num > third && num < second) {
                third = num;
            }
        }
        
        return third == Long.MIN_VALUE ? first : (int) third;
    }
}
```