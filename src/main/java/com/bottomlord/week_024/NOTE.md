# LeetCode_858_镜面反射
## 题目
有一个特殊的正方形房间，每面墙上都有一面镜子。除西南角以外，每个角落都放有一个接受器，编号为 0， 1，以及 2。

正方形房间的墙壁长度为 p，一束激光从西南角射出，首先会与东墙相遇，入射点到接收器 0 的距离为 q 。

返回光线最先遇到的接收器的编号（保证光线最终会遇到一个接收器）。

示例：
```
输入： p = 2, q = 1
输出： 2
解释： 这条光线在第一次被反射回左边的墙时就遇到了接收器 2 。
```
提示：
```
1 <= p <= 1000
0 <= q <= p
```
## 解法
### 思路
- 如果没有顶部的墙，则光每次反射在纵向上都会走长度为`q`的距离，而如果要达到接收器，那纵向的距离就是`p`的整数倍
- 所以结果就是求`p`和`q`的最小公倍数`k`
    - 求最小公倍数通过：
        - 求两数的最大公约数`gcd`
        - 最小公倍数：`p * q / gcd`
    - 通过`k / p`的值的奇偶性来判断是：
        - 偶数：南墙，因为南墙之后一个接收器，所以是东南的接收器`0`
        - 奇数：北墙的两个接收器`1`或`2`
    - 通过`k / q`的值的奇偶性来判断是：
        - 偶数：西北的接收器`2`
        - 奇数：东北的接收器`1`
### 代码
```java
class Solution {
    public int mirrorReflection(int p, int q) {
        int lcm = p * q / gcd(p, q), y = lcm / p, x = lcm / q;
        if ((y & 1) == 0) {
            return 0;
        }
        
        return (x & 1) == 1 ? 1 : 2;
    }

    private int gcd(int p, int q) {
        return q == 0 ? p : gcd(q, p % q);
    }
}
```
# LeetCode_228_1_汇总区间
## 题目
给定一个无重复元素的有序整数数组，返回数组区间范围的汇总。

示例 1:
```
输入: [0,1,2,4,5,7]
输出: ["0->2","4->5","7"]
解释: 0,1,2 可组成一个连续的区间; 4,5 可组成一个连续的区间。
```
示例 2:
```
输入: [0,2,3,4,6,8,9]
输出: ["0","2->4","6","8->9"]
解释: 2,3,4 可组成一个连续的区间; 8,9 可组成一个连续的区间。
```
## 解法
### 思路
- 定义变量：
    - `start`：起始数字，初始化为第1个元素
    - `pre`：上一个数字，初始化为第1个元素
    - `ans`：字符串list
- 遍历数组
    - 从第2个元素开始，判断是否比`pre`大1
        - 是：pre变为当前值，继续遍历
        - 否：生成字符串放入`ans`，`start`和`pre`变为当前值
- 遍历结束，将`start`和`pre`生成对应字符串放入`ans`
### 代码
```java
class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }

        int start = nums[0], pre = start;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - 1 != pre) {
                if (start == pre) {
                    ans.add("" + start);
                } else {
                    ans.add(start + "->" + pre);
                }

                start = nums[i];
            }
            pre = nums[i];
        }
        
        if (start == pre) {
            ans.add("" + start);
        } else {
            ans.add(start + "->" + pre);
        }
        
        return ans;
    }
}
```
# LeetCode_623_在二叉树中增加一行
## 题目
给定一个二叉树，根节点为第1层，深度为 1。在其第 d 层追加一行值为 v 的节点。

添加规则：给定一个深度值 d （正整数），针对深度为 d-1 层的每一非空节点 N，为 N 创建两个值为 v 的左子树和右子树。

将 N 原先的左子树，连接为新节点 v 的左子树；将 N 原先的右子树，连接为新节点 v 的右子树。

