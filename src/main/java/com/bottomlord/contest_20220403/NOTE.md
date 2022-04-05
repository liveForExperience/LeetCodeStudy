# [Contest_1_转化时间需要的最少操作数](https://leetcode-cn.com/problems/minimum-number-of-operations-to-convert-time/)
## 解法
### 思路
- 将cur和correct的两个字符串转化成int值
- 然后考虑如下情况
  - cur的min小于correct的min，直接计算差值，然后依次计算15和5的取模值，得到要累加的最少个数
  - cur的min大于correct的min，说明correct的min要从hour借60分钟，之后过程和如上是一样的
- 然后计算hour之间的差值，和min直接的差值之后，累加作为结果即可
### 代码
```java
class Solution {
    public int convertTime(String current, String correct) {
        String curHour = current.split(":")[0], curMin = current.split(":")[1];
        String correctHour = correct.split(":")[0], correctMin = correct.split(":")[1];

        int hour = Integer.parseInt(correctHour) - Integer.parseInt(curHour);
        int corMinInt = Integer.parseInt(correctMin), curMinInt = Integer.parseInt(curMin);

        if (corMinInt < curMinInt) {
            corMinInt += 60;
            hour--;
        }

        int sumMin = corMinInt - curMinInt;

        int min = 0;
        min += sumMin / 15;
        sumMin %= 15;

        min += sumMin / 5;
        sumMin %= 5;

        return hour + min + sumMin;
    }
}
```
# [Contest_2_找出输掉零场或一场比赛的玩家](https://leetcode-cn.com/contest/weekly-contest-287/problems/find-players-with-zero-or-one-losses/)
## 解法
### 思路
- 通过二维数组生成邻接表
  - key是输家的id
  - value是输家对手，也就是赢家的id列表
- 同时根据二维数组，得到所有参加者的id，遍历这些id
  - 如果邻接表key不包含当前id，那么就是赢家
  - 如果邻接表中对应的value长度为1，那么就是只输了1场的
- 将结果放入2个list后，排序
- 然后放入最终结果列表中返回
### 代码
```java
class Solution {
    public List<List<Integer>> findWinners(int[][] matches) {
        Set<Integer> set = new HashSet<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] match : matches) {
            set.add(match[0]);
            set.add(match[1]);
            
            map.computeIfAbsent(match[1], x -> new ArrayList<>()).add(match[0]);
        }

        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> ans1 = new ArrayList<>(), ans2 = new ArrayList<>();
        for (Integer n : set) {
            if (!map.containsKey(n)) {
                ans1.add(n);
            }
        }
        
        Collections.sort(ans1);

        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            if (entry.getValue().size() == 1) {
                ans2.add(entry.getKey());
            }
        }
        
        Collections.sort(ans2);
        
        ans.add(ans1);
        ans.add(ans2);
        
        return ans;
    }
}
```
# [Contest_3_每个小孩最多能分到多少糖果](https://leetcode-cn.com/problems/maximum-candies-allocated-to-k-children/)
## 解法
### 思路
- 遍历列表，计算出总和，如果总和小于k，那么说明肯定不够分，直接返回0
- 之后从1到总和值范围内，找到尽可能大的值，可以通过二分查找的方式找到这个极值
- 每次二分比较的当前的最小值，能否在数组中凑得至少k，如果可以就说明当前的最小值可行，然后就根据二分调整左右边界，符合就提升最小值的可能，也就是head变成mid +1，如果不符合，就调小最小值，也就是tail = mid - 1
- 遍历结束后，返回tail即可，循环时候的退出条件时head > tail
### 代码
```java
class Solution {
    public int maximumCandies(int[] candies, long k) {
        long min = Integer.MAX_VALUE, sum = 0;
        for (int candy : candies) {
            sum += candy;
            min = Math.min(min, candy);
        }

        if (sum < k) {
            return 0;
        }

        long head = 1, tail = sum / k;
        while (head <= tail) {
            long mid = head + (tail - head) / 2;
            long cur = count(candies, k, mid);
            
            if (cur >= k) {
                head = mid + 1;
            } else {
                tail = mid - 1;
            }
        }
        
        return (int)tail;
    }
    
    private long count(int[] candies, long k, long target) {
        long cur = 0;
        for (int candy : candies) {
            cur += (long) candy / target;

            if (cur >= k) {
                return cur;
            }
        }
        return cur;
    }
}
```
# [Contest_4_加密解密字符串](https://leetcode-cn.com/problems/encrypt-and-decrypt-strings/)
## 解法
### 思路
- 根据题目要求，生成字符与加密字符串的关系
- 根据字符与加密字符串的关系，可以生成对应的加密字符串
- 将dictionary的所有字符串，根据加密逻辑，统计dictionary加密后的字符串的个数
- `encrypt`就复用之前的加密逻辑
- `decrypt`，直接用入参的字符串到之前统计的map里去找个数就可以了
- 这题怎么会是困难！我当时怎么就钻了牛角尖！！！！
### 代码
```java
class Encrypter {
    private String[] values;
    private int[] cindex = new int[26];
    private Map<String, Integer> map;

    public Encrypter(char[] keys, String[] values, String[] dictionary) {
        this.values = values;
        Arrays.fill(cindex, -1);
        for (int i = 0; i < keys.length; i++) {
            char c = keys[i];
            cindex[c - 'a'] = i;
        }

        map = new HashMap<>();
        for (String word : dictionary) {
            String encryptWord = doEncrypt(word);
            map.put(encryptWord, map.getOrDefault(encryptWord, 0) + 1);
        }
    }

    public String encrypt(String word1) {
        return doEncrypt(word1);
    }

    public int decrypt(String word2) {
        return map.getOrDefault(word2, 0);
    }

    private String doEncrypt(String word) {
        StringBuilder sb = new StringBuilder();
        char[] cs = word.toCharArray();
        for (char c : cs) {
            sb.append(values[cindex[c - 'a']]);
        }
        return sb.toString();
    }
}
```