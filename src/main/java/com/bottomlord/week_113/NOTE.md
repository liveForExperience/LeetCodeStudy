# [LeetCode_704_二分查找](https://leetcode-cn.com/problems/binary-search/submissions/)
## 解法
### 思路
二分查找
### 代码
```java
class Solution {
    public int search(int[] nums, int target) {
        int head = 0, tail = nums.length - 1;
        while (head <= tail) {
            int mid = head + (tail - head) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                head = mid + 1;
            } else {
                tail = mid - 1;
            }
        }
        
        return -1;
    }
}
```
# [LeetCode_1598_文件夹操作日志搜集器](https://leetcode-cn.com/problems/crawler-log-folder/)
## 解法
### 思路
- 根据不同操作更新步数：
  - 如果向上步数就-1
  - 如果原地就+0
  - 如果到子文件夹就+1
- 但需要注意的是，如果回退的时候已经在主目录，则不能回退，所以返回的最小值只能是0
### 代码
```java
class Solution {
    public int minOperations(String[] logs) {
        int path = 0;
        for (String log : logs) {
            if (Objects.equals(log, "../")) {
                if (path != 0) {
                    path--;
                }
            } else if (Objects.equals(log, "./")) {

            } else {
                path++;
            }
        }

        return path;
    }
}
```