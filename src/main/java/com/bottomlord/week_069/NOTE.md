# LeetCode_321_拼接最大数
## 题目
给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。现在从这两个数组中选出 k (k <= m + n) 个数字拼接成一个新的数，要求从同一个数组中取出的数字保持其在原数组中的相对顺序。

求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。

说明: 请尽可能地优化你算法的时间和空间复杂度。

示例 1:
```
输入:
nums1 = [3, 4, 6, 5]
nums2 = [9, 1, 2, 5, 8, 3]
k = 5
输出:
[9, 8, 6, 5, 3]
```
示例 2:
```
输入:
nums1 = [6, 7]
nums2 = [6, 0, 4]
k = 5
输出:
[6, 7, 6, 0, 4]
```
示例 3:
```
输入:
nums1 = [3, 9]
nums2 = [8, 9]
k = 3
输出:
[9, 8, 9]
```
## 解法
### 思路
- 确定两个数组的取值大小：从nums1的最小取值范围`max(k - nums2.len, 0)`到最大取值范围`min(nums1.len, k)`：
    - 如果k比nums2大，那么nums1至少取k - nums2的长度
    - 如果k比nums1小，那么nums1最多取k的长度
- 确定每组数组长度情况下的最大子序列：
    - 遍历数组
    - 如果当前数字比之前大，且还可以有舍去的数字个数，那就将前一个数组舍弃
    - 如果当前使用的数字个数超过了限定的长度，就不再增加数字，且累加已舍弃个数
- 获得两个当前长度组合下的最大子序列后，将两个子序列合并，合并过程中就是将最大值挨个拼接
- 在合并过程中，如果发现两个数字相等，需要比对它们剩余的数字组成的字符串谁更大，使用更大的那个字符串的第一个数字，否则，如果使用小的那个，那么接下来的值就可能取不到最大值了，比如
    - `02`和`05`，如果取前一个0，那么之后就只能在2和0之间取了，而其实最大值应该是先取第2个0，然后就在0和5之间取
- 最后将合并的数组和暂存的数组进行比较，并更新
### 代码
```java
class Solution {
        public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int len1 = nums1.length, len2 = nums2.length;
        int[] ans = new int[k];
        if (len1 + len2 < k) {
            return ans;
        }

        for (int i = Math.max(k - len2, 0); i <= Math.min(len1, k); i++) {
            List<Integer> l1 = getLargestNum(nums1, i),
                          l2 = getLargestNum(nums2, k - i);

            int[] tmp = merge(l1, l2);

            if (isLarger(tmp, ans)) {
                ans = tmp;
            }
        }

        return ans;
    }

    private List<Integer> getLargestNum(int[] nums, int k) {
        List<Integer> ans = new ArrayList<>();
        int removeCount = nums.length - k;
        if (k == 0) {
            return ans;
        }

        for (int i = 0; i < nums.length; i++) {
            while (ans.size() > 0 && nums[i] > ans.get(ans.size() - 1) && removeCount > 0) {
                ans.remove(ans.size() - 1);
                removeCount--;
            }

            if (ans.size() < k) {
                ans.add(nums[i]);
            } else {
                removeCount--;
            }
        }

        return ans;
    }

    private int[] merge(List<Integer> a, List<Integer> b) {
        int[] ans = new int[a.size() + b.size()];
        int ia = 0, ib = 0;
        StringBuilder sba = new StringBuilder(), sbb = new StringBuilder();
        a.forEach(sba::append);
        b.forEach(sbb::append);

        for (int i = 0; i < ans.length; i++) {
            if (ia >= a.size()) {
                ans[i] = b.get(ib++);
            } else if (ib >= b.size()) {
                ans[i] = a.get(ia++);
            } else {
                if (sba.substring(ia).compareTo(sbb.substring(ib)) >= 0) {
                    ans[i] = a.get(ia++);
                } else {
                    ans[i] = b.get(ib++);
                }
            }
        }

        return ans;
    }

    private boolean isLarger(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] > b[i]) {
                return true;
            } else if (a[i] < b[i]) {
                return false;
            }
        }

        return false;
    }
}
```
# LeetCode_323_无向图中连通分量的数量
## 题目
给定编号从 0 到 n-1 的 n 个节点和一个无向边列表（每条边都是一对节点），请编写一个函数来计算无向图中连通分量的数目。

