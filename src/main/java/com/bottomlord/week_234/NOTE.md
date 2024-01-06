# [LeetCode_2807_在链表中插入最大公约数](https://leetcode.cn/problems/insert-greatest-common-divisors-in-linked-list)
## 解法
### 思路
- 思考过程：
  - 遍历链表，保证当前节点和节点的next指针指向的节点都不为空
  - 在这种情况下通过gcd算法计算得到最大公约数
  - 基于这个值初始化一个节点，插入到2个节点之间，然后继续遍历即可
- 算法过程：
  - 初始化一个node节点作为遍历时候的指针
  - 通过循环判断`node != null && node.next != null`，如果符合就继续循环
  - 循环中通过gcd算法计算`node.val`和`node.next.val`的最大公约数
  - gcd算法：`x % y == 0 ? y : gcd(y, x % y)`
  - 基于最大公约数初始化一个新节点`gcd`
  - 初始化一个`next`节点指针指向`node.next`
  - 然后通过如下几步实现插入新节点：
    - `node.next = gcd`
    - `gcd.next = next`
  - 然后更新`node`指针为`next`指向的原来的下一个节点，继续循环判断
  - 循环结束后，返回`head`头结点即完成算法
### 代码
```java
class Solution {
    public ListNode insertGreatestCommonDivisors(ListNode head) {
        ListNode node = head;
        while (node != null && node.next != null) {
            ListNode gcd = new ListNode(gcd(node.val, node.next.val)),
                     next = node.next;
            node.next = gcd;
            gcd.next = next;
            node = next;
        }
        
        return head;
    }

    private int gcd(int x, int y) {
        int mod = x % y;
        return mod == 0 ? y : gcd(y, mod);
    }
}
```