# [LeetCode_1405_最长快乐字符串](https://leetcode-cn.com/problems/longest-happy-string/)
## 解法
### 思路
贪心
- 根据字符串个数非升序排序
- 根据题目要求循环拼接字符
- 循环结束返回拼接的字符串
### 代码
```java
class Solution {
    public String longestDiverseString(int a, int b, int c) {
        int[][] pairs = new int[][]{{a, 0}, {b, 1}, {c, 2}};
        StringBuilder sb = new StringBuilder();

        while (true) {
            Arrays.sort(pairs, (x, y) -> y[0] - x[0]);
            boolean hasNext = false;
            for (int[] pair : pairs) {
                if (pair[0] <= 0) {
                    continue;
                }

                char ch = (char)(pair[1] + 'a');
                int len = sb.length();
                if (len >= 2 &&
                    sb.charAt(len - 2) == ch &&
                    sb.charAt(len - 1) == ch) {
                    continue;
                }

                hasNext = true;
                sb.append(ch);
                pair[0]--;
                break;
            }

            if (!hasNext) {
                break;
            }
        }
        
        return sb.toString();
    }
}
```
# [LeetCode_2160_拆分数位后四位数字的最小和](https://leetcode-cn.com/problems/minimum-sum-of-four-digit-number-after-splitting-digits/)
## 解法
### 思路
- 将数字拆分成4个整数
- 非降序排序4个整数
- 返回1和3以及2和4组成的两个数的和
### 代码
```java
class Solution {
    public int minimumSum(int num) {
        int[] arr = new int[4];
        int index = 0;
        while (num > 0) {
            arr[index++] = num % 10;
            num /= 10;
        }
        Arrays.sort(arr);
        return arr[0] * 10 + arr[2] + arr[1] * 10 + arr[3];
    }
}
```
# [LeetCode_1001_网格照明](https://leetcode-cn.com/problems/grid-illumination/)
## 解法
### 思路
- 使用hash表存储lamps数组中x，y所对应的坐标的横竖撇捺4条延伸线的亮灯状态
  - key对应x,y坐标对应的横竖撇捺
  - value对应出现的次数，这个次数在query的时候用来做累减
- 遍历query数组
  - 根据4个map中是否存在该坐标的对应key，来判断是否亮灯
  - 同时对该坐标的四周做灭灯处理，同时处理value，如果value为0，则从map中删除key
- 最终返回亮灯状态对应的数组
- 需要注意：
  - 在query后关闭的，必须是已经打开的开关，如果是因为开关而照亮的灯，则关闭没有作用，不能关闭，所以需要有一个set来记录开过坐标，而因为二维数组是一个n * n的二维数组，所以可以用n * x + y来唯一代表每一个坐标
  - 在query后关闭时，要对新生成的四周一圈的坐标做越界判断，否则可能会导致错误的行列value被减去而应该被减去的value却因为判重逻辑而被跳过
