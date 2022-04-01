# [LeetCode_635_设计日志存储系统](https://leetcode-cn.com/problems/design-log-storage-system/)
## 解法
### 思路
- 使用时间戳对字符串进行等价转义
- 默认使用31天作为一个月的天数
- 然后保存时间戳和id对应的数组放到list里
- put的时候就是转换，然后放入list
- 获取的时候，就是转换，然后遍历list，取出符合的，因为是范围查询，所以可以采用前闭后开的方式，因为end时间如果是2000年，那么2000年的数据都应该包含，所以end的数值要+1，然后采用前闭后开
### 代码
```java
class LogSystem {
    private final int year = 12 * 31 * 24 * 60 * 60,
                    month = 31 * 24 * 60 * 60,
                    day = 24 * 60 * 60,
                    hour = 60 * 60,
                    minute = 60;

    private List<long[]> list;
    private Map<String, Integer> map;
    private String[] template = new String[]{"2000", "1", "1", "0", "0", "0"};
    public LogSystem() {
        list = new ArrayList<>();
        map = new HashMap<>();
        map.put("Year", 0);
        map.put("Month", 1);
        map.put("Day", 2);
        map.put("Hour", 3);
        map.put("Minute", 4);
        map.put("Second", 5);
    }

    public void put(int id, String timestamp) {
        list.add(new long[]{parse(timestamp), (long)id});
    }

    public List<Integer> retrieve(String start, String end, String granularity) {
        long startTime = getByGranularity(start, granularity, false),
                endTime = getByGranularity(end, granularity, true);

        List<Integer> ans = new ArrayList<>();
        for (long[] arr : list) {
            if (arr[0] >= startTime && arr[0] < endTime) {
                ans.add((int) arr[1]);
            }
        }
        
        return ans;
    }

    private long getByGranularity(String time, String granularity, boolean end) {
        String[] factors = time.split(":");
        String yearStr = factors[0], monthStr = factors[1], dayStr = factors[2],
                hourStr = factors[3], minuteStr = factors[4], secondStr = factors[5];

        int level = map.get(granularity);

        String[] origin = Arrays.copyOf(template, template.length);
        int[] cur = new int[]{Integer.parseInt(yearStr), 
                Integer.parseInt(monthStr),
                Integer.parseInt(dayStr),
                Integer.parseInt(hourStr),
                Integer.parseInt(minuteStr),
                Integer.parseInt(secondStr)};
        for (int i = 0; i < template.length; i++) {
            if (level >= i) {
                int num = i == level && end ? cur[i] + 1 : cur[i];
                origin[i] = "" + num;
            }
        }

        return parse(String.join(":", origin));
    }

    private long parse(String time) {
        String[] factors = time.split(":");
        return Long.parseLong(factors[0]) * year +
                Long.parseLong(factors[1]) * month +
                Long.parseLong(factors[2]) * day +
                Long.parseLong(factors[3]) * hour +
                Long.parseLong(factors[4]) * minute +
                Long.parseLong(factors[5]);
    }
}
```
# [LeetCode_2024_考试的最大困扰度](https://leetcode-cn.com/problems/maximize-the-confusion-of-an-exam/)
## 解法
### 思路
滑动窗口
- 通过滑动窗口来确定最大长度，这个方法需要对`T`和`F`的情况做分别的计算
- 滑动窗口要确定窗口的left和right指针
- 初始left和right都是0，然后right递增
- 在遍历的过程中，统计有效长度和不相等字符的个数
- 如果出现不相等字符数大于要求的k，那么就内层循环移动left，直到统计的不相等个数不大于k为止
- 每次遍历过程中都用统计到的长度和暂存的最大值作比较，保留最大值
- 遍历结束后返回暂存的最大值
### 代码
```java
class Solution {
    public int maxConsecutiveAnswers(String answerKey, int k) {
        return Math.max(count(answerKey, 'T', k), count(answerKey, 'F', k));
    }

    private int count(String key, char target, int k) {
        int ans = 0, left = 0, n = key.length(), cur = 0;
        for (int right = 0; right < n; right++) {
            cur += key.charAt(right) != target ? 1 : 0;

            while (cur > k) {
                cur -= key.charAt(left++) != target ? 1 : 0;
            }

            ans = Math.max(right - left + 1, ans);
        }

        return ans;
    }
}
```
# [LeetCode_651_4键键盘](https://leetcode-cn.com/problems/4-keys-keyboard/)
## 解法
### 思路
动态规划：
- dp[i]：第i步能够生成A的最大个数
- 状态转移方程：dp[i] = max(dp[i - 1] + 1, dp[j] * (i - j - 1))
- base case：dp[i] = i
- 过程：
  - 外层遍历初始化base case
  - 内层遍历`j`，然后比较`base case`和`dp[j] * (i - j - 1)`的最大值
  - 遍历结束后返回`dp[n]`
