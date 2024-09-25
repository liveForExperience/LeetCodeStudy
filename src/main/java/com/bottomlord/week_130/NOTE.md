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
# [LeetCode_913_猫和老鼠](https://leetcode-cn.com/problems/cat-and-mouse/)
## 解法
### 思路
状态转移方程：
- `dp[mouse][cat][turn]`:表示当老鼠在mouse节点，猫在cat节点，且游戏已经进行了turn轮开始，猫和老鼠可能的游戏结果
- 状态边界：
  - mouse为0，则老鼠胜利，值为1
  - mouse == cat，则猫胜利，值为2
  - turn >= 2n，说明之后老鼠和猫都必定会走到重复的节点，值必定是0
- 因为是老鼠先移动，再由猫开始移动，所以可以通过turn的奇偶性来判断当前的玩家是谁
  - 偶数是老鼠
  - 奇数是猫
- 老鼠移动时候：
  - 如果存在一个可以移动的节点，能够使老鼠获得1的值，说明当前状态是老鼠必胜的状态，因为老鼠选择的是最优解
  - 如果老鼠到不了值为2的点，但存在一个能够使老鼠获得0的值，那么说明当前是老鼠的必和状态
  - 如果如上两种值都到达不了，那说明当前状态就是老鼠的必败状态
- 猫移动的时候：
  - 如果存在一个可以移动的节点，能够使猫获得2的值，说明当前状态是猫必胜的状态，因为猫选择的是最优解
  - 如果猫到不了值为2的点，但存在一个能够使猫获得0的值，那么说明当前是猫的必和状态
  - 如果如上两种值都到达不了，那说明当前状态就是猫的必败状态
- 初始化一个3维数组，用于存储dp方程的状态，并赋值-1
- 从`dp[1][2][0]`开始填充dp数组
- 如果当前mouse为0，则值为1；如果当前cat和mouse值一样，则值为2，否则就继续基于图进一步搜索
- 进一步搜索就是dfs，遍历图中的下一个顶点
  - 如果返回的结果是必胜则直接停止搜索，返回结果
  - 如果返回的结果是必和则赋值后继续搜索
  - 如果返回的结果是必败则继续搜索
### 代码
```java
class Solution {
    private int[][] graph;
    private int[][][] dp;
    private int n;

    public int catMouseGame(int[][] graph) {
        this.n = graph.length;
        this.graph = graph;
        this.dp = new int[n][n][2 * n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        return getResult(1, 2, 0);
    }

    private int getResult(int mouse, int cat, int turn) {
        if (turn == 2 * n) {
            return 0;
        }

        if (dp[mouse][cat][turn] < 0) {
            if (mouse == 0) {
                dp[mouse][cat][turn] = 1;
            } else if (mouse == cat) {
                dp[mouse][cat][turn] = 2;
            } else {
                getNextResult(mouse, cat, turn);
            }
        }

        return dp[mouse][cat][turn];
    }

    private void getNextResult(int mouse, int cat, int turn) {
        int curMove = turn % 2 == 0 ? mouse : cat;
        int defaultResult = curMove == mouse ? 2 : 1;
        int result = defaultResult;

        for (int next : graph[curMove]) {
            if (curMove == cat && next == 0) {
                continue;
            }

            int nextMouse = curMove == mouse ? next : mouse;
            int nextCat = curMove == cat ? next : cat;
            int nextResult = getResult(nextMouse, nextCat, turn + 1);

            if (nextResult != defaultResult) {
                result = nextResult;
                if (result != 0) {
                    break;
                }
            }
        }

        dp[mouse][cat][turn] = result;
    }
}
```
# [LeetCode_1576_替换所有的问号](https://leetcode-cn.com/problems/replace-all-s-to-avoid-consecutive-repeating-characters/)
## 解法
### 思路
- 遍历字符串
- 找到问号后，比较前后字母
  - 选择和前字母不同的字母
  - 如果后置字母非问号，该选择字母也不能和后置的相同
  - 如果后置字母是问号，则无需考虑
