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
# [LeetCode_502_IPO](https://leetcode-cn.com/problems/ipo/)
## 解法
### 思路
- 先判断当前所有项目需要的资本是否都小于等于w，如果是的话，就累加当前值中最大的k个利润值就是答案
- 如果有大于w的情况，那么就需要遍历k和profit长度之间的最小值，相当于获取前k个最大利润的可以投资的项目的利润和
- 在循环的内部，就要找到当前w资本情况下能找到的最大利润的项目，然后将其累加到w上，并将成本设置为int最大值，从而代表当前项目绝对不能再做
### 代码
```java
class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        boolean speedUp = true;
        for (int cost : capital) {
            if (cost > w) {
                speedUp = false;
                break;
            }
        }

        if (speedUp) {
            Arrays.sort(profits);
            int sum = 0;
            for (int i = profits.length - 1; i >= profits.length - Math.min(k, profits.length); i--) {
                sum += profits[i];
            }
            return sum + w;
        }

        for (int i = 0; i < Math.min(k, profits.length); i++) {
            int index = -1;
            for (int j = 0; j < profits.length; j++) {
                if (w >= capital[j]) {
                    if (index == -1 || profits[j] > profits[index]) {
                        index = j;
                    }
                }
            }

            if (index == -1) {
                break;
            }

            w += profits[index];
            capital[index] = Integer.MAX_VALUE;
        }

        return w;
    }
}
```
# [LeetCode_1640_能否连接形成数组](https://leetcode-cn.com/problems/check-array-formation-through-concatenation/)
## 解法
### 思路
- 使用map存储pieces数组元素的首个元素值及在pieces中的对应坐标关联关系
- 遍历arr数组：
  - 如果在map中找不到当前元素的坐标值，说明pieces中没有arr中需要的元素，返回false
  - 通过map中存储的坐标index，获取piece数组，然后遍历piece数组，以此判断arr和piece数组中的元素是否相等，如果不相等就返回false
- arr遍历结束，说明pieces可以按照题目要求组成arr，返回true
### 代码
```java
class Solution {
    public boolean canFormArray(int[] arr, int[][] pieces) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < pieces.length; i++) {
            map.put(pieces[i][0], i);
        }
        
        for (int i = 0; i < arr.length;) {
            Integer index = map.get(arr[i]);
            if (index == null) {
                return false;
            }
            
            int[] piece = pieces[index];
            for (int j = 0; j < piece.length;) {
                if (arr[i++] != piece[j++]) {
                    return false;
                }
            }
        }
        
        return true;
    }
}
```
## 解法二
### 思路
因为元素的取值范围是[1,100]，所以可以使用数组替换解法一中的map
### 代码
```java
class Solution {
  public boolean canFormArray(int[] arr, int[][] pieces) {
    Integer[] bucket = new Integer[101];
    for (int i = 0; i < pieces.length; i++) {
      bucket[pieces[i][0]] = i;
    }

    for (int i = 0; i < arr.length;) {
      Integer index = bucket[arr[i]];
      if (index == null) {
        return false;
      }

      int[] piece = pieces[index];
      for (int num : piece) {
        if (arr[i] != num) {
          return false;
        }

        i++;
      }
    }

    return true;
  }
}
```
# [LeetCode_68_文本左右对齐](https://leetcode-cn.com/problems/text-justification/)
## 解法
### 思路
- 初始化一个list用于存放当前行能够放入的最大的单词个数
- 初始化rowLen用于记录当前行还能放入的字符长度，初始值为maxWidth+1，+1的原因是，因为在判断当前行能放入多少单词的时候，都需要默认增加一个空格，所以为了方便判断，所以加1，否则在判断最后一个单词的时候就会比较麻烦，因为最后一个单词可以不加空格
- 遍历words数组：
  - 区分两种情况，当前行是否是最后一行
    - 如果是，就按照最后一行的生成规则，基于list，生成最后一行的字符串
    - 如果不是，那么如果当前行已经不能再加入新的单词，就需要再判断两种情况：
      - 如果只有1个word，那就和最后一行的字符串生成规则保持一致
      - 如果超过1个word，就按照普通的字符串生成规则生成
    - 生成字符串的规则
      - 如果是最后一行或者这一行只有1个单词，那么就直接一个单词一个空格的累加，并计算还剩多上长度，单词用完后就直接加空格
      - 如果是普通行，就需要算出单词之间平均还需要多的空格数，以及左边的那些空格中需要多增加的空格数
