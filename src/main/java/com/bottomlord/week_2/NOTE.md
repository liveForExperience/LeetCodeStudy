# LeetCode_344_反转字符串
## 题目
编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。

不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。

你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。

示例 1：
```
输入：["h","e","l","l","o"]
输出：["o","l","l","e","h"]
```
示例 2：
```
输入：["H","a","n","n","a","h"]
输出：["h","a","n","n","a","H"]
```
## 解法一
### 思路
- 头尾双指针遍历
- 互换
### 代码
```java
class Solution {
    public void reverseString(char[] s) {
        int head = 0, tail = s.length - 1;
        while (head < tail) {
            char tmp = s[head];
            s[head++] = s[tail];
            s[tail--] = tmp;
        }
    }
}
```
## 解法二
### 思路
使用异或位运算替换解法一的换位逻辑，实现原地换位。
### 代码
```java
class Solution {
    public void reverseString(char[] s) {
        int head = 0, tail = s.length - 1;
        while (head < tail) {
            s[head] ^= s[tail];
            s[tail] ^= s[head];
            s[head++] ^= s[tail--];
        }
    }
}
```
# LeetCode_933_最近的请求次数
## 题目
写一个 RecentCounter 类来计算最近的请求。

它只有一个方法：ping(int t)，其中 t 代表以毫秒为单位的某个时间。

返回从 3000 毫秒前到现在的 ping 数。

任何处于 [t - 3000, t] 时间范围之内的 ping 都将会被计算在内，包括当前（指 t 时刻）的 ping。

保证每次对 ping 的调用都使用比之前更大的 t 值。

示例：
```
输入：inputs = ["RecentCounter","ping","ping","ping","ping"], inputs = [[],[1],[100],[3001],[3002]]
输出：[null,1,2,3,3]
```
提示：
```
每个测试用例最多调用 10000 次 ping。
每个测试用例会使用严格递增的 t 值来调用 ping。
每次调用 ping 都有 1 <= t <= 10^9。
```
## 解法
### 思路
- 用一个队列存储请求时的t参数
- 循环判断t与队列第一个元素的差：
   - 如果大于3000，不符合题意，出队
   - 否则结束循环
- 将当前t元素入队
- 返回队列长度

整个队列只储存符合题目要求的3000毫秒时间段内的元素。
### 代码
```java
class RecentCounter {
    private Queue<Integer> queue;
    public RecentCounter() {
        this.queue = new ArrayDeque<>();
    }

    public int ping(int t) {
        while (!this.queue.isEmpty()) {
            if (t - this.queue.peek() > 3000) {
                this.queue.poll();
            } else {
                break;
            }
        }

        this.queue.offer(t);
        return this.queue.size();
    }
}
```
# LeetCode_944_删列造序
## 题目
给定由 N 个小写字母字符串组成的数组 A，其中每个字符串长度相等。

删除 操作的定义是：选出一组要删掉的列，删去 A 中对应列中的所有字符，形式上，第 n 列为 [A[0][n], A[1][n], ..., A[A.length-1][n]]）。

比如，有 A = ["abcdef", "uvwxyz"]，

要删掉的列为 {0, 2, 3}，删除后 A 为["bef", "vyz"]， A 的列分别为["b","v"], ["e","y"], ["f","z"]。

你需要选出一组要删掉的列 D，对 A 执行删除操作，使 A 中剩余的每一列都是 非降序 排列的，然后请你返回 D.length 的最小可能值。

示例 1：
```
输入：["cba", "daf", "ghi"]
输出：1
```
解释：
```
当选择 D = {1}，删除后 A 的列为：["c","d","g"] 和 ["a","f","i"]，均为非降序排列。
若选择 D = {}，那么 A 的列 ["b","a","h"] 就不是非降序排列了。
```
示例 2：
```
输入：["a", "b"]
输出：0
解释：D = {}
```
示例 3：
```
输入：["zyx", "wvu", "tsr"]
输出：3
解释：D = {0, 1, 2}
```
提示：
```
1 <= A.length <= 100
1 <= A[i].length <= 1000
```
## 解法一
### 思路
有一些记录状态的临时变量
- 记录上一个字符的pre，初始为0
- 记录是否需要计数的flag，初始为false
- 记录计数值的count

嵌套循环
- 外层是字符串字符数组
- 内层是字符串数组
- 如果当前字符比pre小，那么就中断内层的循环，同时将flag置为true，代表回到外层时需要进行计数了，因为找到不符合要求的列了。
- 通过flag的状态，决定是否count++，通过在外层循环的最后将pre和flag置为初始值，为下一列的字符判断做准备。

判断字符串的当前字符是否大于上一个字符，如果符合就计数+1。时间复杂度是O(N²)
### 代码
```java
class Solution {
    public int minDeletionSize(String[] A) {
        char pre = 0;
        boolean flag = false;
        int count = 0; 
        
        for (int i = 0; i < A[0].length(); i++) {
            for (String s : A) {
                if (s.charAt(i) < pre) {
                    flag = true;
                    break;
                }

                pre = s.charAt(i);
            }
            
            if (flag) {
                count++;
            }
            
            flag = false;
            pre = 0;
        }
        
        return count;
    }
}
```
## 解法二
### 思路
解法一中的pre和flag变量可以省去

