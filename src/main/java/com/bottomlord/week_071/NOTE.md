# [LeetCode_335_路径交叉](https://leetcode-cn.com/problems/self-crossing/)
## 解法
### 思路
会相交的情况：
- 假设最后一条边是i，边的长度是`[i]`
- 移动次数大于3
- 移动4次相交的条件：
    - `[i - 2] <= [i]`
    - `[i - 1] <= [i - 3]`
- 移动5次相交的条件：
    - 如果最后4次的状态和移动四次的状态相同，那么就会相交
    - 如果`[i - 1] == [i - 3]`，且`[i - 2] <= [i - 4]`
- 移动6次及以上相交的条件：
    - 最后4次的状态和移动4次相交的状态相同
    - `[i - 2]`比`[i - 4]`长，且`[i - 1]` + `[i - 5]`比`[i - 3]`长，导致如果相交，就是和`i - 5`相交，这种情况的条件就是：
        - `[i - 1]` + `[i - 5]` >= `[i - 3]`
        - `[i - 2]` + `[i]` >= `[i - 4]`
        - `[i - 4]` > `[i - 2]`
        - `[i - 3]` > `[i - 1]`
#### 代码
```java
class Solution {
    public boolean isSelfCrossing(int[] x) {
        if (x.length <= 3) {
            return false;
        }

        for (int i = 3; i < x.length; i++) {
            if (x[i] >= x[i - 2] && x[i - 1] <= x[i - 3]) {
                return true;
            }

            if (i >= 4 && x[i - 1] == x[i - 3] && x[i] + x[i - 4] >= x[i - 2]) {
                return true;
            }

            if (i >= 5 && x[i] + x[i - 4] >= x[i - 2] && x[i - 5] + x[i - 1] >= x[i - 3] && x[i - 4] < x[i - 2] && x[i] < x[3]) {
                return true;
            }
        }

        return false;
    }
}
```
# [LeetCode_LCP08_剧情触发时间](https://leetcode-cn.com/problems/ju-qing-hong-fa-shi-jian/)
## 解法
### 思路
- 复制requirements数组copy，在坐标3位置上增加一个当前元素的坐标
- 排序copy，按第一个元素升序排列
- 初始化一个队列queue和一个栈stack
- 先判断为0的情况下，有没有特殊情况的requirement
- 然后开始遍历increase，用sum数组累加，并判断当前queue中头部的部分元素是否符合requirement的要求，判断的范围就是requirement的第一个元素是否小于等于sum的第一个元素
- 如果sum的所有元素符合requirements的要求，就出队，并根据坐标3记录在ans数组中，记录的值是当前increase的坐标 + 1
- 如果不符合，就压入stack中，等当前sum的可判断范围遍历结束后，将栈中的元素弹出并压回队列头部
- increase遍历结束后，返回ans
### 代码
```java
class Solution {
    public int[] getTriggerTime(int[][] increase, int[][] requirements) {
        int[] ans = new int[requirements.length];
        Arrays.fill(ans, -1);

        int[][] requirementsCopy = new int[requirements.length][4];
        for (int i = 0; i < requirements.length; i++) {
            requirementsCopy[i][0] = requirements[i][0];
            requirementsCopy[i][1] = requirements[i][1];
            requirementsCopy[i][2] = requirements[i][2];
            requirementsCopy[i][3] = i;
        }

        Arrays.sort(requirementsCopy, Comparator.comparingInt(x -> x[0]));

        int[] sum = new int[]{0, 0, 0};
        LinkedList<int[]> queue = new LinkedList<>();
        Stack<int[]> stack = new Stack<>();
        for (int[] arr : requirementsCopy) {
            queue.offer(arr);
        }

        while (!queue.isEmpty() && queue.getFirst()[0] <= sum[0]) {
            int[] arr = queue.pollFirst();
            if (arr[0] <= sum[0] && arr[1] <= sum[1] && arr[2] <= sum[2]) {
                ans[arr[3]] = 0;
            } else {
                stack.push(arr);
            }
        }

        while (!stack.isEmpty()) {
            queue.offerFirst(stack.pop());
        }

        for (int i = 0; i < increase.length; i++) {
            sum[0] += increase[i][0];
            sum[1] += increase[i][1];
            sum[2] += increase[i][2];

            while (!queue.isEmpty() && queue.getFirst()[0] <= sum[0]) {
                int[] arr = queue.pollFirst();
                if (arr[0] <= sum[0] && arr[1] <= sum[1] && arr[2] <= sum[2]) {
                    ans[arr[3]] = i + 1;
                } else {
                    stack.push(arr);
                }
            }

            while (!stack.isEmpty()) {
                queue.offerFirst(stack.pop());
            }
        }

        return ans;
    }
}
```
# [LeetCode_LCP09_最小跳跃次数](https://leetcode-cn.com/problems/zui-xiao-tiao-yue-ci-shu/)
## 解法
### 思路
动态规划：
- `dp[i]`：第i位置弹出机器的最小步数
- 状态转移方程：
    - `i + jump[i] >= n`：`dp[i] = 1`
    - `i + jump[i] < n`：`d[i] = dp[i + jump[i]] + 1`
