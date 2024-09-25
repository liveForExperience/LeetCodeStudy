package com.bottomlord.week_025;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ThinkPad
 * @date 2019/12/28 8:30
 */
public class LeetCode_1268_1_搜索推荐系统 {
    private TrieNode root;
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        this.root = new TrieNode();
        for (String product : products) {
            insertProduct(product);
        }

        List<List<String>> ans = new ArrayList<>();
        for (int i = 1; i <= searchWord.length(); i++) {
            ans.add(search(searchWord.substring(0, i)));
        }

        return ans;
    }

    private void insertProduct(String product) {
        TrieNode node = root;
        for (char c : product.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new TrieNode();
            }
            node = node.children[c - 'a'];
        }
        if (!node.end) {
            node.end = true;
            node.product = product;
        }
        node.count++;
    }

    private List<String> search(String pattern) {
        List<String> result = new ArrayList<>();
        TrieNode node = root;
        for (char c : pattern.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                return result;
            }

            node = node.children[c - 'a'];
        }

        dfs(node, result);
        return result;
    }

    private void dfs(TrieNode node, List<String> result) {
        if (node.end) {
            for (int i = 0; i < node.count; i++) {
                result.add(node.product);
                if (result.size() >= 3) {
                    return;
                }
            }
        }

        for (TrieNode trieNode : node.children) {
            if (trieNode != null) {
                dfs(trieNode, result);
            }

            if (result.size() >= 3) {
                return;
            }
        }
    }
}
