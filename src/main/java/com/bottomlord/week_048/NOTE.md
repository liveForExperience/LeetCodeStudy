# LeetCode_1431_拥有最多糖果的孩子
## 题目
给你一个数组candies和一个整数extraCandies，其中candies[i]代表第 i 个孩子拥有的糖果数目。

对每一个孩子，检查是否存在一种方案，将额外的extraCandies个糖果分配给孩子们之后，此孩子有 最多的糖果。注意，允许有多个孩子同时拥有 最多的糖果数目。

示例 1：
```
输入：candies = [2,3,5,1,3], extraCandies = 3
输出：[true,true,true,false,true] 
解释：
孩子 1 有 2 个糖果，如果他得到所有额外的糖果（3个），那么他总共有 5 个糖果，他将成为拥有最多糖果的孩子。
孩子 2 有 3 个糖果，如果他得到至少 2 个额外糖果，那么他将成为拥有最多糖果的孩子。
孩子 3 有 5 个糖果，他已经是拥有最多糖果的孩子。
孩子 4 有 1 个糖果，即使他得到所有额外的糖果，他也只有 4 个糖果，无法成为拥有糖果最多的孩子。
孩子 5 有 3 个糖果，如果他得到至少 2 个额外糖果，那么他将成为拥有最多糖果的孩子。
```
示例 2：
```
输入：candies = [4,2,1,1,2], extraCandies = 1
输出：[true,false,false,false,false] 
解释：只有 1 个额外糖果，所以不管额外糖果给谁，只有孩子 1 可以成为拥有糖果最多的孩子。
```
示例 3：
```
输入：candies = [12,1,12], extraCandies = 10
输出：[true,false,true]
```
提示：
```
2 <= candies.length <= 100
1 <= candies[i] <= 100
1 <= extraCandies <= 50
```
## 解法
### 思路
- 遍历获取最大值
- 遍历累加判断是否大于最大值
- 将结果放入布尔数组中返回
### 代码
```java
class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = Integer.MIN_VALUE;
        List<Boolean> ans = new ArrayList<>();
        for (int candy : candies) {
            max = Math.max(candy, max);
        }

        for (int candy : candies) {
            ans.add(candy + extraCandies >= max);
        }

        return ans;
    }
}
```
# Interview_1726_稀疏相似度
## 题目
两个(具有不同单词的)文档的交集(intersection)中元素的个数除以并集(union)中元素的个数，就是这两个文档的相似度。例如，{1, 5, 3} 和 {1, 7, 2, 3} 的相似度是 0.4，其中，交集的元素有 2 个，并集的元素有 5 个。给定一系列的长篇文档，每个文档元素各不相同，并与一个 ID 相关联。它们的相似度非常“稀疏”，也就是说任选 2 个文档，相似度都很接近 0。请设计一个算法返回每对文档的 ID 及其相似度。只需输出相似度大于 0 的组合。请忽略空文档。为简单起见，可以假定每个文档由一个含有不同整数的数组表示。

输入为一个二维数组 docs，docs[i]表示id 为 i 的文档。返回一个数组，其中每个元素是一个字符串，代表每对相似度大于 0 的文档，其格式为 {id1},{id2}: {similarity}，其中 id1 为两个文档中较小的 id，similarity 为相似度，精确到小数点后 4 位。以任意顺序返回数组均可。

示例:
```
输入: 
[
 [14, 15, 100, 9, 3],
 [32, 1, 9, 3, 5],
 [15, 29, 2, 6, 8, 7],
 [7, 10]
]
输出:
[
 "0,1: 0.2500",
 "0,2: 0.1000",
 "2,3: 0.1429"
]
```
提示：
```
docs.length <= 500
docs[i].length <= 500
相似度大于 0 的文档对数不会超过 1000
```
## 解法
### 思路
map：
- 嵌套循环：
    - 外层遍历每一行的数组
    - 内层：
        - 将这行的每个数字作为map的key，数组的坐标集合list作为value
        - 如果当前数字第一次出现，就生成新的list，并将元素放入
        - 如果当前数字不是第一次出现，就直接将元素放入，并在matrix数组中累加计数，代表形成交集的个数
        - 遍历matrix，以最外层循环的坐标作为当前二维数组的横坐标，查找值大于1的元素，如果是，就根据最外层坐标和当前列坐标，生成对应的字符串
        - 因为当前遍历的行数一定大于map中的任意list中的坐标元素，所以生成的时候，当前行数放在id2的位置
