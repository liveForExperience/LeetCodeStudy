# LeetCode_986_区间列表的交集
## 题目
给定两个由一些闭区间组成的列表，每个区间列表都是成对不相交的，并且已经排序。

返回这两个区间列表的交集。

（形式上，闭区间 [a, b]（其中 a <= b）表示实数 x 的集合，而 a <= x <= b。两个闭区间的交集是一组实数，要么为空集，要么为闭区间。例如，[1, 3] 和 [2, 4] 的交集为 [2, 3]。）

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
给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。

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