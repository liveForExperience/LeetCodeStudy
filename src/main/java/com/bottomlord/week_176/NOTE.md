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
# [LeetCode_1769_移动所有球到每个盒子所需的最小操作数](https://leetcode.cn/problems/minimum-number-of-operations-to-move-all-balls-to-each-box/)
## 解法
### 思路
- 实际操作数就是每个有球的格子与当前i坐标格子之间距离之和
- 每个不同的坐标，他们的距离之和的差距可以用如下公式来推倒：
  - 如果左边坐标已经计算出来了操作数x
  - 当前的操作数y就是：y = x + lc - rc - (cs[i] - '0')
    - lc：y对应坐标左边出现过的有球的格子个数
    - rc：y对应坐标右边出现过的有球的格子个数
    - (cs[i] - '0')：当前坐标格子里如果有球，那么当前这个值要减去，因为在x中是计算了的
- 根据如上思路
  - 通过前缀和的思路，将lc和rc对应的前缀和数组算出来
  - 再讲ans[0]坐标的操作数求出来，这个值就是所有有球格子的坐标数之和，因为(i - 0 == i)
- 最后从1开始遍历ans数组，通过如上公式就可以得到结果数组
### 代码

```java
class Solution {
    public int[] minOperations(String boxes) {
        char[] cs = boxes.toCharArray();
        int n = cs.length;
        int[] leftSums = new int[n], rightSums = new int[n], ans = new int[n];
        int sum = 0, lc = 0, rc = 0;
        for (int i = 0; i < n; i++) {
            char c = cs[i];
            leftSums[i] = lc;

            if (c == '1') {
                sum += i;
                lc++;
            }
        }

        ans[0] = sum;

        for (int i = n - 1; i >= 0; i--) {
            rightSums[i] = rc;
            char c = cs[i];

            if (c == '1') {
                rc++;
            }
        }

        for (int i = 1; i < n; i++) {
            ans[i] = ans[i - 1] + leftSums[i] - rightSums[i] - (cs[i] - '0');
        }

        return ans;
    }
}
```
# [LeetCode_1774_最接近目标价格的甜点成本](https://leetcode.cn/problems/closest-dessert-cost/)
## 解法
### 思路
回溯+记忆化搜索
### 代码
```java
class Solution {
    private int target, min = Integer.MAX_VALUE, ans = 0;
    private int[] toppingCosts;

    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        this.target = target;
        this.toppingCosts = toppingCosts;

        for (int baseCost : baseCosts) {
            backTrack(baseCost, new int[toppingCosts.length], new HashSet<>());
        }

        return ans;
    }

    private void backTrack(int cost, int[] count, Set<Integer> memo) {
        if (!memo.add(cost)) {
            return;
        }
        
        int diff = Math.abs(cost - target);

        if (diff == min) {
            ans = Math.min(cost, ans);
        }

        if (diff < min) {
            min = diff;
            ans = cost;
        }

        for (int i = 0; i < toppingCosts.length; i++) {
            if (count[i] >= 2) {
                continue;
            }

            int toppingCost = toppingCosts[i];
            count[i]++;
            cost += toppingCost;

            backTrack(cost, count, memo);

            count[i]--;
            cost -= toppingCost;
        }
    }
}
```
## 解法二
### 思路

### 代码
```java

```