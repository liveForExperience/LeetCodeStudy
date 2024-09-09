# [LeetCode_2181_合并零之间的节点](https://leetcode.cn/problems/merge-nodes-in-between-zeros)
## 解法
### 思路
- 2层循环，外层遍历链表，内层遍历2个0元素之间的所有节点，并进行累加
- 将内层累加的元素值，作为区间第一个0节点的值
- 然后将该节点的next指针指向区间的结尾0节点（也即下一个区间用于承载区间总和值的节点）
- 需要注意当遍历到最后一个区间的时候，通过判断0节点之后是否还有元素来判断是否到了最后一个区间，如果到了，就将next指针指向null
- 遍历结束后，返回head指针指向的节点即可
### 代码
```java
class Solution {
    public ListNode mergeNodes(ListNode head) {
        if (head == null) {
            return null;
        }
        
        ListNode start = head, node = head;
        while (node != null) {
            node = node.next;
            int sum = 0;
            while (node != null && node.val != 0) {
                sum += node.val;
                node = node.next;
            }
            
            start.val = sum;
            start.next = node;
            if (node != null && node.next == null) {
                start.next = null;
                break;
            }
            start = node;
        }

        return head;
    }
}
```