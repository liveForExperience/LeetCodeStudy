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
# LeetCode_709_转换成小写字母
## 题目
实现函数 ToLowerCase()，该函数接收一个字符串参数 str，并将该字符串中的大写字母转换成小写字母，之后返回新的字符串。

示例 1：
```
输入: "Hello"
输出: "hello"
```
示例 2：
```
输入: "here"
输出: "here"
```
示例 3：
```
输入: "LOVELY"
输出: "lovely"
```
## 解法一
### 思路
1. 遍历整个字符数组
2. 把大写改成小写
### 代码
```java
class Solution {
    public String toLowerCase(String str) {
        char[] cs = str.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] >= 65 && cs[i] <= 90) {
                cs[i] += 32;
            }
        }
        return new String(cs);
    } 
}
```
## 解法二
### 思路
直接用jdk的现成方法。。。
### 代码
```java
class Solution {
    public String toLowerCase(String str) {
        return str.toLowerCase();
    }
}
```
# LeetCode_1108_IP地址无效化
## 题目
给你一个有效的 IPv4 地址 address，返回这个 IP 地址的无效化版本。

所谓无效化 IP 地址，其实就是用 "[.]" 代替了每个 "."。
 
示例 1：
```
输入：address = "1.1.1.1"
输出："1[.]1[.]1[.]1"
```
示例 2：
```
输入：address = "255.100.50.0"
输出："255[.]100[.]50[.]0"
```
提示：
```
给出的 address 是一个有效的 IPv4 地址
```
## 解法一
### 思路
遍历字符数组，同时使用StringBuilder进行append，如果碰到"."就套上"[]"。
### 代码
```java
class Solution {
    public String defangIPaddr(String address) {
        StringBuilder sb = new StringBuilder();
        for (char c: address.toCharArray()) {
            if (c == '.') {
                sb.append('[');
                sb.append(c);
                sb.append(']');
            } else {
                sb.append(c);
            }
        }
        
        return sb.toString();
    }
}
```
## 解法二
### 思路
使用String的replace方法
### 代码
```java
class Solution {
    public String defangIPaddr(String address) {
        return address.replace(".", "[.]");
    }
}
```
### 结果
相比较解法一，这个解法的耗时更慢，内存消耗也略微更多。
# LeetCode_804_唯一摩尔斯密码词
## 题目
国际摩尔斯密码定义一种标准编码方式，将每个字母对应于一个由一系列点和短线组成的字符串， 比如: "a" 对应 ".-", "b" 对应 "-...", "c" 对应 "-.-.", 等等。

为了方便，所有26个英文字母对应摩尔斯密码表如下：

[".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."]
给定一个单词列表，每个单词可以写成每个字母对应摩尔斯密码的组合。例如，"cab" 可以写成 "-.-..--..."，(即 "-.-." + "-..." + ".-"字符串的结合)。我们将这样一个连接过程称作单词翻译。

返回我们可以获得所有词不同单词翻译的数量。

例如:
```
输入: words = ["gin", "zen", "gig", "msg"]
输出: 2
```
解释: 
```
各单词翻译如下:
"gin" -> "--...-."
"zen" -> "--...-."
"gig" -> "--...--."
"msg" -> "--...--."
共有 2 种不同翻译, "--...-." 和 "--...--.".
```
## 解法
### 思路
1. 使用数组保存从a到z的摩尔斯密码
2. 迭代字符串数组，拼接当前字符串所对应的摩尔斯密码串
3. 放入一个HashSet进行去重
4. 迭代结束后返回HashSet的长度
### 代码
```java
class Solution {
    public int uniqueMorseRepresentations(String[] words) {
        String[] dict = new String[]{".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        Set<String> set = new HashSet<>();
        for (String word: words) {
            StringBuilder sb = new StringBuilder();
            for (char c: word.toCharArray()) {
                sb.append(dict[c - 'a']);
            }
            set.add(sb.toString());
        }
        return set.size();
    }
}
```
# LeetCode_832_反转图像
## 题目
给定一个二进制矩阵 A，我们想先水平翻转图像，然后反转图像并返回结果。

