# [LeetCode_306_累加数](https://leetcode-cn.com/problems/additive-number/)
## 解法
### 思路
回溯+减枝+高精度运算
### 代码
```java
class Solution {
    private String num;
    private int n;
    private final List<List<Integer>> list = new ArrayList<>();
    public boolean isAdditiveNumber(String num) {
        this.num = num;
        this.n = num.length();
        return backTrack(0);
    }

    private boolean backTrack(int index) {
        int size = list.size();
        if (index == n) {
            return list.size() >= 3;
        }

        LinkedList<Integer> cur = new LinkedList<>();
        int max = num.charAt(index) == '0' ? index + 1 : n;

        for (int i = index; i < max; i++) {
            cur.addFirst(num.charAt(i) - '0');
            if (size < 2 || check(list.get(size - 1), list.get(size - 2), cur)) {
                list.add(cur);
                if (backTrack(i + 1)) {
                    return true;
                }
                list.remove(list.size() - 1);
            }
        }

        return false;
    }

    private boolean check(List<Integer> x, List<Integer> y, List<Integer> cur) {
        int num = 0;
        List<Integer> sum = new ArrayList<>();
        for (int i = 0; i < x.size() || i < y.size(); i++) {
            if (i < x.size()) {
                num += x.get(i);
            }

            if (i < y.size()) {
                num += y.get(i);
            }

            sum.add(num % 10);
            num /= 10;
        }
        
        if (num > 0) {
            sum.add(num);
        }

        boolean result = sum.size() == cur.size();

        for (int i = 0; i < sum.size() && result; i++) {
            if (!Objects.equals(sum.get(i), cur.get(i))) {
                result = false;
                break;
            }
        }

        return result;
    }
}
```
# [LeetCode_2037_使每位学生都有座位的最少移动次数](https://leetcode-cn.com/problems/minimum-number-of-moves-to-seat-everyone/)
## 解法
### 思路
- 分别对两个数组排序
- 遍历两个数组，累加差值
### 代码
```java
class Solution {
    public int minMovesToSeat(int[] seats, int[] students) {
        Arrays.sort(seats);
        Arrays.sort(students);
        int sum = 0;
        for (int i = 0; i < seats.length; i++) {
            sum += Math.abs(seats[i] - students[i]);
        }
        return sum;
    }
}
```
## 解法二
### 思路
自己实现排序
### 代码
```java
class Solution {
    public int minMovesToSeat(int[] seats, int[] students) {
        sort(seats);
        sort(students);
        int sum = 0;
        for (int i = 0; i < seats.length; i++) {
            sum += Math.abs(seats[i] - students[i]);
        }

        return sum;
    }

    private void sort(int[] arr) {
        doSort(0, arr.length - 1, arr);
    }

    private void doSort(int head, int tail, int[] arr) {
        if (head >= tail) {
            return;
        }

        int pivot = partition(head, tail, arr);

        doSort(head, pivot - 1, arr);
        doSort(pivot + 1, tail, arr);
    }

    private int partition(int head, int tail, int[] arr) {
        int num = arr[head];
        while (head < tail) {
            while (head < tail && arr[tail] >= num) {
                tail--;
            }

            arr[head] = arr[tail];

            while (head < tail && arr[head] <= num) {
                head++;
            }

            arr[tail] = arr[head];
        }

        arr[head] = num;
        return head;
    }
}
```
# [LeetCode_2042_检查句子中的数字是否递增](https://leetcode-cn.com/problems/check-if-numbers-are-ascending-in-a-sentence/)
## 解法
### 思路
遍历模拟
### 代码
```java
class Solution {
    public boolean areNumbersAscending(String s) {
        int pre = 0;
        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            if (!isDigit(cs[i])) {
                continue;
            }
            
            int num = 0;
            while (i < cs.length && isDigit(cs[i])) {
                num = num * 10 + (cs[i] - '0');
                i++;
            }
            
            if (num <= pre) {
                return false;
            }

            pre = num;
        }
        
        return true;
    }
    
    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }
}
```
# [LeetCode_1036_逃离大迷宫](https://leetcode-cn.com/problems/escape-a-large-maze/)
## 解法
### 思路
bfs+记忆化+减枝
- 减枝逻辑参考：[题解](https://leetcode-cn.com/problems/escape-a-large-maze/solution/gong-shui-san-xie-bfs-gei-ding-zhang-ai-8w63o/)
### 代码
```java
class Solution {
    private long base = 131;
    private int[][] dirs = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    private int max;
    private Set<Long> set = new HashSet<>();
    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        for (int[] block : blocked) {
            set.add(block[0] * base + block[1]);
        }
        int n = blocked.length;
        max = n * (n - 1) / 2;
        return dfs(source, target) && dfs(target, source);
    }

    private boolean dfs(int[] a, int[] b) {
        Set<Long> memo = new HashSet<>();
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(a);
        while (!queue.isEmpty() && memo.size() <= max) {
            int[] arr = queue.poll();
            int x = arr[0], y = arr[1];
            if (x == b[0] && y == b[1]) {
                return true;
            }

            for (int[] dir : dirs) {
                int nx = dir[0] + arr[0], ny = dir[1] + arr[1];
                if (nx < 0 || nx >= 1000000 || ny < 0 || ny >= 1000000) {
                    continue;
                }

                long hash = nx * base + ny;
                if (set.contains(hash) || memo.contains(hash)) {
                    continue;
                }

                memo.add(hash);
                queue.add(new int[]{nx, ny});
            }
        }

        return memo.size() > max;
    }
}
```
# [LeetCode_2047_句子中的有效单词数](https://leetcode-cn.com/problems/number-of-valid-words-in-a-sentence/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public int countValidWords(String sentence) {
        char[] cs = sentence.toCharArray();
        int count = 0;
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == ' ') {
                continue;
            }

            int start = i;
            while (i < cs.length && cs[i] != ' ') {
                i++;
            }
            int end = i - 1;

            if (check(cs, start, end)) {
                count++;
            }
        }
        
        return count;
    }

    private boolean check(char[] cs, int start, int end) {
        if (cs[start] == '-' || cs[end] == '-') {
            return false;
        }

        int count = 0, count2 = 0;

        for (int i = start; i <= end; i++) {
            char c = cs[i];
            if (c >= 'a' && c <= 'z') {
                continue;
            }

            if (c == '-') {
                count++;

                char pre = cs[i - 1], next = cs[i + 1];
                if (pre < 'a' || pre > 'z' || next < 'a' || next > 'z') {
                    return false;
                }

                if (count > 1) {
                    return false;
                }
                continue;
            }

            if (c == '!' || c == '.' || c == ',') {
                if (i != end) {
                    return false;
                }

                count2++;

                if(count2 > 1) {
                    return false;
                }

                continue;
            }

            return false;
        }

        return true;
    }
}
```
# [LeetCode_2053_数组中第K个独一无二的字符串](https://leetcode-cn.com/problems/kth-distinct-string-in-an-array/)
## 解法
### 思路
- 遍历字符串数组，使用map记录当前字符串的出现个数
- 再次遍历字符串数组，判断当前字符串是否是独一无二的，并根据k找到指定的字符串
### 代码
```java
class Solution {
    public String kthDistinct(String[] arr, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String s : arr) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        
        int count = 0;
        for (String s : arr) {
            if (map.get(s) == 1) {
                count++;
            }
            
            if (count == k) {
                return s;
            }
        }
        
