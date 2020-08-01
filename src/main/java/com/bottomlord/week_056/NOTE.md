# LeetCode_392_判断子序列
## 题目
给定字符串 s 和 t ，判断 s 是否为 t 的子序列。

你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。

字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。

示例 1:
```
s = "abc", t = "ahbgdc"

返回 true.
```
示例 2:
```
s = "axc", t = "ahbgdc"

返回 false.
```
后续挑战 :
```
如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
```
## 解法
### 思路
- 同时遍历两个字符串
- 使用两个坐标代表遍历到的两个字符串的下标
    - `si`代表短字符串的坐标
    - `ti`代表长字符串的坐标
- 如果`ti`遍历完而`si`没有遍历完，返回false
### 代码
```
class Solution {
    public boolean isSubsequence(String s, String t) {
        int si = 0, sl = s.length(), ti = 0, tl = t.length();
        while (si < sl && ti < tl) {
            if (s.charAt(si) == t.charAt(ti)) {
                si++;
                ti++;
            } else {
                ti++;
            }
        }
        
        return si == sl && ti <= tl;
    }
}
```
## 解法二
### 思路
- 遍历t，记录26个字符在字符串中出现的所有下标，使用升序
- 遍历s，依次查看每个字符在t中出现的尽可能小的下标，也就是比前一个出现的下标大的最小值
    - 如果有这个值就继续遍历，直到遍历结束
    - 如果没有这个值就直接返回false
### 代码
```java
class Solution {
    public boolean isSubsequence(String s, String t) {
        Map<Character, List<Integer>> map =  new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            List<Integer> list = map.getOrDefault(c, new ArrayList<>());
            list.add(i);
            map.put(c, list);
        }
        
        int pre = -1;
        for (char c : s.toCharArray()) {
            if (!map.containsKey(c)) {
                return false;
            }
            
            List<Integer> list = map.get(c);
            int tmp = pre;
            for (Integer index : list) {
                if (index > tmp) {
                    pre = index;
                    break;
                }
            }
            
            if (tmp == pre) {
                return false;
            }
        }
        
        return true;
    }
}
```
# LeetCode_159_至多包含两个不同字符的最长子串
## 题目
给定一个字符串 s ，找出 至多 包含两个不同字符的最长子串 t ，并返回该子串的长度。

示例 1:
```
输入: "eceba"
输出: 3
解释: t 是 "ece"，长度为3。
```
示例 2:
```
输入: "ccaabbb"
输出: 5
解释: t 是 "aabbb"，长度为5。
```
## 解法
### 思路
哈希表+双指针：
- 使用hash表，key存储字符，value存储连续相同字符的最右下标
- 使用两个指针：
    - head：代表窗口的起始下标，第一个字符的最小坐标
    - tail：代表窗口的结尾下标，第二个字符的做大坐标
- 过程：
    - 遍历字符串，将当前字符放入map中指定的位置
        - 如果map元素总数大于2个，将元素放入map中，并更新value坐标值
        - 如果map元素总数大于2个，不放入元素
    - 如果当前map的元素个数是否大于2
        - 获取value值最小的key，保存其值value，并删除
        - 更新head指针为`value + 1`
    - 每次循环都更新窗口长度`(right - left) + 1`
        
### 代码
```java
class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int len = s.length(), head = 0, tail = 0, ans = 0;
        Map<Character, Integer> map = new HashMap<>();

        while (tail < len) {
            if (map.size() < 3) {
                map.put(s.charAt(tail), tail++);
            }
            
            if (map.size() == 3) {
                int index = map.remove(s.charAt(Collections.min(map.values())));
                head = index + 1;
            }
            
            ans = Math.max(ans, tail - head);
        }
        
        return ans;
    }
}
```
## 解法二
### 思路
使用数组代替解法一中的map，仍然使用双指针
- 遍历字符串，移动tail指针，并累加遍历到的字符的个数
- 用一个变量count来记录遍历到的不同字符数
- 如果遍历到的字符在数组中个数为0，那么就累加count
- 如果count值等于3，那么就循环移动head指针，将head指针对应的字符数到数组中进行累减
- 如果累减到0，那么就将count减1
- 每次移动tail都更新一次ans值，更新的动作是在移动head之前
- 也因为每次更新是在更新head之前，所以最后一次更新需要在循环结束以后，也就是tail越界，head可能被更新之后
### 代码
```java
class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int len = s.length(), head = 0, tail = 0, count = 0, ans = 0;
        int[] bucket = new int[128];

        while (tail < len) {
            char c = s.charAt(tail);
            if (bucket[c]++ == 0) {
                count++;
            }

            ans = Math.max(ans, tail - head);
            tail++;
            
            while (count > 2) {
                if (--bucket[s.charAt(head++)] == 0) {
                    count--;
                }
            }
        }
        
        return Math.max(ans, tail - head);
    }
}
```
# LeetCode_161_相隔为1的编辑距离
## 题目
给定两个字符串 s 和 t，判断他们的编辑距离是否为 1。

