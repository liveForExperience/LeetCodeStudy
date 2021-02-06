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
# [LeetCode_295_数据流的中位数](https://leetcode-cn.com/problems/find-median-from-data-stream/)
## 解法
### 思路
两个优先级队列
### 代码
```java
class MedianFinder {
        private PriorityQueue<Integer> maxHeap, minHeap;
        private int count;
        public MedianFinder() {
            this.count = 0;
            this.maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
            this.minHeap = new PriorityQueue<>();
        }

        public void addNum(int num) {
            count++;
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());

            if ((count & 1) != 0) {
                maxHeap.offer(minHeap.poll());
            }
        }

        public double findMedian() {
            if (count == 0) {
                return 0;
            }
            
            return (count & 1) != 0 ? (double)maxHeap.peek() : ((double)maxHeap.peek() + (double)minHeap.peek()) / 2;
        }
}
```
# [LeetCode_480_滑动窗口中位数](https://leetcode-cn.com/problems/sliding-window-median/)
## 解法
### 思路
优先级队列+延迟删除
- 使用两个优先级队列，不同的排序器，分别存储窗口的较大一半和较小一般的元素，且保证较小一半的队列总是和较大一半的队列多1个或一样
- 在窗口移动的时候，总是会做增加元素到窗口，和删除元素的动作
    - 新增的时候：
        - 先放入小队列
        - 然后将小队列排序后的堆顶元素放入大队列
        - 判断窗口大小是否是奇数还是偶数，如果是奇数就再将大队列的堆顶元素放回到小队列
    - 删除的时候：
        - 判断堆顶元素是否是要删除的元素
            - 如果是小队列的堆顶元素：
                - 窗口元素是奇数，直接弹出
                - 窗口元素是偶数，弹出堆顶元素后，将大队列元素弹出到小队列
            - 如果是大队列的堆顶元素：
                - 窗口元素是奇数，弹出堆顶元素后，将小队列的堆顶元素弹出到大队列
                - 窗口元素是偶数，直接弹出大队列的堆顶元素
        - 如果不是堆顶元素，就使用一个hash表记录要删除的元素，并统计删除次数
        - 每次删除后，最后还要确认是否可以清理hash表中待删除的元素
### 代码
```java
class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums.length - k + 1 <= 0) {
            return new double[0];
        }
        
        double[] ans = new double[nums.length - k + 1];
        MedianFinder medianFinder = new MedianFinder();
        for (int i = 0; i < k; i++) {
            medianFinder.add(nums[i]);
        }
        
        ans[0] = medianFinder.median();
        
        for (int i = 1; i < ans.length; i++) {
            medianFinder.add(nums[i + k - 1]);
            medianFinder.del(nums[i - 1]);
            ans[i] = medianFinder.median();
        }
        
        return ans;
    }
    
    static class MedianFinder {
        private PriorityQueue<Integer> minHeap, maxHeap;
        private Map<Integer, Integer> freq;
        private int count;
        
        public MedianFinder (){
            minHeap = new PriorityQueue<>(Comparator.reverseOrder());
            maxHeap = new PriorityQueue<>();
            freq = new HashMap<>();
            count = 0;
        }
        
        public void add(int num) {
            count++;
            minHeap.offer(num);
            maxHeap.offer(minHeap.poll());
            if (count % 2 == 1) {
                minHeap.offer(maxHeap.poll());
            }
        }
        
        public void del(int num) {
            if (minHeap.isEmpty() && maxHeap.isEmpty()) {
                return;
            }

            freq.put(num, freq.getOrDefault(num, 0) + 1);

            if (!minHeap.isEmpty() && Objects.equals(num, minHeap.peek())) {
                minHeap.poll();
                if (count % 2 == 0) {
                    minHeap.offer(maxHeap.poll());
                }

                freq.put(num, freq.get(num) - 1);
                if (freq.get(num) == 0) {
                    freq.remove(num);
                }
            } else if (!maxHeap.isEmpty() && Objects.equals(num, maxHeap.peek())) {
                maxHeap.poll();
                if (count % 2 == 1) {
                    maxHeap.offer(minHeap.poll());
                }

                freq.put(num, freq.get(num) - 1);
                if (freq.get(num) == 0) {
                    freq.remove(num);
                }
            } else if (!minHeap.isEmpty() && num < minHeap.peek()) {
                if (count % 2 == 0) {
                    minHeap.offer(maxHeap.poll());
                }
            } else {
                if (count % 2 == 1) {
                    maxHeap.offer(minHeap.poll());
                }
            }

            boolean hasRemove = true;
            Set<Integer> set = new HashSet<>();
            while (hasRemove) {
                hasRemove = false;
                for (Integer key : freq.keySet()) {
                    if (set.contains(key)) {
                        continue;
                    }
                    
                    boolean hasRemoveCurrent = false;
                    if (!minHeap.isEmpty() && Objects.equals(key, minHeap.peek())) {
                        minHeap.poll();
                        hasRemove = true;
                        hasRemoveCurrent = true;
                    } else if (!maxHeap.isEmpty() && Objects.equals(key, maxHeap.peek())) {
                        maxHeap.poll();
                        hasRemove = true;
                        hasRemoveCurrent = true;
                    }

                    if (hasRemoveCurrent) {
                        freq.put(key, freq.get(key) - 1);
                        if (Objects.equals(freq.get(key), 0)) {
                            set.add(key);
                        }
                    }
                }
            }

            for (Integer key : set) {
                freq.remove(key);
            }
            count--;
        }
        
        public double median() {
            if (minHeap.isEmpty() && maxHeap.isEmpty()) {
                return 0;
            }
            
            return count % 2 == 0 ? ((double)minHeap.peek() + (double)maxHeap.peek()) / 2 : (double)minHeap.peek();
        }
    }
}
```
# [LeetCode_440_字典序的第k小数字](https://leetcode-cn.com/problems/k-th-smallest-in-lexicographical-order/)
## 失败解法
### 原因
超出内存限制
### 思路
遍历数字，转字符串，放入优先级队列，长度保持为k个
### 代码
```java
class Solution {
    public int findKthNumber(int n, int k) {
        PriorityQueue<String> queue = new PriorityQueue<>(k);
        for (int i = 1; i <= n; i++) {
            queue.offer("" + i);
        }
        
        for (int i = 0; i < k; i++) {
            if (i == k - 1) {
                return Integer.parseInt(queue.poll());
            }
            queue.poll();
        }
        
        throw new RuntimeException(); 
    }
}
```
## 解法
### 思路
- 数字的字典序排列可以建模成十叉树
- 从小到大的排列可以通过先序遍历的方式实现
- 题目要求找到n个数内的第k小的数，也就是在找到先序遍历过程中的第k个节点
- 先序遍历过程，可以看成2种情况：
    - 第k个元素在当前节点的子树中，这种情况就需要递归下去继续找
    - 第k个元素不在当前节点的子树中，这种情况，就可以直接跳过当前节点，去兄弟节点继续查
