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