# Interview_47_礼物的最大值
## 题目
在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？

示例 1:
```
输入: 
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
输出: 12
解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
```
提示：
```
0 < grid.length <= 200
0 < grid[0].length <= 200
```
## 解法
### 思路
递归+记忆化搜索
- 使用map存储已经遍历过的`x,y`位起点的路径上的最大值
- 递归退出条件是坐标越界
- 过程中就是判断向右或向下的节点，`visited`中是否已经存储，如果已经存储就直接返回，否则递归下去求得这个路径的最大值
- 最终返回当前节点值与向下或向右的最大值之和
### 代码
```java
class Solution {
    public int maxValue(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        return dfs(grid, 0, 0, grid.length, grid[0].length, new HashMap<>());
    }

    private int dfs(int[][] grid, int x, int y, int row, int col, Map<Integer, Integer> visited) {
        if (x < 0 || x >= row || y < 0 || y >= col) {
            return 0;
        }

        int right = visited.containsKey(1000 * x + y + 1) ? visited.get(1000 * x + y + 1) : dfs(grid, x, y + 1, row, col, visited);
        int down = visited.containsKey(1000 * (x + 1) + y) ? visited.get(1000 * (x + 1) + y) : dfs(grid, x + 1, y, row, col, visited);
        
        int max = grid[x][y] + Math.max(right, down);
        visited.put(1000 * x + y, max);
        return max;
    }
}
```
## 解法二
### 思路
动态规划：
- `dp[x][y]`：坐标`x,y`为起点的最大路径值
- 状态转移方程：`dp[x][y] = grid[x][y] + max(dp[x + 1][y], dp[x][y + 1])`
- 初始化：`dp[row - 1][col - 1] = grid[row - 1][col - 1]`
- 返回结果：`dp[0][0]`
- 过程：嵌套循环所有二维数组节点，从行和列的最大值开始向前遍历，遍历中执行状态转移方程
### 代码
```java
class Solution {
    public int maxValue(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int row = grid.length, col = grid[0].length;
        int[][] dp = new int[row][col];
        dp[row - 1][col - 1] = grid[row - 1][col - 1];
        
        for (int i = row - 1; i >= 0; i--) {
            for (int j = col - 1; j >= 0; j--) {
                dp[i][j] = grid[i][j] + Math.max(i + 1 < row ? dp[i + 1][j] : 0, j + 1 < col ? dp[i][j + 1] : 0);
            }
        }
        
        return dp[0][0];
    }
}
```
## 解法三
### 思路
- 因为只能向右和向下，所以第一行和第一列的值可以处理成途径节点的累加值
- 然后通过这一行一列的初始值，开始状态转移
### 代码
```java
class Solution {
    public int maxValue(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int row = grid.length, col = grid[0].length;

        for (int i = 1; i < row; i++) {
            grid[i][0] += grid[i - 1][0];
        }
        
        for (int i = 1; i < col; i++) {
            grid[0][i] += grid[0][i - 1];
        }
        
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                grid[i][j] += Math.max(grid[i - 1][j], grid[i][j - 1]);
            }
        }
        
        return grid[row - 1][col - 1];
    }
}
```
# Interview_48_最长不含重复字符的子字符串
## 题目
请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。

示例 1:
```
输入: "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
```
示例 2:
```
输入: "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
```
示例 3:
```
输入: "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
```
提示：
```
s.length <= 40000
```
## 解法
### 思路
遍历：
- 初始化一个map记录遍历到的字母和数组中坐标的映射关系
- 定义`max`记录暂存的最大值
- 定义`start`作为当前子串的起始坐标
- 遍历数组，记录字母与坐标关系
- 如果发现当前字母已经存在，并且这个重复的坐标在start之后(在start之前的值不再有效，需要在之后重置为当前坐标)：
    - 计算当前坐标与`start`之间的差作为当前子串的长度，在于`max`比较，取较大值暂存为`max`
    - 将之前发现的重复的字母的坐标作为新的`start`，同时将当前坐标覆盖这个字母的坐标