水平翻转图片就是将图片的每一行都进行翻转，即逆序。例如，水平翻转 [1, 1, 0] 的结果是 [0, 1, 1]。

反转图片的意思是图片中的 0 全部被 1 替换， 1 全部被 0 替换。例如，反转 [0, 1, 1] 的结果是 [1, 0, 0]。

示例 1:
```
输入: [[1,1,0],[1,0,1],[0,0,0]]
输出: [[1,0,0],[0,1,0],[1,1,1]]
解释: 首先翻转每一行: [[0,1,1],[1,0,1],[0,0,0]]；
     然后反转图片: [[1,0,0],[0,1,0],[1,1,1]]
```
示例 2:
```
输入: [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
输出: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
解释: 首先翻转每一行: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]]；
     然后反转图片: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
```
说明:
```
1 <= A.length = A[0].length <= 20
0 <= A[i][j] <= 1
```
## 解法
### 思路
嵌套循环二维数组，对一维数组进行换位和反转的动作，时间复杂度是O(n^2)
### 代码
```java
class Solution {
    public int[][] flipAndInvertImage(int[][] A) {
        for (int[] row: A) {
            int last = row.length - 1;
            for (int i = 0; i <= last - i; i++) {
                if (i == last - i) {
                    row[i] = 1 - row[i];
                } else {
                    row[i] = (1- row[i]) ^ (1 - row[last - i]);
                    row[last - i] = row[i] ^ (1 - row[last - i]);
                    row[i] = row[i] ^ row[last - i];
                }
                
            }
        }
        
        return A;
    }
}
```
# LeetCode_617_合并二叉树
## 题目
给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。

你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。

示例 1:
```
输入: 
	Tree 1                     Tree 2                  
          1                         2                             
         / \                       / \                            
        3   2                     1   3                        
       /                           \   \                      
      5                             4   7                  
```
输出: 
```
合并后的树:
	     3
	    / \
	   4   5
	  / \   \ 
	 5   4   7
注意: 合并必须从两个树的根节点开始。
```
## 解法一
### 思路
一开始做的时候有点懵，两个树同时搜索吗？这怎么玩？后来发现其实可以这么思考：
- 首先使用dfs搜索
- 搜索的过程中，如果左右子树的任意一个是null，那么就可以直接把另一个的接上就可以了，不用下钻了。
- 如果都不是null，那么就生成一个树节点，这个树节点是作为结果的树的一部分，它记录：
  -  两个树在下钻过程中遍历到的节点的值的和
   - 同时指向下一层递归后返回的节点，将树连起来
### 代码
```java
class Solution {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        
        if (t2 == null) {
            return t1;
        }
        
        TreeNode node = new TreeNode(t1.val + t2.val);
        node.left = mergeTrees(t1.left, t2.left);
        node.right = mergeTrees(t1.right, t2.right);
        return node;
    }
}
```
## 解法二
### 思路
和解法一一样，使用dfs，但不同点是非递归。而难点也是，如果要同时遍历，那么如何实现。在看了国际站的解法后整理如下：
- 首先栈所放入的元素不再是普通一棵树的题目那种一个节点，而是需要放入一个节点数组
- 通过循环弹栈获取数组来带动遍历搜索的过程，同时可以使得两棵树的节点的信息在同一时间同时处理
- 之后就开始具体的逻辑处理，最终的结果是在t1的基础上返回合并后的树：
   1. 如果弹出的数组中的元素都非空，将两个节点的值相加，同时将两棵树的两个子树按照左左、右右的关系组成数组放入栈中
   2. 如果弹出的数组中的代表t1的子节点的元素(第一个元素)是空，那么就应该把t1的子节点的指针指向元素2，**但是这时候没办法操作指针了**
   3. 所以第1步要修改，指针变化的动作要在第1步做了，判断数组中t1的节点(第一个元素)的左右子树是否是空:
      - 如果是，就把它指向左右子树的指针指向t2相应的左右子树(t2不用判断空，因为只要t1的节点是空了，t1就替换成t2的节点，无论是否为空)。
      - 如果不是，就组成数组，放入栈
   4. 同时加上一步continue的动作，因为t1的节点为空的时候被t2的节点替换了，没有放入栈，所以当第2个元素为空的时候，就代表当前为止只有t1有节点，不用处理了
