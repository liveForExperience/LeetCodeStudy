# [LeetCode_978_最长湍流子数组](https://leetcode-cn.com/problems/longest-turbulent-subarray/)
## 解法
### 思路
双指针
- 初始化左右指针`left`和`right`，用来定义一个符合题意的子数组窗口`[left, right]`，初始化两个指针都指向坐标为0的元素
- 定义一个暂存窗口最大值的变量`ans`，初始为1，原因是初始窗口`[0,0]`就是一个窗口长度为1的窗口
- 然后遍历真个数组，根据如下情况来更新窗口：
    - 在两个指针相等的情况下，类似起始状态，此时`right`肯定是要右移来扩大窗口大小的，但题目不要求相等的相邻元素存在于数组中，所以还要判断`left`和`left+1`元素是否相等，如果相等，`left`也跟着右移，相当于重新开始判断一个新的窗口
    - 在两个指针不相等的情况下：
        - 如果`right`和`right-1`以及和`right+1`组成符合题目要求的相邻元素组合，那么说明子数组窗口可以扩大，`right`右移，同时更新`ans`
        - 如果不能组成符合题意的元素了，那么说明以`left`为起始的子数组已经不能再扩大了，且之前的长度已经更新到了`ans`中，所以就让`left=right`来重置窗口，继续判断
### 代码
```java
class Solution {
    public int maxTurbulenceSize(int[] arr) {
        int left = 0, right = 0, ans = 1, n = arr.length;
        
        while (right < n - 1) {
            if (left == right) {
                right++;
                if (arr[left] == arr[left + 1]) {
                    left++;
                }
            } else {
                if ((arr[right - 1] < arr[right] && arr[right] > arr[right + 1]) ||
                    (arr[right - 1] > arr[right] && arr[right] < arr[right + 1])) {
                    right++;
                } else {
                    left = right;
                }
            }
            
            ans = Math.max(ans, right - left + 1);
        }
        
        return ans;
    }
}
```
## 解法二
### 思路
动态规划：
- `dp[i][j]`：
    - `i`：对应湍流数组结尾坐标
    - `j`：对应`arr[i] < arr[i - 1]`还是`arr[i - 1] > arr[i]`，分别用0和1标识
- 状态转移方程：
    - 如果`arr[i] > arr[i - 1]`，则`dp[i][0] = dp[i - 1][1] + 1`
    - 如果`arr[i] < arr[i - 1]`，则`dp[i][1] = dp[i - 1][0] + 1`
- base case：
    - `dp[0][0] = 1`
    - `dp[0][1] = 1` 
- 最终结果：遍历dp数组找到最大值
### 代码
```java
class Solution {
    public int maxTurbulenceSize(int[] arr) {
        int len = arr.length;
        int[][] dp = new int[len][2];
        dp[0][0] = dp[0][1] = 1;

        int ans = 1;
        for (int i = 1; i < len; i++) {
            dp[i][0] = dp[i][1] = 1;
            if (arr[i - 1] < arr[i]) {
                dp[i][0] = dp[i - 1][1] + 1;
            } else if (arr[i - 1] > arr[i]) {
                dp[i][1] = dp[i - 1][0] + 1;
            }
            ans = Math.max(ans, dp[i][0]);
            ans = Math.max(ans, dp[i][1]);
        }

        return ans;
    }
}
```
## 解法三
### 思路
动态规划的状态转移只依赖当前因素和前一个因素的状态，不需要使用二维数组
### 代码
```java
class Solution {
    public int maxTurbulenceSize(int[] arr) {
        int len = arr.length, pre1 = 1, pre0 = 1, ans = 1;
        
        for (int i = 1; i < len; i++) {
            int cur0 = 1, cur1 = 1;
            if (arr[i- 1] < arr[i]) {
                cur0 = pre1 + 1;
            } else if (arr[i - 1] > arr[i]) {
                cur1 = pre0 + 1;
            }
            
            pre0 = cur0;
            pre1 = cur1;
            ans = Math.max(ans, cur0);
            ans = Math.max(ans, cur1);
        }
        
        return ans;
    }
}
```
# [LeetCode_446_等差数列划分II子数组](https://leetcode-cn.com/problems/arithmetic-slices-ii-subsequence/)
## 失败解法
### 失败原因
超时
### 思路
- dfs深度遍历，记录当前遍历的深度，也就是数组`A`的坐标位置
- 使用一个list列表记录dfs时选取的元素
- 下钻的路径为：
    - 选择当元素，选择后，在返回的时候需要去除选择的元素，恢复状态
    - 不选择当前元素