- 遍历结束后，再计算依次最后一个子串的长度并于`max`比较
- 返回`max`
### 代码
```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        char[] cs = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        int start = 0, max = 0;
        
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(cs[i]) && map.get(cs[i]) >= start) {
                max = Math.max(i - start, max);
                start = map.get(cs[i]) + 1;
            }
            map.put(cs[i], i);
        }
        
        return Math.max(max, s.length() - start);
    }
}
```
# Interview_49_丑数
## 题目
我们把只包含 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。

示例:
```
输入: n = 10
输出: 12
解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
```
说明:  
```
1 是丑数。
n 不超过1690。
```
## 解法
### 思路
堆：
- 将1放入堆中，并计数1
- 将1从堆中取出，并依次乘以`2,3,5`，放入堆中，同时计数
- 再从堆顶取出数，如上继续相乘后放入堆中，以此类推，直到计数达到要求值
- 注意溢出，需要使用long类型
- 注意类似`2 * 3 == 3 * 2`的情况，要使用set将重复乘积去除
### 代码
```java
class Solution {
    public int nthUglyNumber(int n) {
        PriorityQueue<Long> queue = new PriorityQueue<>();
        Set<Long> set = new HashSet<>();
        queue.offer(1L);
        set.add(1L);
        int size = 0;
        int[] arr = new int[]{2,3,5};

        while (!queue.isEmpty()) {
            long top = queue.poll();
            if (++size == n) {
                return (int)top;
            }
            for (int num : arr) {
                long multi = top * num;
                if (set.contains(multi)) {
                    continue;
                }

                queue.offer(multi);
                set.add(multi);
            }
        }
        
        return -1;
    }
}
```
## 解法二
### 思路
动态规划：
- dp[i]：当前第i个丑数的值
- 状态转移方程：`dp[i] = min(dp[i2], dp[i3], dp[i5])`
    - `i2,i3,i5`代表2，3，5作为因数，在目前的丑数集合中各自对应的最大的乘积的坐标
    - 这些坐标对应的值可以在后续的状态转移过程中作为因数与对应的值相乘，求得最小值放入丑数数组中，同时这个坐标也就移动了
    - 这些坐标可以同时指向同一个丑数，代表它们都是这个丑数的因数
- 初始化`dp[0] = 1`
- 返回结果`dp[n - 1]`
### 代码
```java
class Solution {
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int i = 0, i2 = 0, i3 = 0, i5 = 0;
        
        while (++i < n) {
            int min = Math.min(dp[i2] * 2, Math.min(dp[i3] * 3, dp[i5] * 5));
            dp[i] = min;
            if (min == dp[i2] * 2) {
                i2++;
            }

            if (min == dp[i3] * 3) {
                i3++;
            }

            if (min == dp[i5] * 5) {
                i5++;
            }
        }
        return dp[n - 1];
    }
}
```
# Interview_1001_合并排序的数组
## 题目
给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。

初始化 A 和 B 的元素数量分别为 m 和 n。

示例:
```
输入:
A = [1,2,3,0,0,0], m = 3
B = [2,5,6],       n = 3

输出: [1,2,2,3,5,6]
```
## 解法
### 思路
- 将B插入A的尾部
- 排序数组A
### 代码
```java
class Solution {
    public void merge(int[] A, int m, int[] B, int n) {
        for (int i = m; i < m + n; i++) {
            A[i] = B[i - m];
        }

        Arrays.sort(A);
    }
}
```
## 解法二
### 思路
- 初始化一个大小为`[m,n]`的数组`ans`
- 双指针`a`和`b`，分别代表两个数组的下标
- 开始循环，退出条件是两个指针同时越界
- 循环过程中：
    - 比较`a`数和`b`数大小，把较小数放入`ans`中，并向后移动`a`指针
    - 如果有一个指针越界，就把另一个指针指向的数组剩余元素放入`ans`中
