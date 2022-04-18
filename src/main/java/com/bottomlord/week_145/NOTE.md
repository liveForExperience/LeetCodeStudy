# [LeetCode_2239_找到最接近0的数字](https://leetcode-cn.com/problems/find-closest-number-to-zero/)
## 解法
### 思路
- 遍历数组
- 计算和0的距离，找 到距离最小值
- 记录这个最小值用于后续比较，同时记录产生距离的元素值，用于判断当距离相等时，是否需要替换为较大的那个值作为答案
- 遍历结束后，返回暂存的元素
### 代码
```java
class Solution {
    public int findClosestNumber(int[] nums) {
        int min = Integer.MAX_VALUE, ans = Integer.MIN_VALUE;
        for (int num : nums) {
            int distance = Math.abs(num);
            if (distance < min) {
                min = distance;
                ans = num;
            } else if (distance == min) {
                ans = Math.max(ans, num);
            }
        }
        
        return ans;
    }
}
```
# [LeetCode_LCP50_宝石补给](https://leetcode-cn.com/problems/WHnhjV/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public int giveGem(int[] gem, int[][] operations) {
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int[] operation : operations) {
            int x = operation[0], y = operation[1];
            int count = gem[x] / 2;
            gem[x] -= count;
            gem[y] += count;
        }

        for (int num : gem) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        return max - min;
    }
}
```