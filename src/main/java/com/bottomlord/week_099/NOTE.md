# [LeetCode_342_4的幂](https://leetcode-cn.com/problems/power-of-four/)
## 解法
### 思路
- 非正整数，返回false
- 32位的整数，依次左移2位来判断是否与当前值相等，左移结束如果还是不相等就返回false，否则返回true
### 代码
```java
class Solution {
    public boolean isPowerOfFour(int n) {
        if (n <= 0) {
            return false;
        }
        
        int bit = 1, time = 0;
        while (time < 16) {
            if (bit == n) {
                return true;
            }
            
            if (bit > n) {
                return false;
            }

            bit <<= 2;
            time++;
        }

        return false;
    }
}
```
# [LeetCode_1134_阿姆斯特朗数](https://leetcode-cn.com/problems/armstrong-number/)
## 解法
### 思路
模拟计算
### 代码
```java
class Solution {
    public boolean isArmstrong(int n) {
        int bit = 0, num = n;
        List<Integer> list = new ArrayList<>();
        while (num != 0) {
            bit++;
            int a = num % 10;
            list.add(a);
            num /= 10;
        }
        
        int sum = 0;
        for (Integer c : list) {
            sum += (int) Math.pow(c, bit);
        }
        
        return sum == n;
    }
}
```
# [LeetCode_1150_检查一个数是否在数组中占绝大多数](https://leetcode-cn.com/problems/check-if-a-number-is-majority-element-in-a-sorted-array/)
## 解法
### 思路
- 遍历计算target数，且因为是递增的，可以通过`num > target`提前结束
- 最后返回count数是否大于数组长度一半
### 代码
```java
class Solution {
    public boolean isMajorityElement(int[] nums, int target) {
        int count = 0;
        for (int num : nums) {
            if (num == target) {
                count++;
            } else if (num > target) {
                break;
            }
        }

        return count > nums.length / 2;
    }
}
```
## 解法二
### 思路
双指针：
- 左右指针找到等于target的子数组区间的左右边界
- 通过左右指针计算区间的长度是否大于整体长度的一半
### 代码
```java
class Solution {
    public boolean isMajorityElement(int[] nums, int target) {
        int len = nums.length, head = 0, tail = len - 1;
        while (head <= tail) {
            if (nums[head] < target) {
                head++;
            } else if (nums[head] > target) {
                return false;
            }
            
            if (nums[tail] > target) {
                tail--;
            } else if (nums[tail] < target) {
                return false;
            }
            
            if (nums[head] == nums[tail] && nums[head] == target) {
                break;
            }
        }
        
        return tail - head + 1 > len / 2;
    }
}
```
## 解法三
### 思路
二分查找：
- 2次二分查找分别找到子数组的左右边界
- 根据左右边界的坐标差值确定是否符合题目的要求
- 解法是一种范式，需要记住
### 代码
```java
class Solution {
    public boolean isMajorityElement(int[] nums, int target) {
        int len = nums.length;
        int head = 0, tail = len - 1;
        while (head < tail) {
            int mid = head + (tail - head) / 2;
            if (nums[mid] >= target) {
                tail = mid;
            } else {
                head = mid + 1;
            }
        }
        if (nums[tail] != target) {
            return false;
        }

        int r = tail;
        head = 0;
        tail = len - 1;
        while (head < tail) {
            int mid = head + (tail - head) / 2 + 1;
            if (nums[mid] <= target) {
                head = mid;
            } else {
                tail = mid - 1;
            }
        }

        return tail - r + 1 > len / 2;
    }
}
```
# [LeetCode_1744_你能在你最喜欢的那天吃到你最喜欢的糖果吗](https://leetcode-cn.com/problems/can-you-eat-your-favorite-candy-on-your-favorite-day/)
## 解法
### 思路
- 求出能够吃到目标类型糖果最早和最晚的时间，然后判断目标时间是否落在这个区间
- 初始化前缀和数组sums用于快速判断
- queries数组中每个元素数组的三个子元素分别是
    - queries[i][0]：类型t
    - queries[i][1]：天数d，此处的d要+1，因为第0天也是可以吃的，而这里的值代表的第几天，所以如果要算天数，就要在这个值的基础上+1
    - queries[i][2]：吃的上限c
- 遍历queries数组，通过糖果类型t，确定吃完t类型糖果需要的最短和最长时间
    - 最慢：每天1颗糖吃完所有t种糖果
    - 最快：每天c颗糖吃完所有t-1种糖果，在这个天数上再+1，加1是因为：
        - 如果整除c，那么就是正好吃完t-1类糖果的天数，再加1就是吃t类的天数
        - 如果不能整除，因为时向下取整，那么也就要再加1天才能吃到t类糖果
