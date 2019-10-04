# LeetCode_959_由斜杠划分区域
## 题目
在由 1 x 1 方格组成的 N x N 网格 grid 中，每个 1 x 1 方块由 /、\ 或空格构成。这些字符会将方块划分为一些共边的区域。

（请注意，反斜杠字符是转义的，因此 \ 用 "\\" 表示。）。

返回区域的数目。

示例 1：
```
输入：
[
  " /",
  "/ "
]
输出：2
```
示例 2：
```
输入：
[
  " /",
  "  "
]
输出：1
```
示例 3：
```
输入：
[
  "\\/",
  "/\\"
]
输出：4
解释：（回想一下，因为 \ 字符是转义的，所以 "\\/" 表示 \/，而 "/\\" 表示 /\。）
```
示例 4：
```
输入：
[
  "/\\",
  "\\/"
]
输出：5
解释：（回想一下，因为 \ 字符是转义的，所以 "/\\" 表示 /\，而 "\\/" 表示 \/。）
```
示例 5：
```
输入：
[
  "//",
  "/ "
]
输出：3
```
提示：
```
1 <= grid.length == grid[0].length <= 30
grid[i][j] 是 '/'、'\'、或 ' '。
```
## 解法
### 思路
- 因为相关联的部分被看作是一块，要算出有几块，就看他们共同属于的根节点有几个，和并查集找掌门类似
- 将每个单元格看作一个有四个顶点的方框，故顶点有`4 * len * len`个
- 每个顶点作为并查集的元素，然后通过是否有被划分来`union`到同一个父节点
- 不断地遍历字符串，并合并父节点
- 字符串遍历结束后，遍历dsu，查看根节点有几个，作为结果返回
### 代码
```java
class Solution {
    public int regionsBySlashes(String[] grid) {
        int len = grid.length;
        DSU dsu = new DSU(4 * len * len);

        for (int r = 0; r < len; r++) {
            for (int c = 0; c < len; c++) {
                int root = 4 * (r * len + c);
                char val = grid[r].charAt(c);

                if (val != '\\') {
                    dsu.union(root, root + 3);
                    dsu.union(root + 1, root + 2);
                }

                if (val != '/') {
                    dsu.union(root, root + 1);
                    dsu.union(root + 2, root + 3);
                }

                if (r < len - 1) {
                    dsu.union(root + 2, root + 4 * len);
                }

                if (r > 0) {
                    dsu.union(root, root - 4 * len + 2);
                }

                if (c > 0) {
                    dsu.union(root + 3, root - 3);
                }

                if (c < len - 1) {
                    dsu.union(root + 1, root + 7);
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < 4 * len * len; i++) {
            if (dsu.find(i) == i) {
                ans++;
            }
        }
        return ans;
    }
}

class DSU {
    public int[] parent;

    public DSU(int N) {
        parent = new int[N];
        for (int i = 0; i < N; ++i)
            parent[i] = i;
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x, int y) {
        parent[find(x)] = find(y);
    }
}
```
# LeetCode_12_整数转罗马数字
## 题目
罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
```
字符          数值
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
```
通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
```
I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
```
示例 1:
```
输入: 3
输出: "III"
```
示例 2:
```
输入: 4
输出: "IV"
```
示例 3:
```
输入: 9
输出: "IX"
```
示例 4:
```
输入: 58
输出: "LVIII"
解释: L = 50, V = 5, III = 3.
```
示例 5:
```
输入: 1994
输出: "MCMXCIV"
解释: M = 1000, CM = 900, XC = 90, IV = 4.
```
## 解法
### 思路
- int类型依次除以1000到10，得到千位，百位和十位的值
- 通过取模得到相处后剩下的余数
- 对逢4逢9的数进行特别处理
- 一位一位的硬做
### 代码
```java
class Solution {
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();

        int t = num / 1000;
        num %= 1000;

        for (int i = 0; i < t; i++) {
            sb.append("M");
        }

        int h = num / 100;
        num %= 100;

        if (h == 9) {
            sb.append("CM");
        } else if (h == 4) {
            sb.append("CD");
        } else if (h >= 5) {
            sb.append("D");
            h -= 5;
            for (int i = 0; i < h; i++) {
                sb.append("C");
            }
        } else {
            for (int i = 0; i < h; i++) {
                sb.append("C");
            }
        }

        int d = num / 10;
        num %= 10;

        if (d == 9) {
            sb.append("XC");
        } else if (d == 4) {
            sb.append("XL");
        } else if (d >= 5) {
            sb.append("L");
            d -= 5;
            for (int i = 0; i < d; i++) {
                sb.append("X");
            }
        } else {
            for (int i = 0; i < d; i++) {
                sb.append("X");
            }
        }
        
        if (num == 9) {
            sb.append("IX");
        } else if (num == 4) {
            sb.append("IV");
        } else if (num >= 5) {
            sb.append("V");
            num -= 5;
            for (int i = 0; i < num; i++) {
                sb.append("I");
            }
        } else {
            for (int i = 0; i < num; i++) {
                sb.append("I");
            }
        }
        
        return sb.toString();
    }
}
```
## 解法二
### 思路
- 声明两个数组，一个存罗马数字，一个存罗马数字对应的int值`num`，罗马数字中包括逢9逢4的特殊数字
- 嵌套循环，从最大的罗马数字开始，处理int值
    - 外层循环遍历从大到小的罗马数字对应的值
    - 内层循环将`num`除以罗马数字对应值，获得该值有多少个，然后append对应个数得罗马字符
    - 内层循环结束以后，对`num`取模罗马数字值
