# LeetCode_206_反转链表
## 题目
反转一个单链表。

示例:
```
输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL
```
## 解法一
### 思路
在整个过程中，需要关注的是前后两个节点的，所以需有两个指针
- 一个是pre：记录当前节点需要指向的节点
- 一个是cur：记录当前节点
循环遍历整个链表：
- 初始化两个指针
   - pre = null，头节点的上一个节点是空
   - cur = head
- 从head开始遍历，循环条件是(cur！=null)，循环体内的逻辑：
   - 用一个临时指针next指向cur的next节点(先找到下一个节点，因为cur.next的指针之后会变更指向的对象)
   - cur的next指向pre(反转)
   - pre指向cur(为下一个循环做准备)
   - cur指向next(为下一个循环做准备)
### 代码
```java
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }
}
```
## 解法二
### 思路
递归处理反转
- 一层层下钻到最后1个元素
- 之后每一层返回的时候都返回这个元素
- 同时每一层返回时候，都处理这一层下钻时候当前节点和下一层节点之间的指针关系
   1. 自己的next的next指向自己
   2. 自己的next指向null(因为之后返回时候，在上面一层，自己的上一个会通过步骤1将next指向它)
### 代码
```java
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode end = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return end;
    }
}
```
# LeetCode_521_最长特殊序列I
## 题目
给定两个字符串，你需要从这两个字符串中找出最长的特殊序列。最长特殊序列定义如下：该序列为某字符串独有的最长子序列（即不能是其他字符串的子序列）。

子序列可以通过删去字符串中的某些字符实现，但不能改变剩余字符的相对顺序。空序列为所有字符串的子序列，任何字符串为其自身的子序列。

输入为两个字符串，输出最长特殊序列的长度。如果不存在，则返回 -1。

示例 :
```
输入: "aba", "cdc"
输出: 3
解析: 最长特殊序列可为 "aba" (或 "cdc")
```
说明:
```
两个字符串长度均小于100。
字符串中的字符仅含有 'a'~'z'。
```
## 解法
### 思路
一开始以为很复杂，但最后发现其实就是看谁字符串长，谁就是最长的子序列，这题。。。
### 代码
```java
class Solution {
    public int findLUSlength(String a, String b) {
        if (a.equals(b)) {
            return -1;
        }
        return a.length() > b.length() ? a.length() : b.length();
    }
}
```
# LeetCode_762_二进制表示中质数个数置位
## 题目
给定两个整数 L 和 R ，找到闭区间 [L, R] 范围内，计算置位位数为质数的整数个数。

（注意，计算置位代表二进制表示中1的个数。例如 21 的二进制表示 10101 有 3 个计算置位。还有，1 不是质数。）

示例 1:
```
输入: L = 6, R = 10
输出: 4
解释:
6 -> 110 (2 个计算置位，2 是质数)
7 -> 111 (3 个计算置位，3 是质数)
9 -> 1001 (2 个计算置位，2 是质数)
10-> 1010 (2 个计算置位，2 是质数)
```
示例 2:
```
输入: L = 10, R = 15
输出: 5
解释:
10 -> 1010 (2 个计算置位, 2 是质数)
11 -> 1011 (3 个计算置位, 3 是质数)
12 -> 1100 (2 个计算置位, 2 是质数)
13 -> 1101 (3 个计算置位, 3 是质数)
14 -> 1110 (3 个计算置位, 3 是质数)
15 -> 1111 (4 个计算置位, 4 不是质数)
```
注意:
```
L, R 是 L <= R 且在 [1, 10^6] 中的整数。
R - L 的最大值为 10000。
```
## 解法
### 思路
- 通过num & (num - 1)可以把最低位的1转变为0的特性，循环处理num，并记录处理的次数
- 判断得到的次数是否为质数，并记录“是”的次数，并在循环结束返回
### 代码
```java
class Solution {
    public int countPrimeSetBits(int L, int R) {
        int ans = 0;
        for (int i = L; i <= R; i++) {
            int count = 0;
            int num = i;
            while (num > 0) {
                num = num & (num - 1);
                count++;
            }

            if (isPrime(count)) {
                ans++;
            }
        }

        return ans;
    }

    private boolean isPrime(int num) {
        if (num == 2) {
            return true;
        }

        if (num < 2 || num % 2 == 0) {
            return false;
        }

        for (int i = 3; i <= Math.sqrt(num); i += 2) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }
}
```
# LeetCode_136_只出现一次的数字
## 题目
给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。

