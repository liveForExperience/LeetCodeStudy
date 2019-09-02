# LeetCode_35_搜索插入位置
## 题目
给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。

你可以假设数组中无重复元素。

示例 1:
```
输入: [1,3,5,6], 5
输出: 2
```
示例 2:
```
输入: [1,3,5,6], 2
输出: 1
```
示例 3:
```
输入: [1,3,5,6], 7
输出: 4
```
示例 4:
```
输入: [1,3,5,6], 0
输出: 0
```
## 解法
### 思路
遍历数组
- 找到目标值，就返回下标
- 未找到目标值，但遍历到的元素大于目标值，返回该元素下标
- 未找到目标值，且遍历结束，返回数组长度
### 代码
```java
class Solution {
    public int searchInsert(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target || nums[i] > target) {
                return i;
            }
        }
        
        return nums.length;
    }
}
```
## 解法二
### 思路
使用头尾指针进行二分查找，加快速度
### 代码
```java
class Solution {
    public int searchInsert(int[] nums, int target) {
        if (target > nums[nums.length - 1]) {
            return nums.length;
        }
        
        int head = 0, tail = nums.length - 1, mid = 0;
        while (head < tail) {
            mid = head + (tail - head) / 2;
            
            if (nums[mid] == target) {
                return mid;
            }
            
            if (nums[mid] > target) {
                tail = mid;
            }
            
            if (nums[mid] < target) {
                head = mid + 1;
            }
        }
        
        return tail;
    }
}
```
# LeetCode_558_四叉树交集
## 题目
四叉树是一种树数据，其中每个结点恰好有四个子结点：topLeft、topRight、bottomLeft 和 bottomRight。四叉树通常被用来划分一个二维空间，递归地将其细分为四个象限或区域。

我们希望在四叉树中存储 True/False 信息。四叉树用来表示 N * N 的布尔网格。对于每个结点, 它将被等分成四个孩子结点直到这个区域内的值都是相同的。每个节点都有另外两个布尔属性：isLeaf 和 val。当这个节点是一个叶子结点时 isLeaf 为真。val 变量储存叶子结点所代表的区域的值。

例如，下面是两个四叉树 A 和 B：
```
A:
+-------+-------+   T: true
|       |       |   F: false
|   T   |   T   |
|       |       |
+-------+-------+
|       |       |
|   F   |   F   |
|       |       |
+-------+-------+
topLeft: T
topRight: T
bottomLeft: F
bottomRight: F

B:               
+-------+---+---+
|       | F | F |
|   T   +---+---+
|       | T | T |
+-------+---+---+
|       |       |
|   T   |   F   |
|       |       |
+-------+-------+
topLeft: T
topRight:
     topLeft: F
     topRight: F
     bottomLeft: T
     bottomRight: T
bottomLeft: T
bottomRight: F
```
你的任务是实现一个函数，该函数根据两个四叉树返回表示这两个四叉树的逻辑或(或并)的四叉树。
```
A:                 B:                 C (A or B):
+-------+-------+  +-------+---+---+  +-------+-------+
|       |       |  |       | F | F |  |       |       |
|   T   |   T   |  |   T   +---+---+  |   T   |   T   |
|       |       |  |       | T | T |  |       |       |
+-------+-------+  +-------+---+---+  +-------+-------+
|       |       |  |       |       |  |       |       |
|   F   |   F   |  |   T   |   F   |  |   T   |   F   |
|       |       |  |       |       |  |       |       |
+-------+-------+  +-------+-------+  +-------+-------+
```
提示：
```
A 和 B 都表示大小为 N * N 的网格。
N 将确保是 2 的整次幂。
如果你想了解更多关于四叉树的知识，你可以参考这个 wiki 页面。
逻辑或的定义如下：如果 A 为 True ，或者 B 为 True ，或者 A 和 B 都为 True，则 "A 或 B" 为 True。
```
## 解法
### 思路
- 四个象限上：
    - 如果当前node是叶子节点，就返回当前node
    - 如果当前node不是叶子节点，就返回另一个node
    - 如果都不是叶子节点，就递归下一层
- 返回的node
    - 如果都是true或false，需要合并
    - 否则返回新的节点，isLeaf和val都是false，四个节点未递归得到的4个象限节点
### 代码
```java
class Solution {
    public Node intersect(Node quadTree1, Node quadTree2) {
        if (quadTree1.isLeaf) {
            if (quadTree1.val) {
                return quadTree1;
            } else {
                return quadTree2;
            }
        }

        if (quadTree2.isLeaf) {
            if (quadTree2.val) {
                return quadTree2;
            } else {
                return quadTree1;
            }
        }

        Node topLeft = intersect(quadTree1.topLeft, quadTree2.topLeft);
        Node topRight = intersect(quadTree1.topRight, quadTree2.topRight);
        Node bottomLeft = intersect(quadTree1.bottomLeft, quadTree2.bottomLeft);
        Node bottomRight = intersect(quadTree1.bottomRight, quadTree2.bottomRight);

        if (topLeft.isLeaf && topLeft.val && topRight.isLeaf && topRight.val && bottomLeft.isLeaf && bottomLeft.val && bottomRight.isLeaf && bottomRight.val) {
            return new Node(true, true, null, null, null, null);
        }

        if (topLeft.isLeaf && !topLeft.val && topRight.isLeaf && !topRight.val && bottomLeft.isLeaf && !bottomLeft.val && bottomRight.isLeaf && !bottomRight.val) {
            return new Node(false, true, null, null, null, null);
        }

        return new Node(false, false, topLeft, topRight, bottomLeft, bottomRight);
    }
}
```
# LeetCode_746_使用最小花费爬楼梯
## 题目
数组的每个索引做为一个阶梯，第 i个阶梯对应着一个非负数的体力花费值 cost[i](索引从0开始)。

每当你爬上一个阶梯你都要花费对应的体力花费值，然后你可以选择继续爬一个阶梯或者爬两个阶梯。

您需要找到达到楼层顶部的最低花费。在开始时，你可以选择从索引为 0 或 1 的元素作为初始阶梯。

示例 1:
```
输入: cost = [10, 15, 20]
输出: 15
解释: 最低花费是从cost[1]开始，然后走两步即可到阶梯顶，一共花费15。
```
 示例 2:
