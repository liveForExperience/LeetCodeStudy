# LeetCode_1016_子串能表示从1到N数字的二进制串
## 题目
给定一个二进制字符串 S（一个仅由若干 '0' 和 '1' 构成的字符串）和一个正整数 N，如果对于从 1 到 N 的每个整数 X，其二进制表示都是 S 的子串，就返回 true，否则返回 false。

示例 1：
```
输入：S = "0110", N = 3
输出：true
```
示例 2：
```
输入：S = "0110", N = 4
输出：false
```
提示：
```
1 <= S.length <= 1000
1 <= N <= 10^9
```
## 解法
### 思路
- 遍历1到N
- 将数字转成二进制字符串
- 判断是否contains，如果不是返回false
- 遍历结束返回true
### 代码
```java
class Solution {
    public boolean queryString(String S, int N) {
        for (int i = 1; i <= N; i++) {
            String n = Integer.toBinaryString(i);
            if (!S.contains(n)) {
                return false;
            }
        }
        return true;
    }
}
```
# LeetCode_973_最接近原点的K个点
## 题目
我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。

（这里，平面上两点之间的距离是欧几里德距离。）

你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。

示例 1：
```
输入：points = [[1,3],[-2,2]], K = 1
输出：[[-2,2]]
解释： 
(1, 3) 和原点之间的距离为 sqrt(10)，
(-2, 2) 和原点之间的距离为 sqrt(8)，
由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
```
示例 2：
```
输入：points = [[3,3],[5,-1],[-2,4]], K = 2
输出：[[3,3],[-2,4]]
（答案 [[-2,4],[3,3]] 也会被接受。）
```
提示：
```
1 <= K <= points.length <= 10000
-10000 < points[i][0] < 10000
-10000 < points[i][1] < 10000
```
## 解法
### 思路
- 生成一个数组，存放`points`中对应下标生成的欧几里得距离
- 生成一个数组，根据计算出来的距离进行排序
- 根据排序结果找到前k个下标并返回
### 代码
```java
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        int len = points.length;
        int[][] ans = new int[K][2];
        Integer[] rank = new Integer[len];
        double[] distances = new double[len];
        for (int i = 0; i < len; i++) {
            int x = points[i][0], y = points[i][1];
            distances[i] = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
            rank[i] = i;
        }

        Arrays.sort(rank, Comparator.comparingDouble(x -> distances[x]));
        
        for (int i = 0; i < K; i++) {
            ans[i] = points[rank[i]];
        }
        
        return ans;    }
}
```
## 解法二
### 思路
分治算法：
- 参数：
    - `head`：需要进行调整顺序的数组的起始指针
    - `tail`：需要进行调整顺序的数组的结尾指针
    - `k`：需要调整的数组长度
- 递归：
    - 退出条件：`head` >= `tail`
    - 过程：
        - 以头指针的值作为基准`headDis`
        - 进入`while(head < tail)`的循环：
            - 先判断`head<tail`且尾指针的值是大于`headDis`，如果是就将尾指针不断前移，否则就终止循环并将这个值放在头指针的位置
            - 再判断头指针的值是否小于等于`headDis`，如果是就移动头指针，因为之前尾指针如果没有越界，一定会把小的值交换到头指针，所以这个时候头指针的值一定会移动，直到找到一个比`headDis`大的值，然后再和`tail`的元素交换，而交换来的就是原来的`headDis`
            - 然后继续循环，这样每一个循环都是将tail部分比`headDis`小的值换到前面，把`head`部分比`headDis`大的部分再换回去，直到头尾指针相遇
        - 循环条件失效退出后，判断当前的k值于新头指针移动的距离：
            - 如果小于等于，说明包括前k的元素的数组已经找到，现在需要对这个数组进行排序，确保前K个在数组之前
            - 如果大于，说明还没有找到足够多的前N个元素，需要继续移动头指针，而需要移动的值就是`k - (新head - head + 1)`
