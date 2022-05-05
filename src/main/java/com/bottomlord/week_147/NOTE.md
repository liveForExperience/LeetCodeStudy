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