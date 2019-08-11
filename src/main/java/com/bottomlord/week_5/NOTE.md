# LeetCode_892_三维形体的表面积
## 题目
在 N * N 的网格上，我们放置一些 1 * 1 * 1  的立方体。

每个值 v = grid[i][j] 表示 v 个正方体叠放在对应单元格 (i, j) 上。

请你返回最终形体的表面积。

示例 1：
```
输入：[[2]]
输出：10
```
示例 2：
```
输入：[[1,2],[3,4]]
输出：34
```
示例 3：
```
输入：[[1,0],[0,2]]
输出：16
示例 4：
```
输入：[[1,1,1],[1,0,1],[1,1,1]]
输出：32
示例 5：

输入：[[2,2,2],[2,1,2],[2,2,2]]
输出：46

提示：
```
1 <= N <= 50
0 <= grid[i][j] <= 50
```
## 解法
### 思路
把表面积分成6部分
- 上下两面：(N * N - 为0的元素) * 2
- 4个面：
    - 第1行的v的和
    - 第N行的v的和
    - 第1列的v的和
    - 第N列的v的和
- 上面参差不齐露出的部分

难点在露出的部分，需要遍历二维数组，和相邻4个位置的v作比较，大于相邻的部分就累加表面积里
### 代码
```java
class Solution {
    public int surfaceArea(int[][] grid) {
        return topBottom(grid) + side(grid) + diff(grid);
    }

    private int diff(int[][] grid) {
        int sum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    sum-=2;
                    continue;
                }
                
                if (!outOfRnage(i - 1, j, grid)) {
                    int dif = grid[i][j] - grid[i - 1][j];
                    sum += dif > 0 ? dif : 0;
                }

                if (!outOfRnage(i + 1, j, grid)) {
                    int dif = grid[i][j] - grid[i + 1][j];
                    sum += dif > 0 ? dif : 0;
                }

                if (!outOfRnage(i, j - 1, grid)) {
                    int dif = grid[i][j] - grid[i][j - 1];
                    sum += dif > 0 ? dif : 0;
                }

                if (!outOfRnage(i, j + 1, grid)) {
                    int dif = grid[i][j] - grid[i][j + 1];
                    sum += dif > 0 ? dif : 0;
                }
            }
        }

        return sum;
    }

    private int side(int[][] grid) {
        int sum = 0;
        for (int i = 0; i < grid[0].length; i++) {
            sum += grid[0][i];
        }

        int lastRow = grid.length - 1;
        for (int i = 0; i < grid[lastRow].length; i++) {
            sum += grid[lastRow][i];
        }

        for (int[] row : grid) {
            sum += row[0] + row[row.length - 1];
        }
        return sum;
    }

    private int topBottom(int[][] grid) {
        return grid.length * grid.length * 2;
    }

    private boolean outOfRnage(int row, int col, int[][] grid) {
        return row < 0 || col < 0 || row >= grid.length || col >= grid[row].length;
    }
}
```
## 解法二
### 思路
遍历二维数组：
- 先把当前坐标上的立方体的表面积算出：v * 4 + 2，累加
- 和横坐标上前一个元素比较大小，谁堆叠的立方体少，谁的一面就会被遮盖，当然长的那个立方体的一面也会遮盖，把这两个面从累加值中减去
- 同理和纵坐标也做一次比较
- 遍历结束，返回累加值
### 代码
```java
class Solution {
    public int surfaceArea(int[][] grid) {
        int sum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != 0) {
                    sum += grid[i][j] * 4 + 2;
                }
                
                if (i != 0) {
                    sum -= Math.min(grid[i][j], grid[i - 1][j]) * 2;
                }
                
                if (j != 0) {
                    sum -= Math.min(grid[i][j], grid[i][j - 1]) * 2;
                }
            }
        }
        
        return sum;
    }
}
```
# LeetCode_653_两数之和 IV (输入 BST)
## 题目
给定一个二叉搜索树和一个目标结果，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。

案例 1:
```
输入: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 9

输出: True
```
案例 2:
```
输入: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 28

输出: False
```
## 解法
### 思路
- dfs中序递归遍历得到升序序列
- 迭代寻找是否有满足target的两个值
### 代码
```java
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                int ans = list.get(i) + list.get(j); 
                
                if (ans == k) {
                    return true;
                }
                
                if (ans > k) {
                    break;
                }
            }
        }
        return false;
    }
    
    private void dfs(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        
        dfs(node.left, list);
        list.add(node.val);
        dfs(node.right, list);
    }
}
```
## 解法二
### 思路
嵌套递归
- 第一层递归目的是找到两数相加的第一个数，而是否进入第二层递归的判断依据是，k-当前节点的val是否还等于当前节点的val，如果是说明当前节点不符合要求，因为二叉搜索树的节点不能相等
- 如果符合要求，就进行第二层递归，目的是从根节点开始找到k与第一层递归的那个节点的val的差，如果有就返回true
- 如果如果没有找到，就递归取找左右子树，再重复如上的过程
### 代码
```java
class Solution {
    private TreeNode node;
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        
        if (node == null) {
            node = root;
        }

        int result = k - root.val;
        if (root.val == result) {
            return findTarget(root.left, k) || findTarget(root.right, k);
        }

        boolean find = dfs(this.node, result);
        if (find) {
            return true;
        }

        return findTarget(root.left, k) || findTarget(root.right, k);
    }

    private boolean dfs(TreeNode node, int target) {
        if (node == null) {
            return false;
        }

        if (node.val == target) {
            return true;
        }

        return node.val > target ? dfs(node.left, target) : dfs(node.right, target);
    }
}
```
# LeetCode_448_找到所有数组中消失的数字
## 题目
给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。

找到所有在 [1, n] 范围之间没有出现在数组中的数字。

您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。

示例:
```
输入:
[4,3,2,7,8,2,3,1]

输出:
[5,6]
```
## 解法
### 思路
- 遍历数组
    - 通过标记元素值对应下标为-1来表明这个位置的值已经找到
    - 通过通过标记下标对应地元素为0来表明这个位置地值还没有找到
- 再通过遍历重新设置过地数组，把为0的数组放入list
### 代码
```java
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                int tmp = nums[i];
                nums[i] = 0;
                while (tmp > 0) {
                    int next = nums[tmp - 1];
                    nums[tmp - 1] = -1;
                    tmp = next;
                }
            }
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                ans.add(i + 1);
            }
        }
        
        return ans;
    }
}
```
# LeetCode_437_路径总和III
## 题目
给定一个二叉树，它的每个结点都存放着一个整数值。

找出路径和等于给定数值的路径总数。

路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。

二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。

示例：
```
root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

返回 3。和等于 8 的路径有:

1.  5 -> 3
2.  5 -> 2 -> 1
3.  -3 -> 11
```
## 解法
### 思路
因为路径是向下的，所以使用dfs。

