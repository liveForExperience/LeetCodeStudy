# LeetCode_72_编辑距离
## 题目
给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。

你可以对一个单词进行如下三种操作：
```
插入一个字符
删除一个字符
替换一个字符
```
示例 1：
```
输入：word1 = "horse", word2 = "ros"
输出：3
解释：
horse -> rorse (将 'h' 替换为 'r')
rorse -> rose (删除 'r')
rose -> ros (删除 'e')
```
示例 2：
```
输入：word1 = "intention", word2 = "execution"
输出：5
解释：
intention -> inention (删除 't')
inention -> enention (将 'i' 替换为 'e')
enention -> exention (将 'n' 替换为 'x')
exention -> exection (将 'n' 替换为 'c')
exection -> execution (插入 'u')
```
## 解法
### 思路
动态规划：
- `A插入一个字符`等价于`B删除一个字符`
- `B插入一个字符`等价于`A删除一个字符`
- `A修改一个字符`等价于`B修改一个字符`
- `dp[i][j]`：
    - i代表A的前i个字符
    - j代表B的前j个字符
    - dp[i][j]代表A的前i个字符和B的前j个字符，需要保持一致时最小的修改次数
- 状态转移方程：
    - 如果A的第i个字符和B的第j个字符相同：`dp[i][j] = 1 + min(dp[i][j - 1], dp[i - 1][j], dp[i - 1][j - 1] - 1)`
    - 如果A的第i个字符和B的第j个字符不相同：`dp[i][j] = 1 + min(dp[i][j - 1], dp[i - 1][j], dp[i - 1][j - 1])`
- 初始化：
    - `dp[i][0] = i`
    - `dp[0][j] = j`
### 代码
```java
class Solution {
    public int minDistance(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                int add1 = dp[i - 1][j] + 1, add2 = dp[i][j - 1] + 1;
                int edit = word1.charAt(i - 1) == word2.charAt(j - 1) ? dp[i - 1][j - 1] : dp[i - 1][j - 1] + 1;
                dp[i][j] = Math.min(add1, Math.min(add2, edit));
            }
        }

        return dp[len1][len2];
    }
}
```
# Interview_1701_不用加号的加法
## 题目
设计一个函数把两个数字相加。不得使用 + 或者其他算术运算符。

示例:
```
输入: a = 1, b = 1
输出: 2
```
提示：
```
a, b 均可能是负数或 0
结果不会溢出 32 位整数
```
## 解法
### 思路
- 异或获得不进位加法值
- 与获得位上都是1的位置，再左移一位的话就获得了下次需要相加的二进制值
- 一致循环，直到与计算后获得0为止
### 代码
```java
class Solution {
    public int add(int a, int b) {
        while (b != 0) {
            int carry = (a & b) << 1;
            a = (a ^ b);
            b = carry;
        }
        
        return a;
    }
}
```
# Interview_1704_消失的数字
## 题目
数组nums包含从0到n的所有整数，但其中缺了一个。请编写代码找出那个缺失的整数。你有办法在O(n)时间内完成吗？

示例 1：
```
输入：[3,0,1]
输出：2
```
示例 2：
```
输入：[9,6,4,2,3,5,7,0,1]
输出：8
```
## 解法
### 思路
- 通过数组长度和等差数列求和公式，求得总和
- 遍历数组求得元素总和
- 相减获得目标值
### 代码
```java
class Solution {
    public int missingNumber(int[] nums) {
        int origin = nums.length * (nums.length + 1) / 2;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        
        return origin - sum;
    }
}
```
# Interview_1010_数字流的秩
## 题目
假设你正在读取一串整数。每隔一段时间，你希望能找出数字 x 的秩(小于或等于 x 的值的个数)。请实现数据结构和算法来支持这些操作，也就是说：

实现 track(int x) 方法，每读入一个数字都会调用该方法；

实现 getRankOfNumber(int x) 方法，返回小于或等于 x 的值的个数。

