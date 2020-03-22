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
# Interview_0206_回文链表
## 题目
编写一个函数，检查输入的链表是否是回文的。

示例 1：
```
输入： 1->2
输出： false 
```
示例 2：
```
输入： 1->2->2->1
输出： true 
```
进阶：
```
你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
```
## 解法
### 思路
- 遍历链表记录值，放入list
- 双指针从两头遍历list，判断值是否指针对应的值是否相等
### 代码
```java
class Solution {
    public boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            list.add(node.val);
            node = node.next;
        }
        
        int left = 0, right = list.size() - 1;
        while (left < right) {
            if (!Objects.equals(list.get(left), list.get(right))) {
                return false;
            }
            left++;
            right--;
        }
        
        return true;
    }
}
```
## 解法二
### 思路
- 生成一个反转链表
- 两个链表比较
### 代码
```java
class Solution {
    public boolean isPalindrome(ListNode head) {
        ListNode node = head, pre = null;
        while (node != null) {
            ListNode cur = new ListNode(node.val);
            cur.next = pre;
            pre = cur;
            node = node.next;
        }
        
        while (pre != null && head != null) {
            if (pre.val != head.val) {
                return false;
            }
            pre = pre.next;
            head = head.next;
        }
        
        return pre == null && head == null;
    }
}
```
## 解法三
### 思路
- 使用快慢指针，找到链表的中间节点
    - 如果链表长度是偶数的，也就是最后快指针不为null，那么需要再移动一次慢指针
- 从新的盘指针的为止开始翻转链表
- 最终从原链表的两头开始比较节点的值
### 代码
```java
class Solution {
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        if (fast != null) {
            slow = slow.next;
        }

        ListNode right = slow == null ? head : slow;
        while (slow != null) {
            if (slow == right) {
                slow = slow.next;
                right.next = null;
                continue;
            }
            ListNode next = slow.next;
            slow.next = right;
            right = slow;
            slow = next;
        }
        
        ListNode left = head;
        while (left != null && right != null) {
            if (left.val != right.val) {
                return false;
            }
            left = left.next;
            right = right.next;
        }
        
        return true;
    }
}
```
# Interview_0207_链表相交
## 题目
给定两个（单向）链表，判定它们是否相交并返回交点。请注意相交的定义基于节点的引用，而不是基于节点的值。换句话说，如果一个链表的第k个节点与另一个链表的第j个节点是同一节点（引用完全相同），则这两个链表相交。

示例 1：
```
输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
输出：Reference of the node with value = 8
输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
```
示例 2：
```
输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
输出：Reference of the node with value = 2
输入解释：相交节点的值为 2 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
```
示例 3：
```
输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
输出：null
输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
解释：这两个链表不相交，因此返回 null。
```
注意：
```
如果两个链表没有交点，返回 null 。
在返回结果后，两个链表仍须保持原有的结构。
可假定整个链表结构中没有循环。
程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
```
## 解法
### 思路
- 同时遍历两个链表
- 如果一个链表遍历到最后节点，那就继续从另一个链表的头节点点开始遍历
- 如果它们相等，就说明它们相遇或都为null。
    - 如果有相交部分的链表，那么这种走法，在相交节点前，它们路过的节点数是一样的`A不同部分 + B不同部分 + 相同部分`
    - 如果没有相交的部分，那么这种走法，在两个指针同时为空时，它们走过的路程是一样的`A链表全长 + B链表全长`
### 代码
```java
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode nodeA = headA, nodeB = headB;
        while (nodeA != nodeB) {
            if (nodeA == null) {
                nodeA = headB;
            } else {
                nodeA = nodeA.next;
            }
            
            if (nodeB == null) {
                nodeB = headA;
            } else {
                nodeB = nodeB.next;
            }
        }
        
        return nodeA;
    }
}
```
# Interview_0208_环路检测
## 题目
给定一个有环链表，实现一个算法返回环路的开头节点。
```
有环链表的定义：在链表中某个节点的next元素指向在它前面出现过的节点，则表明该链表存在环路。
```
示例 1：
```
输入：head = [3,2,0,-4], pos = 1
输出：tail connects to node index 1
解释：链表中有一个环，其尾部连接到第二个节点。
```
示例 2：
```
输入：head = [1,2], pos = 0
输出：tail connects to node index 0
解释：链表中有一个环，其尾部连接到第一个节点。
```
示例 3：
```
输入：head = [1], pos = -1
输出：no cycle
解释：链表中没有环。
```
进阶：
```
你是否可以不用额外空间解决此题？
```
## 解法
### 思路
使用快慢指针：
- 将有环链表的距离分解成：
    - 链表头`head`到环的起始点`start`的距离是`a`
    - `start`到快漫之阵相遇的点`meet`的距离是`b`
    - `meet`到`start`的距离是`c`
