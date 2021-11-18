# [LeetCode_319_灯泡开关](https://leetcode-cn.com/problems/bulb-switcher/)
## 解法
### 思路
- 通过观察可知，对于某个开关，n次操作后，该开关的会被开关几次，基于该开关所在位置的对应数的约数有多少个，如果有奇数个就是开着的
- 一个数的约数，只有在该数是完全平方数的时候，才会是奇数个，所以最终就是求在n的范围内完全平方数的个数
- 求完全平方数个数的公式就是$\sqrt{n}$
- 为了防止防止double的精度丢失，在n上+0.5做一下预防
### 代码
```java
class Solution {
    public int bulbSwitch(int n) {
        return (int)Math.sqrt(n + 0.5);
    }
}
```
# [LeetCode_391_完美矩形](https://leetcode-cn.com/problems/perfect-rectangle/)
## 解法
### 思路
- 通过观察发现，如果是完美矩形，除了矩形的四个定点，所有其他子矩形的定点都会出现2次的倍数
- 所以通过遍历所有点的信息，更新所有点的信息，从而获取可能完美矩形的四个点信息，同时将当前矩形的面积求和，并记录下所有子矩形的定点，并放到set中观察是否已经出现，如果出现了就去掉
- 遍历结束后
  - 先计算面积总和是否和累加的总和相等，如果不等就直接返回false
  - 再计算memo中是否只有4个点，且每个点都和最后求得的4个点一致，如果都一致，就返回true，否则也是false
### 代码
```java
class Solution {
    public boolean isRectangleCover(int[][] rectangles) {
        int x1 = Integer.MAX_VALUE, y1 = Integer.MAX_VALUE,
            x2 = Integer.MIN_VALUE, y2 = Integer.MIN_VALUE,
            sum = 0;

        Set<String> memo = new HashSet<>();

        for (int[] rectangle : rectangles) {
            int cx1 = rectangle[0], cy1 = rectangle[1], cx2 = rectangle[2], cy2 = rectangle[3];

            x1 = Math.min(cx1, x1);
            y1 = Math.min(cy1, y1);
            x2 = Math.max(cx2, x2);
            y2 = Math.max(cy2, y2);

            sum += (cx2 - cx1) * (cy2 - cy1);

            String[] points = new String[]{cx1 + " " + cy1, cx1 + " " + cy2, cx2 + " " + cy1, cx2 + " " + cy2};
            for (String point : points) {
                if (!memo.add(point)) {
                    memo.remove(point);
                }
            }
        }

        if (sum != (x2 - x1) * (y2 - y1)) {
            return false;
        }

        return memo.size() == 4 && 
               memo.contains(x1 + " " + y1) &&
                memo.contains(x1 + " " + y2) &&
                memo.contains(x2 + " " + y1) &&
                memo.contains(x2 + " " + y2);
    }
}
```
# [LeetCode_318_最大单词长度乘积](https://leetcode-cn.com/problems/maximum-product-of-word-lengths/)
## 解法
### 思路
3层循环模拟
### 代码
```java
class Solution {
    public int maxProduct(String[] words) {
        int len = 0;
        for (int i = 0; i < words.length; i++) {
            boolean[] bucket = new boolean[26];
            char[] cs = words[i].toCharArray();
            for (char c : cs) {
                bucket[c - 'a'] = true;
            }
            
            int il = words[i].length();
                    
            for (int j = i + 1; j < words.length; j++) {
                if (il * words[j].length() <= len) {
                    continue;
                }
                
                char[] jcs = words[j].toCharArray();
                boolean flag = true;
                for (char jc : jcs) {
                    if (bucket[jc - 'a']) {
                        flag = false;
                        break;
                    }
                }
                
                if (flag) {
                    len = il * words[j].length();
                }
            }
        }
        
        return len;
    }
}
```
## 解法二
### 思路
- 解法一的算法，每次j指针对应的字符串都会被重复统计一次字符的出现情况
- 可以提前将所有字符串的字符出现情况统计好，然后再2层循环遍历字符串，直接使用计算好的情况来判断是否有重复字符，然后计算乘积
- 同时，用来统计字符出现情况可以从解法一中的26长度的布尔数组，一个int值，通过位来统计字符出现的情况，然后通过与运算来判断是否有交集
### 代码
```java
class Solution {
    public int maxProduct(String[] words) {
        int len = words.length;
        int[] arr = new int[len];

        for (int i = 0; i < len; i++) {
            String word = words[i];
            for (char c : word.toCharArray()) {
                arr[i] |= 1 << (c - 'a');
            }
        }
        
        int max = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if ((arr[i] & arr[j]) == 0) {
                    max = Math.max(max, words[i].length() * words[j].length());
                }
            }
        }
        
        return max;
    }
}
```
# [LeetCode_563_二叉树的坡度](https://leetcode-cn.com/problems/binary-tree-tilt/)
## 解法
### 思路
dfs
### 代码
```java
class Solution {
    private int sum = 0;
    public int findTilt(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        doFindTilt(root);
        
        return sum;
    }
    
    private int doFindTilt(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        int left = doFindTilt(node.left), right = doFindTilt(node.right);
        
        sum += Math.abs(left - right);
        
        return node.val + left + right;
    }
}
```