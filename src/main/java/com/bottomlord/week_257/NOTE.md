# [LeetCode_2806_取整购买后的账户余额](https://leetcode.cn/problems/account-balance-after-rounded-purchase)
## 解法
### 思路
- 将`purchaseAmount`与10取模得到余数，这个余数`mod`的含义是，比`purchaseAmount`小的最大的10的倍数`num`，比`purchaseAmount`小的值。即：`mod = purchaseAmount - num`
- 判断`mod`是否小于5，如果是，那么他离`num`更近，否则说明它离更大的10的倍数更近，那么就让`purchaseAmount + (10 - mod)`得到结果即可
### 代码
```java
class Solution {
    public int accountBalanceAfterPurchase(int purchaseAmount) {
        int mod = purchaseAmount % 10;
        return mod < 5 ? 100 - (purchaseAmount - mod) : 100 - (purchaseAmount + 10 - mod);
    }
}
```