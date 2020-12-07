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
# [LeetCode_368_最大正数子集](https://leetcode-cn.com/problems/largest-divisible-subset/)
## 解法
### 思路
- 如果将整个数组排序，那么某一个大数如果能被某一个最大值小的序列的最大值整除，那么这个序列的所有数都能整除这个大数，所以可以做动态规划
- dp[i]：以坐标i为最大数的序列
- 状态转移方程：`nums[i] % nums[k] == 0 && dp[k] + 1 > dp[i],dp[i] = dp[k] + 1，k ∈[0, i)`
- base case：`dp[0] = {nums[0]}`
- 过程：
    - 排序数组
    - 初始化dp
    - 遍历数组，从坐标1开始遍历，确定当前需要被整除的数值`nums[i]`
    - 判断：`nums[i] % nums[k] == 0 && dp[k] + 1 > dp[i]`
        - 如果是，就更新`dp[i]`
    - 同时判断当前dp[i]是否是最长序列，如果是，就更新暂存的结果序列
### 代码
```java
class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return new ArrayList<>();
        }

        Arrays.sort(nums);
        List[] dp = new List[len];
        dp[0] = new ArrayList();
        dp[0].add(nums[0]);
        
        List<Integer> ans = dp[0];
        for (int i = 1; i < len; i++) {
            dp[i] = new ArrayList();
            dp[i].add(nums[i]);
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && dp[i].size() < dp[j].size() + 1) {
                    dp[i] = new ArrayList(dp[j]);
                    dp[i].add(nums[i]);
                }
            }
            
            if (ans.size() < dp[i].size()) {
                ans = dp[i];
            }
        }
        
        return ans;
    }
}
```
# LeetCode_369_给单链表加一
## 解法
### 思路
递归
### 代码
```java
class Solution {
    public ListNode plusOne(ListNode head) {
        if (head == null) {
            return null;
        }
        
        int carry = recurse(head);
        if (carry == 1) {
            ListNode node = new ListNode(1);
            node.next = head;
            return node;
        }
        
        return head;
    }
    
    private int recurse(ListNode node) {
        if (node.next == null) {
            int num = node.val + 1;
            if (num > 9) {
                node.val = 0;
                return 1;
            } else {
                node.val = num;
                return 0;
            }
        }
        
        int carry = recurse(node.next);
        int num = carry + node.val;
        if (num > 9) {
            node.val = 0;
            return 1;
        }
        
        node.val = num;
        return 0;
    }
}
```
# [LeetCode_370_区间加法](https://leetcode-cn.com/problems/range-addition/)
## 解法
### 思路
暴力法
### 代码
```java
class Solution {
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] ans = new int[length];
        for (int[] update : updates) {
            int start = update[0], end = update[1];
            for (int i = start; i <= end; i++) {
                ans[i] += update[2];
            }
        }
        return ans;
    }
}
```
## 解法二
### 思路
- 初始化一个数组，用来记录update所对应的变化
- 在记录变化的时候，只需要记录start一个点的变化值，因为在实际生成结果数组的时候，生成的方式是`ans[i + 1] = ans[i]`，这样如果i是某个update的区间的中间一个元素，我就只需要关心i-1坐标的值就可以了
- 那么update区间的结束则可以通过`ans[i] = -update[2]`，这就代表当前区间结束了，区间结尾元素的后一个元素的变化是`-update[2]`
### 代码
```java
class Solution {
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] ans = new int[length];
        for (int[] update : updates) {
            ans[update[0]] += update[2];

            if (update[1] < length - 1) {
                ans[update[1] + 1] -= update[2];
            }
        }

        for (int i = 1; i < length; i++) {
            ans[i] += ans[i - 1];
        }
        
        return ans;
    }
}
```
# [超级次方](https://leetcode-cn.com/problems/super-pow/)
## 解法
### 思路
- 因为`a^[x1,x2,x3,x4] = a^x4 * a^[x1,x2,x3]^10`，所以可以使用递归来计算，退出条件就是代表指数的数组长度为0的时候，返回1
- `(a*b)%c = ((a%c)*(b%c))%c`，推演的公式：
    - 假设`a = x1 * c + y1`，`b = x2 * c + y2`，所以`ab = x1x2c^2 + x1y2c + x2y1c + y1y2`
    - 所以`ab % c = y1y2 % c`
    - 因为`a % c = y1`，`b % c = y2`，所以`(a*b)%c = ((a%c)*(b%c))%c`