### 代码
```java
class Solution {
    private final int[][] dirs = new int[][]{{0, 0}, {0, -1}, {0, 1}, {-1, 0}, {-1, -1}, {-1, 1}, {1, 0}, {1, -1}, {1, 1}};

    public int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        Map<Integer, Integer> row = new HashMap<>(), col = new HashMap<>(),
                left = new HashMap<>(), right = new HashMap<>();
        Set<Integer> set = new HashSet<>();

        for (int[] lamp : lamps) {
            int x = lamp[0], y = lamp[1],
                    l = x + y, r = x - y;

            if (set.contains(x * n + y)) {
                continue;
            }

            increase(row, x);
            increase(col, y);
            increase(left, l);
            increase(right, r);
            set.add(x * n + y);
        }

        int[] ans = new int[queries.length];
        int index = 0;
        for (int[] query : queries) {
            int x = query[0], y = query[1],
                    l = x + y, r = x - y;

            if (row.containsKey(x) || col.containsKey(y) || left.containsKey(l) || right.containsKey(r)) {
                ans[index] = 1;
            }

            for (int[] dir : dirs) {
                int nx = x + dir[0], ny = y + dir[1],
                    nl = nx + ny, nr = nx - ny;

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    continue;
                }

                if (set.contains(nx * n + ny)) {
                    set.remove(nx * n + ny);
                    decrease(row, nx);
                    decrease(col, ny);
                    decrease(left, nl);
                    decrease(right, nr);
                }
            }

            index++;
        }

        return ans;
    }

    private void increase(Map<Integer, Integer> map, Integer num) {
        map.put(num, map.getOrDefault(num, 0) + 1);
    }

    private void decrease(Map<Integer, Integer> map, Integer num) {
        Integer value = map.getOrDefault(num, 0);
        if (value <= 1) {
            map.remove(num);
            return;
        }

        map.put(num, map.get(num) - 1);
    }
}
```
# [LeetCode_2164_对奇偶下标分别排序](https://leetcode-cn.com/problems/sort-even-and-odd-indices-independently/)
## 解法
### 思路
- 将数组分成奇偶2个子数组
- 将奇偶坐标对应的值放入子数组中
- 对两个子数组分别排序
- 将两个子数交叉合并为一个数组返回
- 需要注意奇偶性对应的是下标值不是实际的奇偶顺序
### 代码
```java
class Solution {
    public int[] sortEvenOdd(int[] nums) {
        int n = nums.length;
        boolean odd = n % 2 == 1;
        int evenLen = odd ? n / 2 + 1 : n / 2,
            oddLen = n / 2;

        int[] odds = new int[oddLen], evens = new int[evenLen];
        int oddIndex = 0, evenIndex = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                evens[evenIndex++] = nums[i];
            } else {
                odds[oddIndex++] = nums[i];
            }
        }

        Arrays.sort(odds);
        Arrays.sort(evens);
        
        oddIndex = oddLen - 1;
        evenIndex = 0;

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                nums[i] = evens[evenIndex++];
            } else {
                nums[i] = odds[oddIndex--];
            }
        }
        
        return nums;
    }
}
```
# [LeetCode_offerII003_前n个数字二进制中1的个数](https://leetcode-cn.com/problems/w3tCBm/)
## 解法
### 思路
- 初始化数组用于储存每个数字的1的个数
- 从1开始遍历，逐个计算1的个数，并储存到数组中
- 遍历结束，返回数组
### 代码
```java
class Solution {
    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            ans[i] = count(i);
        }
        return ans;
    }

    private int count(int n) {
        int bit = 1, count = 0;
        while (bit <= n) {
            if ((bit & n) != 0) {
                count++;
            }
            
            bit <<= 1;
        }
        
        return count;
    }
}
```
## 解法二
### 思路
- 通过观察可以发现：
  - 偶数二进制1的个数和它除以2得到的二进制1的个数是一样的，因为偶数的最低位一定是0，然后除以二等于无符号右移1位，所以1的个数不会变
  - 奇数二进制1的个数和比它小的最近偶数相比，多一个1，因为奇数比偶数大1，而这个1就体现在最低位的不同
- 所以可以当做一个简单的动态规划来看待：
  - dp[i]：表示值为i的二进制中1的个数
  - 状态转移方程：
    - 当i为奇数的时候：dp[i] = dp[i - 1] + 1
    - 当i为偶数的时候：dp[i] = dp[i / 2]
  - base case：
    - dp[0] = 0
