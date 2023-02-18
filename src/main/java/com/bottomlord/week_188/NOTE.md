# [LeetCode_1234_替换子串得到平衡字符串](https://leetcode.cn/problems/replace-the-substring-for-balanced-string/)
## 解法
### 思路
双指针
- 初始化字母出现的个数
- 右指针用于确定区间的右边界，寻找右边的过程中，对出现个数做递减，同时判断个数是否全部匹配。这个动作的意义可以理解成找到窗口右边满足要求的最大长度
- 左指针用于确定区间的左边界，然后基于右边界，找到窗口左侧的最大长度
- 左右指针确定后就更新最小窗口
### 代码
```java
class Solution {
    public int balancedString(String s) {
        int l = 0, r = 0, n = s.length(), ans = n;
        int[] bucket = new int[26];
        for (char c : s.toCharArray()) {
            bucket[c - 'A']++;
        }
        
        boolean ok = true;

        for (int num : bucket) {
            if (num > n / 4) {
                ok = false;
                break;
            }
        }
        
        if (ok) {
            return 0;
        }
        
        for (; r < s.length(); r++) {
            bucket[s.charAt(r) - 'A']--;
            ok = true;
            
            for (int num : bucket) {
                if (num > n / 4) {
                    ok = false;
                    break;
                }
            }

            while (ok) {
                ans = Math.min(ans, r - l + 1);
                bucket[s.charAt(l) - 'A']++;

                if (bucket[s.charAt(l) - 'A'] > n / 4) {
                    ok = false;
                }

                l++;
            }
        }

        return ans;
    }
}
```
# [LeetCode_2562_找出数组的串联值](https://leetcode.cn/problems/find-the-array-concatenation-value/)
## 解法
### 思路
双指针+模拟
### 代码
```java
class Solution {
    public long findTheArrayConcVal(int[] nums) {
        int l = 0, r = nums.length - 1;
        long ans = 0;

        while (l <= r) {
            long num = 0;
            int index = 0;
            while (nums[r] > 0) {
                int cur = 1;
                for (int i = 0; i < index; i++) {
                    cur *= 10;
                }
                
                num = (nums[r] % 10L) * cur + num;
                nums[r] /= 10;
                index++;
            }

            while (nums[l] > 0) {
                int cur = 1;
                for (int i = 0; i < index; i++) {
                    cur *= 10;
                }

                num = (nums[l] % 10L) * cur + num;
                nums[l] /= 10;
                index++;
            }

            ans += num;
            l++;
            r--;
        }

        return ans;
    }
}
```
# [LeetCode_1124_表现良好的最长时间段](https://leetcode.cn/problems/longest-well-performing-interval/)
## 解法
### 思路
前缀和+嵌套循环判断
### 代码
```java
class Solution {
    public int longestWPI(int[] hours) {
        int n = hours.length;
        int[] status = new int[n];
        for (int i = 0; i < n; i++) {
            status[i] = hours[i] > 8 ? 1 : -1;
        }

        int[] sums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + status[i - 1];
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= 0; j--) {
                if (sums[j + 1] - sums[i] > 0) {
                    ans = Math.max(ans, j - i + 1);
                    break;
                }
            }
        }

        return ans;
    }
}
```
# [LeetCode_1250_检查好数组](https://leetcode.cn/problems/check-if-it-is-a-good-array/)
## 解法
### 思路
裴蜀定理
- 计算所有元素的最大公约数
- 如果最大公约数是1就返回true
### 代码
```java
class Solution {
    public boolean isGoodArray(int[] nums) {
        int g = nums[0];
        for (int i = 1; i < nums.length; i++) {
            g = gcd(g, nums[i]);
        }

        return g == 1;
    }

    private int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }
}
```
# [LeetCode_742_二叉树最近的叶节点](https://leetcode.cn/problems/closest-leaf-in-a-binary-tree/)
## 解法
### 思路
- dfs整颗树，将树转成图
- bfs图，找到第一个叶子结点，也就是图中只有1个出度的节点
### 代码
```java
class Solution {
    public int findClosestLeaf(TreeNode root, int k) {
        Map<TreeNode, List<TreeNode>> graph = new HashMap<>();
        dfs(graph, root, null);

        Queue<TreeNode> queue = new ArrayDeque<>();
        Set<TreeNode> seen = new HashSet<>();
        for (TreeNode treeNode : graph.keySet()) {
            if (treeNode != null && treeNode.val == k) {
                queue.offer(treeNode);
            }
        }

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                continue;
            }

            List<TreeNode> nodes = graph.getOrDefault(node, new ArrayList<>());

            if (nodes.size() <= 1) {
                return node.val;
            } else {
                for (TreeNode treeNode : nodes) {
                    if (seen.contains(treeNode) || treeNode == null) {
                        continue;
                    }

                    queue.offer(treeNode);
                    seen.add(node);
                }
            }
        }

        return -1;
    }

    private void dfs(Map<TreeNode, List<TreeNode>> graph, TreeNode node, TreeNode parent) {
        if (node == null) {
            return;
        }

        graph.computeIfAbsent(node, x -> new ArrayList<>()).add(parent);
        graph.computeIfAbsent(parent, x -> new ArrayList<>()).add(node);

        dfs(graph, node.left, node);
        dfs(graph, node.right, node);
    }
}
```
# [LeetCode_1270_找出给定方程的正整数解](https://leetcode.cn/problems/find-positive-integer-solution-for-a-given-equation/)
## 解法
### 思路
二分查找
- 题目x和y的取值区间是1-1000，所以二分查找的区间是[1,1000]
- 然后就在确定x的情况下，去根据z和function去找到y的值，找的方式就是二分查找
- 需要注意，如果返回的是0，那就说明x太大，因为function的返回是单调递增的，这个时候就可以提前结束对x的取值循环，直接返回了
### 代码
```java
class Solution {
    public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 1; i <= 1000; i++) {
            int j = binarySearch(i, customfunction, z);
            if (j == 0) {
                return ans;
            } else if (j != -1) {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                list.add(j);
                ans.add(list);
            }
        }

        return ans;
    }

    private int binarySearch(int x, CustomFunction function, int z) {
        int head = 1, tail = 1000;
        while (head <= tail) {
            int mid = (head + tail) >> 1;
            int r = function.f(x, mid);
            if (r < z) {
                head = mid + 1;
            } else if (r > z) {
                tail = mid - 1;
            } else {
                return mid;
            }
        }

        return tail == 0 ? 0 : -1;
    }
}
```