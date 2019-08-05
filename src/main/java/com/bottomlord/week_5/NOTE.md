# LeetCode_892_三维形体的表面积
## 题目
在 N * N 的网格上，我们放置一些 1 * 1 * 1  的立方体。

每个值 v = grid[i][j] 表示 v 个正方体叠放在对应单元格 (i, j) 上。

请你返回最终形体的表面积。

示例 1：
```
输入：[[2]]
输出：10
```
示例 2：
```
输入：[[1,2],[3,4]]
输出：34
```
示例 3：
```
输入：[[1,0],[0,2]]
输出：16
示例 4：
```
输入：[[1,1,1],[1,0,1],[1,1,1]]
输出：32
示例 5：

输入：[[2,2,2],[2,1,2],[2,2,2]]
输出：46

提示：
```
1 <= N <= 50
0 <= grid[i][j] <= 50
```
## 解法
### 思路
把表面积分成6部分
- 上下两面：(N * N - 为0的元素) * 2
- 4个面：
    - 第1行的v的和
    - 第N行的v的和
    - 第1列的v的和
    - 第N列的v的和
- 上面参差不齐露出的部分

难点在露出的部分，需要遍历二维数组，和相邻4个位置的v作比较，大于相邻的部分就累加表面积里
### 代码
```java
class Solution {
    public int surfaceArea(int[][] grid) {
        return topBottom(grid) + side(grid) + diff(grid);
    }

    private int diff(int[][] grid) {
        int sum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    sum-=2;
                    continue;
                }
                
                if (!outOfRnage(i - 1, j, grid)) {
                    int dif = grid[i][j] - grid[i - 1][j];
                    sum += dif > 0 ? dif : 0;
                }

                if (!outOfRnage(i + 1, j, grid)) {
                    int dif = grid[i][j] - grid[i + 1][j];
                    sum += dif > 0 ? dif : 0;
                }

                if (!outOfRnage(i, j - 1, grid)) {
                    int dif = grid[i][j] - grid[i][j - 1];
                    sum += dif > 0 ? dif : 0;
                }

                if (!outOfRnage(i, j + 1, grid)) {
                    int dif = grid[i][j] - grid[i][j + 1];
                    sum += dif > 0 ? dif : 0;
                }
            }
        }

        return sum;
    }

    private int side(int[][] grid) {
        int sum = 0;
        for (int i = 0; i < grid[0].length; i++) {
            sum += grid[0][i];
        }

        int lastRow = grid.length - 1;
        for (int i = 0; i < grid[lastRow].length; i++) {
            sum += grid[lastRow][i];
        }

        for (int[] row : grid) {
            sum += row[0] + row[row.length - 1];
        }
        return sum;
    }

    private int topBottom(int[][] grid) {
        return grid.length * grid.length * 2;
    }

    private boolean outOfRnage(int row, int col, int[][] grid) {
        return row < 0 || col < 0 || row >= grid.length || col >= grid[row].length;
    }
}
```
## 解法二
### 思路
遍历二维数组：
- 先把当前坐标上的立方体的表面积算出：v * 4 + 2，累加
- 和横坐标上前一个元素比较大小，谁堆叠的立方体少，谁的一面就会被遮盖，当然长的那个立方体的一面也会遮盖，把这两个面从累加值中减去
- 同理和纵坐标也做一次比较
- 遍历结束，返回累加值
### 代码
```java
class Solution {
    public int surfaceArea(int[][] grid) {
        int sum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != 0) {
                    sum += grid[i][j] * 4 + 2;
                }
                
                if (i != 0) {
                    sum -= Math.min(grid[i][j], grid[i - 1][j]) * 2;
                }
                
                if (j != 0) {
                    sum -= Math.min(grid[i][j], grid[i][j - 1]) * 2;
                }
            }
        }
        
        return sum;
    }
}
```
# LeetCode_653_两数之和 IV (输入 BST)
## 题目
给定一个二叉搜索树和一个目标结果，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。

案例 1:
```
输入: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 9

输出: True
```
案例 2:
```
输入: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 28

输出: False
```
## 解法
### 思路
- dfs中序递归遍历得到升序序列
- 迭代寻找是否有满足target的两个值
### 代码
```java
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                int ans = list.get(i) + list.get(j); 
                
                if (ans == k) {
                    return true;
                }
                
                if (ans > k) {
                    break;
                }
            }
        }
        return false;
    }
    
    private void dfs(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        
        dfs(node.left, list);
        list.add(node.val);
        dfs(node.right, list);
    }
}
```
## 解法二
### 思路
嵌套递归
- 第一层递归目的是找到两数相加的第一个数，而是否进入第二层递归的判断依据是，k-当前节点的val是否还等于当前节点的val，如果是说明当前节点不符合要求，因为二叉搜索树的节点不能相等
- 如果符合要求，就进行第二层递归，目的是从根节点开始找到k与第一层递归的那个节点的val的差，如果有就返回true
- 如果如果没有找到，就递归取找左右子树，再重复如上的过程
### 代码
```java
class Solution {
    private TreeNode node;
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        
        if (node == null) {
            node = root;
        }

        int result = k - root.val;
        if (root.val == result) {
            return findTarget(root.left, k) || findTarget(root.right, k);
        }

        boolean find = dfs(this.node, result);
        if (find) {
            return true;
        }

        return findTarget(root.left, k) || findTarget(root.right, k);
    }

    private boolean dfs(TreeNode node, int target) {
        if (node == null) {
            return false;
        }

        if (node.val == target) {
            return true;
        }

        return node.val > target ? dfs(node.left, target) : dfs(node.right, target);
    }
}
```
# LeetCode_448_找到所有数组中消失的数字
## 题目
给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。