注意：

满足编辑距离等于 1 有三种可能的情形：
```
往 s 中插入一个字符得到 t
从 s 中删除一个字符得到 t
在 s 中替换一个字符得到 t
```
示例 1：
```
输入: s = "ab", t = "acb"
输出: true
解释: 可以将 'c' 插入字符串 s 来得到 t。
```
示例 2:
```
输入: s = "cab", t = "ad"
输出: false
解释: 无法通过 1 步操作使 s 变为 t。
```
示例 3:
```
输入: s = "1203", t = "1213"
输出: true
解释: 可以将字符串 s 中的 '0' 替换为 '1' 来得到 t。
```
## 解法
### 思路
遍历字符串：
- 先确定特殊情况，如果两个字符串的长度差大于1，直接返回错误
- 遍历字符串，比较两个字符串的字符
    - 如果两个字符相等继续循环
    - 如果两个字符串不相等，判断它们的长度是否相等：
        - 如果相等，就截去当前字符，判断剩下的字符串是否全等
        - 如果不相等，就截取第一个入参的字符串的当前字符，判断剩下的字符串是否全等
- 如果遍历结束，没有中断，那么就判断两个字符串是不是相差1，因为这时候可能短的字符串和另一个字符串开头的完全相等，那么必须要差一个字符，否则就全等了
### 代码
```java
class Solution {
    public boolean isOneEditDistance(String s, String t) {
        int sl = s.length(), tl = t.length();
        if (tl > sl) {
            return isOneEditDistance(t, s);
        }
        
        if (sl - tl > 1) {
            return false;
        }
        
        for (int i = 0; i < tl; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (sl == tl) {
                    return Objects.equals(s.substring(i + 1), t.substring(i + 1));
                } else {
                    return Objects.equals(s.substring(i + 1), t.substring(i));
                }
            }
        }
        
        return tl + 1 == sl;
    }
}
```
## 解法二
### 思路
动态规划：计算编辑距离
### 代码
```java
class Solution {
    public boolean isOneEditDistance(String s, String t) {
        int sl = s.length(), tl = t.length();
        if (Math.abs(sl - tl) > 1) {
            return false;
        }

        int[][] dp = new int[sl + 1][tl + 1];
        for (int i = 0; i <= sl; i++) {
            dp[i][0] = i;
        }
        
        for (int i = 0; i <= tl; i++) {
            dp[0][i] = i;
        }
        
        for (int i = 1; i <= sl; i++) {
            for (int j = 1; j <= tl; j++) {
                int sAdd = dp[i - 1][j] + 1, tAdd = dp[i][j - 1] + 1,
                    edit = s.charAt(i - 1) == t.charAt(j - 1) ? dp[i - 1][j - 1] : dp[i - 1][j - 1] + 1;
                dp[i][j] = Math.min(sAdd, Math.min(tAdd, edit));
            }
        }
        
        return dp[sl][tl] == 1;
    }
}
```
# LCP_13_寻宝
## 题目
我们得到了一副藏宝图，藏宝图显示，在一个迷宫中存在着未被世人发现的宝藏。

迷宫是一个二维矩阵，用一个字符串数组表示。它标识了唯一的入口（用 'S' 表示），和唯一的宝藏地点（用 'T' 表示）。但是，宝藏被一些隐蔽的机关保护了起来。在地图上有若干个机关点（用 'M' 表示），只有所有机关均被触发，才可以拿到宝藏。

要保持机关的触发，需要把一个重石放在上面。迷宫中有若干个石堆（用 'O' 表示），每个石堆都有无限个足够触发机关的重石。但是由于石头太重，我们一次只能搬一个石头到指定地点。

迷宫中同样有一些墙壁（用 '#' 表示），我们不能走入墙壁。剩余的都是可随意通行的点（用 '.' 表示）。石堆、机关、起点和终点（无论是否能拿到宝藏）也是可以通行的。