        return "";
    }
}
```
## 解法二
### 思路
使用2个set来替代解法一中的map
### 代码
```java
class Solution {
    public String kthDistinct(String[] arr, int k) {
        Set<String> set = new HashSet<>(), notOnlyOne = new HashSet<>();
        for (String s : arr) {
            if (!set.add(s)) {
                notOnlyOne.add(s);
            }
        }

        int count = 0;
        for (String s : arr) {
            if (notOnlyOne.contains(s)) {
                continue;
            }
            
            if (k == ++count) {
                return s;
            }
        }
        
        return "";
    }
}
```
# [LeetCode_334_递增的三元子序列](https://leetcode-cn.com/problems/increasing-triplet-subsequence/)
## 失败解法
### 原因
超时，O(N^2)
### 思路
最长上升子序列
- 维护一个数组arr，arr中记录原数组中以坐标i元素为结尾的最长子序列长度
- 维护这个arr的过程，就是遍历数组，然后将当前元素与前置元素比较，并判断当前最长序列值的过程
### 代码
```java
class Solution {
    public boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int max = 1;
        for (int i = 1; i < n; i++) {
            int curMax = dp[i];
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    curMax = Math.max(curMax, dp[j] + 1);
                }
            }
            dp[i] = curMax;
            max = Math.max(curMax, max);
            if (max >= 3) {
                return true;
            }
        }

        return false;
    }
}
```
## 解法
### 思路
- 在解法一的基础上，增加一个数组f，用来记录所有对应下标长度的元素，最小值是多少
- 根据题意可以推测输出，这个数组f中的元素也一定是保持单调递增的，原因是
  - 假设，有`i < j`，且`f[i] == f[j] = x`，那么如果将j去除后半部分的元素，使之长度与i相等，那么`f[j]`的值必然小于x，则这种情况不成立
  - 同样的，如果`f[i] > f[j] = x`，那么缩短j之后，必然也能找到一个新的值更新`f[i]`，所以也不成立
- 遍历数组，获取到待确认的数值`nums[i]`
- 从1到当前坐标+1（代表字符串长度）的区间中做二分查找，查找的对象是f数组
- 二分查找的过程就是确定，比当前值小的元素中最大的元素的位置，这个位置就是长度
- 如果找到大于3的值就返回true，否则遍历结束，返回false
### 代码
```java
class Solution {
    public boolean increasingTriplet(int[] nums) {
        int n = nums.length, max = 1;
        int[] f = new int[n + 1];
        Arrays.fill(f, Integer.MAX_VALUE);
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int l = 1, r = i + 1;
            while (l < r) {
                int mid = l + r >> 1;
                if (f[mid] >= num) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            
            f[r] = num;
            max = Math.max(max, r);
            
            if (max >= 3) {
                return true;
            }
        }
        
        return false;
    }
}
```
# [LeetCode_747_至少是其他数字两倍的最大数](https://leetcode-cn.com/problems/largest-number-at-least-twice-of-others/)
## 解法
### 思路
- 遍历维护最大和第二大元素坐标
- 遍历结束判断最大值是否大于等于第二大值的2倍，如果是就返回坐标，否则返回-1
### 代码
```java
class Solution {
    public int dominantIndex(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }

