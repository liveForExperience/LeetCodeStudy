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