```
输入: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
输出: 6
解释: 最低花费方式是从cost[0]开始，逐个经过那些1，跳过cost[3]，一共花费6。
```
注意：
```
cost 的长度将会在 [2, 1000]。
每一个 cost[i] 将会是一个Integer类型，范围为 [0, 999]。
```
## 失败解法
### 失败原因
超时
### 思路
递归求解
- 递归函数三个参数：
    - 数组cost
    - 当前遍历下标index，初始值为-1
    - 累加值sum
- 过程逻辑：
    - 退出条件：index>=cost.length，说明到达楼梯顶部，返回sum
    - 过程：如果index不为-1，sum累加当前下标对应的元素
    - 返回：走1步和走2步的最小值
### 代码
```java
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        return reverse(cost, -1, 0);
    }
    
    private int reverse(int[] cost, int index, int sum) {
        if (index >= cost.length) {
            return sum;
        }
        
        if (index >= 0) {
            sum += cost[index];    
        }
        
        return Math.min(reverse(cost, index + 1, sum), reverse(cost, index + 2, sum));
    }
}
```
## 解法
### 思路
使用动态规划，每一个台阶的元素与前一步的sum和前二步sum的最小值的和构成了每一步的逻辑，而最后一步也是如此。
### 代码
```java
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        if (cost.length == 0) {
            return 0;
        }

        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];

        for (int i = 2; i < cost.length; i++) {
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
        }
        return Math.min(dp[cost.length - 1], dp[cost.length - 2]);
    }
}
```
# LeetCode_1022_从根到叶的二进制之和
## 题目
数组的每个索引做为一个阶梯，第 i个阶梯对应着一个非负数的体力花费值 cost[i](索引从0开始)。

每当你爬上一个阶梯你都要花费对应的体力花费值，然后你可以选择继续爬一个阶梯或者爬两个阶梯。

您需要找到达到楼层顶部的最低花费。在开始时，你可以选择从索引为 0 或 1 的元素作为初始阶梯。

示例 1:
```
输入: cost = [10, 15, 20]
输出: 15
解释: 最低花费是从cost[1]开始，然后走两步即可到阶梯顶，一共花费15。
```
 示例 2:
```
输入: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
输出: 6
解释: 最低花费方式是从cost[0]开始，逐个经过那些1，跳过cost[3]，一共花费6。
```
注意：
```
cost 的长度将会在 [2, 1000]。
每一个 cost[i] 将会是一个Integer类型，范围为 [0, 999]。
```
## 解法
### 思路
dfs获取二进制字符串，转成数字再求和
### 代码
```java
class Solution {
    public int sumRootToLeaf(TreeNode root) {
        List<String> list = new ArrayList<>();
        dfs(root, "", list);

        int sum = 0;
        for (String str : list) {
            sum += Integer.parseInt(str, 2);
        }
        return sum;
    }

    private void dfs(TreeNode node, String str, List<String> list) {
        if (node == null) {
            return;
        }

        String val = str + node.val;
        if (node.left == null && node.right == null) {
            list.add(val);
        }
        
        dfs(node.left, val, list);
        dfs(node.right, val, list);
    }
}
```
## 解法二
### 思路
在递归过程中进行计算，少了list的遍历动作
### 代码
```java
class Solution {
    int ans = 0;
    public int sumRootToLeaf(TreeNode root) {
        if (root == null) {
            return 0;
        }

        dfs(root, 0);

        return ans;
    }

    private void dfs(TreeNode node, int sum) {
        int val = 2 * sum + node.val;
        if (node.left == null && node.right == null) {
            ans += val;
        }
        
        if (node.right != null) {
            dfs(node.right, val);
        }
        
        if (node.left != null) {
            dfs(node.left, val);
        }
    }
}
```
# LeetCode_350_两个数组的交集II
## 题目
给定两个数组，编写一个函数来计算它们的交集。

示例 1:
```
输入: nums1 = [1,2,2,1], nums2 = [2,2]
输出: [2,2]
```
示例 2:
```
输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
输出: [4,9]
```
说明：
```
输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
我们可以不考虑输出结果的顺序。
```
进阶:
```
如果给定的数组已经排好序呢？你将如何优化你的算法？
如果 nums1 的大小比 nums2 小很多，哪种方法更优？
如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
```
## 解法
### 思路
- 遍历第一个数组，将元素放入map中并计数
- 遍历第二个数组，判断map中是否有该元素，且个数大于0，符合就放入list中
- 最后list转成arr返回
### 代码
```java
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            map.put(num, map.getOrDefault(num , 0) + 1);
        }

        List<Integer> list = new ArrayList<>();
        for (int num : nums2) {
            if (map.containsKey(num) && map.get(num) != 0) {
                map.put(num, map.get(num) - 1);
                list.add(num);
            }
        }
        
        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }
}
```
## 解法二
### 思路
- 排序两个数组
- 用2个指针分别对应两个数组，如果某一个数组指针对应的元素小了，那个指针就增加1
- 否则就说明元素匹配，加到list中
- 最后当两个数组任意一个遍历结束，就把list中的元素放入arr中返回
### 代码
```java
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }
        
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        int len1 = nums1.length, len2 = nums2.length;
        int[] arr = new int[Math.max(len1, len2)];
        
        int i1 = 0, i2 = 0, i = 0;
        while (i1 < len1 && i2 < len2) {
            if (nums1[i1] < nums2[i2]) {
                i1++;
                continue;
            }
            
            if (nums1[i1] > nums2[i2]) {
                i2++;
                continue;
            }
            
            arr[i++] = nums1[i1];
            i1++;
            i2++;
        }
        
        return Arrays.copyOf(arr, i);
    }
}
```
# LeetCode_830_较大分组的位置
## 题目
在一个由小写字母构成的字符串 S 中，包含由一些连续的相同字符所构成的分组。

例如，在字符串 S = "abbxxxxzyy" 中，就含有 "a", "bb", "xxxx", "z" 和 "yy" 这样的一些分组。

我们称所有包含大于或等于三个连续字符的分组为较大分组。找到每一个较大分组的起始和终止位置。

最终结果按照字典顺序输出。

