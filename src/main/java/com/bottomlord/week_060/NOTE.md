# LeetCode_227_基本计算器II
## 题目
实现一个基本的计算器来计算一个简单的字符串表达式的值。

字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。

示例 1:
```
输入: "3+2*2"
输出: 7
```
示例 2:
```
输入: " 3/2 "
输出: 1
```
示例 3:
```
输入: " 3+5 / 2 "
输出: 5
```
说明：
```
你可以假设所给定的表达式都是有效的。
请不要使用内置的库函数 eval。
```
## 解法
### 思路
栈：
- 初始化临时变量：
    - `num`：暂存遍历到的数字的计算过程
    - `op`：记录上一个操作符
- 遍历字符串：
    - 如果是空字符就跳过
    - 如果字符是数字，就计算这个数字，`num = num * 10 + digit`
    - 如果是操作符，那么就判断`op`是什么操作符：
        - 如果是`+``-`，就将对应的正负数压入栈中，
        - 如果是`*``/`，就说明需要使用优先级，将栈中的值和暂存的`num`拿出进行计算，并再将这个接入压入栈中
    - 从如上可知，当前是否为操作符会驱动计算，但是最后一组操作数和操作符的组合却无法被驱动，所以判断的时候需要加入是否是最后一个字符。但又因为最后一个字符可能是` `，所以还需要对s作一个trim的操作，避免这种特殊情况
- 最后将栈中的元素遍历后累加，就能获得结果
### 代码
```java
class Solution {
    public int calculate(String s) {
        s = s.trim();
        
        int num = 0; char op = '+';
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }
            
            if (c == '+' || c == '-' || c == '*' || c == '/' || i == s.length() - 1) {
                if (op == '+') {
                    stack.push(num);
                } else if (op == '-') {
                    stack.push(-num);
                } else if (op == '*') {
                    stack.push(num * stack.pop());
                } else {
                    stack.push(stack.pop() / num);
                }
                
                op = s.charAt(i);
                num = 0;
            }
        }
        
        int ans = 0;
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        
        return ans;
    }
}
```
# LeetCode_229_求众数
## 题目
给定一个大小为 n 的数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。

说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1)。

示例 1:
```
输入: [3,2,3]
输出: [3]
```
示例 2:
```
输入: [1,1,1,3,3,2,2,2]
输出: [1,2]
```
## 解法
### 思路
摩尔投票法
- 如果要在总数中选出n个人，就需要超过`1 / (n + 1)`票，因为题目中要找到所有票数超过`1 / 3`的元素，所以候选人个数就是2
- 在摩尔投票法中，遍历数组，判断`(n + 1)`个元素是否全不相等
    - 如果是，全部抵消
    - 如果不是，计数相等的数字
- 继续遍历比较，直到遍历到最后一个元素，此时判断哪些数字最后的值不为0，不为0不一定代表超过`(n + 1)`，但为0肯定代表不是
- 然后根据不为零的数，再次遍历数组，做计数统计，获得最后准确的答案
## 代码
```java
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int candidate1 = 0, count1 = 0,
            candidate2 = 0, count2 = 0;

        for (int num : nums) {
            if (candidate1 == num) {
                count1++;
                continue;
            }

            if (candidate2 == num) {
                count2++;
                continue;
            }

            if (count1 == 0) {
                candidate1 = num;
                count1++;
                continue;
            }

            if (count2 == 0) {
                candidate2 = num;
                count2++;
                continue;
            }

            count1--;
            count2--;
        }

        List<Integer> ans = new ArrayList<>();
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (candidate1 == num) {
                count1++;
            } else if (candidate2 == num) {
                count2++;
            }
        }

        if (count1 > nums.length / 3) {
            ans.add(candidate1);
        }

        if (count2 > nums.length / 3) {
            ans.add(candidate2);
        }

        return ans;
    }
}
```
# LeetCode_240_探索二维矩阵II
## 题目
编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
```
每行的元素从左到右升序排列。
每列的元素从上到下升序排列。
```
示例:

