# LeetCode_986_区间列表的交集
## 题目
给定两个由一些闭区间组成的列表，每个区间列表都是成对不相交的，并且已经排序。

返回这两个区间列表的交集。

（形式上，闭区间 [a, b]（其中 a <= b）表示实数 x 的集合，而 a <= x <= b。两个闭区间的交集是一组实数，要么为空集，要么为闭区间。例如，[1, 3] 和 [2, 4] 的交集为 [2, 3]。）

示例：
```
输入：A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
输出：[[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
注意：输入和所需的输出都是区间对象组成的列表，而不是数组或列表。
```
提示：
```
0 <= A.length < 1000
0 <= B.length < 1000
0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9
```
## 失败解法
### 思路
- 根据两个二维数组生成两个list，list中包含了数组中包括的元素
- 使用两个指针指向两个list
- 判断两个指针指向的元素大小，小的那个元素的指针移动，直到大于等于另一个指针指向的元素
- 如果相等，则记录这个值，并同时移动两个指针，直到两个指针指向的元素不相等
- 重复之前的动作，直到某一个指针越界
### 失败原因
将元素打散放入list中，回导致原始的集合边界被打破，如果两个数组之间的元素是连续的，那么就会被视为一个集合，导致结果中应该是n个集合的，被合并成了一个集合
### 代码
```java
class Solution {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<Integer> listA = getList(A);
        List<Integer> listB = getList(B);

        int a = 0, aLen = listA.size(), b = 0, bLen = listB.size();

        List<int[]> list = new ArrayList<>();
        while (a < aLen && b < bLen) {
            while (a < aLen && listA.get(a) < listB.get(b)) {
                a++;
            }

            if (a >= aLen) {
                break;
            }

            while (b < bLen && listB.get(b) < listA.get(a)) {
                b++;
            }

            if (b >= bLen) {
                break;
            }

            if (!listA.get(a).equals(listB.get(b))) {
                continue;
            }

            int[] arr = new int[]{listA.get(a), 0};

            do {
                a++;
                b++;
            } while (a < aLen && b < bLen && listA.get(a).equals(listB.get(b)));

            arr[1] = listA.get(a - 1);

            list.add(arr);
        }

        int[][] ans = new int[list.size()][2];
        int index = 0;
        for (int[] arr : list) {
            ans[index++] = arr;
        }
        return ans;
    }

    private List<Integer> getList(int[][] arr) {
        List<Integer> listA = new ArrayList<>();
        for (int[] a : arr) {
            for (int i = a[0]; i <= a[1]; i++) {
                listA.add(i);
            }
        }
        return listA;
    }
}
```
## 解法
### 思路
- 使用两个指针用来遍历两个二维数组
    - `a`
    - `b`
- 循环的退出条件：`a >= A.length || b >= B.length`
- 循环过程中的逻辑：
    - 直接a指针移动的条件：`A[a][1] < B[b][0]`
    - 直接b指针移动的条件：`B[b][1] < A[a][0]`
    - 如果不需要直接移动，说明两个集合有交集，可以生成1个集合：
        - 集合起始元素：`Math.max(A[a][0], B[b][0])`
        - 集合结束元素：`Math.min(A[a][1], B[b][1])`
    - `a`和`b`的移动分三种情况：
        - 如果`A[a][1] < B[b][1]`，说明A集合已经遍历完了，而B没有，a++
        - 如果`A[a][1] > B[b][1]`，说明B集合已经遍历完了，而A没有，a++
        - 如果`A[a][1] == B[b][1]`，说明AB集合同时遍历完，a++，b++
### 代码
```java
class Solution {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        int a = 0, aLen = A.length, b = 0, bLen = B.length;
        List<int[]> list = new ArrayList<>();
        while (a < aLen && b < bLen) {
            if (A[a][1] < B[b][0]) {
                a++;
                continue;
            }
            
            if (B[b][1] < A[a][0]) {
                b++;
                continue;
            }
            
            int[] arr = new int[2];
            arr[0] = Math.max(A[a][0], B[b][0]);
            arr[1] = Math.min(A[a][1], B[b][1]);
            list.add(arr);
            
            if (A[a][1] > B[b][1]) {
                b++;
            } else if (A[a][1] < B[b][1]) {
                a++;
            } else {
                a++;
                b++;
            }
        }
        
        int[][] ans = new int[list.size()][2];
        int index = 0;
        for (int[] arr : list) {
            ans[index++] = arr;
        }
        return ans;
    }
}
```
# LeetCode_95_不同的二叉搜索树
## 题目
给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。

