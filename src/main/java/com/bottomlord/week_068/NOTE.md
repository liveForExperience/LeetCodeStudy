# LeetCode_1365_有多少小于当前数字的数字
## 题目
给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。

换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。

以数组形式返回答案。

示例 1：
```
输入：nums = [8,1,2,2,3]
输出：[4,0,1,1,3]
解释： 
对于 nums[0]=8 存在四个比它小的数字：（1，2，2 和 3）。 
对于 nums[1]=1 不存在比它小的数字。
对于 nums[2]=2 存在一个比它小的数字：（1）。 
对于 nums[3]=2 存在一个比它小的数字：（1）。 
对于 nums[4]=3 存在三个比它小的数字：（1，2 和 2）。
```
示例 2：
```
输入：nums = [6,5,4,8]
输出：[2,1,0,3]
```
示例 3：
```
输入：nums = [7,7,7,7]
输出：[0,0,0,0]
```
提示：
```
2 <= nums.length <= 500
0 <= nums[i] <= 100
```
## 解法
### 思路
O(N^2)的时间复杂度，两次循环
### 代码
```java
class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            for (int num : nums) {
                if (nums[i] > num) {
                    ans[i]++;
                }
            }
        }
        
        return ans;
    }
}
```
## 解法二
### 思路
快排+记录坐标
### 代码
```java
class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int len = nums.length;
        int[] ans = new int[len];
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        for (int i = 0; i < len; i++) {
            List<Integer> list = map.getOrDefault(nums[i], new ArrayList<>());
            list.add(i);
            map.put(nums[i], list);
        }

        Arrays.sort(nums);
        
        for (int i = 0; i < len; i++) {
            if (i != 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            
            List<Integer> list = map.get(nums[i]);
            for (Integer index : list) {
                ans[index] = i;
            }
        }
        
        return ans;
    }
}
```
## 解法三
### 思路
数组计数并计算前缀和
### 代码
```java
class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int len = nums.length;
        int[] count = new int[101], ans = new int[len];
        for (int num : nums) {
            count[num]++;
        }

        int[] sums = new int[count.length];
        int sum = 0;
        for (int i = 0; i < count.length; i++) {
            sum += count[i];
            sums[i] = sum - count[i];
        }

        for (int i = 0; i < len; i++) {
            ans[i] = sums[nums[i]];
        }

        return ans;
    }
}
```
# LeetCode_316_去除重复字母
## 题目
给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。

注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters 相同

示例 1：
```
输入：s = "bcabc"
输出："abc"
```
示例 2：
```
输入：s = "cbacdcbc"
输出："acdb"
```
## 失败解法
### 失败原因
改变了相对顺序
### 思路
- 转字符数组
- 快速排序
- 遍历数组append
### 代码
```java
class Solution {
    public String removeDuplicateLetters(String s) {
        char[] cs = s.toCharArray();
        Arrays.sort(cs);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cs.length; i++) {
            if (i == cs.length - 1) {
                sb.append(cs[i]);
                break;
            }
            
            if (cs[i] == cs[i + 1]) {
                continue;
            }
            
            sb.append(cs[i]);
        }
        return sb.toString();
    }
}
```
## 解法
### 思路
- 为了保证字符的相对顺序，且同时是最小字典序列，那么序列小的小字符能够在前面的条件就是：我前面比我大的字符，在我之后也存在
- 首先对字符串中出现的字符进行计数，用于在遍历过程中判断是否当前字符已经是相同字符中的最后一个
- 在找那个尽可能小的字符时，先暂定一个当前搜索范围内的最小字符，并不断比较更新
- 然后将当前遍历到的字符从计数中-1
- 如果发现当前字符是最后一个出现的字符了，那么说明当前那个最小字符，就是目前字符串中能够排在前面的最小字典序列的字符了，因为就算后面有更小的，它之后也没有前面比它大的那些字符了，也就保证不了原来的相对顺序
- 然后就重复如上的顺序，在递归过程中，截取当前最小字符所在位置之前的所有字符，那些在其后都存在，已经不需要了，同时，将其后字符中与当前最小字符相同的字符去除
- 递归的退出条件就是字符串长度为0
- 每一层递归的目的就是获得当前字符串中能够排在最前面且字典序列符合条件前提下最小的字符，将他们通过递归的方式拼接，就是最终需要的字符串了
### 代码
```java
class Solution {
    public String removeDuplicateLetters(String s) {
        int len = s.length();
        if (len == 0) {
            return "";
        }
        int[] count = new int[26];
        
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        
        int index = 0;
        for (int i = 0; i < len; i++) {
            index = s.charAt(index) <= s.charAt(i) ? index : i;
            if (--count[s.charAt(i) - 'a'] == 0) {
                break;
            }
        }
        
        return s.charAt(index) + removeDuplicateLetters(s.substring(index + 1).replaceAll("" + s.charAt(index), ""));
    }
}
```
## 解法二
### 思路
- 使用栈暂存遍历字符串时找到的字符
- 使用set记录当前字符是否在栈中
- 使用map记录字符在字符串中的最大坐标
- 过程:
    - 遍历字符串
    - 如果set中没有字符串，那么当前字符可以被加到stack中作为结果
    - 将当前字符`c`与栈顶元素比较，这个栈顶元素就是当前作为结果字符串的最后一个字符
    - 如果`c`比栈顶元素小，且栈顶元素在剩余遍历字符串中还存在，就将这个栈顶元素弹出，并将该元素从set中去掉
    - 重复如上比较的过程，直到不能再弹栈为止
    - 然后将当前字符加入到stack中，并加入到set中