- base case：`dp[n - 1] = 1`
- 结果：`dp[0]`
- 过程：
    - 从最大位置朝起始位置移动，进行状态转移
    - 若当前jump的距离无法跳出机器，就依赖`dp[i + jump[i]]`的值做更新
    - 同时将从`i + 1`位置开始到`dp[i] + 1 >= dp[j]`的第一个坐标为止的所有dp值更新成`dp[i] + 1`
        - 要这么更新的原因：当前坐标更新的最小步数，那么在这个坐标之后的（在过程中已经更新过的位置的dp）需要重新做一次更新，确保是基于当前i坐标的最小值
        - 到`dp[i] + 1 > dp[j]`位置为止的原因：向右更新的目的是，根据当前更新的dp，对右边之前的dp值做更新，在更新的时候，如果遍历到的dp值比`dp[i] + 1`大或者相等，那么说明右边可能还存在比`dp[i] + 1`更大的值，因为每次更新的结果就是将当前坐标右边需要更新的值更新成`dp[i] + 1`，也就是说，当前这个值右边是可能存在比它大1的数的
        - 可以这么更新的原因：到坐标的左边位置的步数都是1，所以当前`dp[i]`的值是x，那么它右边的所有坐标，理论上都可以以`dp[i] + 1`的步数离开机器
### 代码
```java
class Solution {
    public int minJump(int[] jump) {
        int len = jump.length;
        int[] dp = new int[len];
        dp[len - 1] = 1;
        
        for (int i = len - 2; i >= 0; i--) {
            dp[i] = i + jump[i] >= len ? 1 : dp[i + jump[i]] + 1;
            
            for (int j = i + 1; j < len && dp[i] + 1 <= dp[j]; j++) {
                dp[j] = dp[i] + 1;
            }
        }
        
        return dp[0];
    }
}
```
# [LeetCode_339_嵌套列表权重和](https://leetcode-cn.com/problems/nested-list-weight-sum/)
## 解法
### 思路
递归求和
### 代码
```java
class Solution {
    public int depthSum(List<NestedInteger> nestedList) {
        int sum = 0;
        for (NestedInteger nestedInteger : nestedList) {
            sum += sum(nestedInteger, 1);
        }
        
        return sum;
    }
    
    private int sum(NestedInteger nestedInteger, int depth) {
        if (nestedInteger == null) {
            return 0;
        }
        
        if (nestedInteger.isInteger()) {
            return nestedInteger.getInteger() * depth;
        }
        
        List<NestedInteger> nestedIntegers = nestedInteger.getList();
        int sum = 0;
        for (NestedInteger ni : nestedIntegers) {
            sum += sum(ni, depth + 1);
        }
        
        return sum;
    }
}
```
# [LeetCode_340_至多包含K个不同字符](https://leetcode-cn.com/problems/longest-substring-with-at-most-k-distinct-characters/)
## 解法
### 思路
双指针+哈希表：
- 初始化两个指针用来记录：
    - x：记录有效字符串的起始坐标
    - y：记录有效字符串的结尾坐标
- 初始化哈希表map：记录遍历到的字符及个数
- 初始化两个数值记录变量：
    - sum：记录当前字符串的长度
    - max：记录当前暂存的最长字符串的值
- 过程：
    - 移动y，一步步确定当前可用的字符串
    - 每次确定完新字符串后，做一些准备动作
        - sum++，先更新字符串长度
        - 往map里放字符并记录个数
    - 准备动作做完后，判断map的长度是否大于k
        - 如果不是，就更新max
        - 如果是：就开始移动x，并判断是否满足了map的size小于等于k的条件，并更新sum，直到满足位置
    - y越界后，返回max