现有矩阵 matrix 如下：
```
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
## 解法
### 思路
- 先逐行遍历，比较行尾元素与target的关系
    - 如果相等直接返回true
    - 如果小于target，跳入下一行
    - 如果大于target，开始当前行的搜索
- 在行内搜索时，如果找到匹配target的元素，就直接返回true
- 遍历结束还没有找到，返回false
### 代码
```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        if (row == 0) {
            return false;
        }

        int col = matrix[0].length;
        if (col == 0) {
            return false;
        }

        for (int[] rows : matrix) {
            if (rows[col - 1] == target) {
                return true;
            }

            if (rows[col - 1] < target) {
                continue;
            }

            for (int c = 0; c < col - 1; c++) {
                if (rows[c] == target) {
                    return true;
                }
            }
        }
        
        return false;
    }
}
```
## 解法二
### 思路
双指针：
- 初始化双指针：
    - row：代表行数，初始化为0
    - col：代表列数，初始化为行尾坐标
- 过程：
    - 循环退出条件：两个指针有一个越界
    - 判断3种情况：
        - 通过当前行列坐标找到target，返回true
        - 比target小，row++增大
        - 比target大，col--减小
### 代码
```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        if (row == 0) {
            return false;
        }
        
        int col = matrix[0].length;
        if (col == 0) {
            return false;
        }
        
        int r = 0, c = col - 1;
        while (r < row && c >= 0) {
            int num = matrix[r][c];
            if (target == num) {
                return true;
            } else if (target > num) { 
                r++;
            } else {
                c--;
            }
        }
        
        return false;
    }
}
```
# LeetCode_243_最短的单词距离
## 题目
给定一个单词列表和两个单词 word1 和 word2，返回列表中这两个单词之间的最短距离。

示例:
```
假设 words = ["practice", "makes", "perfect", "coding", "makes"]
输入: word1 = “coding”, word2 = “practice”
输出: 3
输入: word1 = "makes", word2 = "coding"
输出: 1
```
注意:
```
你可以假设 word1 不等于 word2, 并且 word1 和 word2 都在列表里。
```
## 解法
### 思路
- 遍历分别求单词出现的坐标，并分别记录
- 嵌套遍历两组出现的坐标，求差值绝对值的最小值
### 代码
```java
class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {
        List<Integer> i1 = new ArrayList<>(), i2 = new ArrayList<>();
        
        for (int i = 0; i < words.length; i++) {
            if (Objects.equals(word1, words[i])) {
                i1.add(i);
            } else if (Objects.equals(word2, words[i])) {
                i2.add(i);
            }
        }
        
        int ans = words.length;
        for (Integer a : i1) {
            for (Integer b : i2) {
                ans = Math.min(ans, Math.abs(a - b));
            }
        }
        
        return ans;
    }
}
```
## 解法二
### 思路
最短距离应该都出现在两个临近元素之间，所以可以使用双指针，判断的过程中也根据元素大小来移动指针，哪个元素小，哪个元素对应的坐标就后移一位，使判断过程中保持元素尽量的靠近。
### 代码
```java
class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {
        List<Integer> l1 = new ArrayList<>(), l2 = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (Objects.equals(word, word1)) {
                l1.add(i);
            } else if (Objects.equals(word, word2)) {
                l2.add(i);
            }
        }

        int i1 = 0, i2 = 0, ans = Integer.MAX_VALUE;
        while (i1 < l1.size() && i2 < l2.size()) {
            ans = Math.min(ans, Math.abs(l1.get(i1) - l2.get(i2)));

            if (l1.get(i1) > l2.get(i2)) {
                i2++;
            } else {
                i1++;
            }
        }
        
        return ans;
    }
}
```
# LeetCode_244_最短单词距离II
## 题目
请设计一个类，使该类的构造函数能够接收一个单词列表。然后再实现一个方法，该方法能够分别接收两个单词 word1 和 word2，并返回列表中这两个单词之间的最短距离。您的方法将被以不同的参数调用 多次。

示例:
```
假设 words = ["practice", "makes", "perfect", "coding", "makes"]

