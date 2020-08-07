# LeetCode_2_两数相加
## 题目
给出两个非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照逆序的方式存储的，并且它们的每个节点只能存储一位数字。

如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

您可以假设除了数字 0 之外，这两个数都不会以 0开头。

示例：
```
输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 0 -> 8
原因：342 + 465 = 807
```
## 解法
### 思路
- 同时遍历两个链表
- 计算节点和进位的和
    - 取余10作为新节点的值
    - 除以10作为新的进位制
- 遍历结束后判断是否有进位没有处理，如果是就再加一个进位值得节点
- 返回新链表的头节点
### 代码
```java
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode node1 = l1, node2 = l2, pre = new ListNode(0), start = pre;
        int carry = 0;
        while (node1 != null || node2 != null) {
            int num;
            if (node1 == null) {
                num = carry + node2.val;
                pre.next = new ListNode(num % 10);
                node2 = node2.next;
            } else if (node2 == null) {
                num = carry + node1.val;
                pre.next = new ListNode(num % 10);
                node1 = node1.next;
            } else {
                num = node1.val + node2.val + carry;
                pre.next = new ListNode(num % 10);
                node1 = node1.next;
                node2 = node2.next;
            }
            
            carry = num / 10;
            pre = pre.next;
        }

        if (carry != 0) {
            pre.next = new ListNode(carry);
        }

        return start.next;
    }
}
```
# LeetCode_466_统计重复个数
## 题目
由 n 个连接的字符串 s 组成字符串 S，记作S = [s,n]。例如，["abc",3]=“abcabcabc”。

如果我们可以从 s2中删除某些字符使其变为 s1，则称字符串 s1可以从字符串 s2 获得。例如，根据定义，"abc" 可以从 “abdbec” 获得，但不能从 “acbbe” 获得。

现在给你两个非空字符串 s1和 s2（每个最多 100 个字符长）和两个整数 0 ≤ n1 ≤ 106和 1 ≤ n2 ≤ 106。现在考虑字符串 S1 和 S2，其中 S1=[s1,n1]、S2=[s2,n2] 。

请你找出一个可以满足使[S2,M] 从 S1获得的最大整数 M 。

示例：
```
输入：
s1 ="acb",n1 = 4
s2 ="ab",n2 = 2

返回：
2
```
## 解法
### 思路
嵌套循环计数：
- 变量：
    - i2：字符串s2的下标
    - loop2：字符串s2在遍历s1的过程中循环的次数
- 嵌套循环：
    - 外层循环n1次
    - 内层循环s1的长度次
        - 判断当前s1遍历到的字符是否与当前s2的字符相等
            - 如果是就自增i2
        - 判断i2是否与s2长度相等
            - 如果是，循环次数+1，且i2坐标重置为0
- 返回loop2 / n2，循环的总次数处理n2的次数 
### 代码
```java
class Solution {
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        int len1 = s1.length(), len2 = s2.length();
        
        char[] cs1 = s1.toCharArray(), cs2 = s2.toCharArray();
        
        int i2 = 0, loop2 = 0;
        
        for (int loop1 = 0; loop1 < n1; loop1++) {
            for (int i1 = 0; i1 < len1; i1++) {
                if (cs1[i1] == cs2[i2]) {
                    i2++;
                    
                    if (i2 == len2) {
                        loop2++;
                        i2 = 0;
                    }
                }
            }
        }
        
        return loop2 / n2;
    }
}
```
## 解法二
### 思路
找循环节：
- 循环节，相当于某个重复出现的s1的子字符串，包含了若干个个s2
- [s1,n1]和[s2,n2]两个字符串中存在的循环节，一般都是错位存在的。
- 所以只要找到循环节，就不需要再遍历完所有的字符串，通过计算循环节的个数和循环节中s2的个数，加上头部和尾部非循环节的字符串中s2的个数，把它们的和除以n1就可以了
### 代码
```java
class Solution {
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        int len1 = s1.length(), len2 = s2.length();

        if (len1 == 0 || n1 == 0 || len2 == 0 || n2 == 0) {
            return 0;
        }

        char[] cs1 = s1.toCharArray(), cs2 = s2.toCharArray();

        int[] times = new int[n1], next = new int[n1];

        int i2 = 0, loop2 = 0;
        for (int loop1 = 0; loop1 < n1; loop1++) {
            for (int i1 = 0; i1 < len1; i1++) {
                if (cs1[i1] == cs2[i2]) {
                    i2++;
                }

                if (i2 == len2) {
                    loop2++;
                    i2 = 0;
                }
            }

            times[loop1] = loop2;
            next[loop1] = i2;

            if (loop1 > 0 && next[0] == i2) {
                int head = times[0];

                int mid = ((n1 - 1) / loop1) * (times[loop1] - times[0]);

                int end = times[(n1 - 1) % loop1] - times[0];
                
                return (head + mid + end) / n2;
            }
        }
        
        return times[n1 - 1] / n2;
    }
}
```
# Interview_1621_交换和
## 题目
给定两个整数数组，请交换一对数值（每个数组中取一个数值），使得两个数组所有元素的和相等。

