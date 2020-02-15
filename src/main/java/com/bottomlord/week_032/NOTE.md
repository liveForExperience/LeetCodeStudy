# LeetCode_470_用Rand7实现Rand10
## 题目
已有方法 rand7 可生成 1 到 7 范围内的均匀随机整数，试写一个方法 rand10 生成 1 到 10 范围内的均匀随机整数。

不要使用系统的 Math.random() 方法。

示例 1:
```
输入: 1
输出: [7]
```
示例 2:
```
输入: 2
输出: [8,4]
```
示例 3:
```
输入: 3
输出: [8,1,10]
```
提示:
```
rand7 已定义。
传入参数: n 表示 rand10 的调用次数。
```
进阶:
```
rand7()调用次数的 期望值 是多少 ?
你能否尽量少调用 rand7() ?
```
## 解法
### 思路
- 通过`rand7()`可以获得两组均匀概率的数字：
    - `rand7()`可以获得`[1,7]`
    - `(rand7() - 1) * rand7()`可以获得`[0, 7, 14, 21, ..., 42]`的等差数列
- 将上述两组均匀概率的数字相加，去`[1, 40]`范围的值，就能获得10进制的均匀概率的值
- 公式`1 + rand7() + (rand7() - 1) * rand7()`，并只取`[1, 40]`
- 返回取余10的结果就是题目要求的答案
### 代码
```java
class Solution extends SolBase {
    public int rand10() {
        int index, col, row;
        do {
            col = rand7();
            row = rand7();
            index = col + (row - 1) * 7;
        } while (index > 40);

        return 1 + (index - 1) % 10;
    }
}
```
## 优化代码
### 思路
- 解法一中，被拒绝的范围是`[41, 49]`，这是一个`[1,9]`的区间
- 如果再加上一个`rand7()`，就能获得`[1, 63]`，这样拒绝的范围就是`[61,63]`，也就是`[1,3]`,这个值就成了新的行
- 如果再加上一个`rand7()`，就能获得`[1, 21]`，这样拒绝的范围就是`21`，这个值就成了新的行
- 这个时候就重新开始求`[1,49]`的随机数
### 代码
```java
/**
 * The rand7() API is already defined in the parent class SolBase.
 * public int rand7();
 * @return a random integer in the range 1 to 7
 */
class Solution extends SolBase {
    public int rand10() {
        while (true) {
            int index = rand7() + (rand7() - 1) * 7;
            if (index <= 40) {
                return 1 + (index - 1) % 10;
            }

            index = rand7() + (index - 40 - 1) * 7;
            if (index <= 60) {
                return 1 + (index - 1) % 10;
            }

            index = rand7() + (index - 60 - 1) * 7;
            if (index <= 20) {
                return 1 + (index - 1) % 10;
            }
        }
    }
}
```
# LeetCode_416_分割等和子集
## 题目
给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。

注意:
```
每个数组中的元素不会超过 100
数组的大小不会超过 200
```
示例 1:
```
输入: [1, 5, 11, 5]

输出: true

解释: 数组可以分割成 [1, 5, 5] 和 [11].
```
示例 2:
```
输入: [1, 2, 3, 5]

输出: false

解释: 数组不能分割成两个元素和相等的子集.
```
## 解法
### 思路
动态规划：
- 背包问题，从一定的物品中不重复的选出并放入背包中，获得指定的收益
- `dp[i][j]`：从`[0, i]`的范围中是否能找到一些数，使它们的和为`j`
- 状态转移方程：
    - 如果`nums[i] == j`，那么直接就是true
    - 如果不选择`nums[i]`，那么就看`dp[i - 1][j]`，也就是`[0, i - 1]`的范围中是否能找到这些数使得它们的和为`j`
    - 如果选择`nums[j]`，那么就看`dp[i - 1][j - nums[i]]`，因为`[0, i - 1]`范围中已经没有数能够相加等于`j`，那么就看这些数能否为`j - nums[i]`，那么再加上当前`nums[i]`，那么也能在`[0, i]`的范围中找到相加为`j`的数
