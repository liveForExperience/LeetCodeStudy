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
# [LeetCode_1945_字符串转化后的各位数字之和](https://leetcode-cn.com/problems/sum-of-digits-of-string-after-convert/)
## 解法
### 思路
- 遍历字符串生成数字字符串
- 循环k次生成对应的总和
### 代码
```java
class Solution {
    public int getLucky(String s, int k) {
        String numStr = str2NumStr(s);
        int num = 0;
        for (int i = 0; i < k; i++) {
            num = str2Num(numStr);
            numStr = Integer.toString(num);
        }
        return num;
    }
    
    private String str2NumStr(String str) {
        char[] cs = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : cs) {
            sb.append(c - 'a' + 1);
        }
        
        return sb.toString();
    }
    
    private int str2Num(String str) {
        char[] cs = str.toCharArray();
        int sum = 0;
        for (char c : cs) {
            sum += c - '0';
        }
        return sum;
    }
}
```
# [LeetCode_686_重复叠加字符串匹配](https://leetcode-cn.com/problems/repeated-string-match/)
## 解法
### 思路
- 特殊情况先考虑
  - 如果a已经包含b，那么直接返回1
- 按照题目意思，如果a重复n次就可以包含b，那说明
  - 一定可以从a的某一个字符开始，不断头尾相连的循环a，可以一一和b的字符对应
  - 如果不可以，则说明不包含，直接返回-1
- 如果可以，就不断追加a这个字符串即可
### 代码
```java
class Solution {
    public int repeatedStringMatch(String a, String b) {
                if (a.contains(b)) {
            return 1;
        }
        
        boolean is = false;
        for (int i = 0; i < a.length(); i++) {
            boolean match = true;
            int ia = i;
            for (int ib = 0; ib < b.length(); ib++) {
                if (a.charAt(ia) != b.charAt(ib)) {
                    match = false;
                    break;
                }
                
                if (++ia == a.length()) {
                    ia = 0;
                }
            }
            
            if (match) {
                is = true;
                break;
            }
        }
        
        if (!is) {
            return -1;
        }
        
        StringBuilder sb = new StringBuilder();
        int count = 0;
        while (true) {
            if (sb.toString().contains(b)) {
                return count; 
            }
            
            sb.append(a);
            count++;
        }
    }
}
```
## 解法二
### 思路
- 如果b是a的x倍，那么a乘以x倍之后，会出现3种情况
  - 正好完全一致，例如`a = ab`和`b = abab`
  - 不能包含，于是再累加一次才能包含，例如`a = ab`和`b = ababa`
  - 不能包含，累加一次也不能包含，再累加一次可以，例如`a = abc`和`b = bcabcabca`
- 之所以如上3种情况可以覆盖所有可能是因为，如果a乘以n倍后一定有b这个子串，那么当a乘以x，导致和b一样长度或者少小于a的长度后，再在头尾在加上2个a，一定会包含，如果这都不能包含，那再累加也不可能
- 然后再把b中有a没有字符的情况提前过滤一下，即可
- 在这个算法中，string的indexOf使用的应该是类似KMP的算法，从而获取到匹配的第一个坐标值。
### 代码
```java
class Solution {
    public int repeatedStringMatch(String a, String b) {
        boolean[] bucket = new boolean[26];
        char[] as = a.toCharArray(), bs = b.toCharArray();
        for (char c : as) {
            bucket[c - 'a'] = true;
        }

        for (char c : bs) {
            if (!bucket[c - 'a']) {
                return -1;
            }
        }

        int x = b.length() / a.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < x; i++) {
            sb.append(a);
        }

        for (int i = 0; i <= 2; i++) {
            if (sb.indexOf(b) >= 0) {
                return x + i;
            }
            
            sb.append(a);
        }
        
        return -1;
    }
}
```
# [LeetCode_1044_最长重复子串](https://leetcode-cn.com/problems/longest-duplicate-substring/)
## 解法
### 思路
字符串哈希+前缀和+二分查找
- 最长字符串，语义含有二段性
  - 小于等于最长字符串的都存在
  - 比最长字符串大的都不存在