示例:
```
输入: 3
输出:
[
  [1,null,3,2],
  [3,2,null,1],
  [3,1,null,null,2],
  [2,1,3],
  [1,null,2,null,3]
]
解释:
以上的输出对应以下 5 种不同结构的二叉搜索树：

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
```
## 解法
### 思路
递归：
- 参数：
    - 起始数字`start`
    - 结尾数字`end`
- 退出条件：
    - `start`大于`end`，说明已经没有能够构建节点的值
- 处理逻辑：
    - 循环遍历`start`到`end`
    - 递归构建`start`到`i - 1`作为左子树集合`lefts`
    - 递归构建`i + 1`到`end`作为右子树集合`rights`
    - 嵌套循环两个集合：
        - 构建i节点
        - 绑定从两个集合中将左右节点绑定在i节点上
        - 加入到list中
    - 循环结束，返回list到上一层
### 代码
```java
class Solution {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        
        return doGenerate(1, n);
    }

    private List<TreeNode> doGenerate(int start, int end) {
        List<TreeNode> list = new ArrayList<>();
        if (start > end) {
            list.add(null);
            return list;
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> lefts = doGenerate(start, i - 1);
            List<TreeNode> rights = doGenerate(i + 1, end);

            for (TreeNode left : lefts) {
                for (TreeNode right : rights) {
                    TreeNode node = new TreeNode(i);
                    node.left = left;
                    node.right = right;
                    list.add(node);
                }
            }
        }

        return list;
    }
}
```
# LeetCode_677_键值映射
## 题目
实现一个 MapSum 类里的两个方法，insert 和 sum。

对于方法 insert，你将得到一对（字符串，整数）的键值对。字符串表示键，整数表示值。如果键已经存在，那么原来的键值对将被替代成新的键值对。

对于方法 sum，你将得到一个表示前缀的字符串，你需要返回所有以该前缀开头的键的值的总和。

示例 1:
```
输入: insert("apple", 3), 输出: Null
输入: sum("ap"), 输出: 3
输入: insert("app", 2), 输出: Null
输入: sum("ap"), 输出: 5
```
## 解法
### 思路
- insert：使用map直接存储
- sum：遍历key，通过startWith来判断并累加
### 代码
```java
class MapSum {
    Map<String, Integer> map;        

    public MapSum() {
        map = new HashMap<>();
    }

    public void insert(String key, int val) {
        map.put(key, val);
    }

    public int sum(String prefix) {
        int sum = 0;
        for (String key : map.keySet()) {
            if (key.startsWith(prefix)) {
                sum += map.get(key);
            }
        }
        return sum;
    }
}
```
## 解法二
### 思路
- 使用dict存储字符串的前缀和对应的字符串集合
- 使用map存储字符串对应的值
### 代码
```java
class MapSum {
    Map<String, Integer> map;
    Map<String, Set<String>> dict;
    public MapSum() {
        map = new HashMap<>();
        dict = new HashMap<>();
    }

    public void insert(String key, int val) {
        StringBuilder sb = new StringBuilder();
        char[] cs = key.toCharArray();
        for (char c : cs) {
            String str = sb.append(c).toString();
            if (dict.containsKey(str)) {
                dict.get(str).add(key);
            } else {
                Set<String> set = new HashSet<>();
                set.add(key);
                dict.put(str, set);
            }
        }

        map.put(key, val);
    }

    public int sum(String prefix) {
        if (dict.containsKey(prefix)) {
            int sum = 0;
            for (String key : dict.get(prefix)) {
                sum += map.get(key);
            }
            return sum;
        } else {
            return 0;
        }
    }
}
```
## 解法三
### 思路
使用字典树
- insert：
    - 的时候先从根到底新增或更新`+ val`节点
    - 返回的时候减去该节点原来的值
- sum：
    - 递归搜索字典树并返回该节点值
