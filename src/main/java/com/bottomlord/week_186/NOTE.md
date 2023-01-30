# [LeetCode_1632_矩阵转换后的秩](https://leetcode.cn/problems/rank-transform-of-a-matrix/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_1669_合并两个链表](https://leetcode.cn/problems/merge-in-between-linked-lists/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode head = null, tail = null, pre = null, node = list1;
        int i = 0;
        while (node != null) {
            if (i == a) {
                head = pre;
            }

            if (i == b) {
                tail = node.next;
                break;
            }

            pre = node;
            node = node.next;
            i++;
        }

        if (head == null) {
            return null;
        }

        pre.next = null;
        head.next = list2;
        node = list2;
        pre = null;

        while (node != null) {
            pre = node;
            node = node.next;
        }

        if (pre == null) {
            return null;
        }

        pre.next = tail;

        return list1;
    }
}
```