输入: word1 = “coding”, word2 = “practice”
输出: 3
输入: word1 = "makes", word2 = "coding"
输出: 1
```
注意:
```
你可以假设 word1 不等于 word2, 并且 word1 和 word2 都在列表里。
```
## 解法
### 思路
- 初始化时，遍历列表，通过散列表存储对应单词出现的下标
- 比对时取出下标集合，进行嵌套遍历的比较
### 代码
```java
class WordDistance {
    private Map<String, List<Integer>> map;
    public WordDistance(String[] words) {
        this.map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            List<Integer> list = map.getOrDefault(words[i], new ArrayList<>());
            list.add(i);
            map.put(words[i], list);
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> list1 = map.get(word1),
                      list2 = map.get(word2);

        int ans = Integer.MAX_VALUE;
        for (Integer i1 : list1) {
            for (Integer i2 : list2) {
                ans = Math.min(ans, Math.abs(i1 - i2));
            }
        }

        return ans;
    }
}
```
## 解法二
### 思路
与243题的第二解类似，使用双指针，尽可能使比对元素靠近。
### 代码
```java
class WordDistance {
    private Map<String, List<Integer>> map;
    public WordDistance(String[] words) {
        this.map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            List<Integer> list = map.getOrDefault(words[i], new ArrayList<>());
            list.add(i);
            map.put(words[i], list);
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> l1 = map.get(word1),
                      l2 = map.get(word2);

        int i1 = 0, i2 = 0, ans = Integer.MAX_VALUE;
        while (i1 < l1.size() && i2 < l2.size()) {
            ans = Math.min(ans, Math.abs(l1.get(i1) - l2.get(i2)));

            if (l1.get(i1) > l2.get(i2)) {
                i2++;
            } else {
                i1++;
            }
        }

        return ans;
    }
}
```
# LeetCode_245_最短单词距离III
## 题目
给定一个单词列表和两个单词 word1 和 word2，返回列表中这两个单词之间的最短距离。

word1 和 word2 是有可能相同的，并且它们将分别表示为列表中两个独立的单词。

示例:
```
假设 words = ["practice", "makes", "perfect", "coding", "makes"].

输入: word1 = “makes”, word2 = “coding”
输出: 1
输入: word1 = "makes", word2 = "makes"
输出: 3
```
注意:
```
你可以假设 word1 和 word2 都在列表里。
```
## 解法
### 思路
和243及244题的第一解一样:
- 获取两组list
- 然后嵌套遍历比较，但去除掉元素值相同的情况
### 代码
```java
class Solution {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        List<Integer> l1 = new ArrayList<>(), l2 = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (Objects.equals(word, word1)) {
                l1.add(i);
            }
            
            if (Objects.equals(word, word2)) {
                l2.add(i);
            }
        }
        
        int ans = Integer.MAX_VALUE;
        for (Integer i1 : l1) {
            for (Integer i2 : l2) {
                if (Objects.equals(i1, i2)) {
                    continue;
                }
                
                ans = Math.min(ans, Math.abs(i1 - i2));
            }
        }
        
        return ans;
    }
}
```
## 解法二
### 思路
和243及244题类似，当比较时发现坐标相同时，就移动第一个坐标，否则就和之前一样，移动那个小的
### 代码
```java
class Solution {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        List<Integer> l1 = new ArrayList<>(), l2 = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (Objects.equals(word, word1)) {
                l1.add(i);
            }

