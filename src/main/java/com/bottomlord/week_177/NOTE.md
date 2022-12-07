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
# [LeetCode_1775_通过最少操作次数使数组的和相等](https://leetcode.cn/problems/equal-sum-arrays-with-minimum-number-of-operations/)
## 解法
### 思路
- 设sums1作为nums1的总和，sums2作为nums2的总和
- 然后通过比较的方式，另sums1一定比sums2大，数组也相应交换
- diff = sums1 - sums2
- 较大的数组，每一个元素都可以通过缩减数值来减小diff
- 较小的数组，每一个元素都可以通过增加数值来减小diff
- 通过一个数组arr，长度为6，存储1-5这些差值存在的个数
- 然后从大到小遍历arr，看一下是哪些是否能通过这些值将diff缩小到0，并记录操作次数，如果无法达到目的，就返回-1
### 代码
```java

```