- 将`ans`拷贝到数组A中
### 代码
```java
class Solution {
    public void merge(int[] A, int m, int[] B, int n) {
        int[] ans = new int[m + n];
        int a = 0, b = 0, index = 0;

        while (a < m || b < n) {
            if (a >= m) {
                while (b < n) {
                    ans[index++] = B[b++];
                }
                break;
            }

            if (b >= n) {
                while (a < m) {
                    ans[index++] = A[a++];
                }
                break;
            }

            if (A[a] <= B[b]) {
                ans[index++] = A[a++];
            } else {
                ans[index++] = B[b++];
            }
        }

        System.arraycopy(ans, 0, A, 0, ans.length);
    }
}
```
## 解法三
### 思路
逆向双指针：
- 从A数组的尾部第`m + n - 1`位置`index`开始从后往前填充元素，元素大小为从大到小
- 遍历数组A和数组B的尾部，从后往前，将较大数放在`index`处，并相应移动对应的指针
### 代码
```java
class Solution {
    public void merge(int[] A, int m, int[] B, int n) {
        int index = m + n - 1, a = m - 1, b = n - 1;
        while (a >= 0 || b >= 0) {
            if (a < 0) {
                while (b >= 0) {
                    A[index--] = B[b--];
                }
                break;
            } 
            
            if (b < 0) {
                while (a >= 0) {
                    A[index--] = A[a--];
                }
                break;
            }
            
            if (A[a] >= B[b]) {
                A[index--] = A[a--];
            } else {
                A[index--] = B[b--];
            }
        }
    }
}
```
# Interview_50_第一个只出现一次的字符
## 题目
在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。

示例:
```
s = "abaccdeff"
返回 "b"

s = "" 
返回 " "
```
限制：
```
0 <= s 的长度 <= 50000
```
## 解法
### 思路
- 初始化一个对不同字符计数的数组`bucket`，长度为256
- 初始化一个记录字符出现顺序的数组`cs`，长度为256
- 遍历字符数组，对出现的字符进行计数，并将计数为1的字符放入`cs`中进行排序
- 遍历`cs`并比对`bucket`，返回第一个`bucket`值为1的字符
### 代码
```java
class Solution {
    public char firstUniqChar(String s) {
        int[] bucket = new int[256];
        char[] cs = new char[256];
        int index = 0;

        for (char c : s.toCharArray()) {
            bucket[c]++;

            if (bucket[c] == 1) {
                cs[index++] = c;
            }
        }

        for (char c : cs) {
            if (bucket[c] == 1) {
                return c;
            }
        }
        
        return ' ';
    }
}
```
# Interview_51_数组中的逆序对
## 题目
在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。

示例 1:
```
输入: [7,5,6,4]
输出: 5
```
限制：
```
0 <= 数组长度 <= 50000
```
## 失败解法
### 思路
嵌套循环比较
### 代码
```java
class Solution {
    public int reversePairs(int[] nums) {
        int count = 0;
        
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    count++;
                }
            }
        }
        
        return count;
    }
}
```
## 解法
### 思路
分治，归并排序：
- 将数组进行归并排序
- 在排序过程中计算逆序对个数，针对3个部分进行计算：
    - 归并时分成的左半部分中的逆序对
    - 归并时分成的右半部分中的逆序对
    - 逆序的数分在左右两部分的逆序对