            if (Objects.equals(word, word2)) {
                l2.add(i);
            }
        }

        int ans = Integer.MAX_VALUE;
        int i1 = 0, i2 = 0;
        while (i1 < l1.size() && i2 < l2.size()) {
            Integer num1 = l1.get(i1), num2 = l2.get(i2);
            if (Objects.equals(num1, num2)) {
                i1++;
                continue;
            }
            
            ans = Math.min(ans, Math.abs(num1 - num2));
            
            if (num1 > num2) {
                i2++;
            } else {
                i1++;
            }
        }

        return ans;
    }
}
```
# LeetCode_332_重新安排行程
## 题目
给定一个机票的字符串二维数组 [from, to]，子数组中的两个成员分别表示飞机出发和降落的机场地点，对该行程进行重新规划排序。所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。

说明:
```
如果存在多种有效的行程，你可以按字符自然排序返回最小的行程组合。例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前
所有的机场都用三个大写字母表示（机场代码）。
假定所有机票至少存在一种合理的行程。
```
示例 1:
```
输入: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
输出: ["JFK", "MUC", "LHR", "SFO", "SJC"]
```
示例 2:
```
输入: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
输出: ["JFK","ATL","JFK","SFO","ATL","SFO"]
解释: 另一种有效的行程是 ["JFK","SFO","ATL","JFK","ATL","SFO"]。但是它自然排序更大更靠后。
```
## 解法
### 思路
- 根据子数组第二个元素的ASCII码升序排序
- 初始化map，记录图信息
- 遍历数组，子数组的第一个元素为key，value为list，将第二个元素放入对应的list中
- 回溯：
    - 退出条件是map中所有value都为空，返回true
    - 递归参数为：
        - key：每次的起点，以这个起点到map中找到所有可能的终点
        - map：整个图
        - ans：作为结果的list
    - 在递归的开始，将key放入list中，作为可能的路径中的一点
    - 凭key到map中找到对应的终点list，如果没有就返回false
    - 遍历key为指定起点的list，取出元素后，以它为key继续递归
    - 如果返回的是true，就直接返回
    - 否则就将元素放入list中，并将ans中的最后一个元素去除，并继续下一个list元素的遍历
### 代码
```java
class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        tickets.sort(Comparator.comparing(x -> x.get(1)));
        Map<String, List<String>> map = new HashMap<>();
        for (List<String> ticket : tickets) {
            String key = ticket.get(0);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(ticket.get(1));
            map.put(key, list);
        }
        
        List<String> ans = new ArrayList<>();
        boolean find = backTrack("JFK", map, ans);
        return find ? ans : Collections.emptyList();
    }
    
    private boolean backTrack(String key, Map<String, List<String>> map, List<String> ans) {
        ans.add(key);
        if (empty(map)) {
            return true;
        }
        
        List<String> value = map.get(key);
        if (value == null) {
            return false;
        }
        
        List<String> list = new ArrayList<>(value);
        for (int i = 0; i < list.size(); i++) {
            String end = value.get(i);
            value.remove(end);
            boolean find = backTrack(end, map, ans);
            if (find) {
                return true;
            }
            ans.remove(ans.size() - 1);
            value.add(i, end);
        }
        
        return false;
    }
    
    private boolean empty(Map<String, List<String>> map) {
        for (List<String> list : map.values()) {
            if (!list.isEmpty()) {
                return false;
            }
        }
        
        return true;
    }
}
```
# LeetCode_246_中心对称数
## 题目
中心对称数是指一个数字在旋转了 180 度之后看起来依旧相同的数字（或者上下颠倒地看）。

请写一个函数来判断该数字是否是中心对称数，其输入将会以一个字符串的形式来表达数字。

示例 1:
```
输入: num = "69"
输出: true
```
示例 2:
```
输入: num = "88"
输出: true
```
示例 3:
```
输入: num = "962"
输出: false
```
示例 4：
```
输入：num = "1"
输出：true
```
## 解法
### 思路
- map记录可以翻转的值以及翻转后得到值的映射关系
- 头尾双指针相向遍历字符串
- 判断如下三种情况返回false：
    - map中没有的值
    - 两个值相等，但有值为6或9
    - 两个值不相等，且不是互为69
### 代码
```java
class Solution {
    public boolean isStrobogrammatic(String num) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        map.put(1, 1);
        map.put(6, 9);
        map.put(8, 8);
        map.put(9, 6);
        
