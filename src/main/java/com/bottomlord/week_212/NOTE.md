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
# [LeetCode_2681_英雄的力量](https://leetcode.cn/problems/power-of-heroes/)
## 解法
### 思路
- 因为是子序列，所以最终答案实际并不关心原数组顺序，可以对原数组进行排序
- 排序后，假设数组元素排列顺序为`a,b,c,d,e`，a最小，e最大
- 若我们选取d作为要计算的可能子序列的最大值，那么可以得到
  - 如果以a作为最小值，那么abcd，abd，acd等都是可能的结果，且除了a和d之外，其他元素都不参与到power值的计算中，所以实际就可以通过序列长度，计算出以a为最小值，d为最大值的子序列个数，个数计算公式为：`2^(j - i - 1)`，而以a为最小值，d为最大值的power计算公式就是`a * 2 ^ (3 - 0 - 1) * d²`
  - 如果以b作为最小值，与如上同理，公式是`b * 2 * d²`
  - 如果以c作为最小值，公式是`c * d²`
  - 如果以d作为最小值，公式是`d³`
  - 最终以d作为最大值的power值就是：
    - `d³ + c * d² + b * 2 * d² + a * 2² * d²`
    - `d³ + d²(a * 2² + b * 2 + c)`
    - 如果`s = a * 2² + b * 2 + c`
    - `d²(d + s)`
- 如果选取e作为最大值，那么计算过程与如上是一样的，最终可以观察到s的变化
  - `a * 2³ + b * 2² + c * 2 + d`
  - 因为：`s = a * 2² + b * 2 + c`
  - 所以：`2s + d`
  - 而power值为：`e²(e + (2s + d))`
- 观察可以得到，power值的变化，只和当前子序列的以及前一个子序列的最大值相关
- 具体逻辑：
  - 维护一个dp变量，用于暂存如上推到过程中的s值，初始为0
  - dp状态的状态转移方程：`s = 2 * s + nums[i - 1]`
  - 遍历nums数组，套用如上公式计算总值，且不能忘了取模
  - 遍历结束返回总值
### 代码
```java
class Solution {
  public int sumOfPower(int[] nums) {
    long dp = 0, ans = 0, mod = 1000000007;
    Arrays.sort(nums);
    for (int num : nums) {
      ans = (ans + (long) num * num % mod * (num + dp)) % mod;
      dp = (2 * dp + num) % mod;
    }

    return (int)ans;
  }
}
```