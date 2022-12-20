# [LeetCode_749_隔离病毒](https://leetcode.cn/problems/contain-virus/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_940_不同子序列](https://leetcode.cn/problems/distinct-subsequences-ii/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_1760_袋子里最小数目的球](https://leetcode.cn/problems/minimum-limit-of-balls-in-a-bag/)
## 解法
### 思路
二分查找
- 二分查找那个最小的y值
- 每个查找到的y，其操作数计算公式：`(nums[i] - 1) / y`，将这些操作数累加，与maxOperations比较来判断
  - 如果大于，则在大于y的区间找
  - 如果小于等于，则在小于y的区间找，并暂存答案
- 二分查找结束后，返回结果
### 代码
```java
class Solution {
    public int minimumSize(int[] nums, int maxOperations) {
         Arrays.sort(nums);
        int ans = Integer.MAX_VALUE, left = 1, right = nums[nums.length - 1];
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int opt = 0;
            for (int num : nums) {
                opt += (num - 1) / mid;
            }
            
            if (opt <= maxOperations) {
                ans = Math.min(ans, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return ans;
    }
}
```
# [LeetCode_1781_所有子字符串美丽值之和](https://leetcode.cn/problems/sum-of-beauty-of-all-substrings/)
## 解法
### 思路
循环模拟
- 3层循环
  - 第一层确定子字符串的起始字符
  - 第二层确定子字符串的结尾字符
  - 第三层，在通过2个坐标确定子字符串之后，遍历确定过程中累加的字符频次，来计算美丽值
- 遍历结束，返回计算出来的累加的美丽值
### 代码
```java
class Solution {
    public int beautySum(String s) {
        int ans = 0;
        char[] cs = s.toCharArray();

        for (int i = 0; i < cs.length; i++) {
            int[] cnt = new int[26];
            int maxFreq = 0;
            for (int j = i; j < cs.length; j++) {
                cnt[cs[j] - 'a']++;
                maxFreq = Math.max(maxFreq, cnt[cs[j] - 'a']);

                int minFreq = maxFreq;
                for (int value : cnt) {
                    if (value > 0) {
                        minFreq = Math.min(minFreq, value);
                    }
                }

                ans += maxFreq - minFreq;
            }
        }

        return ans;
    }
}
```