返回一个数组，第一个元素是第一个数组中要交换的元素，第二个元素是第二个数组中要交换的元素。若有多个答案，返回任意一个均可。若无满足条件的数值，返回空数组。

示例:
```
输入: array1 = [4, 1, 2, 1, 1, 2], array2 = [3, 6, 3, 3]
输出: [1, 3]
```
示例:
```
输入: array1 = [1, 2, 3], array2 = [4, 5, 6]
输出: []
```
## 解法
### 思路
- 遍历求两个数组并求和
    - sum1
    - sum2
- 求出两个数组的和的差值
- 判断如果差值是奇数，说明没有可行的交换方式，因为交换后差值为数字差值的2倍
- 将差值除以2生成diff
- 将第二个数组生成set
- 遍历第一个数组，判断`num - diff`是否在set中存在，如果存在就返回
### 代码
```java
class Solution {
    public int[] findSwapValues(int[] array1, int[] array2) {
        int sum1 = Arrays.stream(array1).sum(),
            sum2 = Arrays.stream(array2).sum(),
            diff = sum1 - sum2;

        if ((diff & 1) == 1) {
            return new int[0];
        }

        diff /= 2;
        Set<Integer> set = Arrays.stream(array2).boxed().collect(Collectors.toSet());
        for (int num : array1) {
            if (set.contains(num - diff)) {
                return new int[]{num, num - diff};
            }
        }
        
        return new int[0];
    }
}
```
# Interview_1622_兰顿蚂蚁
## 题目
一只蚂蚁坐在由白色和黑色方格构成的无限网格上。开始时，网格全白，蚂蚁面向右侧。每行走一步，蚂蚁执行以下操作。
```
(1) 如果在白色方格上，则翻转方格的颜色，向右(顺时针)转 90 度，并向前移动一个单位。
(2) 如果在黑色方格上，则翻转方格的颜色，向左(逆时针方向)转 90 度，并向前移动一个单位。
```
编写程序来模拟蚂蚁执行的前 K 个动作，并返回最终的网格。

网格由数组表示，每个元素是一个字符串，代表网格中的一行，黑色方格由 'X' 表示，白色方格由 '_' 表示，蚂蚁所在的位置由 'L', 'U', 'R', 'D' 表示，分别表示蚂蚁 左、上、右、下 的朝向。只需要返回能够包含蚂蚁走过的所有方格的最小矩形。