示例:
```
输入:
["StreamRank", "getRankOfNumber", "track", "getRankOfNumber"]
[[], [1], [0], [0]]
输出:
[null,0,null,1]
```
提示：
```
x <= 50000
track 和 getRankOfNumber 方法的调用次数均不超过 2000 次
```
## 解法
### 思路
使用优先级队列
### 代码
```java
class StreamRank {
    private PriorityQueue<Integer> queue;
    public StreamRank() {
        this.queue = new PriorityQueue<>();
    }

    public void track(int x) {
        this.queue.offer(x);
    }

    public int getRankOfNumber(int x) {
        int count = 0;
        List<Integer> list = new ArrayList<>(); 
        while (!this.queue.isEmpty()) {
            if (this.queue.peek() > x) {
                break;
            }

            list.add(this.queue.poll());
            count++;
        }

        for (int num : list) {
            this.queue.offer(num);
        }

        return count;
    }
}
```
## 解法二
### 思路
二叉搜索树：
- 树的节点类中有一个用来记录，比当前值小或等于的节点个数的属性count
- track：遍历二叉树
    - 如果当前节点和x相等，当前节点的count值加1
    - 如果当前节点比x小，去右子树搜索
    - 如果当前节点比x大，说明x可以放在当前节点的左子树中，当前节点的count加1，这个count代表当前节点的左子树个数
- getRankOfNumber：遍历二叉树
    - 搜索所有比x大的节点，将这些节点的count值累加
    - 直到搜索到和x值相等的值，或没有节点为止    
### 代码
```java
class StreamRank {
    private Node root;
    public StreamRank() {
    }

    public void track(int x) {
        if (root == null) {
            this.root = new Node(x);
        } else {
            Node node = this.root;
            while (true) {
                if (node.val == x) {
                    node.count++;
                    break;
                } else if (node.val > x) {
                    node.count++;
                    if (node.left != null) {
                        node = node.left;
                    } else {
                        node.left = new Node(x);
                        break;
                    }
                } else {
                    if (node.right != null) {
                        node = node.right;
                    } else {
                        node.right = new Node(x);
                        break;
                    }
                }
            }
        }
    }

    public int getRankOfNumber(int x) {
        int ans = 0;
        Node node = root;
        while (node != null) {
            if (node.val == x) {
                ans += node.count;
                break;
            } else if (node.val > x) {
                node = node.left;
            } else {
                ans += node.count;
                node = node.right;
            }
        }
        return ans;
    }
}

class Node {
    public int val;
    public int count;
    public Node left;
    public Node right;
    public Node(int val) {
        this.val = val;
        this.count = 1;
    }
}
```
# Interview_1011_峰与谷
## 题目
在一个整数数组中，“峰”是大于或等于相邻整数的元素，相应地，“谷”是小于或等于相邻整数的元素。例如，在数组{5, 8, 6, 2, 3, 4, 6}中，{8, 6}是峰， {5, 2}是谷。现在给定一个整数数组，将该数组按峰与谷的交替顺序排序。

示例:
```
输入: [5, 3, 1, 2, 3]
输出: [5, 1, 3, 2, 3]
```
提示：
```
nums.length <= 10000
```
## 解法
### 思路
排序
- 头尾指针一次放置在新的数组中，直到指针相遇
- 遍历新数组，将元素依次放入老数组中
### 代码
```java
class Solution {
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int head = 0, tail = nums.length - 1, index = 0;
        int[] tmp = new int[nums.length];
        while (head <= tail) {
            if (head == tail) {
                tmp[index] = nums[head];
                break;
            }
            
            tmp[index++] = nums[head++];
            tmp[index++] = nums[tail--];
        }
        
        index = 0;
        for (int num : tmp) {
            nums[index++] = num;
        }
    }
}
```
## 解法二
### 思路
不使用额外的空间：
- 假设使用峰谷峰的顺序设置数组
    - 奇数位为峰
    - 偶数位为谷
- 从第二个元素开始遍历数组
    - 如果当前元素为奇数位，则在当前元素小于前一个元素的情况下，进行互换
    - 如果当前元素为偶数位，则在当前元素大于前一个元素的情况下，进行互换
### 代码
```java
class Solution {
    public void wiggleSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if ((i & 1) == 0) {
                if (nums[i] > nums[i - 1]) {
                    swap(nums, i, i - 1);
                }
            } else {
                if (nums[i] < nums[i - 1]) {
                    swap(nums, i, i - 1);
                }
            }
        }
    }
    
    private void swap(int[] nums, int x, int y) {
        int tmp = nums[x];
        nums[x] = nums[y];
        nums[y] = tmp;
    }
}
```
# Interview_1601_交换数字
## 题目
编写一个函数，不用临时变量，直接交换numbers = [a, b]中a与b的值。

示例：
```
输入: numbers = [1,2]
输出: [2,1]
```
提示：
```
numbers.length == 2
```
## 解法
### 思路
异或
### 代码
```java
class Solution {
    public int[] swapNumbers(int[] numbers) {
        numbers[0] ^= numbers[1];
        numbers[1] ^= numbers[0];
        numbers[0] ^= numbers[1];
        return numbers;
    }
}
```
# Interview_1602_单词频率
## 题目
设计一个方法，找出任意指定单词在一本书中的出现频率。

