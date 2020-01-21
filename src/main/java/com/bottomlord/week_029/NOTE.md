# LeetCode_452_最少数量的箭引爆气球
## 题目
在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。由于它是水平的，所以y坐标并不重要，因此只要知道开始和结束的x坐标就足够了。开始坐标总是小于结束坐标。平面内最多存在104个气球。

一支弓箭可以沿着x轴从不同点完全垂直地射出。在坐标x处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被引爆。可以射出的弓箭的数量没有限制。 弓箭一旦被射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。

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
给定一个表示分数加减运算表达式的字符串，你需要返回一个字符串形式的计算结果。 这个结果应该是不可约分的分数，即最简分数。 如果最终结果是一个整数，例如 2，你需要将它转换成分数形式，其分母为 1。所以在上述例子中, 2 应该被转换为 2/1。

示例 1:
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
输入和输出字符串只包含 '0' 到 '9' 的数字，以及 '/', '+' 和 '-'。 
输入和输出分数格式均为 ±分子/分母。如果输入的第一个分数或者输出的分数是正数，则 '+' 会被省略掉。
输入只包含合法的最简分数，每个分数的分子与分母的范围是  [1,10]。 如果分母是1，意味着这个分数实际上是一个整数。
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
给定正整数 N ，我们按任何顺序（包括原始顺序）将数字重新排序，注意其前导数字不能为零。

如果我们可以通过上述方式得到 2 的幂，返回 true；否则，返回 false。

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
给一非空的单词列表，返回前 k 个出现次数最多的单词。

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