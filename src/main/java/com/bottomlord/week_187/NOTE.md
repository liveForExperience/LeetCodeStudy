# [LeetCode_722_删除注释](https://leetcode.cn/problems/remove-comments/)
## 解法
### 思路
- 使用状态机处理如下情况
  - 在block里
    - 新的block无视
    - 新的//无视
    - 非注释无视
  - 在//里
    - 新的block无视
    - 新的//无视
    - 当前行无视
  - 不在注释里
    - 新的block，状态转换
    - 新的//，状态转换
    - 追加当前非注释字符
### 代码
```java
class Solution {
  private int status = 0, normal = 0, inline = 1, block = 1 << 1;

  public List<String> removeComments(String[] source) {
    List<String> ans = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    for (String str : source) {
      if (!blockStatus()) {
        sb = new StringBuilder();
      }

      for (int i = 0; i < str.length(); ) {
        i = execute(str, i, sb);
      }

      if (sb.length() > 0 && !blockStatus()) {
        ans.add(sb.toString());
      }
    }
    return ans;
  }

  private int execute(String str, int index, StringBuilder sb) {
    if (blockStatus()) {
      if (isBlockEnd(str, index)) {
        status = normal;
        return index + 2;
      }
    } else if (inlineStatus()) {
      status = normal;
      return str.length();
    } else {
      if (isBlockStart(str, index)) {
        status = block;
        return index + 2;
      } else if (isInline(str, index)) {
        return str.length();
      } else {
        sb.append(str.charAt(index));
      }
    }

    return index + 1;
  }

  private boolean isBlockStart(String str, int index) {
    return str.charAt(index) == '/' && index + 1 < str.length() && str.charAt(index + 1) == '*';
  }

  private boolean isBlockEnd(String str, int index) {
    return str.charAt(index) == '*' && index + 1 < str.length() && str.charAt(index + 1) == '/';
  }

  private boolean isInline(String str, int index) {
    return str.charAt(index) == '/' && index + 1 < str.length() && str.charAt(index + 1) == '/';
  }

  private boolean blockStatus() {
    return status == block;
  }

  private boolean inlineStatus() {
    return status == inline;
  }
}
```
# [LeetCode_1604_警告一小时内使用相同员工卡大于等于三次的人](https://leetcode.cn/problems/alert-using-same-key-card-three-or-more-times-in-a-one-hour-period/)
## 解法
### 思路
hash表
### 代码
```java
class Solution {
public List<String> alertNames(String[] keyName, String[] keyTime) {
        int n = keyName.length;
        Integer[] indexes = new Integer[n];
        int[] keyTimeNum = new int[n];
        
        for (int i = 0; i < n; i++) {
            indexes[i] = i;
            keyTimeNum[i] = convertNum(keyTime[i]);
        }
        
        Arrays.sort(indexes, Comparator.comparingInt(x -> keyTimeNum[x]));

        Map<String, List<Integer>> map = new HashMap<>();
        for (Integer index : indexes) {
            map.computeIfAbsent(keyName[index], x -> new ArrayList<>()).add(keyTimeNum[index]);
        }

        List<String> ans = new ArrayList<>();
        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            if (entry.getValue().size() < 3) {
                continue;
            }
            
            List<Integer> list = entry.getValue();
            for (int i = 2; i < list.size(); i++) {
                if (list.get(i) - list.get(i - 2) <= 60) {
                    ans.add(entry.getKey());
                    break;
                }
            }
        }
        
        Collections.sort(ans);
        return ans;
    }
    
    private int convertNum(String str) {
        String[] factors = str.split(":");
        return Integer.parseInt(factors[0]) * 60 + Integer.parseInt(factors[1]);
    }
}
```
## 解法二
### 思路
在解法一的基础上做优化，减少没必要的操作
### 代码
```java
class Solution {
  public List<String> alertNames(String[] keyName, String[] keyTime) {
    Map<String, List<Integer>> map = new HashMap<>();
    for (int i = 0; i < keyName.length; i++) {
      map.computeIfAbsent(keyName[i], x -> new ArrayList<>()).add(convertNum(keyTime[i]));
    }

    List<String> ans = new ArrayList<>();
    for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
      if (entry.getValue().size() < 3) {
        continue;
      }

      List<Integer> list = entry.getValue();
      Collections.sort(list);

      for (int i = 2; i < list.size(); i++) {
        if (list.get(i) - list.get(i - 2) <= 60) {
          ans.add(entry.getKey());
          break;
        }
      }
    }

    Collections.sort(ans);

    return ans;
  }

  private int convertNum(String str) {
    return ((str.charAt(0) - '0') * 10 + (str.charAt(1) - '0')) * 60 + (str.charAt(3) - '0') * 10 + (str.charAt(4) - '0');
  }
}
```
# [LeetCode_1233_删除子文件夹](https://leetcode.cn/problems/remove-sub-folders-from-the-filesystem/)
## 失败解法
### 原因
超时
### 思路
双层for循环
### 代码
```java
class Solution {
    public List<String> removeSubfolders(String[] folder) {
        int n = folder.length;
        boolean[] memo = new boolean[n];
        for (int i = 0; i < folder.length; i++) {
            String str = folder[i];
            for (int j = 0; j < folder.length; j++) {
                if (i == j) {
                    continue;
                }
                
                if (folder[j].startsWith(str + "/")) {
                    memo[j] = true;
                }
            }
        }

        List<String> ans = new ArrayList<>();
        for (int i = 0; i < memo.length; i++) {
            if (!memo[i]) {
                ans.add(folder[i]);
            }
        }
        return ans;
    }
}
```
## 解法
### 思路
字典树
### 代码
```java
class Solution {
  public List<String> removeSubfolders(String[] folder) {
    Tire tire = new Tire();
    for (String s : folder) {
      tire.insert(s);
    }
    return tire.find();
  }

  private static class Tire {
    TireNode root;

    public Tire() {
      root = new TireNode(null);
    }

    public void insert(String str) {
      List<String> strs = Arrays.stream(str.split("/")).filter(s -> !Objects.equals(s, "")).collect(Collectors.toList());
      TireNode node = root;
      for (String s : strs) {
        TireNode child = node.children.get(s);
        if (child == null) {
          child = new TireNode(s);
          node.children.put(s, child);
        }

        if (node.end) {
          return;
        }

        node = child;
      }

      node.end = true;
    }

    public List<String> find() {
      List<String> list = new ArrayList<>();
      doFind(root, list, new StringBuilder());
      return list;
    }

    private void doFind(TireNode node, List<String> list, StringBuilder sb) {
      if (node == null) {
        return;
      }

      if (node.end) {
        list.add(sb.toString());
        return;
      }

      Map<String, TireNode> children = node.children;
      for (Map.Entry<String, TireNode> entry : children.entrySet()) {
        int len = sb.length();
        sb.append("/").append(entry.getKey());
        doFind(entry.getValue(), list, sb);
        sb.setLength(len);
      }
    }

    static class TireNode {

      String s;
      Map<String, TireNode> children;
      private boolean end;

      public TireNode(String s) {
        this.s = s;
        this.children = new HashMap<>();
        this.end = false;
      }
    }
  }
}
```
# [LeetCode_2553_分割数组中数字的数位](https://leetcode.cn/problems/separate-the-digits-in-an-array/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public int[] separateDigits(int[] nums) {
        List<Integer> all = new LinkedList<>();
        for (int num : nums) {
            List<Integer> list = new ArrayList<>();
            while (num > 0) {
                list.add(num % 10);
                num /= 10;
            }
            
            reverse(list);
            all.addAll(list);
        }
        
        int[] ans = new int[all.size()];
        for (int i = 0; i < all.size(); i++) {
            ans[i] = all.get(i);
        }
        return ans;
    }
    
    private void reverse(List<Integer> list) {
        int head = 0, tail = list.size() - 1;
        while (head < tail) {
            int tmp = list.get(tail);
            list.set(tail, list.get(head));
            list.set(head, tmp);
            
            head++;
            tail--;
        }
    }
}
```
## 解法二
### 思路
使用链表替换
### 代码
```java
class Solution {
    public int[] separateDigits(int[] nums) {
        List<Integer> all = new LinkedList<>();
        for (int num : nums) {
            LinkedList<Integer> list = new LinkedList<>();
            while (num > 0) {
                list.addFirst(num % 10);
                num /= 10;
            }

            all.addAll(list);
        }

        int[] ans = new int[all.size()];
        for (int i = 0; i < all.size(); i++) {
            ans[i] = all.get(i);
        }
        return ans;
    }
}
```
## 解法三
### 思路
使用字符串拼接
### 代码
```java
class Solution {
    public int[] separateDigits(int[] nums) {
        StringBuilder sb = new StringBuilder();
        for (int num : nums) {
            sb.append(num);
        }
        
        char[] cs = sb.toString().toCharArray();
        int[] ans = new int[cs.length];
        for (int i = 0; i < cs.length; i++) {
            ans[i] = cs[i] - '0';
        }
        return ans;
    }
}
```
# [LeetCode_2558_从数量最多的堆取走礼物](https://leetcode.cn/problems/take-gifts-from-the-richest-pile/)
## 解法
### 思路
大顶堆
### 代码
```java
class Solution {
    public long pickGifts(int[] gifts, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((x, y) -> y - x);
        for (int gift : gifts) {
            queue.offer(gift);
        }

        while (k-- > 0 && !queue.isEmpty()) {
            Integer num = queue.poll();
            queue.offer((int)Math.sqrt(num));
        }

        long sum = 0;
        for (Integer num : queue) {
            sum += num;
        }
        return sum;
    }
}
```
# [LeetCode_1797_设计一个验证系统](https://leetcode.cn/problems/design-authentication-manager/)
## 解法
### 思路
小顶堆+hash表
### 代码
```java
class AuthenticationManager {
  private Map<String, Unit> map;
  private PriorityQueue<Unit> queue;
  private int timeToLive;

