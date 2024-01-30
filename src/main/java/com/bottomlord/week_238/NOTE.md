# [LeetCode_2808_使循环数组所有元素相等的最少秒数](https://leetcode.cn/problems/minimum-seconds-to-equalize-a-circular-array)
## 解法
### 思路
- 思考过程：
  - 因为每一秒，`nums[i]`都可以选择赋值为左边（如果是头就是结尾元素）、右边（如果是尾就是头元素）或者自身
  - 那么，如果假定以数组中的某个元素`x`为需要全部转变为的数字，那么以该元素为中心，每一秒都能够变化其身边的2个元素
  - 进一步，如果`x`元素有2个，那么变化的速度就会是2个元素之间的距离除以2再向下取整，向下取整的原因是：
    - 如果距离为奇数，那么中间包含的元素个数就是偶数，那么距离`n`除以2再向下取整，就和`(n - 1) / 2`是等价的，也即秒数
    - 如果距离是偶数，那么中间包含的元素个数就是奇数，那么距离`n`除以2再向下取整，就和`(n - 1) / 2 + 1`是等价的，也即秒数
  - 再进一步，如果`x`元素有n个，那么求得2个值为`x`的相邻坐标的距离的最大值，然后通过如上的计算方式，就能得到这个`x`值作为目标元素时，需要使用的最少秒数，那么遍历所有出现在数组中的值，判断对应的相邻坐标，就能得到题目要求的最少秒数
- 算法过程：
  - 初始化一个哈希表`map`，键为元素值`x`，值为`list`，存储的是`x`对应的坐标
  - 遍历`nums`数组，将值和坐标存储到`map`中
  - 初始化一个变量`min`，用于作为答案的最小秒数，初始化为int最大值
  - 遍历`map`的`values`值列表，遍历坐标列表，计算相邻的最大距离，计算得到需要的最小秒数，然后比较并更新到`min`中
  - 在计算最大距离的时候，最后一个元素需要和第一个元素通过环绕的方式计算得到距离，为了简化计算，可以将第一个元素的坐标值增加`nums`长度后放入列表的最后，从而简化计算
  - 遍历`values`结束后，返回`min`即可
### 代码
```java
class Solution {
    public int minimumSeconds(List<Integer> nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.size(); i++) {
            map.computeIfAbsent(nums.get(i), x -> new ArrayList<>()).add(i);
        }

        int min = Integer.MAX_VALUE;
        for (List<Integer> indexes : map.values()) {
            int seconds = 0;

            indexes.add(indexes.get(0) + nums.size());

            for (int i = 1; i < indexes.size(); i++) {
                seconds = Math.max(seconds, (indexes.get(i) - indexes.get(i - 1)) / 2);
            }
            
            min = Math.min(min, seconds);
        }
        
        return min;
    }
}
```