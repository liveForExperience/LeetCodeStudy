# [LeetCode_749_隔离病毒](https://leetcode.cn/problems/contain-virus/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_940_不同子序列](https://leetcode.cn/problems/distinct-subsequences-ii/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_1801_积压订单中的订单总数](https://leetcode.cn/problems/number-of-orders-in-the-backlog/)
## 解法
### 思路
优先级队列
- 初始化2个优先级队列
  - 存储buy类型订单
  - 存储sell类型订单
- 遍历订单列表，根据订单类型，到对应的队列中找是否能消除当前订单，如果可以就进行依次消除。
- 如果还有剩余，就放入对应的队列中
- 同样，如果队列中的订单被消耗完，那就将其从队列中去除
- 最后累加订单总数，并与10e7 + 9进行取模运算
### 代码
```java
class Solution {
    public int getNumberOfBacklogOrders(int[][] orders) {
                PriorityQueue<int[]> buyQ = new PriorityQueue<>((x, y) -> y[0] - x[0]),
                             sellQ = new PriorityQueue<>(Comparator.comparingInt(x -> x[0]));

        for (int[] order : orders) {
            int price = order[0], type = order[2];

            if (type == 0) {
                while (!sellQ.isEmpty()) {
                    int[] sell = sellQ.peek();
                    if (sell[0] > price) {
                        break;
                    }

                    int minCost = Math.min(sell[1], order[1]);
                    order[1] -= minCost;
                    sell[1] -= minCost;

                    if (sell[1] == 0) {
                        sellQ.poll();
                    }

                    if (order[1] == 0) {
                        break;
                    }
                }

                if (order[1] > 0) {
                    buyQ.offer(order);
                }
            } else {
                while (!buyQ.isEmpty()) {
                    int[] buy = buyQ.peek();
                    if (buy[0] < price) {
                        break;
                    }

                    int minCost = Math.min(buy[1], order[1]);
                    order[1] -= minCost;
                    buy[1] -= minCost;

                    if (buy[1] == 0) {
                        buyQ.poll();
                    }

                    if (order[1] == 0) {
                        break;
                    }
                }

                if (order[1] > 0) {
                    sellQ.offer(order);
                }
            }
        }

        int mod = 1000000007, ans = 0;
        while (!buyQ.isEmpty()) {
            ans += buyQ.poll()[1];
            ans %= mod;
        }
        
        while (!sellQ.isEmpty()) {
            ans += sellQ.poll()[1];
            ans %= mod;
        }
        
        return ans;
    }
}
```
# [LeetCode_2481_分割圆的最少切割次数](https://leetcode.cn/problems/minimum-cuts-to-divide-a-circle/)
## 解法
### 思路
观察发现：
- n为偶数：切割次数为n/2
- n为奇数且不为1时：切割次数为n
- n为1是特殊情况，无需切割，为0
### 代码
```java
class Solution {
  public int numberOfCuts(int n) {
    if (n == 1) {
      return 0;
    }

    return n % 2 == 0 ? n / 2 : n;
  }
}
```
# [LeetCode_1802_有界数组中指定下标处的最大值](https://leetcode.cn/problems/maximum-value-at-a-given-index-in-a-bounded-array/)
## 解法
### 思路
双指针
- 从index坐标开始，向左和右分别同时遍历，且依次在各个坐标对应的值上累加1
- 同时对累加的值进行存储，存储为sum，并将sum和maxSum进行比较，如果sum <= maxSum则继续循环
- 每循环一次，就对结果值ans加1
- 最后返回结果的时候，ans需要-1，因为最后一次是sum等于或者大于maxSum的时候累加的，所以需要减掉1
- 优化：当maxSum-sum的值大于n，且l和r2个指针已经移动到了0和n-1的位置，不能再移动了，那么剩下的动作其实就是每次都增加至多n个元素的同时，ans也加1，那么这个过程就可以通过(maxSum - sum) / n求出次数，从而直接得到ans要增加的个数，且此时不需要-1，那是因为此时增加的个数不像之前是超过的，这次就是正常的个数
### 代码
```java
class Solution {
    public int maxValue(int n, int index, int maxSum) {
        int ans = 1, sum = n, l = index, r = index;
        
        while (sum <= maxSum) {
            sum += r - l + 1;
            ans++;
            
            r = r == n - 1 ? n - 1 : r + 1;
            l = l == 0 ? 0 : l - 1;
            
            if (l == 0 && r == n - 1 && n < maxSum - sum) {
                return ans + (maxSum - sum) / n;
            }
        }
        
        return ans - 1;
    }
}
```