- 结果：返回前K个元素的copy
### 代码
```java
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        divideAndConquer(points, 0, points.length - 1, K);
        return Arrays.copyOfRange(points, 0, K);
    }

    private void divideAndConquer(int[][] points, int head, int tail, int k) {
        if (head >= tail) {
            return;
        }

        int oldHead = head, oldTail = tail, headDis = distance(points, head);
        while (head < tail) {
            while (head < tail && distance(points, tail) >= headDis) {
                tail--;
            }
            swap(points, head, tail);

            while (head < tail && distance(points, head) <= headDis) {
                head++;
            }
            swap(points, head, tail);
        }

        if (k <= head - oldHead + 1) {
            divideAndConquer(points, oldHead, head, k);
        } else {
            divideAndConquer(points, head + 1, oldTail, k - (head - oldHead + 1));
        }
    }

    private int distance(int[][] points, int x) {
        return points[x][0] * points[x][0] + points[x][1] * points[x][1];
    }

    private void swap(int[][] points, int x, int y) {
        int[] tmp = points[x];
        points[x] = points[y];
        points[y] = tmp;
    }
}
```
# LeetCode_638_大礼包
## 题目
在LeetCode商店中， 有许多在售的物品。

然而，也有一些大礼包，每个大礼包以优惠的价格捆绑销售一组物品。

现给定每个物品的价格，每个大礼包包含物品的清单，以及待购物品清单。请输出确切完成待购清单的最低花费。

每个大礼包的由一个数组中的一组数据描述，最后一个数字代表大礼包的价格，其他数字分别表示内含的其他种类物品的数量。

任意大礼包可无限次购买。

示例 1:
```
输入: [2,5], [[3,0,5],[1,2,10]], [3,2]
输出: 14
解释: 
有A和B两种物品，价格分别为¥2和¥5。
大礼包1，你可以以¥5的价格购买3A和0B。
大礼包2， 你可以以¥10的价格购买1A和2B。
你需要购买3个A和2个B， 所以你付了¥10购买了1A和2B（大礼包2），以及¥4购买2A。
```
示例 2:
```
输入: [2,3,4], [[1,1,0,4],[2,2,1,9]], [1,2,1]
输出: 11
解释: 
A，B，C的价格分别为¥2，¥3，¥4.
你可以用¥4购买1A和1B，也可以用¥9购买2A，2B和1C。
你需要买1A，2B和1C，所以你付了¥4买了1A和1B（大礼包1），以及¥3购买1B， ¥4购买1C。
你不可以购买超出待购清单的物品，尽管购买大礼包2更加便宜。
```
说明:
```
最多6种物品， 100种大礼包。
每种物品，你最多只需要购买6个。
你不可以购买超出待购清单的物品，即使更便宜。
```
## 失败解法
### 失败原因
超时
### 思路
回溯：
- 把单买转换成一种特殊的大礼包
- 递归穷举所有的选择，并计算最小值
- 退出条件：
    - 满足个数条件，计算价格，和已存在的最小值比较取最小值
    - 超出个数条件：返回
- 过程：
    - 循环所有的礼包元素：
    - 选择元素并进行下一次递归
    - 返回后恢复并进入下个循环
- 返回最小值作为结果
### 代码
```java
class Solution {
    private int min = Integer.MAX_VALUE;
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        for (int i = 0; i < price.size(); i++) {
            List<Integer> list = new ArrayList<>(price.size() + 1);
            for (int j = 0; j < price.size() + 1; j++) {
                if (i == j) {
                    list.add(1);
                    continue;
                }
                
                if (j == price.size()) {
                    list.add(price.get(i));
                    continue;
                }
                
                list.add(0);
            }
            special.add(list);
        }

        List<Integer> list = new ArrayList<>(needs.size());
        for (int i = 0; i < needs.size(); i++) {
            list.add(0);
        }
        backTrack(special, needs, list, 0);
        return min;
    }

    private void backTrack(List<List<Integer>> special, List<Integer> needs, List<Integer> cur, int sum) {
        if (sum >= min) {
            return;
        }
        
        boolean flag = true;
        for (int i = 0; i < cur.size(); i++) {
            if (cur.get(i) > needs.get(i)) {
                return;
            }

            if (cur.get(i) < needs.get(i)) {
                flag = false;
            }
        }

        if (flag) {
            min = Math.min(sum, min);
            return;
        }

        for (int i = 0; i < special.size(); i++) {
            List<Integer> bag = special.get(i);
            for (int j = 0; j < bag.size() - 1; j++) {
                cur.set(j, cur.get(j) + bag.get(j));
            }
            backTrack(special, needs, cur, sum + bag.get(bag.size() - 1));
            for (int j = 0; j < bag.size() - 1; j++) {
                cur.set(j, cur.get(j) - bag.get(j));
            }
        }
    }
}
```
## 解法
### 思路
和失败解法的思路类似，但是在实现上有几点不同：
- 不把单独买看成特殊的礼包，而是将单独买的价格`price`和`needs`相乘算出全是单独买的总价，用这个总价与使用礼包的组合进行比较
- 在比较的过程中，每一次递归都是将当前选择的礼包从needs中减去，将处理过的`needs`带到下一层，然后在下一层中重复第一步。最终递归到叶子节点，从叶子节点开始不断返回最小值
### 代码
```java
class Solution {
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int ans = 0;
        
        for (int i = 0; i < price.size(); i++) {
            ans += price.get(i) * needs.get(i);
        }
        
        for (int i = 0; i < special.size(); i++) {
            List<Integer> bag = special.get(i);
            boolean flag = true;
            for (int j = 0; j < bag.size() - 1; j++) {
                if (needs.get(j) < bag.get(j)) {
                    flag = false;
                    break;
                }
            }
            
            if (!flag) {
                continue;
            }
            
            for (int j = 0; j < bag.size() - 1; j++) {
                needs.set(j, needs.get(j) - bag.get(j));
            }
            
            ans = Math.min(ans, shoppingOffers(price, special, needs) + bag.get(price.size()));
            
            for (int j = 0; j < bag.size() - 1; j++) {
                needs.set(j, needs.get(j) + bag.get(j));
            }
        }
        
        return ans;
    }
}
```
# LeetCode_739_每日温度
## 题目
根据每日 气温 列表，请重新生成一个列表，对应位置的输入是你需要再等待多久温度才会升高超过该日的天数。如果之后都不会升高，请在该位置用 0 来代替。

