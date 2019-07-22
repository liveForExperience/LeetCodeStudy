# LeetCode_206_反转链表
## 题目
反转一个单链表。

示例:
```
输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL
```
## 解法一
### 思路
在整个过程中，需要关注的是前后两个节点的，所以需有两个指针
- 一个是pre：记录当前节点需要指向的节点
- 一个是cur：记录当前节点
循环遍历整个链表：
- 初始化两个指针
   - pre = null，头节点的上一个节点是空
   - cur = head
- 从head开始遍历，循环条件是(cur！=null)，循环体内的逻辑：
   - 用一个临时指针next指向cur的next节点(先找到下一个节点，因为cur.next的指针之后会变更指向的对象)
   - cur的next指向pre(反转)
   - pre指向cur(为下一个循环做准备)
   - cur指向next(为下一个循环做准备)
### 代码
```java
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }
}
```
## 解法二
### 思路
递归处理反转
- 一层层下钻到最后1个元素
- 之后每一层返回的时候都返回这个元素
- 同时每一层返回时候，都处理这一层下钻时候当前节点和下一层节点之间的指针关系
   1. 自己的next的next指向自己
   2. 自己的next指向null(因为之后返回时候，在上面一层，自己的上一个会通过步骤1将next指向它)
### 代码
```java
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode end = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return end;
    }
}
```
# LeetCode_521_最长特殊序列I
## 题目
给定两个字符串，你需要从这两个字符串中找出最长的特殊序列。最长特殊序列定义如下：该序列为某字符串独有的最长子序列（即不能是其他字符串的子序列）。

子序列可以通过删去字符串中的某些字符实现，但不能改变剩余字符的相对顺序。空序列为所有字符串的子序列，任何字符串为其自身的子序列。

输入为两个字符串，输出最长特殊序列的长度。如果不存在，则返回 -1。

示例 :
```
输入: "aba", "cdc"
输出: 3
解析: 最长特殊序列可为 "aba" (或 "cdc")
```
说明:
```
两个字符串长度均小于100。
字符串中的字符仅含有 'a'~'z'。
```
## 解法
### 思路
一开始以为很复杂，但最后发现其实就是看谁字符串长，谁就是最长的子序列，这题。。。
### 代码
```java
class Solution {
    public int findLUSlength(String a, String b) {
        if (a.equals(b)) {
            return -1;
        }
        return a.length() > b.length() ? a.length() : b.length();
    }
}
```
# LeetCode_762_二进制表示中质数个数置位
## 题目
给定两个整数 L 和 R ，找到闭区间 [L, R] 范围内，计算置位位数为质数的整数个数。

（注意，计算置位代表二进制表示中1的个数。例如 21 的二进制表示 10101 有 3 个计算置位。还有，1 不是质数。）

示例 1:
```
输入: L = 6, R = 10
输出: 4
解释:
6 -> 110 (2 个计算置位，2 是质数)
7 -> 111 (3 个计算置位，3 是质数)
9 -> 1001 (2 个计算置位，2 是质数)
10-> 1010 (2 个计算置位，2 是质数)
```
示例 2:
```
输入: L = 10, R = 15
输出: 5
解释:
10 -> 1010 (2 个计算置位, 2 是质数)
11 -> 1011 (3 个计算置位, 3 是质数)
12 -> 1100 (2 个计算置位, 2 是质数)
13 -> 1101 (3 个计算置位, 3 是质数)
14 -> 1110 (3 个计算置位, 3 是质数)
15 -> 1111 (4 个计算置位, 4 不是质数)
```
注意:
```
L, R 是 L <= R 且在 [1, 10^6] 中的整数。
R - L 的最大值为 10000。
```
## 解法
### 思路
- 通过num & (num - 1)可以把最低位的1转变为0的特性，循环处理num，并记录处理的次数
- 判断得到的次数是否为质数，并记录“是”的次数，并在循环结束返回
### 代码
```java
class Solution {
    public int countPrimeSetBits(int L, int R) {
        int ans = 0;
        for (int i = L; i <= R; i++) {
            int count = 0;
            int num = i;
            while (num > 0) {
                num = num & (num - 1);
                count++;
            }

            if (isPrime(count)) {
                ans++;
            }
        }

        return ans;
    }

    private boolean isPrime(int num) {
        if (num == 2) {
            return true;
        }

        if (num < 2 || num % 2 == 0) {
            return false;
        }

        for (int i = 3; i <= Math.sqrt(num); i += 2) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }
}
```