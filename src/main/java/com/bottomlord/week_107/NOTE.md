# [LeetCode_1713_得到子序列的最少操作次数](https://leetcode-cn.com/problems/minimum-operations-to-make-a-subsequence/)
## 解法
### 思路
- 如果target数组的长度n，arr数组的长度是m，那么m减去他们的最长公共子序列，就是最少的操作次数
- 然后将arr中的元素对应到target数组的坐标上，并将不在target数组中的坐标去除
- 同时将target数组也转换成坐标数组，同时会发现新生成的数组是单调递增的，此时就可以最长递增子序列长度
- 在确定最长递增子序列的时候，参考[题解](https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/yi-ti-shuang-jie-tu-jie-yuan-li-ji-chu-d-ptpz/)
    - 在确定序列的时候，如果当前元素比候选序列中的元素都大，那么就直接增加候选序列长度
    - 如果比序列最大值小，就找到序列中比当前值大的最小值，做替换，以确保后续元素在判断时保留更大的增长可能性
### 代码
```java
class Solution {
public int minOperations(int[] target, int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < target.length; i++) {
            map.put(target[i], i);
        }

        List<Integer> ans = new ArrayList<>();
        for (int num : arr) {
            if (map.containsKey(num)) {
                int index = map.get(num);
                int i = binarySearch(ans, index);
                if (i == ans.size()) {
                    ans.add(index);
                } else {
                    ans.set(i, index);
                }
            }
        }

        return target.length - ans.size();
    }

    private int binarySearch(List<Integer> ans, int num) {
        int size = ans.size();
        if (size == 0 || num > ans.get(size - 1)) {
            return size;
        }

        int head = 0, tail = size - 1;
        while (head < tail) {
            int mid = head + (tail - head) / 2;
            if (ans.get(mid) < num) {
                head = mid + 1;
            } else {
                tail = mid;
            }
        }

        return head;
    }
}
```
# [LeetCode_671_二叉树中的二小的节点](https://leetcode-cn.com/problems/second-minimum-node-in-a-binary-tree/)
## 错误解法
### 原因
测试用例中包含最大值的情况
### 思路
dfs，遇到根节点值，则转换为最大值返回
### 代码
```java
class Solution {
    public int findSecondMinimumValue(TreeNode root) {
        int val = dfs(root, root.val);
        return val == Integer.MAX_VALUE ? -1 : val;
    }
    
    private int dfs(TreeNode node, int min) {
        if (node == null) {
            return Integer.MAX_VALUE;
        }
        
        if (node.left == null && node.right == null) {
            return node.val == min ? Integer.MAX_VALUE : node.val;
        }
        
        return Math.min(dfs(node.left, min), dfs(node.right, min));
    }
}
```
## 解法
### 思路
- 因为二叉树树的根节点是该树所有节点的最小值：
  - 根节点的左右子树中和根节点值不一样的那个节点，对应的子树里的最小值就是该节点，这个节点是又可能为最小值的，但不能确定那个节点一定是，因为可能与根节点值一样的那个节点的子树里，存在比另一个子树中最小值更小的情况
  - 所以需要去搜索和根节点值一样的节点对应的子树
  - 搜索的时候，就一直去搜索去当前节点值一样的那个节点对应的子树，因为另一个节点已经确保了是子树的最小值，可以在搜索返回的时候进行比较判断
  - 当搜索到节点为叶子节点的时候，说明这个节点就是和根节点一样的元素，直接就返回-1
  - 返回之后，就依据-1来判断：
    - 如果左右子树都不是-1，这种情况出现在，与根节点值相同的节点对应子树中找到了第二小的值，然后第二小值与另一边的最小值做比较
    - 如果左子树或者右子树是-1，代表找到了整棵树的最小值，然后就直接返回另一个值，这个值相当于当前找到的第二小值