你的实现应该支持如下操作：
```
WordsFrequency(book)构造函数，参数为字符串数组构成的一本书
get(word)查询指定单词在数中出现的频率
```
示例：
```
WordsFrequency wordsFrequency = new WordsFrequency({"i", "have", "an", "apple", "he", "have", "a", "pen"});
wordsFrequency.get("you"); //返回0，"you"没有出现过
wordsFrequency.get("have"); //返回2，"have"出现2次
wordsFrequency.get("an"); //返回1
wordsFrequency.get("apple"); //返回1
wordsFrequency.get("pen"); //返回1
```
提示：
```
book[i]中只包含小写字母
1 <= book.length <= 100000
1 <= book[i].length <= 10
get函数的调用次数不会超过100000
```
## 解法
### 思路
哈希表
### 代码
```java
class WordsFrequency {
    private Map<String, Integer> map;
    public WordsFrequency(String[] book) {
        map = new HashMap<>();
        for (String word : book) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
    }

    public int get(String word) {
        return map.getOrDefault(word, 0);
    }
}
```
# Interview_1603_交点
## 题目
给定两条线段（表示为起点start = {X1, Y1}和终点end = {X2, Y2}），如果它们有交点，请计算其交点，没有交点则返回空值。

要求浮点型误差不超过10^-6。若有多个交点（线段重叠）则返回X值最小的点，X坐标相同则返回Y值最小的点。

示例 1：
```
输入：
line1 = {0, 0}, {1, 0}
line2 = {1, 1}, {0, -1}
输出： {0.5, 0}
```
示例 2：
```
输入：
line1 = {0, 0}, {3, 3}
line2 = {1, 1}, {2, 2}
输出： {1, 1}
```
示例 3：
```
输入：
line1 = {0, 0}, {1, 1}
line2 = {1, 0}, {2, 1}
输出： {}，两条线段没有交点
```
提示：
```
坐标绝对值不会超过2^7
输入的坐标均是有效的二维坐标
```
## 解法
### 思路
通过求交点的一般式：
- `ax + by + c = 0`
    - `a = y2 - y1`
    - `b = x1 - x2`
    - `c = x2y1 - x1y2`
- 交点：
    - `x = (c2 * b1 - c1 * b2) / (a1 * b2 - a2 * b1)`
    - `y = (c1 * a2 - c2 * a1) / (a1 * b2 - a2 * b1)`
### 代码
```java
class Solution {
    public double[] intersection(int[] start1, int[] end1, int[] start2, int[] end2) {
        int a1 = end1[1] - start1[1], b1 = start1[0] - end1[0], c1 = end1[0] * start1[1] - start1[0] * end1[1];
        int a2 = end2[1] - start2[1], b2 = start2[0] - end2[0], c2 = end2[0] * start2[1] - start2[0] * end2[1];

        if (a1 * b2 == a2 * b1) {
            if (c1 != c2) {
                return new double[0];
            }

            double[] ans = new double[]{Double.MAX_VALUE, Double.MAX_VALUE};
            int[][] arrs = new int[][]{start1, end1, start2, end2};
            for (int[] arr : arrs) {
                if (inLine(arr[0], arr[1], start1, end1, start2, end2)) {
                    if (arr[0] < ans[0] || (arr[0] == ans[0] && arr[1] < ans[1])) {
                        ans[0] = arr[0];
                        ans[1] = arr[1];
                    }
                }
            }
            return ans[0] == Double.MAX_VALUE ? new double[0] : ans;
        }

        double x = 1.0 * (c2 * b1 - c1 * b2) / (a1 * b2 - a2 * b1), 
               y = 1.0 * (c1 * a2 - c2 * a1) / (a1 * b2 - a2 * b1);
        
        if (inLine(x, y, start1, end1, start2, end2)) {
            return new double[]{x, y};
        } else {
            return new double[0];
        }
    }

    private boolean inLine(double x, double y, int[] start1, int[] end1, int[] start2, int[] end2) {
        int minX1 = Math.min(start1[0], end1[0]), maxX1 = Math.max(start1[0], end1[0]);
        int minY1 = Math.min(start1[1], end1[1]), maxY1 = Math.max(start1[1], end1[1]);
        if (x < minX1 || x > maxX1 || y < minY1 || y > maxY1) {
            return false;
        }
        int minX2 = Math.min(start2[0], end2[0]), maxX2 = Math.max(start2[0], end2[0]);
        int minY2 = Math.min(start2[1], end2[1]), maxY2 = Math.max(start2[1], end2[1]);
        if (x < minX2 || x > maxX2 || y < minY2 || y > maxY2) {
            return false;
        }

        return true;
    }
}
```
# Interview_1604_井字游戏
## 题目
设计一个算法，判断玩家是否赢了井字游戏。输入是一个 N x N 的数组棋盘，由字符" "，"X"和"O"组成，其中字符" "代表一个空位。