感觉很像653题，从某个节点开始下钻。所以是嵌套的递归。
- 外层递归每一层的节点，选择从该节点开始内层递归，并判断当前节点是否与sum相等
- 内层递归从该节点的下钻路径，计算和是否满足要求，满足的话就count++
### 代码
```java
class Solution {
    private int count = 0;
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return count;
        }
        
        dfs(root, sum);
        return count;
    }
    
    private void dfs(TreeNode node, int sum) {
        if (node == null) {
            return;
        }
        
        if (node.val == sum) {
            this.count++;
        }
        
        dfs(node.left, sum);
        
        doSearch(node.left, node.val, sum);
        doSearch(node.right, node.val, sum);
        
        dfs(node.right, sum);
    }
    
    private void doSearch(TreeNode node, int sum, int target) {
        if (node == null) {
            return;
        }
        
        sum += node.val;
        
        if (sum == target) {
            this.count++;
        }
        
        doSearch(node.left, sum, target);
        doSearch(node.right, sum, target);
    }
}
```
## 解法二
### 思路
解法一有非常多的重复遍历，效率不高，看到有人分享了dfs+回溯的方法，非常有效。主要思想就是：
- dfs深度优先的每一次深度搜索过程中，把每一层的累加值记录在一个map中
- 如果到节点N的累加值为a，那么如果在map中能找到a - sum的key，就说明，从该key所对应的节点开始，到N点的总和就是sum
- 所以只需要将key对应的value累加，就可以得到结果
- 但需要注意的是，map里存的必需是一条通路上的节点，所以在return之前需要将节点值key对应的value从map中减去
### 代码
```java
class Solution {
    public int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        return dfs(root, sum, 0, map);
    }

    private int dfs(TreeNode node, int sum, int pathSum, Map<Integer, Integer> map) {
        if (node == null) {
            return 0;
        }

        pathSum += node.val;
        int count = map.getOrDefault(pathSum - sum, 0);
        map.put(pathSum, map.getOrDefault(pathSum, 0) + 1);
        int ans = count + dfs(node.left, sum, pathSum, map) + dfs(node.right, sum, pathSum, map);
        map.put(pathSum, map.get(pathSum) - 1);
        return ans;
    }
}
```
# LeetCode_453_最小移动次数使数组元素相等
## 题目
给定一个长度为 n 的非空整数数组，找到让数组所有元素相等的最小移动次数。每次移动可以使 n - 1 个元素增加 1。

示例:
```
输入:
[1,2,3]

输出:
3
```
解释:
```
只需要3次移动（注意每次移动会增加两个元素的值）：

[1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
```
## 解法
### 思路
- 将数组排序
- 试想，将原最大值以外的所有元素增加最大值和最小值的差diff，原最大元素a[n-1]就和原最小元素a[0]一样了
- 因为经过了排序，且同时增大diff，所以最小元素到第二大元素的顺序是不变的，因此a[n-1] == a[0]变成了最小值，a[n-2]变成了最大值
- 那么以此类推，可以不断地将新的最大值和新的最小值的差值累加，直到所有的元素都相等
### 代码
```java
class Solution {
    public int minMoves(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        for (int i = nums.length - 1; i > 0; i--) {
            ans += nums[i] - nums[0];
        }
        return ans;
    }
}
```
# LeetCode_286_缺失的数字
## 题目
给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。

示例 1:
```
输入: [3,0,1]
输出: 2
示例 2:

输入: [9,6,4,2,3,5,7,0,1]
输出: 8
```
## 解法
### 思路
- 遍历数组，求出
    - 最大值
    - 累加的和
    - 最小值
- 查看最小值
    - 如果最小值不为0，返回0
    - 如果最小值为0，计算1到最大值的累加和与sum的差
        - 如果为0，返回max+1
        - 如果不为0，返回差
### 代码
```java
class Solution {
    public int missingNumber(int[] nums) {
        int max = 0, min = nums.length, sum = 0;
        for (int num : nums) {
            min = Math.min(num, min);
            max = Math.max(num, max);
            sum += num;
        }

        if (min != 0) {
            return 0;
        }
        
        int orign = max * (max + 1) / 2;
        return orign != sum ? orign - sum : max + 1;
    }
}
```
## 解法二
### 思路
- 使用异或两次一样的值，数不变的特性
- 将下标和元素异或的值，再异或到初始的数组长度(也就是数组应该是的最大值上)
- 遍历结束，返回那个变量即可
### 代码
```java
class Solution {
    public int missingNumber(int[] nums) {
        int ans = nums.length;
        for (int i = 0; i < nums.length; i++) {
            ans ^= i ^ nums[i];
        }
        return ans;
    }
}
```
# LeetCode_38_报数
## 题目
报数序列是一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：
```
1.     1
2.     11
3.     21
4.     1211
5.     111221
1 被读作  "one 1"  ("一个一") , 即 11。
11 被读作 "two 1s" ("两个一"）, 即 21。
21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。

给定一个正整数 n（1 ≤ n ≤ 30），输出报数序列的第 n 项。

注意：整数顺序将表示为一个字符串。
```
示例 1:
```
输入: 1
输出: "1"
示例 2:

输入: 4
输出: "1211"
```
## 解法一
### 思路
用一个变量记录上一个字符串的内容，方便对该字符串做处理，遍历字符数组，对字符进行计数
- 准备2个变量：
    - 当前出现的字符
    - 字符未变化时持续出现的次数
- 在字符变化的时候，将该字符为变化过程中出现的次数和字符append到StringBuilder中
- 在字符未变化的时候，将长度累加
### 代码
```java
class Solution {
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }

        if (n == 2) {
            return "11";
        }
        
        String pre = "11";
        while (n-- > 2) {
            char[] cs = pre.toCharArray();

            StringBuilder sb = new StringBuilder();
            char preC = cs[0];
            int len = 1;
            
            for (int i = 1; i < cs.length; i++) {
                if (preC == cs[i]) {
                    len++;
                } else {
                    sb.append(len).append(preC);
                    preC = cs[i];
                    len = 1;
                }
            }

            sb.append(len).append(preC);
            pre = sb.toString();
        }
        return pre;
    }
}
```
# LeetCode_371_两整数之和
## 题目
不使用运算符 + 和 - ​​​​​​​，计算两整数 ​​​​​​​a 、b ​​​​​​​之和。

示例 1:
```
输入: a = 1, b = 2
输出: 3
```
示例 2:
```
输入: a = -2, b = 3
输出: 1
```
## 解法
### 思路
通过&和^来处理加法
- 异或，如同二进制的相加，只是没有进位
- 与，通过位上的数是1还是0，来判断当前位是否需要进位
- 只要存在1，那就直接左移位，目的是把进位的值直接与异或相加的值再来一次相加，再一次得到没有进位的相加值
- 而判断是否有进位，需要左移的up值与还没有和进位相加过的那个值来与一下，判断是否有连续进位，所以这里会用到一个tmp暂存那个还没有异或的值
- 一直异或到没有进位的值为止，将结果返回
### 代码
```java
class Solution {
    public int getSum(int a, int b) {
        int ans = a ^ b, up = a & b, tmp;

        while (up != 0) {
            up <<= 1;
            tmp = ans;
            ans ^= up;
            up &= tmp;
        }

        return ans;
    }
}
```
# LeetCode_860_柠檬水找钱
## 题目
在柠檬水摊上，每一杯柠檬水的售价为 5 美元。

顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。

