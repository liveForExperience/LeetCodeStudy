# [LeetCode_2673_使二叉树所有路径值相等的最小代价](https://leetcode.cn/problems/make-costs-of-paths-equal-in-a-binary-tree/)
## 解法
### 思路
- 思考过程：
  - 因为题目限制了这颗树是满二叉树，也就意味着每个路径上的节点数是一样的
  - 因为互为兄弟的2个节点，他们所对应的路径除了自身不同之外，其他节点都是相同的，如果要使这两个路径的值总和相等，就必须使2者相等
  - 那么，为了解决整颗树的所有路径值之和都相等，就可以通过自底向上的思路，一对对兄弟的相等，然后将相等的值累加到父节点上，让父节点的兄弟通过增加的值知道其与其兄弟之间需要通过多大的差值，才能将他们对应的子树的所有路径都计算成路径和相等，最终推导到根节点
- 算法过程
  - 初始化答案值`ans`
  - 从最右的叶子节点开始遍历，每次遍历一组兄弟节点
  - 根据`cost`的坐标作为遍历的坐标，从`n`和`n + 1`开始向数组的开头开始遍历，步长为2，终止条件是`i > 0`，因为根节点没有兄弟节点，所以不必处理
  - 每次都计算兄弟节点的差值，将差值的绝对值累加到`ans`
  - 然后将兄弟节点中的较大的那个值增加到父节点上，父节点即`i / 2`
  - 继续循环，直到循环结束，返回`ans`作为结果
### 代码
```java

```