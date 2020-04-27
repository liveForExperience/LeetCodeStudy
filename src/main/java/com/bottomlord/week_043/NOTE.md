# LeetCode_33_搜索旋转排序数组
## 题目
假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。

你可以假设数组中不存在重复的元素。

你的算法时间复杂度必须是 O(log n) 级别。

示例 1:
```
输入: nums = [4,5,6,7,0,1,2], target = 0
输出: 4
```
示例 2:
```
输入: nums = [4,5,6,7,0,1,2], target = 3
输出: -1
```
## 解法
### 思路
二分查找：
- 初始化头尾指针，开始循环直到头尾指针相遇
- 因为通过中间指针将数组分成两部分后，一定有一部分是有序的
    - 第一步是判断哪一部分是有序数组
    - 如果target在有序数组这部分里，那区间范围就缩小到这一部分
    - 否则就缩小到另一部分
### 代码
```java
class Solution {
    public int search(int[] nums, int target) {
        int head = 0, tail = nums.length - 1;
        while (head <= tail) {
            int mid = head + (tail - head) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[head] <= nums[mid]) {
                if (target < nums[mid] && target >= nums[head]) {
                    tail = mid - 1;
                } else {
                    head = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[tail]) {
                    head = mid + 1;
                } else {
                    tail = mid - 1;
                }
            }
        }

        return -1;
    }
}
```