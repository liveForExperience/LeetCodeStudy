# [LeetCode_384_打乱数组](https://leetcode-cn.com/problems/shuffle-an-array/)
## 解法
### 思路
使用random函数暴力解
### 代码
```java
class Solution {
    int[] origin;
    public Solution(int[] nums) {
        this.origin = nums;
    }

    public int[] reset() {
        return origin;
    }

    public int[] shuffle() {
        List<Integer> list = new ArrayList<>();
        for (int num : origin) {
            list.add(num);
        }

        int[] nums = new int[origin.length];
        Random ran = new Random();
        for (int i = 0; i < origin.length; i++) {
            int index = ran.nextInt(list.size());
            nums[i] = list.get(index);
            list.remove(index);
        }

        return nums;
    }
}
```
## 解法二
### 思路
- 解法一中，shuffle函数里，对存储数组的list的remove操作，时间复杂度较高，可以对这一操作进行优化
- 可以在remove的过程中，将使用的数字放置到list的顶部，然后随机就从之后的坐标中选择
### 代码
```java
class Solution {
    int[] origin;
    public Solution(int[] nums) {
        this.origin = nums;
    }

    public int[] reset() {
        return origin;
    }

    public int[] shuffle() {
        int[] copy = new int[origin.length], nums = new int[origin.length];
        System.arraycopy(origin, 0, copy, 0, origin.length);

        Random ran = new Random();
        for (int i = 0; i < origin.length; i++) {
            int j = ran.nextInt(origin.length - i);
            nums[i] = copy[i + j];
            int tmp = copy[i];
            copy[i] = copy[i + j];
            copy[i + j] = tmp;
        }

        return nums;
    }
}
```
# [LeetCode_859_亲密字符串](https://leetcode-cn.com/problems/buddy-strings/)
## 解法
### 思路
模拟：
- 遍历字符串s和goal
- 计算不同的字符个数，并记录不同的字符
- 如果不同的字符大于2个，就返回false
- 如果4个不同的字符并不同也返回false
- 如果两个字符串完全相等，就看字符串中是否有相同的字符，如果有是true，否则也是false
- 2个字符串需要完全相等
### 代码
```java
class Solution {
    public boolean buddyStrings(String s, String goal) {
        int len = s.length(), count = 0;
        if (len != goal.length()) {
            return false;
        }

        char d1 = ' ', d2 = ' ';
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) != goal.charAt(i)) {
                count++;

                if (count > 2) {
                    return false;
                }

                if (count == 1) {
                    d1 = s.charAt(i);
                    d2 = goal.charAt(i);
                    continue;
                }

                if (s.charAt(i) != d2 || goal.charAt(i) != d1) {
                    return false;
                }
            }
        }

        if (count == 1) {
            return false;
        }
        
        if (count == 0) {
            int[] bucket = new int[26];
            for (char c : goal.toCharArray()) {
                bucket[c - 'a']++;
            }
            for (int i : bucket) {
                if (i >= 2) {
                    return true;
                }
            }
            
            return false;
        }
        
        return true;
    }
}
```
# [LeetCode_1869_哪种连续子字符串更长](https://leetcode-cn.com/problems/longer-contiguous-segments-of-ones-than-zeros/)
## 解法
### 思路
- 遍历字符串
- 分别计算连续1和连续0的长度，更新长度最大值
- 遍历结束后进行判断
### 代码
```java
class Solution {
    public boolean checkZeroOnes(String s) {
        int len = s.length(), max1 = 0, max0 = 0;
        for (int i = 0; i < len;) {
            if (s.charAt(i) == '1') {
                int one = 0;
                while (i < len && s.charAt(i) == '1') {
                    one++;
                    i++;
                }

                max1 = Math.max(one, max1);
            } else {
                int zero = 0;
                while (i < len && s.charAt(i) == '0') {
                    zero++;
                    i++;
                }

                max0 = Math.max(zero, max0);
            }
        }

        return max1 > max0;
    }
}
```
# [LeetCode_423_从英文中重建数字](https://leetcode-cn.com/problems/reconstruct-original-digits-from-english/)
## 解法
### 思路
- 识别英文中各个代表数字的单词中，每个单词是否有独有的字母
  - 0：zero，z
  - 2：two，w
  - 4：four，u
  - 6：six，x
  - 8：eight，g
  - 3：h - nums[8]：h出现在3和8里，8通过g确定后，算出h出现的个数，减去8的个数即是3的个数
  - 5：f - nums[4]：f出现在4和5里，4通过u确定后，算出f出现的个数，减去4的个数即是5的个数
  - 7：s - nums[6]：s出现在6和7里，6通过x确定后，算出s出现的个数，减去6的个数即是7的个数
  - 9：i - nums[5] - nums[6] - nums[8]：i出现在5，6，8，9里，剩余逻辑如上推理
  - 1：n - nums[7] - nums[9] * 2：n出现在1，7，9里，其中9里有2个n，所以需要在计算的时候*2，剩余逻辑如上推理