- 在分治过程中，数组的区间会被划分到极致的长度为1的情况，这时候返回的逆序对一定是0，也就是说这种情况下， 这个左右两部分的上一层是长度为2的数组，这个数组的左右部分的逆序对就是0，而这个数组总的逆序对就需要通过合并时计算的，分布在两部分的逆序对的个数来决定
- 合并的过程中，通过两个指针来比较左右两部分的元素大小来排序，如果发现左边的数大于右边的数，就通过中间`mid - left + 1`的个数来判断当前右边这个小的值一共对应多少个坐标大的值（左边部分已经是通过之前几层递归，排序过了的）
- 参数中务必要加一个tmp数组作为合并过程中的参考数组，并只复制合并区间的元素，否则如果有超长的数组，会导致拷贝的时间过长
### 代码
```java
class Solution {
    public int reversePairs(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return 0;
        }

        return divideAndConquer(nums, 0, len - 1, new int[nums.length]);
    }

    private int divideAndConquer(int[] nums, int left, int right, int[] tmp) {
        if (left == right) {
            return 0;
        }

        int mid = left + (right - left) / 2;
        int l = divideAndConquer(nums, left, mid, tmp);
        int r = divideAndConquer(nums, mid + 1, right, tmp);

        if (nums[mid] <= nums[mid + 1]) {
            return l + r;
        }

        return l + r + mergeSortAndCount(nums, left, mid, right, tmp);
    }


    private int mergeSortAndCount(int[] nums, int left, int mid, int right, int[] tmp) {
        if (right + 1 - left >= 0) {
            System.arraycopy(nums, left, tmp, left, right + 1 - left);
        }

        int l = left, r = mid + 1, ans = 0;
        for (int i = left; i <= right; i++) {
            if (l > mid) {
                nums[i] = tmp[r++];
            } else if (r > right) {
                nums[i] = tmp[l++];
            } else if (tmp[l] <= tmp[r]) {
                nums[i] = tmp[l++];
            } else {
                nums[i] = tmp[r++];
                ans += mid - l + 1;
            }
        }

        return ans;
    }
}
```
# Interview_52_两个链表的第一个公共节点
## 题目
输入两个链表，找出它们的第一个公共节点。

示例 1：
```
输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
输出：Reference of the node with value = 8
输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
```
示例 2：
```
输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
输出：Reference of the node with value = 2
输入解释：相交节点的值为 2 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
```
示例 3：
```
输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
输出：null
输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
解释：这两个链表不相交，因此返回 null。
```
注意：
```
如果两个链表没有交点，返回 null.
在返回结果后，两个链表仍须保持原有的结构。
可假定整个链表结构中没有循环。
程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
本题与主站 160 题相同：https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
```
## 解法
### 思路
- 遍历第一个链表，用set存储链表节点
- 遍历第二个链表，查看是否有相同的节点，如果有就返回，否则返回null
### 代码
```java
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        ListNode node = headA;
        while (node != null) {
            set.add(node);
            node = node.next;
        }
        
        node = headB;
        while (node != null) {
            if (set.contains(node)) {
                return node;
            }
            node = node.next;
        }
        
        return null;
    }
}
```
## 解法二
### 思路
双指针：
- 两个链表指针同时遍历
- 如果指针遍历到尾部节点，就重新从头节点开始遍历
- 如果两个节点相等就返回该节点
- 否则直到两个节点遍历到null，返回null
### 代码
```java
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA, b = headB;
        while (a != b) {
            a = a == null ? headA : a.next;
            b = b == null ? headB : b.next;
        }
        
        return a;
    }
}
```
## 解法三
### 思路
- 解法二的思路有些问题，指针不应该重复遍历本身链表
- 两个链表可以理解成两个有交集的集合，所以如果两个链表的指针走的是它们节点的并集次数，那么同样的节点次数就必然导致它们在第二次经过交叉点或尾部节点的时候相遇或同时到链表尾部
- 所以应该让a指针遍历完A链表后回到B链表的头开始遍历，b指针类似。
### 代码
```java
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
       ListNode a = headA, b = headB;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }

        return a;
    }
}
```
# Interview_53I_在排序数组中查找数字
## 题目
统计一个数字在排序数组中出现的次数。

示例 1:
```
输入: nums = [5,7,7,8,8,10], target = 8
输出: 2
```
示例 2:
```
输入: nums = [5,7,7,8,8,10], target = 6
输出: 0
```
限制：
```
0 <= 数组长度 <= 50000
```
## 解法
### 思路
- 二分查找找到元素
- 从找到的坐标位置前后查找相同元素的个数
### 代码
```java
class Solution {
    public int search(int[] nums, int target) {
        int head = 0, tail = nums.length - 1, index = -1, count = 0;
        
        while (head <= tail) {
            int mid = head + (tail - head) / 2;
            
            if (nums[mid] == target) {
                index = mid;
                count++;
                break;
            } else if (nums[mid] < target) {
                head = mid + 1;
            } else {
                tail = mid - 1;
            }
        }
        
        if (index == -1) {
            return 0;
        }
        
        for (int i = index - 1; i >= 0; i--) {
            if (nums[i] == target) {
                count++;
            } else {
                break;
            }
        }
        
        for (int i = index + 1; i < nums.length; i++) {
            if (nums[i] == target) {
                count++;
            } else {
                break;
            }
        }
        
        return count;
    }
}
```
# LeetCode_34_在排序数组中查找元素的第一个和最后一个位置
## 题目
给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。