每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。

注意，一开始你手头没有任何零钱。

如果你能给每位顾客正确找零，返回 true ，否则返回 false 。

示例 1：
```
输入：[5,5,5,10,20]
输出：true
解释：
前 3 位顾客那里，我们按顺序收取 3 张 5 美元的钞票。
第 4 位顾客那里，我们收取一张 10 美元的钞票，并返还 5 美元。
第 5 位顾客那里，我们找还一张 10 美元的钞票和一张 5 美元的钞票。
由于所有客户都得到了正确的找零，所以我们输出 true。
```
示例 2：
```
输入：[5,5,10]
输出：true
```
示例 3：
```
输入：[10,10]
输出：false
```
示例 4：
```
输入：[5,5,10,10,20]
输出：false
解释：
前 2 位顾客那里，我们按顺序收取 2 张 5 美元的钞票。
对于接下来的 2 位顾客，我们收取一张 10 美元的钞票，然后返还 5 美元。
对于最后一位顾客，我们无法退回 15 美元，因为我们现在只有两张 10 美元的钞票。
由于不是每位顾客都得到了正确的找零，所以答案是 false。
```
提示：
```
0 <= bills.length <= 10000
bills[i] 不是 5 就是 10 或是 20 
```
## 解法一
### 思路
暴力破解
- 使用map存储5，10，20的纸币数量
- 根据支付的金钱，判断map中是否有可以找零的方案，如果没有就返回false
### 代码
```java
class Solution {
    public boolean lemonadeChange(int[] bills) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(5, 0);
        map.put(10, 0);
        map.put(20, 0);
        
        for (int num: bills) {
            map.put(num, map.get(num) + 1);
            
            if (num == 5) {
                continue;
            }
            
            int five = map.get(5);
            int ten = map.get(10);
            
            if (num == 10) {
                if (five >= 1) {
                    map.put(5, five - 1);
                    continue;
                }
                
                return false;
            }
            
            if (num == 20) {
                if (ten >= 1 && five >= 1) {
                    map.put(5, five - 1);
                    map.put(10, ten - 1);
                    continue;
                }
                
                if (five >= 3) {
                    map.put(5, five - 3);
                    continue;
                }
                
                return false;
            }
        }
        
        return true;
    }
}
```
## 优化代码
### 思路
使用变量代替map
### 代码
```java
class Solution {
    public boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;
        for (int num: bills) {
            if (num == 5) {
                five++;
                continue;
            }

            if (num == 10) {
                ten++;
                
                if (five >= 1) {
                    five--;
                    continue;
                }

                return false;
            }

            if (num == 20) {
                if (ten >= 1 && five >= 1) {
                    ten--;
                    five--;
                    continue;
                }

                if (five >= 3) {
                    five -= 3;
                    continue;
                }

                return false;
            }
        }

        return true;
    }
}
```
# LeetCode_404_左叶子之和
## 题目
计算给定二叉树的所有左叶子之和。

示例：
```
    3
   / \
  9  20
    /  \
   15   7

在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
```
## 解法
### 思路
dfs递归
### 代码
```java
class Solution {
    private int sum = 0;
    public int sumOfLeftLeaves(TreeNode root) {
        dfs(root, false);
        return sum;
    }
    
    private void dfs(TreeNode node, boolean isLeft) {
        if (node == null) {
            return;
        }
        
        if (node.left == null && node.right == null) {
            if (isLeft) {
                sum += node.val;
            }
            return;
        }
        
        dfs(node.left, true);
        dfs(node.right, false);
    }
}
```
## 优化代码
### 思路
不使用类变量
### 代码
```java
class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        return dfs(root, false);
    }
    
    private int dfs(TreeNode node, boolean isLeft) {
        if (node == null) {
            return 0;
        }
        
        if (node.left == null && node.right == null) {
            if (isLeft) {
                return node.val;
            }
            
            return 0;
        }
        
        return dfs(node.left, true) + dfs(node.right, false);
    }
}
```
# LeetCode_506_相对名词
## 题目
给出 N 名运动员的成绩，找出他们的相对名次并授予前三名对应的奖牌。前三名运动员将会被分别授予 “金牌”，“银牌” 和“ 铜牌”（"Gold Medal", "Silver Medal", "Bronze Medal"）。

(注：分数越高的选手，排名越靠前。)

示例 1:
```
输入: [5, 4, 3, 2, 1]
输出: ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"]
解释: 前三名运动员的成绩为前三高的，因此将会分别被授予 “金牌”，“银牌”和“铜牌” ("Gold Medal", "Silver Medal" and "Bronze Medal").
余下的两名运动员，我们只需要通过他们的成绩计算将其相对名次即可。
```
提示:
```
N 是一个正整数并且不会超过 10000。
所有运动员的成绩都不相同。
```
## 解法
### 思路
空间换时间
- 遍历数组，找到最大值，创建新的数组用来作为桶
- 再次遍历数组，把num放在桶中，桶的下标是元素值，桶的值对应它的下标
- 然后从桶的最大处向前遍历，并计数count作为名次。名次1、2、3做一次转义。
- 把名次放入ans数组中，ans的下标对应桶的元素，值为count
### 代码
```java
class Solution {
    public String[] findRelativeRanks(int[] nums) {
        String[] ans = new String[nums.length];
        int max = 0;
        for (int num: nums) {
            max = Math.max(num, max);
        }

        Integer[] bucket = new Integer[max + 1];
        for (int i = 0; i < nums.length; i++) {
            bucket[nums[i]] = i;
        }

        int count = 0;
        for (int i = bucket.length - 1; i >= 0; i--) {
            if (bucket[i] != null) {
                count++;
                ans[bucket[i]] = count > 3 ? "" + count : count == 1 ? "Gold Medal" : count == 2 ? "Silver Medal" : "Bronze Medal";
            }
        }
        return ans;
    }
}
```
# LeetCode_606_根据二叉树创建字符串
## 题目
你需要采用前序遍历的方式，将一个二叉树转换成一个由括号和整数组成的字符串。

空节点则用一对空括号 "()" 表示。而且你需要省略所有不影响字符串与原始二叉树之间的一对一映射关系的空括号对。

示例 1:
```
输入: 二叉树: [1,2,3,4]
       1
     /   \
    2     3
   /    
  4     

输出: "1(2(4))(3)"

解释: 原本将是“1(2(4)())(3())”，
在你省略所有不必要的空括号对之后，
它将是“1(2(4))(3)”。
```
示例 2:
```
输入: 二叉树: [1,2,3,null,4]
       1
     /   \
    2     3
     \  
      4 

输出: "1(2()(4))(3)"

解释: 和第一个示例相似，
除了我们不能省略第一个对括号来中断输入和输出之间的一对一映射关系。
```
## 解法
### 思路
- dfs前序遍历二叉树，需要注意的是如果左子树如果为空，要加括号。
- 递归函数中带上一个队列
    1. 先压入当前节点的val
    2. 如果是叶子节点就直接返回
    3. 在递归左子树的时候就先压入一个左括号，返回的时候就压入一个右括号。
    4. 判断右子树是否为空，如果不为空也和左子树一样，否则就不下钻
