# LeetCode_452_最少数量的箭引爆气球
## 题目
在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。由于它是水平的，所以y坐标并不重要，因此只要知道开始和结束的x坐标就足够了。开始坐标总是小于结束坐标。平面内最多存在104个气球。

一支弓箭可以沿着x轴从不同点完全垂直地射出。在坐标x处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足 xstart≤ x ≤ xend，则该气球会被引爆。可以射出的弓箭的数量没有限制。 弓箭一旦被射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。

Example:
```
输入:
[[10,16], [2,8], [1,6], [7,12]]

输出:
2

解释:
对于该样例，我们可以在x = 6（射爆[2,8],[1,6]两个气球）和 x = 11（射爆另外两个气球）。
```
## 解法
### 思路
贪心算法：一般用来解决“找到要做某事的最小数量” 或 “找到在某些情况下适合的最大物品数量” 的问题，且提供的是无序的输入。
- 如果观察一个气球的结束位置是n，那么剩下的气球就有两种情况
    - 起始位置小于等于`n`，可以和这个气球一起引爆
    - 起始位置大于`n`，不可以和这个气球一起引爆
- 所以算法的核心聚焦于两个相邻气球在x轴上的结束坐标和开始坐标，如果后一个气球的起始位置大于前一个气球的结束位置，那么就意味着要多一根箭
### 代码
```java
class Solution {
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;    
        }   
        
        Arrays.sort(points, Comparator.comparingInt(x -> x[1]));
        int start, end, preEnd = points[0][1], ans = 1;
        for (int[] point : points) {
            start = point[0];
            end = point[1];
            
            if (start > preEnd) {
                ans++;
                preEnd = end;
            }
        }
        return ans;
    }
}
```
# LeetCode_592_分数加减运算
## 题目
给定一个表示分数加减运算表达式的字符串，你需要返回一个字符串形式的计算结果。这个结果应该是不可约分的分数，即最简分数。如果最终结果是一个整数，例如2，你需要将它转换成分数形式，其分母为1。所以在上述例子中, 2应该被转换为2/1。

示例1:
```
输入:"-1/2+1/2"
输出: "0/1"
```
示例 2:
```
输入:"-1/2+1/2+1/3"
输出: "1/3"
```
示例 3:
```
输入:"1/3-1/2"
输出: "-1/6"
```
示例 4:
```
输入:"5/3+1/3"
输出: "2/1"
```
说明:
```
输入和输出字符串只包含'0' 到'9'的数字，以及'/', '+' 和'-'。
输入和输出分数格式均为±分子/分母。如果输入的第一个分数或者输出的分数是正数，则'+'会被省略掉。
输入只包含合法的最简分数，每个分数的分子与分母的范围是[1,10]。如果分母是1，意味着这个分数实际上是一个整数。
输入的分数个数范围是 [1,10]。
最终结果的分子与分母保证是 32 位整数范围内的有效整数。
```
## 解法
### 思路
- 遍历字符串，将`+`、`-`符号按顺序放入存放符号的数组`operators`
- 根据`+`、`-`符号拆分字符串
- 再用`/`拆分字符串，将拆分出的两部分分别放入两个数组中，代表分子和分母
- 然后判断`operators`的第一个元素是否是`-`，如果是，那就代表分子的第一个元素是负数，变更该元素为负数
- 遍历存放分母的数组，从头两个元素开始，两两计算得出公倍数，并用当前公倍数继续和后一个元素两两求公倍数，直到元素遍历结束
- 然后遍历分子数组，通过乘以分子对应的分母与公倍数相除的结果来得到通分后的值，然后累加
- 最后将求得的分子和分母求最大公约数，然后通过约分，得到最后的值
### 代码
```java
class Solution {
    public String fractionAddition(String expression) {
        List<Character> operators = new ArrayList<>();
        for (int i = 1; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '+' || c == '-') {
                operators.add(c);
            }
        }

        List<Integer> numerators = new ArrayList<>();
        List<Integer> denominators = new ArrayList<>();
        for (String a : expression.split("\\+")) {
            for (String b : a.split("-")) {
                if (b.length() > 0) {
                    String[] strs = b.split("/");
                    numerators.add(Integer.parseInt(strs[0]));
                    denominators.add(Integer.parseInt(strs[1]));
                }
            }
        }

        if (expression.charAt(0) == '-') {
            numerators.set(0, -numerators.get(0));
        }

        int lcm = 1;
        for (int denominator : denominators) {
            lcm = lcm(denominator, lcm);
        }

        int numerator = lcm / denominators.get(0) * numerators.get(0);
        for (int i = 1; i < numerators.size(); i++) {
            if (operators.get(i - 1) == '+') {
                numerator += lcm / denominators.get(i) * numerators.get(i);
            } else {
                numerator -= lcm / denominators.get(i) * numerators.get(i);
            }
        }

        int gcd = gcd(Math.abs(numerator), Math.abs(lcm));

        return (numerator / gcd) + "/" + (lcm / gcd);
    }

    private int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```