- 最终从栈底开始遍历字符，拼接后返回这个结果
### 代码
```java
class Solution {
    public String removeDuplicateLetters(String s) {
        Stack<Character> stack = new Stack<>();
        Set<Character> set = new HashSet<>();
        Map<Character, Integer> map = new HashMap<>();

        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            map.put(cs[i], i);
        }

        for (int i = 0; i < cs.length; i++) {
            char c = cs[i];
            if (!set.contains(c)) {
                while (!stack.isEmpty() && c < stack.peek() && map.get(stack.peek()) > i) {
                    set.remove(stack.pop());
                }
                set.add(c);
                stack.push(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }
        return sb.toString();
    }
}
```
# LeetCode_317_离建筑物最近的距离
## 题目
你是个房地产开发商，想要选择一片空地 建一栋大楼。你想把这栋大楼够造在一个距离周边设施都比较方便的地方，通过调研，你希望从它出发能在 最短的距离和 内抵达周边全部的建筑物。请你计算出这个最佳的选址到周边全部建筑物的 最短距离和。

提示：
```
你只能通过向上、下、左、右四个方向上移动。
给你一个由 0、1 和 2 组成的二维网格，其中：
0 代表你可以自由通过和选择建造的空地
1 代表你无法通行的建筑物
2 代表你无法通行的障碍物
```
示例：
```
输入：[[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]

1 - 0 - 2 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0
输出：7 
解析：
给定三个建筑物 (0,0)、(0,4) 和 (2,2) 以及一个位于 (0,2) 的障碍物。
由于总距离之和 3+3+1=7 最优，所以位置 (1,2) 是符合要求的最优地点，故返回7。
```
## 解法
### 思路
bfs：
- 遍历二维数组，从建筑物开始bfs
- 因为有n个建筑物，而空地的起始值是0，所以可以每次搜索完一个空地后，将这个空地的值-1，并在bfs外层统一定义当前层的空地的值，比如，第一个建筑物搜索完后，统一是-1，第二次统一是-2，这样每次搜索时就能直到哪些是已经搜索过的
- 在bfs过程中，记录每一个空地坐标到达当前这座建筑物的物理距离，并存放在一个二维数组中，同时和暂存的最小值进行比较，实时更新结果
- 所有建筑物都搜索完以后，就返回那个暂存的结果，如果结果是初始的int最大值，则返回-1
### 代码
```java
class Solution {
    private int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int shortestDistance(int[][] grid) {
        int row = grid.length;
        if (row == 0) {
            return 0;
        }

        int col = grid[0].length;
        int[][] totalDistance = new int[row][col];

        int mark = 0, ans = Integer.MAX_VALUE;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    ans = bfs(grid, row, col, i, j, mark, totalDistance);
                    mark--;
                }
            }
        }

        return ans;
    }

    private int bfs(int[][] grid, int row, int col, int r, int c, int mark, int[][] totalDistance) {
        int ans = Integer.MAX_VALUE;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{r, c, 0});

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            if (arr == null) {
                continue;
            }

            int curDistance = arr[2];

            for (int i = 0; i < directions.length; i++) {
                int nextRow = arr[0] + directions[i][0],
                    nextCol = arr[1] + directions[i][1];
                
                if (nextRow >= 0 && nextRow < row && nextCol >= 0 && nextCol < col && grid[nextRow][nextCol] == mark) {
                    int nextDistance = curDistance + 1;
                    totalDistance[nextRow][nextCol] += nextDistance;
                    ans = Math.min(ans, totalDistance[nextRow][nextCol]);
                    
                    queue.offer(new int[]{nextRow, nextCol, nextDistance});
                    grid[nextRow][nextCol]--;
                }
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
```
# LeetCode_319_灯泡开关
## 题目
初始时有 n 个灯泡关闭。 第 1 轮，你打开所有的灯泡。 第 2 轮，每两个灯泡你关闭一次。 第 3 轮，每三个灯泡切换一次开关（如果关闭则开启，如果开启则关闭）。第 i 轮，每 i 个灯泡切换一次开关。 对于第 n 轮，你只切换最后一个灯泡的开关。 找出 n 轮后有多少个亮着的灯泡。

