# [LeetCode_815_公交路线](https://leetcode-cn.com/problems/bus-routes/)
## 失败解法
### 原因
超时
### 思路
bfs
### 代码
```java
class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }

        Set<Integer>  memo = new HashSet<>();
        Map<Integer, List<int[]>> map = new HashMap<>();
        
        for (int[] route : routes) {
            for (int num : route) {
                List<int[]> list = map.getOrDefault(num, new ArrayList<>());
                list.add(route);
                map.put(num, list);
            }
        }
        
        List<int[]> list = map.get(source);
        if (list == null) {
            return -1;
        }
        
        Queue<int[]> queue = new ArrayDeque<>();
        for (int[] ints : list) {
            queue.offer(ints);
        }
        
        memo.add(source);
        int bus = 0;
        
        while (!queue.isEmpty()) {
             bus++;
             
             int size = queue.size();
             for (int i = 0; i < size; i++) {
                 int[] arr = queue.poll();
                 if (arr == null) {
                     continue;
                 }
                 
                 for (int num : arr) {
                     if (Objects.equals(num, target)) {
                         return bus;
                     }
                     
                     if (memo.contains(num)) {
                         continue;
                     }
                     
                     memo.add(num);
                     List<int[]> nextRoute = map.get(num);
                     if (nextRoute == null) {
                         continue;
                     }
                     
                     for (int[] nextBus : nextRoute) {
                         queue.offer(nextBus);
                     }
                 }
             }
        }
        
        return -1;
    }
}
```
## 失败解法
### 原因
超时
### 思路
在失败解法一的基础上，将记忆集合中的存储元素保存为数组
### 代码
```java
class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }

        Set<int[]> memo = new HashSet<>();
        Map<Integer, List<int[]>> map = new HashMap<>();

        for (int[] route : routes) {
            for (int num : route) {
                List<int[]> list = map.getOrDefault(num, new ArrayList<>());
                list.add(route);
                map.put(num, list);
            }
        }

        List<int[]> list = map.get(source);
        if (list == null) {
            return -1;
        }

        Queue<int[]> queue = new ArrayDeque<>();
        for (int[] ints : list) {
            queue.offer(ints);
            memo.add(ints);
        }

        int bus = 0;

        while (!queue.isEmpty()) {
            bus++;

            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] arr = queue.poll();
                if (arr == null) {
                    continue;
                }

                for (int num : arr) {
                    if (Objects.equals(num, target)) {
                        return bus;
                    }

                    List<int[]> nextRoute = map.get(num);
                    if (nextRoute == null) {
                        continue;
                    }

                    for (int[] nextBus : nextRoute) {
                        if (memo.contains(nextBus)) {
                            continue;
                        }
                        
                        queue.offer(nextBus);
                    }
                }
            }
        }

        return -1;
    }
}
```
## 解法
### 思路
- 初始化一个map：
    - key：对应的是路线中的站点的值
    - value：对应的是当前这个站点在哪些路线上存在，记录所有路线在routes数组中的坐标的集合
- 遍历routes数组：
    - 外层遍历每一个路线
    - 内层遍历遍历当前路线的每一个站点，通过该站点，到map中找到其存在的所有路线，如果存在路线，那么就将当前外层遍历的路线与内层map中得到的路线坐标联通，联通的方式就是通过一个二维布尔数组，将2个坐标对应的布尔值赋为true
    - 同时内层将外层的坐标放到site对应的list中，存到map里
