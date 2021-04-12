# [LeetCode_510_二叉搜索树的中序后继II](https://leetcode-cn.com/problems/inorder-successor-in-bst-ii/)
## 解法
### 思路
中序后继有2种情况：
- 如果该节点有右子树，则找到其右节点后，找到其最左的叶子节点
- 如果该节点没有右子树，则递归找第一个父节点的左节点是当前层节点的节点
### 代码
```java
class Solution {
    public Node inorderSuccessor(Node node) {
        if (node == null) {
            return null;
        }
        
        if (node.right != null) {
            return findLeftChild(node.right);
        } else {
            return findLeftFather(node.parent, node);
        }
    }
    
    private Node findLeftChild(Node node) {
        if (node == null) {
            return null;
        }
        
        if (node.left == null) {
            return node;
        }
        
        return findLeftChild(node.left);
    }
    
    private Node findLeftFather(Node father, Node child) {
        if (father == null) {
            return null;
        }
        
        if (father.left == child) {
            return father;
        }
        
        return findLeftFather(father.parent, father);
    }
}
```
# [LeetCode_81_搜索旋转排序数组II](https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/)
## 解法
### 思路
- 因为是排序数组中查找目标值，最快的方法就是二分查找
- 当前值是一个排序数组的变种，它被旋转了一次，且不是降序
- 在二分查找中，确定中间值的过程与普通的是一致的，如果`target == nums[mid]`，那么就返回true
- 为了确定数组的状态，除了获取数组的中间值外，还要用中间值与数组头部的值进行比较：
    - 如果数组头部值与中间值一致，则右移head值，因为这种情况由几种可能
        - mid的左边都是和中间值一样的元素
        - 中间值其实就是实际元素的最大值，且重复多个，那么mid的左边可能包含实际数组的起始部分
    - 如果数组的头部值小于中间值，则代表mid左边一定是升序的
        - 如果target小于头部值，那么可能实际升序数组的开头部分在mid的右边
        - 如果target大于头部值，且小于中间值，那么就在mid的左边
        - 如果target大于中间值，那么就直接去右边去搜索
    - 如果数组的头部值大于中间值，mid到尾部是一个升序序列
        - 如果target大于尾部值，那么可能在mid的左边
        - 如果target小于尾部值，且大于中间值，就在mid的右边
        - 如果target小于尾部值，且小于中间值，则可能在mid的左边
### 代码
```java
class Solution {
    public boolean search(int[] nums, int target) {
        int len = nums.length, head = 0, tail = len - 1;
        while (head <= tail) {
            int mid = head + (tail - head) / 2;
            if (nums[mid] == target) {
                return true;
            }

            if (nums[head] == nums[mid]) {
                head++;
            } else if (nums[head] < nums[mid]) {
                if (target == nums[head]) {
                    return true;
                }

                if (target > nums[head] && target < nums[mid]) {
                    tail = mid - 1;
                } else {
                    head = mid + 1;
                }
            } else {
                if (target == nums[tail]) {
                    return true;
                }

                if (target < nums[tail] && target > nums[mid]) {
                    head = mid + 1;
                } else {
                    tail = mid - 1;
                }
            }
        }

        return false;
    }
}
```
# [LeetCode_153_寻找旋转排序数组中的最小值](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/)
## 解法
### 思路
通过二分查找找到最小值
- 初始化起始坐标，结尾坐标和最小值变量
- 先找到中间值，然后用中间值和起始值比较
    - 循环的继续条件是起始坐标小于结尾坐标
    - 如果中间值和起始值相等，此时说明数组只剩下至多2个元素，此时比较一下起始值与暂存的最小值，取两者的最小值
    - 如果中间值比起始值大，说明要么最小值是起始值，要么就在中间值右边
    - 如果中间值比起始值小，说明最小值在中间值的左边