找到所有在 [1, n] 范围之间没有出现在数组中的数字。

您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。

示例:
```
输入:
[4,3,2,7,8,2,3,1]

输出:
[5,6]
```
## 解法
### 思路
- 遍历数组
    - 通过标记元素值对应下标为-1来表明这个位置的值已经找到
    - 通过通过标记下标对应地元素为0来表明这个位置地值还没有找到
- 再通过遍历重新设置过地数组，把为0的数组放入list
### 代码
```java
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                int tmp = nums[i];
                nums[i] = 0;
                while (tmp > 0) {
                    int next = nums[tmp - 1];
                    nums[tmp - 1] = -1;
                    tmp = next;
                }
            }
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                ans.add(i + 1);
            }
        }
        
        return ans;
    }
}
```
# LeetCode_437_路径总和III
## 题目
给定一个二叉树，它的每个结点都存放着一个整数值。

找出路径和等于给定数值的路径总数。

路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。

二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。

示例：
```
root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

返回 3。和等于 8 的路径有:

1.  5 -> 3
2.  5 -> 2 -> 1
3.  -3 -> 11
```
## 解法
### 思路
因为路径是向下的，所以使用dfs。

感觉很像653题，从某个节点开始下钻。所以是嵌套的递归。
- 外层递归每一层的节点，选择从该节点开始内层递归，并判断当前节点是否与sum相等
- 内层递归从该节点的下钻路径，计算和是否满足要求，满足的话就count++
### 代码
```java
class Solution {
    private int count = 0;
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return count;
        }
        
        dfs(root, sum);
        return count;
    }
    
    private void dfs(TreeNode node, int sum) {
        if (node == null) {
            return;
        }
        
        if (node.val == sum) {
            this.count++;
        }
        
        dfs(node.left, sum);
        
        doSearch(node.left, node.val, sum);
        doSearch(node.right, node.val, sum);
        
        dfs(node.right, sum);
    }
    
    private void doSearch(TreeNode node, int sum, int target) {
        if (node == null) {
            return;
        }
        
        sum += node.val;
        
        if (sum == target) {
            this.count++;
        }
        
        doSearch(node.left, sum, target);
        doSearch(node.right, sum, target);
    }
}
```
## 解法二
### 思路
解法一有非常多的重复遍历，效率不高，看到有人分享了dfs+回溯的方法，非常有效。主要思想就是：
- dfs深度优先的每一次深度搜索过程中，把每一层的累加值记录在一个map中
- 如果到节点N的累加值为a，那么如果在map中能找到a - sum的key，就说明，从该key所对应的节点开始，到N点的总和就是sum
- 所以只需要将key对应的value累加，就可以得到结果
- 但需要注意的是，map里存的必需是一条通路上的节点，所以在return之前需要将节点值key对应的value从map中减去
### 代码
```java
class Solution {
    public int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        return dfs(root, sum, 0, map);
    }

    private int dfs(TreeNode node, int sum, int pathSum, Map<Integer, Integer> map) {
        if (node == null) {
            return 0;
        }

        pathSum += node.val;
        int count = map.getOrDefault(pathSum - sum, 0);
        map.put(pathSum, map.getOrDefault(pathSum, 0) + 1);
        int ans = count + dfs(node.left, sum, pathSum, map) + dfs(node.right, sum, pathSum, map);
        map.put(pathSum, map.get(pathSum) - 1);
        return ans;
    }
}
```
# LeetCode_453_最小移动次数使数组元素相等
## 题目
给定一个长度为 n 的非空整数数组，找到让数组所有元素相等的最小移动次数。每次移动可以使 n - 1 个元素增加 1。

示例:
```
输入:
[1,2,3]

输出:
3
```
解释:
```
只需要3次移动（注意每次移动会增加两个元素的值）：

[1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
```
## 解法
### 思路
- 将数组排序
- 试想，将原最大值以外的所有元素增加最大值和最小值的差diff，原最大元素a[n-1]就和原最小元素a[0]一样了
- 因为经过了排序，且同时增大diff，所以最小元素到第二大元素的顺序是不变的，因此a[n-1] == a[0]变成了最小值，a[n-2]变成了最大值
- 那么以此类推，可以不断地将新的最大值和新的最小值的差值累加，直到所有的元素都相等
### 代码
```java
class Solution {
    public int minMoves(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        for (int i = nums.length - 1; i > 0; i--) {
            ans += nums[i] - nums[0];
        }
        return ans;
    }
}
```