        int head = 0, tail = num.length() - 1;
        while (head <= tail) {
            int a = num.charAt(head) - '0',
                b = num.charAt(tail) - '0';
            
            if (!map.containsKey(a) || !map.containsKey(b)) {
                return false;
            }
            
            if (a == b && (a == 6 || a == 9)) {
                return false;
            }
            
            if (a != b && ((a != 6 && a != 9) || (a == 6 && b != 9) || (a == 9 && b != 6))) {
                return false;
            }
            
            head++;
            tail--;
        }
        
        return true;
    }
}
```
# LeetCode_247_中心对称数II
## 题目
中心对称数是指一个数字在旋转了 180 度之后看起来依旧相同的数字（或者上下颠倒地看）。

找到所有长度为 n 的中心对称数。

示例 :
```
输入:  n = 2
输出: ["11","69","88","96"]
```
## 解法
### 思路
dfs
### 代码
```java
class Solution {
    public List<String> findStrobogrammatic(int n) {
        char[][] map = new char[][]{{'0', '0'}, {'1', '1'}, {'6', '9'}, {'8', '8'}, {'9', '6'}};
        List<String> ans = new ArrayList<>();
        dfs(new char[n], 0, n - 1, map, ans);
        return ans;
    }

    private void dfs(char[] cs, int head, int tail, char[][] map, List<String> ans) {
        if (head > tail) {
            ans.add(new String(cs));
            return;
        }

        for (char[] pair : map) {
            char c = pair[0];
            if (head == tail && (c == '6' || c == '9')) {
                continue;
            }
            
            if (head == 0 && pair[0] == '0' && head != tail) {
                continue;
            }
            
            cs[head] = pair[0];
            cs[tail] = pair[1];
            dfs(cs, head + 1, tail - 1, map, ans);
        }
    }
}
```
# LeetCode_248_中心对称数III
## 题目
中心对称数是指一个数字在旋转了 180 度之后看起来依旧相同的数字（或者上下颠倒地看）。

写一个函数来计算范围在 [low, high] 之间中心对称数的个数。

示例:
```
输入: low = "50", high = "100"
输出: 3 
解释: 69，88 和 96 是三个在该范围内的中心对称数
注意:
由于范围可能很大，所以 low 和 high 都用字符串表示。
```
## 解法
### 思路
bfs：
- 初始化1位的中心对称数，`""`,`0`,`1`,`8`，这些数字会作为bfs的起始元素
- 建立所有符合翻转后对称可能的数字的翻转映射关系，这些映射关系会作为搜索的所有路径
- bfs搜索所有可能的组合，并和low与high做比较，如果符合就入队继续搜索
- 符合的数字就计数累加1
### 代码
```java
class Solution {
    public int strobogrammaticInRange(String low, String high) {
        char[][] cs = new char[][]{{'0','0'},{'1','1'},{'6','9'},{'8','8'},{'9','6'}};
        Queue<String> queue = new ArrayDeque<>();
        queue.offer("");
        queue.offer("0");
        queue.offer("1");
        queue.offer("8");

        int ans = 0;
        while (!queue.isEmpty()) {
            String s = queue.poll();
            if (s.length() >= low.length() && s.length() <= high.length()) {
                if (!(s.length() > 1 && s.charAt(0) == '0')) {
                    if (check(s, low, high)) {
                        ans++;
                    }
                }
            }

            if (s.length() > high.length()) {
                continue;
            }

            for (char[] c : cs) {
                String next = c[0] + s + c[1];
                if (next.length() <= high.length()) {
                    queue.offer(next);
                }
            }
        }
        
        return ans;
    }

    private boolean check(String num, String low, String high) {
        return compare(num, low) && compare(high, num);
    }

    private boolean compare(String a, String b) {
        if (a.length() != b.length()) {
            return a.length() > b.length();
        }

        int r = a.compareTo(b);
        return r > 0 || r == 0;
    }
}
```