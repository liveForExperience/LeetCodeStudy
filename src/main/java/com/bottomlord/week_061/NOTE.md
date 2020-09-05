# LeetCode_249_移位字符串数组
## 题目
给定一个字符串，对该字符串可以进行 “移位” 的操作，也就是将字符串中每个字母都变为其在字母表中后续的字母，比如："abc" -> "bcd"。这样，我们可以持续进行 “移位” 操作，从而生成如下移位序列：
```
"abc" -> "bcd" -> ... -> "xyz"
给定一个包含仅小写字母字符串的列表，将该列表中所有满足 “移位” 操作规律的组合进行分组并返回。
```
示例：
```
输入：["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"]
输出：
[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]
解释：可以认为字母表首尾相接，所以 'z' 的后续为 'a'，所以 ["az","ba"] 也满足 “移位” 操作规律。
```
## 解法
### 思路
三层循环+记忆化搜索
- 外层循环确定起始字符串
- 中层循环寻找移位的字符串
- 内层比较每一位字符的`(字符差值 + 26) % 26`是否相等
- 在每次开始寻找和确定找到的时候，将字符串放入记忆化布尔数组中标志
### 代码
```java
class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        int len = strings.length;
        boolean[] memo = new boolean[len];
        List<List<String>> ans = new ArrayList<>();
        
        for (int i = 0; i < len; i++) {
            if (memo[i]) {
                continue;
            }
            
            memo[i] = true;
            
            String s1 = strings[i];
            List<String> list = new ArrayList<String>(){{this.add(s1);}};
            
            for (int j = i + 1; j < len; j++) {
                String s2 = strings[j];
                if (memo[j] || s1.length() != s2.length()) {
                    continue;
                }
                
                int dis = (s1.charAt(0) - s2.charAt(0) + 26) % 26;
                boolean flag = true;
                for (int k = 1; k < s1.length(); k++) {
                    if ((s1.charAt(k) - s2.charAt(k) + 26) % 26 != dis) {
                        flag = false;
                        break;
                    }
                }
                
                if (flag) {
                    list.add(s2);
                    memo[j] = true;
                }
            }
            
            ans.add(list);
        }
        
        return ans;
    }
}
```
## 解法二
### 思路
map：
- 遍历字符串，将所有字符串都对齐成起始字符为`a`的状态
- 将转移后相同的字符串放在一个key后的list中
- 遍历结束后，返回所有的集合
### 代码
```java
class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strings) {
            String shift = shift(str);
            List<String> list = map.getOrDefault(shift, new ArrayList<>());
            list.add(str);
            map.put(shift, list);
        }

        return new ArrayList<>(map.values());
    }

    private String shift(String str) {
        int bit = str.charAt(0) - 'a';
        char[] cs = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            cs[i] = cs[i] - (char)bit - 'a' >= 0 ? (char)(cs[i] - (char)bit) : (char)(cs[i] - (char)(bit - 26));
        }
        return new String(cs);
    }
}
```
# LeetCode_250_统计共值子树
## 题目
给定一个二叉树，统计该二叉树数值相同的子树个数。

同值子树是指该子树的所有节点都拥有相同的数值。

示例：
```
输入: root = [5,1,5,5,5,null,5]

              5
             / \
            1   5
           / \   \
          5   5   5

输出: 4
```
## 解法
### 思路
dfs
### 代码
```java
class Solution {
    private int count = 0;
    
    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        dfs(root);
        return count;
    }
    
    private boolean dfs(TreeNode node) {
        if (node.left == null && node.right == null) {
            count++;
            return true;
        }
        
        boolean same = true;
        if (node.left != null) {
            same = dfs(node.left) && node.val == node.left.val;
        }
        
        if (node.right != null) {
            same = dfs(node.right) && same && node.val == node.right.val;
        }
        
        if (!same) {
            return false;
        }
        
        count++;
        return true;
    }
}
```
## 解法二
### 思路
dfs：
- 退出条件：当前节点为null，返回true
- 在递归处理的这一层，如果下层返回的都是true，那么就累加count值，说明当前节点的值与左右子节点的值相等
- 如果下层有任一返回false或者当前节点与上层节点不等，就返回false
- 注意判断下层左右节点是否与当前节点是否相等的条件语句，如果用或来组合在一起，那就必须时非短路的或，否则会导致递归搜索不完整
### 代码
```java
class Solution {
    private int count = 0; 
    public int countUnivalSubtrees(TreeNode root) {
        dfs(root, null);
        return count;
    }
    
    private boolean dfs(TreeNode node, Integer preVal) {
        if (node == null) {
            return true;
        }
        
        if (!dfs(node.left, node.val) | !dfs(node.right, node.val)) {
            return false;
        }
        
        count++;
        
        return Objects.equals(node.val, preVal);
    }
}
```
# LeetCode_251_展开二维向量
## 题目
请设计并实现一个能够展开二维向量的迭代器。该迭代器需要支持 next 和 hasNext 两种操作。、

