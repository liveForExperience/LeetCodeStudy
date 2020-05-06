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
# LeetCode_1095_山脉数组中查找目标值
## 题目
给你一个 山脉数组 mountainArr，请你返回能够使得 mountainArr.get(index) 等于 target 最小 的下标 index 值。

如果不存在这样的下标 index，就请返回 -1。

何为山脉数组？如果数组 A 是一个山脉数组的话，那它满足如下条件：

首先，A.length >= 3

其次，在 0 < i < A.length - 1 条件下，存在 i 使得：
```
A[0] < A[1] < ... A[i-1] < A[i]
A[i] > A[i+1] > ... > A[A.length - 1]
```
你将 不能直接访问该山脉数组，必须通过 MountainArray 接口来获取数据：
```
MountainArray.get(k) - 会返回数组中索引为k 的元素（下标从 0 开始）
MountainArray.length() - 会返回该数组的长度
```
注意：
```
对 MountainArray.get 发起超过 100 次调用的提交将被视为错误答案。此外，任何试图规避判题系统的解决方案都将会导致比赛资格被取消。

为了帮助大家更好地理解交互式问题，我们准备了一个样例 “答案”：https://leetcode-cn.com/playground/RKhe3ave，请注意这 不是一个正确答案。
```
示例 1：
```
输入：array = [1,2,3,4,5,3,1], target = 3
输出：2
解释：3 在数组中出现了两次，下标分别为 2 和 5，我们返回最小的下标 2。
```
示例 2：
```
输入：array = [0,1,2,4,2,1], target = 3
输出：-1
解释：3 在数组中没有出现，返回 -1。
```
提示：
```
3 <= mountain_arr.length() <= 10000
0 <= target <= 10^9
0 <= mountain_arr.get(index) <= 10^9
```
## 解法
### 思路
二分查找：
- 先找到山峰
- 将山脉数组以山峰坐标为中点切分成两个有序数组
- 分别进行二分查找
- 如果都没有就返回-1，否则先返回左边的，再返回右边的结果
### 代码
```java
class Solution {
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int len = mountainArr.length(), head = 0, tail = len - 1, peak = -1;
        
        while (head <= tail) {
            int mid = head + (tail - head) / 2;
            
            int pre = mountainArr.get(mid - 1);
            int cur = mountainArr.get(mid);
            int next = mountainArr.get(mid + 1);
            
            if (pre < cur && cur > next) {
                peak = mid;
                break;
            } else if (cur < next){
                head = mid;
            } else {
                tail = mid;
            }
        }
        
        int index = getIndex(0, peak, mountainArr, target, 0);
        
        return index == -1 ? getIndex(peak, len - 1, mountainArr, target, 1) : index;
    }
    
    private int getIndex(int head, int tail, MountainArray mountainArr, int target, int dir) {
        while (head <= tail) {
            int mid = head + (tail - head) / 2;
            
            int cur = mountainArr.get(mid);

            if (cur == target) {
                return mid;
            } else if (cur < target){
                if (dir == 0) {
                    head = mid + 1;    
                } else {
                    tail = mid - 1;
                }
            } else {
                if (dir == 0) {
                    tail = mid - 1;
                } else {
                    head = mid + 1;
                }
            }
        }
        
        return -1;
    }
}
```
# Interview_1706_2出现的次数
## 题目
编写一个方法，计算从 0 到 n (含 n) 中数字 2 出现的次数。

示例:
```
输入: 25
输出: 9
解释: (2, 12, 20, 21, 22, 23, 24, 25)(注意 22 应该算作两次)
```
提示：
```
n <= 10^9
```
## 失败解法
### 失败原因
超时
### 思路
暴力：
- 嵌套遍历：
    - 外层循环所有数字
    - 内层逐位判断是否是2
### 代码
```java
class Solution {
    public int numberOf2sInRange(int n) {
        int ans = 0;
        for (int i = 0; i <= n; i++) {
            int num = i;
            while (num > 0) {
                ans += num % 10 == 2 ? 1 : 0;
                num /= 10;
            }
        }
        return ans;
    }
}
```
## 解法二
### 思路
- 计算每一位固定为2时，可能出现的个数
- 将数字按位拆分成3部分，xby
    - b代表当前位
    - x代表当前位左边的数，它的值要判断是否`b > 2`，如果是，那么最终要计算的值就是x+1，因为0也可以是选择
    - y代表当前位右边的数，它的值基于该值的长度，即pow(10, len(y))，且如果`b == 2`，那么这时2y也是可能的选择，所以是y + 1种可能
