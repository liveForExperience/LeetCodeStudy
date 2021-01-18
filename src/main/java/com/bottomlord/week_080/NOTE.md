# [LeetCode_425_单词方块](https://leetcode-cn.com/problems/word-squares/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_1232_缀点成线](https://leetcode-cn.com/problems/check-if-it-is-a-straight-line/)
## 解法
### 思路
- 将第一个点平移到原点位置，同时其他所有点对应平移
- 遍历数组，基于`ax + bx == 0`判断是否是同一直线的方式求结果
- 其中`a = coordinator[1][1]`，`b = coordinator[1][0]`
### 代码
```java
class Solution {
    public boolean checkStraightLine(int[][] coordinates) {
        int deltaA = coordinates[0][0], deltaB = coordinates[0][1], n = coordinates.length;
        for (int i = 0; i < n; i++) {
            coordinates[i][0] -= deltaA;
            coordinates[i][1] -= deltaB;
        }

        int a = coordinates[1][1], b = -coordinates[1][0];

        for (int i = 2; i < n; i++) {
            if (coordinates[i][0] * a + coordinates[i][1] * b != 0) {
                return false;
            }
        }
        
        return true;
    }
}
```