例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。

提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。

## 解法
### 思路
- 生成结果数组
- 嵌套循环：
    - 外层循环遍历气温列表
    - 内存循环在外层元素下标基础上继续遍历气温列表，查找比外层元素大的元素，记录下标差
### 代码
```java
class Solution {
    public int[] dailyTemperatures(int[] T) {
        int[] ans = new int[T.length];
        ans[T.length - 1] = 0;

        for (int i = 0; i < T.length - 1; i++) {
            if (T[i] == 100) {
                ans[i] = 0;
                continue;
            }

            for (int j = i + 1; j < T.length; j++) {
                if (T[j] > T[i]) {
                    ans[i] = j - i;
                    break;
                }
            }
        }
        
        return ans;
    }
}
```
## 解法二
### 思路
从数组的尾部开始遍历：
    1. 第一个值一定是0
    2. 第二个值与第一个值比较：
        - 如果小于就是1
        - 否则就是0
    3. 第三个值与第二个值比较：
        - 如果大于第二个值，再通过存储的1，与其右边1位的值比较，也就是和最后一位比较
        - 如果小于第二个值，就是1
    4. 第n个值与第n-1个值比较，如果大于n-1，就通过下标找比它大的下一个数，一直到找到0为止，如果是0就是0
### 代码
```java
class Solution {
    public int[] dailyTemperatures(int[] T) {
        int[] ans = new int[T.length];
        ans[T.length - 1] = 0;

        for (int i = T.length - 2; i >= 0; i--) {
            ans[i] = recurse(i, T, ans, 1);
        }

        return ans;
    }

    private int recurse(int index, int[] T, int[] ans, int path) {
        if (index + path >= T.length) {
            return 0;
        }

        if (T[index] < T[index + path]) {
            return path;
        }

        if (ans[index + path] == 0) {
            return 0;
        }

        return recurse(index, T, ans, path + ans[index + path]);
    }
}
```
# LeetCode_47_全排列II
## 题目
给定一个可包含重复数字的序列，返回所有不重复的全排列。

