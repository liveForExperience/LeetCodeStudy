# [LeetCode_493_翻转对](https://leetcode-cn.com/problems/reverse-pairs/)
## 失败解法
### 思路
- 从数组最后向前循环遍历，时间复杂度是O(N^2)
- 注意int值溢出，比对时转成long
### 代码
```java
class Solution {
    public int reversePairs(int[] nums) {
        int count = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if ((long)nums[i] * 2 < (long)nums[j]) {
                    count++;
                }
            }
        }
        
        return count;
    }
}
```
## 解法
### 思路
归并排序
- 在归并的二分排序后，合并之前，对两个有序的数组进行计数统计
- 因为两个序列独自是升序的，而两个序列之间在原序列基础上又是相对有序的，所以只要以比如右边序列的1个元素作为`nums[j]`，与左边序列的元素进行比较，然后找到符合题目的零界点，那么左边该元素之后的所有元素也都是符合题目要求的
- 暂存一个全局变量count进行计数
### 代码
```java
class Solution {
    private int count = 0;
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        mergeSort(nums, 0, nums.length - 1);
        return count;
    }

    private void mergeSort(int[] nums, int left, int right) {
        if (left == right) {
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);

        int i = left, j = mid + 1;
        while (i <= mid) {
            while (j <= right && (long)nums[i] > (long)nums[j] * 2) {
                j++;
            }

            count += j - mid - 1;
            i++;
        }

        int[] tmp = new int[right - left + 1];
        int index = 0;
        i = left;
        j = mid + 1;

        while (i <= mid && j <= right) {
            if (nums[i] < nums[j]) {
                tmp[index++] = nums[i++];
            } else {
                tmp[index++] = nums[j++];
            }
        }

        while (i <= mid) {
            tmp[index++] = nums[i++];
        }

        while (j <= right) {
            tmp[index++] = nums[j++];
        }

        for (i = 0, j = left; j <= right; i++, j++) {
            nums[j] = tmp[i];
        }
    }
}
```
# [LeetCode_767_重构字符串](https://leetcode-cn.com/problems/reorganize-string/)
## 解法
### 思路
- 计算字符串个数，生成长度为26的计数桶`bucket`
- 初始化一个存储元素为int数组的大顶堆`queue`
- 遍历`bucket`，生成长度为2，0坐标为字母index，1坐标为字母个数的int数组，压入`queue`
- 初始化一个StringBuilder，用来暂存结果
- 遍历`queue`，终止条件为`queue`空
- 获取大顶堆堆顶的2个元素，如果堆顶只有一个元素，且堆顶元素的字母个数大于1，则返回空字符串，说明无法满足题目要求，否则将最后的字母追加到sb中，并返回
- 如果有2个元素：
    - 比对两个元素的字母个数，如果不一样，就用大的减去小的，重新生成int数组，放入大顶堆
    - 然后循环少字母的出现个数次，以先多字母后少字母的顺序将字母追加到sb中
- 遍历大顶堆结束，返回暂存的sb
### 代码
```java
class Solution {
    public String reorganizeString(String S) {
        if (S.length() <= 1) {
            return S;
        }

        int[] bucket = new int[26];
        for (char c : S.toCharArray()) {
            bucket[c - 'a']++;
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>((x, y) -> y[1] - x[1]);
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] > 0) {
                queue.offer(new int[]{i, bucket[i]});
            }
        }

        StringBuilder sb = new StringBuilder();
        
        while (!queue.isEmpty()) {
            int[] first = queue.poll();
            
            if (queue.isEmpty()) {
                if (first[1] != 1) {
                    return "";
                } else {
                    sb.append((char)(first[0] + 'a'));
                    return sb.toString();
                }
            }
            
            int[] second = queue.poll();
            if (first[1] != second[1]) {
                first[1] -= second[1];
                queue.offer(first);
            }
            
            for (int i = 0; i < second[1]; i++) {
                sb.append((char)(first[0] + 'a'));
                sb.append((char)(second[0] + 'a'));
            }
        }

        return sb.toString();
    }
}
```
# [LeetCode_362_敲击计数器](https://leetcode-cn.com/problems/design-hit-counter/)
## 解法
### 思路
队列计数
### 代码
```java
class HitCounter {
    private ArrayDeque<int[]> queue;
    public HitCounter() {
        queue = new ArrayDeque<>();
    }

    public void hit(int timestamp) {
        if (!queue.isEmpty()) {
            int[] last = queue.getLast();
            if (last[0] == timestamp) {
                last[1]++;
                return;
            }
        }

        queue.offer(new int[]{timestamp, 1});
    }

    public int getHits(int timestamp) {
        int pre5minTimestamp = timestamp - 5 * 60, count = 0;
        while (!queue.isEmpty()) {
            int[] element = queue.peek();
            if (element[0] <= pre5minTimestamp) {
                queue.poll();
            } else {
                break;
            }
        }

        for (int[] element : queue) {
            count += element[1];
        }

        return count;
    }
}
```
# [LeetCode_363_矩形区域不超过K的最大数值和](https://leetcode-cn.com/problems/max-sum-of-rectangle-no-larger-than-k/)
## 解法
### 思路
动态规划：
- `dp[r1][c1][r2][c2]`：左上角(r1,c1)与右下角(r2,c2)组成的矩形的面积
- 状态转移方程：`dp[r1][c1][r2][c2] = dp[r1][c1][r2 - 1][c2] + dp[r1][c1][r2][c2 - 1] - dp[r1][c1][r2 - 1][c2 - 1] + matrix[r2][c2]`
- 优化dp：从如上的状态转移方程中可以看到，左上角的(r1,c1)在计算时不会变化，所有可以直接只记录右下角的坐标
- `dp[r1][c1]`：右下角为(r1,c1)的矩形的面积
- 新的状态转移方程：`dp[r1][c1] = dp[r1 - 1][c1] + dp[r1][c1 - 1] - dp[r1 - 1][c1 - 1] + matrix[r1][c1]`
- base case：遍历时的第一个矩形，`dp[i][j] = matrix[i][j]`
- 过程：
    - 从第一个矩形开始不断扩大矩形右下角的取值范围
    - 然后通过状态转移方程计算当前矩形的面积，并与k作比较，取适合的值作为最大值
