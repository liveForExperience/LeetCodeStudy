# LeetCode_275_H指数II
## 题目
给定一位研究者论文被引用次数的数组（被引用次数是非负整数），数组已经按照 升序排列 。编写一个方法，计算出研究者的 h 指数。

h 指数的定义: “h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （N 篇论文中）总共有 h 篇论文分别被引用了至少 h 次。（其余的 N - h 篇论文每篇被引用次数不多于 h 次。）"

示例:
```
输入: citations = [0,1,3,5,6]
输出: 3 
解释: 给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 0, 1, 3, 5, 6 次。
     由于研究者有 3 篇论文每篇至少被引用了 3 次，其余两篇论文每篇被引用不多于 3 次，所以她的 h 指数是 3。

说明:

如果 h 有多有种可能的值 ，h 指数是其中最大的那个。
```
进阶：
```
这是 H 指数 的延伸题目，本题中的 citations 数组是保证有序的。
你可以优化你的算法到对数时间复杂度吗？
```
## 解法
### 思路
解法和274相同
### 代码
```java
class Solution {
    public int hIndex(int[] citations) {
        int len = citations.length, i = 0;
        while (i < len && citations[len - 1 - i] > i) {
            i++;
        }
        
        return i;
    }
}
```
## 解法二
### 思路
二分查找：
- 找到h指数的最理想状态：`citations[i] == len - i`，说明达到引用次数的论文数与引用次数完全相等，如果引用数再大一点，就没有足够的论文了
- 找到h指数次理想的状态：`citations[i] < len - i`，说明当前拥有该引用数的论文比引用数多，那么可能存在更大的引用数，但是结果更大的引用数对应的论文数不够了，那么就以当前值为结果
- 综上所述，可以将这个寻找的过程转换成二分查找：
    - 计算中间值`mid`，判断`citations[mid]`与`len - mid`之间的关系
        - 如果相等，直接返回`len - mid`
        - 如果小于，则找右边，可能还有更大值
        - 如果大于，则找左边，当前引用数过大了
- 最终如果循环内没有找到，则返回次优解，也就是`len - mid`
### 代码
```java
class Solution {
    public int hIndex(int[] citations) {
        int len = citations.length;
        int mid, head = 0, tail = len - 1;

        while (head <= tail) {
            mid = head + (tail - head) / 2;

            if (citations[mid] == len - mid) {
                return len - mid;
            } else if (citations[mid] < len - mid) {
                head = mid + 1;
            } else {
                tail = mid - 1;
            }
        }

        return len - head;
    }
}
```
# LeetCode_968_监控二叉树
## 题目
给定一个二叉树，我们在树的节点上安装摄像头。

节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。

计算监控树的所有节点所需的最小摄像头数量。

示例 1：
```
输入：[0,0,null,0,0]
输出：1
解释：如图所示，一台摄像头足以监控所有节点。
```
示例 2：
```
输入：[0,0,null,0,null,0,null,null,0]
输出：2
解释：需要至少两个摄像头来监视树的所有节点。 上图显示了摄像头放置的有效位置之一。
```
提示：
```
给定树的节点数的范围是 [1, 1000]。
每个节点的值都是 0。
```
## 解法
### 思路
贪心算法：
- 如果叶子节点上放camera，能够监控到自身和父节点
- 如果叶子节点的父节点放camera，能够监控到自身，叶子节点，叶子节点的兄弟节点
- 第二种方法好于第一种
- 所以从底部向上，只给叶子节点的父节点设置camera，然后删除被覆盖的节点
- 继续之前的逻辑
- 过程：
- dfs
- 如果当前节点为叶子节点，设置为0返回
- 如果当前节点的左右子节点有叶子节点，camera计数，并返回1
- 如果当前节点的子节点没有叶子节点，且有设置camera的节点，返回2，代表已经被监控
- 最后返回camera数量，同时判断root节点是否是0，如果是，说明root还需要设置camera来监控自己
### 代码
```java
class Solution {
    int camera = 0;
    public int minCameraCover(TreeNode root) {
        return (dfs(root) == 0 ? 1 : 0) + camera;
    }
    
    private int dfs(TreeNode node) {
        if (node == null) {
            return 2;
        }
        
        int left = dfs(node.left), right = dfs(node.right);
        
        if (left == 0 || right == 0) {
            camera++;
            return 1;
        }
        
        return left == 1 || right == 1 ? 2 : 0;
    }
}
```
# LeetCode_276_栅栏涂色
## 题目
有 k 种颜色的涂料和一个包含 n 个栅栏柱的栅栏，每个栅栏柱可以用其中一种颜色进行上色。

