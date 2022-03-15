# [LeetCode_599_两个列表的最小索引总和](https://leetcode-cn.com/problems/minimum-index-sum-of-two-lists/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        List<String> ans = new ArrayList<>();
        Map<String, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            map.computeIfAbsent(list1[i], x -> new ArrayList<>()).add(i);
        }

        for (int i = 0; i < list2.length; i++) {
            map.computeIfAbsent(list2[i], x -> new ArrayList<>()).add(i);
        }

        int min = Integer.MAX_VALUE;
        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            if (entry.getValue().size() != 2) {
                continue;
            }
            
            int count = entry.getValue().get(0) + entry.getValue().get(1);
            
            if (count < min) {
                ans.clear();
                ans.add(entry.getKey());
                min = count;
            } else if (count == min) {
                ans.add(entry.getKey());
            }
        }
        
        return ans.toArray(new String[0]);
    }
}
```
# [LeetCode_2044_1_统计按位或能得到最大值的子集数目](https://leetcode-cn.com/problems/count-number-of-maximum-bitwise-or-subsets/)
## 解法
### 思路
回溯+计数
### 代码
```java
class Solution {
    int max = Integer.MIN_VALUE, ans = 0;
    public int countMaxOrSubsets(int[] nums) {
        backTrack(nums, 0, 0);
        return ans;
    }
    
    private void backTrack(int[] nums, int start, int num) {
        if (start >= nums.length) {
            return;
        }
        
        for (int i = start; i < nums.length; i++) {
            int cur = num | nums[i];
            if (cur > max) {
                max = cur;
                ans = 1;
            } else if (cur == max) {
                ans++;
            }
            
            backTrack(nums, i + 1, cur);
        }
    }
}
```