- 最终返回t1节点。
### 代码
```java
class Solution {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }

        Stack<TreeNode[]> stack = new Stack<>();
        stack.push(new TreeNode[]{t1, t2});
        while (!stack.empty()) {
            TreeNode[] nodes = stack.pop();
            if (nodes[1] == null) {
                continue;
            }

            nodes[0].val += nodes[1].val;

            if (nodes[0].left == null) {
                nodes[0].left = nodes[1].left;
            } else {
                stack.push(new TreeNode[]{nodes[0].left, nodes[1].left});
            }

            if (nodes[0].right == null) {
                nodes[0].right = nodes[1].right;
            } else {
                stack.push(new TreeNode[]{nodes[0].right, nodes[1].right});
            }
        }

        return t1;
    }
}
```
## 解法三
### 思路
使用bfs替换解法二的dfs，其他步骤基本不变
### 代码
```java
class Solution {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }

        Queue<TreeNode[]> queue = new ArrayDeque<>();
        TreeNode[] root = new TreeNode[]{t1, t2};
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode[] nodes = queue.poll();
            if (nodes[1] == null) {
                continue;
            }

            nodes[0].val += nodes[1].val;
            if (nodes[0].left == null) {
                nodes[0].left = nodes[1].left;
            } else {
                queue.offer(new TreeNode[]{nodes[0].left, nodes[1].left});
            }

            if (nodes[0].right == null) {
                nodes[0].right = nodes[1].right;
            } else {
                queue.offer(new TreeNode[]{nodes[0].right, nodes[1].right});
            }
        }

        return t1;
    }
}
```
# LeetCode_657_机器人能否返回原点
## 题目
在二维平面上，有一个机器人从原点 (0, 0) 开始。给出它的移动顺序，判断这个机器人在完成移动后是否在 (0, 0) 处结束。

移动顺序由字符串表示。字符 move[i] 表示其第 i 次移动。机器人的有效动作有 R（右），L（左），U（上）和 D（下）。如果机器人在完成所有动作后返回原点，则返回 true。否则，返回 false。

注意：机器人“面朝”的方向无关紧要。 “R” 将始终使机器人向右移动一次，“L” 将始终向左移动等。此外，假设每次移动机器人的移动幅度相同。

示例 1:
```
输入: "UD"
输出: true
解释：机器人向上移动一次，然后向下移动一次。所有动作都具有相同的幅度，因此它最终回到它开始的原点。因此，我们返回 true。
```
示例 2:
```
输入: "LL"
输出: false
解释：机器人向左移动两次。它最终位于原点的左侧，距原点有两次 “移动” 的距离。我们返回 false，因为它在移动结束时没有返回原点。
```
## 解法一
### 思路
- 用一个一维数组的两个元素来记录横轴和纵轴上的移动情况，左右上下的移动分别增减代表两个轴的元素。
- 最终遍历这个数组，查看两个元素是否都等于0
### 代码
```java
class Solution {
    public boolean judgeCircle(String moves) {
        int[] record = new int[2];
        for(char c: moves.toCharArray()) {
            if (c == 'L') {
                record[0] += 1;    
            }
            
            if (c == 'R') {
                record[0] -= 1;
            }
            
            if (c == 'U') {
                record[1] += 1;
            }
            
            if (c == 'D') {
                record[1] -= 1;
            }
        }
        
        for (int i: record) {
            if (i != 0) {
                return false;
            }
        }
        
        return true;
    }
}
```
## 解法二
### 思路
解法一我为什么要用数组呢？醉了。直接两个变量不就好啦！
### 代码
```java
class Solution {
        public boolean judgeCircle(String moves) {
        int x = 0, y = 0;
        for (char c: moves.toCharArray()) {
            if (c == 'L') {
                x++;
            }

            if (c == 'R') {
                x--;
            }

            if (c == 'U') {
                y++;
            }

            if (c == 'D') {
                y--;
            }
        }

        return x == 0 && y == 0;
    }
}
```
# LeetCode_461_汉明距离
## 题目
两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。

给出两个整数 x 和 y，计算它们之间的汉明距离。

