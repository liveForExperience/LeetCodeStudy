# [LeetCode_458_可怜的小猪](https://leetcode-cn.com/problems/poor-pigs/)
## 解法
### 思路
- 按照简单推算：
    - 1只猪在能够品尝n次的情况下，可以推算`n + 1`瓶水
    - 2只猪能够推算`(n + 1) ^ 2`瓶水
    - m只猪能够推算`(n + 1) ^ m`瓶水
- 那么题目变成了求(n+1)的多少次幂是最小的大于水瓶数的值
- 这篇文章很好说明了如何去求m头猪喝水n次能够判断的水瓶个数：[链接](https://www.zhihu.com/question/60227816/answer/1274071217)
### 代码
```java
class Solution {
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int n = minutesToTest / minutesToDie + 1;
        return (int)Math.ceil(Math.log(buckets) / Math.log(n));
    }
}
```