### 代码
```java
class MapSum {
        private DictNode root;
        public MapSum() {
            this.root = new DictNode(' ');
        }

        public void insert(String key, int val) {
            doInsert(key, root, 0, val, sum(key));
        }

        public int sum(String prefix) {
            return doSum(prefix, root, 0);
        }

        private void doInsert(String key, DictNode node, int index, int val, int oldV) {
            if (index >= key.length()) {
                return;
            }

            for (DictNode child : node.children) {
                if (key.charAt(index) == child.c) {
                    child.sum += val;
                    doInsert(key, child, index + 1, val, oldV);
                    if (index == key.length() - 1 && node.children.isEmpty()) {
                        DictNode tmp = child;
                        while (tmp != null) {
                            tmp.sum -= oldV;
                            tmp = tmp.parent;
                        }
                        return;
                    }
                    return;
                }
            }

            DictNode cur = new DictNode(key.charAt(index));
            cur.sum = val;
            cur.parent = node;
            node.children.add(cur);
            doInsert(key, cur, index + 1, val, oldV);
        }

        private int doSum(String prefix, DictNode node, int index) {
            if (index >= prefix.length()) {
                return 0;
            }

            for (DictNode child : node.children) {
                if (child.c == prefix.charAt(index)) {
                    if (index == prefix.length() - 1) {
                        return child.sum;
                    }

                    return doSum(prefix, child, index + 1);
                }
            }

            return 0;
        }
    }
```
# LeetCode_129_求根到叶子节点数字之和
## 题目
给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。

例如，从根到叶子节点路径 1->2->3 代表数字 123。

计算从根到叶子节点生成的所有数字之和。

说明: 叶子节点是指没有子节点的节点。

示例 1:
```
输入: [1,2,3]
    1
   / \
  2   3
输出: 25
解释:
从根到叶子节点路径 1->2 代表数字 12.
从根到叶子节点路径 1->3 代表数字 13.
因此，数字总和 = 12 + 13 = 25.
```
示例 2:
```
输入: [4,9,0,5,1]
    4
   / \
  9   0
 / \
5   1
输出: 1026
解释:
从根到叶子节点路径 4->9->5 代表数字 495.
从根到叶子节点路径 4->9->1 代表数字 491.
从根到叶子节点路径 4->0 代表数字 40.
因此，数字总和 = 495 + 491 + 40 = 1026.
```
## 解法
### 思路
- dfs打印节点数值放入list
- 遍历list，将元素相加返回
### 代码
```java
class Solution {
    public int sumNumbers(TreeNode root) {
        List<String> list = new ArrayList<>();
        dfs(root, "", list);
        int ans = 0;
        for (String str : list) {
            ans += Integer.parseInt(str);
        }
        return ans;
    }
    
    private void dfs(TreeNode node, String num, List<String> list) {
        if (node == null) {
            return;
        }
        
        if (node.left == null && node.right == null) {
            list.add(num + node.val);
            return;
        }
        
        dfs(node.left, num + node.val, list);
        dfs(node.right, num + node.val, list);
    }
}
```
## 优化代码
### 思路
在递归过程中累加值，不放在list中再遍历计算
### 代码
```java
class Solution {
    private int sum = 0;

    public int sumNumbers(TreeNode root) {
        dfs(root, "");
        return sum;
    }
    
    private void dfs(TreeNode node, String num) {
        if (node == null) {
            return;
        }
        
        if (node.left == null && node.right == null) {
            sum += Integer.parseInt(num + node.val);
            return;
        }
        
        dfs(node.left, num + node.val);
        dfs(node.right, num + node.val);
    }
}
```
##优化代码
### 思路
使用int值计算取代字符串拼接
### 代码
```java
class Solution {
    private int sum = 0;

    public int sumNumbers(TreeNode root) {
        dfs(root, 0);
        return sum;
    }

    private void dfs(TreeNode node, int num) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            sum += num + node.val;
            return;
        }

        dfs(node.left, num * 10 + node.val * 10);
        dfs(node.right, num * 10 + node.val * 10);
    }
}
```
# LeetCode_328_奇偶链表
## 题目
给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。

请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。

