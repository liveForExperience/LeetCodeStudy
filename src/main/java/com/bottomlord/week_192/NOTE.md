# [LeetCode_2281_巫师的总力量和](https://leetcode.cn/problems/sum-of-total-strength-of-wizards/)
## 解法
### 思路
- 求出数组中两部分的内容
  - 子数组中的最小值：可以计算某个元素在多少子数组中是最小值，然后通过单调栈来求出子数组组合的左右边界
  - 所有子数组的总和的总和：
    - 通过求前缀和的前缀和来求
    - 通过举例可以方便理解：[题解](https://leetcode.cn/problems/sum-of-total-strength-of-wizards/solution/by-wen-rou-yi-dao-123-xy2d/)
- 使用单调栈求出每个坐标对应的左右边界
- 准备前缀和的前缀和数组，这个数组的长度需要是原数组长度n的基础上+2，原因是每次求前缀和都需要在前一个数组的基础上+1，两次前缀和就要+2
- 遍历数组，根据题解中的公式求出子数组总和的总和，再乘以当前元素值即可，需要考虑取模
### 代码
```java
class Solution {
    public int totalStrength(int[] strength) {
        int n = strength.length, mod = (int) 1e9 + 7;
        int[] lefts = new int[n], rights = new int[n];
        Arrays.fill(lefts, -1);
        Arrays.fill(rights, n);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && strength[stack.peek()] >= strength[i]) {
                rights[stack.pop()] = i;
            }

            if (!stack.isEmpty()) {
                lefts[i] = stack.peek();
            }

            stack.push(i);
        }

        long sum = 0L;
        int[] ss = new int[n + 2];
        for (int i = 1; i <= n; i++) {
            sum = sum + strength[i - 1];
            ss[i + 1] = (int) ((ss[i] + sum) % mod);
        }

        long ans = 0L;
        for (int i = 0; i < n; i++) {
            int l = lefts[i] + 1, r = rights[i] - 1;
            long tot = ((long) (i - l + 1) * (ss[r + 2] - ss[i + 1]) - (long) (r - i + 1) * (ss[i + 1] - ss[l])) % mod;
            ans = (ans + strength[i] * tot) % mod;

        }
        return (int) (ans + mod) % mod;
    }
}
```
# [LeetCode_1605_给定行和列的和求可行矩阵](https://leetcode.cn/problems/find-valid-matrix-given-row-and-column-sums/)
## 解法
### 思路
贪心
- 一层一层的处理矩阵
- 每一层的从左向右，取横竖总和的最小值作为当前单元格的值，同时更新横竖的总和数组
- 保证每一行都满足总和矩阵的要求
- 因为行的总和与竖的总和是相等的，所以处理每一行变成了处理一个个的子问题
### 代码
```java
class Solution {
    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int r = rowSum.length, c = colSum.length;
        int[][] matrix = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int diff = Math.min(rowSum[i], colSum[j]);
                matrix[i][j] = diff;
                rowSum[i] -= diff;
                colSum[j] -= diff;
            }
        }
        
        return matrix;
    }
}
```
# [LeetCode_2586_统计范围内的元音字符串数](https://leetcode.cn/problems/count-the-number-of-vowel-strings-in-range/)
## 解法
### 思路
哈希集合+模拟
### 代码
```java
class Solution {
    public int vowelStrings(String[] words, int left, int right) {
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        
        int count = 0;
        for (int i = left; i <= right; i++) {
            String word = words[i];
            char start = word.charAt(0), end = word.charAt(word.length() - 1);
            if (set.contains(start) && set.contains(end)) {
                count++;
            }
        }
        
        return count;
    }
}
```
# [LeetCode_910_最小差值II](https://leetcode.cn/problems/smallest-range-ii/)
## 解法
### 思路
- 数组排序
- 将数组以某个中心点拆分成2部分
  - 该中心点左侧所有元素加上k更趋近于中心值，那么这个子数组的最右侧，也即中心点元素就是可能的最大值，这个值与最大值-k比较获得处理后的最大值
  - 该中心点右侧的逻辑和如上类似
- 先求出最大和最小值处理k后的差值
- 然后遍历，假设每一个坐标都是可能的中心点，然后更新最大最小值，计算可能的最小分数
- 遍历结束后返回结果
### 代码
```java
class Solution {
    public int smallestRangeII(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = nums[n - 1] - nums[0];
        for (int i = 1; i < nums.length; i++) {
            int min = Math.min(nums[0] + k, nums[i] - k),
                max = Math.max(nums[n - 1] - k, nums[i - 1] + k);
            ans = Math.min(ans, max - min);
        }
        return ans;
    }
}
```
# [LeetCode_1615_最大网络秩](https://leetcode.cn/problems/maximal-network-rank/)
## 解法
### 思路
- 生成邻接表
- 生成入度表
- 根据邻接表去除重复计算次数
- 更新最大值
### 代码
```java
class Solution {
    public int maximalNetworkRank(int n, int[][] roads) {
        boolean[][] g = new boolean[n][n];
        int[] inDegrees = new int[n];
        for (int[] road : roads) {
            int a = road[0], b = road[1];
            g[a][b] = true;
            g[b][a] = true;
            inDegrees[a]++;
            inDegrees[b]++;
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                
                max = Math.max(max, inDegrees[i] + inDegrees[j] - (g[i][j] ? 1 : 0));
            }
        }
        return max;
    }
}
```
# [LeetCode_916_单词子集](https://leetcode.cn/problems/word-subsets/)
## 解法
### 思路
- 求出words2中所有单词各自的字母出现次数，然后求出每个字母出现的最大次数
- 遍历words1的所有单词，如果words2算出的最大次数任意大于words1的字母个数，就不作为结果，否则放入结果中
### 代码
```java
class Solution {
    public List<String> wordSubsets(String[] words1, String[] words2) {
        int[] cnt = new int[26];
        for (String word : words2) {
            int[] cur = new int[26];
            for (char c : word.toCharArray()) {
                cur[c - 'a']++;
            }

            for (int i = 0; i < cnt.length; i++) {
                cnt[i] = Math.max(cnt[i], cur[i]);
            }
        }

        List<String> ans = new ArrayList<>();
        for (String word : words1) {
            int[] cur = new int[26];
            for (char c : word.toCharArray()) {
                cur[c - 'a']++;
            }
            
            boolean flag = true;
            for (int i = 0; i < cnt.length; i++) {
                if (cur[i] < cnt[i]) {
                    flag = false;
                    break;
                }
            }
            
            if (flag) {
                ans.add(word);
            }
        }
        
        return ans; 
    }
}
```
# [LeetCode_918_环形子数组的最大和](https://leetcode.cn/problems/maximum-sum-circular-subarray/)
## 解法
### 思路
- 可以将问题分成2种情况
  - 不需要跨越边界，求子数组的最大和
  - 需要跨越边界，求子数组的最小和，通过sum - 最小和获得结果
- 求最大和最小和都可以通过动态规划来求
- 如果最大值小于0，说明所有元素都小于0，这个时候就不需要求跨越边界的情况，找到最大的那个元素就可以
### 代码
```java
class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int sum, max, min, curMax, curMin;
        sum = max = min = curMin = curMax = nums[0];
        

        for (int i = 1; i < nums.length; i++) {
            sum += nums[i];
            curMax = Math.max(curMax + nums[i], nums[i]);
            max = Math.max(max, curMax);
            
            curMin = Math.min(curMin + nums[i], nums[i]);
            min = Math.min(min, curMin);
        }
        
        return max < 0 ? max : Math.max(sum - min, max);
    }
}
```