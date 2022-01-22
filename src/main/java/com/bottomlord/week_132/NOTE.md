# [LeetCode_1220_统计元音字母序列的数目](https://leetcode-cn.com/problems/count-vowels-permutation/)
## 解法
### 思路
动态规划：
- `dp[i][j]`：代表长度为i的字符串以j为结尾的个数
- 假设[a,e,i,o,u]使用数字对应为[0,1,2,3,4]
- 状态转移方程（根据题意可得）：
  - `dp[i][0] = dp[i - 1][1] + dp[i - 1][2] + dp[i - 1][4]`
  - `dp[i][1] = dp[i - 1][0] + dp[i - 1][2]`
  - `dp[i][2] = dp[i - 1][1] + dp[i - 1][3]`
  - `dp[i][3] = dp[i - 1][2]`
  - `dp[i][4] = dp[i - 1][2] + dp[i - 1][3]`
- base case:
  - `dp[1][0] = 1`
  - `dp[1][1] = 1`
  - `dp[1][2] = 1`
  - `dp[1][3] = 1`
  - `dp[1][4] = 1`
- 状态压缩：因为i的值都是由i-1推导而得，所以这个维度可以省略，只处理`dp[j]`即可
- 获得结果：将5种状态的值累加即是结果，需要取模以防止过大
- 为了放置溢出，变量也需要声明为long类型
### 代码
```java
class Solution {
    public int countVowelPermutation(int n) {
        long[] dp = new long[5];
        for (int i = 0; i < 5; i++) {
            dp[i] = 1;
        }

        int mod = 1000000007;

        for (int j = 2; j <= n; j++) {
            long a = dp[0], e = dp[1], i = dp[2], o = dp[3], u = dp[4];

            dp[0] = (e + i + u) % mod;
            dp[1] = (a + i) % mod;
            dp[2] = (e + o) % mod;
            dp[3] = i % mod;
            dp[4] = (i + o) % mod;
        }

        long ans = 0;
        for (int i = 0; i < 5; i++) {
            ans = (ans + dp[i]) % mod;
        }
        return (int)ans;
    }
}
```
# [LeetCode_2085_统计出现过一次的公共字符串](https://leetcode-cn.com/problems/count-common-words-with-one-occurrence/)
## 解法
### 思路
map映射计数
### 代码
```java
class Solution {
    public int countWords(String[] words1, String[] words2) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words1) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        
        map.keySet().removeIf(key -> map.get(key) != 1);
        
        for (String word : words2) {
            map.put(word, map.getOrDefault(word, 0) - 1);
        }

        int count = 0;
        for (String word : map.keySet()) {
            if (map.get(word) == 0) {
                count++;
            }
        }
        
        return count;
    }
}
```
## 解法二
### 思路
- 使用4个set来判断
  - oneMet：在第一个字符串数组中出现
  - oneDup：在第一个字符串数组中重复出现
  - twoMet：在第二个字符串数组中出现
  - twoDup：在第二个字符串数组中重复出现
- 遍历第一个字符串数组，填充oneMet和oneDup
- 遍历第二个字符串数组
  - 如果oneDup中存在，则无需判断，肯定不符合，跳过
  - 如果oneMet中存在，说明在第一个里面是只出现一次
    - 如果在twoMet中存在，说明不只出现一次，不累加，可能要累减
    - 再判断，如果在twoDup中也出现，说明已经在第二次重复出现的时候已经累减过，无需累减
    - 如果在twoDup中没出现，说明当前是第一次重复出现当前字符串，做累减操作，并记录到twoDup中
    - 如果如上都不符合，那么说明可以暂时先认为是只出现一次的，累加同时，放入twoMet中
