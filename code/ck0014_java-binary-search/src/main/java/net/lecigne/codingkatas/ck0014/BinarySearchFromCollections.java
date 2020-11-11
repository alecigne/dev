package net.lecigne.codingkatas.ck0014;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BinarySearchFromCollections implements BinarySearch {

    @Override
    public int search(int[] sortedArray, int target) {
        List<Integer> listToSearch = Arrays.stream(sortedArray).boxed().collect(Collectors.toUnmodifiableList());
        int index = Collections.binarySearch(listToSearch, target);
        return index >= 0 ? index : -1;
    }

}
