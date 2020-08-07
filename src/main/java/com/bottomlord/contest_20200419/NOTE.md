# Contest_1_重新格式化字符串
## 题目
给你一个混合了数字和字母的字符串 s，其中的字母均为小写英文字母。

请你将该字符串重新格式化，使得任意两个相邻字符的类型都不同。也就是说，字母后面应该跟着数字，而数字后面应该跟着字母。

请你返回 重新格式化后 的字符串；如果无法按要求重新格式化，则返回一个 空字符串 。

示例 1：
```
输入：s = "a0b1c2"
输出："0a1b2c"
解释："0a1b2c" 中任意两个相邻字符的类型都不同。 "a0b1c2", "0a1b2c", "0c2a1b" 也是满足题目要求的答案。
```
示例 2：
```
输入：s = "leetcode"
输出：""
解释："leetcode" 中只有字母，所以无法满足重新格式化的条件。
```
示例 3：
```
输入：s = "1229857369"
输出：""
解释："1229857369" 中只有数字，所以无法满足重新格式化的条件。
```
示例 4：
```
输入：s = "covid2019"
输出："c2o0v1i9d"
```
示例 5：
```
输入：s = "ab123"
输出："1a2b3"
```
提示：
```
1 <= s.length <= 500
s 仅由小写英文字母和/或数字组成。
```
## 解法
### 思路
- 遍历字符串，将字母和数字分别放入不同的list种
- 根据list的长度判断这两组内容是否可以组合成符合题目要求的字符串
    - 如果长度差的绝对值大于1，不能
    - 如果字母多，则先从字母开始拼接
    - 如果数字多，则先从数字开始拼接
    - 如果一样多，则从字母开始拼接
### 代码
```java
class Solution {
    public String reformat(String s) {
        List<Character> nums = new ArrayList<>(), alps = new ArrayList<>();
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                nums.add(c);
            } else {
                alps.add(c);
            }
        }
        
        if (Math.abs(nums.size() - alps.size()) > 1) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        if (nums.size() > alps.size()) {
            sb.append(nums.get(0));
            for (int i = 0; i < alps.size(); i++) {
                sb.append(alps.get(i));
                sb.append(nums.get(i + 1));
            }
        } else if (nums.size() < alps.size()) {
            sb.append(alps.get(0));
            for (int i = 0; i < nums.size(); i++) {
                sb.append(nums.get(i));
                sb.append(alps.get(i + 1));
            }
        } else {
            for (int i = 0; i < nums.size(); i++) {
                sb.append(nums.get(i));
                sb.append(alps.get(i));
            }
        }

        
        return sb.toString();
    }
}
```
# Contest_2_点菜展示表
## 题目
给你一个数组 orders，表示客户在餐厅中完成的订单，确切地说， orders[i]=[customerNamei,tableNumberi,foodItemi] ，其中 customerNamei 是客户的姓名，tableNumberi 是客户所在餐桌的桌号，而 foodItemi 是客户点的餐品名称。

请你返回该餐厅的 点菜展示表 。在这张表中，表中第一行为标题，其第一列为餐桌桌号 “Table” ，后面每一列都是按字母顺序排列的餐品名称。接下来每一行中的项则表示每张餐桌订购的相应餐品数量，第一列应当填对应的桌号，后面依次填写下单的餐品数量。

注意：客户姓名不是点菜展示表的一部分。此外，表中的数据行应该按餐桌桌号升序排列。

