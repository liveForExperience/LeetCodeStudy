# [LeetCode_497_非重叠矩形中的随机点](https://leetcode-cn.com/problems/random-point-in-non-overlapping-rectangles/)
## 解法
### 思路
- 获取所有再矩形内随机点个数的总和
- 在获取总和时，同时记录总和的与矩形关系之间形成的前缀和，也就是第n个矩形，与形成n个矩形的随机点个数的关系
- 通过随机函数获取总和中一个随机的值
- 然后通过二分法，找到随机的值在前缀和数组中对应的下标值
- 最后通过下标值找到矩形的左下角坐标，然后找到随机值对应的横坐标和纵坐标
### 代码
```java
class Solution {
    private Random random = new Random();
    private int totalNum, len;
    private int[] pointNumSums;
    private int[][] rects;

    public Solution(int[][] rects) {
        this.rects = rects;
        this.len = rects.length;
        this.pointNumSums = new int[len];
        int index = 0;
        for (int[] rect : rects) {
            int width = rect[2] - rect[0] + 1,
                height = rect[3] - rect[1] + 1;
            totalNum += width * height;
            pointNumSums[index++] = totalNum;
        }
    }

    public int[] pick() {
        int r = random.nextInt(totalNum);

        int head = 0, tail = len - 1;
        while (head < tail) {
            int mid = head + (tail - head) / 2;

            if (r >= pointNumSums[mid]) {
                head = mid + 1;
            } else {
                tail = mid;
            }
        }

        int[] rect = rects[head];
        int width = rect[2] - rect[0] + 1,
            height = rect[3] - rect[1] + 1,
            base = pointNumSums[head] - width * height;

        return new int[]{rect[0] + (r - base) % width, rect[1] + (r - base) / width};
    }
}
```
# [LeetCode_498_对角线遍历](https://leetcode-cn.com/problems/diagonal-traverse/)
## 解法
### 思路
模拟
- 通过规律发现，对角线的横坐标和纵坐标的和等于一个固定值，且这个固定值根据对角线的位置有规律的变化
### 代码
```java
class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        int row = matrix.length;
        if (row == 0) {
            return new int[0];
        }

        int col = matrix[0].length;
        if (col == 0) {
            return new int[0];
        }

        int[] ans = new int[row * col];
        recurse(matrix, ans, row, col, 0, 0, true, 0);
        return ans;
    }

    private void recurse(int[][] matrix, int[] ans, int row, int col, int r, int c, boolean flag, int index) {
        if (r == row || c == col) {
            return;
        }

        ans[index] = matrix[r][c];

        if (r == row - 1 && c == col - 1) {
            return;
        }

        if (flag) {
            if (r == 0) {
                if (c + 1 == col) {
                    recurse(matrix, ans, row, col, r + 1, c, false, index + 1);
                } else {
                    recurse(matrix, ans, row, col, r, c + 1, false, index + 1);
                }
            } else if (c == col - 1) {
                recurse(matrix, ans, row, col, r + 1, c, false, index + 1);
            } else {
                recurse(matrix, ans, row, col, r - 1, c + 1, true, index + 1);
            }
        } else {
            if (c == 0) {
                if (r + 1 == row) {
                    recurse(matrix, ans, row, col, r, c + 1, true, index + 1);
                } else {
                    recurse(matrix, ans, row, col, r + 1, c, true, index + 1);
                }
            } else if (r == row - 1) {
                recurse(matrix, ans, row, col, r, c + 1, true, index + 1);
            } else {
                recurse(matrix, ans, row, col, r + 1, c - 1, false, index + 1);
            }
        }
    }
}
```