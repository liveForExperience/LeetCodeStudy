# LeetCode_2_两数相加
## 题目
给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。

如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

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
由 n 个连接的字符串 s 组成字符串 S，记作 S = [s,n]。例如，["abc",3]=“abcabcabc”。

如果我们可以从 s2 中删除某些字符使其变为 s1，则称字符串 s1 可以从字符串 s2 获得。例如，根据定义，"abc" 可以从 “abdbec” 获得，但不能从 “acbbe” 获得。

现在给你两个非空字符串 s1 和 s2（每个最多 100 个字符长）和两个整数 0 ≤ n1 ≤ 106 和 1 ≤ n2 ≤ 106。现在考虑字符串 S1 和 S2，其中 S1=[s1,n1] 、S2=[s2,n2] 。

请你找出一个可以满足使[S2,M] 从 S1 获得的最大整数 M 。

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