### 代码
```java
class Solution {
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) {
            return -1;
        }
        
        if (root.left == null && root.right == null) {
            return -1;
        }
        
        int val = root.val, left = root.left.val, right = root.right.val;
        
        if (left == val) {
            left = findSecondMinimumValue(root.left);   
        }
        
        if (right == val) {
            right = findSecondMinimumValue(root.right);
        }
        
        if (left != -1 && right != -1) {
            return Math.min(left, right);
        }
        
        if (left == -1) {
            return right;
        }
        
        return left;
    }
}
```
# [LeetCode_1408_数组中的字符串匹配](https://leetcode-cn.com/problems/string-matching-in-an-array/)
## 解法
### 思路
- 2层循环，外层遍历可能长的字符串，内层遍历可能包含的字符串
- set记录内层被暂存的字符串坐标，下次再遇到直接跳过
- list记录答案要求的字符串内容
- 内层逻辑就是判断外层字符串是否包含内层字符串，如果是就更新set和list
### 代码
```java
class Solution {
    public List<String> stringMatching(String[] words) {
        Set<Integer> memo = new HashSet<>();
        List<String> list = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (i == j || memo.contains(j)) {
                    continue;
                }
                
                if (words[i].contains(words[j])) {
                    memo.add(j);
                    list.add(words[j]);
                }
            }
        }

        return list;
    }
}
```
## 解法二
### 思路
- 用数组替代解法一中的set
- 内层判断的时候，加入一个减枝条件，如果外层长度不长于内层，就跳过
### 代码
```java
class Solution {
  public List<String> stringMatching(String[] words) {
    boolean[] memo = new boolean[words.length];
    List<String> list = new ArrayList<>();

    for (int i = 0; i < words.length; i++) {
      for (int j = 0; j < words.length; j++) {
        if (memo[j] || i == j || words[i].length() <= words[j].length()) {
          continue;
        }

        if (words[i].contains(words[j])) {
          memo[j] = true;
          list.add(words[j]);
        }
      }
    }

    return list;
  }
}
```
## 解法三
### 思路
将内层判断的逻辑放在单独的函数中，增加运行效率
### 代码
```java
class Solution {
  public List<String> stringMatching(String[] words) {
    List<String> list = new ArrayList<>();
    for (String word : words) {
      if (judge(word, words)) {
        list.add(word);
      }
    }

    return list;
  }

  private boolean judge(String target, String[] words) {
    for (String str : words) {
      if (str.length() > target.length() && str.contains(target)) {
        return true;
      }
    }
    return false;
  }
}
```
# [LeetCode_863_二叉树中所有距离为K的结点](https://leetcode-cn.com/problems/all-nodes-distance-k-in-binary-tree/)
## 解法
### 思路
- 节点间距离为K有多种情况：
  - 距离为K的结点在target的子树中
  - 距离为K的节点在祖先节点中，而这种情况又分为：
    - 在祖先节点中
    - 在祖先节点的某一侧子树中
- 所以搜索过程要分成2个步骤：
  1. 搜索找到target节点
  2. 基于target节点找到其子树中符合距离要求的节点
  3. 配合函数返回值，区分当前节点的左右子树，搜索后的状态：
    - 如果找到了target节点，在进行第2步找到其子树中的节点后，返回0，代表当前这个路径找到了target
    - 如果搜索到最后，找到的节点为null，代表没有找到target
    - 然后在上层节点获取到搜索的返回值后，加一个1，这个1代表了当前节点的距离值
    - 再做一个判断，就是如果左右子树返回值+1后，得到的值大于1了，那么就说明其子树中有target，那么就在另一侧的子树中进行寻找，同时带上返回值去找，从而判断距离是否符合要求
    - 或者，如果左右子树的返回值+1直接就等于K了，那么就代表当前节点就是符合题目要求并在target祖先节点中的那个节点
    - 在整个搜索过程中，所有找到的节点就直接放入list中暂存，这样搜索结束后，直接返回list即可