如果 d 的值为 1，深度 d - 1 不存在，则创建一个新的根节点 v，原先的整棵树将作为 v 的左子树。

示例 1:
```
输入: 
二叉树如下所示:
       4
     /   \
    2     6
   / \   / 
  3   1 5   

v = 1

d = 2

输出: 
       4
      / \
     1   1
    /     \
   2       6
  / \     / 
 3   1   5   
```
示例 2:
```
输入: 
二叉树如下所示:
      4
     /   
    2    
   / \   
  3   1    

v = 1

d = 3

输出: 
      4
     /   
    2
   / \    
  1   1
 /     \  
3       1
```
注意:
```
输入的深度值 d 的范围是：[1，二叉树最大深度 + 1]。
输入的二叉树至少有一个节点。
```
## 解法
### 思路
- bfs遍历二叉树
- 记录层数
- 当达到`d - 1`层时，生成一层值为v的节点作为`d - 1`的左右子树
- 再将`d - 1`层原来的所有子树按题目要求置于`d`之下
- 注意：`d`为1时，在第一层插入`v`节点的情况
### 代码
```java
class Solution {
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (root == null) {
            return null;
        }
        
        Queue<TreeNode> queue = new ArrayDeque<>();
        int level = 0;
        if (d == 1) {
            TreeNode node = new TreeNode(v);
            node.left = root;
            return node;
        }

        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int count = queue.size();
            if (++level == d - 1) {
                while (count-- > 0) {
                    TreeNode node = queue.poll();
                    if (node == null) {
                        continue;
                    }
                    
                    TreeNode left = node.left, right = node.right, leftV = new TreeNode(v), rightV = new TreeNode(v);
                    
                    node.left = leftV;
                    node.right = rightV;
                    leftV.left = left;
                    rightV.right = right;
                    
                    if (left != null) {
                        queue.offer(left);
                    }
                    
                    if (right != null) {
                        queue.offer(right);
                    }
                }
            } else {
                while (count-- > 0) {
                    TreeNode node = queue.poll();
                    if (node == null) {
                        continue;
                    }
                    
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }
            }
        }
        
        return root;
    }
}
```
# LeetCode_449_序列化和反序列化二叉搜索树
## 题目
序列化是将数据结构或对象转换为一系列位的过程，以便它可以存储在文件或内存缓冲区中，或通过网络连接链路传输，以便稍后在同一个或另一个计算机环境中重建。

设计一个算法来序列化和反序列化二叉搜索树。 对序列化/反序列化算法的工作方式没有限制。 您只需确保二叉搜索树可以序列化为字符串，并且可以将该字符串反序列化为最初的二叉搜索树。

编码的字符串应尽可能紧凑。

注意：不要使用类成员/全局/静态变量来存储状态。 你的序列化和反序列化算法应该是无状态的。
## 解法
### 思路
- 序列化：dfs后序遍历生成以` `分隔的字符串
- 反序列化：
    - 通过字符串生成升序的中序遍历
    - 通过中序和后序序列生成二叉树
- 后序遍历生成的序列，从序列尾部开始，就是从二叉搜索树的根节点开始的先右再左的遍历过程的记录。所以生成的过程也就是：
    - 从序列尾部开始，生成根节点
    - 再将以这个值为序列的最小值，递归到下一层去生成这个节点的右节点
    - 再通过同样方法，将当前值作为最大值，递归到下一层去生成这个节点的左节点
    - 最后返回当前节点
    - 这个过程中，在递归时，可以将保存序列的集合的当前值从集合中删去，方便下一层直接从集合尾部取到需要的值