示例:
```
输入: 3
输出: 1 
解释: 
初始时, 灯泡状态 [关闭, 关闭, 关闭].
第一轮后, 灯泡状态 [开启, 开启, 开启].
第二轮后, 灯泡状态 [开启, 关闭, 开启].
第三轮后, 灯泡状态 [开启, 关闭, 关闭]. 

你应该返回 1，因为只有一个灯泡还亮着。
```
## 失败解法
### 失败原因
超时
### 思路
暴力
### 代码
```java
class Solution {
    public int bulbSwitch(int n) {
        if (n <= 1) {
            return n;
        }
        
        boolean[] flags = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (j % i == 0) {
                    flags[j] = !flags[j];
                }
            }
        }
        
        int ans = 0;
        for (boolean flag : flags) {
            ans += flag ? 1 : 0;
        }
        return ans;
    }
}
```
## 解法
### 思路
- 一个灯泡在第n次时的状态，可以理解成，在n次操作中，这个灯泡被操作了奇数次还是偶数次
- 通过举例：
    - 第1个灯泡：第1次被操作
    - 第10个灯泡：第1、2、5、10次被操作
    - 第20个灯泡：第1、2、4、5、10、20次被操作
- 由举例可知，一个灯泡被操作几次，取决于这个灯泡的因数个数
- 而题目要求算出灯泡是开启的状态，也就是因数个数是奇数个的，所以就是求完全平方数
### 代码
```java
class Solution {
    public int bulbSwitch(int n) {
        if (n <= 1) {
            return n;
        }

        int ans = 1;
        while (ans * ans <= n) {
            ans++;
        }
        return ans - 1;
    }
}
```
# LeetCode_320_列举单词的全部缩写
## 题目
请你写出一个能够举单词全部缩写的函数。

注意：输出的顺序并不重要。

示例：
```
输入: "word"
输出:
["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
```
## 解法
### 思路
回溯：
- 传递的变量：
    - word：原字符串
    - sb：回溯时记录的临时结果字符串
    - num：搜索时用来记录略过的字符个数，最终用该值来作为结果中的简写
    - index：搜索时记录的下标值，作为递归退出条件的依据
    - ans：作为储存结果的集合
- 过程：
    - 退出条件：index越界，代表word已经被搜索完，此时只有num需要被判断是否要累加到sb中，因为字符会在每一层的递归过程中被作为一个路径分支append到sb上，并将sb放入ans中
    - 递归过程：
        - 先记录当前sb的长度len，这个len会用在回溯时做状态恢复的依据
        - 选择2种路径:
            - 用数字代替当前字符，直接掠过，此时index和num同时累加并开始递归
            - 将当前字符append到sb中：
                - 先将之前记录的非0的num加入到sb中
                - 将当前index对应的字符放入sb中
                - 递归，index加1，num归0
        - 回溯时利用len将sb状态恢复
### 代码
```java
class Solution {
    public List<String> generateAbbreviations(String word) {
        List<String> ans = new ArrayList<>();
        backTrack(word, 0, 0, new StringBuilder(), ans);
        return ans;
    }

    private void backTrack(String word, int index, int num, StringBuilder sb, List<String> ans) {
        int len = sb.length();
        
        if (index == word.length()) {
            if (num != 0) {
                sb.append(num);
            }
            ans.add(sb.toString());
        } else {
            backTrack(word, index + 1, num + 1, sb, ans);

            if (num != 0) {
                sb.append(num);
            }

            sb.append(word.charAt(index));
            backTrack(word, index + 1, 0, sb, ans);
            sb.deleteCharAt(sb.length() - 1);
        }
        
        sb.setLength(len);
    }
}
```
# LeetCode_381_O(1)时间插入、删除和获取随机元素-允许重复
## 题目
设计一个支持在平均 时间复杂度 O(1) 下， 执行以下操作的数据结构。

注意: 允许出现重复元素。
```
insert(val)：向集合中插入元素 val。
remove(val)：当 val 存在时，从集合中移除一个 val。
getRandom：从现有集合中随机获取一个元素。每个元素被返回的概率应该与其在集合中的数量呈线性相关。
```
示例:
```
// 初始化一个空的集合。
RandomizedCollection collection = new RandomizedCollection();

// 向集合中插入 1 。返回 true 表示集合不包含 1 。
collection.insert(1);

// 向集合中插入另一个 1 。返回 false 表示集合包含 1 。集合现在包含 [1,1] 。
collection.insert(1);

// 向集合中插入 2 ，返回 true 。集合现在包含 [1,1,2] 。
collection.insert(2);

// getRandom 应当有 2/3 的概率返回 1 ，1/3 的概率返回 2 。
collection.getRandom();

// 从集合中删除 1 ，返回 true 。集合现在包含 [1,2] 。
collection.remove(1);

// getRandom 应有相同概率返回 1 和 2 。
collection.getRandom();
```
## 解法
### 思路
- 插入时因为不需要考虑排序，所以可以保证O1
- 保证随机几率，只要保证当前的元素的个数正确，就可以通过random的API及元素个数来获取正确概率
- 删除时，如果只通过数组或动态数组来保存，那么无法保证O1的时间复杂度，但因为无须考虑有序性，所以，如果每次删除时都将最后一个元素覆盖掉要删除的元素，然后删除最后一个元素，那么这个操作的时间复杂同样就是O1了
### 代码
```java

```