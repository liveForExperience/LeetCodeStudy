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
# [LeetCode_1235_规划兼职工作](https://leetcode.cn/problems/maximum-profit-in-job-scheduling/)
## 解法
### 思路
- 将3组数组整合成一个二维数组`jobs`，其长度`n`是任意一个数组的长度，同时存储：
  - 0：`startTime`
  - 1：`endTime`
  - 2：`profit`
- 对二维数组进行排序，排序规则是`endTime`从小到大的排列
- 初始化一个`dp`数组，数组长度是`n + 1`
  - `dp[i]`含义：考虑第`i`个`jobs`元素时，得到的最大收益值
  - 其中`dp[0] = 0`，代表没有考虑任何元素时候，收益是0
  - 状态转移方程：`dp[i] = max(dp[i - 1], dp[k] + jobs[i][2])`
    - k：代表`endTime`小于`jobs[i][0]`的最大元素坐标
- 找到`k`可以通过二分查找的方式
- 最终返回`dp[n]`作为结果即可
### 代码
```java
class Solution {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] jobs = new int[n][3];

        for (int i = 0; i < n; i++) {
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }

        Arrays.sort(jobs, Comparator.comparingInt(x -> x[1]));

        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int k = binarySearch(jobs, i - 1);
            dp[i] = Math.max(dp[i - 1], dp[k + 1] + jobs[i - 1][2]);
        }
        
        return dp[n];
    }
    
    private int binarySearch(int[][] jobs, int i) {
        int left = -1, right = i, startTime = jobs[i][0];
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            
            if (jobs[mid][1] <= startTime) {
                left = mid;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
}
```