注意：
- 将迭代器换成普通for循环，使用指针
- 内层循环从1开始，防止字符数组越界
### 代码
```java
class Solution {
    public int minDeletionSize(String[] A) {
        int count = 0;

        for (int i = 0; i < A[0].length(); i++) {
            for (int j = 1; j < A.length; j++) {
                if (A[j].charAt(i) < A[j - 1].charAt(i)) {
                    count++;
                    break;
                }
            }
        }

        return count;
    }
}
```
速度反而比解法一慢了
## 解法三
### 思路
观察了解法二的代码，发现时String的charAt方法比较耗时。应该保留解法一的pre变量，同时变量的位置放在第一层循环开始，这样内层循环结束后就不需要再初始化变量。
### 代码
```java
class Solution {
    public int minDeletionSize(String[] A) {
        int count = 0;

        for (int i = 0; i < A[0].length(); i++) {
            char pre = 0;
            for(String s: A){
                char cur = s.charAt(i);
                
                if (cur < pre) {
                    count++;
                    break;
                }
                
                pre = cur;
            }
        }

        return count;
    }
}
```
# LeetCode_500_键盘行
## 题目
给定一个单词列表，只返回可以使用在键盘同一行的字母打印出来的单词。键盘如下图所示。

示例：
```
输入: ["Hello", "Alaska", "Dad", "Peace"]
输出: ["Alaska", "Dad"]
```
注意：
```
你可以重复使用键盘上同一字符。
你可以假设输入的字符串将只包含字母。
```
## 解法
### 思路
- 一个长度为26的int数组，每一个下标对应一个字母，下标对应的元素为键盘的行数。
- 嵌套遍历字符串数组和字符串的字符数组，通过字符值从数组中找到对应的行数
- 如果有不一致就终止内层循环
- 否则放入结果中
### 代码
```java
class Solution {
    public String[] findWords(String[] words) {
        int[] board = new int[]{2,3,3,2,1,2,2,2,1,2,2,2,3,3,1,1,1,1,2,1,1,3,1,3,1,3};
        List<String> list = new ArrayList<>();
        
        for (String word: words) {
            char[] cs = word.toCharArray();
            int line;
            boolean flag = true;
            
            if (cs[0] < 'a') {
                line = board[cs[0] - 'A'];
            } else {
                line = board[cs[0] - 'a'];
            }
            for (int i = 1; i < cs.length; i++) {
                int cur;
                if (cs[i] < 'a') {
                    cur = board[cs[i] - 'A'];
                } else {
                    cur = board[cs[i] - 'a'];
                }
                
                if (cur != line) {
                    flag = false;
                    break;
                }
            }
            
            if (flag) {
                list.add(word);
            }
        }

        return list.toArray(new String[0]);
    }
}
```
# LeetCode_559_N叉树的最大深度
## 题目

## 解法一
### 思路
使用dfs递归搜索方式，记录子树的深度，返回时比较大小取最大值。

**注意root为空的情况**。
### 代码
```java
class Solution {
    public int maxDepth(Node root) {      
        return root == null ? 0 : dfs(root, 0);
    }

    private int dfs(Node node, int count) {      
        if (node.children.size() == 0) {
            return ++count;
        }

        int depth = 0;
        for (Node child: node.children) {
            int tmp = dfs(child, count + 1);
            depth = depth >=  tmp ? depth: tmp;
        }

        return depth;
    }
}
```
## 优化代码
如上的dfs函数中，count其实不需要传递。
- 解法一是在下钻过程中使用count计数
- 其实可以通过返回时候+1来计数
```java
class Solution {
    public int maxDepth(Node root) {      
        if (root == null) {
            return 0;
        }

        int max = 0;
        for (Node node: root.children) {
            int depth = maxDepth(node);
            max = Math.max(max, depth);
        }
        
        return max + 1;
    }
}
```
## 解法二
### 思路
因为要得到深度，所以通过bfs，可以在进入每一层的时候计一次数。
- 在驱动搜索的循环体中，确保循环一次的逻辑中，将N叉树当前这层所有节点的children都放入队列中，这样在写一个循环时，有且仅有下一层的节点会被处理。
- 而为了达成这个要求，就需要在循环体一开始得到当前层的节点数count，也就是队列的当前长度。然后通过内层嵌套的循环体循环将这一层的所有children放入队列中，内层循环条件就是count是否>0
- 同时在驱动搜索的外层循环中对深度depth+1
- 搜索结束后就返回depth
### 代码
```java
class Solution {
    public int maxDepth(Node root) {      
        int depth = 0;
        
        if (root == null) {
            return depth;
        }

        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int count = queue.size();
            depth++;
            while (count > 0) {
                count--;
                Node node = queue.poll();
                for (Node child: node.children) {
                    queue.offer(child);
                }
            }
        }
        
        return depth;
    }
}
```
# LeetCode_561_数组拆分I
## 题目
给定长度为 2n 的数组, 你的任务是将这些数分成 n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从1 到 n 的 min(ai, bi) 总和最大。

示例 1:
```
输入: [1,4,3,2]
输出: 4
解释: n 等于 2, 最大总和为 4 = min(1, 2) + min(3, 4).
```
提示:
```
n 是正整数,范围在 [1, 10000].
数组中的元素范围在 [-10000, 10000].
```
## 解法一
### 思路
为了得到最大的组合，那么就是临近的两个数为一组
- 数组排序
- 下标为偶数的元素就是每组中最小的数
- 将它们累加返回
### 代码
```java
class Solution {
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        for(int i = 0; i < nums.length; i += 2) {
            ans += nums[i];
        }
        return ans;
    }
}
```
## 解法二
### 思路
使用桶排序，然后一个隔一个的累加：
- 因为数组元素的取值范围时[-10000，10000]，所以数组长度就是20001
- 然后通过下标表示nums的元素值，桶数组的元素值代表nums中值相同元素的个数
- 因为是桶排序，所以累加的过程需要通过一个标志来控制一个隔一个元素的从最小值开始累加。
- 累加时循环的是整个bucket数组，循环体内部再嵌套一个循环，用来处理桶中代表个数的元素
- 内层循环时切换状态和累加时，注意要对桶元素做--的动作，依次处理掉所有nums的值
### 代码
```java
class Solution {
    public int arrayPairSum(int[] nums) {
        int[] bucket = new int[20001];
        for (int num: nums) {
            bucket[num + 10000]++;
        }

        int sum = 0;
        boolean flag = true;

        for (int i = 0; i < 20001; i++) {
            while (bucket[i] > 0) {
                if (flag) {
                    sum += i - 10000;
                }

                flag = !flag;
                bucket[i]--;
            }
        }

        return sum;
    }
}
```
# LeetCode_557_反转字符串中的单词III
## 题目
给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。

