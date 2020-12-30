# LeetCode_407_接雨水II
## 解法
### 思路
- 在三维中接雨水，需要考虑当前区块周围一圈的高度，只要一圈中有一个是低于当前区块高度的，就有可能导致无法接住水。
- 那么在考虑怎么求解的时候，就可以从当前矩阵的最外层开始往里收口，不断的从最外层高度最矮的那个区块去找其四周可以接水的位置，如果有，就将结果累加
- 为什么能累加的原因是，首先能够接水，说明接水区块比目前判断的所有区块中最矮的区块更矮，而正在考虑的区块至少是最外层的所有元素，而且是不断内收的，所以如果比这个最矮的区块还矮，那么一定能够接住水
- 接水的数量就是最矮的区块与当前可以接水区块高度的差
### 代码
```java
class Solution {
    public int trapRainWater(int[][] heightMap) {
        if (heightMap.length == 0 || heightMap[0].length == 0) {
            return 0;
        }

        int row = heightMap.length, col = heightMap[0].length;
        boolean[][] visited = new boolean[row][col];
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(x -> x[2]));

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 || i == row - 1 || j == 0 || j == col - 1) {
                    queue.offer(new int[]{i, j, heightMap[i][j]});
                    visited[i][j] = true;
                }
            }
        }

        int[] dirs = {1, 0, -1, 0, 1};
        int sum = 0;
        while (!queue.isEmpty()) {
            int[] shortArr = queue.poll();
            int shortR = shortArr[0], shortC = shortArr[1], shortHeight = shortArr[2];

            for (int i = 0; i < 4; i++) {
                int r = shortR + dirs[i], c = shortC + dirs[i + 1];

                if (r < 0 || r >= row || c < 0 || c >= col || visited[r][c]) {
                    continue;
                }

                int height = heightMap[r][c];
                if (height < shortHeight) {
                    sum += shortHeight - height;
                }

                queue.offer(new int[]{r, c, Math.max(height, shortHeight)});
                visited[r][c] = true;
             }
        }

        return sum;
    }
}
```