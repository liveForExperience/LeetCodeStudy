# [LeetCode_143_重排链表](https://leetcode.cn/problems/reorder-list/)
## 解法
### 思路
- 初始化一个数组来存储node节点
- 遍历单链表
  - 计算出链表长度
  - 将节点存入数组中
- 根据链表长度，从头尾相向遍历数组，按题目要求依次重新排列
- 遍历循环的条件是`start <= end`
- 循环退出后，记得处理新链表结尾的next指针
  - 如果长度为奇数，则头尾指针一定是相交后退出，在相交时，做结尾节点的next置空处理
  - 如果长度为偶数，则在循环结束后，一定是start指针对应的位置结尾节点，对该节点的next指针做置空处理
### 代码
```java
class Solution {
    public void reorderList(ListNode head) {
        ListNode[] nodes = new ListNode[50001];
        int cnt = 0;
        ListNode node = head;
        while (node != null) {
            nodes[cnt++] = node;
            node = node.next;
        }

        int start = 0, end = cnt - 1;
        ListNode fake = new ListNode(0), pre = fake;
        while (start <= end) {
            pre.next = nodes[start];
            
            if (start != end) {
                nodes[start].next = nodes[end];
            }
            
            pre = nodes[end];

            if (start == end) {
                nodes[start].next = null;
                return;
            }

            start++;
            end--;
        }

        nodes[start].next = null;
    }
}
```
# [LeetCode_2681_英雄的力量](https://leetcode.cn/problems/power-of-heroes/)
## 解法
### 思路
- 因为是子序列，所以最终答案实际并不关心原数组顺序，可以对原数组进行排序
- 排序后，假设数组元素排列顺序为`a,b,c,d,e`，a最小，e最大
- 若我们选取d作为要计算的可能子序列的最大值，那么可以得到
  - 如果以a作为最小值，那么abcd，abd，acd等都是可能的结果，且除了a和d之外，其他元素都不参与到power值的计算中，所以实际就可以通过序列长度，计算出以a为最小值，d为最大值的子序列个数，个数计算公式为：`2^(j - i - 1)`，而以a为最小值，d为最大值的power计算公式就是`a * 2 ^ (3 - 0 - 1) * d²`
  - 如果以b作为最小值，与如上同理，公式是`b * 2 * d²`
  - 如果以c作为最小值，公式是`c * d²`
  - 如果以d作为最小值，公式是`d³`
  - 最终以d作为最大值的power值就是：
    - `d³ + c * d² + b * 2 * d² + a * 2² * d²`
    - `d³ + d²(a * 2² + b * 2 + c)`
    - 如果`s = a * 2² + b * 2 + c`
    - `d²(d + s)`
- 如果选取e作为最大值，那么计算过程与如上是一样的，最终可以观察到s的变化
  - `a * 2³ + b * 2² + c * 2 + d`
  - 因为：`s = a * 2² + b * 2 + c`
  - 所以：`2s + d`
  - 而power值为：`e²(e + (2s + d))`
- 观察可以得到，power值的变化，只和当前子序列的以及前一个子序列的最大值相关
- 具体逻辑：
  - 维护一个dp变量，用于暂存如上推到过程中的s值，初始为0
  - dp状态的状态转移方程：`s = 2 * s + nums[i - 1]`
  - 遍历nums数组，套用如上公式计算总值，且不能忘了取模
  - 遍历结束返回总值
### 代码
```java
class Solution {
  public int sumOfPower(int[] nums) {
    long dp = 0, ans = 0, mod = 1000000007;
    Arrays.sort(nums);
    for (int num : nums) {
      ans = (ans + (long) num * num % mod * (num + dp)) % mod;
      dp = (2 * dp + num) % mod;
    }

    return (int)ans;
  }
}
```
# [LeetCode_722_删除注释](https://leetcode.cn/problems/remove-comments/description/)
## 解法
### 思路
- 按照题目描述，在解析代码的时候，代码内容可以分成3种状态
  - 正常
  - 行级注释
  - 块状注释
- 题目就是按照如上不同状态做分类讨论
- 主体逻辑
  - 维护一个状态变量state
  - 初始化一个StringBuilder对象用于存储有效代码内容
  - 初始化一个结果列表，作为结果ans
  - 循环代码列表
  - 循环开始时如果当前状态不是块状代码（其实也就是正常代码，因为行级代码不可能跨行影响状态），那么就重新初始化StringBuilder
  - 循环内部遍历字符串
    - 如果当前是块状逻辑：
      - 当前字符及后续字符是块状结尾，那么就已经坐标到当前i+2的位置，且恢复状态为正常
    - 如果当前是行级状态，那么就直接返回到字符串结尾，且恢复状态到正常
    - 如果当前是正常状态：
      - 如果是块级状态开始，那么设置状态为块级状态，并返回i+2坐标
      - 如果是行级状态开始，那么设置状态为行级状态，并返回字符串结尾
      - 如果是正常字符串，就拼接到StringBuilder之后
    - 内部循环结束，如果StringBuilder大于零，且不是块级别，那么就将StringBuilder放到列表中
    - 列表循环结束，返回ans
