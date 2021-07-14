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
# [LeetCode_218_天际线问题](https://leetcode-cn.com/problems/the-skyline-problem/)
## 解法
### 思路
分治：
- 将天际线的合并拆分成左右两部分，且最终细分到每一幢楼
- 分治后获取到一幢楼的坐标，再与相邻的另一幢楼的坐标做合并
- 合并后的坐标集合再依次和另一组合并的集合再做合并，直到完全合并为止
- 合并的过程中初始化几个变量：
    - 需要合并的左右两个楼房集合的长度：lLen,rLen
    - 合并时候处理用的坐标：li,ri，初始化为0
    - 暂存用的左右坐标高度：lh，rh，初始化为0
    - 存放合并后坐标的集合list
- 左右楼房集合的元素，需要基于原来数组做处理，保留2个元素，分别为x坐标和y坐标，也就是原来的楼房的3个元素处理成2组2个元素
- 主函数就是一个递归分治再合并的函数体
    - 定义退出条件，集合长度为0，则返回空集合，长度为1则返回2组坐标
    - 将集合分成左右两部分，分的过程也是一个递归的过程
    - 最后将2部分的集合做合并
- 合并过程定义为一个函数，用到之前初始化的变量，过程中主要是一个循环，一次处理x偏小的坐标，直到左或者右边的坐标集合处理完
- 循环处理过程中，如果左边的x坐标较小，则高度就是左边的坐标高度与之前的右边暂存的高度rh进行比较，这样比较的原因是：
    - 因为是谁x小处理谁，那么如果当前在处理左边的：
        - 就可能之前一直在处理右边的，直到右边的x值大于左边的了，那么相当于左边的x和右边的x就重合了，此时就要求出两者的最大值
        - 也可能刚开始处理左边的，那么就和初始化为0的右边高度作比较，那也就是左边的高度
        - 如果一直是在处理左边的，那说明有一个右边的宽度很长，那也是和rh作比较就可以了
- 最后合并的时候，需要分2种情况：
    - 第一幢楼就直接放到list里
    - 如果当前生成的坐标和上一个元素的高度一样，那就不需要放入list里，因为只要保留同一线段的最左边坐标
### 代码
```java
class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        if (buildings == null || buildings.length == 0) {
            return Collections.emptyList();
        }

        if (buildings.length == 1) {
            return Arrays.asList(
                    Arrays.asList(buildings[0][0], buildings[0][2]),
                    Arrays.asList(buildings[0][1], 0)
            );
        }

        List<List<Integer>> leftBuildings = getSkyline(Arrays.copyOfRange(buildings, 0, buildings.length / 2)),
                            rightBuildings = getSkyline(Arrays.copyOfRange(buildings, buildings.length / 2, buildings.length));

        return merge(leftBuildings, rightBuildings);
    }

    private List<List<Integer>> merge(List<List<Integer>> leftBuildings, List<List<Integer>> rightBuildings) {
        int lh = 0, rh = 0, li = 0, ri = 0, lLen = leftBuildings.size(), rLen = rightBuildings.size();
        List<List<Integer>> output = new ArrayList<>();
        while (li < lLen && ri < rLen) {
            List<Integer> left = leftBuildings.get(li), right = rightBuildings.get(ri);
            int lx = left.get(0), rx = right.get(0);
            List<Integer> list;
            if (lx < rx) {
                lh = left.get(1);
                li++;
                list = Arrays.asList(lx, Math.max(lh, rh));
            } else if (lx > rx) {
                rh = right.get(1);
                ri++;
                list = Arrays.asList(rx, Math.max(lh, rh));
            } else {
                li++;
                ri++;
                lh = left.get(1);
                rh = right.get(1);
                list = Arrays.asList(lx, Math.max(lh, rh));
            }

            if (output.size() == 0 || !Objects.equals(output.get(output.size() - 1).get(1), list.get(1))) {
                output.add(list);
            }
        }

        while (li < lLen) {
            output.add(leftBuildings.get(li++));
        }

        while (ri < rLen) {
            output.add(rightBuildings.get(ri++));
        }

        return output;
    }
}
```
# [LeetCode_1818_绝对差值和](https://leetcode-cn.com/problems/minimum-absolute-sum-difference/)
## 解法
### 思路
- 遍历数组：
- 累加差值的绝对值的和
- 如果找到并替换一个元素，那么这个元素必须是与nums2遍历到的元素最接近的nums1的元素
- 而为了使题目要求的差值和最小，如果替换的值是`nums1[j]`的话，那么`|nums1[i] - nums2[i]| - |nums1[j] - nums2[i]|`的差值就应该是最大
- 所以遍历过程中，就是找到如上公式的最大值
- 难点就是每次遍历的时候快速的找到`nums1[j]`，为了更快找到，可以通过对`nums1`数组排序，然后二分查找的方式来快速定位
- 这里的二分查找可以设定为找到比目标值大的最小数，这样遍历过程中比较大小两个值哪个差值更小就可以了，但要注意如果求出来的是元素第一个数，则更小值的情况就可以不再考虑
### 代码
```java
class Solution {
public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int[] sorted = Arrays.copyOfRange(nums1, 0, nums1.length);
        Arrays.sort(sorted);
        int n = nums1.length, sum = 0, maxDiff = 0, mod = 1000000007;
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            sum = (sum + diff) % mod;

            int index = binarySearch(sorted, nums2[i]);
            if (index < n) {
                maxDiff = Math.max(maxDiff, diff - (sorted[index] - nums2[i]));
            }

            if (index > 0) {
                maxDiff = Math.max(maxDiff, diff - (nums2[i] - sorted[index - 1]));
            }
        }

        return (sum - maxDiff + mod) % mod;
    }

    private int binarySearch(int[] arr, int target) {
        int head = 0, tail = arr.length - 1;
        if (arr[tail] < target) {
            return tail + 1;
        }

        while (head < tail) {
            int mid = head + (tail - head) / 2;

            if (arr[mid] < target) {
                head = mid + 1;
            } else if (arr[mid] > target){
                tail = mid;
            } else {
                return mid;
            }
        }

        return head;
    }
}
```