### 代码
```java
class Solution {
    public List<String> computeSimilarities(int[][] docs) {
        int len = docs.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[][] matrix = new int[len][len];
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < docs[i].length; j++) {
                List<Integer> list = map.get(docs[i][j]);

                if (list == null) {
                    list = new ArrayList<>();
                } else {
                    for (int index : list) {
                        matrix[i][index]++;
                    }
                }

                list.add(i);
                map.put(docs[i][j], list);
            }

            for (int k = 0; k < len; k++) {
                if (matrix[i][k] > 0) {
                    ans.add(k + "," + i + ": " + String.format("%.4f", (double) matrix[i][k] / (docs[i].length + docs[k].length - matrix[i][k])));
                }
            }
        }

        return ans;
    }
}
```
# LeetCode_16_最接近的三数之和
## 题目
给定一个包括n 个整数的数组nums和 一个目标值target。找出nums中的三个整数，使得它们的和与target最接近。返回这三个数的和。假定每组输入只存在唯一答案。

例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.

与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
## 解法
### 思路
快速排序+双指针：
- 快速排序数组
- 定义变量ans，初始化为nums[0] + nums[1] + nums[2] - target的绝对值
- 遍历数组：
    - 定义第一个数为nums[i]
    - 定义头尾指针：
        - head = nums[i + 1]
        - tail = nums[nums.length - 1]
    - 第二第三个数对应头尾指针指向的元素
    - 计算sum，如果sum值与target的差的绝对值小于ans，那么就更新ans
    - 如果sum < target，说明总和需要增加，head右移
    - 如果sum > target，说明总和需要减小，tail左移
    - 如果sum = target，直接返回结果
### 代码
```java
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        quickSort(nums);
        int ans = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; i++) {
            int head = i + 1, tail = nums.length - 1;
            while (head < tail) {
                int sum = nums[i] + nums[head] + nums[tail];
                if (Math.abs(target - sum) < Math.abs(target - ans)) {
                    ans = sum;
                }

                if (sum < target) {
                    head++;
                } else if (sum > target) {
                    tail--;
                } else {
                    return sum;
                }
            }
        }

        return ans;
    }

    private void quickSort(int[] arr) {
        sort(0, arr.length - 1, arr);
    }

    private void sort(int head, int tail, int[] arr) {
        if (head >= tail) {
            return;
        }

        int pivot = partition(head, tail, arr);
        sort(head, pivot - 1, arr);
        sort(pivot + 1, tail, arr);
    }

    private int partition(int head, int tail, int[] arr) {
        int num = arr[head];
        while (head < tail) {
            while (head < tail && arr[tail] >= num) {
                tail--;
            }

            arr[head] = arr[tail];

            while (head < tail && arr[head] <= num) {
                head++;
            }

            arr[tail] = arr[head];
        }

        arr[head] = num;
        return head;
    }
}
```
# LeetCode_837_新21点
## 题目
爱丽丝参与一个大致基于纸牌游戏 “21点” 规则的游戏，描述如下：

爱丽丝以 0 分开始，并在她的得分少于 K 分时抽取数字。 抽取时，她从 [1, W] 的范围中随机获得一个整数作为分数进行累计，其中 W 是整数。 每次抽取都是独立的，其结果具有相同的概率。

当爱丽丝获得不少于 K 分时，她就停止抽取数字。 爱丽丝的分数不超过 N 的概率是多少？

