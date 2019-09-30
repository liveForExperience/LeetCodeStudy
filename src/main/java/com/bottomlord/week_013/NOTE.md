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