### 代码
```java
class Solution {
    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        int len = queries.length, type = candiesCount.length;
        boolean[] ans = new boolean[len];
        long[] sums = new long[type + 1];
        for (int i = 1; i < sums.length; i++) {
            sums[i] = sums[i - 1] + candiesCount[i - 1];
        }

        for (int i = 0; i < queries.length; i++) {
            int t = queries[i][0], d = queries[i][1] + 1, c = queries[i][2];
            long l = sums[t] / c + 1, r = sums[t + 1];
            ans[i] = d >= l && d <= r;
        }

        return ans;
    }
}
```
# [LeetCode_523_连续的子数组和](https://leetcode-cn.com/problems/continuous-subarray-sum/)
## 错误解法
### 原因
超时
### 思路
前缀和+2层循环迭代
### 代码
```java
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        int len = nums.length;
        int[] sums = new int[len + 1];
        for (int i = 1; i < len + 1; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }

        for (int i = 0; i < len + 1; i++) {
            for (int j = i + 2; j < len + 1; j++) {
                if ((sums[j] - sums[i]) % k == 0) {
                    return true;
                }
            }
        }

        return false;
    }
}
```
## 解法
### 思路
- 在失败解法基础上，通过map来降低时间复杂度
- 失败解法中通过嵌套循环来计算前缀和数组中是否有2个元素的差是k的整数倍
- 如果用map，将之前所有的前缀和与k取模后得到的余数以及该坐标的值记录下来，那么在遍历获取到一个新的前缀和时，就可以直接通过取余k，得到的余数到map中去找是否有重复的
- 如果有重复的，那么这两个前缀和相减，就一定是能被k整除的，然后再看坐标之间的距离是否大于2：
    - 如果是，就是对的 
    - 如果不是，则因为当前遍历到的坐标比map中以存在的相同余数对应的坐标要大，而这种情况还不符合题目距离为2的要求，那么这个坐标就不用储存了，因为存下来覆盖了之前更小的坐标，会使得之后相同余数的坐标与当前坐标的差值变得更小
- 如果没有重复，那就把余数存储下来
- 务必还要将sum值为0的情况记录下来，这个代表从头开始累加的前缀和能够被整除的情况，它的坐标应该为-1，这样第二个元素坐标1的时候，就能够获取大于1的距离
### 代码
```java
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            sum = (sum + nums[i]) % k;
            
            if (map.containsKey(sum)) {
                if (i - map.get(sum) > 1) {
                    return true;
                }
            } else {
                map.put(sum, i);
            }
        }
        
        return false;
    }
}
```
# [LeetCode_1165_单行键盘](https://leetcode-cn.com/problems/single-row-keyboard/)
## 解法
### 思路
- map存储字符与坐标的映射关系
- 遍历要打印的字符串，通过map计算耗时并累加
### 代码
```java
class Solution {
    public int calculateTime(String keyboard, String word) {
        Map<Character, Integer > map = new HashMap<>();
        for (int i = 0; i < keyboard.length(); i++) {
            map.put(keyboard.charAt(i), i);
        }

        int ans = map.get(word.charAt(0));
        for (int i = 0; i < word.length() - 1; i++) {
            ans += Math.abs(map.get(word.charAt(i)) - map.get(word.charAt(i + 1)));
        }

        return ans;
    }
}
```
## 解法二
### 思路
使用数组代替解法1的map
### 代码
```java
class Solution {
    public int calculateTime(String keyboard, String word) {
        int[] bucket = new int[26];
        for (int i = 0; i < keyboard.length(); i++) {
            bucket[keyboard.charAt(i) - 'a'] = i;
        }
        
        int start = 0, ans = 0;
        for (int i = 0; i < word.length(); i++) {
            int index = bucket[word.charAt(i) - 'a'];
            ans += Math.abs(index - start);
            start = index;
        }
        
        return ans;
    }
}
```
# [LeetCode_1175_质数排列](https://leetcode-cn.com/problems/prime-arrangements/)
## 解法
### 思路
- 求质数的个数
    - `厄拉多塞筛法`求个数