示例 1:
```
输入: "abbxxxxzzy"
输出: [[3,6]]
解释: "xxxx" 是一个起始于 3 且终止于 6 的较大分组。
```
示例 2:
```
输入: "abc"
输出: []
解释: "a","b" 和 "c" 均不是符合要求的较大分组。
```
示例 3:
```
输入: "abcdddeeeeaabbbcd"
输出: [[3,5],[6,9],[12,14]]
说明:  1 <= S.length <= 1000
```
## 解法
### 思路
- 初始化一个List<List<Integer>> ans作为结果
- 使用一个变量start作为开始下标的暂存变量，初始化为0
- 遍历字符数组，从下标1开始，如果字符与上一个字符不相等，就将start和上一个字符的下标存入list，再存入ans，同时将start变更为当前元素下标
- 遍历结束，判断最后一段的字符串是否需要放入ans
- 返回ans
### 代码
```java
class Solution {
    public List<List<Integer>> largeGroupPositions(String S) {
        List<List<Integer>> ans = new ArrayList<>();
        if (S.length() <= 1) {
            return ans;
        }
        
        char[] cs = S.toCharArray();
        int start = 0;
        for (int i = 1; i < cs.length; i++) {
            if (cs[i] != cs[i - 1]) {
                if (i - 1 - start >= 2) {
                    List<Integer> list = new ArrayList<>();
                    list.add(start);
                    list.add(i - 1);
                    ans.add(list);    
                }
                start = i;
            }
        }
        
        if (cs.length - start >= 3) {
            List<Integer> list = new ArrayList<>();
            list.add(start);
            list.add(cs.length - 1);
            ans.add(list);
        }
        
        return ans;
    }
}
```
# LeetCode_925_长按键入
## 题目
你的朋友正在使用键盘输入他的名字 name。偶尔，在键入字符 c 时，按键可能会被长按，而字符可能被输入 1 次或多次。

你将会检查键盘输入的字符 typed。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回 True。

示例 1：
```
输入：name = "alex", typed = "aaleex"
输出：true
解释：'alex' 中的 'a' 和 'e' 被长按。
```
示例 2：
```
输入：name = "saeed", typed = "ssaaedd"
输出：false
解释：'e' 一定需要被键入两次，但在 typed 的输出中不是这样。
```
示例 3：
```
输入：name = "leelee", typed = "lleeelee"
输出：true
```
示例 4：
```
输入：name = "laiden", typed = "laiden"
输出：true
解释：长按名字中的字符并不是必要的。
```
提示：
```
name.length <= 1000
typed.length <= 1000
name 和 typed 的字符都是小写字母。
```
## 解法
### 思路
- 使用两个指针分别对应两个字符数组
- 循环的退出条件是两个指针都>=其对应的数组长度
- 比较指针对应的元素是否相等
    - 相等就同时++
    - 不相等，typed字符数组的元素就要判断是否和前一个元素相等，如果相等就++，直到不相等，再判断是否与name数组的元素一致，如果不一致就返回false
### 代码
```java
class Solution {
    public boolean isLongPressedName(String name, String typed) {
        int ni = 0, ti = 0;
        while (ni < name.length() && ti < typed.length()) {
            if (name.charAt(ni) != typed.charAt(ti)) {
                if (ti == 0) {
                    return false;
                }
                
                while (typed.charAt(ti - 1) == typed.charAt(ti)) {
                    ti++;
                    if(ti >= typed.length()) {
                        return false;
                    }
                }

                if (typed.charAt(ti) != name.charAt(ni)) {
                    return false;
                }
            }
            
            ni++;
            ti++;
        }
        
        if (ni < name.length()) {
            return false;
        }
        
        for (int i = ti; i < typed.length(); i++) {
            if (typed.charAt(i - 1) != typed.charAt(i)) {
                return false;
            }
        }

        return true;
    }
}
```
# LeetCode_459_重复的子字符串
## 题目
给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。

示例 1:
```
输入: "abab"
输出: True
解释: 可由子字符串 "ab" 重复两次构成。
```
示例 2:
```
输入: "aba"
输出: False
```
示例 3:
```
输入: "abcabcabcabc"
输出: True
解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
```
## 失败解法
### 思路
遍历字符数组，从第一个字符开始不断累加字符，并使用String.replaceAll看是否能将字符串转成空字符串，直到字符长度的一半向上取整为止
### 失败原因
超时
### 代码
```java
class Solution {
    public boolean repeatedSubstringPattern(String s) {
        if (s.length() <= 1) {
            return false;
        }
        
        char[] cs = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cs.length / 2 + 1; i++) {
            if (i != s.length() - 1 && "".equals(s.replaceAll(sb.append(cs[i]).toString(), ""))) {
                return true;
            }
        }
        
        return false;
    }
}
```
## 解法
### 思路
使用周期串思路，如果存在周期t，那么在[0, 字符串长度len / 2]必定存在元素下标i对应的arr[i] == arr[i % t]，所以就遍历数组，找到这个t
```math
f(x+t) = f(x)
```
### 代码
```java
class Solution {
    public boolean repeatedSubstringPattern(String s) {
        for (int i = 1; i <= s.length() / 2; i++) {
            if (s.length() % i != 0) {
                continue;
            }
            int j = i;
            for (; j < s.length() && s.charAt(j) == s.charAt(j % i); j++);
            if (s.length() == j) {
                return true;
            }
        }
        
        return false;
    }
}
```
## 解法二
### 思路
因为可重复，每一个子字符串的最后一个字符和整个字符串的最后一个字符一定是一样的
- 先找到最后一个字符
- 从最后开始往前找相同的字符
- 找到后，先确定从头到该下标的字符串，然后再看该之后是否也有如此长度的字符串，且两者是否相等，如果是，就说明是可重复的
### 代码
```java
class Solution {
    public boolean repeatedSubstringPattern(String s) {
        char c = s.charAt(s.length() - 1);
        int l = s.lastIndexOf(c, s.length() / 2 - 1) + 1;
        for (; l > 0; l = s.lastIndexOf(c, l - 2) + 1) {
            if (s.length() % l == 0) {
                String one = s.substring(0, l);
                boolean flag = true;
                for (int i = l; i < s.length(); i += l) {
                    if (!one.equals(s.substring(i, i + l))) {
                        flag = false;
                        break;
                    }
                }
                
                if (flag) {
                    return true;
                }
            }
        }
        
        return false;
    }
}
```
# LeetCode_989_数组形式的整数加法
## 题目
对于非负整数 X 而言，X 的数组形式是每位数字按从左到右的顺序形成的数组。例如，如果 X = 1231，那么其数组形式为 [1,2,3,1]。

给定非负整数 X 的数组形式 A，返回整数 X+K 的数组形式。

