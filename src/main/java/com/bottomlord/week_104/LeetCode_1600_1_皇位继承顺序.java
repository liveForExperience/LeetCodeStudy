package com.bottomlord.week_104;

import java.util.*;

public class LeetCode_1600_1_皇位继承顺序 {
    class ThroneInheritance {
        private Map<String, List<String>> map;
        private Set<String> set;
        private String kingName;

        public ThroneInheritance(String kingName) {
            this.kingName = kingName;
            map = new HashMap<>();
            map.put(kingName, new ArrayList<>());
            set = new HashSet<>();
        }

        public void birth(String parentName, String childName) {
            List<String> list = map.getOrDefault(parentName, new ArrayList<>());
            list.add(childName);
            map.put(parentName, list);
        }

        public void death(String name) {
            set.add(name);
        }

        public List<String> getInheritanceOrder() {
            List<String> ans = new ArrayList<>();
            preOrder(ans, kingName);
            return ans;
        }

        private void preOrder(List<String> ans, String name) {
            if (!set.contains(name)) {
                ans.add(name);
            }

            List<String> list = map.getOrDefault(name, new ArrayList<>());
            for (String s : list) {
                preOrder(ans, s);
            }
        }
    }
}