你的算法时间复杂度必须是 O(log n) 级别。

如果数组中不存在目标值，返回 [-1, -1]。

示例 1:
```
输入: nums = [5,7,7,8,8,10], target = 8
输出: [3,4]
```
示例 2:
```
输入: nums = [5,7,7,8,8,10], target = 6
输出: [-1,-1]
```
## 解法
### 思路
思路和面试题53I相同，使用二分查找
### 代码
```java
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int head = 0, tail = nums.length - 1, index = -1;
        int[] ans = {-1, -1};

        while (head <= tail) {
            int mid = head + (tail - head) / 2;

            if (nums[mid] == target) {
                index = mid;
                break;
            } else if (nums[mid] < target) {
                head = mid + 1;
            } else {
                tail = mid - 1;
            }
        }

        if (index == -1) {
            return ans;
        }

        ans[0] = index;
        ans[1] = index;
        for (int i = index - 1; i >= 0; i--) {
            if (nums[i] == target) {
                ans[0] = i;
            } else {
                break;
            }
        }

        for (int i = index + 1; i < nums.length; i++) {
            if (nums[i] == target) {
                ans[1] = i;
            } else {
                break;
            }
        }

        return ans;
    }
}
```
# Interview_53II_0~n-1中缺失的数字
## 题目
一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。

示例 1:
```
输入: [0,1,3]
输出: 2
```
示例 2:
```
输入: [0,1,2,3,4,5,6,7,9]
输出: 8
```
## 解法
### 思路
双指针：
- 头尾指针同时向中间位置移动，循环条件是`head < tail`，这种条件会导致如果题目的数组是奇数个，`len / 2`坐标的值不会被遍历到
- 因为头尾值的和是数组长度len，如果遍历到的两个元素不相等且头尾值不等于len，说明找到了缺失的元素
    - 如果小于len，则缺失`len - 左侧小值`
    - 如果大于len，则缺失`len - 右侧大值`
 - 如果循环结束，没有找到缺失值：
    - 如果len是偶数，那原来长度是奇数，没找到的值就是中间值
    - 如果len是奇数，那原来长度是偶数，没找到的值就是`len - nums[len / 2]`，也就是长度和最后一个没有遍历到的数的差
### 代码
```java
class Solution {
    public int missingNumber(int[] nums) {
        int len = nums.length, head = 0, tail = len - 1;
        
        while (head < tail) {
            if (nums[head] + nums[tail] < len) {
                return len - nums[head];
            } else if(nums[head] + nums[tail] > len) {
                return len - nums[tail];
            }
            head++;
            tail--;
        }
        
        return (len & 1) == 0 ? len / 2 : len - nums[len / 2];
    }
}
```
# Interview_54_二叉搜索树的第k大节点
## 题目
给定一棵二叉搜索树，请找出其中第k大的节点。

示例 1:
```
输入: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
输出: 4
```
示例 2:
```
输入: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
输出: 4
```
限制：
```
1 ≤ k ≤ 二叉搜索树元素个数

通过次数3,391提交次数4,746
```
## 解法
### 思路
dfs
- 中序遍历生成升序序列
- 遍历序列，找到第k大节点
### 代码
```java
class Solution {
    public int kthLargest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        return list.get(list.size() - k);
    }
    
    private void dfs(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        
        dfs(node.left, list);
        list.add(node.val);
        dfs(node.right, list);
    }
}
```
# Interview_55I_二叉树的深度
## 题目
输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。

