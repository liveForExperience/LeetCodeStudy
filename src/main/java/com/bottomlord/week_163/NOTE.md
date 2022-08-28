# [LeetCode_749_隔离病毒](https://leetcode.cn/problems/contain-virus/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_655_输出二叉树](https://leetcode.cn/problems/print-binary-tree/)
## 解法
### 思路
dfs
- dfs获取二叉树深度
- 根据题目要求，得到m和n的值
- 初始化二维list
- dfs搜索并在list中填值
- dfs结束返回
### 代码
```java
class Solution {
    public List<List<String>> printTree(TreeNode root) {
        int m = depth(root), height = m - 1,  n = (1 << m) - 1;
        List<List<String>> ans = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<String> list = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                list.add("");
            }
            ans.add(list);
        }

        print(root, ans, 0, (n - 1) / 2, height);
        return ans;
    }

    private int depth(TreeNode node) {
        if (node == null) {
            return 0;
        }

        return Math.max(depth(node.left), depth(node.right)) + 1;
    }

    private void print(TreeNode node, List<List<String>> ans, int r, int c, int height) {
        if (node == null) {
            return;
        }

        ans.get(r).set(c, "" + node.val);
        print(node.left, ans, r + 1, c - (1 << (height - r - 1)), height);
        print(node.right, ans, r + 1, c + (1 << (height - r - 1)), height);
    }
}
```
# [LeetCode_658_找到K个最接近的元素](https://leetcode.cn/problems/find-k-closest-elements/)
## 解法
### 思路
- 对数组进行排序，排序的规则是与x的距离越小越靠前
- 截取前k个元素
- 对截取的列表做排序后返回
### 代码
```java
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> list = new ArrayList<>();
        for (int num : arr) {
            list.add(num);
        }

        list.sort((a, b) -> {
            int disA = Math.abs(a - x), disB = Math.abs(b - x);
            if (disA != disB) {
                return disA - disB;
            }

            return a - b;
        });
        
        List<Integer> ans = list.subList(0, k);
        ans.sort(Comparator.naturalOrder());
        return ans;
    }
}
```
## 解法二
### 思路
- 二分查找找到最接近x的坐标
- 双指针确定区间范围
- 返回区间元素
### 代码
```java
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int index = binarySearch(arr, x);
        int l = index - 1, r = index;
        while (k-- > 0) {
            if (l < 0) {
                r++;
            } else if (r >= arr.length) {
                l--;
            } else if (x - arr[l] > arr[r] - x) {
                r++;
            } else {
                l--;
            }
        }
        
        List<Integer> ans = new ArrayList<>();
        for (int i = l + 1; i < r; i++) {
            ans.add(arr[i]);
        }
        return ans;
    }
    
    private int binarySearch(int[] arr, int target) {
        int head = 0, tail = arr.length - 1;
        while (head < tail) {
            int mid = head + (tail - head) / 2;
            int num = arr[mid];
            if (num >= target) {
                tail = mid;
            } else {
                head = mid + 1;
            }
        }
        
        return head;
    }
}
```
# [LeetCode_1464_数组中两元素的最大乘积](https://leetcode.cn/problems/maximum-product-of-two-elements-in-an-array/)
## 解法
### 思路
- 排序数组，获取最大的两个元素
- 根据最大的两个元素进行计算并返回
### 代码
```java
public class Solution {
    public int maxProduct(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length - 1] * nums[nums.length - 2];
    }
}
```
## 解法二
### 思路
遍历确定最大的2个数
### 代码
```java
public class Solution {
    public int maxProduct(int[] nums) {
        int one = 0, two = 0;
        for (int num : nums) {
            if (one == 0) {
                one = num;
                continue;
            }

            if (two == 0) {
                if (one > num) {
                    two = num;
                } else {
                    two = one;
                    one = num;
                }
                continue;
            }

            if (num > one) {
                two = one;
                one = num;
            } else if (num > two) {
                two = num;
            }
        }

        return (one - 1) * (two - 1);
    }
}
```
# [LeetCode_662_二叉树最大宽度](https://leetcode.cn/problems/maximum-width-of-binary-tree/)
## 解法
### 思路
bfs
### 代码
```java
class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        Queue<WrapNode> queue = new ArrayDeque<>();
        queue.offer(new WrapNode(root, 0));
        int ans = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            int count = queue.size();
            List<Integer> list = new ArrayList<>();
            while (count-- > 0) {
                WrapNode wrapNode = queue.poll();
                if (wrapNode == null) {
                    continue;
                }
                
                TreeNode node = wrapNode.node;
                list.add(wrapNode.index);
                
                if (node.left != null) {
                    queue.offer(new WrapNode(node.left, wrapNode.index * 2));
                }
                
                if (node.right != null) {
                    queue.offer(new WrapNode(node.right, wrapNode.index * 2 + 1));
                }
            }
            
            ans = Math.max(ans, list.get(list.size() - 1) - list.get(0) + 1);
        }
        
        return ans;
    }
    
    private static class WrapNode {
        private TreeNode node;
        private int index;
        
        public WrapNode(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }
}
```
## 解法二
### 思路
dfs
### 代码
```java
class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        return dfs(new WrapNode(root, 0), 0, map);
    }
    
    private int dfs(WrapNode node, int depth, Map<Integer, Integer> map) {
        if (node == null) {
            return 0;
        }
        
        if (!map.containsKey(depth)) {
            map.put(depth, node.index);
        }
        
        WrapNode left = node.node.left == null ? null : new WrapNode(node.node.left, node.index * 2),
                 right = node.node.right == null ? null : new WrapNode(node.node.right, node.index * 2 + 1);
        
        return Math.max(node.index - map.get(depth) + 1, 
                Math.max(dfs(left, depth + 1, map), dfs(right, depth + 1, map)));
    }

    private static class WrapNode {
        private TreeNode node;
        private int index;

        public WrapNode(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }
}
```
# [LeetCode_793_阶乘函数后K个零](https://leetcode.cn/problems/preimage-size-of-factorial-zeroes-function/)
## 解法
### 思路
[题解](https://leetcode.cn/problems/preimage-size-of-factorial-zeroes-function/solution/by-muse-77-ajqn/)
### 代码
```java
class Solution {
    public int preimageSizeFZF(int k) {
        long head = 0, tail = 5L * k;
        while (head <= tail) {
            long mid = head + (tail - head) / 2;
            long n = 5L, num = 0L;
            while (n <= mid) {
                num += mid / n;
                n *= 5;
            }
            
            if (num == k) {
                return 5;
            } else if (num < k) {
                head = mid + 1;
            } else {
                tail = mid - 1;
            }
        }
        
        return 0;
    }
}
```