示例:
```
输入: [1,1,2]
输出:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
```
## 解法
### 思路
- 数组排序
- 使用一个数组记录当前下标元素是否被使用过
- 回溯算法过程中注意去重
### 代码
```java
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        int[] visited = new int[nums.length];
        backTrace(nums, new LinkedList<>(), ans, visited);
        return ans;
    }

    private void backTrace(int[] nums, LinkedList<Integer> list, List<List<Integer>> ans, int[] visited) {
        if (list.size() == nums.length) {
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i] != 1) {
                if (i > 0 && nums[i] == nums[i - 1] && visited[i - 1] == 0) {
                    continue;
                }

                list.addLast(nums[i]);
                visited[i] = 1;
                backTrace(nums, list, ans, visited);
                visited[i] = 0;
                list.removeLast();
            }
        }
    }
}
```
# LeetCode_856_括号的分数
## 题目
给定一个平衡括号字符串 S，按下述规则计算该字符串的分数：
```
() 得 1 分。
AB 得 A + B 分，其中 A 和 B 是平衡括号字符串。
(A) 得 2 * A 分，其中 A 是平衡括号字符串。
```
示例 1：
```
输入： "()"
输出： 1
```
示例 2：
```
输入： "(())"
输出： 2
```
示例 3：
```
输入： "()()"
输出： 2
```
示例 4：
```
输入： "(()(()))"
输出： 6
```
提示：
```
S 是平衡括号字符串，且只含有 ( 和 ) 。
2 <= S.length <= 50
```
## 解法
### 思路
分治：
- 如果将左括号设为1，右括号设为-1，则遍历字符串时，前缀为0的字符串一定是平衡字符串。
- 那如果遍历的字符串下标差为1，说明是标准的`()`括号，如果下标差大于1，说明存在嵌套，而嵌套的括号适用`2*A`的规则，那么就可以从起始位置+1的小标开始递归遍历子字符串，并对递归返回值*2。
- 递归完成后，代表前缀起始到当前下标的字符串已经处理完成，前缀的起始位置设置为当前下标+1
- 所以整个字符串就是多个子字符串结果相加求和，遍历完字符串后，结果也就出来了。
### 代码
```java
class Solution {
    public int scoreOfParentheses(String S) {
        return divideAndConquer(S.toCharArray(), 0, S.length());
    }

    private int divideAndConquer(char[] cs, int start, int end) {
        int balance = 0, ans = 0;
        for (int i = start; i < end; i++) {
            balance += cs[i] == '(' ? 1 : -1;
            if (balance == 0) {
                if (i - start == 1) {
                    ans++;
                } else {
                    ans += 2 * divideAndConquer(cs, start + 1, i);    
                }
                start = i + 1;
            }
        }
        return ans;
    }
}
```
## 解法二
### 思路
使用栈：
- 使用栈来记录平衡括号字符串的深度，深度代表嵌套的括号层数，`(())`的深度是2
- 当遇到左括号就将一个0推入栈中代表第一层，如果继续碰到左括号，再推入一个0，代表深度为2，如果下一个遇到的是右括号，就将栈中的第二个0推出，+1后再推入，如果下一个又碰到右括号，就将1弹出，乘以2，再弹出0，将2塞入
### 代码
```java
class Solution {
    public int scoreOfParentheses(String S) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        char[] cs = S.toCharArray();
        for (char c : cs) {
            if (c == '(') {
                stack.push(0);
            } else {
                int first = stack.pop();
                int second = stack.pop();
                stack.push(second + Math.max(first * 2, 1));
            }
        }
        return stack.pop();
    }
}
```
## 解法三
### 思路
根据解法一可以看出，分数可以通过分治字符串为多个子平衡括号字符串相加来求，而进一步观察每一个子字符串，其实就是计算`2^(n-1)`的值，n为子字符串的括号嵌套个数
### 代码
```java
class Solution {
    public int scoreOfParentheses(String S) {
        int balance = 0, ans = 0;
        char[] cs = S.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == '(') {
                balance++;
            } else {
                balance--;
                if (cs[i - 1] == '(') {
                    ans += 1 << balance;
                }
            }
        }
        return ans;
    }
}
```
# LeetCode_461_最少移动次数使数组元素相等II
## 题目
给定一个非空整数数组，找到使所有数组元素相等所需的最小移动数，其中每次移动可将选定的一个元素加1或减1。 您可以假设数组的长度最多为10000。

例如:
```
输入:
[1,2,3]

输出:
2

说明：
只有两个动作是必要的（记得每一步仅可使其中一个元素加1或减1）： 

[1,2,3]  =>  [2,2,3]  =>  [2,2,2]
```
## 解法
### 思路
- 排序
- 找中位数
- 遍历算当前元素与中位数的差值，累加
### 代码
```java
class Solution {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length, mid = len % 2 == 0 ? (nums[len / 2] + nums[len / 2 - 1]) / 2 : nums[len / 2], ans = 0;
        for (int num : nums) {
            ans += Math.abs(num - mid);
        }
        return ans;
    }
}
```
## 优化代码
### 思路
- `最大值与最小值的差` = `最大值与中位数的差的绝对值` + `最小值与中位数的差的绝对值`
- 头尾指针同时向中心遍历，计算头尾差的累加值
### 代码
```java
class Solution {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int start = 0, end = nums.length - 1, ans = 0;
        while (start < end) {
            ans += nums[end--] - nums[start++];
        }
        return ans;
    }
}
```
# LeetCode_817_链表组件
## 题目
给定一个链表（链表结点包含一个整型值）的头结点 head。

