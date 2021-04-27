# [LeetCode_1011_在D天内送达包裹的能力](https://leetcode-cn.com/problems/capacity-to-ship-packages-within-d-days/)
## 失败解法
### 原因
超时
### 思路
回溯
- 先求平均值和最大元素之间的较大值作为target
- 以这个target作为标准开始回溯，尝试所有可能
### 代码
```java
class Solution {
    public int shipWithinDays(int[] weights, int D) {
        int max = Integer.MIN_VALUE, sum = 0;
        for (int num : weights) {
            max = Math.max(max, num);
            sum += num;
        }
        
        int avg = sum % D == 0 ? sum / D : sum / D + 1;
        int target = Math.max(avg, max);

        boolean find = false;
        while (!find) {
            find = backTrack(weights, 0, 0, target, D);
            if (!find) {
                target++;
            }
        }

        return target;
    }

    private boolean backTrack(int[] weights, int index, int count, int target, int day) {
        if (count > day) {
            return false;
        }

        if (index == weights.length) {
            return true;
        }

        int sum = 0;
        boolean find = false;
        for (int i = index; i < weights.length; i++) {
            sum += weights[i];
            if (sum > target) {
                break;
            }
            find = backTrack(weights, i + 1, count + 1, target, day);

            if (find) {
                return true;
            }
        }

        return false;
    }
}
```
## 解法
### 思路
二分查找：
- 其实不需要依照失败解法的方式，求平均值，因为元素中的最大值一定会大于等于平均值，所以只要求得元素最大值就可以
- 这个最大值可以理解为是可能值的最小值，因为如果小于这个值就无法将所有元素都搬到船上
- 而最大值就是元素总和，可以理解成必须必须在一天之内放到船上的话，就要求得总和
### 代码
```java
class Solution {
    public int shipWithinDays(int[] weights, int D) {
        int sum = 0, max = Integer.MIN_VALUE;
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
    
    private boolean isValid(int[] weights, int target, int day) {
        int sum = target;
        for (int i = 0; i < weights.length;) {
            int weight = weights[i];
            
            if (sum - weight < 0) {
                sum = target;
                day--;
            } else {
                sum -= weight;
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
## 解法二
### 思路
- 解法一中的右边界可以再缩小为以最大值为所有元素的值，然后根据天数求出来的平均值
### 代码
```java
class Solution {
public int shipWithinDays(int[] weights, int D) {
        int max = Integer.MIN_VALUE;
        for (int weight : weights) {
            max = Math.max(max, weight);
        }
        
        int head = max, tail = max * weights.length / D;
        while (head < tail) {
            int mid = head + ((tail - head) >>> 1);
            
            if (isValid(weights, mid, D)) {
                tail = mid;
            } else {
                head = mid + 1;
            }
        }
        
        return head;
    }
    
    private boolean isValid(int[] weights, int target, int day) {
        int cur = target;
        
        for (int i = 0; i < weights.length;) {
            int weight = weights[i];
            
            if (cur - weight < 0) {
                cur = target;
                day--;
            } else {
                cur -= weight;
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
# [LeetCode_544_输出比赛匹配对](https://leetcode-cn.com/problems/output-contest-matches/)
## 解法
### 思路
递归：
- 将n转换为从1开始的n个元素的字符串数组
- 递归将字符串数组的头尾元素按照题目要求做拼接
- 最终当数组长度为1，返回这一个元素
### 代码
```java
class Solution {
    public String findContestMatch(int n) {
        String[] arr = new String[n];
        for (int i = 1; i <= n; i++) {
            arr[i - 1] = "" + i;
        }

        return recuse(arr);
    }

    private String recuse(String[] arr) {
        if (arr.length == 1) {
            return arr[0];
        }

        String[] newArr = new String[arr.length / 2];
        for (int i = 0; i < arr.length / 2; i++) {
            newArr[i] = combineStr(arr[i], arr[arr.length - 1 - i]);
        }

        return recuse(newArr);
    }

    private String combineStr(String x, String y) {
        return "(" + x + "," + y + ")";
    }
}
```
# [LeetCode_545_二叉树的边界](https://leetcode-cn.com/problems/boundary-of-binary-tree/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_938_二叉搜索树的范围和](https://leetcode-cn.com/problems/range-sum-of-bst/)
## 解法
### 思路
- 深度优先搜索，通过比较当前节点值和最大最小值，来判断是否要累加当前节点并返回
    - 如果当前节点是空节点，就返回0
    - 如果当前节点小于最小值，就累加其右子树的节点
    - 如果当前节点大于最大值，就累加其左子树的节点
    - 否则就累加左右子树的节点
### 代码
```java
class Solution {
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }

        int ans = 0, val = root.val;
        if (val >= low && val <= high) {
            ans += val;
        }
        
        return ans + 
               (val < low ? 0 : rangeSumBST(root.left, low, high)) + 
               (val > high ? 0 : rangeSumBST(root.right, low, high));
    }
}
```