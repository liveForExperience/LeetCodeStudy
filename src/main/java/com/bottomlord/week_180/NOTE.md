# [LeetCode_1754_构造字典序最大的合并字符串](https://leetcode.cn/problems/largest-merge-of-two-strings/)
## 解法
### 思路
双指针：
- 3种情况
    - word1的字符大于word2的首字符，选取word1的字符
    - word2的字符大于word1的首字符，选取word2的字符
    - 2个字符相等的时候，判断后缀字符串的字典序大小，谁大，就用谁的首字符
- 判断后缀字符串的字典序，就是循环遍历判断
### 代码
```java
class Solution {
    public String largestMerge(String word1, String word2) {
        int i = 0, j = 0, n1 = word1.length(), n2 = word2.length();
        StringBuilder sb = new StringBuilder();
        while (i < n1 || j < n2) {
            if (judge(word1, i, n1, word2, j, n2)) {
                sb.append(word1.charAt(i++));
            } else {
                sb.append(word2.charAt(j++));
            }
        }

        return sb.toString();
    }

    private boolean judge(String word1, int i, int n1, String word2, int j, int n2) {
        if (i >= n1) {
            return false;
        } else if (j >= n2) {
            return true;
        } else {
            return word1.substring(i).compareTo(word2.substring(j)) >= 0;
        }
    }
}
```
# [LeetCode_2423_删除字符使频率相同](https://leetcode.cn/problems/remove-letter-to-equalize-frequency/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public boolean equalFrequency(String word) {
        int[] bucket = new int[26];
        char[] cs = word.toCharArray();
        for (char c : cs) {
            bucket[c - 'a']++;
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        int one = 0;
        for (int num : bucket) {
            if (num == 0) {
                continue;
            }
            
            if (num == 1) {
                one++;
            }
            
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        if (map.size() > 2) {
            return false;
        }
        
        if (map.size() == 1) {
            if (one > 0) {
                return true;
            }

            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                return entry.getValue() == 1;
            }
        }
        
        one = 0;
        
        int x = 0, xn = 0, y = 0, yn = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getKey() == 1) {
                one = entry.getValue();
            }
            
            if (x == 0) {
                x = entry.getKey();
                xn = entry.getValue();
            } else {
                y = entry.getKey();
                yn = entry.getValue();
            }
        }
        
        if (one == 1) {
            return true;
        }
        
        if (Math.abs(x - y) != 1) {
            return false;
        }

        if (x > y) {
            return xn == 1;
        } else {
            return yn == 1;
        }
    }
}
```
# [LeetCode_2427_公因子的数目](https://leetcode.cn/problems/number-of-common-factors/)
## 解法
### 思路
- 计算出最大公约数
- 从做大公约数开始递减判断是否是公因数，直到1为止
### 代码
```java
class Solution {
    public int commonFactors(int a, int b) {
        int gcd = gcd(a, b), cnt = 0;
        for (int i = gcd; i >= 1; i--) {
            if (a % i == 0 && b % i == 0) {
                cnt++;
            }
        }

        return cnt;
    }

    private int gcd(int x, int y) {
        return x % y == 0 ? y : gcd(y, x % y);
    }
}
```
# [LeetCode_1739_放置盒子](https://leetcode.cn/problems/building-boxes/)
## 解法
### 思路
- 1层1个，2层1+2个，3层1+2+3个
- 依次类推就可以得到不比n大的最大层数
- 如果满层个数与n相等，就直接返回层数
- 否则就计算剩下方块，需要在当前的堆叠外层去套一层，套的时候就是1+2+3+...+i这样去套，可以参考[图解](https://leetcode.cn/problems/building-boxes/solution/mei-xiang-ming-bai-yi-ge-dong-hua-miao-d-8vbe/)
### 代码
```java
class Solution {
    public int minimumBoxes(int n) {
        int cur = 0, i = 0, j = 0;
        while (cur < n) {
            j++;
            i += j;
            cur += i;
        }
        
        if (cur == n) {
            return i;
        }
        
        cur -= i;
        i -= j;
        j = 0;
        
        while (cur < n) {
            j++;
            cur += j;
        }
        
        return i + j;
    }
}
```
# [LeetCode_2432_处理用时最长的那个任务的员工](https://leetcode.cn/problems/the-employee-that-worked-on-the-longest-task/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public int hardestWorker(int n, int[][] logs) {
        int ans = -1, max = Integer.MIN_VALUE, lastLeave = 0;
        for (int[] log : logs) {
            int id = log[0], leave = log[1], diff = leave - lastLeave;
            if (diff > max) {
                max = diff;
                ans = id;
            } else if (diff == max) {
                ans = ans == -1 ? id : Math.min(ans, id);
            }
            lastLeave = leave;
        }
        
        return ans;
    }
}
```
# [LeetCode_2437_有效时间的数目](https://leetcode.cn/problems/number-of-valid-clock-times/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public int countTime(String time) {
        String[] factors = time.split(":");
        String hour = factors[0], minute = factors[1];
        return generateHour(hour) * generateMinute(minute);
    }

    private int generateHour(String hour) {
        if (!hour.contains("?")) {
            return 1;
        }

        if (hour.charAt(0) == '?' && hour.charAt(1) == '?') {
            return 24;
        }

        if (hour.charAt(0) == '?') {
            return hour.charAt(1) >= '4' ? 2 : 3;
        }

        return hour.charAt(0) < '2' ? 10 : 4;
    }
    
    private int generateMinute(String minute) {
        if (!minute.contains("?")) {
            return 1;
        }
        
        if (minute.charAt(0) == '?' && minute.charAt(1) == '?') {
            return 60;
        }
        
        if (minute.charAt(0) == '?') {
            return 6;
        }
        
        return 10;
    }
}
```
# [LeetCode_2441_与对应负数同时存在的最大正整数](https://leetcode.cn/problems/largest-positive-integer-that-exists-with-its-negative/)
## 解法
### 思路
桶记录
### 代码
```java
class Solution {
    public int findMaxK(int[] nums) {
        boolean[] bucket = new boolean[2001];
        for (int num : nums) {
            bucket[num + 1000] = true;
        }

        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] && bucket[-(i - 1000) + 1000]) {
                return -(i - 1000);
            }
        }
        
        return -1;
    }
}
```
# [LeetCode_2446_判断两个事件是否存在冲突](https://leetcode.cn/problems/determine-if-two-events-have-conflict/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public boolean haveConflict(String[] event1, String[] event2) {
        int s1 = getTime(event1[0]), e1 = getTime(event1[1]),
            s2 = getTime(event2[0]), e2 = getTime(event2[1]);

        return (s2 >= s1 && s2 <= e1) || (e2 >= s1 && e2 <= e1) ||
               (s1 >= s2 && s1 <= e2) || (e1 >= s2 && e1 <= e2);
    }

    private int getTime(String time) {
        String[] factors = time.split(":");
        return Integer.parseInt(factors[0]) * 60 + Integer.parseInt(factors[1]);
    }
}
```