- 使用二分法来找到这个二段性的分界点
- 使用字符串哈希来记录字符串每个字符的哈希值
- 使用二分查找找到最大长度，期间通过字符串哈希+前缀和来判断是否存在重复字符串
- 二分查找结束后，返回找到的最长字符串
- [参考](https://leetcode-cn.com/problems/longest-duplicate-substring/solution/tong-ge-lai-shua-ti-la-er-fen-cha-zhao-z-gc3d/)
### 代码
```java
class Solution {
    private int prime = 31;

    public String longestDupSubstring(String s) {
        int head = 0, tail = s.length();
        String ans = "";
        while (head <= tail) {
            int mid = (head + tail + 1) / 2;
            String str = find(s, mid);
            if (!Objects.equals(str, "")) {
                head = mid + 1;
                ans = str;
            } else {
                tail = mid - 1;
            }
        }

        return ans;
    }

    private String find(String s, int len) {
        Set<Long> set = new HashSet<>();
        long hash = 0, power = 1;
        for (int i = 0; i < len; i++) {
            hash = hash * prime + s.charAt(i);
            power *= prime;
        }
        set.add(hash);

        String ans = "";
        for (int i = len; i < s.length(); i++) {
            hash = hash * prime + s.charAt(i) - power * s.charAt(i - len);

            if (set.contains(hash) &&  s.indexOf(ans = s.substring(i - len + 1, i + 1)) != i) {
                return ans;
            }

            set.add(hash);
        }

        return ans;
    }
}
```
# [LeetCode_28_实现strStr](https://leetcode-cn.com/problems/implement-strstr/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_1705_吃苹果的最大数目](https://leetcode-cn.com/problems/maximum-number-of-eaten-apples/)
## 解法
### 思路
- 使用优先级队列来存储数组元素
  - 数组的第一个元素存储当前苹果的过期时间
  - 数组的第二个元素存储当前批次苹果的个数
- 优先级队列用苹果的过期时间升序来存储
- 将生成苹果和过期和吃苹果的过程都放在一个循环中处理
- 循环的继续的条件：
  - 苹果生成数组还没有过滤完
  - 队列里还有元素
- 循环里
  - 如果苹果还没有生成完成，就把苹果批次数组放入队列中
  - 然后判断当前时间和队列顶部元素，是否队列顶部元素相对当前时间已经过期，如果是的话就循环将这些批次的苹果从队列中去除
  - 然后判断队列中是否还有元素，有的话说明可以吃，就进行吃苹果的逻辑，并对吃苹果的次数进行记录
- 循环结束返回记录吃苹果的次数
### 代码
```java
class Solution {
    public int eatenApples(int[] apples, int[] days) {
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(x -> x[0]));
        int n = apples.length, index = 0, count = 0;
        while (index < n || !queue.isEmpty()) {
            if (index < n && apples[index] > 0) {
                queue.offer(new int[]{index + days[index], apples[index]});
            }

            while (!queue.isEmpty() && queue.peek()[0] <= index) {
                queue.poll();
            }

            if (!queue.isEmpty()) {
                int[] arr = queue.poll();
                arr[1]--;
                count++;
                if (arr[1] > 0 && index < arr[0]) {
                    queue.offer(arr);
                }
            }

            index++;
        }

        return count;
    }
}
```
# [LeetCode_1609_奇偶树](https://leetcode-cn.com/problems/even-odd-tree/)
## 解法
### 思路
bfs
### 代码
```java
class Solution {
  public boolean isEvenOddTree(TreeNode root) {
    Queue<TreeNode> queue = new ArrayDeque<>();
    queue.offer(root);
    int level = 0;
    while (!queue.isEmpty()) {
      int count = queue.size();
      Integer pre = null;
      while (count-- > 0) {
        TreeNode node = queue.poll();
        int val = node.val;
        if (level % 2 == 0) {
          if (val % 2 != 1) {
            return false;
          }

          if (pre != null) {
            if (val <= pre) {
              return false;
            }
          }
        } else {
          if (val % 2 != 0) {
            return false;
          }

          if (pre != null) {
            if (val >= pre) {
              return false;
            }
          }
        }
        pre = val;

        if (node.left != null) {
          queue.offer(node.left);
        }

        if (node.right != null) {
          queue.offer(node.right);
        }
      }

      level++;
    }

    return true;
  }
}
```