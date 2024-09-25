package com.bottomlord.week_218;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author chen yue
 * @date 2023-09-24 21:53:37
 */
public class LeetCode_1993_1_树上的操作 {
    class LockingTree {

        private int[] parents, lockUsers;
        private List<Integer>[] children;

        public LockingTree(int[] parents) {
            int n = parents.length;
            this.parents = parents;
            this.lockUsers = new int[n];
            Arrays.fill(lockUsers, -1);
            this.children = new List[n];
            for (int i = 0; i < n; i++) {
                children[i] = new ArrayList();
            }

            for (int i = 0; i < n; i++) {
                int parent = parents[i];
                if (parent == -1) {
                    continue;
                }
                
                children[parent].add(i);
            }
        }

        public boolean lock(int num, int user) {
            if (lockUsers[num] == -1) {
                lockUsers[num] = user;
                return true;
            }
            
            return false;
        }

        public boolean unlock(int num, int user) {
            if (lockUsers[num] == user) {
                lockUsers[num] = -1;
                return true;
            }
            
            return false;
        }

        public boolean upgrade(int num, int user) {
            boolean ans = lockUsers[num] == -1 && !hasLockedAncestor(num) && checkAndUnlockChildren(num);
            if (ans) {
                lockUsers[num] = user;
            }
            return ans;
        }
        
        private boolean hasLockedAncestor(int num) {
            int parent = parents[num];
            while (parent != -1) {
                if (lockUsers[parent] != -1) {
                    return true;
                }

                parent = parents[parent];
            }

            return false;
        }

        private boolean checkAndUnlockChildren(int num) {
            boolean hasLockedChild = lockUsers[num] != -1;
            lockUsers[num] = -1;

            for (Integer child : children[num]) {
                hasLockedChild |= checkAndUnlockChildren(child);
            }

            return hasLockedChild;
        }
    }
}
