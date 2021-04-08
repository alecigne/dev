package net.lecigne.codingkatas.ck0014;

import java.util.Arrays;

public class BinarySearchFromArrays implements BinarySearch {

    @Override
    public int search(int[] sortedArray, int target) {
        int index = Arrays.binarySearch(sortedArray, target);
        return index >= 0 ? index : -1;
    }

}
