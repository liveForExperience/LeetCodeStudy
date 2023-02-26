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
# [LeetCode_1255_得分最高的单词集合](https://leetcode.cn/problems/maximum-score-words-formed-by-letters/)
## 解法
### 思路
状态压缩
- 因为单词个数只有14，所以通过一个32位的整数就可以表示单词是否被选择的状态
- 遍历所有状态，然后统计即可
### 代码
```java
class Solution {
    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        int n = words.length, sum = 0;
        int[] count = new int[26];
        for (char letter : letters) {
            count[letter - 'a']++;
        }
        
        for (int i = 1; i <= (1 << n); i++) {
            int[] curCount = new int[26];
            for (int j = 0; j < n; j++) {
                if (((1 << j) & i) == 0) {
                    continue;
                }
                
                String word = words[j];
                for (char c : word.toCharArray()) {
                    curCount[c - 'a']++;
                }
            }
            
            boolean flag = true;
            int curSum = 0;
            for (int j = 0; j < 26; j++) {
                if (curCount[j] > count[j]) {
                    flag = false;
                    break;
                }
                
                curSum += curCount[j] * score[j];
            }
            
            if (flag) {
                sum = Math.max(sum, curSum);
            }
        }
        
        return sum;
    }
}
```
# [LeetCode_779_第K个语法符号](https://leetcode.cn/problems/k-th-symbol-in-grammar/)
## 解法
### 思路
- 根据模拟可以发现，当前层与上一层的关系是：
  - 当前层平均分成前后2段
  - 前一段和前一层完全相同
  - 后一段与前一段每一位都完全相反
- 所以这是一个连续子问题，每一层都去判断k是否属于前半段还是后半段，如果属于前半段，就和上一层返回的数值相等，否则就是相反值（0变1，1变0，可以使用^1来处理）
### 代码
```java
class Solution {
    public int kthGrammar(int n, int k) {
        if (n == 1 || k == 1) {
            return 0;
        }

        int mid = (int) Math.pow(2, n - 2);
        if (k <= mid) {
            return kthGrammar(n - 1, k);
        }

        return kthGrammar(n - 1, k - mid) ^ 1;
    }
}
```
# [LeetCode_2566_1_替换一个数字后的最大差值](https://leetcode.cn/problems/maximum-difference-by-remapping-a-digit/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public int minMaxDifference(int num) {
        int len = 0;
        int tmp = num;
        while (tmp > 0) {
            tmp /= 10;
            len++;
        }
        
        int[] arr = new int[len];
        int index = len -1;
        while (num > 0) {
            arr[index--] = num % 10;
            num /= 10;
        }

        int x = -1, y = -1;
        for (int k : arr) {
            if (k != 9 && x == -1) {
                x = k;
            }

            if (k != 0 && y == -1) {
                y = k;
            }
        }
        
        int smallest = 0, biggest = 0;
        for (int k : arr) {
            int cs = k, cb = k;
            if (k == x) {
                cb = 9;
            }
            
            if (k == y) {
                cs = 0;
            }
            
            smallest = smallest * 10 + cs;
            biggest = biggest * 10 + cb;
        }
        
        return biggest - smallest;
    }
}
```
# [LeetCode_2570_合并两个二维数组-求和法](https://leetcode.cn/problems/merge-two-2d-arrays-by-summing-values/)
## 解法
### 思路
双指针模拟
### 代码
```java
class Solution {
    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        List<int[]> list = new ArrayList<>();
        int i1 = 0, n1 = nums1.length, i2 = 0, n2 = nums2.length;
        while (i1 < n1 || i2 < n2) {
            if (i1 >= n1) {
                list.add(nums2[i2++]);
                continue;
            }

            if (i2 >= n2) {
                list.add(nums1[i1++]);
                continue;
            }

            int id1 = nums1[i1][0], id2 = nums2[i2][0];
            if (id1 < id2) {
                list.add(nums1[i1++]);
            } else if (id1 > id2) {
                list.add(nums2[i2++]);
            } else {
                list.add(new int[]{id1, nums1[i1][1] + nums2[i2][1]});
                i1++;
                i2++;
            }
        }

        return list.toArray(new int[list.size()][2]);
    }
}
```