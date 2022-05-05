# [LeetCode_1823_找出游戏的获胜者](https://leetcode-cn.com/problems/find-the-winner-of-the-circular-game/)
## 解法
### 思路
约瑟夫环
- 思路就是倒推
### 代码
```java
class Solution {
    public int findTheWinner(int n, int k) {
        if(n <= 1) {
            return n;
        }

        int ans = (findTheWinner(n - 1, k) + k) % n;
        return ans == 0 ? n : ans;
    }
}
```
# [LeetCode_713_乘积小于K的子数组](https://leetcode.cn/problems/subarray-product-less-than-k/)
## 解法
### 思路
滑动窗口
- 初始化窗口的左边界坐标l和右边界坐标r
- 因为数组的元素都是正数，所以在窗口右边界不变的情况下，当找到左边界坐标l后，l与右边界r之间形成的窗口是小于k的最大范围，l如果左移，则一定是会大于k，同理，当r右移后，乘积也会大于k
- 此时如果要右移r，求更多窗口组合时，l就无需再从0开始确定了，因为l左移多少都一定是大于k的，此时就形成了滑动窗口的情况
### 代码
```java
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) {
            return 0;
        }

        int n = nums.length, l = 0, cur = 1, ans = 0;
        for (int r = 0; r < n; r++) {
            cur *= nums[r];
            while (cur >= k && l <= r) {
                cur /= nums[l++];
            }

            ans += r - l + 1;
        }

        return ans;
    }
}
```