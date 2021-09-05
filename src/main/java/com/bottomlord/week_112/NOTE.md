# [LeetCode_528_权重随机选择](https://leetcode-cn.com/problems/random-pick-with-weight/)
## 失败解法
### 原因
超出内存限制
### 思路
- 初始化一个list，w数组中的坐标对应什么值，就讲坐标放入list中多少个
- pick的时候就生成list长度的随机数，然后根据随机值到list中找到对应的坐标
### 代码
```java
class Solution {
    private List<Integer> list;
    public Solution(int[] w) {
        list = new ArrayList<>();
        for (int i = 0; i < w.length; i++) {
            int count = w[i];
            while (count-- > 0) {
                list.add(i);
            }
        }
    }

    public int pickIndex() {
        return list.get((int) (Math.random() * list.size()) + 1);
    }
}
```
## 解法
### 思路
- 根据w求前缀和
- 根据前缀和最后一个元素的值(也就是失败解法中list的长度)，算出一个随机值
- 根据随机值到前缀和数组中找到第一个大于等于该值的坐标，返回该坐标
### 代码
```java
class Solution {
    private int[] sum;
    private int total;
    public Solution(int[] w) {
        sum = new int[w.length];
        sum[0] = w[0];
        for (int i = 1; i < w.length; i++) {
            sum[i] = sum[i - 1] + w[i];
        }
        
        total = sum[sum.length - 1];
    }

    public int pickIndex() {
        int num = (int) (Math.random() * total) + 1;
        for (int i = 0; i < sum.length; i++) {
            if (sum[i] >= num) {
                return i;
            }
        }
        return 0;
    }
}
```
## 解法二
### 思路
在解法一的基础上，将一次遍历搜索改为二分查找，降低时间复杂度
### 代码
```java
class Solution {
    private int[] sum;
    private int total;
    public Solution(int[] w) {
        sum = new int[w.length];
        sum[0] = w[0];
        for (int i = 1; i < w.length; i++) {
            sum[i] = sum[i - 1] + w[i];
        }

        total = sum[sum.length - 1];
    }

    public int pickIndex() {
        int num = (int) (Math.random() * total) + 1;
        return binarySearch(num);
    }
    
    private int binarySearch(int num) {
        int head = 0, tail = sum.length - 1;
        while (head < tail) {
            int mid = head + (tail - head) / 2;
            if (sum[mid] >= num) {
                tail = mid;
            } else {
                head = mid + 1;
            }
        }

        return head;
    }
}
```
# [LeetCode_1560_圆形赛道上经过次数最多的扇区](https://leetcode-cn.com/problems/most-visited-sector-in-a-circular-track/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public List<Integer> mostVisited(int n, int[] rounds) {
        int[] bucket = new int[n + 1];
        bucket[rounds[0]]++;
        for (int i = 0; i < rounds.length - 1; i++) {
            int index = rounds[i], target = rounds[i + 1];
            index++;
            if (index > n) {
                index = 1;
            }
            while (index != target) {
                bucket[index++]++;
                if (index > n) {
                    index = 1;
                }
            }
            bucket[target]++;
        }
        
        int max = Arrays.stream(bucket).max().getAsInt();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] == max) {
                list.add(i);
            }
        }
        return list;
    }
}
```
## 解法二
### 思路
- 无论中间途径了圈，最终都不影响多跑的那几个扇区，也就是路径起始和结尾中间途径的扇区
- 如果起点小于等于终点，就直接遍历起点到终点，返回list
- 如果大于终点，那么因为要返回升序序列，所以就是先算1到终点，再算起点到n，加到list里返回
### 代码
```java
class Solution {
    public List<Integer> mostVisited(int n, int[] rounds) {
        int left = rounds[0], right = rounds[rounds.length - 1];

        List<Integer> ans = new ArrayList<>();
        if (left <= right) {
            for (int i = left; i <= right; i++) {
                ans.add(i);
            }
            return ans;
        }
        
        for (int i = 1; i <= right; i++) {
            ans.add(i);
        }

        for (int i = left; i <= n; i++) {
            ans.add(i);
        }
        
        return ans;
    }
}
```
# [LeetCode_1109_航班预订统计](https://leetcode-cn.com/problems/corporate-flight-bookings/)
## 解法
### 思路
- 差分数组
- 当某个区间[l,r]的元素要增加，那么差分数组中arr[l]的值相应增加, arr[r + 1]的值相应减少
- 然后从数组的起始位置开始，根据差分的值，推出整个数组的值，和求前缀和数组的计算方式相同
### 代码
```java
class Solution {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] nums = new int[n];
        for (int[] booking : bookings) {
            nums[booking[0] - 1] += booking[2];
            if (booking[1] < n) {
                nums[booking[1]] -= booking[2];
            }
        }
        
        for (int i = 1; i < n; i++) {
            nums[i] += nums[i - 1];
        }
        
        return nums;
    }
}
```
# [LeetCode_1566_重复至少K次且长度为M的模式](https://leetcode-cn.com/problems/detect-pattern-of-length-m-repeated-k-or-more-times/)
## 解法
### 思路
暴力模拟
- 外层循环确定k个m长度的模式串的起始坐标，所以截止位置就是i + m * k <= n
- 内部确定整个模式串中其他k-1个m长度模式子串，截止位置就是i + m * k的位置
- 在内层循环的时候，判断j对应j-m元素是否相等，如果相等继续循环，直到返回循环结束，否则返回false
### 代码
```java
class Solution {
    public boolean containsPattern(int[] arr, int m, int k) {
        int n = arr.length;
        for (int i = 0; i + m * k <= n; i++) {
            boolean flag = true;
            for (int j = i + m; j < i + m * k; j++) {
                if (arr[j] != arr[j - m]) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                return true;
            }
        }

        return false;
    }
}
```
# [LeetCode_165_比较版本号](https://leetcode-cn.com/problems/compare-version-numbers/)
## 解法
### 思路
- 切分字符串生成2个字符串数组
- 按照最长的那个字符串数组的长度进行循环遍历
- 将循环下标对应的数组字符串转换成数字
- 如果下标超过了数组应有的最大坐标，则这个转换值定为0
- 循环过程中，2个转换后的值，如果有大小，就返回对应的1或-1
- 循环正常结束，则说明2个版本号大小一致，返回0
### 代码
```java
class Solution {
    public int compareVersion(String version1, String version2) {
        String[] vs1 = version1.split("\\."), vs2 = version2.split("\\.");
        int len = Math.max(vs1.length, vs2.length);
        
        for (int i = 0; i < len; i++) {
            int num1 = i >= vs1.length ? 0 : Integer.parseInt(vs1[i]),
                num2 = i >= vs2.length ? 0 : Integer.parseInt(vs2[i]);
            
            if (num1 < num2) {
                return -1;
            } else if (num1 > num2) {
                return 1;
            }
        }
        
        return 0;
    }
}
```
# [LeetCode_1572_矩阵对角线元素的和](https://leetcode-cn.com/problems/matrix-diagonal-sum/)
## 解法
### 思路
模拟：遍历二维数组，累加对应的值，需要注意交叉点的特判
### 代码
```java
class Solution {
    public int diagonalSum(int[][] mat) {
        int n = mat.length, sum = 0;
        for (int i = 0; i < n; i++) {
            sum += mat[i][i] + mat[i][n - i - 1];
            
            if (i == n - i - 1) {
                sum -= mat[i][i];
            }
        }
        
        return sum;    
    }
}
```
# [LeetCode_1576_替换所有的问号](https://leetcode-cn.com/problems/replace-all-s-to-avoid-consecutive-repeating-characters/)
## 解法
### 思路
模拟：遍历字符串和前后字符比较，然后循环26个字母，看哪一个同时与前后不同，就替换为该字母，直到循环结束，需要特判头尾时候的情况
### 代码
```java
class Solution {
    public String modifyString(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();
        for (int i = 0; i < n; i++) {
            if (cs[i] != '?') {
                continue;
            }

            char pre = i - 1 >= 0 ? cs[i - 1] : ' ';
            char next = i + 1 < n ? cs[i + 1] : ' ';
            
            for (int j = 0; j < 26; j++) {
                char c = (char) ('a' + j);
                if (c != pre && c != next) {
                    cs[i] = c;
                    break;
                }
            }
        }
        
        return new String(cs);
    }
}
```
# [LeetCode_offer22_链表中倒数第K个节点](https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/
## 解法
### 思路
- 遍历计算链表长度
- 计算倒数第K个的正数表达，遍历找到该节点返回
### 代码
```java
class Solution {
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode node = head;
        int n = 0;
        while (node != null) {
            n++;
            node = node.next;
        }