- 递归结束后，就循环队列append到StringBuilder上，之后返回toString
### 代码
```java
class Solution {
    public String tree2str(TreeNode t) {
        Queue<String> queue = new ArrayDeque<>();
        dfs(t, queue);

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            sb.append(queue.poll());
        }

        return sb.toString();
    }

    private void dfs(TreeNode node, Queue<String> stack) {
        if (node == null) {
            return;
        }

        stack.offer("" + node.val);
        
        if (node.left == null && node.right == null) {
            return;
        }
        
        stack.offer("(");
        dfs(node.left, stack);
        stack.offer(")");
        
        if (node.right != null) {
            stack.offer("(");
            dfs(node.right, stack);
            stack.offer(")");    
        }
    }
}
```
## 解法二
### 思路
直接使用StringBuilder来记录，少一次遍历
### 代码
```java
class Solution {
    public String tree2str(TreeNode t) {
        StringBuilder sb = new StringBuilder();
        dfs(t, sb);
        return sb.toString();
    }
    
    private void dfs(TreeNode node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        
        sb.append(node.val);
        
        if (node.left == null && node.right == null) {
            return;
        }
        
        sb.append("(");
        dfs(node.left, sb);
        sb.append(")");
        
        if (node.right != null) {
            sb.append("(");
            dfs(node.right, sb);
            sb.append(")");
        }
    }
}
```
# LeetCode_733_图像渲染
## 题目
有一幅以二维整数数组表示的图画，每一个整数表示该图画的像素值大小，数值在 0 到 65535 之间。

给你一个坐标 (sr, sc) 表示图像渲染开始的像素值（行 ，列）和一个新的颜色值 newColor，让你重新上色这幅图像。

为了完成上色工作，从初始坐标开始，记录初始坐标的上下左右四个方向上像素值与初始坐标相同的相连像素点，接着再记录这四个方向上符合条件的像素点与他们对应四个方向上像素值与初始坐标相同的相连像素点，……，重复该过程。将所有有记录的像素点的颜色值改为新的颜色值。

最后返回经过上色渲染后的图像。

示例 1:
```
输入: 
image = [[1,1,1],[1,1,0],[1,0,1]]
sr = 1, sc = 1, newColor = 2
输出: [[2,2,2],[2,2,0],[2,0,1]]
解析: 
在图像的正中间，(坐标(sr,sc)=(1,1)),
在路径上所有符合条件的像素点的颜色都被更改成2。
注意，右下角的像素没有更改为2，
因为它不是在上下左右四个方向上与初始点相连的像素点。
```
注意:
```
image 和 image[0] 的长度在范围 [1, 50] 内。
给出的初始点将满足 0 <= sr < image.length 和 0 <= sc < image[0].length。
image[i][j] 和 newColor 表示的颜色值在范围 [0, 65535]内。
```
## 解法
### 思路
dfs递归加上记忆化搜索
### 代码
```java
class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int color = image[sr][sc];
        if (color == newColor) {
            return image;
        }
        
        Map<Integer, Set<Integer>> mem = new HashMap<>();
        dfs(image, color, sr, sc, newColor, mem);

        return image;
    }

    private void dfs(int[][] image, int origin, int sr, int sc, int newColor, Map<Integer, Set<Integer>> mem) {
        if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[sr].length) {
            return;
        }
        
        Set<Integer> set = mem.get(sr);
        if (set != null) {
            if (set.contains(sc)) {
                return;
            }
            set.add(sc);
        } else {
            set = new HashSet<>();
            set.add(sc);
        }
        mem.put(sr, set);

        if (image[sr][sc] != origin) {
            return;
        }

        image[sr][sc] = newColor;

        dfs(image, origin, sr + 1, sc, newColor, mem);
        dfs(image, origin, sr - 1, sc, newColor, mem);
        dfs(image, origin, sr, sc + 1, newColor, mem);
        dfs(image, origin, sr, sc - 1, newColor, mem);
    }
}
```
## 优化代码
### 思路
省去了mem
### 代码
```java
class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int color = image[sr][sc];
        if (color == newColor) {
            return image;
        }

        image[sr][sc] = newColor;

        if (sr - 1 >= 0 && color == image[sr - 1][sc]) {
            floodFill(image, sr - 1, sc, newColor);
        }

        if (sr + 1 < image.length && color == image[sr + 1][sc]) {
            floodFill(image, sr + 1, sc, newColor);
        }

        if (sc - 1 >= 0 && color == image[sr][sc - 1]) {
            floodFill(image, sr, sc - 1, newColor);
        }

        if (sc + 1 < image[sr].length && color == image[sr][sc + 1]) {
            floodFill(image, sr, sc + 1, newColor);
        }
        
        return image;
    }
}
```
# LeetCode_121_买卖股票的最佳时机
## 题目
给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。

如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。

注意你不能在买入股票前卖出股票。

示例 1:
```
输入: [7,1,5,3,6,4]
输出: 5
解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
```
示例 2:
```
输入: [7,6,4,3,1]
输出: 0
解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
```
## 解法一
### 思路
暴力破解法，两层循环来判断最大的收益
### 代码
```java
class Solution {
    public int maxProfit(int[] prices) {
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            int one = prices[i];
            for (int j = i + 1; j < prices.length; j++) {
                max = Math.max(max, prices[j] - one);
            }
        }
        return max;
    }
}
```
## 解法二
### 思路
通过数组中最低点和之后的最高点之间的差，就可以得到数组中一次买卖能得到的最大值。
- 初始两个变量，min初始化为最大值，profit初始化为0
- 遍历数组
- 如果当前元素比min小，把当前元素设置为最小值
- 如果当前元素比min大，就拿这个值减去min的值与profit比较，取最大值
- 遍历结束后返回profit
### 代码
```java
class Solution {
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE, profit = 0;
        for (int price : prices) {
            if (price < min) {
                min = price;
            } else {
                profit = Math.max(profit, price - min);
            }
        }
        return profit;
    }
}
```
# LeetCode_704_二分查找
## 题目

## 解法
### 思路
- 设置头尾指针进入循环，推出条件是头指针大于尾指针
- 通过中位数下标mid对应的中位数num来判断是否与target相等，如果是就返回mid
- 如果不是，就通过num来判断指针的变化
    - 如果num小于target，说明找的数在当前中位数的右边，头指针变为mid + 1
    - 如果num大于target，说明找的数在当前中位数的左边，尾指针变为mid - 1
- 如果没有找到，就返回-1
### 代码
```java
class Solution {
    public int search(int[] nums, int target) {
        int head = 0, tail = nums.length - 1;
        while (head <= tail) {
            int mid = (tail - head) / 2 + head;

            int num = nums[mid];

            if (num == target) {
                return mid;
            }

            if (num > target) {
                tail = mid - 1;
            } else {
                head = mid + 1;
            }
        }
        return -1;
    }
}
```
# LeetCode_937_重新排列日志文件
## 题目
你有一个日志数组 logs。每条日志都是以空格分隔的字串。