- 初始化：
    - `dp[0][0] = true`
    - `dp[0][nums[0]]`：第一个元素，如果不大于target，那么就是true
- 过程：
    - 求得数组中元素的总和，从而获得半值`target`，如果总和为奇数，直接返回false
    - 嵌套遍历：
        - 外层遍历数组小标
        - 内层遍历`[0, target]`
        - 内层逻辑：
            - `nums[i] == target`，直接返回true
            - `dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]]`，还有一个前置条件，`nums[i] <= j`
    - 最终返回`dp[len - 1][target]`，因为只要证明了在整个数组范围内，有若干元素的和是`target`，也就必定有剩下的元素的和也为`target`
### 代码
```java
class Solution {
    public boolean canPartition(int[] nums) {
        int len = nums.length, sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if ((sum & 1) == 1) {
            return false;
        }

        int target = sum / 2;
        boolean[][] dp = new boolean[len][target + 1];
        if (nums[0] <= target) {
            dp[0][nums[0]] = true;
        }

        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= target; j++) {
                if (j == nums[i]) {
                    dp[i][j] = true;
                    continue;
                }

                if (nums[i] <= j) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                }
            }
        }

        return dp[len - 1][target];
    }
}
```
## 解法二
### 思路
记忆化回溯：
- 递归遍历整个样本空间，查看是否有元素相加等于`sum / 2`
- 使用两个set来存储坐标和数值
    - 回溯是修改记录坐标的状态，如果当前值没有相应的元素相加可以匹配，就回溯并遍历下一个元素
    - 同时数值也要存储，用来记录当前这个值已经被检查过，不用重复检查
### 代码
```java
class Solution {
    public boolean canPartition(int[] nums) {
        if (nums.length == 0) {
            return false;
        }

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if ((sum & 1) == 1) {
            return false;
        }

        Set<Integer> numSet = new HashSet<>();
        Set<Integer> indexSet = new HashSet<>();

        return backTrack(sum / 2, nums, numSet, indexSet);
    }

    private boolean backTrack(int num, int[] nums, Set<Integer> numSet, Set<Integer> indexSet) {
        if (num == 0) {
            return true;
        }

        for (int i = 0; i < nums.length; i++) {
            if (indexSet.contains(i)) {
                continue;
            }

            int a = num - nums[i];

            if (a < 0 || numSet.contains(a)) {
                continue;
            }

            if (a == 0) {
                return true;
            }

            numSet.add(a);
            indexSet.add(i);
            if (backTrack(a, nums, numSet, indexSet)) {
                return true;
            }
            indexSet.remove(i);
        }
        
        return false;
    }
}
```
# LeetCode_873_最长的斐波那契子序列的长度
## 题目
如果序列 X_1, X_2, ..., X_n 满足下列条件，就说它是 斐波那契式 的：
```
n >= 3
对于所有 i + 2 <= n，都有 X_i + X_{i+1} = X_{i+2}
给定一个严格递增的正整数数组形成序列，找到 A 中最长的斐波那契式的子序列的长度。如果一个不存在，返回  0 。
```
（回想一下，子序列是从原序列 A 中派生出来的，它从 A 中删掉任意数量的元素（也可以不删），而不改变其余元素的顺序。例如， [3, 5, 8] 是 [3, 4, 5, 6, 7, 8] 的一个子序列）

