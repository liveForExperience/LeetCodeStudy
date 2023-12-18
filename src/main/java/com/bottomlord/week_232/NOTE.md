# [LeetCode_162_寻找峰值](https://leetcode.cn/problems/find-peak-element)
## 解法
### 思路
- 思考过程：
  - 刨除特殊情况，做O(N)时间复杂度的模拟就可以
- 算法过程：
  - 如果数组长度为1，则直接返回坐标0
  - 如果数组的头尾坐标大于它们唯一相邻的元素，则返回该坐标
  - 在`[1, len - 2]`区间中迭代数据，判断是否当前元素相较于相邻元素是严格大于的，那么就返回当前坐标
### 代码
```java
class Solution {
    public int findPeakElement(int[] nums) {
        int len = nums.length;
        if (len == 1 || nums[0] > nums[1]) {
            return 0;
        }
        
        if (nums[len - 1] > nums[len - 2]) {
            return len - 1;
        }

        for (int i = 1; i < len - 1; i++) {
            if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
                return i;
            }
        }
        
        return 0;
    }
}
```
## 解法二
### 思路
- 思考过程：
  - 通过观察可以发现，峰值元素，一定是比前一个元素更大的
    - 如果比前一个元素小，那么可以判断，可能的峰值一定在当前坐标的前半部分
    - 如果比前一个元素大，那么可以判断，可能的峰值一定在当前坐标的后半部分
  - 通过如上的规律，就可以通过二分查找的方法，快速缩小搜索范围，从而在解法一的基础上提速
- 算法过程：
  - 二分查找，初始化头尾坐标分别为`0`和`len - 1`
  - 二分通过递归来实现：
    - 退出条件：头尾坐标相遇
    - 递归逻辑：
      - 通过头尾坐标计算得到`mid`值
      - 比较`mid`与`mid + 1`元素（选择+1而不是思考过程中的-1，是因为-1会导致数组越界，不方便处理，而+1，在计算`mid`的时候因为int的截取小数部分特性，所以不会越界）
        - `nums[mid] > nums[mid + 1]`，搜索前半部分
        - 相反则搜索后半部分
  - 递归结束，返回递归结果即可
### 代码
```java
class Solution {
    public int findPeakElement(int[] nums) {
        return binarySearch(0, nums.length - 1, nums);
    }
    
    private int binarySearch(int head, int tail, int[] nums) {
        if (head == tail) {
            return head;
        }
        
        int mid = head + (tail - head) / 2;
        return nums[mid] > nums[mid + 1] ? binarySearch(head, mid, nums) : binarySearch(mid + 1, tail, nums);
    }
}
```