### 代码
```java
class Solution {
    public int[] countBits(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        
        for (int i = 1; i <= n; i++) {
            if (i % 2 == 0) {
                dp[i] = dp[i / 2];
            } else {
                dp[i] = dp[i - 1] + 1;
            }
        }
        
        return dp;
    }
}
```
# [LeetCode_offerII6_排序数组中两个数字之和](https://leetcode-cn.com/problems/kLl5u1/)
## 解法
### 思路
使用map
### 代码
```java
class Solution {
  public int[] twoSum(int[] numbers, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < numbers.length; i++) {
      int num = numbers[i];
      if (map.containsKey(target - num)) {
        return new int[]{Math.min(i, map.get(target - num)), Math.max(i, map.get(target - num))};
      }
      map.put(num, i);
    }
    return null;
  }
}
```
## 解法二
### 思路
- 因为是升序的数组，所以一定没有重复元素
- 又有序就可以使用双指针
### 代码
```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int head = 0, tail = numbers.length - 1;
        while (head < tail) {
            int sum = numbers[head] + numbers[tail];
            if (sum == target) {
                return new int[]{head, tail};
            } else if (sum > target) {
                tail--;
            } else {
                head++;
            }
        }
        
        return null;
    }
}
```
# [LeetCode_1447_最简分数](https://leetcode-cn.com/problems/simplified-fractions/)
## 解法
### 思路
- 定义最大公约数方程
- 2层嵌套遍历，外层确定分母，内层确定分子
- 判断分母和分子的最大公约数是否是1，如果是就是最简分数，存储到list中
- 循环结束，返回list
### 代码
```java
class Solution {
  public List<String> simplifiedFractions(int n) {
    List<String> ans = new ArrayList<>();
    for (int i = 1; i <= n; i++) {
      for (int j = i + 1; j <= n; j++) {
        if (gcd(i, j) == 1) {
          ans.add(i + "/" + j);
        }
      }
    }
    return ans;
  }

  private int gcd(int x, int y) {
    return y == 0 ? x : gcd(y, x % y);
  }
}
```
# [LeetCode_offerII12_左右两边子数组的和相等](https://leetcode-cn.com/problems/tvdfij/)
## 解法
### 思路
- 求数组总和
- 判断特殊情况：如果总和减去第一个元素值，等于0，那就返回第一个坐标，意思是说这个数组的剩余元素相加等于0，以第一个元素为中间坐标，两侧相等
- 循环遍历数组，退出条件是遍历到倒数第二个元素。
- 循环过程中累加数组值，然后判断是否当前累加值和后一个元素之后的所有元素之和相等，这个等式依赖总和来表示，如果判断成立，就直接返回当前坐标的后一个坐标
- 循环结束，如果没有找到坐标，那么就和循环开始前一样，判断一下数组是否以最后一个元素为中心坐标，如果是就返回最后一个元素
- 如果都没有找到，就返回-1
### 代码
```java
class Solution {
    public int pivotIndex(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        
        if (sum - nums[0] == 0) {
            return 0;
        }
        
        int curSum = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            curSum += nums[i];
            if (sum - nums[i + 1] == curSum * 2) {
                return i + 1;
            }
        }
        
        if (sum - nums[nums.length - 1] == 0) {
            return nums.length - 1;
        }

        return -1;
    }
}
```
## 解法二
### 思路
- 因为有第一个和倒数第一个元素这样的特殊情况，所以可以换一种思路
- 使用两个变量，一个是数组总和sum，一个是遍历的累加总和cur
- 然后sum在循环过程中不断累减，cur不断累加，这两个值是否相等就可以匹配题目的中心坐标两侧相等的要求，只不过需要在累减后但没有累加前的这个时间点进行比较，如果相等就返回当前坐标
- 也就相当于解法一，遍历的i代表的是中心坐标左侧的最后一个元素，而当前解法将i对应的元素当做是那个中心坐标
### 代码
```java
class Solution {
    public int pivotIndex(int[] nums) {
        int sum = 0, cur = 0;
        for (int num : nums) {
            sum += num;
        }

        for (int i = 0; i < nums.length; i++) {
            sum -= nums[i];
            if (sum == cur) {
                return i;
            }
            cur += nums[i];
        }
        
        return -1;
    }
}
```
# [LeetCode_offer18_有效的回文](https://leetcode-cn.com/problems/XltzEq/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
public boolean isPalindrome(String s) {
        char[] cs = s.toCharArray();
        int head = 0, tail = s.length() - 1;
        while (head < tail) {
            while (head < tail && !valid(cs[head])) {
                head++;
            }

            while (head < tail && !valid(cs[tail])) {
                tail--;
            }

            if (head >= tail) {
                return true;
            }

            if (toLower(cs[head]) != toLower(cs[tail])) {
                return false;
            }

            head++;
            tail--;
        }

        return true;
    }

    private boolean valid(char c) {
        return isNum(c) || isAlpha(c);
    }

    private boolean isNum(char c) {
        return c <= '9' && c >= '0';
    }

    private boolean isAlpha(char c) {
        return isLower(c) || isUpper(c);
    }

    private boolean isUpper(char c) {
        return c <= 'Z' && c >= 'A';
    }

    private boolean isLower(char c) {
        return c <= 'z' && c >= 'a';
    }

    private char toLower(char c) {
        if (isUpper(c)) {
            return (char)(c + 32);
        }
        
        return c;
    }
}
```
# [LeetCode_offerII19_最多删除一个字符得到回文](https://leetcode-cn.com/problems/RQku0D/)
## 解法
### 思路
递归
- 初始判断3种情况
- 在具体判断逻辑中，当发生比对字符不等的情况，就继续判断舍去头或尾字符的情况，并根据返回的情况来判断是否成立
- 递归的退出条件就是错误的次数
### 代码
```java
class Solution {
public boolean validPalindrome(String s) {
        int n = s.length();
        return valid(s, 0, n - 1, 0) ||
               valid(s, 0, n - 2, 1) ||
               valid(s, 1, n - 1, 1);
    }