示例 1：
```
输入: [1,2,3,4,5,6,7,8]
输出: 5
解释:
最长的斐波那契式子序列为：[1,2,3,5,8] 。
```
示例 2：
```
输入: [1,3,7,11,12,14,18]
输出: 3
解释:
最长的斐波那契式子序列有：
[1,11,12]，[3,11,14] 以及 [7,11,18] 。
```
提示：
```
3 <= A.length <= 1000
1 <= A[0] < A[1] < ... < A[A.length - 1] <= 10^9
（对于以 Java，C，C++，以及 C# 的提交，时间限制被减少了 50%）
```
## 解法
### 思路
暴力：
- 使用set暂存所有元素
- 嵌套循环，确定起始的两个元素
- 在内层循环中计算它们的和`sum`
- 再内层循环中再开始一个循环，判断set中是否含有`sum`，如果含有，就替换原来的两个元素，继续如上的过程
- 直到set中再也找不到`sum`，计算这个循环的循环次数，与暂存值求最大值
- 外层嵌套的循环遍历结束，返回暂存值
### 代码
```java
class Solution {
    public int lenLongestFibSubseq(int[] A) {
        Set<Integer> set = new HashSet<>();
        for (int num : A) {
            set.add(num);
        }
        
        int ans = 0;
        
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                int x = A[i], y = A[j], sum = x + y, count = 2;
                
                while (set.contains(sum)) {
                    x = y;
                    y = sum;
                    
                    sum = x + y;
                    count++;
                }
                
                ans = Math.max(ans, count);
            }
        }
        
        return ans < 3 ? 0 : ans;
    }
}
```
## 优化代码
### 思路
- 因为题目中的数组是递增且不重复的，所以可以用一个指针来替代查询set的动作
- 初始化三个指针：
    - 前两个指针`i`和`j`，代表所有可能的斐波那契数列的前两个元素的坐标，通过嵌套循环生成
    - 指针`k`代表可能的斐波那契子序列中从第三个开始所有元素的坐标
- 过程：
    - 省去set初始化
    - 嵌套循环数组，生成`i`和`j`，退出条件：
        - `i < len - 2`，一直遍历到数组的倒数第3个
        - `j < len - 1`，一直遍历到数组的倒数第2个
    - 生成`i`时，通过`i + 2`来初始化`k`，代表`k`从`i`算起的第三个数算起
    - 内层循环中
        - 算出`sum = A[i] + A[j]`
        - 向右移动`k`，直到`A[k]` 不再小于`sum`
        - 判断`k`是否越界，如果越界直接返回`ans`，因为这说明当前两个初始元素的和已经超过了数组的最大值，之后已经没有可能的斐波那契数列了
        - 如果`sum != A[k]`，说明当前两个初始元素无法组成斐波那契数列，重新更换组合
        - 到这一步，当前组合能够组成斐波那契数列，开始循环，更新获取当前斐波那契可能的数列值，并统计元素个数
        - 同暂存的`ans`个数比较取最大值
    - 内层循环结束，判断当前`k`是否已经越界，如果越界就退出循环
    - 返回`ans`
### 代码
```java
class Solution {
    public int lenLongestFibSubseq(int[] A) {
        int ans = 0, len = A.length;
        for (int i = 0; i < len - 2; i++) {
            int k = i + 2;
            for (int j = i + 1; j < len - 1; j++) {
                int sum = A[i] + A[j];
                while (k < len && A[k] < sum) {
                    k++;
                }

                if (k == len) {
                    return ans;
                }

                if (A[k] != sum) {
                    continue;
                }

                int a, b = A[j], count = 2, pos = k;
                while (pos < len && A[pos] == sum) {
                    count++;
                    sum = sum + b;
                    a = b;
                    b = sum - a;
                    
                    while (pos < len && A[pos] < sum) {
                        pos++;
                    }
                }
                
                ans = Math.max(ans, count);
            }
            if (k == len) {
                continue;
            }
        }

        return ans;
    }
}
```
## 解法二
### 思路
动态规划：
- `dp[i][j]`：以`A[i]`和`A[j]`两个元素为结尾的斐波那契数列的最大长度
- 状态转移方程：如果当前两个元素的差，在数组中能够找到且对应的下标小于i，那么`dp[i][j] = dp[j - i][i] + 1`
- 初始化：初始化为2，意味着只有这两个元素
- 返回，`dp[][]`中的最大值
### 代码
```java
class Solution {
    public int lenLongestFibSubseq(int[] A) {
        int len = A.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            map.put(A[i], i);
        }

        int[][] dp = new int[len][len];
        int ans = 0;

        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                int k = A[j] - A[i];
                dp[i][j] = 2;
                if (k < A[i] && map.containsKey(k)) {
                    dp[i][j] = Math.max(dp[i][j], dp[map.get(k)][i] + 1);
                }
                
                ans = Math.max(ans, dp[i][j]);
            }
        }
        
        return ans > 2 ? ans : 0;
    }
}
```
# LeetCode_729_我的日程安排表I
## 题目
实现一个 MyCalendar 类来存放你的日程安排。如果要添加的时间内没有其他安排，则可以存储这个新的日程安排。

