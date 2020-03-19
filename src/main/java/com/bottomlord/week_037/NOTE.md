# Interview_0106_字符串压缩
## 题目
字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。比如，字符串aabcccccaaa会变为a2b1c5a3。若“压缩”后的字符串没有变短，则返回原先的字符串。你可以假设字符串中只包含大小写英文字母（a至z）。

示例1:
```
 输入："aabcccccaaa"
 输出："a2b1c5a3"
```
示例2:
```
 输入："abbccd"
 输出："abbccd"
 解释："abbccd"压缩后为"a1b2c2d1"，比原字符串长度更长。
```
提示：
```
字符串长度在[0, 50000]范围内。
```
## 解法
### 思路
嵌套循环：
- 外层确定重复字符的起始位置和字符
- 内层循环迭代所有和起始字符一致的字符，计算个数
- 通过StringBuilder叠加
- 循环结束，与原字符串比较，如果没有原字符串短就返回原字符串，否则返回StringBuilder
### 代码
```java
class Solution {
    public String compressString(String S) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S.length();) {
            char start = S.charAt(i);
            int j = i + 1;
            while (j < S.length() && start == S.charAt(j)) {
                j++;
            }
            sb.append(start).append(j - i);
            i = j;
        }
        return sb.length() >= S.length() ? S : sb.toString();
    }
}
```
# Interview_0107_旋转矩阵
## 题目

给定一幅由N × N矩阵表示的图像，其中每个像素的大小为4字节，编写一种方法，将图像旋转90度。

不占用额外内存空间能否做到？

示例 1:
```
给定 matrix = 
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],

原地旋转输入矩阵，使其变为:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]
```
示例 2:
```
给定 matrix =
[
  [ 5, 1, 9,11],
  [ 2, 4, 8,10],
  [13, 3, 6, 7],
  [15,14,12,16]
], 

原地旋转输入矩阵，使其变为:
[
  [15,13, 2, 5],
  [14, 3, 4, 1],
  [12, 6, 8, 9],
  [16, 7,10,11]
]
```
## 解法
### 思路
- 初始化一个新的二维数组
- 嵌套遍历原来的二维数组，并放入新数组中
    - 从第一列开始到最后一列开始遍历
    - 从最后一行到第一行开始遍历
### 代码
```java
class Solution {
    public void rotate(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        
        int row = matrix.length, col = matrix[0].length;
        int[][] ans = new int[col][row];
        
        for (int i = 0; i < col; i++) {
            for (int j = row - 1; j >= 0; j--) {
                ans[i][row - j - 1] = matrix[j][i];
            }
        }
        
        for (int i = 0; i < row; i++) {
            System.arraycopy(ans[i], 0, matrix[i], 0, col);
        }
    }
}
```
## 解法二
### 思路
矩阵旋转90度，相当于：
1. 对角线反转
2. 左右翻转
### 代码
```java
class Solution {
    public void rotate(int[][] matrix) {
        int row = matrix.length, col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < i; j++) {
                matrix[i][j] += matrix[j][i];
                matrix[j][i] = matrix[i][j] - matrix[j][i];
                matrix[i][j] = matrix[i][j] - matrix[j][i];
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col / 2; j++) {
                matrix[i][j] += matrix[i][col - 1 - j];
                matrix[i][col - 1 - j] = matrix[i][j] - matrix[i][col - 1 - j];
                matrix[i][j] = matrix[i][j] - matrix[i][col - 1 - j];
            }
        }
    }
}
```
# Interview_0108_零矩阵
## 题目
编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。