- 退出条件，深度与`A`的长度相等，此时判断列表`list`
    - 元素个数是否大于2个
    - 序列是否是等差
- 需要注意溢出的情况，列表存储的元素应为long
### 代码
```java
class Solution {
    private int[] arr;
    private int len, ans;
    public int numberOfArithmeticSlices(int[] A) {
        this.arr = A;
        this.len = A.length;
        dfs(new ArrayList<>(), 0);
        return ans;
    }

    private void dfs(List<Long> list, int depth) {
        if (depth == len) {
            if (list.size() < 3) {
                return;
            }
            
            long diff = list.get(1) - list.get(0);
            for (int i = 2; i < list.size(); i++) {
                if (list.get(i) - list.get(i - 1) != diff) {
                    return;
                }
            }
            ans++;
            return;
        }
        
        dfs(list, depth + 1);
        list.add((long)arr[depth]);
        dfs(list, depth + 1);
        list.remove(list.size() - 1);
    }
}
```
## 解法
### 思路
动态规划：
- `dp[i][d]`：以`A[i]`为结尾的等差为`d`的等差数列
- 因为实际等差数列的元素个数必须大于2，所以当i和j做状态转移的时候，并无法获取到起始的状态，因为2个元素组成的并不是真正的等差数列
- 但是如果将这2个元素组成的序列也暂时当作等差数列，计算结束后，再将2个元素组成的序列从状态总数中删除，就可以了，而删除的值通过排列求职公式得到
- 状态转移方程：`dp[i][d] += dp[j][d] + 1`
    - 累加`dp[j][d]`的原因是，每次在原来的等差数列基础上新增一个有效元素后，以这个元素为底，就会新生成原来等差数列个数个的新数列
    - 这里的加1，代表的就是`i`和`j`2个元素组成的伪等差数列
- base case：dp数组初始化为0
- 结果：在状态转移过程中，累加所有新增加的数组个数，也就是`dp[j][d]`这个值
- dp数组要使用一个数组，元素为一个map，map的key是d值，也就是等差数列的差，value就是数组的个数
- 遍历过程中，外层遍历`A`数组，下标为i，而内层则遍历`[0,i]`这个区间，用来确定`i`和当前内层坐标`j`对应元素的差值是否已有至少2个元素的序列情况
    - 如果没有，则说明j这个元素上没有当前差值的序列，连2个的伪等差数列也没有，此时就在坐标i上的map里生成一个当前差值为key，值为1的entry，用来代表一个伪等差数列，下次如果有新的元素和当前j元素形成相同差值的等差数列，就能累加这个值
    - 如果有，那就累加这个值，说明`j`元素可以和这些以`A[i]`元素为底的等差数列组成新的等差数列
### 代码
```java
class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        int len = A.length, ans = 0;
        HashMap<Integer, Integer>[] dp = new HashMap[len];
        for (int i = 0; i < len; i++) {
            dp[i] = new HashMap<>();
            for (int j = 0; j < i; j++) {
                long delta = (long) A[i] - A[j];
                if (delta < Integer.MIN_VALUE || delta > Integer.MAX_VALUE) {
                    continue;
                }

                int diff = (int)delta;
                int pre = dp[j].getOrDefault(diff, 0), cur = dp[i].getOrDefault(diff, 0);
                dp[i].put(diff, pre + cur + 1);
                ans += pre;
            }
        }

        return ans;
    }
}
```
# [LeetCode_992_k个不同整数的子数组](https://leetcode-cn.com/problems/subarrays-with-k-different-integers/)
## 失败解法
### 原因
超时
### 思路
- 遍历数组，确定以遍历到的坐标i为右边界的子数组
- 然后基于i这个右边界，去寻找左边界
    - 最小满足K的子数组的左边界`index1`
    - 最大满足K的子数组的左边界`index2`
    - 这两个值的差相减+1就是当前以i为右边界的数组个数