### 代码
```java
class Solution {
    public int findMin(int[] nums) {
        int len = nums.length, head = 0, tail = len - 1, min = Integer.MAX_VALUE;
        while (head < tail) {
            int mid = head + (tail - head) / 2;

            if (nums[mid] == nums[head]) {
                min = Math.min(nums[head], min);
                head++;
            } else if (nums[mid] > nums[head]) {
                min = Math.min(nums[head], min);
                head = mid + 1;
            } else {
                min = Math.min(nums[mid], min);
                tail = mid;
            }
        }

        return Math.min(nums[head], min);
    }
}
```
## 解法二
### 思路
快排后取第一个值
### 代码
```java
class Solution {
    public int findMin(int[] nums) {
        quickSort(0, nums.length - 1, nums);
        return nums[0];
    }

    private void quickSort(int head, int tail, int[] nums) {
        if (head >= tail) {
            return;
        }

        int pivot = partition(head, tail, nums);

        quickSort(head, pivot - 1, nums);
        quickSort(pivot + 1, tail, nums);
    }

    private int partition(int head, int tail, int[] nums) {
        while (head < tail) {
            while (head < tail && nums[tail] >= nums[head]) {
                tail--;
            }
            swap(head, tail, nums);

            while (head < tail && nums[head] <= nums[tail]) {
                head++;
            }
            swap(head, tail, nums);
        }

        return head;
    }

    private void swap(int x, int y, int[] nums) {
        int tmp = nums[x];
        nums[x] = nums[y];
        nums[y] = tmp;
    }
}
```
# [LeetCode_154_寻找旋转排序数组中的最小值II](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/)
## 解法
### 思路
解法同当前周的153题解1
### 代码
```java
class Solution {
    public int findMin(int[] nums) {
        int len = nums.length, head = 0, tail = len - 1, min = Integer.MAX_VALUE;
        while (head < tail) {
            int mid = head + (tail - head) / 2;

            if (nums[mid] == nums[head]) {
                min = Math.min(nums[head], min);
                head++;
            } else if (nums[mid] > nums[head]) {
                min = Math.min(nums[head], min);
                head = mid + 1;
            } else {
                min = Math.min(nums[mid], min);
                tail = mid;
            }
        }

        return Math.min(nums[head], min);
    }
}
```
## 解法二
### 思路
思路和解法一类似，但是在过程中去除不必要的最小值比较
- 比较中间值mid和尾部值tail的大小：
    - `mid < tail`：说明mid右边包括mid，是一个升序序列，这里面可能时最小值的只能是mid，所以除了mid外舍弃这部分，右边界设置为mid
    - `mid > tail`：说明mid左边是一个升序序列，且最小值肯定不在左边，因为可能的最小值是左边起始值，但如果是的话，那么这个数组就应该是一个完全的升序序列，那就不可能导致`mid > tail`，所以最小值一定在右边，左边界设置为`mid + 1`
    - `mid == tail`：不必去区分到底有哪些可能，直接左移右边界，尝试将相等的情况打破即可
- 整个驱动的条件就是左边界小于右边界，因为是floor的方式获取的中间值，所以退出条件一定是左边界与右边界相等：
    - 此时应该就是剩下2个相邻的值
    - 如果左边界的值小于右边界的值，右边界会左移到和左边界一样，那么最小值就是左边界
    - 如果左边界的值大于右边界的值，左边界会移动到和右边界一样，导致退出
    - 如果左右边界相等，那么就会左移右边界，使左右边界一致
    - 所以无论哪种情况，只需要返回最后左边界指向的数组元素即可
### 代码
```java
class Solution {
    public int findMin(int[] nums) {
        int len = nums.length, head = 0, tail = len - 1;
        while (head < tail) {
            int mid = head + (tail - head) / 2;
            if (nums[mid] < nums[tail]) {
                tail = mid;
            } else if (nums[mid] > nums[tail]) {
                head = mid + 1;
            } else {
                tail--;
            }
        }
        
        return nums[head];
    }
}
```
# [LeetCodeStudy_179_最大数](https://leetcode-cn.com/problems/largest-number/)
## 解法
### 思路
自定义排序：
- 如果字符串4和45在一起，那么拼接的时候应该是454而不是445
- 那么比较的时候其实就是比较a+b和b+a的大小
### 代码
```java
class Solution {
    public String largestNumber(int[] nums) {
        String[] numStrs = Arrays.stream(nums).boxed().map(String::valueOf).toArray(String[]::new);
        Arrays.sort(numStrs, (s1, s2) -> (s2 + s1).compareTo(s1 + s2));
        String ans = String.join("", numStrs);
        return ans.charAt(0) == '0' ? "0" : ans;
    }
}
```