### 代码
```java
class Solution {
    public int maxA(int n) {
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i] = i;
            
            for (int j = 0; j < i - 1; j++) {
                dp[i] = Math.max(dp[j] * (i - j - 1), dp[i]);
            }
        }
        
        return dp[n];
    }
}
```
# [LeetCode_1606_找到处理最多请求的处理器](https://leetcode-cn.com/problems/find-servers-that-handled-most-number-of-requests/)
## 解法
### 思路
优先级队列
- 初始换3个数据结构：
  - 数组request：长度为k，记录每台服务器处理request的个数
  - TreeSet-available：用于记录当前可用的服务器，并通过TreeSet特性找到最接近某个值的数
  - 优先级队列busy：用于记录当前正在处理请求的服务器，存储请求的结束时间和服务器编号，堆顶元素是最小的请求结束时间
- 过程：
  - 初始化request和available
  - 遍历arrival
    - 判断busy是否不为空，如果不为空，就拿堆顶元素和当前时间(arrival[i])做比较，如果元素结束时间不大于当前时间（说明服务器在当前时间已经变得可用），则弹出并放入available中
    - 判断available是否为空
      - 如果为空就放弃请求
      - 如果不为空：
        - 就将当前请求对应的最近的服务器放入busy队列中
        - 在request中对服务器的请求处理数做+1统计
        - 将available中的当前使用server进行去除
  - 循环结束后，统计最大值并返回对应的服务器编号列表
### 代码
```java
class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int[] requests = new int[k];
        TreeSet<Integer> available = new TreeSet<>();
        Queue<int[]> busy = new PriorityQueue<>(Comparator.comparingInt(x -> x[0])) ;

        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        int max = 0;
        for (int i = 0; i < arrival.length; i++) {
            while (!busy.isEmpty() && busy.peek()[0] <= arrival[i]) {
                int[] busyServer = busy.poll();
                available.add(busyServer[1]);
            }

            if (available.isEmpty()) {
                continue;
            }

            Integer server = available.ceiling(i % k);
            if (server == null) {
                server = available.first();
            }
            available.remove(server);

            busy.offer(new int[]{arrival[i] + load[i], server});
            requests[server]++;
            max = Math.max(max, requests[server]);
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < requests.length; i++) {
            if (requests[i] == max) {
                ans.add(i);
            }
        }
        return ans;
    }
}
```
## 解法二
### 思路
- 在解法一的基础上，同样使用优先级队列来代替TreeSet实现的available
- 此时就要解决放回到available队列时候，server要使用大于当前时间i的最小的那个同余数的需求，也就是说新生成的x，在通过x % k计算后，得到的就是server的id
- 使用的公式：`i + (k - (i % k - id))`，公式的含义：
  - i % k，相当于将当前时间缩小到与id一样都小于k的状态
  - i % k - id，相当远算出i缩小后与k之间的距离
  - k - (i % k - id)，相当于算出i缩小后，需要加上多少可以得到下一个大于i且是id同余数的数字
  - i + i + (k - (i % k - id))，相当于求得i之后的那个最小的id同余数