- 遍历结束，返回结果
### 代码
```java
class Solution {
    public String modifyString(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();
        if (cs.length == 1) {
            if (cs[0] == '?') {
                return "a";
            } else {
                return s;
            }
        }

        if (cs[0] == '?') {
            if (cs[1] == 'a') {
                cs[0] = 'b';
            } else {
                cs[0] = 'a';
            }
        }

        if (cs[n - 1] == '?') {
            if (cs[n - 2] == 'a') {
                cs[n - 1] = 'b';
            } else {
                cs[n - 1] = 'a';
            }
        }

        for (int i = 1; i < n - 1; i++) {
            if (cs[i] != '?') {
                continue;
            }

            cs[i] = pick(cs[i - 1], cs[i + 1]);
        }

        return new String(cs);
    }

    private char pick(char pre, char post) {
        for (int i = 0; i < 26; i++) {
            if (pre - 'a' != i && post - 'a' != i) {
                return (char)(i + 'a');
            }
        }
        return 'a';
    }
}
```
# [LeetCode_1991_找到数组的中间位置](https://leetcode-cn.com/problems/find-the-middle-index-in-array/)
## 解法
### 思路
前缀和
### 代码
```java
class Solution {
    public int findMiddleIndex(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }

        int[] sums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }

        for (int i = 0; i < n; i++) {
            if (i == 0) {
                if (sums[n] - sums[1] == 0) {
                    return 0;
                }
                continue;
            }

            if (i == n - 1) {
                if (sums[n - 1] - sums[0] == 0) {
                    return n - 1;
                }
                continue;
            }

            if (sums[i] - sums[0] == sums[n] - sums[i + 1]) {
                return i;
            }
        }

        return -1;
    }
}
```
# [LeetCode_2000_反转单词前缀](https://leetcode-cn.com/problems/reverse-prefix-of-word/)
## 解法
### 思路
- 遍历字符串，找到匹配的第一个字符的坐标
- 如果没找到就直接返回word
- 遍历0到index / 2范围内的字符串，将这些字符与`index - i`的字符进行互换
### 代码
```java
class Solution {
    public String reversePrefix(String word, char ch) {
        int index = findIndex(word, ch);
        if (index == -1) {
            return word;
        }
        char[] cs = word.toCharArray();
        int n = index / 2;
        for (int i = 0; i <= n; i++) {
            char c = cs[i];
            cs[i] = cs[index - i];
            cs[index - i] = c;
        }
        
        return new String(cs);
    }

    private int findIndex(String word, char c) {
        char[] cs = word.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == c) {
                return i;
            }
        }

        return -1;
    }
}
```
# [LeetCode_2006_差的绝对值为K的数对数目](https://leetcode-cn.com/problems/count-number-of-pairs-with-absolute-difference-k/)
## 解法
### 思路
暴力循环
### 代码
```java
class Solution {
  public int countKDifference(int[] nums, int k) {
    int n = nums.length, count = 0;
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        if (Math.abs(nums[i] - nums[j]) == k) {
          count++;
        }
      }
    }

    return count;
  }
}
```
## 解法二
### 思路
哈希表
- 因为要求是`i < j` 且 `|nums[i] - nums[j]| = k`，那么细想可以得到
- 其实题目允许满足`nums[j] - nums[i] = k`或者`nums[i] - nums[j] = k`中的任意一种即可，那么前后顺序就可以不用考虑了，因为i和j的位置可以互换
- 又因为k不等于0，所以nums[i]和nums[j]相等的情况也可以省略
- 所以只需要统计nums中元素出现的个数，然后再遍历一遍nums数组，找到 num + k 的值出现的个数即可
### 代码
```java
class Solution {
  public int countKDifference(int[] nums, int k) {
    int[] bucket = new int[201];
    int count = 0;
    for (int num : nums) {
      bucket[num]++;
    }

    for (int num : nums) {
      count += bucket[num + k];
    }

    return count;
  }
}
```
# [LeetCode_71_简化路径](https://leetcode-cn.com/problems/simplify-path/)
## 解法
### 思路
栈
### 代码
```java
class Solution {
  public String simplifyPath(String path) {
    String[] arr = path.split("/");
    Stack<String> stack = new Stack<>();

    for (String s : arr) {
      if (Objects.equals("", s) || Objects.equals(".", s)) {
        continue;
      }

      if (Objects.equals("..", s)) {
        if (!stack.isEmpty()) {
          stack.pop();
        }
        continue;
      }

      stack.push(s);
    }

    if (stack.isEmpty()) {
      return "/";
    }

    return "/" + String.join("/", stack);
  }
}
```
# [LeetCode_2011_执行操作后的变量值](https://leetcode-cn.com/problems/final-value-of-variable-after-performing-operations/)
## 解法
### 思路
- map存储操作符和数值之间的关系
- 遍历操作符数组，累加映射的数值
- 遍历结束后返回累加后的数值
### 代码
```java
class Solution {
    public int finalValueAfterOperations(String[] operations) {
        Map<String, Integer> mapping = new HashMap<>();
        mapping.put("X++", 1);
        mapping.put("++X", 1);
        mapping.put("X--", -1);
        mapping.put("--X", -1);
        
        int count = 0;
        for (String operation : operations) {
            count += mapping.get(operation);
        }
        return count;
    }
}
```
# [LeetCode_2016_增量元素之间的最大差值](https://leetcode-cn.com/problems/maximum-difference-between-increasing-elements/)
## 解法
### 思路
- 初始化最大差值为-1，因为差值不会为负，且未找到正好返回-1
- 遍历数值
- 记录并更新最小值
- 比较看是否比最小值大
  - 如果是，就更新差值
  - 如果不是，就跳过
