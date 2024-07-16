# [LeetCode_2974_最小数字游戏](https://leetcode.cn/problems/minimum-number-game)
## 解法
### 思路
- 排序`nums`
- 初始化一个新的数组`arr`，长度与`nums`一致
- 从坐标0开始遍历`nums`，步长为2
- 每次循环就是模拟题目的操作
  - `arr[i] = nums[i + 1]`
  - `arr[i + 1] = nums[i]`
- 遍历结束后，返回`arr`
### 代码
```java
class Solution {
    public int[] numberGame(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] arr = new int[n];
        for (int i = 0; i < n; i += 2) {
            arr[i] = nums[i + 1];
            arr[i + 1] = nums[i]; 
        }
        return arr;
    }
}
```
# [LeetCode_3011_判断一个数组是否可以变为有序](https://leetcode.cn/problems/find-if-array-can-be-sorted)
## 解法
### 思路
- 经过思考发现，题目做的限制，其实将数组`nums`拆分成了`k`个连续子数组，这些子数组有一个特征，就是他们的二进制1的个数是相同的，如果经过排序后的`nums`，某个元素进入到1的个数不同的区块中，那么这种交换是不可能发生的
- 于是，将`nums`升序排序后，是否存在跨区块的元素存在，如果存在，就无法完成转换
- 同时，就算都在相同区块中，如果相对位置不同，也即，原来这个位置的二进制1的个数是5，现在不是，那么实际也是不可以的
- 所以，可以先对原数组的每个坐标位置的二进制1的个数进行计算，并存储，然后再对排序后的数组元素依次计算（此处计算可以通过记事本缓存来省略），如果不想等，就返回false
- 算法过程：
  - 初始化数组`bucket`，用于存储`nums`数组原坐标位置的元素1的个数
  - 初始化Map来存储元素与其对应2进制的个数
  - 遍历`nums`，计算每个元素的1的个数
  - 将`nums`排序
  - 遍历排序后的`nums`，根据`memo`来判断是否当前元素与原数组对应坐标的1的个数相等，如果不想等，返回false
  - 否则，遍历结束后，返回true
### 代码
```java

```