- 循环结束后返回sb
### 代码
```java
class Solution {
    private int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private String[] roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < num / nums[i]; j++) {
                sb.append(roman[i]);
            }
            num %= nums[i];
        }
        return sb.toString();
    }
}
```
# LeetCode_287_寻找重复数
## 题目
给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。

示例 1:
```
输入: [1,3,4,2,2]
输出: 2
```
示例 2:
```
输入: [3,1,3,4,2]
输出: 3
```
说明：
```
不能更改原数组（假设数组是只读的）。
只能使用额外的 O(1) 的空间。
时间复杂度小于 O(n2) 。
数组中只有一个重复的数字，但它可能不止重复出现一次。
```
## 解法
### 思路
二分查找，因为必然有一个元素是有若干重复的，所以通过判断比mid小的数是否大于mid来判断重复的数比mid大还是小
### 代码
```java
class Solution {
    public int findDuplicate(int[] nums) {
        int low = 1, high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2, count = 0;
            for (int num : nums) {
                if (num <= mid) {
                    count++;
                }
            }

            if (count > mid) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        
        return low;
    }
}
```
# LeetCode_199_二叉树的右视图
## 题目
给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。

示例:
```
输入: [1,2,3,null,5,null,4]
输出: [1, 3, 4]
解释:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
```
## 解法
### 思路
bfs打印最右节点，每次while循环的时候计算queue的长度，并在长度递减的循环中对长度为1的循环中拉出的节点进行记录
### 代码
```java
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int len = queue.size();
            while (len > 0) {
                TreeNode node = queue.poll();
                if (len == 1) {
                    ans.add(node.val);
                }
                
                len--;

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return ans;
    }
}
```
# LeetCode_147_对链表进行插入排序
## 题目
插入排序算法：
```
插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
重复直到所有输入数据插入完为止。
```
示例 1：
```
输入: 4->2->1->3
输出: 1->2->3->4
```
示例 2：
```
输入: -1->5->3->4->0
输出: -1->0->3->4->5
```
## 解法
### 思路
- 初始化三个指针：
    - 指针的next节点指向链表的头节点：pre
    - 指针指向遍历排序到的节点位置：head
    - 指针指向需要被插入的节点位置：index
