# [LeetCode_460_LFU缓存](https://leetcode.cn/problems/lfu-cache)
## 解法
### 思路
- 实现一个双向链表，链表包含4个操作：
  - 将节点添加到队头
  - 将节点删除
  - 获取头节点
  - 获取尾节点
- 链表的节点`node`存储如下3个属性：
  - key
  - value
  - freq：当前节点被使用的次数
- 缓存的数据就存储在`node`中，并通过`freq`来记录频率
- LFU缓存中定义如下属性：
  - minFreq：用于存储当前缓存中最小的频率值，这个值用于帮助进行删除操作
  - capacity：用于记录容量
  - table：用于记录`key`和对应的`node`
  - freqTable：用于记录频率和对应的双向链表，链表中存储当前频率中的所有节点
- 实现get操作：
  - 如果`table`中不存在，返回-1
  - 如果`table`中存在
    - 获取这个`node`节点
    - 根据`node`的频率将这个节点从`freqTable`中删除
    - 如果删除后，当前`freq`的链表长度为0，那么删除这个频率的键值对，且如果这个删除的`freq`正好等于`minFreq`，那么`minFreq`加1
    - 基于原来`node`的`freq`值加1后，从`freqTable`中获取双向链表，将新的`node`加到链表中（如果链表为空，那么就初始化一个链表）
    - 然后将链表put回`freq`对应的键值对中
    - 返回节点的value
- 实现put操作：
  - 如果`table`中存在key，那么就更新key对应的node的值，并和get一样调整一下node的freq和freqTable中节点的位置
  - 如果`table`中不存在
    - 如果已经到达上限，需要先进行删除操作
    - 如果没有到达上限，那么就在`freqTable`中取出`key`为1的双向链表，将当前节点放在链表头，同时将节点放入`table`中，并将`minFreq`设置为1
### 代码
```java
class LFUCache {

    private Map<Integer, DLinkedList> freqTable;
    private Map<Integer, Node> table;
    private int capacity, minFreq;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.freqTable = new HashMap<>();
        this.table = new HashMap<>();
        this.minFreq = 0;
    }

    public int get(int key) {
        if (!table.containsKey(key)) {
            return -1;
        }

        Node node = table.get(key);
        int freq = node.freq;
        freqTable.get(freq).remove(node);
        if (freqTable.get(freq).size == 0) {
            freqTable.remove(freq);
            if (freq == minFreq) {
                minFreq++;
            }
        }

        DLinkedList list = freqTable.getOrDefault(freq + 1, new DLinkedList());
        node.freq++;
        list.addFirst(node);
        freqTable.put(freq + 1, list);
        return node.value;
    }

    public void put(int key, int value) {
        if (table.containsKey(key)) {
            Node node = table.get(key);
            node.value = value;
            int freq = node.freq;
            freqTable.get(freq).remove(node);

            if (freqTable.get(freq).size == 0) {
                freqTable.remove(freq);

                if (minFreq == freq) {
                    minFreq++;
                }
            }

            DLinkedList list = freqTable.getOrDefault(freq + 1, new DLinkedList());
            node.freq++;
            list.addFirst(node);
            freqTable.put(freq + 1, list);
            table.put(key, node);
            return;
        }

        if (capacity == table.size()) {
            Node node = freqTable.get(minFreq).getLast();
            table.remove(node.key);
            freqTable.get(minFreq).remove(node);
            if (freqTable.get(minFreq).size == 0) {
                freqTable.remove(minFreq);
            }
        }
        
        DLinkedList list = freqTable.getOrDefault(1, new DLinkedList());
        Node node = new Node(key, value, 1);
        list.addFirst(node);
        freqTable.put(1, list);
        table.put(key, node);
        minFreq = 1;
    }

    private class Node {
        private int key, value, freq;

        private Node prev, next;

        public Node() {
            this(-1, -1, 0);
        }

        public Node(int key, int value, int freq) {
            this.key = key;
            this.value = value;
            this.freq = freq;
        }
    }

    private class DLinkedList {
        private Node head, tail;

        private int size;

        public DLinkedList() {
            this.head = new Node();
            this.tail = new Node();
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        public void addFirst(Node node) {
            Node oldFirst = head.next;
            head.next = node;
            node.prev = head;
            node.next = oldFirst;
            oldFirst.prev = node;
            size++;
        }

        public void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }

        public Node getFirst() {
            return head.next;
        }

        public Node getLast() {
            return tail.prev;
        }
    }
}
```
# [LeetCode_1333_餐厅过滤器](https://leetcode.cn/problems/filter-restaurants-by-vegan-friendly-price-and-distance)
## 解法
### 思路
- 遍历餐厅数组`restaurants`，根据给出的3个条件，判断那些id是符合要求的，将他们的元素坐标存储在list中
- 对list基于数组的`rating`值进行降序排序，如果`rating`值相同，就根据`id`进行降序排序
- 遍历list数组，根据元素坐标到`restaurants`中取出id放入新的list中返回即可
### 代码
```java
class Solution {
    public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < restaurants.length; i++) {
            int[] restaurant = restaurants[i];
            if (!(restaurant[2] == 0 && veganFriendly == 1) && restaurant[3] <= maxPrice && restaurant[4] <= maxDistance) {
                list.add(i);
            }
        }

        list.sort((x, y) -> {
            int ratingX = restaurants[x][1], ratingY = restaurants[y][1];
            
            if (ratingX == ratingY) {
                return restaurants[y][0] - restaurants[x][0];
            }
            
            return ratingY - ratingX;
        });

        List<Integer> ans = new ArrayList<>();
        for (Integer i : list) {
            ans.add(restaurants[i][0]);
        }

        return ans;
    }
}
```
# [LeetCode_2251_花期内花的数目]()
## 解法
### 思路
- 根据花期的区间，我们可以分别生成花开时间和花最后还开着的时间的数组
- 对这两个数组`starts`和`ends`进行非降序排序
- 排序后的这两个数组就可以进行二分查找了，而查找的目标值就是遍历`people`的元素值
- 那二分查找出来的2个值分别是什么含义呢？而二分查找的边界应该怎么定义呢？关键就需要理解这里
- 首先，这两个数组的二分查找是不同的
  - `starts`数组要找的是小于等于`people`元素值的最大值
  - `ends`数组要找的是小于`people`元素值的最大值