例如：
```
给定二叉树 [3,9,20,null,null,15,7]，

    3
   / \
  9  20
    /  \
   15   7
返回它的最大深度 3 。
```
提示：
```
节点总数 <= 10000
```
## 解法
### 思路
dfs
### 代码
```java
class Solution {
    public int maxDepth(TreeNode root) {
        return root == null ? 0 : 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
```
# Interview_57II_和为s的连续正数序列
## 题目
输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。

序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。

示例 1：
```
输入：target = 9
输出：[[2,3,4],[4,5]]
```
示例 2：
```
输入：target = 15
输出：[[1,2,3,4,5],[4,5,6],[7,8]]
```
限制：
```
1 <= target <= 10^5
```
## 解法
### 思路
嵌套循环
- 外层确定可能序列的起始元素
- 内层从外层元素开始遍历并累加，如果超过target就终止，否则就加入结果list中
### 代码
```java
class Solution {
    public int[][] findContinuousSequence(int target) {
        List<int[]> ansList = new ArrayList<>();
        for (int i = 1; i < target; i++) {
            int sum = 0;
            boolean find = false;
            List<Integer> list = new ArrayList<>();
            for (int j = i; j < target; j++) {
                sum += j;
                if (sum > target) {
                    break;
                }
                list.add(j);
                if (sum == target) {
                    find = true;
                    break;
                }
            }
            
            if (find) {
                int[] arr = new int[list.size()];
                int index = 0;
                for (int num : list) {
                    arr[index++] = num;
                }
                ansList.add(arr);
            }
        }
        
        return ansList.toArray(new int[0][0]);
    }
}
```
## 解法二
### 思路
- 等差数列求和公式
```math
target = a1 * n + \frac{n * (n - 1)}{2}
a1 = \frac{target - \frac{n * (n - 1)}{2}}{n}
```
- 循环遍历求长度n，从2到target + 1
- 根据当前n求得首项
- 根据首项和长度确定序列并放入结果中
- 注意结果需要根据首个元素排序，所以还要在返回接过钱做一次排序
### 代码
```java
class Solution {
    public int[][] findContinuousSequence(int target) {
        List<int[]> ansList = new ArrayList<>();
        for (int n = 2; n <= target; n++) {
            int xn = target - n * (n - 1) / 2;
            if (xn <= 0) {
                break;
            }

            if (xn % n == 0) {
                int x = xn / n;
                int[] arr = new int[n];
                for (int i = 0; i < n; i++) {
                    arr[i] = x + i;
                }
                ansList.add(arr);
            }
        }
        ansList.sort(Comparator.comparingInt(x -> x[0]));
        return ansList.toArray(new int[0][0]);
    }
}
```
## 解法三
### 思路
双指针
- 初始化头尾指针`head`和`tail`
- 因为结果是以序列的首字母排序，所以头指针在确定无法找到结果的时候再移动
- 整个判断过程中使用等差数列求和公式得到`sum`值，而根据`sum`与`target`之间的关系可以分为3种情况
    1. `sum < target`，说明序列不够大，`tail`继续增加
    2. `sum > target`，这个起始为`head`的序列无法找到匹配`target`的`sum`，向右移动`head`
    3. `sum == target`，将序列放入结果中，且因为再移动`tail`也没法获得新的序列，所以同时移动`head`和`tail`
### 代码
```java
class Solution {
    public int[][] findContinuousSequence(int target) {
        int head = 1, tail = 2, sum = 3;
        List<int[]> ansList = new ArrayList<>();

        while (head < target - 1 && tail < target) {
            if (sum == target) {
                int[] arr = new int[tail - head + 1];
                for (int i = 0; i < tail - head + 1; i++) {
                    arr[i] = head + i;
                }
                ansList.add(arr);
                sum -= head++;
            } else if (sum < target) {
                sum += ++tail;
            } else {
                sum -= head++;
            }
        }
        
        return ansList.toArray(new int[0][0]);
    }
}
```
# Interview_55II_平衡二叉树
## 题目
输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。

