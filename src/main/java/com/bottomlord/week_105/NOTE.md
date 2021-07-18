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
# [LeetCode_1304_和为零的n个唯一整数](https://leetcode-cn.com/problems/find-n-unique-integers-sum-up-to-zero/)
## 解法
### 思路
- 为了使所有元素和为0，最简单的就是放置互为相反数的元素
- 判断n是否为奇数，如果是奇数就从数组的第2个坐标开始放置元素
- 直到放满数组后返回数组即可
### 代码
```java
class Solution {
    public int[] sumZero(int n) {
        int[] ans = new int[n];
        boolean even = n % 2 == 0;
        int index = even ? 0 : 1, num = 1;
        while (index < n) {
            ans[index++] = num;
            ans[index++] = -num;
            num++;
        }
        
        return ans;
    }
}
```
# [LeetCode_1846_减小和重新排列数组后的最大元素](https://leetcode-cn.com/problems/maximum-element-after-decreasing-and-rearranging/)
## 解法
### 思路
- 对元素及个数计数，放入map
- 将entry放入优先级队列
- 初始化一个元素cur为0，用来暂存当前可能的最大值
- 依次去除队列元素，再循环判断当前元素的个数是否大于0，如果当前元素值大于cur，那么cur累加，同时个数累减
- 最终返回cur
### 代码
```java
class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            queue.offer(new int[]{entry.getKey(), entry.getValue()});
        }

        int cur = 0;
        while (!queue.isEmpty()) {
            int[] element = queue.poll();
            while (element[1] > 0) {
                if (element[0] > cur) {
                    cur++;
                }
                element[1]--;
            }
        }

        return cur;
    }
}
```
# [LeetCode_offer53_在排序数组中查找数字I](https://leetcode-cn.com/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof/)
## 解法
### 思路
- 遍历搜索累加
- 遇到超过目标值就提前终止
### 代码
```java
class Solution {
    public int search(int[] nums, int target) {
        int count = 0;
        for (int num : nums) {
            if (num > target) {
                break;
            }
            
            if (num == target) {
                count++;
            }
        }
        
        return count;
    }
}
```
# [LeetCode_1309_解码字母到整数映射](https://leetcode-cn.com/problems/decrypt-string-from-alphabet-to-integer-mapping/)
## 解法
### 思路
- 从尾部开始遍历字符串
- 如果是#号，就再遍历两个位置，然后通过数字找到对应字符
- 如果不是#号，就直接通过数字找到对应字符
- 遍历结束返回结果
### 代码
```java
class Solution {
    public String freqAlphabets(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0;) {
            if (s.charAt(i) == '#') {
                int num = Integer.parseInt(String.valueOf(s.charAt(i - 2)) + s.charAt(i - 1));
                sb.insert(0, (char) ('a' + num - 1));
                i -= 3;
            } else {
                int num = Integer.parseInt(String.valueOf(s.charAt(i)));
                sb.insert(0, (char) ('a' + num - 1));
                i--;
            }
        }
        
        return sb.toString();
    }
}
```
## 解法二
### 思路
- 不用Integer的api转char值后再做计算，直接在char值上-48获取到数字，然后计算出字母位数后再+96转为字母
### 代码
```java
class Solution {
    public String freqAlphabets(String s) {
        StringBuilder sb = new StringBuilder();
        char[] cs = s.toCharArray();
        for (int i = cs.length - 1; i >= 0; i--) {
            if (cs[i] == '#') {
                sb.insert(0, (char) (cs[--i] - 48 + (cs[--i] - 48) * 10 + 96));
            } else {
                sb.insert(0, (char) (cs[i] + 48));
            }
        }

        return sb.toString();
    }
}
```
# [LeetCode_offer42_连续子数组的最大和](https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/)
## 解法
### 思路
动态规划：
- dp[i]：以nums[i]为结尾的连续子数组的最大和
- 状态转移方程：
  - `dp[i] = max(dp[i - 1] + nums[i], nums[i])`
  - 以nums[i]为结尾的连续子数组有2种情况：
        - 单独的元素为最大值 
        - 和以前一个元素为结尾的连续子数组的最大值的和，形成更大值，则以这个值为当前子数组的最大值
- 求出dp中的最大值
### 代码
```java
class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int max = dp[0];
        
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            max = Math.max(max, dp[i]);
        }
        
        return max;
    }
}
```
## 解法二
### 思路
- 状态转移过程中只涉及和前一个元素的状态，所以可以用一个变量来替代dp数组
### 代码
```java
class Solution {
    public int maxSubArray(int[] nums) {
        int status = nums[0], max = status;
        for (int i = 1; i < nums.length; i++) {
            status = Math.max(status + nums[i], nums[i]);
            max = Math.max(status, max);
        }
        return max;
    }
}
```
# [LeetCode_1313_解压缩编码列表](https://leetcode-cn.com/problems/decompress-run-length-encoded-list/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public int[] decompressRLElist(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i += 2) {
            for (int j = 0; j < nums[i]; j++) {
                list.add(nums[i + 1]);
            }
        }
        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }
}
```
# [LeetCode_1317_将整数转换为两个无零整数的和](https://leetcode-cn.com/problems/convert-integer-to-the-sum-of-two-no-zero-integers/)
## 解法
### 思路
枚举
### 代码
```java
class Solution {
    public int[] getNoZeroIntegers(int n) {
        for (int i = 1; i < n; i++) {
            int a = i, b = n - i;
            String as = Integer.toString(a),
                   bs = Integer.toString(b);
            
            if (!as.contains("0") && !bs.contains("0")) {
                return new int[]{a, b};
            }
        }
        
        return new int[0];
    }
}
```
## 解法二
### 思路
简化代码
### 代码
```java
class Solution {
    public int[] getNoZeroIntegers(int n) {
        for (int a = 1; a < n; a++) {
            int b = n - a;
            if (!Integer.toString(a).contains("0") &&
                !Integer.toString(b).contains("0")) {
                return new int[]{a, b};
            }
        }
        
        return new int[0];
    }
}
```
## 解法三
### 思路
使用数学计算替代字符串转换
### 代码
```java
class Solution {
    public int[] getNoZeroIntegers(int n) {
        for (int a = 1; a < n; a++) {
            int b = n - a;
            if (hasNoZero(a) && hasNoZero(b)) {
                return new int[]{a, b};
            }
        }
        return new int[0];
    }

    private boolean hasNoZero(int num) {
        while (num > 0) {
            if (num % 10 == 0) {
                return false;
            }

            num /= 10;
        }
        return true;
    }
}
```
# [LeetCode_interview1002_变位词组](https://leetcode-cn.com/problems/group-anagrams-lcci/)
## 解法
### 思路
- 计算字符串字符的出现次数，并以字符串形式计数
- 计算出来的字符串计数key存入map中
### 代码

```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String key = cal(str);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }

    private String cal(String str) {
        int[] bucket = new int[26];
        char[] cs = str.toCharArray();
        for (char c : cs) {
            bucket[c - 'a']++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] != 0) {
                sb.append('a' + i).append(bucket[i]);
            }
        }
        return sb.toString();
    }
}
```
# [LeetCode_1323_6和9组成的最大数字](https://leetcode-cn.com/problems/maximum-69-number/)
## 解法
### 思路
- 数字转成字符串找到高位第一个6，将其转为9
- 再将字符串转为数字返回
### 代码
```java
class Solution {
    public int maximum69Number (int num) {
        String str = Integer.toString(num);
        char[] cs = str.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == '6') {
                cs[i] = '9';
                break;
            }
        }
        
        return Integer.parseInt(new String(cs));
    }
}
```