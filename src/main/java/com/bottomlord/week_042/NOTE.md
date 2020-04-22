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