示例 1:
```
给定二叉树 [3,9,20,null,null,15,7]

    3
   / \
  9  20
    /  \
   15   7
返回 true 。
```
示例 2:
```
给定二叉树 [1,2,2,3,3,null,null,4,4]

       1
      / \
     2   2
    / \
   3   3
  / \
 4   4
返回 false 。
```
限制：
```
1 <= 树的结点个数 <= 10000
```
## 解法
### 思路
dfs
### 代码
```java
class Solution { 
    public boolean isBalanced(TreeNode root) {
        return dfs(root) >= 0;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }

        if (node.left == null && node.right == null) {
            return 1;
        }

        int left = dfs(node.left),
            right = dfs(node.right);

        if (left < 0 || right < 0) {
            return -1;
        }
        
        if (Math.abs(left - right) > 1) {
            return -1;
        }
        
        return Math.max(left, right) + 1;
    }
}
```
# Interview_56I_数组中数字出现的次数
## 题目
一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。

示例 1：
```
输入：nums = [4,1,4,6]
输出：[1,6] 或 [6,1]
```
示例 2：
```
输入：nums = [1,2,10,4,1,4,3,3]
输出：[2,10] 或 [10,2]
```
限制：
```
2 <= nums <= 10000
```
## 解法
### 思路
- 将所有值遍历并异或，获得的值就是连个不同数异或后的值`xor`
- 因为异或是值，将两个数在同一位上相同的值变为0，不同的值变为1，所以只需找到`xor`最低位的1，就知道这两个数至少这一位是不同的
- 然后再遍历一次数组，将这一位相同的值放在一起异或，这一位不同的值放在一起异或，这样分别求得了不同的两个数
### 代码
```java
class Solution {
    public int[] singleNumbers(int[] nums) {
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        
        int bit = 1;
        while ((bit & xor) == 0) {
            bit <<= 1;
        }
        
        int a = 0, b = 0;
        for (int num : nums) {
            if ((bit & num) == 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }
        
        return new int[]{a, b};
    }
}
```
# Interview_59II_队列的最大值
## 题目
请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的时间复杂度都是O(1)。

若队列为空，pop_front 和 max_value 需要返回 -1

示例 1：
```
输入: 
["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
[[],[1],[2],[],[],[]]
输出: [null,null,null,2,1,2]
```
示例 2：
```
输入: 
["MaxQueue","pop_front","max_value"]
[[],[],[]]
输出: [null,-1,-1]
```
限制：
```
1 <= push_back,pop_front,max_value的总操作数 <= 10000
1 <= value <= 10^5
```
## 解法
### 思路
- 使用两个队列：
    - 一个队列`queue`操作出入队列
    - 一个双端队列`max`单调递减的保存最大值
- max_value：返回`max`队列头部的元素
- push_back：从`max`尾部开始判断，将`max`中比推入的元素更小的元素全部出队，因为这些都是之前的元素在入队时记录下来的最大值，这些值在以后出队的过程中都不再影响最大值的变更
- pop_front：将`queue`的队列头的元素推出，同时查看推出的元素是否与`max`队列头的元素大小一致，如果一致，这个元素也被推出
### 代码
```java
class MaxQueue {
    private Queue<Integer> queue;
    private Deque<Integer> max;
    public MaxQueue() {
        this.queue = new ArrayDeque<>();
        this.max = new ArrayDeque<>();
    }

    public int max_value() {
        return max.isEmpty() ? -1 : max.peek();
    }

    public void push_back(int value) {
        queue.offer(value);

        while (!max.isEmpty() && value > max.getLast()) {
            max.pollLast();
        }
        max.offer(value);
    }

    public int pop_front() {
        if (queue.isEmpty()) {
            return -1;
        }

        int value = queue.poll();
        if (value == max.peek()) {
            max.poll();
        }

        return value;
    }
}
```
# Interview_56II_数组中数字出现的次数II
## 题目
在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。

