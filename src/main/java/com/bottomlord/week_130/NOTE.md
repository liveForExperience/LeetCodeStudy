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