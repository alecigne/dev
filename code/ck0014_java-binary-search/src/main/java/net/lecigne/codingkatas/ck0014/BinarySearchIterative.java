package net.lecigne.codingkatas.ck0014;

public class BinarySearchIterative implements BinarySearch {

    @Override
    public int search(int[] sortedArray, int target) {
        int lowIndex = 0;
        int highIndex = sortedArray.length - 1;

        while (lowIndex <= highIndex) {
            int midIndex = (lowIndex + highIndex) / 2;
            int currentValue = sortedArray[midIndex];

            if (currentValue == target) {
                return midIndex;
            } else if (currentValue > target) {
                highIndex = midIndex - 1;
            } else {
                lowIndex = midIndex + 1;
            }
        }

        return -1;
    }

}
