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
# [LeetCode_1608_特殊数组的特征值](https://leetcode-cn.com/problems/special-array-with-x-elements-greater-than-or-equal-x/)
## 解法
### 思路
- 排序数组
- 遍历数组元素
  - 如果是第一个元素，则初始化num为0
  - 如果不是第一个元素，则初始化num为nums[i] + 1
  - 内层循环累加num，直到num大于nums[i]
  - 在内层循环判断的过程中，如果num值和数组长度减去当前坐标值的差相等，就累加一个count值，作为x的一种情况，同时记录当前num，作为可能的x值
  - 判断结束后，再判断count是否大于1，如果是则不符合题目要求，直接返回-1，否则累加num，直到内层循环退出
- 如果外层循环退出，则判断count是否等于1，如果是就返回之前记录的num，否则就返回-1
### 代码
```java
class Solution {
    public int specialArray(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length, count = 0, n = -1;
        for (int i = 0; i < len; i++) {
            int num;
            if (i == 0) {
                num = 0;
            } else {
                num = nums[i - 1] + 1; 
            }
            
            while (num <= nums[i]) {
                if (num == len - i) {
                    count++;
                    n = num;
                }
                
                if (count > 1) {
                    return -1;
                }
                
                num++;
            }
        }

        return count == 1 ? n : -1;
    }
}
```
# [LeetCode_1614_括号的最大嵌套深度](https://leetcode-cn.com/problems/maximum-nesting-depth-of-the-parentheses/)
## 解法
### 思路
- 遍历字符串
  - 如果遇到左括号就累加深度值depth，并更新最大深度值max
  - 如果遇到右括号就累减深度值
- 遍历结束后返回max
### 代码
```java
class Solution {
    public int maxDepth(String s) {
        int depth = 0, max = 0;
        char[] cs = s.toCharArray();

        for (char c : cs) {
            if (c == '(') {
                depth++;
                max = Math.max(depth, max);
            } else if (c == ')') {
                depth--;
            }
        }
        
        return max;
    }
}
```
# [LeetCode_1619_删除某些元素后的数组均值](https://leetcode-cn.com/problems/mean-of-array-after-removing-some-elements/)
## 解法
### 思路
- 排序数组
- 计算出要排除的元素个数
- 算出剩下元素的总和以及个数
- 基于算出的总和和个数算出平均值
### 代码
```java
class Solution {
  public double trimMean(int[] arr) {
    Arrays.sort(arr);
    int len = arr.length, skip = len / 20, sum = 0, count = 0;

    for (int i = skip; i < len - skip; i++) {
      sum += arr[i];
      count++;
    }

    return 1D * sum / count;
  }
}
```
# [LeetCode_1624_两个相同字符之间的最长子字符串](https://leetcode-cn.com/problems/largest-substring-between-two-equal-characters/)
## 解法
### 思路
- 遍历字符串，统计相同字符的坐标列表，用map存储
- 遍历map，找到列表长度大于1的列表中，最大最小值的差最大的那个值
### 代码
```java
class Solution {
    public int maxLengthBetweenEqualCharacters(String s) {
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.computeIfAbsent(s.charAt(i), x -> new ArrayList<>()).add(i);
        }
        
        int ans = -1;
        for (Character c : map.keySet()) {
            List<Integer> list = map.get(c);
            
            if (list.size() <= 1) {
                continue;
            }
            
            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            for (int num : list) {
                min = Math.min(num, min);
                max = Math.max(num, max);
            }
            
            ans = Math.max(ans, max - min - 1);
        }
        
        return ans;
    }
}
```
## 解法二
### 思路
- 遍历字符串找到某个字符出现的最左和最右的坐标
- 遍历最左和最右的坐标数组，计算相同字符对应的左右差值的最大值
### 代码
```java
class Solution {
    public int maxLengthBetweenEqualCharacters(String s) {
        int[] lefts = new int[26], rights = new int[26];
        Arrays.fill(lefts, -1);
        Arrays.fill(rights, -1);

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (lefts[c - 'a'] == -1) {
                lefts[c - 'a'] = i;
            }

            rights[c - 'a'] = i;
        }

        int ans = -1;
        for (int i = 0; i < 26; i++) {
            if (lefts[i] == rights[i]) {
                continue;
            }

            ans = Math.max(ans, rights[i] - lefts[i] - 1);
        }

        return ans;
    }
}
```
# [LeetCode_1221_分割平衡字符串](https://leetcode-cn.com/problems/split-a-string-in-balanced-strings/)
## 解法
### 思路
- 遍历字符串，对L和R进行计数，当相等时就统计一次，然后重置并继续统计
### 代码
```java
class Solution {
    public int balancedStringSplit(String s) {
        int l = 0, r = 0, ans = 0;
        for (char c : s.toCharArray()) {
            if (c == 'L') {
                l++;
            } else if (c == 'R') {
                r++;
            }
            
            if (l == r) {
                ans++;
                l = 0;
                r = 0;
            }
        }
        
        return ans;
    }
}
```
# [LeetCode_1629_按键持续时间最长的键](https://leetcode-cn.com/problems/slowest-key/)
## 解法
### 思路
- 初始化最大值max为-1，字符为空
- 遍历字符串：
  - 判断按键的时间是否大于max，如果是就更新max和结果字符
  - 如果按键时间与max相等，则判断当前字符是否比结果字符的字典序更大，如果是就更新字符为当前字符
### 代码
```java
class Solution {
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        int max = -1; char ans = ' ';
        for (int i = 0; i < keysPressed.length(); i++) {
            int time = i == 0 ? releaseTimes[i] : releaseTimes[i] - releaseTimes[i - 1];
            if (time > max) {
                max = time;
                ans = keysPressed.charAt(i);
            } else if (time == max && keysPressed.charAt(i) > ans) {
                ans = keysPressed.charAt(i);
            }
        }
        return ans;
    }
}
```
# [LeetCode_1636_按照频率将数组升序排序](https://leetcode-cn.com/problems/sort-array-by-increasing-frequency/)
## 解法
### 思路
使用Arrays.sort()排序，排序前还需要生成包装类数组
### 代码
```java
class Solution {
    public int[] frequencySort(int[] nums) {
        int len = nums.length;
        Integer[] arr = new Integer[len],
                  feqs = new Integer[201];
        
        Arrays.fill(feqs, 0);

        for (int i = 0; i < len; i++) {
            arr[i] = nums[i];
            feqs[nums[i] + 100]++;
        }
        
        Arrays.sort(arr, (x, y) -> {
            if (Objects.equals(feqs[x + 100], feqs[y + 100])) {
                return y - x;
            }
            
            return feqs[x + 100] - feqs[y + 100];
        });
        
        for (int i = 0; i < len ; i++) {
            nums[i] = arr[i];
        }
        
        return nums;
    }
}
```