示例 1:
```
输入: 1->2->3->4->5->NULL
输出: 1->3->5->2->4->NULL
```
示例 2:
```
输入: 2->1->3->5->6->4->7->NULL 
输出: 2->3->6->7->1->5->4->NULL
```
说明:
```
应当保持奇数节点和偶数节点的相对顺序。
链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
```
## 解法
### 思路
将奇偶节点分别生成两个链表，然后头尾再相接：
- 使用四个指针：
    - oddHead
    - odd
    - evenHead
    - even
- odd和even指针分别遍历链表，并重新链接
- 两个指针遍历结束后，odd指针的next指向evenHead即可
### 代码
```java
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode odd = head, evenHead = head.next, even = head.next;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }

        odd.next = evenHead;
        return head;
    }
}
```
# LeetCode_951_翻转等价二叉树
## 题目
我们可以为二叉树 T 定义一个翻转操作，如下所示：选择任意节点，然后交换它的左子树和右子树。

只要经过一定次数的翻转操作后，能使 X 等于 Y，我们就称二叉树 X 翻转等价于二叉树 Y。

编写一个判断两个二叉树是否是翻转等价的函数。这些树由根节点 root1 和 root2 给出。

示例：
```
输入：root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 = [1,3,2,null,6,4,5,null,null,null,null,8,7]
输出：true
解释：We flipped at nodes with values 1, 3, and 5.
```
提示：
```
每棵树最多有 100 个节点。
每棵树中的每个值都是唯一的、在 [0, 99] 范围内的整数。
```
## 解法
### 思路
递归，判断两棵树是否相等可以分解为判断两部分:
- 判断根节点:
    - 如果两个节点都是null，true
    - 如果任意1个节点是null，false
    - 如果两个节点的值不相同，false
- 判断子节点:
    - 两个节点的左子树和左子树，右子树和右子树比较
    - 连个节点的左子树和右子树比较
### 代码
```java
class Solution {
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == root2) {
            return true;
        }
        
        if (root1 == null || root2 == null || root1.val != root2.val) {
            return false;
        }
        
        return flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right) ||
                flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left);
    }
}
```
## 解法二
### 思路
将树转成标准态，如果两个是等价树，则深度优先搜索后打印出的节点一定相同。
- 递归，如果左树的值大于右树，进行翻转
- 如果子数为null，值为-1
### 代码
```java
class Solution {
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        dfs(root1, list1);

        List<Integer> list2 = new ArrayList<>();
        dfs(root2, list2);

        System.out.println(list1);
        System.out.println(list2);
        return list1.equals(list2);
    }

    private void dfs(TreeNode node, List<Integer> list) {
        if (node != null) {
            list.add(node.val);

            int left = node.left != null ? node.left.val : -1;
            int right = node.right != null ? node.right.val : -1;

            if (left < right) {
                dfs(node.left, list);
                dfs(node.right, list);
            } else {
                dfs(node.right, list);
                dfs(node.left, list);
            }
        }
    }
}
```
# LeetCode_889_根据前序和后续遍历构造二叉树
## 题目
返回与给定的前序和后序遍历匹配的任何二叉树。

pre 和 post 遍历中的值是不同的正整数。

示例：
```
输入：pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
输出：[1,2,3,4,5,6,7]
```
提示：
```
1 <= pre.length == post.length <= 30
pre[] 和 post[] 都是 1, 2, ..., pre.length 的排列
每个输入保证至少有一个答案。如果有多个答案，可以返回其中一个。
```
## 解法
### 思路
- 因为子树根节点在先序序列中时第一个，在后序序列中是最后一个
    - 使用先序序列构建子树
    - 使用后序序列验证子树
### 代码
```java
class Solution {
    private int preIndex, postIndex;
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        TreeNode node = new TreeNode(pre[preIndex++]);
        if (node.val != post[postIndex]) {
            node.left = constructFromPrePost(pre, post);
        }

        if (node.val != post[postIndex]) {
            node.right = constructFromPrePost(pre, post);
        }

        postIndex++;
        return node;
    }
}
```
# LeetCode_508_出现次数最多的子树元素和
## 题目
给出二叉树的根，找出出现次数最多的子树元素和。一个结点的子树元素和定义为以该结点为根的二叉树上所有结点的元素之和（包括结点本身）。然后求出出现次数最多的子树元素和。如果有多个元素出现的次数相同，返回所有出现次数最多的元素（不限顺序）。