注意：
```
0 ≤ x, y < 231.
```
示例:
```
输入: x = 1, y = 4
输出: 2
```
解释:
```
1   (0 0 0 1)
4   (0 1 0 0)
       ↑   ↑
上面的箭头指出了对应二进制位不同的位置。
```
## 解法
### 思路
用到两个位操作的技巧
- 异或操作，使得不同的位为1，相同的为0
- x & (x - 1)将最低位的1变为0

使用如上的技巧
- 先两个数异或，得到不同的位为1的数
- 循环的将最低位的数变为0，直到这个数为0为止
- 循环时候计数，最后返回计数结果
### 代码
```java
class Solution {
    public int hammingDistance(int x, int y) {
        int a = x ^ y, count = 0;
        while (a != 0) {
            count++;
            a = a & (a - 1);
        }
        return count;
    }
}
```
# LeetCode_226_翻转二叉树
## 题目
翻转一棵二叉树。

示例：
```
输入：
     4
   /   \
  2     7
 / \   / \
1   3 6   9

输出：
     4
   /   \
  7     2
 / \   / \
9   6 3   1
```
备注:

这个问题是受到 Max Howell 的 原问题 启发的 ：
> 谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。
## 解法一
### 思路
使用dfs递归方式
- 如果为空就返回
- 否则节点指针左右替换一下
- 左右子树下钻下一层
### 代码
```java
class Solution {
    public TreeNode invertTree(TreeNode root) {
        invert(root);
        return root;
    }
    
    private void invert(TreeNode node) {
        if (node == null) {
            return;
        }
        
        TreeNode tmp = node.left;
        node.left = node.right;
        node.right = tmp;
        
        invert(node.left);
        invert(node.right);
    }
}
```
## 解法二
### 思路
dfs非递归方式，循环体的逻辑和解法一类似
### 代码
```java
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
            
            if (node.left != null) {
                stack.push(node.left);
            }
            
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        
        return root;
    }
}
```
# LeetCode_977_有序数组的平方
## 题目
给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。

示例 1：
```
输入：[-4,-1,0,3,10]
输出：[0,1,9,16,100]
```
示例 2：
```
输入：[-7,-3,2,3,11]
输出：[4,9,9,49,121]
```
提示：
```
1 <= A.length <= 10000
-10000 <= A[i] <= 10000
A 已按非递减顺序排序。
```
## 解法一
### 思路
- 循环平方
- sort排序
### 代码
```java
class Solution {
    public int[] sortedSquares(int[] A) {
        for (int i = 0; i < A.length; i++) {
            A[i] *= A[i];
        }

        Arrays.sort(A);
        return A;
    }
}
```
## 解法二
### 思路
数组起始是升序排列的，所以：
- 负数部分就是按照平方后降序排列
- 非负数部分就是按照平方后升序排列

1. 先求出两个指针的位置
   - 最大的负数的坐标i
   - 最小的非负数的坐标j
2. 然后从这两个位置开始，从两个部分平方最小的数开始比较，谁更小，谁依次放入数组中
   - i--(负数平方从小到大的方向)
   - j++(非负数平方从小到达的方向)
3. 直到某一部分的数被迭代完，这一刻代表了：
   - 迭代完的部分已经按照平方从小到大的顺序排列在结果数组中
   - 剩下没有迭代完的那一半，所有的剩下的数直接平方以后按顺序放入结果数组就可以了
### 代码
```java
class Solution {
    public int[] sortedSquares(int[] A) {
        int j = 0;
        while (j < A.length && A[j] < 0) {
            j++;
        }
        int i = j - 1;
        
        int[] ans = new int[A.length];
        int t = 0;
        
        while (i >= 0 && j < A.length) {
            if (A[i] * A[i] < A[j] * A[j]) {
                ans[t++] = A[i] * A[i];
                i--;
            } else {
                ans[t++] = A[j] * A[j];
                j++;
            }
        }
        
        while (i >= 0) {
            ans[t++] = A[i] * A[i];
            i--;
        }
        
        while (j < A.length) {
            ans[t++] = A[j] * A[j];
            j++;
        }
        
        return ans;
    }
}
```
# LeetCode_1051_高度检查器
## 题目
学校在拍年度纪念照时，一般要求学生按照 非递减 的高度顺序排列。

