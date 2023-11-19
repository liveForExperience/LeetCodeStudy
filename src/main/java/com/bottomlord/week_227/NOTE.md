# [LeetCode_2760_最长奇偶子数组](https://leetcode.cn/problems/longest-even-odd-subarray-with-threshold/description)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public int longestAlternatingSubarray(int[] nums, int threshold) {
        int max = 0;
        for (int i = 0; i < nums.length;) {
            if (nums[i] % 2 == 1 || nums[i] > threshold) {
                i++;
                continue;
            }

            i++;
            boolean flag = true;
            int cur = 1;
            while (i < nums.length && (nums[i] % 2 == 0) == !flag && nums[i] <= threshold) {
                cur++;
                flag = !flag;
                i++;
            }

            max = Math.max(cur, max);
        }

        return max;
    }
}
```
# [LeetCode_2342_数位和相等数对的最大和](https://leetcode.cn/problems/max-sum-of-a-pair-with-equal-sum-of-digits/)
## 解法
### 思路
- 思考过程：
  - 通过位运算得到每个数的数位和
  - 使用map来记录数位和对应的元素
  - 遍历所有可能的组合，找到最大值
- 算法过程：
  - 初始化一个map，key是数位和，value可以是一个长度为2的数组，分别存储最大的2个数字，初始化为-1
  - 初始化`ans`变量用于存储暂存的最大值，初始化为-1
  - 遍历`nums`数组，计算得到数位和之后，找到map中对应的value，看是否能放入数组中，并相应更新数组，同时更新`ans`
  - 遍历结束后，返回`ans`即可
### 代码
```java
class Solution {
    public int maximumSum(int[] nums) {
        Map<Integer, int[]> map = new HashMap<>();
        int ans = -1;
        for (int num : nums) {
            int cnt = cnt(num);
            int[] arr = map.getOrDefault(cnt, new int[]{-1, -1});
            if (num > arr[0]) {
                arr[1] = arr[0];
                arr[0] = num;
            } else if (num > arr[1]) {
                arr[1] = num;
            }

            map.put(cnt, arr);
            
            if (arr[0] == -1 || arr[1] == -1) {
                continue;
            }
            
            ans = Math.max(ans, arr[0] + arr[1]);
        }
        
        return ans;
    }

    private int cnt(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}
```