示例 1:
```
输入: 0
输出: ["R"]
```
示例 2:
```
输入: 2
输出:
[
  "_X",
  "LX"
]
```
示例 3:
```
输入: 5
输出:
[
  "_U",
  "X_",
  "XX"
]
```
说明：
```
K <= 100000
```
## 解法
### 思路
map
- key记录坐标`x + " " + y`
- value记录黑白或者方向
- 递归遍历并生成这个map
### 代码
```java
class Solution {
    private int dir = 0, top = 0, bottom = 0, left = 0, right = 0;
    private int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private String[] dirChars = {"R", "D", "L", "U"};
    private Map<String, String> map = new HashMap<>();

    public List<String> printKMoves(int K) {
        recurse(0, 0, K);
        List<String> ans = new ArrayList<>();
        for (int i = top; i <= bottom; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = left; j <= right; j++) {
                String path = i + " " + j;
                sb.append(map.getOrDefault(path, "_"));
            }
            ans.add(sb.toString());
        }

        return ans;
    }

    private void recurse(int x, int y, int K) {
        top = Math.min(x, top);
        bottom = Math.max(x, bottom);
        left = Math.min(y, left);
        right = Math.max(y, right);

        if (K == 0) {
            map.put(x + " " + y, dirChars[dir]);
            return;
        }

        String path = x + " " + y;
        String str = map.getOrDefault(path, "_");

        map.put(path, Objects.equals(str, "_") ? "X" : "_");

        if (Objects.equals(str, "_")) {
            dir = (dir + 1) % 4;
        } else {
            dir = (dir + 3) % 4;
        }

        recurse(x + dirs[dir][0], y + dirs[dir][1], K - 1);
    }
}
```
# LeetCode_26_合并K个排序链表
## 题目
合并k个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。

示例:
```
输入:
[
 1->4->5,
 1->3->4,
 2->6
]
输出: 1->1->2->3->4->4->5->6
```
## 解法
### 思路
- 遍历所有链表，将元素值放入优先级队列
- 遍历优先级队列并生成链表
### 代码
```java
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (ListNode list : lists) {
            ListNode node = list;
            while (node != null) {
                queue.offer(node.val);
                node = node.next;
            }
        }
        
        ListNode start = new ListNode(0), pre = start;
        while (!queue.isEmpty()) {
            pre.next = new ListNode(queue.poll());
            pre = pre.next;
        }
        
        return start.next;
    }
}
```
## 解法二
### 思路
分治合并：
- 遍历链表数组，将相邻的两个链表合并
- 最后返回最后合并成的链表
### 代码
```java
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        int len = lists.length;
        if (len == 0) {
            return null;
        }
        
        while (len > 1) {
            int i = 0;

            for (i = 0; i < len / 2; i++) {
                lists[i] = merge(lists[2 * i], lists[2 * i + 1]);
            }

            if ((len & 1) == 1) {
                lists[i] = lists[len - 1];
                len++;
            }

            len /= 2;
        }
        
        return lists[0];
    }
    
    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode start = new ListNode(0), pre = start;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                pre.next = l2;
                l2 = l2.next;
            } else if (l2 == null) {
                pre.next = l1;
                l1 = l1.next;
            } else if (l1.val < l2.val) {
                pre.next = l1;
                l1 = l1.next;
            } else {
                pre.next = l2;
                l2 = l2.next;
            }
            
            pre = pre.next;
        }
        
        return start.next;
    }
}
```
# Interview_1624_数对和
## 题目
设计一个算法，找出数组中两数之和为指定值的所有整数对。一个数只能属于一个数对。

示例 1:
```
输入: nums = [5,6,5], target = 11
输出: [[5,6]]
```
示例 2:
```
输入: nums = [5,6,5,6], target = 11
输出: [[5,6],[5,6]]
```
提示：
```
nums.length <= 100000
```
## 失败解法
### 失败原因
超时
### 思路
嵌套遍历+记忆化搜索
### 代码
```java
class Solution {
    public List<List<Integer>> pairSums(int[] nums, int target) {
        int len = nums.length;
        boolean[] memo = new boolean[len];
        List<List<Integer>> ans = new ArrayList<>();
        
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (memo[i] || memo[j]) {
                    continue;
                }
                
                if (nums[i] + nums[j] == target) {
                    memo[i] = true;
                    memo[j] = true;
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    ans.add(list);
                    break;
                }
            }
        }
        
        return ans;
    }
}
```
## 解法
### 思路
排序+双指针
### 代码
```java
class Solution {
    public List<List<Integer>> pairSums(int[] nums, int target) {
        Arrays.sort(nums);
        int head = 0, tail = nums.length - 1;
        List<List<Integer>> ans = new ArrayList<>();
        
        while (head < tail) {
            int sum = nums[head] + nums[tail];
            
            if (sum < target) {
                head++;
            } else if (sum > target) {
                tail--;
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(nums[head]);
                list.add(nums[tail]);
                ans.add(list);
                head++;
                tail--;
            }
        }
        
        return ans;
    }
}
```