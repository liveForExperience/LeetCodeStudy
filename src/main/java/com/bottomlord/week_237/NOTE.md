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
# [LeetCode_2859_计算K置位下标对应元素的和](https://leetcode.cn/problems/sum-of-values-at-indices-with-k-set-bits/)
## 解法
### 思路
- 思考过程： 
  - 遍历`nums`，对下标做置位个数的计算，然后将符合的元素累加起来，遍历结束后返回累加值
- 算法过程：
  - 通过`n & (n - 1)`可以消去最低位置位的特性，循环处理并记录循环次数。将次数记录下来后，就是当前数字的置位。
  - 遍历`nums`数组，通过如上的方式判断当前坐标的置位是否是k，如果符合就把元素值累加起来
  - 遍历结束后，返回累计值
### 代码
```java
class Solution {
    public int sumIndicesWithKSetBits(List<Integer> nums, int k) {
        int sum = 0;
        for (int i = 0; i < nums.size(); i++) {
            int num = nums.get(i);
            if (check(i, k)) {
                sum += num;
            }
        }
        return sum;
    }
    
    private boolean check(int num, int k) {
        int cnt = 0;
        while (num > 0) {
            num = num & (num - 1);
            cnt++;
        }
        
        return cnt == k;
    }
}
```
# [LeetCode_2865_美丽塔I](https://leetcode.cn/problems/beautiful-towers-i/)
## 解法
### 思路
- 思考过程：
  - 模拟
  - 遍历`maxHeights`数组的每一个元素，从当前元素开始向前和向后分别移动，并计算移动过程中的相对小值，将这个相对小值作为需要累加的值累加起来
  - 2个方向都遍历完成后，比较并保留相对大的累加值
- 算法过程：
  - 初始化变量`max`用于暂存高度和的最大值
  - 遍历`maxHeights`数组
    - 初始化一个`pre`变量，值等于当前遍历到的元素
    - 初始化一个`sum`变量，用于暂存以当前元素为山峰的高度和
    - 基于当前坐标，向前和向后循环遍历，分别都更新`pre`
      - 更新规则：`pre = Math.min(pre, maxHeights[j])`（`j`代表内层向前向后循环的坐标）
      - 累加`pre`到`sum`上
    - 内层循环结束后，将`sum`与`max`进行比较，取较大值作为`max`的值
  - 循环结束后，返回`max`作为结果
### 代码
```java
class Solution {
    public long maximumSumOfHeights(List<Integer> maxHeights) {
        long max = 0, n = maxHeights.size();
        for (int i = 0; i < maxHeights.size(); i++) {
            long num = maxHeights.get(i), pre = num, sum = num;

            for (int j = i + 1; j < n; j++) {
                pre = Math.min(pre, maxHeights.get(j));
                sum += pre;
            }

            pre = num;
            for (int j = i - 1; j >= 0; j--) {
                pre = Math.min(pre, maxHeights.get(j));
                sum += pre;
            }

            max = Math.max(sum, max);
        }

        return max;
    }
}
```