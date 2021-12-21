# [LeetCode_475_供暖器](https://leetcode-cn.com/problems/heaters/)
## 解法
### 思路
- 先将house和heater数组进行排序
- 然后进行2层的for循环
  - 外层遍历house
  - 内层遍历heater，目的是找到第一个heater坐标比当前house坐标大的值
    - 如果没有找到，heater坐标没有移动，那说明第一个heater就比当前house更靠右，这时候，直接计算半径最大值
    - 如果没有找到，heater坐标已经越界，那说明当前房子比所有heater都靠右，那么也直接计算最大半径值
    - 如果找到了，那么就将当前找到的坐标与房子之间的半径，和前一个heater和当前房子的半径进行比较，取最小值，再和最大值作比较，更新半径值
- 遍历结束以后，返回最大值即可
### 代码
```java
class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int max = 0, index = 0;
        for (int house : houses) {
            while (index < heaters.length && heaters[index] < house) {
                index++;
            }
            
            if (index == 0) {
                max = Math.max(max, heaters[index] - house);
            } else if (index == heaters.length) {
                max = Math.max(max, house - heaters[index - 1]);
            } else {
                max = Math.max(max, Math.min(heaters[index] - house, house - heaters[index - 1]));
            }
        }
        
        return max;
    }
}
```
# [LeetCode_1933_判断字符串是否可分解为值均等的子串](https://leetcode-cn.com/problems/check-if-string-is-decomposable-into-value-equal-substrings/)
## 解法
### 思路
遍历数组+模拟
### 代码
```java
class Solution {
    public boolean isDecomposable(String s) {
        boolean find2 = false;
        for (int i = 0; i < s.length(); i++) {
            int count = 1;
            while (i < s.length() - 1 && s.charAt(i) == s.charAt(i + 1)) {
                count++;
                i++;
            }

            count %= 3;

            if (count == 1) {
                return false;
            }

            if (count == 2) {
                if (find2) {
                    return false;
                }

                find2 = true;
            }
        }

        return find2;
    }
}
```
# [LeetCode_1933_可以输入的最大单词数](https://leetcode-cn.com/problems/maximum-number-of-words-you-can-type/)
## 解法
### 思路
- 使用一维数组记录brokenLetters
- 遍历字符串，通过数组快速判断单词是否可以计数
- 如果可以计数就累加
- 遍历结束，返回计数值
### 代码
```java
class Solution {
  public int canBeTypedWords(String text, String brokenLetters) {
    boolean[] arr = new boolean[26];
    for (char c : brokenLetters.toCharArray()) {
      arr[c - 'a'] = true;
    }

    int count = 0;
    for (int i = 0; i < text.length(); i++) {
      boolean flag = false;
      while (i < text.length() && text.charAt(i) != ' ') {
        if (arr[text.charAt(i) - 'a']) {
          flag = true;
        }

        i++;
      }

      if (!flag) {
        count++;
      }
    }

    return count;
  }
}
```
## 解法二
### 思路
使用string的api切分字符串后，使用一维数组快速判断
### 代码
```java
class Solution {
public int canBeTypedWords(String text, String brokenLetters) {
        String[] strs = text.split(" ");
        boolean[] arr = new boolean[26];
        for (char c : brokenLetters.toCharArray()) {
            arr[c - 'a'] = true;
        }
        
        int count = 0;
        for (String str : strs) {
            if (canType(str, arr)) {
                count++;
            }
        }
        
        return count;
    }
    
    private boolean canType(String str, boolean[] arr) {
        for (char c : str.toCharArray()) {
            if (arr[c - 'a']) {
                return false;
            }
        }
        
        return true;
    }
}
```
# [LeetCode_1154_一年中的第几天](https://leetcode-cn.com/problems/day-of-the-year/)
## 解法
### 思路
- 初始化一个数组，存储年中天数的前缀和
- 根据字符串获取年月日的信息
- 根据月的信息判断是否要考虑闰月的情况
  - 小于等于2月
  - 大于2月
- 如果需要考虑，就根据年算出当年是否是闰年
- 最后根据每个月的天数，算出当前的天数并返回
### 代码
```java
class Solution {
    public int dayOfYear(String date) {
        int[] monthDays = new int[]{0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365};

        String[] factors = date.split("-");
        int year = Integer.parseInt(factors[0]),
            month = Integer.parseInt(factors[1]),
            day = Integer.parseInt(factors[2]);

        if (month <= 2) {
            return monthDays[month - 1] + day;
        }

        return monthDays[month - 1] + day + (isLeapYear(year) ? 1 : 0);
    }

    private boolean isLeapYear(int year) {
        if (year % 100 == 0) {
            return year % 400 == 0;
        }
        
        return year % 4 == 0;
    }
}
```
## 解法二
### 思路
使用字符计算替换String的切分api
### 代码
```java
class Solution {
    public int dayOfYear(String date) {
        int[] monthDays = new int[]{0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365};

        int year = 1000 * (date.charAt(0) - '0') + 100 * (date.charAt(1) - '0') + 10 * (date.charAt(2) - '0') + (date.charAt(3) - '0'),
            month = 10 * (date.charAt(5) - '0') + (date.charAt(6) - '0'),
            day = 10 * (date.charAt(8) - '0') + (date.charAt(9) - '0');

        if (month <= 2) {
            return monthDays[month - 1] + day;
        }

        return monthDays[month - 1] + day + (isLeapYear(year) ? 1 : 0);
    }

    private boolean isLeapYear(int year) {
        if (year % 100 == 0) {
            return year % 400 == 0;
        }

        return year % 4 == 0;
    }
}
```
# [LeetCode_1941_检查是否所有字符出现次数相同](https://leetcode-cn.com/problems/check-if-all-characters-have-equal-number-of-occurrences/)
## 解法
### 思路
- 桶计数
- 遍历桶判断
### 代码
```java
class Solution {
  public boolean areOccurrencesEqual(String s) {
    int[] bucket = new int[26];
    char[] cs = s.toCharArray();
    for (char c : cs) {
      bucket[c - 'a']++;
    }

    Integer target = null;
    for (int num : bucket) {
      if (num == 0) {
        continue;
      }

      if (target == null) {
        target = num;
        continue;
      }

      if (num != target) {
        return false;
      }
    }

    return true;
  }
}
```