示例 1：
```
输入：orders = [["David","3","Ceviche"],["Corina","10","Beef Burrito"],["David","3","Fried Chicken"],["Carla","5","Water"],["Carla","5","Ceviche"],["Rous","3","Ceviche"]]
输出：[["Table","Beef Burrito","Ceviche","Fried Chicken","Water"],["3","0","2","1","0"],["5","0","1","0","1"],["10","1","0","0","0"]] 
解释：
点菜展示表如下所示：
Table,Beef Burrito,Ceviche,Fried Chicken,Water
3    ,0           ,2      ,1            ,0
5    ,0           ,1      ,0            ,1
10   ,1           ,0      ,0            ,0
对于餐桌 3：David 点了 "Ceviche" 和 "Fried Chicken"，而 Rous 点了 "Ceviche"
而餐桌 5：Carla 点了 "Water" 和 "Ceviche"
餐桌 10：Corina 点了 "Beef Burrito" 
```
示例 2：
```
输入：orders = [["James","12","Fried Chicken"],["Ratesh","12","Fried Chicken"],["Amadeus","12","Fried Chicken"],["Adam","1","Canadian Waffles"],["Brianna","1","Canadian Waffles"]]
输出：[["Table","Canadian Waffles","Fried Chicken"],["1","2","0"],["12","0","3"]] 
解释：
对于餐桌 1：Adam 和 Brianna 都点了 "Canadian Waffles"
而餐桌 12：James, Ratesh 和 Amadeus 都点了 "Fried Chicken"
```
示例 3：
```
输入：orders = [["Laura","2","Bean Burrito"],["Jhon","2","Beef Burrito"],["Melissa","2","Soda"]]
输出：[["Table","Bean Burrito","Beef Burrito","Soda"],["2","1","1","1"]]
```
提示：
```
1 <=orders.length <= 5 * 10^4
orders[i].length == 3
1 <= customerNamei.length, foodItemi.length <= 20
customerNamei 和 foodItemi 由大小写英文字母及空格字符 ' ' 组成。
tableNumberi 是 1 到 500 范围内的整数。
```
## 解法
### 思路
- 遍历二维数组
    - 生成菜品名字的set
    - 桌号的set
    - 以桌号为key，菜和菜被点数量的映射关系为value的map
- 将菜品和桌号的set进行排序
- 遍历菜品生成第一行
- 遍历桌号，配合map生成剩下的每一行
### 代码
```java
class Solution {
    public List<List<String>> displayTable(List<List<String>> orders) {
        Set<String> foodSet =  new HashSet<>();
        Set<Integer> tableSet = new HashSet<>();
        Map<Integer, Map<String, Integer>> map = new HashMap<>();

        for (List<String> order : orders) {
            String food = order.get(2);
            foodSet.add(food);

            Integer table = Integer.parseInt(order.get(1));
            tableSet.add(table);

            Map<String, Integer> innerMap = map.getOrDefault(table, new HashMap<>());
            innerMap.put(food, innerMap.getOrDefault(food, 0) + 1);
            map.put(table, innerMap);
        }

        List<String> foods = new ArrayList<>(foodSet);
        List<Integer> tables = new ArrayList<>(tableSet);

        Collections.sort(foods);
        Collections.sort(tables);

        List<List<String>> ans = new ArrayList<>();
        List<String> first = new ArrayList<>();
        first.add("Table");
        first.addAll(foods);
        ans.add(first);

        for (Integer table : tables) {
            List<String> row = new ArrayList<>();
            row.add("" + table);
            for (String food : foods) {
                if (map.containsKey(table)) {
                    Map<String, Integer> innerMap = map.get(table);
                    Integer num = innerMap.getOrDefault(food, 0);
                    row.add("" + num);
                }
            }
            ans.add(row);
        }

        return ans;
    }
}
```
# Contest_3_数青蛙
## 题目
给你一个字符串 croakOfFrogs，它表示不同青蛙发出的蛙鸣声（字符串 "croak" ）的组合。由于同一时间可以有多只青蛙呱呱作响，所以croakOfFrogs 中会混合多个 “croak” 。请你返回模拟字符串中所有蛙鸣所需不同青蛙的最少数目。

注意：要想发出蛙鸣 "croak"，青蛙必须 依序 输出 ‘c’, ’r’, ’o’, ’a’, ’k’ 这 5 个字母。如果没有输出全部五个字母，那么它就不会发出声音。

如果字符串 croakOfFrogs 不是由若干有效的 "croak" 字符混合而成，请返回 -1 。

示例 1：
```
输入：croakOfFrogs = "croakcroak"
输出：1 
解释：一只青蛙 “呱呱” 两次
```
示例 2：
```
输入：croakOfFrogs = "crcoakroak"
输出：2 
解释：最少需要两只青蛙，“呱呱” 声用黑体标注
第一只青蛙 "crcoakroak"
第二只青蛙 "crcoakroak"
```
示例 3：
```
输入：croakOfFrogs = "croakcrook"
输出：-1
解释：给出的字符串不是 "croak" 的有效组合。
```
示例 4：
```
输入：croakOfFrogs = "croakcroa"
输出：-1
```
提示：
```
1 <=croakOfFrogs.length <= 10^5
字符串中的字符只有 'c', 'r', 'o', 'a' 或者 'k'
```
## 解法
### 思路
- 遍历字符串：
    - 如果出现了非k的字符，就将该字符加1
    - 在做字符是否是k的判断前，通过c的个数判断当前青蛙的个数ans
    - 如果出现了k：
        - k的个数+1
        - 所有k之前的元素都依次减1
    - 判断字符的个数是否符合`c >= r >= o >= a >= k`，如果不符合就返回-1
