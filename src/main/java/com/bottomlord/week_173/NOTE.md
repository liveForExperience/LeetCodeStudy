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
# [LeetCode_1620_网络信号最好的坐标](https://leetcode.cn/problems/coordinate-with-maximum-network-quality/)
## 解法
### 思路
模拟：
- 遍历towers，结合radius来确定坐标的最大范围
- 遍历所有坐标可能，然后遍历towers，通过计算欧几里得距离，来确定是否在当前tower的范围内，如果是，就累加信号值
- 判断信号值是否大于最大值，如果是就更新坐标和最大值
- 坐标遍历结束，返回结果
### 代码
```java
class Solution {
    public int[] bestCoordinate(int[][] towers, int radius) {
        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE, max = 0, ax = 0, ay = 0;
        for (int[] tower : towers) {
            int x = tower[0], y = tower[1];
            maxX = Math.max(maxX, x + radius);
            maxY = Math.max(maxY, y + radius);
        }

        for (int x = 0; x <= maxX; x++) {
            for (int y = 0; y <= maxY; y++) {
                int[] coordinate = {x, y};
                int cur = 0;
                for (int[] tower : towers) {
                    int squareDistance = getSquareDistance(tower, coordinate);

                    if (squareDistance <= radius * radius) {
                        double distance = Math.sqrt(squareDistance);
                        cur += tower[2] / (1 + distance);
                    }
                }
                
                if (cur > max) {
                    ax = x;
                    ay = y;
                    max = cur;
                }
            }
        }
        
        return new int[]{ax, ay};
    }

    private int getSquareDistance(int[] towers, int[] coordinate) {
        return (towers[0] - coordinate[0]) * (towers[0] - coordinate[0]) + (towers[1] - coordinate[1]) * (towers[1] - coordinate[1]);
    }
}
```