- 循环遍历数组，直到head指针指向最后一个元素或指向了空
- 如果当前元素小于等于head.next指向的元素值，则继续遍历不处理
- 否则就要从头节点开始，依次判断index.next指针指向的元素是否大于head.next指向的元素，如果是，就说明需要将其插入到该位置
- 开始进行插入：
    1. 声明`cur`指针指向需要变更的`head.next`节点
    2. 将`head.next`指针指向`cur.next`，将下一个需要的遍历位置直到当前需要变更的节点的后一个节点
    3. 将`cur.next`指针指向需要插入的位置的后一个节点`index.next`
    4. 将`index.next`指向`cur`节点，从而使需要变更的节点来到应该在的位置
### 代码
```java
class Solution {
    public ListNode insertionSortList(ListNode head) {
        ListNode pre = new ListNode(0), index;
        pre.next = head;

        while (head != null && head.next != null) {
            if (head.next.val >= head.val) {
                head = head.next;
                continue;
            }

            index = pre;
            while (index.next.val < head.next.val) {
                index = index.next;
            }

            ListNode cur = head.next;
            head.next = cur.next;
            cur.next = index.next;
            index.next = cur;
        }
        
        return pre.next;
    }
}
```
# LeetCode_341_扁平化嵌套列表迭代器
## 题目
给定一个嵌套的整型列表。设计一个迭代器，使其能够遍历这个整型列表中的所有整数。

列表中的项或者为一个整数，或者是另一个列表。

示例 1:
```
输入: [[1,1],2,[1,1]]
输出: [1,1,2,1,1]
解释: 通过重复调用 next 直到 hasNext 返回false，next 返回的元素的顺序应该是: [1,1,2,1,1]。
```
示例 2:
```
输入: [1,[4,[6]]]
输出: [1,4,6]
解释: 通过重复调用 next 直到 hasNext 返回false，next 返回的元素的顺序应该是: [1,4,6]。
```
## 解法
### 思路
递归生成list，之后转成迭代器处理这个类的具体逻辑
### 代码
```java
public class NestedIterator implements Iterator<Integer> {
    private Iterator<Integer> iterator;
    public NestedIterator(List<NestedInteger> nestedList) {
        List<Integer> list = new ArrayList<>();
        recurse(list, nestedList);
        iterator = list.iterator();
    }

    private void recurse(List<Integer> list, List<NestedInteger> nestedList) {
        for (NestedInteger nestedInteger : nestedList) {
            if (nestedInteger.isInteger()) {
                list.add(nestedInteger.getInteger());
            } else {
                recurse(list, nestedInteger.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return iterator.next();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }
}
```
## 解法二
### 思路
将扁平化的过程从解法一的初始化阶段放在了函数调用阶段。使用一个栈来保存list的迭代器，如果迭代器中的是非扁平的元素，就将它取出并放在栈顶，以此类推的获取元素
### 代码
```java
public class NestedIterator implements Iterator<Integer> {
    private LinkedList<Iterator<NestedInteger>> stack = new LinkedList<>();
    private boolean flag = false;
    private Integer num;
    public NestedIterator(List<NestedInteger> nestedList) {
        stack.offer(nestedList.iterator());
    }

    @Override
    public Integer next() {
        flag = false;
        return num;
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty() && !flag) {
            Iterator<NestedInteger> iterator = stack.peekFirst();
            if (iterator.hasNext()) {
                NestedInteger nestedInteger = iterator.next();
                if (nestedInteger == null) {
                    continue;
                }

                if (nestedInteger.isInteger()) {
                    flag = true;
                    num = nestedInteger.getInteger();
                } else {
                    stack.offerFirst(nestedInteger.getList().iterator());
                    iterator.remove();
                }
            } else {
                stack.pollFirst();
            }
        }

        return flag;
    }
}
```
# LeetCode_969_煎饼顺序
## 题目
给定数组 A，我们可以对其进行煎饼翻转：我们选择一些正整数 k <= A.length，然后反转 A 的前 k 个元素的顺序。我们要执行零次或多次煎饼翻转（按顺序一次接一次地进行）以完成对数组 A 的排序。