- 遍历结束后判断字符的个数是否都为0，如果不为0，代表叫声不成立，返回-1
- 否则返回ans
### 代码
```java
class Solution {
    public int minNumberOfFrogs(String croakOfFrogs) {
        int c = 0, r = 0, o = 0, a = 0, k = 0, ans = 0;
        for (char ch : croakOfFrogs.toCharArray()) {
            if (ch == 'c') {
                c++;
            } else if (ch == 'r') {
                r++;
            } else if (ch == 'o') {
                o++;
            } else if (ch == 'a') {
                a++;
            } else if (ch == 'k') {
                k++;
            }

            ans = Math.max(ans, c);
            if (ch == 'k') {
                if (c >= r && r >= o && o >= a && a >= k) {
                    c--;
                    r--;
                    o--;
                    a--;
                    k--;
                }
            }
            
            if (!(c >= r && r >= o && o >= a && a >= k)) {
                return -1;
            }
        }
        
        if (c != 0 || r != 0 || o != 0 || a != 0 || k != 0) {
            return -1;
        }
        
        return ans;
    }
}
```
# Contest_4_生成数组
## 题目
给你三个整数 n、m 和 k 。下图描述的算法用于找出正整数数组中最大的元素。
```
search_cost = 0
n = arr.length
for (i = 0; i < n; i++) {
    if (maximum_value < arr[i]) {
        maximum_value = arr[i]
        maximum_index = i
        search_cost = search_cost + 1
    }
}
return maximum_index
```
请你生成一个具有下述属性的数组 arr ：
```
arr 中有 n 个整数。
1 <= arr[i] <= m 其中 (0 <= i < n) 。
将上面提到的算法应用于 arr ，search_cost 的值等于 k 。
返回上述条件下生成数组 arr 的 方法数 ，由于答案可能会很大，所以 必须 对 10^9 + 7 取余。
```
示例 1：
```
输入：n = 2, m = 3, k = 1
输出：6
解释：可能的数组分别为 [1, 1], [2, 1], [2, 2], [3, 1], [3, 2] [3, 3]
```
示例 2：
```
输入：n = 5, m = 2, k = 3
输出：0
解释：没有数组可以满足上述条件
```
示例 3：
```
输入：n = 9, m = 1, k = 1
输出：1
解释：可能的数组只有 [1, 1, 1, 1, 1, 1, 1, 1, 1]
```
示例 4：
```
输入：n = 50, m = 100, k = 25
输出：34549172
解释：不要忘了对 1000000007 取余
```
示例 5：
```
输入：n = 37, m = 17, k = 7
输出：418930126
```
提示：
```
1 <= n <= 50
1 <= m <= 100
0 <= k <= n
```
## 解法
### 思路
动态规划：
- `dp[i][j][k]`：在数组长度为i+1，第i个元素为j的情况下，cost为k的可能数组的个数
- 初始条件：
    - `dp[0][j][1] = 1`，数组长度为1，cost为1，第i个元素为[1,m]的情况下，可能的个数都是1
- 状态转移方程：
    - 四层嵌套：
        - 前三层嵌套遍历整个三维数组的空间
        - 第四层遍历第i + 1个位置元素的可能值ij
    - 如果ij大于j，那么就生成ik = k + 1
    - 那么`dp[i + 1][ij][k + 1] = dp[i][j][k] + 1`
- 返回结果：遍历`dp[N - 1][1...M][K]`情况下所有的可能的和
### 代码
```java
class Solution {
    public int numOfArrays(int n, int m, int k) {
        int mod = 1000000007;
        long[][][] dp = new long[n][m + 1][k + 2];
        for (int j = 1; j <= m; j++) {
            dp[0][j][1] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= m; j++) {
                for (int l = 1; l <= k; l++) {
                    for (int ij = 1; ij <= m; ij++) {
                        int ik = l + (ij > j ? 1 : 0);
                        dp[i][Math.max(ij, j)][ik] += dp[i - 1][j][l];
                        dp[i][Math.max(ij, j)][ik] %= mod;
                    }
                }
            }
        }

        long ans = 0;
        for (int i = 1; i <= m; i++) {
            ans += dp[n - 1][i][k];
            ans %= mod;
        }
        return (int)ans;
    }
}
```