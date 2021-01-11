# [LeetCode_1202_交换字符串中的元素](https://leetcode-cn.com/u/liveforexperience/)
## 失败解法
### 思路
无向图+记忆化dfs
### 代码
```java
class Solution {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (List<Integer> pair : pairs) {
            List<Integer> list = map.getOrDefault(pair.get(0), new ArrayList<>());
            list.add(pair.get(1));
            map.put(pair.get(0), list);
            
            List<Integer> list2 = map.getOrDefault(pair.get(1), new ArrayList<>());
            list2.add(pair.get(0));
            map.put(pair.get(1), list2);
        }
        
        
        StringBuilder ans = new StringBuilder();
        Set<Integer> memo = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            Set<Integer> set = new HashSet<>();
            PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(s::charAt));
            dfs(i, map, set, queue);
            while (!queue.isEmpty()) {
                Integer num = queue.poll();
                if (num == null || memo.contains(num)) {
                    continue;
                }
                
                ans.append(s.charAt(num));
                memo.add(num);
                break;
            }
        }
        
        return ans.toString();
    }
    
    private void dfs(int index, Map<Integer, List<Integer>> map, Set<Integer> memo, PriorityQueue<Integer> queue) {
        if (memo.contains(index)) {
            return;
        }
        
        queue.add(index);
        memo.add(index);
        
        List<Integer> list = map.get(index);
        if (list == null) {
            return;
        }
        
        for (Integer i : list) {
            dfs(i, map, memo, queue);
        }
    }
}
```
## 解法
### 思路
连通图+并查集
- 创建一个并查集，要存的元素是s的坐标
- 遍历二维数组，将联通的元素放入并查集中存储
- 遍历字符串s，根据下标在并查集中查找代表元，并更具代表元为key，相同代表元的坐标对应字符组成的小顶堆为value，存入map中
- 遍历字符串s，根据坐标查找并查集的代表元，再通过代表元从map中获取小顶堆，将小顶堆的堆顶元素弹出作为结果字符，追缴到结果字符串中
### 代码
```java
class Solution {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int n = s.length();
        Uf uf = new Uf(n);
        for (List<Integer> pair : pairs) {
            uf.union(pair.get(0), pair.get(1));
        }

        Map<Integer, PriorityQueue<Character>> map = new HashMap<>();
        for (int i = 0; i < uf.parents.length; i++) {
            map.computeIfAbsent(uf.find(i), x -> new PriorityQueue<>()).offer(s.charAt(i));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append(map.get(uf.find(i)).poll());
        }
        return sb.toString();
    }

    private class Uf {
        int[] parents;
        int[] ranks;
        int count;

        public Uf(int n) {
            count = 0;
            parents = new int[n];
            ranks = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
                ranks[i] = 1;
            }
        }

        public void union(int x, int y) {
            int px = find(x), py = find(y);

            if (px == py) {
                return;
            }

            if (ranks[px] < ranks[py]) {
                parents[px] = py;
            } else if (ranks[px] > ranks[py]) {
                parents[py] = px;
            } else {
                parents[py] = px;
                ranks[px]++;
            }
        }

        public int find(int x) {
            if (parents[x] != x) {
                parents[x] = find(parents[x]);
            }

            return parents[x];
        }
    }
}
```