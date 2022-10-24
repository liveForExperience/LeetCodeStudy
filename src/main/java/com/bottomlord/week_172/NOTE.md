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
# [LeetCode_915_分割数组](https://leetcode.cn/problems/partition-array-into-disjoint-intervals/)
## 解法
### 思路
- 维护连个数组
    - 记录数组从左侧开始的，截止到坐标i的子数组中的最大值
    - 记录数组从右侧开始的，截止到坐标i的子数组中的最小值
- 同时遍历2个数组，求出从左侧开始，第一个数组的坐标的值小于等于第二个数组坐标的值的坐标
### 代码
```java
class Solution {
    public int partitionDisjoint(int[] nums) {
        int n = nums.length, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        int[] lefts = new int[n], rights = new int[n];
        for (int i = 0; i < n; i++) {
            max = Math.max(max, nums[i]);
            lefts[i] = max;
        }

        for (int i = n - 1; i >= 0; i--) {
            min = Math.min(min, nums[i]);
            rights[i] = min;
        }

        for (int i = 1; i < n; i++) {
            if (lefts[i - 1] <= rights[i]) {
                return i;
            }
        }

        return n;
    }
}
```
## 解法二
### 思路
在解法一的基础上，省略一次左侧的遍历
### 代码
```java
class Solution {
    public int partitionDisjoint(int[] nums) {
        int n = nums.length, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        int[] rights = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            min = Math.min(min, nums[i]);
            rights[i] = min;
        }
        
        for (int i = 0; i < n - 1; i++) {
            max = Math.max(max, nums[i]);
            if (max <= rights[i + 1]) {
                return i + 1;
            }
        }
        
        return n;
    }
}
```