# [LeetCode_1905_统计子岛屿](https://leetcode.cn/problems/count-sub-islands/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_732_我的日程安排III](https://leetcode.cn/problems/my-calendar-iii/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_2283_判断一个数的数字技术是否等于数位的值](https://leetcode.cn/problems/check-if-number-has-equal-digit-count-and-digit-value/)
## 解法
### 思路
- hash表计数
  - 记录数位应该有的个数
  - 记录数位在字符串中实际出现的个数
- 遍历字符串，根据数位比较两个记录值是否相等，如果不相等就返回false
- 遍历结束，返回true
### 代码
```java
class Solution {
    public boolean digitCount(String num) {
        int[] nums = new int[10], targets = new int[10];
        char[] cs = num.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            targets[i] = cs[i] - '0';
            nums[cs[i] - '0']++;
        }

        for (int i = 0; i < cs.length; i++) {
            if (targets[i] != nums[i]) {
                return false;
            }
        }

        return true;
    }
}
```