# [LeetCode_LCP40_心算挑战](https://leetcode.cn/problems/uOAnQW)
## 解法
### 思路
- 对数组`cards`排序
- 圈定`cnt`大个元素对应的子数组
- 如果子数组总和`sum`为偶数，返回结果
- 否则，找到子数组中最小的奇偶数，找出非子数组中的最大奇偶数
- 如果最大奇数和最小偶数都存在，那么计算`sum - minEven + maxOdd`，否则为0
- 如果最大偶数和最小奇数都存在，那么计算`sum - minOdd + maxEven`，否则为0
- 最后计算2种情况的最大值，作为结果返回
### 代码
```java
class Solution {
    public int maxmiumScore(int[] cards, int cnt) {
        Arrays.sort(cards);
        int len = cards.length, start = len - cnt,
            minEven = -1, minOdd = -1, sum = 0;

        for (int i = start; i < cards.length; i++) {
            int num = cards[i];
            sum += num;

            if (minOdd != -1 && minEven != -1) {
                continue;
            }
            
            if (minOdd == -1 && num % 2 == 1) {
                minOdd = num;
            }
            
            if (minEven == -1 && num % 2 == 0) {
                minEven = num;
            }
        }
        
        if (sum % 2 == 0) {
            return sum;
        }
        
        int maxEven = -1, maxOdd = -1;
        for (int i = start - 1; i >= 0; i--) {
            int num = cards[i];
            
            if (maxOdd != -1 && maxEven != -1) {
                continue;
            }

            if (maxOdd == -1 && num % 2 == 1) {
                maxOdd = num;
            }

            if (maxEven == -1 && num % 2 == 0) {
                maxEven = num;
            }
        }
        
        int sum1 = 0, sum2 = 0;
        if (minOdd != -1 && maxEven != -1) {
            sum1 = sum - minOdd + maxEven;
        }
        
        if (minEven != -1 && maxOdd != -1) {
            sum2 = sum - minEven + maxOdd;
        }
        
        return Math.max(sum1, sum2);
    }
}
```
# [LeetCode_3128_直角三角形](https://leetcode.cn/problems/right-triangles/)
## 解法
### 思路
- 初始化2个数组：
  - `rows`：记录纵坐标对应的每一行的1的个数
  - `cols`：记录横坐标对应的每一列的1的个数
- 遍历二维数组`grid`，将遍历到的1分别记录到`rows`和`cols`中
- 再次遍历二维数组`grid`，当遍历到值为1的坐标时，用横坐标和纵坐标分别到`cols`和`rows`中找到对应的个数`r`和`c`，然后分别减1（因为需要剔除自身）
- 然后计算`(r - 1) * (c - 1)`作为以当前坐标为直角点的三角形的个数，并累加到结果变量`cnt`中
- 遍历结束后，返回`cnt`即可
- 此处还有一个优化点，因为有点和没点对应的值是0和1，所以在2次遍历`grid`的过程中，不需要判断值是否为1：
  - 第一次的时候，直接累加`grid[i][j]`，因为有就是1，没有则加0也没有影响
  - 第二次的时候，直接在公式`(r - 1) * (c - 1)`后再乘以`grid[i][j]`，这样，如果当前坐标并不是1，则乘以0也就相当于不累加
- 通过如上的优化可以减少逻辑判断，从而使得循环计算加速
### 代码
```java
class Solution {
    public long numberOfRightTriangles(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        int[] rows = new int[col], cols = new int[row];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                rows[j] += grid[i][j];
                cols[i] += grid[i][j];
            }
        }

        long cnt = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                cnt += (long) (rows[j] - 1) * (cols[i] - 1) * grid[i][j];
            }
        }

        return cnt;
    }
}
```