我们每步可以选择向上/向下/向左/向右移动一格，并且不能移出迷宫。搬起石头和放下石头不算步数。那么，从起点开始，我们最少需要多少步才能最后拿到宝藏呢？如果无法拿到宝藏，返回 -1 。

示例 1：
```
输入： ["S#O", "M..", "M.T"]

输出：16

解释：最优路线为： S->O, cost = 4, 去搬石头 O->第二行的M, cost = 3, M机关触发 第二行的M->O, cost = 3, 我们需要继续回去 O 搬石头。 O->第三行的M, cost = 4, 此时所有机关均触发 第三行的M->T, cost = 2，去T点拿宝藏。 总步数为16。 
```
示例 2：
```
输入： ["S#O", "M.#", "M.T"]

输出：-1

解释：我们无法搬到石头触发机关
```
示例 3：
```
输入： ["S#O", "M.T", "M.."]

输出：17

解释：注意终点也是可以通行的。
```
限制：
```
1 <= maze.length <= 100
1 <= maze[i].length <= 100
maze[i].length == maze[j].length
S 和 T 有且只有一个
0 <= M的数量 <= 16
0 <= O的数量 <= 40，题目保证当迷宫中存在 M 时，一定存在至少一个 O 。
```
## 解法
### 思路
动态规划：
- 整个寻宝过程可以划分为：
    - S->O->M
    - M1->O->M2
    - ......
    - Mn-1->O->Mn
    - Mn->T
- 其中确定开关的顺序和从哪个石头取会改变距离结果
- 所以最终可以把路径拆分成3部分：
    - 起点到开关
    - 开关到开关
    - 开关到终点
- 对于这些点之间的最短距离，可以通过bfs来求得
    - 先求字符串中的特殊位置的坐标
    - 遍历所有起点和开关，求得从该点开始到其他点的最短距离(bfs)
        - 起点到开关：
            - 遍历石头位置
            - 找到`起点->石头`+`开关->石头`的最小值
            - 确定每个开关到起点的最短距离
        - 开关到开关：
            - 双层循环，确定两个开关`i`和`j`的位置
            - 遍历石头的坐标，找到`开关i->石头`+`开关j->石头`的最小值
            - 确定两个开关之间的最短距离
        - 开关到终点：
            - 遍历石头位置
            - 找到`开关->石头`+`开关->终点`的最小值
            - 确定每个开关到终点的最短距离
 - 将如上求得的最短距离，存入一个二维数组`dict`中
    - 所有开关的个数是mSize
    - `dict[i][j]`：   
        - `i`的范围`[0,mSize]`，代表所有开关
        - `j`的范围是`[0,mSize+2]`，`j = mSize`代表起点，`j = mSize+1`代表终点，其他代表其他开关
- 开始动态规划：
    - `dp[mask][j]`：在第j个机关处，机关触发状态`mask`时的最小个数
        - mask：作为掩码，第i位上的1代表第i个开关的状态，1为开，0为关
        - j：作为在状态转移过程中要开的开关的坐标
    - 状态转移方程：
        - 三层循环
            - 第一层：遍历所有mask的可能，也就是从0到mSize位全为1的状态
            - 第二层：移动坐标i，遍历所有当前状态中，位上是1的数，找到状态转移的前一个状态，也就是从开着的第i位开关开始，进行到下一个开关的搜索动作
            - 第三层：移动坐标j，遍历所有当前状态中，位上是0的数，找到当前步要转移的状态，也就是将要开启的开关的位置
        - 方程：`dp[nextMask][j] = min(dp[nextMask][j], dp[mask][i] + dists[i][j])`
    - 初始状态：
        - 只有1位是1的所有mask可能，对应startDist的所有对应距离
        - 其他都是-1，代表还没有搜索过
    - 返回结果：
        - 遍历所有mask值为所有位都是1的可能dp，找到这些可能与结尾阶段的距离之和最小的组合