## 解法二
### 思路
在解法一的基础上，逐个通分分数，规避如果分母过大，求最小公倍数导致分母数值溢出的问题
### 代码
```java
class Solution {
    public String fractionAddition(String expression) {
        List<Character> operators = new ArrayList<>();
        if (expression.charAt(0) != '-') {
            operators.add('+');
        }

        for (char c : expression.toCharArray()) {
            if (c == '+' || c == '-') {
                operators.add(c);
            }
        }

        int preNumerator = 0, preDenominator = 1, index = 0;
        for (String str : expression.split("(\\+)|(-)")) {
            if (str.length() > 0) {
                String[] factorials = str.split("/");
                int numerator = Integer.parseInt(factorials[0]);
                int denominator = Integer.parseInt(factorials[1]);

                int gcd = Math.abs(gcd(preDenominator, denominator));
                if (operators.get(index++) == '+') {
                    preNumerator = preNumerator * denominator / gcd + numerator * preDenominator / gcd;
                } else {
                    preNumerator = preNumerator * denominator / gcd - numerator * preDenominator / gcd;
                }

                preDenominator = preDenominator * denominator / gcd;

                gcd = Math.abs(gcd(preNumerator, preDenominator));
                preNumerator /= gcd;
                preDenominator /= gcd;
            }
        }

        return preNumerator + "/" + preDenominator;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```
# LeetCode_869_重新排序得到2的幂
## 题目
给定正整数 N，我们按任何顺序（包括原始顺序）将数字重新排序，注意其前导数字不能为零。

如果我们可以通过上述方式得到2 的幂，返回 true；否则，返回 false。

示例 1：
```
输入：1
输出：true
```
示例 2：
```
输入：10
输出：false
```
示例 3：
```
输入：16
输出：true
```
示例 4：
```
输入：24
输出：false
```
示例 5：
```
输入：46
输出：true
```
提示：
```
1 <= N <= 10^9
```
## 解法
### 思路
- 通过回溯来和swap来遍历遍历全量的排列
- 判断所有对应排列是否为2的幂
### 代码
```java
class Solution {
    public boolean reorderedPowerOf2(int N) {
        int b = 0, num = N, index = 0;
        while (num > 0) {
            num /= 10;
            b++;
        }

        int[] arr = new int[b];
        while (N > 0) {
            arr[index++] = N % 10;
            N /= 10;
        }

        return backTrace(arr, 0);
    }

    private boolean isPowerOf2(int[] arr) {
        if (arr[0] == 0) {
            return false;
        }

        int num = 0;
        for (int n : arr) {
            num = num * 10 + n;
        }

        while (num > 0 && (num & 1) == 0) {
            num >>= 1;
        }

        return num == 1;
    }

    private boolean backTrace(int[] arr, int start) {
        if (start == arr.length) {
            return isPowerOf2(arr);
        }

        for (int i = start; i < arr.length; i++) {
            swap(arr, start, i);
            if (backTrace(arr, start + 1)) {
                return true;
            }
            swap(arr, start, i);
        }

        return false;
    }

    private void swap(int[] arr, int x, int y) {
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }
}
```
## 解法二
### 思路
- 因为数字可以交换，所以只需要计算2的幂的数每一位上的值的个数，和当前值的所有位上的值的个数比价，完全一致就说明交换后可以获得2的幂的数
### 代码
```java
class Solution {
    public boolean reorderedPowerOf2(int N) {
        int[] arr = count(N);
        for (int i = 0; i < 31; i++) {
            if (Arrays.equals(arr, count(1 << i))) {
                return true;
            }
        }

        return false;
    }

    private int[] count(int num) {
        int[] arr = new int[10];
        while (num > 0) {
            arr[num % 10]++;
            num /= 10;
        }
        return arr;
    }
}
```
# LeetCode_692_前K个高频单词
## 题目
给一非空的单词列表，返回前k 个出现次数最多的单词。

返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。

