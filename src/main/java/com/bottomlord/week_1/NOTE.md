# LeetCode_771_宝石与石头
## 题目
 给定字符串J 代表石头中宝石的类型，和字符串 S代表你拥有的石头。 S 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。

J 中的字母不重复，J 和 S中的所有字符都是字母。字母区分大小写，因此"a"和"A"是不同类型的石头。

示例 1:
```
输入: J = "aA", S = "aAAbbbb"
输出: 3
```
示例 2:
```
输入: J = "z", S = "ZZ"
输出: 0
```
注意:
```
S 和 J 最多含有50个字母。
 J 中的字符不重复。
```
## 理解
宝石数组J好似一张出货单，石头数组S就好比我手上货物的明细单，通过一条条的对照出货单来计算出我要从仓库中拿出哪些货，并记录总数。
## 解法一
### 思路
把数组J整理成一个哈希表，通过哈希表查询时间复杂度O(1)的特性，简化对照出货货物的过程。然后一个个的遍历手上的所有货物，也就是数组S，计算总数
### 代码
```java
class Solution {
    public int numJewelsInStones(String J, String S) {
        Set<Character> jSet = new HashSet<>(J.length());
        for (char c: J.toCharArray()) {
            jSet.add(c);
        }
        int count = 0;
        for (char c: S.toCharArray()) {
            if (jSet.contains(c)) {
                count++;
            }
        }
        return count;
    }
}
```
## 解法二
### 思路
在解法二
### 代码