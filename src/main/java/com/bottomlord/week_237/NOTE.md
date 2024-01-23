# [LeetCode_2765_最长交替子数组](https://leetcode.cn/problems/longest-alternating-subarray)
## 解法
### 思路
- 思考过程：
  - 题目要求的子数组是基于相邻元素的差来判定是否合法的，2者之间的差需要在1与-1之间交替
  - 所以，如果一个子数组判定完毕之后，就不需要再判断这个子数组中的其余元素，但最后1个元素是例外，因为最后1个元素和后续的元素可能不能作为前一个子数组的交替部分，但可能成为下一个子数组开头的交替部分。
- 算法过程：
  - 初始化一个变量`cnt`，用于记录答案的计数值，初始化为-1
  - 从坐标1开始循环遍历`nums`数组
  - 内部初始化一个布尔变量`flag`，用于控制交替的状态
  - 从当前遍历到的元素开始，判断与前一个元素是否能形成一个最小的交替子数组，如果可以，就基于这组元素开始循环，并计数
  - 内部的循环结束之后，如果计数值大于1，那么
    - 比较并更新`cnt`的值
  - 如果没有开始内层的循环，就自增外层循环的坐标
  - 循环结束后，返回`cnt`作为结果
### 代码
```java
class Solution {# [LeetCode_2765_最长交替子数组](https://leetcode.cn/problems/longest-alternating-subarray)
## 解法
### 思路
- 思考过程：
  - 题目要求的子数组是基于相邻元素的差来判定是否合法的，2者之间的差需要在1与-1之间交替
  - 所以，如果一个子数组判定完毕之后，就不需要再判断这个子数组中的其余元素，但最后1个元素是例外，因为最后1个元素和后续的元素可能不能作为前一个子数组的交替部分，但可能成为下一个子数组开头的交替部分。
- 算法过程：
  - 初始化一个变量`cnt`，用于记录答案的计数值，初始化为-1
  - 从坐标1开始循环遍历`nums`数组
  - 内部初始化一个布尔变量`flag`，用于控制交替的状态
  - 从当前遍历到的元素开始，判断与前一个元素是否能形成一个最小的交替子数组，如果可以，就基于这组元素开始循环，并计数
  - 内部的循环结束之后，如果计数值大于1，那么
    - 比较并更新`cnt`的值
  - 如果没有开始内层的循环，就自增外层循环的坐标
  - 循环结束后，返回`cnt`作为结果
### 代码
```java
class Solution {
    public int alternatingSubarray(int[] nums) {
        int cnt = -1, n = nums.length;
        for (int i = 1; i < n;) {
            int c = 1;
            boolean flag = true;
            while (i < n && nums[i] - nums[i - 1] == (flag ? 1 : -1)) {
                flag = !flag;
                i++;
                c++;
            }

            if (c > 1) {
                cnt = Math.max(cnt, c);
            }

            if (c == 1) {
                i++;
            }
        }

        return cnt;
    }
}
```
    public int alternatingSubarray(int[] nums) {
        int cnt = -1, n = nums.length;
        for (int i = 1; i < n;) {
            int c = 1;
            boolean flag = true;
            while (i < n && nums[i] - nums[i - 1] == (flag ? 1 : -1)) {
                flag = !flag;
                i++;
                c++;
            }

            if (c > 1) {
                cnt = Math.max(cnt, c);
            }

            if (c == 1) {
                i++;
            }
        }

        return cnt;
    }
}
```