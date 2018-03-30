package com.hui.zhang.algorithm;

import java.util.ArrayList;
import java.util.List;

public class PrintMatrix {


    public static List<Integer> printMatrix(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null) {
            return result;
        }
        int m = matrix.length;
        int n = 0;
        if (m > 0) {
            n = matrix[0].length;
        } else {
            return result;
        }
        int start = 0;
        while (n > start * 2 && m > start * 2) {
            printMatrixInCircle(matrix, m, n, start, result);
            start++;
        }
        return result;
    }

    public static void printMatrixInCircle(int[][] matrix, int rows, int columns, int start, List result) {
        int endX = columns - 1 - start;
        int endY = rows - 1 - start;
        //从左到右
        for (int i = start; i <= endX; i++) {
            result.add(matrix[start][i]);
        }
        //从上到下
        if (start < endY) {
            for (int i = start + 1; i <= endY; i++) {
                result.add(matrix[i][endX]);
            }
        }
        //从右往左
        if (start < endX && start < endY) {
            for (int i = endX - 1; i >= start; i--) {
                result.add(matrix[endY][i]);
            }
        }
        //从下到上
        if (start < endX && start < endY - 1) {
            for (int i = endY - 1; i >= start + 1; i--) {
                result.add(matrix[i][start]);
            }
        }
    }
}
