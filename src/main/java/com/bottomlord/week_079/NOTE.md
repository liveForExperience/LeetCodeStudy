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
# [LeetCode_1203_项目管理](https://leetcode-cn.com/problems/sort-items-by-groups-respecting-dependencies/)
## 解法
### 思路
拓扑排序
- 因为题目要求同一组的项目需要相邻，这也就意味着组与组之间也存在依赖关系
- 所以求解的过程就是先针对组做拓扑排序，排序完成后，再遍历每个组，针对组对应的项目再做拓扑排序，然后将排序出的项目一一放入结果中
- 因为没有组跟进的项目，在group中的值是-1，为了不让初始化group和item映射关系时认为这些项目都对应一个组，所以需要将他们都逐一标记为大于m的值，也就是说m最大会扩容为m+n，多出来的n就是存放可能为-1的这些项目
- 一个取巧的做法就是，遍历group数组，然后将数组对应的值用递增的m来代替，这样不仅更新了这些为-1的项目，同时也更新了组的最大值
- 然后初始化组和项目的邻接表，维护顶点的出度
- 遍历group，获取当前项目对应的group下标，然后再从`beforeItems`中通过group的下标，也就是项目的id，取出对应项目顶点的入度，通过这个入度再从group中取group的id，看一下当前group和beforeItem的group是否是同一组，如果不是同一组，那说明这两个组之间有依赖关系，维护进组的group的邻接表里，同时当前组的入度数+1
- 再遍历所有item，也就是遍历n个数，然后从beforeItem中取出入度，再维护进item的邻接表里，同时也维护当前item的入度数
- 通过group和item的邻接表及入度数，就能对group和item进行拓扑排序
    - 拓扑排序时，先准备一个结果list和一个驱动排序的queue
    - 遍历入度计数集合，将入度数为0的值放入队列中
    - 遍历queue，每个循环时，将queue中的元素弹出，将当前顶点放入ans集合中，作为已经完成排序的顶点
    - 然后通过该顶点值从邻接表里拿到当前顶点的边
    - 遍历这些边，并将对应边的顶点值的入度数减1
    - 如果该顶点的入度数为0了，说明该顶点入度对应的顶点都已经被遍历过，可以放入queue中开始继续排序
    - 遍历直到queue为空为止
    - 如果list的size和顶点总数不等，说明有环，就返回空集合
- 获取item的拓扑排序后，再配合group集合，生成组与排序的item之间的映射关系map
- 再通过group的拓扑排序，再配合map，获取最终的项目排序
### 代码
```java
class Solution {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        for (int i = 0; i < group.length; i++) {
            if (group[i] == -1) {
                group[i] = m++;
            }
        }

        List<List<Integer>> groupAds = new ArrayList<>(), itemAds = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            groupAds.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            itemAds.add(new ArrayList<>());
        }

        int[] inGroup = new int[m], inItem = new int[n];

        for (int i = 0; i < group.length; i++) {
            int curGroup = group[i];
            for (Integer beforeItem: beforeItems.get(i)) {
                int beforeGroup = group[beforeItem];
                if (curGroup != beforeGroup) {
                    groupAds.get(beforeGroup).add(curGroup);
                    inGroup[curGroup]++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (Integer beforeItem : beforeItems.get(i)) {
                itemAds.get(beforeItem).add(i);
                inItem[i]++;
            }
        }

        List<Integer> orderedGroups = sort(groupAds, inGroup, m),
                      orderedItems = sort(itemAds, inItem, n);

        Map<Integer, List<Integer>> groupItemMap = new HashMap<>();
        for (Integer item : orderedItems) {
            groupItemMap.computeIfAbsent(group[item], key -> new ArrayList<>()).add(item);
        }

        List<Integer> ans = new ArrayList<>();
        for (Integer orderedGroup : orderedGroups) {
            List<Integer> items = groupItemMap.getOrDefault(orderedGroup, new ArrayList<>());
            ans.addAll(items);
        }

        return ans.stream().mapToInt(Integer :: valueOf).toArray();
    }

    private List<Integer> sort(List<List<Integer>> ads, int[] ins, int count) {
        List<Integer> ans = new ArrayList<>();
        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < ins.length; i++) {
            if (ins[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            Integer p = queue.poll();
            if (p == null) {
                continue;
            }
            ans.add(p);

            for (Integer out : ads.get(p)) {
                ins[out]--;

                if (ins[out] == 0) {
                    queue.offer(out);
                }
            }
        }

        if (ans.size() == count) {
            return ans;
        }

        return new ArrayList<>();
    }
}
```
# [LeetCode_422_有效的单词方块](https://leetcode-cn.com/problems/valid-word-square/)
## 解法
### 思路
遍历二维数组做判断
### 代码
```java
class Solution {
    public boolean validWordSquare(List<String> words) {
        int row = words.size();
        if (row == 0) {
            return true;
        }

        for (int i = 0; i < row; i++) {
            String word = words.get(i);
            for (int j = 0; j < word.length(); j++) {
                if (j >= words.size()) {
                    return false;
                }
                
                if (i >= words.get(j).length()) {
                    return false;
                }
                
                if (words.get(j).charAt(i) != word.charAt(j)) {
                    return false;
                }
            }
        }

        return true;
    }
}
```