示例 1：
```
输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
输出: ["i", "love"]
解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
    注意，按字母顺序 "i" 在 "love" 之前。
```
示例 2：
```
输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
输出: ["the", "is", "sunny", "day"]
解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
    出现次数依次为 4, 3, 2 和 1 次。
```
注意：
```
假定 k 总为有效值， 1 ≤ k ≤ 集合元素数。
输入的单词均由小写字母组成。
```
扩展练习：
```
尝试以 O(n log k) 时间复杂度和 O(n) 空间复杂度解决。
```
## 解法
### 思路
- 使用hash表对字符串计数
- 获得keySet对用的list
- 根据key值进行排序
- 返回前k个字符串
### 代码
```java
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        List<String> candidates = new ArrayList<>(map.keySet());
        candidates.sort((x, y) -> map.get(x).equals(map.get(y)) ? x.compareTo(y) : map.get(y) - map.get(x));
        return candidates.subList(0, k);    
    }
}
```
## 解法二
### 思路
- 使用map计数
- 使用大顶堆暂存
- 遍历k次弹出堆顶元素
- 遍历结束返回
### 代码
```java
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<String> queue = new PriorityQueue<>((x, y) -> map.get(x).equals(map.get(y)) ? x.compareTo(y) : map.get(y) - map.get(x));
        for (String key : map.keySet()) {
            queue.offer(key);
        }
        
        List<String> ans = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            ans.add(queue.poll());
        }
        return ans;   
    }
}
```
# LeetCode_142_环形链表II
## 题目
给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。

为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。

说明：不允许修改给定的链表。

示例 1：
```
输入：head = [3,2,0,-4], pos = 1
输出：tail connects to node index 1
解释：链表中有一个环，其尾部连接到第二个节点。
```
示例 2：
```
输入：head = [1,2], pos = 0
输出：tail connects to node index 0
解释：链表中有一个环，其尾部连接到第一个节点。
```
示例 3：
```
输入：head = [1], pos = -1
输出：no cycle
解释：链表中没有环。
```
进阶：
```
你是否可以不用额外空间解决此题？
```
## 解法
### 思路
使用set保存遍历过的节点，如果是遇到的节点就返回该节点，否则返回null
### 代码
```java
public class Solution {
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return head;
            }
            set.add(head);
            head = head.next;
        }
        return null;
    }
}
```
## 解法二
### 思路
- Floyd算法：
    - 使用两个指针，一个步长为1，一个步长为2
    - 如果这个链表有环，那么这两个指针一定会相遇
    - 将相遇的节点记录，作为一个新的指针，和head指针一起重新一步步的移动
    - 遇到的点就是环的入口
- 从leetcode上摘录来的该算法解法
    1. 快指针1次走2步，慢指针1次走1步。所以快指针总是走了慢指针两倍的路。
    2. 回顾一下阶段1的过程，设头节点到入环点的路途为 n, 那么慢指针走了入环路途的一半（n/2）时，快指针就到达入环点了(走完n了)。
    3. 慢指针再继续走完剩下的一般入环路途（剩下的n/2），到达入环点时，快指针已经在环内又走了一个 n 那么远的路了。
    4. 为了方便理解，这里先讨论环很大，大于n的情况（其他情况后文补充）。此时，慢指针正处于入环点，快指针距离入环点的距离为n。环内路，可以用此时快指针的位置分割为两段，前面的 n 部分，和后面的 b 部分。
    5. 此时开始继续快慢指针跑圈，因为已经在环内了，他们其实就是在一条nbnbnbnbnbnbnb（无尽nb路）上跑步。
    6. 慢指针从入环处开始跑b步，距离入环处就剩下了n。此时，快指针则是从距离入环处n步远的位置开始跑了2b步，距离入环处也是剩下了n。他们相遇了，并且距离入环处的距离就是n，n就是头节点到入环点的距离阿!!! 后面的不用说了吧。
### 代码
```java
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head, meet = null;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (fast == slow) {
                meet = fast;
                break;
            }
        }
        
        if (meet == null) {
            return null;
        }
        
        while (head != meet) {
            head = head.next;
            meet = meet.next;
        }
        
        return meet;
    }
}
```
# LeetCode_380_常数时间插入、删除和获取随机元素
## 题目
设计一个支持在平均 时间复杂度 O(1) 下，执行以下操作的数据结构。
```
insert(val)：当元素 val 不存在时，向集合中插入该项。
remove(val)：元素 val 存在时，从集合中移除该项。
getRandom：随机返回现有集合中的一项。每个元素应该有相同的概率被返回。
```
示例 :
```
// 初始化一个空的集合。
RandomizedSet randomSet = new RandomizedSet();

// 向集合中插入 1 。返回 true 表示 1 被成功地插入。
randomSet.insert(1);

// 返回 false ，表示集合中不存在 2 。
randomSet.remove(2);

// 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
randomSet.insert(2);

// getRandom 应随机返回 1 或 2 。
randomSet.getRandom();

// 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
randomSet.remove(1);

// 2 已在集合中，所以返回 false 。
randomSet.insert(2);

// 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
randomSet.getRandom();
```
## 解法
### 思路
- 常数时间获取随机数，需要获取随机下标，所以不能通过hash表的方式，需要使用动态数组
- 通过动态数组可以做到常数时间的插入
- 删除动作如果需要在动态数组中做到常数时间，需要做到每次删除的都是尾部的元素，那么就需要将要删除的元素和尾部元素进行交换
- 所以可以通过如下的数据结构来完成题目要求：
    - 动态数组保存具体元素
    - hash表保存元素和数组下标的映射
