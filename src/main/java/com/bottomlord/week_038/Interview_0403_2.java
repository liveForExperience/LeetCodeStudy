package com.bottomlord.week_038;

import com.bottomlord.ListNode;
import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/3/23 12:47
 */
public class Interview_0403_2 {
    public ListNode[] listOfDepth(TreeNode tree) {
        List<ListNode> list = new ArrayList<>();
        dfs(list, tree, 0);
        return list.toArray(new ListNode[0]);
    }

    private void dfs(List<ListNode> list, TreeNode node, int depth) {
        if (node == null) {
            return;
        }

        if (depth >= list.size()) {
            list.add(new ListNode(node.val));
        } else {
            ListNode listNode = list.get(depth);
            while (listNode.next != null) {
                listNode = listNode.next;
            }

            listNode.next = new ListNode(node.val);
        }

        depth++;
        dfs(list, node.left, depth);
        dfs(list, node.right, depth);
    }
}
