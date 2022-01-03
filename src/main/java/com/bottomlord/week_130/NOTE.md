# [LeetCode_1185_一周中的第几天](https://leetcode-cn.com/problems/day-of-the-week/)
## 解法
### 思路
- 1970年最后一天是周4
- 计算从这天开始到目标年月日共几天
- 然后对7取余，获取每周的天数
### 代码
```java
class Solution {
    private final String[] weekDays = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    private final int[] monthDays = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public String dayOfTheWeek(int day, int month, int year) {
        int ans = 4;
        for (int i = 1971; i < year; i++) {
            boolean isLeap = isLeap(i);
            ans += isLeap ? 366 : 365;
        }

        for (int i = 1; i < month; i++) {
            ans += monthDays[i - 1];
            if (i == 2 && isLeap(year)) {
                ans++;
            }
        }

        ans += day;

        return weekDays[ans % 7];
    }

    private boolean isLeap(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }
}
```
# [LeetCode_1974_使用特殊打字机键入单词的最少时间](https://leetcode-cn.com/problems/minimum-time-to-type-word-using-special-typewriter/)
## 解法
### 思路
- 从a出发，判断正向或者逆向，那个方向的距离最近
- 每次都选最小的距离累加
- 遍历结束，返回结果
### 代码
```java
class Solution {
    public int minTimeToType(String word) {
        int start = 0, ans = 0;
        char[] cs = word.toCharArray();
        for (char c : cs) {
            ans++;
            int index = c - 'a';
            if (index > start) {
                ans += Math.min(index - start, start + 26 - index);
            } else if (index < start) {
                ans += Math.min(start - index, index + 26 - start);
            }
            start = c - 'a';
        }

        return ans;
    }
}
```
# [LeetCode_1979_找出数组的最大公约数](https://leetcode-cn.com/problems/find-greatest-common-divisor-of-array/)
## 解法
### 思路
- 遍历求最大值和最小值
- 对最大和最小值求最大公约数，公式`y == 0 ? x : gcd(y, x % y)`
### 代码
```java
class Solution {
    public int findGCD(int[] nums) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        return gcd(max, min);
    }

    private int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }
}
```
# [LeetCode_1984_学生分数的最小差值](https://leetcode-cn.com/problems/minimum-difference-between-highest-and-lowest-of-k-scores/)
## 解法
### 思路
- 排序
- 比较k长度的子数组中头尾元素的差，记录最小值
- 遍历结束，返回最小值
### 代码
```java
class Solution {
    public int minimumDifference(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = Integer.MAX_VALUE, n = nums.length;
        for (int i = 0; i < n - k + 1; i++) {
            ans = Math.min(ans, nums[i + k - 1] - nums[i]);
        }
        return ans;
    }
}
```