示例 1：
```
输入：A = [1,2,0,0], K = 34
输出：[1,2,3,4]
解释：1200 + 34 = 1234
```
解释 2：
```
输入：A = [2,7,4], K = 181
输出：[4,5,5]
解释：274 + 181 = 455
```
示例 3：
```
输入：A = [2,1,5], K = 806
输出：[1,0,2,1]
解释：215 + 806 = 1021
```
示例 4：
```
输入：A = [9,9,9,9,9,9,9,9,9,9], K = 1
输出：[1,0,0,0,0,0,0,0,0,0,0]
解释：9999999999 + 1 = 10000000000
```
提示：
```
1 <= A.length <= 10000
0 <= A[i] <= 9
0 <= K <= 10000
如果 A.length > 1，那么 A[0] != 0
```
## 解法
### 思路
把k也转成数组形式，通过记录进位的方式进行加法运算
### 代码
```java
class Solution {
    public List<Integer> addToArrayForm(int[] A, int K) {
        char[] kcs = Integer.toString(K).toCharArray();
        int kLen = kcs.length, aLen = A.length, ki = kLen - 1, ai = aLen - 1, carry = 0;
        List<Integer> ans = new ArrayList<>();
        while (ki >= 0 && ai >= 0) {
            int k = Integer.parseInt(Character.toString(kcs[ki--]));
            int a = A[ai--];
            int sum = k + a + carry;
            carry = sum >= 10 ? sum / 10 : 0;
            ans.add(0, sum % 10);
        }

        while (ki >= 0) {
            int sum = Integer.parseInt(Character.toString(kcs[ki--])) + carry;
            carry = sum >= 10 ? sum / 10 : 0;
            ans.add(0, sum % 10);
        }

        while (ai >= 0){
            int sum = A[ai--] + carry;
            carry = sum >= 10 ? sum / 10 : 0;
            ans.add(0, sum % 10);
        }

        if (carry != 0) {
            ans.add(0, carry);
        }
        
        return ans;
    }
}
```
## 解法二
### 思路
- 不要把k转成数组，直接通过取模来移动位数
- 不用记录进位，所有的计算在K上进行，将A数组的元素在每次循环中累加到K上，再取K当前最低位的值即可
### 代码
```java
class Solution {
    public List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> ans = new ArrayList<>();
        int index = A.length - 1;
        while (index >= 0 || K > 0) {
            if (index >= 0) {
                K += A[index--];
            }

            ans.addFirst(K % 10);
            K /= 10;
        }

        return ans;
    }
}
```
## 优化代码
### 思路
解法一二使用的list是数组结构的，如果放在第一位需要重新复制数组，所以很慢，改成linkedList就会很快
### 代码
```java
class Solution {
    public List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> ans = new LinkedList<>();
        int index = A.length - 1;
        while (index >= 0 || K > 0) {
            if (index >= 0) {
                K += A[index--];
            }

            ans.add(0, K % 10);
            K /= 10;
        }

        return ans;
    }
}
```
# LeetCode_594_最长和谐子序列
## 题目
和谐数组是指一个数组里元素的最大值和最小值之间的差别正好是1。

现在，给定一个整数数组，你需要在所有可能的子序列中找到最长的和谐子序列的长度。

示例 1:
```
输入: [1,3,2,2,5,2,3,7]
输出: 5
原因: 最长的和谐数组是：[3,2,2,2,3].
说明: 输入的数组长度最大不超过20,000.
```
## 失败解法
### 思路
使用桶记录元素个数，找到相邻元素个数和的最大值，另需要考虑负数
### 失败原因
超出内存限制
### 代码
```java
class Solution {
    public int findLHS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int len = Integer.MIN_VALUE, min = Integer.MAX_VALUE, max = 0;
        for (int num : nums) {
            min = Math.min(min, num);
            len = Math.max(len, num);
        }
        
        int value = min < 0 ? Math.abs(min) : 0;

        int[] bucket = new int[len + value + 1];
        for (int num : nums) {
            bucket[num + value]++;
        }

        for (int i = 1; i < bucket.length; i++) {
            if (bucket[i - 1] != 0 && bucket[i] != 0) {
                max = Math.max(bucket[i - 1] + bucket[i], max);
            }
        }

        return max;
    }
}
```
## 失败解法二
### 思路
使用map代替桶
### 失败原因
超出时间限制
### 代码
```java
class Solution {
    public int findLHS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, ans = 0;
        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            min = Math.min(num, min);
            max = Math.max(num, max);
        }
        
        while (++min <= max) {
            if (map.get(min) != null && map.get(min - 1) != null) {
                ans = Math.max(map.get(min) + map.get(min - 1), ans);
            }
        }
        
        return ans;
    }
}
```
## 解法
### 思路
基于解法二，使用TreeMap直接排序元素，然后遍历keySet来判断是否有符合要求的相邻元素，并获得和的最大值
### 代码
```java
class Solution {
    public int findLHS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Map<Integer, Integer> map = new TreeMap<>(Comparator.naturalOrder());
        int ans = 0;
        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        for (Integer num : map.keySet()) {
            if (map.containsKey(num - 1)) {
                ans = Math.max(ans, map.get(num) + map.get(num - 1));
            }
        }

        return ans;
    }
}
```
## 优化代码
### 思路
我排序干嘛?!
### 代码
```java
class Solution {
    public int findLHS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (Integer num : map.keySet()) {
            if (map.containsKey(num - 1)) {
                ans = Math.max(ans, map.get(num) + map.get(num - 1));
            }
        }

        return ans;
    }
}
```
## 解法二
### 思路
- 排序数组
- 用两个指针分别指向：
    - 第一个指针i移动到当前指向元素+1不再小于第二个指针j指向的元素为止
    - 指针j指向的元素如果<=指针i指向的元素就移动，且如果==，就更新ans值为j-i+1和ans之间的最大值
### 代码
```java
class Solution {
    public int findLHS(int[] nums) {
        Arrays.sort(nums);

        int ans = 0, i = 0;
        for (int j = 0; j < nums.length; j++) {
            while (nums[i] + 1 < nums[j]) {
                i++;
            }
            if (nums[i] + 1 == nums[j]) {
                ans = Math.max(ans, j - i + 1);
            }
        }
        
        return ans;
    }
}
```
# LeetCode_141_环形链表
## 题目
给定一个链表，判断链表中是否有环。

为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。