以下是井字游戏的规则：
```
玩家轮流将字符放入空位（" "）中。
第一个玩家总是放字符"O"，且第二个玩家总是放字符"X"。
"X"和"O"只允许放置在空位中，不允许对已放有字符的位置进行填充。
当有N个相同（且非空）的字符填充任何行、列或对角线时，游戏结束，对应该字符的玩家获胜。
当所有位置非空时，也算为游戏结束。
如果游戏结束，玩家不允许再放置字符。
如果游戏存在获胜者，就返回该游戏的获胜者使用的字符（"X"或"O"）；如果游戏以平局结束，则返回 "Draw"；如果仍会有行动（游戏未结束），则返回 "Pending"。
```
示例 1：
```
输入： board = ["O X"," XO","X O"]
输出： "X"
```
示例 2：
```
输入： board = ["OOX","XXO","OXO"]
输出： "Draw"
解释： 没有玩家获胜且不存在空位
```
示例 3：
```
输入： board = ["OOX","XXO","OX "]
输出： "Pending"
解释： 没有玩家获胜且仍存在空位
```
提示：
```
1 <= board.length == board[i].length <= 100
输入一定遵循井字棋规则
```
## 解法
### 思路
各种情况逐次遍历，硬做
### 代码
```java
class Solution {
    public String tictactoe(String[] board) {
        int n = board.length;
        boolean blank = false;
        String[] ss = new String[]{"X", "O"};
        for (int i = 0; i < n; i++) {
            if (!blank) {
                if (board[i].contains(" ")) {
                    blank = true;
                }
            }

            for (int j = 0; j < ss.length; j++) {
                if ("".equals(board[i].replace(ss[j], ""))) {
                    return ss[j];
                }
            }

            boolean flag = true;
            for (int j = 1; j < n; j++) {
                if (board[j].charAt(i) == ' ' || !Objects.equals(board[j].charAt(i), board[j - 1].charAt(i))) {
                    flag = false;
                    break;
                }
            }
            
            if (flag) {
                return Character.toString(board[0].charAt(i));
            }
        }

        if (board[0].charAt(0) != ' ') {
            boolean flag = true;
            for (int i = 1; i < n; i++) {
                if (board[i].charAt(i) != board[i - 1].charAt(i - 1)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return Character.toString(board[0].charAt(0));
            }
        }

        if (board[0].charAt(n - 1) != ' ') {
            boolean flag = true;
            for (int i = 1; i < n; i++) {
                if (board[i].charAt(n - 1 - i) != board[i - 1].charAt(n - i)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return Character.toString(board[0].charAt(n - 1));
            }
        }

        return blank ? "Pending" : "Draw";
    }
}
```
# Interview_1606_最小差
## 题目
给定两个整数数组a和b，计算具有最小差绝对值的一对数值（每个数组中取一个值），并返回该对数值的差

示例：
```
输入：{1, 3, 15, 11, 2}, {23, 127, 235, 19, 8}
输出： 3，即数值对(11, 8)
```
提示：
```
1 <= a.length, b.length <= 100000
-2147483648 <= a[i], b[i] <= 2147483647
正确结果在区间[-2147483648, 2147483647]内
```
## 解法
### 思路
双指针：
- 两个数组排序
- 同时遍历两个数组的下标，直到有一个下标越界
- 求两个值的差值的绝对值，并暂存相对最小值
- 比较两个数组的元素，如果a元素小就移动a元素，否则移动b元素
- 小心int最小值减0取绝对值的溢出情况
- 这样移动可以使得两个元素相对的接近
### 代码
```java
class Solution {
    public int smallestDifference(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        long ans = Integer.MAX_VALUE;
        for (int i = 0, j = 0; i < a.length && j < b.length;) {
            ans = Math.min(Math.abs((long)a[i] - (long)b[j]), ans);
            if (a[i] < b[j]) {
                i++;
            } else {
                j++;
            }
        }
        return (int)ans;
    }
}
```
# Interview_1608_整数的英语表示
## 题目
给定一个整数，打印该整数的英文描述。