- 累加这些值就是结果
### 代码
```java
class Solution {
    public int subarraysWithKDistinct(int[] A, int K) {
        Set<Integer> memo = new HashSet<>();
        int len = A.length, ans = 0;

        for (int i = 0; i < len; i++) {
            int index1 = i;
            while (index1 >= 0) {
                memo.add(A[index1]);
                if (memo.size() == K) {
                    break;
                }
                index1--;
            }
            
            int index2 = index1;
            while (index2 > 0) {
                if (memo.contains(A[index2 - 1])) {
                    index2--;
                } else {
                    break;
                }
            }
            
            if (memo.size() == K) {
                ans += index1 - index2 + 1;
            }
            
            memo.clear();
        }

        return ans;
    }
}
```
## 解法
### 思路
滑动窗口：
- 核心逻辑：
    - `最多K个不同整数的子数组个数 - 最多k-1个不同整数的子数组个数 = K个不同整数的子数组个数`
    - 所以问题就变成了，求不同元素个数不大于K的子数组个数，和不同元素不大于k-1的子数组个数，然后将他们相减就能得出答案
- 定义变量：
    - `ans`：记录数组个数
    - `windowCOunt`：记录当前窗口不同元素的个数
    - `right`：窗口的右边界，驱动窗口移动，带动记录`windowCount`数
    - `left`：窗口的左边界，被动移动，用来确保窗口符合要求
    - `count[]`：数组，用来统计元素在窗口中出现的个数，通过`count[i] == 0`来判断是否出现新的不同元素
- 求最长子数组的过程：
    - 退出条件：`right < len`
    - 在`count[]`中统计出现个数
    - 根据`count[i] == 0`判断当前窗口是否有新的元素出现，有的话就增加`windowCount`
    - 循环判断`windowCount`是否大于题目要求的K，如果是，就开始移动`left`，以确保当前窗口重新恢复到符合题意的状态
    - 移动`left`的过程中，不断累减`count`中的值
    - 同时判断`count[left] == 0`，说明`left`的移动使得窗口的不同元素个数和K相同了，当前窗口重新恢复到符合题意的状态
    - 然后根据`right`和`left`的差+1来确定新生成的数组个数，累加到ans上。这样计算的原因是，新增加的连续数组，起始就是以新进来的元素为右边界的所有可能的连续数组，这个数组个数就是整个窗口的长度
    - 循环结束后，返回count
### 代码
```java
class Solution {
    public int subarraysWithKDistinct(int[] A, int K) {
        return helper(A, K) - helper(A, K - 1);
    }
    
    private int helper(int[] a, int k) {
        int len = a.length, ans = 0, left = 0, right = 0, windowCount = 0;
        int[] count = new int[len + 1];
        
        while (right < len) {
            if (count[a[right]] == 0) {
                windowCount++;
            }
            count[a[right]]++;
            
            while (left <= right && windowCount > k) {
                count[a[left]]--;
                if (count[a[left]] == 0) {
                    windowCount--;
                }
                left++;
            }
            
            ans += right - left + 1;
            right++;
        }
        
        return ans;
    }
}
```
# [LeetCode_567_字符串的排列](https://leetcode-cn.com/problems/permutation-in-string/)
## 解法
### 思路
滑动窗口
- 这题相当于求s2的子数组，且子数组的长度为s1的长度，然后再比较s2的子数组字符个数和s1的字符个数
- 遍历s2模拟窗口移动，每次都更新新增和减去的字符个数，然后与s1的个数进行匹配
- 判断的规则是，比较字符出现的个数是否相等
### 代码
```java
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        if (len1 > len2) {
            return false;
        }

        int[] count1 = new int[26], count2 = new int[26];
        
        for (char c : s1.toCharArray()) {
            count1[c - 'a']++;
        }
        
        for (int i = 0; i < len1; i++) {
            count2[s2.charAt(i) - 'a']++;
        }

        if (fit(count1, count2)) {
            return true;
        }
        
        for (int i = len1 - 1; i < len2 - 1; i++) {
            count2[s2.charAt(i + 1) - 'a']++;
            count2[s2.charAt(i - len1 + 1) - 'a']--;

            if (fit(count1, count2)) {
                return true;
            }
        }
        
        return false;
    }
    
    private boolean fit(int[] c1, int[] c2) {
        for (int i = 0; i < 26; i++) {
            if (c1[i] != c2[i]) {
                return false;
            }
        }
        
        return true;
    }
}
```
# [LeetCode_450_删除二叉搜索树中的节点](https://leetcode-cn.com/problems/delete-node-in-a-bst/)
## 解法
### 思路
- 当去除一个二叉搜索树节点，会需要考虑3种情况：
    - 当前节点是叶子节点：直接删除当前节点
    - 当前节点有右子树：找到当前节点的后驱节点，也就是右子树的最左的叶子节点，将该节点作为当前节点，并同时删除该最左节点
    - 当前节点有左子树：找到当前节点的前驱节点，也就是左子树的最右的叶子节点，将该节点作为当前节点，并同时删除该最右节点