示例 1：
```
输入：N = 10, K = 1, W = 10
输出：1.00000
说明：爱丽丝得到一张卡，然后停止。
```
示例 2：
```
输入：N = 6, K = 1, W = 10
输出：0.60000
说明：爱丽丝得到一张卡，然后停止。
在 W = 10 的 6 种可能下，她的得分不超过 N = 6 分。
```
示例 3：
```
输入：N = 21, K = 17, W = 10
输出：0.73278
```
提示：
```
0 <= K <= N <= 10000
1 <= W <= 10000
如果答案与正确答案的误差不超过 10^-5，则该答案将被视为正确答案通过。
此问题的判断限制时间已经减少。
```
## 解法
### 思路
动态规划：
- dp[i]：当前值为i时，获得不大于N的概率
- 初始化：当i在[K, min(N, K + W - 1)]区间中时，是1
- 状态转移方程：`dp[i] = sum(dp[i + 1] + ... + dp[i + W]) / W`
- 返回结果：dp[0]
- 简化转移方程：
    1. `dp[i] - dp[i + 1] = sum(dp[i + 1] + ... + dp[i + W]) / W - sum(dp[i + 2] + ... + dp[i + W + 1]) / W`
    2. `dp[i] = (dp[i + 1] + dp[i + W + 1]) / W - dp[i + 1]`
    3. 需要注意的是i的取值范围是[1, K - 1]，所以这个简化方程不能包含i=K-1的状态，还是需要使用原方法计算
- 注意K为0的情况，这种情况下概率一定是1
### 代码
```java
class Solution {
    public double new21Game(int N, int K, int W) {
        if (K == 0) {
            return 1.0;
        }
        
        double[] dp = new double[K + W + 1];
        for (int i = K; i < K + W && i <= N; i++) {
            dp[i] = 1.0;
        }

        dp[K - 1] = 1.0 * Math.min(W, N - K + 1) / W;
        for (int i = K - 2; i >= 0; i--) {
            dp[i] = (dp[i + 1] - dp[i + W + 1]) / W + dp[i + 1];
        }
        return dp[0];
    }
}
```
# LeetCode_18_四数之和
## 题目
给定一个包含n 个整数的数组nums和一个目标值target，判断nums中是否存在四个元素 a，b，c和 d，使得a + b + c + d的值与target相等？找出所有满足条件且不重复的四元组。

注意：
```
答案中不可以包含重复的四元组。
```
示例：
```
给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。

满足要求的四元组集合为：
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
```
## 解法
### 思路
双指针：
- 快速排序
- 定义四个指针，坐标分别对应：
    - a：0
    - b：1
    - c：2
    - d：len - 1
- c和d相向移动，求四个元素的和sum与target的关系
    - 如果sum > target：c向右移动
    - 如果sum < target：d向左移动
    - 如果sum = target：
        - 记录
        - 将c和d相同的元素过滤掉，确保下个组合是完全不同的
- 如果c和d相遇，右移b，开始新的循环，为了保证不重复，还要确保新的b值变化了，如果没有变化，继续移动
- 如果b无法再移动，移动a，开始新的循环，且移动时和b一样要保证有新的值
### 代码
```java
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int len = nums.length;
        quickSort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for (int a = 0; a < len; a++) {
            if (a > 0 && nums[a] == nums[a - 1]) {
                continue;
            }
            
            for (int b = a + 1; b < len; b++) {
                if (b > a + 1 && nums[b - 1] == nums[b]) {
                    continue;
                }
                
                int c = b + 1, d = len - 1;
                while (c < d) {
                    int sum = nums[a] + nums[b] + nums[c] + nums[d];
                    if (sum < target) {
                        c++;
                    } else if (sum > target) {
                        d--;
                    } else {
                        ans.add(Arrays.asList(nums[a], nums[b], nums[c], nums[d]));
                        while (c < d && nums[c] == nums[c + 1]) {
                            c++;
                        }

                        while (c < d && nums[d] == nums[d - 1]) {
                            d--;
                        }

                        c++;
                        d--;
                    }
                }
            }
        }

        return ans;
    }

    private void quickSort(int[] arr) {
        sort(0, arr.length - 1, arr);
    }

    private void sort(int head, int tail, int[] arr) {
        if (head >= tail) {
            return;
        }

        int pivot = partition(head, tail, arr);

        sort(head, pivot, arr);
        sort(pivot + 1, tail, arr);
    }

    private int partition(int head, int tail, int[] arr) {
        int num = arr[head];

        while (head < tail) {
            while (head < tail && num <= arr[tail]) {
                tail--;
            }
            arr[head] = arr[tail];

            while (head < tail && num >= arr[head]) {
                head++;
            }

            arr[tail] = arr[head];
        }

        arr[head] = num;
        return head;
    }
}
```
# LeetCode_19_删除链表的倒数第N个节点
## 题目
给定一个链表，删除链表的倒数第n个节点，并且返回链表的头结点。

