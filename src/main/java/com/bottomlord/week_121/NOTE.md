# [LeetCode_575_分糖果](https://leetcode-cn.com/problems/distribute-candies/)
## 解法
### 思路
- set统计糖果种类
- 计算每个人能够分到的糖果数量，也就是总糖果数/2
- 获取两个数之间的最小值返回
### 代码
```java
class Solution {
    public int distributeCandies(int[] candyType) {
        Set<Integer> set = Arrays.stream(candyType).boxed().collect(Collectors.toSet());
        return Math.min(set.size(), candyType.length / 2);
    }
}
```
## 解法二
### 思路
使用数组替代解法一的set来统计candy的种类
### 代码
```java
class Solution {
    public int distributeCandies(int[] candyType) {
        boolean[] bucket = new boolean[200001];
        int count = 0;
        for (int num : candyType) {
            if (!bucket[num + 100000]) {
                count++;
                bucket[num + 100000] = true;
            }
        }
        return Math.min(count, candyType.length / 2);
    }
}
```
# [LeetCode_1822](https://leetcode-cn.com/problems/sign-of-the-product-of-an-array/)
## 解法
### 思路
- 遍历数组
- 找到0就返回0
- 统计数组中负号的个数，如果是奇数就返回-1，否则返回1
### 代码
```java
class Solution {
    public int arraySign(int[] nums) {
        boolean flag = true;
        for (int num : nums) {
            if (num == 0) {
                return 0;
            }
            
            if (num < 0) {
                flag = !flag;
            }
        }
        
        return flag ? 1 : -1;
    }
}
```
# [LeetCode_237_删除链表中的节点](https://leetcode-cn.com/problems/delete-node-in-a-linked-list/)
## 解法
### 思路
- 将当前节点的值设置为下一个节点的值
- 将当前节点的next指向下一个节点的next
### 代码
```java
class Solution {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
```
# [LeetCode_1826_有缺陷的传感器](https://leetcode-cn.com/problems/faulty-sensor/)
## 解法
### 思路
- 模拟两个数组各自掉落时候是否符合题目要求
- 如果不是同时符合，那么哪一个符合，就返回对应的值
- 否则返回-1
### 代码
```java
class Solution {
    public int badSensor(int[] sensor1, int[] sensor2) {
        int i = 0, n = sensor1.length;
        while (i < n && sensor1[i] == sensor2[i]) {
            i++;
        }

        if (i >= n - 1) {
            return -1;
        }

        int index = i;
        while (index < n - 1 && sensor1[index] == sensor2[index + 1]) {
            index++;
        }
        
        int index2 = i;
        while (index2 < n - 1 && sensor2[index2] == sensor1[index2 + 1]) {
            index2++;
        }
        
        if (index != index2) {
            if (index == n - 1) {
                return 1;
            }

            if (index2 == n - 1) {
                return 2;
            }
        }

        return -1;
    }
}
```