MyCalendar 有一个 book(int start, int end)方法。它意味着在 start 到 end 时间内增加一个日程安排，注意，这里的时间是半开区间，即 [start, end), 实数 x 的范围为，  start <= x < end。

当两个日程安排有一些时间上的交叉时（例如两个日程安排都在同一时间内），就会产生重复预订。

每次调用 MyCalendar.book方法时，如果可以将日程安排成功添加到日历中而不会导致重复预订，返回 true。否则，返回 false 并且不要将该日程安排添加到日历中。

请按照以下步骤调用 MyCalendar 类: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)

示例 1:
```
MyCalendar();
MyCalendar.book(10, 20); // returns true
MyCalendar.book(15, 25); // returns false
MyCalendar.book(20, 30); // returns true
解释: 
第一个日程安排可以添加到日历中.  第二个日程安排不能添加到日历中，因为时间 15 已经被第一个日程安排预定了。
第三个日程安排可以添加到日历中，因为第一个日程安排并不包含时间 20 。
```
说明:
```
每个测试用例，调用 MyCalendar.book 函数最多不超过 100次。
调用函数 MyCalendar.book(start, end)时， start 和 end 的取值范围为 [0, 10^9]。
```
## 解法
### 思路
暴力：
- 使用list保存时间区间
- 遍历这个list，查看当前时间是否**同时**符合如下条件：
    - 当前起始时间`start`小于窗口结束时间
    - 当前结束时间`end`大于窗口开始时间
- 如果符合就返回false
- 遍历结束，将窗口放入list，返回true
### 代码
```java
class MyCalendar {
    private List<int[]> list;
    public MyCalendar() {
        this.list = new ArrayList<>();
    }

    public boolean book(int start, int end) {
        for (int[] arr : list) {
            if (start < arr[1] && end > arr[0]) {
                return false;
            }
        }

        list.add(new int[]{start, end});
        return true;
    }
}
```
## 解法二
### 思路
使用二叉搜索树：
- 使用二叉搜索树的特性缩短定位目标窗口的时间
- 使用TreeMap
- 其他逻辑和解法一类似
### 代码
```java
class MyCalendar {
    private TreeMap<Integer, Integer> calendar;
    public MyCalendar() {
        this.calendar = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        Integer pre = calendar.floorKey(start);
        Integer next = calendar.ceilingKey(start);

        if ((pre == null || start >= calendar.get(pre)) && (next == null || end <= next)) {
            calendar.put(start, end);
            return true;
        }

        return false;
    }
}
```
## 解法三
### 思路
自己实现二叉搜索树：
- 定义一个存储时间窗口的二叉搜索树节点`CalendarNode`
    - `start`开始时间
    - `end`结束时间
    - `left`左节点
    - `right`右节点
- 过程：
    - 当需要插入新的时间窗口时，递归搜索二叉搜索树
    - 判断需要插入的时间窗口与当前节点的关系：
        - 如果`end <= calendarNode.start`，那么去左子树继续搜索
        - 如果`start >= calendarNode.end`，那么去右子树继续搜索
        - 若左、右子树不存在，就插入新节点并返回true
        - 否则，返回false