示例：
```
给定一个链表: 1->2->3->4->5, 和 n = 2.

当删除了倒数第二个节点后，链表变为 1->2->3->5.
```
说明：
```
给定的 n保证是有效的。
```
进阶：
```
你能尝试使用一趟扫描实现吗？
```
## 解法
### 思路
单指针遍历两次：
- 遍历一次链表求得长度
- 根据n找到应该删除的节点位置，做删除操作
- 定义node指针用来遍历链表
- 定义pre指针用来确定前节点，并用来做删除动作，初始化为null
- pre指针在第二次找删除节点的时候，做更新，如果pre为null，说明删除的是头节点，需要变更head指针指向head.next
### 代码
```java
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int len = 0;
        ListNode node = head, pre = null;
        while (node != null) {
            len++;
            node = node.next;
        }
        
        int index = len - n;
        node = head;
        while (index-- > 0) {
            pre = node;
            node = node.next;
        }
        
        if (pre != null) {
            pre.next = node.next;
        } else {
            head = head.next;
        }
        
        return head;
    }
}
```
## 优化解法
### 思路
遍历第二次的时候直接遍历到要删除节点的前一个位置，这样就省去了pre指针。再通过判断index是否为0来判断是否需要变动head指针
### 代码
```java
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        
        ListNode node = head;
        int len = 0;
        while (node != null) {
            len++;
            node = node.next;
        }
        
        int index = len - n;
        if (index == 0) {
            return head.next;
        }
        
        node = head;
        while (--index > 0) {
            node = node.next;
        }
        
        node.next = node.next.next;
        
        return head;
    }
}
```
## 优化解法
### 思路
使用哨兵
### 代码
```java
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        
        ListNode dummy = new ListNode(0), node = head;
        dummy.next = head;
        int len = 0;
        while (node != null) {
            node = node.next;
            len++;
        }

        len -= n;
        node = dummy;
        while (len-- > 0) {
            node = node.next;
        }
        
        node.next = node.next.next;
        
        return dummy.next;
    }
}
```
## 解法二
### 思路
双指针遍历一次：
- 定义两个指针：
    - a指针先走n步
    - b指针等a走完n步后，同a指针一同移动，知道a指针遍历结束
    - 此时b指针所在的位置就是需要删除的节点前一个的位置
### 代码
```java
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode a = dummy, b = dummy;

        for (int i = 0; i < n + 1; i++) {
            a = a.next;
        }

        while (a != null) {
            a = a.next;
            b = b.next;
        }

        b.next = b.next.next;

        return dummy.next;
    }
}
```
# LeetCode_128_最长连续序列
## 题目

## 解法
给定一个未排序的整数数组，找出最长连续序列的长度。

要求算法的时间复杂度为 O(n)。

示例:
```
输入: [100, 4, 200, 1, 3, 2]
输出: 4
解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
```
### 思路
- 初始化2个set：
    - set：用来存遍历nums时记录的元素值
    - memo：用来存遍历set时，检查过的元素
