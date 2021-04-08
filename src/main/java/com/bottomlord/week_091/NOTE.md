# [LeetCode_510_二叉搜索树的中序后继II](https://leetcode-cn.com/problems/inorder-successor-in-bst-ii/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_81_搜索旋转排序数组II](https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/)
## 解法
### 思路
- 因为是排序数组中查找目标值，最快的方法就是二分查找
- 当前值是一个排序数组的变种，它被旋转了一次，且不是降序
- 在二分查找中，确定中间值的过程与普通的是一致的，如果`target == nums[mid]`，那么就返回true
- 为了确定数组的状态，除了获取数组的中间值外，还要用中间值与数组头部的值进行比较：
    - 如果数组头部值与中间值一致，则右移head值，因为这种情况由几种可能
        - mid的左边都是和中间值一样的元素
        - 中间值其实就是实际元素的最大值，且重复多个，那么mid的左边可能包含实际数组的起始部分
    - 如果数组的头部值小于中间值，则代表mid左边一定是升序的
        - 如果target小于头部值，那么可能实际升序数组的开头部分在mid的右边
        - 如果target大于头部值，且小于中间值，那么就在mid的左边
        - 如果target大于中间值，那么就直接去右边去搜索
    - 如果数组的头部值大于中间值，mid到尾部是一个升序序列
        - 如果target大于尾部值，那么可能在mid的左边
        - 如果target小于尾部值，且大于中间值，就在mid的右边
        - 如果target小于尾部值，且小于中间值，则可能在mid的左边
### 代码
```java
class Solution {
    public boolean search(int[] nums, int target) {
        int len = nums.length, head = 0, tail = len - 1;
        while (head <= tail) {
            int mid = head + (tail - head) / 2;
            if (nums[mid] == target) {
                return true;
            }

            if (nums[head] == nums[mid]) {
                head++;
            } else if (nums[head] < nums[mid]) {
                if (target == nums[head]) {
                    return true;
                }

                if (target > nums[head] && target < nums[mid]) {
                    tail = mid - 1;
                } else {
                    head = mid + 1;
                }
            } else {
                if (target == nums[tail]) {
                    return true;
                }

                if (target < nums[tail] && target > nums[mid]) {
                    head = mid + 1;
                } else {
                    tail = mid - 1;
                }
            }
        }

        return false;
    }
}
```
# [LeetCode_153_寻找旋转排序数组中的最小值](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/)
## 解法
### 思路
通过二分查找找到最小值
- 初始化起始坐标，结尾坐标和最小值变量
- 先找到中间值，然后用中间值和起始值比较
    - 循环的继续条件是起始坐标小于结尾坐标
    - 如果中间值和起始值相等，此时说明数组只剩下至多2个元素，此时比较一下起始值与暂存的最小值，取两者的最小值
    - 如果中间值比起始值大，说明要么最小值是起始值，要么就在中间值右边
    - 如果中间值比起始值小，说明最小值在中间值的左边
### 代码
```java
class Solution {
    public int findMin(int[] nums) {
        int len = nums.length, head = 0, tail = len - 1, min = Integer.MAX_VALUE;
        while (head < tail) {
            int mid = head + (tail - head) / 2;

            if (nums[mid] == nums[head]) {
                min = Math.min(nums[head], min);
                head++;
            } else if (nums[mid] > nums[head]) {
                min = Math.min(nums[head], min);
                head = mid + 1;
            } else {
                min = Math.min(nums[mid], min);
                tail = mid;
            }
        }

        return Math.min(nums[head], min);
    }
}
```