返回能使 A 排序的煎饼翻转操作所对应的 k 值序列。任何将数组排序且翻转次数在 10 * A.length 范围内的有效答案都将被判断为正确。

示例 1：
```
输入：[3,2,4,1]
输出：[4,2,4,3]
解释：
我们执行 4 次煎饼翻转，k 值分别为 4，2，4，和 3。
初始状态 A = [3, 2, 4, 1]
第一次翻转后 (k=4): A = [1, 4, 2, 3]
第二次翻转后 (k=2): A = [4, 1, 2, 3]
第三次翻转后 (k=4): A = [3, 2, 1, 4]
第四次翻转后 (k=3): A = [1, 2, 3, 4]，此时已完成排序。 
```
示例 2：
```
输入：[1,2,3]
输出：[]
解释：
输入已经排序，因此不需要翻转任何内容。
请注意，其他可能的答案，如[3，3]，也将被接受。
```
提示：
```
1 <= A.length <= 100
A[i] 是 [1, 2, ..., A.length] 的排列
```
## 解法
### 思路
- 将数组中的最大值移到下标为0的位置
- 再将最大值移到下标为len - 1的位置
- 将最大值减1，找到这个最大值，继续如上的两步，直到最大值为1
- 具体代码逻辑：
    - 声明新数组B，用来保存数组A对应的元素下标，并根据A数组元素从大到小排序
    - 嵌套遍历
        - 外层循环遍历数组B，用来知道需要存储的两个步骤，放到数组头和放回数组尾部(尾部逐渐变小)的值是多少
        - 内层循环遍历结果list，因为在前一个数组旋转时，会使得这个元素对应下标之前的所有元素都经历`i = f + 1 - i`的变化，有过几次就要转换几次
### 代码
```java
class Solution {
    public List<Integer> pancakeSort(int[] A) {
        List<Integer> ans = new ArrayList<>();
        
        int len = A.length;
        Integer[] b = new Integer[len];
        for (int i = 0; i < len; i++) {
            b[i] = i + 1;
        }
        Arrays.sort(b, (i, j) ->  A[j - 1] - A[i - 1]);
        
        for (int index : b) {
            for (int preIndex : ans) {
                if (index <= preIndex) {
                    index = preIndex + 1 - index;
                }
            }
            
            ans.add(index);
            ans.add(len--);
        }
        
        return ans;
    }
}
```
## 解法二
### 思路
和解法一的思路类似，但是不使用新的数组，而是直接对A数组进行排序，同时判断如果元素不需要移动，就跳过
- 先找到最大元素
- 然后记录需要变更的两次动作
    - 移到数组头需要的元素个数
    - 移到指定位置需要的元素个数
- 然后进行对应的元素移动该操作
- 继续循环，直到最大边界为0为止，也就是只有一个元素需要变更
- 最终返回结果
### 代码
```java
class Solution {
    public List<Integer> pancakeSort(int[] A) {
        int maxIndex = A.length - 1;
        List<Integer> ans = new ArrayList<>();

        while (maxIndex > 0) {
            int maxNumIndex = findMaxNumIndex(A, maxIndex);

            if (maxNumIndex == maxIndex) {
                maxIndex--;
            } else {
                ans.add(maxNumIndex + 1);
                reserve(A, maxNumIndex);
                ans.add(maxIndex + 1);
                reserve(A, maxIndex);
                maxIndex--;
            }
        }

        return ans;
    }

    private int findMaxNumIndex(int[] arr, int maxIndex) {
        int maxNumIndex = 0;
        for (int i = 0; i <= maxIndex; i++) {
            maxNumIndex = arr[maxNumIndex] > arr[i] ? maxNumIndex : i;
        }
        return maxNumIndex;
    }

    private void reserve(int[] arr, int maxIndex) {
        for (int i = 0; i <= maxIndex / 2; i++) {
            int tmp = arr[i];
            arr[i] = arr[maxIndex - i];
            arr[maxIndex - i] = tmp;
        }
    }
}
```