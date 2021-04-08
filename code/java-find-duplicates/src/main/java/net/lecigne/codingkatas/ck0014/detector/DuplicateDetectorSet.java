package net.lecigne.codingkatas.ck0014.detector;

import java.util.HashSet;

public class DuplicateDetectorSet implements DuplicateDetector {

    @Override
    public boolean containsDuplicate(int[] numbers) {
        boolean duplicates = false;
        HashSet<Integer> alreadyThere = new HashSet<>();
        for (Integer integer : numbers) {
            if (!alreadyThere.add(integer)) {
                duplicates = true;
                break;
            }
        }
        return duplicates;
    }

}