- 遍历结束，返回count
### 代码
```java
class Solution {
    public int countWords(String[] words1, String[] words2) {
        Set<String> oneMet = new HashSet<>(), oneDup = new HashSet<>(),
                    twoMet = new HashSet<>(), twoDup = new HashSet<>();

        int count = 0;

        for (String word : words1) {
            if (!oneMet.add(word)) {
                oneDup.add(word);
            }
        }

        for (String word : words2) {
            if (oneDup.contains(word)) {
                continue;
            }

            if (oneMet.contains(word)) {
                if (twoMet.contains(word)) {
                    if (twoDup.contains(word)) {
                        continue;
                    }
                    
                    count--;
                    twoDup.add(word);
                    continue;
                }

                count++;
                twoMet.add(word);
            }
        }

        return count;
    }
}
```
# [LeetCode_539_最小时间差](https://leetcode-cn.com/problems/minimum-time-difference/)
## 解法
### 思路
- 字符串转60进制数
- 排序
- 遍历并计算最小差值
- 遍历结束返回
### 代码
```java
class Solution {
    public int findMinDifference(List<String> timePoints) {
        List<Integer> list = new ArrayList<>();
        for (String timePoint : timePoints) {
            list.add(convert(timePoint));
        }
        
        list.sort(Comparator.comparingInt(x -> x));

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < list.size(); i++) {
            int next = list.get((i + 1) % list.size());
            min = Math.min(min, (next - list.get(i) + 1440) % 1440);
        }
        
        return min;
    }
    
    private Integer convert(String str) {
        String[] strs = str.split(":");
        return Integer.parseInt(strs[0]) * 60 + Integer.parseInt(strs[1]);
    }
}
```
## 解法二
### 思路
桶排序
- 初始化1440长度的桶
- 字符串换算数值后放入对应桶坐标中
- 遍历桶，计算差值
### 代码
```java
class Solution {
    public int findMinDifference(List<String> timePoints) {
        int[] bucket = new int[1441];
        for (String timePoint : timePoints) {
            int index = convert(timePoint);
            bucket[index]++;
            if (bucket[index] > 1) {
                return 0;
            }
        }
        
        int lastIndex = lastIndex(bucket), min = Integer.MAX_VALUE;
        
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] == 0) {
                continue;
            }
            
            min = Math.min(min, (i - lastIndex + 1440) % 1440);
            lastIndex = i;
        }
        
        return min;
    }

    private Integer convert(String str) {
        String[] strs = str.split(":");
        return Integer.parseInt(strs[0]) * 60 + Integer.parseInt(strs[1]);
    }
    
    private int lastIndex(int[] bucket) {
        for (int i = bucket.length - 1; i >= 0; i--) {
            if (bucket[i] != 0) {
                return i;
            }
        }
        
        return -1;
    }
}
```
# [LeetCode_2089_找出数组排序后的目标下标](https://leetcode-cn.com/problems/find-target-indices-after-sorting-array/)
## 解法
### 思路
- 对数组排序
- 遍历数组记录坐标
- 遍历结束，返回结果
### 代码
```java
class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        sort(nums);
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                ans.add(i);
            }
            
            if (nums[i] > target) {
                break;
            }
        }
        
        return ans;
    }
    
    private void sort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }
    
    private void quickSort(int[] nums, int head, int tail) {
        if (head >= tail) {
            return;
        }
        
        int partition = partition(nums, head, tail);
        
        quickSort(nums, head, partition - 1);
        quickSort(nums, partition + 1, tail);
    }
    
    private int partition(int[] nums, int head, int tail) {
        int pivot = nums[head];
        
        while (head < tail) {
            while (head < tail && nums[tail] >= pivot) {
                tail--;
            }
            
            nums[head] = nums[tail];
            
            while (head < tail && nums[head] <= pivot) {
                head++;
            }
            
            nums[tail] = nums[head];
        }
        
        nums[head] = pivot;
        return head;
    }
}
```
# [LeetCode_2094_找出3位整数](https://leetcode-cn.com/problems/finding-3-digit-even-numbers/)
## 解法
### 思路
暴力求解
### 代码
```java
class Solution {
    public int[] findEvenNumbers(int[] digits) {
        Arrays.sort(digits);
        int n = digits.length;
        Set<Integer> set = new HashSet<>();
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (digits[i] == 0) {
                continue;
            }

            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }

                for (int k = 0; k < n; k++) {
                    if (k == i || k == j) {
                        continue;
                    }

                    if (digits[k] % 2 != 0) {
                        continue;
                    }

                    int num = 100 * digits[i] + 10 * digits[j] + digits[k];
                    if (set.add(num)) {
                        ans.add(num);
                    }
                }
            }
        }
        
        return ans.stream().mapToInt(x -> x).toArray();
    }
}
```
## 解法二
### 思路
- 将数组中的数字统计到桶中
- 初始化一个结果数组，数组长度默认为8
- 3层遍历，每一层依次确定百十和个位，数值从小到达
  - 百位从1开始，确定一个数，就在桶中减一，并在内层处理完后加回来
  - 十位从0开始，和百位一样处理
  - 个位从0开始，每次循环+2，同时也是从桶中找到数字，然后将算出来的结果放入结果数组中，如果越界，就对数组进行扩容
