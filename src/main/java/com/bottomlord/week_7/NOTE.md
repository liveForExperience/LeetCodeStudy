# LeetCode_35_搜索插入位置
## 题目
给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。

你可以假设数组中无重复元素。

示例 1:
```
输入: [1,3,5,6], 5
输出: 2
```
示例 2:
```
输入: [1,3,5,6], 2
输出: 1
```
示例 3:
```
输入: [1,3,5,6], 7
输出: 4
```
示例 4:
```
输入: [1,3,5,6], 0
输出: 0
```
## 解法
### 思路
遍历数组
- 找到目标值，就返回下标
- 未找到目标值，但遍历到的元素大于目标值，返回该元素下标
- 未找到目标值，且遍历结束，返回数组长度
### 代码
```java
class Solution {
    public int searchInsert(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target || nums[i] > target) {
                return i;
            }
        }
        
        return nums.length;
    }
}
```
## 解法二
### 思路
使用头尾指针进行二分查找，加快速度
### 代码
```java
class Solution {
    public int searchInsert(int[] nums, int target) {
        if (target > nums[nums.length - 1]) {
            return nums.length;
        }
        
        int head = 0, tail = nums.length - 1, mid = 0;
        while (head < tail) {
            mid = head + (tail - head) / 2;
            
            if (nums[mid] == target) {
                return mid;
            }
            
            if (nums[mid] > target) {
                tail = mid;
            }
            
            if (nums[mid] < target) {
                head = mid + 1;
            }
        }
        
        return tail;
    }
}
```
# LeetCode_558_四叉树交集
## 题目
四叉树是一种树数据，其中每个结点恰好有四个子结点：topLeft、topRight、bottomLeft 和 bottomRight。四叉树通常被用来划分一个二维空间，递归地将其细分为四个象限或区域。

我们希望在四叉树中存储 True/False 信息。四叉树用来表示 N * N 的布尔网格。对于每个结点, 它将被等分成四个孩子结点直到这个区域内的值都是相同的。每个节点都有另外两个布尔属性：isLeaf 和 val。当这个节点是一个叶子结点时 isLeaf 为真。val 变量储存叶子结点所代表的区域的值。

例如，下面是两个四叉树 A 和 B：
```
A:
+-------+-------+   T: true
|       |       |   F: false
|   T   |   T   |
|       |       |
+-------+-------+
|       |       |
|   F   |   F   |
|       |       |
+-------+-------+
topLeft: T
topRight: T
bottomLeft: F
bottomRight: F

B:               
+-------+---+---+
|       | F | F |
|   T   +---+---+
|       | T | T |
+-------+---+---+
|       |       |
|   T   |   F   |
|       |       |
+-------+-------+
topLeft: T
topRight:
     topLeft: F
     topRight: F
     bottomLeft: T
     bottomRight: T
bottomLeft: T
bottomRight: F
```
你的任务是实现一个函数，该函数根据两个四叉树返回表示这两个四叉树的逻辑或(或并)的四叉树。
```
A:                 B:                 C (A or B):
+-------+-------+  +-------+---+---+  +-------+-------+
|       |       |  |       | F | F |  |       |       |
|   T   |   T   |  |   T   +---+---+  |   T   |   T   |
|       |       |  |       | T | T |  |       |       |
+-------+-------+  +-------+---+---+  +-------+-------+
|       |       |  |       |       |  |       |       |
|   F   |   F   |  |   T   |   F   |  |   T   |   F   |
|       |       |  |       |       |  |       |       |
+-------+-------+  +-------+-------+  +-------+-------+
```
提示：
```
A 和 B 都表示大小为 N * N 的网格。
N 将确保是 2 的整次幂。
如果你想了解更多关于四叉树的知识，你可以参考这个 wiki 页面。
逻辑或的定义如下：如果 A 为 True ，或者 B 为 True ，或者 A 和 B 都为 True，则 "A 或 B" 为 True。
```
## 解法
### 思路
- 四个象限上：
    - 如果当前node是叶子节点，就返回当前node
    - 如果当前node不是叶子节点，就返回另一个node
    - 如果都不是叶子节点，就递归下一层
- 返回的node
    - 如果都是true或false，需要合并
    - 否则返回新的节点，isLeaf和val都是false，四个节点未递归得到的4个象限节点
### 代码
```java
class Solution {
    public Node intersect(Node quadTree1, Node quadTree2) {
        if (quadTree1.isLeaf) {
            if (quadTree1.val) {
                return quadTree1;
            } else {
                return quadTree2;
            }
        }

        if (quadTree2.isLeaf) {
            if (quadTree2.val) {
                return quadTree2;
            } else {
                return quadTree1;
            }
        }

        Node topLeft = intersect(quadTree1.topLeft, quadTree2.topLeft);
        Node topRight = intersect(quadTree1.topRight, quadTree2.topRight);
        Node bottomLeft = intersect(quadTree1.bottomLeft, quadTree2.bottomLeft);
        Node bottomRight = intersect(quadTree1.bottomRight, quadTree2.bottomRight);

        if (topLeft.isLeaf && topLeft.val && topRight.isLeaf && topRight.val && bottomLeft.isLeaf && bottomLeft.val && bottomRight.isLeaf && bottomRight.val) {
            return new Node(true, true, null, null, null, null);
        }

        if (topLeft.isLeaf && !topLeft.val && topRight.isLeaf && !topRight.val && bottomLeft.isLeaf && !bottomLeft.val && bottomRight.isLeaf && !bottomRight.val) {
            return new Node(false, true, null, null, null, null);
        }

        return new Node(false, false, topLeft, topRight, bottomLeft, bottomRight);
    }
}
```