- 在快慢指针第一次相遇的时候，快慢指针各自走的距离
    - `slow`：`a + b`
    - `fast`：`a + (b + c) * k + b`，其中`k`代表快指针绕圈的次数，且一定大于等于1，否则就和慢指针走的距离一样，不符合题意
- 因为快指针走的距离是慢指针的2倍，所以得到等式
```math
(a + b) * 2 = a + (b + c) * k + b
```
- 简化后得到
```math
a = (k - 1) * (b + c) + c
```
- 如上等式中，因为`(k - 1) * (b + c)`中`b + c`在题目中代表的是环的总距离，所以可以简化为`a = c`
- 所以当找到两个指针的相遇节点后，再让一个节点从`head`出发，一个从`meet`出发，同时同速，那么它们相遇时就是入环点`start`
- 过程：
    - 通过快慢指针同时遍历，找到`meet`或`fast`为null，判断没有相遇点
    - 然后从`head`和`meet`同时遍历，找到入环点`start`
### 代码
```java
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                break;
            }
        }

        if (fast == null || fast.next == null) {
            return null;
        }
        
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        
        return slow;
    }
}
```
# Interview_0301_三合一
## 题目
三合一。描述如何只用一个数组来实现三个栈。

你应该实现push(stackNum, value)、pop(stackNum)、isEmpty(stackNum)、peek(stackNum)方法。stackNum表示栈下标，value表示压入的值。

构造函数会传入一个stackSize参数，代表每个栈的大小。

示例1:
```
 输入：
["TripleInOne", "push", "push", "pop", "pop", "pop", "isEmpty"]
[[1], [0, 1], [0, 2], [0], [0], [0], [0]]
 输出：
[null, null, null, 1, -1, -1, true]
说明：当栈为空时`pop, peek`返回-1，当栈满时`push`不压入元素。
```
示例2:
```
 输入：
["TripleInOne", "push", "push", "push", "pop", "pop", "pop", "peek"]
[[2], [0, 1], [0, 2], [0, 3], [0], [0], [0], [0]]
 输出：
[null, null, null, null, 2, 1, -1, -1]
```
## 解法
### 思路
- 初始化变量：
    - 长度为`stackSize`的3倍，作为三个栈的存储空间
    - 一个数组，存储三个栈指针
- 根据栈的编号查找栈指针，并操作压栈出栈的操作
### 代码
```java
class TripleInOne {
    private int[] stack;
    private int[] indexs;
    private int stackSize;

    public TripleInOne(int stackSize) {
        this.stackSize = stackSize;
        stack = new int[3 * stackSize];
        indexs = new int[3];

        for (int i = 0; i < indexs.length; i++) {
            indexs[i] = i * stackSize - 1;
        }
    }

    public void push(int stackNum, int value) {
        if (indexs[stackNum] >= (stackNum + 1) * stackSize - 1) {
            return;
        }

        stack[++indexs[stackNum]] = value;
    }

    public int pop(int stackNum) {
        if (isEmpty(stackNum)) {
            return -1;
        }

        return stack[indexs[stackNum]--];
    }

    public int peek(int stackNum) {
        if (isEmpty(stackNum)) {
            return -1;
        }

        return stack[indexs[stackNum]];
    }

    public boolean isEmpty(int stackNum) {
        return indexs[stackNum] < stackNum * stackSize;
    }
}
```
# Interview_0302_栈的最小值
## 题目
请设计一个栈，除了常规栈支持的pop与push函数以外，还支持min函数，该函数返回栈元素中的最小值。执行push、pop和min操作的时间复杂度必须为O(1)。

示例：
```
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> 返回 -3.
minStack.pop();
minStack.top();      --> 返回 0.
minStack.getMin();   --> 返回 -2.
```
## 解法
### 思路
- 初始化变量：
    - 一个栈`data`用来维护数据
    - 一个栈`stack`用来最为单向栈，保存最小值
- 过程：
    - 如果压栈：查看value是否小于stack的栈顶元素，如果是就压入栈中
    - 如果弹栈，查看value是否为栈顶元素，如果是，就弹出stack的栈顶元素