### 代码
```java
class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> ans = new ArrayList<>();
        if (k == 0) {
            ans.add(target.val);
        } else {
            dfs(root, target, ans, k);
        }
        return ans;
    }

    private int dfs(TreeNode node, TreeNode target, List<Integer> ans, int k) {
        if (node == null) {
            return -1;
        }

        if (node == target) {
            dfs2(node.left, 0, ans, k);
            dfs2(node.right, 0, ans, k);
            return 0;
        }

        int left = dfs(node.left, target, ans, k) + 1,
            right = dfs(node.right, target, ans, k) + 1;

        if (left > 0) {
            if (left == k) {
                ans.add(node.val);
            }
            dfs2(node.right, left, ans, k);
            return left;
        } else if (right > 0) {
            if (right == k) {
                ans.add(node.val);
            }
            dfs2(node.left, right, ans, k);
            return right;
        } else {
            return -1;
        }
    }

    private void dfs2(TreeNode node, int distance, List<Integer> ans, int k) {
        if (node == null || distance + 1 > k) {
            return;
        }

        if (distance + 1 == k) {
            ans.add(node.val);
        }

        dfs2(node.left, distance + 1, ans, k);
        dfs2(node.right, distance + 1, ans, k);
    }
}
```
# [LeetCode_1104_二叉树寻路](https://leetcode-cn.com/problems/path-in-zigzag-labelled-binary-tree/)
## 解法
### 思路
- bfs生成二叉树
- dfs找节点生成路径
### 代码
```java
class Solution {
    private List<Integer> ans = new ArrayList<>();

    public List<Integer> pathInZigZagTree(int label) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        TreeNode root = new TreeNode(1);
        queue.offer(root);

        boolean right = true;
        while (!queue.isEmpty()) {
            int curSize = queue.size();
            int nextSize = curSize * 2;
            Integer index = null;
            while (curSize > 0) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }

                int val = node.val;
                if (index == null) {
                    index = right ? val + curSize + nextSize - 1 : val + 1;
                }

                if (right) {
                    node.left = new TreeNode(index--);
                    node.right = new TreeNode(index--);

                    queue.offer(node.left);
                    queue.offer(node.right);
                } else {
                    node.left = new TreeNode(index++);
                    node.right = new TreeNode(index++);

                    queue.offer(node.left);
                    queue.offer(node.right);
                }

                curSize--;
            }

            index = right ? ++index : --index;
            if (index >= label) {
                break;
            }

            right = !right;
        }

        dfs(root, label, new LinkedList<>());
        return ans;
    }

    private boolean dfs(TreeNode node, int target, LinkedList<Integer> list) {
        if (node == null) {
            return false;
        }

        if (node.val == target) {
            list.add(node.val);
            ans = list;
            return true;
        }

        list.addLast(node.val);
        boolean result = dfs(node.left, target, list);
        if (result) {
            return true;
        }
        list.removeLast();

        list.addLast(node.val);
        result = dfs(node.right, target, list);
        if (result) {
            return true;
        }
        list.removeLast();

        return false;
    }

    class TreeNode {
        private int val;
        private TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
```
## 解法二
### 思路
- 如果二叉树都是从左到右进行标记，那么二叉树满足的特性：
  - 根节点位于第一行
  - 第i行有2的i次方-1个节点，最左边的节点是2的i-1次方，最后右边的节点是2的i次方-1
  - 对于标记为val的节点，其左子节点的标记是2*val，右子节点的标记是2*val + 1，当val大于1的时候，其父节点是val / 2
- 当要求变成偶数行从右向左标记的时候，可以将其转换为如上从左到右的顺序进行标记
- 先找到label节点所在的行和该节点从左到右数标记时候的编号
  - 找到行，也就是label大于等于2的i-1次方，小于2的i次方，i就是行数
  - 从左到右的标记的编号则依据i的奇偶性来判断
    - 当i是奇数的时候，第i行从左到右顺序标记，所以其标记就是label
    - 当i是偶数的时候，第i行从右到左顺序标记，反转前后的同一个节点的和是sum(`2的i-1次方`+`2的i次方-1`-1)，所以反转后的实际值就是sum - label
