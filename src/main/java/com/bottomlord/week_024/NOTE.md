# LeetCode_858_镜面反射
## 题目
有一个特殊的正方形房间，每面墙上都有一面镜子。除西南角以外，每个角落都放有一个接受器，编号为 0， 1，以及 2。

正方形房间的墙壁长度为 p，一束激光从西南角射出，首先会与东墙相遇，入射点到接收器 0 的距离为 q 。

返回光线最先遇到的接收器的编号（保证光线最终会遇到一个接收器）。

示例：
```
输入： p = 2, q = 1
输出： 2
解释： 这条光线在第一次被反射回左边的墙时就遇到了接收器 2 。
```
提示：
```
1 <= p <= 1000
0 <= q <= p
```
## 解法
### 思路
- 如果没有顶部的墙，则光每次反射在纵向上都会走长度为`q`的距离，而如果要达到接收器，那纵向的距离就是`p`的整数倍
- 所以结果就是求`p`和`q`的最小公倍数`k`
    - 求最小公倍数通过：
        - 求两数的最大公约数`gcd`
        - 最小公倍数：`p * q / gcd`
    - 通过`k / p`的值的奇偶性来判断是：
        - 偶数：南墙，因为南墙之后一个接收器，所以是东南的接收器`0`
        - 奇数：北墙的两个接收器`1`或`2`
    - 通过`k / q`的值的奇偶性来判断是：
        - 偶数：西北的接收器`2`
        - 奇数：东北的接收器`1`
### 代码
```java
class Solution {
    public int mirrorReflection(int p, int q) {
        int lcm = p * q / gcd(p, q), y = lcm / p, x = lcm / q;
        if ((y & 1) == 0) {
            return 0;
        }
        
        return (x & 1) == 1 ? 1 : 2;
    }

    private int gcd(int p, int q) {
        return q == 0 ? p : gcd(q, p % q);
    }
}
```
# LeetCode_228_1_汇总区间
## 题目
给定一个无重复元素的有序整数数组，返回数组区间范围的汇总。

示例 1:
```
输入: [0,1,2,4,5,7]
输出: ["0->2","4->5","7"]
解释: 0,1,2 可组成一个连续的区间; 4,5 可组成一个连续的区间。
```
示例 2:
```
输入: [0,2,3,4,6,8,9]
输出: ["0","2->4","6","8->9"]
解释: 2,3,4 可组成一个连续的区间; 8,9 可组成一个连续的区间。
```
## 解法
### 思路
- 定义变量：
    - `start`：起始数字，初始化为第1个元素
    - `pre`：上一个数字，初始化为第1个元素
    - `ans`：字符串list
- 遍历数组
    - 从第2个元素开始，判断是否比`pre`大1
        - 是：pre变为当前值，继续遍历
        - 否：生成字符串放入`ans`，`start`和`pre`变为当前值
- 遍历结束，将`start`和`pre`生成对应字符串放入`ans`
### 代码
```java
class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }

        int start = nums[0], pre = start;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - 1 != pre) {
                if (start == pre) {
                    ans.add("" + start);
                } else {
                    ans.add(start + "->" + pre);
                }

                start = nums[i];
            }
            pre = nums[i];
        }
        
        if (start == pre) {
            ans.add("" + start);
        } else {
            ans.add(start + "->" + pre);
        }
        
        return ans;
    }
}
```