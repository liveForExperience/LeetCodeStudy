# [LeetCode_318_最大单词长度乘积](https://leetcode.cn/problems/maximum-product-of-word-lengths)
## 解法
### 思路
- 思考过程： 
  - 题目要求计算和比较字符串长度的乘积最大值，相乘的两个字符串不能有公共字母，因为题目限制了字符串中只能出现小写字母，所以可以通过一个32位的整数存储26位小写字母在字符串中的出现情况，从而判断是否有重复
  - 重复的判断逻辑也很简单，就是通过2个32位整数的2进制位进行相与操作，如果`与`之后的结果是0，那么就说明没有公共字母
  - 然后2层循环遍历数组，根据字母的出现情况判断是否可以将2个字符串的长度相乘，如果可以就得到相乘的结果后和暂存的最大值进行比较即可
- 算法过程：
  - 初始化int数组`arr`，用来存储每个字符串字母出现情况的统计值
  - 初始化变量`ans`，初始值为0，用来作为成绩最大值的暂存变量
  - 循环一次数组，迭代并计算每个元素字符串的字母出现情况，记录到`arr`对应坐标中
  - 再次2层循环数组，两两判断是否有重复字母，判断方式`(arr[i] & arr[j]) == 0`，如果判断结果为没有重复字母，则相乘后与暂存`ans`进行比较并更新为较大值
  - 2层循环结束，返回`ans`座位结果
### 代码
```java
class Solution {
    public int maxProduct(String[] words) {
        int n = words.length, index = 0;
        int[] arr = new int[n];
        for (String word : words) {
            arr[index++] = mask(word);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((arr[i] & arr[j]) == 0) {
                    ans = Math.max(ans, words[i].length() * words[j].length());
                }
            }
        }

        return ans;
    }

    private int mask(String s) {
        char[] cs = s.toCharArray();
        int mask = 0;
        for (char c : cs) {
            mask |= 1 << (c - 'a');
        }
        return mask;
    }
}
```
# [LeetCode_2300_咒语和药水的成功对数](https://leetcode.cn/problems/successful-pairs-of-spells-and-potions)
## 解法
### 思路
- 思考过程：
  - 题目要求的是`spells`数组中的每一个元素，在`options`数组中能找到多少个相乘后可以大于`success`的元素个数
  - 因为`spells`元素的值大于0，所以乘积与`options`元素值成单调性
  - 那么如果将`options`数组进行排序之后，当在`options`数组中找到最小的那个与`spells`元素相乘后大于等`success`的元素，则该元素之后的所有元素也都是符合要求的
  - 那么就可以按照思路，将`options`排序后，通过二分查找找到那个最小的元素，就能快速得到符合要求的元素个数
- 算法过程：
  - 初始化`spells`数组的长度为`m`，`options`数组的长度为`n`
  - 初始化一个长度为`m`的答案数组`ans`
  - 对`options`数组正向排序
  - 遍历`spells`数组，基于每个遍历的元素，在`options`数组中通过二分查找找到最小的相乘大于等于`success`的坐标`index`
  - 然后通过`n - index`得到元素个数，然后存储`ans`数组对应的坐标中
  - 遍历结束后返回`ans`即可
### 代码
```java
class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int m = spells.length, n = potions.length;
        int[] ans = new int[m];
        Arrays.sort(potions);
        for (int i = 0; i < spells.length; i++) {
            ans[i] = n - binarySearch(potions, spells[i], success);
        }
        
        return ans;
    }
    
    private int binarySearch(int[] arr, int x, long target) {
        int head = 0, tail = arr.length - 1;
        
        while (head <= tail) {
            int mid = head + (tail - head) / 2;
            long cur = (long) arr[mid] * x;
            
            if (cur < target) {
                head = mid + 1;
            } else {
                tail = mid - 1;
            }
        }
        
        return head;
    }
}
```