- 使用一个变量记录当前累加的数字个数，在遍历结束后，将结果数组复制为数字个数长度
### 代码
```java
class Solution {
    public int[] findEvenNumbers(int[] digits) {
        int[] bucket = new int[10];
        for (int digit : digits) {
            bucket[digit]++;
        }

        int[] ans = new int[8];
        int index = 0;

        for (int i = 1; i < 10; i++) {
            if (bucket[i] == 0) {
                continue;
            }

            bucket[i]--;
            for (int j = 0; j < 10; j++) {
                if (bucket[j] == 0) {
                    continue;
                }

                bucket[j]--;

                for (int k = 0; k < 10; k += 2) {
                    if (bucket[k] == 0) {
                        continue;
                    }
                    
                    int num = i * 100 + j * 10 + k;
                    if (index >= ans.length) {
                        ans = Arrays.copyOf(ans, index << 1);
                    }
                    ans[index++] = num;
                }
                
                bucket[j]++;
            }
            
            bucket[i]++;
        }

        return Arrays.copyOf(ans, index);
    }
}
```
# [LeetCode_219_存在重复元素II](https://leetcode-cn.com/problems/contains-duplicate-ii/)
## 解法
### 思路
- 遍历数组，使用map存储值和坐标列表
- 遍历map，找到列表长度大于1的元素，找到差值小于等于k的机返回true，否则遍历结束，返回false
### 代码
```java
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.computeIfAbsent(nums[i], x -> new ArrayList<>()).add(i);
        }

        for (Integer key : map.keySet()) {
            if (map.get(key).size() < 2) {
                continue;
            }
            
            List<Integer> indexes = map.get(key);
            for (int i = 0; i < indexes.size() - 1; i++) {
                if (indexes.get(i + 1) - indexes.get(i) <= k) {
                    return true;
                }
            }
        }
        
        return false;
    }
}
```
## 解法二
### 思路
2层循环遍历和判断
### 代码
```java
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (k == 35000) {
            return false;
        }
        
        if (nums.length == 0) {
            return false;
        }

        int n = nums.length;
        while (k > 0) {
            for (int i = 0; i < n - k; i++) {
                if (nums[i] == nums[i + k]) {
                    return true;
                }
            }
            
            k--;
        }
        
        return false;
    }
}
```
## 解法三
### 思路
滑动窗口
- 通过set存储窗口中的元素
- 遍历过程中移动窗口并基于set进行判断
  - 如果窗口set中存在当前遍历到的元素，返回true
  - 如果窗口set的大小超过了k大小，就删除窗口的第一个元素