示例 1:
```
输入: "Let's take LeetCode contest"
输出: "s'teL ekat edoCteeL tsetnoc" 
注意：在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
```
## 解法一
### 思路
- 根据空格拆分成字符串数组
- 将字符串中的字符镜像反转，用到异或位运算的原地互换技巧
- 用StringBuilder拼接反转的字符串，外加空格
- 最后返回trim后的结果
### 代码
```java
class Solution {
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        for (String word: s.split(" ")) {
            sb.append(reverse(word)).append(" ");
        }
        return sb.toString().trim();
    }
    
    private String reverse(String word) {
        char[] cs = word.toCharArray();
        int left = 0, right = cs.length - 1;
        while (left < right) {
            cs[left] ^= cs[right];
            cs[right] ^= cs[left];
            cs[left++] ^= cs[right--];
        }
        return new String(cs);
    }
}
```
## 解法二
### 思路
- 用一个指针去寻找字符串中的空格
- 字符串最后拼接一个空格，类似遍历的哨兵策略，方便搜索空格的指针定位最后一个字
- 在找到空格后，通过left和right指针来循环处理当前空格之前的那个字，和解法一类似地进行反转操作
- 外层循环结束前，也就是找到空格的这个循环，使left指针指向当前空格下标+1的位置
- 循环结束后返回trim后的字符串，去掉之前尾部增加的空格
### 代码
```java
class Solution {
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder(s);
        sb.append(" ");
        
        char[] cs = sb.toString().toCharArray();
        
        int left = 0;
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] != ' ') {
                continue;
            }
            
            int right = i - 1;
            while (left < right) {
                cs[left] ^= cs[right];
                cs[right] ^= cs[left];
                cs[left++] ^= cs[right--];
            }
            
            left = i + 1;
        }
        
        return new String(cs).trim();
    }
}
```
# LeetCode_961_重复N次的元素
## 题目
在大小为 2N 的数组 A 中有 N+1 个不同的元素，其中有一个元素重复了 N 次。

返回重复了 N 次的那个元素。

示例 1：
```
输入：[1,2,3,3]
输出：3
```
示例 2：
```
输入：[2,1,2,5,3,2]
输出：2
```
示例 3：
```
输入：[5,1,5,2,5,3,5,4]
输出：5
```
提示：
```
4 <= A.length <= 10000
0 <= A[i] < 10000
A.length 为偶数
```
## 解法一
### 思路
对数组排序后，重复的数字一定出现在N-1或N下标中：
- 如果两者相等，选择其一
- 如果不同，选择N-1==N-2和N == N+1中为真的那个
### 代码
```java
class Solution {
    public int repeatedNTimes(int[] A) {
        Arrays.sort(A);
        
        int n = A.length / 2;
        if (A[n - 1] == A[n]) {
            return A[n];
        }
        
        if (A[n - 1] == A[n - 2]) {
            return A[n - 1];
        }
        
        return A[n];
    }
}
```
## 解法二
### 思路
因为整个数组长度是2N，且有N个是重复的数字，所以在遍历数组的时候，一定会在某一刻，遍历到的3个数字中有2个是重复的情况
### 代码
```java
class Solution {
    public int repeatedNTimes(int[] A) {
        for (int i = 2; i < A.length; i++) {
            if (A[i] == A[i - 1] || A[i] == A[i - 2]) {
                return A[i];
            }

            if (A[i - 1] == A[i - 2]) {
                return A[i - 1];
            }
        }

        return A[0];
    }
}
```
# LeetCode_1025_除数博弈
## 题目
爱丽丝和鲍勃一起玩游戏，他们轮流行动。爱丽丝先手开局。

最初，黑板上有一个数字 N 。在每个玩家的回合，玩家需要执行以下操作：

选出任一 x，满足 0 < x < N 且 N % x == 0 。
用 N - x 替换黑板上的数字 N 。
如果玩家无法执行这些操作，就会输掉游戏。

只有在爱丽丝在游戏中取得胜利时才返回 True，否则返回 false。假设两个玩家都以最佳状态参与游戏。

示例 1：
```
输入：2
输出：true
解释：爱丽丝选择 1，鲍勃无法进行操作。
```
示例 2：
```
输入：3
输出：false
解释：爱丽丝选择 1，鲍勃也选择 1，然后爱丽丝无法进行操作。
```
提示：
```
1 <= N <= 1000
```
## 解法
### 思路
- 奇数的除数一定是奇数，相减也是奇数
- 偶数的除数一定是偶数，相减也是偶数
- 谁得到N=2的情况，谁就能获得胜利
- 先手者如果拿到的是偶数，只需使对方得到n为奇数的情况，也就是-1，那么对方永远也无法得到获得胜利的2
- 先手者如果拿到的是奇数，那么就陷入了上一条的对手困境，必输
- 所以判断胜利与否就是判断N的奇偶
### 代码
```java
class Solution {
    public boolean divisorGame(int N) {
        return N % 2 == 0;
    }
}
```
# LeetCode_929_独特的电子邮件地址
## 题目
每封电子邮件都由一个本地名称和一个域名组成，以 @ 符号分隔。