- 通过排列公式求得可能个数,假如n以内有m个素数，(n - m)个其他数，总共的排列组合总数目为 m! * (n-m)!种
### 代码
```java
class Solution {
    public int numPrimeArrangements(int n) {
        boolean[] arr = new boolean[n + 1];
        int count = 0;
        for (int i = 2; i < arr.length; i++) {
            if (!arr[i]) {
                count++;
                for (int j = 2; i * j <= n; j++) {
                    arr[i * j] = true;
                }
            }
        }

        return (int)(calculate(count) * calculate(n - count) % 1000000007);
    }

    private long calculate(int count) {
        long ans = 1;
        for (int i = count; i >= 1; i--) {
            ans *= i;
            ans %= 1000000007;
        }
        return ans;
    }
}
```
# [LeetCode_525_连续数组](https://leetcode-cn.com/problems/contiguous-array/)
## 失败解法
### 原因
超时
### 思路
前缀和+2层循环
### 代码
```java
class Solution {
    public int findMaxLength(int[] nums) {
        int[] sums = new int[nums.length + 1];
        for (int i = 1; i < sums.length; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }

        int ans = 0;
        for (int i = 0; i < sums.length; i++) {
            for (int j = i + 2; j < sums.length; j += 2) {
                if ((j - i) / 2 == sums[j] - sums[i]) {
                    ans = Math.max(ans, j - i);
                }
            }
        }
        
        return ans;
    }
}
```
## 解法
### 思路
- 初始化一个hash表
    - key：当前前缀和数组中0和1的个数差值
    - value：当前前缀和数组的长度
- 初始化一个int变量sum用来存储前缀和
- 遍历nums数组
    - 更新sum变量
    - 计算当前前缀和数组的0和1的个数差值，也就是当前前缀和数组长度与2倍的sum值的差，因为数组只包含0和1
    - 然后查看map中是否有存在相同个数差值的key
        - 如果有，求当前长度与记录长度的差值，更新最大长度
        - 如果没有，记录当前差值和长度到map中
- 遍历结束，返回结果，没找到就是0
### 代码
```java
class Solution {
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        int sum = 0, ans = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int len = i + 1, diff = len - 2 * sum;
            if (map.containsKey(diff)) {
                ans = Math.max(ans, len - map.get(diff));
            } else {
                map.put(diff, i + 1);
            }
        }

        return ans;
    }
}
```
# [LeetCode_1180_统计只含单一字母的子串](https://leetcode-cn.com/problems/count-substrings-with-only-one-distinct-letter/)
## 解法
### 思路
- 遍历字符串，获取所有只有一个字符的子串集合
- 遍历子串集合，再通过循环及配合indexOf的API获取到所有该字串的个数
### 代码
```java
class Solution {
    public int countLetters(String s) {
                Set<String> set = new HashSet<>();
        StringBuilder sb = new StringBuilder().append(s.charAt(0));
        set.add(sb.toString());
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != s.charAt(i - 1)) {
                sb.setLength(0);
            }
            sb.append(s.charAt(i));
            set.add(sb.toString());
        }

        int ans = 0;
        for (String str : set) {
            int index = -1;
            do {
                index = s.indexOf(str, index + 1);
                if (index != -1) {
                    ans++;
                }
            } while (index != -1);
        }

        return ans;
    }
}
```
## 解法
### 思路
- 找规律：
    - 当只有1个元素的时候，组合的个数是1
    - 当有2个元素的时候，组合的个数的（1+1+1），这里的3个1分别代表：
        1. 长度1的时候的个数1
        2. 新增的一个元素，导致的长度2减1的可能组合所增加的1个个数
        3. 当前长度所产生的1个个数
    - 当有3个元素的时候，组合的个数就是（3 + 1 + 1 + 1），和长度2类似，只是第二步又被切分为了相同的2步，多加了一个1，因为当前长度是3了，之前有2种可能需要都各自加1
    - 那这样的话，规律就出来了：`n = sum(n - 1) + (n - 1) + 1 = sum(n - 1) + n`
        - 这里的sum(n-1)就是前一个长度所获得的个数
        - 那这里就是一个递推公式了，其实从贵了来看就是一个求前缀和的公式
