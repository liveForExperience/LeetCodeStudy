# [LeetCode_888_公平的糖果棒交换](https://leetcode-cn.com/problems/fair-candy-swap/)
## 解法
### 思路
hash表+迭代
### 代码
```java
class Solution {
    public int[] fairCandySwap(int[] A, int[] B) {
        int a = Arrays.stream(A).sum(), b = Arrays.stream(B).sum(), diff = a - b;
        Set<Integer> set = new HashSet<>();
        for (int num : A) {
            set.add(num);
        }

        for (int num : B) {
            if (set.contains(diff / 2 + num)) {
                return new int[]{diff / 2 + num, num};
            }
        }

        throw new RuntimeException();
    }
}
```
# [LeetCode_1579_保证图可完全遍历](https://leetcode-cn.com/problems/remove-max-number-of-edges-to-keep-graph-fully-traversable/)
## 解法
### 思路
并查集：
- 定义两个并查集，分别代表Alice和Bob的无向图，并通过计算确定他们的联通分量
- 然后遍历公共边，确定哪些公共边是不需要的，也就说要连接的两个顶点已经在一个连通分量中，就累加起来
- 然后分别遍历Alice和Bob的边，同样确定哪些边是不需要的，累加
- 最后判断两个并查集是否有大于1个的连通分量，如果有就返回-1，否则就返回累加值
### 代码
```java
class Solution {
        public int maxNumEdgesToRemove(int n, int[][] edges) {
        UF ufa = new UF(n), ufb = new UF(n);
        for (int[] edge : edges) {
            edge[1]--;
            edge[2]--;
        }
        
        int ans = 0;
        
        for (int[] edge : edges) {
            if (edge[0] == 3) {
                if (!ufa.union(edge[1], edge[2])) {
                    ans++;
                } else {
                    ufb.union(edge[1], edge[2]);
                }
            }
        }
        
        for (int[] edge : edges) {
            if (edge[0] == 1) {
                if (!ufa.union(edge[1], edge[2])) {
                    ans++;
                }
            } else if (edge[0] == 2) {
                if (!ufb.union(edge[1], edge[2])) {
                    ans++;
                }
            }
        }
        
        if (ufa.count != 1 || ufb.count != 1) {
            return -1;
        }
        
        return ans;
    }
    
    static class UF {
        private int[] parent;
        private int[] rank;
        private int count;
        
        public UF(int n) {
            this.parent = new int[n];
            this.rank = new int[n];
            this.count = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }
        
        public int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
        
        public boolean union(int x, int y) {
            int rx = find(x), ry = find(y);
            if (rx == ry) {
                return false;
            }
            
            if (rank[rx] < rank[ry]) {
                parent[rx] = parent[ry];
            } else if (rank[rx] > rank[ry]) {
                parent[ry] = parent[rx];
            } else {
                rank[rx]++;
                parent[ry] = parent[rx];
            }
            
            count--;
            return true;
        }
    }
}
```
# [LeetCode_439_三元表达式解析器](https://leetcode-cn.com/problems/ternary-expression-parser/)
## 解法
### 思路
分治
- 三元表达式可以根据`?`和`:`拆分成3部分
    - 第一部分是布尔值，位于`?`之前
    - 第二部分是为true时的返回值，位于`?`和`:`之间
    - 第三部分时为false时的返回值，位于`:`之后
- 通过遍历字符串，找到第一个`?`，标记，接着当遍历到第一个`:`的时候，意味着三部分的元素都找到了，这个时候，需要进行分治递归
- 根据第一部分的结果，确定返回第二部分还是第三部分，同时以递归的方式，是为了防止嵌套的表达式的出现，从而能更好的解析
### 代码
```java
class Solution {
    public String parseTernary(String expression) {
        if (expression == null || expression.length() == 0) {
            return expression;
        }

        int len = expression.length(), count = 0;
        for (int i = 1; i < len; i++) {
            if (expression.charAt(i) == '?') {
                count++;
            }

            if (expression.charAt(i) == ':') {
                count--;
            }

            if (count == 0) {
                return expression.charAt(0) == 'T' ? parseTernary(expression.substring(2, i)) : parseTernary(expression.substring(i + 1, len));
            }
        }

        return expression;
    }
}
```
## 解法
### 思路
栈：
- 从右向左遍历字符串，步长是2，从而先行处理嵌套的表达式且跳过`?`和`:`的判断
- 通过`:`来判断表达式是否使用栈来暂存`:`之后的false对应的值
- 通过`?`来判断是否计算表达式结果，也就是根据第一部分的布尔值判断时取`:`之前还是之后的值，并直接覆盖在第一部分上，方便继续遍历表达式计算
### 代码
```java
class Solution {
    public String parseTernary(String expression) {
        int len = expression.length();
        char[] cs = expression.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = len - 1; i >= 2; i-=2) {
            if (cs[i - 1] == ':') {
                stack.push(cs[i]);
            } else {
                if (cs[i - 2] == 'T') {
                    cs[i - 2] = cs[i];
                } else {
                    cs[i - 2] = stack.peek();
                }
                stack.pop();
            }
        }
        return "" + cs[0];
    }
}
```