例如，在 alice@leetcode.com中， alice 是本地名称，而 leetcode.com 是域名。

除了小写字母，这些电子邮件还可能包含 '.' 或 '+'。

如果在电子邮件地址的本地名称部分中的某些字符之间添加句点（'.'），则发往那里的邮件将会转发到本地名称中没有点的同一地址。例如，"alice.z@leetcode.com” 和 “alicez@leetcode.com” 会转发到同一电子邮件地址。 （请注意，此规则不适用于域名。）

如果在本地名称中添加加号（'+'），则会忽略第一个加号后面的所有内容。这允许过滤某些电子邮件，例如 m.y+name@email.com 将转发到 my@email.com。 （同样，此规则不适用于域名。）

可以同时使用这两个规则。

给定电子邮件列表 emails，我们会向列表中的每个地址发送一封电子邮件。实际收到邮件的不同地址有多少？

示例：
```
输入：["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
输出：2
解释：实际收到邮件的是 "testemail@leetcode.com" 和 "testemail@lee.tcode.com"。
```
提示：
```
1 <= emails[i].length <= 100
1 <= emails.length <= 100
每封 emails[i] 都包含有且仅有一个 '@' 字符。
```
## 解法
### 思路
- 根据规则解析邮件地址字符串
   - 把邮件字符串根据@拆分成2部分
   - 第1部分的内容去除所有的.
   - 第1部分如果有+号就把字符串截取到第一个+号的位置
   - 把处理好的第1部分和@还有第2部分合并
- 使用set保存
- 返回set长度
### 代码
```java
public class Solution {
    public int numUniqueEmails(String[] emails) {
        Set<String> set = new HashSet<>();

        for (String email: emails) {
            String[] factors = email.split("@");
            String name = factors[0];
            if (name.contains("+")) {
                name = name.substring(0, name.indexOf("+"));
            }
            name = name.replaceAll("\\.", "");
            set.add(name + "@" + factors[1]);
        }

        return set.size();
    }
}
```
## 解法二
### 思路
- 外层循环遍历字符串数组
- 内层循环遍历字符数组，使用StringBuilder来append字符，整个过程需要关注三种状态
- 首先最重要的是@符号，这个状态代表无论字符是什么都要append，使用一个标识符append来记录这个状态
- 否则就要关注另外两种状态：
   - 如果第一次发现了+号，代表着后面除非发现@符号，否则就跳过
   - 如果发现.号，就忽略这个字符
- 内层循环结束，把字符串放入set去重
- 外层循环结束，返回set的长度
### 代码
```java
class Solution {
    public int numUniqueEmails(String[] emails) {
        Set<String> set = new HashSet<>();

        for (String email : emails) {
            boolean ignore = false;
            boolean append = false;
            StringBuilder sb = new StringBuilder();
            for (char c: email.toCharArray()) {
                if (append) {
                    sb.append(c);
                } else {
                    if (c == '@') {
                        sb.append(c);
                        append = true;
                        continue;
                    }

                    if (ignore) {
                        continue;
                    }

                    if (c == '.') {
                        continue;
                    }

                    if (c == '+') {
                        ignore = true;
                        continue;
                    }

                    sb.append(c);
                }
            }

            set.add(sb.toString());
        }

        return set.size();
    }
}
```
# LeetCode_908_最小差值I
## 思路
给定一个整数数组 A，对于每个整数 A[i]，我们可以选择任意 x 满足 -K <= x <= K，并将 x 加到 A[i] 中。

在此过程之后，我们得到一些数组 B。

返回 B 的最大值和 B 的最小值之间可能存在的最小差值。

示例 1：
```
输入：A = [1], K = 0
输出：0
解释：B = [1]
```
示例 2：
```
输入：A = [0,10], K = 2
输出：6
解释：B = [2,8]
```
示例 3：
```
输入：A = [1,3,6], K = 3
输出：0
解释：B = [3,3,3] 或 B = [4,4,4]
```
提示：
```
1 <= A.length <= 10000
0 <= A[i] <= 10000
0 <= K <= 10000
```
## 解法一
### 思路
A数组最大值和最小值之间的差，判断它是否小于2倍的K的绝对值：
- 如果是，就返回0，说明整个数组都可以处理成相同的数
- 如果不是，就返回这两个数之间的差值

时间复杂度是O(NlogN)
### 代码
```java
class Solution {
    public int smallestRangeI(int[] A, int K) {
        Arrays.sort(A);
        int min = A[A.length - 1] - A[0] - 2 * Math.abs(K);
        return min <= 0 ? 0 : min;
    }
}
```
## 解法二
### 思路
不需要排序，只要拿到最大最小值就可以。剩下的还是按照解法一的思路。这样时间复杂度就是O(N)
### 代码
```java
class Solution {
    public int smallestRangeI(int[] A, int K) {
        int min = A[0], max = min;
        for (int i = 1; i < A.length; i++) {
            if (A[i] > max) {
                max = A[i];
            }
            
            if (A[i] < min) {
                min = A[i];
            }
        }
        
        int ans = max - min - 2 * Math.abs(K);
        return ans <= 0 ? 0 : ans;
    }
}
```
# LeetCode_965_单值二叉树
## 题目
如果二叉树每个节点都具有相同的值，那么该二叉树就是单值二叉树。

只有给定的树是单值二叉树时，才返回 true；否则返回 false。

