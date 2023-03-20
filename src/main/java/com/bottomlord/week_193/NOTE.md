# [LeetCode_1012_至少有1位重复的数字](https://leetcode.cn/problems/numbers-with-repeated-digits/)
## 解法
### 思路
- 题目要求得到至少有1位重复的数字，可以通过总数-没有重复的数字，得到这个结果
- 通过数位dp来记录当前数字中数位出现的状态
- 通过递归来搜索所有可能性，并通过dp记事本来提前减枝
- 递归的过程中要考虑2个问题
  - 当前生成的数是否大于n，这个可以通过一个标记isLimit来标识
    - 如果为true，说明当前的元素最大值只能取到n当前数位上的最大值
    - 如果为false，说明当前的元素最大可以取到9，没有限制
  - 当前数位之前是否有有数字
    - 如果为true，说明之前已经开始取数作为数位上的值了
    - 如果为false，说明之前的数位都是跳过的，那么当前的数位其实也可以跳过
### 代码
```java
class Solution {
  private String s;
  private int[][] memo;

  public int numDupDigitsAtMostN(int n) {
    this.s = Integer.toString(n);
    this.memo = new int[s.length()][1 << 10];
    for (int[] arr : memo) {
      Arrays.fill(arr, -1);
    }
    return n - count(0, 0, true, false);
  }

  private int count(int index, int mask, boolean isLimit, boolean isNum) {
    if (index == s.length()) {
      return isNum ? 1 : 0;
    }

    if (!isLimit && isNum && memo[index][mask] != -1) {
      return memo[index][mask];
    }

    int ans = 0;
    if (!isNum) {
      ans = count(index + 1, mask, false, false);
    }

    int limit = isLimit ? (s.charAt(index) - '0') : 9;
    for (int i = isNum ? 0 : 1; i <= limit; i++) {
      if ((mask >> i & 1) == 0) {
        ans += count(index + 1, mask | (1 << i), isLimit && i == limit, true);
      }
    }

    if (!isLimit && isNum) {
      memo[index][mask] = ans;
    }

    return ans;
  }
}
```
# [LeetCode_2591_将钱分给最多的儿童](https://leetcode.cn/problems/distribute-money-to-maximum-children/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public int distMoney(int money, int children) {
        if (money < children) {
            return -1;
        }

        money -= children;

        int c = money / 7, leftMoney = money % 7;

        if (c == children) {
            return leftMoney == 0 ? children : Math.max(0, children - 1);
        } else if (c > children) {
            return Math.max(0, children - 1);
        } else {
            int leftChildren = Math.max(0, children - c);
            if (leftChildren == 1 && leftMoney == 3) {
                return Math.max(0, c - 1);
            }

            return c;
        }
    }
}
```