### 代码
```java
class Solution {
    public List<Integer> pathInZigZagTree(int label) {
        int row = 1, rowStart = 1;
        while (rowStart <= label) {
            rowStart <<= 1;
            row++;
        }

        row--;
        LinkedList<Integer> ans = new LinkedList<>();
        while (row >= 1) {
            boolean even = row % 2 == 0;
            ans.addFirst(label);
            if (row == 1) {
                break;
            }

            int sum = (int) (Math.pow(2, row - 1) + Math.pow(2, row) - 1);
            if (even) {
                label = (sum - label) / 2;
            } else {
                int pre = label / 2;
                label = (int) (Math.pow(2, row - 2) + Math.pow(2, row - 1) - 1) - pre;
            }

            row--;
        }

        return ans;
    }
}
```
# [LeetCode_171_Excel表列序号](https://leetcode-cn.com/problems/excel-sheet-column-number/)
## 解法
### 思路
26进制进位
### 代码
```java
class Solution {
    public int titleToNumber(String columnTitle) {
        int sum = 0;
        char[] cs = columnTitle.toCharArray();
        for (char c : cs) {
            sum = sum * 26 + (c - 'A' + 1);
        }
        return sum;
    }
}
```
# [LeetCode_1413_逐步求和得到正数的最小值](https://leetcode-cn.com/problems/minimum-value-to-get-positive-step-by-step-sum/)
## 解法
### 思路
求前缀和最小值，返回(1-最小值)和1之间的最大值
### 代码
```java
class Solution {
  public int minStartValue(int[] nums) {
    int sum = 0, min = Integer.MAX_VALUE;
    for (int num : nums) {
      sum += num;
      min = Math.min(sum, min);
    }

    return Math.max(1 - min, 1);
  }
}
```
# [LeetCode_987_二叉树的垂序遍历](https://leetcode-cn.com/problems/vertical-order-traversal-of-a-binary-tree/)
## 解法
### 思路
treemap+优先级队列+dfs
### 代码
```java
class Solution {
  public List<List<Integer>> verticalTraversal(TreeNode root) {
    TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
    dfs(root, 0, 0, map);
    List<List<Integer>> ans = new ArrayList<>();
    for (TreeMap<Integer, PriorityQueue<Integer>> rowMap : map.values()) {
      List<Integer> subList= new ArrayList<>();
      for (PriorityQueue<Integer> queue : rowMap.values()) {
        while (!queue.isEmpty()) {
          subList.add(queue.poll());
        }
      }
      ans.add(subList);
    }
    return ans;
  }

  private void dfs(TreeNode node, int row, int col, TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map) {
    if (node == null) {
      return;
    }

    TreeMap<Integer, PriorityQueue<Integer>> rowMap = map.getOrDefault(col, new TreeMap<>());
    PriorityQueue<Integer> rowSet = rowMap.getOrDefault(row, new PriorityQueue<>());
    rowSet.add(node.val);
    rowMap.put(row, rowSet);
    map.put(col, rowMap);

    dfs(node.left, row + 1, col - 1, map);
    dfs(node.right, row + 1, col + 1, map);
  }
}
```
# [LeetCode_1337_矩阵中战斗力最弱的K行](https://leetcode-cn.com/problems/the-k-weakest-rows-in-a-matrix/)
## 解法
### 思路
- 初始化二维数组arr用于记录每行1的个数和对应的坐标
- 遍历原始数组，计算1的个数，并记录到arr中
- 对arr进行排序
- 返回arr中前K个元素
### 代码
```java
class Solution {
    public int[] kWeakestRows(int[][] mat, int k) {
        int len = mat.length;
        int[][] bucket = new int[len][2];
        for (int i = 0; i < len; i++) {
            int[] arr = mat[i];
            if (arr[arr.length - 1] == 1) {
                bucket[i][0] = arr.length;
            } else if (arr[0] == 0) {
                bucket[i][0] = 0;
            } else {
                int count = 0;
                for (int num : arr) {
                    if (num == 0) {
                        break;
                    }
                    count++;
                }
                bucket[i][0] = count;
            }
            bucket[i][1] = i;
        }

        Arrays.sort(bucket, (x, y) -> {
            if (x[0] == y[0]) {
                return x[1] - y[1];
            }

            return x[0] - y[0];
        });

        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = bucket[i][1];
        }

        return ans;
    }
}
```
# [LeetCode_1442_分割字符串的最大得分](https://leetcode-cn.com/problems/the-k-weakest-rows-in-a-matrix/)
## 解法
### 思路
- 初始化数组zero，zero[i]表示从0开始到坐标i为止的0的数量
- 初始化数组one，one[i]表示从len - 1开始到坐标i为止的1的数量
- 从0开始遍历数组，计算zero[i - 1] + one[i]的所有总和中的最大值
### 代码
```java
class Solution {
    public int maxScore(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();

        int[] zero = new int[n], one = new int[n];
        int max = Integer.MIN_VALUE;

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (cs[i] == '0') {
                count++;
            }
            zero[i] = count;
        }

        count = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (cs[i] == '1') {
                count++;
            }
            one[i] = count;
        }

        for (int i = 1; i < n; i++) {
            max = Math.max(max, zero[i - 1] + one[i]);
        }

        return max;
    }
}
```
# [LeetCode_457_环形数组是否存在循环](https://leetcode-cn.com/problems/circular-array-loop/)
## 解法
### 思路
- 理解题目
  - 从任意一个元素开始移动，如果在移动过程中所有遇到的元素都是同样符号，而且能够最终回到起始的节点就说明有循环
  - 在遍历过程中，如果遇到符号和起始的节点不同，或者遍历的元素个数超过了数组的长度，就说明循环不存在