### 代码
```java
class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int[] requests = new int[k];
        Queue<Integer> available = new PriorityQueue<>();
        Queue<int[]> busy = new PriorityQueue<>(Comparator.comparingInt(x -> x[0]));
        for (int i = 0; i < k; i++) {
            available.offer(i);
        }

        int max = 0;
        for (int i = 0; i < arrival.length; i++) {
            int time = arrival[i];

            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                int[] busyServer = busy.poll();
                available.offer(i + (k - (i % k - busyServer[1])) % k);
            }
            
            if (available.isEmpty()) {
                continue;
            }
            
            int server = available.poll() % k;
            requests[server]++;
            busy.offer(new int[]{arrival[i] + load[i], server});
            max = Math.max(requests[server], max);
        }
        
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < requests.length; i++) {
            if (requests[i] == max) {
                ans.add(i);
            }
        }
        return ans;
    }
}
```
# [LeetCode_658_找到K个最接近的元素](https://leetcode-cn.com/problems/find-k-closest-elements/)
## 解法
### 思路
- 拷贝数组
- 遍历原数组，计算出每个元素与x的距离值，并赋值给该元素
- 根据处理过的，记录距离的数组，计算前缀和
- 遍历前缀和数组，根据长度快速查出2个坐标之间距离为k的距离总和，找到最短的那段距离
- 如果找到了就记录起始和结尾坐标
- 遍历结束后，根据记录的起始和结尾坐标，生成list
### 代码
```java
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int[] copy = Arrays.copyOfRange(arr, 0, arr.length);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Math.abs(arr[i] - x);
        }

        int n = arr.length;
        int[] sums = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sums[i + 1] = sums[i] + arr[i];
        }

        int min = Integer.MAX_VALUE, start = 0, end = 0, target = k * x;

        for (int i = 0; i + k <= n; i++) {
            int cur = sums[i + k] - sums[i];
            if (cur < min) {
                start = i;
                end = i + k - 1;
                min = cur;
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            ans.add(copy[i]);
        }
        return ans;
    }
}
```
# [LeetCode_662_二叉树最大深度](https://leetcode-cn.com/problems/maximum-width-of-binary-tree/)
## 解法
### 思路
bfs
- 队列中记录一个obj数组
  - node节点
  - node对应的坐标值（坐标值和用数组模拟二叉树的规则一致，左子树等于根节点坐标值 * 2 - 1，右子树等于根节点坐标 * 2）
- bfs过程中记录当前层的最小和最大值，然后求距离，并和暂存的最大值作比较，取较大值
- 需要注意int值溢出的情况，可以先用long值存储，最后再强转成int
- bfs结束后，返回暂存的最大值
### 代码
```java
class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        Queue<Object[]> queue = new ArrayDeque<>();
        queue.offer(new Object[]{root, 1L});

        long ans = 0;
        while (!queue.isEmpty()) {
            int count = queue.size();
            long min = Long.MAX_VALUE, max = Long.MIN_VALUE;
            while (count-- > 0) {
                Object[] arr = queue.poll();
                if (arr == null) {
                    continue;
                }

                TreeNode node = (TreeNode) arr[0];
                long num = (long) arr[1];

                min = Math.min(min, num);
                max = Math.max(max, num);

                if (node.left != null) {
                    queue.offer(new Object[]{node.left, num * 2 - 1});
                }
                
                if (node.right != null) {
                    queue.offer(new Object[]{node.right, num * 2});
                }
            }
            
            ans = Math.max(max - min + 1, ans);
        }
        
        return (int)ans;
    }
}
```
# [LeetCode_2215_找出两数组的不同](https://leetcode-cn.com/problems/find-the-difference-of-two-arrays/)
## 解法
### 思路
- 用2个set来辅助判断是否在另一个数组中存在
- 因为答案需要去重，使用set来对结果再进行去重
- 分别遍历玩两个数组，并通过set判断后，放入结果set中
- 遍历完成后将结果set转换为list后放入结果列表中
### 代码
```java
class Solution {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>(), set2 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }

        for (Integer num : nums2) {
            set2.add(num);
        }

        List<List<Integer>> ans = new ArrayList<>();
        Set<Integer> ans1 = new HashSet<>(), ans2 = new HashSet<>();

        for (int num : nums1) {
            if (!set2.contains(num)) {
                ans1.add(num);
            }
        }

        for (int num : nums2) {
            if (!set1.contains(num)) {
                ans2.add(num);
            }
        }

        ans.add(new ArrayList<>(ans1));
        ans.add(new ArrayList<>(ans2));
        return ans; 
    }
}
```
# [LeetCode_954_二倍数对数组](https://leetcode-cn.com/problems/array-of-doubled-pairs/)
## 解法
### 思路
- 题目要求长度n的数组，能组成n/2对二倍数对
- 可以通过map对元素个数进行计数
- 然后将数组排序，从最小值开始遍历，尝试通过map查找是否能组成倍数对
- 如果能够组成，就更新map的统计个数
- 遍历结束，如果组成了n /2 对，就返回true，否则返回false
### 代码
```java
class Solution {
    public boolean canReorderDoubled(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        Arrays.sort(arr);

        int n = arr.length, half = n / 2, count = 0;

        for (int num : arr) {
            if (map.getOrDefault(num, 0) <= 0) {
                continue;
            }

            map.put(num, map.get(num) - 1);

            if (map.getOrDefault(num * 2, 0) <= 0) {
                map.put(num, map.get(num) + 1);
                continue;
            }

            map.put(num * 2, map.get(num * 2) - 1);
            count++;
        }

        return count == half;
    }
}
```