        int index = n - k;
        node = head;
        while (index-- > 0) {
            node = node.next;
        }
        
        return node;
    }
}
```
# [LeetCode_582_二进制矩阵中的特殊位置](https://leetcode-cn.com/problems/special-positions-in-a-binary-matrix/)
## 解法
### 思路
- 遍历二维数组，分别统计行和列的元素总和，并分别放入2个一维数组中
- 遍历二维数组，依赖2个一维数组判断是否有符合情况的单元格，即行和列的总和是1，且当前是元素也是1，如果有就累加
### 代码
```java
class Solution {
    public int numSpecial(int[][] mat) {
        int r = mat.length, c = mat[0].length;
        int[] row = new int[r], col = new int[c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (mat[i][j] == 1) {
                    row[i]++;
                    col[j]++;
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (row[i] == 1 && col[j] == 1 && mat[i][j] == 1) {
                    ans++;
                }
            }
        }

        return ans;
    }
}
```
# [LeetCode_1592_重新排列单词间的空格](https://leetcode-cn.com/problems/rearrange-spaces-between-words/)
## 解法
### 思路
- 计算text长度，计算text中单词的个数，通过这两个值获取到空格的个数
- 基于单词个数，获得间隔个数，然后找到最大乘数作为间隔的空格个数，将余数作为结尾的空格个数
### 代码
```java
class Solution {
    public String reorderSpaces(String text) {
        String[] splits = text.split(" ");
        List<String> list = Arrays.stream(splits)
                .filter(str -> !Objects.equals(str, " ") && !Objects.equals("", str))
                .collect(Collectors.toList());
        
        if (list.isEmpty()) {
            return text;
        }
        
        int len = list.stream().map(String::length).reduce(0, Integer::sum);
        int blankTotalLen = text.length() - len;
        
        int blankLen = list.size() == 1 ? 0 : blankTotalLen / (list.size() - 1);
        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            sb.append(str);
            for (int i = 0; i < blankLen && blankTotalLen > 0; i++) {
                sb.append(" ");
                blankTotalLen--;
            }
        }
        
