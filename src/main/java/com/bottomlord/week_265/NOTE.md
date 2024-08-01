# [LeetCode_LCP40_心算挑战](https://leetcode.cn/problems/uOAnQW)
## 解法
### 思路
- 对数组`cards`排序
- 圈定`cnt`大个元素对应的子数组
- 如果子数组总和`sum`为偶数，返回结果
- 否则，找到子数组中最小的奇偶数，找出非子数组中的最大奇偶数
- 如果最大奇数和最小偶数都存在，那么计算`sum - minEven + maxOdd`，否则为0
- 如果最大偶数和最小奇数都存在，那么计算`sum - minOdd + maxEven`，否则为0
- 最后计算2种情况的最大值，作为结果返回
### 代码
```java
class Solution {
    public int maxmiumScore(int[] cards, int cnt) {
        Arrays.sort(cards);
        int len = cards.length, start = len - cnt,
            minEven = -1, minOdd = -1, sum = 0;

        for (int i = start; i < cards.length; i++) {
            int num = cards[i];
            sum += num;

            if (minOdd != -1 && minEven != -1) {
                continue;
            }
            
            if (minOdd == -1 && num % 2 == 1) {
                minOdd = num;
            }
            
            if (minEven == -1 && num % 2 == 0) {
                minEven = num;
            }
        }
        
        if (sum % 2 == 0) {
            return sum;
        }
        
        int maxEven = -1, maxOdd = -1;
        for (int i = start - 1; i >= 0; i--) {
            int num = cards[i];
            
            if (maxOdd != -1 && maxEven != -1) {
                continue;
            }

            if (maxOdd == -1 && num % 2 == 1) {
                maxOdd = num;
            }

            if (maxEven == -1 && num % 2 == 0) {
                maxEven = num;
            }
        }
        
        int sum1 = 0, sum2 = 0;
        if (minOdd != -1 && maxEven != -1) {
            sum1 = sum - minOdd + maxEven;
        }
        
        if (minEven != -1 && maxOdd != -1) {
            sum2 = sum - minEven + maxOdd;
        }
        
        return Math.max(sum1, sum2);
    }
}
```