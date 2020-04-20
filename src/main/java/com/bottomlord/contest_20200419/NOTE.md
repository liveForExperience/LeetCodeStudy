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
1 <= orders.length <= 5 * 10^4
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