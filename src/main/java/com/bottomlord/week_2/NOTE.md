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
# LeetCode_944_删列造序
## 题目
给定由 N 个小写字母字符串组成的数组 A，其中每个字符串长度相等。

删除 操作的定义是：选出一组要删掉的列，删去 A 中对应列中的所有字符，形式上，第 n 列为 [A[0][n], A[1][n], ..., A[A.length-1][n]]）。

比如，有 A = ["abcdef", "uvwxyz"]，

要删掉的列为 {0, 2, 3}，删除后 A 为["bef", "vyz"]， A 的列分别为["b","v"], ["e","y"], ["f","z"]。

你需要选出一组要删掉的列 D，对 A 执行删除操作，使 A 中剩余的每一列都是 非降序 排列的，然后请你返回 D.length 的最小可能值。

示例 1：
```
输入：["cba", "daf", "ghi"]
输出：1
```
解释：
```
当选择 D = {1}，删除后 A 的列为：["c","d","g"] 和 ["a","f","i"]，均为非降序排列。
若选择 D = {}，那么 A 的列 ["b","a","h"] 就不是非降序排列了。
```
示例 2：
```
输入：["a", "b"]
输出：0
解释：D = {}
```
示例 3：
```
输入：["zyx", "wvu", "tsr"]
输出：3
解释：D = {0, 1, 2}
```
提示：
```
1 <= A.length <= 100
1 <= A[i].length <= 1000
```
## 解法一
### 思路
有一些记录状态的临时变量
- 记录上一个字符的pre，初始为0
- 记录是否需要计数的flag，初始为false
- 记录计数值的count

嵌套循环
- 外层是字符串字符数组
- 内层是字符串数组
- 如果当前字符比pre小，那么就中断内层的循环，同时将flag置为true，代表回到外层时需要进行计数了，因为找到不符合要求的列了。
- 通过flag的状态，决定是否count++，通过在外层循环的最后将pre和flag置为初始值，为下一列的字符判断做准备。

判断字符串的当前字符是否大于上一个字符，如果符合就计数+1。时间复杂度是O(N²)
### 代码
```java
class Solution {
    public int minDeletionSize(String[] A) {
        char pre = 0;
        boolean flag = false;
        int count = 0; 
        
        for (int i = 0; i < A[0].length(); i++) {
            for (String s : A) {
                if (s.charAt(i) < pre) {
                    flag = true;
                    break;
                }

                pre = s.charAt(i);
            }
            
            if (flag) {
                count++;
            }
            
            flag = false;
            pre = 0;
        }
        
        return count;
    }
}
```
## 解法二
### 思路
解法一中的pre和flag变量可以省去

注意：
- 将迭代器换成普通for循环，使用指针
- 内层循环从1开始，防止字符数组越界
### 代码
```java
class Solution {
    public int minDeletionSize(String[] A) {
        int count = 0;

        for (int i = 0; i < A[0].length(); i++) {
            for (int j = 1; j < A.length; j++) {
                if (A[j].charAt(i) < A[j - 1].charAt(i)) {
                    count++;
                    break;
                }
            }
        }

        return count;
    }
}
```
速度反而比解法一慢了
## 解法三
### 思路
观察了解法二的代码，发现时String的charAt方法比较耗时。应该保留解法一的pre变量，同时变量的位置放在第一层循环开始，这样内层循环结束后就不需要再初始化变量。
### 代码
```java
class Solution {
    public int minDeletionSize(String[] A) {
        int count = 0;

        for (int i = 0; i < A[0].length(); i++) {
            char pre = 0;
            for(String s: A){
                char cur = s.charAt(i);
                
                if (cur < pre) {
                    count++;
                    break;
                }
                
                pre = cur;
            }
        }

        return count;
    }
}
```