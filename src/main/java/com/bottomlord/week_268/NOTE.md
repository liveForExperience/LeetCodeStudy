# [LeetCode_698_划分为k个相等的子集](https://leetcode.cn/problems/partition-to-k-equal-sum-subsets)
## 解法
### 思路
- 将`nums`降序排序
- 初始化一个数组`bucket`用于记录搜索`nums`过程中元素累加的变化情况
- 通过回溯的方式将`nums`中的元素从大到小依次尝试放入到`bucket`中，尝试找到有效的组合
### 代码
```java
class Solution {
private int[] bucket, nums;
    public boolean canPartitionKSubsets(int[] nums, int k) {
        this.nums = nums;
        int sum = 0;
        for (int num : this.nums) {
            sum += num;
        }

        if (sum % k != 0) {
            return false;
        }

        int target = sum / k;
        for (int num : this.nums) {
            if (num > target) {
                return false;
            }
        }

        this.bucket = new int[k];
        Arrays.sort(this.nums);
        int n = this.nums.length;
        for (int i = 0; i < n / 2; i++) {
            int tmp = this.nums[i];
            this.nums[i] = this.nums[this.nums.length - 1 - i];
            this.nums[this.nums.length - 1 - i] = tmp;
        }
        
        return backTrack(0, target);
    }

    private boolean backTrack(int index, int target) {
        if (index >= this.nums.length) {
            return true;
        }
        
        int num = this.nums[index];
        for (int i = 0; i < this.bucket.length; i++) {
            if (i < this.bucket.length - 1 && bucket[i] == bucket[i + 1]) {
                continue;
            }
            
            if (bucket[i] + num > target) {
                continue;
            }
            
            bucket[i] += num;
            boolean result = backTrack(index + 1, target);
            if (result) {
                return true;
            }
            bucket[i] -= num;
        }
        
        return false;
    }
}
```
# [LeetCode_3146_两个字符串的排列差](https://leetcode.cn/problems/permutation-difference-between-two-strings)
## 解法
### 思路
- 使用一个26个槽的数组`bucket`来记录2个字符串的字符出现的坐标值的绝对值差
  - 数组每个槽初始化为0
  - 每次出现一个字母，就将其在字符串所在的坐标记录在其字母值在`bucket`中所对应的槽位上，记录的逻辑就是用值减去槽位上的值，然后求绝对值
- 再次遍历`bucket`，将绝对值累加作为答案返回即可
### 代码
```java
class Solution {
    public int findPermutationDifference(String s, String t) {
        int[] bucket = new int[26];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int is = s.charAt(i) - 'a', it = t.charAt(i) - 'a';
            bucket[is] = Math.abs(i - bucket[is]);
            bucket[it] = Math.abs(i - bucket[it]);
        }

        int sum = 0;
        for (int num : bucket) {
            sum += num;
        }
        return sum;
    }
}
```
# [LeetCode_3144_分割字符频率相等的最少子字符串](https://leetcode.cn/problems/minimum-substring-partition-of-equal-character-frequency)
## 解法
### 思路
- dfs遍历所有可能性，并使用记事本来记录已经遍历过的子字符串能够分割的平衡子字符串个数
- 因为一定是小写字母，所以可以使用一个长度为26的int数组来作为记事本，坐标对应子字符串的起始坐标
- dfs的方法参数列表：
  - `index`：代表已经遍历到的，需要计算可分割最小个数的子字符串起始坐标
- dfs的退出条件就是`index >= s.length()`，表示`s`已经被遍历结束，此时返回0
- dfs过程中
  - 通过记事本剪枝，如果无法剪枝，就通过从`index`开始遍历所有可能的子字符串可能，在判定为平衡字符串后，从遍历到的坐标i再+1的位置开始继续递归，并在得到返回的个数后，加上当前这个平衡字符串的个数1，作为这个可能所对应的最小个数
  - 然后完成所有可能子字符串的遍历后，将最小个数记录在记事本中，并将个数作为当前递归的结果返回
  - 个数可以初始化为当前剩余字符串长度
- 将dfs返回的结果作为答案返回即可
### 代码
```java
class Solution {
    private String s;
    private int[] memo;

    public int minimumSubstringsInPartition(String s) {
        this.s = s;
        this.memo = new int[s.length() + 1];
        return dfs(0);
    }

    private int dfs(int index) {
        if (index == s.length()) {
            return 0;
        }

        if (memo[index] != 0) {
            return memo[index];
        }

        int min = s.length() - index;
        int[] bucket = new int[26];
        int max = 0, cnt = 0;
        for (int i = index; i < s.length(); i++) {
            if (bucket[s.charAt(i) - 'a']++ == 0) {
                cnt++;
            }

            max = Math.max(max, bucket[s.charAt(i) - 'a']);

            if (cnt * max != i - index + 1) {
                continue;
            }

            if (memo[i + 1] != 0) {
                min = Math.min(min, memo[i + 1] + 1);
                continue;
            }

            memo[i + 1] = dfs(i + 1);
            min = Math.min(min, memo[i + 1] + 1);
        }

        memo[index] = min;
        return min;
    }
}
```