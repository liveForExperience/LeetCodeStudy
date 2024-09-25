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
# [LeetCode_780_到达终点](https://leetcode-cn.com/problems/reaching-points/)
## 解法
### 思路
- 题目限制出现的数字都是正整数，所以：
  - 如果tx == ty，那么这两个数字的组合没有上一个状态
  - 如果tx > ty，那么一定是从(tx + ty, ty)的状态转变过啦的
  - 同理，tx < ty，那么就是从(tx, ty + tx)的状态转变过来的
- 于是可以通过tx和ty反推出某一个前置的状态，这个反推的过程停止与如下的情况
  - tx == sx
  - ty == sy
  - tx == ty
- 当tx和ty反推结束后，对tx和ty进行判断
- 如果tx < sx 或者 ty < sy，那么肯定是不满足的
- 如果tx == sx或者ty == sy，那么说明剩下的那个数，例如tx，一定要比sx大，且一定是通过sx + sy的方式获得的，所以可以通过(tx - sx) % sy == 0来判断是否符合，如果符合就是true，否则就是false
- 还有一种情况就是直接tx == sx 且 ty == sy
### 代码
```java
class Solution {
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        while (tx > sx && ty > sy && tx != ty) {
            if (tx > ty) {
                tx %= ty;
            } else {
                ty %= tx;
            }
        }

        if (tx == sx && ty == sy) {
            return true;
        } else if (tx == sx && (ty > sy && (ty - sy) % sx == 0)) {
            return true;
        } else if (ty == sy && (tx > sx && (tx - sx) % sy == 0)) {
            return true;
        } else {
            return false;
        }
    }
}
```
# [LeetCode_681_最近时刻](https://leetcode-cn.com/problems/next-closest-time/)
## 解法
### 思路
- 枚举所有可能
- 将字符串转换成数字，然后排序
- 计算原值与存在的左右数字之间的差，找到差值最小的那个值
- 转换成字符串后返回
- 数字和字符换可以通过hash表映射
- 需要注意
  - 如果是比time小的时间，应该要加上1天的时间
  - 为了方便找到最大值，可以将数字
### 代码
```java
class Solution {
    public String nextClosestTime(String time) {
        int[] arr = new int[4];
        for (int i = 0; i < 5; i++) {
            if (i == 2) {
                continue;
            }

            int index = i < 2 ? i : i - 1;
            arr[index] = getNumFromStr(time, i);
        }

        if (arr[0] == arr[1] && arr[1] == arr[2] && arr[2] == arr[3]) {
            return time;
        }

        Map<String, Integer> map = new HashMap<>();
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        backTrack(0, arr, new StringBuilder(), map, treeMap, parse(time));

        int target = map.get(time);
        return treeMap.get(treeMap.ceilingKey(target + 1));
    }

    private int getNumFromStr(String time, int index) {
        return time.charAt(index) - '0';
    }

    private List<Integer> listCandidateNum(int[] arr, int index, Integer pre) {
        List<Integer> ans = new ArrayList<>();
        for (int num : arr) {
            if (index == 0) {
                if (num <= 2) {
                    ans.add(num);
                }
            }

            if (index == 1) {
                if (pre < 2) {
                    ans.add(num);
                } else {
                    if (num < 4) {
                        ans.add(num);
                    }
                }
            }

            if (index == 2) {
                if (num <= 5) {
                    ans.add(num);
                }
            }

            if (index == 3) {
                ans.add(num);
            }
        }

        return ans;
    }

    private int parse(String time) {
        String[] times = time.split(":");
        int hour = Integer.parseInt(times[0]), minute = Integer.parseInt(times[1]);
        return hour * 60 + minute;
    }

    private void backTrack(int index, int[] arr, StringBuilder sb, Map<String, Integer> map, TreeMap<Integer, String> treeMap, int target) {
        if (index > 3) {
            String str = sb.substring(0, 2) + ":" + sb.substring(2, 4);
            int num = parse(str);
            
            if (num < target) {
                num += 60 * 24;
            }
            
            map.put(str, num);
            treeMap.put(num, str);
            return;
        }

        List<Integer> candidates = listCandidateNum(arr, index, index != 0 ? Integer.parseInt("" + sb.charAt(index - 1)) : null);
        int len = sb.length();
        for (Integer candidate : candidates) {
            sb.append(candidate);
            backTrack(index + 1, arr, sb, map, treeMap, target);
            sb.setLength(len);
        }
    }
}
```