- 模拟
  - 循环遍历所有元素节点，作为可能的循环的起始点
  - 每一次判断时候，暂存起始的节点元素，起始节点的符号
  - 移动的时候需要考虑收尾相连的情况，而且方向有正反，所以移动的方程是：`next = ((cur + num) % n + n) % n`
  - 移动的时候需要判断
    - 遍历次数是否超过数组长度，如果是，返回false
    - 当前元素是否与起始元素符号相同，如果是，返回false
    - 如果元素和起始元素相同，返回true
### 代码
```java
class Solution {
    public boolean circularArrayLoop(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (check(nums, i)) {
                return true;
            }
        }

        return false;
    }

    private boolean check(int[] nums, int start) {
        int cur = start, k = 0, n = nums.length;
        boolean flag = nums[cur] > 0;

        while (k < n) {
            int next = ((cur + nums[cur]) % n + n) % n;
            if (flag && nums[next] < 0) {
                return false;
            }

            if (!flag && nums[next] > 0) {
                return false;
            }

            if (next == start) {
                return k > 0;
            }

            cur = next;
            k++;
        }

        return false;
    }
}
```
## 解法二
### 思路
- 从解法一中可以发现，搜索判断的时候，很多路径是被重复检查的
- 所以可以用一个记忆数组记录当前节点是否被访问过，记忆数组中记录是第几轮被搜索过的，这样如果当前节点在记忆数组中有值，而且值代表的轮数和当前轮数相同，那就是有循环了，如果不是，那就说明之前已经处理过直接返回false
- 需要注意：和解法一不同，在判断k的退出条件时，要等k>n时才退出，因为在解法二时，需要通过memo来判断是否出现循环，所以可能需要n+1次遍历来判断，因为可能只在最后2个元素出现了循环
### 代码
```java
class Solution {
    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;
        int[] memo = new int[n + 1];
        for (int i = 0, idx = 1; i < n; i++, idx++) {
            if (memo[i] > 0) {
                continue;
            }

            int k = 0, cur = i;
            boolean flag = nums[cur] > 0;
            while (k <= n) {
                int next = ((cur + nums[cur]) %  n + n) % n;
                if (cur == next) {
                    break;
                }

                if (flag && nums[next] < 0) {
                    break;
                }

                if (!flag && nums[next] > 0) {
                    break;
                }

                if (memo[next] > 0) {
                    if (memo[next] != idx) {
                        break;
                    }

                    return true;
                }

                k++;
                cur = next;
                memo[next] = idx;
            }
        }

        return false;
    }
}
```