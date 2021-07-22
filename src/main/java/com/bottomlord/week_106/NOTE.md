# [LeetCode_1838_最高频元素的频数](https://leetcode-cn.com/problems/frequency-of-the-most-frequent-element/)
## 解法
### 思路
排序+滑动窗口
- 对数组排序，是的数组成为一个升序序列
- 这样就可以通过遍历的方式，判断要变化的目标值，也就是滑动窗口右边界
- 初始化滑动窗口的左右指针，分别为0和1
- 遍历过程中，累加达到右边界需要的变化数：
    - 每次移动右边界的时候，都需要累加右边界变化时增加的数，也就是`(nums[r] - nums[r - 1]) * (r - l)`
    - 然后判断total（累加的变化值）的大小是否大于了K，如果大了，就移动左边界，缩小total直到小于等于K为止
    - 然后比较暂存窗口大小和当前窗口大小的大小，取较大值暂存
- 需要注意
    - total要用long值否则会溢出
    - ans最小值是1
### 代码
```java
class Solution {
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int l = 0, n = nums.length, ans = 1;
        long total = 0;
        for (int r = 1; r < n; r++) {
            total += (long) (nums[r] - nums[r - 1]) * (r - l);
            while (total > k) {
                total -= (nums[r] - nums[l]);
                l++;
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }
}
```
# [LeetCode_1342_将数字变成0的操作次数](https://leetcode-cn.com/problems/number-of-steps-to-reduce-a-number-to-zero/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
  public int numberOfSteps(int num) {
    int count = 0;
    while (num > 0) {
      if (num % 2 == 0) {
        num /= 2;
      } else {
        num--;
      }

      count++;
    }
    return count;
  }
}
```
# [LeetCode_1877_数组中最大数对和的最小值](https://leetcode-cn.com/problems/minimize-maximum-pair-sum-in-array/)
## 解法
### 思路
排序后头尾匹配，求最大值
### 代码
```java
class Solution {
  public int minPairSum(int[] nums) {
    Arrays.sort(nums);
    int head = 0, tail = nums.length - 1, max = Integer.MIN_VALUE;
    while (head < tail) {
      max = Math.max(nums[head++] + nums[tail--], max);
    }

    return max;
  }
}
```
# [LeetCode_1346_检查整数及其两倍数是否存在](https://leetcode-cn.com/problems/check-if-n-and-its-double-exist/)
## 错误解法
### 原因
解法错误，0的2倍是本身，不能直接判断
### 思路
- 遍历数组，将元素存储在set中
- 遍历set，查找是否存在是当前元素2倍的元素
- 有就返回true，否则false
### 代码
```java
class Solution {
    public boolean checkIfExist(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int num : arr) {
            set.add(num);
        }
        for (int num : set) {
            if (set.contains(num * 2)) {
                return true;
            }
        }
        