### 代码
```java
class RandomizedSet {
    private Random random;
    private List<Integer> list;
    private Map<Integer, Integer> map;

    public RandomizedSet() {
        this.random = new Random();
        this.list = new ArrayList<>();
        this.map = new HashMap<>();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }

        map.put(val, list.size());
        list.add(val);
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }

        int index = map.get(val);
        int last = list.get(list.size() - 1);
        list.set(index, last);
        map.put(last, index);

        list.remove(list.size() - 1);
        map.remove(val);
        return true;
    }

    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}
```
# LeetCode_133_克隆图
## 题目
给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）。

图中的每个节点都包含它的值 val（int） 和其邻居的列表（list[Node]）。
```java
class Node {
    public int val;
    public List<Node> neighbors;
}
```
测试用例格式：

简单起见，每个节点的值都和它的索引相同。例如，第一个节点值为 1，第二个节点值为 2，以此类推。该图在测试用例中使用邻接列表表示。

邻接列表是用于表示有限图的无序列表的集合。每个列表都描述了图中节点的邻居集。

给定节点将始终是图中的第一个节点（值为 1）。你必须将 给定节点的拷贝 作为对克隆图的引用返回。

示例 1：
```
输入：adjList = [[2,4],[1,3],[2,4],[1,3]]
输出：[[2,4],[1,3],[2,4],[1,3]]
解释：
图中有 4 个节点。
节点 1 的值是 1，它有两个邻居：节点 2 和 4 。
节点 2 的值是 2，它有两个邻居：节点 1 和 3 。
节点 3 的值是 3，它有两个邻居：节点 2 和 4 。
节点 4 的值是 4，它有两个邻居：节点 1 和 3 。
```
示例 2：
```
输入：adjList = [[]]
输出：[[]]
解释：输入包含一个空列表。该图仅仅只有一个值为 1 的节点，它没有任何邻居。
```
示例 3：
```
输入：adjList = []
输出：[]
解释：这个图是空的，它不含任何节点。
```
示例 4：
```
输入：adjList = [[2],[1]]
输出：[[2],[1]]
```
提示：
```
节点数介于 1 到 100 之间。
每个节点值都是唯一的。
无向图是一个简单图，这意味着图中没有重复的边，也没有自环。
由于图是无向的，如果节点 p 是节点 q 的邻居，那么节点 q 也必须是节点 p 的邻居。
图是连通图，你可以从给定节点访问到所有节点。
```
## 解法
### 思路
dfs：
- 初始化一个map用来映射原节点和克隆节点
- 从初始节点遍历整个图，通过map来判断是否需要克隆，且将克隆好的元素放入map中
### 代码
```java
class Solution {
    public Node cloneGraph(Node node) {
        Map<Node, Node> map = new HashMap<>();
        return dfs(node, map);
    }
    
    private Node dfs(Node node, Map<Node, Node> map) {
        if (node == null) {
            return null;
        }
        
        if (map.containsKey(node)) {
            return map.get(node);
        }
        
        Node clone = new Node(node.val, new ArrayList<>());
        map.put(node, clone);
        for (Node neighbour : node.neighbors) {
            clone.neighbors.add(dfs(neighbour, map));
        }
        
        return clone;
    }
}
```
## 解法二
### 思路
bfs：思路和dfs类似
- 遍历节点的neighbours
- 如果节点在map中不存在，生成克隆放入map中
- 同时将遍历到的neighbour这个节点放入队列中，用来进行下次处理
- 进入队列的都是原节点，且这个原节点的克隆节点已经被放入map，但是neighbours的元素还没有被填充
- 循环里就是来根据原节点来填充neighbours，同时将没有克隆的节点放入队列和map
### 代码
```java
class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        Map<Node, Node> map = new HashMap<>();
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(node);
        map.put(node, new Node(node.val, new ArrayList<>()));

        while (!queue.isEmpty()) {
            Node n = queue.poll();
            for (Node neighbour : n.neighbors) {
                if (!map.containsKey(neighbour)) {
                    map.put(neighbour, new Node(neighbour.val, new ArrayList<>()));
                    queue.offer(neighbour);
                }
                map.get(n).neighbors.add(map.get(neighbour));
            }
        }
        
        return map.get(node);
    }
}
```
# LeetCode_1105_填充书架
## 题目
附近的家居城促销，你买回了一直心仪的可调节书架，打算把自己的书都整理到新的书架上。