### 代码
```java
public class Codec {
    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }
        
        StringBuilder sb = new StringBuilder();
        dfs(root, sb);
        return sb.toString().trim();
    }

    private void dfs(TreeNode node, StringBuilder sb) {
        if (node == null) {
            return;
        }

        dfs(node.left, sb);
        dfs(node.right, sb);

        sb.append(" ").append(node.val);
    }

    public TreeNode deserialize(String data) {
        if (data == null) {
            return null;
        }

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (String num : data.split(" ")) {
            queue.offer(Integer.valueOf(num));
        }

        return getTree(Integer.MIN_VALUE, Integer.MAX_VALUE, queue);
    }

    private TreeNode getTree(Integer low, Integer high, ArrayDeque<Integer> queue) {
        if (queue.isEmpty()) {
            return null;
        }

        int val = queue.getLast();
        
        if (val < low || val > high) {
            return null;
        }
        
        queue.removeLast();
        
        TreeNode node = new TreeNode(val);
        node.right = getTree(val, high, queue);
        node.left = getTree(low, val, queue);
        
        return node;
    }
}
```
# LeetCode_963_最小面积矩形II
## 题目
给定在 xy 平面上的一组点，确定由这些点组成的任何矩形的最小面积，其中矩形的边不一定平行于 x 轴和 y 轴。

如果没有任何矩形，就返回 0。

示例 1：
```
输入：[[1,2],[2,1],[1,0],[0,1]]
输出：2.00000
解释：最小面积的矩形出现在 [1,2],[2,1],[1,0],[0,1] 处，面积为 2。
```
示例 2：
```
输入：[[0,1],[2,1],[1,1],[1,0],[2,0]]
输出：1.00000
解释：最小面积的矩形出现在 [1,0],[1,1],[2,1],[2,0] 处，面积为 1。
```
示例 3：
```
输入：[[0,3],[1,2],[3,1],[1,3],[2,1]]
输出：0
解释：没法从这些点中组成任何矩形。
```
示例 4：
```
输入：[[3,1],[1,1],[0,1],[2,1],[3,3],[3,2],[0,2],[2,3]]
输出：2.00000
解释：最小面积的矩形出现在 [2,1],[2,3],[3,3],[3,1] 处，面积为 2。
```
提示：
```
1 <= points.length <= 50
0 <= points[i][0] <= 40000
0 <= points[i][1] <= 40000
所有的点都是不同的。
与真实值误差不超过 10^-5 的答案将视为正确结果。
```
## 解法
### 思路
- 形成一个矩形的充分必要条件：
    - 两条对角线的中点相同
    - 端点到中点距离也相同
- 嵌套遍历点数组
    - 外层遍历一个点
    - 内层遍历外层之后的所有点
    - 计算两个点之间的距离以及中点坐标
    - 生成一个嵌套的hash表，外层通过距离分类，内层通过中点坐标分类，存放的是外层遍历到的点
- 遍历生成的hash表
    - 找到长度和中点相等的两个点
    - 通过中点求得第三个点的值`P' = 2 * center - P`
    - 通过三个点求得矩形面，并和暂存得最小值进行比较
- 返回最小值
### 代码
```java
class Solution {
    public double minAreaFreeRect(int[][] points) {
        int len = points.length;
        Point[] arr = new Point[len];
        for (int i = 0; i < len; i++) {
            arr[i] = new Point(points[i][0], points[i][1]);
        }

        Map<Integer, Map<Point, List<Point>>> seen = new HashMap<>();
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                Point center = new Point(arr[i].x + arr[j].x, arr[i].y + arr[j].y);

                int distance = (arr[i].x - arr[j].x) * (arr[i].x - arr[j].x) + (arr[i].y - arr[j].y) * (arr[i].y - arr[j].y);
                if (!seen.containsKey(distance)) {
                    seen.put(distance, new HashMap<>());
                }

                if (!seen.get(distance).containsKey(center)) {
                    seen.get(distance).put(center, new ArrayList<>());
                }

                seen.get(distance).get(center).add(arr[i]);
            }
        }

        double min = Double.MAX_VALUE;
        for (Map<Point, List<Point>> map : seen.values()) {
            for (Point center : map.keySet()) {
                List<Point> candidates = map.get(center);
                int l = candidates.size();

                for (int i = 0; i < l; i++) {
                    for (int j = i + 1; j < l; j++) {
                        Point a = candidates.get(i);
                        Point b = candidates.get(j);
                        Point c = new Point(center);

                        c.translate(-b.x, -b.y);
                        double area = a.distance(b) * a.distance(c);
                        min = Math.min(min, area);
                    }
                }
            }
        }
        
        return min < Double.MAX_VALUE ? min : 0;
    }
}
```
# LeetCode_1011_在D天内送达包裹的能力
## 题目
传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。

