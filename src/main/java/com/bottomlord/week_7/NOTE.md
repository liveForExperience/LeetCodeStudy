# LeetCode_35_搜索插入位置
## 题目
给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。

你可以假设数组中无重复元素。

示例 1:
```
输入: [1,3,5,6], 5
输出: 2
```
示例 2:
```
输入: [1,3,5,6], 2
输出: 1
```
示例 3:
```
输入: [1,3,5,6], 7
输出: 4
```
示例 4:
```
输入: [1,3,5,6], 0
输出: 0
```
## 解法
### 思路
遍历数组
- 找到目标值，就返回下标
- 未找到目标值，但遍历到的元素大于目标值，返回该元素下标
- 未找到目标值，且遍历结束，返回数组长度
### 代码
```java
class Solution {
    public int searchInsert(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target || nums[i] > target) {
                return i;
            }
        }
        
        return nums.length;
    }
}
```
## 解法二
### 思路
使用头尾指针进行二分查找，加快速度
### 代码
```java
class Solution {
    public int searchInsert(int[] nums, int target) {
        if (target > nums[nums.length - 1]) {
            return nums.length;
        }
        
        int head = 0, tail = nums.length - 1, mid = 0;
        while (head < tail) {
            mid = head + (tail - head) / 2;
            
            if (nums[mid] == target) {
                return mid;
            }
            
            if (nums[mid] > target) {
                tail = mid;
            }
            
            if (nums[mid] < target) {
                head = mid + 1;
            }
        }
        
        return tail;
    }
}
```