# LeetCode_816_模糊坐标
## 题目
我们有一些二维坐标，如 "(1, 3)" 或 "(2, 0.5)"，然后我们移除所有逗号，小数点和空格，得到一个字符串S。返回所有可能的原始字符串到一个列表中。

原始的坐标表示法不会存在多余的零，所以不会出现类似于"00", "0.0", "0.00", "1.0", "001", "00.01"或一些其他更小的数来表示坐标。此外，一个小数点前至少存在一个数，所以也不会出现“.1”形式的数字。

最后返回的列表可以是任意顺序的。而且注意返回的两个数字中间（逗号之后）都有一个空格。

示例 1:
```
输入: "(123)"
输出: ["(1, 23)", "(12, 3)", "(1.2, 3)", "(1, 2.3)"]
示例 2:
输入: "(00011)"
输出:  ["(0.001, 1)", "(0, 0.011)"]
解释: 
0.0, 00, 0001 或 00.01 是不被允许的。
示例 3:
输入: "(0123)"
输出: ["(0, 123)", "(0, 12.3)", "(0, 1.23)", "(0.1, 23)", "(0.1, 2.3)", "(0.12, 3)"]
示例 4:
输入: "(100)"
输出: [(10, 0)]
解释: 
1.0 是不被允许的。
```
提示:
```
4 <= S.length <= 12.
S[0] = "(", S[S.length - 1] = ")", 且字符串 S 中的其他元素都是数字。
```
## 解法
### 思路
将字符串分成两部分进行枚举：
- 过程是3重循环：
    - 外部循环确定切分字符串的位置
    - 在外层确定两个部分可能存在的所有字符串可能
    - 剩下两层用来嵌套组装成结果要求的字符串
- 外层寻找可能的过程中，需要特别处理的几种情况：
    - 字符串首个字符为0：
        - 如果长度为1，直接存
        - 如果尾字符不是0，可以在首个字符后加`.`
        - 如果尾字符是0，则不能称为有效数字，例如`01230`
    - 字符串首字符不是0：
        - 尾字符串是0，则不能加`.`，直接存
        - 尾字符串不是0，则需要遍历所有加`.`的可能并保存
### 代码
```java
class Solution {
    public List<String> ambiguousCoordinates(String S) {
        int len = S.length();
        List<String> ans = new ArrayList<>();

        for (int i = 1; i < len - 2; i++) {
            List<String> lefts = find(S.substring(1, i + 1));
            List<String> rights = find(S.substring(i + 1, len - 1));

            for (String left : lefts) {
                for (String right : rights) {
                    ans.add("(" + left + ", " + right + ")");
                }
            }
        }

        return ans;
    }

    private List<String> find(String str) {
        List<String> list = new ArrayList<>();
        if (str.charAt(0) == '0') {
            if (str.length() > 1) {
                if (str.charAt(str.length() - 1) != '0') {
                    list.add("0." + str.substring(1));
                }
            } else {
                list.add(str);
            }
        } else {
            if (str.length() == 1 || str.charAt(str.length() - 1) == '0') {
                list.add(str);
            } else {
                for (int i = 1; i < str.length(); i++) {
                    list.add(str.substring(0, i) + "." + str.substring(i));
                }
                list.add(str);
            }
        }
        return list;
    }
}
```
# LeetCode_433_最小基因变化
## 题目
一条基因序列由一个带有8个字符的字符串表示，其中每个字符都属于 "A", "C", "G", "T"中的任意一个。

假设我们要调查一个基因序列的变化。一次基因变化意味着这个基因序列中的一个字符发生了变化。

例如，基因序列由"AACCGGTT" 变化至 "AACCGGTA" 即发生了一次基因变化。

与此同时，每一次基因变化的结果，都需要是一个合法的基因串，即该结果属于一个基因库。

现在给定3个参数 — start, end, bank，分别代表起始基因序列，目标基因序列及基因库，请找出能够使起始基因序列变化为目标基因序列所需的最少变化次数。如果无法实现目标变化，请返回 -1。

注意:
```
起始基因序列默认是合法的，但是它并不一定会出现在基因库中。
所有的目标基因序列必须是合法的。
假定起始基因序列与目标基因序列是不一样的。
```
示例 1:
```
start: "AACCGGTT"
end:   "AACCGGTA"
bank: ["AACCGGTA"]

返回值: 1
```
示例 2:
```
start: "AACCGGTT"
end:   "AAACGGTA"
bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]

返回值: 2
```
示例 3:
```
start: "AAAAACCC"
end:   "AACCCCCC"
bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]

返回值: 3
```
## 解法
### 思路
bfs：
- 初始化变量:
    - `queue`：驱动bfs，将`start`压入
    - `set`：保存生成过的字符串，添加`start`
    - `level`：暂存改变字符串的次数，也就是bfs的层数
- 循环：
    - 退出条件：
        - 队列为空：返回-1，说明所有可能遍历过了，没有匹配的
        - 循环过程中变换成了要求的字符串，返回`level`
    - 过程：
        - 将每一层的所有字符串遍历一遍
        - 如果当前字符串和目标的相同，返回
        - 每个字符串的每个不同字符都依次替换成`A,T,G,C`，并判断是否符合基因库和是否已经生成过，如果都符合就放入队列
        - 进行下一个循环
### 代码
```java
class Solution {
    public int minMutation(String start, String end, String[] bank) {
        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));
        Queue<String> queue = new LinkedList<>();
        Set<String> set = new HashSet<>();
        int level = 0;

        queue.offer(start);
        bankSet.add(start);
        
        char[] genes = new char[]{'A', 'T', 'G', 'C'};

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                String str = queue.poll();
                if (str == null) {
                    continue;
                }

                if (Objects.equals(str, end)) {
                    return level;
                }

                for (int i = 0; i < str.length(); i++) {
                    char[] cs = str.toCharArray();
                    for (int j = 0; j < 4; j++) {
                        if (cs[i] != genes[j]) {
                            cs[i] = genes[j];
                        }

                        String tmp = new String(cs);

                        if (!set.contains(tmp) && bankSet.contains(tmp)) {
                            set.add(tmp);
                            queue.offer(tmp);
                        }
                    }
                }
            }

            level++;
        }

        return -1;
    }
}
```