### 代码
```java
class MyCalendar {
    private CalendarNode root;
    public MyCalendar() {}

    public boolean book(int start, int end) {
        if (root == null) {
            root = new CalendarNode(start, end);
            return true;
        }

        return dfs(root, start, end);
    }

    private boolean dfs(CalendarNode node, int start, int end) {
        if (end <= node.start) {
            if (node.left == null) {
                node.left = new CalendarNode(start, end);
                return true;
            } else {
                return dfs(node.left, start, end);
            }
        }

        if (start >= node.end) {
            if (node.right == null) {
                node.right = new CalendarNode(start, end);
                return true;
            } else {
                return dfs(node.right, start, end);
            }
        }

        return false;
    }
    
    private class CalendarNode {
        public int start;
        public int end;
        public CalendarNode left;
        public CalendarNode right;
    
        public CalendarNode(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
```
# LeetCode_201_数字范围按位与
## 题目
给定范围 [m, n]，其中 0 <= m <= n <= 2147483647，返回此范围内所有数字的按位与（包含 m, n 两端点）。

示例 1: 
```
输入: [5,7]
输出: 4
```
示例 2:
```
输入: [0,1]
输出: 0
```
## 失败解法
### 失败原因
执行超时
### 思路
暴力：
- 循环与
- 当结果等于0退出
- 当相与的值等于Integer时退出
### 代码
```java
class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        if (m == Integer.MAX_VALUE) {
            return m;
        }
        
        int ans = m;
        for (int i = m + 1; i <= n; i++) {
            ans &= i;
            if (ans == 0 || i == Integer.MAX_VALUE) {
                break;
            }
        }
        
        return ans;
    }
}
```
## 解法
### 思路
- 相邻两个数相与，最低位必定为0，而如果`m < n`，那么必定至少有2个数，所以只要`m < n`，最低位就必定是0
- 所以可以不断右移两个数，判断这两个数是否是`m < n`，那么就能判断这些数相与后，有多少位个0
- 右移的时候记次数，用来代表0的个数，然后左移`m`或`n`0的个数次，就获得了最后相与的值
### 代码
```java
class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        int count = 0;
        while (m < n) {
            count++;
            m >>>= 1;
            n >>>= 1;
        }
        
        for (int i = 0; i < count; i++) {
            m <<= 1;
        }
        
        return m;
    }
}
```
## 优化代码
### 思路
- 通过`n & (n - 1)`可以将最低位的值置为0
- 通过这一步可以将上个解法先计算0的个数，再左移生成0的步骤合并
### 代码
```java
class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        while (m < n) {
            n &= (n - 1);
        }

        return n;
    }
}
```
## 解法二
### 思路
- 根据解法一可得，获得答案的关键是找到从高位到低位，第一次不同的位`x`
- 然后要获得答案，只需要用m与一个这样的数：`x`位的左边和m的`x`位左边完全相同，`x`位的右边全是0，`x`位与m的`x`相反的值相与，就能得到结果
- 如果S代表`x`左边的值，那么就相当于`m & SSSx000000`
- 如果W代表`x`右边位上的值，那么`m = SSS0WWWW, n = SSS1WWWW`，因为m是较小的值，所以`m = SSS0WWWW`是一定的
- 将`m ^ n`，获得`0001WWWW`
- 通过`Integer.highestOneBit(m ^ n)`获得`00010000`
- 通过取反获得`11101111`
- 再`+1`就获得了需要的结果`11110000`
- 将这个值与`m`相与，就能得到最终的结果
- 如果两个值相等，不能使用如上的思路，需要特殊处理，直接返回当前值`m`即可
### 代码
```java
class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        if (m == n) {
            return m;
        }

        return m & ~Integer.highestOneBit(m ^ n) + 1;
    }
}
```
## 优化代码
### 思路
相反数等于一个数的取反加1，所以可以将解法二继续优化
### 代码
```java
class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        return m == n ? m : m & -Integer.highestOneBit(m ^ n);
    }
}
```
# Interview_03_数组中的重复数字
## 题目
找出数组中重复的数字。

在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。

