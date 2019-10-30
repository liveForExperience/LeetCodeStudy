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

```