- 而这二者的之所以有区别，是因为
  - 我们要在`starts`数组中二分查找到开花时间不大于`people`观察时间的最后那个元素坐标，也可以理解成通过坐标能够知道有多少花是在人来的时候至少开过的（包括当时开的）
  - 而我们要在`ends`数组中二分查找的是在`people`观察时已经谢了的最大那个坐标，因为花期是指开着的时间时间，`ends`中的元素是指花最后开着的时间，所以只有小于`people`值的元素才代表在人来观察之前就谢了，所以要小于目标值
- 得到这两个值之后，通过计算`开过的 - 已经谢了的`，就能得到`还开着的`
- 所以只要计算2个值的差，就能得到每个人观察到的花的数量
### 代码
```java
class Solution {
    public int[] fullBloomFlowers(int[][] flowers, int[] people) {
        int n = flowers.length;
        int[] starts = new int[n], ends = new int[n];
        for (int i = 0; i < flowers.length; i++) {
            int[] flower = flowers[i];
            starts[i] = flower[0];
            ends[i] = flower[1];
        }

        Arrays.sort(starts);
        Arrays.sort(ends);

        int[] ans = new int[people.length];
        for (int i = 0; i < people.length; i++) {
            int person = people[i];
            int bloom = binarySearchBiggestLowerOrEquals(starts, person) + 1,
                fade = binarySearchBiggestLower(ends, person) + 1;
                System.out.println(bloom + ":" + fade);
            ans[i] = bloom - fade;
        }
        return ans;
    }

    private int binarySearchBiggestLowerOrEquals(int[] arr, int target) {
        int head = 0, tail = arr.length - 1;
        while (head + 1 < tail) {
            int mid = head + (tail - head) / 2;

            if (arr[mid] <= target) {
                head = mid;
            } else {
                tail = mid;
            }
        }

        return arr[head] > target ? -1 : arr[tail] <= target ? tail : head;
    }

    private int binarySearchBiggestLower(int[] arr, int target) {
        int head = 0, tail = arr.length - 1;
        while (head + 1 < tail) {
            int mid = head + (tail - head) / 2;

            if (arr[mid] < target) {
                head = mid;
            } else {
                tail = mid;
            }
        }

        return arr[head] >= target ? -1 : arr[tail] < target ? tail : head; 
    }
}
```