# LeetCode_163_缺失的区间
## 题目
给定一个排序的整数数组 nums ，其中元素的范围在 闭区间 [lower, upper] 当中，返回不包含在数组中的缺失区间。

示例：
```
输入: nums = [0, 1, 3, 50, 75], lower = 0 和 upper = 99,
输出: ["2", "4->49", "51->74", "76->99"]
```
## 失败解法
### 失败原因
超时
### 思路
- 从`lower`到`upper`开始遍历，并暂存一个记录数组下标的`index`
- 逐一比较，如果不相等，就开始内层循环，继续逐一比较，直到相等为止
- 将内层循环的起始和结尾组成字符串放入list中
### 代码
```java
class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> ans = new ArrayList<>();
        int index = 0, len = nums.length;
        for (int i = lower; i <= upper;) {
            if (index < len && i == nums[index]) {
                index++;
                i++;
            } else {
                int start = i;
                StringBuilder sb = new StringBuilder();
                sb.append(start);
                i++;
                while (i <= upper && (index >= len || i != nums[index])) {
                    i++;
                }
                int end = i - 1;
                if (start != end) {
                    sb.append("->").append(end);
                }
                ans.add(sb.toString());
            }
        }
        
        return ans;
    }
}
```
## 失败解法二
### 失败原因
示例元素导致计算时int越界
### 思路
基于失败解法，内层不再循环，因为是升序的，所以可以直接以`nums[index] - 1`作为生成的字符串的有边界元素
### 代码
```java
class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> list = new ArrayList<>();
        int index = 0, len = nums.length;
        for (int i = lower; i <= upper;) {
            if (index < len && i == nums[index]) {
                index++;
                i++;
            } else {
                int start = i;
                i++;
                String end = "";
                if (index >= len) {
                    end = "->" + upper;
                    i = upper + 1;
                } else if (i != nums[index]) {
                    end = "->" + nums[index];
                    i = nums[index];
                }
                list.add(start + end);
            }
        }
        
        return list;
    }
}
```
## 解法
### 思路
- 失败解法二不仅需要处理越界，还需要处理`nums`数组元素相等的情况
- 前两种解法都需要考虑`index`越界的问题，非常麻烦，如果外层循环是遍历`nums`数组，那么就可以少一部分的边界检查
- 另外还要考虑`nums`搜索完后，没有遍历到的区间内容，以及`nums`是空的情况
### 代码
```java
class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> list = new ArrayList<>();
        int len = nums.length;
        if (len == 0) {
            list.add(lower + (lower == upper ? "" : "->" + upper));
            return list;
        }
        
        long index = lower;
        for (int i = 0; i < nums.length;) {
            while (i < len && nums[i] == index) {
                while (i < len && nums[i] == index) {
                    i++;
                }
                index++;
            }
            
            if (i < len) {
                list.add(index + (nums[i] - 1 == index ? "" : "->" + (nums[i] - 1)));
                index = nums[i];
            }
        }
        
        if (nums[len - 1] < upper) {
            list.add(nums[len - 1] + 1 + (upper == nums[len - 1] + 1 ? "" : "->" + upper));
        }

        return list;
    }
}
```