### 代码
```java
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int x = 0, y =0, max = 0, sum = 0;
        Map<Character, Integer> map = new HashMap<>();
        char[] cs = s.toCharArray();
        while (y < cs.length) {
            map.put(cs[y], map.getOrDefault(cs[y], 0) + 1);
            y++;
            sum++;
            if (map.size() <= k) {
                max = Math.max(max, sum);
            } else {
                while (x < y) {
                    sum--;
                    map.put(cs[x], map.get(cs[x]) - 1);
                    if (map.get(cs[x]) <= 0) {
                        map.remove(cs[x]);
                    }
                    x++;
                    if (map.size() <= k) {
                        break;
                    }
                }
            }
        }
        return max;
    }
}
```
# [LeetCode_348_判定井字棋胜负](https://leetcode-cn.com/problems/design-tic-tac-toe/)
## 解法
### 思路
二维数组模拟
### 代码
```java
class TicTacToe {
    private int[][] board;
    private int n;

    public TicTacToe(int n) {
        this.n = n;
        this.board = new int[n][n];
    }

    public int move(int row, int col, int player) {
        board[row][col] = player;

        boolean rw = true, cw = true, pw = true, nw = true;
        for (int i = 0; i < n; i++) {
            if (rw && board[row][i] != player) {
                rw = false;
            }

            if (cw && board[i][col] != player) {
                cw = false;
            }

            if (pw && board[i][i] != player) {
                pw = false;
            }

            if (nw && board[i][n - 1 - i] != player) {
                nw = false;
            }

            if (!rw && !cw && !pw && !nw) {
                break;
            }
        }

        return rw || cw || pw || nw ? player : 0;
    }
}
```
## 解法二
### 思路
使用一维数组模拟，使用正负1代表玩家在当前行或列或对角线出现的记录，这样最终只要计算当前行、列和两个对角线有没有出现总和的绝对值是初始长度n的情况，就可以判断有人胜出，而谁赢就通过值是正数还是负数来判断
### 代码
```java
class TicTacToe {
    private int[] rows, cols;
    private int n, pie, na;

    public TicTacToe(int n) {
        this.n = n;
        this.pie = 0;
        this.na = 0;
        this.rows = new int[n];
        this.cols = new int[n];
    }

    public int move(int row, int col, int player) {
        if (row == col) {
            na = player == 1 ? na + 1 : na - 1;
        }

        if (row + col == n - 1) {
            pie = player == 1 ? pie + 1: pie - 1;
        }

        rows[row] = player == 1 ? rows[row] + 1 : rows[row] - 1;
        cols[col] = player == 1 ? cols[col] + 1 : cols[col] - 1;

        if (Math.abs(na) == n) {
            return na > 0 ? 1 : 2;
        }

        if (Math.abs(pie) == n) {
            return pie > 0 ? 1 : 2;
        }

        if (Math.abs(rows[row]) == n) {
            return rows[row] > 0 ? 1 : 2;
        }

        if (Math.abs(cols[col]) == n) {
            return cols[col] > 0 ? 1 : 2;
        }

        return 0;
    }

}
```
# [LeetCode_351_安卓系统手势解锁](https://leetcode-cn.com/problems/android-unlock-patterns/)
## 解法
### 思路
记忆化+回溯：
- 使用坐标`[0,8]`对应数字盘
- 递归过程中传递的参数：
    - `last`：上一个坐标，初始化为-1
    - `len`：剩余深度
    - `memo`：记忆化搜索的缓存
- 退出条件：
    - `len = 0`：代表生成完成，返回值1
- 过程：
    - 遍历所有9个坐标，判断该坐标是否符合要求
        - `last == -1`：代表当前是第一个数字，返回true
        - `memo[index] == true`：代表已经使用过该数字，返回false
        - `(index + last) % 2 == 1`：代表两个数之间在行或列上相邻
        - `(index + last) / 2 == 4`：代表两个数分别在数字盘的对角顶点
        - `index % 3 != last % 3`：代表两个数不在同一列
        - `index / 3 != last / 3`：代表两个数不在同一行
        - `(index % 3 != last % 3) && (index / 3 != last / 3)`：代表两个点在同一斜线上
        - 如上所有条件都判断过后，就只剩下在同一行或同一列且中间隔一个坐标的情况
    - 如果当前坐标符合要求，memo设置为遍历过，继续递归，递归时len-1，递归返回值与当前层暂存的计数值累加，并回溯重置memo
    - 最终返回当前层累加的暂存值  