示例 1:
```
输入: n = 5 和 edges = [[0, 1], [1, 2], [3, 4]]

     0          3
     |          |
     1 --- 2    4 

输出: 2
```
示例 2:
```
输入: n = 5 和 edges = [[0, 1], [1, 2], [2, 3], [3, 4]]

     0           4
     |           |
     1 --- 2 --- 3

输出:  1
```
注意:
```
你可以假设在 edges 中不会出现重复的边。而且由于所以的边都是无向边，[0, 1] 与 [1, 0]  相同，所以它们不会同时在 edges 中出现。
```
## 解法
### 思路
dfs：
- 将顶点对组成存储无向图顶点联通性的集合
- 使用记忆化搜索，所有顶点，如果没有搜索过，则开始dfs，并计数1
- 最终范围最外层dfs的次数
### 代码
```java
class Solution {
    public int countComponents(int n, int[][] edges) {
        boolean[] memo = new boolean[n];
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!memo[i]) {
                count++;
                dfs(graph, memo, i);
            }
        }
        
        return count;
    }

    private void dfs(List<List<Integer>> graph, boolean[] memo, int index) {
        if (memo[index]) {
            return;
        }
        
        memo[index] = true;
        
        for (int i = 0; i < graph.get(index).size(); i++) {
            if (!memo[graph.get(index).get(i)]) {
                dfs(graph, memo, graph.get(index).get(i));
            }
        }
    }
}
```
## 解法二
### 思路
并查集
### 代码
```java
class Solution {
    public int countComponents(int n, int[][] edges) {
        UnionFind unionFind = new UnionFind(n);
        for (int[] edge : edges) {
            unionFind.union(edge[0], edge[1]);
        }
        return unionFind.count;
    }
    
    class UnionFind {
        private int[] parent;
        private int count;

        public UnionFind(int n) {
            this.parent = new int[n];
            this.count = n;
            for (int i = 0; i < n; i++) {
                this.parent[i] = i;
            }
        }

        public int find(int n) {
            if (parent[n] != n) {
                parent[n] = find(parent[n]);
            }

            return parent[n];
        }

        public void union(int x, int y) {
            int px = find(x), py = find(y);
            if (px != py) {
                parent[px] = py;
                count--;
            }
        }
    }
}
```
# LeetCode_324_摆动排序II
## 题目
给定一个无序的数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。

示例 1:
```
输入: nums = [1, 5, 1, 1, 6, 4]
输出: 一个可能的答案是 [1, 4, 1, 5, 1, 6]
```
示例 2:
```
输入: nums = [1, 3, 2, 2, 3, 1]
输出: 一个可能的答案是 [2, 3, 1, 3, 1, 2]
说明:
你可以假设所有输入都会得到有效的结果。
```
进阶:
```
你能用 O(n) 时间复杂度和 / 或原地 O(1) 额外空间来实现吗？
```
## 解法
### 思路
快排：
- 复制原数组生成copy
- 排序copy
- 从copy的中间和尾部向前遍历元素，并依次放入nums中
- 如果数组是偶数个，中间位置左
- 从中间和尾部开始遍历的原因：不能相向遍历，否则会导致相同元素被相邻放置在新数组中
- 大数部分和奇数相等或比奇数多一个的原因：当放置元素时，因为是从大到小放置，所以如果小数先用完，那么剩下位置的元素放置的就是大数部分最小的一个，而这个最小的数一定比最后一个小数部分大，因为小数部分是整个数组最小的数
### 代码
```java
class Solution {
    public void wiggleSort(int[] nums) {
        int len = nums.length;
        int[] copy = Arrays.copyOf(nums,len);
        Arrays.sort(copy);
        int mid = len % 2 == 1 ? len / 2 : len / 2 - 1, l = mid, r = nums.length - 1, index = 0;
        while (l >= 0 || r > nums.length / 2) {
            if (l >= 0) {
                nums[index++] = copy[l--];
            }

            if (r > mid) {
                nums[index++] = copy[r--];
            }
        }
    }
}
```
## 解法二
### 思路
快排变种：
- 分治递归找到中位数
    - 传递参数：
        - 数组
        - 需要定位的数组起始位置
        - 需要定位的数组的结尾位置
        - 需要定位的位置
    - 过程：
        - 定义pivot为数组起始元素，假设这个值为中位数，看有多少比它小，多少比它大
        - 定义head指针，用来代表比pivot小的值的最大坐标位置 + 1的位置
        - 定义tail指针，用来达标比pivot大的值的最小坐标位置 - 1的位置
        - head和tail相向进行搜索
        - 如果tail对应的值比pivot大，就直接左移tail，否则就和head对应的值互换，并右移head，代表小值确定了一个
        - 如果head对应的值比pivot小，就直接右移head，否则就和tail对应的值互换，并左移tail，代表大值确定了一个
        - 当两个指针相遇的时候：
            - 先将head指针对应值的位置设置为pivot
            - 如果head指针与中间位置一样大，说明找到了中位数
            - 如果head指针比中间位置小，说明中间位置还在head的右边，继续递归
            - 如果head指针比中间位置大，说明中间位置还在head的左边，继续递归