- 于是通过如上的推演，在满足题目要求，对结果做取模时候，可以在每个因数上进行取模，然后在乘积上再取模，从而获得结果
### 代码
```java
class Solution {
    private final int base = 1337;
    
    public int superPow(int a, int[] b) {
        if (b.length == 0) {
            return 1;
        }
        
        int last = b[b.length - 1];
        int part1 = pow(a, last), part2 = pow(superPow(a, Arrays.copyOfRange(b, 0, b.length - 1)), 10);
        return (part1 * part2) % base;
    }
    
    private int pow(int a, int b) {
        if (b == 0) {
            return 1;
        }
        
        a %= base;
        int ans = 1;
        for (int i = 0; i < b; i++) {
            ans *= a;
            ans %= base;
        }
        
        return ans;
    }
}
```
## 解法二
### 思路
在上一个解法的基础上，在计算幂时候可以做提速：
- 如果指数是奇数：`a^b = a^(b - 1) * a`
- 如果指数是偶数：`a^b = a^(b/2) * a^(b/2)`，`c = a^(b/2)`，`a^b = c*c`如果只计算c的话，整个过程递归就是一个O(logN)的时间复杂度的计算
### 代码
```java
class Solution {
    private final int base = 1337;
    
    public int superPow(int a, int[] b) {
        if (b.length == 0) {
            return 1;
        }
        
        int last = b[b.length - 1];
        int part1 = pow(a, last), part2 = pow(superPow(a, Arrays.copyOfRange(b, 0, b.length - 1)), 10);
        return (part1 * part2) % base;
    }
    
    private int pow(int a, int b) {
        if (b == 0) {
            return 1;
        }
        
        a %= base;
        
        if (b % 2 == 1) {
            return (a * (pow(a, b - 1))) % base;
        } else {
            int c = pow(a, b / 2);
            return (c * c) % base;
        }
    }
}
```
# [LeetCode_659_分割数组为连续子序列](https://leetcode-cn.com/problems/split-array-into-consecutive-subsequences/)
## 解法
### 思路
- 遍历序列，在遍历过程中，将连续的元素作为一个序列，累加计数
- 然后将序列的结尾数字作为key，累加的序列长度作为value保存，且判断累加的时候，肯定是判断当前元素的前一个元素存不存在，它的序列有多长，如果连起来以后，上一个数和它对应的序列长度就没用了，那就删掉
- 遍历的时候，肯定出现重复的数字，那么同一个数就需要保存多个序列的长度，而之后的数如果要连接当前这个数的序列，在选择序列的时候，应该找到的是短的那个序列，那么value就应该存一个小顶堆，将序列长度放在小顶堆里
- 等整个序列被遍历完以后，在遍历下map中的所有entry，查一下有没有小于3的序列长度，有的话就是false，否则是true
### 代码
```java
class Solution {
    public boolean isPossible(int[] nums) {
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, new PriorityQueue<>());
            }

            if (map.containsKey(num - 1)) {
                Integer preLen = map.get(num - 1).poll();
                map.get(num).offer(preLen + 1);

                if (map.get(num - 1).isEmpty()) {
                    map.remove(num - 1);
                }
            } else {
                map.get(num).offer(1);
            }
        }

        for (Map.Entry<Integer, PriorityQueue<Integer>> entry : map.entrySet()) {
            PriorityQueue<Integer> queue = entry.getValue();
            for (Integer num : queue) {
                if (num < 3) {
                    return false;
                }
            }
        }

        return true;
    }
}
```
## 解法二
### 思路
- 使用2个map，分别统计：
    - `countMap`：数字出现的个数
    - `endMap`：数字在序列中作为结尾出现的个数
- 过程：
    - 遍历`nums`序列，在`countMap`中统计数字出现的个数
    - 再遍历`nums`序列，根据遍历到的数字，在`endMap`中查找是否存在比当前元素小1的数字，也就是找当前遍历到的序列中是否存在与当前元素紧接着的序列
        - 如果有：将该序列的结尾数字对应的个数减1，代表有1个这样的序列和当前元素连接，这个序列的结尾数字变成当前数字，所以在`endMap`中将当前元素作为key放入map中，并在对应value中加1
        - 如果没有：说明当前元素是单独的，那就检查`countMap`中是否存在和当前元素紧接着的2个数字，如果不存在，那就直接返回false，否则，就将这连续的3个数字的出现个数减1，并将3个数字的最后一个数字作为`endMap`中又一个序列的结尾数字，放入`endMap`中并计数
    - `nums`序列遍历结束后，直接返回true，代表所有元素都已经遍历完，且都可以和其他元素组成序列
    - 在第二次遍历`nums`的过程中，需要先检查`countMap`中当前元素的出现个数，如果是0，代表当前元素在之前的处理`endMap`时已经减去，可以直接跳过
### 代码
```java
class Solution {
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>(), endMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        
        for (int num : nums) {
            if (countMap.get(num) == 0) {
                continue;
            }
            
            int preEndCount = endMap.getOrDefault(num - 1, 0);
            if (preEndCount > 0) {
                endMap.put(num, endMap.getOrDefault(num, 0) + 1);
                endMap.put(num - 1, preEndCount - 1);
                countMap.put(num, countMap.get(num) - 1);
            } else {
                int secondCount = countMap.getOrDefault(num + 1, 0),
                    thirdCount = countMap.getOrDefault(num + 2, 0);
                
                if (secondCount > 0 && thirdCount > 0) {
                    countMap.put(num, countMap.get(num) - 1);
                    countMap.put(num + 1, secondCount - 1);
                    countMap.put(num + 2, thirdCount - 1);
                    endMap.put(num + 2, endMap.getOrDefault(num + 2, 0) + 1);
                } else {
                    return false;
                }
            }
        }
        
        return true;
    }
}
```