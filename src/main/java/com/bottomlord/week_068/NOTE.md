# LeetCode_1365_有多少小于当前数字的数字
## 题目
给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。

换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。

以数组形式返回答案。

示例 1：
```
输入：nums = [8,1,2,2,3]
输出：[4,0,1,1,3]
解释： 
对于 nums[0]=8 存在四个比它小的数字：（1，2，2 和 3）。 
对于 nums[1]=1 不存在比它小的数字。
对于 nums[2]=2 存在一个比它小的数字：（1）。 
对于 nums[3]=2 存在一个比它小的数字：（1）。 
对于 nums[4]=3 存在三个比它小的数字：（1，2 和 2）。
```
示例 2：
```
输入：nums = [6,5,4,8]
输出：[2,1,0,3]
```
示例 3：
```
输入：nums = [7,7,7,7]
输出：[0,0,0,0]
```
提示：
```
2 <= nums.length <= 500
0 <= nums[i] <= 100
```
## 解法
### 思路
O(N^2)的时间复杂度，两次循环
### 代码
```java
class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            for (int num : nums) {
                if (nums[i] > num) {
                    ans[i]++;
                }
            }
        }
        
        return ans;
    }
}
```
## 解法二
### 思路
快排+记录坐标
### 代码
```java
class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int len = nums.length;
        int[] ans = new int[len];
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        for (int i = 0; i < len; i++) {
            List<Integer> list = map.getOrDefault(nums[i], new ArrayList<>());
            list.add(i);
            map.put(nums[i], list);
        }

        Arrays.sort(nums);
        
        for (int i = 0; i < len; i++) {
            if (i != 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            
            List<Integer> list = map.get(nums[i]);
            for (Integer index : list) {
                ans[index] = i;
            }
        }
        
        return ans;
    }
}
```
## 解法三
### 思路
数组计数并计算前缀和
### 代码
```java
class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int len = nums.length;
        int[] count = new int[101], ans = new int[len];
        for (int num : nums) {
            count[num]++;
        }

        int[] sums = new int[count.length];
        int sum = 0;
        for (int i = 0; i < count.length; i++) {
            sum += count[i];
            sums[i] = sum - count[i];
        }

        for (int i = 0; i < len; i++) {
            ans[i] = sums[nums[i]];
        }

        return ans;
    }
}
```
# LeetCode_316_去除重复字母
## 题目
给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。

注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters 相同

示例 1：
```
输入：s = "bcabc"
输出："abc"
```
示例 2：
```
输入：s = "cbacdcbc"
输出："acdb"
```
## 失败解法
### 失败原因
改变了相对顺序
### 思路
- 转字符数组
- 快速排序
- 遍历数组append
### 代码
```java
class Solution {
    public String removeDuplicateLetters(String s) {
        char[] cs = s.toCharArray();
        Arrays.sort(cs);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cs.length; i++) {
            if (i == cs.length - 1) {
                sb.append(cs[i]);
                break;
            }
            
            if (cs[i] == cs[i + 1]) {
                continue;
            }
            
            sb.append(cs[i]);
        }
        return sb.toString();
    }
}
```
## 解法
### 思路
- 为了保证字符的相对顺序，且同时是最小字典序列，那么序列小的小字符能够在前面的条件就是：我前面比我大的字符，在我之后也存在
- 首先对字符串中出现的字符进行计数，用于在遍历过程中判断是否当前字符已经是相同字符中的最后一个
- 在找那个尽可能小的字符时，先暂定一个当前搜索范围内的最小字符，并不断比较更新
- 然后将当前遍历到的字符从计数中-1
- 如果发现当前字符是最后一个出现的字符了，那么说明当前那个最小字符，就是目前字符串中能够排在前面的最小字典序列的字符了，因为就算后面有更小的，它之后也没有前面比它大的那些字符了，也就保证不了原来的相对顺序
- 然后就重复如上的顺序，在递归过程中，截取当前最小字符所在位置之前的所有字符，那些在其后都存在，已经不需要了，同时，将其后字符中与当前最小字符相同的字符去除
- 递归的退出条件就是字符串长度为0
- 每一层递归的目的就是获得当前字符串中能够排在最前面且字典序列符合条件前提下最小的字符，将他们通过递归的方式拼接，就是最终需要的字符串了
### 代码
```java
class Solution {
    public String removeDuplicateLetters(String s) {
        int len = s.length();
        if (len == 0) {
            return "";
        }
        int[] count = new int[26];
        
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        
        int index = 0;
        for (int i = 0; i < len; i++) {
            index = s.charAt(index) <= s.charAt(i) ? index : i;
            if (--count[s.charAt(i) - 'a'] == 0) {
                break;
            }
        }
        
        return s.charAt(index) + removeDuplicateLetters(s.substring(index + 1).replaceAll("" + s.charAt(index), ""));
    }
}
```