- 遍历nums，用set记录num
- 遍历set，从当前元素开始向前后开始查找，如果set中有相邻元素就记录累加，并将该值记录在memo中，直到set中不再有邻接元素为止，和暂存的max比较并更新，进入下一个循环    
- 返回max
### 代码
```java
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet()),
                     memo = new HashSet<>();
        int max = 0;
        for (int num : set) {
            if (memo.contains(num)) {
                continue;
            }
            
            memo.add(num);
            int len = 1;
            int add = num + 1;
            while (set.contains(add)) {
                memo.add(add++);
                len++;
            }
            
            int minus = num - 1;
            while (set.contains(minus)) {
                memo.add(minus--);
                len++;
            }
            
            max = Math.max(max, len);
        }
        
        return max;
    }
}
```
# LeetCode_126_单词接龙II
## 题目
给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord 的最短转换序列。转换需遵循如下规则：
```
每次转换只能改变一个字母。
转换过程中的中间单词必须是字典中的单词。
```
说明:
```
如果不存在这样的转换序列，返回一个空列表。
所有单词具有相同的长度。
所有单词只由小写字母组成。
字典中不存在重复的单词。
你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
```
示例 1:
```
输入:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

输出:
[
  ["hit","hot","dot","dog","cog"],
  ["hit","hot","lot","log","cog"]
]
```
示例 2:
```
输入:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]
```
输出: []
```
解释: endWord "cog" 不在字典中，所以不存在符合要求的转换序列。
```
## 解法
### 思路
bfs：
- 根据wordList生成word和id形成的映射，id设置成自增
- 再用id和word对应，生成一个list字典，字典为数组，下标为id，值为word
- 遍历wordList，并根据word和id的映射，生成一个有向图，使用id代替word
- 用一个数组cost记录从beginWord到当前word的开销
- 使用queue来驱动进行bfs
- 元素为遍历图的路径顶点
- 将路径的最后一个节点与endWord比较
    - 如果匹配，就放入结果集合中
    - 如果不匹配，记录搜索，且判断当前的开销 + 1是否比图中记录的下一个节点的开销小，如果是的话，更新这个下一节点的开销以及路径，放入队列中继续遍历
### 代码
```java
class Solution {
    private Map<String, Integer> map = new HashMap<>();
    private List<String> list = new ArrayList<>();
    private List<Integer>[] edges;

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        int index = 0;
        for (String word : wordList) {
            map.put(word, index++);
            list.add(word);
        }

        if (!map.containsKey(endWord)) {
            return Collections.emptyList();
        }

        if (!map.containsKey(beginWord)) {
            map.put(beginWord, index);
            list.add(beginWord);
        }

        edges = new ArrayList[list.size()];
        for (int i = 0; i < list.size(); i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (hasOneDiff(list.get(i), list.get(j))) {
                    edges[i].add(j);
                    edges[j].add(i);
                }
            }
        }

        int[] cost = new int[list.size()];
        Arrays.fill(cost, 1 << 20);

        Queue<List<Integer>> queue = new ArrayDeque<>();
        cost[map.get(beginWord)] = 0;
        int dest = map.get(endWord);
        List<Integer> beginList = new ArrayList<>();
        beginList.add(map.get(beginWord));
        queue.add(beginList);
        List<List<String>> ans = new ArrayList<>();

        while (!queue.isEmpty()) {
            List<Integer> now = queue.poll();
            Integer curr = now.get(now.size() - 1);

            if (curr == dest) {
                List<String> tmp = new ArrayList<>();
                for (int i : now) {
                    tmp.add(this.list.get(i));
                }
                ans.add(tmp);
            } else {
                List<Integer> toList = edges[curr];

                for (int to : toList) {
                    if (cost[curr] + 1 <= cost[to]) {
                        cost[to] = cost[curr] + 1;
                        List<Integer> tmp = new ArrayList<>(now);
                        tmp.add(to);
                        queue.add(tmp);
                    }
                }
            }
        }

        return ans;
    }

    private boolean hasOneDiff(String one, String two) {
        int diff = 0;
        for (int i = 0; i < one.length() && diff < 2; i++) {
            if (one.charAt(i) != two.charAt(i)) {
                diff++;
            }
        }

        return diff == 1;
    }
}
```