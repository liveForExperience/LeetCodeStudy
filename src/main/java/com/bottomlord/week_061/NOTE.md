# LeetCode_249_移位字符串数组
## 题目
给定一个字符串，对该字符串可以进行 “移位” 的操作，也就是将字符串中每个字母都变为其在字母表中后续的字母，比如："abc" -> "bcd"。这样，我们可以持续进行 “移位” 操作，从而生成如下移位序列：
```
"abc" -> "bcd" -> ... -> "xyz"
给定一个包含仅小写字母字符串的列表，将该列表中所有满足 “移位” 操作规律的组合进行分组并返回。
```
示例：
```
输入：["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"]
输出：
[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]
解释：可以认为字母表首尾相接，所以 'z' 的后续为 'a'，所以 ["az","ba"] 也满足 “移位” 操作规律。
```
## 解法
### 思路
三层循环+记忆化搜索
- 外层循环确定起始字符串
- 中层循环寻找移位的字符串
- 内层比较每一位字符的`(字符差值 + 26) % 26`是否相等
- 在每次开始寻找和确定找到的时候，将字符串放入记忆化布尔数组中标志
### 代码
```java
class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        int len = strings.length;
        boolean[] memo = new boolean[len];
        List<List<String>> ans = new ArrayList<>();
        
        for (int i = 0; i < len; i++) {
            if (memo[i]) {
                continue;
            }
            
            memo[i] = true;
            
            String s1 = strings[i];
            List<String> list = new ArrayList<String>(){{this.add(s1);}};
            
            for (int j = i + 1; j < len; j++) {
                String s2 = strings[j];
                if (memo[j] || s1.length() != s2.length()) {
                    continue;
                }
                
                int dis = (s1.charAt(0) - s2.charAt(0) + 26) % 26;
                boolean flag = true;
                for (int k = 1; k < s1.length(); k++) {
                    if ((s1.charAt(k) - s2.charAt(k) + 26) % 26 != dis) {
                        flag = false;
                        break;
                    }
                }
                
                if (flag) {
                    list.add(s2);
                    memo[j] = true;
                }
            }
            
            ans.add(list);
        }
        
        return ans;
    }
}
```