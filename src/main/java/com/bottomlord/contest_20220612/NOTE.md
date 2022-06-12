# [Contest_1_计算应缴税款总额](https://leetcode.cn/problems/calculate-amount-paid-in-taxes/)
## 解法
### 思路
- 遍历税额数组，直到应缴税额额度第一次大于等于income的时候为止
- 累加当前税额和之前税额差值与税率的乘积
- 需要注意最后一次需要通过income和税额额度的最小值来确定额度值
- 遍历结束返回累加的结果
### 代码
```java
class Solution {
    public double calculateTax(int[][] brackets, int income) {
        double sum = 0D;
        boolean flag = false;
        for (int i = 0; i < brackets.length; i++) {
            int[] cur = brackets[i];
            int x1 = cur[0], y1 = cur[1];
            
            if (income <= x1) {
                flag = true;
            }
            
            if (i == 0) {
                sum += 1D * Math.min(x1, income) * y1 / 100;
            } else {
                int[] pre = brackets[i - 1];
                sum += 1D * (Math.min(x1, income) - pre[0]) * y1 / 100;
            }
            
            if (flag) {
                break;
            }
        }

        return sum;
    }
}
```
# [Contest_2_网格中的最小路径代价](https://leetcode.cn/problems/minimum-path-cost-in-a-grid/)
## 解法
### 思路
线性dp
### 代码

```java
class Solution {
    public int minPathCost(int[][] grid, int[][] moveCost) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            System.arraycopy(grid[i], 0, dp[i], 0, n);
        }

        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int min = Integer.MAX_VALUE;
                int[] row = grid[i - 1];
                for (int k = 0; k < row.length; k++) {
                    int num = row[k];
                    int cost = moveCost[num][j];
                    min = Math.min(min, dp[i - 1][k] + cost);
                }

                dp[i][j] += min;
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, dp[m - 1][i]);
        }

        return ans;
    }
}
```
# [Contest_3_公平分发饼干](https://leetcode.cn/problems/fair-distribution-of-cookies/)
## 解法
### 思路
二分查找+回溯
- 将数组倒序排列
- 通过二分法找到目标值，也就是所有人能分到的最为平均的值
- 先通过二分假设一个目标值
- 然后通过回溯，判断这个目标值是否可行
### 代码
```java
class Solution {
    public int distributeCookies(int[] jobs, int k) {
        Arrays.sort(jobs);
        int sum = 0;
        for (int i = 0; i < jobs.length / 2; i++) {
            int tmp = jobs[i];
            jobs[i] = jobs[jobs.length - 1 - i];
            jobs[jobs.length - 1 - i] = tmp;

            sum += jobs[i] + jobs[jobs.length - 1 - i];
        }
        
        if (jobs.length % 2 == 1) {
            sum += jobs[jobs.length / 2];
        }
        
        int l = jobs[jobs.length - 1], r = sum;
        while (l < r) {
            int mid = l + (r - l) / 2;
            
            if (backTrack(jobs, new int[k], 0, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        
        return r;
    }
    
    private boolean backTrack(int[] jobs, int[] workloads, int index, int limit) {
        if (index >= jobs.length) {
            return true;
        }
        
        int workload = jobs[index];
        
        for (int i = 0; i < workloads.length; i++) {
            if (workloads[i] + jobs[index] <= limit) {
                workloads[i] += workload;
                if (backTrack(jobs, workloads, index + 1, limit)) {
                    return true;
                }
                workloads[i] -= workload;
            }
        }
        
        return false;
    }
}
```
# [Contest_4_公司命名](https://leetcode.cn/problems/naming-a-company/)
## 失败解法
### 失败原因
超时
### 思路
基于字符串后缀分组
- 两两比较两个后缀字符串，将两组的首字母进行比较，各自去除同时拥有的字符个数后，相乘得到当前两组能组成的所有结果
- 遍历结束后，返回所有两两组合的总和
### 代码
```java
class Solution {
    public long distinctNames(String[] ideas) {
        Map<String, Integer> map = new HashMap<>();
        for (String idea : ideas) {
            String prefix = idea.substring(1);
            map.put(prefix, map.getOrDefault(prefix, 0) | (1 << idea.charAt(0) - 'a'));
        }

        long count = 0;
        List<String> list = new ArrayList<>(map.keySet());
        for (int i = 0; i < list.size(); i++) {
            int a = map.get(list.get(i));
            for (int j = 0; j < list.size(); j++) {
                int b = map.get(list.get(j));

                int same = Long.bitCount(a & b);
                count += (long) (Integer.bitCount(a) - same) * (Integer.bitCount(b) - same);
            }
        }

        return count;
    }
}
```
## 解法
### 思路
在失败解法的基础上，优化两两比对的那个部分
- 参考：https://leetcode.cn/problems/naming-a-company/solution/by-endlesscheng-ruz8/
### 代码
```java
class Solution {
    public long distinctNames(String[] ideas) {
        Map<String, Integer> map = new HashMap<>();
        for (String idea : ideas) {
            String prefix = idea.substring(1);
            map.put(prefix, map.getOrDefault(prefix, 0) | (1 << idea.charAt(0) - 'a'));
        }

        int[][] count = new int[26][26];
        long ans = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            int mask = entry.getValue();
            
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    if (((mask >> i) & 1) == 0) {
                        if (((mask >> j) & 1) > 0) {
                            count[i][j]++;
                        }
                    } else {
                        if (((mask >> j) & 1) == 0) {
                            ans += count[i][j];
                        }
                    }
                }
            }
        }

        return ans * 2;
    }
}
```