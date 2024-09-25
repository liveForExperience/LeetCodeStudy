package com.bottomlord.week_133;

import java.util.*;

/**
 * @author chen yue
 * @date 2022-01-26 08:47:40
 */
public class LeetCode_2013_1_检测正方形 {
    class DetectSquares {
        private final Map<Integer, int[]> rowMap;

        public DetectSquares() {
            this.rowMap = new HashMap<>();
        }

        public void add(int[] point) {
            rowMap.computeIfAbsent(point[0], x -> new int[1001])[point[1]]++;
        }

        public int count(int[] point) {
            int row = point[0], col = point[1];
            if (!rowMap.containsKey(row) || rowMap.get(row)[col] <= 0) {
                return 0;
            }

            int[] colSet = rowMap.get(row);

            int count = 0;
            for (int candidateCol = 0; candidateCol < colSet.length; candidateCol++) {
                if (colSet[candidateCol] == 0) {
                    continue;
                }

                int candidateLen = Math.abs(candidateCol - col);

                int positiveCandidateRow = row + candidateLen,
                    negativeCandidateRow = row - candidateLen;

                if (positiveCandidateRow <= 1000 &&
                    rowMap.containsKey(positiveCandidateRow) &&
                    rowMap.get(positiveCandidateRow)[candidateCol] > 0) {
                    count += colSet[candidateCol] * rowMap.get(positiveCandidateRow)[col] * rowMap.get(positiveCandidateRow)[candidateCol];
                }

                if (negativeCandidateRow >= 0 &&
                        rowMap.containsKey(negativeCandidateRow) &&
                        rowMap.get(negativeCandidateRow)[candidateCol] > 0) {
                    count += colSet[candidateCol] * rowMap.get(negativeCandidateRow)[col] * rowMap.get(negativeCandidateRow)[candidateCol];
                }
            }

            return count;
        }
    }

}