示例：
```
Vector2D iterator = new Vector2D([[1,2],[3],[4]]);

iterator.next(); // 返回 1
iterator.next(); // 返回 2
iterator.next(); // 返回 3
iterator.hasNext(); // 返回 true
iterator.hasNext(); // 返回 true
iterator.next(); // 返回 4
iterator.hasNext(); // 返回 false
```
注意：
```
请记得 重置 在 Vector2D 中声明的类变量（静态变量），因为类变量会 在多个测试用例中保持不变，影响判题准确。请 查阅 这里。
你可以假定 next() 的调用总是合法的，即当 next() 被调用时，二维向量总是存在至少一个后续元素。
```
进阶：
```
尝试在代码中仅使用 C++ 提供的迭代器 或 Java 提供的迭代器。
```
## 解法
### 思路
- 遍历数组放入list
- 使用jdk中的迭代器api
### 代码
```java
class Vector2D {
    private List<Integer> list;
    private ListIterator<Integer> iterator;
    public Vector2D(int[][] v) {
        this.list = new ArrayList<>();
        for (int[] arr : v) {
            for (int num : arr) {
                this.list.add(num);
            }
        }
        this.iterator = this.list.listIterator();
    }

    public int next() {
        return iterator.next();
    }

    public boolean hasNext() {
        return iterator.hasNext();
    }
}
```
## 解法二
### 思路
双指针：
- 维护行列指针
- `next()`：
    - 使用`hasNext()`做判断，如果没有下一个，同时也是利用`hasNext()`对列坐标做矫正，抛出异常
    - 返回当前行列对应的元素
    - 移动列指针
- `hasNext()`：
    - 先做指针的矫正，也就是当列指针到达行尾的时候，需要换行
    - 因为列已经做了维护，所以只要判断行是否越界就可以
### 代码
```java
import java.util.NoSuchElementException;
class Vector2D {
    private int[][] v;
    private int r, c;
    public Vector2D(int[][] v) {
        this.v = v;
        this.r = 0;
        this.c = 0;
    }

    public int next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return v[r][c++];
    }

    public boolean hasNext() {
        while (r < v.length && c == v[r].length) {
            c = 0;
            r++;
        }

        return r < v.length;
    }
}
```
## 解法三
### 思路
使用队列
### 代码
```java
class Vector2D {
    private Queue<Integer> queue;
    public Vector2D(int[][] v) {
        this.queue = new ArrayDeque<>();
        for (int[] arr : v) {
            for (int num : arr) {
                this.queue.offer(num);
            }
        }
    }

    public int next() {
        return this.queue.poll();
    }

    public boolean hasNext() {
        return !this.queue.isEmpty();
    }
}
```
# LeetCode_252_会议室
## 题目
给定一个会议时间安排的数组，每个会议时间都会包括开始和结束的时间 [[s1,e1],[s2,e2],...] (si < ei)，请你判断一个人是否能够参加这里面的全部会议。

示例 1:
```
输入: [[0,30],[5,10],[15,20]]
输出: false
```
示例 2:
```
输入: [[7,10],[2,4]]
输出: true
```
## 解法
### 思路
排序
- 针对二维数组中每个数组元素的第一个值作升序排序
- 遍历二维数组，若前一个数组的第二个元素大于后一个数组的第一个元素，返回false
### 代码
```java
class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(x -> x[0]));
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i][1] > intervals[i + 1][0]) {
                return false;
            }
        }
        
        return true;
    }
}
```
## 解法二
### 思路
使用快排排序
### 代码
```java
class Solution {
        public boolean canAttendMeetings(int[][] intervals) {
        quickSort(intervals, 0, intervals.length - 1);
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i][1] > intervals[i + 1][0]) {
                return false;
            }
        }
        return true;
    }

    private void quickSort(int[][] intervals, int head, int tail) {
        if (head >= tail) {
            return;
        }

        int partition = partition(intervals, head, tail);

        quickSort(intervals, head, partition - 1);
        quickSort(intervals, partition + 1, tail);
    }

    private int partition(int[][] intervals, int head, int tail) {
        int[] pivot = intervals[head];
        while (head < tail) {
            while (head < tail && intervals[tail][0] >= pivot[0]) {
                tail--;
            }
            intervals[head] = intervals[tail];

            while (head < tail && intervals[head][0] <= pivot[0]) {
                head++;
            }
            intervals[tail] = intervals[head];
        }

        intervals[head] = pivot;
        return head;
    }
}
```
# LeetCode_253_会议室II
## 题目
给定一个会议时间安排的数组，每个会议时间都会包括开始和结束的时间 [[s1,e1],[s2,e2],...] (si < ei)，为避免会议冲突，同时要考虑充分利用会议室资源，请你计算至少需要多少间会议室，才能满足这些会议安排。