对于每条日志，其第一个字为字母数字标识符。然后，要么：
```
标识符后面的每个字将仅由小写字母组成，或；
标识符后面的每个字将仅由数字组成。
我们将这两种日志分别称为字母日志和数字日志。保证每个日志在其标识符后面至少有一个字。
```
将日志重新排序，使得所有字母日志都排在数字日志之前。字母日志按内容字母顺序排序，忽略标识符；在内容相同时，按标识符排序。数字日志应该按原来的顺序排列。

返回日志的最终顺序。

示例 ：
```
输入：["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
输出：["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
```
提示：
```
0 <= logs.length <= 100
3 <= logs[i].length <= 100
logs[i] 保证有一个标识符，并且标识符后面有一个字。
```
## 解法一
### 思路
自定义Arrays.sort方法
- 把字符通过第一个空格分成两部分
    - 标识符
    - 其余
- 通过取余部分的第一个字符判断是数字的还是字母的
- 然后按照如下的规则顺序排序
    - 字母优先于数字
    - 其余部分相同，根据标识符排序
    - 数字部分就按原顺序
### 代码
```java
class Solution {
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (a, b) -> {
            String[] splitA = a.split(" ", 2);
            String[] splitB = b.split(" ", 2);

            boolean isDigitA = Character.isDigit(splitA[1].charAt(0));
            boolean isDigitB = Character.isDigit(splitB[1].charAt(0));

            if (!isDigitA && !isDigitB) {
                int diff = splitA[1].compareTo(splitB[1]);
                return diff != 0 ? diff : splitA[0].compareTo(splitB[0]);
            }

            return isDigitA ? (isDigitB ? 0 : 1) : -1;
        });
        return logs;
    }
}
```
## 解法二
### 思路
- 将字符串按照数字和字母分别放入两个list中
- 对放字母的list做排序，排序规则和解法一一致，只不过不使用String的方法而是使用字符下标
- 然后先遍历字母list再遍历数字list，放入logs中，并返回
### 代码
```java
class Solution {
    public String[] reorderLogFiles(String[] logs) {
       List<String> alph = new ArrayList<>();
        List<String> num = new ArrayList<>();
        for (String log: logs) {
            int i = 0;
            while (log.charAt(i++) != ' ');
            if (Character.isDigit(log.charAt(i))) {
                num.add(log);
            } else {
                alph.add(log);
            }
        }

        alph.sort((a, b) -> {
            int i = 0, j = 0;
            while (a.charAt(i++) != ' ') ;
            while (b.charAt(j++) != ' ') ;

            int diff = a.substring(i).compareTo(b.substring(j));
            return diff != 0 ? diff : a.substring(0, i - 1).compareTo(b.substring(0, j - 1));
        });
        
        int i = 0;
        for (String log: alph) {
            logs[i++] = log;
        }
        for (String log: num) {
            logs[i++] = log;
        }
        
        return logs;
    }
}
```
# LeetCode_67_二进制的求和
## 题目
给定两个二进制字符串，返回他们的和（用二进制表示）。

输入为非空字符串且只包含数字 1 和 0。

示例 1:
```
输入: a = "11", b = "1"
输出: "100"
```
示例 2:
```
输入: a = "1010", b = "1011"
输出: "10101"
```
## 解法
### 思路
模拟二进制的相加过程，需要考虑进位的状况。
- 遍历两个字符数组，获得相同位上的值，相加
- 再加上上一位的进位值
- 获得的值与1做与运算，获得当前位的值，append到sb上
- 然后右移位后再与1做与运算，作为下一次循环的进位值
- 当两个字符数组都遍历结束，返回sb的字符串
### 代码
```java
class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();

        int iA = a.length() - 1;
        int iB = b.length() - 1;

        int carry = 0;
        
        while (iA > -1 || iB > -1) {
            int aBit = iA > -1 ? a.charAt(iA) - '0' : 0;
            int bBit = iB > -1 ? b.charAt(iB) - '0' : 0;

            int sum = aBit + bBit + carry;
            
            sb.insert(0, sum & 1);
            
            carry = (sum >>> 1) & 1;
            
            iA--;
            iB--;
        }
        
        if (carry == 1) {
            sb.insert(0, carry);
        }
        
        return sb.toString();
    }
}
```
# LeetCde_167_两数之和II_输入有序数组
## 题目
给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。

函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。

说明:
```
返回的下标值（index1 和 index2）不是从零开始的。
你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
```
示例:
```
输入: numbers = [2, 7, 11, 15], target = 9
输出: [1,2]
解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
```
## 解法一
### 思路
硬做，两层for循环
### 代码
```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int[] ans = new int[2];
        boolean find = false;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target) {
                    ans[0] = i + 1;
                    ans[1] = j + 1;
                    find = true;
                    break;
                }
            }
            if (find) {
                break;
            }
        }
        
        return find ? ans : new int[0];
    }
}
```
## 解法二
### 思路
还是嵌套for，但是内层使用二分法找target - numbers[i]的值
### 代码
```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int[] ans = new int[2];
        boolean find = false;
        for (int i = 0; i < numbers.length; i++) {
            int num = target - numbers[i], head = i + 1, tail = numbers.length - 1;
            while (head <= tail) {
                int mid = (tail - head) / 2 + head;
                int tmp = numbers[mid];
                if (tmp == num) {
                    ans[0] = i + 1;
                    ans[1] = mid + 1;
                    find = true;
                    break;
                }
                
                if (tmp < num) {
                    head = mid + 1;
                } else {
                    tail = mid - 1;
                }
            }
            
            if (find) {
                break;
            }
        }

        return find ? ans : new int[0];
    }
}
```
## 解法三
### 思路
通过头尾指针来找，时间复杂度上是O(N)，比解法二O(NlogN)更快。
### 代码
```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int head = 0, tail = numbers.length - 1;
        while (head < tail) {
            int sum = numbers[head] + numbers[tail];
            if (sum == target) {
                return new int[]{head + 1, tail + 1};
            }

            if (sum < target) {
                head++;
            } else {
                tail--;
            }
        }
        return new int[0];
    }
}
```
# LeetCode_217_存在重复元素
## 题目
如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。

示例 1:
```
输入: [1,2,3,1]
输出: true
```
示例 2:
```
输入: [1,2,3,4]
输出: false
```
示例 3:
```
输入: [1,1,1,3,3,4,3,2,4,2]
输出: true
```
## 解法一
### 思路
遍历数组，使用set来做判断
### 代码
```java
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num: nums) {
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }
}
```
## 解法二
### 思路
不使用hash表，使用桶，两次for循环，时间复杂度是O(N)，和解法一一样，但是少了对hash表的操作，时间会快一些。
### 代码
```java
class Solution {
    public boolean containsDuplicate(int[] nums) {
        if (nums.length == 0) {
            return false;
        }

        int min = nums[0] , max = min;
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (num < min) {
                min = num;
            } else if (num > max) {
                max = num;
            }
        }

        boolean[] bucket = new boolean[max - min + 1];

        for (int num: nums) {
            if (bucket[num - min]) {
                return true;
            }
            bucket[num - min] = true;
        }
        
        return false;
    }
}
```
## 解法三
### 思路
- 排序
- 遍历数组
- 检查和后一个元素是否相等
### 代码
```java
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; ++i) {
            if (nums[i] == nums[i + 1]) return true;
        }
        return false;
    }
}
```
## 未理解的解法
### 代码
```java
class Solution {
    public boolean containsDuplicate(int[] nums) {
        if (nums.length < 1 || nums[0] == 237384 || nums[0] == -24500) {
            return false;
        }
            
        boolean[] bc = new boolean[1024];
        for (int num : nums) {
            if (bc[num & 1023])
                return true;
            bc[num & 1023] = true;
        }
        return false;
    }
}
```
### 补充
# LeetCode_155_最小栈
## 题目
设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
```
push(x) -- 将元素 x 推入栈中。
pop() -- 删除栈顶的元素。
top() -- 获取栈顶元素。
getMin() -- 检索栈中的最小元素。
```
示例:
```
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> 返回 -3.
minStack.pop();
minStack.top();      --> 返回 0.
minStack.getMin();   --> 返回 -2.
```
## 失败解法
### 思路
- stack实现push、pop、top的功能
- queue实现getMin的功能

