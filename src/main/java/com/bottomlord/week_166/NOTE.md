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