- 最外层通过遍历`[m,n]`的区间，也就是len的长度来驱动回溯的结果，将所有长度的可能累加作为最终的结果返回
### 代码
```java
class Solution {
    public int numberOfPatterns(int m, int n) {
        int sum = 0;
        for (int i = m; i <= n; i++) {
            sum += backTrack(-1, i, new boolean[9]);
        }
        return sum;
    }
    
    private int backTrack(int last, int len, boolean[] memo) {
        if (len == 0) {
            return 1;
        }
        
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            if (isValid(last, i, memo)) {
                memo[i] = true;
                sum += backTrack(i, len - 1, memo);
                memo[i] = false;
            }
        }
        return sum;
    }
    
    private boolean isValid(int last, int index, boolean[] memo) {
        if (memo[index]) {
            return false;
        }
        
        if (last == -1) {
            return true;
        }
        
        if ((index + last) % 2 == 1) {
            return true;
        }
        
        int mid = (last + index) / 2;
        if (mid == 4) {
            return memo[mid];
        }
        
        if ((index % 3 != last % 3) && (index / 3) != (last / 3)) {
            return true;
        }
        
        return memo[mid];
    }
}
```
## 解法二
### 思路
在解法一的基础上，将最外层驱动的过程从9次缩减成3次：
- 1、3、7、9这4个点对称
- 2、4、6、8这4个点对称
- 5
### 代码
```java
class Solution {
    public int numberOfPatterns(int m, int n) {
        int sum = 0;
        for (int i = m; i <= n; i++) {
            boolean[] memo = new boolean[9];
            memo[0] = true;
            sum += backTrack(0, i - 1, memo) * 4;
            
            Arrays.fill(memo, false);
            memo[1] = true;
            sum += backTrack(1, i - 1, memo) * 4;
            
            Arrays.fill(memo, false);
            memo[4] = true;
            sum += backTrack(4, i - 1, memo);
        }
        return sum;
    }

    private int backTrack(int last, int len, boolean[] memo) {
        if (len == 0) {
            return 1;
        }

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            if (isValid(last, i, memo)) {
                memo[i] = true;
                sum += backTrack(i, len - 1, memo);
                memo[i] = false;
            }
        }
        return sum;
    }

    private boolean isValid(int last, int index, boolean[] memo) {
        if (memo[index]) {
            return false;
        }

        if ((index + last) % 2 == 1) {
            return true;
        }

        int mid = (last + index) / 2;
        if (mid == 4) {
            return memo[mid];
        }

        if ((index % 3 != last % 3) && (index / 3) != (last / 3)) {
            return true;
        }

        return memo[mid];
    }
}
```
# [LeetCode_352_将数据流变为多个不相交区域](https://leetcode-cn.com/problems/data-stream-as-disjoint-intervals/)
## 解法
### 思路
- 使用treeSet保存数值
- 分流时，遍历set，查看当前遍历到的元素
    - 是否是数据流的中的中间元素，也就是它的前后值是否存在于set中
    - 是否是数据流的起始元素，也就是没有小1，有大1的元素
    - 是否是数据流的结尾元素，也就是没有大1，有小1的元素
    - 是否是数据流中单一个点，也就是没有大1，也没有小1的元素
### 代码
```java
class SummaryRanges {
        private Set<Integer> set;
        
        public SummaryRanges() {
            this.set = new TreeSet<>();
        }

        public void addNum(int val) {
            this.set.add(val);
        }

        public int[][] getIntervals() {
            List<int[]> list = new LinkedList<>();
            int[] nums = new int[2];
            Iterator<Integer> iterator = set.iterator();
            while (iterator.hasNext()) {
                int num = iterator.next();
                if (!set.contains(num - 1) && !set.contains(num + 1)) {
                    nums = new int[]{num, num};
                    list.add(nums);
                    nums = new int[2];
                    continue;
                }

                if (set.contains(num - 1) && set.contains(num + 1)) {
                    continue;
                }

                if (!set.contains(num - 1) && set.contains(num + 1)) {
                    nums[0] = num;
                    continue;
                }

                if (set.contains(num - 1) && !set.contains(num + 1)) {
                    nums[1] = num;
                    list.add(nums);
                    nums = new int[2];
                }
            }

            int[][] ans = new int[list.size()][2];
            for (int i = 0; i < list.size(); i++) {
                ans[i][0] = list.get(i)[0];
                ans[i][1] = list.get(i)[1];
            }
            return ans;
        }
    }
```