    private boolean valid(String s, int head, int tail, int count) {
        if (count > 1) {
            return false;
        }

        while (head < tail) {
            if (s.charAt(head) != s.charAt(tail)) {
                return valid(s, head + 1, tail, count + 1) || valid(s, head, tail - 1, count + 1);
            }

            head++;
            tail--;
        }

        return true;
    }
}
```
# [LeetCode_offerII23_两个链表的第一个重合节点](https://leetcode-cn.com/problems/3u1WK4/)
## 解法
### 思路
- 计算出两个链表各自的长度，m和n
- 那么从两个链表头开始遍历链表，且到达链表尾部后再接上另一链表的头，这样遍历的距离一定是一样的，也就是m+n
- 而在这个m+n的长度中，如果两个链表是交汇的，那么定义第一个链表独立的长度是a，第二个链表独立的长度是b，共同的部分是c，那么`m + n = a + b + 2c`
- 而如上的过程中，他们必定会先经历`a + c + b`或`b + c + a`的距离，所以他们的遍历过程一定会在`a + b + c`这段距离之后同时到达第二个c的第一个节点，故这个节点就是他们的第一个交汇点。
### 代码
```java
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode nodeA = headA, nodeB = headB;
        if (nodeA == nodeB) {
            return nodeA;
        }
        
        int a = count(nodeA), b = count(nodeB), index = 0;
        while (index++ < a + b) {
            if (nodeA == null) {
                nodeA = headB;
            } else {
                nodeA = nodeA.next;
            }
            
            if (nodeB == null) {
                nodeB = headA;
            } else {
                nodeB = nodeB.next;
            }
            
            if (nodeA == nodeB) {
                return nodeA;
            }
        }
        
        return null;
    }
    
    private int count(ListNode node) {
        int count = 0;
        while (node != null) {
            count++;
            node = node.next;
        }
        return count;
    }
}
```
## 解法二
### 思路
在解法一的基础上简化逻辑
- 因为从两个头遍历的路程是相等的，所以就算不交汇，他们最终一定同时为null
- 按照如上的规则，代码就可以直接简化，不必先计算两个链表各自的长度，直接遍历即可
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
# [LeetCode_offerII24_反转链表](https://leetcode-cn.com/problems/UHnkqh/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode node = head, pre = null;
        while (node != null) {
            ListNode next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        return pre;
    }
}
```
# [LeetCode_1002_飞地的数量](https://leetcode-cn.com/problems/number-of-enclaves/)
## 解法
### 思路
- 从边缘开始的所有格子开始，将陆地标志翻转成海洋
- 翻转完毕后，遍历格子，统计陆地的个数
- 返回统计的结果
### 代码
```java
class Solution {
    private int row, col;
    private int[][] grid, directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public int numEnclaves(int[][] grid) {
        this.grid = grid;
        this.row = grid.length;
        this.col = grid[0].length;

        boolean[][] memo = new boolean[row][col];

        for (int i = 0; i < row; i++) {
            shrink(i, 0);
            shrink(i, col - 1);
        }

        for (int i = 0; i < col; i++) {
            shrink(0, i);
            shrink(row - 1, i);
        }
        
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    count++;
                }
            }
        }
        
        return count;
    }

    private void shrink(int x, int y) {
        if (x < 0 || x >= row || y < 0 || y >= col || grid[x][y] == 0) {
            return;
        }
        
        grid[x][y] = 0;
        
        for (int[] direction : directions) {
            shrink(x + direction[0], y + direction[1]);
        }
    }
}
```
# [LeetCode_offerII27_回文链表](https://leetcode-cn.com/problems/aMhZSa/)
## 解法
### 思路
- 先遍历并记录下链表的数字序列
- 然后开始头尾比较，如果有不匹配就返回false
- 遍历结束，因为没有不匹配，所以返回true
### 代码
```java
class Solution {
    public boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            list.add(node.val);
            node = node.next;
        }
        
        int l = 0, r = list.size() - 1;
        while (l < r) {
            if (!Objects.equals(list.get(l++), list.get(r--))) {
                return false;
            }
        }
        return true;
    }
}
```
## 解法二
### 思路
快慢指针
- 用快慢指针：
  - 初始化一个假节点，该节点的next指针指向链表的头指针
  - 快慢指针指向这个假节点
  - 快慢指针的目的是要找到链表中间节点的左边一个节点，或者两个中间节点的靠左节点
  - 慢指针每次移动1步，快指针每次移动2步