### 代码
```java
class MinStack {
    private Stack<Integer> data;
    private Stack<Integer> stack;
    public MinStack() {
        this.data = new Stack<>();
        this.stack = new Stack<>();
    }

    public void push(int x) {
        if (stack.isEmpty() || stack.peek() >= x) {
            stack.push(x);
        }

        data.push(x);
    }

    public void pop() {
        if (data.isEmpty()) {
            return;
        }

        if (!stack.isEmpty() && Objects.equals(data.peek(), stack.peek())) {
            stack.pop();
        }

        data.pop();
    }

    public int top() {
        return data.isEmpty() ? -1 : data.peek();
    }

    public int getMin() {
        return stack.isEmpty() ? -1 : stack.peek();
    }
}
```
## 解法二
### 思路
使用一个栈同时保存值和最小值：
- 初始变量
    - 一个变量`min`暂存当前的最小值
    - 一个栈存储值和以前的最小值
- 过程：
    - 压栈：判断压入的值与当前最小值的大小，如果小等于就将当前最小值压入栈中，并将当前值作为最小值暂存在`min`中
    - 弹栈：如果出栈值和当前值相等，就将栈顶元素弹出，并将第二个元素也弹出，作为当前的最小值
### 代码
```java
class MinStack {
    private int min;
    private Stack<Integer> stack;
    public MinStack() {
        this.min = Integer.MAX_VALUE;
        this.stack = new Stack<>();
    }

    public void push(int x) {
        if (x <= min) {
            stack.push(min);
            min = x;
        }

        stack.push(x);
    }

    public void pop() {
        if (stack.pop() == min) {
            min = stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}
```
# LeetCode_365_水壶问题
## 题目
有两个容量分别为 x升 和 y升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，从而可以得到恰好 z升 的水？

如果可以，最后请用以上水壶中的一或两个来盛放取得的 z升 水。

你允许：
```
装满任意一个水壶
清空任意一个水壶
从一个水壶向另外一个水壶倒水，直到装满或者倒空
```
示例 1: (From the famous "Die Hard" example)
```
输入: x = 3, y = 5, z = 4
输出: True
```
示例 2:
```
输入: x = 2, y = 6, z = 5
输出: False
```
## 解法
### 思路
bfs：
- 两个水壶的状态：
    - x被倒满
    - y被倒满
    - x被清空
    - y被清空
    - x的水倒满了y，x还有剩余
    - y的水倒满了x，y还有剩余
    - x的水全倒入y，x被清空
    - y的水全倒入x，y被清空
- 定义一个类`state`定义当前两个水壶的状态
- 使用set对bfs的过程剪纸
- 状态变化的过程中，判断每种状态与对应的现有状况是否相符
### 代码
```java
class Solution {
    public boolean canMeasureWater(int x, int y, int z) {
        if (z == 0) {
            return true;
        }

        if (x + y < z) {
            return false;
        }

        Queue<State> queue = new ArrayDeque<>();
        Set<State> memo = new HashSet<>();

        State init = new State(0, 0);
        queue.offer(init);
        memo.add(init);

        while (!queue.isEmpty()) {
            int count = queue.size();

            while (count-- != 0) {
                State state = queue.poll();

                if (state == null) {
                    continue;
                }

                int curX = state.x, curY = state.y;
                if (curX == z || curY == z || curX + curY == z) {
                    return true;
                }

                for (State next : getNextStates(x, y, curX, curY)) {
                    if (!memo.contains(next)) {
                        queue.offer(next);
                        memo.add(next);
                    }
                }
            }
        }

        return false;
    }

    private List<State> getNextStates(int x, int y, int curX, int curY) {
        List<State> nextStates = new ArrayList<>();

        State fullX = new State(x, curY);
        State fullY = new State(curX, y);
        State emptyX = new State(0, curY);
        State emptyY = new State(curX, 0);
        State x2yLeft = new State(curX - (y - curY), y);
        State y2xLeft = new State(x, curY - (x - curX));
        State x2yNoLeft = new State(0, curX + curY);
        State y2xNotLeft = new State(curX + curY, 0);

        if (curX < x) {
            nextStates.add(fullX);
        }

        if (curY < y) {
            nextStates.add(fullY);
        }

        if (curX > 0) {
            nextStates.add(emptyX);
        }

        if (curY > 0) {
            nextStates.add(emptyY);
        }

        if (curX - (y - curY) > 0) {
            nextStates.add(x2yLeft);
        }

        if (curY - (x - curX) > 0) {
            nextStates.add(y2xLeft);
        }

        if (curX + curY < y) {
            nextStates.add(x2yNoLeft);
        }

        if (curX + curY < x) {
            nextStates.add(y2xNotLeft);
        }

        return nextStates;
    }

    private class State {
        private int x;
        private int y;

        public State(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            State state = (State) o;
            return x == state.x &&
                    y == state.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
```
## 解法二
### 思路
贝祖定理：
- 找到x和y的最大公约数gcd，如果z是gcd的倍数，那么就可以满足
```math
ax + by = z
```
### 代码
```java
class Solution {
    public boolean canMeasureWater(int x, int y, int z) {
        if (x == 0 && y == 0) {
            return z == 0;
        }
        
        return z == 0 || (z % gcd(x, y) == 0 && x + y >= z);
    }
    
    private int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }
}
```
# Interview_0303_堆盘子
## 题目
堆盘子。设想有一堆盘子，堆太高可能会倒下来。因此，在现实生活中，盘子堆到一定高度时，我们就会另外堆一堆盘子。请实现数据结构SetOfStacks，模拟这种行为。SetOfStacks应该由多个栈组成，并且在前一个栈填满时新建一个栈。此外，SetOfStacks.push()和SetOfStacks.pop()应该与普通栈的操作方法相同（也就是说，pop()返回的值，应该跟只有一个栈时的情况一样）。 进阶：实现一个popAt(int index)方法，根据指定的子栈，执行pop操作。