- 结果返回暂存的最大值
### 代码
```java
class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int row = matrix.length, col = matrix[0].length, max = Integer.MIN_VALUE;

        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                int[][] dp = new int[row + 1][col + 1];
                for (int r = i; r <= row; r++) {
                    for (int c = j; c <= col; c++) {
                        dp[r][c] = dp[r - 1][c] + dp[r][c - 1] - dp[r - 1][c - 1] + matrix[r - 1][c - 1];
                        if (dp[r][c] <= k && dp[r][c] > max) {
                            max = dp[r][c];
                        }
                    }
                }
            }
        }
        
        return max;
    }
}
```
## 解法二
### 思路
- 使用前缀和将二维数组压缩成一维
- 在确定列的左右边界后，通过计算前缀和数组（每一个元素就是当前行的左右边界元素总和）的最大连续和，并与k作比较，就可以获得max
### 代码
```java
class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int row = matrix.length, col = matrix[0].length, max = Integer.MIN_VALUE;

        for (int i = 0; i < col; i++) {
            int[] sum = new int[row];
            for (int j = i; j < col; j++) {
                for (int l = 0; l < row; l++) {
                    sum[l] += matrix[l][j];
                }

                max = Math.max(max, dpMax(sum, k));
            }
        }

        return max;
    }

    private int dpMax(int[] sum, int k) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < sum.length; i++) {
            int count = 0;
            for (int j = i; j < sum.length; j++) {
                count += sum[j];

                if (count <= k && count > max) {
                    max = count;
                }
            }
        }

        return max;
    }
}
```
## 解法三
### 思路
优化解法三的dpMax函数
- 在求最大连续子序列和的时候，如果没有k的限制，那在遍历数组的时候，就确保每次要累加的前一个dp值是正数
    - 如果是正数，就继续累加
    - 如果不是正数，那就重新从当前这个前缀和开始累加
- 但是因为有了k这个限制，所以当按照上面的方法求得最大值时，如果这个值<=k，那么这个值就是正确答案，但是如果>k，那么就需要重新用解法二的暴力方法求解了
### 代码
```java
class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int row = matrix.length, col = matrix[0].length, max = Integer.MIN_VALUE;

        for (int i = 0; i < col; i++) {
            int[] sum = new int[row];
            for (int j = i; j < col; j++) {
                for (int l = 0; l < row; l++) {
                    sum[l] += matrix[l][j];
                }

                max = Math.max(max, dpMax(sum, k));
            }
        }

        return max;
    }

    private int dpMax(int[] sum, int k) {
        int rollSum = sum[0], max = rollSum;
        for (int i = 1; i < sum.length; i++) {
            if (rollSum > 0) {
                rollSum += sum[i];
            } else {
                rollSum = sum[i];
            }
            
            if (rollSum > max) {
                max = rollSum;
            }
        }
        
        if (max <= k) {
            return max;
        }

        max = Integer.MIN_VALUE;
        for (int i = 0; i < sum.length; i++) {
            int count = 0;
            for (int j = i; j < sum.length; j++) {
                count += sum[j];

                if (count == k) {
                    return k;
                }
                
                if (count < k && count > max) {
                    max = count;
                }
            }
        }

        return max;
    }
}
```
# [LeetCode_364_加权嵌套序列和II](https://leetcode-cn.com/problems/nested-list-weight-sum-ii/)
## 解法
### 思路
bfs
### 代码
```java
class Solution {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        Queue<NestedInteger> queue = new ArrayDeque<>();
        for (NestedInteger nestedInteger : nestedList) {
            queue.offer(nestedInteger);
        }
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            int count = queue.size();
            int sum = 0;
            while (count-- > 0) {
                NestedInteger nestedInteger = queue.poll();
                if (nestedInteger == null) {
                    continue;
                }
                
                if (nestedInteger.isInteger()) {
                    sum += nestedInteger.getInteger();
                    continue;
                }
                
                List<NestedInteger> nestedIntegers = nestedInteger.getList();
                for (NestedInteger element : nestedIntegers) {
                    queue.offer(element);
                }
            }
            
            list.add(sum);
        }

        int ans = 0;
        for (int i = 0; i < list.size(); i++) {
            ans += list.get(i) * (list.size() - i);
        }
        return ans;
    }
}
```
# [LeetCode_366_寻找二叉树的叶子节点](https://leetcode-cn.com/problems/find-leaves-of-binary-tree/)
## 解法
### 思路
- 循环dfs，dfs中将叶子节点置空并将值放入list中
- 循环直至root为null
- dfs时做的是前序遍历
### 代码
```java
class Solution {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        
        while (root != null) {
            List<Integer> list = new ArrayList<>();
            root = preOrder(root, list);
            ans.add(list);
        }
        
        return ans;
    }
    
    private TreeNode preOrder(TreeNode node, List<Integer> list) {
        if (node == null) {
            return null;
        }
        
        if (node.left == null && node.right == null) {
            list.add(node.val);
            return null;
        }
        
        node.left = preOrder(node.left, list);
        node.right = preOrder(node.right, list);
        return node;
    }
}
```