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