你把要摆放的书 books 都整理好，叠成一摞：从上往下，第 i 本书的厚度为 books[i][0]，高度为 books[i][1]。

按顺序 将这些书摆放到总宽度为 shelf_width 的书架上。

先选几本书放在书架上（它们的厚度之和小于等于书架的宽度 shelf_width），然后再建一层书架。重复这个过程，直到把所有的书都放在书架上。

需要注意的是，在上述过程的每个步骤中，摆放书的顺序与你整理好的顺序相同。 例如，如果这里有 5 本书，那么可能的一种摆放情况是：第一和第二本书放在第一层书架上，第三本书放在第二层书架上，第四和第五本书放在最后一层书架上。

每一层所摆放的书的最大高度就是这一层书架的层高，书架整体的高度为各层高之和。

以这种方式布置书架，返回书架整体可能的最小高度。

示例：
```
输入：books = [[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]], shelf_width = 4
输出：6
解释：
3 层书架的高度和为 1 + 3 + 2 = 6 。
第 2 本书不必放在第一层书架上。
```
提示：
```
1 <= books.length <= 1000
1 <= books[i][0] <= shelf_width <= 1000
1 <= books[i][1] <= 1000
```
## 解法
### 思路
动态规划：
- `dp[i]`：当第i本书在某一层书架第一个位置的时候，这第i到最后一本书所需的最小高度
- 状态转移方程：`dp[i] = min(dp[i], dp[j + 1] + height)`
    - i：作为当前层第一本书的坐标
    - j：i之后的所有可以被放置的书，它会递增，且需要满足不超出当前层的宽度要求
    - 含义：以i为这一层第一本书的前提下，不断往后面加书，每次都判断一次，`当前层的最大高度与之前书架层高的和`与`i本之后的所有书所需要的最小书架高度`之间的最小值
    - 且内层循环所加的书都看作是当前层的最后一本，所以再后一本就是下一层的头一本书，其dp值就代表它之后所有书所需要的书架高度的最小值
- 过程：
    - 外层从数组的最后一个元素开始向前遍历，坐标值为i
    - 内层从i开始向后遍历，直到超出书本总数或者剩余的宽度不够为止
- 最后返回`dp[0]`
### 代码
```java
class Solution {
    public int minHeightShelves(int[][] books, int shelf_width) {
        int[] dp = new int[books.length + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[books.length] = 0;

        for (int i = books.length - 1; i >= 0; i--) {
            int height = 0, leftWeight = shelf_width;
            for (int j = i; j < books.length && leftWeight - books[j][0] >= 0; j++) {
                height = Math.max(height, books[j][1]);
                leftWeight = leftWeight - books[j][0];
                dp[i] = Math.min(dp[i], height + dp[j + 1]);
            }
        }
        
        return dp[0];
    }
}
```
# LeetCode_1090_受标签影响的最大值
## 题目
我们有一个项的集合，其中第 i 项的值为 values[i]，标签为 labels[i]。

我们从这些项中选出一个子集 S，这样一来：
```
|S| <= num_wanted
对于任意的标签 L，子集 S 中标签为 L 的项的数目总满足 <= use_limit。
返回子集 S 的最大可能的 和。
```
示例 1：
```
输入：values = [5,4,3,2,1], labels = [1,1,2,2,3], num_wanted = 3, use_limit = 1
输出：9
解释：选出的子集是第一项，第三项和第五项。
```
示例 2：
```
输入：values = [5,4,3,2,1], labels = [1,3,3,3,2], num_wanted = 3, use_limit = 2
输出：12
解释：选出的子集是第一项，第二项和第三项。
```
示例 3：
```
输入：values = [9,8,8,7,6], labels = [0,0,0,1,1], num_wanted = 3, use_limit = 1
输出：16
解释：选出的子集是第一项和第四项。
```
示例 4：
```
输入：values = [9,8,8,7,6], labels = [0,0,0,1,1], num_wanted = 3, use_limit = 2
输出：24
解释：选出的子集是第一项，第二项和第四项。
```
提示：
```
1 <= values.length == labels.length <= 20000
0 <= values[i], labels[i] <= 20000
1 <= num_wanted, use_limit <= values.length
```
## 解法
### 思路
贪心算法：
- 使用二维数组保存value和label的映射关系
- 根据value进行倒叙排序
- 使用一个bucket数组统计label的出现次数
- 遍历二维数组，根据剩余的`num_wanted`进行累加，如果`num_wanted == 0`则结束累加
- 在累加中，如果value对应的label在bucket中计数超过了`use_limit`，那么不累加这个值
- 遍历结束，返回累加值
### 代码
```java
class Solution {
    public int largestValsFromLabels(int[] values, int[] labels, int num_wanted, int use_limit) {
        int[][] arrs = new int[values.length][2];
        for (int i = 0; i < values.length; i++) {
            arrs[i][0] = values[i];
            arrs[i][1] = labels[i];
        }
        
        Arrays.sort(arrs, (x, y) -> y[0] - x[0]);
        int[] bucket = new int[20001];
        int ans = 0;
        
        for (int[] arr : arrs) {
            if (num_wanted == 0) {
                break;
            }
            
            if (bucket[arr[1]] < use_limit) {
                ans += arr[0];
                bucket[arr[1]]++;
                num_wanted--;
            }
        }
        
        return ans;
    }
}
```
# LeetCode_621_任务调度器
## 题目
给定一个用字符数组表示的 CPU 需要执行的任务列表。其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。CPU 在任何一个单位时间内都可以执行一个任务，或者在待命状态。