示例 1:
```
输入: 123
输出: "One Hundred Twenty Three"
```
示例 2:
```
输入: 12345
输出: "Twelve Thousand Three Hundred Forty Five"
```
示例 3:
```
输入: 1234567
输出: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
```
示例 4:
```
输入: 1234567891
输出: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
```
## 解法
### 思路
- 把字符串分成三类：
    - 小于20的
    - 100以内的10的倍数
    - 千，百万，10亿
- 将整数按每三位分割，每一段的内容是一致的，区别在最后是千，百万，十亿还是没有
- 所以可以使用递归来算每一段，每一段里面用字符串的前两种做判断和拼接
### 代码
```java
class Solution {
    private String[] ones = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
            "Nine", "Ten", "Eleven","Twelve", "Thirteen", "Fourteen", "Fifteen",
            "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private String[] gens = {"Billion", "Million", "Thousand", ""};
    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }

        String ans = "";
        int[] factors = new int[]{1000000000, 1000000, 1000, 1};
        for (int i = 0; i < factors.length; i++) {
            ans += recurse(num / factors[i]);
            if (num / factors[i] != 0) {
                ans += " " + gens[i] + " ";
            }
            num %= factors[i];
        }
        
        return ans.trim();
    }

    private String recurse(int num) {
        if (num < 20) {
            return ones[num];
        }

        if (num < 100) {
            String ans = tens[num / 10];
            if (num % 10 != 0) {
                ans += " " + ones[num % 10];
            }
            return ans;
        }

        String ans = ones[num / 100] + " Hundred";
        if (num % 100 != 0) {
            ans += " " + recurse(num % 100);
        }
        return ans;
    }
}
```
# Interview_1611_跳水板
## 题目
你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为shorter，长度较长的木板长度为longer。你必须正好使用k块木板。编写一个方法，生成跳水板所有可能的长度。

返回的长度需要从小到大排列。

示例：
```
输入：
shorter = 1
longer = 2
k = 3
输出： {3,4,5,6}
```
提示：
```
0 < shorter <= longer
0 <= k <= 100000
```
## 解法
### 思路
循环：
- 遍历获得一种木板的使用个数
- 循环内根据使用的木板个数累加不同结果，存入set中
- 排序
### 代码
```java
class Solution {
    public int[] divingBoard(int shorter, int longer, int k) {
        if (k == 0) {
            return new int[0];
        }
        
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i <= k; i++) {
            set.add(shorter * (k - i) + longer * i);
        }
        return set.stream().mapToInt(x -> x).sorted().toArray();
    }
}
```
## 解法二
### 思路
- 解法一中，每一种可能的结果中，都大于等于k个最短木板的长度
- 每一种不同结果的区别在于这个最短木板长度的基础上，到底有多少个长短木板的差值
- 应为答案不能有重复，所以如果长短木板一样长，那么起始结果就只有一个
- 过程：
    - 特殊处理k是0、长短木板长度一样的情况
    - 计算长短木板的差`diff`
    - 循环`k + 1`次，计算`k个短木板长度 + i个diff`的结果，放入数组中
    - 循环结束返回 
### 代码
```java
class Solution {
    public int[] divingBoard(int shorter, int longer, int k) {
        if (k == 0) {
            return new int[0];
        }
        
        if (shorter == longer) {
            return new int[]{shorter * k};
        }
        
        int diff = longer - shorter;
        int[] ans = new int[k + 1];
        for (int i = 0; i <= k; i++) {
            ans[i] = shorter * k + diff * i;
        }
        return ans;
    }
}
```
# Interview_1609_运算
## 题目
请实现整数数字的乘法、减法和除法运算，运算结果均为整数数字，程序中只允许使用加法运算符和逻辑运算符，允许程序中出现正负常数，不允许使用位运算。

你的实现应该支持如下操作：
```
Operations() 构造函数
minus(a, b) 减法，返回a - b
multiply(a, b) 乘法，返回a * b
divide(a, b) 除法，返回a / b
```
示例：
```
Operations operations = new Operations();
operations.minus(1, 2); //返回-1
operations.multiply(3, 4); //返回12
operations.divide(5, -2); //返回-2
```
提示：
```
你可以假设函数输入一定是有效的，例如不会出现除法分母为0的情况
单个用例的函数调用次数不会超过1000次
```
## 解法
### 思路
- 减法：使用负号
- 乘法：
    - 判断正负
    - 如果b小于等于10，那就直接累加b次的a
    - 如果b大于10，从b尾部开始获取每一位的值，累加这个值，再乘以相应的位数个10，将获得的值累加
    - 根据正负号返回值
