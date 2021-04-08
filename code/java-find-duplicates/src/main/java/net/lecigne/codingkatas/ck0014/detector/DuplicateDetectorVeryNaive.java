package net.lecigne.codingkatas.ck0014.detector;

/**
 * Very naive implementation of duplicate detection. Every index is checked against the entire array everytime (minus of
 * course the current index).
 */
public class DuplicateDetectorVeryNaive implements DuplicateDetector {

    @Override
    public boolean containsDuplicate(int[] numbers) {
        boolean duplicates = false;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                if (i != j && numbers[i] == numbers[j]) {
                    duplicates = true;
                    break;
                }
            }
        }
        return duplicates;
    }

}
