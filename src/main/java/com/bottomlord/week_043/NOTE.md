# LeetCode_33_搜索旋转排序数组
## 题目
假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。

你可以假设数组中不存在重复的元素。

你的算法时间复杂度必须是 O(log n) 级别。

示例 1:
```
输入: nums = [4,5,6,7,0,1,2], target = 0
输出: 4
```
示例 2:
```
输入: nums = [4,5,6,7,0,1,2], target = 3
输出: -1
```
## 解法
### 思路
二分查找：
- 初始化头尾指针，开始循环直到头尾指针相遇
- 因为通过中间指针将数组分成两部分后，一定有一部分是有序的
    - 第一步是判断哪一部分是有序数组
    - 如果target在有序数组这部分里，那区间范围就缩小到这一部分
    - 否则就缩小到另一部分
### 代码
```java
class Solution {
    public int search(int[] nums, int target) {
        int head = 0, tail = nums.length - 1;
        while (head <= tail) {
            int mid = head + (tail - head) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[head] <= nums[mid]) {
                if (target < nums[mid] && target >= nums[head]) {
                    tail = mid - 1;
                } else {
                    head = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[tail]) {
                    head = mid + 1;
                } else {
                    tail = mid - 1;
                }
            }
        }

        return -1;
    }
}
```
# Interview_1625_LRU缓存
## 题目
设计和构建一个“最近最少使用”缓存，该缓存会删除最近最少使用的项目。缓存应该从键映射到值(允许你插入和检索特定键对应的值)，并在初始化时指定最大容量。当缓存被填满时，它应该删除最近最少使用的项目。

它应该支持以下操作： 获取数据 get 和 写入数据 put 。
```
获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
```
示例:
```
LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // 返回  1
cache.put(3, 3);    // 该操作会使得密钥 2 作废
cache.get(2);       // 返回 -1 (未找到)
cache.put(4, 4);    // 该操作会使得密钥 1 作废
cache.get(1);       // 返回 -1 (未找到)
cache.get(3);       // 返回  3
cache.get(4);       // 返回  4
```
## 解法
### 思路
hashmap + 双向链表 + 头尾哨兵
### 代码
```java
class LRUCache {
    private Map<Integer, DoubleLinkedNode> map;
    private int size;
    private int capacity;
    private DoubleLinkedNode head;
    private DoubleLinkedNode tail;
    public LRUCache(int capacity) {
        map = new HashMap<>();
        head = new DoubleLinkedNode();
        tail = new DoubleLinkedNode();
        head.next = tail;
        tail.pre = head;
        this.capacity = capacity;
        this.size = 0;
    }

    public int get(int key) {
        if (size == 0 || !map.containsKey(key)) {
            return -1;
        }

        DoubleLinkedNode node = map.get(key);
        removeNode(node);
        addNode(node);

        return node.val;
    }

    public void put(int key, int value) {
        DoubleLinkedNode node = map.get(key);

        if (node != null) {
            removeNode(node);
            node.val = value;
            addNode(node);
        } else {
            node = new DoubleLinkedNode(key, value);
            addNode(node);
            map.put(node.key, node);
            size++;

            if (size > capacity) {
                DoubleLinkedNode last = tail.pre;
                removeNode(last);
                map.remove(last.key);
            }
        }
    }

    private void removeNode(DoubleLinkedNode node) {
        node.next.pre = node.pre;
        node.pre.next = node.next;
    }

    private void addNode(DoubleLinkedNode node) {
        node.pre = head;
        node.next = head.next;

        head.next.pre = node;
        head.next = node;
    }
}

class DoubleLinkedNode {
    public int key;
    public int val;
    public DoubleLinkedNode pre;
    public DoubleLinkedNode next;

    public DoubleLinkedNode() {}
    public DoubleLinkedNode(int key, int val){
        this.key = key;
        this.val = val;
    }
}
```
# Interview_1626_计算器
## 题目
给定一个包含正整数、加(+)、减(-)、乘(*)、除(/)的算数表达式(括号除外)，计算其结果。

表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。

示例 1:
```
输入: "3+2*2"
输出: 7
```
示例 2:
```
输入: " 3/2 "
输出: 1
```
示例 3:
```
输入: " 3+5 / 2 "
输出: 5
```
说明：
```
你可以假设所给定的表达式都是有效的。
请不要使用内置的库函数 eval。
```
## 解法
### 思路
栈：
- 遍历字符串
    - 如果是数字就记录数字和位数，并继续循环
    - 如果是符号，开始判断上一个符号，该符号的默认值是加号：
        - 加号：将数字压入栈中
        - 减号：将该数字的负值压入栈中
        - 乘号：将前一个元素出栈并乘以当前数字，放入栈中
        - 除号：将前一个元素出栈并除以当前数字，放入栈中
        - 将当前符号作为下一次判断的符号进行暂存
    - 如果是最后一个元素，执行和遇到符号时一样的计算过程
- 遍历栈，累加值并作为结果返回
- 注意去除头尾和中间的空格
### 代码
```java
class Solution {
    public int calculate(String s) {
        if (s == null) {
            return 0;
        }
        
        s = s.trim();
        
        int num = 0;
        char operator = '+';
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == ' ') {
                continue;
            }
            
            if (Character.isDigit(c)) {
                num = 10 * num + (c - '0');
            }
            
            if (!Character.isDigit(c) || i == s.length() - 1) {
                if (operator == '+') {
                    stack.push(num);
                } else if (operator == '-') {
                    stack.push(-num);
                } else if (operator == '*') {
                    stack.push(stack.pop() * num);
                } else if (operator == '/') {
                    stack.push(stack.pop() / num);
                }
                
                num = 0;
                operator = c;
            }
        }
        
        int ans = 0;
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        
        return ans;
    }
}
```
# Interview_1705_字母与数字
## 题目
给定一个放有字符和数字的数组，找到最长的子数组，且包含的字符和数字的个数相同。

返回该子数组，若存在多个最长子数组，返回左端点最小的。若不存在这样的数组，返回一个空数组。

示例 1:
```
输入: ["A","1","B","C","D","2","3","4","E","5","F","G","6","7","H","I","J","K","L","M"]

输出: ["A","1","B","C","D","2","3","4","E","5","F","G","6","7"]
```
示例 2:
```
输入: ["A","A"]

输出: []
```
提示：
```
array.length <= 100000
```
## 解法
### 思路
前缀和 + map
- 遍历字符串，求每一个位置上，两种字符个数累加值之间的差值
- 将差值作为key，如果key为第一个值，那么就保存该值
- 每次生成累加值的差值，就在map中查询，并计算当前坐标和key对应的value的值的差，如果差大于暂存的最大值，取该值作为暂存的最大值
### 代码
```java
class Solution {
    public String[] findLongestSubarray(String[] array) {
        int max = 0, sum = 0, start = 0, end = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            for (char c : array[i].toCharArray()) {
                if (Character.isDigit(c)) {
                    sum++;
                    break;
                } else {
                    sum--;
                    break;
                }
            }

            if (sum == 0) {
                start = 0;
                end = i;
                max = i + 1;
                continue;
            }

            if (!map.containsKey(sum)) {
                map.put(sum, i);
            } else {
                if (max < i - map.get(sum)) {   
                    start = map.get(sum) + 1;
                    end = i;
                    max = i - map.get(sum) + 1;
                }
            }
        }

        if (start == 0) {
            return Arrays.copyOf(array, max);
        }

        return Arrays.copyOfRange(array, start, end + 1);
    }
}
```