- 除法：
    - 判断正负
    - 如果a是0，返回0
    - 如果a和b的绝对值相等，根据正负号返回正负1
    - 从a的头部开始获取a每一位的值，前一位上的值10倍和余数的和，与当前这一位的值的和，计算能被b累减几次，这个次数与之前暂存的次数的10倍相加，直到a被遍历完，返回这个次数的累加值即可  
### 代码
```java
class Operations {
    public Operations() {

    }

    public int minus(int a, int b) {
        return a + (-b);
    }

    public int multiply(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }

        boolean negative = isNegative(a, b);

        a = Math.abs(a);
        b = Math.abs(b);
        
        int ans = 0;
        if (b <= 10) {
            for (int i = 0; i < b; i++) {
                ans += a;
            }
            return negative ? -ans : ans;
        }

        String sb = String.valueOf(b);
        for (int i = sb.length() - 1; i >= 0; i--) {
            int bit = sb.charAt(i) - '0';
            int multi = multiply(a, bit);
            for (int j = 0; j < sb.length() - 1 - i; j++) {
                multi = multiply(multi, 10);
            }
            ans += multi;
        }

        return negative ? -ans : ans;
    }

    public int divide(int a, int b) {
        if (b == 1) {
            return a;
        }

        if (b == -1) {
            return -a;
        }

        boolean negative = isNegative(a, b);
        a = Math.abs(a);
        b = Math.abs(b);
        if (a < b) {
            return 0;
        }

        if (a == b) {
            return negative ? -1 : 1;
        }

        int ans = 0, cur = 0;
        String sa = String.valueOf(a);
        for (int i = 0; i < sa.length(); i++) {
            int bit = sa.charAt(i) - '0', count = 0;
            cur = multiply(cur, 10) + bit;
            while (cur >= b) {
                cur = minus(cur, b);
                count++;
            }
            ans = multiply(ans, 10) + count;
        }

        return negative ? -ans : ans;
    }

    private boolean isNegative(int a, int b) {
        return (a < 0 && b > 0) || (a > 0 && b < 0);
    }
}
```
# Interview_1610_生存人数
## 题目
给定N个人的出生年份和死亡年份，第i个人的出生年份为birth[i]，死亡年份为death[i]，实现一个方法以计算生存人数最多的年份。

你可以假设所有人都出生于1900年至2000年（含1900和2000）之间。如果一个人在某一年的任意时期都处于生存状态，那么他们应该被纳入那一年的统计中。例如，生于1908年、死于1909年的人应当被列入1908年和1909年的计数。

如果有多个年份生存人数相同且均为最大值，输出其中最小的年份。

示例：
```
输入：
birth = {1900, 1901, 1950}
death = {1948, 1951, 2000}
输出： 1901
```
提示：
```
0 < birth.length == death.length <= 10000
birth[i] <= death[i]
```
## 解法
### 思路
- 分别对出生和死亡日期进行排序
- 使用两个指针同时遍历两个数组
- 循环退出的条件初始数组遍历结束
- 如果出生日期小于死亡日期
    - 暂存人口累加，如果比最大值大，就记录当前出生日期
    - 移动出生指针
- 如果出生日期与死亡日期一样，两个指针同时移动
- 如果死亡日期小于出生日期，人口减少，移动死亡指针
### 代码
```java
class Solution {
    public int maxAliveYear(int[] birth, int[] death) {
        Arrays.sort(birth);
        Arrays.sort(death);
        int ans = 0, max = 0, cur = 0, b = 0, d = 0;
        while (b < birth.length) {
            if (birth[b] <= death[d]) {
                cur++;
                if (cur > max) {
                    max = cur;
                    ans = b;
                }
                b++;
            } else {
                cur--;
                d++;
            }
        }
        return birth[ans];
    }
}
```
# Interview_1613_平分正方形
## 题目
给定两个正方形及一个二维平面。请找出将这两个正方形分割成两半的一条直线。假设正方形顶边和底边与 x 轴平行。

每个正方形的数据square包含3个数值，正方形的左下顶点坐标[X,Y] = [square[0],square[1]]，以及正方形的边长square[2]。所求直线穿过两个正方形会形成4个交点，请返回4个交点形成线段的两端点坐标（两个端点即为4个交点中距离最远的2个点，这2个点所连成的线段一定会穿过另外2个交点）。2个端点坐标[X1,Y1]和[X2,Y2]的返回格式为{X1,Y1,X2,Y2}，要求若X1 != X2，需保证X1 < X2，否则需保证Y1 <= Y2。

