# [LeetCode_901_股票价格跨度](https://leetcode.cn/problems/online-stock-span)
## 解法
### 思路
- 根据题目设计的前提，next函数接受的参数都会且只会作为下一次调用时候的依据，所以不需要存储全部的历史数据
- 可以模拟一个单调栈，栈内存储的元素提供2种信息：
  - 价格，用于比较
  - 坐标，用于计算跨度
- 单调性基于`next函数入参值是否大于栈顶元素`这个判断条件
  - 如果大于，那么就丢弃
  - 如果不大于，就保留且不再继续处理
- 根据如上的处理逻辑，当next函数入参处理完栈内元素后，通过坐标差就能得到跨度，而因为坐标值无法通过函数入参提供，所以类中需要同时维护一个坐标属性，并在每次调用的时候进行自增
### 代码
```java
class StockSpanner {

    private int index;
    private LinkedList<int[]> stack;
    
    public StockSpanner() {
        this.index = 0;
        this.stack = new LinkedList<>();
    }

    public int next(int price) {
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            stack.poll();
        }
        
        if (stack.isEmpty()) {
            stack.push(new int[]{price, index++});
            return index;
        }
        
        int[] peek = stack.peek();
        stack.push(new int[]{price, index});
        return index++ - peek[1];
    }
}
```
# [LeetCode_2731_移动机器人](https://leetcode.cn/problems/movement-of-robots)
## 解法
### 思路
- 根据题意发现，如果两个机器人相遇，然后转换方向，对于要求的距离结果，实际转向的动作是没有意义的，完全可以理解成2个机器人相遇后擦肩而过。
- 那题目就变成了
  - 先求每个机器人行走d次后的坐标
  - 对坐标进行排序
  - 求所有的距离之和
- 求所有距离之和的问题可以通过如下的方式来解决：
  - 假设有`a,b,c,d,e`5个点
  - `c - b`的距离是x
  - 那么`c - a`，`d - a`,`e - a`的距离中都包含了x，同理，`b`也在和`c,d,e`之间的距离中包含了这个x，所以就可以得到如下的公式
  - `i * (n - i) * x`：
    - `i`：代表c之前的元素个数，也即2（a和b）
    - `n`：代表所有元素个数，也即5
    - `n - i`：代表c和c之后的所有元素，也即3（c,d,e）
  - 遍历排序后的坐标后，套用公式计算这个差值累加即可
### 代码
```java
class Solution {
    public int sumDistance(int[] nums, String s, int d) {
        int n = nums.length;
        long[] arr = new long[n];
        char[] cs = s.toCharArray();
        for (int i = 0; i < n; i++) {
            long v = cs[i] == 'L' ? -1 : 1;
            arr[i] = nums[i] + v * d;
        }

        Arrays.sort(arr);
        long sum = 0, mod = 1000000007;
        for (int i = 1; i < n; i++) {
            long x = arr[i] - arr[i - 1];
            sum += i * x % mod * (n - i) % mod;
            sum %= mod;
        }

        return (int) sum;
    }
}
```
# [LeetCode_2512_奖励最顶尖的K名学生](https://leetcode.cn/problems/reward-top-k-students)
## 解法
### 思路
- 使用2个set分别记录正面和负面评语
- 初始化一个优先级队列
  - 存储的元素为1个存放2个元素的数组，一个元素记录评语总分，一个学生元素记录id
  - 队列比较器的规则：
    - 如果数值相同，按id降序排列
    - 如果数值不同，按数值升序排列
    - 构建一个小顶堆
- 遍历评语数组，分别基于2个set算出当前学生的评语总分
- 然后构建数组放入优先级队列
- 放入后，判断当前队列是否大于k，如果是，就把堆顶最小元素出队
- 遍历结束后，将队列中元素依次取出，倒序放入结果列表中返回即可
### 代码
```java
class Solution {
    public List<Integer> topStudents(String[] positives, String[] negatives, String[] reports, int[] ids, int k) {
        Set<String> pset = new HashSet<>(), nset = new HashSet<>();
        Collections.addAll(pset, positives);
        Collections.addAll(nset, negatives);
        int n = reports.length;
        PriorityQueue<int[]> queue = new PriorityQueue<>((x, y) -> {
            if (x[0] == y[0]) {
                return y[1] - x[1];
            }

            return x[0] - y[0];
        });
        
        for (int i = 0; i < n; i++) {
            String report = reports[i];
            String[] words = report.split(" ");
            int score = 0;
            for (String word : words) {
                if (pset.contains(word)) {
                    score += 3;
                }

                if (nset.contains(word)) {
                    score--;
                }
            }

            queue.offer(new int[]{score, ids[i]});

            if (queue.size() > k) {
                queue.poll();
            }
        }

        List<Integer> ans = new ArrayList<>();
        k = queue.size();
        for (int i = 0; i < k; i++) {
            ans.add(null);
        }

        while (!queue.isEmpty()) {
            ans.set(--k, queue.poll()[1]);
        }

        return ans;
    }
}
```
# [LeetCode_1488_避免洪水泛滥](https://leetcode.cn/problems/avoid-flood-in-the-city)
## 解法
### 思路
- 根据题意，如果某一个湖泊泛滥，那么必须至少有一个晴天在湖泊满的那天之后，而我只需要将离湖泊满了之后最近的那个晴天，记录其抽取的是该湖泊即可
- 而如果有额外多的晴天，因为题目允许任意符合要求的答案，所以统一设置为一个值就行，我设置为1
- 根据如上的分析，需要以下几个数据结构来辅助算法执行
  - 一个能记录晴天的集合，该集合还能够提供二分查找的能力，满足我找到离即将泛滥的湖泊满之后最近的那个晴天，所以可以使用`TreeSet`
  - 一个记录满了的湖泊和对应天数对应关系的键值对，所以可以用`HashMap`
- 然后算法就比较简单：
  - 首先初始化一个结果数组，将所有坐标填充为1
  - 遍历`rains`数组，作如下判断
    - 如果元素值为0，代表是晴天，那么就将该元素的坐标记录到`TreeMap`中
    - 如果元素值为1，说明这天需要对湖泊状态进行判断，进而做出可能要抽水的决策
      - 如果`HashMap`中存储有当前元素值，说明该元素值对应的湖泊即将泛滥，需要从`TreeMap`中找到那个最近的晴天
        - 如果找不到，就返回空数组，结束算法
        - 如果找到了
          - 那么结果数组的该晴天坐标，对应的值就是当前的湖泊：`ans[day] = lake`，
          - `TreeMap`中将该晴天坐标删除
          - 更新`HashMap`中该湖泊对应的天数值
      - 如果没有该湖泊值，那么就记录该湖泊及对应的天数值
  - 遍历结束，返回结果数组即可
### 代码
```java
class Solution {
    public int[] avoidFlood(int[] rains) {
        int[] ans = new int[rains.length];
        Arrays.fill(ans, 1);
        TreeSet<Integer> set = new TreeSet<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < rains.length; i++) {
            int lake = rains[i];
            if (lake == 0) {
                set.add(i);
                continue;
            }

            ans[i] = -1;

            if (map.containsKey(lake)) {
                Integer day = set.ceiling(map.get(lake));
                if (day == null) {
                    return new int[0];
                }

                ans[day] = lake;
                set.remove(day);
            }

            map.put(lake, i);
        }

        return ans;
    }
}
```