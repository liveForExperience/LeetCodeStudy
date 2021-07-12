# [LeetCode_275_H指数II](https://leetcode-cn.com/problems/h-index-ii/)
## 解法
### 思路
从高引用论文开始倒序遍历，累加h值，直到当前引用数不再大于h值为止
### 代码
```java
class Solution {
    public int hIndex(int[] citations) {
        int h = 0, i = citations.length - 1;
        while (i >= 0 && citations[i] > h) {
            h++;
            i--;
        }
        return h;
    }
}
```
# [LeetCode_1290_二进制链表转整数](https://leetcode-cn.com/problems/convert-binary-number-in-a-linked-list-to-integer/)
## 解法
### 思路
遍历链表并累加计算目标值，每次遍历一个节点就在累加值上乘以2进位
### 代码
```java
class Solution {
    public int getDecimalValue(ListNode head) {
        int ans = 0;
        ListNode node = head;
        while (node != null) {
            ans = ans * 2 + node.val;
            node = node.next;
        }
        return ans;
    }
}
```
# [LeetCode_统计位数为偶数的数字](https://leetcode-cn.com/problems/find-numbers-with-even-number-of-digits/)
## 解法
### 思路
转字符串后求字符串长度，如果是偶数就累加
### 代码
```java
class Solution {
    public int findNumbers(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            String str = Integer.toString(num);
            ans += str.length() % 2 == 0 ? 1 : 0;
        }
        return ans;
    }
}
```
## 解法二
### 思路
数学计算判断
### 代码
```java
class Solution {
    public int findNumbers(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            if (judge(num)) {
                ans++;
            }
        }
        return ans;
    }
    
    private boolean judge(int num) {
        int count = 0;
        while (num > 0) {
            count++;
            num /= 10;
        }
        
        return count % 2 == 0;
    }
}
```
# [LeetCode_1299_将每个元素替换为右侧最大元素](https://leetcode-cn.com/problems/replace-elements-with-greatest-element-on-right-side/)
## 解法
### 思路
从数组右侧开始遍历，并统计最右到当前坐标（不包含）区间内的最大值，将这个最大值作为当前元素的右侧最大元素
### 代码
```java
class Solution {
    public int[] replaceElements(int[] arr) {
        int max = Integer.MIN_VALUE, len = arr.length;
        int[] ans = new int[len];
        for (int i = len - 1; i >= 0; i--) {
            if (i == len - 1) {
                ans[i] = -1;
                max = arr[i];
                continue;
            }
            
            ans[i] = max;
            max = Math.max(max, arr[i]);
        }
        
        return ans;
    }
}
```