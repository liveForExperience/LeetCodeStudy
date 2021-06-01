# [LeetCode_342_4的幂](https://leetcode-cn.com/problems/power-of-four/)
## 解法
### 思路
- 非正整数，返回false
- 32位的整数，依次左移2位来判断是否与当前值相等，左移结束如果还是不相等就返回false，否则返回true
### 代码
```java
class Solution {
    public boolean isPowerOfFour(int n) {
        if (n <= 0) {
            return false;
        }
        
        int bit = 1, time = 0;
        while (time < 16) {
            if (bit == n) {
                return true;
            }
            
            if (bit > n) {
                return false;
            }

            bit <<= 2;
            time++;
        }

        return false;
    }
}
```
# [LeetCode_1134_阿姆斯特朗数](https://leetcode-cn.com/problems/armstrong-number/)
## 解法
### 思路
模拟计算
### 代码
```java
class Solution {
    public boolean isArmstrong(int n) {
        int bit = 0, num = n;
        List<Integer> list = new ArrayList<>();
        while (num != 0) {
            bit++;
            int a = num % 10;
            list.add(a);
            num /= 10;
        }
        
        int sum = 0;
        for (Integer c : list) {
            sum += (int) Math.pow(c, bit);
        }
        
        return sum == n;
    }
}
```
# [LeetCode_1150_检查一个数是否在数组中占绝大多数](https://leetcode-cn.com/problems/check-if-a-number-is-majority-element-in-a-sorted-array/)
## 解法
### 思路
- 遍历计算target数，且因为是递增的，可以通过`num > target`提前结束
- 最后返回count数是否大于数组长度一半
### 代码
```java
class Solution {
    public boolean isMajorityElement(int[] nums, int target) {
        int count = 0;
        for (int num : nums) {
            if (num == target) {
                count++;
            } else if (num > target) {
                break;
            }
        }

        return count > nums.length / 2;
    }
}
```
## 解法二
### 思路
双指针：
- 左右指针找到等于target的子数组区间的左右边界
- 通过左右指针计算区间的长度是否大于整体长度的一半
### 代码
```java
class Solution {
    public boolean isMajorityElement(int[] nums, int target) {
        int len = nums.length, head = 0, tail = len - 1;
        while (head <= tail) {
            if (nums[head] < target) {
                head++;
            } else if (nums[head] > target) {
                return false;
            }
            
            if (nums[tail] > target) {
                tail--;
            } else if (nums[tail] < target) {
                return false;
            }
            
            if (nums[head] == nums[tail] && nums[head] == target) {
                break;
            }
        }
        
        return tail - head + 1 > len / 2;
    }
}
```
## 解法三
### 思路
二分查找：
- 2次二分查找分别找到子数组的左右边界
- 根据左右边界的坐标差值确定是否符合题目的要求
- 解法是一种范式，需要记住
### 代码
```java
class Solution {
    public boolean isMajorityElement(int[] nums, int target) {
        int len = nums.length;
        int head = 0, tail = len - 1;
        while (head < tail) {
            int mid = head + (tail - head) / 2;
            if (nums[mid] >= target) {
                tail = mid;
            } else {
                head = mid + 1;
            }
        }
        if (nums[tail] != target) {
            return false;
        }

        int r = tail;
        head = 0;
        tail = len - 1;
        while (head < tail) {
            int mid = head + (tail - head) / 2 + 1;
            if (nums[mid] <= target) {
                head = mid;
            } else {
                tail = mid - 1;
            }
        }

        return tail - r + 1 > len / 2;
    }
}
```