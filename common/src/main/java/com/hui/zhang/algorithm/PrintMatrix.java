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

    public static void main(String[] args) {
        int[][] a = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        printMatrixDiagonal(a);
    }

    private static void printMatrixDiagonal(int[][] matrix) {
        int size = matrix.length;
        int len = 2 * size - 1;
        for (int k = 0; k < len; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (j - i == size - k - 1) {
                        System.out.printf(matrix[i][j] + ",");
                    }
                }
            }
        }
    }

    private static void printMatrixInCircle(int[][] matrix) {
        int tR = 0;
        int tC = 0;
        int dR = matrix.length - 1;
        int dC = matrix[0].length - 1;
        while (tR <= dR && dC <= dR) {
            printEdge(matrix, tR++, tC++, dR--, dC--);
        }
    }

    /**
     * 打印边界
     *
     * @param m
     * @param tR
     * @param tC
     * @param dR
     * @param dC
     */
    private static void printEdge(int[][] m, int tR, int tC, int dR, int dC) {
        if (tR == dR) {//只有一行
            for (int i = tC; i <= dC; i++) {
                System.out.printf(m[tR][i] + " ");
            }
        } else if (tC == dC) {//只有一列
            for (int i = tR; i < dR; i++) {
                System.out.printf(m[i][tC] + " ");
            }
        } else {
            int curC = tC;
            int curR = tR;
            while (curC != dC) {//从左到右
                System.out.printf(m[tR][curC] + "");
                curC++;
            }
            while (curR != dR) {//从上到下
                System.out.printf(m[curR][dC] + "");
                curR++;
            }
            while (curC != tC) {//从左到有
                System.out.printf(m[dR][curC] + "");
                curC--;
            }
            while (curR != tR) {
                System.out.printf(m[curR][tC] + "");
                curR--;
            }

        }
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
