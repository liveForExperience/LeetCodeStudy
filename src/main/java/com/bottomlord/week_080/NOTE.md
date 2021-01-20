# [LeetCode_425_单词方块](https://leetcode-cn.com/problems/word-squares/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_1232_缀点成线](https://leetcode-cn.com/problems/check-if-it-is-a-straight-line/)
## 解法
### 思路
- 将第一个点平移到原点位置，同时其他所有点对应平移
- 遍历数组，基于`ax + bx == 0`判断是否是同一直线的方式求结果
- 其中`a = coordinator[1][1]`，`b = coordinator[1][0]`
### 代码
```java
class Solution {
    public boolean checkStraightLine(int[][] coordinates) {
        int deltaA = coordinates[0][0], deltaB = coordinates[0][1], n = coordinates.length;
        for (int i = 0; i < n; i++) {
            coordinates[i][0] -= deltaA;
            coordinates[i][1] -= deltaB;
        }

        int a = coordinates[1][1], b = -coordinates[1][0];

        for (int i = 2; i < n; i++) {
            if (coordinates[i][0] * a + coordinates[i][1] * b != 0) {
                return false;
            }
        }
        
        return true;
    }
}
```
# [LeetCode_721_账户合并](https://leetcode-cn.com/problems/accounts-merge/)
## 解法
### 思路
并查集
- 遍历accounts二维数组，生成如下内容：
    - 生成一个邮箱和坐标id的映射关系`emailIndexMap`
    - 生成一个邮箱和账户的映射关系`emailAccountMap`
    - 需要注意：重复出现的邮箱只生成一次坐标id，这样才能将不同账户，相同邮箱的情况通过并查集关联起来
- 遍历accounts二维数组，通过`emailIndexMap`，将坐标通过并查集关联起来
- 初始化`indexEmailListMap`，key为index，value为email的集合，作为并查集父index与关联email的映射关系
- 遍历`emailIndexMap`的key集合，通过并查集将index的parentIndex求出，作为key，然后将email放入对应的list中
- 遍历`indexEmailListMap`的values集合，通过list的第一个email到`emailAccountMap`中获得账户，并对values排序，最后放入结果集合中
### 代码
```java
class Solution {
        public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Integer> emailIndexMap = new HashMap<>();
        Map<String, String> emailAccountMap = new HashMap<>();

        int emailIndex = 0;
        for (List<String> account : accounts) {
            String name = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                if (!emailIndexMap.containsKey(email)) {
                    emailIndexMap.put(email, emailIndex++);
                    emailAccountMap.put(email, name);
                }
            }
        }

        Uf uf = new Uf(emailIndex);
        for (List<String> account : accounts) {
            String firstEmail = account.get(1);
            int firstIndex = emailIndexMap.get(firstEmail);

            for (int i = 2; i < account.size(); i++) {
                String nextEmail = account.get(i);
                int nextIndex = emailIndexMap.get(nextEmail);
                uf.union(firstIndex, nextIndex);
            }
        }

        Map<Integer, List<String>> indexEmailListMap = new HashMap<>();
        for (String email : emailIndexMap.keySet()) {
            int index = uf.find(emailIndexMap.get(email));
            indexEmailListMap.computeIfAbsent(index, x -> new ArrayList<>()).add(email);
        }

        List<List<String>> ans = new ArrayList<>();
        for (List<String> emails : indexEmailListMap.values()) {
            String account = emailAccountMap.get(emails.get(0));
            Collections.sort(emails);
            List<String> list = new ArrayList<>();
            list.add(account);
            list.addAll(emails);
            ans.add(list);
        }
        
        return ans;
    }

    private static class Uf {
        private final int[] parent;
        private final int[] rank;

        public Uf(int n) {
            parent = new int[n];
            rank = new int[n];
            
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public void union(int x, int y) {
            int rootX = find(x), rootY = find(y);

            if (rootX == rootY) {
                return;
            }

            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                rank[rootX]++;
                parent[rootY] = rootX;
            }
        }

        public int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }

            return parent[x];
        }
    }
}
```
# [LeetCode_1584_连接所有点的最小费用](https://leetcode-cn.com/problems/min-cost-to-connect-all-points/)
## 解法
### 思路
- 获取一个有N个节点的图，且答案要求的是从这个图中获取一个子图，子图中2点之间有且仅有一条路径，而这样一个子图一定是一颗树
- 通过图获取最小生成树的算法：Kruskal
    - 将图G=\{V,E\}G={V,E}中的所有边按照长度由小到大进行排序，等长的边可以按任意顺序。
    - 初始化图G'为{V,∅}，从前向后扫描排序后的边，如果扫描到的边e在G'中连接了两个相异的连通块,则将它插入G'中。
    - 最后得到的图G'就是图G的最小生成树。 
- 实际代码过程：
    - 将完全图中的边全部提取到边集数组中
    - 对所有边进行排序
    - 从小到大枚举所有边，每次都贪心选择并放入答案中
    - 选择的条件是，通过维护的并查集，检查连通性，如果不联通就选择
### 代码
```java
class Solution {
        public int minCostConnectPoints(int[][] points) {
        int len = points.length;

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                edges.add(new Edge(dist(points, i, j), i, j));
            }
        }

        edges.sort(Comparator.comparingInt(x -> x.len));

        Uf uf = new Uf(len);
        int ans = 0, num = 1;
        for (Edge edge : edges) {
            if (uf.union(edge.x, edge.y)) {
                ans += edge.len;
                num++;

                if (num == len) {
                    break;
                }
            }
        }

        return ans;
    }

    private int dist(int[][] points, int i, int j) {
        return Math.abs(points[i][0]  - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
    }

    private static class Edge {
        private int len, x, y;
        public Edge(int len, int x, int y) {
            this.len = len;
            this.x = x;
            this.y = y;
        }
    }

    private static class Uf {
        private int[] parent;
        private int[] rank;

        public Uf(int n) {
            this.parent = new int[n];
            this.rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        private int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        private boolean union(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX == rootY) {
                return false;
            }

            if (rank[rootX] < rank[rootY]) {
                parent[rootY] = parent[rootX];
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootX] = parent[rootY];
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }

            return true;
        }
    }
}
```