当某个栈为空时，应当删除该栈。当栈中没有元素或不存在该栈时，pop，popAt 应返回 -1.

示例1:
```
 输入：
["StackOfPlates", "push", "push", "popAt", "pop", "pop"]
[[1], [1], [2], [1], [], []]
 输出：
[null, null, null, 2, 1, -1]
```
示例2:
```
 输入：
["StackOfPlates", "push", "push", "push", "popAt", "popAt", "popAt"]
[[2], [1], [2], [3], [0], [0], [0]]
 输出：
[null, null, null, null, 2, 1, 3]
```
## 解法
### 思路
- 链表中存放数组
- 数组长度为`cap + 1`
- 数组第一位用来存放当前数组的元素指针
### 代码
```java
class StackOfPlates {
    private LinkedList<int[]> list;
    private int cap;
    private int size;
    public StackOfPlates(int cap) {
        this.cap = cap;
        this.list = new LinkedList<>();
        this.list.add(new int[cap + 1]);
    }

    public void push(int val) {
        if (cap == 0) {
            return;
        }
        
        if (list.get(size)[0] == cap) {
            list.add(new int[cap + 1]);
            size++;
        }

        int[] stack = list.get(size);
        stack[0]++;
        stack[stack[0]] = val;
    }

    public int pop() {
        if (size == 0 && list.getFirst()[0] == 0) {
            return -1;
        }

        int[] stack = list.get(size);
        int num =  stack[stack[0]--];
        if (stack[0] == 0 && size != 0) {
            list.removeLast();
            size--;
        }
        return num;
    }

    public int popAt(int index) {
        if (size < index || (size == 0 && list.getFirst()[0] == 0)) {
            return -1;
        }

        int[] stack = list.get(index);
        int num = stack[stack[0]--];
        if (stack[0] == 0 && size != 0) {
            list.remove(index);
            size--;
        }
        return num;
    }
}
```
# Interview_0304_化栈为队
## 题目
实现一个MyQueue类，该类用两个栈来实现一个队列。

示例：
```
MyQueue queue = new MyQueue();

queue.push(1);
queue.push(2);
queue.peek();  // 返回 1
queue.pop();   // 返回 1
queue.empty(); // 返回 false
```
说明：
```
你只能使用标准的栈操作 -- 也就是只有 push to top, peek/pop from top, size 和 is empty 操作是合法的。
你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）。
```
## 解法
### 思路
两个栈模拟一个队列
### 代码
```java
class MyQueue {
    private Stack<Integer> in;
    private Stack<Integer> out;
    public MyQueue() {
        this.in = new Stack<>();
        this.out = new Stack<>();
    }

    public void push(int x) {
        in.push(x);
    }

    public int pop() {
        if (empty()) {
            return -1;
        }

        if (!out.isEmpty()) {
            return out.pop();
        }

        while (!in.isEmpty()) {
            out.push(in.pop());
        }

        return out.pop();
    }

    public int peek() {
        if (empty()) {
            return -1;
        }

        if (!out.isEmpty()) {
            return out.peek();
        }

        while (!in.isEmpty()) {
            out.push(in.pop());
        }

        return out.peek();
    }

    public boolean empty() {
        return in.isEmpty() && out.isEmpty();
    }
}
```
# LeetCode_945_使数组唯一的最小增量
## 题目
给定整数数组 A，每次 move 操作将会选择任意 A[i]，并将其递增 1。

返回使 A 中的每个值都是唯一的最少操作次数。

