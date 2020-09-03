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