示例 1:
```
输入: [[0, 30],[5, 10],[15, 20]]
输出: 2
```
示例 2:
```
输入: [[7,10],[2,4]]
输出: 1
```
## 解法
### 思路
- 根据数组元素的第一个值进行升序排序，如果第一个值相同就以第二个值升序
- 生成与二维数组长度相同的布尔数组`memo`，作为记忆化搜索的记录表
- 开始循环，退出条件是`memo`的所有元素都为true
- 循环体内：
    - 初始化一个起始区间，`{-1,-1}`作为每次判断的上一个区间
    - 会议室格式自增1
    - 从窗口的第一个元素开始判断
        - 如果当前坐标的`memo`值为true，跳过
        - 如果当前区间的起始值比上一个窗口的结束值小，跳过
        - 如果如上两种情况都不是，说明当前这个会议室的当前区间可连续，翻转`memo[i]`
        - 将当前窗口作为下一次循环的上个窗口
- 最终返回会议室个数
### 代码
```java
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        int len = intervals.length;
        if (len == 0) {
            return 0;
        }

        Arrays.sort(intervals, (x1, x2) -> {
            if (x1[0] == x2[0]) {
                return Integer.compare(x1[1], x2[1]);
            }
            
            return Integer.compare(x1[0], x2[0]);
        });
        
        boolean[] memo = new boolean[len];
        int ans = 0;
        
        while (!allDone(memo)) {
            ans++;
            int[] pre = {-1,-1};
            for (int i = 0; i < len; i++) {
                if (memo[i] || intervals[i][0] < pre[1]) {
                    continue;
                }
                
                memo[i] = true;
                pre = intervals[i];
            }
        }
        
        return ans;
    }

    private boolean allDone(boolean[] memo) {
        for (boolean m : memo) {
            if (!m) {
                return false;
            }
        }

        return true;
    }
}
```
## 解法二
### 思路
- 如果站在会议室使用者的角度，当发现当前会议室被占用后，就会另找一个会议室
- 基于这点，先将会议室窗口集合按照起始时间排序，放入优先级队列
- 使用一个list用于暂存会议室最新的窗口
- 然后遍历优先级队列，在发现如下情况时新增会议室
    - list为空
    - 没有窗口能对接当前队列中弹出的窗口
- 队列为空后，返回list的长度
### 代码
```java
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparing(x -> x[0]));
        for (int[] interval : intervals) {
            queue.offer(interval);
        }
        
        List<int[]> list = new LinkedList<>();
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            
            if (list.isEmpty()) {
                list.add(cur);
                continue;
            }

            boolean flag = false;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i)[1] <= cur[0]) {
                    list.set(i, cur);
                    flag = true;
                    break;
                }
            }
            
            if (!flag) {
                list.add(cur);
            }
        }
        
        return list.size();
    }
}
```
## 解法三
### 思路
小顶堆（优化解法二）：
- 从使用者的角度，只会观察所有会议室的结束时间，因为要最小化会议室个数，那就从目前开启的会议室中开始找可以接上使用的，只有到没有的时候再新加会议室
- 对窗口数组基于起始时间排序
- 初始化一个小顶堆
- 遍历窗口数组：
- 如下情况下，直接压入结束时间，代表新开一个会议室
    - 如果堆为空
    - 如果堆顶元素大于当前起始时间
- 如果堆顶元素比当前窗口的起始时间小或者等于，弹出堆顶元素，压入当前窗口的结束时间，代表使用该会议室
- 遍历窗口元素结束后，返回堆的个数
### 代码
```java
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparing(x -> x[0]));
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int[] interval : intervals) {
            if (queue.isEmpty() || queue.peek() <= interval[0]) {
                queue.poll();
            }
            queue.offer(interval[1]);
        }
        
        return queue.size();
    }
}
```
# LeetCode_254_因子的组合
## 题目
整数可以被看作是其因子的乘积。

例如：
```
8 = 2 x 2 x 2;
  = 2 x 4.
请实现一个函数，该函数接收一个整数 n 并返回该整数所有的因子组合。
```
注意：
```
你可以假定 n 为永远为正数。
因子必须大于 1 并且小于 n。
```
示例 1：
```
输入: 1
输出: []
```
示例 2：
```
输入: 37
输出: []
```
示例 3：
```
输入: 12
输出:
[
  [2, 6],
  [2, 2, 3],
  [3, 4]
]
```
示例 4:
```
输入: 32
输出:
[
  [2, 16],
  [2, 2, 8],
  [2, 2, 2, 4],
  [2, 2, 2, 2, 2],
  [2, 4, 4],
  [4, 8]
]
```
## 解法
### 思路
dfs
### 代码
```java
class Solution {
    public List<List<Integer>> getFactors(int n) {
        return dfs(2, n);
    }

    private List<List<Integer>> dfs(int factor, int num) {
        if (num <= 1) {
            return new ArrayList<>();
        }
        
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = factor; i * i <= num; i++) {
            if (num % i == 0) {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                list.add(num / i);
                ans.add(list);
                List<List<Integer>> next = dfs(i, num / i);
                
                for (List<Integer> nextList : next) {
                    nextList.add(i);
                    ans.add(nextList);
                }
            }
        }
        
        return ans;
    }
}
```