- 确定好了中间节点（慢指针指向节点的next指针指向的节点），以该节点为起始，做链表翻转，将后半部分翻转回来
- 然后就可以从head和翻转后的后半部分结尾开始，相向的同时遍历，并做判断。这样就不需要像解法一一样使用动态数组存储数据
### 代码
```java
class Solution {
    public boolean isPalindrome(ListNode head) {
        ListNode fake = new ListNode(0), slow = fake, fast = fake;
        fake.next = head;
        
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        
        ListNode tail = reverse(slow.next);
        
        while (head != tail && head != null && tail != null) {
            if (head.val != tail.val) {
                return false;
            }
            
            head = head.next;
            tail = tail.next;
        }
        
        return true;
    }
    
    private ListNode reverse(ListNode node) {
        ListNode pre = null;
        while (node != null) {
            ListNode next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        
        return pre;
    }
}
```
# [LeetCode_offerII32_有效的变位词](https://leetcode-cn.com/problems/dKk3P7/)
## 解法
### 思路
- 比较字符串是否完全相等，如果相等就返回false
- 使用数组统计两个字符串的个数
- 第一个字符串的个数累加，第二个字符串的个数累减
- 遍历统计数组，如果个数不是0，就返回false
- 如果都没有返回false，就返回true
### 代码
```java
class Solution {
    public boolean isAnagram(String s, String t) {
        if (Objects.equals(s, t)) {
            return false;
        }
        
        int[] arr = new int[26];
        for (char c : s.toCharArray()) {
            arr[c - 'a']++;
        }

        for (char c : t.toCharArray()) {
            arr[c - 'a']--;
        }

        for (int num : arr) {
            if (num != 0) {
                return false;
            }
        }
        
        return true;
    }
}
```
# [LeetCode_offerII34_外星语言是否排序](https://leetcode-cn.com/problems/lwyVBB/)
## 解法
### 思路
哈希表
### 代码
```java
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            char c = order.charAt(i);
            map.put(c, i);
        }
        
        map.put(' ', -1);
        
        int len = words.length;
        for (int i = 0; i < len - 1; i++) {
            char[] cs = get(words[i], words[i + 1]);
            if (cs == null) {
                continue;
            }

            if (map.get(cs[0]) > map.get(cs[1])) {
                return false;
            }
        }
        return true;
    }

    private char[] get(String x, String y) {
        int xl = x.length(), yl = y.length();
        for (int i = 0; i < xl || i < yl; i++) {
            if (i >= xl) {
                return new char[]{' ', y.charAt(i)};
            }
            
            if (i >= yl) {
                return new char[]{x.charAt(i), ' '};
            }
            
            if (x.charAt(i) != y.charAt(i)) {
                return new char[]{x.charAt(i), y.charAt(i)};
            }
        }

        return null;
    }
}
```