  public AuthenticationManager(int timeToLive) {
    this.map = new HashMap<>();
    this.queue = new PriorityQueue<>((x, y) -> x.currentTime - y.currentTime);
    this.timeToLive = timeToLive;
  }

  public void generate(String tokenId, int currentTime) {
    Unit unit = new Unit(tokenId, currentTime);
    map.put(tokenId, unit);
    queue.offer(unit);
  }

  public void renew(String tokenId, int currentTime) {
    if (!map.containsKey(tokenId)) {
      return;
    }

    Unit unit = map.get(tokenId);
    if (isExpired(unit, currentTime)) {
      map.remove(tokenId);
      queue.remove(unit);
      return;
    }

    queue.remove(unit);
    Unit newUnit = new Unit(tokenId, currentTime);
    queue.offer(newUnit);
    map.put(tokenId, newUnit);
  }

  public int countUnexpiredTokens(int currentTime) {
    System.out.println(queue);
    System.out.println(currentTime);

    while (!queue.isEmpty()) {
      Unit unit = queue.peek();
      if (isExpired(unit, currentTime)) {
        queue.poll();
        map.remove(unit.tokenId);
      } else {
        break;
      }
    }

    return map.size();
  }

  private boolean isExpired(Unit unit, int currentTime) {
    return unit.currentTime + timeToLive <= currentTime;
  }