但超出了时间限制
### 代码
```java
class MinStack {
    private Stack<Integer> stack;
    private Queue<Integer> queue;
    public MinStack() {
        this.stack = new Stack<>();
        this.queue = new ArrayDeque<>();
    }

    public void push(int x) {
        this.queue.offer(x);
        if (x < this.queue.peek()) {
            while (this.queue.peek() != x) {
                this.queue.offer(this.queue.poll());
            }
        }

        this.stack.push(x);
    }

    public void pop() {
        if (!stack.isEmpty()) {
            int num = this.stack.pop();
            int min = queue.peek();
            while (queue.peek() != num) {
                this.queue.offer(this.queue.poll());
            }
            this.queue.poll();

            if (queue.isEmpty()) {
                return;
            }

            while (queue.peek() != min) {
                this.queue.offer(this.queue.poll());
            }
        }
    }

    public int top() {
        return this.stack.peek();
    }

    public int getMin() {
        return this.queue.peek();
    }
}
```
## 解法一
### 思路
- 一个变量存的是最小值min
- 一个stack存的是和上一个最小值之间的差值，通过这个差值就能得出
    - 如果差值是正数，说明min在这个元素push的时候没有变化，在top的时候通过min+diff可以得到这个元素的值
    - 如果差值是0，说明这个元素和最小值一样大，在pop的时候不需要对min改变
    - 如果差值是负数，说明当前元素比上一个min要小，pop的时候min是需要变成上一个min的
- 还要注意int的溢出，所以泛型使用long，返回时还要使用Math.toIntExact方法
### 代码
```java
class MinStack {
    private Stack<Long> stack;
    private Long min;
    public MinStack() {
        this.stack = new Stack<>();
    }

    public void push(int x) {
        if (this.stack.isEmpty()) {
            this.stack.push(0L);
            this.min = (long)x;
        } else {
            this.stack.push((long)x - min);
            if (x < min) {
                this.min = (long)x;
            }
        }
    }

    public void pop() {
        if (this.stack.isEmpty()) {
            return;
        }

        long diff = this.stack.pop();
        if (diff < 0) {
            this.min -= diff;
        }
    }

    public int top() {
        Long diff = this.stack.peek();
        if (diff < 0) {
            return Math.toIntExact(min);
        }
        return Math.toIntExact(min + diff);
    }

    public int getMin() {
        return Math.toIntExact(this.min);
    }
}
```
# LeetCode_409_最长回文串
## 题目
给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。

在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。

注意:
```
假设字符串的长度不会超过 1010。
```
示例 1:
```
输入:
"abccccdd"

输出:
7
```
解释:
```
我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
```
## 解法
### 思路
算字符的个数，然后求它们的偶数对，如果有任意字符是奇数个就再+1
### 代码
```java
class Solution {
    public int longestPalindrome(String s) {
        int[] bucket = new int[52];
        for (char c: s.toCharArray()) {
            int index;
            if (c >= 'a') {
                index = c - 'a';
            } else {
                index = c - 'A' + 26;
            }
            bucket[index] += 1;
        }
        
        boolean odd = false;
        int len = 0;
        for (int num : bucket) {
            if (num == 0) {
                continue;
            }
            
            if (num % 2 == 0) {
                len += num;
            } else {
                len += num - 1;
                
                if (!odd) {
                    len++;
                    odd = true;
                }
            }
        }
        return len;
    }
}
```
## 优化代码
### 思路
- 不要做那么多判断大小写，就用128长度的数组
- 也不要用标识来判断是否加过一个奇数，直接判断len是否是偶数，如果是，且有一个字符的个数是奇数，就len++
### 代码
```java
class Solution {
    public int longestPalindrome(String s) {
        int[] bucket = new int[128];
        for (char c : s.toCharArray()) {
            bucket[c]++;
        }

        int len = 0;
        for (int num : bucket) {
            len += num / 2 * 2;
            if (num % 2 == 1 && len % 2 == 0) {
                len++;
            }
        }

        return len;
    }
}
```
# LeetCode_563_二叉树的坡度
## 题目
给定一个二叉树，计算整个树的坡度。

一个树的节点的坡度定义即为，该节点左子树的结点之和和右子树结点之和的差的绝对值。空结点的的坡度是0。

整个树的坡度就是其所有节点的坡度之和。

示例:
```
输入: 
         1
       /   \
      2     3
输出: 1
解释: 
结点的坡度 2 : 0
结点的坡度 3 : 0
结点的坡度 1 : |2-3| = 1
树的坡度 : 0 + 0 + 1 = 1
```
注意:
```
任何子树的结点的和不会超过32位整数的范围。
坡度的值不会超过32位整数的范围。
```
## 解法
### 思路
dfs递归
- 退出：节点null,返回0
- 过程：获取左右子树的返回，计算差值的绝对值，累加到sum中
- 返回：左右子树和当前节点的值
### 代码
```java
class Solution {
    private int sum = 0;

    public int findTilt(TreeNode root) {
        dfs(root);
        return sum;
    }
    
    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        int left = dfs(node.left);
        int right = dfs(node.right);
        sum += Math.abs(left - right);
        
        return left + right + node.val;
    }
}
```
## 解法二
### 思路
不要将类变成有状态的，可以进行两次递归
- 第一次递归将节点值变成左右子树节点和的差
- 第二次遍历，将这些差合起来，在根节点时，就把所有第一次处理过的节点都累加起来了
### 代码
```java
class Solution {
    public int findTilt(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root);
        return dfs(root);
    }

    private int dfs(TreeNode node) {
        int leftSum = node.left == null ? 0 : dfs(node.left);
        int rightSum = node.right == null ? 0 : dfs(node.right);
        
        int val = node.val;
        node.val = Math.abs(leftSum - rightSum);
        
        return leftSum + rightSum + val; 
    }
}
```
# LeetCode_917_仅仅反转字母
## 题目
给定一个字符串 S，返回 “反转后的” 字符串，其中不是字母的字符都保留在原地，而所有字母的位置发生反转。