若同时有多条直线满足要求，则选择斜率最大的一条计算并返回（与Y轴平行的直线视为斜率无穷大）。

示例：
```
输入：
square1 = {-1, -1, 2}
square2 = {0, -1, 2}
输出： {-1,0,2,0}
解释： 直线 y = 0 能将两个正方形同时分为等面积的两部分，返回的两线段端点为[-1,0]和[2,0]
```
提示：
```
square.length == 3
square[2] > 0
```
## 解法
### 思路
- 经过正方形中心点的直线平分一个正方形
- 计算两个正方形的中心点
- 分三种情况求线段顶点：
    - 垂直x轴
    - 斜率绝对值小于1，与左右两边相交
    - 斜率绝对值大于1，与上下两边相交
- 因为答案要求x1<x2或y1<y2，所以一开始根据x的大小决定是否交换两个数组
### 代码
```java
class Solution {
    public double[] cutSquares(int[] square1, int[] square2) {
        if (square1[0] > square2[0]) {
            int[] tmp = square1;
            square1 = square2;
            square2 = tmp;
        }

        double r1 = square1[2] / 2.0, r2 = square2[2] / 2.0;
        double x1 = square1[0] + r1, x2 = square2[0] + r2;
        double y1 = square1[1] + r1, y2 = square2[1] + r2;

        double max = Math.max(y1 + r1, y2 + r2);
        if (Objects.equals(x1, x2)) {
            return new double[]{x1, y1 - r1, x1, max};
        }

        double k = (y2 - y1) / (x2 - x1), b = y1 - k * x1;
        if (Math.abs(k) <= 1) {
            double l = square1[0], r = Math.max(x1 + r1, x2 + r2);
            return new double[]{l, l * k + b, r, r * k + b};
        } else {
            double d = Math.min(square1[1], square2[1]);
            double dx = (d - b) / k, ux = (max - b) / k;

            if (dx < ux || (Objects.equals(dx, ux) && d < max)) {
                return new double[]{dx, d, ux, max};
            } else {
                return new double[]{ux, max, dx, d};
            }
        }
    }
}
```
# LeetCode_887_鸡蛋掉落
## 题目
你将获得 K 个鸡蛋，并可以使用一栋从 1 到 N  共有 N 层楼的建筑。

每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它掉下去。

你知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，从 F 楼层或比它低的楼层落下的鸡蛋都不会破。

每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。

你的目标是确切地知道 F 的值是多少。

无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？

 

示例 1：
```
输入：K = 1, N = 2
输出：2
解释：
鸡蛋从 1 楼掉落。如果它碎了，我们肯定知道 F = 0 。
否则，鸡蛋从 2 楼掉落。如果它碎了，我们肯定知道 F = 1 。
如果它没碎，那么我们肯定知道 F = 2 。
因此，在最坏的情况下我们需要移动 2 次以确定 F 是多少。
```
示例 2：
```
输入：K = 2, N = 6
输出：3
```
示例 3：
```
输入：K = 3, N = 14
输出：4
```
提示：
```
1 <= K <= 100
1 <= N <= 10000
```
## 解法
### 思路
//todo 待解释
### 代码
```java
public class Solution {

    public int superEggDrop(int K, int N) {
        // dp[i][j]：一共有 i 层楼梯的情况下，使用 j 个鸡蛋的最少仍的次数
        int[][] dp = new int[N + 1][K + 1];
        
        // 初始化
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], i);
        }
        for (int j = 0; j <= K; j++) {
            dp[0][j] = 0;
        }

        dp[1][0] = 0;
        for (int j = 1; j <= K; j++) {
            dp[1][j] = 1;
        }
        for (int i = 0; i <= N; i++) {
            dp[i][0] = 0;
            dp[i][1] = i;
        }

        // 开始递推
        for (int i = 2; i <= N; i++) {
            for (int j = 2; j <= K; j++) {
                // 在区间 [1, i] 里确定一个最优值
                int left = 1;
                int right = i;
                while (left < right) {
                    // 找 dp[k - 1][j - 1] <= dp[i - mid][j] 的最大值 k
                    int mid = left + (right - left + 1) / 2;
                    
                    int breakCount = dp[mid - 1][j - 1];
                    int notBreakCount = dp[i - mid][j];
                    if (breakCount > notBreakCount) {
                        // 排除法（减治思想）写对二分见第 35 题，先想什么时候不是解
                        // 严格大于的时候一定不是解，此时 mid 一定不是解
                        // 下一轮搜索区间是 [left, mid - 1]
                        right = mid - 1;
                    } else {
                        // 这个区间一定是上一个区间的反面，即 [mid, right]
                        // 注意这个时候取中间数要上取整，int mid = left + (right - left + 1) / 2;
                        left = mid;
                    }
                }
                // left 这个下标就是最优的 k 值，把它代入转移方程 Math.max(dp[k - 1][j - 1], dp[i - k][j]) + 1) 即可
                dp[i][j] = Math.max(dp[left - 1][j - 1], dp[i - left][j]) + 1;
            }
        }
        return dp[N][K];
    }
}
```
# Interview_1614_最佳直线
## 题目
给定一个二维平面及平面上的 N 个点列表Points，其中第i个点的坐标为Points[i]=[Xi,Yi]。请找出一条直线，其通过的点的数目最多。

