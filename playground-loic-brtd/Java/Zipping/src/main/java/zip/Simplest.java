package zip;

import java.util.List;
import java.util.function.BiConsumer;

public class Simplest {

    public static <A, B> void zip(Iterable<A> a, Iterable<B> b, BiConsumer<A, B> f) {
        var itA = a.iterator();
        var itB = b.iterator();
        while (itA.hasNext() && itB.hasNext()) {
            f.accept(itA.next(), itB.next());
        }
    }

    public static void main(String[] args) {
        var integers = List.of(1, 2, 3);
        var strings = List.of("one", "two", "three");

        zip(integers, strings, (l, r) -> {
            System.out.format("(%s, %s)\n", l, r);
        });
    }

}
