# [LeetCode_540_有序数组中的单一元素](https://leetcode-cn.com/problems/single-element-in-a-sorted-array/)
## 解法
### 思路
- 两个一样的数字抑或后是0
- 基于如上特性，遍历数组并抑或元素
- 遍历结束，不停抑或后得到的值就是单一元素
### 代码
```java
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int num = 0;
        for (int i : nums) {
            num ^= i;
        }
        return num;
    }
}
```