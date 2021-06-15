# [LeetCode_852_山脉数组的峰顶索引](https://leetcode-cn.com/problems/peak-index-in-a-mountain-array/)
## 解法
### 思路
遍历找到比前后元素都大的元素坐标，找到后直接返回
### 代码
```java
class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] < arr[i] && arr[i + 1] < arr[i]) {
                return i;
            }
        }
        
        return -1;
    }
}
```
## 解法二
### 思路
二分查找
### 代码
```java
class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        int head = 1, tail = arr.length - 2;
        while (head <= tail) {
            int mid = (tail - head) / 2 + head;
            
            if (arr[mid - 1] < arr[mid] && arr[mid + 1] < arr[mid]) {
                return mid;
            } else if (arr[mid - 1] < arr[mid]) {
                head = mid + 1;
            } else {
                tail = mid - 1;
            }
        }
        
        return -1;
    }
}
```
# [LeetCode_279_完全平方数](https://leetcode-cn.com/problems/perfect-squares/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_1449_数位成本和为目标值的最大数字](https://leetcode-cn.com/problems/form-largest-integer-with-digits-that-add-up-to-target/)
## 解法
### 思路

### 代码
```java

```