说明：

你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

示例 1:
```
输入: [2,2,1]
输出: 1
```
示例 2:
```
输入: [4,1,2,1,2]
输出: 4
```
## 解法
### 思路
- 数组排序
- 从第一个元素开始，步长为2，到倒数第二个元素为止，判断当前元素是否和后一个元素相等。

注意如果单独的那个元素是最大的时候，需要避免数组越界。
### 代码
```java
class Solution {
    public int singleNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i += 2) {
            if (nums[i] != nums[i + 1]) {
                return nums[i];
            }
        }

        return nums[nums.length - 1];
    }
}
```
## 解法二
### 思路
- 遍历数组nums，使用map记录每个数字出现的次数
- 遍历map键值对，找到值为1的数并返回
### 代码
```java
class Solution {
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>(nums.length / 2 + 1);
        for (int num: nums) {
            if (map.containsKey(num)) {
                map.computeIfPresent(num, (k, v) -> v+=1);
            } else {
                map.put(num, 1);
            }
        }
        
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if (entry.getValue() < 2) {
                return entry.getKey();
            }
        }
        
        return -1;
    }
}
```
## 优化代码
### 思路
在解法二中记数时候，其实可以通过把出现两次的数字从map中去掉的方法，省去最后遍历的过程，同时可以只是用set。
### 代码
```java
class Solution {
    public int singleNumber(int[] nums) {
        Set<Integer> set = new HashSet<>(nums.length / 2 + 1);
        for (int num: nums) {
            if (set.contains(num)) {
                set.remove(num);
            } else {
                set.add(num);
            }
        }

        return set.iterator().next();
    }
}
```
## 解法三
### 思路
使用异或的特性
### 代码
```java
class Solution {
    public int singleNumber(int[] nums) {
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            ans ^= nums[i];
        }
        return ans;
    }
}
```
# LeetCode_575_分糖果
## 题目
给定一个偶数长度的数组，其中不同的数字代表着不同种类的糖果，每一个数字代表一个糖果。你需要把这些糖果平均分给一个弟弟和一个妹妹。返回妹妹可以获得的最大糖果的种类数。

示例 1:
```
输入: candies = [1,1,2,2,3,3]
输出: 3
解析: 一共有三种种类的糖果，每一种都有两个。
     最优分配方案：妹妹获得[1,2,3],弟弟也获得[1,2,3]。这样使妹妹获得糖果的种类数最多。
```
示例 2 :
```
输入: candies = [1,1,2,3]
输出: 2
解析: 妹妹获得糖果[2,3],弟弟获得糖果[1,1]，妹妹有两种不同的糖果，弟弟只有一种。这样使得妹妹可以获得的糖果种类数最多。
```
注意:
```
数组的长度为[2, 10,000]，并且确定为偶数。
数组中数字的大小在范围[-100,000, 100,000]内。
```
## 解法一
### 思路
妹妹可以分到的糖果数是总数的一半，而可以分到分到的糖果的种类数则可以分为两种情况：
- 糖果种类数大于等于总数的一半，那么就是总数一半
- 糖果总数小于总数，那就是种类总数

所以可以用set去重得到种类数，且如果在遍历过程中超过总数一半，就直接返回总数一半的值。
### 代码
```java
class Solution {
    public int distributeCandies(int[] candies) {
        Set<Integer> set = new HashSet<>();
        
        int len = candies.length / 2;
        for (int num : candies) {
            set.add(num);
            if (set.size() >= len) {
                return len;
            }
        }
        
        return set.size();
    }
}
```
## 优化代码
### 思路
使用桶的思路，替换set，节省拆装箱等的时间
### 代码
```java
class Solution {
    public int distributeCandies(int[] candies) {
        int min = candies[0], max = candies[0];
        for (int i = 1; i < candies.length; i++) {
            min = Math.min(min, candies[i]);
            max = Math.max(max, candies[i]);
        }
        
        boolean[] buckets = new boolean[max - min + 1];
        for (int num : candies) {
            buckets[num - min] = true;
        }
        
        int count = 0;
        int len = candies.length / 2;
        for (boolean b: buckets) {
            if (b) {
                count++;
            }
            
            if (count >= len) {
                return len;
            }
        }
        
        return count;
    }
}
```