# LeetCode_215_数组中的第K个最大元素
## 题目
在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

示例 1:
```
输入: [3,2,1,5,6,4] 和 k = 2
输出: 5
```
示例 2:
```
输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
输出: 4
```
说明:
```
你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
```
## 解法
### 思路
基于快排
- 快排在确定一个元素的位置时，左右两边的元素虽然没有排序，但已经被确定的分成了两部分
- 然后只根据index对于K的位置：
    - 如果index < k，那么就在index的右边继续搜
    - 如果index > k，那么就在index的左边继续搜
    - 如果index == k，就直接返回
### 代码
```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        return quickSearch(nums, 0, nums.length - 1, nums.length - k);
    }

    private int quickSearch(int[] nums, int start, int end, int index) {
        int pivot = partition(nums, start, end);

        if (pivot == index) {
            return nums[pivot];
        } else {
            return pivot < index ? quickSearch(nums, pivot + 1, end, index) : quickSearch(nums, start, pivot - 1, index);
        }
    }

    private int partition(int[] nums, int start, int end) {
        int num = nums[start];
        
        while (start < end) {
            while (start < end && nums[end] >= num) {
                end--;
            }
            nums[start] = nums[end];
            
            while (start < end && nums[start] <= num) {
                start++;
            }
            nums[end] = nums[start];
        }
        
        nums[start] = num;
        return start;
    }
}
```
## 解法二
### 思路
大顶堆
### 代码
```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, Comparator.reverseOrder());
        for (int num : nums) {
            queue.offer(num);
        }

        for (int i = 0; i < k - 1; i++) {
            queue.poll();
        }
        
        return queue.peek();
    }
}
```