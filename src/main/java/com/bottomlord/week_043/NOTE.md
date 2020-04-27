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