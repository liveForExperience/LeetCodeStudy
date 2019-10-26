package com.bottomlord.week_016;

import com.bottomlord.QuadTreeNode;

public class LeetCode_427_1_建立四叉树 {
    public QuadTreeNode construct(int[][] grid) {
        return recurse(grid, 0, grid.length - 1, 0, grid[0].length - 1);
    }

    private QuadTreeNode recurse(int[][] grid, int rowStart, int rowEnd, int colStart, int colEnd) {
        if (rowStart > rowEnd || colStart > colEnd) {
            return null;
        }

        if (rowStart == rowEnd && colStart == colEnd) {
            return new QuadTreeNode(grid[rowStart][colStart] == 1, true, null, null, null, null);
        }

        QuadTreeNode node = new QuadTreeNode();
        boolean isLeaf = true;
        for (int i = rowStart + 1; i <= rowEnd; i++) {
            for (int j = colStart + 1; j <= colEnd; j++) {
                if (grid[i][j] != grid[i][j - 1] || grid[i - 1][j] != grid[i][j]) {
                    isLeaf = false;
                    break;
                }
            }
            if (!isLeaf) {
                break;
            }
        }

        if (isLeaf) {
            node.isLeaf = true;
            node.val = grid[rowStart][colStart] == 1;
            return node;
        } else {
            node.topLeft = recurse(grid, rowStart, rowStart + (rowEnd - rowStart) / 2, colStart, colStart + (colEnd - colStart) / 2);
            node.topRight = recurse(grid, rowStart, rowStart + (rowEnd - rowStart) / 2, colStart + (colEnd - colStart) / 2 + 1, colEnd);
            node.bottomLeft = recurse(grid, rowStart + (rowEnd - rowStart) / 2 + 1, rowEnd, colStart, colStart + (colEnd - colStart) / 2);
            node.bottomRight = recurse(grid, rowStart + (rowEnd - rowStart) / 2 + 1, rowEnd, colStart + (colEnd - colStart) / 2 + 1, colEnd);
        }
        return node;
    }
}