### 代码
```java
class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else {
            if (root.left == null && root.right == null) {
                return null;
            }
            
            if (root.right != null) {
                root.val = processNode(root);
                root.right = deleteNode(root.right, root.val);
            } else {
                root.val = preNode(root);
                root.left = deleteNode(root.left, root.val);
            }
        }

        return root;
    }
    
    private int processNode(TreeNode node) {
        node = node.right;
        while (node.left != null) {
            node = node.left;
        }
        return node.val;
    }
    
    private int preNode(TreeNode node) {
        node = node.left;
        while (node.right != null) {
            node = node.right;
        }
        return node.val;
    }
}
```
# [LeetCode_456_132模式](https://leetcode-cn.com/problems/132-pattern/)
## 失败解法
### 原因
超时
### 思路
暴力
### 代码
```java
class Solution {
    public boolean find132pattern(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] >= nums[j]) {
                    continue;
                }

                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] < nums[k] && nums[k] < nums[j]) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
```
## 解法
### 思路
- 题目的意思，第一个数小于第三个数，第三个数小于第二个数
- 所以第一个数应该尽可能的小，可以通过遍历数组，求[0,i]区间的最小值作为可能的第一个元素并生成数组mins
- 然后第三个数应该在第一个和第二个数之间，那么为了求这第三个数，可以从数组末尾开始遍历，然后将元素存储在栈中。
- 栈中的元素就是可能的第三个数，而能进入栈中的，应该是比第一个数大的最小值，所以栈顶元素应该随遍历过程做更新，如果当前栈顶元素比第一个元素小就弹出，直到第一个大的为止
- 同时整个算法是1层循环，从mins的末尾开始往前遍历，同时因为mins和nums的长度相同，遍历使用的数组下标同时可以作为nums的元素指针
- 遍历过程中：
    - 更新栈顶元素，也就是比较mins[i]的值是否小于stack的栈顶元素，如果不是就把栈顶元素弹出
    - 其次就是判断mins[i]和stack.peek和nums[i]是否形成升序排列，如果是就返回true
- 遍历结束，返回false
### 代码
```java
class Solution {
    public boolean find132pattern(int[] nums) {
        int len = nums.length;
        int[] mins = new int[len];
        mins[0] = nums[0];
        for (int i = 1; i < len; i++) {
            mins[i] = Math.min(nums[i], mins[i - 1]);
        }

        Stack<Integer> stack = new Stack<>();
        for (int i = len - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= mins[i]) {
                stack.pop();
            }

            if (!stack.isEmpty() && mins[i] < stack.peek() && stack.peek() < nums[i]) {
                return true;
            }

            stack.push(nums[i]);
        }

        return false;
    }
}
```
# [LeetCode_457_环形数组循环](https://leetcode-cn.com/problems/circular-array-loop/)
## 解法
### 思路
快慢指针：
- 因为数组中不会出现0，所以用0代表已经搜索过的元素
- 因为索引有负数，所以模拟遍历环形列表的过程就是`((i + nums[i] + len) % len + len) % len`
- 2层循环：
    - 外层循环定义慢指针的起始坐标，如果起始坐标对应的值是0.说明当前坐标值已经在之前搜索过，不能形成环，就跳过。而之所以会有0，是因为，在探索过程中，会将遍历过的元素置为0
    - 内层循环：
        - 首先确定快慢指针，慢指针是外层坐标，快指针是基于索引的下一个坐标
        - 然后循环判断，当前慢指针的值与快指针的值是否是同符号，同时和快指针的下一个坐标的值是否也是同符号，如果不是，就退出循环，说明当前慢指针为起始，无法形成环。
        - 这样判断的原因是，如果要形成环，索引的符号需要先保持一致，否则与语义相悖
        - 然后判断快慢指针是否有相同的情况：
            - 如果有，则再判断，慢指针与快指针的下一个坐标是否也一样，如果还一样，说明当前环的长度为1，不符合要求，退出循环，找下一个起始的慢坐标。否则就说明找到环了，返回true结束
            - 如果没有，就更新快慢指针
        - 如果内层的循环结束了，说明当前起始的慢指针不能找到环，则将当前循环探索过的元素置为0
    - 循环结束，则返回false