  private class Unit {
    private String tokenId;
    private Integer currentTime;

    public Unit(String tokenId, Integer currentTime) {
      this.tokenId = tokenId;
      this.currentTime = currentTime;
    }

    public String toString() {
      return tokenId + ":" + currentTime;
    }
  }
}
```
## 解法二
### 思路
双向链表+hash表
### 代码
```java
class AuthenticationManager {

    private Map<String, DoubleLinkedListNode> map;
    private DoubleLinkedList list;
    private int timeToLive;

    public AuthenticationManager(int timeToLive) {
        this.map = new HashMap<>();
        this.list = new DoubleLinkedList();
        this.timeToLive = timeToLive;
    }

    public void generate(String tokenId, int currentTime) {
        DoubleLinkedListNode node = new DoubleLinkedListNode(tokenId, currentTime);
        map.put(tokenId, node);
        list.addToHead(node);
    }

    public void renew(String tokenId, int currentTime) {
        if (!map.containsKey(tokenId)) {
            return;
        }

        DoubleLinkedListNode node = map.get(tokenId);
        if (isExpired(node.currentTime, currentTime)) {
            map.remove(tokenId);
            list.remove(node);
            return;
        }

        node.currentTime = currentTime;
        list.remove(node);
        list.addToHead(node);
    }

    public int countUnexpiredTokens(int currentTime) {
        while (list.size() > 0 && list.getTail() != null && isExpired(list.getTail().currentTime, currentTime)) {
            DoubleLinkedListNode node = list.getTail();
            map.remove(node.tokenId);
            list.remove(node);
        }
        
        return list.size();
    }

    private boolean isExpired(int storeTime, int currentTime) {
        return storeTime + timeToLive <= currentTime;
    }

    private class DoubleLinkedList {

        private DoubleLinkedListNode head, tail;
        private int length;

        public DoubleLinkedList() {
            this.head = new DoubleLinkedListNode();
            this.tail = new DoubleLinkedListNode();
            this.head.next = tail;
            this.tail.pre = head;
            this.length = 0;
        }

        public void addToHead(DoubleLinkedListNode node) {
            node.next = this.head.next;
            node.pre = this.head;
            node.next.pre = node;
            node.pre.next = node;
            length++;
        }

        public DoubleLinkedListNode getTail() {
            return length == 0 ? null : this.tail.pre;
        }

        public int size() {
            return this.length;
        }

        public void remove(DoubleLinkedListNode node) {
            if (length == 0) {
                return;
            }

            node.pre.next = node.next;
            node.next.pre = node.pre;
            length--;
        }


    }

    private class DoubleLinkedListNode {
        private DoubleLinkedListNode next, pre;
        private String tokenId;
        private Integer currentTime;

        public DoubleLinkedListNode() {}