同时给定列表 G，该列表是上述链表中整型值的一个子集。

返回列表 G 中组件的个数，这里对组件的定义为：链表中一段最长连续结点的值（该值必须在列表 G 中）构成的集合。

示例 1：
```
输入: 
head: 0->1->2->3
G = [0, 1, 3]
输出: 2
解释: 
链表中,0 和 1 是相连接的，且 G 中不包含 2，所以 [0, 1] 是 G 的一个组件，同理 [3] 也是一个组件，故返回 2。
```
示例 2：
```
输入: 
head: 0->1->2->3->4
G = [0, 3, 1, 4]
输出: 2
解释: 
链表中，0 和 1 是相连接的，3 和 4 是相连接的，所以 [0, 1] 和 [3, 4] 是两个组件，故返回 2。
```
注意:
```
如果 N 是给定链表 head 的长度，1 <= N <= 10000。
链表中每个结点的值所在范围为 [0, N - 1]。
1 <= G.length <= 10000
G 是链表中所有结点的值的一个子集.
```
## 解法
### 思路
- 遍历G构建映射表
- 遍历链表，查询是否存在当前节点元素
    - 如果存在，遍历这个子链表，记一次数
    - 如果不存在，继续遍历
- 返回计数值
### 代码
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public int numComponents(ListNode head, int[] G) {
        Set<Integer> set = new HashSet<>();
        for (int num : G) {
            set.add(num);
        }
        
        int ans = 0;
        ListNode node = head;
        while (node != null) {
            boolean flag = false;
            while (node != null && set.contains(node.val)) {
                node = node.next;
                flag = true;
            }
            
            if (flag) {
                ans++;
            }
            
            if (node == null) {
                break;
            }
            
            node = node.next;
        }
        
        return ans;
    }
}
```
## 优化代码
### 思路
用数组作为桶代替set
### 代码
```java
class Solution {
    public int numComponents(ListNode head, int[] G) {
        int[] bucket = new int[10000];
        for (int num : G) {
            bucket[num]++;
        }

        int ans = 0;
        ListNode node = head;
        while (node != null) {
            boolean flag = false;
            while (node != null && bucket[node.val] > 0) {
                node = node.next;
                flag = true;
            }

            if (flag) {
                ans++;
            }

            if (node == null) {
                break;
            }

            node = node.next;
        }

        return ans;
    }
}
```
# LeetCode_73_矩阵置零
## 题目
给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。

示例 1:
```
输入: 
[
  [1,1,1],
  [1,0,1],
  [1,1,1]
]
输出: 
[
  [1,0,1],
  [0,0,0],
  [1,0,1]
]
```
示例 2:
```
输入: 
[
  [0,1,2,0],
  [3,4,5,2],
  [1,3,1,5]
]
输出: 
[
  [0,0,0,0],
  [0,4,5,0],
  [0,3,1,0]
]
```
进阶:
```
一个直接的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
你能想出一个常数空间的解决方案吗？
```
## 解法
### 思路
- 遍历二维数组，找到0所在的行和列
- 在分别将记录的行和列置为0
### 代码
```java
class Solution {
    public void setZeroes(int[][] matrix) {
        Set<Integer> rows = new HashSet<>();
        Set<Integer> cols = new HashSet<>();
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }
        
        for (int num : rows) {
            Arrays.fill(matrix[num], 0);
        }
        