- 这次循环后，建立了路线与路线之间基于相同站点的联系，同时维护除了站点和路线之间的映射关系
- 接着初始化一个数组，长度为routes的长度，用于计算从souce到达每个对应route需要的步数，初始化为-1，代表未曾到达
- 然后通过source，从map中获取对应的route，将这些route对应的距离都设置成1，代表从source到达这些路线都需要1步
- 然后将source对应的route坐标放入queue中，进行bfs搜索，搜索的目的是找到相通的且没有搜索过的route，对这些route对应的长度做计算，也就是在前一个route的距离基础上+1，这样就把所有能够从souce出发到达的route都计算过距离了
- 最后，通过target从map中获取一个route的坐标集合，求这些集合中，不是-1且值最小的距离作为结果，如果都是-1，那就返回-1
### 代码
```java
class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }

        int n = routes.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        boolean[][] edges = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int site : routes[i]) {
                List<Integer> nums = map.getOrDefault(site, new ArrayList<>());
                if (!nums.isEmpty()) {
                    for (int num : nums) {
                        edges[i][num] = edges[num][i] = true;
                    }
                }

                nums.add(i);
                map.put(site, nums);
            }
        }

        int[] distance = new int[n];
        Arrays.fill(distance, -1);

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i : map.getOrDefault(source, new ArrayList<>())) {
            distance[i] = 1;
            queue.offer(i);
        }

        while (!queue.isEmpty()) {
            int i = queue.poll();
            for (int j = 0; j < n; j++) {
                if (edges[i][j] && distance[j] == -1) {
                    distance[j] = distance[i] + 1;
                    queue.offer(j);
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i : map.getOrDefault(target, new ArrayList<>())) {
            if (distance[i] != -1) {
                ans = Math.min(ans, distance[i]);
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
```
# [LeetCode_168_Excel表列名称](https://leetcode-cn.com/problems/excel-sheet-column-title/)
## 解法
### 思路
10进制转26进制
- 字母按照ZABCD的顺序排列，放入数组中
- 循环处理目标值，计算目标值与26的余数，求出的余数对应数组中的下标，从而从数组中获取字母，这个字母拼接在结果字符串的第一个位置，也就是说是从低位开始不断往高位累加字符
- 需要注意，如果是整除的情况，也就是余数为0，那么字母肯定是Z，同时还要从高位借一个1，用来做进位，所以计算的时候就是得到整除情况，就从目标值上减去一个1
- 循环过程就是不断用26去除目标值，使其不断缩小，直到为0为止
### 代码
```java
class Solution {
    public String convertToTitle(int columnNumber) {
        char[] cs = new char[26];
        cs[0] = 'Z';
        cs[1] = 'A';
        for (int i = 2; i < 26; i++) {
            cs[i] = (char)(cs[i - 1] + 1);
        }

        StringBuilder sb = new StringBuilder();
        while (columnNumber != 0) {
            int digit = columnNumber % 26;
            if (digit == 0) {
                columnNumber--;
            }
            sb.insert(0, cs[digit]);
            columnNumber /= 26;
        }

        return sb.toString();
    }
}
```
## 解法二
### 思路
精简计算过程，每次都从目标值借1，这样取余获得的0-25的值就对应了A-Z的26个字母
### 代码
```java
class Solution {
    public String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        while (columnNumber != 0) {
            columnNumber--;
            sb.insert(0, (char)(columnNumber % 26 + 'A'));
            columnNumber /= 26;
        }
        
        return sb.toString();
    }
}
```
# [LeetCode_1271_十六进制魔术数字](https://leetcode-cn.com/problems/hexspeak/)
## 解法
### 思路
这题和[168](https://leetcode-cn.com/problems/excel-sheet-column-title/)的区别是，168涉及Z的进位，这里的16进制，就是0-F，也就是0-15，所以不需要向上借1。
### 代码
```java
class Solution {
  public String convertToTitle(int columnNumber) {
    StringBuilder sb = new StringBuilder();
    while (columnNumber != 0) {
      columnNumber--;
      sb.insert(0, (char)(columnNumber % 26 + 'A'));
      columnNumber /= 26;
    }

    return sb.toString();
  }
}
```
# [LeetCode_offer37_序列化二叉树](https://leetcode-cn.com/problems/xu-lie-hua-er-cha-shu-lcof/)
## 解法
### 思路
先序dfs
### 代码
```java
public class Codec {
    private static final String SPLIT_STR = "::";

    public String serialize(TreeNode root) {
        return doSerialize(root);
    }

    public TreeNode deserialize(String data) {
        return doDeserialize(new LinkedList<>(Arrays.asList(data.split(","))));
    }

    private String doSerialize(TreeNode node) {
        if (node == null) {
            return "null";
        }
        
        return node.val + "," + doSerialize(node.left) + "," + doSerialize(node.right);
    }
    
    private TreeNode doDeserialize(LinkedList<String> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        
        if (Objects.equals(list.getFirst(), "null")) {
            list.removeFirst();
            return null;
        }
        
        TreeNode node = new TreeNode(Integer.parseInt(list.getFirst()));
        list.removeFirst();
        node.left = doDeserialize(list);
        node.right = doDeserialize(list);
        return node;
    }
}
```
## 解法二
### 思路
- 以`(T)num(T) | X`的方式序列化
  - (T) 代表一棵子树
  - num代表当前节点值
  - X代表空树
- 以中序遍历的方式序列化
- 反序列化时如果遇到X，就代表当前子树到当前节点就不需要继续反序列化了
### 代码
```java
public class Codec {
    public String serialize(TreeNode root) {
        if (root == null) {
            return "X";
        }
        String left = "(" + serialize(root.left) + ")";
        String right = "(" + serialize(root.right) + ")";
        return left + root.val + right;
    }

    public TreeNode deserialize(String data) {
        return parse(data, new int[]{0});
    }

    private TreeNode parse(String data, int[] index) {
        if (data.charAt(index[0]) == 'X') {
            index[0]++;
            return null;
        }

        TreeNode node = new TreeNode(0);
        node.left = subParse(data, index);
        node.val = parseInt(data, index);
        node.right = subParse(data, index);
        return node;
    }

    private TreeNode subParse(String data, int[] index) {
        index[0]++;
        TreeNode node = parse(data, index);
        index[0]++;
        return node;
    }

    private int parseInt(String data, int[] index) {
        int digit = 0;
        boolean sign = true;
        if (data.charAt(index[0]) == '-') {
            index[0]++;
            sign = false;
        }
        
        while (Character.isDigit(data.charAt(index[0]))) {
            digit = digit * 10 + (data.charAt(index[0]++) - '0');
        }
        return sign ? digit : -digit;
    }
}
```
# [LeetCode_lcp07_传递消息](https://leetcode-cn.com/problems/chuan-di-xin-xi/)
## 解法
### 思路
dfs
### 代码
```java
class Solution {
    public int numWays(int n, int[][] relation, int k) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] arr : relation) {
            List<Integer> list = map.getOrDefault(arr[0], new ArrayList<>());
            list.add(arr[1]);
            map.put(arr[0], list);
        }

        return dfs(map, 0, n - 1, 0, k);
    }

    private int dfs(Map<Integer, List<Integer>> map, int cur, int target, int round, int targetRound) {
        if (round > targetRound) {
            return 0;
        }

        if (round == targetRound) {
            return cur == target ? 1 : 0;
        }

        int count = 0;
        List<Integer> list = map.getOrDefault(cur, new ArrayList<>());
        for (int num : list) {

            count += dfs(map, num, target, round + 1, targetRound);
        }

        return count;
    }
}
```
# [LeetCode_1275_找出井字棋的获胜者](https://leetcode-cn.com/problems/find-winner-on-a-tic-tac-toe-game/)
## 解法
### 思路
模拟统计
### 代码
```java
class Solution {
    public String tictactoe(int[][] moves) {
        int count = 0, pie = 0, na = 0;
        int[] row = new int[3], col = new int[3];
        for (int i = 0; i < moves.length; i++) {
            count++;
            int[] move = moves[i];

            boolean flag = i % 2 == 0;
            row[move[0]] += flag ? 1 : -1;
            col[move[1]] += flag ? 1 : -1;
            if (move[0] == move[1]) {
                pie += flag ? 1 : -1;
            }

            if (move[0] + move[1] == 2) {
                na += flag ? 1 : -1;
            }
            
            if (row[move[0]] == 3 || col[move[1]] == 3 || pie == 3 || na == 3) {
                return "A";
            }

            if (row[move[0]] == -3 || col[move[1]] == -3 || pie == -3 || na == -3) {
                return "B";
            }
        }

        return count == 9 ? "Draw" : "Pending";
    }
}
```
# [LeetCode_1833_雪糕的最大数量](https://leetcode-cn.com/problems/maximum-ice-cream-bars/)
## 解法
### 思路
排序+累加判断
### 代码
```java
class Solution {
    public int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);
        int count = 0;
        for (int cost : costs) {
            coins -= cost;
            if (coins < 0) {
                return count;
            }
            count++;
        }
        return count;
    }
}
```
## 失败解法
### 原因
超时
### 思路
自己实现快排
### 代码
```java
class Solution {
  public int maxIceCream(int[] costs, int coins) {
    sort(costs);
    int count = 0;
    for (int cost : costs) {
      coins -= cost;
      if (coins < 0) {
        return count;
      }
      count++;
    }
    return count;
  }

  private void sort(int[] arr) {
    doSort(arr, 0, arr.length - 1);
  }

  private void doSort(int[] arr, int start, int end) {
    if (start >= end) {
      return;
    }

    int partition = partition(arr, start, end);
    doSort(arr, start, partition - 1);
    doSort(arr, partition + 1, end);
  }

  private int partition(int[] arr, int start, int end) {
    while (start < end) {
      while (start < end && arr[start] <= arr[end]) {
        end--;
      }
      swap(arr, start, end);

      while (start < end && arr[start] <= arr[end]) {
        start++;
      }
      swap(arr, start, end);
    }
    return start;
  }

  private void swap(int[] arr, int x, int y) {
    int tmp = arr[x];
    arr[x] = arr[y];
    arr[y] = tmp;
  }
}
```
## 解法二
### 思路
桶排序+迭代累加
### 代码
```java
class Solution {
    public int maxIceCream(int[] costs, int coins) {    
        int max = 0;
        for (int cost : costs) {
            max = Math.max(max, cost);
        }
        int[] arr = new int[max + 1];
        
        for (int cost : costs) {
            arr[cost]++;
        }
        
        int count = 0, sum = 0;
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            if (num == 0) {
                continue;
            }
            
            if (i * num + sum <= coins) {
                sum += i * num;
                count += num;
            } else {
                count += (coins - sum) / i;
                return count;
            }
        }
        
        return count;
    }
}
```
# [LeetCode_1279_红绿灯路口](https://leetcode-cn.com/problems/traffic-light-controlled-intersection/)
## 解法
### 思路
加同步锁，每次判断的时候锁住，如果当前车子所在的路不是绿灯，就翻转为绿灯，并通过
### 代码
```java
    class TrafficLight {
  private boolean aIsGreen;
  public TrafficLight() {
    this.aIsGreen = true;
  }

  public void carArrived(
          int carId,           // ID of the car
          int roadId,          // ID of the road the car travels on. Can be 1 (road A) or 2 (road B)
          int direction,       // Direction of the car
          Runnable turnGreen,  // Use turnGreen.run() to turn light to green on current road
          Runnable crossCar    // Use crossCar.run() to make car cross the intersection 
  ) {
    doCarArrived(roadId, turnGreen, crossCar);
  }

  private synchronized void doCarArrived(
          int roadId,
          Runnable turnGreen,
          Runnable crossCar
  ) {
    if ((roadId != 1) == aIsGreen) {
      turnGreen.run();
      aIsGreen = !aIsGreen;
    }
    crossCar.run();
  }
}
```
# [LeetCode_451_根据字符出现次数排序](https://leetcode-cn.com/problems/sort-characters-by-frequency/)
## 解法
### 思路
- map统计字符出现次数
- 放入entry数组中依照value大小降序排序
- 遍历entry数组元素，依照key和value填充字符串
### 代码
```java
class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        char[] cs = s.toCharArray();
        for (char c : cs) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        Map.Entry<Character, Integer>[] entries = new Map.Entry[map.size()];
        int index = 0;
        for (Map.Entry<Character, Integer> characterIntegerEntry : map.entrySet()) {
            entries[index++] = characterIntegerEntry;
        }
        Arrays.sort(entries, (x,y) -> y.getValue() - x.getValue());
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : entries) {
            int time = entry.getValue();
            while (time-- > 0) {
                sb.append(entry.getKey());
            }
        }
        
        return sb.toString();
    }
}
``` 
## 解法二
### 思路
使用数组替换map
### 代码
```java
class Solution {
    public String frequencySort(String s) {
        int[][] bucket = new int[256][2];
        char[] cs = s.toCharArray();
        for (char c : cs) {
            bucket[c][0] = c;
            bucket[c][1]++;
        }

        Arrays.sort(bucket, (x, y) -> y[1] - x[1]);
        StringBuilder sb = new StringBuilder();
        for (int[] arr : bucket) {
            if (arr[1] == 0) {
                break;
            }
            
            while (arr[1]-- > 0) {
                sb.append((char) arr[0]);
            }
        }
        
        return sb.toString();
    }
}
```