- 遍历结束，返回最大差值
### 代码
```java
class Solution {
    public int maximumDifference(int[] nums) {
        int max = -1, min = nums[0], n = nums.length;
        for (int i = 1; i < n; i++) {
            if (nums[i] <= min) {
                min = nums[i];
                continue;
            }
            
            max = Math.max(max, nums[i] - min);
        }
        return max;
    }
}
```
# [LeetCode_1614_括号的最大嵌套深度](https://leetcode-cn.com/problems/maximum-nesting-depth-of-the-parentheses/)
## 解法
### 思路
- 遍历字符串
- 遇到左括号就累加，然后更新最大值
  - 直接可以累加，是因为题目保证了只存在有效括号
- 遇到右括号就累减
### 代码
```java
class Solution {
    public int maxDepth(String s) {
        int count = 0, ans = 0;
        char[] cs = s.toCharArray();
        for (char c : cs) {
            if (c == '(') {
                count++;
                ans = Math.max(ans, count);
            }
            
            if (c == ')') {
                count--;
            }
        }
        
        return ans;
    }
}
```
# [LeetCode_2027_转换字符串的最少操作次数](https://leetcode-cn.com/problems/minimum-moves-to-convert-string/)
## 解法
### 思路
- 遍历字符串
- 遇到第一个字符为X，就将当前连同之后的2个字符都翻转为O，并做一次计数
- 遍历结束，检查最后2个字符是否也有X的情况，如果有再+1
- 返回累加的结果
### 代码
```java
class Solution {
    public int minimumMoves(String s) {
        char[] cs = s.toCharArray();
        int count = 0, n = cs.length;
        for (int i = 0; i < cs.length - 2; i++) {
            if (cs[i] == 'X') {
                count++;
                cs[i] = cs[i + 1] = cs[i + 2] = 'O';
            }
        }

        if (cs[n - 2] == 'X' || cs[n - 1] == 'X') {
            count++;
        }

        return count;
    }
}
```
# [LeetCode_2032_至少在两个数组上出现的值](https://leetcode-cn.com/problems/two-out-of-three/)
## 解法
### 思路
- 题目描述有问题，不是说新的数组和原来三个数组不同，而是说新数组中的元素不重复
- 使用有限状态机
- 使用数组存储数字在3个数组中的状态
### 代码
```java
class Solution {
  public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
    int[] status = new int[101];
    List<Integer> ans = new ArrayList<>();
    for (int num : nums1) {
      status[num] = 1;
    }

    for (int num : nums2) {
      if (status[num] > 1) {
        continue;
      }

      status[num]++;
      status[num] <<= 1;
      if (status[num] == 4) {
        ans.add(num);
      }
    }

    for (int num : nums3) {
      if (status[num] == 1 || status[num] == 2) {
        status[num] += 4;
        ans.add(num);
      }
    }

    return ans;
  }
}
```
# [LeetCode_1629_按键持续时间最长的键](https://leetcode-cn.com/problems/slowest-key/)
## 解法
### 思路
- 桶计数
- 遍历记录间隔时间最大值
- 遍历桶，找到最大值对应的字符
### 代码
```java
class Solution {
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        int[] bucket = new int[26];
        int max = 0;
        char[] cs = keysPressed.toCharArray();
        for (int i = 0; i < releaseTimes.length; i++) {
            bucket[cs[i] - 'a'] = Math.max(i == 0 ? releaseTimes[i] : releaseTimes[i] - releaseTimes[i - 1], bucket[cs[i] - 'a']);
            max = Math.max(max, bucket[cs[i] - 'a']);
        }

        for (int i = 25; i >= 0; i--) {
            if (bucket[i] == max) {
                return (char) (i + 'a');
            }
        }

        return ' ';
    }
}
```
# [LeetCode_89_格雷编码](https://leetcode-cn.com/problems/gray-code/)
## 解法
### 思路
[图解](https://leetcode-cn.com/problems/gray-code/solution/gray-code-jing-xiang-fan-she-fa-by-jyd/)
### 代码
```java
class Solution {
    public List<Integer> grayCode(int n) {
        int head = 1;
        List<Integer> ans = new ArrayList<>();
        ans.add(0);

        for (int i = 0; i < n; i++) {
            for (int j = ans.size() - 1; j >= 0; j--) {
                ans.add(head + ans.get(j));
            }
            head <<= 1;
        }

        return ans;
    }
}
```