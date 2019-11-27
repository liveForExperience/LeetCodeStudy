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
# LeetCode_912_排序数组
## 题目
给定一个整数数组`nums`，将该数组升序排列。

示例 1：
```
输入：[5,2,3,1]
输出：[1,2,3,5]
```
示例 2：
```
输入：[5,1,1,2,0,0]
输出：[0,0,1,1,2,5]
```
提示：
```
1 <= A.length <= 10000
-50000 <= A[i] <= 50000
```
## 解法
### 思路
使用stream API
### 代码
```java
class Solution {
    public List<Integer> sortArray(int[] nums) {
        return Arrays.stream(nums).boxed().sorted().collect(Collectors.toList());
    }
}
```
## 解法二
### 思路
使用Arrays API
### 代码
```java
class Solution {
    public List<Integer> sortArray(int[] nums) {
        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        return list;
    }
}
```
# LeetCode_495_提莫攻击
## 题目
在《英雄联盟》的世界中，有一个叫 “提莫” 的英雄，他的攻击可以让敌方英雄艾希（编者注：寒冰射手）进入中毒状态。现在，给出提莫对艾希的攻击时间序列和提莫攻击的中毒持续时间，你需要输出艾希的中毒状态总时长。

你可以认为提莫在给定的时间点进行攻击，并立即使艾希处于中毒状态。

示例1:
```
输入: [1,4], 2
输出: 4
原因: 在第 1 秒开始时，提莫开始对艾希进行攻击并使其立即中毒。中毒状态会维持 2 秒钟，直到第 2 秒钟结束。
在第 4 秒开始时，提莫再次攻击艾希，使得艾希获得另外 2 秒的中毒时间。
所以最终输出 4 秒。
```
示例2:
```
输入: [1,2], 2
输出: 3
原因: 在第 1 秒开始时，提莫开始对艾希进行攻击并使其立即中毒。中毒状态会维持 2 秒钟，直到第 2 秒钟结束。
但是在第 2 秒开始时，提莫再次攻击了已经处于中毒状态的艾希。
由于中毒状态不可叠加，提莫在第 2 秒开始时的这次攻击会在第 3 秒钟结束。
所以最终输出 3。
```
注意：
```
你可以假定时间序列数组的总长度不超过 10000。
你可以假定提莫攻击时间序列中的数字和提莫攻击的中毒持续时间都是非负整数，并且不超过 10,000,000。
在真实的面试中遇到过这道题？
```
## 解法
### 思路
- 遍历数组
- 计算遍历到的元素`n`对应窗口的起始`start`和结束`end`，及间隔时间
    - `n`大于暂存的`end`，则直接暂存`start`和`end`，并计算两者的间隔
    - `n`小于等于暂存的`end`，则只更新`end`，并先计算老`end`和新`end`的间隔作为耗时
- 返回结果
### 代码
```java
class Solution {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int end = -1, ans = 0;
        for (int time : timeSeries) {
            if (time > end) {
                ans += duration;
            } else {
                ans += time + duration - 1 - end;
            }
            end = time + duration - 1;
        }
        return ans;
    }
}
```