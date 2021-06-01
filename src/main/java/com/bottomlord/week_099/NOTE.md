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
# [LeetCode_1744_你能在你最喜欢的那天吃到你最喜欢的糖果吗](https://leetcode-cn.com/problems/can-you-eat-your-favorite-candy-on-your-favorite-day/)
## 解法
### 思路
- 求出能够吃到目标类型糖果最早和最晚的时间，然后判断目标时间是否落在这个区间
- 初始化前缀和数组sums用于快速判断
- queries数组中每个元素数组的三个子元素分别是
    - queries[i][0]：类型t
    - queries[i][1]：天数d，此处的d要+1，因为第0天也是可以吃的，而这里的值代表的第几天，所以如果要算天数，就要在这个值的基础上+1
    - queries[i][2]：吃的上限c
- 遍历queries数组，通过糖果类型t，确定吃完t类型糖果需要的最短和最长时间
    - 最慢：每天1颗糖吃完所有t种糖果
    - 最快：每天c颗糖吃完所有t-1种糖果，在这个天数上再+1，加1是因为：
        - 如果整除c，那么就是正好吃完t-1类糖果的天数，再加1就是吃t类的天数
        - 如果不能整除，因为时向下取整，那么也就要再加1天才能吃到t类糖果
### 代码
```java
class Solution {
    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        int len = queries.length, type = candiesCount.length;
        boolean[] ans = new boolean[len];
        long[] sums = new long[type + 1];
        for (int i = 1; i < sums.length; i++) {
            sums[i] = sums[i - 1] + candiesCount[i - 1];
        }

        for (int i = 0; i < queries.length; i++) {
            int t = queries[i][0], d = queries[i][1] + 1, c = queries[i][2];
            long l = sums[t] / c + 1, r = sums[t + 1];
            ans[i] = d >= l && d <= r;
        }

        return ans;
    }
}
```