示例 1
```
输入:

  5
 /  \
2   -3
返回 [2, -3, 4]，所有的值均只出现一次，以任意顺序返回所有值。
```
示例 2
```
输入:

  5
 /  \
2   -5
返回 [2]，只有 2 出现两次，-5 只出现 1 次。
```
```
提示： 假设任意子树元素和均可以用 32 位有符号整数表示。
```
## 解法
### 思路
- 后序遍历计算累加值二叉树并用map统计sum个数
- 找到出现次数最多的元素返回
### 代码
```java
class Solution {
    public int[] findFrequentTreeSum(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        dfs(root, map);

        if (map.size() == 0) {
            return new int[0];
        }
        
        int max = Collections.max(map.values());
        List<Integer> list = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if (entry.getValue() == max) {
                list.add(entry.getKey());
            }
        }

        int[] ans = new int[list.size()];
        int index = 0;
        for (int num : list) {
            ans[index++] = num;
        }
        return ans;
    }

    private int dfs(TreeNode node, Map<Integer, Integer> map) {
        if (node == null) {
            return 0;
        }

        int left = dfs(node.left, map);
        int right = dfs(node.right, map);

        int sum = left + right + node.val;
        map.put(sum, map.getOrDefault(sum, 0) + 1);

        return sum;
    }
}
```
# LeetCode_1043_分隔数组以得到最大值
## 题目
给出整数数组 A，将该数组分隔为长度最多为 K 的几个（连续）子数组。分隔完成后，每个子数组的中的值都会变为该子数组中的最大值。

返回给定数组完成分隔后的最大和。

示例：
```
输入：A = [1,15,7,9,2,5,10], K = 3
输出：84
解释：A 变为 [15,15,15,9,10,10,10]
```
提示：
```
1 <= K <= A.length <= 500
0 <= A[i] <= 10^6
```
## 解法
### 思路
动态规划：
- dp[i]存储[0,i]范围元素的子数组最大和
- dp[i] = Math.max(dp[i], i >= K ? dp[i - k] : 0 + k * maxK)
    - `maxK`：K长度子序列中的最大值
    - `i >= K ? dp[i - k] : 0`：如果下标i大小小于最小子序列K，那么dp[i]中的最大和一定就是[0,K]范围中的最大值*k，不需要管前一个状态
- 过程是双层嵌套循环：
    - 第一层遍历数组，获得当前元素值
    - 内层循环K次，保存`i - k + 1`元素与当前元素的最大值，dp赋值
### 代码
```java
class Solution {
    public int maxSumAfterPartitioning(int[] A, int K) {
        int len = A.length;
        int[] dp = new int[len];

        for (int i = 0; i < len; i++) {
            int curMax = A[i];
            for (int k = 1; k <= K && i - k + 1 >= 0; k++) {
                curMax = Math.max(curMax, A[i - k + 1]);
                dp[i] = Math.max(dp[i], (i >= k ? dp[i - k] : 0) + k * curMax);
            }
        }
        
        return dp[len - 1];
    }
}
```
# LeetCode_215_数组中第k个最大元素
## 题目
在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

示例 1:
```
输入: [3,2,1,5,6,4] 和 k = 2
输出: 5
```
示例 2:
```
输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
输出: 4
```
说明:
```
你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
```
## 解法
### 思路
- api排序
- 遍历到第k个数返回
### 代码
```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        int index = nums.length;
        for (int i = 0; i < k; i++) {
            index -= 1;
        }
        return nums[index];
    }
}
```
# LeetCode_347_前k个高频元素
## 题目
给定一个非空的整数数组，返回其中出现频率前 k 高的元素。