- 所以这里就可以先把这些所有可能的等差数列求出来，放在一个数组里
- 然后遍历字符串，看连续的字符长度有多长，就套用计算好的等差数列的和，累加起来，就好了
### 代码
```java
class Solution {
    public int countLetters(String s) {
        int len = s.length();
        if (len == 1) {
            return 1;
        }

        int[] sums = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            sums[i] = sums[i - 1] + i;
        }

        int ans = 0;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            int count = 1, oi = i;
            while (i + 1< len && s.charAt(i +1) == c) {
                count = sums[i + 1 - (oi - 1)];
                i++;
            }
            ans += count;
        }

        return ans;
    }
}
```
# [LeetCode_160_相交链表](https://leetcode-cn.com/problems/intersection-of-two-linked-lists/)
## 解法
### 思路
- 初始化两个指向两个链表头结点的指针
- 然后分别循环遍历两个指针
    - 退出条件是两个指针相等
    - 如果某个指针到底了，就重新指向头结点继续跑
    - 所以退出的可能只有两种，要么同时到底，要么找到相交点，同时到底也就代表在之前的过程中都没有找到相交点，那就是不想交
### 代码
```java
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode nodeA = headA, nodeB = headB;
        while (nodeA != nodeB) {
            nodeA = nodeA == null ? headA : nodeA.next;
            nodeB = nodeB == null ? headB : nodeB.next;
        }
        
        return nodeA;
    }
}
```
## 解法二
### 思路
- 如果链表A的不重合的长度是n，链表B不重合的长度是m
- 那么两个链表的总长度就是m + n + 2 * len
- 两个链表在相交后是共同走len的长度的
    - A在相交前最多走的是n + len + m
    - B在相交前做多走的是m + len + n
    - 那很明显两者是相等的