### 代码
```java
class Solution {
    private int state = 0;
    private static final int NORMAL = 0, LINE = 1, BLOCK = 2;
    public List<String> removeComments(String[] source) {
        StringBuilder sb = new StringBuilder();
        List<String> ans = new ArrayList<>();
        for (String s : source) {
            if (!isBlock()) {
                sb = new StringBuilder();
            }

            for (int i = 0; i < s.length();) {
                i = execute(s, i, sb);
            }

            if (sb.length() > 0 && !isBlock()) {
                ans.add(sb.toString());
            }
        }

        return ans;
    }

    private int execute(String str, int index, StringBuilder sb) {
        if (isBlock()) {
            if (isBlockEnd(str, index)) {
                state = NORMAL;
                return index + 2;
            }
        } else if (isLine()) {
            return str.length();
        } else {
            if (isBlockStart(str, index)) {
                state = BLOCK;
                return index + 2;
            } else if (isLine(str, index)) {
                return str.length();
            } else {
                sb.append(str.charAt(index));
            }
        }

        return index + 1;
    }

    private boolean isBlock() {
        return state == BLOCK;
    }

    private boolean isLine() {
        return state == LINE;
    }

    private boolean isBlockStart(String str, int index) {
        return doJudge(str, index, '/', '*');
    }

    private boolean isBlockEnd(String str, int index) {
        return doJudge(str, index, '*', '/');
    }

    private boolean isLine(String str, int index) {
        return doJudge(str, index, '/', '/');
    }

    private boolean doJudge(String str, int index, char first, char second) {
        return str.charAt(index) == first &&
                index + 1 < str.length() &&
                str.charAt(index + 1) == second;
    }
}
```
# [LeetCode_980_不同路径](https://leetcode.cn/problems/unique-paths-iii/)
## 解法
### 思路
- 万事不决用回溯
- 主体逻辑
  - 遍历grid二维数组，获得如下数据
    - 值为1的起始位置
    - 累计值为0的元素个数target
  - 遍历结束后
    - 初始化一个布尔二维数组，用于做记事本，防止重复经过0坐标位置
    - 初始化一个cnt的int整数，用于记录路过的0坐标个数，因为题目要求所有0位置都要走过
    - 初始化一个类变量ans，用于记录路径个数作为答案
    - 初始化一个代表方向的二维数组，用于模拟4个方向的移动
  - 从之前记录的起始位置开始回溯
  - 回溯过程中，先判断当前坐标是否合法，合法的条件
    - 不越界
    - 不为-1
    - 记事本中未记录
  - 如果当前元素值为0，则累加cnt
  - memo记事本中记录当前坐标为true
  - 判断当前坐标元素是否为2，如果是，且cnt与target值一致，则累加ans值。且无论是否一致，都直接返回，因为就算不一致，那么经过了2，也就不可能成为符合题目要求的路径了
  - 循环4个方向，向下递归
  - 循环结束，进行回溯动作
    - 如果当前元素值为0，则累减cnt
    - 讲当前坐标的memo值设置为false
  - 递归返回后，返回ans值作为答案
