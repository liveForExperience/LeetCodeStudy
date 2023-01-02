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