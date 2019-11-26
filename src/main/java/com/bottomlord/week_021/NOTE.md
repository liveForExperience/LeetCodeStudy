# LeetCode_445_两数相加
## 题目
给定两个非空链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储单个数字。将这两数相加会返回一个新的链表。

你可以假设除了数字 0 之外，这两个数字都不会以零开头。

进阶:
```
如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
```
示例:
```
输入: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
输出: 7 -> 8 -> 0 -> 7
```
## 解法
### 思路
- 两个链表分别倒置
- 从新链表的头指针开始遍历
- 两个不同链表的节点相加，暂存进位值，并使用各位生成结果链表的节点值
- 遍历过程中还需要再倒置以下新结果的链表
- 遍历结束后，查看进位是否为1，如果是就在结果链表前再加一个值为1的节点
### 代码
```java
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l = reserve(l1);
        ListNode r = reserve(l2);

        int carry = 0;
        ListNode cur = null, pre;
        while (l != null || r != null) {
            int lnum = l == null ? 0 : l.val;
            int rnum = r == null ? 0 : r.val;

            int val = lnum + rnum + carry;
            carry = val / 10;

            pre = cur;
            cur = new ListNode(val % 10);
            cur.next = pre;

            l = l == null ? null : l.next;
            r = r == null ? null : r.next;
        }

        if (carry == 1) {
            ListNode head = new ListNode(1);
            head.next = cur;
            return head;
        }
        
        return cur;
    }

    private ListNode reserve(ListNode node) {
        ListNode pre = null;
        while (node != null) {
            ListNode next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        return pre;
    }
}
```
## 解法二
### 思路
- 使用两个栈来保存两个链表的节点值
- 遍历两个栈，生成链表并反转
- 判断carry是否有值，如果有就在链表前加一个节点
### 代码
```java
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> ls = getStack(l1);
        Stack<Integer> rs = getStack(l2);

        int carry = 0;
        ListNode cur = null, pre = null;
        while (!ls.isEmpty() || !rs.isEmpty()) {
            int val = carry;
            if (!ls.isEmpty()) {
                val += ls.pop();
            }
            if (!rs.isEmpty()) {
                val += rs.pop();
            }

            carry = val / 10;
            pre = cur;
            cur = new ListNode(val % 10);
            cur.next = pre;
        }

        if (carry == 1) {
            ListNode head = new ListNode(1);
            head.next = cur;
            return head;
        }
        
        return cur;
    }

    private Stack<Integer> getStack(ListNode node) {
        Stack<Integer> stack = new Stack<>();
        while (node != null) {
            stack.push(node.val);
            node = node.next;
        }
        return stack;
    }
}
```