然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。

你需要计算完成所有任务所需要的最短时间。

示例 1：
```
输入: tasks = ["A","A","A","B","B","B"], n = 2
输出: 8
执行顺序: A -> B -> (待命) -> A -> B -> (待命) -> A -> B.
```
注：
```
任务的总个数为 [1, 10000]。
n 的取值范围为 [0, 100]。
```
## 解法
### 思路
贪心算法：
- 使用bucket数组统计26个任务的出现个数，将出现个数从大到小排序
- 根据间隔时间n，每个区间分配最多`n+1`个不同的任务，如果不同任务不够再用空闲时间代替
- 嵌套循环：
    - 外层循环的退出条件是`bucket[25] == 0`，也就是所有统计的任务都为0了，坐标25的元素是整个统计数组中的最大值的位置，如果是0就代表所有元素都是0了
    - 内层循环n次，循环体内从最大元素开始向前遍历，如果元素大于0，就`--`
        - 退出条件1：`bucket[25] == 0`
        - 退出条件2：内层循环了n次
### 代码
```java
class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] bucket = new int[26];
        for (char c : tasks) {
            bucket[c - 'A']++;
        }
        Arrays.sort(bucket);
        int ans = 0;

        while (bucket[25] > 0) {
            int i = 0;
            while (i <= n) {
                if (bucket[25] == 0) {
                    break;
                }

                if (i < 26 && bucket[25 - i] > 0) {
                    bucket[25 - i]--;
                }

                i++;
                ans++;
            }

            Arrays.sort(bucket);
        }
        
        return ans;
    }
}
```
## 解法二
### 思路
- 如果任务数最多的任务A的个数是`k`，那么执行完A任务需要`(k - 1) * (n) + 1`个cpu时间，而留给其他任务的空闲时间是`(k - 1) * n`
    - 如果B任务的个数和最多的A任务的个数相同，那么B会占据`k - 1`个空闲时间，以及一个cpu时间
    - 如果C任务的个数比A任务少1个，则C任务就会占据`k - 1`个空闲时间
    - 如果D任务的个数比A少2个以上，则D任务在空闲时间内可以随意放置
- 如果将所有任务安排完以后，仍然有空余时间，那么使用的时间就是`任务个数 + 剩余空闲时间`
- 如果空闲时间使用完，还有任务没有安排，则总时间就是任务个数
### 代码
```java
class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] bucket = new int[26];
        for (char c : tasks) {
            bucket[c - 'A']++;
        }
        Arrays.sort(bucket);

        int max = bucket[25] - 1, idle = max * n;
        for (int i = 24; i >= 0 && bucket[i] > 0; i--) {
            idle -= Math.min(bucket[i], max);
        }

        return idle > 0 ? tasks.length + idle : tasks.length;
    }
}
```
# LeetCode_518_零钱兑换II
## 题目
给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。 

示例 1:
```
输入: amount = 5, coins = [1, 2, 5]
输出: 4
解释: 有四种方式可以凑成总金额:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
```
示例 2:
```
输入: amount = 3, coins = [2]
输出: 0
解释: 只用面额2的硬币不能凑成总金额3。
```
示例 3:
```
输入: amount = 10, coins = [10] 
输出: 1
```
注意:
```
你可以假设：

0 <= amount (总金额) <= 5000
1 <= coin (硬币面额) <= 5000
硬币种类不超过 500 种
结果符合 32 位符号整数
```
## 失败解法
### 失败原因:
超出时间限制
### 思路
递归：
- 退出条件：
    - 累加值`sum`大于`amount`
    - `sum == amount`
