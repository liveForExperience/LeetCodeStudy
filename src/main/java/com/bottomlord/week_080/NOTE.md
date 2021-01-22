# [LeetCode_425_单词方块](https://leetcode-cn.com/problems/word-squares/)
## 解法
### 思路
回溯：
- 遍历words列表，从每一个单词开始查找组合的可能性，查找可能性的方式就是回溯
- 回溯：
    - 参数：
        - 坐标index：记录当前遍历到单词的第几个字符，也相当于矩形的第几行
        - 字符串集合wordList：记录了可以作为下一行候选的单词集合，回溯和返回结果时，都是基于这个集合
    - 退出的条件：
        - 单词长度遍历完，将当前的集合放入结果大集合中
        - 没有符合前缀要求的字符串作为下一个单词，返回
    - 过程：
        - 根据`index`，获取下一个单词需要符合的前缀字符串，用来寻找下一行的集合
        - 遍历所有符合要求的单词，也就是符合前缀的单词，然后将这个单词加入到`wordList`，然后进入下一层，回溯返回时，再从`wordList`中去除当前加入的这个单词
### 代码
```java
class Solution {
    public List<List<String>> wordSquares(String[] words) {
        int len = words[0].length();
        List<List<String>> ans = new ArrayList<>();
        for (String word : words) {
            backTrack(1, len, words, new ArrayList<String>(){{this.add(word);}}, ans);
        }
        return ans;
    }

    private void backTrack(int index, int len, String[] words, List<String> wordList, List<List<String>> ans) {
        if (index == len) {
            ans.add(new ArrayList<>(wordList));
            return;
        }

        StringBuilder prefixSb = new StringBuilder();
        for (String word : wordList) {
            prefixSb.append(word.charAt(index));
        }
        String prefix = prefixSb.toString();

        for (String word : words) {
            if (word.startsWith(prefix)) {
                wordList.add(word);
                backTrack(index + 1, len, words, wordList, ans);
                wordList.remove(wordList.size() - 1);
            }
        }
    }
}
```
## 解法二
### 思路
在解法一的基础上增加一个hash表，这个hash表用来加速的步骤是：回溯过程中能匹配前缀的字符串集合
### 代码
```java
class Solution {
    public List<List<String>> wordSquares(String[] words) {
        int len = words[0].length();
        Map<String, List<String>> map = new HashMap<>();
        List<List<String>> ans = new ArrayList<>();
        for (String word : words) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < len - 1; i++) {
                sb.append(word.charAt(i));
                String prefix = sb.toString();
                List<String> list = map.getOrDefault(prefix, new ArrayList<>());
                list.add(word);
                map.put(prefix, list);
            }
        }

        for (String word : words) {
            backTrack(1, len, new ArrayList<String>(){{this.add(word);}}, ans, map);
        }

        return ans;
    }

    private void backTrack(int index, int len, List<String> wordList, List<List<String>> ans, Map<String, List<String>> map) {
        if (index == len) {
            ans.add(new ArrayList<>(wordList));
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (String word : wordList) {
            sb.append(word.charAt(index));
        }
        String prefix = sb.toString();

        if (!map.containsKey(prefix)) {
            return;
        }

        for (String word : map.get(prefix)) {
            wordList.add(word);
            backTrack(index + 1, len, wordList, ans, map);
            wordList.remove(wordList.size() - 1);
        }
    }
}
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
# [LeetCode_1489_找到最小生成树里的关键边和伪关键边](https://leetcode-cn.com/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree/solution/zhao-dao-zui-xiao-sheng-cheng-shu-li-de-gu57q/)
## 解法
### 思路
Kruskal最小生成树算法：
- 生成一个新的边的集合，这个集合的元素在原来集合元素的基础上，多存储一个原来集合元素的坐标
- 对新的边集合根据权重从小到大排序
- 通过kruskal算法获得最小生成树的权重和value
- 然后遍历所有的边，依次做如下判断：
    - 如果故意删除当前边，再通过kruskal算法计算权重和，得到的值是否比value大，如果是的话认为这条边为关键边，直接判断下一条边
    - 如果当前边不是关键边，那就只需要判断当前边是否能组成最小树集合，就能判断是不是伪关键边
    - 遍历过程中，如果符合如上2种情况，就保存下原来的边集合坐标
- 最后返回暂存的结果集合
### 代码
```java
class Solution {
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
                int[][] newEdges = new int[edges.length][4];
        for (int i = 0; i < edges.length; i++) {
            System.arraycopy(edges[i], 0, newEdges[i], 0, 3);
            newEdges[i][3] = i;
        }

        Arrays.sort(newEdges, Comparator.comparingInt(x -> x[2]));
        Uf uf = new Uf(n);
        int value = 0;
        for (int i = 0; i < edges.length; i++) {
            if (uf.union(newEdges[i][0], newEdges[i][1])) {
                value += newEdges[i][2];
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());
        ans.add(new ArrayList<>());

        for (int i = 0; i < edges.length; i++) {
            int v = 0;
            Uf uf1 = new Uf(n);
            for (int j = 0; j < edges.length; j++) {
                if (i != j && uf1.union(newEdges[j][0], newEdges[j][1])) {
                    v += newEdges[j][2];
                }
            }

            if (uf1.count != 1 || v > value) {
                ans.get(0).add(newEdges[i][3]);
                continue;
            }

            Uf uf2 = new Uf(n);
            uf2.union(newEdges[i][0], newEdges[i][1]);
            int v2 = newEdges[i][2];

            for (int j = 0; j < edges.length; j++) {
                if (i != j && uf2.union(newEdges[j][0], newEdges[j][1])) {
                    v2 += newEdges[j][2];
                }
            }

            if (uf2.count == 1 && v2 == value) {
                ans.get(1).add(newEdges[i][3]);
            }
        }

        return ans;
    }

    private static class Uf {
        private final int[] parent;
        private final int[] rank;
        private int count;

        public Uf(int n) {
            this.parent = new int[n];
            this.rank = new int[n];
            this.count = n;
            for (int i = 0; i < n; i++) {
                this.parent[i] = i;
                this.rank[i] = 1;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public boolean union(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX == rootY) {
                return false;
            }

            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                rank[rootX]++;
                parent[rootY] = rootX;
            }

            count--;
            return true;
        }
    }
}
```