示例 1：
```
输入：
[
  [1,1,1],
  [1,0,1],
  [1,1,1]
]
输出：
[
  [1,0,1],
  [0,0,0],
  [1,0,1]
]
```
示例 2：
```
输入：
[
  [0,1,2,0],
  [3,4,5,2],
  [1,3,1,5]
]
输出：
[
  [0,0,0,0],
  [0,4,5,0],
  [0,3,1,0]
]
```
## 解法
### 思路
- 遍历二维数组，获得所有为0的坐标
- 初始化2个set，分别存放获得的0的坐标的行与列的值
- 循环结束，遍历两个set，确定需要修改为0的行与列
### 代码
```java
class Solution {
    public void setZeroes(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        
        int row = matrix.length, col = matrix[0].length;
        Set<Integer> rows = new HashSet<>(), cols = new HashSet<>();
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }
        
        for (int i : rows) {
            Arrays.fill(matrix[i], 0);
        }
        
        for (int i : cols) {
            for (int j = 0; j < row; j++) {
                matrix[j][i] = 0;
            }
        }
    }
}
```
## 优化代码
### 思路
使用布尔数组作为哈希表实现，记录需要清零的行列
### 代码
```java
class Solution {
    public void setZeroes(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        int row = matrix.length, col = matrix[0].length;
        boolean[] rows = new boolean[row], cols = new boolean[col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    rows[i] = true;
                    cols[j] = true;
                }
            }
        }

        for (int i = 0; i < row; i++) {
            if (rows[i]) {
                Arrays.fill(matrix[i], 0);
            }
        }

        for (int i = 0; i < col; i++) {
            if (cols[i]) {
                for (int j = 0; j < row; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
    }
}
```
# LeetCode_1160_拼写单词
## 题目
给你一份『词汇表』（字符串数组） words 和一张『字母表』（字符串） chars。

假如你可以用 chars 中的『字母』（字符）拼写出 words 中的某个『单词』（字符串），那么我们就认为你掌握了这个单词。

注意：每次拼写时，chars 中的每个字母都只能用一次。

返回词汇表 words 中你掌握的所有单词的 长度之和。

示例 1：
```
输入：words = ["cat","bt","hat","tree"], chars = "atach"
输出：6
解释： 
可以形成字符串 "cat" 和 "hat"，所以答案是 3 + 3 = 6。
```
示例 2：
```
输入：words = ["hello","world","leetcode"], chars = "welldonehoneyr"
输出：10
解释：
可以形成字符串 "hello" 和 "world"，所以答案是 5 + 5 = 10。
```
提示：
```
1 <= words.length <= 1000
1 <= words[i].length, chars.length <= 100
所有字符串中都仅包含小写英文字母
```
## 解法
### 思路
- 外层遍历词汇表
    - 初始化数组，记录字母表中字符出现的个数
- 内层遍历单词
    - 将遍历到的字符从字母表中减一，如果字母小于0，说明单词无法掌握
    - 如果遍历结束，没有小于0的字符个数，累加结果
### 代码
```java
class Solution {
    public int countCharacters(String[] words, String chars) {
        int[] bucket = new int[256];
        for (char c : chars.toCharArray()) {
            bucket[c]++;
        }
        int ans = 0;
        for (String word : words) {
            int[] tmp = Arrays.copyOf(bucket, bucket.length);
            boolean learn = true;
            for (char c : word.toCharArray()) {
                tmp[c]--;
                if (tmp[c] < 0) {
                    learn = false;
                    break;
                }
            }
            
            if (learn) {
                ans += word.length();
            }
        }
        
        return ans;
    }
}
```
# Interview_0109_字符串轮转
## 题目
字符串轮转。给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成（比如，waterbottle是erbottlewat旋转后的字符串）。