示例 1：
```
输入：[1,1,1,1,1,null,1]
输出：true
```
示例 2：
```
输入：[2,2,2,5,2]
输出：false
```
提示：
```
给定树的节点数范围是 [1, 100]。
每个节点的值都是整数，范围为 [0, 99] 。
```
## 解法一
### 思路
dfs递归
### 代码
```java
class Solution {
    public boolean isUnivalTree(TreeNode root) {
        return dfs(root, root.val);
    }
    
    private boolean dfs(TreeNode node, int val) {
        if (node == null) {
            return true;
        }
        
        if (node.val != val) {
            return false;
        }
        
        return dfs(node.left, val) && dfs(node.right, val);
    }
}
```
## 解法二
### 思路
dfs非递归
### 代码
```java
class Solution {
    public boolean isUnivalTree(TreeNode root) {
        if (root == null) {
            return false;
        }
        
        Stack<TreeNode> stack = new Stack<>();
        int val = root.val;
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.val != val) {
                return false;
            }
            
            if (node.left != null) {
                stack.push(node.left);
            }
            
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        
        return true;
    }
}
```
## 解法三
### 思路
bfs
### 代码
```java
class Solution {
    public boolean isUnivalTree(TreeNode root) {
        if (root == null) {
            return false;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        int val = root.val;
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.val != val) {
                return false;
            }

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        return true;
    }
}
```
# LeetCode_108_将有序数组转成二叉搜索树
## 题目
将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。

本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。

示例:
```
给定有序数组: [-10,-3,0,5,9],

一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：

      0
     / \
   -3   9
   /   /
 -10  5
```
## 解法一
### 思路
为了做到高度平衡，根节点应该选择数组长度中点位置的元素。使用dfs递归来构建。
### 代码
```java
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }

    private TreeNode dfs(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (right - left) / 2 + left;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = dfs(nums, left, mid - 1);
        node.right = dfs(nums, mid + 1, right);
        return node;
    }
}
```
## 解法二
### 思路
同解法一，使用dfs非递归
### 代码
```java
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        Stack<int []> arrStack = new Stack<>();
        arrStack.push(new int[]{0, nums.length - 1});
        
        Stack<TreeNode> nodeStack = new Stack<>();
        TreeNode root = new TreeNode(nums[(nums.length - 1) / 2]);
        nodeStack.push(root);
        
        while (!arrStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            
            int[] arr = arrStack.pop();
            int left = arr[0], right = arr[1];
            int mid = (right - left) / 2 + left;
            
            if (left <= mid - 1) {
                node.left = new TreeNode(nums[(mid - 1 - left) / 2 + left]);    
                nodeStack.push(node.left);
                arrStack.push(new int[]{left, mid - 1});
            }
            
            if (mid + 1 <= right) {
                node.right = new TreeNode(nums[(right - mid - 1) / 2 + mid + 1]);
                nodeStack.push(node.right);
                arrStack.push(new int[]{mid + 1, right});
            }
        }
        
        return root;
    }
}
```
# LeetCode_171_Excel表列序号
## 题目
给定一个Excel表格中的列名称，返回其相应的列序号。

例如，
```
    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 
    ...
```
示例 1:
```
输入: "A"
输出: 1
```
示例 2:
```
输入: "AB"
输出: 28
```
示例 3:
```
输入: "ZY"
输出: 701
```
## 解法
### 思路
26进制和10进制的转换
- 遍历字符数组，定义两个游标
   - 一个用来确定当前位的值
   - 一个用来确定是第几位
- 通过**字符c-'A'**获得10每一位的10进制数，然后通过位数算出是26的几次方
- 把循环体中算出的当前位代表的10进制值循环累加到变量ans
### 代码
```java
class Solution {
    public int titleToNumber(String s) {
       int ans = 0;
        char[] cs = s.toCharArray();
        
        for (int i = 0, bit = cs.length - 1; i < cs.length; i++, bit--) {
            ans += Math.pow(26, bit) * (cs[i] - 'A' + 1);
        }
        
        return ans; 
    }
}
```
# LeetCode_867_转置矩阵
## 题目
给定一个矩阵 A， 返回 A 的转置矩阵。

矩阵的转置是指将矩阵的主对角线翻转，交换矩阵的行索引与列索引。

示例 1：
```
输入：[[1,2,3],[4,5,6],[7,8,9]]
输出：[[1,4,7],[2,5,8],[3,6,9]]
```
示例 2：
```
输入：[[1,2,3],[4,5,6]]
输出：[[1,4],[2,5],[3,6]]
```
提示：
```
1 <= A.length <= 1000
1 <= A[0].length <= 1000
```
## 解法
### 思路
转置就是将元素的1维2维的下标位置互换。
- 新建一个根据原二维数组生成一个转置的二维数组
- 遍历二维数组，赋值给新数组
### 代码
```java
class Solution {
    public int[][] transpose(int[][] A) {
        if (A == null) {
            return null;
        }
        
        int[][] B = new int[A[0].length][A.length];
        
        for (int i = 0 ; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                B[j][i] = A[i][j];
            }
        }

        return B;
    }
}
```
# LeetCode_999_车的可用捕获量
## 题目
在一个 8 x 8 的棋盘上，有一个白色车（rook）。也可能有空方块，白色的象（bishop）和黑色的卒（pawn）。它们分别以字符 “R”，“.”，“B” 和 “p” 给出。大写字符表示白棋，小写字符表示黑棋。

车按国际象棋中的规则移动：它选择四个基本方向中的一个（北，东，西和南），然后朝那个方向移动，直到它选择停止、到达棋盘的边缘或移动到同一方格来捕获该方格上颜色相反的卒。另外，车不能与其他友方（白色）象进入同一个方格。

返回车能够在一次移动中捕获到的卒的数量。