示例 1：
```
输入："ab-cd"
输出："dc-ba"
```
示例 2：
```
输入："a-bC-dEf-ghIj"
输出："j-Ih-gfE-dCba"
```
示例 3：
```
输入："Test1ng-Leet=code-Q!"
输出："Qedo1ct-eeLg=ntse-T!"
```
提示：
```
S.length <= 100
33 <= S[i].ASCIIcode <= 122 
S 中不包含 \ or "
```
## 解法
### 思路
- 头尾指针遍历
- 判断字符是否为字母
- 异或运算换位置
### 代码
```java
class Solution {
    public String reverseOnlyLetters(String S) {
        char[] cs = S.toCharArray();
        int head = 0, tail = cs.length - 1;
        while (head < tail) {
            if (Character.isLetter(cs[head]) && Character.isLetter(cs[tail])) {
                cs[head] ^= cs[tail];
                cs[tail] ^= cs[head];
                cs[head] ^= cs[tail];

                head++;
                tail--;
                continue;
            }

            if (Character.isLetter(cs[head])) {
                tail--;
            } else {
                head++;
            }
        }
        return new String(cs);
    }
}
```
# LeetCode_598_范围求和II
## 题目
给定一个初始元素全部为 0，大小为 m*n 的矩阵 M 以及在 M 上的一系列更新操作。

操作用二维数组表示，其中的每个操作用一个含有两个正整数 a 和 b 的数组表示，含义是将所有符合 0 <= i < a 以及 0 <= j < b 的元素 M[i][j] 的值都增加 1。

在执行给定的一系列操作后，你需要返回矩阵中含有最大整数的元素个数。

示例 1:
```
输入: 
m = 3, n = 3
operations = [[2,2],[3,3]]
输出: 4
```
解释: 
```
初始状态, M = 
[[0, 0, 0],
 [0, 0, 0],
 [0, 0, 0]]

执行完操作 [2,2] 后, M = 
[[1, 1, 0],
 [1, 1, 0],
 [0, 0, 0]]

执行完操作 [3,3] 后, M = 
[[2, 2, 1],
 [2, 2, 1],
 [1, 1, 1]]

M 中最大的整数是 2, 而且 M 中有4个值为2的元素。因此返回 4。
```
注意:
```
m 和 n 的范围是 [1,40000]。
a 的范围是 [1,m]，b 的范围是 [1,n]。
操作数目不超过 10000。
```
## 解法
### 思路
- 求ops中长度为2的数组中两个位置的元素，出现过的最小值，将这两个最小值相乘，就得到了累加次数最多的个数。
- 如果数组长度为0，就直接返回m * n的值
### 代码
```java
class Solution {
    public int maxCount(int m, int n, int[][] ops) {
        if (ops.length == 0) {
            return m * n;
        }
        
        int x = ops[0][0], y = ops[0][1];
        for (int i = 1; i < ops.length; i++) {
            int[] op = ops[i];
            x = Math.min(x, op[0]);
            y = Math.min(y, op[1]);
        }
        
        return x * y;
    }
}
```
# LeetCode_383_赎金信
## 题目
给定一个赎金信 (ransom) 字符串和一个杂志(magazine)字符串，判断第一个字符串ransom能不能由第二个字符串magazines里面的字符构成。如果可以构成，返回 true ；否则返回 false。

(题目说明：为了不暴露赎金信字迹，要从杂志上搜索各个需要的字母，组成单词来表达意思。)

注意：
```
你可以假设两个字符串均只含有小写字母。

canConstruct("a", "b") -> false
canConstruct("aa", "ab") -> false
canConstruct("aa", "aab") -> true
```
## 解法
### 思路
- 把magazine字符数组遍历生成一个桶来计算字符的数量
- 遍历ransomNote字符串来递减桶中指定字符的数量，如果字符对应的元素数量小于0，就返回false
### 代码
```java
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] bucket = new int[128];
        for (char c : magazine.toCharArray()) {
            bucket[c]++;
        }
        
        for (char c : ransomNote.toCharArray()) {
            if (bucket[c] == 0) {
                return false;
            }
            
            bucket[c]--;
        }
        
        return true;
    }
}
```
# LeetCode_110_平衡二叉树
## 题目
给定一个二叉树，判断它是否是高度平衡的二叉树。

本题中，一棵高度平衡二叉树定义为：

一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。

示例 1:
```
给定二叉树 [3,9,20,null,null,15,7]

    3
   / \
  9  20
    /  \
   15   7
返回 true 。
```
示例 2:
```
给定二叉树 [1,2,2,3,3,null,null,4,4]

       1
      / \
     2   2
    / \
   3   3
  / \
 4   4
返回 false 。
```
## 解法一
### 思路
dfs
- 函数参数：
    - 遍历的节点node
    - 节点所在的层数level
- 退出：节点为空，返回level - 1
- 过程：下钻并获得左右子树的level值，比较它们的差是否大于1，与类变量进行与运算
- 返回：返回当前层的左右子树的最大层数
- 最后返回类变量result
### 代码
```java
class Solution {
    private boolean result = true;
    public boolean isBalanced(TreeNode root) {
        dfs(root, 0);
        return result;
    }

    private int dfs(TreeNode node, int level) {
        if (node == null) {
            return level - 1;
        }

        int left = dfs(node.left, level + 1);
        int right = dfs(node.right, level + 1);

        this.result = this.result && Math.abs(left - right) <= 1;

        return Math.max(left, right);
    }
}
```
## 解法二
### 思路
不使用类变量，逻辑和解法一近似，只是如果比对left和right的差值时，如果绝对值超过1，就返回-1，这样最后通过辨别是否返回-1，来确定是否平衡
### 代码
```java
class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        
        return dfs(root, 0) != -1;
    }
    
    private int dfs(TreeNode node, int level) {
        if (node == null) {
            return level - 1;
        }
        
        int left = dfs(node.left, level + 1);
        int right = dfs(node.right, level + 1);
        
        if (left == -1 || right == -1) {
            return -1;
        }
        
        if (Math.abs(left - right) > 1) {
            return -1;
        }
        
        return Math.max(left, right);
    }
}
```
## 优化代码
### 思路
解法二是从顶部开始计算深度，这样多了一个level参数，而且root为null的情况也需要特别处理，如果从底部开始算深度，那么就可以使代码更简单，不需要传递level值，root为null也可以统一处理。
- 退出条件中，node为null就返回0，代表当前层数无效
- 处理逻辑和解法二一致
- 返回中需要累加1，意味着从底部开始计算深度
### 代码
```java
class Solution {
    public boolean isBalanced(TreeNode root) {
        return dfs(root) != -1;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        int left = dfs(node.left);
        int right = dfs(node.right);
        
        if (left == -1 || right == -1) {
            return -1;
        }
        
        if (Math.abs(left - right) > 1) {
            return -1;
        }
        
        return Math.max(left, right) + 1;
    }
}
```
# LeetCode_888_公平的糖果交换
## 题目
爱丽丝和鲍勃有不同大小的糖果棒：A[i] 是爱丽丝拥有的第 i 块糖的大小，B[j] 是鲍勃拥有的第 j 块糖的大小。

因为他们是朋友，所以他们想交换一个糖果棒，这样交换后，他们都有相同的糖果总量。（一个人拥有的糖果总量是他们拥有的糖果棒大小的总和。）

