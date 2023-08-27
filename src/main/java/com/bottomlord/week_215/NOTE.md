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
# [LeetCode_1782_统计点对的数目](https://leetcode.cn/problems/count-pairs-of-nodes)
## 解法
### 思路
- 设每个有效点对相连的边的个数是`cnt`，答案要求的是`cnt`大于`queries[i]`的点对的个数
- 如果点对由`a`和`b`组成，`cnt`可以通过`edge[a] + edge[b] - dup(a,b)`得到
- `dup(a,b)`的含义是，如果边是`(a,b)`，那么这种边会导致计算的边数重复，`dup(a,b)`就是计算这样的边的个数
- 如果正常做，就是枚举所有的`a`和`b`，计算相连边的个数，这样的时间复杂度是`O(N²)`，会超时
- 如果不去考虑重复的问题，只找到满足`edge[a] + edge[b] > queries[i]`的点对个数，可以使用二分查找：
  - 首先将所有坐标对应的`edgee[x]`维护好，可以通过枚举`edges`二维数组统计，`x`即点对中的点
  - 其次，因为二分查找需要查找的数组是有序的，而我们要查找的是`edge[x]`的边数对应的数组，所以需要对`edge[x]`进行从小到大的排序
  - 初始化指针`a = 1`和`b = n`，坐标对应`edges`中的值，也即点对中的点，该值范围是`[1,n]`
  - 初始化一个循环，循环继续条件是`a < b`，因为题目要求点对是符合`a < b`的
  - 如果`edge[a] + edge[b] <= queries[i]`，说明`edge[a]`与任何一个在`(a, b)`范围内的`b`坐标，它们的度的和都一定小于等于`queries[i]`，所以可以不用考虑这个`a`坐标，`a`右移
  - 如果`edge[a] + edge[b] > queries[i]`
    - 这个状态是符合题目要求的，所以所有以`b`坐标为右端点，范围在`[a,b)`的坐标，都符合题目的要求，因为通过对度的个数排序后，所有这些`edge[a]`与当前确定的`edge[b]`相加，都一定大于`queries[i]`
    - 所以可以将这些`a`的个数统计出来后，累加到暂定的不考虑重复的答案个数中
    - 然后在处理过如上步骤后，这个`b`坐标就不需要考虑了，可以左移
  - 当循环退出后，累计出来的个数，就是不考虑重复边状态下的答案个数了
- 之后就是考虑去除掉重复边的问题，可以通过枚举所有点对出现的个数`c`，如果这些点对`(a,b)`的各自的度满足：
  - `edge[a] + edge[c] > queries[i]`
  - `edge[a] + edge[b] - c <= queries[i]`
  - 那么就说明其实这组点对实际是不符合题目要求的，就把这组点对从答案个数中减去
- 如此循环`queries`数组的所有元素，得到对应的答案值即可
### 代码
```java
class Solution {
  public int[] countPairs(int n, int[][] edges, int[] queries) {
    int[] degrees = new int[n + 1];
    Map<Integer, Integer> edgeMap = new HashMap<>();
    for (int[] edge : edges) {
      int x = edge[0], y = edge[1];
      if (x > y) {
        int tmp = y;
        y = x;
        x = tmp;
      }

      degrees[x]++;
      degrees[y]++;
      int mask = (x << 16) | y;
      edgeMap.put(mask, edgeMap.getOrDefault(mask, 0) + 1);
    }

    int[] ans = new int[queries.length];
    int[] sortedDegrees = degrees.clone();
    Arrays.sort(sortedDegrees);
    for (int i = 0; i < queries.length; i++) {
      int a = 1, b = n, query = queries[i];
      while (a < b) {
        if (sortedDegrees[a] + sortedDegrees[b] <= query) {
          a++;
        } else {
          ans[i] += b - a;
          b--;
        }
      }

      for (Map.Entry<Integer, Integer> entry : edgeMap.entrySet()) {
        int mask = entry.getKey(), c = entry.getValue(),
                x = mask >> 16, y = mask & 0xffff, sum = degrees[x] + degrees[y];

        if (sum > query && sum - c <= query) {
          ans[i]--;
        }
      }
    }

    return ans;
  }
}
```