示例 1：
```
输入：[[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".","R",".",".",".","p"],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."]]
输出：3
解释：
在本例中，车能够捕获所有的卒。
```
示例 2：
```
输入：[[".",".",".",".",".",".",".","."],[".","p","p","p","p","p",".","."],[".","p","p","B","p","p",".","."],[".","p","B","R","B","p",".","."],[".","p","p","B","p","p",".","."],[".","p","p","p","p","p",".","."],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."]]
输出：0
解释：
象阻止了车捕获任何卒。
```
示例 3：
```
输入：[[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".","p",".",".",".","."],["p","p",".","R",".","p","B","."],[".",".",".",".",".",".",".","."],[".",".",".","B",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".",".",".",".",".","."]]
输出：3
解释： 
车可以捕获位置 b5，d6 和 f5 的卒。
```
提示：
```
board.length == board[i].length == 8
board[i][j] 可以是 'R'，'.'，'B' 或 'p'
只有一个格子上存在 board[i][j] == 'R'
```
## 解法
### 思路
- 找到车子的位置
- 让车子递归走四个方向，根据不同的状况返回
   - 遇到白象返回0
   - 遇到黑卒返回1
   - 走出棋盘返回0
### 代码
```java
class Solution {
    public int numRookCaptures(char[][] board) {
        int ans = 0;
        int row = -1, col = -1;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'R') {
                    row = i;
                    col = j;
                    break;
                }
            }

            if (row != -1) {
                break;
            }
        }

        for (int i = -1; i < 2; i += 2) {
            ans += recurse(board, row, col, i, 0);
        }

        for (int i = -1; i < 2; i += 2) {
            ans += recurse(board, row, col, 0, i);
        }

        return ans;
    }

    private int recurse(char[][] board, int row, int col, int rowStep, int colStep) {
        if (row > board.length - 1 || row < 0 || col > board[0].length - 1 || col < 0) {
            return 0;
        }

        if (board[row][col] == 'p') {
            return 1;
        }

        if (board[row][col] == 'B') {
            return 0;
        }

        return recurse(board, row + rowStep, col + colStep, rowStep, colStep);
    }
}
```
# LeetCode_922_按奇偶排序数组II
## 题目
给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。

对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。

你可以返回任何满足上述条件的数组作为答案。

示例：
```
输入：[4,2,5,7]
输出：[4,5,2,7]
解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
```
提示：
```
2 <= A.length <= 20000
A.length % 2 == 0
0 <= A[i] <= 1000
```
## 解法一
### 思路
- 有两个数据结构用来存放不匹配的奇偶数
- 有一个数据结构用来放不匹配的下标
- 遍历数组，判断当前元素与下标是否都是奇数或偶数
- 如果不是：
   - 把元素放入对应的数据结构
   - 找是否有现成的匹配元素，有的话就把元素取出，放入数组中
   - 如果没有把下标放入存下标的数据结构
- 遍历结束，检查存下标的数据结构是否为空，不是就按照下标的奇偶性从指定的数据结构中取值赋给制定下标的位置
### 代码
```java
class Solution {
    public int[] sortArrayByParityII(int[] A) {
        Stack<Integer> oddStack = new Stack<>();
        Stack<Integer> evenStack = new Stack<>();
        Stack<Integer> indexStack = new Stack<>();

        for (int i = 0; i < A.length; i++) {
            if (A[i] % 2 == 0 && i % 2 == 1) {
                evenStack.push(A[i]);
                if (!oddStack.isEmpty()) {
                    A[i] = oddStack.pop();
                } else {
                    indexStack.push(i);
                }
            }

            if (A[i] % 2 == 1 && i % 2 == 0) {
                oddStack.push(A[i]);
                if (!evenStack.isEmpty()) {
                    A[i] = evenStack.pop();
                } else {
                    indexStack.push(i);
                }
            }
        }

        while (!indexStack.isEmpty()) {
            int i = indexStack.pop();
            if (i % 2 == 0) {
                A[i] = evenStack.pop();
            } else {
                A[i] = oddStack.pop();
            }
        }

        return A;
    }
}
```
## 解法二
### 思路
- 新建一个新数组
- 遍历原数组，偶数就放在新数组偶数位，反之则放在奇数位。同时对应指针+2
- 最终返回新数组
### 代码
```
class Solution {
    public int[] sortArrayByParityII(int[] A) {
        int[] arr = new int[A.length];
        int even = 0, odd = 1;
        for (int value : A) {
            if (value % 2 == 0) {
                arr[even] = value;
                even += 2;
            } else {
                arr[odd] = value;
                odd += 2;
            }
        }
        
        return arr;
    }
}
```
# LeetCode_349_两数组的交集
## 题目
给定两个数组，编写一个函数来计算它们的交集。

示例 1:
```
输入: nums1 = [1,2,2,1], nums2 = [2,2]
输出: [2]
```
示例 2:
```
输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
输出: [9,4]
```
说明:
```
输出结果中的每个元素一定是唯一的。
我们可以不考虑输出结果的顺序。
```
## 解法一
### 思路
1. 使用set去重第一个数组
2. 遍历第二个数组，有相同的就放入数组，同时把set中对应元素去除
3. 如果set为空或者结束遍历，就返回数组(要截取下，因为一开始预估了一个尽可能小的长度)
### 代码
```java
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        for (int i : nums1) {
            set.add(i);
        }
        
        int len = nums1.length < nums2.length ? nums1.length : nums2.length;
        int[] arr = new int[len];
        int count = 0;
        for (int i : nums2) {
            if (set.contains(i)) {
                arr[count++] = i;
                set.remove(i);
            }
            
            if (set.isEmpty()) {
                break;
            }
        }
        
        return Arrays.copyOf(arr, count);
    }
}
```
## 优化代码
### 思路
试着用boolean数组来省去拆装箱的过程