- 这些数字的个数通过如上推算出来后，就可以通过数字大小拼接字符串了
### 代码
```java
class Solution {
    public String originalDigits(String s) {
        char[] cs = s.toCharArray();
        int[] nums = new int[10], bucket = new int[26];
        for (char c : cs) {
            bucket[c - 'a']++;
        }

        nums[0] = bucket['z' - 'a'];
        nums[2] = bucket['w' - 'a'];
        nums[4] = bucket['u' - 'a'];
        nums[6] = bucket['x' - 'a'];
        nums[8] = bucket['g' - 'a'];
        nums[3] = bucket['h' - 'a'] - nums[8];
        nums[5] = bucket['f' - 'a'] - nums[4];
        nums[7] = bucket['s' - 'a'] - nums[6];
        nums[9] = bucket['i' - 'a'] - nums[5] - nums[6] - nums[8];
        nums[1] = bucket['n' - 'a'] -nums[7] - 2 * nums[9];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < nums[i]; j++) {
                sb.append(i);
            }
        }
        
        return sb.toString();
    }
}
```
# [LeetCode_1876_长度为三且各字符不同的子字符串]()
## 解法
### 思路
- 循环字符串
- 每连续的3个字符为一组进行判断，是否有重复字符
- 如果没有就累加1
- 循环结束返回累加值
### 代码
```java
class Solution {
    public int countGoodSubstrings(String s) {
        char[] cs = s.toCharArray();
        int count = 0;
        for (int i = 0; i < s.length() - 2; i++) {
            if (cs[i] != cs[i + 1] && cs[i + 1] != cs[i + 2] && cs[i] != cs[i + 2]) {
                count++;
            }
        }
        return count;
    }
}
```
# [LeetCode_458_可怜的小猪](https://leetcode-cn.com/problems/poor-pigs/)
## 解法
### 思路
- 一只小猪代表一个维度(设为n), 每只小猪可以测试的次数+1为这只小猪在本维度上可以确定的点的数量(设为s), 则组成的n维空间中的总的点的数量为 s^n .
- 小猪喝水策略: 把所有的水桶排成一个n维的n方体, 每只小猪喝垂直于本维度(轴)的一个"超平面".
- 如2只小猪, 5个点. 25桶水排成一个矩形, 一只喝行, 一只喝列. 2只小猪确定一个点(***).
- 如3只小猪, 5个点. 125桶水排成一个立方体, 一只喝垂直于x轴的面, 一只喝垂直于y轴的面, 一只喝垂直于z轴的面. 3只小猪确定一个点(***).
- 如n只小猪, 5个点. 5^n桶水排成一个n方体, 每只喝垂直于本维度的一个"超平面"上的所有的水. n只小猪确定n维空间中的一个点(***).
### 代码
```java
class Solution {
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        return (int) Math.ceil(Math.log(buckets) / Math.log(minutesToTest / minutesToDie + 1));
    }
}
```
# [LeetCode_700_二叉搜索树中的搜索](https://leetcode-cn.com/problems/search-in-a-binary-search-tree/)
## 解法
### 思路
dfs
### 代码
```java
class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        
        int cur = root.val;
        
        if (cur == val) {
            return root;
        }
        
        if (cur < val) {
            return searchBST(root.right, val);
        } else {
            return searchBST(root.left, val);
        }
    }
}
```
# [LeetCode_1880_检查某单词是否等于两单词之和](https://leetcode-cn.com/problems/check-if-word-equals-summation-of-two-words/)
## 解法
### 思路
- 构建字符换转数字函数
- 相加比较
### 代码
```java
class Solution {
    public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
        return convert(firstWord) + convert(secondWord) == convert(targetWord);
    }

    private long convert(String word) {
        long sum = 0;
        char[] cs = word.toCharArray();
        
        for (int i = 0; i < cs.length; i++) {
            sum = sum * 10 + (cs[i] - 'a');
        }

        return sum;
    }
}
```
# [LeetCode_519_随机翻转矩阵](https://leetcode-cn.com/problems/random-flip-matrix/)
## 解法
### 思路
- 将横和纵坐标的乘积作为随机值来获取
- 随机值的边界就是[0,x*y)
- 边界的右边界在每次flip之后缩减
- 每一次使用过一个随机值后，将该值与随机值的右边界交换，使得该值永远不会在下一次随机的时候获取到，获取到的则是没使用过得右边界
- 当然，如果随机到的就是右边界，那因为下次因为右边界的缩减，所以也永远不会再随机到
### 代码
```java
class Solution {
    private int num, r, c;
    private Map<Integer, Integer> mapping;
    private Random random;
    public Solution(int n_rows, int n_cols) {
        num = n_rows * n_cols;
        r = n_rows;
        c = n_cols;
        mapping = new HashMap<>();
        random = new Random();
    }

    public int[] flip() {
        int index = random.nextInt(num--);
        int value = mapping.getOrDefault(index, index);
        mapping.put(index, mapping.getOrDefault(num, num));
        return new int[]{value / c, value % c};
    }

    public void reset() {
        num = r * c;
        mapping.clear();
    }
}
```
# [LeetCode_438_找到字符串中所有字母的异位词](https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/)
## 解法
### 思路
- 记录遍历到字符串每个字符时候的累计字符统计值的快照
- 利用这个快照可以通过前缀和的方式快速计算出区间的字符出现的个数
- 通过整理出s和p的字符计数值快照，然后遍历字符串，依次比较字符间s的区间差值快照，比较是否和p的快照一致，如果一致就记录起始坐标
- 遍历结束后返回即可
### 代码
```java
class Solution {
  public List<Integer> findAnagrams(String s, String p) {
    int len = s.length(), subLen = p.length();
    int[][] mapping = new int[len + 1][26];

    for (int i = 0; i < len; i++) {
      mapping[i + 1] = Arrays.copyOfRange(mapping[i], 0, 26);
      mapping[i + 1][s.charAt(i) - 'a']++;
    }

    int[] subBucket = new int[26];
    for (int i = 0; i < subLen; i++) {
      subBucket[p.charAt(i) - 'a']++;
    }

    List<Integer> ans = new ArrayList<>();
    for (int i = 0; i < len - subLen + 1; i++) {
      if (Arrays.equals(subBucket, get(i, i + subLen, mapping))) {
        ans.add(i);
      }
    }

    return ans;
  }

  private int[] get(int ia, int ib, int[][] mapping) {
    int[] bucket = Arrays.copyOfRange(mapping[ib], 0, mapping[ib].length);
    int[] arr = mapping[ia];
    for (int i = 0; i < arr.length; i++) {
      bucket[i] -= arr[i];
    }

    return bucket;
  }
}
```
# [LeetCode_1886_判断矩阵经轮转后是否一致](https://leetcode-cn.com/problems/determine-whether-matrix-can-be-obtained-by-rotation/)
## 解法
### 思路

### 代码
```java
class Solution {
    public boolean findRotation(int[][] mat, int[][] target) {
        for (int i = 0; i < 4; i++) {
            if (isSame(mat, target)) {
                return true;
            }
            mat = rotation(mat);
        }
        
        return false;
    }
    
    private boolean isSame(int[][] x, int[][] y) {
        int len = x[0].length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (x[i][j] != y[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private int[][] rotation(int[][] mat) {
        int len = mat[0].length;
        int[][] matrix = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                matrix[i][j] = mat[j][len - 1 - i];
            }
        }
        return matrix;
    }
}
```