        return false;
    }
}
```
## 解法
### 思路
在错误解法上增加一个变量记录0出现的个数，如果只出现1次，则遍历到0的时候，直接跳过，否则0出现多余1次，直接返回true
### 代码
```java
class Solution {
    public boolean checkIfExist(int[] arr) {
        Set<Integer> set = new HashSet<>();
        int zero = 0;
        for (int num : arr) {
            if (num == 0) {
                zero++;
                if (zero > 1) {
                    return true;
                }
            }
            
            set.add(num);
        }
        
        for (int num : set) {
            if (num == 0) {
                continue;
            }
            
            if (set.contains(num * 2)) {
                return true;
            }
        }
        
        return false;
    }
}
```
## 解法二
### 思路
数组代替set
- 元素大小在-1000到1000之间，所以数组长度初始化为2001
- 遍历数组，将当前元素*2+1000的值作为2倍后的值，将当前元素+1000的值作为当前值
- 然后在数组中查找
    - 当前值/2的元素是否已经计数，这里还有个前提，当前值必须是偶数，否则不可能是被*2得到的数
    - 2倍值的元素是否已经计数，这里有个前提，如果求出的*2的数大于-1000到1000的范围，也就是0到2000的范围，那么这种元素根本不可能出现在数组中，就不需要考虑
    - 如果如上有计数，就返回true
### 代码
```java
class Solution {
  public boolean checkIfExist(int[] arr) {
    int[] count = new int[2001];
    for (int num : arr) {
      int d = num * 2 + 1000;
      if (d >= 0 && d <= 2000 && count[d] > 0) {
        return true;
      }

      if (num % 2 == 0 && count[num / 2 + 1000] > 0) {
        return true;
      }

      count[num +1000]++;
    }

    return false;
  }
}
```
# [LeetCode_1351_统计有序矩阵中的负数](https://leetcode-cn.com/problems/count-negative-numbers-in-a-sorted-matrix/)
## 解法
### 思路
- 遍历二维数组，按行遍历，从尾部开始遍历，直到找到第一个非负整数位置，计数累加
- 一行行计算累加，最后返回
### 代码
```java
class Solution {
    public int countNegatives(int[][] grid) {
                int col = grid[0].length;
        int count = 0;
        for (int[] rows : grid) {
            for (int c = col - 1; c >= 0; c--) {
                if (rows[c] >= 0) {
                    break;
                }
                count++;
            }
        }
        return count;
    }
}
```
## 解法二
### 思路
- 一行行遍历判断
- 特殊情况：
  - 第一个元素是负数，那么整行都是负数
  - 最后一个元素是非负数，那么整行都不是负数
- 通常情况：通过二分查找找到第一个负数，然后直接累加这个负数到尾部的长度
### 代码
```java
class Solution {
    public int countNegatives(int[][] grid) {
        int col = grid[0].length, count = 0;
        for (int[] row : grid) {
            if (row[0] < 0) {
                count += col;
                continue;
            }
            
            if (row[col - 1] >= 0) {
                continue;
            }
            
            int head = 0, tail = col - 1;
            while (head <= tail) {
                int mid = head + (tail - head) / 2;
                if (row[mid] < 0) {
                    tail = mid - 1;
                } else {
                    head = mid + 1;
                }
            }
            
            count += col - head;
        }
        
        return count;
    }
}
```
# [LeetCode_offer52_1_两个链表的第一个公共节点](https://leetcode-cn.com/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/)
## 解法
### 思路
- 初始化两个了链表的头结点
- 然后同时以1的步长一起遍历两个链表
- 任意一个链表走到头，就从另一个链表的头节点继续走
- 如果同时是null，说明没有公共节点，否则，遍历到第一个相同的节点，就是公共节点
- 注意特殊判断，任意一个链表为空的情况，直接返回null
### 代码
```java
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode a = headA, b = headB;
        while (a != b) {
            a = a.next;
            b = b.next;
            
            if (a == null && b == null) {
                return null;
            }
            
            if (a == null) {
                a = headB;
            }
            
            if (b == null) {
                b = headA;
            }
        }
        
        return a;
    }
}
```
## 解法二
### 思路
基于解法一的思路简化代码
### 代码
```java
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
                ListNode a = headA, b = headB;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        
        return a;
    }
}
```
# [LeetCode_138_复制带随机指针的链表](https://leetcode-cn.com/problems/copy-list-with-random-pointer/)
## 解法
### 思路
- 遍历原链表进行复制，将next指针指向新的节点，random指针指向原来的节点
- 遍历过程中存储原节点和新节点的映射关系
- 遍历新节点，通过映射表将原节点替换成新节点
### 代码
```java
class Solution {
  public Node copyRandomList(Node head) {
    Node node = head;
    Node newHead = new Node(0);
    Node newNode = newHead;

    Map<Node, Node> mapping = new HashMap<>();

    while (node != null) {
      newNode.next = new Node(node.val);
      newNode = newNode.next;
      newNode.random = node.random;
      mapping.put(node, newNode);
      node = node.next;
    }

    node = newHead.next;
    while (node != null) {
      node.random = mapping.get(node.random);
      node = node.next;
    }

    return newHead.next;
  }
}
```