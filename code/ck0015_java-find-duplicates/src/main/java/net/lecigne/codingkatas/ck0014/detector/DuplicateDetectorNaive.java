package net.lecigne.codingkatas.ck0014.detector;

/**
 * "Less" naive implementation of duplicate detection. Every index is checked against the remaining array.
 */
public class DuplicateDetectorNaive implements DuplicateDetector {

    @Override
    public boolean containsDuplicate(int[] numbers) {
        boolean duplicates = false;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] == numbers[j]) {
                    duplicates = true;
                    break;
                }
            }
        }
        return duplicates;
    }

}
