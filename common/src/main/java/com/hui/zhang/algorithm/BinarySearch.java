package com.hui.zhang.algorithm;

/**
 * @author zhanghui32
 * @date 2017/7/11
 * 二分查找 场景：对有序列表进行查找
 */
public class BinarySearch {

    /**
     * 普通实现
     * @param arr
     * @param des
     * @return
     */
    public int binarySearch(int[] arr, int des) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int middle = low + ((high + low) >> 1);
            if (des == arr[middle]) {
                return middle;
            } else if (des < arr[middle]) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }
        return -1;
    }

    /**
     * 递归实现
     * @param arr
     * @param low
     * @param high
     * @param key
     * @return
     */
    public int binarySearch(int[] arr, int low, int high, int key) {
        if (low <= high) {
            int mid = (low + high) / 2;
            if (key == arr[mid]) {
                return mid;
            } else if (key < arr[mid]) {
                return binarySearch(arr, low, mid - 1, key);
            } else if (key > arr[mid]) {
                return binarySearch(arr, mid + 1, high, key);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 4, 5, 7, 9, 34, 67, 89, 360};
        BinarySearch binarySearch = new BinarySearch();
        int i = binarySearch.binarySearch(arr, 9);
        System.out.println("value:" + i);
    }
}