返回一个整数数组 ans，其中 ans[0] 是爱丽丝必须交换的糖果棒的大小，ans[1] 是 Bob 必须交换的糖果棒的大小。

如果有多个答案，你可以返回其中任何一个。保证答案存在。

示例 1：
```
输入：A = [1,1], B = [2,2]
输出：[1,2]
```
示例 2：
```
输入：A = [1,2], B = [2,3]
输出：[1,2]
```
示例 3：
```
输入：A = [2], B = [1,3]
输出：[2,3]
```
示例 4：
```
输入：A = [1,2,5], B = [2,4]
输出：[5,4]
```
提示：
```
1 <= A.length <= 10000
1 <= B.length <= 10000
1 <= A[i] <= 100000
1 <= B[i] <= 100000
保证爱丽丝与鲍勃的糖果总量不同。
答案肯定存在。
```
## 解法一
### 思路
- 遍历两个数组一遍：
    - 分别获得数组的元素总和
    - 生成set集合
- 通过总和获得交换的数字A[i]和B[j]间的差
- 遍历A数组对应set，查询另一个set中是否有该值与差相减后的值
### 代码
```java
class Solution {
    public int[] fairCandySwap(int[] A, int[] B) {
        int maxA = 0, maxB = 0;
        Set<Integer> setA = new HashSet<>();
        Set<Integer> setB = new HashSet<>();
        for (int num : A) {
            maxA += num;
            setA.add(num);
        }

        for (int num : B) {
            maxB += num;
            setB.add(num);
        }
        
        int diff = (maxA - maxB) / 2;
        
        for (int num : setA) {
            if (setB.contains(num - diff)) {
                return new int[]{num, num - diff};
            }
        }
        
        return new int[2];
    }
}
```
## 解法二
### 思路
逻辑和解法一类似，使用数组来代替set
### 代码
```java
class Solution {
    public int[] fairCandySwap(int[] A, int[] B) {
        int maxA = Integer.MIN_VALUE, maxB = Integer.MIN_VALUE, sumA = 0, sumB = 0;

        for (int num : A) {
            maxA = Math.max(maxA, num);
            sumA += num;
        }

        for (int num : B) {
            maxB = Math.max(maxB, num);
            sumB += num;
        }

        boolean[] bucketA = new boolean[maxA + 1];
        boolean[] bucketB = new boolean[maxB + 1];

        for (int num : A) {
            bucketA[num] = true;
        }

        for (int num : B) {
            bucketB[num] = true;
        }

        int diff = (sumA - sumB) / 2;

        for (int i = 0; i < bucketA.length; i++) {
            if (i - diff >= 0 && bucketA[i] && bucketB[i - diff]) {
                return new int[]{i, i - diff};
            }
        }
        
        return new int[2];
    }
}
```
# LeetCode_1013_将数组分成和相等的三部分
## 题目
给定一个整数数组 A，只有我们可以将其划分为三个和相等的非空部分时才返回 true，否则返回 false。

形式上，如果我们可以找出索引 i+1 < j 且满足 (A[0] + A[1] + ... + A[i] == A[i+1] + A[i+2] + ... + A[j-1] == A[j] + A[j-1] + ... + A[A.length - 1]) 就可以将数组三等分。

示例 1：
```
输出：[0,2,1,-6,6,-7,9,1,2,0,1]
输出：true
解释：0 + 2 + 1 = -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1
```
示例 2：
```
输入：[0,2,1,-6,6,7,9,-1,2,0,1]
输出：false
```
示例 3：
```
输入：[3,3,6,5,-2,2,5,1,-9,4]
输出：true
解释：3 + 3 = 6 = 5 - 2 + 2 + 5 + 1 - 9 + 4
```
提示：
```
3 <= A.length <= 50000
-10000 <= A[i] <= 10000
```
## 解法
### 思路
- 算出数组总和sum
- 求出除以3的平均值avg
- 遍历数组求出累加值为avg的次数，每次记录后就清空累加值
- 计算记录的次数是否等于3
### 代码
```java
class Solution {
    public boolean canThreePartsEqualSum(int[] A) {
        int sum = 0, avg = 0, tmp = 0, count = 0, index = 0;
        for (int num : A) {
            sum += num;
        }
        
        if (sum % 3 != 0) {
            return false;
        }
        
        avg = sum / 3;
        
        for (int i = 0; i < A.length; i++) {
            tmp += A[i];
            if (tmp == avg) {
                count++;
                tmp = 0;
            }
            
            if (count == 2) {
                index = i + 1;
                break;
            }
        }
        
        for (int i = index; i < A.length; i++) {
            tmp += A[i];
        }
        
        if (tmp == avg) {
            count++;
        }
        
        return count == 3;
    }
}
```
# LeetCode_661_图片平滑器
## 题目
包含整数的二维矩阵 M 表示一个图片的灰度。你需要设计一个平滑器来让每一个单元的灰度成为平均灰度 (向下舍入) ，平均灰度的计算是周围的8个单元和它本身的值求平均，如果周围的单元格不足八个，则尽可能多的利用它们。

示例 1:
```
输入:
[[1,1,1],
 [1,0,1],
 [1,1,1]]
输出:
[[0, 0, 0],
 [0, 0, 0],
 [0, 0, 0]]
解释:
对于点 (0,0), (0,2), (2,0), (2,2): 平均(3/4) = 平均(0.75) = 0
对于点 (0,1), (1,0), (1,2), (2,1): 平均(5/6) = 平均(0.83333333) = 0
对于点 (1,1): 平均(8/9) = 平均(0.88888889) = 0
```
注意:
```
给定矩阵中的整数范围为 [0, 255]。
矩阵的长和宽的范围均为 [1, 150]。
```
## 解法
### 思路
暴力方法：每一个点都计算一次平均值。
### 代码
```java
class Solution {
    public int[][] imageSmoother(int[][] M) {
        if (M.length == 0) {
            return new int[0][0];
        }
        
        int[][] ans = new int[M.length][M[0].length];
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[i].length; j++) {
                int count = 1, sum = M[i][j];
                if (i - 1 >= 0 && j - 1 >= 0) {
                    count++;
                    sum += M[i - 1][j - 1];
                }
                
                if (i + 1 < M.length && j + 1 < M[i].length) {
                    count++;
                    sum += M[i + 1][j + 1];
                }
                
                if (i - 1 >= 0 && j + 1 < M[i].length) {
                    count++;
                    sum += M[i - 1][j + 1];
                }
                
                if (i + 1 < M.length && j - 1 >= 0) {
                    count++;
                    sum += M[i + 1][j - 1];
                }
                
                if (i - 1 >= 0) {
                    count++;
                    sum += M[i - 1][j];
                }
                
                if (j - 1 >= 0) {
                    count++;
                    sum += M[i][j - 1];
                }
                
                if (i + 1 < M.length) {
                    count++;
                    sum += M[i + 1][j];
                }
                
                if (j + 1 < M[i].length) {
                    count++;
                    sum += M[i][j + 1];
                }
                
                ans[i][j] = sum / count;
            }
        }
        
        return ans;
    }
}
```