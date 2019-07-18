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

```