        for (int i = 0; i < matrix.length; i++) {
            for (int num : cols) {
                matrix[i][num] = 0;
            }
        }
    }
}
```
## 优化代码
### 思路
前一个解法在置零过程中，有的元素位置被置零了多了，这个重复的过程可以进行优化。
### 代码
```java
class Solution {
    public void setZeroes(int[][] matrix) {
        Set<Integer> rows = new HashSet<>();
        Set<Integer> cols = new HashSet<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            if (rows.contains(i)) {
                Arrays.fill(matrix[i], 0);
                continue;
            }
            for (int j = 0; j < matrix[i].length; j++) {
                if (cols.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
```
## 优化代码II
### 思路
- 直接使用数组代替set，减少构建set的开销
- 遍历矩阵，只要遇到元素为0，就将所在行和列的0下标位置置为0，这样在下一次遍历时只要判断所在行列的0下标是否为0就能知道当前元素是否需要置为0
- 但是如果先做标记，把行列的0下标元素置为0，那就无法判断这个位置的元素是否需要将所在行列也全部置零，所以需要前判断第一行和第一列是否需要值为零，先做一次标记，但先不置零，等其他行列标记和置零结束后，在根据标记进行操作
### 代码
```java
class Solution {
    public void setZeroes(int[][] matrix) {
        boolean row0 = false, col0 = false;
        for (int[] rows : matrix) {
            if (rows[0] == 0) {
                col0 = true;
                break;
            }
        }

        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                row0 = true;
                break;
            }
        }
                
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        
        if (row0) {
            Arrays.fill(matrix[0], 0);
        }
        
        if (col0) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
```
# LeetCode_89_格雷编码
## 题目
格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。

给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。格雷编码序列必须以 0 开头。

示例 1:
```
输入: 2
输出: [0,1,3,2]
解释:
00 - 0
01 - 1
11 - 3
10 - 2

对于给定的 n，其格雷编码序列并不唯一。
例如，[0,2,3,1] 也是一个有效的格雷编码序列。

00 - 0
10 - 2
11 - 3
01 - 1
```
示例 2:
```
输入: 0
输出: [0]
解释: 我们定义格雷编码序列必须以 0 开头。
     给定编码总位数为 n 的格雷编码序列，其长度为 2n。当 n = 0 时，长度为 20 = 1。
     因此，当 n = 0 时，其格雷编码序列为 [0]。
```
## 解法
### 思路
- 格雷码可以通过一种巧妙地方式来生成：
    - 通过G(n - 1)，生成其镜像G\`(n - 1)
    - 然后再G(n - 1)前加上0
    - 在G\`(n - 1)前加1
    - 生成的所有数就是G(n)
- 从如上可以观察到整个过程没分成了G(n) = fun(G(n - 1))，可以通过动态规划来做
    - base case：
        - dp[0] = [0]
        - dp[1] = [0, 1]
    - 状态转移方程：dp[n] = fun(dp[n - 1])，dp[n-1] + (在dp[n - 1]的所有镜像值得基础上，在它们最高位+1的位上置为1)
    - 实现：
        - 嵌套循环：外层循环遍历n位的位数，内层循环处理每一位的状态转移方程计算
        - 内层具体逻辑：
            - 首先每一位的处理结果会被存在一个list中，当前位可以从list中找到需要的dp[n - 1]
            - 其次会有一个值作为最高位+1的那个位上的1，所以每次当前位处理完后，会左移，作为下一位的高位1
            - 剩余就是从尾部遍历list，生成上一位结果的镜像值并加上高位1
### 代码
```java
class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> ans = new ArrayList<Integer>(){{add(0);}};
        int head = 1;
        for (int i = 0; i < n; i++) {
            for (int j = ans.size() - 1; j >= 0; j--) {
                ans.add(head + ans.get(j));
            }
            head <<= 1;
        }
        return ans;
    }
}
```
# LeetCode_1238_循环码排列
## 题目
给你两个整数 n 和 start。你的任务是返回任意 (0,1,2,,...,2^n-1) 的排列 p，并且满足：
```
p[0] = start
p[i] 和 p[i+1] 的二进制表示形式只有一位不同
p[0] 和 p[2^n -1] 的二进制表示形式也只有一位不同
```
示例 1：
```
输入：n = 2, start = 3
输出：[3,2,0,1]
解释：这个排列的二进制表示是 (11,10,00,01)
     所有的相邻元素都有一位是不同的，另一个有效的排列是 [3,1,0,2]
```
示例 2：
```
输出：n = 3, start = 2
输出：[2,6,7,5,4,0,1,3]
解释：这个排列的二进制表示是 (010,110,111,101,100,000,001,011)
```
提示：
```
1 <= n <= 16
0 <= start < 2^n
```
## 解法
### 思路
基于题89格雷编码：
- 生成格雷编码
- 找到start值的下标
- 根据start进行旋转(将以start为起始的subList作为list的起始，将以0为起始的subList放置在尾部)
### 代码
```java
class Solution {
    public List<Integer> circularPermutation(int n, int start) {
        List<Integer> list = new ArrayList<Integer>(){{add(0);}};
        int head = 1, index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = list.size() - 1; j >= 0; j--) {
                int num = head + list.get(j);
                list.add(num);
                
                if (num == start) {
                    index = list.size() - 1;
                }
            }
            head <<= 1;
        }
        
        List<Integer> ans = new ArrayList<>();
        ans.addAll(list.subList(index, list.size()));
        ans.addAll(list.subList(0, index));
        return ans;
    }
}
```
## 解法二
### 思路
- 通过生成格雷码可知，最终的结果必定是最高位是1和0两种，且剩余位为镜像排列
- 所以过程可以模拟成dfs的中序遍历，左右子树分别作为互为镜像的两部分，根节点在当前层左中序处理时，将最高位变为1或0，带入右子树
- 为了能起到这个目的，需要有一个初始为最高位为1其余为0的值，然后再递归进入左右子树的时候通过左移这个标志来一位位地改变数
- 因为需要一位位地改变数，所以这个值需要有状态地被记录，方便放入结果list中
- dfs会导致左右子树地值是从最低位开始被变更，这样使得中序可以符合题目要求产生镜像地值，从而也能理解到，这棵二叉树地根节点就是start
### 代码
```java
class Solution {
    private int val;
    public List<Integer> circularPermutation(int n, int start) {
        this.val = start;
        List<Integer> ans = new ArrayList<Integer>(){{add(start);}};
        dfs(1 << (n - 1), ans);
        return ans;
    }

