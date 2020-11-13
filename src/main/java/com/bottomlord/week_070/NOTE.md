# LeetCode_325_和等于k的最长子数组长度
## 题目
给定一个数组 nums 和一个目标值 k，找到和等于 k 的最长子数组长度。如果不存在任意一个符合要求的子数组，则返回 0。

注意:
```
 nums 数组的总和是一定在 32 位有符号整数范围之内的。
```
示例 1:
```
输入: nums = [1, -1, 5, -2, 3], k = 3
输出: 4 
解释: 子数组 [1, -1, 5, -2] 和等于 3，且长度最长。
```
示例 2:
```
输入: nums = [-2, -1, 2, 1], k = 1
输出: 2 
解释: 子数组 [-1, 2] 和等于 1，且长度最长。
```
## 解法
### 思路
前缀和
### 代码
```java
class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        int len = nums.length;
        int[] sums = new int[len];
        
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sums[i] = (sum += nums[i]);
        }
        
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (sums[j] - sums[i] + nums[i] == k) {
                    max = Math.max(max, j - i + 1);
                }
            }
        }

        return max == Integer.MIN_VALUE ? 0 : max;
    }
}
```
## 解法二
### 思路
hash表+前缀和
### 代码
```java
class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        int len = nums.length, sum = 0;
        int[] sums = new int[len];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            sums[i] = (sum += nums[i]);

            List<Integer> list = map.getOrDefault(sum, new ArrayList<>());
            list.add(i);
            map.put(sum, list);
        }

        int max = 0;
        for (int i = 0; i < len; i++) {
            int num = sums[i] + k - nums[i];
            if (map.containsKey(num)) {
                for (int index : map.get(num)) {
                    max = Math.max(max, index - i + 1);
                }
            }
        }
        
        return max;
    }
}
```
## 优化代码
### 思路
在解法二的基础上，因为求的是最大举例，而循环的是起始举例，所以hash的value存储对应前缀和最大的坐标值即可，不用存储list
### 代码
```java
class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        int len = nums.length, sum = 0;
        int[] sums = new int[len];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            sums[i] = (sum += nums[i]);
            map.put(sum, i);
        }

        int max = 0;
        for (int i = 0; i < len; i++) {
            int num = sums[i] + k - nums[i];
            if (map.containsKey(num)) {
                max = Math.max(max, map.get(num) - i + 1);
            }
        }

        return max;
    }
}
```
# LeetCode_330_按要求补齐数组
## 题目
给定一个已排序的正整数数组 nums，和一个正整数 n 。从 [1, n] 区间内选取任意个数字补充到 nums 中，使得 [1, n] 区间内的任何数字都可以用 nums 中某几个数字的和来表示。请输出满足上述要求的最少需要补充的数字个数。

示例 1:
```
输入: nums = [1,3], n = 6
输出: 1 
解释:
根据 nums 里现有的组合 [1], [3], [1,3]，可以得出 1, 3, 4。
现在如果我们将 2 添加到 nums 中， 组合变为: [1], [2], [3], [1,3], [2,3], [1,2,3]。
其和可以表示数字 1, 2, 3, 4, 5, 6，能够覆盖 [1, 6] 区间里所有的数。
所以我们最少需要添加一个数字。
```
示例 2:
```
输入: nums = [1,5,10], n = 20
输出: 2
解释: 我们需要添加 [2, 4]。
```
示例 3:
```
输入: nums = [1,2,2], n = 5
输出: 0
```
## 解法
### 思路
- 假设miss是最小的不能被覆盖的值，这也就代表，`[0,miss)`是完全被覆盖的
- 因为覆盖的范围从1开始，所以假设第一个miss是1
- 然后开始遍历提供的数组，判断数组的值是否能够覆盖miss
- miss是1，那么能够覆盖1的值就是1，如果`nums[0] > 1`，说明需要添加1才能够覆盖
- 然后miss的值也就从1变成了`1 + 1`(miss + miss)
- 继续判断是否覆盖了，假设这次`nums[1] == 2`，覆盖了`miss = 2`，那么下一个miss就是`miss + nums[i]`，也就是这里的`2+2`，因为2是原来的最小的不能覆盖的值，现在添加并能覆盖了，那么比覆盖的值小的这个`nums[i]`与`miss`的和之内的值一定也能覆盖
- 重复按照如上的过程累加miss，直到miss大于预设值n
- 为了放置数组越界，miss需要声明为long类型
### 代码
```java
class Solution {
    public int minPatches(int[] nums, int n) {
        int len = nums.length, index = 0, count = 0;
        long miss = 1;
        while (miss <= n) {
            if (index < len && nums[index] <= miss) {
                miss += nums[index++];
            } else {
                miss += miss;
                count++;
            }
        }
        return count;
    }
}
```
# LeetCode_331_验证二叉树的前序序列化
## 题目
序列化二叉树的一种方法是使用前序遍历。当我们遇到一个非空节点时，我们可以记录下这个节点的值。如果它是一个空节点，我们可以使用一个标记值记录，例如 #。
```
     _9_
    /   \
   3     2
  / \   / \
 4   1  #  6
/ \ / \   / \
# # # #   # #
```
例如，上面的二叉树可以被序列化为字符串 "9,3,4,#,#,1,#,#,2,#,6,#,#"，其中 # 代表一个空节点。

