# [LeetCode_726_原子的数量](https://leetcode-cn.com/problems/number-of-atoms/)
## 解法
### 思路
栈+map
- 使用map记录一个括号内的原子和原子个数的映射关系（也可能没有括号）
- 使用栈+map来记录遍历的过程中产生的不同层次的括号内的状态
- 遍历的过程分成3种情况：
    - 遇到左括号，说明找到了一个新的括号，也说明需要一个新的map，此时将一个新的map压入栈中，并继续遍历
    - 遇到右括号，说明一个括号的内容遍历完了，需要对该括号内的原子与其外层的原子数做合并
    - 如果不是左括号也不是有括号，就正常遍历解析字符串，并放入当前层map中，解析一次就是一组原子和原子数的组合
- 遍历完字符串后，就将栈顶的代表整个字符串内容的map弹出，然后按照字典序排列，可以用treemap
- 然后按照题目要求拼接字符串
### 代码
```java
class Solution {
    private String formula;
    private int i, n;

    public String countOfAtoms(String formula) {
        this.formula = formula;
        this.i = 0;
        this.n = formula.length();

        Stack<Map<String, Integer>> stack = new Stack<>();
        stack.push(new HashMap<>());

        while (i < n) {
                        if (formula.charAt(i) == '(') {
                i++;
                stack.push(new HashMap<>());
            } else if (formula.charAt(i) == ')') {
                i++;
                int num = parseNum();
                Map<String, Integer> popMap = stack.pop(), topMap = stack.peek();
                for (Map.Entry<String, Integer> entry : popMap.entrySet()) {
                    String atom = entry.getKey();
                    topMap.put(atom, topMap.getOrDefault(atom, 0) + entry.getValue() * num);
                }
            } else {
                String atom = parseAtom();
                int num = parseNum();
                Map<String, Integer> topMap = stack.peek();
                topMap.put(atom, topMap.getOrDefault(atom, 0) + num);
            }
        }
        
        TreeMap<String, Integer> treeMap = new TreeMap<>(stack.peek());
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : treeMap.entrySet()) {
            sb.append(entry.getKey());
            if (entry.getValue() > 1) {
                sb.append(entry.getValue());
            }
        }
        return sb.toString();
    }

    private int parseNum() {
        if (i == n || !Character.isDigit(formula.charAt(i))) {
            return 1;
        }
        
        int num = 0;
        while (i < n && Character.isDigit(formula.charAt(i))) {
            num = num * 10 + (formula.charAt(i) - '0');
            i++;
        }
        return num;
    }

    private String parseAtom() {
        StringBuilder sb = new StringBuilder();
        sb.append(formula.charAt(i++));
        while (i < n && Character.isLowerCase(formula.charAt(i))) {
            sb.append(formula.charAt(i++));
        }
        return sb.toString();
    }
}
```
# [LeetCode_1418_点菜展示表](https://leetcode-cn.com/problems/display-table-of-food-orders-in-a-restaurant/)
## 解法
### 思路
- 遍历orders，TreeSet集合存储菜名作为表头
- 初始化一个TreeMap，用于存储桌子和菜名及数量的映射关系，value初始化为一个map，这个map的key就是TreeSet的值
- 遍历TreeSet生成表头
- 遍历TreeMap，内部遍历value位置的map，生成二维表的数据
### 代码
```java
class Solution {
    public List<List<String>> displayTable(List<List<String>> orders) {
         TreeSet<String> set = new TreeSet<>();
        TreeMap<String, TreeMap<String, Integer>> map = new TreeMap<>(Comparator.comparingInt(Integer::parseInt));
        for (List<String> order : orders) {
            set.add(order.get(2));
            map.put(order.get(1), new TreeMap<>());
        }

        for (Map.Entry<String, TreeMap<String, Integer>> entry : map.entrySet()) {
            TreeMap<String, Integer> treeMap = entry.getValue();
            for (String str : set) {
                treeMap.put(str, 0);
            }
        }

        for (List<String> order : orders) {
            TreeMap<String, Integer> treeMap = map.get(order.get(1));
            treeMap.put(order.get(2), treeMap.getOrDefault(order.get(2), 0) + 1);
        }

        List<List<String>> ans = new ArrayList<>();
        List<String> head = new ArrayList<>();
        head.add("Table");
        head.addAll(set);
        ans.add(head);

        for (Map.Entry<String, TreeMap<String, Integer>> entry : map.entrySet()) {
            List<String> list = new ArrayList<>();
            list.add(entry.getKey());
            list.addAll(entry.getValue().values().stream().map(String::valueOf).collect(Collectors.toList()));
            ans.add(list);
        }

        return ans;
    }
}
```