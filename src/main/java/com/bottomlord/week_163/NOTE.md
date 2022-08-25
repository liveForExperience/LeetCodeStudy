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