给定一串以逗号分隔的序列，验证它是否是正确的二叉树的前序序列化。编写一个在不重构树的条件下的可行算法。

每个以逗号分隔的字符或为一个整数或为一个表示 null 指针的 '#' 。

你可以认为输入格式总是有效的，例如它永远不会包含两个连续的逗号，比如 "1,,3" 。

示例 1:
```
输入: "9,3,4,#,#,1,#,#,2,#,6,#,#"
输出: true
```
示例 2:
```
输入: "1,#"
输出: false
```
示例 3:
```
输入: "9,#,#,1"
输出: false
```
## 解法
### 思路
- 将二叉树的节点及空节点都视为1个槽位
- 每一个节点都消耗1个槽位
- 非空节点同时可以生成2个槽位
- 遍历所有的节点元素，看槽位是否足够放置节点数，且数量一致，如果不是，就代表不是有效的二叉树
- 因为序列是前序排列，所以可以从序列的第一个节点（根节点）开始统计槽位数，在遍历过程中查看槽位数是否小于0，如果是就代表槽位已经不足，判定不是二叉树
- 在遍历结束后查看槽位数是否为0，如果不为0也不是二叉树
### 代码
```java
class Solution {
    public boolean isValidSerialization(String preorder) {
        String[] elements = preorder.split(",");
        int slots = 1;
        for (String element : elements) {
            slots--;
            
            if (slots < 0) {
                return false;
            }
            
            if (!Objects.equals(element, "#")) {
                slots += 2;
            }
        }
        return slots == 0;
    }
}
```
## 解法二
### 思路
- 不使用额外字符串数组，而是直接遍历字符串
- 需要注意在过程中，当遇到`,`时对槽位数做处理和判断
- 所以当字符串遍历结束后，还需要对字符串的最后一个字符做判断，确定是否是空节点，并对应处理
### 代码
```java
class Solution {
    public boolean isValidSerialization(String preorder) {
        int slots = 1, len = preorder.length();
        for (int i = 0; i < len; i++) {
            if (preorder.charAt(i) == ',') {
                slots--;
                
                if (slots < 0) {
                    return false;
                }
                
                if (preorder.charAt(i - 1) != '#') {
                    slots += 2;
                }
            }
        }

        if (preorder.charAt(len - 1) == ',') {
            return false;
        }
        
        slots = preorder.charAt(len - 1) == '#' ? slots - 1 : slots + 1;
        return slots == 0;
    }
}
```
# LeetCode_514_自由之路
## 题目
视频游戏“辐射4”中，任务“通向自由”要求玩家到达名为“Freedom Trail Ring”的金属表盘，并使用表盘拼写特定关键词才能开门。

给定一个字符串 ring，表示刻在外环上的编码；给定另一个字符串 key，表示需要拼写的关键词。您需要算出能够拼写关键词中所有字符的最少步数。

最初，ring 的第一个字符与12:00方向对齐。您需要顺时针或逆时针旋转 ring 以使 key 的一个字符在 12:00 方向对齐，然后按下中心按钮，以此逐个拼写完 key 中的所有字符。

