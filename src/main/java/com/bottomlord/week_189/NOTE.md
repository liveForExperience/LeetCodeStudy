# [LeetCode_1792_最大平均通过率](https://leetcode.cn/problems/maximum-average-pass-ratio/)
## 解法
### 思路
优先级队列
- 比较的是增量之间的差值，将增量大的放在堆顶
- 需要注意比较的时候要转换成double
### 代码
```java
class Solution {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((x, y) -> {
            double diff = increasment(y) - increasment(x);
            if (diff == 0) {
                return 0;
            }
            return diff > 0 ? 1 : -1;
        });

        for (int[] aClass : classes) {
            queue.offer(aClass);
        }

        for (int i = 0; i < extraStudents; i++) {
            int[] aClass = queue.poll();
            if (aClass == null) {
                continue;
            }

            aClass[0]++;
            aClass[1]++;
            queue.offer(aClass);
        }

        double sum = 0, size = queue.size();
        while (!queue.isEmpty()) {
            int[] aClass = queue.poll();
            sum += 1D * aClass[0] / aClass[1];
        }

        return sum / size;
    }

    private double increasment(int[] x) {
        return (1D * x[0] + 1) / (x[1] + 1) - 1D * x[0] / x[1];
    }
}
```
# [LeetCode_755_倒水](https://leetcode.cn/problems/pour-water/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public int[] pourWater(int[] heights, int volume, int k) {
        while (volume-- > 0) {
            int choice = k;
            for (int i = k - 1; i >= 0; i--) {
                if (heights[i] < heights[i + 1]) {
                    choice = i;
                } else if (heights[i] > heights[i + 1]) {
                    break;
                }
            }
            
            if (choice != k) {
                heights[choice]++;
                continue;
            }
            
            for (int i = k + 1; i < heights.length; i++) {
                if (heights[i] < heights[i - 1]) {
                    choice = i;
                } else if (heights[i] > heights[i - 1]) {
                    break;
                }
            }
            
            heights[choice]++;
        }
        
        return heights;
    }   
}
```
# [LeetCode_776_拆分二叉搜索树](https://leetcode.cn/problems/split-bst/)
## 解法
### 思路
dfs：
- 思路是从底向上返回数组，并考虑BST的特性
- 递归的退出条件就是节点为null，此时就返回一个都是空元素的数组
- 主体逻辑中:
  - 获取到当前节点的值val
  - val与target进行比较
    - 如果小于等于target，那么当前节点的左子树一定都是小于target的节点，当前节点就可以作为当前层返回的数组的第一个元素，而当前节点的右子树虽然都大于当前节点的值，但是也可能包含比taget小的值，所以也需要搜索，搜索的过程中也会返回一个包含2个元素的数组，第一个元素就是当前节点的右子树的根节点，而第二个元素就是当前层第二个元素的根节点
    - 同理应用在大于target的情况上
  - 最终返回当前层组成数组给上一层，这样递归返回
### 代码
```java
class Solution {
    public TreeNode[] splitBST(TreeNode root, int target) {
        if (root == null) {
            return new TreeNode[]{null, null};
        }

        TreeNode[] arr = new TreeNode[2];
        int val = root.val;
        TreeNode left = root.left, right = root.right;
        if (val <= target) {
            arr[0] = root;
            TreeNode[] rightArr = splitBST(right, target);
            root.right = rightArr[0];
            arr[1] = rightArr[1];
        } else {
            arr[1] = root;
            TreeNode[] leftArr = splitBST(left, target);
            root.left = leftArr[1];
            arr[0] = leftArr[0];
        }

        return arr;
    }
}
```