你需要给所有栅栏柱上色，并且保证其中相邻的栅栏柱 最多连续两个 颜色相同。然后，返回所有有效涂色的方案数。

注意:
```
n 和k 均为非负的整数。
```
示例:
```
输入: n = 3，k = 2
输出: 6
解析: 用 c1 表示颜色 1，c2 表示颜色 2，所有可能的涂色方案有:

            柱 1    柱 2   柱 3     
 -----      -----  -----  -----       
   1         c1     c1     c2 
   2         c1     c2     c1 
   3         c1     c2     c2 
   4         c2     c1     c1  
   5         c2     c1     c2
   6         c2     c2     c1
```
## 解法
### 思路
动态规划：
- `dp[n]`：第n个栅栏可以涂色的可能个数
- 状态转移方程：
    - `dp[i - 1] * (k - 1) + dp[i - 2] * (k - 1)`
    - `dp[i - 1] * (k - 1)`：和前一个栅栏不是同一个颜色的可能状态
    - `dp[i - 2] * (k - 1)`：和前一个栅栏是同一个颜色，则和前2个栅栏不是同一个颜色，这样情况的颜色可能状态
    - 如上两个当前栅栏可以涂成的颜色，组成的可能个数
- base case：
    - `dp[1] = k` 
    - `dp[2] = k * k`
- 返回结果：`dp[n]`
### 代码
```java
class Solution {
    public int numWays(int n, int k) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (i == 1) {
                dp[i] = k;
            } else if (i == 2) {
                dp[i] = dp[i - 1] * k;
            } else {
                dp[i] = dp[i - 1] * (k - 1) + dp[i - 2] * (k - 1);
            }

        }

        return dp[n];
    }
}
```
# LeetCode_277_搜寻名人
## 题目
假设你是一个专业的狗仔，参加了一个 n 人派对，其中每个人被从 0 到 n - 1 标号。在这个派对人群当中可能存在一位 “名人”。所谓 “名人” 的定义是：其他所有 n - 1 个人都认识他/她，而他/她并不认识其他任何人。

现在你想要确认这个 “名人” 是谁，或者确定这里没有 “名人”。而你唯一能做的就是问诸如 “A 你好呀，请问你认不认识 B呀？” 的问题，以确定 A 是否认识 B。你需要在（渐近意义上）尽可能少的问题内来确定这位 “名人” 是谁（或者确定这里没有 “名人”）。

在本题中，你可以使用辅助函数 bool knows(a, b) 获取到 A 是否认识 B。请你来实现一个函数 int findCelebrity(n)。

派对最多只会有一个 “名人” 参加。若 “名人” 存在，请返回他/她的编号；若 “名人” 不存在，请返回 -1。

示例 1:
```
输入: graph = [
  [1,1,0],
  [0,1,0],
  [1,1,1]
]
输出: 1
解析: 有编号分别为 0、1 和 2 的三个人。graph[i][j] = 1 代表编号为 i 的人认识编号为 j 的人，而 graph[i][j] = 0 则代表编号为 i 的人不认识编号为 j 的人。“名人” 是编号 1 的人，因为 0 和 2 均认识他/她，但 1 不认识任何人。
```
示例 2:
```
输入: graph = [
  [1,0,1],
  [1,1,0],
  [0,1,1]
]
输出: -1
解析: 没有 “名人”
```
注意:
```
该有向图是以邻接矩阵的形式给出的，是一个 n × n 的矩阵， a[i][j] = 1 代表 i 与 j 认识，a[i][j] = 0 则代表 i 与 j 不认识。
请记住，您是无法直接访问邻接矩阵的。
```
## 解法
### 思路
- 初始化`i = 0`，然后跳过0，从1开始遍历(n - 1)个数。
- i的作用是作为名人的候选人
- 判断`knows(i, j)`
    - 如果是true：说明i肯定不是名人，j有可能是，这时使`i = j`
    - 如果是false，说明j肯定不是名人，i有可能是，继续循环