旋转 ring 拼出 key 字符 key[i] 的阶段中：
```
您可以将 ring 顺时针或逆时针旋转一个位置，计为1步。旋转的最终目的是将字符串 ring 的一个字符与 12:00 方向对齐，并且这个字符必须等于字符 key[i] 。
如果字符 key[i] 已经对齐到12:00方向，您需要按下中心按钮进行拼写，这也将算作 1 步。按完之后，您可以开始拼写 key 的下一个字符（下一阶段）, 直至完成所有拼写。
```
示例：
```
输入: ring = "godding", key = "gd"
输出: 4
解释:
 对于 key 的第一个字符 'g'，已经在正确的位置, 我们只需要1步来拼写这个字符。 
 对于 key 的第二个字符 'd'，我们需要逆时针旋转 ring "godding" 2步使它变成 "ddinggo"。
 当然, 我们还需要1步进行拼写。
 因此最终的输出是 4。
```
提示：
```
ring 和 key 的字符串长度取值范围均为 1 至 100；
两个字符串中都只有小写字符，并且均可能存在重复字符；
字符串 key 一定可以由字符串 ring 旋转拼出。
```
## 解法
### 思路
动态规划：
- `dp[i][j]`：从左往右匹配，当key的第i个字符，通过ring的第j个字符匹配上时一共使用的最小步数
- `base case`：`dp[0][j] = min(k, n - k) + 1`，n代表ring的长度，k代表第一个字符在ring中的坐标
- 状态转移方程：`dp[i][j] = min(dp[i][j], min(dp[i - 1][k] + min(j - k, n - j + k) + 1))`，k是坐标`i - 1`时，所有ring的相同字符坐标
- 返回结果：min(dp[m - 1][j])，与第`m-1`个字符相等的ring字符坐标，在dp中的最小值
- 需要准备下key中每一个字符在ring中的坐标集合，用来加速判断
### 代码
```java
class Solution {
    public int findRotateSteps(String ring, String key) {
        int m = key.length(), n = ring.length();
        int[][] dp = new int[m][n];
        List<Integer>[] pos = new List[26];
        for (int i = 0; i < 26; i++) {
            pos[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < n; i++) {
            pos[ring.charAt(i) - 'a'].add(i);
        }

        for (int[] arr : dp) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }

        for (int i = 0; i < pos[key.charAt(0) - 'a'].size(); i++) {
            int index = pos[key.charAt(0) - 'a'].get(i);
            dp[0][index] = Math.min(index, n - index) + 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j : pos[key.charAt(i) - 'a']) {
                for (int k : pos[key.charAt(i - 1) - 'a']) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + Math.min(Math.abs(j - k), n - Math.abs(j - k)) + 1);
                }
            }
        }

        return Arrays.stream(dp[m - 1]).min().getAsInt();
    }
}
```
# LeetCode_333_最大BST树
## 题目
给定一个二叉树，找到其中最大的二叉搜索树（BST）子树，并返回该子树的大小。其中，最大指的是子树节点数最多的。

二叉搜索树（BST）中的所有节点都具备以下属性：

左子树的值小于其父（根）节点的值。

右子树的值大于其父（根）节点的值。

注意:
```
子树必须包含其所有后代。
```
进阶:
```
你能想出 O(n) 时间复杂度的解法吗？
```
示例 1：
```
输入：root = [10,5,15,1,8,null,7]
输出：3
解释：本例中最大的 BST 子树是高亮显示的子树。返回值是子树的大小，即 3 。
```
示例 2：
```
输入：root = [4,2,7,2,3,5,null,2,null,null,null,null,null,1]
输出：2
```
提示：
```
树上节点数目的范围是 [0, 104]
-104 <= Node.val <= 104
```
## 解法
### 思路
- bfs遍历二叉树
- 每个节点dfs中序遍历
- 如果节点生成的序列是升序就更新结果值为序列长度
- bfs结束后，返回结果
### 代码
```java
class Solution {
    public int largestBSTSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int ans = 0;
        while (!queue.isEmpty()) {
            int count = queue.size();
            while (count-- > 0) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }

                List<Integer> inOrderList = new ArrayList<>();
                inOrder(node, inOrderList);
                if (natureOrder(inOrderList)) {
                    ans = Math.max(ans, inOrderList.size());
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

        return ans;
    }

    private void inOrder(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        inOrder(node.left, list);
        list.add(node.val);
        inOrder(node.right, list);
    }

    private boolean natureOrder(List<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) <= list.get(i - 1)) {
                return false;
            }
        }
        return true;
    }
}
```
## 解法二
### 思路
- 如果当前节点的左右子树是BST，那么：
    - 当前值比左子树的最大值大，比右子树的最小值小，则返回左右子树的节点值总和+1
    - 如果不是就返回左右子树中最大的那个BST
- 如果只有一个子树是BST，就返回那个
- 如果没有子树是，就返回null
- 这样看就需要自底向上的遍历这棵树
- 定义一个遍历结果的抽象：
    - 遍历后找到的最大BST的根节点
    - 最大BST的最大值
    - 最大BST的最小值
    - 最大BST的节点数
### 代码
```java
class Solution {
    public int largestBSTSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Result result = find(root);
        return result == null ? 0 : result.size;
    }

    private Result find(TreeNode node) {
        if (node == null) {
            return null;
        }

        Result left = null, right = null;
        if (node.left != null) {
            left = find(node.left);
        }

        if (node.right != null) {
            right = find(node.right);
        }

        boolean leftIsValid = node.left == null || (left.root == node.left && left.max < node.val),
                rightIsValid = node.right == null || (right.root == node.right && right.min > node.val);

        if (leftIsValid && rightIsValid) {
            Result result = new Result();
            result.root = node;
            result.min = left == null ? node.val : left.min;
            result.max = right == null ? node.val : right.max;
            result.size = (left == null ? 0 : left.size) + (right == null ? 0 : right.size) + 1;
            return result;
        }

        if (left != null && right != null) {
            return left.size > right.size ? left : right;
        }

        if (left != null) {
            return left;
        }

        return right;
    }

    class Result {
        private TreeNode root;
        private int min;
        private int max;
        private int size;
    }
}
```