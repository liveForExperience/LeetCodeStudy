# LeetCode_275_H指数II
## 题目
给定一位研究者论文被引用次数的数组（被引用次数是非负整数），数组已经按照 升序排列 。编写一个方法，计算出研究者的 h 指数。

h 指数的定义: “h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （N 篇论文中）总共有 h 篇论文分别被引用了至少 h 次。（其余的 N - h 篇论文每篇被引用次数不多于 h 次。）"

示例:
```
输入: citations = [0,1,3,5,6]
输出: 3 
解释: 给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 0, 1, 3, 5, 6 次。
     由于研究者有 3 篇论文每篇至少被引用了 3 次，其余两篇论文每篇被引用不多于 3 次，所以她的 h 指数是 3。

说明:

如果 h 有多有种可能的值 ，h 指数是其中最大的那个。
```
进阶：
```
这是 H 指数 的延伸题目，本题中的 citations 数组是保证有序的。
你可以优化你的算法到对数时间复杂度吗？
```
## 解法
### 思路
解法和274相同
### 代码
```java
class Solution {
    public int hIndex(int[] citations) {
        int len = citations.length, i = 0;
        while (i < len && citations[len - 1 - i] > i) {
            i++;
        }
        
        return i;
    }
}
```
## 解法二
### 思路
二分查找：
- 找到h指数的最理想状态：`citations[i] == len - i`，说明达到引用次数的论文数与引用次数完全相等，如果引用数再大一点，就没有足够的论文了
- 找到h指数次理想的状态：`citations[i] < len - i`，说明当前拥有该引用数的论文比引用数多，那么可能存在更大的引用数，但是结果更大的引用数对应的论文数不够了，那么就以当前值为结果
- 综上所述，可以将这个寻找的过程转换成二分查找：
    - 计算中间值`mid`，判断`citations[mid]`与`len - mid`之间的关系
        - 如果相等，直接返回`len - mid`
        - 如果小于，则找右边，可能还有更大值
        - 如果大于，则找左边，当前引用数过大了
- 最终如果循环内没有找到，则返回次优解，也就是`len - mid`
### 代码
```java
class Solution {
    public int hIndex(int[] citations) {
        int len = citations.length;
        int mid, head = 0, tail = len - 1;

        while (head <= tail) {
            mid = head + (tail - head) / 2;

            if (citations[mid] == len - mid) {
                return len - mid;
            } else if (citations[mid] < len - mid) {
                head = mid + 1;
            } else {
                tail = mid - 1;
            }
        }

        return len - head;
    }
}
```