- 使用类变量`ans`记录匹配的可能
- 递归中的逻辑是遍历`coins`数组，累加`sum`后继续递归
### 代码
```java
class Solution {
    private int ans = 0;
    public int change(int amount, int[] coins) {
        backTrace(amount, 0, 0, coins);
        return ans;
    }

    private void backTrace(int amount, int sum, int index, int[] coins) {
        if (sum > amount) {
            return;
        }
        
        if (sum == amount) {
            ans++;
            return;
        }
        
        for (int i = index; i < coins.length; i++) {
            backTrace(amount, sum + coins[i], i, coins);
        }
    }
}
```
## 解法
### 思路
动态规划：
- `dp[i][j]`：在`[0,i]`的硬币区间内，能够累加成`j`的组合个数
- 状态转移方程：
    - `dp[i][j] = dp[i - 1][j]`，每一个新的数字所组成的组合个数，都基于没有当前数字的前n个数字形成组合的累加
    - `dp[i][j] += sum(dp[i][j - coins[i]])`，当前数字组成个数相当于，所有目标值`j`减去可能数字`coins[i]`的个数的累加
### 代码
```java
class Solution {
    public int change(int amount, int[] coins) {
        int len = coins.length;
        if (len == 0) {
            return amount == 0 ? 1 : 0;
        }

        int[][] dp = new int[len][amount + 1];
        dp[0][0] = 1;
        for (int i = coins[0]; i <= amount; i += coins[0]) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - coins[i] >= 0) {
                    dp[i][j] += dp[i][j - coins[i]];
                }
            }
        }
        
        return dp[len - 1][amount];
    }
}
```
# LeetCode_1072_按列翻转得到最大值等行数
## 题目
给定由若干 0 和 1 组成的矩阵 matrix，从中选出任意数量的列并翻转其上的 每个 单元格。翻转后，单元格的值从 0 变成 1，或者从 1 变为 0 。

返回经过一些翻转后，行上所有值都相等的最大行数。

示例 1：
```
输入：[[0,1],[1,1]]
输出：1
解释：不进行翻转，有 1 行所有值都相等。
```
示例 2：
```
输入：[[0,1],[1,0]]
输出：2
解释：翻转第一列的值之后，这两行都由相等的值组成。
```
示例 3：
```
输入：[[0,0,0],[0,0,1],[1,1,0]]
输出：2
解释：翻转前两列的值之后，后两行由相等的值组成。
```
提示：
```
1 <= matrix.length <= 300
1 <= matrix[i].length <= 300
所有 matrix[i].length 都相等
matrix[i][j] 为 0 或 1
```
## 解法
### 思路
因为矩阵中所有元素只有1或0，所以可以将矩阵中每一行都转换成以0为头的key，然后计算出现个数最多的key，计算它们出现的次数作为答案即可
### 代码
```java
class Solution {
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        for (int[] row : matrix) {
            if (row[0] == 0) {
                continue;
            }
            
            for (int i = 0; i < row.length; i++) {
                row[i] ^= 1;
            }
        }

        Map<String, Integer> map = new HashMap<>();
        for (int[] row : matrix) {
            StringBuilder sb = new StringBuilder();
            for (int num : row) {
                sb.append(num);
            }
            map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + 1);
        }
        
        return Collections.max(map.values());
    }
}
```
## 优化代码
### 思路
- 解法一进行两次的for循环，其实可以合并成一个
- 解法一中使用字符串来作为map中计数用的key，其实也可以通过计算hash值来作为key
### 代码
```java
class Solution {
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] row : matrix) {
            if (row[0] == 1) {
                for (int i = 0; i < row.length; i++) {
                    row[i] ^= 1;
                }
            }

            int h = Arrays.hashCode(row);
            map.put(h, map.getOrDefault(h, 0) + 1);
        }

        return Collections.max(map.values());
    }
}
```
# LeetCode_650_只有两个键的键盘
## 题目
最初在一个记事本上只有一个字符 'A'。你每次可以对这个记事本进行两种操作：
```
Copy All (复制全部) : 你可以复制这个记事本中的所有字符(部分的复制是不允许的)。
Paste (粘贴) : 你可以粘贴你上一次复制的字符。
给定一个数字 n 。你需要使用最少的操作次数，在记事本中打印出恰好 n 个 'A'。输出能够打印出 n 个 'A' 的最少操作次数。
```
示例 1:
```
输入: 3
输出: 3
解释:
最初, 我们只有一个字符 'A'。
第 1 步, 我们使用 Copy All 操作。
第 2 步, 我们使用 Paste 操作来获得 'AA'。
第 3 步, 我们使用 Paste 操作来获得 'AAA'。
```
说明:
```
n 的取值范围是 [1, 1000] 。
```
## 解法
### 思路
- 如果n是质数，那么就只能复制第一个A之后一个个的粘贴
- 如果n不是质数，那么必定能被分解成`p * q = n`
- 那么如果`CPP`这种组合可以对应上式中不断分解后获得的质数，问题好解了
- 举例30，它可以被分拆成3个质数`2 * 3 * 5 = 30`相乘，那么模拟一下，起始先`CP`获得2个A，之后`CPP`，相当于复制3个AA，获得6个A，之后`CPPPP`，获得5个AAAAAA。
- 随意一个操作字符串长度就相当质数，问题就可以被分解成将n分成若干个质数相乘，而答案就是这些质数的和
### 代码
```java
class Solution {
    public int minSteps(int n) {
        int ans = 0, d = 2;
        while (n > 1) {
            while (n % d == 0) {
                ans += d;
                n /= d;
            }
            d++;
        }
        return ans;
    }
}
```
## 解法二
### 思路
动态规划：
- `dp[i]`：`n == i`时需要的最小操作数
- 状态转移方程：`dp[i] = dp[j] + i / j`，i个字符需要j个字符的最小操作 + `i / j`个操作，而且`i / j`还需要能被整除
### 代码
```java
class Solution {
    public int minSteps(int n) {
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (i % j == 0) {
                    dp[i] = dp[j] + i / j;
                }
            }
        }

        return dp[n];
    }
}
```
# LeetCode_583_两个字符串的删除操作
## 题目
给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。