示例 1：
```
输入：head = [3,2,0,-4], pos = 1
输出：true
解释：链表中有一个环，其尾部连接到第二个节点。
```
示例 2：
```
输入：head = [1,2], pos = 0
输出：true
解释：链表中有一个环，其尾部连接到第一个节点。
```
示例 3：
```
输入：head = [1], pos = -1
输出：false
解释：链表中没有环。
```
进阶：
```
你能用 O(1)（即，常量）内存解决此问题吗？
```
## 解法
### 思路
使用set存储遍历的节点指针，同时判断是否contains
### 代码
```java
public class Solution {
    public boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        ListNode node = head;
        while (node != null) {
            if (set.contains(node)) {
                return true;
            }
            set.add(node);
            node = node.next;
        }
        return false;
    }
}
```
## 解法二
### 思路
使用快漫指针加快判断的过程，同时也就不需要使用set存储指针，直接判断快慢是否相等就可以了
### 代码
```java
public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                return true;
            }
        }
        
        return false;
    }
}
```
# LeetCode_501_二叉搜索树中的众数
## 题目
给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。

假定 BST 有如下定义：
```
结点左子树中所含结点的值小于等于当前结点的值
结点右子树中所含结点的值大于等于当前结点的值
左子树和右子树都是二叉搜索树
```
例如：
```
给定 BST [1,null,2,2],

   1
    \
     2
    /
   2
返回[2].
```
```
提示：如果众数超过1个，不需考虑输出顺序
```
```
进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
```
## 解法
### 思路
- dfs遍历二叉搜索树，map存储元素个数
- 遍历values找到个数最大值
- 遍历map找到value和最大值一样的num放入ans
### 代码
```java
class Solution {
    public int[] findMode(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        dfs(root, map);
        int max = Collections.max(map.values());
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if (entry.getValue() == max) {
                list.add(entry.getKey());
            }
        }
        
        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    public void dfs(TreeNode node, Map<Integer, Integer> map) {
        if (node == null) {
            return;
        }
        
        map.put(node.val, map.getOrDefault(node.val, 0) + 1);
        dfs(node.left, map);
        dfs(node.right, map);
    }
}
```
## 解法二
### 思路
- 在dfs的过程中记录：
    - 上一个节点的值
    - 上一个节点的数量
    - 暂定的众数的数量
    - 存众数的list
- 当前值为null直接返回
- 如果当前值和上一个节点值不同，或者是初始的根节点，则上一个节点的值为1
- 如果当前值与上一个节点值相同，累加上一节点的数量
- 如果上一个节点的数比暂定众数多，清空list，list放入上一个节点
- 如果上一个节点的数和暂定众数一样，list放入上一个节点
### 代码
```java
class Solution {
    private Integer pre;
    private int count = 0;
    private int mode = 0;
    private List<Integer> list = new ArrayList<>();
    public int[] findMode(TreeNode root) {
        dfs(root);
        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }

        dfs(node.left);
        if (pre == null || node.val != pre) {
            count = 1;
        } else {
            count++;
        }

        if (count > mode) {
            mode = count;
            list.clear();
            list.add(node.val);
        } else if (count == mode) {
            list.add(node.val);
        }
        
        pre = node.val;
        dfs(node.right);
    }
}
```
# LeetCode_744_寻找比目标字母大的最小字母
## 题目
给定一个只包含小写字母的有序数组letters 和一个目标字母 target，寻找有序数组里面比目标字母大的最小字母。

数组里字母的顺序是循环的。举个例子，如果目标字母target = 'z' 并且有序数组为 letters = ['a', 'b']，则答案返回 'a'。

示例:
```
输入:
letters = ["c", "f", "j"]
target = "a"
输出: "c"
```
```
输入:
letters = ["c", "f", "j"]
target = "c"
输出: "f"
```
```
输入:
letters = ["c", "f", "j"]
target = "d"
输出: "f"
```
```
输入:
letters = ["c", "f", "j"]
target = "g"
输出: "j"
```
```
输入:
letters = ["c", "f", "j"]
target = "j"
输出: "c"
```
```
输入:
letters = ["c", "f", "j"]
target = "k"
输出: "c"
```
注:
```
letters长度范围在[2, 10000]区间内。
letters 仅由小写字母组成，最少包含两个不同的字母。
目标字母target 是一个小写字母。
```
## 解法
### 思路
使用桶
- 遍历数组，如果发现比target大1的字符，就直接返回该字符，否则将遍历到的字符放入桶中进行标识
- 并记录字符的最大值和最小值
    - 如果target小于最小值，返回最小值
    - 如果target大于最大值，返回最小值
    - 从最小值开始遍历，看target小于哪个值
### 代码
```java
class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        boolean[] bucket = new boolean[26];
        int min = 'z', max = 'a';
        for (char c : letters) {
            if (c - target == 1) {
                return c;
            }
            
            bucket[c - 'a'] = true;
            min = Math.min(c, min);
            max = Math.max(c, max);
        }
        
        if (target < min || target >= max) {
            return (char)min;
        }
        
        for (int i = min - 'a'; i <= max - 'a'; i++) {
            if (bucket[i] && target - 'a' < i) {
                return (char)(i + 'a');
            }
        }
        
        return 'a';
    }
}
```
## 解法二
### 思路
使用双指针来找比target大的最小字母，因为这个数组是排序的
- 和解法一一样先确定两种情况，比最小字母小，或者大于等于最大字母，则返回最小字母
- 之后通过mid指针来定位
    - mid对应元素小于target，l指针指向mid + 1
    - mid对应元素大于target，r指针指向mid
- 当l > r之后，返回r指向的元素即可，因为在循环中，当mid找到答案时，r会指向这个mid，同时新的mid将会不断迫使l向r逼近，并在最终通过mid + 1的操作大于r，从而停止循环
### 代码
```java
class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        if (letters == null || letters.length == 0) {
            return 0;
        }

        int l = 0, r = letters.length - 1;
        if (target < letters[l] || target >= letters[r]) {
            return letters[l];
        }

        while (l < r) {
            int mid = l + r >> 1;
            if (letters[mid] > target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return letters[r];
    }
}
```
# LeetCode_674_最大连续递增序列
## 题目
给定一个未经排序的整数数组，找到最长且连续的的递增序列。

示例 1:
```
输入: [1,3,5,4,7]
输出: 3
解释: 最长连续递增序列是 [1,3,5], 长度为3。
尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为5和7在原数组里被4隔开。 
```
示例 2:
```
输入: [2,2,2,2,2]
输出: 1
解释: 最长连续递增序列是 [2], 长度为1。
注意：数组长度不会超过10000。
```
## 解法
### 思路
- 直接返回条件：
    - 长度为0，返回0
    - 长度为1，返回1