- 所以可以直接在A链表走完之后，让他从B链表的头继续走，而B则相反，那么他们如果有交点，就一定会在同一时刻相遇
### 代码
```java
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode nodeA = headA, nodeB = headB;
        while (nodeA != nodeB) {
            nodeA = nodeA == null ? headB : nodeA.next;
            nodeB = nodeB == null ? headA : nodeB.next;
        }
        return nodeA;
    }
}
```
# [LeetCode_203_移除链表元素](https://leetcode-cn.com/problems/remove-linked-list-elements/)
## 解法
### 思路
- 生成一个fake头节点，连接到原来链表的头部
- 初始化指针指向原链表头部，一个指针用来指向当前遍历的前一个节点
- 如果发现节点和值相同就做删除操作，否则就正常移动指针
- 最后返回fake头的next指针指向的节点
### 代码
```java
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode fake = new ListNode(0);
        fake.next = head;
        ListNode pre = fake, node = head;
        while (node != null) {
            ListNode next = node.next;
            if (node.val == val) {
                pre.next = next;
            } else {
                pre = node;
            }
            node = next;
        }
        
        return fake.next;
    }
}
```
# [LeetCode_203_计数质数](https://leetcode-cn.com/problems/count-primes/)
## 解法
### 思路
`厄拉多塞筛法`
### 代码
```java
class Solution {
    public int countPrimes(int n) {
        boolean[] arr = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!arr[i]) {
                count++;
                
                for (int j = 2; i * j < n; j++) {
                    arr[i * j] = true;
                }
            }
        }
        
        return count;
    }
}
```
# [LeetCode_1184_公交车站的距离](https://leetcode-cn.com/problems/distance-between-bus-stops/)
## 解法
### 思路
计算正向和反向的距离之和，求最小值
### 代码
```java
class Solution {
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        int index = start, r = 0, l = 0, n = distance.length;
        while (index != destination) {
            r += distance[index];
            index = (index + 1) %  n;
        }
        
        index = start;
        while (index != destination) {
            l += distance[(index - 1 + n) % n];
            index = (index - 1 + n) % n;
        }
        
        return Math.min(l, r);
    }
}
```
# [LeetCode_1185_一周中的第几天](https://leetcode-cn.com/problems/day-of-the-week/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public String dayOfTheWeek(int day, int month, int year) {
        String[] days = new String[]{"Friday", "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday"};
        int[] monthDays = new int[]{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        int count = 0;
        for (int i = 1971; i < year; i++) {
            count += 365;
            if (isLeapYear(i)) {
                count++;
            }
        }

        for (int i = 1; i < month; i++) {
            count += monthDays[i];
        }

        count = isLeapYear(year) && month > 2 ? count + day + 1 : count + day;
        return days[(count - 1) % 7];
    }

    private boolean isLeapYear(int year) {
        return (year % 100 != 0 && year % 4 == 0) || year % 400 == 0;
    }
}
```
# [LeetCode_474_一和零](https://leetcode-cn.com/problems/ones-and-zeroes/)
## 解法
### 思路
[dp题解](https://leetcode-cn.com/problems/ones-and-zeroes/solution/gong-shui-san-xie-xiang-jie-ru-he-zhuan-174wv/#comment)
### 代码
```java
class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        int[][] counts = new int[len + 1][2];
        for (int i = 0; i < len; i++) {
            String str = strs[i];
            int zero = 0, one = 0;
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == '0') {
                    zero++;
                } else {
                    one++;
                }
            }
            counts[i + 1][0] = zero;
            counts[i + 1][1] = one;
        }

        int[][][] dp = new int[len + 1][m + 1][n + 1];
        for (int i = 1; i <= len; i++) {
            int zero = counts[i][0], one = counts[i][1];
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    int a = dp[i - 1][j][k];
                    int b = (j >= zero && k >= one) ? dp[i - 1][j - zero][k - one] + 1 : 0;
                    dp[i][j][k] = Math.max(a, b);
                }
            }
        }

        return dp[len][m][n];
    }
}
```
# [LeetCode_LCP18_早餐组合](https://leetcode-cn.com/problems/2vYnGI/)
## 错误解法
### 原因
超时
### 思路
嵌套循环
### 代码
```java
class Solution {
    public int breakfastNumber(int[] staple, int[] drinks, int x) {
        int count = 0;
        for (int s : staple) {
            for (int d : drinks) {
                if (s + d <= x) {
                    count = (count + 1) % 1000000007;
                }
            }
        }
        return count;
    }
}
```
## 失败解法
### 原因
超时
### 思路
- 排序后计数，正序遍历staple数组
- 然后倒序遍历drinks，判断当前坐标是否越界或者两数和超过目标值x
  - 内层倒序遍历结束后，就可以获得能够满足外层staple元素的最大drink值对应的坐标，也就意味着所有比当前坐标小的元素也都满足，就可以直接累加了，无需再继续遍历
- 外层遍历结束后，返回累加结果，累加过程中不能忘记取模
### 代码
```java
class Solution {
    public int breakfastNumber(int[] staple, int[] drinks, int x) {
        int mod = 1000000007;

        Arrays.sort(staple);
        Arrays.sort(drinks);

        int ans = 0;
        for (int s : staple) {
            int j = drinks.length - 1;
            while (j >= 0 && s + drinks[j] > x) {
               j--;
            }

            if (j < 0) {
                break;
            }

            ans = (ans + j + 1) % mod;
        }

        return ans;
    }
}
```
## 解法
### 思路
- 排序后累加，再第二个错误解法的基础上，其实可以进一步缩减内层循环的范围
- 因为外层是升序的，所以外层的前一个元素与drinks数组中不能满足x要求的这些内层元素，也无法和当前外层元素达成满足x的情况，所以内层可以直接从上一次循环得到的内层坐标值开始判断，无需再从drinks的尾部开始
### 代码
```java
class Solution {
    public int breakfastNumber(int[] staple, int[] drinks, int x) {
        int mod = 1000000007;

        Arrays.sort(staple);
        Arrays.sort(drinks);

        int ans = 0;
        int j = drinks.length - 1;
        for (int s : staple) {
            
            while (j >= 0 && s + drinks[j] > x) {
               j--;
            }

            if (j < 0) {
                break;
            }

            ans = (ans + j + 1) % mod;
        }

        return ans;
    }
}
```
# [LeetCode_1189_气球的最大数量](https://leetcode-cn.com/problems/maximum-number-of-balloons/)
## 解法
### 思路
- 统计气球中包含字符的个数
- 对出现两次的o和l做除以2的处理后，返回这些统计值的最小值，这就是整个字符串balloon能够通过text组成的个数
### 代码
```java
class Solution {
    public int maxNumberOfBalloons(String text) {
        int b = 0, a = 0, l = 0, o = 0, n = 0;
        for (int i = 0; i < text.length(); i++) {
            int c = text.charAt(i);
            if (c == 'b') {
                b++;
            } else if (c == 'a') {
                a++;
            } else if (c == 'l') {
                l++;
            } else if (c == 'o') {
                o++;
            } else if (c == 'n') {
                n++;
            }
        }
        
        return Math.min(b, Math.min(a, Math.min(l / 2, Math.min(o / 2, n))));
    }
}
```
# [LeetCode_1196_最多可以买到的苹果数量](https://leetcode-cn.com/problems/how-many-apples-can-you-put-into-the-basket/)
## 解法
### 思路
排序后累加计算
### 代码
```java
class Solution {
    public int maxNumberOfApples(int[] arr) {
        Arrays.sort(arr);
        int count = 0, total = 0;
        for (int num : arr) {
            total += num;
            if (total <= 5000) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }
}
```