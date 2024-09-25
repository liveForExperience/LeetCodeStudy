# [Contest_1_字母在字符串中的百分比](https://leetcode.cn/problems/percentage-of-letter-in-string/)
## 解法
### 思路
- 遍历字符串并记录目标字符出现的个数
- 返回总长度乘100再除个数的int值作为结果
### 代码
```java
class Solution {
    public int percentageLetter(String s, char letter) {
        int n = s.length(), count = 0;
        for (char c : s.toCharArray()) {
            if (c == letter) {
                count++;
            }
        }

        return count *100 / n;
    }
}
```
# [Contest_2_装满石头的背包的最大数量](https://leetcode.cn/problems/maximum-bags-with-full-capacity-of-rocks/)
## 解法
### 思路
- 用数组记录每个背包还能放入的石头个数
- 排序该数组
- 从小到大消耗可以增加的石头数量，并计数，直到消耗完
- 遍历结束或者消耗完石头，就返回累加的个数值
### 代码
```java
class Solution {
    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int n = capacity.length;
        int[] bucket = new int[n];
        for (int i = 0; i < n; i++) {
            bucket[i] = capacity[i] - rocks[i];
        }

        Arrays.sort(bucket);
        int count = 0;
        for (int num : bucket) {
            if (num == 0) {
                count++;
                continue;
            }
            
            if (num <= additionalRocks) {
                count++;
                additionalRocks -= num;
                continue;
            }
            
            if (additionalRocks <= 0) {
                break;
            }
        }
        
        return count;
    }
}
```
# [Contest_3_表示一个折线图的最小线段数](https://leetcode.cn/problems/minimum-lines-to-represent-a-line-chart/)
## 解法
### 思路
- 计算如果两组点的斜率相同，且其中一个点重复，则这两组点必定在同一条直线上
- 使用bigDecimal来避免double的精度丢失
### 代码
```java
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Comparator;
class Solution {
    public int minimumLines(int[][] stockPrices) {
        int n = stockPrices.length, ans = 0;
        Arrays.sort(stockPrices, Comparator.comparingInt(x -> x[0]));
        for (int i = 0; i < n - 1;) {
            int j = i + 1;
            int[] x = stockPrices[i], y = stockPrices[j];
            BigDecimal cur = count(x, y);
            while (j < n) {
                if (count(x, stockPrices[j]).equals(cur)) {
                    j++;
                } else {
                    break;
                }
            }

            ans++;

            i = j - 1;
        }

        return ans;
    }

    private BigDecimal count(int[] x, int[] y) {
        return (new BigDecimal(y[1]).subtract(new BigDecimal(x[1])))
                .divide(new BigDecimal(y[0]).subtract(new BigDecimal(x[0])), 20, RoundingMode.HALF_UP);
    }
}
```
## 解法二
### 思路
- 使用乘法替换除法，避免浮点数的精度丢失问题
- 计算线段个数的时候，只要连续的3个点不能保证是相同的斜率，那么就说明多了一个线段，这样计算的时候复杂度也会小很多
- 还需要注意，因为是在变化的时候才会更新ans值，所以ans值需要初始化第一个线段，也就是初始化为1
### 代码
```java
class Solution {
    public int minimumLines(int[][] stockPrices) {
        int n = stockPrices.length, ans = 1;
        Arrays.sort(stockPrices, Comparator.comparingInt(x -> x[0]));
        if (n == 1) {
            return 0;
        }
        for (int i = 2; i < n; i++) {
            int x1 = stockPrices[i][0] - stockPrices[i - 1][0], y1 = stockPrices[i][1] - stockPrices[i - 1][1],
                    x2 = stockPrices[i][0] - stockPrices[i - 2][0], y2 = stockPrices[i][1] - stockPrices[i - 2][1];

            if (x1 * y2 != x2 * y1) {
                ans++;
            }
        }

        return ans;
    }
}
```
# [Contest_4_巫师的总力量和](https://leetcode.cn/problems/sum-of-total-strength-of-wizards/)
## 解法
### 思路

### 代码
```java

```