示例1:
```
 输入：s1 = "waterbottle", s2 = "erbottlewat"
 输出：True
```
示例2:
```
 输入：s1 = "aa", "aba"
 输出：False
```
提示：
```
字符串长度在[0, 100000]范围内。
```
说明:
```
你能只调用一次检查子串的方法吗？
```
## 解法
### 思路
- 移动旋转字符串的指针i，判断`i`指向的字符与原字符串的第一个字符是否相等
- 如果相等，尝试比较从当前位置到字符串尾部和从头到当前位置的两部分字符串拼接后，是否与原字符串相等
- 如果不是相同字符串，继续移动`i`，直到找到或`i`越界为止
### 代码
```java
class Solution {
    public boolean isFlipedString(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        if (Objects.equals(s1, "") && Objects.equals(s2, "")) {
            return true;
        }

        int len2 = s2.length();
        for (int i = 0; i < len2; i++) {
            if (s1.charAt(0) == s2.charAt(i)) {
                if (Objects.equals(s1, String.valueOf(s2.toCharArray(), i, len2 - i) + String.valueOf(s2.toCharArray(), 0, i))) {
                    return true;
                }
            }
        }

        return false;
    }
}
```
## 解法二
### 思路
如果`s2`是`s1`旋转后的字符串，那么将两个`s2`拼接后，必然能包含一个完整的字符串`s1`
### 代码
```java
class Solution {
    public boolean isFlipedString(String s1, String s2) {
        return s1.length() == s2.length() && (s2 + s2).contains(s1);
    }
}
```
# Interview_0201_移除重复节点
## 题目
编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。

示例1:
```
 输入：[1, 2, 3, 3, 2, 1]
 输出：[1, 2, 3]
```
示例2:
```
 输入：[1, 1, 1, 1, 2]
 输出：[1, 2]
```
提示：
```
链表长度在[0, 20000]范围内。
链表元素在[0, 20000]范围内。
```
进阶：
```
如果不得使用临时缓冲区，该怎么解决？
```
## 解法
### 思路
- 遍历链表
- hash表缓存遍历过的节点
- 将出现在缓存中的节点去除
### 代码
```java
class Solution {
    public ListNode removeDuplicateNodes(ListNode head) {
        ListNode node = head, fake = new ListNode(0), pre = fake;
        fake.next = head;
        Set<Integer> set = new HashSet<>();
        while (node != null) {
            if (!set.contains(node.val)) {
                set.add(node.val);
                pre = node;
            } else {
                pre.next = node.next;
            }
            node = node.next;
        }
        
        return fake.next;
    }
}
```
## 解法二
### 思路
- 嵌套循环链表
- 外层依次循环每一个节点
- 内层从外层遍历的节点开始依次循环剩余链表，如果返现遍历到的节点与外层节点值相等，就做删除操作
### 代码
```java
class Solution {
    public ListNode removeDuplicateNodes(ListNode head) {
        ListNode node = head;
        while (node != null) {
            ListNode innerNode = node.next, pre = node;
            while (innerNode != null) {
                if (node.val == innerNode.val) {
                    pre.next = innerNode.next;
                } else {
                    pre = innerNode;
                }
                innerNode = innerNode.next;
            }
            node = node.next;
        }
        return head;
    }
}
```
# Interview_0202_返回倒数第k个节点
## 题目

实现一种算法，找出单向链表中倒数第 k 个节点。返回该节点的值。

注意：本题相对原题稍作改动

示例：
```
输入： 1->2->3->4->5 和 k = 2
输出： 4
```
说明：
```
给定的 k 保证是有效的。
```
## 解法
### 思路
- 遍历单向链表
    - 计算链表长度
    - 使用动态数组记录节点值
- 返回第`len - k`个节点
### 代码
```java
class Solution {
    public int kthToLast(ListNode head, int k) {
        List<Integer> list = new ArrayList<>();
        ListNode node = head;
        int len = 0;
        while (node != null) {
            len++;
            list.add(node.val);
            node = node.next;
        }
        
        return list.get(len - k);
    }
}
```
## 解法二
### 思路
遍历两次链表：
- 第一次计算长度
- 第二次根据长度找到倒数第K个节点为止
### 代码
```java
class Solution {
    public int kthToLast(ListNode head, int k) {
        ListNode node = head;
        int len = 0;
        while (node != null) {
            len++;
            node = node.next;
        }
        
        node = head;
        int num = len - k;
        while (num-- > 0) {
            node = node.next;
        }
        
        return node.val;
    }
}
```
# Interview_0203_删除中间节点
## 题目
实现一种算法，删除单向链表中间的某个节点（除了第一个和最后一个节点，不一定是中间节点），假定你只能访问该节点。

