# LeetCode_959_由斜杠划分区域
## 题目
在由 1 x 1 方格组成的 N x N 网格 grid 中，每个 1 x 1 方块由 /、\ 或空格构成。这些字符会将方块划分为一些共边的区域。

（请注意，反斜杠字符是转义的，因此 \ 用 "\\" 表示。）。

返回区域的数目。

示例 1：
```
输入：
[
  " /",
  "/ "
]
输出：2
```
示例 2：
```
输入：
[
  " /",
  "  "
]
输出：1
```
示例 3：
```
输入：
[
  "\\/",
  "/\\"
]
输出：4
解释：（回想一下，因为 \ 字符是转义的，所以 "\\/" 表示 \/，而 "/\\" 表示 /\。）
```
示例 4：
```
输入：
[
  "/\\",
  "\\/"
]
输出：5
解释：（回想一下，因为 \ 字符是转义的，所以 "/\\" 表示 /\，而 "\\/" 表示 \/。）
```
示例 5：
```
输入：
[
  "//",
  "/ "
]
输出：3
```
提示：
```
1 <= grid.length == grid[0].length <= 30
grid[i][j] 是 '/'、'\'、或 ' '。
```
## 解法
### 思路
- 因为相关联的部分被看作是一块，要算出有几块，就看他们共同属于的根节点有几个，和并查集找掌门类似
- 将每个单元格看作一个有四个顶点的方框，故顶点有`4 * len * len`个
- 每个顶点作为并查集的元素，然后通过是否有被划分来`union`到同一个父节点
- 不断地遍历字符串，并合并父节点
- 字符串遍历结束后，遍历dsu，查看根节点有几个，作为结果返回
### 代码
```java
class Solution {
    public int regionsBySlashes(String[] grid) {
        int len = grid.length;
        DSU dsu = new DSU(4 * len * len);

        for (int r = 0; r < len; r++) {
            for (int c = 0; c < len; c++) {
                int root = 4 * (r * len + c);
                char val = grid[r].charAt(c);

                if (val != '\\') {
                    dsu.union(root, root + 3);
                    dsu.union(root + 1, root + 2);
                }

                if (val != '/') {
                    dsu.union(root, root + 1);
                    dsu.union(root + 2, root + 3);
                }

                if (r < len - 1) {
                    dsu.union(root + 2, root + 4 * len);
                }

                if (r > 0) {
                    dsu.union(root, root - 4 * len + 2);
                }

                if (c > 0) {
                    dsu.union(root + 3, root - 3);
                }

                if (c < len - 1) {
                    dsu.union(root + 1, root + 7);
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < 4 * len * len; i++) {
            if (dsu.find(i) == i) {
                ans++;
            }
        }
        return ans;
    }
}

class DSU {
    public int[] parent;

    public DSU(int N) {
        parent = new int[N];
        for (int i = 0; i < N; ++i)
            parent[i] = i;
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x, int y) {
        parent[find(x)] = find(y);
    }
}
```
# LeetCode_12_整数转罗马数字
## 题目
罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
```
字符          数值
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
```
通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
```
I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
```
示例 1:
```
输入: 3
输出: "III"
```
示例 2:
```
输入: 4
输出: "IV"
```
示例 3:
```
输入: 9
输出: "IX"
```
示例 4:
```
输入: 58
输出: "LVIII"
解释: L = 50, V = 5, III = 3.
```
示例 5:
```
输入: 1994
输出: "MCMXCIV"
解释: M = 1000, CM = 900, XC = 90, IV = 4.
```
## 解法
### 思路
- int类型依次除以1000到10，得到千位，百位和十位的值
- 通过取模得到相处后剩下的余数
- 对逢4逢9的数进行特别处理
- 一位一位的硬做
### 代码
```java
class Solution {
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();

        int t = num / 1000;
        num %= 1000;

        for (int i = 0; i < t; i++) {
            sb.append("M");
        }

        int h = num / 100;
        num %= 100;

        if (h == 9) {
            sb.append("CM");
        } else if (h == 4) {
            sb.append("CD");
        } else if (h >= 5) {
            sb.append("D");
            h -= 5;
            for (int i = 0; i < h; i++) {
                sb.append("C");
            }
        } else {
            for (int i = 0; i < h; i++) {
                sb.append("C");
            }
        }

        int d = num / 10;
        num %= 10;

        if (d == 9) {
            sb.append("XC");
        } else if (d == 4) {
            sb.append("XL");
        } else if (d >= 5) {
            sb.append("L");
            d -= 5;
            for (int i = 0; i < d; i++) {
                sb.append("X");
            }
        } else {
            for (int i = 0; i < d; i++) {
                sb.append("X");
            }
        }
        
        if (num == 9) {
            sb.append("IX");
        } else if (num == 4) {
            sb.append("IV");
        } else if (num >= 5) {
            sb.append("V");
            num -= 5;
            for (int i = 0; i < num; i++) {
                sb.append("I");
            }
        } else {
            for (int i = 0; i < num; i++) {
                sb.append("I");
            }
        }
        
        return sb.toString();
    }
}
```
## 解法二
### 思路
- 声明两个数组，一个存罗马数字，一个存罗马数字对应的int值`num`，罗马数字中包括逢9逢4的特殊数字
- 嵌套循环，从最大的罗马数字开始，处理int值
    - 外层循环遍历从大到小的罗马数字对应的值
    - 内层循环将`num`除以罗马数字对应值，获得该值有多少个，然后append对应个数得罗马字符
    - 内层循环结束以后，对`num`取模罗马数字值
- 循环结束后返回sb
### 代码
```java
class Solution {
    private int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private String[] roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < num / nums[i]; j++) {
                sb.append(roman[i]);
            }
            num %= nums[i];
        }
        return sb.toString();
    }
}
```
# LeetCode_287_寻找重复数
## 题目
给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。

示例 1:
```
输入: [1,3,4,2,2]
输出: 2
```
示例 2:
```
输入: [3,1,3,4,2]
输出: 3
```
说明：
```
不能更改原数组（假设数组是只读的）。
只能使用额外的 O(1) 的空间。
时间复杂度小于 O(n2) 。
数组中只有一个重复的数字，但它可能不止重复出现一次。
```
## 解法
### 思路
二分查找，因为必然有一个元素是有若干重复的，所以通过判断比mid小的数是否大于mid来判断重复的数比mid大还是小
### 代码
```java
class Solution {
    public int findDuplicate(int[] nums) {
        int low = 1, high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2, count = 0;
            for (int num : nums) {
                if (num <= mid) {
                    count++;
                }
            }

            if (count > mid) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        
        return low;
    }
}
```