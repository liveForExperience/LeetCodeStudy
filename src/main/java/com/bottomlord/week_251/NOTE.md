# [LeetCode_1329_将矩阵按对角线排序](https://leetcode.cn/problems/sort-the-matrix-diagonally)
## 解法
### 思路
- 遍历mat数组，通过题目要求的方向，依次斜向遍历所有矩阵中的斜向数组
- 初始化一个用于桶排序的数组，将斜向遍历的元素存入桶中，通过桶下标完成排序，桶元素对应元素的出现个数
- 当桶填充完毕后，依次通过填充桶的顺序将桶中排序好的元素放入到`mat`数组中
### 代码
```java
class Solution {
    public int[][] diagonalSort(int[][] mat) {
        int row = mat.length, col = mat[0].length, index = 0;
        int[][] bucket = new int[row + col][101];
        
        for (int i = 0; i < row; i++) {
            int r = i, c = 0;
            for (; r < row && c < col; r++, c++) {
                bucket[index][mat[r][c]]++;
            }
            
            r = i; c = 0;
            for (int j = 0; j < bucket[index].length;) {
                if (bucket[index][j] == 0) {
                    j++;
                    continue;
                }
                
                bucket[index][j]--;
                mat[r++][c++] = j;
                
                if (bucket[index][j] == 0) {
                    j++;
                }
            }
            
            index++;
        }
        
        for (int i = 0; i < col; i++) {
            int r = 0, c = i;
            for (; r < row && c < col; r++, c++) {
                bucket[index][mat[r][c]]++;
            }
            
            r = 0; c = i;
            for (int j = 0; j < bucket[index].length;) {
                if (bucket[index][j] == 0) {
                    j++;
                    continue;
                }
                
                bucket[index][j]--;
                mat[r++][c++] = j;
                
                if (bucket[index][j] == 0) {
                    j++;
                }
            }
            
            index++;
        }
        
        return mat;
    }
}
```
# [LeetCode_2798_满足目标工作时长的员工数目](https://leetcode.cn/problems/number-of-employees-who-met-the-target/)
## 解法
### 思路
遍历`hours`，然后一一比对数组元素`hour`与`target`之间的关系，如果`hour >= target`，那么就累加计数
### 代码
```java
class Solution {
    public int numberOfEmployeesWhoMetTarget(int[] hours, int target) {
        int cnt = 0;
        for (int hour : hours) {
            cnt += hour >= target ? 1 : 0;
        }
        return cnt;
    }
}
```