传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。

返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。

示例 1：
```
输入：weights = [1,2,3,4,5,6,7,8,9,10], D = 5
输出：15
解释：
船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
第 1 天：1, 2, 3, 4, 5
第 2 天：6, 7
第 3 天：8
第 4 天：9
第 5 天：10

请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) 是不允许的。 
```
示例 2：
```
输入：weights = [3,2,2,4,1,4], D = 3
输出：6
解释：
船舶最低载重 6 就能够在 3 天内送达所有包裹，如下所示：
第 1 天：3, 2
第 2 天：2, 4
第 3 天：1, 4
```
示例 3：
```
输入：weights = [1,2,3,1,1], D = 4
输出：3
解释：
第 1 天：1
第 2 天：2
第 3 天：3
第 4 天：1, 1
```
提示：
```
1 <= D <= weights.length <= 50000
1 <= weights[i] <= 500
```
## 解法
### 思路
- 遍历获得数组中的最大值，这个值是传送带运载值的最小值`min`
- 从`min`开始尝试是否能在D天内完成运输任务，直到第一个能成功的值出现
- 返回这个值
### 代码
```java
class Solution {
    public int shipWithinDays(int[] weights, int D) {
        int num = Integer.MIN_VALUE;
        for (int weight : weights) {
            num = Math.max(weight, num);
        }

        while (true) {
            if (isValid(weights, num, D)) {
                break;
            } else {
                num++;
            }
        }

        return num;
    }

    private boolean isValid(int[] weights, int num, int day) {
        int cur = num;

        for (int i = 0; i < weights.length;) {
            if (weights[i] > num) {
                return false;
            }

            if (cur - weights[i] < 0) {
                cur = num;
                day--;
            } else {
                cur -= weights[i];
                i++;
            }

            if (day <= 0) {
                return false;
            }
        }

        return true;
    }
}
```
## 优化代码
### 思路
在解法一的基础上，使用二分搜索来降低时间复杂度
- 因为搜索空间的下界是数组中的最大值，而上界则是整个数组的和
- 所以可以通过一次遍历求得这两个值
- 然后通过二分搜索来进行判断，找到最小的那个符合规则的值
    - 如果中间值符合要求，则表示当前值是大于等于最小值得数
    - 如果中间值不符合，则表示，中间值左边的所有数都不符合要求
    - 当`head`不再小于`tail`，说明之前找到值的左侧所有值都已经被检查过，且都不符合要求
### 代码
```java
class Solution {
    public int shipWithinDays(int[] weights, int D) {
        int max = Integer.MIN_VALUE, sum = 0;
        for (int weight : weights) {
            sum += weight;
            max = Math.max(max, weight);
        }

        int head = max, tail = sum;
        while (head < tail) {
            int mid = head + (tail - head) / 2;

            if (isValid(weights, mid, D)) {
                tail = mid;
            } else {
                head = mid + 1;
            }
        }

        return head;
    }

    private boolean isValid(int[] weights, int num, int day) {
        int cur = num;

        for (int i = 0; i < weights.length;) {
            if (weights[i] > num) {
                return false;
            }

            if (cur - weights[i] < 0) {
                cur = num;
                day--;
            } else {
                cur -= weights[i];
                i++;
            }

            if (day <= 0) {
                return false;
            }
        }

        return true;
    }
}
```