注意需要注意很多的边界条件
### 代码
```java
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }
        
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int i :nums1) {
            max = Math.max(i, max);
            min = Math.min(i, min);
        }

        boolean[] bArr = new boolean[max - min + 1];
        for(int value: nums1){
            bArr[value - min] = true;
        }

        int[] arr = new int[nums2.length];
        int count = 0;
        for (int num : nums2) {
            if (num >= min && num <= max && bArr[num - min]) {
                arr[count++] = num;
                bArr[num - min] = false;
            }
        }

        int[] ans = new int[count];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = arr[i];
        }
        
        return ans;
    }
}
```
# LeetCode_821_字符的最短距离
## 题目
给定一个字符串 S 和一个字符 C。返回一个代表字符串 S 中每个字符到字符串 S 中的字符 C 的最短距离的数组。

示例 1:
```
输入: S = "loveleetcode", C = 'e'
输出: [3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0]
```
说明:
```
字符串 S 的长度范围为 [1, 10000]。
C 是一个单字符，且保证是字符串 S 里的字符。
S 和 C 中的所有字母均为小写字母。
```
## 解法一
### 思路
- 生成一个记录最短距离的数组distance，元素的初始值为Integer.MAX_VALUE
- 遍历字符数组，当碰到和C相同的字符时，开始左右递归：
   - 递归函数的参数：
       - 记录最短距离的distance数组
       - 开始递归时那个与C字符相同的元素的下标值index
       - 当前递归到的下标值cur
       - 步长，1代表向左，-1代表向右
   - 递归的处理逻辑：
       1. 设定退出条件：超出字符数组边界
       2. 比较index和cur差值的绝对值
       3. distance的cur下标位置的元素，取原值与差值的最小值
- 循环结束，返回distance
### 代码
```java
public class Solution {
    public int[] shortestToChar(String S, char C) {
        char[] cs = S.toCharArray();
        int [] distance = new int[cs.length];
        for (int i = 0; i < distance.length; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == C) {
                rescurse(distance, i, i, 1);
                rescurse(distance, i, i, -1);
            }
        }

        return distance;
    }

    private void rescurse(int[] distance, int index, int cur, int path) {
        if (cur < 0 || cur  > distance.length - 1) {
            return;
        }

        distance[cur] = Math.min(distance[cur], Math.abs(index - cur));

        rescurse(distance, index, cur + path, path);
    }
}
```
# LeetCode_258_各位相加
## 题目
给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。

示例:
```
输入: 38
输出: 2 
解释: 各位相加的过程为：3 + 8 = 11, 1 + 1 = 2。 由于 2 是一位数，所以返回 2。
```
进阶:
```
你可以不使用循环或者递归，且在 O(1) 时间复杂度内解决这个问题吗？
```
## 解法一
### 思路
- 循环体内逻辑：
    - 取余，获得目前最低位上的值，并与ans相加
    - 通过int除法运算截断小数的特点，除以10截去处理过位数
    - 当第2步执行完后，num已经被截为10，说明当前循环处理的是最后一位
    - 判断ans是否为个位数，不是，就将ans清空，把值赋给num，继续循环处理
- 所以循环的判断条件就是num>0，如果>0，说明ans已经是个位数
### 代码
```java
class Solution {
    public int addDigits(int num) {
        int ans = 0;
        while(num > 0) {
            ans += num % 10;
            num /= 10;

            if(num == 0 && ans >= 10) {
                num = ans;
                ans = 0;
            }
        }

        return ans;
    }
}
```
## 解法二
### 思路
## 思路
时间复杂度为O(1)的解法：
- 除个位外，每一位上的值都是通过(9+1)进位的过程得到的，想一下**拨算盘进位**
- 把整数n看成n样物品，原本是以10个1份打包的，现在从这些10个1份打包好的里面，拿出1个，让它们以9个为1份打包。
- 这样就出现了两部分的东西：
    - 原本10个现在9个1份的，打包好的物品，这些，我们不用管
    - 零散的物品，它们还可以分成：
        - 从原来打包的里面拿出来的物品，它们的总和 =》 **原来打包好的份数** =》 **10进制进位的次数** =》 **10进制下，除个位外其他位上的值的总和**
        - 以10个为1份打包时，打不进去的零散物品 =》 **10进制个位上的值**