### 代码
```java
class Solution {
    private final int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int[][] grid;
    private boolean[][] memo;
    private int ans, r, c, target = 0, cnt = 0;
    public int uniquePathsIII(int[][] grid) {
        this.ans = 0;
        this.grid = grid;
        this.r = grid.length;
        this.c = grid[0].length;
        this.memo = new boolean[r][c];
        int startX = -1, startY = -1;

        for (int x = 0; x < r; x++) {
            for (int y = 0; y < c; y++) {
                if (grid[x][y] == 1) {
                    startX = x;
                    startY = y;
                } else if (grid[x][y] == 0) {
                    target++;
                }
            }
        }

        backTrack(startX, startY);

        return ans;
    }

    private void backTrack(int x, int y) {
        if (!isValid(x, y)) {
            return;
        }

        if (grid[x][y] == 0) {
            cnt++;
        }
        
        if (grid[x][y] == 2) {
            if (cnt == target) {
                ans++;
            }
            return;
        }

        memo[x][y] = true;

        for (int[] direction : directions) {
            int nx = direction[0], ny = direction[1];
            backTrack(x + nx, y + ny);
        }

        memo[x][y] = false;
        
        if (grid[x][y] == 0) {
            cnt--;
        }
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < r && y >= 0 && y < c && grid[x][y] != -1 && !memo[x][y];
    }
}
```
## 解法二
### 思路
- 回溯+记事本+状态压缩
- 回溯过程中，希望通过记事本，将已经走过的路径状态缓存起来，这样能够避免走重复的可能路径
- 但是如果单单记录坐标，是无法准确描述当前状态的，因为曾经走过的路径不同，所以需要通过一个状态压缩变量来记录当前路径上曾经走过的状态
- 可以通过使用整数的2进制位来记录已经经过的位置，然后因为题目设置的边界是r * c的长度不超过20，所以可以通过64位的整数，在低(r * c)位存储坐标访问状态，高(64 - r * c)位存储坐标，这样作为一个memo的key，把返回的路径数存储起来
- 其他的逻辑和解法一类似
### 代码
```java
class Solution {
    private final int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int[][] grid;
    private int r, c;
    private final Map<Integer, Integer> memo = new HashMap<>();
    public int uniquePathsIII(int[][] grid) {
        this.grid = grid;
        this.r = grid.length;
        this.c = grid[0].length;
        int startX = -1, startY = -1, status = 0;
        for (int x = 0; x < r; x++) {
            for (int y = 0; y < c; y++) {
                if (grid[x][y] == 1) {
                    startX = x;
                    startY = y;
                    status = mask(status, x, y);
                } else if (grid[x][y] == 0 || grid[x][y] == 2) {
                    status = mask(status, x, y);
                }
            }
        }

        return backTrack(startX, startY, status);
    }

    private int backTrack(int x, int y, int status) {
        if (!isValid(status, x, y)) {
            return 0;
        }
        
        status = unmask(status, x, y);
        
        if (grid[x][y] == 2) {
            return status == 0 ? 1 : 0;
        }
        
        int key = ((x * c + y) << (r * c)) + status;
        
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        
        int ans = 0;
        for (int[] direction : directions) {
            int nx = direction[0], ny = direction[1];
            ans += backTrack(x + nx, y + ny, status);
        }
        memo.put(key, ans);
        return ans;
    }

    private boolean isValid(int status, int x, int y) {
        return x >= 0 && x < r && y >= 0 && y < c  && !visited(status, x, y);
    }

    private boolean visited(int status, int x, int y) {
        return (status & (1 << (x * c + y))) == 0;
    }

    private int mask(int status, int x, int y) {
        return status | (1 << (x * c + y));
    }
    
    private int unmask(int status, int x, int y) {
        return status ^ (1 << (x * c + y));
    }
}
```
# [LeetCode_24_两两交换链表中的节点](https://leetcode.cn/problems/swap-nodes-in-pairs/)
## 解法
### 思路
- 题目难点是对指针变化的模拟，和单向链表的反转类似，只不过需要注意到，题目要求两两反转，所以也就是反转操作是间隔着进行的，需要通过一个变量在遍历原链表时做行为的切换
- 主体逻辑
  - 初始化一个布尔变量flag，用于控制是否需要在当前节点操作反转
    - true：翻转操作
    - false：不操作
  - 判断链表长度是否小于2，如果是，直接返回节点即可
  - 定义一个递归函数，用来模拟反转过程，函数入参有3个
    - 当前节点cur
    - 前一个节点pre1
    - 前二个节点pre2
  - 递归的退出条件：当前节点为空
  - 递归的主体逻辑：
    - 如果flag为false，则直接跳过当前节点
    - 如果flag为true，则开始反转
  - 反转过程
    - 使用tmp指针指向cur.next
    - cur.next指针指向pre1
    - pre1.next指向tmp
    - pre2.next指向cur
  - 递归结束后返回head.next指针（因为已经反转了，第二个节点才是新的头节点）
### 代码
```java
class Solution {
    private boolean flag;
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        flag = true;
        ListNode ans = head.next;
        recursion(head, null, null);
        return ans;
    }

    private void recursion(ListNode cur, ListNode pre1, ListNode pre2) {
        if (cur == null) {
            return;
        }

        flag = !flag;

        if (!flag) {
            recursion(cur.next, cur, pre1);
        } else {
            ListNode next = cur.next;
            cur.next = pre1;
            pre1.next = next;
            if (pre2 != null) {
                pre2.next = cur;
            }
            recursion(next, pre1, cur);
        }
    }
}
```