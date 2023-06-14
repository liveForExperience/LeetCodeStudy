# [LeetCode_1171_从链表中删去总和值为零的连续节点](https://leetcode.cn/problems/remove-zero-sum-consecutive-nodes-from-linked-list/)
## 解法
### 思路
前缀和+hash表
- 通过前缀和可以压缩寻找总和值为0的子序列的时间
- 通过hash表可以将前缀和和对应的节点关系存储起来
- 如上两步可以通过遍历一次链表来处理
- 这里需要关注前缀和与节点关系这一步，因为前缀和相同的对应节点肯定会有多个，那么是否要存储多个节点呢？
- 答案是不需要，因为题目要求的是反复地删除子序列和为0的节点，也就意味着子序列的长度要尽可能的长，那我们可以只存储第一个和最后一个节点，这样他们之间的长度就是最长的了
- 但是只有这个关联关系，处理反复删除的动作还是会非常麻烦，最麻烦的地方就是可能之前删除了一对，现在要删除的一对对应的子序列的一部分是之前子序列的一部分，要过滤掉这些就非常麻烦
- 那么可以尝试只存储前缀和相同的最后一个节点
- 然后再重新遍历并计算一次前缀和，然后判断这次的前缀和是否在hash表中存在，存在的话，就取出对应的节点，做节点删除的操作，然后从被删除节点的后一个节点继续遍历和累加前缀和
- 这样操作，能够保证,删除的子序列是尽可能长的
- 之前一直困扰，如果我删除了这段尽可能长的和为0的序列，那之后继续累加前缀和，出现一个相同前缀和的被删除节点怎么办？后来反应过来，既然总和是0，那么说明删除的头尾的前缀和是一样的，继续累加前缀和，这个对应的节点因为之前覆盖的操作，一定会出现在删除子序列之后
- 题目需要返回链表的头结点，所以需要在一开始初始化一个dummy节点作为假节点，方便最终处理结束后返回
### 代码
```java
class Solution {
    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        Map<Integer, ListNode> map = new HashMap<>();
        int sum = 0;
        ListNode node = dummy;
        while (node != null) {
            sum += node.val;
            map.put(sum, node);
            node = node.next;
        }

        sum = 0;
        node = dummy;
        while (node != null) {
            sum += node.val;

            if (map.containsKey(sum)) {
                node.next = map.get(sum).next;
            }

            node = node.next;
        }

        return dummy.next;
    }
}
```
# [LeetCode_2699_修改图中的边权](https://leetcode.cn/problems/modify-graph-edge-weights/)
## 解法
### 思路
- 按照题目要求，如果是前缀一致，那么就代表除了[1,i]区间内的元素被翻转，其他元素都没有翻转
- 那么，当我遍历到第i个位置，且只有[1,i]的这些元素被翻转了，是不是也意味着，我遍历过的所有元素的最大值只可能是i
- 而这也就意味着：
  - 比i大的元素没有翻转
  - 比i小的元素都翻转了(为什么呢？因为我遍历了i个元素，最大值是i)
- 所以代码就很好写了
  - 维护一个max变量，用于暂存最大值
  - 遍历flips数组，更新max变量
  - 判断下max变量值是否与`当前坐标+1`相等(因为flips中存储的是1到n的元素值)，如果是就累加一次计数值count
  - 遍历结束，返回count值即可
### 代码
```java
class Solution {
    public int numTimesAllBlue(int[] flips) {
        int max = -1, count = 0;
        for (int i = 0; i < flips.length; i++) {
            max = Math.max(max, flips[i]);
            
            if (max == i + 1) {
                count++;
            }
        }
        
        return count;
    }
}
```