示例 1：
```
输入：
[2, 3, 1, 0, 2, 5, 3]
输出：2 或 3 
```
限制：
```
2 <= n <= 100000
```
## 解法
### 思路
set：
- 遍历数组
- 使用set记录遍历到的元素
- 如果遍历到的元素set中已存在就返回
### 代码
```java
class Solution {
    public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return num;
            }
            
            set.add(num);
        }
        
        return 1;
    }
}
```
## 解法二
### 思路
- 排序数组
- 遍历数组，如果前后元素一样就返回
### 代码
```java
class Solution {
    public int findRepeatNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return nums[i];
            }
        }
        
        return 1;
    }
}
```
## 解法三
### 思路
- 循环放置元素`nums[i]`，直到`nums[i] == i`
- 如果发现`nums[i] == nums[nums[i]]`，说明已经有了一个放置在正确位置上的值，当前值就是重复值
- 否则就将当前值放在这个值对应的下标下，这样当前这个值就去到了应该在的位置上了
- 如此循环，在时间复杂度位O(N)的情况下就能找到指定的值
### 代码
```java
class Solution {
    public int findRepeatNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                
                int tmp = nums[i];
                nums[i] = nums[nums[i]];
                nums[tmp] = tmp;
            }
        }
        
        return 1;
    }
}
```
# Interview_04_1_二维数组中的查找
## 题目
在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。

示例:
```
现有矩阵 matrix 如下：

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
给定 target = 5，返回 true。

给定 target = 20，返回 false。
```
限制：
```
0 <= n <= 1000

0 <= m <= 1000
```
## 解法
### 思路
从`matrix[0][0]`开始遍历，当遍历到的元素大于目标值，就换一行继续遍历，直到找到元素或者元素遍历完
### 代码
```java
class Solution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }

        for (int[] row : matrix) {
            for (int num : row) {
                if (num == target) {
                    return true;
                }

                if (num > target) {
                    break;
                }
            }
        }
        
        return false;
    }
}
```
## 失败解法
### 失败原因
超时
### 思路
递归遍历二维数组，方向位向下和向右，如果越界或者大于目标值，就返回false，找到了就返回true
### 代码
```java
class Solution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }
        
        return dfs(matrix, matrix.length, matrix[0].length, 0, 0, target);
    }
    
    private boolean dfs(int[][] matrix, int row, int col, int x, int y, int target) {
        if (x >= row || y >= col || matrix[x][y] > target) {
            return false;
        }
        
        if (matrix[x][y] == target) {
            return true;
        }
        
        return dfs(matrix, row, col, x + 1, y, target) || dfs(matrix, row, col, x, y + 1, target);
    }
}
```
## 解法二
### 思路
从[0, col - 1]开始遍历：
- 退出条件是行或列越界
- 如果不越界且小于target，row++
- 如果不越界且大于target，col--
- 否则返回true
### 代码
```java
class Solution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }
        
        int x = 0, y = matrix[0].length - 1;
        while (x < matrix.length && y >= 0) {
            if (matrix[x][y] > target) {
                y--;
            } else if (matrix[x][y] < target) {
                x++;
            } else {
                return true;
            }
        }
        
        return false;
    }
}
```
# Interview_05_替换空格
## 题目
请实现一个函数，把字符串 s 中的每个空格替换成"%20"。

示例 1：
```
输入：s = "We are happy."
输出："We%20are%20happy."
```
限制：
```
0 <= s 的长度 <= 10000
```
## 解法
### 思路
- 初始化StringBuilder
- 遍历字符数组`s`
- 如果当前字符为空格，替换成目标字符，加入StringBuilder
- 如果当前字符不是空格，直接加入StringBuilder
### 代码
```java
class Solution {
    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                sb.append("%20");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
```
# Interview_06_从尾到头打印链表
## 题目
输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。

