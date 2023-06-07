package zip;

import java.util.Iterator;

public class InfiniteIntIterator implements Iterator<Integer> {

    private int current = 0;

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Integer next() {
        return current++;
    }
}
