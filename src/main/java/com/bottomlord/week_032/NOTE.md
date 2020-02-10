# LeetCode_470_用Rand7实现Rand10
## 题目
已有方法 rand7 可生成 1 到 7 范围内的均匀随机整数，试写一个方法 rand10 生成 1 到 10 范围内的均匀随机整数。

不要使用系统的 Math.random() 方法。

示例 1:
```
输入: 1
输出: [7]
```
示例 2:
```
输入: 2
输出: [8,4]
```
示例 3:
```
输入: 3
输出: [8,1,10]
```
提示:
```
rand7 已定义。
传入参数: n 表示 rand10 的调用次数。
```
进阶:
```
rand7()调用次数的 期望值 是多少 ?
你能否尽量少调用 rand7() ?
```
## 解法
### 思路
- 通过`rand7()`可以获得两组均匀概率的数字：
    - `rand7()`可以获得`[1,7]`
    - `(rand7() - 1) * rand7()`可以获得`[0, 7, 14, 21, ..., 42]`的等差数列
- 将上述两组均匀概率的数字相加，去`[1, 40]`范围的值，就能获得10进制的均匀概率的值
- 公式`1 + rand7() + (rand7() - 1) * rand7()`，并只取`[1, 40]`
- 返回取余10的结果就是题目要求的答案
### 代码
```java
class Solution extends SolBase {
    public int rand10() {
        int index, col, row;
        do {
            col = rand7();
            row = rand7();
            index = col + (row - 1) * 7;
        } while (index > 40);

        return 1 + (index - 1) % 10;
    }
}
```
## 优化代码
### 思路
- 解法一中，被拒绝的范围是`[41, 49]`，这是一个`[1,9]`的区间
- 如果再加上一个`rand7()`，就能获得`[1, 63]`，这样拒绝的范围就是`[61,63]`，也就是`[1,3]`,这个值就成了新的行
- 如果再加上一个`rand7()`，就能获得`[1, 21]`，这样拒绝的范围就是`21`，这个值就成了新的行
- 这个时候就重新开始求`[1,49]`的随机数
### 代码
```java
/**
 * The rand7() API is already defined in the parent class SolBase.
 * public int rand7();
 * @return a random integer in the range 1 to 7
 */
class Solution extends SolBase {
    public int rand10() {
        while (true) {
            int index = rand7() + (rand7() - 1) * 7;
            if (index <= 40) {
                return 1 + (index - 1) % 10;
            }

            index = rand7() + (index - 40 - 1) * 7;
            if (index <= 60) {
                return 1 + (index - 1) % 10;
            }

            index = rand7() + (index - 60 - 1) * 7;
            if (index <= 20) {
                return 1 + (index - 1) % 10;
            }
        }
    }
}
```
# LeetCode_416_分割等和子集
## 题目
给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。

注意:
```
每个数组中的元素不会超过 100
数组的大小不会超过 200
```
示例 1:
```
输入: [1, 5, 11, 5]

输出: true

解释: 数组可以分割成 [1, 5, 5] 和 [11].
```
示例 2:
```
输入: [1, 2, 3, 5]

输出: false

解释: 数组不能分割成两个元素和相等的子集.
```
## 解法
### 思路
动态规划：
- 背包问题，从一定的物品中不重复的选出并放入背包中，获得指定的收益
- `dp[i][j]`：从`[0, i]`的范围中是否能找到一些数，使它们的和为`j`
- 状态转移方程：
    - 如果`nums[i] == j`，那么直接就是true
    - 如果不选择`nums[i]`，那么就看`dp[i - 1][j]`，也就是`[0, i - 1]`的范围中是否能找到这些数使得它们的和为`j`
    - 如果选择`nums[j]`，那么就看`dp[i - 1][j - nums[i]]`，因为`[0, i - 1]`范围中已经没有数能够相加等于`j`，那么就看这些数能否为`j - nums[i]`，那么再加上当前`nums[i]`，那么也能在`[0, i]`的范围中找到相加为`j`的数
- 初始化：
    - `dp[0][0] = true`
    - `dp[0][nums[0]]`：第一个元素，如果不大于target，那么就是true
- 过程：
    - 求得数组中元素的总和，从而获得半值`target`，如果总和为奇数，直接返回false
    - 嵌套遍历：
        - 外层遍历数组小标
        - 内层遍历`[0, target]`
        - 内层逻辑：
            - `nums[i] == target`，直接返回true
            - `dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]]`，还有一个前置条件，`nums[i] <= j`
    - 最终返回`dp[len - 1][target]`，因为只要证明了在整个数组范围内，有若干元素的和是`target`，也就必定有剩下的元素的和也为`target`
### 代码
```java
class Solution {
    public boolean canPartition(int[] nums) {
        int len = nums.length, sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if ((sum & 1) == 1) {
            return false;
        }

        int target = sum / 2;
        boolean[][] dp = new boolean[len][target + 1];
        if (nums[0] <= target) {
            dp[0][nums[0]] = true;
        }

        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= target; j++) {
                if (j == nums[i]) {
                    dp[i][j] = true;
                    continue;
                }

                if (nums[i] <= j) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                }
            }
        }

        return dp[len - 1][target];
    }
}
```
## 解法二
### 思路
记忆化回溯：
- 递归遍历整个样本空间，查看是否有元素相加等于`sum / 2`
- 使用两个set来存储坐标和数值
    - 回溯是修改记录坐标的状态，如果当前值没有相应的元素相加可以匹配，就回溯并遍历下一个元素
    - 同时数值也要存储，用来记录当前这个值已经被检查过，不用重复检查
### 代码
```java
class Solution {
    public boolean canPartition(int[] nums) {
        if (nums.length == 0) {
            return false;
        }

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if ((sum & 1) == 1) {
            return false;
        }

        Set<Integer> numSet = new HashSet<>();
        Set<Integer> indexSet = new HashSet<>();

        return backTrack(sum / 2, nums, numSet, indexSet);
    }

    private boolean backTrack(int num, int[] nums, Set<Integer> numSet, Set<Integer> indexSet) {
        if (num == 0) {
            return true;
        }

        for (int i = 0; i < nums.length; i++) {
            if (indexSet.contains(i)) {
                continue;
            }

            int a = num - nums[i];

            if (a < 0 || numSet.contains(a)) {
                continue;
            }

            if (a == 0) {
                return true;
            }

            numSet.add(a);
            indexSet.add(i);
            if (backTrack(a, nums, numSet, indexSet)) {
                return true;
            }
            indexSet.remove(i);
        }
        
        return false;
    }
}
```