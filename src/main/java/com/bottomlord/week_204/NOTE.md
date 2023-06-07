# [LeetCode_2352_相等行列对](https://leetcode.cn/problems/equal-row-and-column-pairs/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public int equalPairs(int[][] grid) {
        int n = grid.length;
        Map<Integer, List<Integer>> colMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            colMap.computeIfAbsent(grid[0][i], x -> new ArrayList<>()).add(i);
        }

        int count = 0;
        for (int row = 0; row < n; row++) {
            int start = grid[row][0];
            List<Integer> cols = colMap.get(start);
            if (cols == null) {
                continue;
            }
            
            for (Integer col : cols) {
                boolean flag = true;
                for (int j = 0; j < n; j++) {
                    if (grid[j][col] != grid[row][j]) {
                        flag = false;
                        break;
                    }
                }
                
                if (flag) {
                    count++;
                }
            }
        }
        
        return count;
    }
}
```
# [LeetCode_2611_奶酪和老鼠](https://leetcode.cn/problems/mice-and-cheese/)
## 解法
### 思路
- 先算出第二只老鼠吃所有的奶酪的总得分
- 同时求出第一只老鼠吃当前奶酪与第二只老鼠吃的时候的得分差值
- 为了能得到最大值，其实就是求差值的最大值，差值越大，说明第一只老鼠吃的时候能得到更高的分数
- 然后对差值数组做降序排序
- 遍历排序后的差值数组，累加到之前的总和中作为结果
### 代码
```java
class Solution {
    public int miceAndCheese(int[] reward1, int[] reward2, int k) {
        int sum = 0, n = reward1.length;
        Integer[] diffs = new Integer[n];
        for (int i = 0; i < n; i++) {
            diffs[i] = reward1[i] - reward2[i];
            sum += reward2[i];
        }
        
        Arrays.sort(diffs, (x, y) -> y - x);

        for (int i = 0; i < k; i++) {
            sum += diffs[i];
        }
        
        return sum;
    }
}
```