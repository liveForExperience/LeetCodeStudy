# [LeetCode_749_隔离病毒](https://leetcode.cn/problems/contain-virus/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_2357_使数组中所有元素都等于零](https://leetcode.cn/problems/make-array-zero-by-subtracting-equal-amounts/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public int minimumOperations(int[] nums) {
        int sum = 0, ans = 0;
        for (int num : nums) {
            sum += num;
        }

        while (sum > 0) {
            sum = handleAndGetSum(nums, getNotZeroMin(nums));
            ans++;
        }

        return ans;
    }

    private int getNotZeroMin(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num == 0) {
                continue;
            }

            min = Math.min(min, num);
        }
        return min;
    }

    private int handleAndGetSum(int[] nums, int min) {
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                continue;
            }

            nums[i] -= min;
            sum += nums[i];
        }

        return sum;
    }
}
```
# [LeetCode_2363_合并相似的物品](https://leetcode.cn/problems/merge-similar-items/)
## 解法
### 思路
- 排序
- 双指针遍历并处理
### 代码
```java
class Solution {
    public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
        Arrays.sort(items1, Comparator.comparingInt(x -> x[0]));
        Arrays.sort(items2, Comparator.comparingInt(x -> x[0]));

        int i1 = 0, i2 = 0, l1 = items1.length, l2 = items2.length;
        List<List<Integer>> ans = new ArrayList<>();
        while (i1 < l1 || i2 < l2) {
            if (i1 == l1) {
                ans.add(getList(items2[i2][0], items2[i2][1]));
                i2++;
                continue;
            }
            
            if (i2 == l2) {
                ans.add(getList(items1[i1][0], items1[i1][1]));
                i1++;
                continue;
            }
            
            int v1 = items1[i1][0], v2 = items2[i2][0];
            if (v1 == v2) {
                ans.add(getList(items1[i1][0], items1[i1][1] + items2[i2][1]));
                i1++;
                i2++;
            } else if (v1 < v2) {
                ans.add(getList(items1[i1][0], items1[i1][1]));
                i1++;
            } else {
                ans.add(getList(items2[i2][0], items2[i2][1]));
                i2++;
            }
        }
        
        return ans;
    }
    
    private List<Integer> getList(int val, int weight) {
        List<Integer> list = new ArrayList<>();
        list.add(val);
        list.add(weight);
        return list;
    }
}
```
# [LeetCode_2367_算术三元组的数目](https://leetcode.cn/problems/number-of-arithmetic-triplets/)
## 解法
### 思路
桶计数
### 代码
```java
class Solution {
    public int arithmeticTriplets(int[] nums, int diff) {
        int ans = 0;
        boolean[] bucket = new boolean[201];
        for (int num : nums) {
            bucket[num] = true;
        }

        for (int num : nums) {
            if (num + diff <= 200 &&
                num + 2 * diff <= 200 &&
                bucket[num + diff] &&
                bucket[num + 2 * diff]) {
                ans++;
            }
        }

        return ans;
    }
}
```
# [LeetCode_2373_矩阵中的局部最大值](https://leetcode.cn/problems/largest-local-values-in-a-matrix/)
## 解法
### 思路
- 初始化maxLocal
- 遍历maxLocal并根据grid计算出最大值，放置在maxLocal的对应位置
### 代码
```java
class Solution {
    public int[][] largestLocal(int[][] grid) {
        int r = grid.length, c = grid[0].length;
        int[][] maxLocal = new int[r - 2][c - 2];
        for (int i = 0; i < r - 2; i++) {
            for (int j = 0; j < c - 2; j++) {
                maxLocal[i][j] = findMax(grid, i + 1, j + 1);
            }
        }
        return maxLocal;
    }
    
    private int findMax(int[][] grid, int r, int c) {
        int max = Integer.MIN_VALUE;
        for (int i = r - 1; i <= r + 1; i++) {
            for (int j = c - 1; j <= c + 1; j++) {
                max = Math.max(max, grid[i][j]);
            }
        }
        
        return max;
    }
}
```
# [LeetCode_2379_得到K个黑块的最少涂色次数](https://leetcode.cn/problems/minimum-recolors-to-get-k-consecutive-black-blocks/)
## 解法
### 思路
前缀和
### 代码
```java
class Solution {
    public int minimumRecolors(String blocks, int k) {
        int len = blocks.length();
        int[] sums = new int[len + 1];
        char[] cs = blocks.toCharArray();
        for (int i = 0; i < len; i++) {
            sums[i + 1] += sums[i] + (cs[i] == 'B' ? 1 : 0);
        }

        int max = Integer.MIN_VALUE;
        for (int i = k; i <= len; i++) {
            max = Math.max(max, sums[i] - sums[i - k]);
        }

        return k - max;
    }
}
```