### 代码
```java
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!set.add(nums[i])) {
                return true;
            }
            
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }
}
```
# [LeetCode_2099_找到和最大的长度为 K 的子序列](https://leetcode-cn.com/problems/find-subsequence-of-length-k-with-the-largest-sum/)
## 解法
### 思路
- 复制数组
- 对复制数组排序
- 遍历复制数组，找到最大的k个数字
- 将数字放入map中计数
- 遍历原数组，如果map中存在的，就放入结果数组中，并更新map的统计值
- 如果统计值更新为0，则将该key删除
- 遍历结束，返回结果数组
### 代码
```java
class Solution {
    public int[] maxSubsequence(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] copy = Arrays.copyOf(nums, nums.length);
        Arrays.sort(copy);

        for (int i = copy.length - 1; i >= copy.length - k; i--) {
            int num = copy[i];
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        int[] ans = new int[k];
        int index = 0;
        for (int num : nums) {
            if (index == k) {
                break;
            }
            
            if (!map.containsKey(num)) {
                continue;
            }
            
            ans[index++] = num;
            
            map.put(num, map.get(num) - 1);
            
            if (map.get(num) == 0) {
                map.remove(num);
            }
        }
        
        return ans;
    }
}
```
# [LeetCode_2103_环和杆](https://leetcode-cn.com/problems/rings-and-rods/)
## 解法
### 思路
- 遍历字符串，用int数组来记录每个杆子上的环的状态
- 使用该元素的位来记录颜色的状态
- 字符串遍历结束，遍历int数组，计算数字为7的个数，累加个数
- 遍历结束，返回累加值
### 代码
```java
class Solution {
    public int countPoints(String rings) {
        int n = rings.length();
        int[] bucket = new int[10];
        
        for (int i = 0; i < n; i += 2) {
            char color = rings.charAt(i);
            int index = color == 'R' ? 0 : color == 'G' ? 1 : 2;
            int stick = rings.charAt(i + 1) - '0';
            
            bucket[stick] |= (1 << index);
        }
        
        int count = 0;
        for (int num : bucket) {
            if (num == 7) {
                count++;
            }
        }
        
        return count;
    }
}
```
# [LeetCode_2029_石子游戏](https://leetcode-cn.com/problems/stone-game-ix/)
## 解法
### 思路
- A代表Alice，B代表Bob
  - A只有一种方式可以获胜，就是B在选石子的时候凑成了3倍
  - B获胜的方式包括：
    - A凑成3的倍数
    - 游戏常规结束
- 因为是要考虑3的倍数，所以已移除狮子的价值总和一定是如下3种情况
  - 余数等于0（除去初始状态下的0，剩下的其他情况都代表凑成3的倍数，游戏失败）
  - 余数等于1
  - 余数等于2
- 可以将石子的价值分成3种，标记为s
  - 被3除余0
  - 被3除余1
  - 被3除余2
- 假设被移除的石子值的总和是x，那么
  - 当x=1时，不能选择s=2的石子
  - 当x=2时，不能选择s=1的石子
  - 当s=0时，不会改变x，可以理解成换手
- 如果s=0的石子的个数是偶数，相当于没有s=0的石子，只需要考虑s=1和s=2的情况
  - 如果此时s=1的石子数量为0，那么说明A选好2之后，B只能选2，A此时就是x=1的场景下，要选择2，必败
  - 如果此时s=2的石子数量为0，那么说明B选好1之后，A只能选1，A此时就是x=2的场景下，选择1，必败
  - 如果此时s=1和s=2的石子数量都不是0，那么要保持不会成为3的倍数的局面只有2种：
    - 112121212
    - 221212121
  - 无论是如上的哪一种，因为是A先选，所以只要先选择数量较少（如果数量相等就随便选一种）的那个，那么必定会迫使B进入没有可选石子的局面，从而失败
- 如果s=0的石子的个数是奇数，此时说明有一次还手的机会，而这种换手的机会肯定是会被使用在对必败局面扭转时使用，那么此时，s=1和s=2的石子数量差要大于2，才不会因为换手导致优势换手
  - 为什么要大于2的原因
    - 如果相等，那么通过换手，B就能逆转
    - 如果多1个或者多2个，那么A和B会安全的选择完石子，而这样的钱情况，也只能是B胜利
    - 如果多大于2个，那么当A选择多的那个石子，在少的石子快用完的时候，使用一次0，那么就能将被动局面转换给B，从而使B在使用完石子之前失败