请你返回至少有多少个学生没有站在正确位置数量。该人数指的是：能让所有学生以 非递减 高度排列的必要移动人数。

示例：
```
输入：[1,1,4,2,1,3]
输出：3
解释：
高度为 4、3 和最后一个 1 的学生，没有站在正确的位置。
```
提示：
```
1 <= heights.length <= 100
1 <= heights[i] <= 100
```
## 解法一
### 思路
- 复制一个数组
- 把一个数组排序
- 遍历数组，比对是否一致，不一致的计数
### 代码
```java
class Solution {
    public int heightChecker(int[] heights) {
        int[] tmp = Arrays.copyOf(heights, heights.length);
        Arrays.sort(tmp);
        
        int count = 0;
        for (int i = 0; i < heights.length; i++) {
            if (tmp[i] != heights[i]) {
                count++;
            }
        }
        
        return count;
    }
}
```
# LeetCode_292_Nim游戏
## 题目
你和你的朋友，两个人一起玩 Nim 游戏：桌子上有一堆石头，每次你们轮流拿掉 1 - 3 块石头。 拿掉最后一块石头的人就是获胜者。你作为先手。

你们是聪明人，每一步都是最优解。 编写一个函数，来判断你是否可以在给定石头数量的情况下赢得游戏。

示例:
```
输入: 4
输出: false 
解释: 如果堆中有 4 块石头，那么你永远不会赢得比赛；
     因为无论你拿走 1 块、2 块 还是 3 块石头，最后一块石头总是会被你的朋友拿走。
```
## 解法一
### 思路
当石头数为4的倍数时候，无论自己如何选择取走的石头数，都会给对方机会在最终使我落入4个石头需要取的境地。所以只需要算当前n的数字是否为4的倍数就可以。
### 代码
```java
class Solution {
    public boolean canWinNim(int n) {
        return n % 4 != 0; 
    }
}
```
# LeetCode_942_增减字符串匹配
## 题目
给定只含 "I"（增大）或 "D"（减小）的字符串 S ，令 N = S.length。

返回 [0, 1, ..., N] 的任意排列 A 使得对于所有 i = 0, ..., N-1，都有：
```
如果 S[i] == "I"，那么 A[i] < A[i+1]
如果 S[i] == "D"，那么 A[i] > A[i+1]
```
示例 1：
```
输出："IDID"
输出：[0,4,1,3,2]
```
示例 2：
```
输出："III"
输出：[0,1,2,3]
```
示例 3：
```
输出："DDI"
输出：[3,2,0,1]
```
提示：
```
1 <= S.length <= 1000
S 只包含字符 "I" 或 "D"。
```
## 解法一
### 思路
通过观察示例，发现一个规律：
- 从左到右遍历S时候，碰到的D时，就会在D所对应的下标位置，按降序将应放入数组中的元素值放入这个下标对应的数组位置。
- 而I的情况正相反

从概念上来理解的话，遍历S的过程有点像将数组一个字符一个字符的缩短，而I或D就对应了数组中要随着缩短字符而去掉头还是尾的元素。为了保证匹配题目的要求，这个去掉的元素就要按照规则：
- D就去掉最大的，这样剩下的元素无论放哪一个都肯定对
- I就去掉最小的，道理同上。

这样就使得如同分解子问题一样不断地切分了这个问题。
### 代码
```
class Solution {
    public int[] diStringMatch(String S) {
        int left = 0, right = S.length(), i = 0;
        int[] ans = new int[S.length() + 1]; 
        
        for (char c: S.toCharArray()) {
            if (c == 'D') {
                ans[i++] = right--;
            }
            
            if (c == 'I') {
                ans[i++] = left++;
            }
        }
        
        ans[i] = left;
        
        return ans;
    }
}
```
# LeetCode_728_自除数
## 题目
自除数 是指可以被它包含的每一位数除尽的数。

例如，128 是一个自除数，因为 128 % 1 == 0，128 % 2 == 0，128 % 8 == 0。

还有，自除数不允许包含 0 。