### 代码
```java
class Solution {
    public int numberOf2sInRange(int n) {
        String str = String.valueOf(n);
        int count = 0, len = str.length();

        for (int i = len - 1; i >= 0; i--) {
            int left = i == 0 ? 0 : Integer.parseInt(str.substring(0, i));
            int cur = Integer.parseInt(Character.toString(str.charAt(i)));
            if (cur > 2) {
                left++;
            }

            int right = (int) Math.pow(10, len - i - 1);
            count += left * right;

            if (cur == 2) {
                right = i + 1 < len ? Integer.parseInt(str.substring(i + 1)) + 1 : 1;
                count += right;
            }
        }

        return count;
    }
}
```
# Interview_1707_婴儿名字
## 题目
每年，政府都会公布一万个最常见的婴儿名字和它们出现的频率，也就是同名婴儿的数量。有些名字有多种拼法，例如，John 和 Jon 本质上是相同的名字，但被当成了两个名字公布出来。给定两个列表，一个是名字及对应的频率，另一个是本质相同的名字对。设计一个算法打印出每个真实名字的实际频率。注意，如果 John 和 Jon 是相同的，并且 Jon 和 Johnny 相同，则 John 与 Johnny 也相同，即它们有传递和对称性。

在结果列表中，选择字典序最小的名字作为真实名字。

示例：
```
输入：names = ["John(15)","Jon(12)","Chris(13)","Kris(4)","Christopher(19)"], synonyms = ["(Jon,John)","(John,Johnny)","(Chris,Kris)","(Chris,Christopher)"]
输出：["John(27)","Chris(36)"]
```
提示：
```
names.length <= 100000
```
## 解法
### 思路
hash表实现并查集
- 一个map模拟并查集，并操作union
- 一个map记录所有name的count值
- 在union的过程中，将nickname的count值累加到trulyname的count值里
- 最后遍历count的map来返回最后的结果
### 代码
```java
class Solution {
    public String[] trulyMostPopular(String[] names, String[] synonyms) {
        List<String[]> list = new ArrayList<>();
        Map<String, Integer> countMap = new HashMap<>();
        Map<String, String> dsuMap = new HashMap<>();

        for (String name : names) {
            int count = Integer.parseInt(name.substring(name.indexOf('(') + 1, name.indexOf(')')));
            countMap.put(name.substring(0, name.indexOf('(')), count);
        }

        for (String synonym : synonyms) {
            String name1 = synonym.substring(synonym.indexOf('(') + 1, synonym.indexOf(','));
            String name2 = synonym.substring(synonym.indexOf(',') + 1, synonym.indexOf(')'));

            while (dsuMap.containsKey(name1)) {
                name1 = dsuMap.get(name1);
            }

            while (dsuMap.containsKey(name2)) {
                name2 = dsuMap.get(name2);
            }

            if (!Objects.equals(name1, name2)) {
                int count = countMap.getOrDefault(name1, 0) + countMap.getOrDefault(name2, 0);

                String trulyName = name1.compareTo(name2) < 0 ? name1 : name2;
                String nickName = name1.compareTo(name2) < 0 ? name2 : name1;

                dsuMap.put(nickName, trulyName);

                countMap.remove(nickName);
                countMap.put(trulyName, count);
            }
        }

        String[] ans = new String[countMap.size()];
        int index = 0;
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            ans[index++] = entry.getKey() + "(" + entry.getValue() + ")";
        }
        return ans;
    }
}
```
# Interview_1708_马戏团人塔
## 题目
有个马戏团正在设计叠罗汉的表演节目，一个人要站在另一人的肩膀上。出于实际和美观的考虑，在上面的人要比下面的人矮一点且轻一点。已知马戏团每个人的身高和体重，请编写代码计算叠罗汉最多能叠几个人。

示例：
```
输入：height = [65,70,56,75,60,68] weight = [100,150,90,190,95,110]
输出：6
解释：从上往下数，叠罗汉最多能叠 6 层：(56,90), (60,95), (65,100), (68,110), (70,150), (75,190)
```
提示：
```
height.length == weight.length <= 10000
```
## 解法
### 思路
- 将数组先根据身高升序排序
- 当身高相同时，再根据体重降序排序
- 身高相同进行降序的目的是为了在二分查找时，避免选择到高度相等的情况
- 二分查找的过程是：
    - 新初始化一个数组dp用来存放满足条件的升序的体重值
    - 遍历排序好的体重数组
    - 一开始初始化的数组结束下标是0
    - 当二分查找时体重值比dp数组中的所有元素值都大时，那么说明这个值符合要求，继续加到数组中，并将范围扩大。
    - 而dp数组的范围就是最终可以叠罗汉的最大人数
### 代码
```java
class Solution {
    public int bestSeqAtIndex(int[] height, int[] weight) {
        int len = height.length;
        int[][] persons = new int[len][2];
        for (int i = 0; i < len; i++) {
            persons[i][0] = height[i];
            persons[i][1] = weight[i];
        }
        Arrays.sort(persons, (x1, x2) -> x1[0] == x2[0] ? x2[1] - x1[1] : x1[0] - x2[0]);

        int[] dp = new int[len];
        int res = 0;
        for (int[] person : persons) {
            int i = Arrays.binarySearch(dp, 0, res, person[1]);

            if (i < 0) {
                i = -(i + 1);
            }

            dp[i] = person[1];

            if (i == res) {
                res++;
            }
        }

        return res;
    }
}
```