- 当知道n的时候，其实并不知道这颗十叉树的深度是多少，所以在确定第k个节点是否在当前节点子树的时候，可以通过计算当前层兄弟节点最小值的方式，确定当前层是否包含n这个值，如果第一次大于n，说明当前层是最大深度
- 获取当前层节点最小值的方式，又可以通过每次在当前父节点值的基础上*10的方式获取
- 同时在找这个最大深度的过程中，下一个节点和当前节点之间当前层的个数差就相当于先序遍历的次数，但有一个特殊情况，是当前层对应的节点数大于n的时候，就不是最小值之间的差，而是n和前一节点最小值之间的差
- 在右移兄弟节点和下钻的选择过程中：
    - 右移就是当前节点值+1
    - 下钻就是当前节点值*10
- 退出就是知道步数=k
### 代码
```java

```
# [LeetCode_1423_可获得的最大点数](https://leetcode-cn.com/problems/maximum-points-you-can-obtain-from-cards/)
## 解法
### 思路
前缀和
### 代码
```java
class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int len = cardPoints.length;
        int[] sums = new int[len + 1];
        sums[1] = cardPoints[0];
        for (int i = 2; i < len + 1; i++) {
            sums[i] = sums[i - 1] + cardPoints[i - 1];
        }

        int max = 0;
        for (int i = 0; i <= k; i++) {
            int left = i, right = len - k + i;
            int cur = sums[left] - sums[0] + sums[len] - sums[right];
            max = Math.max(max, cur);
        }
        return max;
    }
}
```
## 解法二
### 思路
模拟没有被挑中的牌的窗口，然后移动窗口，计算窗口最小值
### 代码
```java
class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int len = cardPoints.length, wLen = len - k, sum = 0;
        for (int cardPoint : cardPoints) {
            sum += cardPoint;
        }
        
        if (wLen == 0) {
            return sum;
        }
        
        int wSum = 0;
        for (int i = 0; i < wLen; i++) {
            wSum += cardPoints[i];
        }
        
        int min = wSum;
        for (int i = 1; i < len - wLen + 1; i++) {
            wSum = wSum - cardPoints[i - 1] + cardPoints[i + wLen - 1];
            min = Math.min(min, wSum);
        }
        return sum - min;
    }
}
```