- 如果是空数组，就返回false
### 代码
```java
class Solution {
    public boolean circularArrayLoop(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return false;
        }

        for (int i = 0; i < len; i++) {
            if (nums[i] == 0) {
                continue;
            }

            int slow = i, fast = next(nums, slow, len), val = nums[slow];
            while (val * nums[fast] > 0 && val * nums[next(nums, fast, len)] > 0) {
                if (slow == fast) {
                    if (slow == next(nums, slow, len)) {
                        break;
                    }

                    return true;
                }
                
                slow = next(nums, slow, len);
                fast = next(nums, next(nums, fast, len), len);
            }
            
            slow = i;
            while (val * nums[slow] > 0) {
                nums[slow] = 0;
                slow = next(nums, slow, len);
            }
        }
        
        return false;
    }

    private int next(int[] nums, int index, int len) {
        return ((index + nums[index]) % len + len) % len;
    }
}
```
# LeetCode_765_情侣牵手
## 解法
### 思路
并查集：
- 将情侣2人看作一个节点，当n个情侣之间位置交错，那么只要交换n-1次就可以将他们换成符合题意的组合
- 而这个题目就可以看成找到图中的连通分量，然后算出连通分量的节点个数，然后-1就是要换的次数
- 使用并查集来求连通分量
- 遍历数组，求出相邻2个元素对应的节点值，节点值就是当前int值/2的商，这两个值响铃就说明两个值在一个联通分量里，将他们union在一起
- 然后遍历并查集，求出连通分量个数和节点数，最后得到次数
### 代码
```java
class Solution {
    public int minSwapsCouples(int[] row) {
        int len = row.length, total = len / 2;
        Uf uf = new Uf(total);
        
        for (int i = 0; i < len; i += 2) {
            int x = row[i] / 2, y = row[i + 1] / 2;
            uf.union(x, y);
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < total; i++) {
            int r = uf.find(i);
            map.put(r, map.getOrDefault(r, 0) + 1);
        }
        
        int ans = 0;
        for (Integer value : map.values()) {
            ans += (value - 1);
        }
        return ans;
    }

    private static class Uf {
        private int[] parent;
        private int[] rank;

        public Uf(int n) {
            parent = new int[n];
            rank = new int[n];
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
        
        public void union(int x, int y) {
            int rx = find(x), ry = find(y);
            
            if (rx == ry) {
                return;
            }
            
            if (rank[rx] < rank[ry]) {
                parent[rx] = ry;
            } else if (rank[rx] > rank[ry]) {
                parent[ry] = rx;
            } else {
                parent[ry] = rx;
                rank[rx]++;
            }
        }
    }
}
```
## 解法二
### 思路
bfs，求联通分量
### 代码
```java
class Solution {
    public int minSwapsCouples(int[] row) {
        int len = row.length, total = len / 2;
        List<Integer>[] graph = new ArrayList[total];
        for (int i = 0; i < total; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < len; i += 2) {
            int l = row[i] / 2, r = row[i + 1] / 2;
            graph[l].add(r);
            graph[r].add(l);
        }
        int ans = 0;
        boolean[] memo = new boolean[total];
        for (int i = 0; i < total; i++) {
            if (memo[i]) {
                continue;
            }
            int count = 0;
            Queue<Integer> queue = new ArrayDeque<>();
            memo[i] = true;
            queue.offer(i);
            while (!queue.isEmpty()) {
                Integer num = queue.poll();
                count++;
                for (Integer g : graph[num]) {
                    if (!memo[g]) {
                        memo[g] = true;
                        queue.offer(g);
                    }
                }
            }

            ans += count - 1;
        }
        
        return ans;
    }
}
```