- 定义变量：
    - 暂存临时长度的变量len，初始化为1
    - 暂存最大长度的变量max，初始化为0
- 遍历数组，从第2个元素开始：
    - 当元素大于前一个元素的时候，len++
    - 当元素小于前一个元素的时候，max比较并更新，len初始化为1
- 最后返回max和len的最大值，以防数组本就是升序序列    
### 代码
```java
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        if (nums.length == 1) {
            return 1;
        }
        
        int len = 1, max = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                len++;
            } else {
                max = Math.max(len, max);
                len = 1;
            }
        }
        
        return Math.max(len, max);
    }
}
```
# LeetCode_203_移除链表元素
## 题目
删除链表中等于给定值 val 的所有节点。

示例:
```
输入: 1->2->6->3->4->5->6, val = 6
输出: 1->2->3->4->5
```
## 解法
### 思路
- 使用变量：
    - pre：暂存上一个节点的指针，初始化为null
    - node：暂存当前节点的指针
- 遍历链表
    - 如果pre==null，也就是head节点的时候，val相等，修改head的指针指向node的next
    - 如果val相同，修改pre的next指针为next.next
### 代码
```java
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode node = head;
        ListNode pre = null;
        while (node != null) {
            if (node.val == val) {
                if (pre == null) {
                    node = node.next;
                    head = node;
                    
                    continue;
                }
                
                pre.next = node.next;
            } else {
                pre = node;
            }
            
            node = node.next;
        }
        
        return head;
    }
}
```
# LeetCode_836_矩形重叠
## 题目
矩形以列表 [x1, y1, x2, y2] 的形式表示，其中 (x1, y1) 为左下角的坐标，(x2, y2) 是右上角的坐标。

如果相交的面积为正，则称两矩形重叠。需要明确的是，只在角或边接触的两个矩形不构成重叠。

给出两个矩形，判断它们是否重叠并返回结果。

示例 1：
```
输入：rec1 = [0,0,2,2], rec2 = [1,1,3,3]
输出：true
```
示例 2：
```
输入：rec1 = [0,0,1,1], rec2 = [1,0,2,1]
输出：false
```
说明：
```
两个矩形 rec1 和 rec2 都以含有四个整数的列表的形式给出。
矩形中的所有坐标都处于 -10^9 和 10^9 之间。
```
## 解法
### 思路
- 找到4条边
    - left：x1
    - top：y2
    - right：x2
    - bottom：y1
- 找到不可能覆盖的情况，左为rec2，右为rec1
    - left >= right
    - right <= left
    - top <= bottom
    - bottom >= top
### 代码
```java
class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        return !(rec2[0] >= rec1[2] ||
                rec2[1] >= rec1[3] ||
                rec2[2] <= rec1[0] ||
                rec2[3] <= rec1[1]);
    }
}
```
# LeetCode_1042_不邻接植花
## 题目
有 N 个花园，按从 1 到 N 标记。在每个花园中，你打算种下四种花之一。

paths[i] = [x, y] 描述了花园 x 到花园 y 的双向路径。

另外，没有花园有 3 条以上的路径可以进入或者离开。

你需要为每个花园选择一种花，使得通过路径相连的任何两个花园中的花的种类互不相同。

以数组形式返回选择的方案作为答案 answer，其中 answer[i] 为在第 (i+1) 个花园中种植的花的种类。花的种类用  1, 2, 3, 4 表示。保证存在答案。

示例 1：
```
输入：N = 3, paths = [[1,2],[2,3],[3,1]]
输出：[1,2,3]
```
示例 2：
```
输入：N = 4, paths = [[1,2],[3,4]]
输出：[1,2,1,2]
```
示例 3：
```
输入：N = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]]
输出：[1,2,3,4]
```
提示：
```
1 <= N <= 10000
0 <= paths.size <= 20000
不存在花园有 4 条或者更多路径可以进入或离开。
保证存在答案。
```
## 解法
### 思路
使用图论涂色，因为每个节点的路径不超过3个，所以可以放心涂色
- 遍历数组，统计节点的关联节点
- 循环处理每一个节点：
    1. 查看关联节点使用了的颜色
    2. 遍历颜色数组，根据已经使用的颜色，确定用什么颜色为当前节点上色
    3. 记录在结果数组中
### 代码
```java
class Solution {
    public int[] gardenNoAdj(int N, int[][] paths) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            map.put(i, new HashSet<>());
        }

        for (int[] graph : paths) {
            map.get(graph[0] - 1).add(graph[1] - 1);
            map.get(graph[1] - 1).add(graph[0] - 1);
        }

        int[] ans = new int[N];
        for (int i = 0; i < N; i++) {
            boolean[] used = new boolean[5];
            for (int j: map.get(i)) {
                if (ans[j] != 0) {
                    used[ans[j]] = true;
                }
            }

            for (int j = 1; j < 5; j++) {
                if (!used[j]) {
                    ans[i] = j;
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
使用数组代替set
### 代码
```java
class Solution {
    public int[] gardenNoAdj(int N, int[][] paths) {
        int[][] topo = new int[N + 1][3];
        for (int[] path : paths) {
            int index = 0;
            while (topo[path[0]][index] != 0) {
                index++;
            }
            topo[path[0]][index] = path[1];

            index = 0;
            while (topo[path[1]][index] != 0) {
                index++;
            }
            topo[path[1]][index] = path[0];
        }

        int[] ans = new int[N];
        for (int i = 1; i < N + 1; i++) {
             boolean[] used = new boolean[5];
             for (int j = 0; j < topo[i].length; j++) {
                 if (topo[i][j] != 0 && ans[topo[i][j] - 1] != 0) {
                     used[ans[topo[i][j] - 1]] = true;
                 }
             }

             for (int j = 1; j < 5; j++) {
                 if (!used[j]) {
                     ans[i - 1] = j;
                     break;
                 }
             }
        }

        return ans;
    }
}
```
# LeetCode_367_有效的完全平方数
## 题目
给定一个正整数 num，编写一个函数，如果 num 是一个完全平方数，则返回 True，否则返回 False。

说明：不要使用任何内置的库函数，如  sqrt。

示例 1：
```
输入：16
输出：True
```
示例 2：
```
输入：14
输出：False
```
## 解法
### 思路
使用双指针来找平方数，谨防溢出
### 代码
```java
class Solution {
    public boolean isPerfectSquare(int num) {
        int head = 0, tail = num / 2 + 1;
        while (head <= tail) {
            int mid = head + (tail - head) / 2;
            if ((long) mid * mid == num) {
                return true;
            }

            if ((long) mid * mid > num) {
                tail = mid - 1;
            } else {
                head = mid + 1;
            }
        }

        return false;
    }
}
```
# LeetCode_572_另一个树的子树
## 题目
给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。

示例 1:
```
给定的树 s:

     3
    / \
   4   5
  / \
 1   2