        public DoubleLinkedListNode(String tokenId, Integer currentTime) {
            this.tokenId = tokenId;
            this.currentTime = currentTime;
        }
    }
}
```
# [LeetCode_723_粉碎糖果](https://leetcode.cn/problems/candy-crush/)
## 解法
### 思路
递归模拟
### 代码
```java
class Solution {
public int[][] candyCrush(int[][] board) {
        int R = board.length, C = board[0].length;
        boolean todo = false;
        for (int r = 0; r < R; ++r) {
            for (int c = 0; c + 2 < C; ++c) {
                int v = Math.abs(board[r][c]);
                if (v != 0 && v == Math.abs(board[r][c+1]) && v == Math.abs(board[r][c+2])) {
                    board[r][c] = board[r][c+1] = board[r][c+2] = -v;
                    todo = true;
                }
            }
        }

        for (int r = 0; r + 2 < R; ++r) {
            for (int c = 0; c < C; ++c) {
                int v = Math.abs(board[r][c]);
                if (v != 0 && v == Math.abs(board[r+1][c]) && v == Math.abs(board[r+2][c])) {
                    board[r][c] = board[r+1][c] = board[r+2][c] = -v;
                    todo = true;
                }
            }
        }

        for (int c = 0; c < C; ++c) {
            int wr = R - 1;
            for (int r = R-1; r >= 0; --r)
                if (board[r][c] > 0)
                    board[wr--][c] = board[r][c];
            while (wr >= 0)
                board[wr--][c] = 0;
        }

        return todo ? candyCrush(board) : board;
    }
}
```
# [LeetCode_1223_掷骰子模拟](https://leetcode.cn/problems/dice-roll-simulation/)
## 解法
### 思路
动态规划
- dp[i][j]：在第i次投掷时候掷出数字j+1的可能数
- 状态转移方程：
  - 不考虑rowMax：dp[i][j] = sum(dp[i - 1][j])，j = 0,1,2,3,4,5
  - 考虑rowMax：那么就应该去除掉连续当前如果不投掷j + 1，从i - rowMax[j]到 i-1区间内连续投掷出j+1，然后i - rowMax[j] - 1次投掷出非j+1的所有可能性，这些可能就是连续投掷出rowMax[j] + 1次这种不合法情况的可能性
  - 所以需要将如上两部分相继转移完成，并使用mod进行取模
- 初始状态：dp[1][j] = 1
- 返回结果：sum(dp[n][j])
### 代码
```java
class Solution {
    private int mod = 1000000007;
    public int dieSimulator(int n, int[] rollMax) {
        int[][] dp = new int[n + 1][6];
        for (int i = 0; i < 6; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 6; j++) {
                long ans = 0;
                for (int k = 0; k < 6; k++) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][k]) % mod;
                }
                
                int index = i - rollMax[j] - 1;
                if (index > 0) {
                    for (int k = 0; k < 6; k++) {
                        if (k == j) {
                            continue;
                        }
                        
                        dp[i][j] = (dp[i][j] - dp[index][k] + mod) % mod;
                    }
                } else if (index == 0) {
                    dp[i][j]--;
                }
            }
        }
        
        long ans = 0;
        for (int i = 0; i < 6; i++) {
            ans = (ans + dp[n][i]) % mod;
        }
        return (int) ans;
    }
}
```
# [LeetCode_737_句子相似性II](https://leetcode.cn/problems/sentence-similarity-ii/)
## 解法
### 思路
并查集
### 代码
```java
class Solution {
    private int index = 0;
    public boolean areSentencesSimilarTwo(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        if (sentence1.length != sentence2.length) {
            return false;
        }

        Map<String, Integer> map = new HashMap<>();
        int index = 0;
        for (String str : sentence1) {
            generate(str, map);
        }

        for (String str : sentence2) {
            generate(str, map);
        }

        for (List<String> similarPair : similarPairs) {
            for (String str : similarPair) {
                generate(str, map);
            }
        }

        Uf uf = new Uf(this.index);
        for (List<String> similarPair : similarPairs) {
            for (int i = 1; i < similarPair.size(); i++) {
                uf.union(map.get(similarPair.get(i)), map.get(similarPair.get(i - 1)));
            }
        }

        for (int i = 0; i < sentence1.length; i++) {
            if (uf.find(map.get(sentence1[i])) != uf.find(map.get(sentence2[i]))) {
                return false;
            }
        }
        
        return true;
    }

    private void generate(String str, Map<String, Integer> map) {
        if (map.containsKey(str)) {
            return;
        }

        map.put(str, index++);
    }

    private class Uf {
        private int[] parent;

        public Uf(int n) {
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public void union(int x, int y) {
            int rx = find(x), ry = find(y);
            parent[rx] = ry;
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }

            return parent[x];
        }
    }
}
```