        for (int i = 0; i < blankTotalLen; i++) {
            sb.append(" ");
        }
        
        return sb.toString();
    }
}
```
## 解法二
### 思路
- 不使用split等String的Api，而是通过遍历字符串的方式获取到空格总数和单词
- 再基于单词和空格总数，计算出间隔的空格长度已经剩下需要添加的空格数
- 基于计算出来的长度重新拼接字符串
### 代码
```java
class Solution {
    public String reorderSpaces(String text) {
        int blankNum = 0;
        List<String> words = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                blankNum++;
                continue;
            }

            int start = i;
            while (i < text.length() && text.charAt(i) != ' ') {
                i++;
            }
            words.add(text.substring(start, i--));
        }

        if (words.isEmpty()) {
            return text;
        }
        
        int blankGapLen = words.size() == 1 ? 0 : blankNum / (words.size() - 1);

        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(word);
            if (blankNum >= blankGapLen) {
                for (int j = 0; j < blankGapLen; j++) {
                    sb.append(" ");
                    blankNum--;
                }
            }
        }

        while (blankNum-- > 0) {
            sb.append(" ");
        }

        return sb.toString();
    }
}
```
# [LeetCode_interview_1714_最小k个数](https://leetcode-cn.com/problems/smallest-k-lcci/)
## 解法
### 思路
优先级队列
### 代码
```java
class Solution {
    public int[] smallestK(int[] arr, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int num : arr) {
            queue.offer(num);
        }

        k = Math.min(k, arr.length);
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] =  queue.poll();
        }

        return ans;
    }
}
```
## 解法二
### 思路
快排
### 代码
```java
class Solution {
    public int[] smallestK(int[] arr, int k) {
        sort(arr);
        k = Math.min(k, arr.length);
        int[] ans = new int[k];
        System.arraycopy(arr, 0, ans, 0, k);
        return ans;
    }

    private void sort(int[] arr) {
        doSort(arr, 0, arr.length - 1);
    }

    private void doSort(int[] arr, int head, int tail) {
        if (head >= tail) {
            return;
        }

        int piovt = partition(arr, head, tail);

        doSort(arr, head, piovt - 1);
        doSort(arr, piovt + 1, tail);
    }

    private int partition(int[] arr, int head, int tail) {
        while (head < tail) {
            while (head < tail && arr[head] <= arr[tail]) {
                tail--;
            }
            swap(arr, head, tail);
            
            while (head < tail && arr[head] <= arr[tail]) {
                head++;
            }
            swap(arr, head, tail);
        }
        
        return head;
    }
    
    private void swap(int[] arr, int x, int y) {
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }
}
```
# [LeetCode_offer10-I_斐波那契数列](https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/)
## 解法
### 思路
递归+减枝
### 代码
```java
class Solution {
    public int fib(int n) {
        Map<Integer, Integer> memo = new HashMap<>();
        memo.put(0, 0);
        memo.put(1, 1);
        memo.put(2, 1);
        return doFib(n, memo);
    }

    private int doFib(int n, Map<Integer, Integer> memo) {
        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        memo.put(n - 1, doFib(n - 1, memo));
        memo.put(n - 2, doFib(n - 2, memo));
        return (memo.get(n - 1) + memo.get(n - 2)) % 1000000007;
    }
}
```
## 解法二
### 思路
动态规划
### 代码
```java

```
# [LeetCode_470_用rand7()实现rand10()]()
## 解法
### 思路
- (randX() - 1)*Y + randY() 可以等概率的生成[1, X * Y]范围的随机数
### 代码
```java
class Solution extends SolBase {
public int rand10() {
        int num;
        do {
            num = (rand7()  - 1) * 7 + rand7();
        } while (num > 40);
        
        return 1 + num % 10;
    }
}
```