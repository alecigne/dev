package net.lecigne.codingkatas.ck0014;

public class BinarySearchRecursive implements BinarySearch {

    @Override
    public int search(int[] sortedArray, int value) {
        return recursiveHelper(sortedArray, value, 0, sortedArray.length - 1);
    }

    private int recursiveHelper(int[] sortedArray, int value, int leftIndex, int rightIndex) {
        int middle = (leftIndex + rightIndex) / 2;

        if (leftIndex > rightIndex) {
            return -1;
        }

        if (sortedArray[middle] > value) {
            return recursiveHelper(sortedArray, value, leftIndex, middle - 1);
        } else if (sortedArray[middle] < value) {
            return recursiveHelper(sortedArray, value, middle + 1, rightIndex);
        } else {
            return middle;
        }
    }

}