        int a = nums[0], b = nums[1], maxI, secondI;
        if (a >= b) {
            maxI = 0;
            secondI = 1;
        } else {
            maxI = 1;
            secondI = 0;
        }
        for (int i = 2; i < nums.length; i++) {
            int num = nums[i], max = nums[maxI], second = nums[secondI];
            if (num >= max) {
                secondI = maxI;
                maxI = i;
            } else if (num >= second) {
                secondI = i;
            }
        }

        return nums[maxI] >= 2 * nums[secondI] ? maxI : -1;
    }
}
```
# [LeetCode_2057_值相等的最小索引](https://leetcode-cn.com/problems/smallest-index-with-equal-value/)
## 解法
### 思路
遍历并判断
### 代码
```java
class Solution {
    public int smallestEqual(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i % 10 == nums[i]) {
                return i;
            }
        }
        
        return -1;
    }
}
```
# [LeetCode_2062_统计字符串中的元音子字符串](https://leetcode-cn.com/problems/count-vowel-substrings-of-a-string/)
## 解法
### 思路
- 外层遍历字符串，找到元音子字符串的起始字符
- 内层循环确定从哪个字符开始成为有效的子字符串，然后持续累加到不是元音为止
- 累加计数值，最终返回
### 代码
```java
class Solution {
    public int countVowelSubstrings(String word) {
        Set<Character> memo = new HashSet<>();
        memo.add('a');
        memo.add('e');
        memo.add('i');
        memo.add('o');
        memo.add('u');
        int n = word.length();
        
        int target = 1 | (1 << ('e' - 'a')) | (1 << ('i' - 'a')) | (1 << ('o' - 'a')) | (1 << ('u' - 'a')),
            sum = 0;
        for (int i = 0; i < n; i++) {
            if (!memo.contains(word.charAt(i))) {
                continue;
            }
            
            int num = 0, count = 0;
            for (int j = i; j < n; j++) {
                if (!memo.contains(word.charAt(j))) {
                    break;
                }
                
                num |= (1 << (word.charAt(j) - 'a'));
                
                if (num == target) {
                    count++;
                }
            }
            
            sum += count;
        }
        
        return sum;
    }
}
```
# [LeetCode_373_查找和最小的K对数字](https://leetcode-cn.com/problems/find-k-pairs-with-smallest-sums/)
## 解法
### 思路
优先级队列
- 和最小的组合一定是`nums1[0] + nums2[1]`
- 初始化一个优先级队列，该队列存储结果数组，它的排序规则就是`nums1[idx1[0]] + nums2[idx1[1]] - nums1[idx2[0]] - nums2[idx2[1]]`
- 然后将部分的数组放入到优先级队列中，例如遍历nums1[]数组，将nums1的所有下标和0组合成数组放入优先级队列
- 然后遍历优先级队列，每次拿出一个最小的数组后，在这个数组基础上，第二个元素+1，然后放入队列中。这样可以避免通过嵌套循环产生的重复数据
- 然后遍历到k个之后返回即可
### 代码
```java
class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((x, y) -> nums1[x[0]] + nums2[x[1]] - nums1[y[0]] - nums2[y[1]]);
        int len1 = nums1.length, len2 = nums2.length;

        for (int i = 0; i < Math.min(len1, k); i++) {
            queue.offer(new int[]{i, 0});
        }

        List<List<Integer>> ans = new ArrayList<>();
        int index = 0;
        while (!queue.isEmpty() && index++ < k) {
            int[] idx = queue.poll();

            List<Integer> list = new ArrayList<>();
            list.add(nums1[idx[0]]);
            list.add(nums2[idx[1]]);
            ans.add(list);

            if (idx[1] + 1 < len2) {
                queue.offer(new int[]{idx[0], idx[1] + 1});
            }
        }

        return ans;
    }
}
```