给定上边界和下边界数字，输出一个列表，列表的元素是边界（含边界）内所有的自除数。

示例 1：
```
输入： 
上边界left = 1, 下边界right = 22
输出： [1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]
```
注意：
```
每个输入参数的边界满足 1 <= left <= right <= 10000。
```
## 解法一
### 思路
有技巧地死做
- 数字转字符串
- 拆字符串
- 判断有没有0
- 放入set去重
- 转数字
- 取余判断
### 代码
```java
class Solution {
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> ans = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (int i = left; i <= right; i++) {
            String str = Integer.toString(i);
            String[] strs = str.split("");
            for (String s: strs) {
                if ("0".equals(s)) {
                    set.clear();
                    break;
                }
                set.add(s);
            }
            
            if (set.size() == 0) {
                continue;
            }
            
            boolean flag = true;
            for (String s: set) {
                if (i % Integer.parseInt(s) != 0) {
                    flag = false;
                    break;
                }
            }
            
            if (flag) {
                ans.add(i);
            }
            
            set.clear();
        }
        return ans;
    }
}
```
## 解法二
### 思路
通过int值小数部分会被截取的特点
- 遍历left到right
- 不断截断遍历的数，并通过取模来获得10进制当前位的值，并判断两件事
   - 取模得到的值是否为0
   - 遍历到的这个数是否能被当前位的值整除
- 如果截取到值为0的时候，说明已经截完了，如果这时候上述条件都不满足，那就放入ans中
- 遍历结束后返回ans
### 代码
```java
class Solution {
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> ans = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            int t = i;
            boolean flag = true;
            while (t != 0) {
                int s = t % 10;
                if (s == 0 || i % s != 0) {
                    flag = false;
                    break;
                }
                
                t /= 10;
            }
            
            if (flag) {
                ans.add(i);
            }
        }
        
        return ans;
    }
}
```
# LeetCode_590_N叉树的后序遍历
## 题目
给定一个 N 叉树，返回其节点值的后序遍历。
## 解法一
### 思路
dfs递归方式，就是要注意后序遍历，所以需要修改递归和放值的顺序
### 代码
```java
class Solution {
    public List<Integer> postorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        dfs(ans, root);
        return ans;
    }
    
    private void dfs(List<Integer> ans, Node node) {
        if (node == null) {
            return;
        }
        
        for (Node child: node.children) {
            dfs(ans, child);
        }
        
        ans.add(node.val);
    }
}
```
## 解法二
### 思路
dfs非递归方法，因为是后序遍历，所以：
- 下钻的时候不是通过pop弹栈的方式，而是通过peek的方式，一层层的将children压入栈中
- 而放入ans中的时机是在
   - 要么钻到叶子节点了
   - 要么是回到上一层的时候
- 在放入ans中后就把当前节点出栈
- 同时还要干一件重要的事情，使用一个pre指针指向当前这个出栈的节点。原因是，通过这个pre指针，就能够判断当前遍历到的节点是已经第二次被经过(这次是返回的过程，需要记录节点值了)。具体方法就是当前这个节点是否包含pre指针指向的那个节点，是就代表现在是在返回，需要记录了。
- 最后while结束，返回ans就可以了
### 代码
```java
class Solution {
    public List<Integer> postorder(Node root) {
        List<Integer> ans = new ArrayList<>();

        Node pre = null;

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node cur = stack.peek();

            if (cur.children == null || (pre != null && cur.children.contains(pre))) {
                ans.add(cur.val);
                stack.pop();
                pre = cur;
            } else {
                for (int i = cur.children.size() - 1; i >= 0; i--) {
                    stack.push(cur.children.get(i));
                }
            }
        }
        
        return ans;
    }
}
```
# LeetCode_589_N叉树的前序遍历
## 题目
给定一个 N 叉树，返回其节点值的前序遍历。
## 解法一
### 思路
dfs递归解法，注意先放入ans再递归就可以
### 代码
```java
class Solution {
    public List<Integer> preorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        dfs(ans, root);
        return ans;
    }
    
    private void dfs(List<Integer> ans, Node node) {
        if (node == null) {
            return;
        }
        
        ans.add(node.val);
        
        for (Node child: node.children) {
            dfs(ans, child);
        }
    }
}
```
## 解法二
### 思路
dfs非递归方式，很标准
### 代码
```java
class Solution {
    public List<Integer> preorder(Node root) {
        List<Integer> ans = new ArrayList<>(); 
        
        if (root == null) {
            return ans;
        }
        
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            ans.add(node.val);
            
            if (node.children != null) {
                for (int i = node.children.size() - 1; i >= 0; i--) {
                    stack.push(node.children.get(i));
                }
            }
        }
        
        return ans;
    }
}
```
# LeetCode_700_二叉搜索树中的搜索
## 题目
给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。