给定的树 t：

   4 
  / \
 1   2
返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值。
```
示例 2:
```
给定的树 s：

     3
    / \
   4   5
  / \
 1   2
    /
   0
给定的树 t：

   4
  / \
 1   2
返回 false。
```
## 解法
### 思路
嵌套dfs
- 外层递归遍历树的所有节点，当val相等时开始内层递归
- 内层递归时判断是否符合题目要求，并返回结果
- 外层返回时通过或运算获得最终结果
### 代码
```java
class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }

        if (s == null || t == null) {
            return false;
        }

        if (s.val == t.val) {
            return dfs(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
        }

        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private boolean dfs(TreeNode s, TreeNode t) {
        if (s == null) {
            return t == null;
        }

        if (t == null) {
            return false;
        }

        if (s.val != t.val) {
            return false;
        }

        return dfs(s.left, t.left) && dfs(s.right, t.right);
    }
}
```
## 解法二
### 思路
使用类变量存储t
### 代码
```java
class Solution {
    private TreeNode node;
    public boolean isSubtree(TreeNode s, TreeNode t) {
        node = t;
        return dfs(s, t);
    }

    private boolean dfs(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }

        if (s == null || t == null) {
            return false;
        }

        return s.val == t.val ? dfs(s.left, t.left) && dfs(s.right, t.right) ||
                                dfs(s.left, t) || dfs(s.right, t) : 
                                dfs(s.left, node) || dfs(s.right, node);
    }                               
}
```
# LeetCode_1010_总持续时间可被60整除的歌曲
## 题目
在歌曲列表中，第 i 首歌曲的持续时间为 time[i] 秒。

返回其总持续时间（以秒为单位）可被 60 整除的歌曲对的数量。形式上，我们希望索引的数字  i < j 且有 (time[i] + time[j]) % 60 == 0。

示例 1：
```
输入：[30,20,150,100,40]
输出：3
解释：这三对的总持续时间可被 60 整数：
(time[0] = 30, time[2] = 150): 总持续时间 180
(time[1] = 20, time[3] = 100): 总持续时间 120
(time[1] = 20, time[4] = 40): 总持续时间 60
```
示例 2：
```
输入：[60,60,60]
输出：3
解释：所有三对的总持续时间都是 120，可以被 60 整数。
```
提示：
```
1 <= time.length <= 60000
1 <= time[i] <= 500
```
## 失败解法
### 思路
嵌套循环，内外层元素相加判断是否取余60为0
### 失败原因
超出时间限制
### 代码
```java
class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        int count = 0;
        for (int i = 0; i < time.length; i++) {
            for (int j = i + 1; j < time.length; j++) {
                if ((time[i] + time[j]) % 60 == 0) {
                    count++;
                }
            }
        }
        
        return count;    
    }
}
```
## 解法
### 思路
- 遍历数组并先取余60，并计算这个余数的个数，储存在数组中
- 然后从1开始遍历直到小于30，计算两个元素相加等于60的元素的乘积，累加到ans中
- 从[1,30)的原因是，有两个特殊情况，0和30，因为它们和自己相加就是60或可被60整除，所以要对个数计算组合
### 代码
```java
class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        if (time == null || time.length == 0) {
            return 0;
        }

        int[] bucket = new int[60];
        for (int i = 0; i < time.length; i++) {
            bucket[time[i] % 60]++;
        }

        int count = 0;
        for (int i = 1; i < 30; i++) {          
            if (bucket[i] == 0  || bucket[60 - i] == 0) {
                continue;
            }
            count += bucket[i] * bucket[60 - i];
        }

        count += bucket[0] * (bucket[0] - 1) / 2;
        count += bucket[30] * (bucket[30] - 1) / 2;

        return count;
    }
}
```
## 优化代码
### 思路
把2中特殊情况整合在一次循环中，直接循环数组，计数的过程就是把当前元素和可以相加称为60的元素相乘，这样就把当前这个数可以组合通过求组合的方式在遍历的过程中累加到了ans中。但这种解法虽然间接，但速度并没有未优化的解法快。
### 代码
```java
class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        int ans = 0;
        int[] bucket = new int[60];
        for (int num : time) {
            ans += bucket[(60 - num % 60) % 60];
            bucket[num % 60]++;
        }
        return ans;
    }
}
```
# LeetCode_198_打家劫舍
## 题目
你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。

示例 1:
```
输入: [1,2,3,1]
输出: 4
解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     偷窃到的最高金额 = 1 + 3 = 4 。
```
示例 2:
```
输入: [2,7,9,3,1]
输出: 12
解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
```
## 解法
### 思路
使用动态规划，找到动态转移方程式：
```math
f(k) = max(f(k - 2) + Ai, f(k - 1))
```
方程式用来解释整个过程中最简单的情况：
- 当只有一个元素的时候：f(1) = A1 => preMax
- 当有两个元素的时候：f(2) = max(A1, A2) => curMax
- 当有三个元素的时候：f(3) = max(max(A1, A2), A1 + A3)

所以，以此类推，整个过程就是在求，当前最大值curMax与上一次最大值和当前元素和preMax + Ak之间的最大值的过程。
### 代码
```java
class Solution {
    public int rob(int[] nums) {
        int preMax = 0, curMax = 0;
        for (int num : nums) {
            int tmp = curMax;
            curMax = Math.max(preMax + num, curMax);
            preMax = tmp;
        }
        return curMax;
    }
}
```
# LeetCode_1018_可被5整除的二进制前缀
## 题目

## 失败解法
### 思路
- 遍历数组累加计算获得数组代表的值
- 然后右移位计算每一位
### 失败原因
溢出
### 代码
```java
class Solution {
    public List<Boolean> prefixesDivBy5(int[] A) {
        int num = 0;
        for (int i = A.length - 1; i >= 0; i--) {
            num += Math.pow(2, A.length - 1 - i) * A[i];
        }

        List<Boolean> ans = new ArrayList<>(A.length);
        for (int i = A.length - 1; i >= 0; i--){
            ans.add(0, num % 5 == 0);
            num >>= 1;
        }
        return ans;
    }
}
```
## 解法
### 思路
如果有溢出，就需要简化累加的过程，不要计算所有位
- 如果n取余5为0，那么右移位1位，取余也是0
- 所以从最低位开始，记录每一次的余数，然后右移余数1位再加当前位的值来求取余5的值是否为0
### 代码
```java
class Solution {
    public List<Boolean> prefixesDivBy5(int[] A) {
        int pre = 0;
        List<Boolean> ans = new ArrayList<>(A.length);
        for (int i = 0; i < A.length; i++) {
            pre = ((pre << 1) + A[i]) % 5;
            ans.add(pre == 0);
        }
        
        return ans;
    }
}
```
# LeetCode_125_验证回文串
## 题目
给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。

说明：本题中，我们将空字符串定义为有效的回文串。

示例 1:
```
输入: "A man, a plan, a canal: Panama"
输出: true
```
示例 2:
```
输入: "race a car"
输出: false
```
## 解法
### 思路
- 通过头尾指针移动来判断是否符合规则
- 且如果非数字和字母需要跳过
### 代码
```java
class Solution {
    public boolean isPalindrome(String s) {
        char[] cs = s.toCharArray();
        int head = 0, tail = cs.length - 1;
        while (head < tail) {
            if (!Character.isLetter(cs[head]) && !Character.isDigit(cs[head])) {
                head++;
                continue;
            }

            if (!Character.isLetter(cs[tail]) && !Character.isDigit(cs[tail])) {
                tail--;
                continue;
            }

            if (cs[head] == cs[tail]) {
                head++;
                tail--;
                continue;
            }

            if (cs[head] >= 97) {
                cs[head] -= 32;
            }
            
            if (cs[tail] >= 97) {
                cs[tail] -= 32;
            }
            
            if (cs[head] != cs[tail]) {
                return false;
            }
            
            head++;
            tail--;
        }
        return true;
    }
}
```
# LeetCode_66_加一
## 题目
给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。

最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。

你可以假设除了整数 0 之外，这个整数不会以零开头。

示例 1:
```
输入: [1,2,3]
输出: [1,2,4]
解释: 输入数组表示数字 123。
```
示例 2:
```
输入: [4,3,2,1]
输出: [4,3,2,2]
解释: 输入数组表示数字 4321。
```
## 解法
### 思路
从数组尾部开始向前遍历，需要考虑进位，进而需要考虑极端情况下元素都是9时，数组长度要变化。
### 代码
```java
class Solution {
    public int[] plusOne(int[] digits) {
        int[] ans = new int[digits.length + 1];
        int carry = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            int sum = carry + digits[i];
            ans[i + 1] = sum % 10;
            carry = sum >= 10 ? 1 : 0;
        }

