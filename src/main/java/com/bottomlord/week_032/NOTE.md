# LeetCode_470_用Rand7实现Rand10
## 题目
已有方法 rand7 可生成 1 到 7 范围内的均匀随机整数，试写一个方法 rand10 生成 1 到 10 范围内的均匀随机整数。

不要使用系统的 Math.random() 方法。

示例 1:
```
输入: 1
输出: [7]
```
示例 2:
```
输入: 2
输出: [8,4]
```
示例 3:
```
输入: 3
输出: [8,1,10]
```
提示:
```
rand7 已定义。
传入参数: n 表示 rand10 的调用次数。
```
进阶:
```
rand7()调用次数的 期望值 是多少 ?
你能否尽量少调用 rand7() ?
```
## 解法
### 思路
- 通过`rand7()`可以获得两组均匀概率的数字：
    - `rand7()`可以获得`[1,7]`
    - `(rand7() - 1) * rand7()`可以获得`[0, 7, 14, 21, ..., 42]`的等差数列
- 将上述两组均匀概率的数字相加，去`[1, 40]`范围的值，就能获得10进制的均匀概率的值
- 公式`1 + rand7() + (rand7() - 1) * rand7()`，并只取`[1, 40]`
- 返回取余10的结果就是题目要求的答案
### 代码
```java
class Solution extends SolBase {
    public int rand10() {
        int index, col, row;
        do {
            col = rand7();
            row = rand7();
            index = col + (row - 1) * 7;
        } while (index > 40);

        return 1 + (index - 1) % 10;
    }
}
```
## 优化代码
### 思路
- 解法一中，被拒绝的范围是`[41, 49]`，这是一个`[1,9]`的区间
- 如果再加上一个`rand7()`，就能获得`[1, 63]`，这样拒绝的范围就是`[61,63]`，也就是`[1,3]`,这个值就成了新的行
- 如果再加上一个`rand7()`，就能获得`[1, 21]`，这样拒绝的范围就是`21`，这个值就成了新的行
- 这个时候就重新开始求`[1,49]`的随机数
### 代码
```java
/**
 * The rand7() API is already defined in the parent class SolBase.
 * public int rand7();
 * @return a random integer in the range 1 to 7
 */
class Solution extends SolBase {
    public int rand10() {
        while (true) {
            int index = rand7() + (rand7() - 1) * 7;
            if (index <= 40) {
                return 1 + (index - 1) % 10;
            }

            index = rand7() + (index - 40 - 1) * 7;
            if (index <= 60) {
                return 1 + (index - 1) % 10;
            }

            index = rand7() + (index - 60 - 1) * 7;
            if (index <= 20) {
                return 1 + (index - 1) % 10;
            }
        }
    }
}
```