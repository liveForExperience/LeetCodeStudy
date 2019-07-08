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
在国内站看到的解法，不需要利用hash表，直接通过String的replace方法，将宝石数组J中的字符从石头数组S中去除掉，那么S字符串的长度差就是宝石的个数。
### 代码
```java
class Solution {
    public int numJewelsInStones(String J, String S) {
        String[] jss = J.split("");
        int len = S.length();
        for (String s: jss) {
            S = S.replace(s, "");
        }
        
        return len - S.length();
    }
}
```
# LeetCode_237_删除链表中的节点
## 题目
请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。

现有一个链表 -- head = [4,5,1,9]

示例 1:
```
输入: head = [4,5,1,9], node = 5
输出: [4,1,9]
解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
```
示例 2:
```
输入: head = [4,5,1,9], node = 1
输出: [4,5,9]
解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
```
说明:
```
链表至少包含两个节点。
链表中所有节点的值都是唯一的。
给定的节点为非末尾节点并且一定是链表中的一个有效节点。
不要从你的函数中返回任何结果。
```
## 理解
从单向链表中删除节点
## 解法
### 思路
链表删除节点，因为不用考虑尾节点的问题，可以直接如下操作：
- 将后一个节点的值赋给当前节点
- 将当前节点的指针指向后一个的后一个节点(相当于跳过了后一个节点)
### 代码
```java
class Solution {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
```
# LeetCode_938_二叉搜索树的范围和
## 题目
给定二叉搜索树的根结点 root，返回 L 和 R（含）之间的所有结点的值的和。

二叉搜索树保证具有唯一的值。

示例 1：
```
输入：root = [10,5,15,3,7,null,18], L = 7, R = 15
输出：32
```
示例 2：
```
输入：root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
输出：23
```
提示：
```
树中的结点数量最多为 10000 个。
最终的答案保证小于 2^31。
```
## 理解
对树的遍历搜索
## 解法一
### 思路
- 因为是二叉搜索树，所以dfs中序遍历是按照升序排列
- 因为求的是LR之间的所有数的和，所以LR之间的数就是升序序列的一部分，是升序排列的

**过程**：在通过dfs中序遍历的同时，用一个临时变量count来累加>=L和<=R的value。最终返回count。
### 代码
```java
class Solution {
    public int rangeSumBST(TreeNode root, int L, int R) {
        return count(root, L, R, 0);
    }

    private int count(TreeNode node, int L, int R, int count) {
        if (node == null) {
            return count;
        }
        int value = node.val;

        if (value >= L && value <= R) {
            count += value;
        }

        count = count(node.left, L, R, count);
        count = count(node.right, L, R, count);

        return count;
    }
}
```
## 解法二
### 思路
解法一中有一个可以优化的地方，就是真正需要遍历的点其实就是L到R的节点。而因为二叉搜索树的特性，所以通过遍历的当前节点与L或R进行比较，就可以优化搜索路径。
- 如果value小于L，说明当前节点的左子树不需要再去搜索，直接搜索右子树
- 如果value大于R，说明当前节点的右子树不需要再去搜索，直接搜索左子树
### 代码
```java
class Solution {
    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        }

        if (root.val < L) {
            return rangeSumBST(root.right, L, R);
        }

        if (root.val > R) {
            return rangeSumBST(root.left, L, R);
        }

        return root.val + rangeSumBST(root.left, L, R) + rangeSumBST(root.right, L, R);
    }
}
```