### 代码
```java
class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        int wordNum = words.length, rowLen = maxWidth + 1, curRowLen = rowLen;
        List<String> curRowWords = new ArrayList<>(), ans = new ArrayList<>();
        for (int i = 0; i < wordNum; i++) {
            String word = words[i];

            if (curRowLen - word.length() - 1 >= 0) {
                curRowWords.add(word);
                if (i == wordNum - 1) {
                    ans.add(fillSpecialRow(curRowWords, maxWidth));
                }
                curRowLen -= (word.length() + 1);
            } else {
                if (curRowWords.size() == 1) {
                    ans.add(fillSpecialRow(curRowWords, maxWidth));
                } else {
                    ans.add(fillRow(curRowWords, maxWidth, curRowLen));
                }

                curRowWords.clear();
                curRowLen = rowLen;
                i--;
            }
        }

        return ans;
    }

    private String fillRow(List<String> words, int len, int leftLen) {
        int wordNum = words.size(), gaps = wordNum - 1,
            extraGapLen = leftLen / gaps, extraLen = leftLen % gaps;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < wordNum; i++) {
            sb.append(words.get(i));
            if (i != wordNum - 1) {
                sb.append(" ");

                for (int j = 0; j < extraGapLen; j++) {
                    sb.append(" ");
                }
            }

            if (extraLen > 0) {
                sb.append(" ");
                extraLen--;
            }
        }

        return sb.toString();
    }

    private String fillSpecialRow(List<String> words, int len) {
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(word);
            if (sb.length() < len) {
                sb.append(" ");
            }
        }

        while (sb.length() < len) {
            sb.append(" ");
        }

        return sb.toString();
    }
}
```
# [LeetCode_1894_找到需要补充粉笔的学生编号](https://leetcode-cn.com/problems/find-the-student-that-will-replace-the-chalk/)
## 解法
### 思路
- 求前缀和，在求的过程中，如果有前缀和大于k，就直接返回当前坐标
- 用k对所有学生消耗的总量做取余数的操作，获得余数作为新的k，代表处理了若干轮后，剩下的粉笔数一定会在当前轮被用完
- 遍历前缀和，查找第一个大于k的坐标，返回
### 代码
```java
class Solution {
    public int chalkReplacer(int[] chalk, int k) {
        int len = chalk.length;
        int[] sums = new int[len];
        sums[0] = chalk[0];
        if (sums[0] > k) {
            return 0;
        }
        
        for (int i = 1; i < len; i++) {
            sums[i] = sums[i - 1] + chalk[i];
            
            if (sums[i] > k) {
                return i;
            }
        }
        
        k %= sums[len - 1];
        
        for (int i = 0; i < len; i++) {
            if (sums[i] > k) {
                return i;
            }
        }
        
        return -1;
    }
}
```
# [LeetCode_1652_拆炸弹](https://leetcode-cn.com/problems/defuse-the-bomb/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public int[] decrypt(int[] code, int k) {
        int len = code.length;
        int[] ans = new int[len];
        if (k == 0) {
            Arrays.fill(ans, 0);
            return ans;
        }

        boolean positive = k > 0;
        for (int i = 0; i < len; i++) {
            if (positive) {
                for (int j = 1; j <= k; j++) {
                    ans[i] += code[(i + j) % len];
                }
            } else {
                for (int j = -1; j >= k; j--) {
                    ans[i] += code[(i + j + len) % len];
                }
            }
        }

        return ans;
    }
}
```
## 解法二
### 思路
前缀和
### 代码
```java
class Solution {
    public int[] decrypt(int[] code, int k) {
        int len = code.length;
        int[] ans = new int[len];

        if (k == 0) {
            return ans;
        }
        
        int[] sums = new int[len];
        for (int i = 0; i < len; i++) {
            int pre = i == 0 ? 0 : sums[i - 1];
            sums[i] = pre + code[i];
        }
        
        boolean positive = k > 0;
        for (int i = 0; i < len; i++) {
            if (positive) {
                if (k <= len - i - 1) {
                    ans[i] = sums[i + k] - sums[i];
                } else {
                    ans[i] += sums[len - 1] - sums[i] + sums[k - len + i];
                }
            } else {
                int dis = Math.abs(k);
                if (i + 1 > dis) {
                    ans[i] = sums[i - 1] - ((i - dis > 0) ? sums[i - dis - 1] : 0);
                } else if (i == 0) {
                    ans[i] = sums[len - 1] - sums[len - dis - 1];
                } else {
                    ans[i] = sums[i - 1] + sums[len - 1] - sums[len - dis + i - 1];
                }
            }
        }
        
        return ans;
    }
}
```