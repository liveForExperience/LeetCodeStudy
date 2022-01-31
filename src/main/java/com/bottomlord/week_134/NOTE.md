# [LeetCode_2154_将找到的值乘以2](https://leetcode-cn.com/problems/keep-multiplying-found-values-by-two/)
## 解法
### 思路
- 初始化一个布尔变量
- 2层循环，外层依赖布尔变量做退出判断
- 外层开始时先使布尔值变为false
- 内层遍历数组找original，如果找到就将布尔值变成true，并且将original变成2倍
- 直到外层结束，返回original值
### 代码
```java
class Solution {
    public int findFinalValue(int[] nums, int original) {
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int num : nums) {
                if (num == original) {
                    original = original * 2;
                    flag = true;
                    break;
                }
            }
        }

        return original;
    }
}
```