示例 1:
```
输入: "sea", "eat"
输出: 2
解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"
```
说明:
```
给定单词的长度不超过500。
给定单词中的字符只含有小写字母。
```
## 解法
### 思路
动态规划，相当于求最大公共子序列：
- `dp[i][j]`：s1字符串前i个字符和s2字符串前j个字符中的最大公共子序列长度
- 状态转移方程：
    - 如果`word1[i] == word2[j]`：`dp[i][j] = 1 + dp[i - 1][j - 1]`
    - 如果`word1[i] != word2[j]`：`dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])`
- base case：`i == 0 || j == 0`，对应的二维数组的值时0
- 返回`word1.len + word2.len - 2 * dp[word1.len][word2.len]`
### 代码
```java
class Solution {
    public int minDistance(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return len1 + len2 - 2 * dp[len1][len2];
    }
}
```
## 解法二
### 思路
动态规划，不使用最长公共子序列来解题：
- `dp[i][j]`：s1的第i个字符和s2到第j个字符范围内，删除字符的最小次数
- 状态转移方程：
    - 如果`i == 0 || j == 0`：说明需要删除的次数就是非0的字符串长度范围`i + j`
    - 如果`word1[i] == word2[j]`：那说明当前字符不需要被删除，那么需要删除的字符次数就是`dp[i][j] == dp[i - 1][j - 1]`
    - 如果`word1[i] != word2[j]`：说明当前字符需要被删除，而删除的最小次数就是`1 + min(dp[i - 1][j], dp[i][j - 1])`
- 最终返回dp[word1.length][word2.length]
### 代码
```java
class Solution {
    public int minDistance(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = i + j;
                } else if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], dp[i][j -  1]);
                }
            }
        }
        
        return dp[len1][len2];
    }
}
```
## 解法三
### 思路
- 在解法二的基础上，使用一维数组来代替二维数组，因为在解法二的求解过程中，状态转移只需要考虑当前行和上一行，所以可以使用一个数组暂存上一行的数组，同时用临时数组来计算当前行。
- 每次判断的都是word2长度上字符的进行判断，word1上的字符用来最为基础字符，也就是外层循环，推进整个动态规划的过程
- 为什么可以使用一维，还可以参考题解中的图示[583官方题解](https://leetcode-cn.com/problems/delete-operation-for-two-strings/solution/liang-ge-zi-fu-chuan-de-shan-chu-cao-zuo-by-leetco/)
### 代码
```java
class Solution {
    public int minDistance(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        int[] dp = new int[len2 + 1];
        
        for (int i = 0; i <= len1; i++) {
            int[] tmp = new int[len2 + 1];
            for (int j = 0; j <= len2; j++) {
                if (i == 0 || j == 0) {
                    tmp[j] = i + j;
                } else if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    tmp[j] = dp[j - 1];
                } else {
                    tmp[j] = 1 + Math.min(dp[j], tmp[j - 1]);
                }
            }
            dp = tmp;
        }
        
        return dp[len2];
    }
}
```