# [LeetCode_777_在LR字符串中交换相邻字符](https://leetcode.cn/problems/swap-adjacent-in-lr-string/)
## 解法
### 思路
双指针：
- 通过观察可知：
  - XL可以转化为LX，相当于L基于X在向左移动
  - RX可以转化为XR，相当于R基于X在向右移动
  - 如果将X全部去除，两个字符串应该是完全相同的，因为L无法穿越R，R也无法穿越L
  - 定义序号相同：就是相对顺序相同
  - 如果相同序号的字符不同，那就返回false
  - 如果序号相同的L，start的坐标小于end的，那么因为L只能向左，所以返回false
  - 如果需要相同的R，start的坐标大于end的，那么因为R只能向右，所以返回false
### 代码
```java
public class Solution {
  public boolean canTransform(String start, String end) {
    int x = 0, y = 0, n = start.length();
    while (x < n || y < n) {
      while (x < n && start.charAt(x) == 'X') {
        x++;
      }

      while (y < n && end.charAt(y) == 'X') {
        y++;
      }

      if (x == n || y == n) {
        return x == y;
      }

      if (start.charAt(x) != end.charAt(y)) {
        return false;
      }

      if (start.charAt(x) == 'L' && x < y) {
        return false;
      }

      if (start.charAt(x) == 'R' && x > y) {
        return false;
      }

      x++;
      y++;
    }

    return true;
  }
}
```