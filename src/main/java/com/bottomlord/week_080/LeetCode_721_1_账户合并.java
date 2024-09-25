package com.bottomlord.week_080;

import java.util.*;

/**
 * @author ChenYue
 * @date 2021/1/18 8:43
 */
public class LeetCode_721_1_账户合并 {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Integer> emailIndexMap = new HashMap<>();
        Map<String, String> emailAccountMap = new HashMap<>();

        int emailIndex = 0;
        for (List<String> account : accounts) {
            String name = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                if (!emailIndexMap.containsKey(email)) {
                    emailIndexMap.put(email, emailIndex++);
                    emailAccountMap.put(email, name);
                }
            }
        }

        Uf uf = new Uf(emailIndex);
        for (List<String> account : accounts) {
            String firstEmail = account.get(1);
            int firstIndex = emailIndexMap.get(firstEmail);

            for (int i = 2; i < account.size(); i++) {
                String nextEmail = account.get(i);
                int nextIndex = emailIndexMap.get(nextEmail);
                uf.union(firstIndex, nextIndex);
            }
        }

        Map<Integer, List<String>> indexEmailListMap = new HashMap<>();
        for (String email : emailIndexMap.keySet()) {
            int index = uf.find(emailIndexMap.get(email));
            indexEmailListMap.computeIfAbsent(index, x -> new ArrayList<>()).add(email);
        }

        List<List<String>> ans = new ArrayList<>();
        for (List<String> emails : indexEmailListMap.values()) {
            String account = emailAccountMap.get(emails.get(0));
            Collections.sort(emails);
            List<String> list = new ArrayList<>();
            list.add(account);
            list.addAll(emails);
            ans.add(list);
        }

        return ans;
    }

    private static class Uf {
        private final int[] parent;
        private final int[] rank;

        public Uf(int n) {
            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public void union(int x, int y) {
            int rootX = find(x), rootY = find(y);

            if (rootX == rootY) {
                return;
            }

            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                rank[rootX]++;
                parent[rootY] = rootX;
            }
        }

        public int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }

            return parent[x];
        }
    }
}
