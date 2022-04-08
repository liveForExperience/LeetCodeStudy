# [LeetCode_307_区域和检索-数组可修改]()
## 解法
### 思路
线段树
### 代码
```java
class NumArray {
    private int n;
    private int[] tree;
    public NumArray(int[] nums) {
        this.n = nums.length;
        this.tree = new int[2 * n];
        for (int i = 0, j = n; j < 2 * n; j++, i++) {
            tree[j] = nums[i];
        }

        for (int i = n - 1; i > 0; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    public void update(int index, int val) {
        int i = index + n;
        tree[i] =val;
        while (i > 0) {
            int l = i % 2 == 0 ? i : i - 1;
            int r = i % 2 == 0 ? i + 1 : i;

            i /= 2;
            tree[i] = tree[l] + tree[r];
        }
    }

    public int sumRange(int left, int right) {
        left += n;
        right += n;
        int sum = 0;
        while (left <= right) {
            if (left % 2 == 1) {
                sum += tree[left++];
            }

            if (right % 2 == 0) {
                sum += tree[right--];
            }

            left /= 2;
            right /= 2;
        }

        return sum;
    }
}
```
# [LeetCode_666_路径总和IV](https://leetcode-cn.com/problems/path-sum-iv/)
## 解法
### 思路
构建二叉树并dfs
### 代码
```java
class Solution {
    private int ans = 0;

    public int pathSum(int[] nums) {
        int maxDepth = nums[nums.length - 1] / 100;
        List<TreeNode[]> list = new ArrayList<>();
        for (int i = 0; i < maxDepth; i++) {
            list.add(new TreeNode[1 << i]);
        }
        
        for (int num : nums) {
            int depth = num / 100, pos = (num % 100) / 10, val = num % 10;
            TreeNode node = new TreeNode(val);
            list.get(depth - 1)[pos - 1] = node;
            if (depth - 1 > 0) {
                if (pos % 2 == 1) {
                    list.get(depth - 2)[(pos - 1) / 2].left = node;
                } else {
                    list.get(depth - 2)[(pos - 1) / 2].right = node;
                }
            }
        }

        dfs(list.get(0)[0], 0);
        return ans;
    }

    private void dfs(TreeNode node, int sum) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            ans += node.val + sum;
        }

        dfs(node.left, sum + node.val);
        dfs(node.right, sum + node.val);
    }

    private class TreeNode {
        private int val;
        private TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
```
# [LeetCode_310_最小高度树](https://leetcode-cn.com/problems/minimum-height-trees/)
## 解法
### 思路
- 根据观察可以发现，最小高度树，根节点只可能是1或者2个
- 而根节点一定是可以通过层层去除叶子结点，最终获得的
- 所以可以通过邻接表，求出叶子结点的列表
- 然后将叶子结点去除后，不断循环，直到节点个数小于等于2为止
- 邻接表中界定叶子结点：相连节点个数为1，则当前节点就是叶子节点
### 代码
```java
class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) {
            return Collections.singletonList(0);
        }

        List<Set<Integer>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new HashSet<>());
        }

        for (int[] edge : edges) {
            list.get(edge[0]).add(edge[1]);
            list.get(edge[1]).add(edge[0]);
        }

        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).size() == 1) {
                leaves.add(i);
            }
        }

        while (n > 2) {
            n -= leaves.size();
            List<Integer> newLeaves = new ArrayList<>();
            for (Integer leaf : leaves) {
                int node = list.get(leaf).iterator().next();
                list.get(node).remove(leaf);
                if (list.get(node).size() == 1) {
                    newLeaves.add(node);
                }
            }
            leaves = newLeaves;
        }
        
        return leaves;
    }
}
```
# [LeetCode_2220_转换数字的最少位翻转次数](https://leetcode-cn.com/problems/minimum-bit-flips-to-convert-number/)
## 解法
### 思路
求汉明距离
### 代码
```java
class Solution {
    public int minBitFlips(int start, int goal) {
        int xor = start ^ goal;
        
        int bit = 1, count = 0;
        while (xor > 0) {
            if ((xor & bit) == 1) {
                count++;
            }
            
            xor >>= 1;
        }
        
        return count;
    }
}
```
# [LeetCode_2229_检查数组是否连续](https://leetcode-cn.com/problems/check-if-an-array-is-consecutive/)
## 解法
### 思路
- 排序后遍历数组，查看是否以1为步数递增
### 代码
```java
class Solution {
    public boolean isConsecutive(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] + 1 != nums[i + 1]) {
                return false;
            }
        }

        return true;
    }
}
```
# [LeetCode_670_最大交换](https://leetcode-cn.com/problems/maximum-swap/)
## 解法
### 思路
- 将数字转换为字符数组
- 从第0个元素开始，找到从当前坐标到字符数组结尾的范围中最大值
- 如果最大值和当前坐标对应的值不相等，那么就交换，然后停止处理，直接将字符数组转换后返回
- 如果相等，那么就右移坐标，然后使用相同的逻辑处理，只不过范围缩小了
- 如果整个遍历过程都没有找到符合的情况，那么说明数字本身就是最大值，返回原值即可
### 代码
```java
class Solution {
    public int maximumSwap(int num) {
        String str = Integer.toString(num);
        int n = str.length();
        char[] cs = str.toCharArray();
        
        for (int i = 0; i < n; i++) {
            int index = findMaxIndex(cs, i);
            char maxChar = str.charAt(index);
            
            if (cs[i] != maxChar) {
                cs[index] = cs[i];
                cs[i] = maxChar;
                break;
            }
        }

        return Integer.parseInt(new String(cs));
    }
    
    private int findMaxIndex(char[] cs, int start) {
        char maxChar = '0';
        int index = start;
        
        for (int i = start; i < cs.length; i++) {
            if (maxChar <= cs[i]) {
                maxChar = cs[i];
                index = i;
            }
        }
        
        return index;
    }
}
```