    private void dfs(int xor, List<Integer> ans) {
        if (xor == 0) {
            return;
        }

        dfs(xor >> 1, ans);
        this.val ^= xor;
        ans.add(this.val);
        dfs(xor >> 1, ans);
    }
}
```
# LeetCode_337_打家劫舍III
## 题目
在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。

计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。

示例 1:
```
输入: [3,2,3,null,3,null,1]

     3
    / \
   2   3
    \   \ 
     3   1

输出: 7 
解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
```
示例 2:
```
输入: [3,4,5,1,3,null,1]

     3
    / \
   4   5
  / \   \ 
 1   3   1

输出: 9
解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
```
## 错误解法
### 思路
- bfs广度优先遍历二叉树，求得每一层的总和
- 再通过动态规划求得最大值：`dp[n] = Math.max(dp[n - 2] + nums[cur], dp[n - 1])`
### 错误原因
不相邻的节点不是必需层与层之间隔离，可以相邻层不同路径的节点
### 代码
```java
class Solution {
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }

        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int count = queue.size(), sum = 0;
            while (count-- > 0) {
                TreeNode node = queue.poll();
                sum += node.val;

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            list.add(sum);
        }
        
        if (list.size() == 0) {
            return 0;
        }
        
        if (list.size() == 1) {
            return list.get(0);
        }
        
        if (list.size() == 2) {
            return Math.max(list.get(0), list.get(1));
        }
        
        int[] dp = new int[list.size()];
        dp[0] = list.get(0);
        dp[1] = Math.max(list.get(0), list.get(1));
        
        for (int i = 2; i < list.size(); i++) {
            dp[i] = Math.max(dp[i - 2] + list.get(i), dp[i - 1]);
        }
        
        return dp[list.size() - 1];
    }
}
```
## 解法
### 思路
其实题目是要求一个树形dp，所以通过dfs后序遍历来获得左右子树的dp值，然后在根节点进行状态转移方程的计算
- dp[]值中两个元素，分别是当前节点抢劫和当前节点不抢劫的状况：
    - 不抢劫：dp[0] = Math.max(左子树dp[0], 左子树dp[1]) + Math.max(右子树dp[0], 右子树dp[1])，当前节点不抢劫，所以下一个节点无所谓抢不抢，求两种情况的最大值就可以
    - 抢劫：dp[1] = 左子树dp[0] + 右子树dp[0] + 当前节点值val，如果抢了，下一个节点就不能抢了，只能求两个子树不抢的状态，加上当前值
- 最后返回结果的第1和第2元素中的最大值
### 代码
```java
class Solution {
    public int rob(TreeNode root) {
        int[] ans = dfs(root);
        return Math.max(ans[0], ans[1]);
    }

    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);
        
        int[] cur = new int[2];
        cur[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        cur[1] = left[0] + right[0] + node.val;
        
        return cur;
    }
}
```