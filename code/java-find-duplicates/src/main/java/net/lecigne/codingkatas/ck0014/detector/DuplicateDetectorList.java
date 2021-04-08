package net.lecigne.codingkatas.ck0014.detector;

import java.util.ArrayList;
import java.util.List;

public class DuplicateDetectorList implements DuplicateDetector {

    @Override
    public boolean containsDuplicate(int[] numbers) {
        boolean duplicates = false;
        List<Integer> alreadyThere = new ArrayList<>();
        for (Integer i : numbers) {
            if (alreadyThere.contains(i)) {
                duplicates = true;
                break;
            }
            alreadyThere.add(i);
        }
        return duplicates;
    }

}
