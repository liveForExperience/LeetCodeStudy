# [LeetCode_927_三等分](https://leetcode.cn/problems/three-equal-parts/)
## 解法
### 思路
- 求出1的个数，看是否能被3整除
- 求出最后一部分的后导零的个数
- 根据后导零的个数，求出前两部分的合适的分割点，也就是第一部分的后导零位置，如果凑不齐后导零，就说明无法成功切分
### 代码
```java
class Solution {
    public int[] threeEqualParts(int[] arr) {
        int count = 0, n = arr.length;
        for (int num : arr) {
            count += num;
        }

        if (count == 0) {
            return new int[]{0, n - 1};
        }

        if (count % 3 != 0) {
            return new int[]{-1, -1};
        }

        int len = count / 3, first = -1,
                second = -1, third = -1,
                cur = 0;

        for (int i = 0; i < n; i++) {
            int num = arr[i];

            if (num == 0) {
                continue;
            }

            if (cur == 0) {
                first = i;
            } else if (cur == len) {
                second = i;
            } else if (cur == 2 * len) {
                third = i;
            }

            cur++;
        }

        int suffix = n - third;

        if (second - first < suffix ||
            third - second < suffix) {
            return new int[]{-1, -1};
        }

        for (int i = first, j = second, k = third; k < n; i++, j++, k++) {
            if (arr[i] != arr[j] ||
                arr[j] != arr[k]) {
                return new int[]{-1, -1};
            }
        }

        return new int[]{first + suffix - 1, second + suffix};
    }
}
```
# [LeetCode_870_优势洗牌](https://leetcode.cn/problems/advantage-shuffle/)
## 失败解法
### 原因
超时
### 思路
- 对nums1进行排序
- 然后遍历nums2，根据nums2的当前元素找到这个元素在nums1中从相同坐标开始的子序列中的最小的较大值
- 如果不存在，那么就是当前这个nums1的元素，因为这个元素是最小的
### 代码
```java
class Solution {
    public int[] advantageCount(int[] nums1, int[] nums2) {
        for (int i = 0; i < nums2.length; i++) {
            int[] arr = Arrays.copyOfRange(nums1, i, nums1.length);
            Arrays.sort(arr);
            System.arraycopy(arr, 0, nums1, i, nums1.length - i);

            int target = nums2[i],
                    index = binarySearch(nums1, i, target);

            if (nums1[index] <= target) {
                continue;
            }

            swap(nums1, i, index);
        }

        return nums1;
    }

    private int binarySearch(int[] nums, int start, int target) {
        int l = start, r = nums.length - 1;

        while (l < r) {
            int mid = l + (r - l) / 2,
                    num = nums[mid];

            if (num <= target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return l;
    }

    private void swap(int[] nums, int x, int y) {
        int tmp = nums[x];
        nums[x] = nums[y];
        nums[y] = tmp;
    }
}
```
## 解法
### 思路
- 生成2个坐标数组，分别对应nums1和nums2
- 根据nums1和nums2数组，对2个坐标数组做升序排序
- 初始化结果数组
- 初始化left和right坐标，用来确定需要比较的范围
- 遍历nums1的坐标数组
- 比较nums1中当前最小的元素，与nums2中当前范围内最小的元素之间的大小
  - 如果大，那么就用这个元素，因为这个是nums1中最好的选择
  - 如果小，那么nums1的这个元素就肯定不可能在比其他nums2的任何元素大了，直接交给nums2中最大的那个元素去处理
### 代码
```java
class Solution {
    public int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;
        Integer[] idx1 = new Integer[n], idx2 = new Integer[n];
        for (int i = 0; i < n; i++) {
            idx1[i] = i;
            idx2[i] = i;
        }

        Arrays.sort(idx1, Comparator.comparingInt(x -> nums1[x]));
        Arrays.sort(idx2, Comparator.comparingInt(x -> nums2[x]));
        
        int[] ans = new int[n];
        int left = 0, right = n - 1;

        for (int i = 0; i < n; i++) {
            if (nums1[idx1[i]] > nums2[idx2[left]]) {
                ans[idx2[left]] = nums1[idx1[i]];
                left++;
            } else {
                ans[idx2[right]] = nums1[idx1[i]];
                right--;
            }
        }
        
        return ans;
    }
}
```