示例 1:
```
输入: nums = [1,1,1,2,2,3], k = 2
输出: [1,2]
```
示例 2:
```
输入: nums = [1], k = 1
输出: [1]
```
说明：
```
你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
```
## 解法
### 思路
- map记录元素出现个数
- 使用优先级队列建一个小顶堆
- 遍历map将元素放入堆中，如果堆元素大于k，就poll堆，这样就把目前为止第k+1个多的数排除了，直到循环结束
- 再次循环将堆中元素放入list
### 代码
```java
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(map::get));
        for (Integer key : map.keySet()) {
            queue.add(key);
            if (queue.size() > k) {
                queue.poll();
            }
        }

        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            list.add(queue.poll());
        }
        
        return list;
    }
}
```
## 解法二
### 思路
- 使用list保存map对象元素
- 比较器对value值进行降序比较
- 遍历list并将前k个key值放入list
### 代码
```java
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((x, y) -> y.getValue() - x.getValue());
        
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            ans.add(list.get(i).getKey());
        }
        return ans;
    }
}
```
# LeetCode_712_两个字符串的最小ASCII删除和
## 题目
给定两个字符串s1, s2，找到使两个字符串相等所需删除字符的ASCII值的最小和。

示例 1:
```
输入: s1 = "sea", s2 = "eat"
输出: 231
解释: 在 "sea" 中删除 "s" 并将 "s" 的值(115)加入总和。
在 "eat" 中删除 "t" 并将 116 加入总和。
结束时，两个字符串相等，115 + 116 = 231 就是符合条件的最小和。
```
示例 2:
```
输入: s1 = "delete", s2 = "leet"
输出: 403
解释: 在 "delete" 中删除 "dee" 字符串变成 "let"，
将 100[d]+101[e]+101[e] 加入总和。在 "leet" 中删除 "e" 将 101[e] 加入总和。
结束时，两个字符串都等于 "let"，结果即为 100+101+101+101 = 403 。
如果改为将两个字符串转换为 "lee" 或 "eet"，我们会得到 433 或 417 的结果，比答案更大。
```
注意:
```
0 < s1.length, s2.length <= 1000。
所有字符串中的字符ASCII值在[97, 122]之间。
```
## 解法
### 思路
动态规划：
- dp[i][j]存储的是从第1个字符到s1[i]和到s2[j]的删除和
- 状态转移方程有两种情况：
    - `s1[i] == s2[j]`：`dp[i][j] = dp[i - 1][j - 1]`
    - `s1[i] != s2[j]`：`dp[i][j] = Math.min(dp[i - 1][j] + s1[i], dp[i][j - 1] + s2[j])`
- base case：dp[0][0] = 0
- 初始化，遍历数组：
    - `dp[i][0] = dp[i - 1][0] + s1[i]`
    - `dp[0][j] = dp[0][j - 1] + s2[j]`
- 最终返回：dp[s1.len][s2.len]
### 代码
```java
class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 1; i <= len1; i++) {
            dp[i][0] = dp[i - 1][0] + s1.charAt(i - 1);
        }

        for (int j = 1; j <= len2; j++) {
            dp[0][j] = dp[0][j - 1] + s2.charAt(j - 1);
        }

        for (int i = 1; i <= len1; i++) {
            for (int j  = 1; j <= len2; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j] + s1.charAt(i - 1), dp[i][j - 1] + s2.charAt(j - 1));
                }
            }
        }

        return dp[len1][len2];
    }
}
```
# LeetCode_11_盛最多水的容器
## 题目
给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

说明：你不能倾斜容器，且 n 的值至少为 2。

图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。

示例:
```
输入: [1,8,6,2,5,4,8,3,7]
输出: 49
```
## 解法
### 思路
- 嵌套循环，外层为左柱下标，内层为右柱下标
- 将下标差与两者间高度的最小值的乘积记录，再和最大值比较取最大值
### 代码
```java
class Solution {
    public int maxArea(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                max = Math.max(max, Math.min(height[i], height[j]) * (j - i));
            }
        }
        return max;
    }
}
```
## 解法二
### 思路
- 使用双指针来遍历数组
- 计算双指针指向的元素最小值与下标差的乘积，与最大值进行比较取最大值
- 如果是左指针对应的元素小，左指针移动
- 如果是右指针对应的元素小，右指针移动
- 之所以这么移动的原因是，移动的过程是缩小公式一个因子的过程，那么要想取得更大值，只能取求另一个因子更大的可能
### 代码
```java
class Solution {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1, max = 0;
        while (left < right) {
            int l = height[left];
            int r = height[right];
            if (l < r) {
                max = Math.max(l * (right - left), max);
                left++;
            } else {
                max = Math.max(r * (right - left), max);
                right--;
            }
        }
        return max;
    }
}
```