例如，
```
给定二叉搜索树:
        4
       / \
      2   7
     / \
    1   3
    
和值: 2

你应该返回如下子树:
      2     
     / \   
    1   3
    
在上述示例中，如果要找的值是 5，但因为没有节点值为 5，我们应该返回 NULL。
```
## 解法一
### 思路
因为是二叉搜索树，利用其左子树比根节点小，右子树比根节点大的特性，使用dfs方式根据val大小选择遍历路径遍历，效率最高。这个解法使用递归地方式：
- 如果null或者等于val就返回
- 如果小于val就往右子树下钻
- 如果大于val就往左子树下钻
### 代码
```java
class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        return dfs(root, val);
    }
    
    private TreeNode dfs(TreeNode node, int val) {
        if (node == null || node.val == val) {
            return node;
        }
        
        if (node.val < val) {
            return dfs(node.right, val);
        } else {
            return dfs(node.left, val);
        }
    }
}
```
## 解法二
### 思路
dfs非递归，和解法一类似，注意null的情况
### 代码
```java
class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        TreeNode ans = null;
        
        if (root == null) {
            return ans;
        }
        
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            
            if (node.val == val) {
                ans = node;
                break;
            }
            
            if (node.val < val) {
                if (node.right == null) {
                    break;
                }
                stack.push(node.right);
            } else {
                if (node.left == null) {
                    break;
                }
                stack.push(node.left);
            }
        }
        
        return ans;
    }
}
```
# LeetCode_852_山脉数组的峰顶索引
## 题目
我们把符合下列属性的数组 A 称作山脉：

A.length >= 3
存在 0 < i < A.length - 1 使得A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1]
给定一个确定为山脉的数组，返回任何满足 A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1] 的 i 的值。

示例 1：
```
输入：[0,1,0]
输出：1
```
示例 2：
```
输入：[0,2,1,0]
输出：1
```
提示：
```
3 <= A.length <= 10000
0 <= A[i] <= 10^6
A 是如上定义的山脉
```
## 解法一
### 思路
遍历整个数组
- 起始从第二个元素开始
- 在倒数第二个元素结束
- 判断当前元素是否比前一个大，也比后一个大

时间复杂度是O(N)
### 代码
```java
class Solution {
    public int peakIndexInMountainArray(int[] A) {
        for (int i = 1; i < A.length - 1; i++) {
            if (A[i] > A[i-1] && A[i] > A[i+1]) {
                return i;
            }
        }
        
        return 0;
    }
}
```
## 解法二
### 思路
使用二分法来解，定义left，right和mid，注意为了防止溢出，mid = (right - left) / 2 + left
- 循环条件就是left <= right
- 循环体里做两件事：
   - 如果和解法一一样，mid元素比左右两个元素都大，就直接返回
   - 否则如下进行边界的修改，同时根据修改的边界修改mid的值：
      - 如果是升序(mid > mid - 1)，说明峰顶在mid的右边，left = mid
      - 如果是降序(mid < mid - 1)，说明峰顶在mid的左边，right = mid;
- 最终返回mid
### 代码
```java
class Solution {
    public int peakIndexInMountainArray(int[] A) {
        int left = 0, right = A.length - 1, mid = (right - left) / 2 + left;
        while (left <= right) {
            if (A[mid] > A[mid - 1] && A[mid] > A[mid + 1]) {
                return mid;
            }
            
            if (A[mid] > A[mid - 1]) {
                left = mid;
            } else {
                right = mid;
            }
            
            mid = (right - left) / 2 + left;
        }

        return mid;
    }
}
```