### 代码
```java
class Solution {
    private final int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private int row, col;
    private String[] maze;

    public int minimalSteps(String[] maze) {
        this.row = maze.length;
        this.col = maze[0].length();
        this.maze = maze;

        List<int[]> os = new ArrayList<>(),
                    ms = new ArrayList<>();

        int sx = -1, sy = -1, tx = -1, ty = -1;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                char c = getChar(i, j);

                if (c == 'M') {
                    ms.add(new int[]{i, j});
                } else if (c == 'O') {
                    os.add(new int[]{i, j});
                } else if (c == 'S') {
                    sx = i;
                    sy = j;
                } else if (c == 'T') {
                    tx = i;
                    ty = j;
                }
            }
        }

        int oSize = os.size(), mSize = ms.size();

        int[][] startDist = bfs(sx, sy);
        
        if (mSize == 0) {
            return startDist[tx][ty];
        }

        int[][] dists = new int[mSize][mSize + 2];
        for (int[] dist : dists) {
            Arrays.fill(dist, -1);
        }

        int[][][] mDists = new int[mSize][][];
        for (int i = 0; i < mSize; i++) {
            int[][] mDist = bfs(ms.get(i)[0], ms.get(i)[1]);
            mDists[i] = mDist;
            dists[i][mSize + 1] = mDist[tx][ty];
        }

        for (int i = 0; i < mSize; i++) {
            int startPhaseDist = -1;
            for (int[] o : os) {
                int ox = o[0], oy = o[1];
                if (mDists[i][ox][oy] != -1 && startDist[ox][oy] != -1) {
                    if (startPhaseDist == -1 || startPhaseDist > mDists[i][ox][oy] + startDist[ox][oy]) {
                        startPhaseDist = mDists[i][ox][oy] + startDist[ox][oy];
                    }
                }
            }
            dists[i][mSize] = startPhaseDist;
        }

        for (int i = 0; i < mSize; i++) {
            for (int j = i + 1; j < mSize; j++) {
                int midPhaseDist = -1;
                for (int k = 0; k < oSize; k++) {
                    int ox = os.get(k)[0], oy = os.get(k)[1],
                        ioDist = mDists[i][ox][oy], joDist = mDists[j][ox][oy];

                    if (ioDist != -1 && joDist != -1) {
                        if (midPhaseDist == -1 || midPhaseDist > ioDist + joDist) {
                            midPhaseDist = ioDist + joDist;
                        }
                    }
                }

                dists[i][j] = midPhaseDist;
                dists[j][i] = midPhaseDist;
            }
        }
        
        for (int i = 0; i < mSize; i++) {
            if (dists[i][mSize + 1] == -1 || dists[i][mSize] == -1) {
                return -1;
            }
        }

        int[][] dp = new int[1 << mSize][mSize];
        for (int[] arr : dp) {
            Arrays.fill(arr, -1);
        }

        for (int i = 0; i < mSize; i++) {
            dp[1 << i][i] = dists[i][mSize];
        }

        for (int mask = 1; mask < (1 << mSize); mask++) {
            for (int i = 0; i < mSize; i++) {
                if ((mask & (1 << i)) != 0) {
                    for (int j = 0; j < mSize; j++) {
                        if ((mask & (1 << j)) == 0) {
                            int nextMask = mask | (1 << j);
                            if (dp[nextMask][j] == -1 || dp[nextMask][j] > dp[mask][i] + dists[i][j]) {
                                dp[nextMask][j] = dp[mask][i] + dists[i][j];
                            }
                        }
                    }
                }
            }
        }

        int ans = Integer.MAX_VALUE, finalMask = (1 << mSize) - 1;
        for (int i = 0; i < mSize; i++) {
            ans = Math.min(dp[finalMask][i] + dists[i][mSize + 1], ans);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private int[][] bfs(int x, int y) {
        int[][] ans = new int[row][col];

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});

        for (int[] arr : ans) {
            Arrays.fill(arr, -1);
        }
        
        ans[x][y] = 0;

        while (!queue.isEmpty()) {
            int count = queue.size();

            while (count-- > 0) {
                int[] arr = queue.poll();
                if (arr == null) {
                    continue;
                }

                int curX = arr[0], curY = arr[1];
                for (int[] direction : directions) {
                    int nextX = curX + direction[0],
                        nextY = curY + direction[1];

                    if (isValid(nextX, nextY) && getChar(nextX, nextY) != '#' && ans[nextX][nextY] == -1) {
                        ans[nextX][nextY] = ans[curX][curY] + 1;
                        queue.offer(new int[]{nextX, nextY});
                    }
                }
            }
        }

        return ans;
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < row && y >= 0 && y < col;
    }

    private char getChar(int x, int y) {
        return maze[x].charAt(y);
    }
}
```
# LeetCode_162_寻找峰值
## 题目
峰值元素是指其值大于左右相邻值的元素。

给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。

数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。

你可以假设 nums[-1] = nums[n] = -∞。

