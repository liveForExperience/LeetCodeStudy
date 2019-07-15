# LeetCode_344_反转字符串
## 题目
编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。

不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。

你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。

示例 1：
```
输入：["h","e","l","l","o"]
输出：["o","l","l","e","h"]
```
示例 2：
```
输入：["H","a","n","n","a","h"]
输出：["h","a","n","n","a","H"]
```
## 解法一
### 思路
- 头尾双指针遍历
- 互换
### 代码
```java
class Solution {
    public void reverseString(char[] s) {
        int head = 0, tail = s.length - 1;
        while (head < tail) {
            char tmp = s[head];
            s[head++] = s[tail];
            s[tail--] = tmp;
        }
    }
}
```
## 解法二
### 思路
使用异或位运算替换解法一的换位逻辑，实现原地换位。
### 代码
```java
class Solution {
    public void reverseString(char[] s) {
        int head = 0, tail = s.length - 1;
        while (head < tail) {
            s[head] ^= s[tail];
            s[tail] ^= s[head];
            s[head++] ^= s[tail--];
        }
    }
}
```
# LeetCode_933_最近的请求次数
## 题目
写一个 RecentCounter 类来计算最近的请求。

它只有一个方法：ping(int t)，其中 t 代表以毫秒为单位的某个时间。

返回从 3000 毫秒前到现在的 ping 数。

任何处于 [t - 3000, t] 时间范围之内的 ping 都将会被计算在内，包括当前（指 t 时刻）的 ping。

保证每次对 ping 的调用都使用比之前更大的 t 值。

示例：
```
输入：inputs = ["RecentCounter","ping","ping","ping","ping"], inputs = [[],[1],[100],[3001],[3002]]
输出：[null,1,2,3,3]
```
提示：
```
每个测试用例最多调用 10000 次 ping。
每个测试用例会使用严格递增的 t 值来调用 ping。
每次调用 ping 都有 1 <= t <= 10^9。
```
## 解法
### 思路
- 用一个队列存储请求时的t参数
- 循环判断t与队列第一个元素的差：
   - 如果大于3000，不符合题意，出队
   - 否则结束循环
- 将当前t元素入队
- 返回队列长度

整个队列只储存符合题目要求的3000毫秒时间段内的元素。
### 代码
```java
class RecentCounter {
    private Queue<Integer> queue;
    public RecentCounter() {
        this.queue = new ArrayDeque<>();
    }

    public int ping(int t) {
        while (!this.queue.isEmpty()) {
            if (t - this.queue.peek() > 3000) {
                this.queue.poll();
            } else {
                break;
            }
        }

        this.queue.offer(t);
        return this.queue.size();
    }
}
```
