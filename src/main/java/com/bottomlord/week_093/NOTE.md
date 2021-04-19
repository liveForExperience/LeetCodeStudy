# [LeetCode_27_移除元素](https://leetcode-cn.com/problems/remove-element/submissions/)
## 解法
### 思路
- 使用一个坐标作为新数组的元素指针，然后遍历原数组，判断当前元素是否与val相等
    - 如果相等就跳过，继续移动原指针
    - 否则就将当前元素放在新指针对应的位置，同时移动新旧2个指针
- 遍历结束后，返回新指针的值，就是新数组的长度
### 代码
```java
class Solution {
    public int removeElement(int[] nums, int val) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                continue;
            }
            nums[index++] = nums[i];
        }
        return index;
    }
}
```