示例 1:
```
输入: nums = [1,2,3,1]
输出: 2
解释: 3 是峰值元素，你的函数应该返回其索引 2。
```
示例 2:
```
输入: nums = [1,2,1,3,5,6,4]
输出: 1 或 5 
解释: 你的函数可以返回索引 1，其峰值元素为 2；
     或者返回索引 5， 其峰值元素为 6。
```
说明:
```
你的解法应该是 O(logN) 时间复杂度的。
```
## 解法
### 思路
二分查找：
- 确定中间值
- 把中间值和右边值比较，因为int值除以2会截取，所以值会偏左，可以这么比较
    - 如果当前值大于右边，说明在下坡中，那么就去左边找峰值
    - 如果当前值小于等于左边，说明在不在下坡中，那么就去右边找峰值
### 代码
```java
class Solution {
    public int findPeakElement(int[] nums) {
        return binarySearch(nums, 0, nums.length - 1);
    }

    private int binarySearch(int[] nums, int head, int tail) {
        if (head == tail) {
            return head;
        }

        int mid = head + (tail - head) / 2;

        return nums[mid] > nums[mid + 1] ? binarySearch(nums, head, mid) : binarySearch(nums, mid + 1, tail);
    }
}
```
# LeetCode_632_最小区间
## 题目
你有 k 个升序排列的整数数组。找到一个最小区间，使得 k 个列表中的每个列表至少有一个数包含在其中。

我们定义如果 b-a < d-c 或者在 b-a == d-c 时 a < c，则区间 [a,b] 比 [c,d] 小。

示例 1:
```
输入:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
输出: [20,24]
解释: 
列表 1：[4, 10, 15, 24, 26]，24 在区间 [20,24] 中。
列表 2：[0, 9, 12, 20]，20 在区间 [20,24] 中。
列表 3：[5, 18, 22, 30]，22 在区间 [20,24] 中。
```
注意:
```
给定的列表可能包含重复元素，所以在这里升序表示 >= 。
1 <= k <= 3500
-105 <= 元素的值 <= 105
对于使用Java的用户，请注意传入类型已修改为List<List<Integer>>。重置代码模板后可以看到这项改动。
```
## 解法
### 思路
堆：
- 题目要求的是求最小区间
- 所以参数有：
    - 区间的最左元素值`left`，初始为0，
    - 区间的最右元素值`right`，初始为int最大值
    - 暂存区间差值`minRange`，初始为`right - left`
    - 当前遍历到的所有序列元素值中的最大值`max`，这个值一般会用来作为`right`，尤其是找到更小区间的时候
    - 当前所有序列遍历到的下标，用一维数组`next`表示，数组每个下标对应`nums`的每个序列，值代表序列的遍历指针下标
- 过程：
    - 创建一个小顶堆，使用比较器，比较的是所有序列当前遍历到的值，放入的元素是序列在`nums`中的下标
    - 从0开始遍历到`nums`的长度，放入`nums`中所有序列的下标，比较它们坐标为0元素的值
    - 开始循环
        - 将堆顶的最小序列下标取出`minIndex`，也就能够获取到当前能够组成的区间最左值
        - 通过与`max`相减，获取当前区间差值，和`minRange`比较
        - 如果小：
            - 更新`minRange`
            - 更新`left = minIndex`
            - 更新`right = max`
        - 将`next[minIndex]`做一下递增，然后判断递增后的值有没有超出这个序列的边界，如果超出了说明整个判断过程结束了，无法再继续更新区间
        - 如果没有越界，就将`minIndex`重新放入小顶堆，排序
        - 同时更新`max`，看下`minIndex`序列的坐标移动后，新生成的区间的最大值有没有改变
- 循环结束，返回`[left, right]`
### 代码
```java
class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        int left = 0, right = Integer.MAX_VALUE;
        int max = 0, minRange = right - left;
        int size = nums.size();
        int[] next = new int[size];

        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return nums.get(o1).get(next[o1]) - nums.get(o2).get(next[o2]);
            }
        });

        for (int i = 0; i < size; i++) {
            queue.offer(i);
            max = Math.max(max, nums.get(i).get(next[i]));
        }

        while (true) {
            Integer minIndex = queue.poll();
            if (minIndex == null) {
                continue;
            }

            int curRange = max - nums.get(minIndex).get(next[minIndex]);
            if (curRange < minRange) {
                minRange = curRange;
                left = nums.get(minIndex).get(next[minIndex]);
                right = max;
            }

            next[minIndex]++;
            if (next[minIndex] == nums.get(minIndex).size()) {
                break;
            }

            queue.offer(minIndex);
            max = Math.max(max, nums.get(minIndex).get(next[minIndex]));
        }
        
        return new int[]{left, right};
    }
}
```