package com.epam.autotasks.collections;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

class RangedOpsIntegerSet extends AbstractSet<Integer> {

    private final Set<Integer> values = new TreeSet<>();

    public boolean add(int fromInclusive, int toExclusive) {
        boolean modified = false;
        for (int i = fromInclusive; i < toExclusive; i++) {
            if (values.add(i)) {
                modified = true;
            }
        }
        return modified;
    }

    public boolean remove(int fromInclusive, int toExclusive) {
        boolean modified = false;
        for (int i = fromInclusive; i < toExclusive; i++) {
            if (values.remove(i)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean add(final Integer integer) {
        return values.add(integer);
    }

    @Override
    public boolean remove(final Object o) {
        return values.remove(o);
    }

    @Override
    public Iterator<Integer> iterator() {
        return values.iterator();
    }

    @Override
    public int size() {
        return values.size();
    }
}