- 因为最多只能有一个名人，所以循环结束后，i对应的坐标就作为名人候选人，再循环一次做最后的确认
    - 如果`knows(i, x)`是true，说明i不是名人，返回-1
    - 如果`knows(x, i)`是false，说明i不是名人，返回-1
- 如果第二次循环结束，就返回i作为结果
### 代码
```java
/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class Solution extends Relation {
    public int findCelebrity(int n) {
        int candidate = 0;
        for (int i = 1; i < n; i++) {
            if (knows(candidate, i)) {
                candidate = i;
            }
        }
        
        for (int i = 0; i < n; i++) {
            if (i == candidate) {
                continue;
            }
            
            if (knows(candidate, i)) {
                return -1;
            }
            
            if (!knows(i, candidate)) {
                return -1;
            }
        }
        
        return candidate;
    }
}
```
# LeetCode_280_摆动排序
## 题目
给你一个无序的数组 nums, 将该数字 原地 重排后使得 nums[0] <= nums[1] >= nums[2] <= nums[3]...。

示例:
```
输入: nums = [3,5,2,1,6,4]
输出: 一个可能的解答是 [3,5,1,6,2,4]
```
## 解法
### 思路
- 拷贝并排序生成order
- 生成头尾指针`head`和`tail`，用于标记order
- 然后从头开始，`head`递增，`tail`递减地相向重新生成数组，知道`head`和`tail`相遇
### 代码
```java
class Solution {
    public void wiggleSort(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return;
        }
        
        int[] copy = Arrays.copyOf(nums, len);
        quickSort(copy, 0, len - 1);
        int head = 0, tail = len - 1;
        
        int i = 0;
        while (head <= tail) {
            if (head == tail) {
                nums[i] = copy[head++];
            } else {
                nums[i++] = copy[head++];
                nums[i++] = copy[tail--];
            }
        }
    }
    private void quickSort(int[] nums, int head, int tail) {
        if (head >= tail) {
            return;
        }
        int index = partition(nums, head, tail);

        quickSort(nums, head, index - 1);
        quickSort(nums, index + 1, tail);
    }

    private int partition(int[] nums, int head, int tail) {
        int pivot = nums[head];

        while (head < tail) {
            while (head < tail && nums[tail] >= pivot) {
                tail--;
            }

            nums[head] = nums[tail];

            while (head < tail && nums[head] <= pivot) {
                head++;
            }

            nums[tail] = nums[head];
        }

        nums[head] = pivot;
        return head;
    }
}
```
## 解法二
### 思路
- 排序
- 从第二个元素(下标1)开始，和后面一个元素互换
### 代码
```java
class Solution {
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length - 1; i += 2) {
            int tmp = nums[i];
            nums[i] = nums[i + 1];
            nums[i + 1] = tmp;
        }
    }
}
```
## 解法三
### 思路
- 一次遍历：
    - 如果是奇数位，且比后一个元素大，交换
    - 如果是偶数位，且比后一个元素小，交换
### 代码
```java
class Solution {
    public void wiggleSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if ((i % 2 == 0) == (nums[i] > nums[i + 1])) {
                int tmp = nums[i];
                nums[i] = nums[i + 1];
                nums[i + 1] = tmp;
            }
        }
    }
}
```
# LeetCode_281_锯齿迭代器
## 题目
给出两个一维的向量，请你实现一个迭代器，交替返回它们中间的元素。