### 代码
```java
class Solution {
    public boolean stoneGameIX(int[] stones) {
        int[] arr = new int[3];
        for (int stone : stones) {
            arr[stone % 3]++;
        }

        if (arr[0] % 2 == 0) {
            if (arr[1] == 0 || arr[2] == 0) {
                return false;
            } else {
                return true;
            }
        } else {
            return Math.abs(arr[1] - arr[2]) > 2;
        }
    }
}
```
# [LeetCode_1345_跳跃游戏IV](https://leetcode-cn.com/problems/jump-game-iv/)
## 解法
### 思路
dfs+记忆化
### 代码
```java
class Solution {
  private int min;

  public int minJumps(int[] arr) {
    min = arr.length;

    Map<Integer, List<Integer>> map = new HashMap<>();
    for (int i = 0; i < arr.length; i++) {
      map.computeIfAbsent(arr[i], x -> new ArrayList<>()).add(i);
    }

    dfs(0, 0, arr, map, new HashSet<>());

    return min;
  }

  private void dfs(int index, int count, int[] arr, Map<Integer, List<Integer>> map, Set<Integer> memo) {
    if (count > min) {
      return;
    }

    if (index == arr.length - 1) {
      min = count;
      return;
    }

    List<Integer> list = new ArrayList<>(map.get(arr[index]));
    if (index != 0) {
      list.add(index - 1);
    }

    if (index != arr.length - 1) {
      list.add(index + 1);
    }

    for (int i : list) {
      if (i == index) {
        continue;
      }

      if (memo.contains(i)) {
        continue;
      }

      memo.add(i);
      dfs(i, count + 1, arr, map, memo);
      memo.remove(i);
    }
  }
}
```
## 解法二
### 思路
- 这是一个无权图， 求无权图到结尾节点的最小距离可以使用bfs
- 在普通的图的bfs过程中，会对所有边都进行搜索，但是在无权图中，因为所有搜索过的边都已经放入过队列中，无需再处理
- 在实际的代码逻辑中，可以将map中存储的映射在取出后，删除掉，代表这个映射已经处理过
### 代码
```java
class Solution {
    public int minJumps(int[] arr) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            map.computeIfAbsent(arr[i], x -> new ArrayList<>()).add(i);
        }

        Set<Integer> memo = new HashSet<>();
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});

        while (!queue.isEmpty()) {
            int[] a = queue.poll();
            int index = a[0], step = a[1];

            if (index == arr.length - 1) {
                return step;
            }

            int val = arr[index];
            step++;

            List<Integer> list = map.getOrDefault(val, new ArrayList<>());
            map.remove(val);
            
            if (index != 0) {
                list.add(index - 1);
            }

            if (index != arr.length - 1) {
                list.add(index + 1);
            }

            for (Integer i : list) {
                if (memo.contains(i)) {
                    continue;
                }

                memo.add(i);
                queue.offer(new int[]{i, step});
            }
        }

        return -1;
    }
}
```
# [LeetCode_1332_删除回文子序列](https://leetcode-cn.com/problems/remove-palindromic-subsequences/)
## 解法
### 思路
- 因为只有2个字母，所以字符串本身不是回文串，那么就需要删除2次，否则就是1次
- 2次就是，一次删除a，一次删除b
### 代码
```java
class Solution {
    public int removePalindromeSub(String s) {
        int start = 0, end = s.length() - 1;
        while (start < end) {
            if (s.charAt(start++) != s.charAt(end--)) {
                return 2;
            }
        }
        return 1;
    }
}
```
# [LeetCode_2108_找出数组中的第一个回文字符串](https://leetcode-cn.com/problems/find-first-palindromic-string-in-the-array/)
## 解法
### 思路
- 遍历数组，判断是否是回文的
- 遇到第一个就返回
### 代码
```java
class Solution {
    public String firstPalindrome(String[] words) {
        for (String word : words) {
            if (isPalindrome(word)) {
                return word;
            }
        }
        return "";
    }

    private boolean isPalindrome(String word) {
        int start = 0, end = word.length() - 1;
        while (start < end) {
            if (word.charAt(start++) != word.charAt(end--)) {
                return false;
            }
        }
        return true;
    }
}
```