示例 1：
```
输入：nums = [3,4,3,3]
输出：4
```
示例 2：
```
输入：nums = [9,1,7,9,7,9,7]
输出：1
```
限制：
```
1 <= nums.length <= 10000
1 <= nums[i] < 2^31
```
## 解法
### 思路
嵌套循环：
- 外层循环32位
- 内层遍历数组的所有元素
- 计算当前位有多少个1，如果得到的计数值不能被3整除，说明出现唯一一次的值在这一位是1
- 将这一位或在结果上
### 代码
```java
class Solution {
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int bit = 1 << i, count = 0;
            for (int num : nums) {
                if ((num & bit) != 0) {
                    count++;
                }
            }
            
            
            if (count % 3 != 0) {
                ans |= bit;
            }
        }
        
        return ans;
    }
}
```
## 解法二
### 思路
//todo 未知解法
### 代码
```java
class Solution {
    public int singleNumber(int[] nums) {
        int ans = 0, sup = 0;
        for (int num : nums) {
            ans = (ans ^ num) & ~sup;
            sup = (sup ^ num) & ~ans;
        }
        return ans;
    }
}
```
# Interview_57_和为s的两个数字
## 题目
输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。

示例 1：
```
输入：nums = [2,7,11,15], target = 9
输出：[2,7] 或者 [7,2]
```
示例 2：
```
输入：nums = [10,26,30,31,47,60], target = 40
输出：[10,30] 或者 [30,10]
```
限制：
```
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^6
```
## 解法
### 思路
双指针：
- `sum > target`：因为单调递增，需要向前移动`tail`指针，缩小sum
- `sum < target`：需要向后移动`head`，增大`sum`
### 代码
```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int head = 0, tail = nums.length - 1;
        int[] ans = new int[2];
        while (head < tail) {
            int sum = nums[head] + nums[tail];
            if (sum < target) {
                head++;
            } else if (sum > target) {
                tail--;
            } else {
                ans[0] = nums[head];
                ans[1] = nums[tail];
                break;
            }
        }
        
        return ans;
    }
}
```
# LeetCode_322_零钱兑换
## 题目
给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。

示例 1:
```
输入: coins = [1, 2, 5], amount = 11
输出: 3 
解释: 11 = 5 + 5 + 1
```
示例 2:
```
输入: coins = [2], amount = 3
输出: -1
说明:
你可以认为每种硬币的数量是无限的。
```
## 解法
### 思路
dfs+记忆化搜索：
- 退出条件：
    - 金额值小于0，说明当前路径不能获得答案，返回-1
    - 金额值为0，说明当前路径可以获得答案，答案从上一层开始计算，返回0
    - 记忆搜索中如果存在当前金额，直接返回
- 递归逻辑：
    - 遍历所有硬币面值，递归获得当前面值减去遍历到的硬币值计算到的硬币数`count`
    - 如果`count != -1`，将`count + 1`与暂存的最小值比较，取最小值
    - 所有硬币面值遍历完后：
        - 记录当前变化过的最小值
        - 返回最小值 + 1
### 代码
```java
class Solution {
    public int coinChange(int[] coins, int amount) {
        if (amount < 1) {
            return 0;
        }

        return dfs(coins, amount, new int[amount + 1]);
    }

    private int dfs(int[] coins, int amount, int[] mem) {
        if (amount < 0) {
            return -1;
        }

        if (amount == 0) {
            return 0;
        }

        if (mem[amount] != 0) {
            return mem[amount];
        }
        
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int count = dfs(coins, amount - coin, mem);
            if (count != -1) {
                min = Math.min(count + 1, min);
            }
        }
        
        mem[amount] = min == Integer.MAX_VALUE ? -1 : min;
        return mem[amount];
    }
}
```
## 解法二
### 思路
动态规划：
- `dp[i]`：金额为i时的硬币数量最小值
- 状态转移方程：`dp[i] = dp[i - coin] + 1`
- 初始化：
    - dp数组填充为`amount + 1`
    - `dp[0] = 0`
- 返回：看`dp[amount]`是否是`amount + 1`，如果是说明没有找到正确的组合，返回-1，否则就返回`dp[amount]`
### 代码
```java
class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }
}
```