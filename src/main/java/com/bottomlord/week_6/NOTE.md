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