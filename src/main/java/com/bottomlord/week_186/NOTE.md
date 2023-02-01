# [LeetCode_1632_矩阵转换后的秩](https://leetcode.cn/problems/rank-transform-of-a-matrix/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_1669_合并两个链表](https://leetcode.cn/problems/merge-in-between-linked-lists/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode head = null, tail = null, pre = null, node = list1;
        int i = 0;
        while (node != null) {
            if (i == a) {
                head = pre;
            }

            if (i == b) {
                tail = node.next;
                break;
            }

            pre = node;
            node = node.next;
            i++;
        }

        if (head == null) {
            return null;
        }

        pre.next = null;
        head.next = list2;
        node = list2;
        pre = null;

        while (node != null) {
            pre = node;
            node = node.next;
        }

        if (pre == null) {
            return null;
        }

        pre.next = tail;

        return list1;
    }
}
```
# [LeetCode_2540_最小公共值](https://leetcode.cn/problems/minimum-common-value/)
## 解法
### 思路
双指针
### 代码
```java
class Solution {
    public int getCommon(int[] nums1, int[] nums2) {
        int i1 = 0, i2 = 0, n1 = nums1.length, n2 = nums2.length;
        while (i1 < n1 && i2 < n2) {
            if (nums1[i1] == nums2[i2]) {
                return nums1[i1];
            } else if (nums1[i1] < nums2[i2]) {
                i1++;
            } else {
                i2++;
            }
        }

        return -1;
    }
}
```
# [LeetCode_2544_交替数字和](https://leetcode.cn/problems/alternating-digit-sum/)
## 解法
### 思路
- 计算得到数字长度
- 根据长度定义数组
- 计算填充数组
- 根据数组获取数值
### 代码
```java
class Solution {
    public int alternateDigitSum(int n) {
        int len = 0, num = n;
        while (num > 0) {
            len++;
            num /= 10;
        }
        
        int[] arr = new int[len];
        int index = 0;
        while (n > 0) {
            arr[index++] = n % 10;
            n /= 10;
        }
        
        boolean flag = true;
        int sum = 0;
        for (int i = len - 1; i >= 0; i--) {
            sum = flag ? sum + arr[i] : sum - arr[i];
            flag = !flag;
        }
        
        return sum;
    }
}
```
# [LeetCode_2549_统计桌面上的不同数字](https://leetcode.cn/problems/count-distinct-numbers-on-board/)
## 解法
### 思路
- n-1一定满足要求，不断循环后，[2,n]一定都会在桌面上
- 1是一个特例，本身就放在桌上，直接返回1
### 代码
```java
class Solution {
    public int distinctIntegers(int n) {
        return n == 1 ? 1 : n - 1;
    }
}
```
# [LeetCode_702_搜索长度未知的有序数组](https://leetcode.cn/problems/search-in-a-sorted-array-of-unknown-size/)
## 解法
### 思路
二分查找
### 代码
```java
class Solution {
    public int search(ArrayReader reader, int target) {
        int head = 0, tail = 9999;

        while (head <= tail) {
            int mid = head + (tail - head) / 2;
            int num = reader.get(mid);
            
            if (num == Integer.MAX_VALUE) {
                tail--;
                continue;
            }
            
            if (num == target) {
                return mid;
            } else if (num > target) {
                tail = mid - 1;
            } else {
                head = mid + 1;
            }
        }
        
        return -1;
    }
}
```