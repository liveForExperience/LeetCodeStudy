# LeetCode_325_和等于k的最长子数组长度
## 题目
给定一个数组 nums 和一个目标值 k，找到和等于 k 的最长子数组长度。如果不存在任意一个符合要求的子数组，则返回 0。

注意:
```
 nums 数组的总和是一定在 32 位有符号整数范围之内的。
```
示例 1:
```
输入: nums = [1, -1, 5, -2, 3], k = 3
输出: 4 
解释: 子数组 [1, -1, 5, -2] 和等于 3，且长度最长。
```
示例 2:
```
输入: nums = [-2, -1, 2, 1], k = 1
输出: 2 
解释: 子数组 [-1, 2] 和等于 1，且长度最长。
```
## 解法
### 思路
前缀和
### 代码
```java
class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        int len = nums.length;
        int[] sums = new int[len];
        
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sums[i] = (sum += nums[i]);
        }
        
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (sums[j] - sums[i] + nums[i] == k) {
                    max = Math.max(max, j - i + 1);
                }
            }
        }

        return max == Integer.MIN_VALUE ? 0 : max;
    }
}
```
## 解法二
### 思路
hash表+前缀和
### 代码
```java
class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        int len = nums.length, sum = 0;
        int[] sums = new int[len];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            sums[i] = (sum += nums[i]);

            List<Integer> list = map.getOrDefault(sum, new ArrayList<>());
            list.add(i);
            map.put(sum, list);
        }

        int max = 0;
        for (int i = 0; i < len; i++) {
            int num = sums[i] + k - nums[i];
            if (map.containsKey(num)) {
                for (int index : map.get(num)) {
                    max = Math.max(max, index - i + 1);
                }
            }
        }
        
        return max;
    }
}
```
## 优化代码
### 思路
在解法二的基础上，因为求的是最大举例，而循环的是起始举例，所以hash的value存储对应前缀和最大的坐标值即可，不用存储list
### 代码
```java
class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        int len = nums.length, sum = 0;
        int[] sums = new int[len];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            sums[i] = (sum += nums[i]);
            map.put(sum, i);
        }

        int max = 0;
        for (int i = 0; i < len; i++) {
            int num = sums[i] + k - nums[i];
            if (map.containsKey(num)) {
                max = Math.max(max, map.get(num) - i + 1);
            }
        }

        return max;
    }
}
```
# LeetCode_330_按要求补齐数组
## 题目
给定一个已排序的正整数数组 nums，和一个正整数 n 。从 [1, n] 区间内选取任意个数字补充到 nums 中，使得 [1, n] 区间内的任何数字都可以用 nums 中某几个数字的和来表示。请输出满足上述要求的最少需要补充的数字个数。

示例 1:
```
输入: nums = [1,3], n = 6
输出: 1 
解释:
根据 nums 里现有的组合 [1], [3], [1,3]，可以得出 1, 3, 4。
现在如果我们将 2 添加到 nums 中， 组合变为: [1], [2], [3], [1,3], [2,3], [1,2,3]。
其和可以表示数字 1, 2, 3, 4, 5, 6，能够覆盖 [1, 6] 区间里所有的数。
所以我们最少需要添加一个数字。
```
示例 2:
```
输入: nums = [1,5,10], n = 20
输出: 2
解释: 我们需要添加 [2, 4]。
```
示例 3:
```
输入: nums = [1,2,2], n = 5
输出: 0
```
## 解法
### 思路
- 假设miss是最小的不能被覆盖的值，这也就代表，`[0,miss)`是完全被覆盖的
- 因为覆盖的范围从1开始，所以假设第一个miss是1
- 然后开始遍历提供的数组，判断数组的值是否能够覆盖miss
- miss是1，那么能够覆盖1的值就是1，如果`nums[0] > 1`，说明需要添加1才能够覆盖
- 然后miss的值也就从1变成了`1 + 1`(miss + miss)
- 继续判断是否覆盖了，假设这次`nums[1] == 2`，覆盖了`miss = 2`，那么下一个miss就是`miss + nums[i]`，也就是这里的`2+2`，因为2是原来的最小的不能覆盖的值，现在添加并能覆盖了，那么比覆盖的值小的这个`nums[i]`与`miss`的和之内的值一定也能覆盖
- 重复按照如上的过程累加miss，直到miss大于预设值n
- 为了放置数组越界，miss需要声明为long类型
### 代码
```java
class Solution {
    public int minPatches(int[] nums, int n) {
        int len = nums.length, index = 0, count = 0;
        long miss = 1;
        while (miss <= n) {
            if (index < len && nums[index] <= miss) {
                miss += nums[index++];
            } else {
                miss += miss;
                count++;
            }
        }
        return count;
    }
}
```