设穿过最多点的直线所穿过的全部点编号从小到大排序的列表为S，你仅需返回[S[0],S[1]]作为答案，若有多条直线穿过了相同数量的点，则选择S[0]值较小的直线返回，S[0]相同则选择S[1]值较小的直线返回。

示例：
```
输入： [[0,0],[1,1],[1,0],[2,0]]
输出： [0,2]
解释： 所求直线穿过的3个点的编号为[0,2,3]
```
提示：
```
2 <= len(Points) <= 300
len(Points[i]) = 2
```
## 解法
### 思路
- 嵌套循环，遍历所有两两坐标之间的组合
- 嵌套中计算两两之间的`ax + by +c = 0`中的abc的值
- 嵌套中再遍历所有节点，算出能够带入`ax + by + c = 0`的坐标，计数
- 计数值和最大值做比较，如果是最大值，就记录坐标。如果和最大值相等，就取x值最小的点
### 代码
```java
class Solution {
    public int[] bestLine(int[][] points) {
        int[] ans = new int[2];
        int max = 0;

        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                int x1 = points[i][0], y1 = points[i][1];
                int x2 = points[j][0], y2 = points[j][1];

                int a = y2 - y1, b = x1 - x2, c = y1 * (x2 - x1) - x1 * (y2 - y1);
                int count = 0;
                for (int[] point : points) {
                    if (atLine(a, b, c, point[0], point[1])) {
                        count++;
                    }
                }

                if (count > max) {
                    max = count;
                    ans[0] = i;
                    ans[1] = j;
                } else if (count == max) {
                    if (i == ans[0] && j < ans[1]) {
                        ans[1] = j;
                    }
                }
            }
        }

        return ans;
    }

    private boolean atLine(int a, int b, int c, int x, int y) {
        return a * x + b * y + c == 0;
    }
}
```
# Interview_1615_珠玑妙算
## 题目
珠玑妙算游戏（the game of master mind）的玩法如下。

计算机有4个槽，每个槽放一个球，颜色可能是红色（R）、黄色（Y）、绿色（G）或蓝色（B）。例如，计算机可能有RGGB 4种（槽1为红色，槽2、3为绿色，槽4为蓝色）。作为用户，你试图猜出颜色组合。打个比方，你可能会猜YRGB。要是猜对某个槽的颜色，则算一次“猜中”；要是只猜对颜色但槽位猜错了，则算一次“伪猜中”。注意，“猜中”不能算入“伪猜中”。

给定一种颜色组合solution和一个猜测guess，编写一个方法，返回猜中和伪猜中的次数answer，其中answer[0]为猜中的次数，answer[1]为伪猜中的次数。

示例：
```
输入： solution="RGBY",guess="GGRR"
输出： [1,1]
解释： 猜中1次，伪猜中1次。
```
## 解法
### 思路
- 先算两个字符串中相同字符的个数`all`
- 然后算相同位置字符一样的个数`same`
- 答案第一个`same`，第二个返回`all - same`
### 代码
```java
class Solution {
    public int[] masterMind(String solution, String guess) {
        char[] css = solution.toCharArray(), csg = guess.toCharArray();

        int same = 0;
        for (int i = 0; i < solution.length(); i++) {
            if (css[i] == csg[i]) {
                same++;
            }
        }

        int all = 0;
        int[] bucket1 = new int[26], bucket2 = new int[26];
        for (int i = 0; i < solution.length(); i++) {
            bucket1[css[i] - 'A']++;
            bucket2[csg[i] - 'A']++;
        }
        
        for (int i = 0; i < bucket1.length; i++) {
            all += Math.min(bucket1[i], bucket2[i]);
        }
        
        return new int[]{same, all - same};
    }
}
```