- 找到中位数后使用3-way-partition将数组按与中位数的大小关系分成左右两部分，且保证与中位数相等的元素与中位数相邻
- 最后复制大小两部分数组，将内容反序依次插入到nums中
### 代码
```java
class Solution {
    public void wiggleSort(int[] nums) {
        int midIndex = quickSelect(nums, 0, nums.length - 1);
        int mid = nums[midIndex];

        int l = 0, r = nums.length - 1, k = 0;
        while (l <= r) {
            if (nums[l] > mid) {
                swap(nums, l, r);
                r--;
            } else if (nums[l] < mid) {
                swap(nums, l, k);
                l++;
                k++;
            } else {
                l++;
            }
        }
        
        midIndex = nums.length % 2 == 1 ? midIndex + 1 : midIndex;
        int[] partA = Arrays.copyOfRange(nums, 0, midIndex),
              partB = Arrays.copyOfRange(nums, midIndex, nums.length);

        for (int i = 0; i < partA.length; i++) {
            nums[2 * i] = partA[partA.length - 1 - i];
        }

        for (int i = 0; i < partB.length; i++) {
            nums[2 * i + 1] = partB[partB.length - 1 - i];
        }
    }

    private int quickSelect(int[] nums, int l, int r) {
        int pivot = nums[l];

        int head = l, tail = r;
        while (head < tail) {
            while (head < tail && nums[tail] >= pivot) {
                tail--;
            }
            nums[head] = nums[tail];

            while (head < tail && nums[head] <= pivot) {
                head++;
            }
            nums[tail] = nums[head];
        }

        nums[head] = pivot;

        if (head == nums.length / 2) {
            return head;
        }

        if (head < nums.length / 2) {
            return quickSelect(nums, head + 1, r);
        } else {
            return quickSelect(nums, l, head - 1);
        }
    }

    private void swap(int[] nums, int x, int y) {
        int tmp = nums[x];
        nums[x] = nums[y];
        nums[y] = tmp;
    }
}
```
# LeetCode_1356_
## 题目
给你一个整数数组 arr 。请你将数组中的元素按照其二进制表示中数字 1 的数目升序排序。

如果存在多个数字二进制中 1 的数目相同，则必须将它们按照数值大小升序排列。

请你返回排序后的数组。

示例 1：
```
输入：arr = [0,1,2,3,4,5,6,7,8]
输出：[0,1,2,4,8,3,5,6,7]
解释：[0] 是唯一一个有 0 个 1 的数。
[1,2,4,8] 都有 1 个 1 。
[3,5,6] 有 2 个 1 。
[7] 有 3 个 1 。
按照 1 的个数排序得到的结果数组为 [0,1,2,4,8,3,5,6,7]
```
示例 2：
```
输入：arr = [1024,512,256,128,64,32,16,8,4,2,1]
输出：[1,2,4,8,16,32,64,128,256,512,1024]
解释：数组中所有整数二进制下都只有 1 个 1 ，所以你需要按照数值大小将它们排序。
```
示例 3：
```
输入：arr = [10000,10000]
输出：[10000,10000]
```
示例 4：
```
输入：arr = [2,3,5,7,11,13,17,19]
输出：[2,3,5,17,7,11,13,19]
```
示例 5：
```
输入：arr = [10,100,1000,10000]
输出：[10,100,10000,1000]
```
提示：
```
1 <= arr.length <= 500
0 <= arr[i] <= 10^4
```
## 解法
### 思路
- 计算每个数字1的个数
- 针对元素下标排序：
    - 先比1的个数
    - 再比元素大小
- 根据下标顺序生成数组并返回
### 代码
```java
class Solution {
    public int[] sortByBits(int[] arr) {
        int[] nums = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nums[i] = count(arr[i]);
        }

        Integer[] order = new Integer[arr.length];
        for (int i = 0; i < order.length; i++) {
            order[i] = i;
        }
        
        Arrays.sort(order, (x, y) -> {
            if (nums[x] == nums[y]) {
                return arr[x] - arr[y];
            }
            
            return nums[x] - nums[y];
        });
        
        int[] ans = new int[nums.length];
        for (int i = 0; i < order.length; i++) {
            ans[i] = arr[order[i]];
        }
        return ans;
    }
    
    private int count(int num) {
        int count = 0;
        while (num > 0) {
            num = num & (num - 1);
            count++;
        }

        return count;
    }
}
```