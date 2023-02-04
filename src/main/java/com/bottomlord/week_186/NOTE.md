# [LeetCode_1632_矩阵转换后的秩](https://leetcode.cn/problems/rank-transform-of-a-matrix/)
## 解法
### 思路
hash表 + 并查集 + 拓扑排序
### 代码
```java
class Solution {
    public int[][] matrixRankTransform(int[][] matrix) {
        int r = matrix.length, c = matrix[0].length;
        Uf uf = new Uf(r * c);

        for (int i = 0; i < r; i++) {
            Map<Integer, List<Integer>> map = new HashMap<>();
            for (int j = 0; j < c; j++) {
                int index = i * c + j, val = matrix[i][j];
                map.computeIfAbsent(val, x -> new ArrayList<>()).add(index);
            }

            for (List<Integer> list : map.values()) {
                if (list.size() < 2) {
                    continue;
                }

                for (int k = 1; k < list.size(); k++) {
                    uf.union(list.get(k - 1), list.get(k));
                }
            }
        }

        for (int j = 0; j < c; j++) {
            Map<Integer, List<Integer>> map = new HashMap<>();
            for (int i = 0; i < r; i++) {
                int index = i * c + j, val = matrix[i][j];
                map.computeIfAbsent(val, x -> new ArrayList<>()).add(index);
            }

            for (List<Integer> list : map.values()) {
                if (list.size() < 2) {
                    continue;
                }

                for (int k = 1; k < list.size(); k++) {
                    uf.union(list.get(k - 1), list.get(k));
                }
            }
        }

        List<Integer>[] adjs = new ArrayList[r * c];
        for (int i = 0; i < adjs.length; i++) {
            adjs[i] = new ArrayList<>();
        }

        int[] inDegrees = new int[r * c];
        for (int i = 0; i < r; i++) {
            int[][] arr = new int[c][2];
            for (int j = 0; j < c; j++) {
                arr[j][0] = matrix[i][j];
                arr[j][1] = j;
            }

            Arrays.sort(arr, Comparator.comparingInt(x -> x[0]));
            for (int j = 1; j < c; j++) {
                if (arr[j - 1][0] != arr[j][0]) {
                    int u = uf.find(i * c + arr[j - 1][1]);
                    int v = uf.find(i * c + arr[j][1]);
                    inDegrees[v]++;
                    adjs[u].add(v);
                }
            }
        }

        for (int j = 0; j < c; j++) {
            int[][] arr = new int[r][2];
            for (int i = 0; i < r; i++) {
                arr[i][0] = matrix[i][i];
                arr[i][1] = i;
            }

            Arrays.sort(arr, Comparator.comparingInt(x -> x[0]));
            for (int i = 1; i < r; i++) {
                if (arr[i - 1][0] != arr[i][0]) {
                    int u = uf.find(arr[i - 1][1] * c + j);
                    int v = uf.find(arr[i][1] * c + j);
                    inDegrees[v]++;
                    adjs[u].add(v);
                }
            }
        }

        int[] res = new int[r * c];
        Arrays.fill(res, 1);
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < r * c; i++) {
            if (uf.find(i) == i && inDegrees[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (Integer v : adjs[u]) {
                res[v] = Math.max(res[v], res[u] + 1);
                if (--inDegrees[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        int[][] ans = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                ans[i][j] = res[uf.find(i * c + j)];
            }
        }

        return ans;
    }

    class Uf {
        private int[] parent;

        public Uf(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] == x) {
                return x;
            }
            return parent[x] = find(parent[x]);
        }

        public void union(int x, int y) {
            int rx = find(x), ry = find(y);
            if (rx == ry) {
                return;
            }
            parent[ry] = rx;
        }
    }
}
```
# [LeetCode_1669_合并两个链表](https://leetcode.cn/problems/merge-in-between-linked-lists/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode head = null, tail = null, pre = null, node = list1;
        int i = 0;
        while (node != null) {
            if (i == a) {
                head = pre;
            }

            if (i == b) {
                tail = node.next;
                break;
            }

            pre = node;
            node = node.next;
            i++;
        }

        if (head == null) {
            return null;
        }

        pre.next = null;
        head.next = list2;
        node = list2;
        pre = null;

        while (node != null) {
            pre = node;
            node = node.next;
        }

        if (pre == null) {
            return null;
        }

        pre.next = tail;

        return list1;
    }
}
```
# [LeetCode_2540_最小公共值](https://leetcode.cn/problems/minimum-common-value/)
## 解法
### 思路
双指针
### 代码
```java
class Solution {
    public int getCommon(int[] nums1, int[] nums2) {
        int i1 = 0, i2 = 0, n1 = nums1.length, n2 = nums2.length;
        while (i1 < n1 && i2 < n2) {
            if (nums1[i1] == nums2[i2]) {
                return nums1[i1];
            } else if (nums1[i1] < nums2[i2]) {
                i1++;
            } else {
                i2++;
            }
        }

        return -1;
    }
}
```
# [LeetCode_2544_交替数字和](https://leetcode.cn/problems/alternating-digit-sum/)
## 解法
### 思路
- 计算得到数字长度
- 根据长度定义数组
- 计算填充数组
- 根据数组获取数值
### 代码
```java
class Solution {
    public int alternateDigitSum(int n) {
        int len = 0, num = n;
        while (num > 0) {
            len++;
            num /= 10;
        }
        
        int[] arr = new int[len];
        int index = 0;
        while (n > 0) {
            arr[index++] = n % 10;
            n /= 10;
        }
        
        boolean flag = true;
        int sum = 0;
        for (int i = len - 1; i >= 0; i--) {
            sum = flag ? sum + arr[i] : sum - arr[i];
            flag = !flag;
        }
        
        return sum;
    }
}
```
# [LeetCode_2549_统计桌面上的不同数字](https://leetcode.cn/problems/count-distinct-numbers-on-board/)
## 解法
### 思路
- n-1一定满足要求，不断循环后，[2,n]一定都会在桌面上
- 1是一个特例，本身就放在桌上，直接返回1
### 代码
```java
class Solution {
    public int distinctIntegers(int n) {
        return n == 1 ? 1 : n - 1;
    }
}
```
# [LeetCode_702_搜索长度未知的有序数组](https://leetcode.cn/problems/search-in-a-sorted-array-of-unknown-size/)
## 解法
### 思路
二分查找
### 代码
```java
class Solution {
    public int search(ArrayReader reader, int target) {
        int head = 0, tail = 9999;

        while (head <= tail) {
            int mid = head + (tail - head) / 2;
            int num = reader.get(mid);
            
            if (num == Integer.MAX_VALUE) {
                tail--;
                continue;
            }
            
            if (num == target) {
                return mid;
            } else if (num > target) {
                tail = mid - 1;
            } else {
                head = mid + 1;
            }
        }
        
        return -1;
    }
}
```
## 解法二
### 思路
在解法1的基础上，尽量再缩小边界
### 代码
```java
class Solution {
    public int search(ArrayReader reader, int target) {
        if (reader.get(0) == target) {
            return 0;
        }

        int head = 1, tail = 1;
        while (reader.get(tail) < target) {
            head = tail;
            tail <<= 1;
        }
        
        if (reader.get(head) == Integer.MAX_VALUE) {
            return -1;
        }

        while (head <= tail) {
            int mid = head + (tail - head) / 2;
            int num = reader.get(mid);

            if (num == Integer.MAX_VALUE) {
                tail = mid - 1;
                continue;
            }
            
            if (num == target) {
                return mid;
            } else if (num < target) {
                head = mid + 1;
            } else {
                tail = mid - 1;
            }
        }

        return -1;
    }
}
```
# [LeetCode_1129_颜色交替的最短路径](https://leetcode.cn/problems/shortest-path-with-alternating-colors/)
## 解法
### 思路
bfs
### 代码
```java
class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        Map<Integer, List<Integer>> redMap = new HashMap<>(), blueMap = new HashMap<>();
        
        int[] ans = new int[n];
        Arrays.fill(ans, Integer.MAX_VALUE);

        for (int[] redEdge : redEdges) {
            redMap.computeIfAbsent(redEdge[0], x -> new ArrayList<>()).add(redEdge[1]);
        }

        for (int[] blueEdge : blueEdges) {
            blueMap.computeIfAbsent(blueEdge[0], x -> new ArrayList<>()).add(blueEdge[1]);
        }

        ans[0] = 0;
        if (redMap.containsKey(0)) {
            boolean[] blueMemo = new boolean[n], redMemo = new boolean[n];
            int path = 0;
            Queue<Integer> queue = new ArrayDeque<>();
            List<Integer> list = redMap.get(0);
            for (Integer edge : list) {
                queue.offer(edge);
            }

            while (!queue.isEmpty()) {
                path++;
                boolean flag = path % 2 == 1;
                int count = queue.size();
                while (count-- > 0) {
                    Integer num = queue.poll();
                    if (num == null) {
                        continue;
                    }

                    if (flag) {
                        if (blueMemo[num]) {
                            continue;
                        } else {
                            blueMemo[num] = true;
                        }
                    } else {
                        if (redMemo[num]) {
                            continue;
                        } else {
                            redMemo[num] = true;
                        }
                    }

                    ans[num] = Math.min(ans[num], path);

                    List<Integer> outDegrees = path % 2 == 1 ? blueMap.getOrDefault(num, new ArrayList<>()) : redMap.getOrDefault(num, new ArrayList<>());
                    for (Integer outDegree : outDegrees) {
                        queue.offer(outDegree);
                    }
                }
            }
        }

        if (blueMap.containsKey(0)) {
            boolean[] blueMemo = new boolean[n], redMemo = new boolean[n];
            int path = 0;
            Queue<Integer> queue = new ArrayDeque<>();
            List<Integer> list = blueMap.get(0);
            for (Integer edge : list) {
                queue.offer(edge);
            }

            while (!queue.isEmpty()) {
                path++;
                boolean flag = path % 2 == 1;
                int count = queue.size();
                while (count-- > 0) {
                    Integer num = queue.poll();
                    if (num == null) {
                        continue;
                    }

                    if (flag) {
                        if (redMemo[num]) {
                            continue;
                        } else {
                            redMemo[num] = true;
                        }
                    } else {
                        if (blueMemo[num]) {
                            continue;
                        } else {
                            blueMemo[num] = true;
                        }
                    }

                    ans[num] = Math.min(ans[num], path);

                    List<Integer> outDegrees = path % 2 == 1 ? redMap.getOrDefault(num, new ArrayList<>()) : blueMap.getOrDefault(num, new ArrayList<>());
                    for (Integer outDegree : outDegrees) {
                        queue.offer(outDegree);
                    }
                }
            }
        }

        for (int i = 0; i < ans.length; i++) {
            if (ans[i] == Integer.MAX_VALUE) {
                ans[i] = -1;
            }
        }

        return ans;
    }
}
```
# [LeetCode_1145_二叉树着色游戏](https://leetcode.cn/problems/binary-tree-coloring-game/)
## 解法
### 思路
- 选手1选择的x点，会将整棵树拆分成3个部分
  - left
  - right
  - n - (left + right) - 1
- 当某一部分大于n的一半时候，就说明选手2能够获胜
- 计算节点数，使用dfs
### 代码
```java
class Solution {
    private int left = 0, right = 0;
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        return count(root, x) / 2 < Math.max(Math.max(left, right), n - (left + right) - 1);
    }

    private int count(TreeNode node, int num) {
        if (node == null) {
            return 0;
        }

        int left = count(node.left, num), right = count(node.right, num);
        if (node.val == num) {
            this.left = left;
            this.right = right;
        }

        return left + right + 1;
    }
}
```
# [LeetCode_1798_你能构造出的连续值的最大数目](https://leetcode.cn/problems/maximum-number-of-consecutive-values-you-can-make/)
## 解法
### 思路
- 然后思考：当x不断增大的过程中，如果将增大前的x称为px，那么新的x其实可以拆分成px + coin，在将coins非降序排列后，因为px一定能保证从0到px的每一个数都能够使用coin之前的所有元素构造出来，所以，如果coin小于等于px+1，那么x一定也能被全部构造出来，如果大于px+1，那么至少px+1这个数就构造不出来了
- 所以解题就变得很简单
- x初始化为0，因为其不需要元素就能构造
- 将coins非降序排列
- 遍历coins，判断当前coins元素coin是否满足：coin <= x + 1，如果满足，则x更新为：x = x + coin
- 循环结束，返回暂存的x即可
### 代码
```java
class Solution {
    public int getMaximumConsecutive(int[] coins) {
        Arrays.sort(coins);
        int x = 0;
        for (int coin : coins) {
            if (coin <= x + 1) {
                x = coin + x;
            }
        }
        
        return x + 1;
    }
}
```