示例:
```
输入:
v1 = [1,2]
v2 = [3,4,5,6] 

输出: [1,3,2,4,5,6]

解析: 通过连续调用 next 函数直到 hasNext 函数返回 false，
     next 函数返回值的次序应依次为: [1,3,2,4,5,6]。
拓展：假如给你 k 个一维向量呢？你的代码在这种情况下的扩展性又会如何呢?
```
拓展声明：
```
 “锯齿” 顺序对于 k > 2 的情况定义可能会有些歧义。所以，假如你觉得 “锯齿” 这个表述不妥，也可以认为这是一种 “循环”。例如：

输入:
[1,2,3]
[4,5,6,7]
[8,9]

输出: [1,4,8,2,5,9,3,6,7].
```
## 解法
### 思路
- 将两个数组按交叉顺序放入一个数组中
- 使用下标作为是否还有下个元素的判断
### 代码
```java
public class ZigzagIterator {
    private int[] arr;
    private int i = 0;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        arr = new int[v1.size() + v2.size()];
        int i1 = 0, i2 = 0, i = 0;
        while (i1 < v1.size() || i2 < v2.size()) {
            if (i1 < v1.size()) {
                arr[i++] = v1.get(i1++);
            }

            if (i2 < v2.size()) {
                arr[i++] = v2.get(i2++);
            }
        }
    }

    public int next() {
        return arr[i++];
    }

    public boolean hasNext() {
        return i < arr.length;
    }
}
```
# LeetCode_282_给表达式添加运算符
## 题目
给定一个仅包含数字 0-9 的字符串和一个目标值，在数字之间添加二元运算符（不是一元）+、- 或 * ，返回所有能够得到目标值的表达式。

示例 1:
```
输入: num = "123", target = 6
输出: ["1+2+3", "1*2*3"] 
```
示例 2:
```
输入: num = "232", target = 8
输出: ["2*3+2", "2+3*2"]
```
示例 3:
```
输入: num = "105", target = 5
输出: ["1*0+5","10-5"]
```
示例 4:
```
输入: num = "00", target = 0
输出: ["0+0", "0-0", "0*0"]
```
示例 5:
```
输入: num = "3456237490", target = 9191
输出: []
```
## 解法
### 思路
回溯
- 使用StringBuilder记录可能的组合，并对其进行回溯的相关操作
- 计算`+`和`-`时，只需要记录之前的结果，再与当前层产生的数值基于符号进行计算即可，然后不断下钻，直到遍历到字符串最后
- 但增加了`*`后，因为有了优先级的概念，则必须优先计算`*`，所以如果当前层要与之前的结果进行相乘，必须先让结果`eval`与上一次`+`或`-`的操作数`pre`相减，然后再加上`pre * num`才行
- 而如果之前的操作数对应的是符号`*`，则这个`pre`的值应该是上一层的pre再乘以上一层的num得到的
- 所以在出现`*`后，回溯过程中不仅需要有上一层计算的结果，还需要有`pre`代表最近的非`*`对应的操作数，这个操作数可能是累乘得到的
- 还要注意，在每一层生成当前`num`时，需要注意起始值为`0`的情况，这种数字是不合法的
### 代码
```java
class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> ans = new ArrayList<>();
        dfs(num, target, ans, new StringBuilder(), 0, 0, 0);
        return ans;
    }

    private void dfs(String num, int target, List<String> ans, StringBuilder sb, int start, long eval, long pre) {
        if (start == num.length()) {
            if (eval == target) {
                ans.add(sb.toString());
            }
            return;
        }

        for (int i = start; i < num.length(); i++) {
            if (num.charAt(start) == '0' && i > start) {
                break;
            }

            long cur = Long.parseLong(num.substring(start, i + 1));
            int len = sb.length();
            
            if (start == 0) {
                dfs(num, target, ans, sb.append(cur), i + 1, cur, cur);
                sb.setLength(len);
            } else {
                dfs(num, target, ans, sb.append("+").append(cur), i + 1, eval + cur, cur);
                sb.setLength(len);

                dfs(num, target, ans, sb.append("-").append(cur), i + 1, eval - cur, -cur);
                sb.setLength(len);

                dfs(num, target, ans, sb.append("*").append(cur), i + 1, eval - pre + pre * cur, pre * cur);
                sb.setLength(len);
            }
        }
    }
}
```