        if (carry == 1) {
            ans[0] = 1;
            return ans;
        } else {
            int[] newAns = new int[ans.length - 1];
            System.arraycopy(ans, 1, newAns, 0, ans.length - 1);
            return newAns;
        }
    }
}
```
# LeetCode_374_猜数字大小
## 题目
我们正在玩一个猜数字游戏。 游戏规则如下：

我从 1 到 n 选择一个数字。 你需要猜我选择了哪个数字。

每次你猜错了，我会告诉你这个数字是大了还是小了。

你调用一个预先定义好的接口 guess(int num)，它会返回 3 个可能的结果（-1，1 或 0）：
```
-1 : 我的数字比较小
 1 : 我的数字比较大
 0 : 恭喜！你猜对了！
 ```
示例 :
```
输入: n = 10, pick = 6
输出: 6
```
## 解法
### 思路
二分搜索寻找中间值
### 代码
```java
public class Solution extends GuessGame {
    public int guessNumber(int n) {
        int head = 0, tail = n;
        while (head <= tail) {
            int mid = head + ((tail - head) >> 1);
            if (guess(mid) == 0) {
                return mid;
            }

            if (guess(mid) == -1) {
                tail = mid - 1;
            } else {
                head = mid + 1;
            }
        }

        return -1;
    }
}
```
# LeetCode_387_字符串中第一个唯一字符
## 题目
给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。

案例:
```
s = "leetcode"
返回 0.

s = "loveleetcode",
返回 2.
```
```
注意事项：您可以假定该字符串只包含小写字母。
```
## 解法
### 思路
- 用一个桶来计数字符
- 另一个桶记录字符的下标
- 遍历第一个桶，找到计数为1的字符，再从第二个桶中找到对应的下标
- 遍历过程中，不断更新计数为1的字符的下标，谁小取谁
- 遍历结束返回下标
### 代码
```java
class Solution {
    public int firstUniqChar(String s) {
        int max = 0;
        char[] cs = s.toCharArray();
        
        for (char c: cs) {
            max = Math.max(c, max);
        }

        int[] numBucket = new int[max + 1];
        Integer[] indexBucket = new Integer[max + 1];

        for (int i = 0; i < cs.length; i++) {
            numBucket[cs[i]]++;
            if (indexBucket[cs[i]] == null) {
                indexBucket[cs[i]] = i;
            }
        }
        
        int index = cs.length;
        for (int i = 0; i < numBucket.length; i++) {
            if (numBucket[i] == 1) {
                index = Math.min(index, indexBucket[i]);
            }
        }
        
        return index == cs.length ? -1 : index;
    }
}
```
## 解法二
### 思路
直接通过Stirng的lastIndexOf和indexOf是否相等来判断是否是唯一的字符，这样只需要循环一次
### 代码
```java
class Solution {
    public int firstUniqChar(String s) {
        int index = -1;
        for (char i = 'a'; i <= 'z'; i++) {
            int start = s.indexOf(i);
            if (start != -1 && s.indexOf(i) == s.lastIndexOf(i)) {
                index = (index == -1 || index > start) ? start : index;
            }
        }

        return index == s.length() ? -1 : index;
    }
}
```
# LeetCode_172_阶乘后的零
## 题目
## 解法
### 思路
阶乘结果中的零取决于乘数中有多少对2和5，因为5的个数少，所以求出乘数中的为5的因数即可
### 代码
```java
class Solution {
    public int trailingZeroes(int n) {
        int ans = 0;
        while (n > 0) {
            n /= 5;
            ans += n;
        }
        return ans;
    }
}
```