示例：
```
输入：单向链表a->b->c->d->e->f中的节点c
结果：不返回任何数据，但该链表变为a->b->d->e->f
```
## 解法
### 思路
- 因为是中间节点，不用考虑节点为空的情况
- 将下一个节点的值赋给当前节点
- 将当前节点的指针指向下一个节点的下一个节点
### 代码
```java
class Solution {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
```
# Interview_0204_分割链表
## 题目
编写程序以 x 为基准分割链表，使得所有小于 x 的节点排在大于或等于 x 的节点之前。如果链表中包含 x，x 只需出现在小于 x 的元素之后(如下所示)。分割元素 x 只需处于“右半部分”即可，其不需要被置于左右两部分之间。

示例:
```
输入: head = 3->5->8->5->10->2->1, x = 5
输出: 3->1->2->10->5->5->8
```
## 解法
### 思路
- 5个指针：
    - 遍历指针：node
    - 左半部分假头：fakeLeftHead
    - 左半部分指针：left
    - 右半部分假头：fakeRightHead
    - 右半部分指针：right
- 过程：
    - 遍历链表，比较当前节点值与x的大小
    - 将对应指针指向当前节点，并重新建立关系
    - 遍历结束需要处理`right`的`next`为空，否则会不断循环溢出
### 代码
```java
class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode fakeLeftHead = new ListNode(0), fakeRightHead = new ListNode(0), left = null, right  = null, node = head;
        
        while (node != null) {
            if (node.val < x) {
                if (left == null) {
                    left = node;
                    fakeLeftHead.next = left;
                } else {
                    left.next = node;
                    left = left.next;
                }
            } else {
                if (right == null) {
                    right = node;
                    fakeRightHead.next = right;
                } else {
                    right.next = node;
                    right = right.next;
                }
            }
            node = node.next;
        }
        
        
        if (right != null) {
            right.next = null;
        }
        
        if (left == null) {
            return fakeRightHead.next;
        }
        
        left.next = fakeRightHead.next;
        return fakeLeftHead.next;
    }
}
```
# Interview_0205_链表求和
## 题目
给定两个用链表表示的整数，每个节点包含一个数位。

这些数位是反向存放的，也就是个位排在链表首部。

编写函数对这两个整数求和，并用链表形式返回结果。

示例：
```
输入：(7 -> 1 -> 6) + (5 -> 9 -> 2)，即617 + 295
输出：2 -> 1 -> 9，即912
进阶：假设这些数位是正向存放的，请再做一遍。
```
示例：
```
输入：(6 -> 1 -> 7) + (2 -> 9 -> 5)，即617 + 295
输出：9 -> 1 -> 2，即912
```
## 解法
### 思路
- 初始一个链表节点作为答案节点的头节点
- 初始化指针
    - `pre`用来遍历和连接这个答案链表
    - `head`代表答案节点的假头指针
- 同时遍历两个入参链表
    - 如果两个节点同时存在：
        - 计算2个节点的值以及进位的和sum
        - 如果大于10，记录进位为1，否则进位为0
    - 如果有一个节点不存在：
        - 计算存在的那个节点值和进位的和
    - 初始化值为sum的节点，与`pre`连接
    - `pre`移动到当前节点
- 遍历结束，如果`carry`为1，要再加一位节点，值为1
- 返回头指针的`next`节点
### 代码
```java
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0), pre = head;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int sum = 0;
            if (l1 == null) {
                sum += l2.val + carry;
                l2 = l2.next;
            } else if (l2 == null) {
                sum += l1.val + carry;
                l1 = l1.next;
            } else {
                sum += l1.val + l2.val + carry;
                l1 = l1.next;
                l2 = l2.next;
            }
            
            carry = sum >= 10 ? 1 : 0;
            sum %= 10;
            
            ListNode cur = new ListNode(sum);
            pre.next = cur;
            pre = cur;
        }
        
        if (carry == 1) {
            pre.next = new ListNode(1);
        }
        
        return head.next;
    }
}
```