# [LeetCode_143_重排链表](https://leetcode.cn/problems/reorder-list/)
## 解法
### 思路
- 初始化一个数组来存储node节点
- 遍历单链表
  - 计算出链表长度
  - 将节点存入数组中
- 根据链表长度，从头尾相向遍历数组，按题目要求依次重新排列
- 遍历循环的条件是`start <= end`
- 循环退出后，记得处理新链表结尾的next指针
  - 如果长度为奇数，则头尾指针一定是相交后退出，在相交时，做结尾节点的next置空处理
  - 如果长度为偶数，则在循环结束后，一定是start指针对应的位置结尾节点，对该节点的next指针做置空处理
### 代码
```java
class Solution {
    public void reorderList(ListNode head) {
        ListNode[] nodes = new ListNode[50001];
        int cnt = 0;
        ListNode node = head;
        while (node != null) {
            nodes[cnt++] = node;
            node = node.next;
        }

        int start = 0, end = cnt - 1;
        ListNode fake = new ListNode(0), pre = fake;
        while (start <= end) {
            pre.next = nodes[start];
            
            if (start != end) {
                nodes[start].next = nodes[end];
            }
            
            pre = nodes[end];

            if (start == end) {
                nodes[start].next = null;
                return;
            }

            start++;
            end--;
        }

        nodes[start].next = null;
    }
}
```