示例 1:
```
输入：[1,2,2]
输出：1
解释：经过一次 move 操作，数组将变为 [1, 2, 3]。
```
示例 2:
```
输入：[3,2,1,2,1,7]
输出：6
解释：经过 6 次 move 操作，数组将变为 [3, 4, 1, 2, 5, 7]。
可以看出 5 次或 5 次以下的 move 操作是不能让数组的每个值唯一的。
```
提示：
```
0 <= A.length <= 40000
0 <= A[i] < 40000
```
## 解法
### 思路
- 排序数组
- 遍历数组，记录遍历到的最大值
- 如果当前元素小于最大值，就累加到最大值+1（因为题目要求只能自增，所以所有被遍历到的数字只能增加到比最大值大1就可以确保数字唯一）
- 记录累加的值
- 遍历结束后返回累加值
### 代码
```java
class Solution {
    public int minIncrementForUnique(int[] A) {
        Arrays.sort(A);
        int max = -1, sum = 0;
        for (int num : A) {
            if (num <= max) {
                sum += (max - num + 1);
                max++;
            } else {
                max = num;
            }
        }
        return sum;
    }
}
```
## 解法二
### 思路
并查集：
- 初始化一个并查集：
    - 初始化并查集中小标对应的值都为-1，代表没有值
    - 并查集union时，必须是小的合到大的值里
    - 将遍历到的值放入并查集中时，需要查看当前值左右两边的值是否在并查集中，如果是，就要union
- 遍历数组
    - 如果并查集中不包含当前值，将值放入
    - 如果有值，就查找到当前这个值的parent值，加1后计算差值，进行累加
    - 同时将这个加1后的值放入并查集中
### 代码
```java
class Solution {
    public int minIncrementForUnique(int[] A) {
        UnionFind unionFind = new UnionFind();
        int ans = 0;
        for (int num : A) {
            if (unionFind.contains(num)) {
                int root = unionFind.find(num);
                ans += root - num + 1;
                unionFind.init(root + 1);
            } else {
                unionFind.init(num);
            }
        }
        
        return ans;
    }

    private class UnionFind {
        private int[] parent;

        public UnionFind() {
            parent = new int[79999];
            Arrays.fill(parent, -1);
        }

        public void init(int num) {
            parent[num] = num;

            if (num > 0 && parent[num - 1] != -1) {
                union(num - 1, num);
            }

            if (parent[num + 1] != -1) {
                union(num, num + 1);
            }
        }

        public boolean contains(int num) {
            return parent[num] != -1;
        }
        
        public int find(int num) {
            if (num != parent[num]) {
                parent[num] = find(parent[num]);
            }

            return parent[num];
        }

        public void union(int x, int y) {
            parent[parent[x]] = find(y);
        }
    }
}
```
# Interview_0305_栈排序
## 题目
栈排序。 编写程序，对栈进行排序使最小元素位于栈顶。最多只能使用一个其他的临时栈存放数据，但不得将元素复制到别的数据结构（如数组）中。该栈支持如下操作：push、pop、peek 和 isEmpty。当栈为空时，peek 返回 -1。

示例1:
```
 输入：
["SortedStack", "push", "push", "peek", "pop", "peek"]
[[], [1], [2], [], [], []]
 输出：
[null,null,null,1,null,2]
```
示例2:
```
 输入： 
["SortedStack", "pop", "pop", "push", "pop", "isEmpty"]
[[], [], [], [1], [], []]
 输出：
[null,null,null,null,null,true]
```
说明:
```
栈中的元素数目在[0, 5000]范围内。
```
## 解法
### 思路
单向栈：
- 初始两个栈：
    - 栈a存储元素
    - 栈b用来辅助排序
- push：
    - 如果元素小于栈顶元素，就直接压入栈中
    - 如果大于栈顶元素，就将栈a的元素弹出，知道栈顶元素大于元素，将元素压入栈a后，将栈b的元素弹出压回到栈a中
- pop：直接弹出栈a的元素
### 代码
```java
class SortedStack {
    private Stack<Integer> a;
    private Stack<Integer> b;
    public SortedStack() {
        this.a = new Stack<>();
        this.b = new Stack<>();
    }

    public void push(int val) {
        if (a.isEmpty() || val < a.peek()) {
            a.push(val);
            return;
        }

        while (!a.isEmpty() && a.peek() < val) {
            b.push(a.pop());
        }

        a.push(val);

        while (!b.isEmpty()) {
            a.push(b.pop());
        }
    }

    public void pop() {
        if (!a.isEmpty()) {
           a.pop();
        }
    }

    public int peek() {
        return a.isEmpty() ? -1 : a.peek();
    }

    public boolean isEmpty() {
        return a.isEmpty();
    }
}
```