- 如上零散物品的总数，就是第一次处理num后得到的累加值
- 如果这个累加值>9，那么如题就还需要将各个位上的值再相加，直到结果为个位数为止。也就意味着还需要来一遍如上的过程。
- 那么按照如上的思路，似乎可以通过**n % 9**得到最后的值
- 但是有1个关键的问题，如果num是9的倍数，那么就不适用上述逻辑。原本我是想得到**n被打包成10个1份的份数**+**打不进10个1份的散落个数**的和。**通过与9取模，去获得那个不能整除的1，作为计算份数的方式**，但是如果可以被9整除，我就无法得到那个1，也得不到个位上的数。
- 所以需要做一下特殊处理，**(num - 1) % 9 + 1**
- 可以这么做的原因：原本可以被完美分成9个为一份的n样物品，我故意去掉一个，那么就又可以回到上述逻辑中去得到我要的**n被打包成10个一份的份数**+**打不进10个一份的散落个数**的和。而这个减去的1就相当于从，在10个1份打包的时候散落的个数中借走的，本来就不影响原来10个1份打包的份数，先拿走再放回来，都只影响散落的个数，所以没有关系。
### 代码
```java
class Solution {
    public int addDigits(int num) {
        return (num - 1) % 9 + 1;
    }
}
```
# LeetCode_682_棒球比赛
## 题目
```
你现在是棒球比赛记录员。
给定一个字符串列表，每个字符串可以是以下四种类型之一：
1.整数（一轮的得分）：直接表示您在本轮中获得的积分数。
2. "+"（一轮的得分）：表示本轮获得的得分是前两轮有效 回合得分的总和。
3. "D"（一轮的得分）：表示本轮获得的得分是前一轮有效 回合得分的两倍。
4. "C"（一个操作，这不是一个回合的分数）：表示您获得的最后一个有效 回合的分数是无效的，应该被移除。

每一轮的操作都是永久性的，可能会对前一轮和后一轮产生影响。
你需要返回你在所有回合中得分的总和。
```
示例 1:
```
输入: ["5","2","C","D","+"]
输出: 30
解释: 
第1轮：你可以得到5分。总和是：5。
第2轮：你可以得到2分。总和是：7。
操作1：第2轮的数据无效。总和是：5。
第3轮：你可以得到10分（第2轮的数据已被删除）。总数是：15。
第4轮：你可以得到5 + 10 = 15分。总数是：30。
```
示例 2:
```
输入: ["5","-2","4","C","D","9","+","+"]
输出: 27
解释: 
第1轮：你可以得到5分。总和是：5。
第2轮：你可以得到-2分。总数是：3。
第3轮：你可以得到4分。总和是：7。
操作1：第3轮的数据无效。总数是：3。
第4轮：你可以得到-4分（第三轮的数据已被删除）。总和是：-1。
第5轮：你可以得到9分。总数是：8。
第6轮：你可以得到-4 + 9 = 5分。总数是13。
第7轮：你可以得到9 + 5 = 14分。总数是27。
```
注意：
```
输入列表的大小将介于1和1000之间。
列表中的每个整数都将介于-30000和30000之间。
```
## 解法
### 思路
因为所有的特殊情况都是和就近份数有关，所以利用栈的先进后出的特性
- 遍历字符串数组
- 分数就入栈特殊情况通过操作栈来处理，
- 特殊情况就按具体情况处理
- 遍历结束，循环整个栈，累加里面的分数，返回结果
### 代码
```java
class Solution {
    public int calPoints(String[] ops) {
        Stack<Integer> stack = new Stack<>();
        for (String op : ops) {
            if ("C".equals(op)) {
                stack.pop();
                continue;
            }

            if ("D".equals(op)) {
                stack.push(stack.peek() * 2);
                continue;
            }

            if ("+".equals(op)) {
                int pre = stack.pop();
                int pre2 = stack.peek();
                stack.push(pre);
                stack.push(pre + pre2);
                continue;
            }

            stack.push(Integer.parseInt(op));
        }

        int ans = 0;
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }

        return ans;
    }
}
```
# LeetCode_1002_查找常用字符
## 题目
给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。

你可以按任意顺序返回答案。

示例 1：
```
输入：["bella","label","roller"]
输出：["e","l","l"]
```
示例 2：
```
输入：["cool","lock","cook"]
输出：["c","o"]
```
提示：
```
1 <= A.length <= 100
1 <= A[i].length <= 100
A[i][j] 是小写字母
```
## 解法
### 思路
- 遍历字符串数组
- 遍历字符数组，生成一个map
- 与第一个字符串的map进行比较，如果是第一个字符串就只进行初始化
- 如果第一个map里有，当前map也有，就取两者的最小值
- 如果map里有，当前的没有，就把那个字符对应的值变为0
- 最后遍历map的键值对，生成指定数量的字符串
### 代码
```java
class Solution {
    public List<String> commonChars(String[] A) {
        Map<Character, Integer> map = null;
        
        for (String str: A) {
            Map<Character, Integer> tmp = new HashMap<>();
            for (char c : str.toCharArray()) {
                if (tmp.containsKey(c)) {
                    tmp.computeIfPresent(c, (k, v) -> v += 1);
                } else {
                    tmp.put(c, 1);
                }
            }
            
            if (map == null) {
                map = tmp;
                continue;
            }
            
            for (Character c: map.keySet()) {
                if (tmp.containsKey(c)) {
                    map.computeIfPresent(c, (k, v) -> v = Math.min(v, tmp.get(c)));
                } else {
                    map.computeIfPresent(c, (k, v) -> v = 0);
                }
            }
        }
        
        List<String> list = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry: map.entrySet()) {
            int count = entry.getValue();
            while (count-- > 0) {
                list.add(String.valueOf(entry.getKey()));
            }
        }
        
        return list;
    }
}
```
## 解法二
### 思路
结果依赖的是所有字符串共有的字符，所以只要有任意一个是其他字符串没有的，就不能加到结果list中
- 可以使用2个长度26的数组记录两两字符串之间共同出现过的字符，然后依次比较26个字符元素出现的个数，取两者中的最小值
- 2个字符串其中一个记录比较后的最小值，并用来与后一个字符串进行相同逻辑的比较
- 等所有字符串都遍历完成了，就把这个记录用的数组进行遍历，并按照下标是字符，元素是个数的原则，加到list中
- 最后返回

速度比解法一提升了非常多，不需要生成hash表。
### 代码
```java
class Solution {
    public List<String> commonChars(String[] A) {
        int[] dict = new int[26];
        for (char c: A[0].toCharArray()) {
            dict[c - 'a']++;
        }

        for (String str: A) {
            int[] tmp = new int[26];
            for (char c: str.toCharArray()) {
                tmp[c - 'a']++;    
            }
            
            for (int i = 0; i < dict.length; i++) {
                if (dict[i] > 0) {
                    dict[i] = Math.min(dict[i], tmp[i]);
                }
            }
        }
        
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < dict.length; i++) {
            while (dict[i]-- > 0) {
                ans.add(String.valueOf((char)(i + 'a')));
            }
        }
        return ans;
    }
}
```