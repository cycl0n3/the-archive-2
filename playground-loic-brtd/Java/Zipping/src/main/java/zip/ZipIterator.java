package zip;

import java.util.Iterator;
import java.util.function.BiFunction;

class ZipIterator<A, B, C> implements Iterator<C> {

    private final Iterator<A> left;
    private final Iterator<B> right;
    private final BiFunction<A, B, C> combiner;

    public ZipIterator(Iterator<A> left, Iterator<B> right, BiFunction<A, B, C> combiner) {
        this.left = left;
        this.right = right;
        this.combiner = combiner;
    }

    @Override
    public boolean hasNext() {
        return left.hasNext() && right.hasNext();
    }

    @Override
    public C next() {
        return combiner.apply(left.next(), right.next());
    }
}
