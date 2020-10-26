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
## 解法
### 思路

### 代码
```java

```