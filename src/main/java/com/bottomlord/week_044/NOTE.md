# Interview_1709_第k个数
## 题目
有些数的素因子只有 3，5，7，请设计一个算法找出第 k 个数。注意，不是必须有这些素因子，而是必须不包含其他的素因子。例如，前几个数按顺序应该是 1，3，5，7，9，15，21。

示例 1:
```
输入: k = 5

输出: 9
```
## 解法
### 思路
动态规划：
- 初始化3个指针：
    - p3：该指针指向的数字只乘以3
    - p5；该指针指向的数字只乘以5
    - p7：该指针指向的数字值乘以7
- `dp[i]`：第i个丑数
- 状态转移方程：
    - `dp[i] = min(dp[p3] * 3, dp[p5] * 5, dp[p6] * 6)`
    - 谁是最小值，谁的指针+1
- 初始化：
    - dp[0] = 1
    - p3 = 0
    - p5 = 0
    - p7 = 0
- 结果：返回`dp[k - 1]`
### 代码
```java
class Solution {
    public int getKthMagicNumber(int k) {
        int p3 = 0, p5 = 0, p7 = 0;
        int[] dp = new int[k];
        dp[0] = 1;
        for (int i = 1; i < k; i++) {
            dp[i] = Math.min(dp[p3] * 3, Math.min(dp[p5] * 5, dp[p7] * 7));
            
            if (dp[p3] * 3 == dp[i]) {
                p3++;
            }

            if (dp[p5] * 5 == dp[i]) {
                p5++;
            }

            if (dp[p7] * 7 == dp[i]) {
                p7++;
            }
        }
        
        return dp[k - 1];
    }
}
```
# Interview_1710_主要元素
## 题目
如果数组中多一半的数都是同一个，则称之为主要元素。给定一个整数数组，找到它的主要元素。若没有，返回-1。

示例 1：
```
输入：[1,2,5,9,5,9,5,5,5]
输出：5
```
示例 2：
```
输入：[3,2]
输出：-1
```
示例 3：
```
输入：[2,2,1,1,1,2,2]
输出：2
```
说明：
```
你有办法在时间复杂度为 O(N)，空间复杂度为 O(1) 内完成吗？
```
## 解法
### 思路
散列表计数
### 代码
```java
class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int len = nums.length;
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > len / 2) {
                return entry.getKey();
            }
        }
        
        return -1;
    }
}
```
## 解法二
### 思路
摩尔投票：
- 因为主要元素`major`出现的个数过半，所以如果`major`出现一次就+1，其他数字出现一次就-1，那么最终`major`的计数值`count`最终还是会至少剩下1个
- 定义两个变量：
    - count
    - major
- 遍历数组
    - 如果count值为0，将当前值设置为major值
    - 如果count值不为0
        - 当前值与major值相等，count++
        - 当前值与major值不等，count--
### 代码
```java
class Solution {
    public int majorityElement(int[] nums) {
        int count = 0, major = -1;
        for (int num : nums) {
            if (count == 0) {
                major = num;
                count++;
            } else {
                if (major == num) {
                    count++;
                } else {
                    count--;
                }
            }
        }

        return count > 0 ? major : -1;
    }
}
```