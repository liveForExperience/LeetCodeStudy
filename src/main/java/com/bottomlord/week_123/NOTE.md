# [LeetCode_319_灯泡开关](https://leetcode-cn.com/problems/bulb-switcher/)
## 解法
### 思路
- 通过观察可知，对于某个开关，n次操作后，该开关的会被开关几次，基于该开关所在位置的对应数的约数有多少个，如果有奇数个就是开着的
- 一个数的约数，只有在该数是完全平方数的时候，才会是奇数个，所以最终就是求在n的范围内完全平方数的个数
- 求完全平方数个数的公式就是$\sqrt{n}$
- 为了防止防止double的精度丢失，在n上+0.5做一下预防
### 代码
```java
class Solution {
    public int bulbSwitch(int n) {
        return (int)Math.sqrt(n + 0.5);
    }
}
```
# [LeetCode_391_完美矩形](https://leetcode-cn.com/problems/perfect-rectangle/)
## 解法
### 思路
- 通过观察发现，如果是完美矩形，除了矩形的四个定点，所有其他子矩形的定点都会出现2次的倍数
- 所以通过遍历所有点的信息，更新所有点的信息，从而获取可能完美矩形的四个点信息，同时将当前矩形的面积求和，并记录下所有子矩形的定点，并放到set中观察是否已经出现，如果出现了就去掉
- 遍历结束后
  - 先计算面积总和是否和累加的总和相等，如果不等就直接返回false
  - 再计算memo中是否只有4个点，且每个点都和最后求得的4个点一致，如果都一致，就返回true，否则也是false
### 代码
```java
class Solution {
    public boolean isRectangleCover(int[][] rectangles) {
        int x1 = Integer.MAX_VALUE, y1 = Integer.MAX_VALUE,
            x2 = Integer.MIN_VALUE, y2 = Integer.MIN_VALUE,
            sum = 0;

        Set<String> memo = new HashSet<>();

        for (int[] rectangle : rectangles) {
            int cx1 = rectangle[0], cy1 = rectangle[1], cx2 = rectangle[2], cy2 = rectangle[3];

            x1 = Math.min(cx1, x1);
            y1 = Math.min(cy1, y1);
            x2 = Math.max(cx2, x2);
            y2 = Math.max(cy2, y2);

            sum += (cx2 - cx1) * (cy2 - cy1);

            String[] points = new String[]{cx1 + " " + cy1, cx1 + " " + cy2, cx2 + " " + cy1, cx2 + " " + cy2};
            for (String point : points) {
                if (!memo.add(point)) {
                    memo.remove(point);
                }
            }
        }

        if (sum != (x2 - x1) * (y2 - y1)) {
            return false;
        }

        return memo.size() == 4 && 
               memo.contains(x1 + " " + y1) &&
                memo.contains(x1 + " " + y2) &&
                memo.contains(x2 + " " + y1) &&
                memo.contains(x2 + " " + y2);
    }
}
```