示例 1：
```
输入：head = [1,3,2]
输出：[2,3,1]
```
限制：
```
0 <= 链表长度 <= 10000
```
## 解法
### 思路
递归：
- 退出条件：遍历到的节点位置为null
- 递归返回时将当前节点的值放入list
- 递归结束，返回list
### 代码
```java
class Solution {
    public int[] reversePrint(ListNode head) {
        List<Integer> list = new ArrayList<>();
        rescue(list, head);
        
        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        
        return ans;
    }
    
    private void rescue(List<Integer> list, ListNode node) {
        if (node == null) {
            return;
        }

        rescue(list, node.next);
        list.add(node.val);
    }
}
```
## 解法二
### 思路
- 翻转链表
- 遍历链表，将元素放入集合
- 返回集合
### 代码
```java
class Solution {
    public int[] reversePrint(ListNode head) {
        ListNode node = head, pre = null, start = node;
        int count = 0;

        while (node != null) {
            ListNode next = node.next;
            node.next = pre;
            pre = node;
            if (next == null) {
                start = node;
            }
            node = next;
            count++;
        }

        int[] ans = new int[count];
        int index = 0;
        while (start != null) {
            ans[index++] = start.val;
            start = start.next;
        }
        return ans;
    }
}
```
## 解法三
### 思路
- 遍历链表计算节点个数
- 初始化数组
- 遍历链表，从数组最后为止开始放置遍历到的节点值
- 返回数组
### 代码
```java
class Solution {
    public int[] reversePrint(ListNode head) {
        int count = 0;
        ListNode node = head;
        
        while (node != null) {
            node = node.next;
            count++;
        }
        
        node = head;
        int[] ans = new int[count];
        for (int i = count - 1; i >= 0; i--) {
            ans[i] = node.val;
            node = node.next;
        }
        
        return ans;
    }
}
```
# Interview_07_重建二叉树
## 题目
输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。

例如，给出
```
前序遍历 preorder = [3,9,20,15,7]
中序遍历 inorder = [9,3,15,20,7]
返回如下的二叉树：

    3
   / \
  9  20
    /  \
   15   7
```
限制：
```
0 <= 节点个数 <= 5000
```
## 解法
### 思路
- 前序遍历的第一个节点一定是根节点
- 通过该节点到中序遍历中找到对应的根节点：
    - 该节点的左边部分为根节点的左子树
    - 该节点的右边部分为根节点的右子树
- 以此类推，生成整个二叉树
- 过程：
    - 生成中序遍历的坐标与值之间的映射，方便根据前序的值获得中序的坐标
    - 开始递归：
        - 参数：
            - 前序的头尾指针，`preHead`，`preTail`
            - 中序的头尾指针，`inHead`，`inTail`
            - 这四个参数分成两部分，一一对应为当前递归层需要处理的子树
        - 如果参数符合如下条件，返回null，说明递归过程结束
            - `preHead > preTail`
            - `inHead > inTail`
        - 递归当前层使用前序的第一个节点`preHead`作为节点值，然后继续左右子树递归
        - 左右子树递归的四个参数值：
            - 左子树：
                - `preHead`：`preHead + 1`
                - `preTail`：`rootIndex - inHead(中序的左子树遍历序列的长度)` + `preHead`
                - `inHead`：`inHead`
                - `inTail`：`rootIndex - 1`
            - 右子树：
                - `preHead`：`rootIndex - inHead + preHead + 1`
                - `preTail`：`preTail`
                - `inHead`：`rootIndex + 1`
                - `inTail`：`inTail`
### 代码
```java
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int preLen = preorder.length, inLen = inorder.length;

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inLen; i++) {
            map.put(inorder[i], i);
        }

        return buildTree(0, preLen - 1, 0, inLen - 1, map, preorder);
    }

    private TreeNode buildTree(int preHead, int preTail, int inHead, int inTail, Map<Integer, Integer> map, int[] preorder) {
        if (preHead > preTail || inHead > inTail) {
            return null;
        }

        int rootVal = preorder[preHead], rootIndex = map.get(rootVal);
        TreeNode root = new TreeNode(rootVal);
        
        root.left = buildTree(preHead + 1, preHead + rootIndex - inHead, inHead, rootIndex - 1, map, preorder);
        root.right = buildTree(preHead + rootIndex - inHead + 1, preTail, rootIndex + 1, inTail, map, preorder);
        return root;
    }
}
```
# Interview_08_用两个栈实现队列
## 题目

## 解法
### 思路

### 代码
```java

```