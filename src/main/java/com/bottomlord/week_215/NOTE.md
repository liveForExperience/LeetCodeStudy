# [LeetCode_2337_移动片段得到字符串](https://leetcode.cn/problems/move-pieces-to-obtain-a-string/)
## 解法
### 思路
- 逻辑变量：
  - n：`start`和`target`的长度
  - i：`start`字符串对应字符的坐标
  - j：`target`字符串对应字符的坐标
- 逻辑过程：
  - 双指针遍历，退出条件是`i`和`j`坐标同时越界
  - 当`i`或`j`坐标都小于n时
    - 跳过所有"_"
    - 如果`i`和`j`遇到的第一个非`_`坐标不相等，返回false
    - 如果遇到的是`L`，且`j`大于`i`返回false
    - 如果遇到的是`R`，且`j`小于`i`返回false
  - 当`i`或`j`坐标大于等于n时
    - 如果`j`大于等于n，则返回false
    - 如果`i`大于等于n，且`j`坐标到`n-1`区间内，存在非`_`字符，则返回false
  - 遍历结束，返回true
### 代码
```java
class Solution {
    public boolean canChange(String start, String target) {
        int n = start.length(), i = 0, j = 0;
        while (i < n || j < n) {
            while (i < n && start.charAt(i) == '_') {
                i++;
            }
            
            while (j < n && target.charAt(j) == '_') {
                j++;
            }
            
            if (i < n && j >= n) {
                return false;
            }
            
            if (i >= n) {
                while (j < n) {
                    if (target.charAt(j++) != '_') {
                        return false;
                    }
                }
                
                return true;
            }
            
            char ci = start.charAt(i), cj = target.charAt(j);
            if (ci != cj) {
                return false;
            }
            
            if (ci == 'L' && i < j) {
                return false;
            }

            if (ci == 'R' && i > j) {
                return false;
            }

            i++;
            j++;
        }
        
        return true;
    }
}
```
# [LeetCode_1267_统计参与通信的服务器](https://leetcode.cn/problems/count-servers-that-communicate/)
## 解法
### 思路
- 第一次遍历二维数组，使用2个map来统计行和列上的计算机个数
- 第二次遍历，基于第一次生成的2个map，判断当前计算机是否能与其他服务器通信，也即是否有大于1的行列，然后根据判断结果计数
- 返回第二次遍历的统计结果
### 代码
```java
class Solution {
    public int countServers(int[][] grid) {
        Map<Integer, Integer> rmap = new HashMap<>(), cmap = new HashMap<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                
                rmap.put(i, rmap.getOrDefault(i, 0) + 1);
                cmap.put(j, cmap.getOrDefault(j, 0) + 1);
            }
        }
        
        int cnt = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                
                if (rmap.get(i) > 1 || cmap.get(j) > 1) {
                    cnt++;
                }
            }
        }
        
        return cnt;
    }
}
```
# [LeetCode_1448_统计二叉树中好节点的数目](https://leetcode.cn/problems/count-good-nodes-in-binary-tree/)
## 解法
### 思路
- 定义一个类变量`cnt`，在遍历过程中用来记录好节点个数
- dfs遍历整棵树，在下钻的过程中，维护一个从根节点下来的最大值`max`，如果当前节点的val不小于最大值，就累加`cnt`，并更新这个最大值`max`
- 遍历结束，返回`max`
### 代码
```java
class Solution {
    private int cnt;
    
    public int goodNodes(TreeNode root) {
        this.cnt = 0;
        dfs(root, root.val);
        return cnt;
    }
    
    private void dfs(TreeNode node, int max) {
        if (node == null) {
            return;
        }
        
        if (node.val >= max) {
            cnt++;
        }
        
        max = Math.max(max, node.val);
        dfs(node.left, max);
        dfs(node.right, max);
    }
}
```